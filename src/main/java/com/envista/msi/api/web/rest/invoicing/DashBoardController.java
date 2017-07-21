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
    public ResponseEntity<List<DashBoardDto>> getDashBoardInfo(@RequestParam String fromDate, @RequestParam String toDate, @RequestParam String actionType) throws JSONException {
        log.info("***getDashBoardInfo method started****");

        List<DashBoardDto> dashboardInfoList = service.getDashBoardInfo(fromDate, toDate, actionType);

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
        int updatedRows = service.closeCurrentWeekCredits(myJSON.getString("ebillManifestIds"), myJSON.getString("action"), myJSON.getLong("weekEndId"));

        return new ResponseEntity<Integer>(updatedRows, HttpStatus.OK);
    }

    @RequestMapping(value = "/uploadCreditRespInfo", method = RequestMethod.POST)
    public ResponseEntity<String> UploadCreditResp(@RequestParam("files") MultipartFile[] file, @RequestParam("weekEndId") Long weekEndId, HttpServletRequest request) throws IOException {
        log.info("***UploadCreditResp method started***");
        try {
            MultipartHttpServletRequest mRequest;
            mRequest = (MultipartHttpServletRequest) request;
            List<MultipartFile> files = mRequest.getFiles("file");
            FileInfoDto fileInfoDto = service.insertFileInfo(files.get(0).getOriginalFilename(), weekEndId);
            List<CreditResponseDto> dtos = fileOperations.customOmitFileUploadOperation(files.get(0), fileInfoDto != null ? fileInfoDto.getId() : 0L);
            creditResponseService.insert(dtos);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<String>("file(s) uploaded Successfully", HttpStatus.OK);
    }

    @RequestMapping(value = "/testUpload", method = RequestMethod.POST)
    public ResponseEntity<String> testUpload() throws IOException {
        log.info("***UploadCreditResp method started***");
        try {

            List<CreditResponseDto> dtos = fileOperations.customOmitFileUploadOperation(null, 0L);
            creditResponseService.insert(dtos);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<String>("file(s) uploaded Successfully", HttpStatus.OK);
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
    @RequestMapping(value = "/scrubCredits", params = {"weekEndId"}, method = RequestMethod.PUT)
    public ResponseEntity<Integer> ScrubCredits(@RequestParam Long weekEndId) throws JSONException {
        log.info("***ScrubCredits method started****");

        int count = service.scrubCredits(weekEndId);


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

}
