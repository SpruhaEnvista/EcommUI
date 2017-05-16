package com.envista.msi.api.web.rest.invoicing;

import com.envista.msi.api.service.invoicing.CodeValueService;
import com.envista.msi.api.service.invoicing.CreditsPRService;
import com.envista.msi.api.service.invoicing.WeekEndService;
import com.envista.msi.api.web.rest.dto.invoicing.CodeValueDto;
import com.envista.msi.api.web.rest.dto.invoicing.CreditsPRDto;
import com.envista.msi.api.web.rest.dto.invoicing.CreditsPRSearchBean;
import com.envista.msi.api.web.rest.dto.invoicing.WeekEndDto;
import com.envista.msi.api.web.rest.util.DateUtil;
import org.apache.commons.lang.StringEscapeUtils;
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
 * Created by KRISHNAREDDYM on 5/11/2017.
 */
@RestController
@RequestMapping("/api/creditsPR")
public class CreditsPRController {

    private final Logger log = LoggerFactory.getLogger(CreditsPRController.class);

    @Inject
    private CreditsPRService service;

    @Inject
    private CodeValueService codeValueService;

    @Inject
    private WeekEndService weekEndService;

    /**
     * HTTP Get - Search
     */
    @RequestMapping(value = "/search", params = {"businessPartnerId", "customerIds", "savedFilter", "invStatusId", "invCatagoryId", "invWeekEndId", "invoiceModeId",
            "carrierId", "creditClassId", "omitFlag", "reviewFlag", "createDate", "invoiceDate", "closeDate", "invoiceNumbers", "trackingNumbers", "internalKeyIds", "invoiceMethodId",
            "payRunNos", "controlNums", "adjReasons", "invComments"}, produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    public ResponseEntity<List<CreditsPRDto>> search(@RequestParam Long businessPartnerId, @RequestParam String customerIds, @RequestParam String savedFilter
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


        List<CreditsPRDto> dtos = service.search(bean);


        return new ResponseEntity<List<CreditsPRDto>>(dtos, HttpStatus.OK);
    }


    /**
     * HTTP DELETE - Delete custom omits
     */
    @RequestMapping(value = "/update", params = {"ebillManifestIds", "actionType"}, method = RequestMethod.PUT)
    public ResponseEntity<Integer> updateStatus(@RequestParam String ebillManifestIds, @RequestParam String actionType) {

        log.info("***updateStatus method started****ebillManifestIds are : " + ebillManifestIds);

        int updateddRows = service.update(ebillManifestIds, actionType);

        return new ResponseEntity<Integer>(updateddRows, HttpStatus.OK);
    }

    @RequestMapping(value = "/getSSCodeValues", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    public ResponseEntity<JSONObject> getSearchScreenCodeValues() throws JSONException {

        JSONObject jsonObject = new JSONObject();

        List<CodeValueDto> creditTypes = codeValueService.GetCodeValues("CreditTypes", null, null, null);
        jsonObject.put("creditTypes", creditTypes);

        List<CodeValueDto> invCatagories = codeValueService.GetCodeValues("Internal Invoicing Categories", null, null, null);
        jsonObject.put("invCatagories", invCatagories);

        List<CodeValueDto> invStatuses = codeValueService.GetCodeValues("Invoicing Statuses", null, null, null);
        jsonObject.put("invStatuses", invStatuses);

        List<WeekEndDto> weekEndDtos = weekEndService.getAll();
        jsonObject.put("weekEndIfo", weekEndDtos);

        return new ResponseEntity<JSONObject>(jsonObject, HttpStatus.OK);
    }
}
