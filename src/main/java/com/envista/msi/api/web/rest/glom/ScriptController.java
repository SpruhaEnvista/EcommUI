package com.envista.msi.api.web.rest.glom;

import com.envista.msi.api.service.glom.ScriptService;
import com.envista.msi.api.web.rest.dto.glom.ScriptBean;
import com.envista.msi.api.web.rest.dto.glom.ScriptDto;
import com.envista.msi.api.web.rest.util.pagination.PaginationBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;

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
     * HTTP GET - Get all Voices
     */
    @RequestMapping(value = "/getAll", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    public ResponseEntity<PaginationBean> getAll(@RequestParam(required = false, defaultValue = "0") Integer offset,
                                                 @RequestParam(required = false, defaultValue = "10") Integer limit,
                                                 @RequestParam(required = false, defaultValue = "null") String sort
    ) throws Exception {

        log.info("***getAll method started****");

        ScriptBean bean = new ScriptBean();
        bean.setSortColumn(sort);
        bean.setOffset(offset);
        bean.setPageSize(limit);
        bean.setActionType("getall");

        PaginationBean paginationData = new PaginationBean();
        paginationData = service.getAllPaginationData(bean);
        log.info("***getAll json***==== " + paginationData);
        return ResponseEntity.status(HttpStatus.OK).body(paginationData);
    }

    @RequestMapping(value = "/getByCustomer", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    public ResponseEntity<PaginationBean> getByCustomer(@RequestParam(required = true) Long customerId,
                                                        @RequestParam(required = false, defaultValue = "0") Integer offset,
                                                        @RequestParam(required = false, defaultValue = "10") Integer limit,
                                                        @RequestParam(required = false, defaultValue = "null") String sort
    ) throws Exception {

        log.info("***getAll method started****");

        ScriptBean bean = new ScriptBean();
        bean.setSortColumn(sort);
        bean.setOffset(offset);
        bean.setPageSize(limit);
        bean.setCustomerId(customerId);
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
        ScriptDto dbDto = service.insertOrUpdate(bean);

        return new ResponseEntity<ScriptDto>(dbDto, HttpStatus.OK);
    }

    /**
     * HTTP PUT - Update Script
     */
    @RequestMapping(value = "/update", consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.PUT)
    public ResponseEntity<ScriptDto> update(@RequestBody ScriptBean bean) {
        bean.setActionType("UPDATE");
        ScriptDto dbDto = service.insertOrUpdate(bean);
        return new ResponseEntity<ScriptDto>(dbDto, HttpStatus.OK);
    }

    @RequestMapping(value = "/findByScriptName/{scriptName}/{prevScriptName}", produces = MediaType.TEXT_PLAIN_VALUE, method = RequestMethod.GET)
    public ResponseEntity<String> findByVoiceName(@PathVariable("scriptName") String scriptName, @PathVariable("prevScriptName") String prevScriptName) {


        ScriptDto dto = service.findByScriptName(scriptName.toUpperCase(), prevScriptName.toUpperCase());

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

}
