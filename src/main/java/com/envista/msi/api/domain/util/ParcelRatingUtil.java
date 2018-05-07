package com.envista.msi.api.domain.util;

import com.envista.msi.api.web.rest.dto.rtr.ParcelAuditDetailsDto;
import com.envista.msi.api.web.rest.dto.rtr.ParcelRateDetailsDto;
import com.envista.msi.api.web.rest.dto.rtr.RatedChargeDetailsDto;

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
}
