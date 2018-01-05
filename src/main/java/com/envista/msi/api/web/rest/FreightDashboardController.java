package com.envista.msi.api.web.rest;

import com.envista.msi.api.service.DashboardsService;
import com.envista.msi.api.web.rest.dto.dashboard.DashboardsFilterCriteria;
import com.envista.msi.api.web.rest.response.CommonResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Sujit kumar on 02/01/2018.
 */

@RestController
@RequestMapping("/api/dashboards/frt")
public class FreightDashboardController extends DashboardBaseController{
    @Autowired
    private DashboardsService dashboardsService;

    @RequestMapping(value = "/frtSaving", method = {RequestMethod.GET}, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CommonResponse> getFreightSavings(){
        DashboardsFilterCriteria filter = populateDashboardFilterCriteria(dashboardsService.getUserAppliedFilter(getUserProfile().getUserId()));
        return prepareCommonResponseEntity(dashboardsService.getFreightAuditSavings(filter));
    }

    @RequestMapping(value = "/frtSavingsByAjdRsn", method = {RequestMethod.GET}, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CommonResponse> getFreightSavingsByCarrierByAdjustmentReason(){
        DashboardsFilterCriteria filter = populateDashboardFilterCriteria(dashboardsService.getUserAppliedFilter(getUserProfile().getUserId()));
        return prepareCommonResponseEntity(dashboardsService.getFreightSavingsByCarrierByAdjustmentReason(filter));
    }
}
