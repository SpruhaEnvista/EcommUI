package com.envista.msi.api.web.rest.glom;

import com.envista.msi.api.service.glom.DataCriteriaService;
import com.envista.msi.api.web.rest.dto.glom.DataCriteriaDto;
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
import java.util.List;

/**
 * Created by KRISHNAREDDYM on 11/29/2017.
 */
@RestController
@RequestMapping("/api/dataCriteria")
public class DataCriteriaController {

    private final Logger log = LoggerFactory.getLogger(DataObjectController.class);

    @Inject
    private DataCriteriaService service;


    /**
     * HTTP GET -  get criteria by data object id
     */
    @RequestMapping(value = "/getByDataObjectId", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    public ResponseEntity<List<DataCriteriaDto>> findById(@RequestParam(required = false, defaultValue = "0") Long dataObjectId) {


        return new ResponseEntity<List<DataCriteriaDto>>(service.findById(dataObjectId), HttpStatus.OK);
    }
}
