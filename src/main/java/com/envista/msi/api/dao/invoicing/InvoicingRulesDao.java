package com.envista.msi.api.dao.invoicing;

import com.envista.msi.api.domain.PersistentContext;
import com.envista.msi.api.domain.util.QueryParameter;
import com.envista.msi.api.domain.util.StoredProcedureParameter;
import com.envista.msi.api.web.rest.dto.invoicing.InvoicingRuleDetailsDto;
import com.envista.msi.api.web.rest.dto.invoicing.InvoicingRuleDto;
import org.springframework.stereotype.Repository;

import javax.inject.Inject;
import java.util.List;

/**
 * Created by KRISHNAREDDYM on 4/27/2017.
 */
@Repository("invoicingRulesDao")
public class InvoicingRulesDao {

    @Inject
    private PersistentContext persistentContext;

    public List<InvoicingRuleDto> findByCustomerIds(String customOmitIds) {

        QueryParameter queryParameter = StoredProcedureParameter.with("P_CUSTOMER_IDS", customOmitIds);

        return persistentContext.findEntities("InvoicingRuleDto.findByCustomerIds", queryParameter);
    }

    public List<InvoicingRuleDetailsDto> findById(Long id) {

        QueryParameter queryParameter = StoredProcedureParameter.with("P_INVOICING_RULE_ID", id);

        return persistentContext.findEntitiesAndMapFields("InvoicingRuleDto.findById", queryParameter);
    }
}
