package com.envista.msi.api.web.rest.invoicing;

import com.envista.msi.api.service.invoicing.CreditResponseService;
import com.envista.msi.api.service.invoicing.DashBoardService;
import com.envista.msi.api.web.rest.dto.invoicing.CreditResponseDto;
import com.envista.msi.api.web.rest.dto.invoicing.DashBoardDto;
import com.envista.msi.api.web.rest.util.FileOperations;
import org.json.JSONException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
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
    public ResponseEntity<Integer> getPendingCredits(@RequestParam String fromDate, @RequestParam String toDate, @RequestParam String actionType) throws JSONException {
        log.info("***getPendingCredits method started****");

        int pendingCredits = service.getPendingCredits(fromDate, toDate, actionType);

        log.info("***getPendingCredits count***==== " + pendingCredits);
        return new ResponseEntity<Integer>(pendingCredits, HttpStatus.OK);
    }

    /**
     * HTTP GET - Get all Voices
     */
    @RequestMapping(value = "/closeWeek", params = {"omitFlag", "reviewFlag"}, method = RequestMethod.PUT)
    public ResponseEntity<Integer> closeCurrentWeek(@RequestParam String omitFlag, @RequestParam String reviewFlag) {
        log.info("***closeCurrentWeek method started****");

        int updatedRows = service.closeCurrentWeekCredits(omitFlag, reviewFlag);

        return new ResponseEntity<Integer>(updatedRows, HttpStatus.OK);
    }

    @RequestMapping(value = "/uploadCreditRespInfo", method = RequestMethod.POST)
    public ResponseEntity<String> UploadCreditResp(@RequestParam("files") MultipartFile[] file, HttpServletRequest request) throws IOException {
        log.info("***UploadCreditResp method started***");
        try {
            MultipartHttpServletRequest mRequest;
            mRequest = (MultipartHttpServletRequest) request;
            List<MultipartFile> files = mRequest.getFiles("file");
            List<CreditResponseDto> dtos = fileOperations.customOmitFileUploadOperation(files);
            creditResponseService.insert(dtos);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<String>("file(s) uploaded Successfully", HttpStatus.OK);
    }
}
