package com.envista.msi.api.dao.rtr;

import com.envista.msi.api.dao.DaoException;
import com.envista.msi.api.domain.PersistentContext;
import com.envista.msi.api.domain.util.QueryParameter;
import com.envista.msi.api.domain.util.StoredProcedureParameter;
import com.envista.msi.api.web.rest.dto.rtr.ParcelAuditDetailsDto;
import com.envista.msi.api.web.rest.dto.rtr.ParcelAuditRequestResponseLog;
import org.springframework.stereotype.Repository;

import javax.inject.Inject;
import javax.persistence.ParameterMode;
import java.math.BigDecimal;
import java.util.List;

/**
 * Created by Sujit kumar on 08/06/2017.
 */

@Repository("parcelRTRDao")
public class ParcelRTRDao {

    @Inject
    private PersistentContext persistentContext;

    public List<ParcelAuditDetailsDto> loadUpsParcelAuditDetails(String customerIds, String fromDate, String toDate, String trackingNumbers, String invoiceId){
        QueryParameter queryParameter = QueryParameter.with("p_from_date", fromDate)
                .and("p_to_date", toDate)
                .and("p_tracking_numbers", trackingNumbers)
                .and("p_invoice_id", invoiceId);

        if(customerIds != null && !customerIds.isEmpty()){
            queryParameter.and("p_customer_CSV", customerIds);
        }else{
            queryParameter.and("p_customer_CSV", "");
        }
        List<ParcelAuditDetailsDto> parcelAuditDetailsList = persistentContext.findEntitiesAndMapFields(ParcelAuditDetailsDto.Config.StoredProcedureQueryName.AUDIT_UPS_PARCEL_DETAILS, queryParameter);
        parcelAuditDetailsList.forEach(auditDetails -> persistentContext.getHibernateSession().evict(auditDetails));
        return parcelAuditDetailsList;
    }

    public List<ParcelAuditDetailsDto> loadUpsParcelAuditDetails(String fromDate, String toDate, String trackingNumber){
        return loadUpsParcelAuditDetails(null, fromDate, toDate, trackingNumber, null);
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
        parcelAuditDetailsList.forEach(auditDetails -> persistentContext.getHibernateSession().evict(auditDetails));
        return parcelAuditDetailsList;
    }

    public List<ParcelAuditDetailsDto> loadNonUpsParcelAuditDetails(String fromDate, String toDate, String carrierIds, String trackingNumbers){
        return loadNonUpsParcelAuditDetails(null, fromDate, toDate, carrierIds, trackingNumbers, null);
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
            QueryParameter queryParameter = StoredProcedureParameter.withPosition(1, ParameterMode.IN, String.class, requestResponseLog.getRequestXml())
                    .andPosition(2, ParameterMode.IN, String.class, requestResponseLog.getRequestXml1())
                    .andPosition(3, ParameterMode.IN, String.class, requestResponseLog.getResponseXml())
                    .andPosition(4, ParameterMode.IN, String.class, requestResponseLog.getResponseXml1())
                    .andPosition(5, ParameterMode.IN, String.class, requestResponseLog.getResponseXml2())
                    .andPosition(6, ParameterMode.IN, String.class, requestResponseLog.getCreateUser());
            persistentContext.executeStoredProcedure("SHP_FRT_SAVE_XML_RATING_PROC", queryParameter);
        }catch (Exception e){
            e.printStackTrace();
            throw new DaoException("Error while saving request and response xml", e);
        }
    }

    public List<ParcelAuditDetailsDto> loadInvoiceIds(String fromDate, String toDate, String customerId, String invoiceIds, int limit){
        QueryParameter queryParameter = StoredProcedureParameter.with("p_from_date", fromDate)
                .and("p_to_date", toDate)
                .and("p_customer_id", customerId)
                .and("p_invoice_ids", invoiceIds)
                .and("p_limit", limit);
        List<ParcelAuditDetailsDto> parcelAuditDetailsList = persistentContext.findEntities(ParcelAuditDetailsDto.Config.StoredProcedureQueryName.LOAD_INVOICE_IDS, queryParameter);
        parcelAuditDetailsList.forEach(auditDetails -> persistentContext.getHibernateSession().evict(auditDetails));
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

    public void updateShipmentRateDetails(String referenceTableName, String entityIds, String userName, BigDecimal dimDivisor, String shipperCategory,BigDecimal ratedWeight,String contractName,BigDecimal fuelTablePerc,BigDecimal ratedSurchargeDisc){
        try{
            QueryParameter queryParameter = StoredProcedureParameter.withPosition(1, ParameterMode.IN, String.class, referenceTableName)
                    .andPosition(2, ParameterMode.IN, String.class, entityIds)
                    .andPosition(3, ParameterMode.IN, String.class, userName)
                    .andPosition(4, ParameterMode.IN, BigDecimal.class, dimDivisor)
                    .andPosition(5, ParameterMode.IN, String.class, shipperCategory)
                    .andPosition(6, ParameterMode.IN, BigDecimal.class, ratedWeight)
                    .andPosition(7, ParameterMode.IN, String.class, contractName)
                    .andPosition(8, ParameterMode.IN, BigDecimal.class, fuelTablePerc)
                    .andPosition(9, ParameterMode.IN, BigDecimal.class, ratedSurchargeDisc);
            persistentContext.executeStoredProcedure("SHP_SAVE_RATE_DETAILS_PROC", queryParameter);
        }catch (Exception e){
            e.printStackTrace();
            throw new DaoException("Error while updating Rate Details", e);
        }
    }
}
