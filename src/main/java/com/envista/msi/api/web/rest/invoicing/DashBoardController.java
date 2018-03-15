package com.envista.msi.api.web.rest.invoicing;

import com.envista.msi.api.service.invoicing.CreditResponseService;
import com.envista.msi.api.service.invoicing.DashBoardService;
import com.envista.msi.api.service.invoicing.WeekEndService;
import com.envista.msi.api.web.rest.dto.invoicing.*;
import com.envista.msi.api.web.rest.util.FileOperations;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Created by KRISHNAREDDYM on 5/8/2017.
 */
@RestController
@RequestMapping("/api/dashBoard")
public class DashBoardController {

    private final Logger log = LoggerFactory.getLogger(DashBoardController.class);

    @Inject
    private DashBoardService service;

    @Inject
    private CreditResponseService creditResponseService;

    @Autowired
    FileOperations fileOperations;


    @Inject
    private WeekEndService weekEndService;

    /**
     * HTTP GET - Get all Voices
     */
    @RequestMapping(value = "/getDashBoardInfo", params = {"fromDate", "toDate","actionType"}, produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    public ResponseEntity<List<DashBoardDto>> getDashBoardInfo(@RequestParam String fromDate, @RequestParam String toDate, @RequestParam String actionType
            , @RequestParam(required = false, defaultValue = "null") String sort) throws JSONException {
        log.info("***getDashBoardInfo method started****");

        List<DashBoardDto> dashboardInfoList = service.getDashBoardInfo(fromDate, toDate, actionType, sort);

        log.info("***getDashBoardInfo json***==== " + dashboardInfoList);
        return new ResponseEntity<List<DashBoardDto>>(dashboardInfoList, HttpStatus.OK);
    }

    /**
     * HTTP GET - Get all Voices
     */
    @RequestMapping(value = "/getPendingCredits", params = {"fromDate", "toDate", "actionType"}, method = RequestMethod.GET)
    public ResponseEntity<JSONObject> getPendingCredits(@RequestParam String fromDate, @RequestParam String toDate, @RequestParam String actionType) throws JSONException {
        log.info("***getPendingCredits method started****");

        JSONObject jsonObject = new JSONObject();

        int count = service.getPendingCreditsCount(fromDate, toDate, actionType);
        jsonObject.put("pendingCreditsCount", count);
       /* if (dtos != null) {
            jsonObject.put("pendingCreditsCount", dtos.size());
*//*            StringBuilder builder = new StringBuilder();
            int count = 0;
            for (DashBoardDto dto : dtos) {
                if (count != 0)
                    builder.append(",");

                builder.append(dto.getEbillManifestId());
                count++;
            }*//*
            jsonObject.put("pendingEbillIds", "");
        } else {
            jsonObject.put("pendingCreditsCount", 0);
            jsonObject.put("pendingEbillIds", "");
        }*/

        log.info("***ge tPendingCredits count***==== " + jsonObject);
        return new ResponseEntity<JSONObject>(jsonObject, HttpStatus.OK);
    }

    /**
     * This method close the current week and inserts new week
     * @param myJSON
     * @return Integer
     * @throws JSONException
     */
    @RequestMapping(value = "/closeWeek",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    public ResponseEntity<Integer> closeCurrentWeek(@RequestBody JSONObject myJSON) throws JSONException{
        log.info("***closeCurrentWeek method started****");
        int updatedRows = service.closeCurrentWeekCredits(myJSON.getString("userName"), myJSON.getString("action"), myJSON.getLong("weekEndId"));

        return new ResponseEntity<Integer>(updatedRows, HttpStatus.OK);
    }

    @RequestMapping(value = "/uploadCreditRespInfo", method = RequestMethod.POST)
    public ResponseEntity<JSONObject> UploadCreditResp(@RequestParam("files") MultipartFile[] file, @RequestParam("weekEndId") Long weekEndId, @RequestParam("userName") String userName, HttpServletRequest request, @RequestParam("fileTypeId") Long fileTypeId, @RequestParam("fileType") String fileType) throws IOException {
        log.info("***UploadCreditResp method started***");
        String responseStr="";
        JSONObject jsonObject = new JSONObject();
        try {
            MultipartHttpServletRequest mRequest;
            mRequest = (MultipartHttpServletRequest) request;
            List<MultipartFile> files = mRequest.getFiles("file");
            Map<String,Object> resObj=null;
            List<CreditResponseDto> dtos=null;
            resObj= fileOperations.customOmitFileUploadOperation(files.get(0), 0L,fileType,fileTypeId);
            if(resObj != null && resObj.size() >0 && resObj.get("dtos") != null){
                dtos=(List<CreditResponseDto>)resObj.get("dtos");
                    FileInfoDto fileInfoDto = service.insertFileInfo(files.get(0).getOriginalFilename(), weekEndId, userName,fileTypeId);
                int numDtos = dtos.size();
                if (numDtos > 100000) {
                    int numLack = (int) numDtos / 100000;
                    int startInd = 0;
                    int endInd = 100000;
                    for (int i = 1; i <= numLack; i++) {
                        endInd = 100000 * i;
                        List<CreditResponseDto> subDTOList = dtos.subList(startInd, endInd);
                        creditResponseService.insert(subDTOList, fileInfoDto.getId());
                        startInd = endInd;
                    }
                    if (endInd != numDtos) {
                        List<CreditResponseDto> subDTOList = dtos.subList( endInd,  numDtos);
                        creditResponseService.insert(subDTOList, fileInfoDto.getId());
                    }
                }else{
                    creditResponseService.insert(dtos, fileInfoDto.getId());
                }
                    responseStr="File uploaded Successfully.";
            }else if(resObj != null && resObj.size() >0 && resObj.get("error") != null && !resObj.get("error").equals("")){
                responseStr = (String)resObj.get("error");
            }
            jsonObject.put("message",responseStr);


        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<JSONObject>(jsonObject, HttpStatus.OK);
                //new ResponseEntity<String>(responseStr, HttpStatus.OK);
    }



    /**
     * HTTP GET - Get Current Week End Info
     */
    @RequestMapping(value = "/getCurrentWeekEnd", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    public ResponseEntity<WeekEndDto> getCurrentWeekEnd() throws JSONException {
        log.info("***getCurrentWeekEnd method started****");

        WeekEndDto dto = weekEndService.getCurrentWeekEndDate();

        log.info("***getCurrentWeekEnd json***==== " + dto);
        return new ResponseEntity<WeekEndDto>(dto, HttpStatus.OK);
    }

    /**
     * HTTP GET - Get Current Week End Info
     */
    @RequestMapping(value = "/scrubCredits", params = {"weekEndId","userName"}, method = RequestMethod.PUT)
    public ResponseEntity<Integer> ScrubCredits(@RequestParam Long weekEndId,@RequestParam String userName) throws JSONException {
        log.info("***ScrubCredits method started****");

        int count = service.scrubCredits(weekEndId, userName);


        return new ResponseEntity<Integer>(count, HttpStatus.OK);
    }

    /**
     * HTTP GET - Get week status Info
     */
    @RequestMapping(value = "/getWeekStatus", params = {"fromDate", "toDate"}, produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    public ResponseEntity<WeekStatusDto> getWeekStatusInfo(@RequestParam String fromDate, @RequestParam String toDate) throws JSONException {
        log.info("***getWeekStatusInfo method started****");

        WeekStatusDto dto = service.getWeekStatusInfo(fromDate, toDate);

        log.info("***getWeekStatusInfo json***==== " + dto);
        return new ResponseEntity<WeekStatusDto>(dto, HttpStatus.OK);
    }

    @RequestMapping(value = "/getFileDefTypesList",produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    public ResponseEntity<List<FileDefDto>> getFileDefTypesListInfo() throws JSONException {
        log.info("***getFileDefTypesListInfo method started****");

        List<FileDefDto> dtos = service.getFileDefTypesListInfo();

        log.info("***getFileDefTypesListInfo json***==== " + dtos);
        return new ResponseEntity<List<FileDefDto>>(dtos, HttpStatus.OK);
    }

    @RequestMapping(value = "/getFileInfoList", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    public ResponseEntity<List<FileInfoDto>> getFileInfoList(@RequestParam("weekEndId") Long weekEndId) throws JSONException {
        log.info("***getFileDefTypesListInfo method started****");

        List<FileInfoDto> dtos = service.getFileInfoByWeekEndId(weekEndId);

        log.info("***getFileInfoList json***==== " + dtos);
        return new ResponseEntity<List<FileInfoDto>>(dtos, HttpStatus.OK);
    }

    @RequestMapping(value = "/getScrubCreditsHis", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    public ResponseEntity<List<ScrubCreditsHisDto>> getScrubCreditsHistory(@RequestParam("weekEndId") Long weekEndId) throws JSONException {
        log.info("***getFileDefTypesListInfo method started****");

        List<ScrubCreditsHisDto> dtos = service.getScrubCreditsHistory(weekEndId);

        log.info("***getScrubCreditsHis json***==== " + dtos);
        return new ResponseEntity<List<ScrubCreditsHisDto>>(dtos, HttpStatus.OK);
    }

    @RequestMapping(value = "/getWeekStatusByWeekendDate", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    public ResponseEntity<WeekEndDto> getWeekStatusByWeekendDate(@RequestParam String weekEndDate) throws JSONException {
        log.info("***getWeekStatusByWeekendDate method started****");

        WeekEndDto dto = weekEndService.findByWeekEndDate(weekEndDate);

        log.info("***getWeekStatusByWeekendDate json***==== " + dto);
        return new ResponseEntity<WeekEndDto>(dto, HttpStatus.OK);
    }
    @RequestMapping(value = "/delete", params = {"fileInfoId"}, method = RequestMethod.PUT)
    public ResponseEntity<Integer> delete(@RequestParam Long fileInfoId) {

        int count = service.delete(fileInfoId);

        return new ResponseEntity<Integer>(count, HttpStatus.OK);
    }
}
