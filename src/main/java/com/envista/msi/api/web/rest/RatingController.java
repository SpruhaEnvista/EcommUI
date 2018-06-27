package com.envista.msi.api.web.rest;

import com.envista.msi.api.service.RatingService;
import com.envista.msi.api.web.rest.dto.reports.ReportCustomerCarrierDto;
import com.envista.msi.api.web.rest.util.JSONUtil;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;
import java.util.List;

/*
* Created by Srikanth Punna on 06/19/2018
* */

@RestController
@RequestMapping("/api/rates")
public class RatingController {

    @Inject
    private RatingService ratingService;

    @RequestMapping(value="/carrierlist", method={RequestMethod.GET}, produces={MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<JSONObject> getRateCarriersList(@RequestParam String userId, @RequestParam String customerIds){
        try{
            List<ReportCustomerCarrierDto> carriersList = ratingService.getRateCarrierList(Long.parseLong(userId), customerIds);
            JSONObject carrierJSON = new JSONObject();
            if(carriersList != null)
                carrierJSON.put("rateCarriersList", JSONUtil.carriersJson(carriersList));
            return new ResponseEntity<JSONObject>(carrierJSON, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<JSONObject>(new JSONObject(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
