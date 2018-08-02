package com.envista.msi.api.dao.rating;

import com.envista.msi.api.domain.PersistentContext;
import com.envista.msi.api.domain.util.QueryParameter;
import com.envista.msi.api.domain.util.StoredProcedureParameter;
import com.envista.msi.api.web.rest.dto.reports.ReportCustomerCarrierDto;
import com.envista.msi.api.web.rest.dto.rtr.EventLogDto;
import com.envista.msi.api.web.rest.dto.rtr.StoreRatingDetailsDto;
import com.envista.msi.api.web.rest.dto.rtr.ViewLogDto;
import com.envista.msi.rating.dao.DirectJDBCDAO;
import org.springframework.stereotype.Repository;

import javax.inject.Inject;
import java.util.List;

@Repository("RatingDao")
public class RatingDao {

    @Inject
    private PersistentContext persistentContext;

    public List<ReportCustomerCarrierDto> getRateCarrierList(Long userId, String customerIds){
        QueryParameter queryParameter = StoredProcedureParameter.with("p_user_id", userId).and("p_customer_ids", customerIds);
        return persistentContext.findEntities("ReportCustomerCarrierDto.getRateCarriers", queryParameter);
    }

    public List<EventLogDto> getEventLog(Long jobId){
        QueryParameter queryParameter = StoredProcedureParameter.with("job_id", jobId);
        return persistentContext.findEntities("EventLogDto.getEventLogs", queryParameter);
    }

    public List<StoreRatingDetailsDto> saveRatingDetailsList( StoreRatingDetailsDto storeRatingDetailsDto ){
        QueryParameter queryParameter = StoredProcedureParameter.with("customer_id", storeRatingDetailsDto.getCustomerId()).
                and("carrier_id", storeRatingDetailsDto.getCarrierIds())
                .and("from_invoice_date", storeRatingDetailsDto.getFromInvoiceDate())
                .and("to_invoice_date", storeRatingDetailsDto.getToInvoiceDate())
                .and("from_ship_date",storeRatingDetailsDto.getFromShipDate() )
                .and("to_ship_date",storeRatingDetailsDto.getToShipDate() )
                .and("rate_set", storeRatingDetailsDto.getRateSet())
                .and("service_levels_id", storeRatingDetailsDto.getServiceLevelsId())
                .and("invoice_rate",storeRatingDetailsDto.isInvoiceRate() )
                .and("flagged_shipments",storeRatingDetailsDto.isFlaggedShipments() )
                .and("threshold_value",storeRatingDetailsDto.getThresholdValue() )
                .and("threshold_type",storeRatingDetailsDto.getThresholdType() )
                .and("rate",storeRatingDetailsDto.isRate() )
                .and("info_lookup",storeRatingDetailsDto.isInfoLookUp())
                .and("user_name",storeRatingDetailsDto.getCreateUser())
                .and("status",storeRatingDetailsDto.getStatus());
        persistentContext.findEntities("StoreRatingDetailsDto.saveRatingDetailsList", queryParameter);
        return null;
    }

    public List<StoreRatingDetailsDto> getRatingJobsList(Long customerId){
        return new DirectJDBCDAO().getRatingJobsByCustomer(customerId);
    }

    public List<ViewLogDto> getViewLog(Long jobId){
        QueryParameter queryParameter = StoredProcedureParameter.with("job_id", jobId);
        return persistentContext.findEntities("ViewLogDto.getViewLogs", queryParameter);
    }
}
