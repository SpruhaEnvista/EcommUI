package com.envista.msi.api.web.rest.invoicing;

import com.envista.msi.api.service.DashboardsService;
import com.envista.msi.api.service.ReportsService;
import com.envista.msi.api.service.invoicing.CodeValueService;
import com.envista.msi.api.service.invoicing.CustomOmitsService;
import com.envista.msi.api.web.rest.dto.dashboard.filter.UserFilterUtilityDataDto;
import com.envista.msi.api.web.rest.dto.invoicing.CodeValueDto;
import com.envista.msi.api.web.rest.dto.invoicing.CustomOmitsDto;
import com.envista.msi.api.web.rest.dto.reports.ReportCustomerCarrierDto;
import com.envista.msi.api.web.rest.util.DateUtil;
import com.envista.msi.api.web.rest.util.FileOperations;
import com.envista.msi.api.web.rest.util.JSONUtil;
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
 * Created by KRISHNAREDDYM on 4/20/2017.
 */
@RestController
@RequestMapping("/api/customOmits")
public class CustomOmitsController {

    private final Logger LOG = LoggerFactory.getLogger(CustomOmitsController.class);

    @Inject
    private CustomOmitsService service;

    @Inject
    private CodeValueService codeValueService;

    @Autowired
    private DashboardsService dashboardsService;

    @Autowired
    ReportsService reportsService;

    @Autowired
    private FileOperations fileOperations;

    /**
     * HTTP GET - Get all
     */
    @RequestMapping(value = "/getAll", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    public ResponseEntity<List<CustomOmitsDto>> getAll() {
        LOG.info("***getAll method started****");
        List<CustomOmitsDto> dtos = service.findAll();
        LOG.info("***customOmitsTbs json***====" + dtos);
        return new ResponseEntity<List<CustomOmitsDto>>(dtos, HttpStatus.OK);
    }

    /**
     * HTTP GET -  get by id
     */
    @RequestMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    public ResponseEntity<CustomOmitsDto> findById(@PathVariable("id") Long id) {
        LOG.info("***findById method started**** id is : " + id);
        return new ResponseEntity<CustomOmitsDto>(service.findById(id), HttpStatus.OK);
    }

    /**
     * HTTP GET - Get all
     */
    @RequestMapping(value = "/getByUserId/{userId}", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    public ResponseEntity<PaginationBean> getByUserId(@PathVariable("userId") Long userId,
                                                      @RequestParam(required = false, defaultValue = "0") Integer offset,
                                                      @RequestParam(required = false, defaultValue = "10") Integer limit,
                                                      @RequestParam(required = false, defaultValue = "null") String sort) throws Exception {
        LOG.info("***getByUserId method started****");
        PaginationBean customOmitsPaginationData = new PaginationBean();

        CustomOmitsDto dto = new CustomOmitsDto();
        dto.setUserId(userId);
        customOmitsPaginationData = service.findByUserIdWithPagination(dto, offset, limit, sort);
        //List<CustomOmitsDto> dtos = service.findByUserId(userId);
        LOG.info("***getByUserId json***====" + customOmitsPaginationData);
        return new ResponseEntity<PaginationBean>(customOmitsPaginationData, HttpStatus.OK);
    }

    /**
     * HTTP POST - Create new Custom Omit
     */
    @RequestMapping(value = "/insert", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    public ResponseEntity<CustomOmitsDto> insert(@RequestBody CustomOmitsDto tb) {
        LOG.info("***create method started****" + tb);

        tb.setCustomerName(StringUtils.replace(tb.getCustomerName(), "----", "").trim());

        if (tb.getExpiryDate() != null)
            tb.setExpiryDate(DateUtil.format(new Date(Long.valueOf(tb.getExpiryDate())), "dd-MM-yyyy"));

        CustomOmitsDto dto = service.insert(tb);

        LOG.info("***create method ****" + dto);
        return new ResponseEntity<CustomOmitsDto>(dto, HttpStatus.OK);
    }

    /**
     * HTTP PUT - Update
     */
    @RequestMapping(value = "/update", consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.PUT)
    public ResponseEntity<CustomOmitsDto> update(@RequestBody CustomOmitsDto tb) {
        LOG.info("***update method started****" + tb);
        tb.setCustomerName(StringUtils.replace(tb.getCustomerName(), "----", "").trim());
        if (tb.getExpiryDate() != null)
            tb.setExpiryDate(DateUtil.format(new Date(Long.valueOf(tb.getExpiryDate())), "dd-MM-yyyy"));

        CustomOmitsDto dto = service.update(tb);

        return new ResponseEntity<CustomOmitsDto>(dto, HttpStatus.OK);
    }

    @RequestMapping(value = "/findSearchCriteria", params = {"trackingNumber", "customerIds", "creditTypeId", "comments", "carrierId", "userId"}, produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    public ResponseEntity<PaginationBean> findBySearchCriteria(@RequestParam String trackingNumber, @RequestParam String customerIds, @RequestParam long creditTypeId, @RequestParam String comments, long carrierId, long userId,
                                                               @RequestParam(required = false, defaultValue = "0") Integer offset, @RequestParam(required = false, defaultValue = "10") Integer limit, @RequestParam(required = false, defaultValue = "null") String sort) throws Exception {

        LOG.info("***findBySearchCriteria method started****" + trackingNumber);
        PaginationBean CustomOmitsPaginationData = new PaginationBean();

        if (customerIds != null && StringUtils.containsIgnoreCase(customerIds, "CU")) {
            customerIds = StringUtils.remove(customerIds, "CU");
        }

        CustomOmitsDto dto = new CustomOmitsDto();

        dto.setCustomOmitsId(0L);
        dto.setCustomerIds(customerIds);
        dto.setTrackingNumber(trackingNumber);
        dto.setCreditTypeId(creditTypeId);
        dto.setComments(comments);
        dto.setCarrierId(carrierId);
        dto.setUserId(userId);
        CustomOmitsPaginationData = service.findBySearchCriteria(dto, offset, limit, sort);

        //List<CustomOmitsDto> dtos = service.findBySearchCriteria(dto);

        LOG.info("***findBySearchCriteria method****" + CustomOmitsPaginationData);

        return new ResponseEntity<PaginationBean>(CustomOmitsPaginationData, HttpStatus.OK);
    }

    /**
     * HTTP DELETE - Delete custom omits
     */
    @RequestMapping(value = "/delete", params = {"customOmitIds"}, method = RequestMethod.PUT)
    public ResponseEntity<Integer> deleteCustomOmits(@RequestParam String customOmitIds) {

        LOG.info("***deleteCustomOmit method started****custom Omit ids are : " + customOmitIds);

        int deletetedRows = service.delete(customOmitIds);

        return new ResponseEntity<Integer>(deletetedRows, HttpStatus.OK);
    }

    /**
     * HTTP GET - Get all
     */
    @RequestMapping(value = "/getAllCustomers/{userId}", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    public ResponseEntity<JSONObject> getCustomers(@PathVariable("userId") Long userId) throws JSONException {
        LOG.info("***getCustomers method started****");
        JSONObject jsonObject = new JSONObject();

        if (userId == null)
            userId = (long) 0;

        List<ReportCustomerCarrierDto> customers = dashboardsService.getDashboardCustomers(userId);

        ReportCustomerCarrierDto customerHierarchy = reportsService.getCustomerHierarchyObject(customers, false);
        jsonObject.put("customers", JSONUtil.customerHierarchyJson(customerHierarchy));

        jsonObject.put("carriers", service.getAllCarriers());

        LOG.info("***jsonObject***" + jsonObject);


        return new ResponseEntity<JSONObject>(jsonObject, HttpStatus.OK);
    }

    /**
     * HTTP GET - Get all
     */
    @RequestMapping(value = "/getCarriers", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    public ResponseEntity<JSONObject> getCarriers() throws JSONException {
        LOG.info("***getCarriers method started****");
        JSONObject jsonObject = new JSONObject();


        jsonObject.put("carriers", service.getAllCarriers());

        LOG.info("***jsonObject***" + jsonObject);


        return new ResponseEntity<JSONObject>(jsonObject, HttpStatus.OK);
    }

    /**
     * HTTP GET - Get all
     */
    @RequestMapping(value = "/getCariersByCustomer/{customerIds}", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    public ResponseEntity<List<UserFilterUtilityDataDto>> getCariersByCustomer(@PathVariable("customerIds") String customerIds) {
        LOG.info("***getCariersByCustomer method started****");
        if (StringUtils.containsIgnoreCase(customerIds, "CU")) {
            customerIds = StringUtils.remove(customerIds, "CU");
        }
        List<UserFilterUtilityDataDto> carrList = dashboardsService.getCarrierByCustomer(customerIds, true);
        LOG.info("***getCariersByCustomer json***====" + carrList);
        return new ResponseEntity<List<UserFilterUtilityDataDto>>(carrList, HttpStatus.OK);
    }

    @RequestMapping(value = "/getCodeValues", params = {"codeGroupName", "property1", "codeValue", "actionType"}, produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    public ResponseEntity<List<CodeValueDto>> getCodeValues(@RequestParam String codeGroupName, @RequestParam String property1, @RequestParam String codeValue, @RequestParam String actionType) {


        List<CodeValueDto> dtos = codeValueService.GetCodeValues(codeGroupName, property1, codeValue, actionType);

        return new ResponseEntity<List<CodeValueDto>>(dtos, HttpStatus.OK);
    }

    @RequestMapping(value = "/getSearchCount", params = {"trackingNumber", "customerIds", "carrierId", "customOmitId"}, method = RequestMethod.GET)
    public ResponseEntity<Integer> getSearchCount(@RequestParam String trackingNumber, @RequestParam String customerIds, @RequestParam long carrierId, @RequestParam long customOmitId) throws Exception {

        LOG.info("***findBySearchCriteria method started****" + trackingNumber);
        PaginationBean CustomOmitsPaginationData = new PaginationBean();

        if (customerIds != null && StringUtils.containsIgnoreCase(customerIds, "CU")) {
            customerIds = StringUtils.remove(customerIds, "CU");
        }

        CustomOmitsDto dto = new CustomOmitsDto();

        dto.setCustomOmitsId(customOmitId);
        dto.setCustomerIds(customerIds);
        dto.setTrackingNumber(trackingNumber);
        dto.setCreditTypeId(0L);
        dto.setCarrierId(carrierId);
        dto.setUserId(0L);
        int count = service.getSeachCount(dto);

        return new ResponseEntity<Integer>(count, HttpStatus.OK);
    }


    @RequestMapping(value = "/findBySearchCriteriaAndExport", params = {"trackingNumber", "customerIds", "creditTypeId", "comments", "carrierId", "userId","exportType","totalRecordsCount"}, produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    public @ResponseBody void findBySearchCriteriaAndExport(@RequestParam String trackingNumber, @RequestParam String customerIds, @RequestParam long creditTypeId, @RequestParam String comments, @RequestParam long carrierId,@RequestParam long userId,@RequestParam String exportType,
                                                               @RequestParam(required = false, defaultValue = "10") Integer totalRecordsCount,HttpServletResponse response) throws Exception{

        LOG.info("***findBySearchCriteriaAndExport method started****" + trackingNumber);
        PaginationBean CustomOmitsPaginationData = new PaginationBean();

        if (customerIds != null && StringUtils.containsIgnoreCase(customerIds, "CU")) {
            customerIds = StringUtils.remove(customerIds, "CU");
        }

        CustomOmitsDto dto = new CustomOmitsDto();

        dto.setCustomOmitsId(0L);
        dto.setCustomerIds(customerIds);
        dto.setTrackingNumber(trackingNumber);
        dto.setCreditTypeId(creditTypeId);
        dto.setComments(comments);
        dto.setCarrierId(carrierId);
        dto.setUserId(userId);
        CustomOmitsPaginationData = service.findBySearchCriteria(dto, 0, totalRecordsCount, null);

        LOG.info("***findBySearchCriteriaAndExports method****" + CustomOmitsPaginationData);

        fileOperations.exportCustomOmits(exportType,(List<CustomOmitsDto>)CustomOmitsPaginationData.getData(),response);

    }

    /**
     * HTTP GET - Get Customers By Carrier
     */
    @RequestMapping(value = "/getCustomersByCarrier", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    public ResponseEntity<JSONObject> getCustomersByCarrier(@RequestParam(required = true, defaultValue = "0L") Long userId, @RequestParam(required = true, defaultValue = "0L") Long carrierId) throws JSONException {
        LOG.info("***getCustomersByCarrier method started****");
        JSONObject jsonObject = new JSONObject();

        if (userId == null)
            userId = (long) 0;

        if (carrierId == null)
            carrierId = (long) 0;

        List<ReportCustomerCarrierDto> customers = service.getCustomers(userId, carrierId, 0, "GETBYCARRIER");

        ReportCustomerCarrierDto customerHierarchy = reportsService.getCustomerHierarchyObject(customers, false);
        jsonObject.put("customers", JSONUtil.customerHierarchyJson(customerHierarchy));

        return new ResponseEntity<JSONObject>(jsonObject, HttpStatus.OK);
    }

    /**
     * HTTP GET - Get Customers By BusinessPartner
     */
    @RequestMapping(value = "/getCustomersByBusinessPartner", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    public ResponseEntity<JSONObject> getCustomersByBusinessPartner(@RequestParam(required = true, defaultValue = "0L") Long userId, @RequestParam(required = true, defaultValue = "0L") Long businessPartnerId) throws JSONException {
        LOG.info("***getCustomersByBusinessPartner method started****");
        JSONObject jsonObject = new JSONObject();

        if (userId == null)
            userId = (long) 0;

        if (businessPartnerId == null)
            businessPartnerId = (long) 0;

        List<ReportCustomerCarrierDto> customers = service.getCustomers(userId, 0, businessPartnerId, "GETBYBUSINESSPARTNER");

        ReportCustomerCarrierDto customerHierarchy = reportsService.getCustomerHierarchyObject(customers, false);
        jsonObject.put("customers", JSONUtil.customerHierarchyJson(customerHierarchy));

        return new ResponseEntity<JSONObject>(jsonObject, HttpStatus.OK);
    }

    /**
     * HTTP GET - Get BusinessPartner By Customer
     */
    @RequestMapping(value = "/getBusinessPartnerByCustomer", method = RequestMethod.GET)
    public ResponseEntity<Integer> getBusinessPartnerByCustomer(@RequestParam(required = true, defaultValue = "0L") Long customerId) throws JSONException {
        LOG.info("***getBusinessPartnerByCustomer method started****");
        JSONObject jsonObject = new JSONObject();

        if (customerId == null)
            customerId = (long) 0;


        int businessPartnerId = service.getBusinessPartnerByCustomer(customerId);

        return new ResponseEntity<Integer>(businessPartnerId, HttpStatus.OK);
    }
}
