package com.envista.msi.api.dao.rating;

import com.envista.msi.api.domain.PersistentContext;
import com.envista.msi.api.domain.util.QueryParameter;
import com.envista.msi.api.domain.util.StoredProcedureParameter;
import com.envista.msi.api.web.rest.dto.reports.ReportCustomerCarrierDto;
import com.envista.msi.api.web.rest.dto.rtr.StoreRatingDetailsDto;
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

    public List<StoreRatingDetailsDto> saveRatingDetailsList( StoreRatingDetailsDto storeRatingDetailsDto ){
        QueryParameter queryParameter = StoredProcedureParameter.with("p_customer_id", storeRatingDetailsDto.getCustomerId()).
                and("p_carrier_id", storeRatingDetailsDto.getCarrierIds())
                .and("p_from_invoice_date", storeRatingDetailsDto.getFromInvoiceDate())
                .and("p_to_invoice_date", storeRatingDetailsDto.getToInvoiceDate())
                .and("p_from_ship_date",storeRatingDetailsDto.getFromShipDate() )
                .and("p_to_ship_date",storeRatingDetailsDto.getToShipDate() )
                .and("p_rate_set", storeRatingDetailsDto.getRateSet())
                .and("p_service_levels_id", storeRatingDetailsDto.getServiceLevelsId())
                .and("p_invoice_rate",storeRatingDetailsDto.isInvoiceRate() )
                .and("p_flagged_shipments",storeRatingDetailsDto.isFlaggedShipments() )
                .and("p_threshold_value",storeRatingDetailsDto.getThresholdValue() )
                .and("p_threshold_type",storeRatingDetailsDto.getThresholdType() )
                .and("p_rate",storeRatingDetailsDto.isRate() )
                .and("p_info_lookup",storeRatingDetailsDto.isInfoLookUp())
                .and("p_user_name",storeRatingDetailsDto.getCreateUser())
                .and("p_status",storeRatingDetailsDto.getStatus());
        persistentContext.findEntities("StoreRatingDetailsDto.saveRatingDetailsList", queryParameter);
        return null;
    }
}
