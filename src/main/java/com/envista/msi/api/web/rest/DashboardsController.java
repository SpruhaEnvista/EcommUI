package com.envista.msi.api.web.rest;

import com.envista.msi.api.domain.util.DashboardUtil;
import com.envista.msi.api.service.DashboardsService;
import com.envista.msi.api.service.UserService;
import com.envista.msi.api.web.rest.dto.UserProfileDto;
import com.envista.msi.api.web.rest.dto.dashboard.DashboardAppliedFilterDto;
import com.envista.msi.api.web.rest.dto.dashboard.accessorialspend.AccessorialSpendDto;
import com.envista.msi.api.web.rest.dto.dashboard.common.CommonValuesForChartDto;
import com.envista.msi.api.web.rest.dto.dashboard.common.NetSpendCommonDto;
import com.envista.msi.api.web.rest.dto.dashboard.common.NetSpendMonthlyChartDto;
import com.envista.msi.api.web.rest.dto.dashboard.DashboardsFilterCriteria;
import com.envista.msi.api.web.rest.dto.dashboard.netspend.*;
import com.envista.msi.api.web.rest.dto.dashboard.shipmentoverview.AverageSpendPerShipmentDto;
import com.envista.msi.api.web.rest.dto.dashboard.shipmentoverview.AverageWeightModeShipmtDto;
import com.envista.msi.api.web.rest.dto.dashboard.taxspend.TaxSpendByCarrierDto;
import com.envista.msi.api.web.rest.dto.dashboard.taxspend.TaxSpendByMonthDto;
import com.envista.msi.api.web.rest.dto.dashboard.taxspend.TaxSpendDto;
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

    public enum AccessorialSpendConstant{
        TOP_ACCESSORIAL_SPEND,
        TOP_ACCESSORIAL_SPEND_BY_CARRIER,
        TOP_ACCESSORIAL_SPEND_BY_MONTH,
        ACCESSORIAL_SPEND,
        ACCESSORIAL_SPEND_BY_CARRIER,
        ACCESSORIAL_SPEND_BY_MONTH,
    }

    public enum ShipmentOverviewConstant{
        AVG_SPEND_PER_SHIPMT,
        AVG_WEIGHT_BY_MODE_SHIPMT,
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
        JSONObject netSpendJsonData = null;
        try{
            UserProfileDto user = getUserProfile();
            if(null == user){
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }
            DashboardsFilterCriteria filter = loadAppliedFilters(user.getUserId());
            JSONObject nspData = netSpendJsonData = loadNetSpendJsonData(NetSpendConstant.NET_SPEND_BY_MODE, filter);
            netSpendJsonData = (nspData != null ? nspData : new JSONObject());
        }catch(Exception e){
            return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<String>(netSpendJsonData.toString(), HttpStatus.OK);
    }

    @RequestMapping(value = "/netSpendOvrTmByMnth", method = {RequestMethod.POST, RequestMethod.OPTIONS}, produces = {MediaType.APPLICATION_JSON_VALUE}, consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<String> getNetSpendOverTimeByMonth(@RequestBody NetSpendRequestDto netSpendRequest){
        JSONObject netSpendJsonData = null;
        try{
            UserProfileDto user = getUserProfile();
            if(null == user){
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }
            String carrierName = netSpendRequest.getCarrierName();
            String carrierDetails = netSpendRequest.getCarrierDetails();
            String invoiceDate = netSpendRequest.getInvoiceDate();
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
                    DashboardUtil.setDatesFromMonth(filter, invoiceDate);
                }
            }
            JSONObject nspData = loadNetSpendJsonData(NetSpendConstant.NET_SPEND_OVER_TIME_BY_MONTH, filter);
            netSpendJsonData = (nspData != null ? nspData : new JSONObject());
        }catch(Exception e){
            return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<String>(netSpendJsonData.toString(), HttpStatus.OK);
    }



    @RequestMapping(value = "/netSpendByOverTime", method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.OPTIONS}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<String> getNetSpendByOverTime(){
        JSONObject netSpendJsonData = null;
        try{
            UserProfileDto user = getUserProfile();
            if(null == user){
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }
            DashboardsFilterCriteria filter = loadAppliedFilters(user.getUserId());
            JSONObject nspData = loadNetSpendJsonData(NetSpendConstant.NET_SPEND_BY_OVER_TIME, filter);
            netSpendJsonData = (nspData != null ? nspData : new JSONObject());
        }catch(Exception e){
            return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<String>(netSpendJsonData.toString(), HttpStatus.OK);
    }

    @RequestMapping(value = "/netSpendByCarr", method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.OPTIONS}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<String> getNetSpendByCarrier(@RequestParam String mode){
        JSONObject netSpendJsonData = null;
        try{
            UserProfileDto user = getUserProfile();
            if(null == user){
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }
            DashboardsFilterCriteria filter = loadAppliedFilters(user.getUserId());
            if(filter != null && mode != null){
                filter.setModeNames(mode);
            }
            JSONObject nspData = loadNetSpendJsonData(NetSpendConstant.NET_SPEND_BY_CARRIER, filter);
            netSpendJsonData = (nspData != null ? nspData : new JSONObject());
        }catch(Exception e){
            return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<String>(netSpendJsonData.toString(), HttpStatus.OK);
    }

    @RequestMapping(value = "/netSpendByMth", method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.OPTIONS}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<String> getNetSpendByMonth(@RequestParam String carrier, @RequestParam  String mode){
        JSONObject netSpendJsonData = null;
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
            JSONObject nspData = loadNetSpendJsonData(NetSpendConstant.NET_SPEND_BY_MONTH, filter);
            netSpendJsonData = (nspData != null ? nspData : new JSONObject());
        }catch(Exception e){
            return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<String>(netSpendJsonData.toString(), HttpStatus.OK);
    }

    @RequestMapping(value = "/taxSpend", method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.OPTIONS}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<String> getTaxSpend(){
        JSONObject taxSpendJson = null;
        try {
            UserProfileDto user = getUserProfile();
            if (null == user) {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }
            DashboardsFilterCriteria filter = loadAppliedFilters(user.getUserId());
            JSONObject taxData = loadTaxSpendJsonData(TaxSpendConstant.TAX_SPEND, filter);
            taxSpendJson = (taxData != null ? taxData : new JSONObject());
        }catch(Exception e){
            return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<String>(taxSpendJson.toString(), HttpStatus.OK);
    }

    @RequestMapping(value = "/taxSpendByCarr", method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.OPTIONS}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<String> getTaxSpendByCarrier(@RequestParam String tax){
        JSONObject taxSpendJson = null;
        try {
            UserProfileDto user = getUserProfile();
            if (null == user) {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }
            DashboardsFilterCriteria filter = loadAppliedFilters(user.getUserId());
            if(filter != null && tax != null){
                filter.setTax(tax);
            }
            JSONObject taxData = loadTaxSpendJsonData(TaxSpendConstant.TAX_SPEND_BY_CARRIER, filter);
            taxSpendJson = (taxData != null ? taxData : new JSONObject());
        }catch(Exception e){
            return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<String>(taxSpendJson.toString(), HttpStatus.OK);
    }

    @RequestMapping(value = "/taxSpendByMth", method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.OPTIONS}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<String> getTaxSpendByMonth(@RequestParam String carrierId, @RequestParam String tax){
        JSONObject taxSpendJson = null;
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
            JSONObject taxData = loadTaxSpendJsonData(TaxSpendConstant.TAX_SPEND_BY_MONTH, filter);
            taxSpendJson = (taxData != null ? taxData : new JSONObject());
        }catch(Exception e){
            return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<String>(taxSpendJson.toString(), HttpStatus.OK);
    }

    @RequestMapping(value = "/topAccSpend", method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.OPTIONS}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<String> getTopAccessorialSpend(){
        JSONObject accSpendJson = null;
        try{
            UserProfileDto user = getUserProfile();
            if (null == user) {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }
            DashboardsFilterCriteria filter = loadAppliedFilters(user.getUserId());
            JSONObject accData = loadAccessorialSpendJsonData(AccessorialSpendConstant.TOP_ACCESSORIAL_SPEND, filter);
            accSpendJson = (accData != null ? accData : new JSONObject());
        }catch(Exception e){
            return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<String>(accSpendJson.toString(), HttpStatus.OK);
    }

    @RequestMapping(value = "/topAccSpendByCarr", method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.OPTIONS}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<String> getTopAccessorialSpendByCarrier(@RequestParam String accessorial, @RequestParam String invoiceDate){
        JSONObject accSpendJson = null;
        try{
            UserProfileDto user = getUserProfile();
            if (null == user) {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }
            DashboardsFilterCriteria filter = loadAppliedFilters(user.getUserId());
            if(filter !=  null){
                if(accessorial != null && !accessorial.isEmpty()){
                    filter.setAccessorialName(accessorial);
                }
                if(invoiceDate != null && !invoiceDate.isEmpty()){
                    DashboardUtil.setDatesFromMonth(filter, invoiceDate);
                }
            }
            JSONObject accData = loadAccessorialSpendJsonData(AccessorialSpendConstant.TOP_ACCESSORIAL_SPEND_BY_CARRIER, filter);
            accSpendJson = (accData != null ? accData : new JSONObject());
        }catch(Exception e){
            return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<String>(accSpendJson.toString(), HttpStatus.OK);
    }

    @RequestMapping(value = "/topAccSpendByMonth", method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.OPTIONS}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<String> getTopAccessorialSpendByMonth(@RequestParam String accessorial, @RequestParam String invoiceDate, @RequestParam String carrierId){
        JSONObject accSpendJson = null;
        try{
            UserProfileDto user = getUserProfile();
            if (null == user) {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }
            DashboardsFilterCriteria filter = loadAppliedFilters(user.getUserId());
            if(filter !=  null){
                if(accessorial != null && !accessorial.isEmpty()){
                    filter.setAccessorialName(accessorial);
                }
                if(invoiceDate != null && !invoiceDate.isEmpty()){
                    DashboardUtil.setDatesFromMonth(filter, invoiceDate);
                }
                if(carrierId != null && !carrierId.isEmpty()){
                    filter.setCarriers(carrierId);
                }
            }
            JSONObject accData = loadAccessorialSpendJsonData(AccessorialSpendConstant.TOP_ACCESSORIAL_SPEND_BY_MONTH, filter);
            accSpendJson = (accData != null ? accData : new JSONObject());
        }catch(Exception e){
            return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<String>(accSpendJson.toString(), HttpStatus.OK);
    }

    @RequestMapping(value = "/accSpend", method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.OPTIONS}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<String> getAccessorialSpend(){
        JSONObject accSpendJson = null;
        try{
            UserProfileDto user = getUserProfile();
            if (null == user) {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }
            DashboardsFilterCriteria filter = loadAppliedFilters(user.getUserId());
            JSONObject accData = loadAccessorialSpendJsonData(AccessorialSpendConstant.ACCESSORIAL_SPEND, filter);
            accSpendJson = (accData != null ? accData : new JSONObject());
        }catch(Exception e){
            return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<String>(accSpendJson.toString(), HttpStatus.OK);
    }

    @RequestMapping(value = "/accSpendByCarr", method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.OPTIONS}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<String> getAccessorialSpendByCarrier(@RequestParam String tax){
        JSONObject accSpendJson = null;
        try{
            UserProfileDto user = getUserProfile();
            if (null == user) {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }
            DashboardsFilterCriteria filter = loadAppliedFilters(user.getUserId());
            if(filter != null && tax != null && !tax.isEmpty()){
                filter.setAccDesc(tax);
            }
            JSONObject accData = loadAccessorialSpendJsonData(AccessorialSpendConstant.ACCESSORIAL_SPEND_BY_CARRIER, filter);
            accSpendJson = (accData != null ? accData : new JSONObject());
        }catch(Exception e){
            return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<String>(accSpendJson.toString(), HttpStatus.OK);
    }

    @RequestMapping(value = "/accSpendByMonth", method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.OPTIONS}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<String> getAccessorialSpendByMonth(@RequestParam String carrierId, @RequestParam String tax){
        JSONObject accSpendJson = null;
        try{
            UserProfileDto user = getUserProfile();
            if (null == user) {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }
            DashboardsFilterCriteria filter = loadAppliedFilters(user.getUserId());
            if(filter != null){
                if(carrierId != null && !carrierId.isEmpty()){
                    filter.setCarriers(carrierId);
                }
                if(tax != null && !tax.isEmpty()){
                    filter.setAccDesc(tax);
                }
            }
            JSONObject accData = loadAccessorialSpendJsonData(AccessorialSpendConstant.ACCESSORIAL_SPEND_BY_MONTH, filter);
            accSpendJson = (accData != null ? accData : new JSONObject());
        }catch(Exception e){
            return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<String>(accSpendJson.toString(), HttpStatus.OK);
    }

    @RequestMapping(value = "/avgSpendPerShipment", method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.OPTIONS}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<String> getAvgSpendPerShipment(){
        JSONObject avgSpendPerShipmtJsonData = null;
        try{
            UserProfileDto user = getUserProfile();
            if(null == user){
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }
            DashboardsFilterCriteria filter = loadAppliedFilters(user.getUserId());
            JSONObject avgShipmentData = loadShipmentOverviewJsonData(ShipmentOverviewConstant.AVG_SPEND_PER_SHIPMT, filter);
            avgSpendPerShipmtJsonData = (avgShipmentData != null ? avgShipmentData : new JSONObject());
        }catch(Exception e){
            return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<String>(avgSpendPerShipmtJsonData.toString(), HttpStatus.OK);
    }

    @RequestMapping(value = "/avgWeightByModeShipment", method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.OPTIONS}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<String> getAverageWeightByModeShipment(){
        JSONObject avgWeightModeShipmtJsonData = null;
        try{
            UserProfileDto user = getUserProfile();
            if(null == user){
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }
            DashboardsFilterCriteria filter = loadAppliedFilters(user.getUserId());
            JSONObject avgShipmentData = loadShipmentOverviewJsonData(ShipmentOverviewConstant.AVG_WEIGHT_BY_MODE_SHIPMT, filter);
            avgWeightModeShipmtJsonData = (avgShipmentData != null ? avgShipmentData : new JSONObject());
        }catch(Exception e){
            return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<String>(avgWeightModeShipmtJsonData.toString(), HttpStatus.OK);
    }

    private JSONObject loadNetSpendJsonData(NetSpendConstant netSpendType, DashboardsFilterCriteria filter) throws JSONException {
        JSONObject netSpendJson = null;
        switch (netSpendType){
            case NET_SPEND_BY_MODE:
                netSpendJson = loadNetSpendByModeJson(filter);
                break;
            case NET_SPEND_BY_OVER_TIME:
                netSpendJson = loadNetSpendByOverTimeJson(filter);
                break;
            case NET_SPEND_OVER_TIME_BY_MONTH:
                netSpendJson = loadNetSpendOverTimeByMonthJson(filter);
                break;
            case NET_SPEND_BY_CARRIER:
                netSpendJson = loadNetSpendByCarrierJson(filter);
                break;
            case NET_SPEND_BY_MONTH:
                netSpendJson = loadNetSpendByMonthJson(filter);
                break;
            default:
                throw new MethodNotFoundException("Method param value not matched");
        }
        return netSpendJson;
    }

    private JSONObject loadNetSpendByMonthJson(DashboardsFilterCriteria filter) throws JSONException {
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

    private JSONObject loadNetSpendByCarrierJson(DashboardsFilterCriteria filter) throws JSONException {
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

    private JSONObject loadNetSpendByOverTimeJson(DashboardsFilterCriteria filter) throws JSONException {
        JSONObject netSpendJsonData = null;
        List<NetSpendOverTimeDto> netSpendDtoList = dashboardsService.getNetSpendByOverTime(filter, false);
        if(netSpendDtoList != null){
            netSpendJsonData = JSONUtil.prepareNetSpendOverTimeJson(netSpendDtoList);
        }
        return netSpendJsonData;
    }

    private JSONObject loadNetSpendOverTimeByMonthJson(DashboardsFilterCriteria filter) throws JSONException {
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

    private JSONObject loadNetSpendByModeJson(DashboardsFilterCriteria filter) throws JSONException {
        JSONObject netSpendJsonData = null;
        List<NetSpendByModeDto> netSpendDtoList = dashboardsService.getNetSpendByModes(filter, false);
        netSpendJsonData = JSONUtil.prepareNetSpendByModesJson(netSpendDtoList);
        return netSpendJsonData;
    }

    private JSONObject loadTaxSpendJsonData(TaxSpendConstant taxSpendType, DashboardsFilterCriteria filter) throws JSONException {
        JSONObject taxSpendJson = null;
        switch (taxSpendType){
            case TAX_SPEND:
                taxSpendJson = loadTaxSpendJson(filter);
                break;
            case TAX_SPEND_BY_CARRIER:
                taxSpendJson = loadTaxSpendByCarrJson(filter);
                break;
            case TAX_SPEND_BY_MONTH:
                taxSpendJson = loadTaxSpendByMonthJson(filter);
                break;
            default:
                throw new MethodNotFoundException("Method param value not matched");
        }
        return taxSpendJson;
    }

    private JSONObject loadTaxSpendByMonthJson(DashboardsFilterCriteria filter) throws JSONException {
        JSONObject taxSpendJson = null;
        List<TaxSpendByMonthDto> taxSpendList = dashboardsService.getTaxSpendByMonth(filter);
        List<NetSpendMonthlyChartDto> taxSpendPieChartList = null;
        if(taxSpendList != null && taxSpendList.size() > 0){
            taxSpendPieChartList = new ArrayList<NetSpendMonthlyChartDto>();
            for(TaxSpendByMonthDto taxSpend : taxSpendList){
                if(taxSpend != null){
                    taxSpendPieChartList.add(new NetSpendMonthlyChartDto(taxSpend));
                }
            }
            taxSpendJson = JSONUtil.prepareMonthlyChartJson(taxSpendPieChartList);
        }
        return taxSpendJson;
    }

    private JSONObject loadTaxSpendByCarrJson(DashboardsFilterCriteria filter) throws JSONException {
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

    private JSONObject loadTaxSpendJson(DashboardsFilterCriteria filter) throws JSONException {
        JSONObject taxSpendJson = null;
        List<TaxSpendDto> taxSpendList = dashboardsService.getTaxSpend(filter);
        List<NetSpendCommonDto> netSpendList = null;
        if(taxSpendList != null && taxSpendList.size() > 0){
            netSpendList = new ArrayList<NetSpendCommonDto>();
            for(TaxSpendDto taxSpend : taxSpendList){
                if(taxSpend != null){
                    netSpendList.add(new NetSpendCommonDto(taxSpend));
                }
            }
            taxSpendJson = JSONUtil.prepareCommonSpendJson(netSpendList);
        }
        return taxSpendJson;
    }

    private JSONObject loadAccessorialSpendJsonData(AccessorialSpendConstant accSpendType, DashboardsFilterCriteria filter) throws JSONException {
        JSONObject accSpendJson = null;
        switch (accSpendType){
            case TOP_ACCESSORIAL_SPEND:
                accSpendJson = loadTopAccessorialSpendJson(filter);
                break;
            case TOP_ACCESSORIAL_SPEND_BY_CARRIER:
                accSpendJson = loadTopAccessorialSpendByCarrierJson(filter);
                break;
            case TOP_ACCESSORIAL_SPEND_BY_MONTH:
                accSpendJson = loadTopAccessorialSpendByMonthJson(filter);
                break;
            case ACCESSORIAL_SPEND:
                accSpendJson = loadAccessorialSpendJson(filter);
                break;
            case ACCESSORIAL_SPEND_BY_CARRIER:
                accSpendJson = loadAccessorialSpendByCarrierJson(filter);
                break;
            case ACCESSORIAL_SPEND_BY_MONTH:
                accSpendJson = loadAccessorialSpendByMonthJson(filter);
                break;
            default:
                throw new MethodNotFoundException("Method param value not matched");
        }
        return accSpendJson;
    }

    private JSONObject loadAccessorialSpendByMonthJson(DashboardsFilterCriteria filter) throws JSONException {
        JSONObject accSpendJson = null;
        List<AccessorialSpendDto> accSpendList = dashboardsService.getAccessorialSpendByMonth(filter, false);
        List<NetSpendMonthlyChartDto> chartDataList = null;
        if(accSpendList != null){
            chartDataList = new ArrayList<NetSpendMonthlyChartDto>();
            for(AccessorialSpendDto accSpend : accSpendList){
                if(accSpend != null){
                    chartDataList.add(new NetSpendMonthlyChartDto(accSpend));
                }
            }
            accSpendJson = JSONUtil.prepareMonthlyChartJson(chartDataList);
        }
        return accSpendJson;
    }

    private JSONObject loadAccessorialSpendByCarrierJson(DashboardsFilterCriteria filter) throws JSONException {
        JSONObject accSpendJson = null;
        List<AccessorialSpendDto> accSpendList = dashboardsService.getTopAccessorialSpendByCarrier(filter, false);
        if(accSpendList != null && accSpendList.size() > 0){
            List<CommonValuesForChartDto> commonValList = null;
            if(accSpendList != null && accSpendList.size() > 0){
                commonValList = new ArrayList<CommonValuesForChartDto>();
                for(AccessorialSpendDto accSpend : accSpendList){
                    if(accSpend != null){
                        commonValList.add(new CommonValuesForChartDto(accSpend));
                    }
                }
                accSpendJson = JSONUtil.prepareCommonJsonForChart(commonValList);
            }
        }
        return accSpendJson;
    }

    private JSONObject loadAccessorialSpendJson(DashboardsFilterCriteria filter) throws JSONException {
        JSONObject accSpendJson = null;
        List<AccessorialSpendDto> accSpendList = dashboardsService.getAccessorialSpend(filter, false);
        List<NetSpendCommonDto> netSpendList = null;
        if(accSpendList != null && accSpendList.size() > 0){
            netSpendList = new ArrayList<NetSpendCommonDto>();
            for(AccessorialSpendDto accSpend : accSpendList){
                if(accSpend != null){
                    netSpendList.add(new NetSpendCommonDto(accSpend));
                }
            }
            accSpendJson = JSONUtil.prepareCommonSpendJson(netSpendList);
        }
        return accSpendJson;
    }

    private JSONObject loadTopAccessorialSpendByMonthJson(DashboardsFilterCriteria filter) throws JSONException {
        JSONObject accSpendJson = null;
        List<AccessorialSpendDto> topAccSpendList = dashboardsService.getTopAccessorialSpendByMonth(filter, false);
        List<NetSpendMonthlyChartDto> chartDataList = null;
        if(topAccSpendList != null){
            chartDataList = new ArrayList<NetSpendMonthlyChartDto>();
            for(AccessorialSpendDto accSpend : topAccSpendList){
                if(accSpend != null){
                    chartDataList.add(new NetSpendMonthlyChartDto(accSpend));
                }
            }
            accSpendJson = JSONUtil.prepareMonthlyChartJson(chartDataList);
        }
        return accSpendJson;
    }

    private JSONObject loadTopAccessorialSpendByCarrierJson(DashboardsFilterCriteria filter) throws JSONException {
        JSONObject accSpendJson = null;
        List<AccessorialSpendDto> topAccSpendList = dashboardsService.getTopAccessorialSpendByCarrier(filter, false);
        List<CommonValuesForChartDto> commonValList = null;
        if(topAccSpendList != null && topAccSpendList.size() > 0){
            commonValList = new ArrayList<CommonValuesForChartDto>();
            for(AccessorialSpendDto accSpend : topAccSpendList){
                if(accSpend != null){
                    commonValList.add(new CommonValuesForChartDto(accSpend));
                }
            }
            accSpendJson = JSONUtil.prepareCommonJsonForChart(commonValList);
        }
        return accSpendJson;
    }

    private JSONObject loadTopAccessorialSpendJson(DashboardsFilterCriteria filter) throws JSONException {
        JSONObject accSpendJson = null;
        List<AccessorialSpendDto> topAccSpendList = dashboardsService.getTopAccessorialSpend(filter, false);
        if(topAccSpendList != null && topAccSpendList.size() > 0){
            accSpendJson = JSONUtil.prepareTopAccessorialSpendJson(topAccSpendList);
        }
        return accSpendJson;
    }

    private JSONObject loadShipmentOverviewJsonData(ShipmentOverviewConstant shipmentOverviewType, DashboardsFilterCriteria filter) throws JSONException {
        JSONObject avgShipmentJson = null;

        switch (shipmentOverviewType){
            case AVG_SPEND_PER_SHIPMT:
                avgShipmentJson = loadAvgSpendPerShipmtJson(filter);
                break;
            case AVG_WEIGHT_BY_MODE_SHIPMT:
                avgShipmentJson = loadNetSpendByOverTimeJson(filter);
                break;
            case NET_SPEND_BY_CARRIER:
                avgShipmentJson = loadNetSpendByCarrierJson(filter);
                break;
            case NET_SPEND_BY_MONTH:
                avgShipmentJson = loadNetSpendByMonthJson(filter);
                break;
            default:
                throw new MethodNotFoundException("Method param value not matched");
        }
        return avgShipmentJson;
    }



    private JSONObject loadAvgSpendPerShipmtJson(DashboardsFilterCriteria filter) throws JSONException {
        JSONObject avgSpendShipmentJson = null;

        List<AverageSpendPerShipmentDto> avgPerShipmentList = dashboardsService.getAvgSpendPerShipment(filter,false);
        if(avgPerShipmentList != null && avgPerShipmentList.size() > 0){
            avgSpendShipmentJson = JSONUtil.prepareAverageWeightOrSpendJson(avgPerShipmentList);
        }
        return avgSpendShipmentJson;
    }

    private JSONObject loadAvgWeightByModeShipmtJson(DashboardsFilterCriteria filter) throws JSONException {
        JSONObject avgShipmentJson = null;

        List<AverageWeightModeShipmtDto> avgShipmentList = dashboardsService.getAverageWeightByModeShipmt(filter,false);
        if(avgShipmentList != null && avgShipmentList.size() > 0){
            avgShipmentJson = JSONUtil.prepareAverageWeightJson(avgShipmentList);
        }
        return avgShipmentJson;
    }

}
