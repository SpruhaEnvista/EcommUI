package com.envista.msi.api.service.freight;

import com.envista.msi.api.dao.freight.InvoiceViewDao;
import com.envista.msi.api.web.rest.dto.freight.InvoiceSummaryAppChrgsDto;
import com.envista.msi.api.web.rest.dto.freight.InvoiceSummaryInvDtlsDto;
import com.envista.msi.api.web.rest.dto.freight.InvoiceViewAddressDtlsDto;
import com.envista.msi.api.web.rest.dto.freight.InvoiceViewAuditDtlsDto;
import com.envista.msi.api.web.rest.dto.freight.InvoiceViewInvDtlsDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.List;

/**
 * Created by Sreedhar.T on 7/19/2017.
 */
@Service
@Transactional
public class InvoiceViewService {

    @Inject
    InvoiceViewDao invoiceViewDao;

    public List<InvoiceSummaryAppChrgsDto> getInvoiceChargesList(Long invoiceId) {
        return  invoiceViewDao.getReportResults(invoiceId);
    }

    public InvoiceSummaryInvDtlsDto getInvoiceDetailsSummList(Long invoiceId){
        return invoiceViewDao.getInvoiceDetailsForInvSumm(invoiceId);
    }

    public InvoiceViewAddressDtlsDto getInvoiceAddressDetails(Long invoiceId, String addrDtl){
        return invoiceViewDao.getAddressDetailsForInvoice(invoiceId,addrDtl);
    }

    public InvoiceViewInvDtlsDto getInvoiceDetailsForInvoiceView(Long invoiceId){
        return invoiceViewDao.getInvoiceDetailsForInvoiceView(invoiceId);
    }

    public List<InvoiceViewAuditDtlsDto> getInvoiceViewAuditDetails(Long invoiceId){
        return invoiceViewDao.getInvoiceViewAuditDetails(invoiceId);
    }
}
