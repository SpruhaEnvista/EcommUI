package com.envista.msi.api.web.rest.invoicing;

import com.envista.msi.api.service.invoicing.CreditsPRService;
import com.envista.msi.api.service.invoicing.UvCreditsService;
import com.envista.msi.api.web.rest.dto.invoicing.CreditsPRDto;
import com.envista.msi.api.web.rest.dto.invoicing.CreditsPRSearchBean;
import com.envista.msi.api.web.rest.dto.invoicing.UvCreditsDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;
import java.util.List;

/**
 * Created by KRISHNAREDDYM on 5/12/2017.
 */
@RestController
@RequestMapping("/api/uvCredits")
public class UvCreditsController {

    private final Logger log = LoggerFactory.getLogger(UvCreditsController.class);

    @Inject
    private UvCreditsService service;

    /**
     * HTTP Get - Search
     */
    @RequestMapping(value = "/search", params = {"businessPartnerId", "customerIds", "savedFilter", "invoicingStatus", "invCatagoryId", "invWeekEndDate", "invoiceModeId",
            "carrierId", "creditClassId", "omitFlag", "reviewFlag", "createDate", "invoiceDate", "closeDate", "invoiceNumbers", "trackingNumbers", "internalKeyIds", "invoiceMethodId",
            "payRunNos", "controlNums", "adjReasons", "invComments"}, produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    public ResponseEntity<List<UvCreditsDto>> search(@RequestParam Long businessPartnerId, @RequestParam String customerIds, @RequestParam String savedFilter
            , @RequestParam String invoicingStatus, @RequestParam Long invCatagoryId, @RequestParam String invWeekEndDate, @RequestParam Long invoiceModeId
            , @RequestParam Long carrierId, @RequestParam Long creditClassId, @RequestParam String omitFlag, @RequestParam String reviewFlag
            , @RequestParam String createDate, @RequestParam String invoiceDate, @RequestParam String closeDate, @RequestParam String invoiceNumbers
            , @RequestParam String trackingNumbers, @RequestParam String internalKeyIds, @RequestParam Long invoiceMethodId, @RequestParam String payRunNos
            , @RequestParam String controlNums, @RequestParam String adjReasons, @RequestParam String invComments) {
        log.info("***search method started****");

        CreditsPRSearchBean bean = new CreditsPRSearchBean();
        bean.setBusinessPartnerId(businessPartnerId);
        bean.setCustomerIds(customerIds);
        bean.setSavedFilter(savedFilter);
        bean.setInvoicingStatus(invoicingStatus);
        bean.setInvCatagoryId(invCatagoryId);
        bean.setInvWeekEndDate(invWeekEndDate);
        bean.setInvoiceModeId(invoiceModeId);
        bean.setCarrierId(carrierId);
        bean.setCreditClassId(creditClassId);
        bean.setOmitFlag(omitFlag);
        bean.setReviewFlag(reviewFlag);
        bean.setCreateDate(createDate);
        bean.setInvoiceDate(invoiceDate);
        bean.setCloseDate(closeDate);
        bean.setInvoiceNumbers(invoiceNumbers);
        bean.setTrackingNumbers(trackingNumbers);
        bean.setInternalKeyIds(internalKeyIds);
        bean.setInvoiceMethodId(invoiceMethodId);
        bean.setPayRunNos(payRunNos);
        bean.setControlNums(controlNums);
        bean.setAdjReasons(adjReasons);
        bean.setInvComments(invComments);


        List<UvCreditsDto> dtos = service.search(bean);


        return new ResponseEntity<List<UvCreditsDto>>(dtos, HttpStatus.OK);
    }


/*    *//**
     * HTTP DELETE - Delete custom omits
     *//*
    @RequestMapping(value = "/update", params = {"ebillManifestIds", "actionType"}, method = RequestMethod.PUT)
    public ResponseEntity<Integer> updateStatus(@RequestParam String ebillManifestIds, @RequestParam String actionType) {

        log.info("***updateStatus method started****ebillManifestIds are : " + ebillManifestIds);

        int updateddRows = service.update(ebillManifestIds, actionType);

        return new ResponseEntity<Integer>(updateddRows, HttpStatus.OK);
    }*/
}
