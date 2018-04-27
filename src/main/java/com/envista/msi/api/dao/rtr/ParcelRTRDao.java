package com.envista.msi.api.dao.rtr;

import com.envista.msi.api.dao.DaoException;
import com.envista.msi.api.domain.PersistentContext;
import com.envista.msi.api.domain.util.QueryParameter;
import com.envista.msi.api.domain.util.StoredProcedureParameter;
import com.envista.msi.api.web.rest.dto.rtr.*;
import org.springframework.stereotype.Repository;

import javax.inject.Inject;
import javax.persistence.ParameterMode;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Sujit kumar on 08/06/2017.
 */

@Repository("parcelRTRDao")
public class ParcelRTRDao {

    @Inject
    private PersistentContext persistentContext;

    public List<ParcelAuditDetailsDto> loadUpsParcelAuditDetails(String customerIds, String fromDate, String toDate, String trackingNumbers, String invoiceId, Integer ignoreRtrStatus){
        QueryParameter queryParameter = QueryParameter.with("p_from_date", fromDate)
                .and("p_to_date", toDate)
                .and("p_tracking_numbers", trackingNumbers)
                .and("p_invoice_id", invoiceId)
                .and("p_ignore_rtr_status", ignoreRtrStatus);

        if(customerIds != null && !customerIds.isEmpty()){
            queryParameter.and("p_customer_CSV", customerIds);
        }else{
            queryParameter.and("p_customer_CSV", "");
        }
        List<ParcelAuditDetailsDto> parcelAuditDetailsList = persistentContext.findEntitiesAndMapFields(ParcelAuditDetailsDto.Config.StoredProcedureQueryName.AUDIT_UPS_PARCEL_DETAILS, queryParameter);
        if(parcelAuditDetailsList != null) parcelAuditDetailsList.forEach(auditDetails -> persistentContext.getHibernateSession().evict(auditDetails));
        return parcelAuditDetailsList;
    }

    public List<ParcelAuditDetailsDto> loadUpsParcelAuditDetails(String fromDate, String toDate, String trackingNumber){
        return loadUpsParcelAuditDetails(null, fromDate, toDate, trackingNumber, null, 0);
    }

    public List<ParcelAuditDetailsDto> loadUpsParcelAuditDetails(String customerIds, String trackingNumber, Integer ignoreRtrStatus){
        return loadUpsParcelAuditDetails(customerIds, null, null, trackingNumber, null, ignoreRtrStatus);
    }

    public List<ParcelAuditDetailsDto> loadNonUpsParcelAuditDetails(String customerIds, String fromDate, String toDate, String carrierIds, String trackingNumbers, String invoiceId){
        QueryParameter queryParameter = QueryParameter.with("p_from_date", fromDate)
                .and("p_to_date", toDate).and("p_carrier_ids", carrierIds)
                .and("p_tracking_numbers", trackingNumbers)
                .and("p_invoice_id", invoiceId);

        if(customerIds != null && !customerIds.isEmpty()){
            queryParameter.and("p_customer_CSV", customerIds);
        }else{
            queryParameter.and("p_customer_CSV", "");
        }
        List<ParcelAuditDetailsDto> parcelAuditDetailsList = persistentContext.findEntitiesAndMapFields(ParcelAuditDetailsDto.Config.StoredProcedureQueryName.AUDIT_NOT_UPS_PARCEL_DETAILS, queryParameter);
        if (parcelAuditDetailsList != null) parcelAuditDetailsList.forEach(auditDetails -> persistentContext.getHibernateSession().evict(auditDetails));
        return parcelAuditDetailsList;
    }

    public List<ParcelAuditDetailsDto> loadNonUpsParcelAuditDetails(String fromDate, String toDate, String carrierIds, String trackingNumbers){
        return loadNonUpsParcelAuditDetails(null, fromDate, toDate, carrierIds, trackingNumbers, null);
    }

    public List<ParcelAuditDetailsDto> loadNonUpsParcelAuditDetails(String customerIds, String trackingNumbers, String carrierId){
        return loadNonUpsParcelAuditDetails(customerIds, null, null, carrierId, trackingNumbers, null);
    }

    public void updateRTRInvoiceAmount(Long id, String userName, BigDecimal rtrAmount, String rtrStatus, Long carrierId){
        try{
            QueryParameter queryParameter = StoredProcedureParameter.withPosition(1, ParameterMode.IN, Long.class, id)
                    .andPosition(2, ParameterMode.IN, String.class, userName)
                    .andPosition(3, ParameterMode.IN, BigDecimal.class, rtrAmount)
                    .andPosition(4, ParameterMode.IN, String.class, rtrStatus)
                    .andPosition(5, ParameterMode.IN, Long.class, carrierId);
            persistentContext.executeStoredProcedure("SHP_UPDATE_RTR_INV_AMT_PROC", queryParameter);
        }catch (Exception e){
            e.printStackTrace();
            throw new DaoException("Error while updating rtr amount", e);
        }
    }

    public void updateInvoiceAmountByIds(String entityIds, String userName, String rtrStatus, Long carrierId){
        try{
            QueryParameter queryParameter = StoredProcedureParameter.withPosition(1, ParameterMode.IN, String.class, entityIds)
                    .andPosition(2, ParameterMode.IN, String.class, userName)
                    .andPosition(3, ParameterMode.IN, String.class, rtrStatus)
                    .andPosition(4, ParameterMode.IN, Long.class, carrierId);
            persistentContext.executeStoredProcedure("SHP_UPDATE_INV_AMT_BY_ID_PROC", queryParameter);
        }catch (Exception e){
            e.printStackTrace();
            throw new DaoException("Error while updating invoice amount", e);
        }
    }

    public void saveParcelAuditRequestAndResponseLog(ParcelAuditRequestResponseLog requestResponseLog){
        try{
            QueryParameter queryParameter = StoredProcedureParameter.withPosition(1, ParameterMode.IN, String.class, requestResponseLog.getTableName())
                    .andPosition(2, ParameterMode.IN, String.class, requestResponseLog.getEntityIds())
                    .andPosition(3, ParameterMode.IN, String.class, requestResponseLog.getRequestXml1())
                    .andPosition(4, ParameterMode.IN, String.class, requestResponseLog.getRequestXml2())
                    .andPosition(5, ParameterMode.IN, String.class, requestResponseLog.getResponseXml1())
                    .andPosition(6, ParameterMode.IN, String.class, requestResponseLog.getResponseXml2())
                    .andPosition(7, ParameterMode.IN, String.class, requestResponseLog.getResponseXml3())
                    .andPosition(8, ParameterMode.IN, String.class, requestResponseLog.getCreateUser());
            persistentContext.executeStoredProcedure("SHP_FRT_SAVE_XML_RATING_PROC", queryParameter);
        }catch (Exception e){
            e.printStackTrace();
            throw new DaoException("Error while saving request and response xml", e);
        }
    }

    public List<ParcelAuditDetailsDto> loadInvoiceIds(String fromDate, String toDate, String customerId, String invoiceIds, int limit, String rateTo){
        QueryParameter queryParameter = StoredProcedureParameter.with("p_from_date", fromDate)
                .and("p_to_date", toDate)
                .and("p_customer_id", customerId)
                .and("p_invoice_ids", invoiceIds)
                .and("p_limit", limit)
                .and("p_rate_to", rateTo);
        List<ParcelAuditDetailsDto> parcelAuditDetailsList = persistentContext.findEntities(ParcelAuditDetailsDto.Config.StoredProcedureQueryName.LOAD_INVOICE_IDS, queryParameter);
        if(parcelAuditDetailsList != null) parcelAuditDetailsList.forEach(auditDetails -> persistentContext.getHibernateSession().evict(auditDetails));
        return parcelAuditDetailsList;
    }

    public void updateInvoiceRtrStatus(Long invoiceId, String rtrStatus, String userName){
        try {
            QueryParameter queryParameter = StoredProcedureParameter.withPosition(1, ParameterMode.IN, Long.class, invoiceId)
                    .andPosition(2, ParameterMode.IN, String.class, rtrStatus)
                    .andPosition(3, ParameterMode.IN, String.class, userName);
            persistentContext.executeStoredProcedure("SHP_UPDATE_INV_RTR_STATUS_PROC", queryParameter);
        }catch (Exception e){
            e.printStackTrace();
            throw new DaoException("Error while saving Invoice rtr status", e);
        }
    }

    public void updateShipmentRateDetails(String referenceTableName, String entityIds, String userName, ParcelRateDetailsDto rateDetails){
        try{
            QueryParameter queryParameter = StoredProcedureParameter.withPosition(1, ParameterMode.IN, String.class, referenceTableName)
                    .andPosition(2, ParameterMode.IN, String.class, entityIds)
                    .andPosition(3, ParameterMode.IN, String.class, userName)
                    .andPosition(4, ParameterMode.IN, BigDecimal.class, rateDetails != null && rateDetails.getDimDivisor() != null ? rateDetails.getDimDivisor() : new BigDecimal("0"))
                    .andPosition(5, ParameterMode.IN, String.class, rateDetails != null && rateDetails.getShipperCategory() != null ? rateDetails.getShipperCategory() : new BigDecimal("0"))
                    .andPosition(6, ParameterMode.IN, BigDecimal.class, rateDetails != null && rateDetails.getRatedWeight() != null ? rateDetails.getRatedWeight() : new BigDecimal("0"))
                    .andPosition(7, ParameterMode.IN, String.class, rateDetails != null && rateDetails.getContractName() != null ? rateDetails.getContractName() : new BigDecimal("0"))
                    .andPosition(8, ParameterMode.IN, BigDecimal.class, rateDetails != null && rateDetails.getFuelTablePercentage() != null ? rateDetails.getFuelTablePercentage() : new BigDecimal("0"))
                    .andPosition(9, ParameterMode.IN, BigDecimal.class, rateDetails != null && rateDetails.getRatedFuelSurchargeDiscount() != null ? rateDetails.getRatedFuelSurchargeDiscount() : new BigDecimal("0"))
                    .andPosition(10, ParameterMode.IN, BigDecimal.class, rateDetails != null && rateDetails.getRatedCustomFuelSurchargeDiscount() != null ? rateDetails.getRatedCustomFuelSurchargeDiscount() : new BigDecimal("0"))
                    .andPosition(11, ParameterMode.IN, BigDecimal.class, rateDetails != null && rateDetails.getRatedBaseDiscount() != null ? rateDetails.getRatedBaseDiscount() : new BigDecimal("0"))
                    .andPosition(12, ParameterMode.IN, BigDecimal.class, rateDetails != null && rateDetails.getRatedEarnedDiscount() != null ? rateDetails.getRatedEarnedDiscount() : new BigDecimal("0"))
                    .andPosition(13, ParameterMode.IN, BigDecimal.class, rateDetails != null && rateDetails.getRatedMinMaxAdjustment() != null ? rateDetails.getRatedMinMaxAdjustment() : new BigDecimal("0"))
                    .andPosition(14, ParameterMode.IN, BigDecimal.class, rateDetails != null && rateDetails.getRatedGrossFuel() != null ? rateDetails.getRatedGrossFuel() : new BigDecimal("0"))
                    .andPosition(15, ParameterMode.IN, BigDecimal.class, rateDetails != null && rateDetails.getResidentialSurchargeDiscount() != null ? rateDetails.getResidentialSurchargeDiscount() : new BigDecimal("0"))
                    .andPosition(16, ParameterMode.IN, BigDecimal.class, rateDetails != null && rateDetails.getResidentialSurchargeDiscountPercentage() != null ? rateDetails.getResidentialSurchargeDiscountPercentage() : new BigDecimal("0"))
                    .andPosition(17, ParameterMode.IN, BigDecimal.class, rateDetails != null && rateDetails.getDeliveryAreaSurchargeDiscount() != null ? rateDetails.getDeliveryAreaSurchargeDiscount() : new BigDecimal("0"));
            persistentContext.executeStoredProcedure("SHP_SAVE_RATE_DETAILS_PROC", queryParameter);
        }catch (Exception e){
            e.printStackTrace();
            throw new DaoException("Error while updating Rate Details", e);
        }
    }

    public void updateOtherDiscountShipmentRateDetails(String referenceTableName, String entityIds, String userName, ParcelRateDetailsDto rateDetails){
        try{
            QueryParameter queryParameter = StoredProcedureParameter.withPosition(1, ParameterMode.IN, String.class, referenceTableName)
                    .andPosition(2, ParameterMode.IN, String.class, entityIds)
                    .andPosition(3, ParameterMode.IN, String.class, userName)
                    .andPosition(4, ParameterMode.IN, String.class, rateDetails != null && rateDetails.getContractName() != null ? rateDetails.getContractName() : new BigDecimal("0"))
                    .andPosition(5, ParameterMode.IN, String.class, rateDetails != null && rateDetails.getShipperCategory() != null ? rateDetails.getShipperCategory() : new BigDecimal("0"))
                    .andPosition(6, ParameterMode.IN, BigDecimal.class, rateDetails != null && rateDetails.getOtherDiscount1() != null ? rateDetails.getOtherDiscount1() : new BigDecimal("0"))
                    .andPosition(7, ParameterMode.IN, BigDecimal.class, rateDetails != null && rateDetails.getOtherDiscount2() != null ? rateDetails.getOtherDiscount2() : new BigDecimal("0"))
                    .andPosition(8, ParameterMode.IN, BigDecimal.class, rateDetails != null && rateDetails.getOtherDiscount3() != null ? rateDetails.getOtherDiscount3() : new BigDecimal("0"));
            persistentContext.executeStoredProcedure("SHP_UPDATE_OTHER_DSC_PROC", queryParameter);
        }catch (Exception e){
            e.printStackTrace();
            throw new DaoException("Error while updating Rate Details", e);
        }
    }


    public Map<String, String>  loadDASChargeDetails() {
        String ModuleName = "DAS Charge Mapping";
        try{

            QueryParameter queryParameter = StoredProcedureParameter.with("p_module_name", ModuleName);

            List<ParcelAuditDASChargeDetailsDto> parcelAuditDASChargeList = persistentContext.findEntitiesAndMapFields(ParcelAuditDASChargeDetailsDto.Config.StoredProcedureQueryName.AUDIT_LOAD_DAS_CHARGE_DETAILS, queryParameter);
            if(parcelAuditDASChargeList != null) parcelAuditDASChargeList.forEach(DASChargeDetails -> persistentContext.getHibernateSession().evict(DASChargeDetails));
            Map<String, String> resultsMap = new HashMap<String, String>();
            for(ParcelAuditDASChargeDetailsDto dto:parcelAuditDASChargeList){
                resultsMap.put(dto.getLookupCode(),dto.getLookupValue());
            }
            return resultsMap;
        }catch (Exception e){
            e.printStackTrace();
            throw new DaoException("Error while loading DAS charge Details", e);
        }


    }

    public void updateAccessorialShipmentRateDetails(String referenceTableName, String entityIds, String userName, ParcelRateDetailsDto rateDetails){
        try{
            QueryParameter queryParameter = StoredProcedureParameter.withPosition(1, ParameterMode.IN, String.class, referenceTableName)
                    .andPosition(2, ParameterMode.IN, String.class, entityIds)
                    .andPosition(3, ParameterMode.IN, String.class, userName)
                    .andPosition(4, ParameterMode.IN, String.class, rateDetails != null && rateDetails.getContractName() != null ? rateDetails.getContractName() : new BigDecimal("0"))
                    .andPosition(5, ParameterMode.IN, String.class, rateDetails != null && rateDetails.getShipperCategory() != null ? rateDetails.getShipperCategory() : new BigDecimal("0"))
                    .andPosition(6, ParameterMode.IN, BigDecimal.class, rateDetails != null && rateDetails.getAccessorial1() != null ? rateDetails.getAccessorial1() : new BigDecimal("0"))
                    .andPosition(7, ParameterMode.IN, BigDecimal.class, rateDetails != null && rateDetails.getAccessorial2() != null ? rateDetails.getAccessorial2() : new BigDecimal("0"))
                    .andPosition(8, ParameterMode.IN, BigDecimal.class, rateDetails != null && rateDetails.getAccessorial3() != null ? rateDetails.getAccessorial3() : new BigDecimal("0"));
            persistentContext.executeStoredProcedure("SHP_RATE_UPDATE_ACC_PROC", queryParameter);
        }catch (Exception e){
            e.printStackTrace();
            throw new DaoException("Error while updating Rate Details", e);
        }
    }

    public List<RatedChargeDetailsDto> getRatedChargeAmount(Long parentId){
        QueryParameter queryParameter = QueryParameter.with("p_parent_id", parentId);
        List<RatedChargeDetailsDto> ratedChargeDetails = persistentContext.findEntitiesAndMapFields("RatedChargeDetailsDto.getRatedChargeAmount", queryParameter);
        if(ratedChargeDetails != null) ratedChargeDetails.forEach(auditDetails -> persistentContext.getHibernateSession().evict(auditDetails));
        return ratedChargeDetails;
    }
}
