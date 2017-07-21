package com.envista.msi.api.dao.freight;

import com.envista.msi.api.domain.PersistentContext;
import com.envista.msi.api.domain.util.QueryParameter;
import com.envista.msi.api.domain.util.StoredProcedureParameter;
import com.envista.msi.api.web.rest.dto.freight.InvoiceSummaryAppChrgsDto;
import com.envista.msi.api.web.rest.dto.freight.InvoiceSummaryInvDtlsDto;
import com.envista.msi.api.web.rest.dto.freight.InvoiceViewAddressDtlsDto;
import com.envista.msi.api.web.rest.dto.freight.InvoiceViewInvDtlsDto;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import javax.management.Query;
import java.util.List;

/**
 * Created by Sreedhar.T on 7/19/2017.
 */
@Repository("InvoiceViewDao")
public class InvoiceViewDao {


    @Inject
    private PersistentContext persistentContext;
    /**
     * Get invoiceCharges based on invoiceId
     * @param invoiceId
     * @return list<InvoiceSummaryAppChrgsDto>
     */
    @Transactional( readOnly = true )
    public List<InvoiceSummaryAppChrgsDto> getReportResults(Long invoiceId) {
        QueryParameter queryParameter = StoredProcedureParameter.with("invoiceId", invoiceId);
        return persistentContext.findEntitiesAndMapFields("InvSummAppChrgs.getCharges",queryParameter);
    }

    @Transactional( readOnly = true )
    public InvoiceSummaryInvDtlsDto getInvoiceDetailsForInvSumm(Long invoiceId){
        QueryParameter queryParameter = StoredProcedureParameter.with("invoiceId",invoiceId);
        return persistentContext.findEntityAndMapFields("InvSummInvDtls.invoiceDetails",queryParameter);
    }

    @Transactional( readOnly = true )
    public InvoiceViewAddressDtlsDto getAddressDetailsForInvoice(Long invoiceId, String addrDtl){

        QueryParameter queryParameter = StoredProcedureParameter.with("invoiceId",invoiceId)
                                                                .and("addrType",addrDtl);
        return persistentContext.findEntityAndMapFields("InvViewAddrDtls.invoiceAddrDetails",queryParameter);
    }

    @Transactional( readOnly = true )
    public InvoiceViewInvDtlsDto getInvoiceDetailsForInvoiceView(Long invoiceId){
        QueryParameter queryParameter = StoredProcedureParameter.with("invoiceId",invoiceId);
        return persistentContext.findEntityAndMapFields("InvViewInvDtls.invoiceDetails",queryParameter);
    }
}
