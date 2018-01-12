package com.envista.msi.api.web.rest.invoicing;

import com.envista.msi.api.service.invoicing.CodeValueService;
import com.envista.msi.api.service.invoicing.UvCreditsService;
import com.envista.msi.api.service.invoicing.VoiceService;
import com.envista.msi.api.web.rest.dto.invoicing.CreditsPRSearchBean;
import com.envista.msi.api.web.rest.dto.invoicing.UvVoiceUpdateBean;
import com.envista.msi.api.web.rest.util.DateUtil;
import com.envista.msi.api.web.rest.util.InvoicingUtilities;
import com.envista.msi.api.web.rest.util.pagination.PaginationBean;
import org.apache.commons.lang.StringUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.util.Date;
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

    @Inject
    private VoiceService voiceService;

    @Inject
    private CodeValueService codeValueService;

    /**\
     * This method return all unknown voices with given search criteria
     * @param businessPartnerId
     * @param customerIds
     * @param savedFilter
     * @param invStatusId
     * @param invCatagoryId
     * @param invWeekEndId
     * @param invoiceModeId
     * @param carrierId
     * @param creditClassId
     * @param claimFlag
     * @param reviewFlag
     * @param createDateFrom
     * @param invoiceDateFrom
     * @param closeDateFrom
     * @param invoiceNumbers
     * @param trackingNumbers
     * @param internalKeyIds
     * @param invoiceMethodId
     * @param payRunNos
     * @param controlNums
     * @param adjReasons
     * @param invComments
     * @param createDateTo
     * @param invoiceDateTo
     * @param closeDateTo
     * @return List<UvCreditsDto>
     */
    @RequestMapping(value = "/search", params = {"businessPartnerId", "customerIds", "savedFilter", "invStatusId", "invCatagoryId", "invWeekEndId", "invoiceModeId",
            "carrierId", "creditClassId", "claimFlag", "reviewFlag", "createDateFrom", "invoiceDateFrom", "closeDateFrom", "invoiceNumbers", "trackingNumbers", "internalKeyIds", "invoiceMethodId",
            "payRunNos", "controlNums", "adjReasons", "invComments", "createDateTo", "invoiceDateTo", "closeDateTo"}, produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    public ResponseEntity<PaginationBean> search(@RequestParam Long businessPartnerId, @RequestParam String customerIds, @RequestParam String savedFilter
            , @RequestParam Long invStatusId, @RequestParam Long invCatagoryId, @RequestParam Long invWeekEndId, @RequestParam Long invoiceModeId
            , @RequestParam Long carrierId, @RequestParam Long creditClassId, @RequestParam String claimFlag, @RequestParam String reviewFlag
            , @RequestParam String createDateFrom, @RequestParam String invoiceDateFrom, @RequestParam String closeDateFrom, @RequestParam String invoiceNumbers
            , @RequestParam String trackingNumbers, @RequestParam String internalKeyIds, @RequestParam Long invoiceMethodId, @RequestParam String payRunNos
            , @RequestParam String controlNums, @RequestParam String adjReasons, @RequestParam String invComments, @RequestParam String createDateTo, @RequestParam String invoiceDateTo
            , @RequestParam String closeDateTo, @RequestParam(required = false, defaultValue = "0") Integer offset, @RequestParam(required = false, defaultValue = "10") Integer limit) throws Exception {
        log.info("***search method started****");

        CreditsPRSearchBean bean = new CreditsPRSearchBean();
        bean.setBusinessPartnerId(businessPartnerId);
        if (StringUtils.containsIgnoreCase(customerIds, "CU")) {
            customerIds = StringUtils.remove(customerIds, "CU");
        }
        bean.setCustomerIds(customerIds);
        bean.setSavedFilter(savedFilter);
        bean.setInvoicingStatus(invStatusId);
        bean.setInvCatagoryId(invCatagoryId);
        bean.setInvWeekEnId(invWeekEndId);
        bean.setInvoiceModeId(invoiceModeId);
        bean.setCarrierId(carrierId);
        bean.setCreditClassId(creditClassId);
        bean.setClaimFlag(claimFlag);
        bean.setReviewFlag(reviewFlag);
        if (createDateFrom != null && !StringUtils.equalsIgnoreCase(createDateFrom, "null"))
            bean.setCreateDateFrom(DateUtil.format(new Date(Long.valueOf(createDateFrom)), "dd-MM-yyyy"));
        if (invoiceDateFrom != null && !StringUtils.equalsIgnoreCase(invoiceDateFrom, "null"))
            bean.setInvoiceDateFrom(DateUtil.format(new Date(Long.valueOf(invoiceDateFrom)), "dd-MM-yyyy"));
        if (closeDateFrom != null && !StringUtils.equalsIgnoreCase(closeDateFrom, "null"))
            bean.setCloseDateFrom(DateUtil.format(new Date(Long.valueOf(closeDateFrom)), "dd-MM-yyyy"));

        if (createDateTo != null && !StringUtils.equalsIgnoreCase(createDateTo, "null"))
            bean.setCreateDateTo(DateUtil.format(new Date(Long.valueOf(createDateTo)), "dd-MM-yyyy"));
        if (invoiceDateTo != null && !StringUtils.equalsIgnoreCase(invoiceDateTo, "null"))
            bean.setInvoiceDateTo(DateUtil.format(new Date(Long.valueOf(invoiceDateTo)), "dd-MM-yyyy"));
        if (closeDateTo != null && !StringUtils.equalsIgnoreCase(closeDateTo, "null"))
            bean.setCloseDateTo(DateUtil.format(new Date(Long.valueOf(closeDateTo)), "dd-MM-yyyy"));

        bean.setInvoiceNumbers(invoiceNumbers);
        bean.setTrackingNumbers(trackingNumbers);
        bean.setInternalKeyIds(internalKeyIds);
        bean.setInvoiceMethodId(invoiceMethodId);
        bean.setPayRunNos(payRunNos);
        bean.setControlNums(controlNums);
        bean.setAdjReasons(adjReasons);
        bean.setInvComments(invComments);


        PaginationBean paginationData = service.getSearchPaginationData(bean, offset, limit);


        return new ResponseEntity<PaginationBean>(paginationData, HttpStatus.OK);
    }


    /**
     * This method update unknown voices
     *
     * @param myJSON
     * @return Integer
     * @throws JSONException
     */
    @RequestMapping(value = "/updateUnknownVoices", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    public ResponseEntity<Integer> updateUnknownVoices(@RequestBody JSONObject myJSON) throws JSONException {
        log.info("***updateUnknownVoices method started****");

        List<UvVoiceUpdateBean> beans = InvoicingUtilities.prepareUvUpdateBean(myJSON);
        int updateCount = service.update(beans);
        return new ResponseEntity<Integer>(updateCount, HttpStatus.OK);
    }

    /**
     * This method returns all active voices names and actions
     *
     * @return Integer
     * @throws JSONException
     */
    @RequestMapping(value = "/getVoicesAndActions", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    public ResponseEntity<JSONObject> getVoicesAndActions() throws JSONException {
        log.info("***getVoicesAndActions method started****");

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("actionsAndVoicesList", InvoicingUtilities.prepareVoiceArray(voiceService.getUVVoices()));
        return new ResponseEntity<JSONObject>(jsonObject, HttpStatus.OK);
    }

    @RequestMapping(value = "/getClaimFlag", params = {"voiceId"}, produces = MediaType.TEXT_PLAIN_VALUE, method = RequestMethod.GET)
    public ResponseEntity<String> getClaimFlagByVoiceId(@RequestParam Long voiceId) throws JSONException {
        log.info("***getClaimFlagByVoiceId method started****");

        String claimFlag = codeValueService.getClaimFlagByVoiceId(voiceId);

        return new ResponseEntity<String>(claimFlag, HttpStatus.OK);
    }
}
