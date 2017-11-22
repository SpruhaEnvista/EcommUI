package com.envista.msi.api.web.rest.glom;

import com.envista.msi.api.service.glom.RulesService;
import com.envista.msi.api.web.rest.dto.glom.RulesBean;
import com.envista.msi.api.web.rest.dto.glom.RulesDto;
import com.envista.msi.api.web.rest.util.pagination.PaginationBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;

/**
 * Created by KRISHNAREDDYM on 9/18/2017.
 */
@RestController
@RequestMapping("/api/rules")
public class RulesController {

    private final Logger log = LoggerFactory.getLogger(RulesController.class);

    @Inject
    private RulesService service;


    /**
     * HTTP GET - Get all Rules
     */
    @RequestMapping(value = "/getAll", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    public ResponseEntity<PaginationBean> getAll(@RequestParam(required = true) Long scriptId,
                                                 @RequestParam(required = false, defaultValue = "0") Integer offset,
                                                 @RequestParam(required = false, defaultValue = "10") Integer limit,
                                                 @RequestParam(required = false, defaultValue = "null") String sort,
                                                 @RequestParam(required = false, defaultValue = "1") Integer isActive
    ) throws Exception {

        log.info("***getAll method started****");

        RulesBean bean = new RulesBean();
        bean.setScriptId(scriptId);
        bean.setSortColumn(sort);
        bean.setOffset(offset);
        bean.setPageSize(limit);
        bean.setIsActive(isActive);
        bean.setActionType("getall");
        bean.setRuleId(0L);

        PaginationBean paginationData = new PaginationBean();
        paginationData = service.getAllPaginationData(bean);
        log.info("***getAll json***==== " + paginationData);
        return ResponseEntity.status(HttpStatus.OK).body(paginationData);
    }


    /**
     * HTTP POST - Create new Rule
     */
    @RequestMapping(value = "/insert", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    public ResponseEntity<RulesDto> insert(@RequestBody RulesBean bean) {


        bean.setActionType("INSERT");


        RulesDto dbDto = service.insertOrUpdate(bean);

        return new ResponseEntity<RulesDto>(dbDto, HttpStatus.OK);
    }

    /**
     * HTTP PUT - Update Rule
     */
    @RequestMapping(value = "/update", consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.PUT)
    public ResponseEntity<RulesDto> update(@RequestBody RulesBean bean) {

        bean.setActionType("UPDATE");

        RulesDto dbDto = service.insertOrUpdate(bean);
        return new ResponseEntity<RulesDto>(dbDto, HttpStatus.OK);
    }

    @RequestMapping(value = "/findByRuleName", produces = MediaType.TEXT_PLAIN_VALUE, method = RequestMethod.GET)
    public ResponseEntity<String> findByRuleName(@RequestParam(required = true, defaultValue = "null") String ruleName,
                                                 @RequestParam(required = false, defaultValue = "null") String prevRuleName,
                                                 @RequestParam(required = true, defaultValue = "0L") Long scriptId) {


        RulesDto dto = service.findByRuleName(ruleName.toUpperCase(), prevRuleName.toUpperCase(), scriptId);

        String msg = "Rule Name is not exist";

        if (dto != null)
            msg = "Rule Name is already exist";

        return new ResponseEntity<String>(msg, HttpStatus.OK);
    }

/*    */

    /**
     * HTTP DELETE - Delete Rule
     */
    @RequestMapping(value = "/delete", params = {"ruleIds"}, method = RequestMethod.PUT)
    public ResponseEntity<Integer> delete(@RequestParam String ruleIds) {


        int count = service.delete(ruleIds);

        return new ResponseEntity<Integer>(count, HttpStatus.OK);
    }

    /**
     * HTTP GET -  get Rule by rule id
     */
    @RequestMapping(value = "/{ruleId}", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    public ResponseEntity<RulesDto> findById(@PathVariable("ruleId") Long ruleId) {

        RulesBean bean = new RulesBean();
        bean.setRuleId(ruleId);
        bean.setScriptId(0L);
        bean.setOffset(0);
        bean.setPageSize(0);
        bean.setIsActive(1);
        bean.setActionType("getbyid");

        return new ResponseEntity<RulesDto>(service.findById(bean), HttpStatus.OK);
    }
}
