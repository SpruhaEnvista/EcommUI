package com.envista.msi.api.web.rest;

import com.envista.msi.api.security.SecurityUtils;
import com.envista.msi.api.service.RatingService;
import com.envista.msi.api.service.UserService;
import com.envista.msi.api.web.rest.dto.UserProfileDto;
import com.envista.msi.api.web.rest.dto.reports.ReportCustomerCarrierDto;
import com.envista.msi.api.web.rest.dto.rtr.EventLogDto;
import com.envista.msi.api.web.rest.dto.rtr.StoreRatingDetailsDto;
import com.envista.msi.api.web.rest.util.DateUtil;
import com.envista.msi.api.web.rest.dto.rtr.ViewLogDto;
import com.envista.msi.api.web.rest.util.JSONUtil;
import org.json.JSONArray;
import com.envista.msi.api.web.rest.util.audit.parcel.ParcelAuditConstant;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;


import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

/*
 * Created by Srikanth Punna on 06/19/2018
 * */

@RestController
@RequestMapping("/api/rates")
public class RatingController {

    @Inject
    private UserService userService;

    @Inject
    private RatingService ratingService;

    @RequestMapping(value = "/carrierlist", method = {RequestMethod.GET}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<JSONObject> getRateCarriersList(@RequestParam String userId, @RequestParam String customerIds) {
        try {
            List<ReportCustomerCarrierDto> carriersList = ratingService.getRateCarrierList(Long.parseLong(userId), customerIds);
            JSONObject carrierJSON = new JSONObject();
            if (carriersList != null)
                carrierJSON.put("rateCarriersList", JSONUtil.carriersJson(carriersList));
            return new ResponseEntity<JSONObject>(carrierJSON, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<JSONObject>(new JSONObject(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/getEventLog", method = {RequestMethod.GET}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<JSONObject> getEventLog(@RequestParam Long jobId) {

        try {
            List<EventLogDto> eventLogList = ratingService.getEventLog(jobId);
            JSONObject eventLogJson = new JSONObject();
            if (eventLogList != null)
                eventLogJson.put("eventLogs", new JSONArray(eventLogList));
            return new ResponseEntity<JSONObject>(eventLogJson, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<JSONObject>(new JSONObject(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @RequestMapping(value = "/storeRatingDetailsList", method = {RequestMethod.POST, RequestMethod.OPTIONS}, produces = {MediaType.APPLICATION_JSON_VALUE}, consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<StoreRatingDetailsDto>> storeRatingDetailsList(@RequestBody StoreRatingDetailsDto storeRatingDetailsDto) {
        List<StoreRatingDetailsDto> ratingDetailsList = null;
        try {

            String dateFormat = "dd/MM/yyyy";
            String fromInvoiceDate ="";
            String toInvoiceDate="";
            String fromShipDate="";
            String toShipDate="";
            if(storeRatingDetailsDto.getFromInvoiceDate() != null && !storeRatingDetailsDto.getFromInvoiceDate().isEmpty()){
                fromInvoiceDate   = DateUtil.format(Long.parseLong(storeRatingDetailsDto.getFromInvoiceDate()), dateFormat);
                storeRatingDetailsDto.setFromInvoiceDate(fromInvoiceDate);

            }

            if(storeRatingDetailsDto.getToInvoiceDate() != null && !storeRatingDetailsDto.getToInvoiceDate().isEmpty()){
                toInvoiceDate   = DateUtil.format(Long.parseLong(storeRatingDetailsDto.getToInvoiceDate()), dateFormat);
                storeRatingDetailsDto.setToInvoiceDate(toInvoiceDate);

            }

            if(storeRatingDetailsDto.getFromShipDate() != null && !storeRatingDetailsDto.getFromShipDate().isEmpty()){
                fromShipDate   = DateUtil.format(Long.parseLong(storeRatingDetailsDto.getFromShipDate()), dateFormat);
                storeRatingDetailsDto.setFromShipDate(fromShipDate);
            }

            if(storeRatingDetailsDto.getToShipDate()!= null && !storeRatingDetailsDto.getToShipDate().isEmpty()){
                toShipDate   = DateUtil.format(Long.parseLong(storeRatingDetailsDto.getToShipDate()), dateFormat);
                storeRatingDetailsDto.setToShipDate(toShipDate);
            }

            UserProfileDto user = null;
            try{
                user = userService.getUserProfileByUserName(SecurityUtils.getCurrentUserLogin());
            }catch (Exception e){ //Do nothing.
            }
            if(storeRatingDetailsDto != null){
                storeRatingDetailsDto.setStatus(ParcelAuditConstant.ParcelRatingInputProcessStatus.NEW.value);
                storeRatingDetailsDto.setCreateUser(user != null ? user.getUserName() : ParcelAuditConstant.PARCEL_RTR_RATING_USER_NAME);
                }
            if (storeRatingDetailsDto != null & storeRatingDetailsDto.getRateSet() != null)
                ratingDetailsList = ratingService.saveRatingDetailsList(storeRatingDetailsDto);
            return new ResponseEntity<List<StoreRatingDetailsDto>>(ratingDetailsList, HttpStatus.OK);
        } catch (Exception e) {

            return new ResponseEntity<List<StoreRatingDetailsDto>>(ratingDetailsList, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value="/ratingJobs/getJobsByCustomer", method={RequestMethod.GET}, produces={MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<StoreRatingDetailsDto>> getJobsByCustomer(@RequestParam String customerId){
        try{
            List<StoreRatingDetailsDto> customerRatingJobsList = ratingService.getRatingJobsList(Long.parseLong(customerId));
            return new ResponseEntity<List<StoreRatingDetailsDto>>(customerRatingJobsList, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<List<StoreRatingDetailsDto>>(new ArrayList<StoreRatingDetailsDto>(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/getViewLog", method = {RequestMethod.GET}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<JSONObject> getViewLog(@RequestParam Long jobId) {

        try {
            List<ViewLogDto> eventLogList = ratingService.getViewLog(jobId);
            JSONObject eventLogJson = new JSONObject();
            if (eventLogList != null)
                eventLogJson.put("eventLogs", new JSONArray(eventLogList));
            return new ResponseEntity<JSONObject>(eventLogJson, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<JSONObject>(new JSONObject(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

}
