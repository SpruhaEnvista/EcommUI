package com.envista.msi.api.dao.rating;

import com.envista.msi.api.domain.PersistentContext;
import com.envista.msi.api.domain.util.QueryParameter;
import com.envista.msi.api.domain.util.StoredProcedureParameter;
import com.envista.msi.api.web.rest.dto.reports.ReportCustomerCarrierDto;
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
}
