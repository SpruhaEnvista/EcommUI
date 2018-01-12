package com.envista.msi.api.web.rest.invoicing;

import com.envista.msi.api.service.invoicing.CodeValueService;
import com.envista.msi.api.service.invoicing.CreditsPRService;
import com.envista.msi.api.service.invoicing.WeekEndService;
import com.envista.msi.api.web.rest.dto.invoicing.CodeValueDto;
import com.envista.msi.api.web.rest.dto.invoicing.CreditsPRDto;
import com.envista.msi.api.web.rest.dto.invoicing.CreditsPRSearchBean;
import com.envista.msi.api.web.rest.dto.invoicing.WeekEndDto;
import com.envista.msi.api.web.rest.util.DateUtil;
import com.envista.msi.api.web.rest.util.FileOperations;
import com.envista.msi.api.web.rest.util.InvoicingUtilities;
import com.envista.msi.api.web.rest.util.pagination.PaginationBean;
import org.apache.commons.lang.StringUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;
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

    @Autowired
    private FileOperations fileOperations;

    /**
     * HTTP Get - Search
     */
    @RequestMapping(value = "/search", params = {"businessPartnerId", "customerIds", "savedFilter", "invStatusId", "invCatagoryId", "invWeekEndId", "invoiceModeId",
            "carrierId", "creditClassId", "claimFlag", "reviewFlag", "createDateFrom", "invoiceDateFrom", "closeDateFrom", "invoiceNumbers", "trackingNumbers", "internalKeyIds", "invoiceMethodId",
            "payRunNos", "controlNums", "adjReasons", "invComments", "createDateTo", "invoiceDateTo", "closeDateTo"}, produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    public ResponseEntity<PaginationBean> search(@RequestParam Long businessPartnerId, @RequestParam String customerIds, @RequestParam String savedFilter
            , @RequestParam Long invStatusId, @RequestParam Long invCatagoryId, @RequestParam Long invWeekEndId, @RequestParam Long invoiceModeId
            , @RequestParam Long carrierId, @RequestParam Long creditClassId, @RequestParam String claimFlag, @RequestParam String reviewFlag
            , @RequestParam String createDateFrom, @RequestParam String invoiceDateFrom, @RequestParam String closeDateFrom, @RequestParam String invoiceNumbers
            , @RequestParam String trackingNumbers, @RequestParam String internalKeyIds, @RequestParam Long invoiceMethodId, @RequestParam String payRunNos
            , @RequestParam String controlNums, @RequestParam String adjReasons, @RequestParam String invComments
            , @RequestParam String createDateTo, @RequestParam String invoiceDateTo, @RequestParam String closeDateTo
            , @RequestParam(required = false, defaultValue = "0") Integer offset, @RequestParam(required = false, defaultValue = "10") Integer limit, @RequestParam(required = false, defaultValue = "null") String sort) throws Exception {
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
        bean.setSort(sort);


        PaginationBean paginationData = service.getSearchPaginationData(bean, offset, limit);


        return new ResponseEntity<PaginationBean>(paginationData, HttpStatus.OK);
    }


    /**
     *
     */
    @RequestMapping(value = "/update", params = {"ebillManifestIds", "actionType", "userName"}, method = RequestMethod.PUT)
    public ResponseEntity<Integer> updateStatus(@RequestParam String ebillManifestIds, @RequestParam String actionType, @RequestParam String userName) {

        log.info("***updateStatus method started****ebillManifestIds are : " + ebillManifestIds);

        int updateddRows = service.update(ebillManifestIds, actionType, userName);

        return new ResponseEntity<Integer>(updateddRows, HttpStatus.OK);
    }

    @RequestMapping(value = "/getSSCodeValues", params = {"weekEndDate"}, produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    public ResponseEntity<JSONObject> getSearchScreenCodeValues(@RequestParam String weekEndDate) throws JSONException {

        JSONObject jsonObject = new JSONObject();

        List<CodeValueDto> creditTypes = codeValueService.GetCodeValues("CreditTypes", null, null, null);
        jsonObject.put("creditTypes", creditTypes);

        List<CodeValueDto> invCatagories = codeValueService.GetCodeValues("Internal Invoicing Categories", null, null, null);
        jsonObject.put("invCatagories", invCatagories);

        List<CodeValueDto> invStatuses = codeValueService.GetCodeValues("Invoicing Statuses", null, null, null);
        jsonObject.put("invStatuses", invStatuses);

        List<WeekEndDto> weekEndDtos = weekEndService.getAll();
        jsonObject.put("weekEndIfo", weekEndDtos);

        jsonObject.put("bpList", InvoicingUtilities.getBuissnessPartnersList());

        jsonObject.put("flagList", InvoicingUtilities.getFlagValueList());

        WeekEndDto weekEndDto = null;
        if (weekEndDate != null && !StringUtils.equalsIgnoreCase(weekEndDate, "null"))
            weekEndDto = weekEndService.findByWeekEndDate(weekEndDate);
        if (weekEndDto == null)
            jsonObject.put("weekEndDateInfo", "No Data");
        else
            jsonObject.put("weekEndDateInfo", weekEndDto);

        return new ResponseEntity<JSONObject>(jsonObject, HttpStatus.OK);
    }

    @RequestMapping(value = "/searchAndExport", params = {"businessPartnerId", "customerIds", "savedFilter", "invStatusId", "invCatagoryId", "invWeekEndId", "invoiceModeId",
            "carrierId", "creditClassId", "claimFlag", "reviewFlag", "createDateFrom", "invoiceDateFrom", "closeDateFrom", "invoiceNumbers", "trackingNumbers", "internalKeyIds", "invoiceMethodId",
            "payRunNos", "controlNums", "adjReasons", "invComments","exportType","totalRecordsCount","createDateTo", "invoiceDateTo", "closeDateTo"}, produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    public @ResponseBody
    void  searchAndExport(@RequestParam Long businessPartnerId, @RequestParam String customerIds, @RequestParam String savedFilter
            , @RequestParam Long invStatusId, @RequestParam Long invCatagoryId, @RequestParam Long invWeekEndId, @RequestParam Long invoiceModeId
            , @RequestParam Long carrierId, @RequestParam Long creditClassId, @RequestParam String claimFlag, @RequestParam String reviewFlag
            , @RequestParam String createDateFrom, @RequestParam String invoiceDateFrom, @RequestParam String closeDateFrom, @RequestParam String invoiceNumbers
            , @RequestParam String trackingNumbers, @RequestParam String internalKeyIds, @RequestParam Long invoiceMethodId, @RequestParam String payRunNos
            , @RequestParam String controlNums, @RequestParam String adjReasons, @RequestParam String invComments, @RequestParam String exportType
            , @RequestParam(required = false, defaultValue = "10") Integer totalRecordsCount, @RequestParam String createDateTo, @RequestParam String invoiceDateTo
            , @RequestParam String closeDateTo, HttpServletResponse response) throws Exception {
        log.info("***searchAndExport method started****");

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

        PaginationBean paginationData = service.getSearchPaginationData(bean, 0, totalRecordsCount);

        fileOperations.exportPendingCredits(exportType,(List<CreditsPRDto>)paginationData.getData(),response);


    }
}
