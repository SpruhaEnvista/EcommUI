package com.envista.msi.api.web.rest.util.audit.parcel;

import com.envista.msi.api.domain.util.ParcelRatingUtil;
import com.envista.msi.api.web.rest.dto.rtr.MsiARChargeCodesDto;
import com.envista.msi.api.web.rest.dto.rtr.ParcelAuditDetailsDto;
import com.envista.msi.api.web.rest.util.DateUtil;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by Sujit kumar on 21/06/2017.
 */
public class ParcelRateRequestBuilder {
    /**
     * Rate request event date formant.
     */
    private static final String RATE_REQUEST_EVENT_DATE_FORMAT = "MM/dd/yyyy hh:mm";

    /**
     * To build Parcel Audit request object for UPS carrier from given parcelAuditDetailsList.
     * @param parcelAuditDetailsList
     * @param licenseKey
     * @return
     */
    public static ParcelRateRequest buildParcelRateRequestForUps(List<ParcelAuditDetailsDto> parcelAuditDetailsList, String licenseKey, MsiARChargeCodesDto msiARChargeCodes, boolean isHwt) {

        ParcelRateRequest parcelRateRequest = new ParcelRateRequest();
        parcelRateRequest.setLicenseKey(licenseKey);

        Map<String, String> dasChargeList = msiARChargeCodes.getDasChargeCodes();
        Map<String, String> lpsCharges = msiARChargeCodes.getLpsChargeCodes();
        boolean hasRJ5Charge = false;
        if (parcelAuditDetailsList != null && !parcelAuditDetailsList.isEmpty()) {
            for (ParcelAuditDetailsDto auditDetails : parcelAuditDetailsList) {
                if (auditDetails != null && "RJ5".equalsIgnoreCase(auditDetails.getChargeDescriptionCode())) {
                    hasRJ5Charge = true;
                }
            }

            ParcelRateRequest.BatchShipment batchShipment = new ParcelRateRequest.BatchShipment();
            batchShipment.setId("1");
            String mode = "PCL";
            ParcelAuditDetailsDto parcelAuditDetails = parcelAuditDetailsList.get(0);
            if (parcelAuditDetails != null) {
                String billOption = (null == parcelAuditDetails.getBillOption() ? "" : parcelAuditDetails.getBillOption());
                if (billOption.equalsIgnoreCase("Prepaid") || billOption.equals("1") || billOption.equalsIgnoreCase("Outbound")) {
                    billOption = "PP";
                } else if (billOption.equalsIgnoreCase("Collect") || billOption.equals("2")) {
                    billOption = "FC";
                } else if (billOption.equalsIgnoreCase("Third Party") || billOption.equals("3")) {
                    billOption = "TP";
                }

                batchShipment.setBilledMiles("0.0");


                //Constraints section
                ParcelRateRequest.Constraints constraints = new ParcelRateRequest.Constraints();
                //String contractNumber = (null == parcelAuditDetails.getContractNumber() ? "" : parcelAuditDetails.getContractNumber());
                //having doubt on SCAC code
                String scacCode = (null == parcelAuditDetails.getRtrScacCode() ? "" : "FDEG".equals(parcelAuditDetails.getRtrScacCode()) ? "FDE" : parcelAuditDetails.getRtrScacCode());
                String currency = (null == parcelAuditDetails.getCurrency() || parcelAuditDetails.getCurrency().isEmpty() ? "USD" : parcelAuditDetails.getCurrency());

                ParcelRateRequest.Carrier carrier = new ParcelRateRequest.Carrier();
                carrier.setScac(scacCode);
                constraints.setCarrier(carrier);

                constraints.setBillOption(billOption);
                constraints.setCurrency(currency);
                constraints.setMode(mode);

                String serviceLevel = findServiceLevel(parcelAuditDetailsList);
                if (serviceLevel == null || serviceLevel.trim().isEmpty())
                    throw new RuntimeException("Invalid Service Level for " + parcelAuditDetailsList.get(0).getTrackingNumber());

                constraints.setService(serviceLevel);
                constraints.setCustomerCode(parcelAuditDetails.getCustomerCode());


                ParcelRateRequest.RevenueTier revenueTier = new ParcelRateRequest.RevenueTier();
                String revenueValue = parcelAuditDetailsList.get(0).getRevenueTier();
                if (revenueValue == null || revenueValue.equals("0"))
                    revenueValue = "";
                revenueTier.setRevenueTier(revenueValue);
                batchShipment.setRevenueTier(revenueTier);

                ParcelRateRequest.Shipper shipper = new ParcelRateRequest.Shipper();
                shipper.setNumber(parcelAuditDetailsList.get(0).getShipperNumber());
                batchShipment.setShipper(shipper);

                List<ParcelRateRequest.ServiceFlag> serviceFlagList = new ArrayList<>();
                List<ParcelRateRequest.Item> items = new ArrayList<>();

                int itemSequence = 1;


                if (isHwt) {

                    Map<String, List<ParcelAuditDetailsDto>> listMap = prepareTrackingNumberWiseAuditDetails(parcelAuditDetailsList);

                    for (Map.Entry<String, List<ParcelAuditDetailsDto>> entry : listMap.entrySet()) {

                        prepareUpsItems(entry.getValue(), items, serviceFlagList, itemSequence, dasChargeList, lpsCharges, parcelAuditDetails, hasRJ5Charge);
                        itemSequence++;

                    }
                } else {
                    prepareUpsItems(parcelAuditDetailsList, items, serviceFlagList, itemSequence, dasChargeList, lpsCharges, parcelAuditDetails, hasRJ5Charge);
                }


                batchShipment.setItems(items);
                constraints.setServiceFlags(serviceFlagList);
                batchShipment.setConstraints(constraints);

                //Events section
                String pickupDate = "", dropDate = "", locationCode = "";
                if (parcelAuditDetails.getPickupDate() != null) {
                    pickupDate = DateUtil.format(parcelAuditDetails.getPickupDate(), RATE_REQUEST_EVENT_DATE_FORMAT);
                }
                String senderCountry = (null == parcelAuditDetails.getSenderCountry() || parcelAuditDetails.getSenderCountry().isEmpty() ? "US" : parcelAuditDetails.getSenderCountry());
                String senderState = (null == parcelAuditDetails.getSenderState() ? "" : parcelAuditDetails.getSenderState());
                String senderCity = (null == parcelAuditDetails.getSenderCity() ? "" : parcelAuditDetails.getSenderCity());
                String senderZipCode = (null == parcelAuditDetails.getSenderZipCode() ? "" : parcelAuditDetails.getSenderZipCode());

                ParcelRateRequest.Event pickupDateEvent = new ParcelRateRequest.Event();
                pickupDateEvent.setSequence(1);
                pickupDateEvent.setType(ParcelRateRequest.EventType.Pickup.getValue());
                pickupDateEvent.setDate(pickupDate);

                ParcelRateRequest.Location senderLocation = new ParcelRateRequest.Location();
                senderLocation.setCity(senderCity);
                senderLocation.setState(senderState);
                senderLocation.setCountry(senderCountry);
                senderLocation.setZip(senderZipCode);
                senderLocation.setLocationCode(locationCode);
                pickupDateEvent.setLocation(senderLocation);

                if (parcelAuditDetails.getDeliveryDate() != null) {
                    dropDate = DateUtil.format(parcelAuditDetails.getDeliveryDate(), RATE_REQUEST_EVENT_DATE_FORMAT);
                } else {
                    dropDate = pickupDate;
                }
                String receiverCountry = (null == parcelAuditDetails.getReceiverCountry() || parcelAuditDetails.getReceiverCountry().isEmpty() ? "US" : parcelAuditDetails.getReceiverCountry());
                String receiverState = (null == parcelAuditDetails.getReceiverState() ? "" : parcelAuditDetails.getReceiverState());
                String receiverCity = (null == parcelAuditDetails.getReceiverCity() ? "" : parcelAuditDetails.getReceiverCity());
                String receiverZipCode = (null == parcelAuditDetails.getReceiverZipCode() ? "" : parcelAuditDetails.getReceiverZipCode());

                ParcelRateRequest.Event dropDateEvent = new ParcelRateRequest.Event();
                dropDateEvent.setSequence(2);
                dropDateEvent.setType(ParcelRateRequest.EventType.Drop.getValue());
                dropDateEvent.setDate(dropDate);

                ParcelRateRequest.Location receiverLocation = new ParcelRateRequest.Location();
                receiverLocation.setCity(receiverCity);
                receiverLocation.setState(receiverState);
                receiverLocation.setCountry(receiverCountry);
                receiverLocation.setZip(receiverZipCode);
                receiverLocation.setLocationCode(locationCode);
                dropDateEvent.setLocation(receiverLocation);

                batchShipment.setEvents(Arrays.asList(pickupDateEvent, dropDateEvent));
                parcelRateRequest.getShipments().add(batchShipment);
            }
        }
        return parcelRateRequest;
    }

    /**
     * To build Parcel Audit request object for non-UPS carriers from given parcelAuditDetailsList.
     * @param parcelAuditDetailsList
     * @param licenseKey
     * @return
     */
    public static ParcelRateRequest buildParcelRateRequestForNonUpsCarrier(List<ParcelAuditDetailsDto> parcelAuditDetailsList, String licenseKey, MsiARChargeCodesDto msiARChargeCodes, boolean isHwt) {

            ParcelRateRequest parcelRateRequest = new ParcelRateRequest();
            parcelRateRequest.setLicenseKey(licenseKey);
            Map<String, String> dasChargeList = msiARChargeCodes.getDasChargeCodes();
            Map<String, String> lpsCharges = msiARChargeCodes.getLpsChargeCodes();
            if (parcelAuditDetailsList != null && !parcelAuditDetailsList.isEmpty()) {
                ParcelRateRequest.BatchShipment batchShipment = new ParcelRateRequest.BatchShipment();
                batchShipment.setId("1");

                String mode = "PCL";
                ParcelAuditDetailsDto parcelAuditDetails = parcelAuditDetailsList.get(0);
                if (parcelAuditDetails != null) {
                    String billOption = (null == parcelAuditDetails.getBillOption() ? "" : parcelAuditDetails.getBillOption());
                    if (billOption.equalsIgnoreCase("Prepaid") || billOption.equals("1") || billOption.equalsIgnoreCase("Outbound")) {
                        billOption = "PP";
                    } else if (billOption.equalsIgnoreCase("Collect") || billOption.equals("2")) {
                        billOption = "FC";
                    } else if (billOption.equalsIgnoreCase("Third Party") || billOption.equals("3")) {
                        billOption = "TP";
                    }

                    batchShipment.setBilledMiles("0.0");


                    //Constraints section
                    ParcelRateRequest.Constraints constraints = new ParcelRateRequest.Constraints();
                    //String contractNumber = (null == parcelAuditDetails.getContractNumber() ? "" : parcelAuditDetails.getContractNumber());
                    //having doubt on SCAC code
                    String rtrScacCode = (null == parcelAuditDetails.getRtrScacCode() ? "" : "FDEG".equals(parcelAuditDetails.getRtrScacCode()) ? "FDE" : parcelAuditDetails.getRtrScacCode());
                    String currency = (null == parcelAuditDetails.getCurrency() || parcelAuditDetails.getCurrency().isEmpty() ? "USD" : parcelAuditDetails.getCurrency());

                    ParcelRateRequest.Carrier carrier = new ParcelRateRequest.Carrier();
                    carrier.setScac(rtrScacCode);
                    constraints.setCarrier(carrier);

                    constraints.setBillOption(billOption);
                    constraints.setCurrency(currency);
                    constraints.setMode(mode);

                    String serviceLevel = findServiceLevel(parcelAuditDetailsList);
                    if (serviceLevel == null || serviceLevel.trim().isEmpty())
                        throw new RuntimeException("Invalid Service Level for " + parcelAuditDetailsList.get(0).getTrackingNumber());

                    constraints.setService(serviceLevel);
                    constraints.setCustomerCode(parcelAuditDetails.getCustomerCode());


                    ParcelRateRequest.RevenueTier revenueTier = new ParcelRateRequest.RevenueTier();
                    String revenueValue = parcelAuditDetailsList.get(0).getRevenueTier();
                    if (revenueValue == null || revenueValue.equals("0"))
                        revenueValue = "";
                    revenueTier.setRevenueTier(revenueValue);
                    batchShipment.setRevenueTier(revenueTier);

                    ParcelRateRequest.Shipper shipper = new ParcelRateRequest.Shipper();
                    shipper.setNumber(parcelAuditDetailsList.get(0).getShipperNumber());
                    batchShipment.setShipper(shipper);

                    List<ParcelRateRequest.Item> items = new ArrayList<>();
                    List<ParcelRateRequest.ServiceFlag> serviceFlagList = new ArrayList<>();
                    int itemSequence = 1;

                    if (isHwt) {

                        Map<String, List<ParcelAuditDetailsDto>> listMap = prepareTrackingNumberWiseAuditDetails(parcelAuditDetailsList);

                        for (Map.Entry<String, List<ParcelAuditDetailsDto>> entry : listMap.entrySet()) {

                            prepareNonUpsItems(entry.getValue(), items, serviceFlagList, itemSequence, dasChargeList, lpsCharges);
                            itemSequence++;

                        }
                    } else {
                        prepareNonUpsItems(parcelAuditDetailsList, items, serviceFlagList, itemSequence, dasChargeList, lpsCharges);
                    }

                    batchShipment.setItems(items);
                    constraints.setServiceFlags(serviceFlagList);
                    batchShipment.setConstraints(constraints);
                    //Events section
                    String pickupDate = "", dropDate = "", locationCode = "";
                    if (parcelAuditDetails.getPickupDate() != null) {
                        pickupDate = DateUtil.format(parcelAuditDetails.getPickupDate(), RATE_REQUEST_EVENT_DATE_FORMAT);
                    }
                    String senderCountry = (null == parcelAuditDetails.getSenderCountry() || parcelAuditDetails.getSenderCountry().isEmpty() ? "US" : parcelAuditDetails.getSenderCountry());
                    String senderState = (null == parcelAuditDetails.getSenderState() ? "" : parcelAuditDetails.getSenderState());
                    String senderCity = (null == parcelAuditDetails.getSenderCity() ? "" : parcelAuditDetails.getSenderCity());
                    String senderZipCode = (null == parcelAuditDetails.getSenderZipCode() ? "" : parcelAuditDetails.getSenderZipCode());

                    ParcelRateRequest.Event pickupDateEvent = new ParcelRateRequest.Event();
                    pickupDateEvent.setSequence(1);
                    pickupDateEvent.setType(ParcelRateRequest.EventType.Pickup.getValue());
                    pickupDateEvent.setDate(pickupDate);

                    ParcelRateRequest.Location senderLocation = new ParcelRateRequest.Location();
                    senderLocation.setCity(senderCity);
                    senderLocation.setState(senderState);
                    senderLocation.setCountry(senderCountry);
                    senderLocation.setZip(senderZipCode);
                    senderLocation.setLocationCode(locationCode);
                    pickupDateEvent.setLocation(senderLocation);

                    if (parcelAuditDetails.getDeliveryDate() != null) {
                        dropDate = DateUtil.format(parcelAuditDetails.getDeliveryDate(), RATE_REQUEST_EVENT_DATE_FORMAT);
                    } else {
                        dropDate = pickupDate;
                    }
                    String receiverCountry = (null == parcelAuditDetails.getReceiverCountry() || parcelAuditDetails.getReceiverCountry().isEmpty() ? "US" : parcelAuditDetails.getReceiverCountry());
                    String receiverState = (null == parcelAuditDetails.getReceiverState() ? "" : parcelAuditDetails.getReceiverState());
                    String receiverCity = (null == parcelAuditDetails.getReceiverCity() ? "" : parcelAuditDetails.getReceiverCity());
                    String receiverZipCode = (null == parcelAuditDetails.getReceiverZipCode() ? "" : parcelAuditDetails.getReceiverZipCode());

                    ParcelRateRequest.Event dropDateEvent = new ParcelRateRequest.Event();
                    dropDateEvent.setSequence(2);
                    dropDateEvent.setType(ParcelRateRequest.EventType.Drop.getValue());
                    dropDateEvent.setDate(dropDate);

                    ParcelRateRequest.Location receiverLocation = new ParcelRateRequest.Location();
                    receiverLocation.setCity(receiverCity);
                    receiverLocation.setState(receiverState);
                    receiverLocation.setCountry(receiverCountry);
                    receiverLocation.setZip(receiverZipCode);
                    receiverLocation.setLocationCode(locationCode);
                    dropDateEvent.setLocation(receiverLocation);

                    batchShipment.setEvents(Arrays.asList(pickupDateEvent, dropDateEvent));
                    parcelRateRequest.getShipments().add(batchShipment);
                }
            }
            return parcelRateRequest;
    }

    /**
     * To find service-level for Freight type shipment.
     * @param parcelAuditDetails
     * @return
     */
    public static String findServiceLevel(List<ParcelAuditDetailsDto> parcelAuditDetails) {
        if(parcelAuditDetails != null && !parcelAuditDetails.isEmpty()){
            for(ParcelAuditDetailsDto auditDetails : parcelAuditDetails){
                if(auditDetails != null && auditDetails.getChargeClassificationCode() != null
                        && ParcelAuditConstant.ChargeClassificationCode.FRT.name().equals(auditDetails.getChargeClassificationCode())
                        && auditDetails.getNetAmount() != null && !auditDetails.getNetAmount().isEmpty()){
                    return auditDetails.getServiceLevel();
                }
            }
        }
        return null;
    }

    /**
     * To prepare shipment wise audit details,
     * Here shipment means a tracking number.
     *
     * @param auditDetailsList
     * @return
     */
    private static Map<String, List<ParcelAuditDetailsDto>> prepareTrackingNumberWiseAuditDetails(List<ParcelAuditDetailsDto> auditDetailsList) {
        Map<String, List<ParcelAuditDetailsDto>> parcelAuditMap = null;
        if (auditDetailsList != null && !auditDetailsList.isEmpty()) {
            parcelAuditMap = new HashMap<>();
            for (ParcelAuditDetailsDto parcelAuditDetails : auditDetailsList) {
                if (parcelAuditDetails != null) {
                    String trackingNumber = parcelAuditDetails.getTrackingNumber();
                    if (trackingNumber != null && !trackingNumber.isEmpty() && parcelAuditMap.containsKey(parcelAuditDetails.getTrackingNumber())) {
                        parcelAuditMap.get(trackingNumber).add(parcelAuditDetails);
                    } else {
                        List<ParcelAuditDetailsDto> auditList = new ArrayList<>();
                        auditList.add(parcelAuditDetails);
                        parcelAuditMap.put(trackingNumber, auditList);
                    }
                }
            }
        }
        return parcelAuditMap;
    }

    /**
     * This method will prepare items for single shipment or HWT shipments
     *
     * @param parcelAuditDetailsList
     * @param items
     * @param serviceFlagList
     * @param itemSequence
     * @param dasChargeList
     * @param lpsCharges
     * @param parcelAuditDetails
     */
    private static void prepareUpsItems(List<ParcelAuditDetailsDto> parcelAuditDetailsList, List<ParcelRateRequest.Item> items, List<ParcelRateRequest.ServiceFlag> serviceFlagList, int itemSequence, Map<String, String> dasChargeList, Map<String, String> lpsCharges, ParcelAuditDetailsDto parcelAuditDetails, boolean hasRJ5Charge) {

        ParcelAuditDetailsDto latestFreightCharge = ParcelRatingUtil.getLatestFrightCharge(parcelAuditDetailsList);
        if (latestFreightCharge != null){
            String weight = (null == latestFreightCharge.getPackageWeight() || latestFreightCharge.getPackageWeight().isEmpty() ? "1" : latestFreightCharge.getPackageWeight());
            String weightUnit = (null == latestFreightCharge.getWeightUnit() || latestFreightCharge.getWeightUnit().isEmpty() || "L".equalsIgnoreCase(latestFreightCharge.getWeightUnit()) ? "LBS" : latestFreightCharge.getWeightUnit());
            String actualWeightUnit = (null == latestFreightCharge.getActualWeightUnit() || latestFreightCharge.getActualWeightUnit().isEmpty() || "L".equalsIgnoreCase(latestFreightCharge.getActualWeightUnit()) ? "LBS" : latestFreightCharge.getActualWeightUnit());
            String quantity = (null == latestFreightCharge.getItemQuantity() || latestFreightCharge.getItemQuantity().isEmpty() ? "1" : latestFreightCharge.getItemQuantity());
            String quantityUnit = (null == latestFreightCharge.getQuantityUnit() || latestFreightCharge.getQuantityUnit().isEmpty() ? "PCS" : latestFreightCharge.getQuantityUnit());
            String dimLenght = (null == latestFreightCharge.getDimLength() || latestFreightCharge.getDimLength().isEmpty() ? "" : latestFreightCharge.getDimLength());
            String dimWidth = (null == latestFreightCharge.getDimWidth() || latestFreightCharge.getDimWidth().isEmpty() ? "" : latestFreightCharge.getDimWidth());
            String dimHeight = (null == latestFreightCharge.getDimHeight() || latestFreightCharge.getDimHeight().isEmpty() ? "" : latestFreightCharge.getDimHeight());
            String dimUnit = (null == latestFreightCharge.getUnitOfDim() || latestFreightCharge.getUnitOfDim().isEmpty() ? "" : (latestFreightCharge.getUnitOfDim().equalsIgnoreCase("I") ? "in" : latestFreightCharge.getUnitOfDim()));
            BigDecimal actualWeight = (null == latestFreightCharge.getActualWeight() ? null : latestFreightCharge.getActualWeight());
            ParcelRateRequest.Weight weightObj = new ParcelRateRequest.Weight();
            if (!weight.isEmpty()){
                weightObj.setWeight(new BigDecimal(weight.trim()));
            }
            weightObj.setUnits(weightUnit);

            ParcelRateRequest.Weight actualWeightElement = new ParcelRateRequest.Weight();
            actualWeightElement.setWeight(actualWeight);
            actualWeightElement.setUnits(actualWeightUnit);

            ParcelRateRequest.Quantity quantityObj = new ParcelRateRequest.Quantity();
            quantityObj.setQuantity(new BigDecimal(quantity));
            quantityObj.setUnits(quantityUnit);

            ParcelRateRequest.Dimensions dimensionsObj = new ParcelRateRequest.Dimensions();
            try {
                if (!dimLenght.isEmpty()) dimensionsObj.setLength(new BigDecimal(dimLenght));
            } catch (Exception e) {
            }
            try {
                if (!dimWidth.isEmpty()) dimensionsObj.setWidth(new BigDecimal(dimWidth));
            } catch (Exception e) {
            }
            try {
                if (!dimHeight.isEmpty()) dimensionsObj.setHeight(new BigDecimal(dimHeight));
            } catch (Exception e){}
            dimensionsObj.setUnits(dimUnit);

            ParcelRateRequest.Item item = new ParcelRateRequest.Item();
            item.setSequence(itemSequence);
            item.setWeight(weightObj);
            item.setActualWeight(actualWeightElement);
            item.setQuantity(quantityObj);
            item.setDimensions(dimensionsObj);
            item.setContainer(parcelAuditDetails.getPackageType());
            items.add(item);

            //ServiceFlags section
            for (ParcelAuditDetailsDto auditDetails : parcelAuditDetailsList) {
                if (auditDetails != null) {
                    if (auditDetails.getChargeClassificationCode() != null
                            && ParcelAuditConstant.ChargeClassificationCode.ACC.name().equalsIgnoreCase(auditDetails.getChargeClassificationCode())) {
                        if (auditDetails.getChargeDescriptionCode() != null && !auditDetails.getChargeDescriptionCode().isEmpty()
                                && !"RJ5".equalsIgnoreCase(auditDetails.getChargeDescriptionCode())){
                            ParcelRateRequest.ServiceFlag serviceFlag = new ParcelRateRequest.ServiceFlag();
                            if (!hasRJ5Charge && auditDetails.getChargeDescriptionCode().equalsIgnoreCase("RES")){
                                serviceFlag.setCode("RSC");
                            } else if (dasChargeList.containsKey(auditDetails.getChargeDescriptionCode())) {
                                serviceFlag.setCode(dasChargeList.get(auditDetails.getChargeDescriptionCode()));
                            } else if (lpsCharges != null && lpsCharges.containsKey(auditDetails.getChargeDescriptionCode())) {
                                serviceFlag.setCode(lpsCharges.get(auditDetails.getChargeDescriptionCode()));
                            } else {
                                serviceFlag.setCode(auditDetails.getChargeDescriptionCode());
                            }
                            serviceFlag.setSequence(itemSequence);
                            serviceFlag.setNetAmount(auditDetails.getNetAmount());
                            serviceFlagList.add(serviceFlag);
                        }
                    }
                }
            }


        } else {
            throw new RuntimeException("Freight Item not found");
        }
    }


    /**
     * This method will prepare items for single shipment or MWT shipments
     * @param parcelAuditDetailsList
     * @param items
     * @param serviceFlagList
     * @param itemSequence
     * @param dasChargeList
     * @param lpsCharges
     */
    private static void prepareNonUpsItems(List<ParcelAuditDetailsDto> parcelAuditDetailsList, List<ParcelRateRequest.Item> items, List<ParcelRateRequest.ServiceFlag> serviceFlagList, int itemSequence, Map<String, String> dasChargeList, Map<String, String> lpsCharges) {

        ParcelAuditDetailsDto firstBaseCharge = ParcelRatingUtil.getFirstFrightChargeForNonUpsCarrier(parcelAuditDetailsList);
        if (firstBaseCharge != null) {
            if (firstBaseCharge.getChargeClassificationCode() != null
                    && ParcelAuditConstant.ChargeClassificationCode.FRT.name().equalsIgnoreCase(firstBaseCharge.getChargeClassificationCode())) {
                String weight = (null == firstBaseCharge.getPackageWeight() || firstBaseCharge.getPackageWeight().isEmpty() ? "" : firstBaseCharge.getPackageWeight());
                String weightUnit = (null == firstBaseCharge.getWeightUnit() || firstBaseCharge.getWeightUnit().isEmpty() || "L".equalsIgnoreCase(firstBaseCharge.getWeightUnit()) ? "LBS" : firstBaseCharge.getWeightUnit());
                String actualWeightUnit = (null == firstBaseCharge.getActualWeightUnit() || firstBaseCharge.getActualWeightUnit().isEmpty() || "L".equalsIgnoreCase(firstBaseCharge.getActualWeightUnit()) ? "LBS" : firstBaseCharge.getActualWeightUnit());
                String quantity = (null == firstBaseCharge.getItemQuantity() || firstBaseCharge.getItemQuantity().isEmpty() ? "1" : firstBaseCharge.getItemQuantity());
                String quantityUnit = (null == firstBaseCharge.getQuantityUnit() || firstBaseCharge.getQuantityUnit().isEmpty() ? "PCS" : firstBaseCharge.getQuantityUnit());
                String dimLenght = (null == firstBaseCharge.getDimLength() || firstBaseCharge.getDimLength().isEmpty() ? "" : firstBaseCharge.getDimLength());
                String dimWidth = (null == firstBaseCharge.getDimWidth() || firstBaseCharge.getDimWidth().isEmpty() ? "" : firstBaseCharge.getDimWidth());
                String dimHeight = (null == firstBaseCharge.getDimHeight() || firstBaseCharge.getDimHeight().isEmpty() ? "" : firstBaseCharge.getDimHeight());
                String dimUnit = (null == firstBaseCharge.getUnitOfDim() || firstBaseCharge.getUnitOfDim().isEmpty() ? "" : firstBaseCharge.getUnitOfDim().equalsIgnoreCase("I") ? "in" : firstBaseCharge.getUnitOfDim());
                BigDecimal actualWeight = (null == firstBaseCharge.getActualWeight() ? null : firstBaseCharge.getActualWeight());

                ParcelRateRequest.Weight weightObj = new ParcelRateRequest.Weight();
                if (!weight.isEmpty()){
                    weightObj.setWeight(new BigDecimal(weight.trim()));
                }
                weightObj.setUnits(weightUnit);

                ParcelRateRequest.Weight actualWeightElement = new ParcelRateRequest.Weight();
                actualWeightElement.setWeight(actualWeight);
                actualWeightElement.setUnits(actualWeightUnit);

                ParcelRateRequest.Quantity quantityObj = new ParcelRateRequest.Quantity();
                quantityObj.setQuantity(new BigDecimal(quantity));
                quantityObj.setUnits(quantityUnit);

                ParcelRateRequest.Dimensions dimensionsObj = new ParcelRateRequest.Dimensions();
                try {
                    if (!dimLenght.isEmpty()) dimensionsObj.setLength(new BigDecimal(dimLenght));
                } catch (Exception e) {
                }
                try {
                    if (!dimWidth.isEmpty()) dimensionsObj.setWidth(new BigDecimal(dimWidth));
                } catch (Exception e) {
                }
                try {
                    if (!dimHeight.isEmpty()) dimensionsObj.setHeight(new BigDecimal(dimHeight));
                } catch (Exception e){}
                dimensionsObj.setUnits(dimUnit);

                ParcelRateRequest.Item item = new ParcelRateRequest.Item();
                item.setSequence(itemSequence);
                item.setWeight(weightObj);
                item.setActualWeight(actualWeightElement);
                item.setQuantity(quantityObj);
                item.setDimensions(dimensionsObj);
                item.setContainer(firstBaseCharge.getPackageType());
                items.add(item);

                //ServiceFlags section

                for (ParcelAuditDetailsDto auditDetails : parcelAuditDetailsList) {
                    if (auditDetails != null) {
                        if (auditDetails.getChargeClassificationCode() != null && ParcelAuditConstant.ChargeClassificationCode.ACS.name().equalsIgnoreCase(auditDetails.getChargeClassificationCode())
                                && !Arrays.asList(ParcelAuditConstant.ChargeDescriptionCode.FSC.name(), ParcelAuditConstant.ChargeDescriptionCode.DSC.name()).contains(auditDetails.getChargeDescriptionCode())){
                            ParcelRateRequest.ServiceFlag serviceFlag = new ParcelRateRequest.ServiceFlag();
                            if (auditDetails.getChargeDescriptionCode().equalsIgnoreCase("RES")) {
                                serviceFlag.setCode("RSC");
                            } else if (dasChargeList.containsKey(auditDetails.getChargeDescriptionCode())) {
                                if (auditDetails.getChargeDescription() != null && (auditDetails.getChargeDescription().contains("EXTENDED") || auditDetails.getChargeDescription().contains("extended"))) {
                                    serviceFlag.setCode("DSX");
                                } else {
                                    serviceFlag.setCode(dasChargeList.get(auditDetails.getChargeDescriptionCode()));
                                }
                            } else if (lpsCharges != null && lpsCharges.containsKey(auditDetails.getChargeDescriptionCode())) {
                                serviceFlag.setCode(lpsCharges.get(auditDetails.getChargeDescriptionCode()));
                            } else {
                                serviceFlag.setCode(auditDetails.getChargeDescriptionCode());
                            }
                            serviceFlag.setSequence(itemSequence);
                            serviceFlag.setNetAmount(auditDetails.getNetAmount());
                            serviceFlagList.add(serviceFlag);
                        }
                    }
                }
            }
        }
    }


}
