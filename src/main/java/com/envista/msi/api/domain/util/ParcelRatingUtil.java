package com.envista.msi.api.domain.util;

import com.envista.msi.api.web.rest.dto.rtr.*;
import com.envista.msi.api.web.rest.util.audit.parcel.ParcelAuditConstant;
import com.envista.msi.rating.bean.RatingQueueBean;
import org.apache.commons.lang.StringUtils;

import java.math.BigDecimal;
import java.util.*;

/**
 * Created by Sujit kumar on 20/04/2018.
 */
public class ParcelRatingUtil {
    public static ParcelAuditDetailsDto getLatestFrightCharge(List<ParcelAuditDetailsDto> parcelAuditDetails){
        ParcelAuditDetailsDto parcelAuditDetail = null;
        Long maxEntityId = 0l;
        if(parcelAuditDetails != null && !parcelAuditDetails.isEmpty()){
            for(ParcelAuditDetailsDto auditDetail : parcelAuditDetails) {
                if(auditDetail != null){
                    if("FRT".equalsIgnoreCase(auditDetail.getChargeClassificationCode())){
                        if(auditDetail.getId() > maxEntityId){
                            parcelAuditDetail = auditDetail;
                        }
                    }
                }
            }
        }
        return parcelAuditDetail;
    }

    public static ParcelAuditDetailsDto getFirstFrightChargeForNonUpsCarrier(List<ParcelAuditDetailsDto> parcelAuditDetails){
        if(parcelAuditDetails != null && !parcelAuditDetails.isEmpty()){
            for(ParcelAuditDetailsDto auditDetail : parcelAuditDetails) {
                if(auditDetail != null){
                    if("FRT".equalsIgnoreCase(auditDetail.getChargeClassificationCode())){
                        return auditDetail;
                    }
                }
            }
        }
        return null;
    }

    public static Map<Long, List<ParcelAuditDetailsDto>> organiseShipmentsByParentId(List<ParcelAuditDetailsDto> parcelAuditDetails){
        Map<Long, List<ParcelAuditDetailsDto>> shipments = null;
        if(parcelAuditDetails != null && !parcelAuditDetails.isEmpty()) {
            Collections.sort(parcelAuditDetails, new Comparator<ParcelAuditDetailsDto>() {
                @Override
                public int compare(ParcelAuditDetailsDto o1, ParcelAuditDetailsDto o2) {
                    if(o1 != null && o2 != null){
                        return o1.getParentId().compareTo(o2.getParentId());
                    }
                    return 0;
                }
            });
            shipments = new LinkedHashMap<>();
            for (ParcelAuditDetailsDto auditDetail : parcelAuditDetails) {
                if (auditDetail != null) {
                    if(shipments.containsKey(auditDetail.getParentId())){
                        shipments.get(auditDetail.getParentId()).add(auditDetail);
                    } else{
                        List<ParcelAuditDetailsDto> shipmentChanges = new ArrayList<>(Arrays.asList(auditDetail));
                        shipments.put(auditDetail.getParentId(), shipmentChanges);
                    }
                }
            }
        }
        return shipments;
    }

    public static boolean containsCharge(String charge, List<ParcelAuditDetailsDto> parcelAuditDetails){
        if(charge != null && !charge.isEmpty() && parcelAuditDetails != null && !parcelAuditDetails.isEmpty()){
            for(ParcelAuditDetailsDto auditDetails : parcelAuditDetails){
                if(auditDetails != null && auditDetails.getChargeDescription() != null && charge.equalsIgnoreCase(auditDetails.getChargeDescription())){
                    return true;
                }
            }
        }
        return false;
    }

    public static BigDecimal findAmountByChargeClassificationCodeType(String chargeType, List<ParcelAuditDetailsDto> shipmentCharges){
        BigDecimal amount = new BigDecimal("0");
        if(chargeType != null && !chargeType.isEmpty() && shipmentCharges != null && !shipmentCharges.isEmpty()){
            for(ParcelAuditDetailsDto ratedCharge : shipmentCharges){
                if(ratedCharge != null){
                    if(chargeType.equalsIgnoreCase(ratedCharge.getChargeClassificationCode())){
                        if(ratedCharge.getRtrAmount() != null) {
                            amount = amount.add(ratedCharge.getRtrAmount());
                        }
                        break;
                    }
                }
            }
        }
        return amount;
    }

    public static BigDecimal findAmountByChargeDescriptionCodeType(String chargeType, List<ParcelAuditDetailsDto> shipmentCharges){
        BigDecimal amount = new BigDecimal("0");
        if(chargeType != null && !chargeType.isEmpty() && shipmentCharges != null && !shipmentCharges.isEmpty()){
            for(ParcelAuditDetailsDto ratedCharge : shipmentCharges){
                if(ratedCharge != null){
                    if(chargeType.equalsIgnoreCase(ratedCharge.getChargeDescriptionCode())){
                        if(ratedCharge.getRtrAmount() != null) {
                            amount = amount.add(ratedCharge.getRtrAmount());
                        }
                        break;
                    }
                }
            }
        }
        return amount;
    }

    public static BigDecimal findRtrAmountByChargeClassificationCode(String chargeClassificationCode, List<RatedChargeDetailsDto> shipmentCharges){
        BigDecimal amount = new BigDecimal("0");
        if(chargeClassificationCode != null && !chargeClassificationCode.isEmpty() && shipmentCharges != null && !shipmentCharges.isEmpty()){
            for(RatedChargeDetailsDto ratedCharge : shipmentCharges){
                if(ratedCharge != null){
                    if(chargeClassificationCode.equalsIgnoreCase(ratedCharge.getChargeClassificationCode())){
                        if(ratedCharge.getRatedAmount() != null) {
                            amount = amount.add(ratedCharge.getRatedAmount());
                        }
                        break;
                    }
                }
            }
        }
        return amount;
    }

    public static BigDecimal findRtrAmountByChargeClassificationCodeAndChargeDescriptionCode(String chargeClassificationCode, String chargeDescriptionCode, List<RatedChargeDetailsDto> shipmentCharges){
        BigDecimal amount = new BigDecimal("0");
        if(chargeClassificationCode != null && !chargeClassificationCode.isEmpty() && shipmentCharges != null && !shipmentCharges.isEmpty()){
            for(RatedChargeDetailsDto ratedCharge : shipmentCharges){
                if(ratedCharge != null){
                    if(chargeClassificationCode.equalsIgnoreCase(ratedCharge.getChargeClassificationCode()) && chargeDescriptionCode.equalsIgnoreCase(ratedCharge.getChargeDescriptionCode())){
                        if(ratedCharge.getRatedAmount() != null) {
                            amount = amount.add(ratedCharge.getRatedAmount());
                        }
                        break;
                    }
                }
            }
        }
        return amount;
    }

    public static BigDecimal getRatedBaseDiscount(List<RatedChargeDetailsDto> ratedCharges){
        BigDecimal amount = new BigDecimal("0");
        if(ratedCharges != null && !ratedCharges.isEmpty()){
            for(RatedChargeDetailsDto ratedCharge : ratedCharges){
                if(ratedCharge != null){
                    if("FRT".equalsIgnoreCase(ratedCharge.getChargeClassificationCode())){
                        if(ratedCharge.getRatedBaseDiscount() != null) amount = ratedCharge.getRatedBaseDiscount();
                        break;
                    }
                }
            }
        }
        return amount;
    }

    public static BigDecimal getRatedEarnedDiscount(List<RatedChargeDetailsDto> ratedCharges){
        BigDecimal amount = new BigDecimal("0");
        if(ratedCharges != null && !ratedCharges.isEmpty()){
            for(RatedChargeDetailsDto ratedCharge : ratedCharges){
                if(ratedCharge != null){
                    if("FRT".equalsIgnoreCase(ratedCharge.getChargeClassificationCode())){
                        if(ratedCharge.getRatedEarnedDiscount() != null) amount = ratedCharge.getRatedEarnedDiscount();
                        break;
                    }
                }
            }
        }
        return amount;
    }

    public static BigDecimal findAccessorialAmountByAccessorialCode(String accessorialCode, List<RatedChargeDetailsDto> shipmentCharges){
        BigDecimal amount = new BigDecimal("0");
        if(accessorialCode != null && !accessorialCode.isEmpty() && shipmentCharges != null && !shipmentCharges.isEmpty()){
            RatedChargeDetailsDto ratedAcc = shipmentCharges.get(0);
            if(ratedAcc != null){
                if(ratedAcc.getAccessorial1Code() != null && !ratedAcc.getAccessorial1Code().isEmpty() && accessorialCode.equalsIgnoreCase(ratedAcc.getAccessorial1Code())){
                    amount = ratedAcc.getAccessorial1();
                } else if(ratedAcc.getAccessorial2Code() != null && !ratedAcc.getAccessorial2Code().isEmpty() && accessorialCode.equalsIgnoreCase(ratedAcc.getAccessorial2Code())){
                    amount = ratedAcc.getAccessorial2();
                } else if(ratedAcc.getAccessorial3Code() != null && !ratedAcc.getAccessorial3Code().isEmpty() && accessorialCode.equalsIgnoreCase(ratedAcc.getAccessorial3Code())){
                    amount = ratedAcc.getAccessorial3();
                } else if(ratedAcc.getAccessorial4Code() != null && !ratedAcc.getAccessorial4Code().isEmpty() && accessorialCode.equalsIgnoreCase(ratedAcc.getAccessorial4Code())){
                    amount = ratedAcc.getAccessorial4();
                }
            }
        }
        return amount;
    }

    public static BigDecimal getRatedFreightChargeForCommOrResAjustment(List<RatedChargeDetailsDto> shipmentCharges){
        BigDecimal amount = new BigDecimal("0");
        if(shipmentCharges != null && !shipmentCharges.isEmpty()){
            amount = shipmentCharges.get(0).getFreightCharge();
        }
        return amount;
    }

    public static BigDecimal getRatedFuelChargeForCommOrResAjustment(List<RatedChargeDetailsDto> shipmentCharges){
        BigDecimal amount = new BigDecimal("0");
        if(shipmentCharges != null && !shipmentCharges.isEmpty()){
            amount = shipmentCharges.get(0).getFuelSurcharge();
        }
        return amount;
    }

    public static Map<String, List<ParcelAuditDetailsDto>> prepareTrackingNumberWiseAuditDetails(List<ParcelAuditDetailsDto> auditDetailsList){
        Map<String, List<ParcelAuditDetailsDto>> parcelAuditMap = null;
        if(auditDetailsList != null && !auditDetailsList.isEmpty()){
            parcelAuditMap = new HashMap<>();
            for(ParcelAuditDetailsDto parcelAuditDetails : auditDetailsList){
                if(parcelAuditDetails != null){
                    String trackingNumber = parcelAuditDetails.getTrackingNumber();
                    if(trackingNumber != null && !trackingNumber.isEmpty() && parcelAuditMap.containsKey(parcelAuditDetails.getTrackingNumber())){
                        parcelAuditMap.get(trackingNumber).add(parcelAuditDetails);
                    }else{
                        List<ParcelAuditDetailsDto> auditList = new ArrayList<>();
                        auditList.add(parcelAuditDetails);
                        parcelAuditMap.put(trackingNumber, auditList);
                    }
                }
            }
        }
        return parcelAuditMap;
    }

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

    public static RatingQueueBean prepareShipmentEntryForUpsShipment(List<ParcelAuditDetailsDto> shipmentDetails, MsiARChargeCodesDto msiARChargeCodes){
        RatingQueueBean ratingQueueBean = new RatingQueueBean();
        ParcelAuditDetailsDto firstCharge = shipmentDetails.get(0);

        ratingQueueBean.setGffId(firstCharge.getId());
        ratingQueueBean.setTrackingNumber(firstCharge.getTrackingNumber());
        ratingQueueBean.setParentId(firstCharge.getParentId());
        ratingQueueBean.setCarrierId(firstCharge.getCarrierId());
        Map<String, String> dasChargeList = msiARChargeCodes.getDasChargeCodes();
        Map<String, String> lpsCharges = msiARChargeCodes.getLpsChargeCodes();
        boolean hasRJ5Charge = false;
        if(shipmentDetails != null && !shipmentDetails.isEmpty()){
            for(ParcelAuditDetailsDto auditDetails : shipmentDetails) {
                if (auditDetails != null && auditDetails.getPackageDimension() != null && !auditDetails.getPackageDimension().isEmpty()) {
                    try{
                        String [] dimension = auditDetails.getPackageDimension().toLowerCase().split("x");
                        if(dimension != null && dimension.length > 0){
                            auditDetails.setDimLength(dimension[0] != null ? dimension[0].trim() : "");
                            auditDetails.setDimWidth(dimension[1] != null ? dimension[1].trim() : "");
                            auditDetails.setDimHeight(dimension[2] != null ? dimension[2].trim() : "");
                        }
                    }catch (Exception e){}
                }
                if("RJ5".equalsIgnoreCase(auditDetails.getChargeDescriptionCode())) {
                    hasRJ5Charge = true;
                }
            }

            if(firstCharge != null){
                String billOption = (null == firstCharge.getBillOption() ? "" : firstCharge.getBillOption());
                if(billOption.equalsIgnoreCase("Prepaid") || billOption.equals("1") || billOption.equalsIgnoreCase("Outbound")){
                    billOption = "PP";
                }else if(billOption.equalsIgnoreCase("Collect") || billOption.equals("2")){
                    billOption = "FC";
                }else if(billOption.equalsIgnoreCase("Third Party") || billOption.equals("3")){
                    billOption = "TP";
                }

                ratingQueueBean.setBillOption(billOption);

                StringJoiner accessorials = new StringJoiner(",");
                for(ParcelAuditDetailsDto auditDetails : shipmentDetails){
                    if(auditDetails != null){
                        if(auditDetails.getChargeClassificationCode() != null
                                && ParcelAuditConstant.ChargeClassificationCode.ACC.name().equalsIgnoreCase(auditDetails.getChargeClassificationCode())){
                            if(auditDetails.getChargeDescriptionCode() != null && !auditDetails.getChargeDescriptionCode().isEmpty()
                                    && !"RJ5".equalsIgnoreCase(auditDetails.getChargeDescriptionCode())){
                                if(!hasRJ5Charge && auditDetails.getChargeDescriptionCode().equalsIgnoreCase("RES")){
                                    accessorials.add("RSC");
                                } else if(dasChargeList.containsKey(auditDetails.getChargeDescriptionCode())) {
                                    accessorials.add(dasChargeList.get(auditDetails.getChargeDescriptionCode()));
                                } else if(lpsCharges != null && lpsCharges.containsKey(auditDetails.getChargeDescriptionCode())) {
                                    accessorials.add(lpsCharges.get(auditDetails.getChargeDescriptionCode()));
                                } else {
                                    accessorials.add(auditDetails.getChargeDescriptionCode());
                                }
                            }
                        }
                    }
                }
                ratingQueueBean.setAccessorialInfo(accessorials.toString());
                String scacCode = (null == firstCharge.getRtrScacCode() ? "" : "FDEG".equals(firstCharge.getRtrScacCode()) ? "FDE" : firstCharge.getRtrScacCode());
                ratingQueueBean.setScacCode(scacCode);

                String currency = (null == firstCharge.getCurrency() || firstCharge.getCurrency().isEmpty() ? "USD" : firstCharge.getCurrency());
                ratingQueueBean.setCurrencyCode(currency);

                String serviceLevel = findServiceLevel(shipmentDetails);
                if(serviceLevel == null || serviceLevel.trim().isEmpty()){
                    throw new RuntimeException("Invalid Service Level for " + shipmentDetails.get(0).getTrackingNumber());
                } else {
                    ratingQueueBean.setService(serviceLevel);
                }
                ratingQueueBean.setCustomerCode(firstCharge.getCustomerCode());
                ratingQueueBean.setShipperNumber(shipmentDetails.get(0).getShipperNumber());
                ratingQueueBean.setRevenueTier(shipmentDetails.get(0).getRevenueTier());
                ParcelAuditDetailsDto latestFreightCharge = ParcelRatingUtil.getLatestFrightCharge(shipmentDetails);
                if(latestFreightCharge != null){
                    float weight = (null == latestFreightCharge.getPackageWeight() || latestFreightCharge.getPackageWeight().isEmpty() ? 1f : Float.parseFloat(latestFreightCharge.getPackageWeight()));
                    String weightUnit = (null == latestFreightCharge.getWeightUnit() || latestFreightCharge.getWeightUnit().isEmpty() || "L".equalsIgnoreCase(latestFreightCharge.getWeightUnit()) ? "LBS" : latestFreightCharge.getWeightUnit());
                    long quantity = (null == latestFreightCharge.getItemQuantity() || latestFreightCharge.getItemQuantity().isEmpty() ? 1l : Long.parseLong(latestFreightCharge.getItemQuantity()));
                    String quantityUnit = (null == latestFreightCharge.getQuantityUnit() || latestFreightCharge.getQuantityUnit().isEmpty() ? "PCS" : latestFreightCharge.getQuantityUnit());
                    float dimLenght = (null == latestFreightCharge.getDimLength() || latestFreightCharge.getDimLength().isEmpty() ? 0.0f : Float.parseFloat(latestFreightCharge.getDimLength()));
                    float dimWidth = (null == latestFreightCharge.getDimWidth() || latestFreightCharge.getDimWidth().isEmpty()? 0.0f : Float.parseFloat(latestFreightCharge.getDimWidth()));
                    float dimHeight = (null == latestFreightCharge.getDimHeight() || latestFreightCharge.getDimHeight().isEmpty() ? 0.0f : Float.parseFloat(latestFreightCharge.getDimHeight()));
                    String dimUnit = (null == latestFreightCharge.getUnitOfDim() || latestFreightCharge.getUnitOfDim().isEmpty() ? "" : (latestFreightCharge.getUnitOfDim().equalsIgnoreCase("I") ? "in" : latestFreightCharge.getUnitOfDim()));
                    float actualWeight = (null == latestFreightCharge.getActualWeight() ? null : latestFreightCharge.getActualWeight().floatValue());
                    String actualWeightUnit = (null == latestFreightCharge.getActualWeightUnit() || latestFreightCharge.getActualWeightUnit().isEmpty() || "L".equalsIgnoreCase(latestFreightCharge.getActualWeightUnit()) ? "LBS" : latestFreightCharge.getActualWeightUnit());

                    ratingQueueBean.setFrtWeight(weight);
                    ratingQueueBean.setFrtWeightUnits(weightUnit);
                    ratingQueueBean.setFrtQyantity(quantity);
                    ratingQueueBean.setFrtQuantityUnits(quantityUnit);
                    ratingQueueBean.setDimLength(dimLenght);
                    ratingQueueBean.setDimWidth(dimWidth);
                    ratingQueueBean.setDimHeight(dimHeight);
                    ratingQueueBean.setDimUnits(dimUnit);
                    ratingQueueBean.setFrtActualWeight(actualWeight);
                    ratingQueueBean.setFrtActualWeightUnits(actualWeightUnit);
                    ratingQueueBean.setPackageType(latestFreightCharge.getPackageType());

                } else{
                    throw new RuntimeException("Freight Item not found");
                }

                ratingQueueBean.setShipDate(firstCharge.getPickupDate());

                String senderCountry =  (null == firstCharge.getSenderCountry() || firstCharge.getSenderCountry().isEmpty() ? "US" :  firstCharge.getSenderCountry());
                String senderState =  (null == firstCharge.getSenderState() ? "" :  firstCharge.getSenderState());
                String senderCity =  (null == firstCharge.getSenderCity() ? "" :  firstCharge.getSenderCity());
                String senderZipCode =  (null == firstCharge.getSenderZipCode() ? "" :  firstCharge.getSenderZipCode());

                ratingQueueBean.setShipperCountry(senderCountry);
                ratingQueueBean.setShipperState(senderState);
                ratingQueueBean.setShipperCity(senderCity);
                ratingQueueBean.setShipperZip(senderZipCode);

                if(firstCharge.getDeliveryDate() != null){
                    ratingQueueBean.setDeliveryDate(firstCharge.getDeliveryDate());
                }else{
                    ratingQueueBean.setDeliveryDate(firstCharge.getPickupDate());
                }
                String receiverCountry =  (null == firstCharge.getReceiverCountry() || firstCharge.getReceiverCountry().isEmpty() ? "US" :  firstCharge.getReceiverCountry());
                String receiverState =  (null == firstCharge.getReceiverState() ? "" :  firstCharge.getReceiverState());
                String receiverCity =  (null == firstCharge.getReceiverCity() ? "" :  firstCharge.getReceiverCity());
                String receiverZipCode = (null == firstCharge.getReceiverZipCode() ? "" :  firstCharge.getReceiverZipCode());

                ratingQueueBean.setReceiverCountry(receiverCountry);
                ratingQueueBean.setReceiverState(receiverState);
                ratingQueueBean.setReceiverCity(receiverCity);
                ratingQueueBean.setReceiverZip(receiverZipCode);
                ratingQueueBean.setHwtIdentifier(firstCharge.getMultiWeightNumber());
            }
        }
        return ratingQueueBean;
    }

    public static RatingQueueBean prepareShipmentEntryForNonUpsShipment(List<ParcelAuditDetailsDto> shipmentDetails, MsiARChargeCodesDto msiARChargeCodes){
        RatingQueueBean ratingQueueBean = new RatingQueueBean();
        ParcelAuditDetailsDto firstCharge = shipmentDetails.get(0);

        ratingQueueBean.setManiestId(firstCharge.getId());
        ratingQueueBean.setTrackingNumber(firstCharge.getTrackingNumber());
        ratingQueueBean.setParentId(firstCharge.getParentId());
        ratingQueueBean.setCarrierId(firstCharge.getCarrierId());
        Map<String, String> dasChargeList = msiARChargeCodes.getDasChargeCodes();
        Map<String, String> lpsCharges = msiARChargeCodes.getLpsChargeCodes();

        String billOption = (null == firstCharge.getBillOption() ? "" : firstCharge.getBillOption());
        if(billOption.equalsIgnoreCase("Prepaid") || billOption.equals("1") || billOption.equalsIgnoreCase("Outbound")){
            billOption = "PP";
        }else if(billOption.equalsIgnoreCase("Collect") || billOption.equals("2")){
            billOption = "FC";
        }else if(billOption.equalsIgnoreCase("Third Party") || billOption.equals("3")){
            billOption = "TP";
        }
        ratingQueueBean.setBillOption(billOption);

        StringJoiner accessorials = new StringJoiner(",");
        for(ParcelAuditDetailsDto auditDetails : shipmentDetails){
            if(auditDetails != null){
                if(auditDetails.getChargeClassificationCode() != null && ParcelAuditConstant.ChargeClassificationCode.ACS.name().equalsIgnoreCase(auditDetails.getChargeClassificationCode())
                        && !Arrays.asList(ParcelAuditConstant.ChargeDescriptionCode.FSC.name(), ParcelAuditConstant.ChargeDescriptionCode.DSC.name()).contains(auditDetails.getChargeDescriptionCode())){
                    if(auditDetails.getChargeDescriptionCode().equalsIgnoreCase("RES")){
                        accessorials.add("RSC");
                    } else if(dasChargeList.containsKey(auditDetails.getChargeDescriptionCode())){
                        if(auditDetails.getChargeDescription() != null && (auditDetails.getChargeDescription().contains("EXTENDED") || auditDetails.getChargeDescription().contains("extended"))){
                            accessorials.add("DSX");
                        } else {
                            accessorials.add(dasChargeList.get(auditDetails.getChargeDescriptionCode()));
                        }
                    } else if(lpsCharges != null && lpsCharges.containsKey(auditDetails.getChargeDescriptionCode())) {
                        accessorials.add(lpsCharges.get(auditDetails.getChargeDescriptionCode()));
                    } else {
                        accessorials.add(auditDetails.getChargeDescriptionCode());
                    }
                }
            }
        }
        ratingQueueBean.setAccessorialInfo(accessorials.toString());

        String rtrScacCode = (null == firstCharge.getRtrScacCode() ? "" : "FDEG".equals(firstCharge.getRtrScacCode()) ? "FDE" : firstCharge.getRtrScacCode());
        String currency = (null == firstCharge.getCurrency() || firstCharge.getCurrency().isEmpty() ? "USD" : firstCharge.getCurrency());
        ratingQueueBean.setScacCode(rtrScacCode);
        ratingQueueBean.setCurrencyCode(currency);

        String serviceLevel = findServiceLevel(shipmentDetails);
        if(serviceLevel == null || serviceLevel.trim().isEmpty())
            throw new RuntimeException("Invalid Service Level for " + firstCharge.getTrackingNumber());

        ratingQueueBean.setService(serviceLevel);
        ratingQueueBean.setCustomerCode(firstCharge.getCustomerCode());
        ratingQueueBean.setRevenueTier(firstCharge.getRevenueTier());
        ratingQueueBean.setShipperNumber(firstCharge.getShipperNumber());


        ParcelAuditDetailsDto firstBaseCharge = ParcelRatingUtil.getFirstFrightChargeForNonUpsCarrier(shipmentDetails);
        if (firstBaseCharge != null) {
            if (firstBaseCharge.getChargeClassificationCode() != null
                    && ParcelAuditConstant.ChargeClassificationCode.FRT.name().equalsIgnoreCase(firstBaseCharge.getChargeClassificationCode())) {
                Float weight = (null == firstBaseCharge.getPackageWeight() || firstBaseCharge.getPackageWeight().isEmpty() ? 0.0f : Float.parseFloat(firstBaseCharge.getPackageWeight()));
                String weightUnit = (null == firstBaseCharge.getWeightUnit() || firstBaseCharge.getWeightUnit().isEmpty() || "L".equalsIgnoreCase(firstBaseCharge.getWeightUnit()) ? "LBS" : firstBaseCharge.getWeightUnit());
                Long quantity = (null == firstBaseCharge.getItemQuantity() || firstBaseCharge.getItemQuantity().isEmpty() ? 1l : Long.parseLong(firstBaseCharge.getItemQuantity()));
                String quantityUnit = (null == firstBaseCharge.getQuantityUnit() || firstBaseCharge.getQuantityUnit().isEmpty() ? "PCS" : firstBaseCharge.getQuantityUnit());
                Float dimLenght = (null == firstBaseCharge.getDimLength() || firstBaseCharge.getDimLength().isEmpty() ? 0.0f : Float.parseFloat(firstBaseCharge.getDimLength()));
                Float dimWidth = (null == firstBaseCharge.getDimWidth() || firstBaseCharge.getDimWidth().isEmpty()? 0.0f : Float.parseFloat(firstBaseCharge.getDimWidth()));
                Float dimHeight = (null == firstBaseCharge.getDimHeight() || firstBaseCharge.getDimHeight().isEmpty() ? 0.0f : Float.parseFloat(firstBaseCharge.getDimHeight()));
                String dimUnit = (null == firstBaseCharge.getUnitOfDim() || firstBaseCharge.getUnitOfDim().isEmpty() ? "" : firstBaseCharge.getUnitOfDim().equalsIgnoreCase("I") ? "in" : firstBaseCharge.getUnitOfDim());
                BigDecimal actualWeight = (null == firstBaseCharge.getActualWeight() ? new BigDecimal("0") : firstBaseCharge.getActualWeight());
                String actualWeightUnit = (null == firstBaseCharge.getActualWeightUnit() || firstBaseCharge.getActualWeightUnit().isEmpty() || "L".equalsIgnoreCase(firstBaseCharge.getActualWeightUnit()) ? "LBS" : firstBaseCharge.getActualWeightUnit());

                ratingQueueBean.setFrtWeight(weight);
                ratingQueueBean.setFrtWeightUnits(weightUnit);
                ratingQueueBean.setFrtActualWeight(actualWeight.floatValue());
                ratingQueueBean.setFrtActualWeightUnits(actualWeightUnit);
                ratingQueueBean.setFrtQyantity(quantity);
                ratingQueueBean.setFrtQuantityUnits(quantityUnit);
                ratingQueueBean.setDimLength(dimLenght);
                ratingQueueBean.setDimWidth(dimWidth);
                ratingQueueBean.setDimHeight(dimHeight);
                ratingQueueBean.setDimUnits(dimUnit);
                ratingQueueBean.setPackageType(firstBaseCharge.getPackageType());
            }
        }

        ratingQueueBean.setShipDate(firstCharge.getPickupDate());

        String senderCountry =  (null == firstCharge.getSenderCountry() || firstCharge.getSenderCountry().isEmpty() ? "US" :  firstCharge.getSenderCountry());
        String senderState =  (null == firstCharge.getSenderState() ? "" :  firstCharge.getSenderState());
        String senderCity =  (null == firstCharge.getSenderCity() ? "" :  firstCharge.getSenderCity());
        String senderZipCode =  (null == firstCharge.getSenderZipCode() ? "" :  firstCharge.getSenderZipCode());

        ratingQueueBean.setShipperCountry(senderCountry);
        ratingQueueBean.setShipperState(senderState);
        ratingQueueBean.setShipperCity(senderCity);
        ratingQueueBean.setShipperZip(senderZipCode);

        if(firstCharge.getDeliveryDate() != null){
            ratingQueueBean.setDeliveryDate(firstCharge.getDeliveryDate());
        }else{
            ratingQueueBean.setDeliveryDate(firstCharge.getPickupDate());
        }
        String receiverCountry =  (null == firstCharge.getReceiverCountry() || firstCharge.getReceiverCountry().isEmpty() ? "US" :  firstCharge.getReceiverCountry());
        String receiverState =  (null == firstCharge.getReceiverState() ? "" :  firstCharge.getReceiverState());
        String receiverCity =  (null == firstCharge.getReceiverCity() ? "" :  firstCharge.getReceiverCity());
        String receiverZipCode = (null == firstCharge.getReceiverZipCode() ? "" :  firstCharge.getReceiverZipCode());

        ratingQueueBean.setReceiverCountry(receiverCountry);
        ratingQueueBean.setReceiverState(receiverState);
        ratingQueueBean.setReceiverCity(receiverCity);
        ratingQueueBean.setReceiverZip(receiverZipCode);
        ratingQueueBean.setHwtIdentifier(firstCharge.getMultiWeightNumber());

        return ratingQueueBean;
    }

    public static boolean isShipmentRated(List<ParcelAuditDetailsDto> shipment){
        boolean rated = false;
        if(shipment != null && !shipment.isEmpty()) {
            for(ParcelAuditDetailsDto shipmentCharge : shipment){
                if(shipmentCharge != null && shipmentCharge.getRtrStatus() != null
                        && Arrays.asList(ParcelAuditConstant.RTRStatus.CLOSED.value, ParcelAuditConstant.RTRStatus.UNDER_CHARGED.value, ParcelAuditConstant.RTRStatus.OVER_CHARGED.value).contains(shipmentCharge.getRtrStatus())) {
                    rated = true;
                }
            }
        }
        return rated;
    }

    public static ParcelAuditRequestResponseLog prepareRequestResponseLog(String requestPayload, String response, Long entityId, String tableName) {
        ParcelAuditRequestResponseLog requestResponseLog = new ParcelAuditRequestResponseLog();
        requestResponseLog.setEntityIds(entityId.toString());
        requestResponseLog.setCreateUser(ParcelAuditConstant.PARCEL_RTR_RATING_USER_NAME);
        requestResponseLog.setTableName(tableName);
        if(requestPayload != null && !requestPayload.isEmpty()){
            int requestLength = requestPayload.length();
            if (requestLength <= 4000) {
                requestResponseLog.setRequestXml1(requestPayload);
            } else {
                requestResponseLog.setRequestXml1(requestPayload.substring(0, 3999));
                if(requestLength >= 4000 && requestLength < 8000){
                    requestResponseLog.setRequestXml2(requestPayload.substring(4000, 7999));
                }else {
                    requestResponseLog.setRequestXml2(requestPayload.substring(4000, 7999));
                    requestResponseLog.setRequestXml3(requestPayload.substring(8000, requestLength));
                }
            }
        }

        if(response != null && !response.isEmpty()){
            int respLength = response.length();
            if (respLength <= 4000) {
                requestResponseLog.setResponseXml1(response);
            } else {
                requestResponseLog.setResponseXml1(response.substring(0, 3999));
                if(respLength >= 4000 && respLength < 8000){
                    requestResponseLog.setResponseXml2(response.substring(4000, 7999));
                }else if(respLength >= 8000 && respLength < 12000){
                    requestResponseLog.setResponseXml2(response.substring(4000, 7999));
                    requestResponseLog.setResponseXml3(response.substring(8000, respLength));
                }else{
                    requestResponseLog.setResponseXml2(response.substring(4000, 7999));
                    try{  requestResponseLog.setResponseXml3(response.substring(8000, 11999));}catch (Exception e){}
                }
            }
        }
        return requestResponseLog;
    }

    public static BigDecimal findSumOfNetAmount(List<ParcelAuditDetailsDto> parcelAuditDetailsList) {
        BigDecimal sumOfNetAmount = new BigDecimal("0.0");
        if(parcelAuditDetailsList != null && !parcelAuditDetailsList.isEmpty()){
            for(ParcelAuditDetailsDto parcelAuditDetails : parcelAuditDetailsList){
                if(parcelAuditDetails != null && parcelAuditDetails.getNetAmount() != null && !parcelAuditDetails.getNetAmount().isEmpty()){
                    try{
                        sumOfNetAmount = sumOfNetAmount.add(new BigDecimal(parcelAuditDetails.getNetAmount()));
                    }catch (Exception e){}
                }
            }
        }
        return sumOfNetAmount;
    }

    public static boolean hasMultipleParentIds(Map<Long, List<ParcelAuditDetailsDto>> shipments){
        return shipments != null && shipments.size() > 1;
    }

    public static boolean hasFrtCharge(List<ParcelAuditDetailsDto> shipment){
        boolean frtFound = false;
        if(shipment != null && !shipment.isEmpty()) {
            for(ParcelAuditDetailsDto auditDetails : shipment) {
                if(auditDetails != null && "FRT".equalsIgnoreCase(auditDetails.getChargeClassificationCode())){
                    frtFound = true;
                    break;
                }
            }
        }
        return frtFound;
    }

    public static ParcelAuditDetailsDto getPreviousShipmentBaseChargeDetails(Map<Long, List<ParcelAuditDetailsDto>> allSortedShipments, Long parentId) {
        List<ParcelAuditDetailsDto> previousShipment = null;
        if(allSortedShipments != null && !allSortedShipments.isEmpty()){
            for(Map.Entry<Long, List<ParcelAuditDetailsDto>> shipmentEntry : allSortedShipments.entrySet()) {
                if(shipmentEntry != null) {
                    if(parentId.equals(shipmentEntry.getKey()) && previousShipment != null) {
                        for(ParcelAuditDetailsDto charge : previousShipment){
                            if(charge != null && "FRT".equalsIgnoreCase(charge.getChargeClassificationCode())){
                                return charge;
                            }
                        }
                    }
                }
                previousShipment = shipmentEntry.getValue();
            }
        }
        return null;
    }

    public static List<ParcelAuditDetailsDto> getPreviousShipmentDetails(Map<Long, List<ParcelAuditDetailsDto>> allSortedShipments, Long parentId) {
        List<ParcelAuditDetailsDto> previousShipment = null;
        if(allSortedShipments != null && !allSortedShipments.isEmpty()){
            for(Map.Entry<Long, List<ParcelAuditDetailsDto>> shipmentEntry : allSortedShipments.entrySet()) {
                if(shipmentEntry != null) {
                    if(parentId.equals(shipmentEntry.getKey()) && previousShipment != null) {
                        return previousShipment;
                    }
                }
                previousShipment = shipmentEntry.getValue();
            }
        }
        return null;
    }

    public static boolean isRatingDone(String ratingStatus){
        return ratingStatus != null
                && Arrays.asList(ParcelAuditConstant.RTRStatus.CLOSED.value, ParcelAuditConstant.RTRStatus.UNDER_CHARGED.value, ParcelAuditConstant.RTRStatus.OVER_CHARGED.value).contains(ratingStatus);
    }

    public static boolean isFirstShipmentToRate(Map<Long, List<ParcelAuditDetailsDto>> allSortedShipments, Long parentId){
        boolean isFirstShipment = false;
        if(allSortedShipments != null && !allSortedShipments.isEmpty()) {
            Long firstShipmentParentId = allSortedShipments.keySet().iterator().next();
            if(firstShipmentParentId != null && parentId != null && firstShipmentParentId.equals(parentId)) {
                isFirstShipment = true;
            }
        }
        return isFirstShipment;
    }

    public static boolean containsFuelSurcharge(List<ParcelAuditDetailsDto> shipment){
        if(shipment != null) {
            for(ParcelAuditDetailsDto charge : shipment){
                if(charge != null){
                    if("FSC".equalsIgnoreCase(charge.getChargeClassificationCode())){
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public static boolean containsFRTCharge(List<ParcelAuditDetailsDto> shipment){
        if(shipment != null) {
            for(ParcelAuditDetailsDto charge : shipment){
                if(charge != null){
                    if("FRT".equalsIgnoreCase(charge.getChargeClassificationCode())){
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public static ParcelAuditDetailsDto findFrtCharge(List<ParcelAuditDetailsDto> shipment) {
        if (shipment != null) {
            for (ParcelAuditDetailsDto charge : shipment) {
                if (charge != null) {
                    if ("FRT".equalsIgnoreCase(charge.getChargeClassificationCode())) {
                        return charge;
                    }
                }
            }
        }
        return null;
    }

    /**
     * This method will prepare bundle number or multi weight id tracking number wise details
     *
     * @param listMap
     * @return
     */
    public static Map<String, List<ParcelAuditDetailsDto>> prepareMultiWeightNumberWiseAuditDetails(Map<String, List<ParcelAuditDetailsDto>> listMap) {

        Map<String, List<ParcelAuditDetailsDto>> mwtDetailsMap = new HashMap<>();
        Map<String, List<ParcelAuditDetailsDto>> tempMap = new HashMap<>(listMap);
        for (Map.Entry<String, List<ParcelAuditDetailsDto>> entry : tempMap.entrySet()) {


            for (ParcelAuditDetailsDto parcelAuditDetails : entry.getValue()) {
                if (parcelAuditDetails != null) {

                    if (parcelAuditDetails.getMultiWeightNumber() != null && !parcelAuditDetails.getMultiWeightNumber().isEmpty()) {
                        if (mwtDetailsMap.containsKey(parcelAuditDetails.getMultiWeightNumber()))
                            mwtDetailsMap.get(parcelAuditDetails.getMultiWeightNumber()).addAll(tempMap.get(parcelAuditDetails.getTrackingNumber()));
                        else
                            mwtDetailsMap.put(parcelAuditDetails.getMultiWeightNumber(), tempMap.get(parcelAuditDetails.getTrackingNumber()));

                        listMap.remove(parcelAuditDetails.getTrackingNumber());
                        break;
                    }
                }
            }
        }
        tempMap = null;
        return mwtDetailsMap;
    }

    /**
     * This method will prepare Lead Shipment tracking number wise details
     *
     * @param listMap
     * @return
     */
    public static Map<String, List<ParcelAuditDetailsDto>> prepareHwtNumberWiseAuditDetails(Map<String, List<ParcelAuditDetailsDto>> listMap) {

        Map<String, List<ParcelAuditDetailsDto>> hwtDetailsMap = new HashMap<>();
        Map<String, List<ParcelAuditDetailsDto>> tempMap = new HashMap<>(listMap);
        for (Map.Entry<String, List<ParcelAuditDetailsDto>> entry : tempMap.entrySet()) {

            for (ParcelAuditDetailsDto parcelAuditDetails : entry.getValue()) {
                if (parcelAuditDetails != null) {

                    if (parcelAuditDetails.getMultiWeightNumber() != null && !parcelAuditDetails.getMultiWeightNumber().isEmpty()) {
                        if (hwtDetailsMap.containsKey(parcelAuditDetails.getMultiWeightNumber()))
                            hwtDetailsMap.get(parcelAuditDetails.getMultiWeightNumber()).addAll(tempMap.get(parcelAuditDetails.getTrackingNumber()));
                        else
                            hwtDetailsMap.put(parcelAuditDetails.getMultiWeightNumber(), tempMap.get(parcelAuditDetails.getTrackingNumber()));

                        listMap.remove(parcelAuditDetails.getTrackingNumber());
                        break;
                    }
                }
            }
        }
        tempMap = null;
        return hwtDetailsMap;
    }

    /**
     * This method will return lead shipment details
     *
     * @param parcelAuditDetails
     * @return
     */
    public static List<ParcelAuditDetailsDto> getLeadShipmentDetails(List<ParcelAuditDetailsDto> parcelAuditDetails) {

        List<ParcelAuditDetailsDto> detailsDtos = new ArrayList<>();

        ParcelAuditDetailsDto minDto = parcelAuditDetails.stream().min(Comparator.comparing(ParcelAuditDetailsDto::getId)).orElseThrow(NoSuchElementException::new);

        if (minDto != null) {
            for (ParcelAuditDetailsDto dto : parcelAuditDetails) {

                if (StringUtils.equalsIgnoreCase(dto.getTrackingNumber(), minDto.getTrackingNumber())) {

                    detailsDtos.add(dto);
                }
            }
        }
        return detailsDtos;
    }

    /**
     * This method will prepare bundle number or multi weight id tracking number wise details
     *
     * @return
     */
    public static Map<String, List<RatingQueueBean>> prepareHwtShipmentWiseInfo(ArrayList<RatingQueueBean> beanList) {

        Collections.sort(beanList, new Comparator<RatingQueueBean>() {
            @Override
            public int compare(RatingQueueBean h1, RatingQueueBean h2) {
                return h1.getHwtIdentifier().compareTo(h2.getHwtIdentifier());
            }
        });
        Map<String, List<RatingQueueBean>> mwtDetailsMap = new HashMap<>();


        for (RatingQueueBean queueBean : beanList) {


            if (queueBean.getHwtIdentifier() != null && !queueBean.getHwtIdentifier().isEmpty()) {



                if (mwtDetailsMap.containsKey(queueBean.getHwtIdentifier())) {
                    mwtDetailsMap.get(queueBean.getHwtIdentifier()).add(queueBean);
                } else {
                    List<RatingQueueBean> dtoList = new ArrayList<RatingQueueBean>();
                    dtoList.add(queueBean);
                    mwtDetailsMap.put(queueBean.getHwtIdentifier(), dtoList);


                }


            }


        }


        return mwtDetailsMap;

    }

    /**
     * @param queueBeans
     * @return
     */
    public static String prepareTrackingNumbersInOperator(List<RatingQueueBean> queueBeans) {

        StringBuilder builder = null;
            if(queueBeans.size()==1){
                builder = new StringBuilder();
                builder.append( queueBeans.get(0).getTrackingNumber() );
            }else {
                for (RatingQueueBean bean : queueBeans) {

                    if (builder == null) {
                        builder = new StringBuilder();
                    } else {
                        builder.append(",");
                    }
                    builder.append("'" + bean.getTrackingNumber() + "'");
                }
            }
        return builder.toString();
    }

    /**
     * @param queueBeans
     * @return
     */
    public static String prepareQueueIdsInOperator(List<RatingQueueBean> queueBeans) {

        StringBuilder builder = null;

        for (RatingQueueBean bean : queueBeans) {

            if (builder == null) {
                builder = new StringBuilder();
            } else {
                builder.append(",");
            }
            builder.append(bean.getRatingQueueId());
        }
        return builder.toString();
    }


    /**
     * @param shipmentToRate
     * @param queueBeans
     * @return
     */
    public static RatingQueueBean getLeadShipmentQueueBean(List<ParcelAuditDetailsDto> shipmentToRate, List<RatingQueueBean> queueBeans) {

        for (RatingQueueBean bean : queueBeans) {

            if (shipmentToRate.get(0).getParentId().equals(bean.getManiestId())) {

                return bean;
            }
        }
        return null;
    }
}
