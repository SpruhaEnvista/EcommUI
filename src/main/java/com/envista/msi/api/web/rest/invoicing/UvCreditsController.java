package com.envista.msi.api.web.rest.invoicing;

import com.envista.msi.api.service.invoicing.CodeValueService;
import com.envista.msi.api.service.invoicing.CreditsPRService;
import com.envista.msi.api.service.invoicing.UvCreditsService;
import com.envista.msi.api.service.invoicing.VoiceService;
import com.envista.msi.api.web.rest.dto.invoicing.CreditsPRDto;
import com.envista.msi.api.web.rest.dto.invoicing.CreditsPRSearchBean;
import com.envista.msi.api.web.rest.dto.invoicing.UvCreditsDto;
import com.envista.msi.api.web.rest.dto.invoicing.UvVoiceUpdateBean;
import com.envista.msi.api.web.rest.util.DateUtil;
import com.envista.msi.api.web.rest.util.InvoicingUtilities;
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
     * @param omitFlag
     * @param reviewFlag
     * @param createDate
     * @param invoiceDate
     * @param closeDate
     * @param invoiceNumbers
     * @param trackingNumbers
     * @param internalKeyIds
     * @param invoiceMethodId
     * @param payRunNos
     * @param controlNums
     * @param adjReasons
     * @param invComments
     * @return List<UvCreditsDto>
     */
    @RequestMapping(value = "/search", params = {"businessPartnerId", "customerIds", "savedFilter", "invStatusId", "invCatagoryId", "invWeekEndId", "invoiceModeId",
            "carrierId", "creditClassId", "omitFlag", "reviewFlag", "createDate", "invoiceDate", "closeDate", "invoiceNumbers", "trackingNumbers", "internalKeyIds", "invoiceMethodId",
            "payRunNos", "controlNums", "adjReasons", "invComments"}, produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    public ResponseEntity<List<UvCreditsDto>> search(@RequestParam Long businessPartnerId, @RequestParam String customerIds, @RequestParam String savedFilter
            , @RequestParam Long invStatusId, @RequestParam Long invCatagoryId, @RequestParam Long invWeekEndId, @RequestParam Long invoiceModeId
            , @RequestParam Long carrierId, @RequestParam Long creditClassId, @RequestParam String omitFlag, @RequestParam String reviewFlag
            , @RequestParam String createDate, @RequestParam String invoiceDate, @RequestParam String closeDate, @RequestParam String invoiceNumbers
            , @RequestParam String trackingNumbers, @RequestParam String internalKeyIds, @RequestParam Long invoiceMethodId, @RequestParam String payRunNos
            , @RequestParam String controlNums, @RequestParam String adjReasons, @RequestParam String invComments) {
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
        bean.setOmitFlag(omitFlag);
        bean.setReviewFlag(reviewFlag);
        if (createDate != null && !StringUtils.equalsIgnoreCase(createDate, "null"))
            bean.setCreateDate(DateUtil.format(new Date(Long.valueOf(createDate)), "dd-MM-yyyy"));
        if (invoiceDate != null && !StringUtils.equalsIgnoreCase(invoiceDate, "null"))
            bean.setInvoiceDate(DateUtil.format(new Date(Long.valueOf(invoiceDate)), "dd-MM-yyyy"));
        if (closeDate != null && !StringUtils.equalsIgnoreCase(closeDate, "null"))
            bean.setCloseDate(DateUtil.format(new Date(Long.valueOf(closeDate)), "dd-MM-yyyy"));
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


    /**
     * This method update unknown voices
     *
     * @param myJSON
     * @return Integer
     * @throws JSONException
     */
    @RequestMapping(value = "/updateUnknownVoices", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    public ResponseEntity<Integer> updateUnknownVoices(@RequestBody JSONObject myJSON, @RequestParam("userName") String userName) throws JSONException {
        log.info("***updateUnknownVoices method started****");

        List<UvVoiceUpdateBean> beans = InvoicingUtilities.prepareUvUpdateBean(myJSON);
        int updateCount = service.update(beans, userName);
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

    @RequestMapping(value = "/getOmitFlag", params = {"voiceId"}, produces = MediaType.TEXT_PLAIN_VALUE, method = RequestMethod.GET)
    public ResponseEntity<String> getOmitFlagByVoiceId(@RequestParam Long voiceId) throws JSONException {
        log.info("***getOmitFlagByVoiceId method started****");

        String omitFlag = codeValueService.getOmitFlagByVoiceId(voiceId);

        return new ResponseEntity<String>(omitFlag, HttpStatus.OK);
    }
}
