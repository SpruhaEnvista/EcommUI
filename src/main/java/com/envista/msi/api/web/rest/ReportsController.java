package com.envista.msi.api.web.rest;

import com.envista.msi.api.security.SecurityUtils;
import com.envista.msi.api.service.ReportsService;
import com.envista.msi.api.service.UserService;
import com.envista.msi.api.web.rest.dto.UserProfileDto;
import com.envista.msi.api.web.rest.dto.dashboard.DashboardsFilterCriteria;
import com.envista.msi.api.web.rest.dto.dashboard.netspend.NetSpendRequestDto;
import com.envista.msi.api.web.rest.dto.reports.*;
import com.envista.msi.api.web.rest.util.JSONUtil;
import org.json.JSONArray;
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

    @Inject
    private UserService userService;

    protected UserProfileDto getUserProfile(){
        UserProfileDto user = null;
        try {
            user = userService.getUserProfileByUserName(SecurityUtils.getCurrentUserLogin());
        } catch (Exception e) {
            //nothing
        }
        return user;
    }

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
    public ResponseEntity<List<SavedSchedReportsDto>> getSavedSchedReports(@RequestParam String userId,@RequestParam(required = false) String folderId){
        List<SavedSchedReportsDto> resultsList = reportsService.getSavedSchedReports(Long.parseLong(userId), (folderId == null ? 0 : Long.parseLong(folderId)));
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
    public ResponseEntity<JSONObject> getReportFormat(@RequestParam String rptId){
        try {
            JSONObject reprotFormatJson=new JSONObject();
            List<ReportFormatDto> reportFormats =reportsService.getReportFormat(Long.parseLong(rptId));
            if(reportFormats!=null && reportFormats.size()>0){
                JSONArray reportFormatJsonArr= new JSONArray();
                for (ReportFormatDto reportFormat : reportFormats) {
                    JSONObject jsonObject=new JSONObject();
                    jsonObject.put("typeId",reportFormat.getTypeId());
                    jsonObject.put("reportFormat",reportFormat.getReportFormat());
                    jsonObject.put("selected",reportFormat.getSelected());
                    reportFormatJsonArr.put(jsonObject);
                }
                reprotFormatJson.put("reportFormats",reportFormatJsonArr);
            }
            return new ResponseEntity<JSONObject>(reprotFormatJson, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<JSONObject>(new JSONObject(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @RequestMapping(value = "/dateoptions", method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.OPTIONS}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<JSONObject> getReportDateOptions(@RequestParam String rptId){
        try {
            JSONObject dateOptionJson=new JSONObject();
            List<ReportFormatDto> dateOptions =reportsService.getReportDateOptions(Long.parseLong(rptId));
            if(dateOptions!=null && dateOptions.size()>0){
                JSONArray dateOptionsJsonArr= new JSONArray();
                for (ReportFormatDto dateOption : dateOptions) {
                    JSONObject jsonObject=new JSONObject();
                    jsonObject.put("dateOptionId",dateOption.getRptDateOptionId());
                    jsonObject.put("dateCriteriaName",dateOption.getDateCriteriaName());
                    jsonObject.put("isDefault",dateOption.getIsDefault());
                    jsonObject.put("selected",dateOption.getSelected());
                    dateOptionsJsonArr.put(jsonObject);
                }
                dateOptionJson.put("dateOptions",dateOptionsJsonArr);
            }
            return new ResponseEntity<JSONObject>(dateOptionJson, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<JSONObject>(new JSONObject(), HttpStatus.INTERNAL_SERVER_ERROR);
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

    @RequestMapping(value = "/folder/create", method = {RequestMethod.POST, RequestMethod.OPTIONS}, produces = {MediaType.APPLICATION_JSON_VALUE},consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<ReportFolderDto> createReportFolder(@RequestBody ReportFolderDto reportFolderDto){
        ReportFolderDto reportFolder = null;
        if(reportFolderDto != null && reportFolderDto.getRptFolderName() != null){
            reportFolder = reportsService.createReportFolder(reportFolderDto,getUserProfile());
        }
        return new ResponseEntity<ReportFolderDto>(reportFolder,HttpStatus.OK);
    }

    @RequestMapping(value="/move/report", method = {RequestMethod.POST, RequestMethod.OPTIONS}, produces = {MediaType.APPLICATION_JSON_VALUE}, consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<ReportFolderDetailsDto> moveReportsToFolder(@RequestBody ReportFolderDetailsDto reportFolderDtlsDto) {
        ReportFolderDetailsDto rptFolderDetailsDto = null;
        if(reportFolderDtlsDto != null){
            rptFolderDetailsDto = reportsService.moveRptsToFolder(reportFolderDtlsDto);
        }
        return new ResponseEntity<ReportFolderDetailsDto>(rptFolderDetailsDto,HttpStatus.OK);
    }

    @RequestMapping(value = "/chageowner", method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.OPTIONS}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<SavedSchedReportsDto> changeOwnerBasedOnSSRptId(@RequestParam String currentUserName,@RequestParam Long currentUserId,
                                                                         @RequestParam String newUserName,@RequestParam Long newUserId,@RequestParam Long ssRptId){
        SavedSchedReportsDto savedSchedReportsDto = null;
        if(ssRptId != null && ssRptId > 0 && currentUserName != null && currentUserName.trim().length() > 0
                && currentUserId != null && currentUserId >0 && newUserName != null && newUserName.trim().length() > 0
                    && newUserId != null && newUserId > 0) {
            savedSchedReportsDto = reportsService.changeOwnerBasedonSSRptId(currentUserName,currentUserId,newUserName,newUserId,ssRptId);
        }
        return new ResponseEntity<SavedSchedReportsDto>(savedSchedReportsDto,HttpStatus.OK);
    }

    @RequestMapping(value = "/criteriacolumn", method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.OPTIONS}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<ReportColumnDto>> getReportCriteria(@RequestParam String userId, @RequestParam String rptId, @RequestParam String carrierIds){
        try {
            List<ReportColumnDto> reportCriteriaCols = reportsService.getReportCriteria(Long.parseLong(userId),Long.parseLong(rptId),carrierIds);
            return new ResponseEntity<List<ReportColumnDto>>(reportCriteriaCols, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<List<ReportColumnDto>>(new ArrayList<ReportColumnDto>(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/inclexclsortcolumn", method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.OPTIONS}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<ReportColumnDto>> getIncludeExcludeSortCol(@RequestParam String userId, @RequestParam String rptId, @RequestParam String carrierIds){
        try {
            List<ReportColumnDto> reportIncludeExclSortCols = reportsService.getIncludeExcludeSortCol(Long.parseLong(userId),Long.parseLong(rptId),carrierIds);
            return new ResponseEntity<List<ReportColumnDto>>(reportIncludeExclSortCols, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<List<ReportColumnDto>>(new ArrayList<ReportColumnDto>(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/locale", method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.OPTIONS}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<ReportCodeValueDto>> getReportLocaleLabel(@RequestParam String rptId){
        try {
            List<ReportCodeValueDto> reportLocaleLabels = reportsService.getReportLocaleLabel(Long.parseLong(rptId));
            return new ResponseEntity<List<ReportCodeValueDto>>(reportLocaleLabels, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<List<ReportCodeValueDto>>(new ArrayList<ReportCodeValueDto>(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/currency", method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.OPTIONS}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<ReportCodeValueDto>> getReportCurrencyLabel(@RequestParam String rptId){
        try {
            List<ReportCodeValueDto> reportCurrencyLabels = reportsService.getReportCurrencyLabel(Long.parseLong(rptId));
            return new ResponseEntity<List<ReportCodeValueDto>>(reportCurrencyLabels, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<List<ReportCodeValueDto>>(new ArrayList<ReportCodeValueDto>(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/weight", method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.OPTIONS}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<JSONObject> getReportWeightLabel(@RequestParam String rptId){
        try {
              JSONObject weightJson=new JSONObject();
              List<ReportCodeValueDto> reportWeightLabels = reportsService.getReportWeightLabel(Long.parseLong(rptId));
              if (reportWeightLabels != null && reportWeightLabels.size()>0){
                   JSONArray weightArray= new JSONArray();
                   for(ReportCodeValueDto dto: reportWeightLabels){
                       JSONObject dtoJson=new JSONObject();
                       dtoJson.put("value",dto.getCodeValue());
                       dtoJson.put("label",dto.getProperty1());
                       weightArray.put(dtoJson);
                   }
                   weightJson.put("reportWeight",weightArray);
              }
              return new ResponseEntity<JSONObject>(weightJson, HttpStatus.OK);
        } catch (Exception e) {
              return new ResponseEntity<JSONObject>(new JSONObject(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @RequestMapping(value = "/controlnumber", method = {RequestMethod.POST, RequestMethod.OPTIONS}, produces = {MediaType.APPLICATION_JSON_VALUE},consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<JSONObject> getControlNumber(@RequestBody ReportFormatDto lookupBntDto) throws JSONException{
        try {
            JSONObject ctrlNoJson=new JSONObject();
            String customers = "";
            String customerIds = lookupBntDto.getCustomerIds();
            JSONArray jsonArray = new JSONArray(customerIds); // json
            if(jsonArray!=null) {
                    for (int i = 0; i < jsonArray.length(); i++) {
                        String customerId = String.valueOf(jsonArray.get(i));
                        if(i>0){
                            customers = customers +",";
                        }
                        customers = customers + customerId;
                    }
            }
            if(customers!=null && customers.trim().length()>0) {
                List<ReportFormatDto> reportControlNo = reportsService.getControlNumber(customers,lookupBntDto.getPayRunNo(),lookupBntDto.getCheckNo());
                if (reportControlNo != null && reportControlNo.size() > 0) {
                    JSONArray ctrlNoArray = new JSONArray();
                    for (ReportFormatDto dto : reportControlNo) {
                        JSONObject dtoJson = new JSONObject();
                        dtoJson.put("payRunNumber", dto.getControlNumber());
                        ctrlNoArray.put(dtoJson);
                    }
                    ctrlNoJson.put("controlNumber", ctrlNoArray);
                }
            }
            return new ResponseEntity<JSONObject>(ctrlNoJson, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<JSONObject>(new JSONObject(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @RequestMapping(value = "/folders", method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.OPTIONS}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<JSONArray> getReportFolder(@RequestParam String userId){
        try {
            JSONArray folderArray= new JSONArray();
            List<ReportFolderDto> folderDtos = reportsService.getReportFolder(Long.parseLong(userId));
            if (folderDtos != null && folderDtos.size()>0){
                for(ReportFolderDto dto: folderDtos){
                    JSONObject dtoJson=new JSONObject();
                    dtoJson.put("folderId",dto.getRptFolderId());
                    dtoJson.put("folderName",dto.getRptFolderName());
                    dtoJson.put("parentId",dto.getParentId());
                    folderArray.put(dtoJson);
                }
            }
            return new ResponseEntity<JSONArray>(folderArray, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<JSONArray>(new JSONArray(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @RequestMapping(value = "/ftpserver", method = {RequestMethod.POST, RequestMethod.OPTIONS}, produces = {MediaType.APPLICATION_JSON_VALUE},consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<JSONObject> getReportFTPServer(@RequestBody ReportFTPServerDto ftpServerDto) throws Exception{
        try {
            JSONObject ftpServerJson=null;
            String customers = "";
            String shipperGroups="";
            String shipers="";
            String customerIds = ftpServerDto.getCustomerIds();
            if(customerIds!=null) {
                JSONArray jsonArray = new JSONArray(customerIds); // json
                if (jsonArray != null) {
                    for (int i = 0; i < jsonArray.length(); i++) {
                        String customerId = String.valueOf(jsonArray.get(i));
                        if (i > 0)
                            customers = customers + ",";
                        customers = customers + customerId;
                    }
                }
            }
            String shipperGroupIds = ftpServerDto.getShipperGroupIds();
            if(shipperGroupIds!=null) {
                JSONArray jsonArray = new JSONArray(shipperGroupIds); // json
                if (jsonArray != null) {
                    for (int i = 0; i < jsonArray.length(); i++) {
                        String shipperGroupId = String.valueOf(jsonArray.get(i));
                        if (i > 0)
                            shipperGroups = shipperGroups + ",";
                        shipperGroups = shipperGroups + shipperGroupId;
                    }
                }
            }
            String shipperIds = ftpServerDto.getShipersIds();
            if(shipperIds!=null) {
                JSONArray jsonArray = new JSONArray(shipperIds); // json
                if (jsonArray != null) {
                    for (int i = 0; i < jsonArray.length(); i++) {
                        String shipperId = String.valueOf(jsonArray.get(i));
                        if (i > 0) {
                            shipers = shipers + ",";
                        }
                        shipers = shipers + shipperId;
                    }
                }
            }
            JSONObject asJson = reportsService.getReportFTPServer(customers,shipperGroups,shipers,ftpServerDto.getRptId());
            ftpServerJson = asJson != null ? asJson : new JSONObject();
            return new ResponseEntity<JSONObject>(ftpServerJson, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<JSONObject>(new JSONObject(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }




    @RequestMapping(value="/userListByRptId", method = {RequestMethod.GET,RequestMethod.OPTIONS}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<ReportUserListByRptIdDto>> getUsersListByRptId(@RequestParam Long rptId){
        List<ReportUserListByRptIdDto> rptUsersList = reportsService.getUserListByRptId(rptId);
        return new ResponseEntity<List<ReportUserListByRptIdDto>>(rptUsersList,HttpStatus.OK);
    }

}
