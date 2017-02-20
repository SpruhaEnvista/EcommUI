package com.envista.msi.api.web.rest;

import com.envista.msi.api.service.ReportsService;
import com.envista.msi.api.web.rest.dto.UserProfileDto;
import com.envista.msi.api.web.rest.dto.dashboard.DashboardsFilterCriteria;
import com.envista.msi.api.web.rest.dto.reports.ReportResultsDto;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import javax.websocket.server.PathParam;
import java.util.Date;
import java.util.List;

/**
 * Created by Sreenivas on 2/17/2017.
 */

@RestController
@RequestMapping("/api/reports")
public class ReportsController {

    @Inject
    private ReportsService reportsService;

    @RequestMapping(value = "/results/{userId}", method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.OPTIONS}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<ReportResultsDto>> getReportResults(@PathVariable String userId){
        List<ReportResultsDto> resultsList = reportsService.getReportResults(Long.parseLong(userId));
        return new ResponseEntity<List<ReportResultsDto>>(resultsList, HttpStatus.OK);
    }
    @RequestMapping(value = "/results/updateexpirydate", method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.OPTIONS}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<ReportResultsDto> getReportResults(@RequestParam Long generatedRptId, @RequestParam String expiryDate){
        ReportResultsDto reportResultsDto = reportsService.updateExpiryDate(generatedRptId, expiryDate );
        return new ResponseEntity<ReportResultsDto>(reportResultsDto, HttpStatus.OK);
    }
    @RequestMapping(value = "/userslist/{userId}", method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.OPTIONS}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<ReportResultsDto>> getUsersList(@PathVariable String userId){
        List<ReportResultsDto> resultsList = reportsService.getReportResults(Long.parseLong(userId));
        return new ResponseEntity<List<ReportResultsDto>>(resultsList, HttpStatus.OK);
    }
}
