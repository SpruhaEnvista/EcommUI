package com.envista.msi.api.web.rest;

import com.envista.msi.api.service.DashboardsService;
import com.envista.msi.api.service.UserService;
import com.envista.msi.api.web.rest.dto.DashboardAppliedFilterDto;
import com.envista.msi.api.web.rest.dto.DashboardsFilterCriteria;
import com.envista.msi.api.web.rest.dto.UserProfileDto;
import com.envista.msi.api.web.rest.dto.common.CommonValuesForChartDto;
import com.envista.msi.api.web.rest.dto.netspend.*;
import com.envista.msi.api.web.rest.dto.taxspend.TaxSpendByCarrierDto;
import com.envista.msi.api.web.rest.dto.taxspend.TaxSpendByMonthDto;
import com.envista.msi.api.web.rest.dto.taxspend.TaxSpendDto;
import com.envista.msi.api.web.rest.util.JSONUtil;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.el.MethodNotFoundException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Sarvesh on 1/18/2017.
 */

@RestController
@RequestMapping("/api/dashboards")
public class DashboardsController extends DashboardBaseController {


    @Autowired
    UserService userService;

    @Autowired
    private DashboardsService dashboardsService;

    public enum TaxSpendConstant{
        TAX_SPEND,
        TAX_SPEND_BY_CARRIER,
        TAX_SPEND_BY_MONTH;
    }

    public enum NetSpendConstant{
        NET_SPEND_BY_MODE,
        NET_SPEND_OVER_TIME_BY_MONTH,
        NET_SPEND_BY_OVER_TIME,
        NET_SPEND_BY_CARRIER,
        NET_SPEND_BY_MONTH;
    }

    @RequestMapping(value = "/appliedFilter", method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.OPTIONS}, produces = MediaType.APPLICATION_JSON_VALUE)
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

    private DashboardsFilterCriteria loadAppliedFilters(Long userId){
        DashboardAppliedFilterDto dashboardAppliedFilterDto = null;
        dashboardAppliedFilterDto = dashboardsService.getUserAppliedFilter(userId);
        return populateDashboardFilterCriteria(dashboardAppliedFilterDto);
    }

    @RequestMapping(value = "/netSpendByMode", method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.OPTIONS}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<String> getNetSpendByModes() throws JSONException {
        JSONObject netSpendJsonData = new JSONObject();
        try{
            UserProfileDto user = getUserProfile();
            if(null == user){
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }
            DashboardsFilterCriteria filter = loadAppliedFilters(user.getUserId());
            JSONObject nspData = netSpendJsonData = loadNetSpendJsonData(NetSpendConstant.NET_SPEND_BY_MODE, user, filter);
            if(nspData != null){
                netSpendJsonData = nspData;
            }
        }catch(Exception e){
            return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<String>(netSpendJsonData.toString(), HttpStatus.OK);
    }

    @RequestMapping(value = "/netSpendOvrTmByMnth", method = {RequestMethod.POST, RequestMethod.OPTIONS}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<String> getNetSpendByMonth(@RequestParam String carrierName, @RequestParam String invoiceDate, @RequestBody String carrierDetails){
        JSONObject netSpendJsonData = new JSONObject();
        try{
            UserProfileDto user = getUserProfile();
            if(null == user){
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }
            DashboardsFilterCriteria filter = loadAppliedFilters(user.getUserId());
            if(filter != null){
                if (carrierName != null && !carrierName.isEmpty() && carrierDetails != null && !carrierDetails.isEmpty()) {
                    JSONArray carrierDetailsArr = new JSONArray(carrierDetails);
                    int carriersLen = carrierDetailsArr.length();
                    for ( int i = 0; i < carriersLen; i++) {
                        JSONObject carrierInfo = carrierDetailsArr.getJSONObject(i);
                        if ( carrierName.equalsIgnoreCase(carrierInfo.getString("carrierName") ) ) {
                            filter.setCarriers(carrierInfo.getString("carrierId"));
                            break;
                        }
                    }
                }
                if (invoiceDate != null) {
                    setDatesFromMonth(filter, invoiceDate);
                }
            }
            JSONObject nspData = loadNetSpendJsonData(NetSpendConstant.NET_SPEND_OVER_TIME_BY_MONTH, user, filter);
            if(nspData != null){
                netSpendJsonData = nspData;
            }
        }catch(Exception e){
            return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<String>(netSpendJsonData.toString(), HttpStatus.OK);
    }

    private void setDatesFromMonth(DashboardsFilterCriteria filter, String monthAndYear) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
        String selectedMonth = monthAndYear.split(" ")[0];
        String lastTwoDigitsOfYear = monthAndYear.split(" ")[1].replace("-", "");

        String yearOfFromDate = filter.getFromDate().split("-")[2];
        String monthOfFromDate = filter.getFromDate().split("-")[1];
        String yearOfToDate=filter.getToDate().split("-")[2];
        String monthOfToDate = filter.getToDate().split("-")[1];
        String firstTwoDigitsOfYear = yearOfFromDate.substring(0, 2);
        int year = 0;
        if (lastTwoDigitsOfYear.length() != 4) {
            year = Integer.parseInt(firstTwoDigitsOfYear + lastTwoDigitsOfYear);
        } else {
            year = Integer.parseInt(lastTwoDigitsOfYear);
        }

        Calendar c = Calendar.getInstance();
        Date date = (Date) new SimpleDateFormat("MMM", Locale.ENGLISH).parse(selectedMonth);
        c.setTime(date);
        int numericMonth = c.get(Calendar.MONTH);
        c.set(year, numericMonth, 1);
        c.set(Calendar.DAY_OF_MONTH, c.getActualMaximum(Calendar.DAY_OF_MONTH));
        String toDate = sdf.format(c.getTime());
        if (selectedMonth.equalsIgnoreCase(monthOfFromDate) && yearOfFromDate.equals(String.valueOf(year))) {
            if (!selectedMonth.equalsIgnoreCase(monthOfToDate)) {
                filter.setToDate(toDate);
            }
        } else if (selectedMonth.equalsIgnoreCase(monthOfToDate) && yearOfToDate.equals(String.valueOf(year))) {
            if (!selectedMonth.equalsIgnoreCase(monthOfFromDate)) {
                filter.setFromDate("01-" + selectedMonth + "-" + year);
            }
        } else {
            filter.setFromDate("01-" + selectedMonth + "-" + year);
            filter.setToDate(toDate);
        }

    }

    @RequestMapping(value = "/netSpendByOverTime", method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.OPTIONS}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<String> getNetSpendByOverTime(){
        JSONObject netSpendJsonData = new JSONObject();
        try{
            UserProfileDto user = getUserProfile();
            if(null == user){
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }
            DashboardsFilterCriteria filter = loadAppliedFilters(user.getUserId());
            JSONObject nspData = loadNetSpendJsonData(NetSpendConstant.NET_SPEND_BY_OVER_TIME, user, filter);
            if(nspData != null){
                netSpendJsonData = nspData;
            }
        }catch(Exception e){
            return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<String>(netSpendJsonData.toString(), HttpStatus.OK);
    }

    @RequestMapping(value = "/netSpendByCarr", method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.OPTIONS}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<String> getCarrierScoreCard(@RequestParam String mode){
        JSONObject netSpendJsonData = new JSONObject();
        try{
            UserProfileDto user = getUserProfile();
            if(null == user){
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }
            DashboardsFilterCriteria filter = loadAppliedFilters(user.getUserId());
            if(filter != null && mode != null){
                filter.setModeNames(mode);
            }
            JSONObject nspData = loadNetSpendJsonData(NetSpendConstant.NET_SPEND_BY_CARRIER, user, filter);
            if(nspData != null){
                netSpendJsonData = nspData;
            }
        }catch(Exception e){
            return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<String>(netSpendJsonData.toString(), HttpStatus.OK);
    }

    @RequestMapping(value = "/netSpendByMth", method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.OPTIONS}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<String> getCarrierScoreCardByMonth(@RequestParam String carrier, @RequestParam  String mode){
        JSONObject netSpendJsonData = new JSONObject();
        try{
            UserProfileDto user = getUserProfile();
            if(null == user){
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }

            DashboardsFilterCriteria filter = loadAppliedFilters(user.getUserId());
            if(filter != null){
                if(carrier != null){
                    filter.setCarriers(carrier);
                }
                if(mode != null){
                    filter.setModeNames(mode);
                }
            }
            JSONObject nspData = loadNetSpendJsonData(NetSpendConstant.NET_SPEND_BY_MONTH, user, filter);
            if(nspData != null){
                netSpendJsonData = nspData;
            }
        }catch(Exception e){
            return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<String>(netSpendJsonData.toString(), HttpStatus.OK);
    }

    @RequestMapping(value = "/taxSpend", method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.OPTIONS}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<String> getTaxSpend(){
        JSONObject taxSpendJson = new JSONObject();
        try {
            UserProfileDto user = getUserProfile();
            if (null == user) {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }
            DashboardsFilterCriteria filter = loadAppliedFilters(user.getUserId());
            JSONObject taxData = loadTaxSpendJsonData(TaxSpendConstant.TAX_SPEND, user, filter);
            if(taxData != null){
                taxSpendJson = taxData;
            }
        }catch(Exception e){
            return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<String>(taxSpendJson.toString(), HttpStatus.OK);
    }

    @RequestMapping(value = "/taxSpendByCarr", method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.OPTIONS}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<String> getTaxSpendByCarrier(@RequestParam String tax){
        JSONObject taxSpendJson = new JSONObject();
        try {
            UserProfileDto user = getUserProfile();
            if (null == user) {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }
            DashboardsFilterCriteria filter = loadAppliedFilters(user.getUserId());
            if(filter != null && tax != null){
                filter.setTax(tax);
            }
            JSONObject taxData = loadTaxSpendJsonData(TaxSpendConstant.TAX_SPEND_BY_CARRIER, user, filter);
            if(taxData != null){
                taxSpendJson = taxData;
            }
        }catch(Exception e){
            return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<String>(taxSpendJson.toString(), HttpStatus.OK);
    }

    @RequestMapping(value = "/taxSpendByMth", method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.OPTIONS}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<String> getTaxSpendByMonth(@RequestParam String carrierId, @RequestParam String tax){
        JSONObject taxSpendJson = new JSONObject();
        try {
            UserProfileDto user = getUserProfile();
            if (null == user) {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }
            DashboardsFilterCriteria filter = loadAppliedFilters(user.getUserId());
            if(filter != null){
                if(carrierId != null){
                    filter.setCarriers(carrierId);
                }
                if(tax != null){
                    filter.setTax(tax);
                }
            }
            JSONObject taxData = loadTaxSpendJsonData(TaxSpendConstant.TAX_SPEND_BY_MONTH, user, filter);
            if(taxData != null){
                taxSpendJson = taxData;
            }
        }catch(Exception e){
            return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<String>(taxSpendJson.toString(), HttpStatus.OK);
    }

    private JSONObject loadNetSpendJsonData(NetSpendConstant netSpend, UserProfileDto user, DashboardsFilterCriteria filter) throws JSONException {
        JSONObject netSpendJson = null;
        switch (netSpend){
            case NET_SPEND_BY_MODE:
                netSpendJson = loadNetSpendByModeJson(user, filter);
                break;
            case NET_SPEND_BY_OVER_TIME:
                netSpendJson = loadNetSpendByOverTimeJson(user, filter);
                break;
            case NET_SPEND_OVER_TIME_BY_MONTH:
                netSpendJson = loadNetSpendOverTimeByMonthJson(user, filter);
                break;
            case NET_SPEND_BY_CARRIER:
                netSpendJson = loadNetSpendByCarrierJson(user, filter);
                break;
            case NET_SPEND_BY_MONTH:
                netSpendJson = loadNetSpendByMonthJson(user, filter);
                break;
            default:
                throw new MethodNotFoundException("Method param value not matched");
        }
        return netSpendJson;
    }

    private JSONObject loadNetSpendByMonthJson(UserProfileDto user, DashboardsFilterCriteria filter) throws JSONException {
        JSONObject netSpendJsonData = null;
        List<NetSpendByMonthDto> netSpendByMonthDtos = dashboardsService.getNetSpendByMonth(filter, false);
        List<NetSpendMonthlyChartDto> monthlyChartDtos = null;
        if(netSpendByMonthDtos != null && netSpendByMonthDtos.size() > 0){
            monthlyChartDtos = new ArrayList<NetSpendMonthlyChartDto>();
            for(NetSpendByMonthDto netSpendByMonthDto : netSpendByMonthDtos){
                if(netSpendByMonthDto != null){
                    monthlyChartDtos.add(new NetSpendMonthlyChartDto(netSpendByMonthDto));
                }
            }
            netSpendJsonData = JSONUtil.prepareMonthlyChartJson(monthlyChartDtos);
        }
        return netSpendJsonData;
    }

    private JSONObject loadNetSpendByCarrierJson(UserProfileDto user, DashboardsFilterCriteria filter) throws JSONException {
        JSONObject netSpendJsonData = null;

        List<NetSpendByCarrierDto> netSpendList = dashboardsService.getNetSpendByCarrier(filter, false);
        if(netSpendList != null){
            List<CommonValuesForChartDto> commonValueList = new ArrayList<CommonValuesForChartDto>();
            for(NetSpendByCarrierDto netSpend : netSpendList){
                if(netSpend != null){
                    commonValueList.add(new CommonValuesForChartDto(netSpend));
                }
            }
            netSpendJsonData = JSONUtil.prepareCommonJsonForChart(commonValueList);
        }
        return netSpendJsonData;
    }

    private JSONObject loadNetSpendByOverTimeJson(UserProfileDto user, DashboardsFilterCriteria filter) throws JSONException {
        JSONObject netSpendJsonData = null;
        List<NetSpendOverTimeDto> netSpendDtoList = dashboardsService.getNetSpendByOverTime(filter, false);
        if(netSpendDtoList != null){
            netSpendJsonData = JSONUtil.prepareNetSpendOverTimeJson(netSpendDtoList);
        }
        return netSpendJsonData;
    }

    private JSONObject loadNetSpendOverTimeByMonthJson(UserProfileDto user, DashboardsFilterCriteria filter) throws JSONException {
        JSONObject netSpendJsonData = null;
        List<NetSpendOverTimeByMonthDto> netSpendDtoList = dashboardsService.getNetSpendOverTimeByMonth(filter, false);
        List<NetSpendMonthlyChartDto> monthlyChartDtos = null;
        if(netSpendDtoList != null && netSpendDtoList.size() > 0){
            monthlyChartDtos = new ArrayList<NetSpendMonthlyChartDto>();
            for(NetSpendOverTimeByMonthDto overTimeByMonthDto : netSpendDtoList){
                if(overTimeByMonthDto != null){
                    monthlyChartDtos.add(new NetSpendMonthlyChartDto(overTimeByMonthDto));
                }
            }
            netSpendJsonData = JSONUtil.prepareMonthlyChartJson(monthlyChartDtos);
        }
        return netSpendJsonData;
    }

    private JSONObject loadNetSpendByModeJson(UserProfileDto user, DashboardsFilterCriteria filter) throws JSONException {
        JSONObject netSpendJsonData = null;
        List<NetSpendByModeDto> netSpendDtoList = dashboardsService.getNetSpendByModes(filter, false);
        netSpendJsonData = JSONUtil.prepareNetSpendByModesJson(netSpendDtoList);
        return netSpendJsonData;
    }

    private JSONObject loadTaxSpendJsonData(TaxSpendConstant taxSpend, UserProfileDto user, DashboardsFilterCriteria filter) throws JSONException {
        JSONObject taxSpendJson = null;
        switch (taxSpend){
            case TAX_SPEND:
                taxSpendJson = loadTaxSpendJson(user, filter);
                break;
            case TAX_SPEND_BY_CARRIER:
                taxSpendJson = loadTaxSpendByCarrJson(user, filter);
                break;
            case TAX_SPEND_BY_MONTH:
                taxSpendJson = loadTaxSpendByMonthJson(user, filter);
                break;
            default:
                throw new MethodNotFoundException("Method param value not matched");
        }
        return taxSpendJson;
    }

    private JSONObject loadTaxSpendByMonthJson(UserProfileDto user, DashboardsFilterCriteria filter) throws JSONException {
        JSONObject taxSpendJson = null;
        List<TaxSpendByMonthDto> taxSpendList = dashboardsService.getTaxSpendByMonth(filter);
        if(taxSpendList != null && taxSpendList.size() > 0){
            List<NetSpendMonthlyChartDto> taxSpendPieChartList = null;
            for(TaxSpendByMonthDto taxSpend : taxSpendList){
                if(taxSpend != null){
                    taxSpendPieChartList.add(new NetSpendMonthlyChartDto(taxSpend));
                }
            }
            taxSpendJson = JSONUtil.prepareMonthlyChartJson(taxSpendPieChartList);
        }
        return taxSpendJson;
    }

    private JSONObject loadTaxSpendByCarrJson(UserProfileDto user, DashboardsFilterCriteria filter) throws JSONException {
        JSONObject taxSpendJson = null;
        List<TaxSpendByCarrierDto> taxSpendList = dashboardsService.getTaxSpendByCarrier(filter);
        if(taxSpendList != null && taxSpendList.size() > 0){
            List<CommonValuesForChartDto> commonValueList = new ArrayList<CommonValuesForChartDto>();
            for(TaxSpendByCarrierDto taxSpend : taxSpendList){
                if(taxSpend != null){
                    commonValueList.add(new CommonValuesForChartDto(taxSpend));
                }
            }
            taxSpendJson = JSONUtil.prepareCommonJsonForChart(commonValueList);
        }
        return taxSpendJson;
    }

    private JSONObject loadTaxSpendJson(UserProfileDto user, DashboardsFilterCriteria filter) throws JSONException {
        JSONObject taxSpendJson = null;
        List<TaxSpendDto> taxSpendList = dashboardsService.getTaxSpend(filter);
        if(taxSpendList != null && taxSpendList.size() > 0){
            taxSpendJson = JSONUtil.prepareTaxSpendJson(taxSpendList);
        }
        return taxSpendJson;
    }
}
