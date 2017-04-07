package com.envista.msi.api.web.rest;

import com.envista.msi.api.service.ReportsService;
import com.envista.msi.api.web.rest.dto.reports.*;
import com.envista.msi.api.web.rest.util.JSONUtil;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
/**
 * Created by Sreenivas on 2/17/2017.
 */

@RestController
@RequestMapping("/api/reports")
public class ReportsController {

    @Inject
    private ReportsService reportsService;

    private static final String APPLICATION_PDF = "application/text";

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
    public ResponseEntity<List<ReportResultsUsersListDto>> getUsersList(@RequestParam(required = false) String filter){
        String userName = "";
        if(filter != null){
            filter = filter.trim();
            String filters[] = filter.split(":");
            if( filters[1] != null)
                userName = filters[1].trim().toLowerCase();
        }
        List<ReportResultsUsersListDto> usersList = reportsService.getUsersList(userName);
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
    @RequestMapping(value = "/getModesReport", method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.OPTIONS}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<ReportModesDto>> getReportForModes(@RequestParam String userId){
        try {
            List<ReportModesDto> reportModeDto = reportsService.getReportForModes(Long.parseLong(userId));
            return new ResponseEntity<List<ReportModesDto>>(reportModeDto, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<List<ReportModesDto>>(new ArrayList<ReportModesDto>(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @RequestMapping(value = "/customercarrierlist", method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.OPTIONS}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<JSONObject> getReportCustomers(@RequestParam String rptId,@RequestParam String userId){
        JSONObject customCarrierJson=null;
        try {
            JSONObject asJson =loadCustomerCarrierJson(Long.parseLong(userId),Long.parseLong(rptId));
            customCarrierJson = asJson != null ? asJson : new JSONObject();
        } catch (Exception e) {
            return new ResponseEntity<JSONObject>(new JSONObject(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<JSONObject>(customCarrierJson, HttpStatus.OK);
    }
    public JSONObject loadCustomerCarrierJson(Long userId,Long rptId) throws JSONException{
        List<ReportCustomerCarrierDto> customerList=reportsService.getReportCustomers(rptId,userId);
        List<ReportCustomerCarrierDto> carrierList=reportsService.getReportCarrier(rptId,userId);
        JSONObject customerCarrierJson=new JSONObject();
        if(customerList!=null && customerList.size()>0) {
            customerCarrierJson.put("customerList", JSONUtil.customerHierarchyJson(reportsService.getCustomerHierarchyObject(customerList)));
        }
        if(carrierList!=null && carrierList.size()>0) {
            customerCarrierJson.put("carrierList",JSONUtil.carriersJson(carrierList));
        }
        return customerCarrierJson;
    }
    @RequestMapping(value = "/format", method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.OPTIONS}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<ReportFormatDto>> getReportFormat(@RequestParam String rptId){
        try {
            List<ReportFormatDto> reportFormats =reportsService.getReportFormat(Long.parseLong(rptId));
            return new ResponseEntity<List<ReportFormatDto>>(reportFormats, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<List<ReportFormatDto>>(new ArrayList<ReportFormatDto>(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/filedownload", method = RequestMethod.GET, produces = APPLICATION_PDF)
    public @ResponseBody void downloadFile(HttpServletResponse response,@RequestParam String crGeneratedRptId) throws IOException {

        File file = reportsService.getReportFileDetails (Long.parseLong(crGeneratedRptId));
        InputStream in = new FileInputStream(file);

        response.setContentType(APPLICATION_PDF);
        response.setHeader("Content-Disposition", "attachment; filename=" + file.getName());
        response.setHeader("Content-Length", String.valueOf(file.length()));
        FileCopyUtils.copy(in, response.getOutputStream());
    }

    @RequestMapping(value = "/saveSchedReport", method = {RequestMethod.POST, RequestMethod.OPTIONS}, produces = {MediaType.APPLICATION_JSON_VALUE},consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<SavedSchedReportDto> saveSchedReport(@RequestBody SavedSchedReportDto savedSchedReportDto){
        SavedSchedReportDto savedDto = reportsService.saveSchedReport(savedSchedReportDto);
        return new ResponseEntity<SavedSchedReportDto>(savedDto, HttpStatus.OK);
    }
    @RequestMapping(value = "/saveSchedPacketReport", method = {RequestMethod.POST, RequestMethod.OPTIONS}, produces = {MediaType.APPLICATION_JSON_VALUE},consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<SavedSchedReportDto> saveSchedPacketReport(@RequestBody SavedSchedReportDto savedSchedReportDto){
        SavedSchedReportDto savedDto = reportsService.saveSchedPacketReport(savedSchedReportDto);
        return new ResponseEntity<SavedSchedReportDto>(savedDto, HttpStatus.OK);
    }
}
