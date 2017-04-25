package com.envista.msi.api.web.rest.invoicing;

import com.envista.msi.api.service.DashboardsService;
import com.envista.msi.api.service.ReportsService;
import com.envista.msi.api.service.invoicing.CodeValueService;
import com.envista.msi.api.service.invoicing.CustomOmitsService;
import com.envista.msi.api.web.rest.dto.dashboard.filter.UserFilterUtilityDataDto;
import com.envista.msi.api.web.rest.dto.invoicing.CodeValueDto;
import com.envista.msi.api.web.rest.dto.invoicing.CustomOmitsDto;
import com.envista.msi.api.web.rest.dto.reports.ReportCustomerCarrierDto;
import com.envista.msi.api.web.rest.util.JSONUtil;
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
    public ResponseEntity<List<CustomOmitsDto>> getByUserId(@PathVariable("userId") Long userId) {
        LOG.info("***getByUserId method started****");
        List<CustomOmitsDto> dtos = service.findByuserId(userId);
        LOG.info("***getByUserId json***====" + dtos);
        return new ResponseEntity<List<CustomOmitsDto>>(dtos, HttpStatus.OK);
    }

    /**
     * HTTP POST - Create new Custom Omit
     */
    @RequestMapping(value = "/insert", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    public ResponseEntity<CustomOmitsDto> insert(@RequestBody CustomOmitsDto tb) {
        LOG.info("***create method started****" + tb);

        tb.setCustomerName(StringUtils.replace(tb.getCustomerName(), "----", "").trim());

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

        CustomOmitsDto dto = service.update(tb);
        return new ResponseEntity<CustomOmitsDto>(dto, HttpStatus.OK);
    }

    @RequestMapping(value = "/findSearchCriteria", params = {"trackingNumber", "customerIds", "creditTypeId", "comments", "carrierId", "userId"}, produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    public ResponseEntity<List<CustomOmitsDto>> findBySearchCriteria(@RequestParam String trackingNumber, @RequestParam String customerIds, @RequestParam long creditTypeId, @RequestParam String comments, long carrierId, long userId) {

        LOG.info("***findBySearchCriteria method started****" + trackingNumber);

        if (customerIds != null && StringUtils.containsIgnoreCase(customerIds, "CU")) {
            customerIds = StringUtils.remove(customerIds, "CU");
        }

        CustomOmitsDto dto = new CustomOmitsDto();

        dto.setCustomerIds(customerIds);
        dto.setTrackingNumber(trackingNumber);
        dto.setCreditTypeId(creditTypeId);
        dto.setComments(comments);
        dto.setCarrierId(carrierId);
        dto.setUserId(userId);

        List<CustomOmitsDto> dtos = service.findBySearchCriteria(dto);

        LOG.info("***findBySearchCriteria method****" + dtos.size());

        return new ResponseEntity<List<CustomOmitsDto>>(dtos, HttpStatus.OK);
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

    @RequestMapping(value = "/getCodeValues", params = {"carrierId", "codeGroupId"}, produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    public ResponseEntity<List<CodeValueDto>> getCodeValues(@RequestParam Long carrierId, @RequestParam Long codeGroupId) {


        List<CodeValueDto> dtos = codeValueService.GetCodeValues(carrierId, codeGroupId);

        return new ResponseEntity<List<CodeValueDto>>(dtos, HttpStatus.OK);
    }
}
