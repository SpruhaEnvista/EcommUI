package com.envista.msi.api.web.rest;

import com.envista.msi.api.service.freight.InvoiceViewService;
import com.envista.msi.api.web.rest.dto.freight.InvoiceSummaryAppChrgsDto;
import com.envista.msi.api.web.rest.dto.freight.InvoiceSummaryInvDtlsDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.util.List;

/**
 * Created by Sreedhar.T on 7/19/2017.
 */
@RestController
@RequestMapping("/api/invoiceview")
public class FreightInvoiceViewController {

    @Inject
    InvoiceViewService invoiceViewService;

    @RequestMapping(value="/summ/approvedcharges", method = {RequestMethod.GET, RequestMethod.OPTIONS}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<InvoiceSummaryAppChrgsDto>> getApprovedCharges(@RequestParam Long invoiceId) {

        List<InvoiceSummaryAppChrgsDto> appChargesList = null;
        if(invoiceId != null){
            appChargesList = invoiceViewService.getInvoiceChargesList(invoiceId);
        }
        return new ResponseEntity<List<InvoiceSummaryAppChrgsDto>>(appChargesList, HttpStatus.OK);
    }

    @RequestMapping(value="/summ/invdetails", method = {RequestMethod.GET, RequestMethod.OPTIONS}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<InvoiceSummaryInvDtlsDto> getInvoiceSummInvoiceDetails(@RequestParam Long invoiceId) {

        InvoiceSummaryInvDtlsDto invDetails = null;
        if(invoiceId != null){
            invDetails = invoiceViewService.getInvoiceDetailsSummList(invoiceId);
        }
        return new ResponseEntity<InvoiceSummaryInvDtlsDto>(invDetails, HttpStatus.OK);
    }

}
