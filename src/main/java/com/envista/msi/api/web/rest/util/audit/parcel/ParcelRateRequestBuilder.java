package com.envista.msi.api.web.rest.util.audit.parcel;

import com.envista.msi.api.web.rest.dto.rtr.ParcelAuditDetailsDto;
import com.envista.msi.api.web.rest.util.DateUtil;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
    public static ParcelRateRequest buildParcelRateRequestForUps(List<ParcelAuditDetailsDto> parcelAuditDetailsList, String licenseKey){
        ParcelRateRequest parcelRateRequest = new ParcelRateRequest();
        parcelRateRequest.setLicenseKey(licenseKey);

        if(parcelAuditDetailsList != null && !parcelAuditDetailsList.isEmpty()){
            boolean isWisoCustomer = parcelAuditDetailsList.get(0).getCustomerCode().equalsIgnoreCase("WISO");
            ParcelRateRequest.BatchShipment batchShipment = new ParcelRateRequest.BatchShipment();
            batchShipment.setId("1");
            String mode = "PCL";
            ParcelAuditDetailsDto parcelAuditDetails = parcelAuditDetailsList.get(0);
            if(parcelAuditDetails != null){
                String billOption = (null == parcelAuditDetails.getBillOption() ? "" : parcelAuditDetails.getBillOption());
                if(billOption.equalsIgnoreCase("Prepaid") || billOption.equals("1") || billOption.equalsIgnoreCase("Outbound")){
                    billOption = "PP";
                }else if(billOption.equalsIgnoreCase("Collect") || billOption.equals("2")){
                    billOption = "FC";
                }else if(billOption.equalsIgnoreCase("Third Party") || billOption.equals("3")){
                    billOption = "TP";
                }

                batchShipment.setBilledMiles("0.0");
                //ServiceFlags section
                List<ParcelRateRequest.ServiceFlag> serviceFlagList = new ArrayList<>();
                for(ParcelAuditDetailsDto auditDetails : parcelAuditDetailsList){
                    if(auditDetails != null){
                        if(auditDetails.getChargeClassificationCode() != null
                                && ParcelAuditConstant.ChargeClassificationCode.ACC.name().equalsIgnoreCase(auditDetails.getChargeClassificationCode())){
                            if(auditDetails.getChargeDescriptionCode() != null && !auditDetails.getChargeDescriptionCode().isEmpty()){
                                ParcelRateRequest.ServiceFlag serviceFlag = new ParcelRateRequest.ServiceFlag();
                                if(auditDetails.getChargeDescriptionCode().equalsIgnoreCase("RES")){
                                    auditDetails.setChargeDescriptionCode("RSC");
                                }
                                serviceFlag.setCode(auditDetails.getChargeDescriptionCode());
                                serviceFlagList.add(serviceFlag);
                            }
                        }
                    }
                }

                //Constraints section
                ParcelRateRequest.Constraints constraints = new ParcelRateRequest.Constraints();
                String contractNumber = (null == parcelAuditDetails.getContractNumber() ? "" : parcelAuditDetails.getContractNumber());
                //having doubt on SCAC code
                String scacCode = (null == parcelAuditDetails.getRtrScacCode() ? "" : "FDEG".equals(parcelAuditDetails.getRtrScacCode()) ? "FDE" : parcelAuditDetails.getRtrScacCode());
                String currency = (null == parcelAuditDetails.getCurrency() || parcelAuditDetails.getCurrency().isEmpty() ? "USD" : parcelAuditDetails.getCurrency());

                if(!isWisoCustomer) {
                    ParcelRateRequest.Contract contract = new ParcelRateRequest.Contract();
                    contract.setName(contractNumber);
                    constraints.setContract(contract);
                }

                ParcelRateRequest.Carrier carrier = new ParcelRateRequest.Carrier();
                carrier.setScac(scacCode);
                constraints.setCarrier(carrier);

                constraints.setBillOption(billOption);
                constraints.setCurrency(currency);
                constraints.setMode(mode);
                String serviceLevel = findServiceLevel(parcelAuditDetailsList);
                constraints.setService(null == serviceLevel ? "" : serviceLevel);
                constraints.setCustomerCode(parcelAuditDetails.getCustomerCode());
                constraints.setServiceFlags(serviceFlagList);
                batchShipment.setConstraints(constraints);

                if(isWisoCustomer){
                    ParcelRateRequest.Shipper shipper = new ParcelRateRequest.Shipper();
                    shipper.setNumber(parcelAuditDetailsList.get(0).getShipperNumber());
                    batchShipment.setShipper(shipper);
                }

                List<ParcelRateRequest.Item> items = new ArrayList<>();
                int itemSequence = 1;
                for(ParcelAuditDetailsDto auditDetails : parcelAuditDetailsList) {
                    if (auditDetails != null) {
                        if (auditDetails.getChargeClassificationCode() != null
                                && ParcelAuditConstant.ChargeClassificationCode.FRT.name().equalsIgnoreCase(auditDetails.getChargeClassificationCode())) {
                            String weight = (null == auditDetails.getPackageWeight() || auditDetails.getPackageWeight().isEmpty() ? "" : auditDetails.getPackageWeight());
                            String weightUnit = (null == auditDetails.getWeightUnit() || auditDetails.getWeightUnit().isEmpty() || "L".equalsIgnoreCase(auditDetails.getWeightUnit()) ? "LBS" : auditDetails.getWeightUnit());
                            String quantity = (null == auditDetails.getItemQuantity() || auditDetails.getItemQuantity().isEmpty() ? "1" : auditDetails.getItemQuantity());
                            String quantityUnit = (null == auditDetails.getQuantityUnit() || auditDetails.getQuantityUnit().isEmpty() ? "PCS" : auditDetails.getQuantityUnit());
                            String dimLenght = (null == auditDetails.getDimLength() || auditDetails.getDimLength().isEmpty() ? "" : auditDetails.getDimLength());
                            String dimWidth = (null == auditDetails.getDimWidth() || auditDetails.getDimWidth().isEmpty()? "" : auditDetails.getDimWidth());
                            String dimHeight = (null == auditDetails.getDimHeight() || auditDetails.getDimHeight().isEmpty() ? "" : auditDetails.getDimHeight());
                            String dimUnit = (null == auditDetails.getUnitOfDim() || auditDetails.getUnitOfDim().isEmpty() ? "" : auditDetails.getUnitOfDim());

                            ParcelRateRequest.Weight weightObj = new ParcelRateRequest.Weight();
                            if(!weight.isEmpty()){
                                weightObj.setWeight(new BigDecimal(weight.trim()));
                            }
                            weightObj.setUnits(weightUnit);

                            ParcelRateRequest.Quantity quantityObj = new ParcelRateRequest.Quantity();
                            quantityObj.setQuantity(new BigDecimal(quantity));
                            quantityObj.setUnits(quantityUnit);

                            ParcelRateRequest.Dimensions dimensionsObj = new ParcelRateRequest.Dimensions();
                            try{ if(!dimLenght.isEmpty()) dimensionsObj.setLength(new BigDecimal(dimLenght)); }catch (Exception e){}
                            try{ if(!dimWidth.isEmpty()) dimensionsObj.setWidth(new BigDecimal(dimWidth)); }catch (Exception e){}
                            try{ if(!dimHeight.isEmpty()) dimensionsObj.setHeight(new BigDecimal(dimHeight)); }catch (Exception e){}
                            dimensionsObj.setUnits(dimUnit);

                            ParcelRateRequest.Item item = new ParcelRateRequest.Item();
                            item.setSequence(itemSequence++);
                            item.setWeight(weightObj);
                            item.setQuantity(quantityObj);
                            item.setDimensions(dimensionsObj);
                            items.add(item);
                        }
                    }
                }
                batchShipment.setItems(items);

                //Events section
                String pickupDate = "", dropDate = "", locationCode = "";
                if(parcelAuditDetails.getPickupDate() != null){
                    pickupDate = DateUtil.format(parcelAuditDetails.getPickupDate(), RATE_REQUEST_EVENT_DATE_FORMAT);
                }
                String senderCountry =  (null == parcelAuditDetails.getSenderCountry() || parcelAuditDetails.getSenderCountry().isEmpty() ? "US" :  parcelAuditDetails.getSenderCountry());
                String senderState =  (null == parcelAuditDetails.getSenderState() ? "" :  parcelAuditDetails.getSenderState());
                String senderCity =  (null == parcelAuditDetails.getSenderCity() ? "" :  parcelAuditDetails.getSenderCity());
                String senderZipCode =  (null == parcelAuditDetails.getSenderZipCode() ? "" :  parcelAuditDetails.getSenderZipCode());

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

                if(parcelAuditDetails.getDeliveryDate() != null){
                    dropDate = DateUtil.format(parcelAuditDetails.getDeliveryDate(), RATE_REQUEST_EVENT_DATE_FORMAT);
                }else{
                    dropDate = pickupDate;
                }
                String receiverCountry =  (null == parcelAuditDetails.getReceiverCountry() || parcelAuditDetails.getReceiverCountry().isEmpty() ? "US" :  parcelAuditDetails.getReceiverCountry());
                String receiverState =  (null == parcelAuditDetails.getReceiverState() ? "" :  parcelAuditDetails.getReceiverState());
                String receiverCity =  (null == parcelAuditDetails.getReceiverCity() ? "" :  parcelAuditDetails.getReceiverCity());
                String receiverZipCode = (null == parcelAuditDetails.getReceiverZipCode() ? "" :  parcelAuditDetails.getReceiverZipCode());

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
    public static ParcelRateRequest buildParcelRateRequestForNonUpsCarrier(List<ParcelAuditDetailsDto> parcelAuditDetailsList, String licenseKey){
        ParcelRateRequest parcelRateRequest = new ParcelRateRequest();
        parcelRateRequest.setLicenseKey(licenseKey);

        if(parcelAuditDetailsList != null && !parcelAuditDetailsList.isEmpty()){
            boolean isWisoCustomer = parcelAuditDetailsList.get(0).getCustomerCode().equalsIgnoreCase("WISO");
            ParcelRateRequest.BatchShipment batchShipment = new ParcelRateRequest.BatchShipment();
            batchShipment.setId("1");

            for(ParcelAuditDetailsDto auditDetails : parcelAuditDetailsList) {
                if (auditDetails != null && auditDetails.getDwFieldInformation() != null) {
                    try{
                        String [] dwFieldInfo = auditDetails.getDwFieldInformation().split(",");
                        if(dwFieldInfo != null && dwFieldInfo.length > 0){
                            auditDetails.setChargeClassificationCode(dwFieldInfo[1].trim());
                            auditDetails.setChargeDescriptionCode(dwFieldInfo[2].trim().equalsIgnoreCase("RES") ? "RSC" : dwFieldInfo[2].trim());
                        }
                    }catch (Exception e){}
                }
            }


            String mode = "PCL";
            ParcelAuditDetailsDto parcelAuditDetails = parcelAuditDetailsList.get(0);
            if(parcelAuditDetails != null){
                String billOption = (null == parcelAuditDetails.getBillOption() ? "" : parcelAuditDetails.getBillOption());
                if(billOption.equalsIgnoreCase("Prepaid") || billOption.equals("1") || billOption.equalsIgnoreCase("Outbound")){
                    billOption = "PP";
                }else if(billOption.equalsIgnoreCase("Collect") || billOption.equals("2")){
                    billOption = "FC";
                }else if(billOption.equalsIgnoreCase("Third Party") || billOption.equals("3")){
                    billOption = "TP";
                }

                batchShipment.setBilledMiles("0.0");

                //ServiceFlags section
                List<ParcelRateRequest.ServiceFlag> serviceFlagList = new ArrayList<>();
                for(ParcelAuditDetailsDto auditDetails : parcelAuditDetailsList){
                    if(auditDetails != null){
                        if(auditDetails.getChargeClassificationCode() != null && ParcelAuditConstant.ChargeClassificationCode.ACS.name().equalsIgnoreCase(auditDetails.getChargeClassificationCode())
                                && !Arrays.asList(ParcelAuditConstant.ChargeDescriptionCode.FSC.name(), ParcelAuditConstant.ChargeDescriptionCode.DSC.name()).contains(auditDetails.getChargeDescriptionCode())){
                            ParcelRateRequest.ServiceFlag serviceFlag = new ParcelRateRequest.ServiceFlag();
                            serviceFlag.setCode(auditDetails.getChargeDescriptionCode().equalsIgnoreCase("RES") ? "RSC" : auditDetails.getChargeDescriptionCode());
                            serviceFlagList.add(serviceFlag);
                        }
                    }
                }

                //Constraints section
                ParcelRateRequest.Constraints constraints = new ParcelRateRequest.Constraints();
                String contractNumber = (null == parcelAuditDetails.getContractNumber() ? "" : parcelAuditDetails.getContractNumber());
                //having doubt on SCAC code
                String rtrScacCode = (null == parcelAuditDetails.getRtrScacCode() ? "" : "FDEG".equals(parcelAuditDetails.getRtrScacCode()) ? "FDE" : parcelAuditDetails.getRtrScacCode());
                String currency = (null == parcelAuditDetails.getCurrency() || parcelAuditDetails.getCurrency().isEmpty() ? "USD" : parcelAuditDetails.getCurrency());

                if(!isWisoCustomer) {
                    ParcelRateRequest.Contract contract = new ParcelRateRequest.Contract();
                    contract.setName(contractNumber);
                    constraints.setContract(contract);
                }

                ParcelRateRequest.Carrier carrier = new ParcelRateRequest.Carrier();
                carrier.setScac(rtrScacCode);
                constraints.setCarrier(carrier);

                constraints.setBillOption(billOption);
                constraints.setCurrency(currency);
                constraints.setMode(mode);
                constraints.setService(findServiceLevel(parcelAuditDetailsList));
                constraints.setCustomerCode(parcelAuditDetails.getCustomerCode());
                constraints.setServiceFlags(serviceFlagList);
                batchShipment.setConstraints(constraints);

                if(isWisoCustomer){
                    ParcelRateRequest.Shipper shipper = new ParcelRateRequest.Shipper();
                    shipper.setNumber(parcelAuditDetailsList.get(0).getShipperNumber());
                    batchShipment.setShipper(shipper);
                }

                List<ParcelRateRequest.Item> items = new ArrayList<>();
                int itemSequence = 1;
                for(ParcelAuditDetailsDto auditDetails : parcelAuditDetailsList) {
                    if (auditDetails != null) {
                        if (auditDetails.getChargeClassificationCode() != null
                                && ParcelAuditConstant.ChargeClassificationCode.FRT.name().equalsIgnoreCase(auditDetails.getChargeClassificationCode())) {
                            String weight = (null == auditDetails.getPackageWeight() || auditDetails.getPackageWeight().isEmpty() ? "" : auditDetails.getPackageWeight());
                            String weightUnit = (null == auditDetails.getWeightUnit() || auditDetails.getWeightUnit().isEmpty() || "L".equalsIgnoreCase(auditDetails.getWeightUnit()) ? "LBS" : auditDetails.getWeightUnit());
                            String quantity = (null == auditDetails.getItemQuantity() || auditDetails.getItemQuantity().isEmpty() ? "1" : auditDetails.getItemQuantity());
                            String quantityUnit = (null == auditDetails.getQuantityUnit() || auditDetails.getQuantityUnit().isEmpty() ? "PCS" : auditDetails.getQuantityUnit());
                            String dimLenght = (null == auditDetails.getDimLength() || auditDetails.getDimLength().isEmpty() ? "" : auditDetails.getDimLength());
                            String dimWidth = (null == auditDetails.getDimWidth() || auditDetails.getDimWidth().isEmpty()? "" : auditDetails.getDimWidth());
                            String dimHeight = (null == auditDetails.getDimHeight() || auditDetails.getDimHeight().isEmpty() ? "" : auditDetails.getDimHeight());
                            String dimUnit = (null == auditDetails.getUnitOfDim() || auditDetails.getUnitOfDim().isEmpty() ? "" : auditDetails.getUnitOfDim());

                            ParcelRateRequest.Weight weightObj = new ParcelRateRequest.Weight();
                            if(!weight.isEmpty()){
                                weightObj.setWeight(new BigDecimal(weight.trim()));
                            }
                            weightObj.setUnits(weightUnit);

                            ParcelRateRequest.Quantity quantityObj = new ParcelRateRequest.Quantity();
                            quantityObj.setQuantity(new BigDecimal(quantity));
                            quantityObj.setUnits(quantityUnit);

                            ParcelRateRequest.Dimensions dimensionsObj = new ParcelRateRequest.Dimensions();
                            try{ if(!dimLenght.isEmpty()) dimensionsObj.setLength(new BigDecimal(dimLenght)); }catch (Exception e){}
                            try{ if(!dimWidth.isEmpty()) dimensionsObj.setWidth(new BigDecimal(dimWidth)); }catch (Exception e){}
                            try{ if(!dimHeight.isEmpty()) dimensionsObj.setHeight(new BigDecimal(dimHeight)); }catch (Exception e){}
                            dimensionsObj.setUnits(dimUnit);

                            ParcelRateRequest.Item item = new ParcelRateRequest.Item();
                            item.setSequence(itemSequence++);
                            item.setWeight(weightObj);
                            item.setQuantity(quantityObj);
                            item.setDimensions(dimensionsObj);
                            items.add(item);
                        }
                    }
                }
                batchShipment.setItems(items);

                //Events section
                String pickupDate = "", dropDate = "", locationCode = "";
                if(parcelAuditDetails.getPickupDate() != null){
                    pickupDate = DateUtil.format(parcelAuditDetails.getPickupDate(), RATE_REQUEST_EVENT_DATE_FORMAT);
                }
                String senderCountry =  (null == parcelAuditDetails.getSenderCountry() || parcelAuditDetails.getSenderCountry().isEmpty() ? "US" :  parcelAuditDetails.getSenderCountry());
                String senderState =  (null == parcelAuditDetails.getSenderState() ? "" :  parcelAuditDetails.getSenderState());
                String senderCity =  (null == parcelAuditDetails.getSenderCity() ? "" :  parcelAuditDetails.getSenderCity());
                String senderZipCode =  (null == parcelAuditDetails.getSenderZipCode() ? "" :  parcelAuditDetails.getSenderZipCode());

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

                if(parcelAuditDetails.getDeliveryDate() != null){
                    dropDate = DateUtil.format(parcelAuditDetails.getDeliveryDate(), RATE_REQUEST_EVENT_DATE_FORMAT);
                }else{
                    dropDate = pickupDate;
                }
                String receiverCountry =  (null == parcelAuditDetails.getReceiverCountry() || parcelAuditDetails.getReceiverCountry().isEmpty() ? "US" :  parcelAuditDetails.getReceiverCountry());
                String receiverState =  (null == parcelAuditDetails.getReceiverState() ? "" :  parcelAuditDetails.getReceiverState());
                String receiverCity =  (null == parcelAuditDetails.getReceiverCity() ? "" :  parcelAuditDetails.getReceiverCity());
                String receiverZipCode = (null == parcelAuditDetails.getReceiverZipCode() ? "" :  parcelAuditDetails.getReceiverZipCode());

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
    private static String findServiceLevel(List<ParcelAuditDetailsDto> parcelAuditDetails) {
        if(parcelAuditDetails != null && !parcelAuditDetails.isEmpty()){
            for(ParcelAuditDetailsDto auditDetails : parcelAuditDetails){
                if(auditDetails != null && auditDetails.getChargeClassificationCode() != null
                        && ParcelAuditConstant.ChargeClassificationCode.FRT.name().equals(auditDetails.getChargeClassificationCode())){
                    return auditDetails.getServiceLevel();
                }
            }
        }
        return null;
    }
}
