package com.envista.msi.api.web.rest;

import com.envista.msi.api.service.DashboardsService;
import com.envista.msi.api.web.rest.dto.DashboardAppliedFilterDto;
import com.envista.msi.api.web.rest.dto.DashboardDataDto;
import com.envista.msi.api.web.rest.dto.DashboardsFilterCriteria;
import com.envista.msi.api.web.rest.dto.NetSpendDto;
import com.envista.msi.api.web.rest.util.JSONUtil;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.*;

/**
 * Created by Sarvesh on 1/18/2017.
 */

@RestController
@RequestMapping("/api/dashboards")
public class DashboardsController {

    @Autowired
    HttpSession httpSession;

    @Autowired
    private DashboardsService dashboardsService;


    @RequestMapping(value = "/appliedFilter", method = { RequestMethod.GET}, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DashboardAppliedFilterDto> getAppliedFilterDetails(@RequestParam(value = "userId" , required = true) String userId  ){
        DashboardAppliedFilterDto dashboardAppliedFilterDto = null;
        try {
            dashboardAppliedFilterDto = dashboardsService.getUserAppliedFilter(Long.parseLong(userId));
            DashboardsFilterCriteria dashboardsFilterCriteria = populateDashboardFilterCriteria(dashboardAppliedFilterDto);
            return new ResponseEntity<DashboardAppliedFilterDto>(dashboardAppliedFilterDto, HttpStatus.OK );
        } catch (Exception e) {
            return new ResponseEntity<DashboardAppliedFilterDto>(new DashboardAppliedFilterDto(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    private DashboardsFilterCriteria populateDashboardFilterCriteria(DashboardAppliedFilterDto dashboardAppliedFilterDto){
        if(null == dashboardAppliedFilterDto){ return null; }

        DashboardsFilterCriteria dashboardsFilterCriteria = new DashboardsFilterCriteria();
        dashboardsFilterCriteria.setConvertCurrencyCode(dashboardAppliedFilterDto.getCurrencyCode());
        if(dashboardAppliedFilterDto.getCurrencyId() != null && !dashboardAppliedFilterDto.getCurrencyId().isEmpty()){
            dashboardsFilterCriteria.setConvertCurrencyId(Long.parseLong(dashboardAppliedFilterDto.getCurrencyId()));
        }
        dashboardsFilterCriteria.setCarriers(dashboardAppliedFilterDto.getCarrierIds());
        dashboardsFilterCriteria.setDateType(dashboardAppliedFilterDto.getDateType());
        dashboardsFilterCriteria.setFromDate(dashboardAppliedFilterDto.getFromDate());
        dashboardsFilterCriteria.setLanes(dashboardAppliedFilterDto.getLanes());
        dashboardsFilterCriteria.setModes(dashboardAppliedFilterDto.getModes());
        dashboardsFilterCriteria.setService(dashboardAppliedFilterDto.getServices());
        dashboardsFilterCriteria.setToDate(dashboardAppliedFilterDto.getToDate());
        dashboardsFilterCriteria.setShipperGroupIdsCSV(dashboardAppliedFilterDto.getShipperGroupId());
        dashboardsFilterCriteria.setConvertWeightUnit(dashboardAppliedFilterDto.getWeightUnit());
        dashboardsFilterCriteria.setUserId(dashboardAppliedFilterDto.getLoginUserId());
        return dashboardsFilterCriteria;
    }

    @RequestMapping(value = "/netSpendByMode", method = {RequestMethod.POST}, produces = {MediaType.APPLICATION_JSON_VALUE}, consumes = {MediaType.APPLICATION_JSON_VALUE})
    public String getNetSpendByModes(@RequestBody DashboardDataDto dbData) throws JSONException {
        List<NetSpendDto> netSpendDtoList = dashboardsService.getNetSpendByModes(dbData, false);
        JSONObject netSpendJsonData = new JSONObject();
        try{
            netSpendJsonData = JSONUtil.prepareNetSpendByModesJson(netSpendDtoList);
        }catch(Exception e){
            //do nothing.
        }
        return netSpendJsonData.toString();
    }

    @RequestMapping(value = "/netSpendByMode", method = {RequestMethod.GET}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public String getNetSpendByModes(@RequestParam String dateType) throws JSONException {
        DashboardDataDto dbData = new DashboardDataDto();
        dbData.setDateType(dateType);
        /*dbData.setConvertCurrencyId(0);
        dbData.setConvertCurrencyCode(convertCurrencyCode);
        dbData.setCustomerIdsCSV(customerIdsCSV);
        dbData.setCarriers(carriers);
        dbData.setModes(modes);
        dbData.setService(services);
        dbData.setFromDate(fromDate);
        dbData.setToDate(toDate);*/
        List<NetSpendDto> netSpendDtoList = dashboardsService.getNetSpendByModes(dbData, false);
        JSONObject netSpendJsonData = new JSONObject();
        try{
            netSpendJsonData = JSONUtil.prepareNetSpendByModesJson(netSpendDtoList);
        }catch(Exception e){
            //do nothing.
        }
        return netSpendJsonData.toString();
    }

    @RequestMapping(value = "/netSpendByMonth", method = {RequestMethod.POST}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public String getNetSpendByMonth(@RequestBody DashboardDataDto dbData){
        List<NetSpendDto> netSpendDtoList = dashboardsService.getNetSpendByOverTime(dbData, false);
        JSONObject netSpendJsonData = new JSONObject();
        try{
            netSpendJsonData = JSONUtil.prepareJsonForMonthlyChart(netSpendDtoList);
        }catch(Exception e){
            //do nothing.
        }
        return netSpendJsonData.toString();
    }

    @RequestMapping(value = "/netSpendByOverTime", method = {RequestMethod.POST}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public String getNetSpendByOverTime(@RequestBody DashboardDataDto dbData){
        List<NetSpendDto> netSpendDtoList = dashboardsService.getNetSpendByOverTime(dbData, false);
        JSONObject netSpendJsonData = new JSONObject();
        try{
            //need to prepare json based on the data.
        }catch(Exception e){
            //do nothing.
        }
        return netSpendJsonData.toString();
    }
}
