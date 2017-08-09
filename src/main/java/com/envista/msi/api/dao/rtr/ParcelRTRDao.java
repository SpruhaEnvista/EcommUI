package com.envista.msi.api.dao.rtr;

import com.envista.msi.api.dao.DaoException;
import com.envista.msi.api.domain.PersistentContext;
import com.envista.msi.api.domain.util.QueryParameter;
import com.envista.msi.api.domain.util.StoredProcedureParameter;
import com.envista.msi.api.web.rest.dto.rtr.ParcelAuditDetailsDto;
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

    public List<ParcelAuditDetailsDto> loadUpsParcelAuditDetails(String customerIds, String fromDate, String toDate){
        QueryParameter queryParameter = QueryParameter.with("p_from_date", fromDate)
                .and("p_to_date", toDate);

        if(customerIds != null && !customerIds.isEmpty()){
            queryParameter.and("p_customer_CSV", customerIds);
        }else{
            queryParameter.and("p_customer_CSV", "");
        }
        return persistentContext.findEntitiesAndMapFields(ParcelAuditDetailsDto.Config.StoredProcedureQueryName.AUDIT_UPS_PARCEL_DETAILS, queryParameter);
    }

    public List<ParcelAuditDetailsDto> loadUpsParcelAuditDetails(String fromDate, String toDate){
        return loadUpsParcelAuditDetails(null, fromDate, toDate);
    }

    public List<ParcelAuditDetailsDto> loadNonUpsParcelAuditDetails(String customerIds, String fromDate, String toDate, String carrierIds){
        QueryParameter queryParameter = QueryParameter.with("p_from_date", fromDate)
                .and("p_to_date", toDate).and("p_carrier_ids", carrierIds);

        if(customerIds != null && !customerIds.isEmpty()){
            queryParameter.and("p_customer_CSV", customerIds);
        }else{
            queryParameter.and("p_customer_CSV", "");
        }
        return persistentContext.findEntitiesAndMapFields(ParcelAuditDetailsDto.Config.StoredProcedureQueryName.AUDIT_NOT_UPS_PARCEL_DETAILS, queryParameter);
    }

    public List<ParcelAuditDetailsDto> loadNonUpsParcelAuditDetails(String fromDate, String toDate, String carrierIds){
        return loadNonUpsParcelAuditDetails(null, fromDate, toDate, carrierIds);
    }

    public void updateRTRInvoiceAmount(Long id, String userName, BigDecimal rtrAmount, String rtrStatus, Long carrierId){
        try{
            QueryParameter queryParameter = StoredProcedureParameter.withPosition(1, ParameterMode.IN, Long.class, id)
                    .andPosition(2, ParameterMode.IN, String.class, userName)
                    .andPosition(3, ParameterMode.IN, Long.class, rtrAmount)
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
}
