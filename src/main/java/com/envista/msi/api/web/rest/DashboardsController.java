package com.envista.msi.api.web.rest;

import com.envista.msi.api.domain.util.DashboardUtil;
import com.envista.msi.api.service.DashboardsService;
import com.envista.msi.api.service.UserService;
import com.envista.msi.api.web.rest.dto.UserProfileDto;
import com.envista.msi.api.web.rest.dto.dashboard.DashboardAppliedFilterDto;
import com.envista.msi.api.web.rest.dto.dashboard.accessorialspend.AccessorialSpendDto;
import com.envista.msi.api.web.rest.dto.dashboard.auditactivity.*;
import com.envista.msi.api.web.rest.dto.dashboard.common.CommonValuesForChartDto;
import com.envista.msi.api.web.rest.dto.dashboard.common.NetSpendCommonDto;
import com.envista.msi.api.web.rest.dto.dashboard.common.NetSpendMonthlyChartDto;
import com.envista.msi.api.web.rest.dto.dashboard.DashboardsFilterCriteria;
import com.envista.msi.api.web.rest.dto.dashboard.netspend.*;
import com.envista.msi.api.web.rest.dto.dashboard.shipmentoverview.*;
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

    enum TaxSpendConstant{
        TAX_SPEND,
        TAX_SPEND_BY_CARRIER,
        TAX_SPEND_BY_MONTH;
    }

    enum NetSpendConstant{
        NET_SPEND_BY_MODE,
        NET_SPEND_OVER_TIME_BY_MONTH,
        NET_SPEND_BY_OVER_TIME,
        NET_SPEND_BY_CARRIER,
        NET_SPEND_BY_MONTH;
    }

    enum AccessorialSpendConstant{
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

    enum InboundSpendConstant{
        INBOUND_SPEND,
        INBOUND_SPEND_BY_MONTH;
    }

    enum OutboundSpendConstant{
        OUTBOUND_SPEND,
        OUTBOUND_SPEND_BY_MONTH;
    }
    
    enum InvoiceStatusCountConstant{
        INVOICE_STATUS_COUNT,
        INVOICE_STATUS_COUNT_BY_CARRIER,
        INVOICE_STATUS_COUNT_BY_MONTH
    }

    enum InvoiceStatusAmountConstant{
        INVOICE_STATUS_AMOUNT,
        INVOICE_STATUS_AMOUNT_BY_CARRIER,
        INVOICE_STATUS_AMOUNT_BY_MONTH
    }

    enum InvoiceMethodScoreConstant{
        INVOICE_METHOD_SCORE,
        INVOICE_METHOD_SCORE_BY_CARRIER,
        INVOICE_METHOD_SCORE_BY_MONTH
    }

    enum OrderMatchConstant{
        ORDER_MATCH,
        ORDER_MATCH_BY_CARRIER,
        ORDER_MATCH_BY_MONTH
    }

    enum BilledVsApprovedConstant{
        BILLED_VS_APPROVED,
        BILLED_VS_APPROVED_BY_MONTH
    }

    enum RecoveryAdjustmentConstants{
        RECOVERY_ADJUSTMENT,
        RECOVERY_ADJUSTMENT_BY_CARRIER,
        RECOVERY_ADJUSTMENT_BY_MONTH
    }

    enum RecoveryServiceConstants{
        RECOVERY_SERVICE,
        RECOVERY_SERVICE_BY_MONTH
    }

    enum PackageExceptionConstants{
        PACKAGE_EXCEPTION,
        PACKAGE_EXCEPTION_BY_CARRIER,
        PACKAGE_EXCEPTION_BY_MONTH,
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

    @RequestMapping(value = "/serviceUsagePerf", method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.OPTIONS}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<String> getServiceLevelUsageAndPerformance(){
        JSONObject serviceLvlPerf = null;
        try{
            UserProfileDto user = getUserProfile();
            if(null == user){
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }
            DashboardsFilterCriteria filter = loadAppliedFilters(user.getUserId());
            List<ServiceLevelUsageAndPerformanceDto> serviceLevelUsageAndPerfList = dashboardsService.getServiceLevelUsageAndPerformance(filter, false);
            JSONObject sfJson = JSONUtil.prepareServiceLevelUsageAndPerfromanceJson(serviceLevelUsageAndPerfList);
            serviceLvlPerf = sfJson != null ? sfJson : new JSONObject();
        }catch(Exception e){
            return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<String>(serviceLvlPerf.toString(), HttpStatus.OK);
    }

    @RequestMapping(value = "/inboundSpend", method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.OPTIONS}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<String> getInboundSpend(){
        JSONObject inboundSpendJson = null;
        try{
            UserProfileDto user = getUserProfile();
            if(null == user){
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }
            DashboardsFilterCriteria filter = loadAppliedFilters(user.getUserId());
            JSONObject inbSpendJson = loadInboundSpendJsonData(InboundSpendConstant.INBOUND_SPEND, filter);
            inboundSpendJson = inbSpendJson != null ? inbSpendJson : new JSONObject();
        }catch (Exception e){
            return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<String>(inboundSpendJson.toString(), HttpStatus.OK);
    }

    @RequestMapping(value = "/inboundSpendByMnth", method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.OPTIONS}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<String> getInboundSpendByMonth(@RequestParam String carrierId, @RequestParam String invoiceDate){
        JSONObject inboundSpendJson = null;
        try{
            UserProfileDto user = getUserProfile();
            if(null == user){
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }
            DashboardsFilterCriteria filter = loadAppliedFilters(user.getUserId());
            if(filter != null){
                if(carrierId != null && !carrierId.isEmpty()){
                    filter.setCarriers(carrierId);
                }
                if(invoiceDate != null && !invoiceDate.isEmpty()){
                    DashboardUtil.setDatesFromMonth(filter, invoiceDate);
                }
            }
            JSONObject inbSpendJson = loadInboundSpendJsonData(InboundSpendConstant.INBOUND_SPEND_BY_MONTH, filter);
            inboundSpendJson = inbSpendJson != null ? inbSpendJson : new JSONObject();
        }catch (Exception e){
            return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<String>(inboundSpendJson.toString(), HttpStatus.OK);
    }

    @RequestMapping(value = "/outboundSpend", method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.OPTIONS}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<String> getoutboundSpend(){
        JSONObject outboundSpendJson = null;
        try{
            UserProfileDto user = getUserProfile();
            if(null == user){
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }
            DashboardsFilterCriteria filter = loadAppliedFilters(user.getUserId());
            JSONObject outbSpendJson = loadOutboundSpendJsonData(OutboundSpendConstant.OUTBOUND_SPEND, filter);
            outboundSpendJson = outbSpendJson != null ? outbSpendJson : new JSONObject();
        }catch (Exception e){
            return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<String>(outboundSpendJson.toString(), HttpStatus.OK);
    }

    @RequestMapping(value = "/outboundSpendByMnth", method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.OPTIONS}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<String> getoutboundSpendByMonth(@RequestParam String carrierId, @RequestParam String invoiceDate){
        JSONObject outboundSpendJson = null;
        try{
            UserProfileDto user = getUserProfile();
            if(null == user){
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }
            DashboardsFilterCriteria filter = loadAppliedFilters(user.getUserId());
            if(filter != null){
                if(carrierId != null && !carrierId.isEmpty()){
                    filter.setCarriers(carrierId);
                }
                if(invoiceDate != null && !invoiceDate.isEmpty()){
                    DashboardUtil.setDatesFromMonth(filter, invoiceDate);
                }
            }
            JSONObject outbSpendJson = loadOutboundSpendJsonData(OutboundSpendConstant.OUTBOUND_SPEND_BY_MONTH, filter);
            outboundSpendJson = outbSpendJson != null ? outbSpendJson : new JSONObject();
        }catch (Exception e){
            return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<String>(outboundSpendJson.toString(), HttpStatus.OK);
    }

    private JSONObject loadOutboundSpendJsonData(OutboundSpendConstant outboundSpendType, DashboardsFilterCriteria filter) throws JSONException {
        JSONObject inboundSpendJson = null;
        switch (outboundSpendType){
            case OUTBOUND_SPEND:
                inboundSpendJson = loadOutboundSpendJson(filter);
                break;
            case OUTBOUND_SPEND_BY_MONTH:
                inboundSpendJson = loadOutboundSpendByMonthJson(filter);
                break;
            default:
                throw new MethodNotFoundException("Method param value not matched");
        }
        return inboundSpendJson;
    }

    private JSONObject loadOutboundSpendByMonthJson(DashboardsFilterCriteria filter) throws JSONException {
        List<OutboundSpendDto> outboundSpendList = dashboardsService.getOutboundSpendByMonth(filter, false);
        List<NetSpendMonthlyChartDto> monthlyChartDtos = NetSpendMonthlyChartDto.buildOutboundSpendListToNetSpendMonthlyChartList(outboundSpendList);
        return JSONUtil.prepareMonthlyChartJson(monthlyChartDtos);
    }

    private JSONObject loadOutboundSpendJson(DashboardsFilterCriteria filter) throws JSONException {
        List<OutboundSpendDto> outboundSpendList = dashboardsService.getOutboundSpend(filter, false);
        List<NetSpendCommonDto> spendlist = NetSpendCommonDto.buildOutboundSpendListToNetSpendCommonList(outboundSpendList);
        return JSONUtil.prepareInAndOutBuondJson(spendlist);
    }

    private JSONObject loadInboundSpendJsonData(InboundSpendConstant inboundSpendType, DashboardsFilterCriteria filter) throws JSONException {
        JSONObject inboundSpendJson = null;
        switch (inboundSpendType){
            case INBOUND_SPEND:
                inboundSpendJson = loadInboundSpendJson(filter);
                break;
            case INBOUND_SPEND_BY_MONTH:
                inboundSpendJson = loadInboundSpendByMonthJson(filter);
                break;
            default:
                throw new MethodNotFoundException("Method param value not matched");
        }
        return inboundSpendJson;
    }

    private JSONObject loadInboundSpendByMonthJson(DashboardsFilterCriteria filter) throws JSONException {
        List<InboundSpendDto> inboundSpendList = dashboardsService.getInboundSpendByMonth(filter, false);
        List<NetSpendMonthlyChartDto> monthlyChartDtos = NetSpendMonthlyChartDto.buildInboundSpendListToNetSpendMonthlyChartList(inboundSpendList);
        return JSONUtil.prepareMonthlyChartJson(monthlyChartDtos);
    }

    private JSONObject loadInboundSpendJson(DashboardsFilterCriteria filter) throws JSONException {
        List<InboundSpendDto> inboundSpendList = dashboardsService.getInboundSpend(filter, false);
        List<NetSpendCommonDto> spendlist = NetSpendCommonDto.buildInboundSpendListToNetSpendCommonList(inboundSpendList);
        return JSONUtil.prepareInAndOutBuondJson(spendlist);
    }
    
    @RequestMapping(value = "/invStsCnt", method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.OPTIONS}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<String> getInvoiceStatusCount(){
        JSONObject invStsCountJson = null;
        try {
            UserProfileDto user = getUserProfile();
            if (null == user) {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }
            DashboardsFilterCriteria filter = loadAppliedFilters(user.getUserId());
            JSONObject stsCntJson = loadInvoiceStatusCountJsonData(InvoiceStatusCountConstant.INVOICE_STATUS_COUNT, filter);
            invStsCountJson = (stsCntJson != null ? stsCntJson : new JSONObject());
        }catch(Exception e){
            return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<String>(invStsCountJson.toString(), HttpStatus.OK);
    }

    @RequestMapping(value = "/invStsCntByCarr", method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.OPTIONS}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<String> getInvoiceStatusCountByCarrier(@RequestParam String invoiceStatusId){
        JSONObject invStsCountJson = null;
        try {
            UserProfileDto user = getUserProfile();
            if (null == user) {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }
            DashboardsFilterCriteria filter = loadAppliedFilters(user.getUserId());
            if(filter != null){
                filter.setInvoiceStatusId(invoiceStatusId);
            }
            JSONObject stsCntJson = loadInvoiceStatusCountJsonData(InvoiceStatusCountConstant.INVOICE_STATUS_COUNT_BY_CARRIER, filter);
            invStsCountJson = (stsCntJson != null ? stsCntJson : new JSONObject());
        }catch(Exception e){
            return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<String>(invStsCountJson.toString(), HttpStatus.OK);
    }

    @RequestMapping(value = "/invStsCntByMnth", method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.OPTIONS}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<String> getInvoiceStatusCountByMonth(@RequestParam String carrierId, @RequestParam String invoiceStatusId){
        JSONObject invStsCountJson = null;
        try {
            UserProfileDto user = getUserProfile();
            if (null == user) {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }
            DashboardsFilterCriteria filter = loadAppliedFilters(user.getUserId());
            if(filter != null){
                filter.setCarriers(carrierId);
                filter.setInvoiceStatusId(invoiceStatusId);
            }
            JSONObject stsCntJson = loadInvoiceStatusCountJsonData(InvoiceStatusCountConstant.INVOICE_STATUS_COUNT_BY_MONTH, filter);
            invStsCountJson = (stsCntJson != null ? stsCntJson : new JSONObject());
        }catch(Exception e){
            return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<String>(invStsCountJson.toString(), HttpStatus.OK);
    }

    @RequestMapping(value = "/invStsAmt", method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.OPTIONS}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<String> getInvoiceStatusAmount(){
        JSONObject invStsAmountJson = null;
        try {
            UserProfileDto user = getUserProfile();
            if (null == user) {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }
            DashboardsFilterCriteria filter = loadAppliedFilters(user.getUserId());
            JSONObject stsAmtJson = loadInvoiceStatusAmountJsonData(InvoiceStatusAmountConstant.INVOICE_STATUS_AMOUNT, filter);
            invStsAmountJson = (stsAmtJson != null ? stsAmtJson : new JSONObject());
        }catch(Exception e){
            return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<String>(invStsAmountJson.toString(), HttpStatus.OK);
    }

    @RequestMapping(value = "/invStsAmtByCarr", method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.OPTIONS}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<String> getInvoiceStatusAmountByCarrier(@RequestParam String invoiceStatusId){
        JSONObject invStsAmountJson = null;
        try {
            UserProfileDto user = getUserProfile();
            if (null == user) {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }
            DashboardsFilterCriteria filter = loadAppliedFilters(user.getUserId());
            if(filter != null){
                filter.setInvoiceStatusId(invoiceStatusId);
            }
            JSONObject stsAmtJson = loadInvoiceStatusAmountJsonData(InvoiceStatusAmountConstant.INVOICE_STATUS_AMOUNT_BY_CARRIER, filter);
            invStsAmountJson = (stsAmtJson != null ? stsAmtJson : new JSONObject());
        }catch(Exception e){
            return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<String>(invStsAmountJson.toString(), HttpStatus.OK);
    }

    @RequestMapping(value = "/invStsAmtByMnth", method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.OPTIONS}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<String> getInvoiceStatusAmountByMonth(@RequestParam String carrierId, @RequestParam String invoiceStatusId){
        JSONObject invStsAmountJson = null;
        try {
            UserProfileDto user = getUserProfile();
            if (null == user) {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }
            DashboardsFilterCriteria filter = loadAppliedFilters(user.getUserId());
            if(filter != null){
                filter.setCarriers(carrierId);
                filter.setInvoiceStatusId(invoiceStatusId);
            }
            JSONObject stsAmtJson = loadInvoiceStatusAmountJsonData(InvoiceStatusAmountConstant.INVOICE_STATUS_AMOUNT_BY_MONTH, filter);
            invStsAmountJson = (stsAmtJson != null ? stsAmtJson : new JSONObject());
        }catch(Exception e){
            return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<String>(invStsAmountJson.toString(), HttpStatus.OK);
    }

    @RequestMapping(value = "/invMthdScore", method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.OPTIONS}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<String> getInvoiceMethodScore(){
        JSONObject invMethodScoreJson = null;
        try {
            UserProfileDto user = getUserProfile();
            if (null == user) {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }
            DashboardsFilterCriteria filter = loadAppliedFilters(user.getUserId());
            JSONObject invScoreJson = loadInvoiceMethodScoreJsonData(InvoiceMethodScoreConstant.INVOICE_METHOD_SCORE, filter);
            invMethodScoreJson = (invScoreJson != null ? invScoreJson : new JSONObject());
        }catch(Exception e){
            return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<String>(invMethodScoreJson.toString(), HttpStatus.OK);
    }

    @RequestMapping(value = "/invMthdScoreByCarr", method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.OPTIONS}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<String> getInvoiceMethodScoreByCarrier(@RequestParam String invoiceMethod){
        JSONObject invMethodScoreJson = null;
        try {
            UserProfileDto user = getUserProfile();
            if (null == user) {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }
            DashboardsFilterCriteria filter = loadAppliedFilters(user.getUserId());
            if(filter != null){
                filter.setInvoiceMethod(invoiceMethod);
            }
            JSONObject invScoreJson = loadInvoiceMethodScoreJsonData(InvoiceMethodScoreConstant.INVOICE_METHOD_SCORE_BY_CARRIER, filter);
            invMethodScoreJson = (invScoreJson != null ? invScoreJson : new JSONObject());
        }catch(Exception e){
            return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<String>(invMethodScoreJson.toString(), HttpStatus.OK);
    }

    @RequestMapping(value = "/invMthdScoreByMnth", method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.OPTIONS}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<String> getInvoiceMethodScoreByMonth(@RequestParam String invoiceMethod, @RequestParam String carrierId){
        JSONObject invMethodScoreJson = null;
        try {
            UserProfileDto user = getUserProfile();
            if (null == user) {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }
            DashboardsFilterCriteria filter = loadAppliedFilters(user.getUserId());
            if(filter != null){
                filter.setInvoiceMethod(invoiceMethod);
                filter.setCarriers(carrierId);
            }
            JSONObject invScoreJson = loadInvoiceMethodScoreJsonData(InvoiceMethodScoreConstant.INVOICE_METHOD_SCORE_BY_MONTH, filter);
            invMethodScoreJson = (invScoreJson != null ? invScoreJson : new JSONObject());
        }catch(Exception e){
            return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<String>(invMethodScoreJson.toString(), HttpStatus.OK);
    }

    @RequestMapping(value = "/orderMatch", method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.OPTIONS}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<String> getOrderMatchStatus(){
        JSONObject orderMatchJson = null;
        try {
            UserProfileDto user = getUserProfile();
            if (null == user) {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }
            DashboardsFilterCriteria filter = loadAppliedFilters(user.getUserId());
            JSONObject ordMchJson = loadOrderMatchJsonData(OrderMatchConstant.ORDER_MATCH, filter);
            orderMatchJson = (ordMchJson != null ? ordMchJson : new JSONObject());
        }catch(Exception e){
            return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<String>(orderMatchJson.toString(), HttpStatus.OK);
    }

    @RequestMapping(value = "/orderMatchByCarr", method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.OPTIONS}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<String> getOrderMatchByCarrier(@RequestParam String orderMatchValue){
        JSONObject orderMatchJson = null;
        try {
            UserProfileDto user = getUserProfile();
            if (null == user) {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }
            DashboardsFilterCriteria filter = loadAppliedFilters(user.getUserId());
            if(filter != null){
                filter.setOrderMatch(orderMatchValue);
            }
            JSONObject ordMchJson = loadOrderMatchJsonData(OrderMatchConstant.ORDER_MATCH_BY_CARRIER, filter);
            orderMatchJson = (ordMchJson != null ? ordMchJson : new JSONObject());
        }catch(Exception e){
            return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<String>(orderMatchJson.toString(), HttpStatus.OK);
    }

    @RequestMapping(value = "/orderMatchByMnth", method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.OPTIONS}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<String> getOrderMatchByMonth(@RequestParam String carrierId, @RequestParam String orderMatchValue){
        JSONObject orderMatchJson = null;
        try {
            UserProfileDto user = getUserProfile();
            if (null == user) {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }
            DashboardsFilterCriteria filter = loadAppliedFilters(user.getUserId());
            if(filter != null){
                filter.setCarriers(carrierId);
                filter.setOrderMatch(orderMatchValue);
            }
            JSONObject ordMchJson = loadOrderMatchJsonData(OrderMatchConstant.ORDER_MATCH_BY_MONTH, filter);
            orderMatchJson = (ordMchJson != null ? ordMchJson : new JSONObject());
        }catch(Exception e){
            return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<String>(orderMatchJson.toString(), HttpStatus.OK);
    }

    @RequestMapping(value = "/billedVsApproved", method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.OPTIONS}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<String> getBilledVsApproved(){
        JSONObject billedVsApprovedJson = null;
        try {
            UserProfileDto user = getUserProfile();
            if (null == user) {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }
            DashboardsFilterCriteria filter = loadAppliedFilters(user.getUserId());
            JSONObject bvaJson = loadBilledVsApprovedJsonData(BilledVsApprovedConstant.BILLED_VS_APPROVED, filter);
            billedVsApprovedJson = (bvaJson != null ? bvaJson : new JSONObject());
        }catch(Exception e){
            return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<String>(billedVsApprovedJson.toString(), HttpStatus.OK);
    }

    @RequestMapping(value = "/billedVsApprovedByMnth", method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.OPTIONS}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<String> getBilledVsApprovedByMonth(@RequestParam String carrierId, @RequestParam String billedVsApproved){
        JSONObject billedVsApprovedJson = null;
        try {
            UserProfileDto user = getUserProfile();
            if (null == user) {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }
            DashboardsFilterCriteria filter = loadAppliedFilters(user.getUserId());
            if(filter != null){
                filter.setCarriers(carrierId);
                filter.setBilledVsApproved(billedVsApproved);
            }
            JSONObject bvaJson = loadBilledVsApprovedJsonData(BilledVsApprovedConstant.BILLED_VS_APPROVED_BY_MONTH, filter);
            billedVsApprovedJson = (bvaJson != null ? bvaJson : new JSONObject());
        }catch(Exception e){
            return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<String>(billedVsApprovedJson.toString(), HttpStatus.OK);
    }

    @RequestMapping(value = "/recovAdj", method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.OPTIONS}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<String> getRecoveryAdjustment(){
        JSONObject recovAdjJson = null;
        try{
            UserProfileDto user = getUserProfile();
            if (null == user) {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }
            DashboardsFilterCriteria filter = loadAppliedFilters(user.getUserId());
            JSONObject raJson = loadRecoveryAdjustmentJsonData(RecoveryAdjustmentConstants.RECOVERY_ADJUSTMENT, filter);
            recovAdjJson = (raJson != null ? raJson : new JSONObject());
        }catch (Exception e){
            return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<String>(recovAdjJson.toString(), HttpStatus.OK);
    }

    @RequestMapping(value = "/recovAdjByCarr", method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.OPTIONS}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<String> getRecoveryAdjustmentByCarrier(@RequestParam String service, @RequestParam String invoiceDate){
        JSONObject recovAdjJson = null;
        try{
            UserProfileDto user = getUserProfile();
            if (null == user) {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }
            DashboardsFilterCriteria filter = loadAppliedFilters(user.getUserId());
            if(filter != null){
                if (service != null && !service.isEmpty()){
                    filter.setService(service);
                }
                if(invoiceDate != null && !invoiceDate.isEmpty()){
                    DashboardUtil.setDatesFromMonth(filter, invoiceDate);
                }
            }
            JSONObject raJson = loadRecoveryAdjustmentJsonData(RecoveryAdjustmentConstants.RECOVERY_ADJUSTMENT_BY_CARRIER, filter);
            recovAdjJson = (raJson != null ? raJson : new JSONObject());
        }catch (Exception e){
            return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<String>(recovAdjJson.toString(), HttpStatus.OK);
    }

    @RequestMapping(value = "/recovAdjByMnth", method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.OPTIONS}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<String> getRecoveryAdjustmentByMonth(@RequestParam String service, @RequestParam String invoiceDate, @RequestParam String carrierId){
        JSONObject recovAdjJson = null;
        try{
            UserProfileDto user = getUserProfile();
            if (null == user) {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }
            DashboardsFilterCriteria filter = loadAppliedFilters(user.getUserId());
            if(filter != null){
                if (service != null && !service.isEmpty()){
                    filter.setService(service);
                }
                if(invoiceDate != null && !invoiceDate.isEmpty()){
                    DashboardUtil.setDatesFromMonth(filter, invoiceDate);
                }
                if(carrierId != null && !carrierId.isEmpty()){
                    filter.setCarriers(carrierId);
                }
            }
            JSONObject raJson = loadRecoveryAdjustmentJsonData(RecoveryAdjustmentConstants.RECOVERY_ADJUSTMENT_BY_MONTH, filter);
            recovAdjJson = (raJson != null ? raJson : new JSONObject());
        }catch (Exception e){
            return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<String>(recovAdjJson.toString(), HttpStatus.OK);
    }

    @RequestMapping(value = "/recovServ", method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.OPTIONS}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<String> getRecoveryServices(){
        JSONObject recovServJson = null;
        try{
            UserProfileDto user = getUserProfile();
            if (null == user) {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }
            DashboardsFilterCriteria filter = loadAppliedFilters(user.getUserId());
            JSONObject rsJson = loadRecoveryServicesJsonData(RecoveryServiceConstants.RECOVERY_SERVICE, filter);
            recovServJson = (rsJson != null ? rsJson : new JSONObject());
        }catch (Exception e){
            return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<String>(recovServJson.toString(), HttpStatus.OK);
    }

    @RequestMapping(value = "/recovServByMnth", method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.OPTIONS}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<String> getRecoveryServicesByMonth(@RequestParam String carrierId, @RequestParam String service){
        JSONObject recovServJson = null;
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
                if(service != null && !service.isEmpty()){
                    filter.setService(service);
                }
            }
            JSONObject rsJson = loadRecoveryServicesJsonData(RecoveryServiceConstants.RECOVERY_SERVICE_BY_MONTH, filter);
            recovServJson = (rsJson != null ? rsJson : new JSONObject());
        }catch (Exception e){
            return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<String>(recovServJson.toString(), HttpStatus.OK);
    }

    @RequestMapping(value = "/pkgExcp", method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.OPTIONS}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<String> getPackageExceptions(){
        JSONObject pkgExcpJson = null;
        try{
            UserProfileDto user = getUserProfile();
            if (null == user) {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }
            DashboardsFilterCriteria filter = loadAppliedFilters(user.getUserId());
            JSONObject rsJson = loadPackageExceptionsJsonData(PackageExceptionConstants.PACKAGE_EXCEPTION, filter);
            pkgExcpJson = (rsJson != null ? rsJson : new JSONObject());
        }catch (Exception e){
            return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<String>(pkgExcpJson.toString(), HttpStatus.OK);
    }

    @RequestMapping(value = "/pkgExcpByCarr", method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.OPTIONS}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<String> getPackageExceptionsByCarrier(@RequestParam String invoiceDate, @RequestParam String deliveryFlag){
        JSONObject pkgExcpJson = null;
        try{
            UserProfileDto user = getUserProfile();
            if (null == user) {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }
            DashboardsFilterCriteria filter = loadAppliedFilters(user.getUserId());
            if(filter != null){
                if (deliveryFlag != null && !deliveryFlag.isEmpty())
                    filter.setDeliveryFlag(deliveryFlag);
                DashboardUtil.setDatesFromMonth(filter, invoiceDate);
            }
            JSONObject rsJson = loadPackageExceptionsJsonData(PackageExceptionConstants.PACKAGE_EXCEPTION_BY_CARRIER, filter);
            pkgExcpJson = (rsJson != null ? rsJson : new JSONObject());
        }catch (Exception e){
            return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<String>(pkgExcpJson.toString(), HttpStatus.OK);
    }

    @RequestMapping(value = "/pkgExcpByMnth", method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.OPTIONS}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<String> getPackageExceptionsByMonth(@RequestParam String invoiceDate, @RequestParam String deliveryFlag,@RequestParam String carrierId){
        JSONObject pkgExcpJson = null;
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
                if (deliveryFlag != null && !deliveryFlag.isEmpty()){
                    filter.setDeliveryFlag(deliveryFlag);
                }
                DashboardUtil.setDatesFromMonth(filter, invoiceDate);
            }
            JSONObject rsJson = loadPackageExceptionsJsonData(PackageExceptionConstants.PACKAGE_EXCEPTION_BY_MONTH, filter);
            pkgExcpJson = (rsJson != null ? rsJson : new JSONObject());
        }catch (Exception e){
            return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<String>(pkgExcpJson.toString(), HttpStatus.OK);
    }

    private JSONObject loadPackageExceptionsJsonData(PackageExceptionConstants packageExceptionType, DashboardsFilterCriteria filter) throws JSONException {
        JSONObject pkgExcpJson = null;
        switch (packageExceptionType){
            case PACKAGE_EXCEPTION:
                pkgExcpJson = loadPackageExceptionsJson(filter);
                break;
            case PACKAGE_EXCEPTION_BY_CARRIER:
                pkgExcpJson = loadPackageExceptionsByCarrierJson(filter);
                break;
            case PACKAGE_EXCEPTION_BY_MONTH:
                pkgExcpJson = loadPackageExceptionsByMonthJson(filter);
                break;
            default:
                throw new MethodNotFoundException("Method param value not matched");
        }
        return pkgExcpJson;
    }

    private JSONObject loadPackageExceptionsByMonthJson(DashboardsFilterCriteria filter) throws JSONException {
        JSONObject pkgExcpJson = null;
        List<PackageExceptionDto> packageExceptionList = dashboardsService.getPackageExceptionsByMonth(filter, false);
        if(packageExceptionList != null && !packageExceptionList.isEmpty()){
            List<NetSpendMonthlyChartDto> monthlyChartList = NetSpendMonthlyChartDto.buildackageExceptionListToMonthlyChartList(packageExceptionList);
            pkgExcpJson = JSONUtil.prepareMonthlyChartJson(monthlyChartList);
        }
        return pkgExcpJson;
    }

    private JSONObject loadPackageExceptionsByCarrierJson(DashboardsFilterCriteria filter) throws JSONException {
        JSONObject pkgExcpJson = null;
        List<PackageExceptionDto> packageExceptionList = dashboardsService.getPackageExceptionsByCarrier(filter, false);
        if(packageExceptionList != null && !packageExceptionList.isEmpty()){
            List<CommonValuesForChartDto> commonValuesForChartList = CommonValuesForChartDto.buildPackageExceptionListToCommonValueForChartList(packageExceptionList);
            pkgExcpJson = JSONUtil.prepareCommonJsonForChart(commonValuesForChartList);
        }
        return pkgExcpJson;
    }

    private JSONObject loadPackageExceptionsJson(DashboardsFilterCriteria filter) throws JSONException {
        JSONObject pkgExcpJson = null;
        List<PackageExceptionDto> packageExceptionList = dashboardsService.getPackageExceptions(filter, false);
        if(packageExceptionList != null && !packageExceptionList.isEmpty()){
            pkgExcpJson = JSONUtil.preparePackageExceptionJson(packageExceptionList);
        }
        return pkgExcpJson;
    }

    private JSONObject loadRecoveryServicesJsonData(RecoveryServiceConstants recoveryServiceType, DashboardsFilterCriteria filter) throws JSONException {
        JSONObject recovServJson = null;
        switch (recoveryServiceType){
            case RECOVERY_SERVICE:
                recovServJson = loadRecoveryServicesJson(filter);
                break;
            case RECOVERY_SERVICE_BY_MONTH:
                recovServJson = loadRecoveryServicesByMonthJson(filter);
                break;
            default:
                throw new MethodNotFoundException("Method param value not matched");
        }
        return recovServJson;
    }

    private JSONObject loadRecoveryServicesByMonthJson(DashboardsFilterCriteria filter) throws JSONException {
        JSONObject recovServJson = null;
        List<RecoveryServiceDto> recoveryServiceList = dashboardsService.getRecoveryServicesByMonth(filter, false);
        if(recoveryServiceList != null && !recoveryServiceList.isEmpty()){
            List<NetSpendMonthlyChartDto> monthlyChartList = NetSpendMonthlyChartDto.buildRecoveryServiceListToMonthlyChartList(recoveryServiceList);
            recovServJson = JSONUtil.prepareMonthlyChartJson(monthlyChartList);
        }
        return recovServJson;
    }

    private JSONObject loadRecoveryServicesJson(DashboardsFilterCriteria filter) throws JSONException {
        JSONObject recovServJson = null;
        List<RecoveryServiceDto> recoveryServiceList = dashboardsService.getRecoveryServices(filter, false);
        if(recoveryServiceList != null && !recoveryServiceList.isEmpty()){
            recovServJson = JSONUtil.prepareTotalCreditRecoveryByServiceLevelJson(recoveryServiceList);
        }
        return recovServJson;
    }

    private JSONObject loadRecoveryAdjustmentJsonData(RecoveryAdjustmentConstants recoveryAdjustmentType, DashboardsFilterCriteria filter) throws JSONException {
        JSONObject recovAdjJson = null;
        switch (recoveryAdjustmentType){
            case RECOVERY_ADJUSTMENT:
                recovAdjJson = loadRecoveryAdjustmentJson(filter);
                break;
            case RECOVERY_ADJUSTMENT_BY_CARRIER:
                recovAdjJson = loadRecoveryAdjustmentByCarrierJson(filter);
                break;
            case RECOVERY_ADJUSTMENT_BY_MONTH:
                recovAdjJson = loadRecoveryAdjustmentByMonthJson(filter);
                break;
            default:
                throw new MethodNotFoundException("Method param value not matched");
        }
        return recovAdjJson;
    }

    private JSONObject loadRecoveryAdjustmentByMonthJson(DashboardsFilterCriteria filter) throws JSONException {
        JSONObject recovAdjJson = null;
        List<RecoveryAdjustmentDto> recoveryAdjustmentList = dashboardsService.getRecoveryAdjustmentByMonth(filter, false);
        if(recoveryAdjustmentList != null && !recoveryAdjustmentList.isEmpty()){
            List<NetSpendMonthlyChartDto> monthlyChartList = NetSpendMonthlyChartDto.buildRecoveryAdjustmentListToMonthlyChartList(recoveryAdjustmentList);
            recovAdjJson = JSONUtil.prepareMonthlyChartJson(monthlyChartList);
        }
        return recovAdjJson;
    }

    private JSONObject loadRecoveryAdjustmentByCarrierJson(DashboardsFilterCriteria filter) throws JSONException {
        JSONObject recovAdjJson = null;
        List<RecoveryAdjustmentDto> recoveryAdjustmentList = dashboardsService.getRecoveryAdjustmentByCarrier(filter, false);
        if(recoveryAdjustmentList != null && !recoveryAdjustmentList.isEmpty()){
            List<CommonValuesForChartDto> commonValuesForChartList = CommonValuesForChartDto.buildRecoveryAdjustmentListToCommonValueForChartList(recoveryAdjustmentList);
            recovAdjJson = JSONUtil.prepareCommonJsonForChart(commonValuesForChartList);
        }
        return recovAdjJson;
    }

    private JSONObject loadRecoveryAdjustmentJson(DashboardsFilterCriteria filter) throws JSONException {
        JSONObject recovAdjJson = null;
        List<RecoveryAdjustmentDto> recoveryAdjustmentList = dashboardsService.getRecoveryAdjustment(filter, false);
        if(recoveryAdjustmentList != null && !recoveryAdjustmentList.isEmpty()){
            recovAdjJson = JSONUtil.prepareRecoveryAdjustmentJson(recoveryAdjustmentList);
        }
        return recovAdjJson;
    }

    private JSONObject loadBilledVsApprovedJsonData(BilledVsApprovedConstant billedVsApprovedType, DashboardsFilterCriteria filter) throws JSONException {
        JSONObject billedVsApprovedJson = null;
        switch (billedVsApprovedType){
            case BILLED_VS_APPROVED:
                billedVsApprovedJson = loadBilledVsApprovedJson(filter);
                break;
            case BILLED_VS_APPROVED_BY_MONTH:
                billedVsApprovedJson = loadBilledVsApprovedByMonthJson(filter);
                break;
            default:
                throw new MethodNotFoundException("Method param value not matched");
        }
        return billedVsApprovedJson;
    }

    private JSONObject loadBilledVsApprovedByMonthJson(DashboardsFilterCriteria filter) throws JSONException {
        JSONObject billedVsApprovedJson = null;
        List<BilledVsApprovedDto> billedVsApprovedList = dashboardsService.getBilledVsApprovedByMonth(filter);
        List<NetSpendMonthlyChartDto> monthChartValueList = null;
        if(billedVsApprovedList != null && billedVsApprovedList.size() > 0){
            monthChartValueList = new ArrayList<NetSpendMonthlyChartDto>();
            for(BilledVsApprovedDto billedVsApproved : billedVsApprovedList){
                if(billedVsApproved != null){
                    monthChartValueList.add(new NetSpendMonthlyChartDto(billedVsApproved));
                }
            }
            billedVsApprovedJson = JSONUtil.prepareMonthlyChartJson(monthChartValueList);
        }
        return billedVsApprovedJson;
    }

    private JSONObject loadBilledVsApprovedJson(DashboardsFilterCriteria filter) throws JSONException {
        JSONObject billedVsApprovedJson = null;
        List<BilledVsApprovedDto> billedVsApprovedList = dashboardsService.getBilledVsApprovedData(filter);
        if(billedVsApprovedList != null && billedVsApprovedList.size() > 0){
            billedVsApprovedJson = JSONUtil.prepareBilledVsApprovedJson(billedVsApprovedList);
        }
        return billedVsApprovedJson;
    }

    private JSONObject loadOrderMatchJsonData(OrderMatchConstant orderMatchType, DashboardsFilterCriteria filter) throws JSONException {
        JSONObject orderMatchJson = null;
        switch (orderMatchType){
            case ORDER_MATCH:
                orderMatchJson = loadOrdeMatchStatusJson(filter);
                break;
            case ORDER_MATCH_BY_CARRIER:
                orderMatchJson = loadOrdeMatchByCarrierJson(filter);
                break;
            case ORDER_MATCH_BY_MONTH:
                orderMatchJson = loadOrdeMatchByMonthJson(filter);
                break;
            default:
                throw new MethodNotFoundException("Method param value not matched");
        }
        return orderMatchJson;
    }

    private JSONObject loadOrdeMatchByMonthJson(DashboardsFilterCriteria filter) throws JSONException {
        JSONObject orderMatchJson = null;
        List<OrderMatchDto> orderMatchList = dashboardsService.getOrderMatchByMonth(filter);
        List<NetSpendMonthlyChartDto> monthChartValueList = null;
        if(orderMatchList != null && orderMatchList.size() > 0){
            monthChartValueList = new ArrayList<NetSpendMonthlyChartDto>();
            for(OrderMatchDto orderMatch : orderMatchList){
                if(orderMatch != null){
                    monthChartValueList.add(new NetSpendMonthlyChartDto(orderMatch));
                }
            }
            orderMatchJson = JSONUtil.prepareMonthlyChartJson(monthChartValueList);
        }
        return orderMatchJson;
    }

    private JSONObject loadOrdeMatchByCarrierJson(DashboardsFilterCriteria filter) throws JSONException {
        JSONObject orderMatchJson = null;
        List<OrderMatchDto> orderMatchList = dashboardsService.getOrderMatchByCarrier(filter);
        List<CommonValuesForChartDto> chartValueList = null;
        if(orderMatchList != null && orderMatchList.size() > 0){
            chartValueList = new ArrayList<CommonValuesForChartDto>();
            for(OrderMatchDto orderMatch : orderMatchList){
                if(orderMatch != null){
                    chartValueList.add(new CommonValuesForChartDto(orderMatch));
                }
            }
            orderMatchJson = JSONUtil.prepareCommonJsonForChart(chartValueList);
        }
        return orderMatchJson;
    }

    private JSONObject loadOrdeMatchStatusJson(DashboardsFilterCriteria filter) throws JSONException {
        JSONObject orderMatchJson = null;
        List<OrderMatchDto> orderMatchList = dashboardsService.getOrderMatchStatus(filter);
        if(orderMatchList != null && orderMatchList.size() > 0){
            orderMatchJson = JSONUtil.prepareOrderMatchJson(orderMatchList);
        }
        return orderMatchJson;
    }

    private JSONObject loadInvoiceMethodScoreJsonData(InvoiceMethodScoreConstant invoiceMethodScoreType, DashboardsFilterCriteria filter) throws JSONException {
        JSONObject invMethodScoreJson = null;
        switch (invoiceMethodScoreType){
            case INVOICE_METHOD_SCORE:
                invMethodScoreJson = loadInvoiceMethodScoreJson(filter);
                break;
            case INVOICE_METHOD_SCORE_BY_CARRIER:
                invMethodScoreJson = loadInvoiceMethodScoreByCarrierJson(filter);
                break;
            case INVOICE_METHOD_SCORE_BY_MONTH:
                invMethodScoreJson = loadInvoiceMethodScoreByMonthJson(filter);
                break;
            default:
                throw new MethodNotFoundException("Method param value not matched");
        }
        return invMethodScoreJson;
    }

    private JSONObject loadInvoiceMethodScoreByMonthJson(DashboardsFilterCriteria filter) throws JSONException {
        JSONObject invMethodScoreJson = null;
        List<InvoiceMethodScoreDto> invMthScrList = dashboardsService.getInvoiceMethodScoreByMonth(filter);
        List<NetSpendMonthlyChartDto> monthChartValueList = null;
        if(invMthScrList != null && invMthScrList.size() > 0){
            monthChartValueList = new ArrayList<NetSpendMonthlyChartDto>();
            for(InvoiceMethodScoreDto invoiceMethodScore : invMthScrList){
                if(invoiceMethodScore != null){
                    monthChartValueList.add(new NetSpendMonthlyChartDto(invoiceMethodScore));
                }
            }
            invMethodScoreJson = JSONUtil.prepareMonthlyChartJson(monthChartValueList);
        }
        return invMethodScoreJson;
    }

    private JSONObject loadInvoiceMethodScoreByCarrierJson(DashboardsFilterCriteria filter) throws JSONException {
        JSONObject invMethodScoreJson = null;
        List<InvoiceMethodScoreDto> invMthScrList = dashboardsService.getInvoiceMethodScoreByCarrier(filter);
        List<CommonValuesForChartDto> chartValueList = null;
        if(invMthScrList != null && invMthScrList.size() > 0){
            chartValueList = new ArrayList<CommonValuesForChartDto>();
            for(InvoiceMethodScoreDto invoiceMethodScore : invMthScrList){
                if(invoiceMethodScore != null){
                    chartValueList.add(new CommonValuesForChartDto(invoiceMethodScore));
                }
            }
            invMethodScoreJson = JSONUtil.prepareCommonJsonForChart(chartValueList);
        }
        return invMethodScoreJson;
    }

    private JSONObject loadInvoiceMethodScoreJson(DashboardsFilterCriteria filter) throws JSONException {
        JSONObject invMethodScoreJson = null;
        List<InvoiceMethodScoreDto> invMthScrList = dashboardsService.getInvoiceMethodScore(filter);
        List<CommonValuesForChartDto> chartValueList = null;
        if(invMthScrList != null && invMthScrList.size() > 0){
            invMethodScoreJson = JSONUtil.prepareInvoiceMethodScoreJson(invMthScrList);
        }
        return invMethodScoreJson;
    }

    private JSONObject loadInvoiceStatusAmountJsonData(InvoiceStatusAmountConstant invStsType, DashboardsFilterCriteria filter) throws JSONException {
        JSONObject invStsJson = null;
        switch (invStsType){
            case INVOICE_STATUS_AMOUNT:
                invStsJson = loadInvoiceStatusAmountJson(filter);
                break;
            case INVOICE_STATUS_AMOUNT_BY_CARRIER:
                invStsJson = loadInvoiceStatusAmountByCarrierJson(filter);
                break;
            case INVOICE_STATUS_AMOUNT_BY_MONTH:
                invStsJson = loadInvoiceStatusAmountByMonthJson(filter);
                break;
            default:
                throw new MethodNotFoundException("Method param value not matched");
        }
        return invStsJson;
    }

    private JSONObject loadInvoiceStatusAmountByMonthJson(DashboardsFilterCriteria filter) throws JSONException {
        JSONObject invStsJson = null;
        List<InvoiceStatusAmountDto> invStsAmtList = dashboardsService.getInvoiceStatusAmountByMonth(filter);
        List<NetSpendMonthlyChartDto> monthChartValueList = null;
        if(invStsAmtList != null && invStsAmtList.size() > 0){
            monthChartValueList = new ArrayList<NetSpendMonthlyChartDto>();
            for(InvoiceStatusAmountDto invStatusAmt : invStsAmtList){
                if(invStatusAmt != null){
                    monthChartValueList.add(new NetSpendMonthlyChartDto(invStatusAmt));
                }
            }
            invStsJson = JSONUtil.prepareMonthlyChartJson(monthChartValueList);
        }
        return invStsJson;
    }

    private JSONObject loadInvoiceStatusAmountByCarrierJson(DashboardsFilterCriteria filter) throws JSONException {
        JSONObject invStsJson = null;
        List<InvoiceStatusAmountDto> invStsAmtList = dashboardsService.getInvoiceStatusAmountByCarrier(filter);
        List<CommonValuesForChartDto> chartValueList = null;
        if(invStsAmtList != null && invStsAmtList.size() > 0){
            chartValueList = new ArrayList<CommonValuesForChartDto>();
            for(InvoiceStatusAmountDto invStatusAmt : invStsAmtList){
                if(invStatusAmt != null){
                    chartValueList.add(new CommonValuesForChartDto(invStatusAmt));
                }
            }
            invStsJson = JSONUtil.prepareCommonJsonForChart(chartValueList);
        }
        return invStsJson;
    }

    private JSONObject loadInvoiceStatusAmountJson(DashboardsFilterCriteria filter) throws JSONException {
        JSONObject invStsJson = null;
        List<InvoiceStatusAmountDto> invStsAmtList = dashboardsService.getInvoiceStatusAmount(filter);
        List<CommonValuesForChartDto> chartValueList = null;
        if(invStsAmtList != null && invStsAmtList.size() > 0){
            chartValueList = new ArrayList<CommonValuesForChartDto>();
            for(InvoiceStatusAmountDto invStsAmt : invStsAmtList){
                if(invStsAmt != null){
                    chartValueList.add(new CommonValuesForChartDto(invStsAmt));
                }
            }
            invStsJson = JSONUtil.prepareCommonJsonForChart(chartValueList);
        }
        return invStsJson;
    }

    private JSONObject loadInvoiceStatusCountJsonData(InvoiceStatusCountConstant invStsType, DashboardsFilterCriteria filter) throws JSONException {
        JSONObject invStsJson = null;
        switch (invStsType){
            case INVOICE_STATUS_COUNT:
                invStsJson = loadInvoiceStatusJson(filter);
                break;
            case INVOICE_STATUS_COUNT_BY_CARRIER:
                invStsJson = loadInvoiceStatusByCarrierJson(filter);
                break;
            case INVOICE_STATUS_COUNT_BY_MONTH:
                invStsJson = loadInvoiceStatusByMonthJson(filter);
                break;
            default:
                throw new MethodNotFoundException("Method param value not matched");
        }
        return invStsJson;
    }

    private JSONObject loadInvoiceStatusByMonthJson(DashboardsFilterCriteria filter) throws JSONException {
        JSONObject invStsJson = null;
        List<InvoiceStatusCountDto> invStsCountList = dashboardsService.getInvoiceStatusCountByMonth(filter);
        List<NetSpendMonthlyChartDto> monthChartValueList = null;
        if(invStsCountList != null && invStsCountList.size() > 0){
            monthChartValueList = new ArrayList<NetSpendMonthlyChartDto>();
            for(InvoiceStatusCountDto invStatusCount : invStsCountList){
                if(invStatusCount != null){
                    monthChartValueList.add(new NetSpendMonthlyChartDto(invStatusCount));
                }
            }
            invStsJson = JSONUtil.prepareMonthlyChartJson(monthChartValueList);
        }
        return invStsJson;
    }

    private JSONObject loadInvoiceStatusByCarrierJson(DashboardsFilterCriteria filter) throws JSONException {
        JSONObject invStsJson = null;
        List<InvoiceStatusCountDto> invStsCountList = dashboardsService.getInvoiceStatusCountByCarrier(filter);
        List<CommonValuesForChartDto> chartValueList = null;
        if(invStsCountList != null && invStsCountList.size() > 0){
            chartValueList = new ArrayList<CommonValuesForChartDto>();
            for(InvoiceStatusCountDto invStatusCount : invStsCountList){
                if(invStatusCount != null){
                    chartValueList.add(new CommonValuesForChartDto(invStatusCount));
                }
            }
            invStsJson = JSONUtil.prepareCommonJsonForChart(chartValueList);
        }
        return invStsJson;
    }

    private JSONObject loadInvoiceStatusJson(DashboardsFilterCriteria filter) throws JSONException {
        JSONObject invStsJson = null;
        List<InvoiceStatusCountDto> invStsCountList = dashboardsService.getInvoiceStatusCount(filter);
        List<CommonValuesForChartDto> chartValueList = null;
        if(invStsCountList != null && invStsCountList.size() > 0){
            chartValueList = new ArrayList<CommonValuesForChartDto>();
            for(InvoiceStatusCountDto invStatusCount : invStsCountList){
                if(invStatusCount != null){
                    chartValueList.add(new CommonValuesForChartDto(invStatusCount));
                }
            }
            invStsJson = JSONUtil.prepareCommonJsonForChart(chartValueList);
        }
        return invStsJson;
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
