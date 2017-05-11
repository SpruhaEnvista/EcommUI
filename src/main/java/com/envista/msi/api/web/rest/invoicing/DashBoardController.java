package com.envista.msi.api.web.rest.invoicing;

import com.envista.msi.api.service.invoicing.DashBoardService;
import com.envista.msi.api.web.rest.dto.invoicing.DashBoardDto;
import org.json.JSONException;
import org.json.JSONObject;
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
 * Created by KRISHNAREDDYM on 5/8/2017.
 */
@RestController
@RequestMapping("/api/dashBoard")
public class DashBoardController {

    private final Logger log = LoggerFactory.getLogger(DashBoardController.class);

    @Inject
    private DashBoardService service;

    /**
     * HTTP GET - Get all Voices
     */
    @RequestMapping(value = "/getDashBoardInfo", params = {"fromDate", "toDate","actionType"}, produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    public ResponseEntity<List<DashBoardDto>> getDashBoardInfoByBusinessPartner(@RequestParam String fromDate, @RequestParam String toDate, @RequestParam String actionType) throws JSONException {
        log.info("***getDashBoardInfo method started****");

        List<DashBoardDto> dashboardInfoList = service.getDashBoardInfo(fromDate, toDate, actionType);

        log.info("***getDashBoardInfo json***==== " + dashboardInfoList);
        return new ResponseEntity<List<DashBoardDto>>(dashboardInfoList, HttpStatus.OK);
    }
}
