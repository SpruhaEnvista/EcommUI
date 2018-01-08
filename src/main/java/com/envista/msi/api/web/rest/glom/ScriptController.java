package com.envista.msi.api.web.rest.glom;

import com.envista.msi.api.service.glom.ScriptService;
import com.envista.msi.api.web.rest.dto.CustomerDto;
import com.envista.msi.api.web.rest.dto.glom.ScriptBean;
import com.envista.msi.api.web.rest.dto.glom.RunScriptBean;
import com.envista.msi.api.web.rest.dto.glom.ScriptDto;
import com.envista.msi.api.web.rest.dto.glom.RunScriptDto;
import com.envista.msi.api.web.rest.util.DateUtil;
import com.envista.msi.api.web.rest.util.pagination.PaginationBean;
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
 * Created by KRISHNAREDDYM on 9/14/2017.
 */
@RestController
@RequestMapping("/api/script")
public class ScriptController {

    private final Logger log = LoggerFactory.getLogger(ScriptController.class);

    @Inject
    private ScriptService service;


    /**
     * HTTP GET - Get all Rules
     */
    @RequestMapping(value = "/getAll", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    public ResponseEntity<PaginationBean> getAll(@RequestParam(required = false, defaultValue = "0") Integer offset,
                                                 @RequestParam(required = false, defaultValue = "10") Integer limit,
                                                 @RequestParam(required = false, defaultValue = "null") String sort,
                                                 @RequestParam(required = false, defaultValue = "1") Integer isActive
    ) throws Exception {

        log.info("***getAll method started****");

        ScriptBean bean = new ScriptBean();
        bean.setSortColumn(sort);
        bean.setOffset(offset);
        bean.setPageSize(limit);
        bean.setIsActive(isActive);
        bean.setActionType("getall");

        PaginationBean paginationData = new PaginationBean();
        paginationData = service.getAllPaginationData(bean);
        log.info("***getAll json***==== " + paginationData);
        return ResponseEntity.status(HttpStatus.OK).body(paginationData);
    }

    @RequestMapping(value = "/getByCustomer", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    public ResponseEntity<PaginationBean> getByCustomer(@RequestParam(required = true) int customerId,
                                                        @RequestParam(required = false, defaultValue = "0") Integer offset,
                                                        @RequestParam(required = false, defaultValue = "10") Integer limit,
                                                        @RequestParam(required = false, defaultValue = "null") String sort,
                                                        @RequestParam(required = false, defaultValue = "1") Integer isActive
    ) throws Exception {

        log.info("***getAll method started****");

        ScriptBean bean = new ScriptBean();
        bean.setSortColumn(sort);
        bean.setOffset(offset);
        bean.setPageSize(limit);
        bean.setCustomerId(customerId);
        bean.setIsActive(isActive);
        bean.setActionType("getbycustomerid");

        PaginationBean paginationData = new PaginationBean();
        paginationData = service.getAllPaginationData(bean);
        log.info("***getAll json***==== " + paginationData);
        return ResponseEntity.status(HttpStatus.OK).body(paginationData);
    }


    /**
     * HTTP POST - Create new Script
     */
    @RequestMapping(value = "/insert", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    public ResponseEntity<ScriptDto> insert(@RequestBody ScriptBean bean) {


        bean.setActionType("INSERT");

        if (bean.getExpiryDate() != null)
            bean.setExpiryDate(DateUtil.format(new Date(Long.valueOf(bean.getExpiryDate())), "dd-MM-yyyy"));

        if (bean.getEffectiveDate() != null)
            bean.setEffectiveDate(DateUtil.format(new Date(Long.valueOf(bean.getEffectiveDate())), "dd-MM-yyyy"));

        ScriptDto dbDto = service.insertOrUpdate(bean);

        return new ResponseEntity<ScriptDto>(dbDto, HttpStatus.OK);
    }

    /**
     * HTTP PUT - Update Script
     */
    @RequestMapping(value = "/update", consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.PUT)
    public ResponseEntity<ScriptDto> update(@RequestBody ScriptBean bean) {
        bean.setActionType("UPDATE");

        if (bean.getExpiryDate() != null)
            bean.setExpiryDate(DateUtil.format(new Date(Long.valueOf(bean.getExpiryDate())), "dd-MM-yyyy"));

        if (bean.getEffectiveDate() != null)
            bean.setEffectiveDate(DateUtil.format(new Date(Long.valueOf(bean.getEffectiveDate())), "dd-MM-yyyy"));

        ScriptDto dbDto = service.insertOrUpdate(bean);
        return new ResponseEntity<ScriptDto>(dbDto, HttpStatus.OK);
    }

    @RequestMapping(value = "/findByScriptName/{scriptName}/{prevScriptName}/{customerId}/{prevCustomerId}", produces = MediaType.TEXT_PLAIN_VALUE, method = RequestMethod.GET)
    public ResponseEntity<String> findByScriptName(@PathVariable("scriptName") String scriptName, @PathVariable("prevScriptName") String prevScriptName,
                                                   @PathVariable("customerId") int customerId, @PathVariable("prevCustomerId") int prevCustomerId) {


        ScriptDto dto = service.findByScriptName(scriptName.toUpperCase(), prevScriptName.toUpperCase(), customerId, prevCustomerId);

        String msg = "Script Name is not exist";

        if (dto != null)
            msg = "Script Name is already exist";

        return new ResponseEntity<String>(msg, HttpStatus.OK);
    }

/*    */

    /**
     * HTTP DELETE - Delete Script
     */
    @RequestMapping(value = "/delete", params = {"scriptIds"}, method = RequestMethod.PUT)
    public ResponseEntity<Integer> delete(@RequestParam String scriptIds) {


        int count = service.delete(scriptIds);

        return new ResponseEntity<Integer>(count, HttpStatus.OK);
    }

    /**
     * HTTP GET -  get Script by script id
     */
    @RequestMapping(value = "/{scriptId}", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    public ResponseEntity<ScriptDto> findById(@PathVariable("scriptId") Long scriptId) {

        ScriptBean bean = new ScriptBean();
        bean.setScriptId(scriptId);
        bean.setActionType("getbyid");

        return new ResponseEntity<ScriptDto>(service.findById(bean), HttpStatus.OK);
    }


    @RequestMapping(value = "/getCustomers/{userId}", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    public ResponseEntity<List<CustomerDto>> findById(@PathVariable("userId") int userId) {


        return new ResponseEntity<List<CustomerDto>>(service.getAllCustomers(userId), HttpStatus.OK);
    }
    @RequestMapping(value = "/insertRunScript", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    public ResponseEntity<RunScriptDto> insertRunScript(@RequestBody RunScriptBean bean) {


        bean.setActionType("INSERT");

        if (bean.getFromDate() != null)
            bean.setFromDate(DateUtil.format(new Date(Long.valueOf(bean.getFromDate())), "dd-MM-yyyy"));

        if (bean.getToDate() != null)
            bean.setToDate(DateUtil.format(new Date(Long.valueOf(bean.getToDate())), "dd-MM-yyyy"));

        RunScriptDto dbDto = service.insertRunScript(bean);

        return new ResponseEntity<RunScriptDto>(dbDto, HttpStatus.OK);
    }

    @RequestMapping(value = "/insertRunScript", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    public ResponseEntity<RunScriptDto> insertRunScript(@RequestBody RunScriptBean bean) {


        bean.setActionType("INSERT");

        if (bean.getFromDate() != null)
            bean.setFromDate(DateUtil.format(new Date(Long.valueOf(bean.getFromDate())), "dd-MM-yyyy"));

        if (bean.getToDate() != null)
            bean.setToDate(DateUtil.format(new Date(Long.valueOf(bean.getToDate())), "dd-MM-yyyy"));

        RunScriptDto dbDto = service.insertRunScript(bean);

        return new ResponseEntity<RunScriptDto>(dbDto, HttpStatus.OK);
    }

}
