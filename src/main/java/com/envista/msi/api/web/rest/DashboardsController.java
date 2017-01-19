package com.envista.msi.api.web.rest;

import com.envista.msi.api.service.DashboardsService;
import com.envista.msi.api.web.rest.dto.DashboardsDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

/**
 * Created by Sarvesh on 1/18/2017.
 */

@RestController
@RequestMapping("/api/dashboards")
public class DashboardsController {

    @Autowired
    HttpSession httpSession;

    @Autowired
    private DashboardsService dashboardsService;


    @RequestMapping(value = "/appliedFilter", method = { RequestMethod.GET}, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DashboardsDto> getAppliedFilterDetails(@RequestParam(value = "userId" , required = true) String userId  ){

        try {
            return new ResponseEntity<DashboardsDto>( dashboardsService.getUserAppliedFilter(Long.parseLong(userId)) , HttpStatus.OK );
        } catch (Exception e) {
            return new ResponseEntity<DashboardsDto>( new DashboardsDto() ,HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

}
