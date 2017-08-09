package com.envista.msi.api.web.rest;

import com.envista.msi.api.service.freight.InvoiceViewService;
import com.envista.msi.api.web.rest.dto.freight.InvoiceSummaryAppChrgsDto;
import com.envista.msi.api.web.rest.dto.freight.InvoiceSummaryInvDtlsDto;
import com.envista.msi.api.web.rest.dto.freight.InvoiceViewAddressDtlsDto;
import com.envista.msi.api.web.rest.dto.freight.InvoiceViewAuditDtlsDto;
import com.envista.msi.api.web.rest.dto.freight.InvoiceViewInvDtlsDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import javax.websocket.server.PathParam;
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
        if(invoiceId != null && invoiceId > 0){
            appChargesList = invoiceViewService.getInvoiceChargesList(invoiceId);
        }
        return new ResponseEntity<List<InvoiceSummaryAppChrgsDto>>(appChargesList, HttpStatus.OK);
    }

    @RequestMapping(value="/summ/invdetails", method = {RequestMethod.GET, RequestMethod.OPTIONS}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<InvoiceSummaryInvDtlsDto> getInvoiceSummInvoiceDetails(@RequestParam Long invoiceId) {

        InvoiceSummaryInvDtlsDto invDetails = null;
        if(invoiceId != null && invoiceId > 0) {
            invDetails = invoiceViewService.getInvoiceDetailsSummList(invoiceId);
        }
        return new ResponseEntity<InvoiceSummaryInvDtlsDto>(invDetails, HttpStatus.OK);
    }

    @RequestMapping(value="/htab/address/{addrDtl}", method = {RequestMethod.GET, RequestMethod.OPTIONS}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<InvoiceViewAddressDtlsDto> getInvoiceAddressDetails(@PathVariable String addrDtl, @RequestParam Long invoiceId) {
        InvoiceViewAddressDtlsDto addrDtls = null;
        if(addrDtl != null && addrDtl.length() > 0 && invoiceId != null && invoiceId > 0){
            addrDtls = invoiceViewService.getInvoiceAddressDetails(invoiceId,addrDtl);
        }
        return new ResponseEntity<InvoiceViewAddressDtlsDto>(addrDtls, HttpStatus.OK);
    }

    @RequestMapping(value="/htab/invdetails", method = {RequestMethod.GET, RequestMethod.OPTIONS}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<InvoiceViewInvDtlsDto> getInvoiceAddressDetails(@RequestParam Long invoiceId) {
        InvoiceViewInvDtlsDto invDtls = null;
        if( invoiceId != null && invoiceId > 0){
            invDtls = invoiceViewService.getInvoiceDetailsForInvoiceView(invoiceId);
        }
        return new ResponseEntity<InvoiceViewInvDtlsDto>(invDtls, HttpStatus.OK);
    }

    @RequestMapping(value = "/audit/invdetails", method = {RequestMethod.GET, RequestMethod.OPTIONS}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<InvoiceViewAuditDtlsDto>> getInvoiceViewAuditDetails(@RequestParam Long invoiceId) {
        List<InvoiceViewAuditDtlsDto> auditDtls = null;
        if(invoiceId != null && invoiceId > 0 ){
            auditDtls = invoiceViewService.getInvoiceViewAuditDetails(invoiceId);
        }
        return new ResponseEntity<List<InvoiceViewAuditDtlsDto>>(auditDtls, HttpStatus.OK);
    }

}
