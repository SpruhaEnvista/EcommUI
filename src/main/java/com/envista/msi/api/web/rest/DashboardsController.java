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
        //List<NetSpendDto> netSpendDtoList = dashboardsService.getNetSpendByModes(dbData, false);

        //keeping dummy data.
        List<NetSpendDto> netSpendDtoList = new ArrayList<NetSpendDto>();
        NetSpendDto netSpendDto1 = new NetSpendDto();
        netSpendDto1.setId(1L);
        netSpendDto1.setModes("Small Package");
        netSpendDto1.setSpend(25268097.58);
        netSpendDto1.setScoreType("Total");

        NetSpendDto netSpendDto2 = new NetSpendDto();
        netSpendDto2.setId(2L);
        netSpendDto2.setModes("Brokerage");
        netSpendDto2.setSpend(19320.12);
        netSpendDto2.setScoreType("Total");

        NetSpendDto netSpendDto3 = new NetSpendDto();
        netSpendDto3.setId(3L);
        netSpendDto3.setModes("TL");
        netSpendDto3.setSpend(16341707.34);
        netSpendDto3.setScoreType("Total");

        NetSpendDto netSpendDto4 = new NetSpendDto();
        netSpendDto4.setId(4L);
        netSpendDto4.setModes("Air");
        netSpendDto4.setSpend(65488264.93);
        netSpendDto4.setScoreType("Total");

        NetSpendDto netSpendDto5 = new NetSpendDto();
        netSpendDto5.setId(4L);
        netSpendDto5.setModes("Ocean");
        netSpendDto5.setSpend(10556346.82);
        netSpendDto5.setScoreType("Total");

        NetSpendDto netSpendDto6 = new NetSpendDto();
        netSpendDto6.setId(6L);
        netSpendDto6.setModes("LTL");
        netSpendDto6.setSpend(30463628.24);
        netSpendDto6.setScoreType("Total");

        NetSpendDto netSpendDto7 = new NetSpendDto();
        netSpendDto7.setId(3L);
        netSpendDto7.setModes("FTL");
        netSpendDto7.setSpend(89409.01);
        netSpendDto7.setScoreType("Total");

        netSpendDtoList.add(netSpendDto1);
        netSpendDtoList.add(netSpendDto2);
        netSpendDtoList.add(netSpendDto3);
        netSpendDtoList.add(netSpendDto4);
        netSpendDtoList.add(netSpendDto5);
        netSpendDtoList.add(netSpendDto6);
        netSpendDtoList.add(netSpendDto7);

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
