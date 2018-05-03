package com.envista.msi.api.domain.util;

import com.envista.msi.api.web.rest.dto.rtr.MsiARChargeCodesDto;
import com.envista.msi.api.web.rest.dto.rtr.ParcelAuditDetailsDto;
import com.envista.msi.api.web.rest.dto.rtr.ParcelRateDetailsDto;
import com.envista.msi.api.web.rest.dto.rtr.RatedChargeDetailsDto;
import com.envista.msi.api.web.rest.util.DateUtil;
import com.envista.msi.api.web.rest.util.audit.parcel.ParcelAuditConstant;
import com.envista.msi.rating.bean.RatingQueueBean;

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

    public static RatingQueueBean prepareShipmentEntryForUpsShipment(List<ParcelAuditDetailsDto> shipmentDetails, String licenseKey, MsiARChargeCodesDto msiARChargeCodes){
        RatingQueueBean ratingQueueBean = new RatingQueueBean();
        ParcelAuditDetailsDto firstCharge = shipmentDetails.get(0);

        ratingQueueBean.setManiestId(firstCharge.getId());
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


            //String mode = "PCL";
            ParcelAuditDetailsDto parcelAuditDetails = shipmentDetails.get(0);
            if(parcelAuditDetails != null){
                String billOption = (null == parcelAuditDetails.getBillOption() ? "" : parcelAuditDetails.getBillOption());
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
                String scacCode = (null == parcelAuditDetails.getRtrScacCode() ? "" : "FDEG".equals(parcelAuditDetails.getRtrScacCode()) ? "FDE" : parcelAuditDetails.getRtrScacCode());
                ratingQueueBean.setScacCode(scacCode);

                String currency = (null == parcelAuditDetails.getCurrency() || parcelAuditDetails.getCurrency().isEmpty() ? "USD" : parcelAuditDetails.getCurrency());
                ratingQueueBean.setCurrencyCode(currency);

                String serviceLevel = findServiceLevel(shipmentDetails);
                if(serviceLevel == null || serviceLevel.trim().isEmpty()){
                    throw new RuntimeException("Invalid Service Level for " + shipmentDetails.get(0).getTrackingNumber());
                } else {
                    ratingQueueBean.setService(serviceLevel);
                }
                ratingQueueBean.setCustomerCode(parcelAuditDetails.getCustomerCode());
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

                } else{
                    throw new RuntimeException("Freight Item not found");
                }

                ratingQueueBean.setShipDate(parcelAuditDetails.getPickupDate());

                String senderCountry =  (null == parcelAuditDetails.getSenderCountry() || parcelAuditDetails.getSenderCountry().isEmpty() ? "US" :  parcelAuditDetails.getSenderCountry());
                String senderState =  (null == parcelAuditDetails.getSenderState() ? "" :  parcelAuditDetails.getSenderState());
                String senderCity =  (null == parcelAuditDetails.getSenderCity() ? "" :  parcelAuditDetails.getSenderCity());
                String senderZipCode =  (null == parcelAuditDetails.getSenderZipCode() ? "" :  parcelAuditDetails.getSenderZipCode());

                ratingQueueBean.setShipperCountry(senderCountry);
                ratingQueueBean.setShipperState(senderState);
                ratingQueueBean.setShipperCity(senderCity);
                ratingQueueBean.setShipperZip(senderZipCode);

                if(parcelAuditDetails.getDeliveryDate() != null){
                    ratingQueueBean.setDeliveryDate(parcelAuditDetails.getDeliveryDate());
                }else{
                    ratingQueueBean.setDeliveryDate(parcelAuditDetails.getPickupDate());
                }
                String receiverCountry =  (null == parcelAuditDetails.getReceiverCountry() || parcelAuditDetails.getReceiverCountry().isEmpty() ? "US" :  parcelAuditDetails.getReceiverCountry());
                String receiverState =  (null == parcelAuditDetails.getReceiverState() ? "" :  parcelAuditDetails.getReceiverState());
                String receiverCity =  (null == parcelAuditDetails.getReceiverCity() ? "" :  parcelAuditDetails.getReceiverCity());
                String receiverZipCode = (null == parcelAuditDetails.getReceiverZipCode() ? "" :  parcelAuditDetails.getReceiverZipCode());

                ratingQueueBean.setReceiverCountry(receiverCountry);
                ratingQueueBean.setReceiverState(receiverState);
                ratingQueueBean.setReceiverCity(receiverCity);
                ratingQueueBean.setReceiverZip(receiverZipCode);
            }
        }
        return ratingQueueBean;
    }

    public static RatingQueueBean prepareShipmentEntryForNonUpsShipment(List<ParcelAuditDetailsDto> shipmentDetails, String licenseKey, MsiARChargeCodesDto msiARChargeCodes){
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
                        //Need to change logic here
                        accessorials.add(dasChargeList.get(auditDetails.getChargeDescriptionCode()));
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

        return ratingQueueBean;
    }
}
