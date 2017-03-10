package com.envista.msi.api.web.rest;

import com.envista.msi.api.service.ReportsService;
import com.envista.msi.api.web.rest.dto.UserProfileDto;
import com.envista.msi.api.web.rest.dto.dashboard.DashboardsFilterCriteria;
import com.envista.msi.api.web.rest.dto.dashboard.netspend.NetSpendRequestDto;
import com.envista.msi.api.web.rest.dto.reports.ReportResultsDto;
import com.envista.msi.api.web.rest.dto.reports.ReportResultsUsersListDto;
import com.envista.msi.api.web.rest.dto.reports.SavedSchedReportsDto;
import com.envista.msi.api.web.rest.dto.reports.UpdateSavedSchedReportDto;
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

    @RequestMapping(value = "/results/reportslist/{userId}", method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.OPTIONS}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<ReportResultsDto>> getReportResults(@PathVariable String userId){
        List<ReportResultsDto> resultsList = reportsService.getReportResults(Long.parseLong(userId));
        return new ResponseEntity<List<ReportResultsDto>>(resultsList, HttpStatus.OK);
    }
    @RequestMapping(value = "/results/updateexpirydate", method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.OPTIONS}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<ReportResultsDto> updateExpiryDate(@RequestParam Long generatedRptId, @RequestParam String expiryDate){
        ReportResultsDto reportResultsDto = reportsService.updateExpiryDate(generatedRptId, expiryDate );
        return new ResponseEntity<ReportResultsDto>(reportResultsDto, HttpStatus.OK);
    }
    @RequestMapping(value = "/results/userslist", method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.OPTIONS}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<ReportResultsUsersListDto>> getUsersList(){
        List<ReportResultsUsersListDto> usersList = reportsService.getUsersList();
        return new ResponseEntity<List<ReportResultsUsersListDto>>(usersList, HttpStatus.OK);
    }
    @RequestMapping(value = "/results/delete", method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.OPTIONS}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<ReportResultsDto> deleteReportInResult(@RequestParam String generatedRptId, @RequestParam String userId, @RequestParam String userName){
        try {
            ReportResultsDto reportResultsDto = reportsService.deleteReportInResults(Long.parseLong(generatedRptId),Long.parseLong(userId), userName);
            return new ResponseEntity<ReportResultsDto>(reportResultsDto, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<ReportResultsDto>(new ReportResultsDto(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @RequestMapping(value = "/savedschedreports", method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.OPTIONS}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<SavedSchedReportsDto>> getSavedSchedReports(@RequestParam String userId){
        List<SavedSchedReportsDto> resultsList = reportsService.getSavedSchedReports(Long.parseLong(userId));
        return new ResponseEntity<List<SavedSchedReportsDto>>(resultsList, HttpStatus.OK);
    }
    @RequestMapping(value = "/updatesavedschedreport", method = {RequestMethod.POST, RequestMethod.OPTIONS}, produces = {MediaType.APPLICATION_JSON_VALUE},consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<UpdateSavedSchedReportDto> updateSavedSchedReport(@RequestBody UpdateSavedSchedReportDto updateSavedSchedReportDto){
        UpdateSavedSchedReportDto updateDto = reportsService.runSavedSchedReport(updateSavedSchedReportDto);
        return new ResponseEntity<UpdateSavedSchedReportDto>(updateDto, HttpStatus.OK);
    }
    @RequestMapping(value = "/runsavedschedreport", method = {RequestMethod.POST, RequestMethod.OPTIONS}, produces = {MediaType.APPLICATION_JSON_VALUE},consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<UpdateSavedSchedReportDto> runSavedSchedReport(@RequestBody UpdateSavedSchedReportDto updateSavedSchedReportDto){
        UpdateSavedSchedReportDto updateDto = reportsService.runSavedSchedReport(updateSavedSchedReportDto);
        return new ResponseEntity<UpdateSavedSchedReportDto>(updateDto, HttpStatus.OK);
    }
    @RequestMapping(value = "/copyfromreportresults", method = {RequestMethod.POST, RequestMethod.OPTIONS}, produces = {MediaType.APPLICATION_JSON_VALUE},consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<UpdateSavedSchedReportDto> saveFromReportResults(@RequestBody UpdateSavedSchedReportDto updateSavedSchedReportDto){
        UpdateSavedSchedReportDto updateDto = reportsService.saveFromReportResults(updateSavedSchedReportDto);
        return new ResponseEntity<UpdateSavedSchedReportDto>(updateDto, HttpStatus.OK);
    }
    @RequestMapping(value = "/results/pushtouser", method = {RequestMethod.POST, RequestMethod.OPTIONS}, produces = {MediaType.APPLICATION_JSON_VALUE},consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<ReportResultsUsersListDto> pushToUser(@RequestBody List<ReportResultsUsersListDto> reportResultsUsersListDto){
        ReportResultsUsersListDto updateDto = reportsService.pushToUser(reportResultsUsersListDto);
        return new ResponseEntity<ReportResultsUsersListDto>(updateDto, HttpStatus.OK);
    }
}
