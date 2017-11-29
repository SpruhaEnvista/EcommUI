package com.envista.msi.api.web.rest.glom;

import com.envista.msi.api.service.glom.DataObjectService;
import com.envista.msi.api.web.rest.util.pagination.PaginationBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;

/**
 * Created by KRISHNAREDDYM on 11/29/2017.
 */
@RestController
@RequestMapping("/api/dataObject")
public class DataObjectController {

    private final Logger log = LoggerFactory.getLogger(DataObjectController.class);

    @Inject
    private DataObjectService service;


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

        PaginationBean paginationData = new PaginationBean();

        paginationData = service.getAllPaginationData(offset, limit, isActive, sort);

        log.info("***getAll json***==== " + paginationData);

        return ResponseEntity.status(HttpStatus.OK).body(paginationData);
    }
}
