package com.envista.msi.api.web.rest.invoicing;

import com.envista.msi.api.service.invoicing.CreditsPRService;
import com.envista.msi.api.web.rest.dto.invoicing.CreditsPRDto;
import com.envista.msi.api.web.rest.dto.invoicing.CreditsPRSearchBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.util.List;

/**
 * Created by KRISHNAREDDYM on 5/11/2017.
 */
@RestController
@RequestMapping("/api/creditsPR")
public class CreditsPRController {

    private final Logger log = LoggerFactory.getLogger(CreditsPRController.class);

    @Inject
    private CreditsPRService service;

    /**
     * HTTP Get - Search
     */
    @RequestMapping(value = "/search", params = {"businessPartnerId", "customerIds", "savedFilter", "invoicingStatus", "invCatagoryId", "invWeekEndDate", "invoiceModeId",
            "carrierId", "creditClassId", "omitFlag", "reviewFlag", "createDate", "invoiceDate", "closeDate", "invoiceNumbers", "trackingNumbers", "internalKeyIds", "invoiceMethodId",
            "payRunNos", "controlNums", "adjReasons", "invComments"}, produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    public ResponseEntity<List<CreditsPRDto>> search(@RequestParam Long businessPartnerId, @RequestParam String customerIds, @RequestParam String savedFilter
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


        List<CreditsPRDto> dtos = service.search(bean);


        return new ResponseEntity<List<CreditsPRDto>>(dtos, HttpStatus.OK);
    }


}
