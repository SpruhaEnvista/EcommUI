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

    /*public static BigDecimal getRatedBaseDiscount(List<ParcelRateDetailsDto> ratedCharges){
        BigDecimal amount = new BigDecimal("0");
        if(ratedCharges != null && !ratedCharges.isEmpty()){
            for(RatedChargeDetailsDto ratedCharge : ratedCharges){
                if(ratedCharge != null){
                    if("FRT".equalsIgnoreCase(ratedCharge.getChargeClassificationCode())){
                        if(ratedCharge.getBaseDiscount() != null) amount = ratedCharge.getBaseDiscount();
                        break;
                    }
                }
            }
        }
        return amount;
    }

    public static BigDecimal getRatedEarnedDiscount(List<ParcelRateDetailsDto> ratedCharges){
        BigDecimal amount = new BigDecimal("0");
        if(ratedCharges != null && !ratedCharges.isEmpty()){
            for(RatedChargeDetailsDto ratedCharge : ratedCharges){
                if(ratedCharge != null){
                    if("FRT".equalsIgnoreCase(ratedCharge.getChargeClassificationCode())){
                        if(ratedCharge.() != null) amount = ratedCharge.getBaseDiscount();
                        break;
                    }
                }
            }
        }
        return amount;
    }*/
}
