package com.envista.msi.api.domain.util;

import com.envista.msi.api.web.rest.dto.rtr.ParcelAuditDetailsDto;

import java.util.List;

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
}
