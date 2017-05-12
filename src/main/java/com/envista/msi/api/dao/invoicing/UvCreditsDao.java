package com.envista.msi.api.dao.invoicing;

import com.envista.msi.api.domain.PersistentContext;
import com.envista.msi.api.domain.util.QueryParameter;
import com.envista.msi.api.domain.util.StoredProcedureParameter;
import com.envista.msi.api.web.rest.dto.invoicing.CreditsPRDto;
import com.envista.msi.api.web.rest.dto.invoicing.CreditsPRSearchBean;
import com.envista.msi.api.web.rest.dto.invoicing.UvCreditsDto;
import org.springframework.stereotype.Repository;

import javax.inject.Inject;
import java.util.List;

/**
 * Created by KRISHNAREDDYM on 5/12/2017.
 */
@Repository("uvCreditsDao")
public class UvCreditsDao {

    @Inject
    private PersistentContext persistentContext;


    public List<UvCreditsDto> search(CreditsPRSearchBean bean) {

        QueryParameter queryParameter = StoredProcedureParameter.with("P_BUSINESS_PARTNER_ID", bean.getBusinessPartnerId())
                .and("P_CUSTOMER_IDS", bean.getCustomerIds()).and("P_SAVED_FILTER", bean.getSavedFilter())
                .and("P_INVOICING_STATUS", bean.getInvoicingStatus()).and("P_INV_CATAGORY_ID", bean.getInvCatagoryId())
                .and("P_INV_WEEK_END_DATE", bean.getInvWeekEndDate()).and("P_INVOICE_MODE_ID", bean.getInvoiceModeId())
                .and("P_CARRIER_ID", bean.getCarrierId()).and("P_CREDIT_CLASS_ID", bean.getCreditClassId())
                .and("P_OMIT_FLAG", bean.getOmitFlag()).and("P_REVIEW_FLAG", bean.getReviewFlag())
                .and("P_CREATE_DATE", bean.getCreateDate()).and("P_INVOICE_DATE", bean.getInvoiceDate())
                .and("P_CLOSE_DATE", bean.getCloseDate()).and("P_INVOICE_NUMBERS", bean.getInvoiceNumbers())
                .and("P_TRACKING_NUMBERS", bean.getTrackingNumbers()).and("P_INTERNAL_KEY_IDS", bean.getInternalKeyIds())
                .and("P_INVOICE_METHOD_ID", bean.getInvoiceMethodId()).and("P_PAYRUN_NOS", bean.getPayRunNos())
                .and("P_CONTROL_NUMS", bean.getControlNums()).and("P_ADJ_REASONS", bean.getAdjReasons())
                .and("P_INV_COMMENTS", bean.getInvComments());

        return persistentContext.findEntities("UvCreditsDto.getSearchInfo", queryParameter);
    }

}
