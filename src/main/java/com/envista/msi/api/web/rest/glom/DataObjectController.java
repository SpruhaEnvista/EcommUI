package com.envista.msi.api.web.rest.glom;

import com.envista.msi.api.service.glom.DataObjectService;
import com.envista.msi.api.web.rest.dto.glom.DataObjectBean;
import com.envista.msi.api.web.rest.dto.glom.DataObjectDto;
import com.envista.msi.api.web.rest.util.pagination.PaginationBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.sql.SQLException;
import java.util.List;

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

    /**
     * HTTP POST - Create new Script
     */
    @RequestMapping(value = "/insert", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.TEXT_PLAIN_VALUE, method = RequestMethod.POST)
    public ResponseEntity<String> insert(@RequestBody DataObjectBean bean) throws SQLException {


        bean.setActionType("INSERT");
        bean.setDataObjectId(0L);


        service.insertOrUpdate(bean);

        return new ResponseEntity<String>("success", HttpStatus.OK);
    }

    /**
     * HTTP PUT - Update Script
     */
    @RequestMapping(value = "/update", consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.TEXT_PLAIN_VALUE, method = RequestMethod.PUT)
    public ResponseEntity<String> update(@RequestBody DataObjectBean bean) throws SQLException {

        bean.setActionType("UPDATE");

        service.insertOrUpdate(bean);

        return new ResponseEntity<String>("success", HttpStatus.OK);
    }
    @RequestMapping(value = "/delete", params = {"dataObjectId"}, method = RequestMethod.PUT)
    public ResponseEntity<Integer> delete(@RequestParam String dataObjectId) {

        int count = service.delete(dataObjectId);

        return new ResponseEntity<Integer>(count, HttpStatus.OK);
    }

    /**
     * HTTP GET - Get all active data objects
     */
    @RequestMapping(value = "/getActiveDataObjects", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    public ResponseEntity<List<DataObjectDto>> getActiveDataObjects() throws Exception {

        log.info("***getActiveDataObjects method started****");


        List<DataObjectDto> dataObjectDtos = service.getActiveDataObjects();

        return ResponseEntity.status(HttpStatus.OK).body(dataObjectDtos);
    }
}
