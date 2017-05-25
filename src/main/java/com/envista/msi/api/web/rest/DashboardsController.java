package com.envista.msi.api.web.rest;

import com.envista.msi.api.domain.util.DashboardUtil;
import com.envista.msi.api.service.DashboardsService;
import com.envista.msi.api.service.ReportsService;
import com.envista.msi.api.service.UserService;
import com.envista.msi.api.web.rest.dto.MapCoordinatesDto;
import com.envista.msi.api.web.rest.dto.UserProfileDto;
import com.envista.msi.api.web.rest.dto.dashboard.DashboardAppliedFilterDto;
import com.envista.msi.api.web.rest.dto.dashboard.DashboardsFilterCriteria;
import com.envista.msi.api.web.rest.dto.dashboard.annualsummary.AccountSummaryDto;
import com.envista.msi.api.web.rest.dto.dashboard.annualsummary.AnnualSummaryDto;
import com.envista.msi.api.web.rest.dto.dashboard.annualsummary.MonthlySpendByModeDto;
import com.envista.msi.api.web.rest.dto.dashboard.auditactivity.*;
import com.envista.msi.api.web.rest.dto.dashboard.common.CommonMonthlyChartDto;
import com.envista.msi.api.web.rest.dto.dashboard.common.CommonValuesForChartDto;
import com.envista.msi.api.web.rest.dto.dashboard.common.NetSpendCommonDto;
import com.envista.msi.api.web.rest.dto.dashboard.filter.DashSavedFilterDto;
import com.envista.msi.api.web.rest.dto.dashboard.filter.UserFilterUtilityDataDto;
import com.envista.msi.api.web.rest.dto.dashboard.netspend.*;
import com.envista.msi.api.web.rest.dto.dashboard.networkanalysis.PortLanesDto;
import com.envista.msi.api.web.rest.dto.dashboard.networkanalysis.ShipmentDto;
import com.envista.msi.api.web.rest.dto.dashboard.networkanalysis.ShipmentRegionDto;
import com.envista.msi.api.web.rest.dto.dashboard.networkanalysis.ShippingLanesDto;
import com.envista.msi.api.web.rest.dto.dashboard.shipmentoverview.*;
import com.envista.msi.api.web.rest.dto.reports.ReportCustomerCarrierDto;
import com.envista.msi.api.web.rest.util.DateUtil;
import com.envista.msi.api.web.rest.util.JSONUtil;
import com.envista.msi.api.web.rest.util.pagination.PaginationBean;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.el.MethodNotFoundException;
import javax.servlet.http.HttpSession;
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
    ReportsService reportsService;

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
        TOP_ACCESSORIAL_SPEND_BY_ACC,
        ACCESSORIAL_SPEND,
        ACCESSORIAL_SPEND_BY_CARRIER,
        ACCESSORIAL_SPEND_BY_MONTH;
    }

    enum ShipmentOverviewConstant{
        AVG_SPEND_PER_SHIPMT,
        AVG_WEIGHT_BY_MODE_SHIPMT,
        AVG_SPEND_PER_SHIPMT_BY_CARRIER,
        AVG_SPEND_PER_SHIPMT_BY_MONTH,
        AVG_WEIGHT_SHIPMT_BY_CARRIER,
        AVG_WEIGHT_SHIPMT_BY_MONTH;
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
        INVOICE_STATUS_COUNT_BY_MONTH;
    }

    enum InvoiceStatusAmountConstant{
        INVOICE_STATUS_AMOUNT,
        INVOICE_STATUS_AMOUNT_BY_CARRIER,
        INVOICE_STATUS_AMOUNT_BY_MONTH;
    }

    enum InvoiceMethodScoreConstant{
        INVOICE_METHOD_SCORE,
        INVOICE_METHOD_SCORE_BY_CARRIER,
        INVOICE_METHOD_SCORE_BY_MONTH;
    }

    enum OrderMatchConstant{
        ORDER_MATCH,
        ORDER_MATCH_BY_CARRIER,
        ORDER_MATCH_BY_MONTH;
    }

    enum BilledVsApprovedConstant{
        BILLED_VS_APPROVED,
        BILLED_VS_APPROVED_BY_MONTH;
    }

    enum RecoveryAdjustmentConstants{
        RECOVERY_ADJUSTMENT,
        RECOVERY_ADJUSTMENT_BY_CARRIER,
        RECOVERY_ADJUSTMENT_BY_MONTH;
    }

    enum RecoveryServiceConstants{
        RECOVERY_SERVICE,
        RECOVERY_SERVICE_BY_MONTH;
    }

    enum ShipmentsRegionConstant{
        SHIPMENTS_REGION,
        SHIPMENTS_REGION_BY_CARRIER,
        SHIPMENTS_REGION_BY_MONTH;
    }

    enum ShippingLanesConstant{
        SHIPPING_LANES,
        SHIPPING_LANES_BY_CARRIER,
        SHIPPING_LANES_BY_MONTH;
    }

    enum PortLanesConstant{
        PORT_LANES,
        PORT_LANES_BY_CARRIER,
        PORT_LANES_BY_MONTH;
    }

    enum PackageExceptionConstants{
        PACKAGE_EXCEPTION,
        PACKAGE_EXCEPTION_BY_CARRIER,
        PACKAGE_EXCEPTION_BY_MONTH;
    }

    enum AnnualSummaryConstants{
        ANNUAL_SUMMARY,
        ANNUAL_SUMMARY_BY_SERVICE,
        ANNUAL_SUMMARY_BY_CARRIER,
        ANNUAL_SUMMARY_BY_MONTH;
    }

    enum MonthlySpendByModeConstants{
        MONTHLY_SPEND_BY_MODE,
        MONTHLY_SPEND_BY_MODE_BY_SERVICE;
    }

    enum AccountSummaryConstants{
        ACCOUNT_SUMMARY,
        PARCEL_ACCOUNT_SUMMARY;
    }

    enum ActualVsBilledWeightConstants{
        ACTUAL_VS_BILLED_WEIGHT,
        ACTUAL_VS_BILLED_WEIGHT_BY_CARRIER,
        ACTUAL_VS_BILLED_WEIGHT_BY_MONTH;
    }

    @RequestMapping(value = "/appliedFilter", method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.OPTIONS}, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DashboardAppliedFilterDto> getAppliedFilterDetails(@RequestParam(value = "userId" , required = true) String userId  ){
        DashboardAppliedFilterDto dashboardAppliedFilterDto = null;
        dashboardAppliedFilterDto = dashboardsService.getUserAppliedFilter(Long.parseLong(userId));
        DashboardsFilterCriteria dashboardsFilterCriteria = populateDashboardFilterCriteria(dashboardAppliedFilterDto);
        return new ResponseEntity<DashboardAppliedFilterDto>(dashboardAppliedFilterDto, HttpStatus.OK );
    }

    private DashboardsFilterCriteria loadAppliedFilters(Long userId){
        return populateDashboardFilterCriteria(dashboardsService.getUserAppliedFilter(userId));
    }

    @RequestMapping(value = "/netSpendByMode", method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.OPTIONS}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<String> getNetSpendByModes() throws Exception {
        UserProfileDto user = getUserProfile();
        DashboardsFilterCriteria filter = loadAppliedFilters(user.getUserId());
        JSONObject nspData = loadNetSpendJsonData(NetSpendConstant.NET_SPEND_BY_MODE, filter);
        return new ResponseEntity<String>(nspData != null ? nspData.toString() : new JSONObject().toString(), HttpStatus.OK);
    }

    @RequestMapping(value = "/netSpendOvrTmByMnth", method = {RequestMethod.POST, RequestMethod.OPTIONS}, produces = {MediaType.APPLICATION_JSON_VALUE}, consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<String> getNetSpendOverTimeByMonth(@RequestBody NetSpendRequestDto netSpendRequest) throws Exception{
        UserProfileDto user = getUserProfile();
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
        return new ResponseEntity<String>(nspData != null ? nspData.toString() : new JSONObject().toString(), HttpStatus.OK);
    }

    @RequestMapping(value = "/netSpendByOverTime", method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.OPTIONS}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<String> getNetSpendByOverTime() throws Exception {
        UserProfileDto user = getUserProfile();
        DashboardsFilterCriteria filter = loadAppliedFilters(user.getUserId());
        JSONObject nspData = loadNetSpendJsonData(NetSpendConstant.NET_SPEND_BY_OVER_TIME, filter);
        return new ResponseEntity<String>(nspData != null ? nspData.toString() : new JSONObject().toString(), HttpStatus.OK);
    }

    @RequestMapping(value = "/netSpendByCarr", method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.OPTIONS}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<String> getNetSpendByCarrier(@RequestParam String mode) throws Exception {
        UserProfileDto user = getUserProfile();
        DashboardsFilterCriteria filter = loadAppliedFilters(user.getUserId());
        if(filter != null && mode != null){
            filter.setModeNames(mode);
        }
        JSONObject nspData = loadNetSpendJsonData(NetSpendConstant.NET_SPEND_BY_CARRIER, filter);
        return new ResponseEntity<String>(nspData != null ? nspData.toString() : new JSONObject().toString(), HttpStatus.OK);
    }

    @RequestMapping(value = "/netSpendByMth", method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.OPTIONS}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<String> getNetSpendByMonth(@RequestParam String carrier, @RequestParam  String mode) throws Exception {
        UserProfileDto user = getUserProfile();
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
        return new ResponseEntity<String>(nspData != null ? nspData.toString() : new JSONObject().toString(), HttpStatus.OK);
    }

    @RequestMapping(value = "/taxSpend", method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.OPTIONS}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<String> getTaxSpend() throws Exception {
        JSONObject taxSpendJson = null;
        UserProfileDto user = getUserProfile();
        DashboardsFilterCriteria filter = loadAppliedFilters(user.getUserId());
        JSONObject taxData = loadTaxSpendJsonData(TaxSpendConstant.TAX_SPEND, filter);
        return new ResponseEntity<String>(taxData != null ? taxData.toString() : new JSONObject().toString(), HttpStatus.OK);
    }

    @RequestMapping(value = "/taxSpendByCarr", method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.OPTIONS}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<String> getTaxSpendByCarrier(@RequestParam String tax) throws Exception {
        UserProfileDto user = getUserProfile();
        DashboardsFilterCriteria filter = loadAppliedFilters(user.getUserId());
        if(filter != null && tax != null){
            filter.setTax(tax);
        }
        JSONObject taxSpendJson = loadTaxSpendJsonData(TaxSpendConstant.TAX_SPEND_BY_CARRIER, filter);
        return new ResponseEntity<String>(taxSpendJson != null ? taxSpendJson.toString() : new JSONObject().toString(), HttpStatus.OK);
    }

    @RequestMapping(value = "/taxSpendByMth", method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.OPTIONS}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<String> getTaxSpendByMonth(@RequestParam String carrierId, @RequestParam String tax) throws Exception {
        UserProfileDto user = getUserProfile();
        DashboardsFilterCriteria filter = loadAppliedFilters(user.getUserId());
        if(filter != null){
            if(carrierId != null){
                filter.setCarriers(carrierId);
            }
            if(tax != null){
                filter.setTax(tax);
            }
        }
        JSONObject taxSpendJson = loadTaxSpendJsonData(TaxSpendConstant.TAX_SPEND_BY_MONTH, filter);
        return new ResponseEntity<String>(taxSpendJson != null ? taxSpendJson.toString() : new JSONObject().toString(), HttpStatus.OK);
    }

    @RequestMapping(value = "/topAccSpend", method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.OPTIONS}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<String> getTopAccessorialSpend() throws Exception {
        UserProfileDto user = getUserProfile();
        DashboardsFilterCriteria filter = loadAppliedFilters(user.getUserId());
        JSONObject accSpendJson = loadAccessorialSpendJsonData(AccessorialSpendConstant.TOP_ACCESSORIAL_SPEND, filter);
        return new ResponseEntity<String>(accSpendJson != null ? accSpendJson.toString() : new JSONObject().toString(), HttpStatus.OK);
    }

    @RequestMapping(value = "/topAccSpendByCarr", method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.OPTIONS}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<String> getTopAccessorialSpendByCarrier(@RequestParam String accessorial, @RequestParam String invoiceDate) throws Exception {
        UserProfileDto user = getUserProfile();
        DashboardsFilterCriteria filter = loadAppliedFilters(user.getUserId());
        if(filter !=  null){
            if(accessorial != null && !accessorial.isEmpty()){
                filter.setAccessorialName(accessorial);
            }
            if(invoiceDate != null && !invoiceDate.isEmpty()){
                DashboardUtil.setDatesFromMonth(filter, invoiceDate);
            }
        }
        JSONObject accSpendJson = loadAccessorialSpendJsonData(AccessorialSpendConstant.TOP_ACCESSORIAL_SPEND_BY_CARRIER, filter);
        return new ResponseEntity<String>(accSpendJson != null ? accSpendJson.toString() : new JSONObject().toString(), HttpStatus.OK);
    }

    @RequestMapping(value = "/topAccSpendByMonth", method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.OPTIONS}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<String> getTopAccessorialSpendByMonth(@RequestParam String accessorial, @RequestParam String invoiceDate, @RequestParam String carrierId) throws Exception {
        UserProfileDto user = getUserProfile();
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
        JSONObject accSpendJson = loadAccessorialSpendJsonData(AccessorialSpendConstant.TOP_ACCESSORIAL_SPEND_BY_MONTH, filter);
        return new ResponseEntity<String>(accSpendJson != null ? accSpendJson.toString() : new JSONObject().toString(), HttpStatus.OK);
    }

    @RequestMapping(value = "/topAccSpendByAcc", method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.OPTIONS}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<String> getTopAccessorialSpendByAcc(@RequestParam String invoiceDate) throws Exception {
        UserProfileDto user = getUserProfile();
        DashboardsFilterCriteria filter = loadAppliedFilters(user.getUserId());
        if(filter !=  null){
            if(invoiceDate != null && !invoiceDate.isEmpty()){
                DashboardUtil.setDatesFromMonth(filter, invoiceDate);
            }
        }
        JSONObject accSpendJson = loadAccessorialSpendJsonData(AccessorialSpendConstant.TOP_ACCESSORIAL_SPEND_BY_ACC, filter);
        return new ResponseEntity<String>(accSpendJson != null ? accSpendJson.toString() : new JSONObject().toString(), HttpStatus.OK);
    }

    @RequestMapping(value = "/accSpend", method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.OPTIONS}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<String> getAccessorialSpend() throws Exception {
        UserProfileDto user = getUserProfile();
        DashboardsFilterCriteria filter = loadAppliedFilters(user.getUserId());
        JSONObject accSpendJson = loadAccessorialSpendJsonData(AccessorialSpendConstant.ACCESSORIAL_SPEND, filter);
        accSpendJson = (accSpendJson != null ? accSpendJson : new JSONObject());
        return new ResponseEntity<String>(accSpendJson != null ? accSpendJson.toString() : new JSONObject().toString(), HttpStatus.OK);
    }

    @RequestMapping(value = "/accSpendByCarr", method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.OPTIONS}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<String> getAccessorialSpendByCarrier(@RequestParam String tax) throws Exception {
        UserProfileDto user = getUserProfile();
        DashboardsFilterCriteria filter = loadAppliedFilters(user.getUserId());
        if(filter != null && tax != null && !tax.isEmpty()){
            filter.setAccDesc(tax);
        }
        JSONObject accSpendJson = loadAccessorialSpendJsonData(AccessorialSpendConstant.ACCESSORIAL_SPEND_BY_CARRIER, filter);
        return new ResponseEntity<String>(accSpendJson != null ? accSpendJson.toString() : new JSONObject().toString(), HttpStatus.OK);
    }

    @RequestMapping(value = "/accSpendByMonth", method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.OPTIONS}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<String> getAccessorialSpendByMonth(@RequestParam String carrierId, @RequestParam String tax) throws Exception {
        UserProfileDto user = getUserProfile();
        DashboardsFilterCriteria filter = loadAppliedFilters(user.getUserId());
        if(filter != null){
            if(carrierId != null && !carrierId.isEmpty()){
                filter.setCarriers(carrierId);
            }
            if(tax != null && !tax.isEmpty()){
                filter.setAccDesc(tax);
            }
        }
        JSONObject accSpendJson = loadAccessorialSpendJsonData(AccessorialSpendConstant.ACCESSORIAL_SPEND_BY_MONTH, filter);
        return new ResponseEntity<String>(accSpendJson != null ? accSpendJson.toString() : new JSONObject().toString(), HttpStatus.OK);
    }

    @RequestMapping(value = "/shipmentsByRegion", method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.OPTIONS}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<String> getShipmentsByRegion() throws Exception {
        UserProfileDto user = getUserProfile();
        DashboardsFilterCriteria filter = loadAppliedFilters(user.getUserId());
        JSONObject shipmentsRegionJsonData = loadShipmentByRegionJsonData(ShipmentsRegionConstant.SHIPMENTS_REGION, filter);
        return new ResponseEntity<String>(shipmentsRegionJsonData != null ? shipmentsRegionJsonData.toString() : new JSONObject().toString(), HttpStatus.OK);
    }

    @RequestMapping(value = "/shipmentRegionByCarrier", method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.OPTIONS}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<String> getShipmentRegionByCarrier(@RequestParam String shipperCity,@RequestParam String receiverCity ) throws Exception {
        UserProfileDto user = getUserProfile();
        DashboardsFilterCriteria filter = loadAppliedFilters(user.getUserId());
        if( filter != null ) {
            filter.setShipperCity(shipperCity);
            filter.setReceiverCity(receiverCity);
        }
        JSONObject shipmentsRegionJsonData = loadShipmentByRegionJsonData(ShipmentsRegionConstant.SHIPMENTS_REGION_BY_CARRIER, filter);
        return new ResponseEntity<String>(shipmentsRegionJsonData != null ? shipmentsRegionJsonData.toString() : new JSONObject().toString(), HttpStatus.OK);
    }

    @RequestMapping(value = "/shipmentRegionByMonth", method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.OPTIONS}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<String> getShipmentRegionByMonth(@RequestParam String shipperCity,@RequestParam String receiverCity, @RequestParam String carrierId) throws Exception {
        UserProfileDto user = getUserProfile();
        DashboardsFilterCriteria filter = loadAppliedFilters(user.getUserId());
        if( filter != null ) {
            filter.setShipperCity(shipperCity);
            filter.setReceiverCity(receiverCity);
            filter.setCarriers(carrierId);
        }
        JSONObject shipmentsRegionJsonData = loadShipmentByRegionJsonData(ShipmentsRegionConstant.SHIPMENTS_REGION_BY_MONTH, filter);
        return new ResponseEntity<String>(shipmentsRegionJsonData != null ? shipmentsRegionJsonData.toString() : new JSONObject().toString(), HttpStatus.OK);
    }

    @RequestMapping(value = "/topShippingLanes", method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.OPTIONS}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<String> getTopShippingLanes() throws Exception {
        UserProfileDto user = getUserProfile();
        DashboardsFilterCriteria filter = loadAppliedFilters(user.getUserId());
        JSONObject resultJsonData = loadTopShippingLanesJsonData(ShippingLanesConstant.SHIPPING_LANES, filter);
        return new ResponseEntity<String>(resultJsonData != null ? resultJsonData.toString() : new JSONObject().toString(), HttpStatus.OK);
    }

    @RequestMapping(value = "/shippingLanesByCarrier", method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.OPTIONS}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<String> getShippingLanesByCarrier(@RequestParam String shipperAddress,@RequestParam String receiverAddress) throws Exception {
        UserProfileDto user = getUserProfile();
        DashboardsFilterCriteria filter = loadAppliedFilters(user.getUserId());
        if( filter != null ) {
            filter.setShipperAddress(shipperAddress);
            filter.setReceiverAddress(receiverAddress);
        }
        JSONObject resultJsonObj = loadTopShippingLanesJsonData(ShippingLanesConstant.SHIPPING_LANES_BY_CARRIER, filter);
        return new ResponseEntity<String>(resultJsonObj != null ? resultJsonObj.toString() : new JSONObject().toString(), HttpStatus.OK);
    }

    @RequestMapping(value = "/shippingLanesByMonth", method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.OPTIONS}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<String> getShippingLanesByMonth(@RequestParam String shipperAddress,@RequestParam String receiverAddress, @RequestParam String carrierId) throws Exception {
        UserProfileDto user = getUserProfile();
        DashboardsFilterCriteria filter = loadAppliedFilters(user.getUserId());
        if( filter != null ) {
            filter.setShipperAddress(shipperAddress);
            filter.setReceiverAddress(receiverAddress);
            filter.setCarriers(carrierId);
        }
        JSONObject resultsJsonObj = loadTopShippingLanesJsonData(ShippingLanesConstant.SHIPPING_LANES_BY_MONTH, filter);
        return new ResponseEntity<String>(resultsJsonObj != null ? resultsJsonObj.toString() : new JSONObject().toString(), HttpStatus.OK);
    }

    @RequestMapping(value = "/topPortLanes", method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.OPTIONS}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<String> getTopPortLanes() throws Exception {
        UserProfileDto user = getUserProfile();
        DashboardsFilterCriteria filter = loadAppliedFilters(user.getUserId());
        JSONObject resultJsonData = loadTopPortLanesJsonData(PortLanesConstant.PORT_LANES, filter);
        return new ResponseEntity<String>(resultJsonData != null ? resultJsonData.toString() : new JSONObject().toString(), HttpStatus.OK);
    }

    @RequestMapping(value = "/portLanesByCarrier", method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.OPTIONS}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<String> getPortLanesByCarrier(@RequestParam String pol,@RequestParam String pod) throws Exception {
        UserProfileDto user = getUserProfile();
        DashboardsFilterCriteria filter = loadAppliedFilters(user.getUserId());
        if( filter != null ) {
            filter.setPol(pol);
            filter.setPod(pod);
        }
        JSONObject resultJsonObj = loadTopPortLanesJsonData(PortLanesConstant.PORT_LANES_BY_CARRIER, filter);
        return new ResponseEntity<String>(resultJsonObj != null ? resultJsonObj.toString() : new JSONObject().toString(), HttpStatus.OK);
    }

    @RequestMapping(value = "/portLanesByMonth", method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.OPTIONS}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<String> getPortLanesByMonth(@RequestParam String pol,@RequestParam String pod, @RequestParam String carrierId) throws Exception {
        UserProfileDto user = getUserProfile();
        DashboardsFilterCriteria filter = loadAppliedFilters(user.getUserId());
        if( filter != null ) {
            filter.setPol(pol);
            filter.setPod(pod);
            filter.setCarriers(carrierId);
        }
        JSONObject resultsJsonObj = loadTopPortLanesJsonData(PortLanesConstant.PORT_LANES_BY_MONTH, filter);
        return new ResponseEntity<String>(resultsJsonObj != null ? resultsJsonObj.toString() : new JSONObject().toString(), HttpStatus.OK);
    }


    @RequestMapping(value = "/avgSpendPerShipment", method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.OPTIONS}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<String> getAvgSpendPerShipment() throws Exception {
        UserProfileDto user = getUserProfile();
        DashboardsFilterCriteria filter = loadAppliedFilters(user.getUserId());
        JSONObject avgShipmentData = loadShipmentOverviewJsonData(ShipmentOverviewConstant.AVG_SPEND_PER_SHIPMT, filter);
        return new ResponseEntity<String>(avgShipmentData != null ? avgShipmentData.toString() : new JSONObject().toString(), HttpStatus.OK);
    }

    @RequestMapping(value = "/avgWeightByModeShipment", method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.OPTIONS}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<String> getAverageWeightByModeShipment() throws Exception {
        UserProfileDto user = getUserProfile();
        DashboardsFilterCriteria filter = loadAppliedFilters(user.getUserId());
        JSONObject avgShipmentData = loadShipmentOverviewJsonData(ShipmentOverviewConstant.AVG_WEIGHT_BY_MODE_SHIPMT, filter);
        return new ResponseEntity<String>(avgShipmentData != null ? avgShipmentData.toString() : new JSONObject().toString(), HttpStatus.OK);
    }

    @RequestMapping(value = "/serviceUsagePerf", method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.OPTIONS}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<String> getServiceLevelUsageAndPerformance() throws JSONException {
        UserProfileDto user = getUserProfile();
        DashboardsFilterCriteria filter = loadAppliedFilters(user.getUserId());
        List<ServiceLevelUsageAndPerformanceDto> serviceLevelUsageAndPerfList = dashboardsService.getServiceLevelUsageAndPerformance(filter, false);
        JSONObject sfJson = JSONUtil.prepareServiceLevelUsageAndPerfromanceJson(serviceLevelUsageAndPerfList);
        return new ResponseEntity<String>(sfJson != null ? sfJson.toString() : new JSONObject().toString(), HttpStatus.OK);
    }

    @RequestMapping(value = "/inboundSpend", method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.OPTIONS}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<String> getInboundSpend() throws JSONException {
        UserProfileDto user = getUserProfile();
        DashboardsFilterCriteria filter = loadAppliedFilters(user.getUserId());
        JSONObject inbSpendJson = loadInboundSpendJsonData(InboundSpendConstant.INBOUND_SPEND, filter);
        return new ResponseEntity<String>(inbSpendJson != null ? inbSpendJson.toString() : new JSONObject().toString(), HttpStatus.OK);
    }

    @RequestMapping(value = "/inboundSpendByMnth", method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.OPTIONS}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<String> getInboundSpendByMonth(@RequestParam String carrierId, @RequestParam String invoiceDate) throws Exception {
        UserProfileDto user = getUserProfile();
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
        return new ResponseEntity<String>(inbSpendJson != null ? inbSpendJson.toString() : new JSONObject().toString(), HttpStatus.OK);
    }

    @RequestMapping(value = "/outboundSpend", method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.OPTIONS}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<String> getOutboundSpend() throws JSONException {
        UserProfileDto user = getUserProfile();
        DashboardsFilterCriteria filter = loadAppliedFilters(user.getUserId());
        JSONObject outbSpendJson = loadOutboundSpendJsonData(OutboundSpendConstant.OUTBOUND_SPEND, filter);
        return new ResponseEntity<String>(outbSpendJson != null ? outbSpendJson.toString() : new JSONObject().toString(), HttpStatus.OK);
    }

    @RequestMapping(value = "/outboundSpendByMnth", method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.OPTIONS}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<String> getOutboundSpendByMonth(@RequestParam String carrierId, @RequestParam String invoiceDate) throws Exception {
        UserProfileDto user = getUserProfile();
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
        return new ResponseEntity<String>(outbSpendJson != null ? outbSpendJson.toString() : new JSONObject().toString(), HttpStatus.OK);
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
        JSONObject outboundJson = null;
        List<OutboundSpendDto> outboundSpendList = dashboardsService.getOutboundSpendByMonth(filter, false);
        if(outboundSpendList != null && !outboundSpendList.isEmpty()){
            List<CommonMonthlyChartDto> commonMonthlyChartDtoList= new ArrayList<CommonMonthlyChartDto>();
            for (OutboundSpendDto outboundSpendDto: outboundSpendList ) {
                CommonMonthlyChartDto commonMonthlyChartDto= new CommonMonthlyChartDto();
                commonMonthlyChartDto.setBillDate(outboundSpendDto.getBillDate());
                commonMonthlyChartDto.setAmount(outboundSpendDto.getAmount());
                commonMonthlyChartDtoList.add(commonMonthlyChartDto);
            }
            outboundJson = JSONUtil.prepareMonthlyChartJson(commonMonthlyChartDtoList);
        }
        return outboundJson;
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
        JSONObject inboundJson = null;
        List<InboundSpendDto> inboundSpendList = dashboardsService.getInboundSpendByMonth(filter, false);
        if(inboundSpendList != null && !inboundSpendList.isEmpty()){
            List<CommonMonthlyChartDto> commonMonthlyChartDtoList= new ArrayList<CommonMonthlyChartDto>();
            for (InboundSpendDto inboundSpendDto: inboundSpendList ) {
                CommonMonthlyChartDto commonMonthlyChartDto= new CommonMonthlyChartDto();
                commonMonthlyChartDto.setBillDate(inboundSpendDto.getBillDate());
                commonMonthlyChartDto.setAmount(inboundSpendDto.getAmount());
                commonMonthlyChartDtoList.add(commonMonthlyChartDto);
            }
            inboundJson = JSONUtil.prepareMonthlyChartJson(commonMonthlyChartDtoList);
        }
        return inboundJson;
    }

    private JSONObject loadInboundSpendJson(DashboardsFilterCriteria filter) throws JSONException {
        List<InboundSpendDto> inboundSpendList = dashboardsService.getInboundSpend(filter, false);
        List<NetSpendCommonDto> spendlist = NetSpendCommonDto.buildInboundSpendListToNetSpendCommonList(inboundSpendList);
        return JSONUtil.prepareInAndOutBuondJson(spendlist);
    }

    @RequestMapping(value = "/invStsCnt", method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.OPTIONS}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<String> getInvoiceStatusCount() throws Exception {
        UserProfileDto user = getUserProfile();
        DashboardsFilterCriteria filter = loadAppliedFilters(user.getUserId());
        JSONObject stsCntJson = loadInvoiceStatusCountJsonData(InvoiceStatusCountConstant.INVOICE_STATUS_COUNT, filter);
        return new ResponseEntity<String>(stsCntJson != null ? stsCntJson.toString() : new JSONObject().toString(), HttpStatus.OK);
    }

    @RequestMapping(value = "/invStsCntByCarr", method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.OPTIONS}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<String> getInvoiceStatusCountByCarrier(@RequestParam String invoiceStatusId) throws Exception {
        UserProfileDto user = getUserProfile();
        DashboardsFilterCriteria filter = loadAppliedFilters(user.getUserId());
        if(filter != null){
            filter.setInvoiceStatusId(invoiceStatusId);
        }
        JSONObject stsCntJson = loadInvoiceStatusCountJsonData(InvoiceStatusCountConstant.INVOICE_STATUS_COUNT_BY_CARRIER, filter);
        return new ResponseEntity<String>(stsCntJson != null ? stsCntJson.toString() : new JSONObject().toString(), HttpStatus.OK);
    }

    @RequestMapping(value = "/invStsCntByMnth", method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.OPTIONS}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<String> getInvoiceStatusCountByMonth(@RequestParam String carrierId, @RequestParam String invoiceStatusId) throws Exception {
        UserProfileDto user = getUserProfile();
        DashboardsFilterCriteria filter = loadAppliedFilters(user.getUserId());
        if(filter != null){
            filter.setCarriers(carrierId);
            filter.setInvoiceStatusId(invoiceStatusId);
        }
        JSONObject stsCntJson = loadInvoiceStatusCountJsonData(InvoiceStatusCountConstant.INVOICE_STATUS_COUNT_BY_MONTH, filter);
        return new ResponseEntity<String>(stsCntJson != null ? stsCntJson.toString() : new JSONObject().toString(), HttpStatus.OK);
    }

    @RequestMapping(value = "/invStsAmt", method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.OPTIONS}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<String> getInvoiceStatusAmount() throws Exception {
        UserProfileDto user = getUserProfile();
        DashboardsFilterCriteria filter = loadAppliedFilters(user.getUserId());
        JSONObject stsAmtJson = loadInvoiceStatusAmountJsonData(InvoiceStatusAmountConstant.INVOICE_STATUS_AMOUNT, filter);
        return new ResponseEntity<String>(stsAmtJson != null ? stsAmtJson.toString() : new JSONObject().toString(), HttpStatus.OK);
    }

    @RequestMapping(value = "/invStsAmtByCarr", method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.OPTIONS}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<String> getInvoiceStatusAmountByCarrier(@RequestParam String invoiceStatusId) throws Exception {
        UserProfileDto user = getUserProfile();
        DashboardsFilterCriteria filter = loadAppliedFilters(user.getUserId());
        if(filter != null){
            filter.setInvoiceStatusId(invoiceStatusId);
        }
        JSONObject stsAmtJson = loadInvoiceStatusAmountJsonData(InvoiceStatusAmountConstant.INVOICE_STATUS_AMOUNT_BY_CARRIER, filter);
        return new ResponseEntity<String>(stsAmtJson != null ? stsAmtJson.toString() : new JSONObject().toString(), HttpStatus.OK);
    }

    @RequestMapping(value = "/invStsAmtByMnth", method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.OPTIONS}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<String> getInvoiceStatusAmountByMonth(@RequestParam String carrierId, @RequestParam String invoiceStatusId) throws Exception {
        UserProfileDto user = getUserProfile();
        DashboardsFilterCriteria filter = loadAppliedFilters(user.getUserId());
        if(filter != null){
            filter.setCarriers(carrierId);
            filter.setInvoiceStatusId(invoiceStatusId);
        }
        JSONObject stsAmtJson = loadInvoiceStatusAmountJsonData(InvoiceStatusAmountConstant.INVOICE_STATUS_AMOUNT_BY_MONTH, filter);
        return new ResponseEntity<String>(stsAmtJson != null ? stsAmtJson.toString() : new JSONObject().toString(), HttpStatus.OK);
    }

    @RequestMapping(value = "/invMthdScore", method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.OPTIONS}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<String> getInvoiceMethodScore() throws Exception {
        UserProfileDto user = getUserProfile();
        DashboardsFilterCriteria filter = loadAppliedFilters(user.getUserId());
        JSONObject invScoreJson = loadInvoiceMethodScoreJsonData(InvoiceMethodScoreConstant.INVOICE_METHOD_SCORE, filter);
        return new ResponseEntity<String>(invScoreJson != null ? invScoreJson.toString() : new JSONObject().toString(), HttpStatus.OK);
    }

    @RequestMapping(value = "/invMthdScoreByCarr", method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.OPTIONS}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<String> getInvoiceMethodScoreByCarrier(@RequestParam String invoiceMethod) throws Exception {
        UserProfileDto user = getUserProfile();
        DashboardsFilterCriteria filter = loadAppliedFilters(user.getUserId());
        if(filter != null){
            filter.setInvoiceMethod(invoiceMethod);
        }
        JSONObject invScoreJson = loadInvoiceMethodScoreJsonData(InvoiceMethodScoreConstant.INVOICE_METHOD_SCORE_BY_CARRIER, filter);
        return new ResponseEntity<String>(invScoreJson != null ? invScoreJson.toString() : new JSONObject().toString(), HttpStatus.OK);
    }

    @RequestMapping(value = "/invMthdScoreByMnth", method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.OPTIONS}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<String> getInvoiceMethodScoreByMonth(@RequestParam String invoiceMethod, @RequestParam String carrierId) throws Exception {
        UserProfileDto user = getUserProfile();
        DashboardsFilterCriteria filter = loadAppliedFilters(user.getUserId());
        if(filter != null){
            filter.setInvoiceMethod(invoiceMethod);
            filter.setCarriers(carrierId);
        }
        JSONObject invScoreJson = loadInvoiceMethodScoreJsonData(InvoiceMethodScoreConstant.INVOICE_METHOD_SCORE_BY_MONTH, filter);
        return new ResponseEntity<String>(invScoreJson != null ? invScoreJson.toString() : new JSONObject().toString(), HttpStatus.OK);
    }

    @RequestMapping(value = "/orderMatch", method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.OPTIONS}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<String> getOrderMatchStatus() throws Exception {
        UserProfileDto user = getUserProfile();
        DashboardsFilterCriteria filter = loadAppliedFilters(user.getUserId());
        JSONObject ordMchJson = loadOrderMatchJsonData(OrderMatchConstant.ORDER_MATCH, filter);
        return new ResponseEntity<String>(ordMchJson != null ? ordMchJson.toString() : new JSONObject().toString(), HttpStatus.OK);
    }

    @RequestMapping(value = "/orderMatchByCarr", method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.OPTIONS}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<String> getOrderMatchByCarrier(@RequestParam String orderMatchValue) throws Exception {
        UserProfileDto user = getUserProfile();
        DashboardsFilterCriteria filter = loadAppliedFilters(user.getUserId());
        if(filter != null){
            filter.setOrderMatch(orderMatchValue);
        }
        JSONObject ordMchJson = loadOrderMatchJsonData(OrderMatchConstant.ORDER_MATCH_BY_CARRIER, filter);
        return new ResponseEntity<String>(ordMchJson != null ? ordMchJson.toString() : new JSONObject().toString(), HttpStatus.OK);
    }

    @RequestMapping(value = "/orderMatchByMnth", method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.OPTIONS}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<String> getOrderMatchByMonth(@RequestParam String carrierId, @RequestParam String orderMatchValue) throws Exception {
        UserProfileDto user = getUserProfile();
        DashboardsFilterCriteria filter = loadAppliedFilters(user.getUserId());
        if(filter != null){
            filter.setCarriers(carrierId);
            filter.setOrderMatch(orderMatchValue);
        }
        JSONObject ordMchJson = loadOrderMatchJsonData(OrderMatchConstant.ORDER_MATCH_BY_MONTH, filter);
        return new ResponseEntity<String>(ordMchJson != null ? ordMchJson.toString() : new JSONObject().toString(), HttpStatus.OK);
    }

    @RequestMapping(value = "/billedVsApproved", method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.OPTIONS}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<String> getBilledVsApproved() throws Exception {
        UserProfileDto user = getUserProfile();
        DashboardsFilterCriteria filter = loadAppliedFilters(user.getUserId());
        JSONObject billedVsApprovedJson = loadBilledVsApprovedJsonData(BilledVsApprovedConstant.BILLED_VS_APPROVED, filter);
        return new ResponseEntity<String>(billedVsApprovedJson != null ? billedVsApprovedJson.toString() : new JSONObject().toString(), HttpStatus.OK);
    }

    @RequestMapping(value = "/billedVsApprovedByMnth", method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.OPTIONS}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<String> getBilledVsApprovedByMonth(@RequestParam String carrierId, @RequestParam String billedVsApproved) throws Exception {
        UserProfileDto user = getUserProfile();
        DashboardsFilterCriteria filter = loadAppliedFilters(user.getUserId());
        if(filter != null){
            filter.setCarriers(carrierId);
            filter.setBilledVsApproved(billedVsApproved);
        }
        JSONObject billedVsApprovedJson = loadBilledVsApprovedJsonData(BilledVsApprovedConstant.BILLED_VS_APPROVED_BY_MONTH, filter);
        return new ResponseEntity<String>(billedVsApprovedJson != null ? billedVsApprovedJson.toString() : new JSONObject().toString(), HttpStatus.OK);
    }

    @RequestMapping(value = "/recovAdj", method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.OPTIONS}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<String> getRecoveryAdjustment() throws Exception {
        UserProfileDto user = getUserProfile();
        DashboardsFilterCriteria filter = loadAppliedFilters(user.getUserId());
        JSONObject recovAdjJson = loadRecoveryAdjustmentJsonData(RecoveryAdjustmentConstants.RECOVERY_ADJUSTMENT, filter);
        return new ResponseEntity<String>(recovAdjJson != null ? recovAdjJson.toString() : new JSONObject().toString(), HttpStatus.OK);
    }

    @RequestMapping(value = "/recovAdjByCarr", method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.OPTIONS}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<String> getRecoveryAdjustmentByCarrier(@RequestParam String service, @RequestParam String invoiceDate) throws Exception {
        UserProfileDto user = getUserProfile();
        DashboardsFilterCriteria filter = loadAppliedFilters(user.getUserId());
        if(filter != null){
            if (service != null && !service.isEmpty()){
                filter.setService(service);
            }
            if(invoiceDate != null && !invoiceDate.isEmpty()){
                DashboardUtil.setDatesFromMonth(filter, invoiceDate);
            }
        }
        JSONObject recovAdjJson = loadRecoveryAdjustmentJsonData(RecoveryAdjustmentConstants.RECOVERY_ADJUSTMENT_BY_CARRIER, filter);
        return new ResponseEntity<String>(recovAdjJson != null ? recovAdjJson.toString() : new JSONObject().toString(), HttpStatus.OK);
    }

    @RequestMapping(value = "/recovAdjByMnth", method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.OPTIONS}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<String> getRecoveryAdjustmentByMonth(@RequestParam String service, @RequestParam String invoiceDate, @RequestParam String carrierId) throws Exception {
        UserProfileDto user = getUserProfile();
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
        JSONObject recovAdjJson = loadRecoveryAdjustmentJsonData(RecoveryAdjustmentConstants.RECOVERY_ADJUSTMENT_BY_MONTH, filter);
        return new ResponseEntity<String>(recovAdjJson != null ? recovAdjJson.toString() : new JSONObject().toString(), HttpStatus.OK);
    }

    @RequestMapping(value = "/recovServ", method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.OPTIONS}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<String> getRecoveryServices() throws Exception {
        UserProfileDto user = getUserProfile();
        DashboardsFilterCriteria filter = loadAppliedFilters(user.getUserId());
        JSONObject recovServJson = loadRecoveryServicesJsonData(RecoveryServiceConstants.RECOVERY_SERVICE, filter);
        return new ResponseEntity<String>(recovServJson != null ? recovServJson.toString() : new JSONObject().toString(), HttpStatus.OK);
    }

    @RequestMapping(value = "/recovServByMnth", method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.OPTIONS}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<String> getRecoveryServicesByMonth(@RequestParam String carrierId, @RequestParam String service) throws Exception {
        UserProfileDto user = getUserProfile();
        DashboardsFilterCriteria filter = loadAppliedFilters(user.getUserId());
        if(filter != null){
            if(carrierId != null && !carrierId.isEmpty()){
                filter.setCarriers(carrierId);
            }
            if(service != null && !service.isEmpty()){
                filter.setService(service);
            }
        }
        JSONObject recovServJson = loadRecoveryServicesJsonData(RecoveryServiceConstants.RECOVERY_SERVICE_BY_MONTH, filter);
        return new ResponseEntity<String>(recovServJson != null ? recovServJson.toString() : new JSONObject().toString(), HttpStatus.OK);
    }

    @RequestMapping(value = "/pkgExcp", method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.OPTIONS}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<String> getPackageExceptions() throws JSONException {
        UserProfileDto user = getUserProfile();
        DashboardsFilterCriteria filter = loadAppliedFilters(user.getUserId());
        JSONObject pkgExcpJson = loadPackageExceptionsJsonData(PackageExceptionConstants.PACKAGE_EXCEPTION, filter);
        return new ResponseEntity<String>(pkgExcpJson != null ? pkgExcpJson.toString() : new JSONObject().toString(), HttpStatus.OK);
    }

    @RequestMapping(value = "/pkgExcpByCarr", method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.OPTIONS}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<String> getPackageExceptionsByCarrier(@RequestParam String invoiceDate, @RequestParam String deliveryFlag) throws Exception {
        UserProfileDto user = getUserProfile();
        DashboardsFilterCriteria filter = loadAppliedFilters(user.getUserId());
        if(filter != null){
            if (deliveryFlag != null && !deliveryFlag.isEmpty())
                filter.setDeliveryFlag(deliveryFlag);
            DashboardUtil.setDatesFromMonth(filter, invoiceDate);
        }
        JSONObject pkgExcpJson = loadPackageExceptionsJsonData(PackageExceptionConstants.PACKAGE_EXCEPTION_BY_CARRIER, filter);
        return new ResponseEntity<String>(pkgExcpJson != null ? pkgExcpJson.toString() : new JSONObject().toString(), HttpStatus.OK);
    }

    @RequestMapping(value = "/pkgExcpByMnth", method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.OPTIONS}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<String> getPackageExceptionsByMonth(@RequestParam String invoiceDate, @RequestParam String deliveryFlag,@RequestParam String carrierId) throws Exception {
        UserProfileDto user = getUserProfile();
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
        JSONObject pkgExcpJson = loadPackageExceptionsJsonData(PackageExceptionConstants.PACKAGE_EXCEPTION_BY_MONTH, filter);
        return new ResponseEntity<String>(pkgExcpJson != null ? pkgExcpJson.toString() : new JSONObject().toString(), HttpStatus.OK);
    }

    @RequestMapping(value = "/avgSpendPerShipmentByCarrier", method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.OPTIONS}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<String> getAverageSpendPerShipmentByCarrier(@RequestParam String service, @RequestParam String invoiceDate) throws Exception {
        UserProfileDto user = getUserProfile();
        DashboardsFilterCriteria filter = loadAppliedFilters(user.getUserId());
        if(filter !=  null){
            if(service != null && !service.isEmpty()){
                filter.setService(service);
            }
            if(invoiceDate != null && !invoiceDate.isEmpty()){
                DashboardUtil.setDatesFromMonth(filter, invoiceDate);
            }
        }
        JSONObject avgShipmentData = loadShipmentOverviewJsonData(ShipmentOverviewConstant.AVG_SPEND_PER_SHIPMT_BY_CARRIER, filter);
        return new ResponseEntity<String>(avgShipmentData != null ? avgShipmentData.toString() : new JSONObject().toString(), HttpStatus.OK);
    }

    @RequestMapping(value = "/avgSpendPerShipmentByMonth", method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.OPTIONS}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<String> getAverageSpendPerShipmentByMonth(@RequestParam String invoiceDate,@RequestParam String carrierId,@RequestParam String service) throws Exception {
        UserProfileDto user = getUserProfile();
        DashboardsFilterCriteria filter = loadAppliedFilters(user.getUserId());
        if(filter !=  null){
            if(service != null && !service.isEmpty()){
                filter.setService(service);
            }
            if(invoiceDate != null && !invoiceDate.isEmpty()){
                DashboardUtil.setDatesFromMonth(filter, invoiceDate);
            }
            if(carrierId != null && !carrierId.isEmpty()){
                filter.setCarriers(carrierId);
            }
        }
        JSONObject avgShipmentData = loadShipmentOverviewJsonData(ShipmentOverviewConstant.AVG_SPEND_PER_SHIPMT_BY_MONTH, filter);
        return new ResponseEntity<String>(avgShipmentData != null ? avgShipmentData.toString() : new JSONObject().toString(), HttpStatus.OK);
    }

    @RequestMapping(value = "/avgWeightModeByCarrier", method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.OPTIONS}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<String> getAverageWeightModeByCarrier(@RequestParam String service, @RequestParam String invoiceDate) throws Exception {
        UserProfileDto user = getUserProfile();
        DashboardsFilterCriteria filter = loadAppliedFilters(user.getUserId());
        if(filter !=  null){
            if(service != null && !service.isEmpty()){
                filter.setService(service);
            }
            if(invoiceDate != null && !invoiceDate.isEmpty()){
                DashboardUtil.setDatesFromMonth(filter, invoiceDate);
            }
        }
        JSONObject avgWeightModeData = loadShipmentOverviewJsonData(ShipmentOverviewConstant.AVG_WEIGHT_SHIPMT_BY_CARRIER, filter);
        return new ResponseEntity<String>(avgWeightModeData != null ? avgWeightModeData.toString() : new JSONObject().toString(), HttpStatus.OK);
    }

    @RequestMapping(value = "/avgWeightModeByMonth", method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.OPTIONS}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<String> getAverageWeightModeByMonth(@RequestParam String service,@RequestParam String invoiceDate,@RequestParam String carrierId) throws Exception {
        UserProfileDto user = getUserProfile();
        DashboardsFilterCriteria filter = loadAppliedFilters(user.getUserId());
        if(filter !=  null){
            if(service != null && !service.isEmpty()){
                filter.setService(service);
            }
            if(invoiceDate != null && !invoiceDate.isEmpty()){
                DashboardUtil.setDatesFromMonth(filter, invoiceDate);
            }
            if(carrierId != null && !carrierId.isEmpty()){
                filter.setCarriers(carrierId);
            }
        }
        JSONObject avgWeightModeData = loadShipmentOverviewJsonData(ShipmentOverviewConstant.AVG_WEIGHT_SHIPMT_BY_MONTH, filter);
        return new ResponseEntity<String>(avgWeightModeData != null ? avgWeightModeData.toString() : new JSONObject().toString(), HttpStatus.OK);
    }


    @RequestMapping(value = "/annualSumm", method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.OPTIONS}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<String> getAnnualSummary() throws JSONException {
        UserProfileDto user = getUserProfile();
        DashboardsFilterCriteria filter = loadAppliedFilters(user.getUserId());
        JSONObject annualSummaryJson = loadAnnualSummaryJsonData(AnnualSummaryConstants.ANNUAL_SUMMARY, filter);
        return new ResponseEntity<String>(annualSummaryJson != null ? annualSummaryJson.toString() : new JSONObject().toString(), HttpStatus.OK);
    }

    @RequestMapping(value = "/annualSummByService", method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.OPTIONS}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<String> getAnnualSummaryByService(@RequestParam String mode) throws JSONException {
        UserProfileDto user = getUserProfile();
        DashboardsFilterCriteria filter = loadAppliedFilters(user.getUserId());
        if(filter != null){
            if(mode != null && !mode.isEmpty()){
                filter.setService(mode);
            }
        }
        JSONObject annualSummaryJson = loadAnnualSummaryJsonData(AnnualSummaryConstants.ANNUAL_SUMMARY_BY_SERVICE, filter);
        return new ResponseEntity<String>(annualSummaryJson != null ? annualSummaryJson.toString() : new JSONObject().toString(), HttpStatus.OK);
    }

    @RequestMapping(value = "/annualSummByCarrier", method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.OPTIONS}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<String> getAnnualSummaryByCarrier(@RequestParam String service, @RequestParam String mode) throws JSONException {
        UserProfileDto user = getUserProfile();
        DashboardsFilterCriteria filter = loadAppliedFilters(user.getUserId());
        if(filter != null){
            if(service != null && !service.isEmpty()){
                filter.setService(service);
            }
            if(mode != null && !mode.isEmpty()){
                filter.setModeNames(mode);
            }
        }
        JSONObject annualSummaryJson = loadAnnualSummaryJsonData(AnnualSummaryConstants.ANNUAL_SUMMARY_BY_CARRIER, filter);
        return new ResponseEntity<String>(annualSummaryJson != null ? annualSummaryJson.toString() : new JSONObject().toString(), HttpStatus.OK);
    }

    @RequestMapping(value = "/annualSummByMonth", method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.OPTIONS}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<String> getAnnualSummaryByMonth(@RequestParam String service, @RequestParam String mode, @RequestParam String carrierId) throws JSONException {
        UserProfileDto user = getUserProfile();
        DashboardsFilterCriteria filter = loadAppliedFilters(user.getUserId());
        if(filter != null){
            if(service != null && !service.isEmpty()){
                filter.setService(service);
            }
            if(mode != null && !mode.isEmpty()){
                filter.setModeNames(mode);
            }
            if(carrierId != null && !carrierId.isEmpty()){
                filter.setCarriers(carrierId);
            }
        }
        JSONObject annualSummaryJson = loadAnnualSummaryJsonData(AnnualSummaryConstants.ANNUAL_SUMMARY_BY_MONTH, filter);
        return new ResponseEntity<String>(annualSummaryJson != null ? annualSummaryJson.toString(): new JSONObject().toString(), HttpStatus.OK);
    }

    @RequestMapping(value = "/monthlySpendByMode", method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.OPTIONS}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<String> getMonthlySpendByMode() throws JSONException {
        UserProfileDto user = getUserProfile();
        DashboardsFilterCriteria filter = loadAppliedFilters(user.getUserId());
        JSONObject monthlySpendJson = loadMonthlySpendByModeJsonData(MonthlySpendByModeConstants.MONTHLY_SPEND_BY_MODE, filter);
        return new ResponseEntity<String>(monthlySpendJson != null ? monthlySpendJson.toString() : new JSONObject().toString(), HttpStatus.OK);
    }

    @RequestMapping(value = "/monthlySpendByModeByServ", method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.OPTIONS}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<String> getMonthlySpendByModeByService(@RequestParam String mode) throws JSONException {
        UserProfileDto user = getUserProfile();
        DashboardsFilterCriteria filter = loadAppliedFilters(user.getUserId());
        if(filter != null){
            if(mode != null && !mode.isEmpty()){
                filter.setService(mode);
            }
        }
        JSONObject monthlySpendJson = loadMonthlySpendByModeJsonData(MonthlySpendByModeConstants.MONTHLY_SPEND_BY_MODE_BY_SERVICE, filter);
        return new ResponseEntity<String>(monthlySpendJson != null ? monthlySpendJson.toString() : new JSONObject().toString(), HttpStatus.OK);
    }

    @RequestMapping(value = "/accSumm", method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.OPTIONS}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<String> getAccountSummary() throws JSONException {
        UserProfileDto user = getUserProfile();
        DashboardsFilterCriteria filter = loadAppliedFilters(user.getUserId());
        JSONObject accSummJson = loadAccountSummaryJsonData(AccountSummaryConstants.ACCOUNT_SUMMARY, filter);
        return new ResponseEntity<String>(accSummJson != null ? accSummJson.toString() : new JSONObject().toString(), HttpStatus.OK);
    }

    @RequestMapping(value = "/parcelAccSumm", method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.OPTIONS}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<String> getParcelAccountSummary() throws JSONException {
        UserProfileDto user = getUserProfile();
        DashboardsFilterCriteria filter = loadAppliedFilters(user.getUserId());
        JSONObject accSummJson = loadAccountSummaryJsonData(AccountSummaryConstants.PARCEL_ACCOUNT_SUMMARY, filter);
        return new ResponseEntity<String>(accSummJson != null ? accSummJson.toString() : new JSONObject().toString(), HttpStatus.OK);
    }

    private JSONObject loadAccountSummaryJsonData(AccountSummaryConstants accountSummaryType, DashboardsFilterCriteria filter) throws JSONException {
        JSONObject accSummJson = null;
        switch (accountSummaryType){
            case ACCOUNT_SUMMARY:
                accSummJson = loadAccountSummaryJson(filter);
                break;
            case PARCEL_ACCOUNT_SUMMARY:
                accSummJson = loadParcelAccountSummaryJson(filter);
                break;
            default:
                throw new MethodNotFoundException("Method param value not matched");
        }
        return accSummJson;
    }

    private JSONObject loadParcelAccountSummaryJson(DashboardsFilterCriteria filter) throws JSONException {
        List<AccountSummaryDto> accountSummaryList = dashboardsService.getParcelAccountSummary(filter, false);
        return JSONUtil.prepareParcelAccountSummaryJson(accountSummaryList, filter);
    }

    private JSONObject loadAccountSummaryJson(DashboardsFilterCriteria filter) throws JSONException {
        List<AccountSummaryDto> accountSummaryList = dashboardsService.getAccountSummary(filter, false);
        return JSONUtil.prepareAccountSummaryJson(accountSummaryList, filter);
    }

    private JSONObject loadMonthlySpendByModeJsonData(MonthlySpendByModeConstants monthlySpendByModeType, DashboardsFilterCriteria filter) throws JSONException {
        JSONObject monthlySpendJson = null;
        switch (monthlySpendByModeType){
            case MONTHLY_SPEND_BY_MODE:
                monthlySpendJson = loadMonthlySpendByModeJson(filter);
                break;
            case MONTHLY_SPEND_BY_MODE_BY_SERVICE:
                monthlySpendJson = loadMonthlySpendByModeByServiceJson(filter);
                break;
            default:
                throw new MethodNotFoundException("Method param value not matched");
        }
        return monthlySpendJson;
    }

    private JSONObject loadMonthlySpendByModeByServiceJson(DashboardsFilterCriteria filter) throws JSONException {
        List<MonthlySpendByModeDto> monthlySpendByModeList = dashboardsService.getMonthlySpendByModeByService(filter, false);
        return JSONUtil.prepareMonthlySpendByModeByServiceJson(monthlySpendByModeList);
    }

    private JSONObject loadMonthlySpendByModeJson(DashboardsFilterCriteria filter) throws JSONException {
        List<MonthlySpendByModeDto> monthlySpendByModeList = dashboardsService.getMonthlySpendByMode(filter, false);
        return JSONUtil.prepareMonthlySpendByModeJson(monthlySpendByModeList);
    }

    private JSONObject loadAnnualSummaryJsonData(AnnualSummaryConstants annualSummaryType, DashboardsFilterCriteria filter) throws JSONException {
        JSONObject annualSummaryJson = null;
        switch (annualSummaryType){
            case ANNUAL_SUMMARY:
                annualSummaryJson = loadAnnualSummaryJson(filter);
                break;
            case ANNUAL_SUMMARY_BY_SERVICE:
                annualSummaryJson = loadAnnualSummaryByServiceJson(filter);
                break;
            case ANNUAL_SUMMARY_BY_CARRIER:
                annualSummaryJson = loadAnnualSummaryByCarrierJson(filter);
                break;
            case ANNUAL_SUMMARY_BY_MONTH:
                annualSummaryJson = loadAnnualSummaryByMonthJson(filter);
                break;
            default:
                throw new MethodNotFoundException("Method param value not matched");
        }
        return annualSummaryJson;
    }

    private JSONObject loadAnnualSummaryByMonthJson(DashboardsFilterCriteria filter) throws JSONException {
        JSONObject annualSummaryJson = null;
        List<AnnualSummaryDto> annualSummaryList = dashboardsService.getAnnualSummaryByMonth(filter, false);
        if(annualSummaryList != null && !annualSummaryList.isEmpty()){
            List<CommonMonthlyChartDto> monthlyChartDataList = new ArrayList<CommonMonthlyChartDto>();
            for(AnnualSummaryDto annualSummary : annualSummaryList){
                if(annualSummary != null){
                    CommonMonthlyChartDto commonMonthlyChartDto= new CommonMonthlyChartDto();
                    commonMonthlyChartDto.setBillDate(annualSummary.getBillDate());
                    commonMonthlyChartDto.setAmount(annualSummary.getAmount());
                    monthlyChartDataList.add(commonMonthlyChartDto);
                }
            }
            annualSummaryJson = JSONUtil.prepareMonthlyChartJson(monthlyChartDataList);
        }
        return annualSummaryJson;
    }

    private JSONObject loadAnnualSummaryByCarrierJson(DashboardsFilterCriteria filter) throws JSONException {
        List<AnnualSummaryDto> annualSummaryList = dashboardsService.getAnnualSummaryByCarrier(filter, false);
        List<CommonValuesForChartDto> commonValuesForChartList = CommonValuesForChartDto.buildAnnualSummaryListToCommonValueForChartList(annualSummaryList);
        return JSONUtil.prepareCommonJsonForChart(commonValuesForChartList);
    }

    private JSONObject loadAnnualSummaryByServiceJson(DashboardsFilterCriteria filter) throws JSONException {
        List<AnnualSummaryDto> annualSummaryList = dashboardsService.getAnnualSummaryByService(filter, false);
        return JSONUtil.prepareAnnualSummaryByServiceJson(annualSummaryList);
    }

    private JSONObject loadAnnualSummaryJson(DashboardsFilterCriteria filter) throws JSONException {
        List<AnnualSummaryDto> annualSummaryList = dashboardsService.getAnnualSummary(filter, false);
        return JSONUtil.prepareAnnualSummaryJson(annualSummaryList);
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
            List<CommonMonthlyChartDto> commonMonthlyChartDtoList= new ArrayList<CommonMonthlyChartDto>();
            for (PackageExceptionDto packageExceptionDto: packageExceptionList ) {
                CommonMonthlyChartDto commonMonthlyChartDto= new CommonMonthlyChartDto();
                commonMonthlyChartDto.setBillDate(packageExceptionDto.getBillDate());
                commonMonthlyChartDto.setAmount(packageExceptionDto.getAmount());
                commonMonthlyChartDtoList.add(commonMonthlyChartDto);
            }
            pkgExcpJson = JSONUtil.prepareMonthlyChartJson(commonMonthlyChartDtoList);
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

    private JSONObject loadRecoveryServicesJsonData(RecoveryServiceConstants recoveryServiceType, DashboardsFilterCriteria filter) throws Exception {
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
            List<CommonMonthlyChartDto> commonMonthlyChartDtoList= new ArrayList<CommonMonthlyChartDto>();
            for (RecoveryServiceDto recoveryServiceDto: recoveryServiceList ) {
                CommonMonthlyChartDto commonMonthlyChartDto= new CommonMonthlyChartDto();
                commonMonthlyChartDto.setBillDate(recoveryServiceDto.getBillDate());
                commonMonthlyChartDto.setAmount(recoveryServiceDto.getAmount());
                commonMonthlyChartDtoList.add(commonMonthlyChartDto);
            }
            recovServJson = JSONUtil.prepareMonthlyChartJson(commonMonthlyChartDtoList);
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

    private JSONObject loadRecoveryAdjustmentJsonData(RecoveryAdjustmentConstants recoveryAdjustmentType, DashboardsFilterCriteria filter) throws Exception {
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

    private JSONObject loadRecoveryAdjustmentByMonthJson(DashboardsFilterCriteria filter) throws Exception {
        JSONObject recovAdjJson = null;
        List<RecoveryAdjustmentDto> recoveryAdjustmentList = dashboardsService.getRecoveryAdjustmentByMonth(filter, false);
        if(recoveryAdjustmentList != null && !recoveryAdjustmentList.isEmpty()){
            List<CommonMonthlyChartDto> commonMonthlyChartDtoList= new ArrayList<CommonMonthlyChartDto>();
            for (RecoveryAdjustmentDto recoveryAdjustmentDto: recoveryAdjustmentList ) {
                CommonMonthlyChartDto commonMonthlyChartDto= new CommonMonthlyChartDto();
                commonMonthlyChartDto.setBillDate(recoveryAdjustmentDto.getBillDate());
                commonMonthlyChartDto.setAmount(recoveryAdjustmentDto.getAmount());
                commonMonthlyChartDtoList.add(commonMonthlyChartDto);
            }
            recovAdjJson = JSONUtil.prepareMonthlyChartJson(commonMonthlyChartDtoList);
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

    private JSONObject loadBilledVsApprovedJsonData(BilledVsApprovedConstant billedVsApprovedType, DashboardsFilterCriteria filter) throws Exception {
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

    private JSONObject loadBilledVsApprovedByMonthJson(DashboardsFilterCriteria filter) throws Exception {
        JSONObject billedVsApprovedJson = null;
        List<BilledVsApprovedDto> billedVsApprovedList = dashboardsService.getBilledVsApprovedByMonth(filter);
        if(billedVsApprovedList != null && !billedVsApprovedList.isEmpty()){
            List<CommonMonthlyChartDto> commonMonthlyChartDtoList= new ArrayList<CommonMonthlyChartDto>();
            for (BilledVsApprovedDto billedVsApprovedDto: billedVsApprovedList ) {
                CommonMonthlyChartDto commonMonthlyChartDto= new CommonMonthlyChartDto();
                commonMonthlyChartDto.setBillDate(billedVsApprovedDto.getBillDate());
                commonMonthlyChartDto.setAmount(billedVsApprovedDto.getAmount());
                commonMonthlyChartDtoList.add(commonMonthlyChartDto);
            }
            billedVsApprovedJson = JSONUtil.prepareMonthlyChartJson(commonMonthlyChartDtoList);
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

    private JSONObject loadOrderMatchJsonData(OrderMatchConstant orderMatchType, DashboardsFilterCriteria filter) throws Exception {
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

    private JSONObject loadOrdeMatchByMonthJson(DashboardsFilterCriteria filter) throws Exception {
        JSONObject orderMatchJson = null;
        List<OrderMatchDto> orderMatchList = dashboardsService.getOrderMatchByMonth(filter);
        if(orderMatchList != null && !orderMatchList.isEmpty()){
            List<CommonMonthlyChartDto> commonMonthlyChartDtoList= new ArrayList<CommonMonthlyChartDto>();
            for (OrderMatchDto orderMatchDto: orderMatchList ) {
                CommonMonthlyChartDto commonMonthlyChartDto= new CommonMonthlyChartDto();
                commonMonthlyChartDto.setBillDate(orderMatchDto.getBillDate());
                commonMonthlyChartDto.setAmount(orderMatchDto.getAmount());
                commonMonthlyChartDtoList.add(commonMonthlyChartDto);
            }
            orderMatchJson = JSONUtil.prepareMonthlyChartJson(commonMonthlyChartDtoList);
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

    private JSONObject loadInvoiceMethodScoreJsonData(InvoiceMethodScoreConstant invoiceMethodScoreType, DashboardsFilterCriteria filter) throws Exception {
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

    private JSONObject loadInvoiceMethodScoreByMonthJson(DashboardsFilterCriteria filter) throws Exception {
        JSONObject invMethodScoreJson = null;
        List<InvoiceMethodScoreDto> invMthScrList = dashboardsService.getInvoiceMethodScoreByMonth(filter);
        if(invMthScrList != null && !invMthScrList.isEmpty()){
            List<CommonMonthlyChartDto> commonMonthlyChartDtoList= new ArrayList<CommonMonthlyChartDto>();
            for (InvoiceMethodScoreDto invoiceMethodScoreDto: invMthScrList ) {
                CommonMonthlyChartDto commonMonthlyChartDto= new CommonMonthlyChartDto();
                commonMonthlyChartDto.setBillDate(invoiceMethodScoreDto.getBillDate());
                commonMonthlyChartDto.setAmount(invoiceMethodScoreDto.getAmount());
                commonMonthlyChartDtoList.add(commonMonthlyChartDto);
            }
            invMethodScoreJson = JSONUtil.prepareMonthlyChartJson(commonMonthlyChartDtoList);
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
        if(invMthScrList != null && invMthScrList.size() > 0){
            invMethodScoreJson = JSONUtil.prepareInvoiceMethodScoreJson(invMthScrList);
        }
        return invMethodScoreJson;
    }

    private JSONObject loadInvoiceStatusAmountJsonData(InvoiceStatusAmountConstant invStsType, DashboardsFilterCriteria filter) throws Exception {
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

    private JSONObject loadInvoiceStatusAmountByMonthJson(DashboardsFilterCriteria filter) throws Exception {
        JSONObject invStsJson = null;
        List<InvoiceStatusAmountDto> invStsAmtList = dashboardsService.getInvoiceStatusAmountByMonth(filter);
        if(invStsAmtList != null && !invStsAmtList.isEmpty()){
            List<CommonMonthlyChartDto> commonMonthlyChartDtoList= new ArrayList<CommonMonthlyChartDto>();
            for (InvoiceStatusAmountDto invoiceStatusAmountDto: invStsAmtList ) {
                CommonMonthlyChartDto commonMonthlyChartDto= new CommonMonthlyChartDto();
                commonMonthlyChartDto.setBillDate(invoiceStatusAmountDto.getBillDate());
                commonMonthlyChartDto.setAmount(invoiceStatusAmountDto.getAmount());
                commonMonthlyChartDtoList.add(commonMonthlyChartDto);
            }
            invStsJson = JSONUtil.prepareMonthlyChartJson(commonMonthlyChartDtoList);
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

    private JSONObject loadInvoiceStatusCountJsonData(InvoiceStatusCountConstant invStsType, DashboardsFilterCriteria filter) throws Exception {
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

    private JSONObject loadInvoiceStatusByMonthJson(DashboardsFilterCriteria filter) throws Exception {
        JSONObject invStsJson = null;
        List<InvoiceStatusCountDto> invStsCountList = dashboardsService.getInvoiceStatusCountByMonth(filter);
        if(invStsCountList != null && !invStsCountList.isEmpty()){
            List<CommonMonthlyChartDto> commonMonthlyChartDtoList= new ArrayList<CommonMonthlyChartDto>();
            for (InvoiceStatusCountDto invoiceStatusCountDto: invStsCountList ) {
                CommonMonthlyChartDto commonMonthlyChartDto= new CommonMonthlyChartDto();
                commonMonthlyChartDto.setBillDate(invoiceStatusCountDto.getBillDate());
                commonMonthlyChartDto.setAmount(invoiceStatusCountDto.getAmount());
                commonMonthlyChartDtoList.add(commonMonthlyChartDto);
            }
            invStsJson = JSONUtil.prepareMonthlyChartJson(commonMonthlyChartDtoList);
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

    private JSONObject loadNetSpendJsonData(NetSpendConstant netSpendType, DashboardsFilterCriteria filter) throws Exception {
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

    private JSONObject loadNetSpendByMonthJson(DashboardsFilterCriteria filter) throws Exception {
        JSONObject netSpendJson = null;
        List<NetSpendByModeDto> netSpendByMonthDtoList = dashboardsService.getNetSpendByMonth(filter, false);
        if(netSpendByMonthDtoList != null && !netSpendByMonthDtoList.isEmpty()){
            List<CommonMonthlyChartDto> commonMonthlyChartDtoList= new ArrayList<CommonMonthlyChartDto>();
            for (NetSpendByModeDto netSpend : netSpendByMonthDtoList ) {
                CommonMonthlyChartDto commonMonthlyChartDto= new CommonMonthlyChartDto();
                commonMonthlyChartDto.setBillDate(netSpend.getBillDate());
                commonMonthlyChartDto.setAmount(netSpend.getAmount());
                commonMonthlyChartDtoList.add(commonMonthlyChartDto);
            }
            netSpendJson = JSONUtil.prepareMonthlyChartJson(commonMonthlyChartDtoList);
        }
        return netSpendJson;
    }

    private JSONObject loadNetSpendByCarrierJson(DashboardsFilterCriteria filter) throws JSONException {
        JSONObject netSpendJsonData = null;

        List<NetSpendByModeDto> netSpendList = dashboardsService.getNetSpendByCarrier(filter, false);
        if(netSpendList != null && !netSpendList.isEmpty()){
            List<CommonValuesForChartDto> commonValueList = new ArrayList<CommonValuesForChartDto>();
            for(NetSpendByModeDto netSpend : netSpendList){
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

    private JSONObject loadNetSpendOverTimeByMonthJson(DashboardsFilterCriteria filter) throws Exception {
        JSONObject netSpendJson = null;
        List<NetSpendOverTimeDto> netSpendDtoList = dashboardsService.getNetSpendOverTimeByMonth(filter, false);
        if(netSpendDtoList != null && !netSpendDtoList.isEmpty()){
            List<CommonMonthlyChartDto> commonMonthlyChartDtoList= new ArrayList<CommonMonthlyChartDto>();
            for (NetSpendOverTimeDto netSpendOverTime: netSpendDtoList ) {
                CommonMonthlyChartDto commonMonthlyChartDto= new CommonMonthlyChartDto();
                commonMonthlyChartDto.setBillDate(netSpendOverTime.getBillDate());
                commonMonthlyChartDto.setAmount(netSpendOverTime.getAmount());
                commonMonthlyChartDtoList.add(commonMonthlyChartDto);
            }
            netSpendJson = JSONUtil.prepareMonthlyChartJson(commonMonthlyChartDtoList);
        }
        return netSpendJson;
    }

    private JSONObject loadNetSpendByModeJson(DashboardsFilterCriteria filter) throws JSONException {
        JSONObject netSpendJsonData = null;
        List<NetSpendByModeDto> netSpendDtoList = dashboardsService.getNetSpendByModes(filter, false);
        if(netSpendDtoList != null && !netSpendDtoList.isEmpty()){
            netSpendJsonData = JSONUtil.prepareNetSpendByModesJson(netSpendDtoList);
        }
        return netSpendJsonData;
    }

    private JSONObject loadTaxSpendJsonData(TaxSpendConstant taxSpendType, DashboardsFilterCriteria filter) throws Exception {
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

    private JSONObject loadTaxSpendByMonthJson(DashboardsFilterCriteria filter) throws Exception {
        JSONObject taxSpendJson = null;
        List<TaxSpendDto> taxSpendList = dashboardsService.getTaxSpendByMonth(filter);
        if(taxSpendList != null && !taxSpendList.isEmpty()){
            List<CommonMonthlyChartDto> commonMonthlyChartDtoList= new ArrayList<CommonMonthlyChartDto>();
            for (TaxSpendDto taxSpend: taxSpendList ) {
                CommonMonthlyChartDto commonMonthlyChartDto= new CommonMonthlyChartDto();
                commonMonthlyChartDto.setBillDate(taxSpend.getBillDate());
                commonMonthlyChartDto.setAmount(taxSpend.getAmount());
                commonMonthlyChartDtoList.add(commonMonthlyChartDto);
            }
            taxSpendJson = JSONUtil.prepareMonthlyChartJson(commonMonthlyChartDtoList);
        }
        return taxSpendJson;
    }

    private JSONObject loadTaxSpendByCarrJson(DashboardsFilterCriteria filter) throws JSONException {
        JSONObject taxSpendJson = null;
        List<TaxSpendDto> taxSpendList = dashboardsService.getTaxSpendByCarrier(filter);
        if(taxSpendList != null && taxSpendList.size() > 0){
            List<CommonValuesForChartDto> commonValueList = new ArrayList<CommonValuesForChartDto>();
            for(TaxSpendDto taxSpend : taxSpendList){
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

    private JSONObject loadAccessorialSpendJsonData(AccessorialSpendConstant accSpendType, DashboardsFilterCriteria filter) throws Exception {
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
            case TOP_ACCESSORIAL_SPEND_BY_ACC:
                accSpendJson = loadTopAccessorialSpendByAccJson(filter);
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

    private JSONObject loadTopAccessorialSpendByAccJson(DashboardsFilterCriteria filter) throws JSONException {
        JSONObject accSpendJson = null;
        List<AccessorialSpendDto> accSpendList = dashboardsService.getTopAccessorialSpendByAccessorial(filter, false);
        if(accSpendList != null && accSpendList.size() > 0){
            List<CommonValuesForChartDto> commonValList = null;
            if(accSpendList != null && accSpendList.size() > 0){
                commonValList = new ArrayList<CommonValuesForChartDto>();
                for(AccessorialSpendDto accSpend : accSpendList){
                    if(accSpend != null){
                        CommonValuesForChartDto commonValuesForChart = new CommonValuesForChartDto();
                        commonValuesForChart.setName(accSpend.getAccessorialName());
                        commonValuesForChart.setValue(accSpend.getSpend());
                        commonValList.add(commonValuesForChart);
                    }
                }
                accSpendJson = JSONUtil.prepareCommonJsonForChart(commonValList);
            }
        }
        return accSpendJson;
    }

    private JSONObject loadAccessorialSpendByMonthJson(DashboardsFilterCriteria filter) throws Exception {
        JSONObject accSpendJson = null;
        List<AccessorialSpendDto> accSpendList = dashboardsService.getAccessorialSpendByMonth(filter, false);
        if(accSpendList != null && !accSpendList.isEmpty()){
            List<CommonMonthlyChartDto> commonMonthlyChartDtoList= new ArrayList<CommonMonthlyChartDto>();
            for (AccessorialSpendDto accessorialSpendDto: accSpendList ) {
                CommonMonthlyChartDto commonMonthlyChartDto= new CommonMonthlyChartDto();
                commonMonthlyChartDto.setBillDate(accessorialSpendDto.getBillingDate());
                commonMonthlyChartDto.setAmount(accessorialSpendDto.getAmount());
                commonMonthlyChartDtoList.add(commonMonthlyChartDto);
            }
            accSpendJson = JSONUtil.prepareMonthlyChartJson(commonMonthlyChartDtoList);
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

    private JSONObject loadTopAccessorialSpendByMonthJson(DashboardsFilterCriteria filter) throws Exception {
        JSONObject accesorialJson = null;
        List<AccessorialSpendDto> topAccSpendList = dashboardsService.getTopAccessorialSpendByMonth(filter, false);
        if(topAccSpendList != null && !topAccSpendList.isEmpty()){
            List<CommonMonthlyChartDto> commonMonthlyChartDtoList= new ArrayList<CommonMonthlyChartDto>();
            for (AccessorialSpendDto accessorialSpendDto: topAccSpendList ) {
                CommonMonthlyChartDto commonMonthlyChartDto= new CommonMonthlyChartDto();
                commonMonthlyChartDto.setBillDate(accessorialSpendDto.getBillingDate());
                commonMonthlyChartDto.setAmount(accessorialSpendDto.getAmount());
                commonMonthlyChartDtoList.add(commonMonthlyChartDto);
            }
            accesorialJson = JSONUtil.prepareMonthlyChartJson(commonMonthlyChartDtoList);
        }
        return accesorialJson;
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
    private JSONObject loadShipmentByRegionJsonData(ShipmentsRegionConstant shipmentsRegionConstant, DashboardsFilterCriteria filter) throws Exception {
        JSONObject resultJson = null;
        switch (shipmentsRegionConstant){
            case SHIPMENTS_REGION:
                resultJson = loadShipmentByRegionJson(filter);
                break;
            case SHIPMENTS_REGION_BY_CARRIER:
                resultJson = loadShipmentRegionByCarrierJson(filter);
                break;
            case SHIPMENTS_REGION_BY_MONTH:
                resultJson = loadShipmentRegionByMonthJson(filter);
                break;
            default:
                throw new MethodNotFoundException("Method param value not matched");
        }
        return resultJson;
    }
    private JSONObject loadShipmentByRegionJson(DashboardsFilterCriteria dashboardsFilterCriteria) throws Exception {
        JSONObject resultJsonData = null;
        dashboardsFilterCriteria.setNoOfLanes(100);
        List<ShipmentRegionDto> shipmentRegionDtoList = dashboardsService.getShipmentByRegion(dashboardsFilterCriteria);

        if(shipmentRegionDtoList != null && !shipmentRegionDtoList.isEmpty()){
            Set<String> addressList = new HashSet<String>();
            int i = 0;
            for(ShipmentRegionDto shipmentRegion : shipmentRegionDtoList){
                if(shipmentRegion != null){
                    if(shipmentRegion.getShipperAddress() != null){
                        addressList.add(shipmentRegion.getShipperAddress().toUpperCase());
                    }
                    if(shipmentRegion.getReceiverAddress() != null){
                        addressList.add(shipmentRegion.getReceiverAddress().toUpperCase());
                    }
                }
            }
            Set<MapCoordinatesDto>  mapCoordinatesDtoList = dashboardsService.getMapCoordinates(addressList);
            resultJsonData = JSONUtil.prepareShipmentByRegionLanesJson(shipmentRegionDtoList);
            resultJsonData = JSONUtil.prepareShipmentByRegionNodesJson(mapCoordinatesDtoList , resultJsonData);
        }
        if(resultJsonData != null){
            resultJsonData.remove("addressList");
        }
        return resultJsonData;
    }

    public JSONObject loadShipmentRegionByCarrierJson(DashboardsFilterCriteria filterCriteria) throws Exception {
        List<ShipmentRegionDto> shipmentRegionDtoList = dashboardsService.getShipmentRegionByCarrier(filterCriteria);
        List<CommonValuesForChartDto> commonValuesForChartDtoList = new ArrayList<CommonValuesForChartDto>();
        for(ShipmentRegionDto shipmentRegionDto : shipmentRegionDtoList ) {
            CommonValuesForChartDto commonValuesForChartDto = new CommonValuesForChartDto();
            commonValuesForChartDto.setId(shipmentRegionDto.getCarrierId());
            commonValuesForChartDto.setName(shipmentRegionDto.getCarrierName());
            commonValuesForChartDto.setValue(shipmentRegionDto.getSpend());
            commonValuesForChartDtoList.add(commonValuesForChartDto);
        }
        return JSONUtil.prepareCommonJsonForChart(commonValuesForChartDtoList);
    }

    public JSONObject loadShipmentRegionByMonthJson(DashboardsFilterCriteria filterCriteria) throws Exception {
        List<ShipmentRegionDto> shipmentRegionDtoList = dashboardsService.getShipmentRegionByMonth(filterCriteria);
        List<CommonMonthlyChartDto> commonMonthlyChartDtoList= new ArrayList<CommonMonthlyChartDto>();
        for (ShipmentRegionDto shipmentRegionDto: shipmentRegionDtoList ) {
            CommonMonthlyChartDto commonMonthlyChartDto= new CommonMonthlyChartDto();
            commonMonthlyChartDto.setBillDate(shipmentRegionDto.getBillDate());
            commonMonthlyChartDto.setAmount(shipmentRegionDto.getAmount());
            commonMonthlyChartDtoList.add(commonMonthlyChartDto);
        }
        return  JSONUtil.prepareMonthlyChartJson(commonMonthlyChartDtoList);
    }

    private JSONObject loadTopShippingLanesJsonData(ShippingLanesConstant shippingLanesConstant, DashboardsFilterCriteria filter) throws Exception {
        JSONObject resultJson = null;
        switch (shippingLanesConstant){
            case SHIPPING_LANES:
                resultJson = loadTopShippingLanesJsonData(filter);
                break;
            case SHIPPING_LANES_BY_CARRIER:
                resultJson = loadShippingLanesByCarrierJson(filter);
                break;
            case SHIPPING_LANES_BY_MONTH:
                resultJson = loadShippingLanesByMonthJson(filter);
                break;
            default:
                throw new MethodNotFoundException("Method param value not matched");
        }
        return resultJson;
    }

    public JSONObject loadTopShippingLanesJsonData(DashboardsFilterCriteria filterCriteria) throws Exception {
        List<ShippingLanesDto> shippingLanesDtoList = dashboardsService.loadTopShippingLanes(filterCriteria);
        return JSONUtil.prepareTopShippingLanesJson(shippingLanesDtoList);
    }

    public JSONObject loadShippingLanesByCarrierJson(DashboardsFilterCriteria filterCriteria) throws Exception {
        List<ShippingLanesDto> shippingLanesDtoList = dashboardsService.getShippingLanesByCarrier(filterCriteria);
        List<CommonValuesForChartDto> commonValuesForChartDtoList = new ArrayList<CommonValuesForChartDto>();
        for(ShippingLanesDto shippingLanesDto : shippingLanesDtoList ) {
            CommonValuesForChartDto commonValuesForChartDto = new CommonValuesForChartDto();
            commonValuesForChartDto.setId(shippingLanesDto.getCarrierId());
            commonValuesForChartDto.setName(shippingLanesDto.getCarrierName());
            commonValuesForChartDto.setValue(shippingLanesDto.getSpend());
            commonValuesForChartDtoList.add(commonValuesForChartDto);
        }
        return  JSONUtil.prepareCommonJsonForChart(commonValuesForChartDtoList);
    }

    public JSONObject loadShippingLanesByMonthJson(DashboardsFilterCriteria filterCriteria) throws Exception {
        List<ShippingLanesDto> shippingLanesDtoList = dashboardsService.getShippingLanesByMonth(filterCriteria);
        List<CommonMonthlyChartDto> commonMonthlyChartDtoList= new ArrayList<CommonMonthlyChartDto>();
        for (ShippingLanesDto shippingLanesDto: shippingLanesDtoList ) {
            CommonMonthlyChartDto commonMonthlyChartDto= new CommonMonthlyChartDto();
            commonMonthlyChartDto.setBillDate(shippingLanesDto.getBillDate());
            commonMonthlyChartDto.setAmount(shippingLanesDto.getAmount());

            commonMonthlyChartDtoList.add(commonMonthlyChartDto);
        }
        return  JSONUtil.prepareMonthlyChartJson(commonMonthlyChartDtoList);
    }

    private JSONObject loadTopPortLanesJsonData(PortLanesConstant portLanesConstant, DashboardsFilterCriteria filter) throws Exception {
        JSONObject resultJson = null;
        switch (portLanesConstant){
            case PORT_LANES:
                resultJson = loadTopPortLanesJsonData(filter);
                break;
            case PORT_LANES_BY_CARRIER:
                resultJson = loadPortLanesByCarrierJson(filter);
                break;
            case PORT_LANES_BY_MONTH:
                resultJson = loadPortLanesByMonthJson(filter);
                break;
            default:
                throw new MethodNotFoundException("Method param value not matched");
        }
        return resultJson;
    }

    public JSONObject loadTopPortLanesJsonData(DashboardsFilterCriteria filterCriteria) throws Exception {
        List<PortLanesDto> portLanesDtoList = dashboardsService.loadTopPortLanes(filterCriteria);
        return JSONUtil.prepareTopPortLanesJson(portLanesDtoList);
    }

    public JSONObject loadPortLanesByCarrierJson(DashboardsFilterCriteria filterCriteria) throws Exception {
        List<PortLanesDto> portLanesDtoList = dashboardsService.getPortLanesByCarrier(filterCriteria);
        List<CommonValuesForChartDto> commonValuesForChartDtoList = new ArrayList<CommonValuesForChartDto>();
        for(PortLanesDto portLanesDto : portLanesDtoList ) {
            CommonValuesForChartDto commonValuesForChartDto = new CommonValuesForChartDto();
            commonValuesForChartDto.setId(portLanesDto.getCarrierId());
            commonValuesForChartDto.setName(portLanesDto.getCarrierName());
            commonValuesForChartDto.setValue(portLanesDto.getSpend());
            commonValuesForChartDtoList.add(commonValuesForChartDto);
        }
        return  JSONUtil.prepareCommonJsonForChart(commonValuesForChartDtoList);
    }

    public JSONObject loadPortLanesByMonthJson(DashboardsFilterCriteria filterCriteria) throws Exception {
        List<PortLanesDto> portLanesDtoList = dashboardsService.getPortLanesByMonth(filterCriteria);
        List<CommonMonthlyChartDto> commonMonthlyChartDtoList= new ArrayList<CommonMonthlyChartDto>();
        for (PortLanesDto portLanesDto: portLanesDtoList ) {
            CommonMonthlyChartDto commonMonthlyChartDto= new CommonMonthlyChartDto();
            commonMonthlyChartDto.setBillDate(portLanesDto.getBillDate());
            commonMonthlyChartDto.setAmount(portLanesDto.getAmount());

            commonMonthlyChartDtoList.add(commonMonthlyChartDto);
        }
        return  JSONUtil.prepareMonthlyChartJson(commonMonthlyChartDtoList);
    }


    private JSONObject loadShipmentOverviewJsonData(ShipmentOverviewConstant shipmentOverviewType, DashboardsFilterCriteria filter) throws Exception {
        JSONObject avgShipmentJson = null;

        switch (shipmentOverviewType){
            case AVG_SPEND_PER_SHIPMT:
                avgShipmentJson = loadAvgSpendPerShipmtJson(filter);
                break;
            case AVG_WEIGHT_BY_MODE_SHIPMT:
                avgShipmentJson = loadAvgWeightByModeShipmtJson(filter);
                break;
            case AVG_SPEND_PER_SHIPMT_BY_CARRIER:
                avgShipmentJson = loadAvgSpendPerShipmtByCarrierJson(filter);
                break;
            case AVG_SPEND_PER_SHIPMT_BY_MONTH:
                avgShipmentJson = loadAvgSpendPerShipmtByMonthJson(filter);
                break;
            case AVG_WEIGHT_SHIPMT_BY_CARRIER:
                avgShipmentJson = loadAvgWeightModeByCarrierJson(filter);
                break;
            case AVG_WEIGHT_SHIPMT_BY_MONTH:
                avgShipmentJson = loadAvgWeightModeByMonthJson(filter);
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

    private JSONObject loadAvgSpendPerShipmtByCarrierJson(DashboardsFilterCriteria filter) throws Exception {
        JSONObject avgShipmentJson = null;

        List<AverageSpendPerShipmentDto> avgShipmentList = dashboardsService.getAvgSpendPerShipmtByCarrier(filter,false);
        if(avgShipmentList != null && avgShipmentList.size() > 0){
            List<CommonValuesForChartDto> commonValueList = new ArrayList<CommonValuesForChartDto>();
            for(AverageSpendPerShipmentDto avgShipmtByCarrier : avgShipmentList){
                if(avgShipmtByCarrier != null){
                    CommonValuesForChartDto commonValueChartDto = new CommonValuesForChartDto();
                    commonValueChartDto.setId(avgShipmtByCarrier.getId());
                    commonValueChartDto.setName(avgShipmtByCarrier.getName());
                    commonValueChartDto.setValue(avgShipmtByCarrier.getValue());
                    commonValueList.add(commonValueChartDto);
                }
            }
            avgShipmentJson = JSONUtil.prepareCommonJsonForChart(commonValueList);
        }
        return avgShipmentJson;
    }

    private JSONObject loadAvgSpendPerShipmtByMonthJson(DashboardsFilterCriteria filter) throws JSONException {
        JSONObject avgShipmentJson = null;

        List<AverageSpendPerShipmentDto> avgShipmentList = dashboardsService.getAvgSpendPerShipmtByMonth(filter,false);
        if(avgShipmentList!=null) {
            List<CommonMonthlyChartDto> commonMonthlyChartDtoList = new ArrayList<CommonMonthlyChartDto>();
            for (AverageSpendPerShipmentDto avgShipmtByMonthDto : avgShipmentList) {
                if (avgShipmtByMonthDto != null) {
                    for (AverageSpendPerShipmentDto averageSpendPerShipment: avgShipmentList ) {
                        CommonMonthlyChartDto commonMonthlyChartDto= new CommonMonthlyChartDto();
                        commonMonthlyChartDto.setBillDate(averageSpendPerShipment.getBillDate());
                        commonMonthlyChartDto.setAmount(averageSpendPerShipment.getAmount());
                        commonMonthlyChartDtoList.add(commonMonthlyChartDto);
                    }
                }
            }
            avgShipmentJson = JSONUtil.prepareMonthlyChartJson(commonMonthlyChartDtoList);
        }
        return avgShipmentJson;
    }

    private JSONObject loadAvgWeightModeByCarrierJson(DashboardsFilterCriteria filter) throws Exception {
        JSONObject avgWeightJson = null;

        List<AverageWeightModeShipmtDto> avgWeightList = dashboardsService.getAverageWeightModeByCarrier(filter,false);
        if(avgWeightList != null && avgWeightList.size() > 0){
            List<CommonValuesForChartDto> commonValueList = new ArrayList<CommonValuesForChartDto>();
            for(AverageWeightModeShipmtDto avgWeight : avgWeightList){
                if(avgWeight != null){
                    CommonValuesForChartDto commonValueChartDto = new CommonValuesForChartDto();
                    commonValueChartDto.setId(avgWeight.getId());
                    commonValueChartDto.setName(avgWeight.getName());
                    commonValueChartDto.setValue(avgWeight.getValue());
                    commonValueList.add(commonValueChartDto);
                }
            }
            avgWeightJson = JSONUtil.prepareCommonJsonForChart(commonValueList);
        }
        return avgWeightJson;
    }

    private JSONObject loadAvgWeightModeByMonthJson(DashboardsFilterCriteria filter) throws Exception {
        JSONObject avgWeightJson = null;
        List<AverageWeightModeShipmtDto> avgWeightList = dashboardsService.getAverageWeightModeByMonth(filter,false);
        if(avgWeightList != null && avgWeightList.size() > 0){
            List<CommonMonthlyChartDto> commonMonthlyChartDtoList = new ArrayList<CommonMonthlyChartDto>();
            for (AverageWeightModeShipmtDto avgWeight : avgWeightList) {
                if (avgWeight != null) {
                    CommonMonthlyChartDto commonMonthlyChartDto = new CommonMonthlyChartDto();
                    commonMonthlyChartDto.setAmount(avgWeight.getAmount());
                    commonMonthlyChartDto.setBillDate(avgWeight.getBillDate());
                    commonMonthlyChartDtoList.add(commonMonthlyChartDto);
                }
            }
            avgWeightJson = JSONUtil.prepareMonthlyChartJson(commonMonthlyChartDtoList);
        }
        return avgWeightJson;
    }

    @RequestMapping(value = "/dashboardReport", method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.OPTIONS}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<PaginationBean> getDashboardReport(@RequestParam(required = false) String invoiceDate, @RequestParam(required = false) String dashletteName, @RequestParam(required = false) String carrierId,
                                                             @RequestParam(required = false) String mode, @RequestParam(required = false) String carscoretype, @RequestParam(required = false) String service,
                                                             @RequestParam(required = false, defaultValue = "0") Integer offset, @RequestParam(required = false, defaultValue = "20") Integer limit,
                                                             @RequestParam(required = false) String filter) throws Exception {
        PaginationBean reportPaginationData = new PaginationBean();
        UserProfileDto user = getUserProfile();
        DashboardsFilterCriteria appliedFilter = loadAppliedFilters(user.getUserId());
        if(appliedFilter != null){
            if(invoiceDate != null && !invoiceDate.isEmpty()){
                DashboardUtil.setDatesFromMonth(appliedFilter, invoiceDate);
            }
            appliedFilter.setDashletteName(dashletteName);
            if(carrierId != null && !carrierId.isEmpty()){
                appliedFilter.setCarriers(carrierId);
            }
            appliedFilter.setModeNames(mode);
            appliedFilter.setScoreType(carscoretype);
            appliedFilter.setService(service);
            appliedFilter.setOffset(offset);
            appliedFilter.setPageSize(limit);
        }
        if(filter != null && !filter.isEmpty()){
            reportPaginationData = dashboardsService.getDashboardReportPaginationData(appliedFilter, offset, limit, DashboardUtil.prepareSearchFilterCriteria(filter));
        }else{
            reportPaginationData = dashboardsService.getDashboardReportPaginationData(appliedFilter, offset, limit);
        }
        return ResponseEntity.status(HttpStatus.OK).body(reportPaginationData);
    }



    /**
     *
     * @return
     */
    @RequestMapping(value = "/colConfigByUser", method = {RequestMethod.GET, RequestMethod.OPTIONS}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<String> getColumnConfigByUser() throws JSONException {
        UserProfileDto user = getUserProfile();
        DashboardsFilterCriteria filter = loadAppliedFilters(user.getUserId());
        JSONObject reportColumnNames = dashboardsService.getDashboardReportCustomColumnNames(filter);
        return ResponseEntity.status(HttpStatus.OK).body(reportColumnNames != null ? reportColumnNames.toString() : new JSONObject().toString());
    }

    @RequestMapping(value = "/actualVsBillWeight", method = {RequestMethod.GET, RequestMethod.OPTIONS}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<String> getActualVsBilledWeight() throws JSONException {
        UserProfileDto user = getUserProfile();
        DashboardsFilterCriteria filter = loadAppliedFilters(user.getUserId());
        JSONObject weightJson = loadActualVsBilledWeightJsonData(ActualVsBilledWeightConstants.ACTUAL_VS_BILLED_WEIGHT, filter);
        return ResponseEntity.status(HttpStatus.OK).body(weightJson != null ? weightJson.toString() : new JSONObject().toString());
    }

    @RequestMapping(value = "/actualVsBillWeightByCarr", method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.OPTIONS}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<String> getActualVsBilledWeightByCarrier(@RequestParam String invoiceDate) throws Exception {
        UserProfileDto user = getUserProfile();
        DashboardsFilterCriteria filter = loadAppliedFilters(user.getUserId());
        if(filter != null){
            if(invoiceDate != null && !invoiceDate.isEmpty()){
                DashboardUtil.setDatesFromMonth(filter, invoiceDate);
            }
        }
        JSONObject weightJson = loadActualVsBilledWeightJsonData(ActualVsBilledWeightConstants.ACTUAL_VS_BILLED_WEIGHT_BY_CARRIER, filter);
        return ResponseEntity.status(HttpStatus.OK).body(weightJson != null ? weightJson.toString() : new JSONObject().toString());
    }

    @RequestMapping(value = "/actualVsBillWeightByMnth", method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.OPTIONS}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<String> getActualVsBilledWeightByMonth(@RequestParam String carrierIds, @RequestParam String invoiceDate) throws Exception {
        UserProfileDto user = getUserProfile();
        DashboardsFilterCriteria filter = loadAppliedFilters(user.getUserId());
        if(filter != null){
            if(carrierIds != null && !carrierIds.isEmpty()){
                filter.setCarriers(carrierIds);
            }
            if(invoiceDate != null && !invoiceDate.isEmpty()){
                DashboardUtil.setDatesFromMonth(filter, invoiceDate);
            }
        }
        JSONObject weightJson = loadActualVsBilledWeightJsonData(ActualVsBilledWeightConstants.ACTUAL_VS_BILLED_WEIGHT_BY_MONTH, filter);
        return ResponseEntity.status(HttpStatus.OK).body(weightJson != null ? weightJson.toString() : new JSONObject().toString());
    }

    private JSONObject loadActualVsBilledWeightJsonData(ActualVsBilledWeightConstants weightType, DashboardsFilterCriteria filter) throws JSONException {
        JSONObject weightJson = null;
        switch (weightType){
            case ACTUAL_VS_BILLED_WEIGHT:
                weightJson = loadActualVsBilledWeightJson(filter);
                break;
            case ACTUAL_VS_BILLED_WEIGHT_BY_CARRIER:
                weightJson = loadActualVsBilledWeightByCarrierJson(filter);
                break;
            case ACTUAL_VS_BILLED_WEIGHT_BY_MONTH:
                weightJson = loadActualVsBilledWeightByMonthJson(filter);
                break;
            default:
                throw new MethodNotFoundException("Method param value not matched");
        }
        return weightJson;
    }

    private JSONObject loadActualVsBilledWeightByMonthJson(DashboardsFilterCriteria filter) throws JSONException {
        List<ActualVsBilledWeightDto> weightList = dashboardsService.getActualVsBilledWeightByMonth(filter, false);
        return JSONUtil.prepareActualVsBillWeightMonthlyChartJson(weightList);
    }

    private JSONObject loadActualVsBilledWeightByCarrierJson(DashboardsFilterCriteria filter) throws JSONException {
        List<ActualVsBilledWeightDto> weightList = dashboardsService.getActualVsBilledWeightByCarrier(filter, false);
        return JSONUtil.prepareActualVsBillWeightByCarrierJson(weightList);
    }

    private JSONObject loadActualVsBilledWeightJson(DashboardsFilterCriteria filter) throws JSONException {
        List<ActualVsBilledWeightDto> weightList = dashboardsService.getActualVsBilledWeight(filter, false);
        return JSONUtil.prepareActualVsBilledWeightJson(weightList);
    }

    @RequestMapping(value = "/filterDetailsById", method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.OPTIONS}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Map<String, Object>> getUserFilterDetails(Long filterId, boolean isParcelDashlettes) throws JSONException {
        Map<String, Object> userFilterData = dashboardsService.getUserFilterDetails(filterId, isParcelDashlettes);
        return ResponseEntity.status(HttpStatus.OK).body(userFilterData);
    }

    @RequestMapping(value = "/servicesByModes", method = {RequestMethod.GET, RequestMethod.OPTIONS}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Map<String, Object>>  getServicesByGroupCode(@RequestParam String customerId, @RequestParam String carrierIds, @RequestParam String modes, @RequestParam String dateType) throws JSONException {
        Map<String, Object> userFilterData = new HashMap();
        UserProfileDto user = getUserProfile();

        DashboardsFilterCriteria filter = new DashboardsFilterCriteria();
        filter.setCustomerIdsCSV(customerId);
        filter.setCarriers(carrierIds);
        filter.setDateType(dateType);
        filter.setModes(modes);
        List<UserFilterUtilityDataDto> serviceList = dashboardsService.getFilterServices(filter, user.isParcelDashlettes());
        if(serviceList != null && !serviceList.isEmpty()){
            userFilterData.put("serviceLevelsListData", JSONUtil.prepareFilterServiceJson(serviceList));
        }
        return ResponseEntity.status(HttpStatus.OK).body(userFilterData);
    }

    @RequestMapping(value = "/carrsByCustomer", method = {RequestMethod.GET, RequestMethod.OPTIONS}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Map<String, Object>>  getCarriersByCustomer(@RequestParam String customerIds, @RequestParam boolean isParcelDashlettes) throws JSONException {
        Map<String, Object> userFilterData = new HashMap();
        List<UserFilterUtilityDataDto> carrList = dashboardsService.getCarrierByCustomer(customerIds, isParcelDashlettes);
        if(carrList != null && !carrList.isEmpty()){
            userFilterData.put("carriers", JSONUtil.prepareFilterCarrierJson(carrList));
        }
        return ResponseEntity.status(HttpStatus.OK).body(userFilterData);
    }

    @RequestMapping(value = "/modesByCarr", method = {RequestMethod.GET, RequestMethod.OPTIONS}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Map<String, Object>>  getCarriersByCustomer(@RequestParam String customerId, @RequestParam String carrierIds, @RequestParam String dateType) throws JSONException {
        Map<String, Object> userFilterData = new HashMap();
        UserProfileDto user = getUserProfile();

        DashboardsFilterCriteria filter = new DashboardsFilterCriteria();
        filter.setCustomerIdsCSV(customerId);
        filter.setCarriers(carrierIds);
        filter.setDateType(dateType);
        List<UserFilterUtilityDataDto> modesList = dashboardsService.getFilterModes(filter, user.isParcelDashlettes());
        if(modesList != null && !modesList.isEmpty()){
            userFilterData.put("modesListData", JSONUtil.prepareFilterModesJson(modesList, dashboardsService.getModeWiseCarrier(carrierIds), user.isParcelDashlettes()));
        }
        return ResponseEntity.status(HttpStatus.OK).body(userFilterData);
    }

    @RequestMapping(value = "/requiredFilter", method = {RequestMethod.GET, RequestMethod.OPTIONS}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Map<String, Object>>  getCarriersByCustomer() throws JSONException {
        Map<String, Object> userFilterData = new HashMap();
        UserProfileDto user = getUserProfile();
        List<DashSavedFilterDto> userSavedFilters = dashboardsService.getSavedFiltersByUser(user.getUserId());
        List<ReportCustomerCarrierDto> customers = dashboardsService.getDashboardCustomers(user.getUserId());
        userFilterData.put("savedFilterNames", userSavedFilters);
        userFilterData.put("currenciesList", JSONUtil.prepareCurrenciesJson(dashboardsService.getCodeValuesByCodeGroup(468L)));
        ReportCustomerCarrierDto customerHierarchy = null;

        if(customers != null && !customers.isEmpty()){
            customerHierarchy = reportsService.getCustomerHierarchyObject(customers, false);
        }
        userFilterData.put("customers", customerHierarchy != null ? JSONUtil.customerHierarchyJson(customerHierarchy) : new JSONArray());

        if(userSavedFilters != null && !userSavedFilters.isEmpty()){
            DashSavedFilterDto defaultFilter = DashboardUtil.findDefaultUserFilter(userSavedFilters);
            if(null == defaultFilter){
                defaultFilter = userSavedFilters.get(0);
            }
            if(defaultFilter != null){
                if(DashboardUtil.isContainsCustomer(defaultFilter.getCustomerIds(), customers)){
                    userFilterData.putAll(dashboardsService.getUserFilterDetails(defaultFilter, user.isParcelDashlettes(), DashboardUtil.prepareDashboardFilterCriteria(defaultFilter)));
                }else{
                    userFilterData.putAll(loadFilterBasedOnCustomer(customers, customerHierarchy, user));
                }
            }
        }else{
            userFilterData.putAll(loadFilterBasedOnCustomer(customers, customerHierarchy, user));
        }
        if(userFilterData.get("filterDetails") != null){
            dashboardsService.saveAppliedFilterDetails(DashboardUtil.prepareAppliedFilter((DashSavedFilterDto) userFilterData.get("filterDetails"), user.getUserName(), user.getUserId()));
        }
        userFilterData.put("userColumnConfig", dashboardsService.getDashboardReportCustomColumnNames(loadAppliedFilters(user.getUserId())));
        return ResponseEntity.status(HttpStatus.OK).body(userFilterData);
    }

    private Map<String, Object> loadFilterBasedOnCustomer(List<ReportCustomerCarrierDto> customers, ReportCustomerCarrierDto customerHierarchy, UserProfileDto user) throws JSONException {
        String customerId = "";
        Map<String, Object> userFilterData = new HashMap();
        if(customers != null && !customers.isEmpty()){
            if(user.getDefaultCustomer() != null && !user.getDefaultCustomer().isEmpty()){
                if(user.getDefaultCustomer().startsWith("CU")){
                    customerId = user.getDefaultCustomer().replace("CU", "");
                }else{
                    customerId = user.getDefaultCustomer().trim();
                }
                if(!DashboardUtil.isContainsCustomer(customerId, customers)){
                    customerId = customerHierarchy.getCollection().iterator().next().getValue().trim();
                }
            }else{
                if(customerHierarchy != null && customerHierarchy.getCollection() != null){
                    customerId = customerHierarchy.getCollection().iterator().next().getValue().trim();
                }
            }

            if (customerId != null && !customerId.isEmpty()){
                DashboardsFilterCriteria filter = new DashboardsFilterCriteria();
                filter.setDateType("INVOICE_DATE");
                filter.setFromDate(DateUtil.format(DateUtil.subtractDays(new Date(), 90), "dd-MMM-yyyy"));
                filter.setToDate(DateUtil.format(new Date(), "dd-MMM-yyyy"));
                filter.setCustomerIdsCSV(customerId);

                DashSavedFilterDto dashSavedFilter = new DashSavedFilterDto();
                dashSavedFilter.setFilterId(0L);
                dashSavedFilter.setFilterName("Default");
                dashSavedFilter.setCurrencyId(0L);
                dashSavedFilter.setDateType(filter.getDateType());
                dashSavedFilter.setFromDate(filter.getFromDate());
                dashSavedFilter.setToDate(filter.getToDate());
                dashSavedFilter.setCustomerIds(customerId);

                userFilterData.putAll(dashboardsService.getNewUserFilterDetails(dashSavedFilter, user.isParcelDashlettes(), filter));
            }
        }
        return userFilterData;
    }

    @RequestMapping(value = "/shipmentCountByZone", method = {RequestMethod.GET, RequestMethod.OPTIONS}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<String> getShipmentCountByZone() throws JSONException {
        JSONObject shipmentCountJson = null;
        UserProfileDto user = getUserProfile();
        DashboardsFilterCriteria filter = loadAppliedFilters(user.getUserId());
        List<ShipmentDto> shipmentData = dashboardsService.getShipmentCountByZone(filter);
        if(shipmentData != null && !shipmentData.isEmpty()){
            Set<String> addresses = new HashSet<String>();
            for(ShipmentDto shipmentDto : shipmentData){
                if(shipmentDto != null){
                    String shipperCity = shipmentDto.getShipperCity() != null ? shipmentDto.getShipperCity() : "";
                    String shipperState = shipmentDto.getShipperState() != null ? shipmentDto.getShipperState() : "";
                    String shipperCountry = shipmentDto.getShipperCountry() != null ? shipmentDto.getShipperCountry() : "";
                    addresses.add(shipperCity + "," + shipperState + "," + shipperCountry);
                }
            }
            Set<MapCoordinatesDto> mapCoordinates = dashboardsService.getMapCoordinates(addresses);
            shipmentCountJson = JSONUtil.prepareShipmentCountByZoneJson(shipmentData, mapCoordinates);
        }
        shipmentCountJson = shipmentCountJson != null ? shipmentCountJson : new JSONObject();
        return ResponseEntity.status(HttpStatus.OK).body(shipmentCountJson != null ? shipmentCountJson.toString() : new JSONObject().toString());
    }

    @RequestMapping(value = "/dashboardCustomers", method = {RequestMethod.GET, RequestMethod.OPTIONS}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<String> getDashboardCustomers() throws JSONException {
        JSONObject customerJson = new JSONObject();
        UserProfileDto user = getUserProfile();
        reportsService.getCustomerHierarchyObject(dashboardsService.getDashboardCustomers(user.getUserId()), false);
        customerJson.put("customers", JSONUtil.customerHierarchyJson(reportsService.getCustomerHierarchyObject(dashboardsService.getDashboardCustomers(user.getUserId()), false)));
        return ResponseEntity.status(HttpStatus.OK).body(customerJson.toString());
    }

    @RequestMapping(value = "/applyFilter", method = {RequestMethod.POST}, produces = {MediaType.APPLICATION_JSON_VALUE}, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> saveFilterData(@RequestBody DashboardAppliedFilterDto filter, HttpSession session) throws JSONException {
        JSONObject respJson = new JSONObject();
        UserProfileDto user = getUserProfile();
        String customerId = filter.getCustomerIds();
        if(customerId.startsWith("CU")){
            filter.setCustomerIds(customerId.substring(2));
        }
        filter.setLoginUserId(user.getUserId());
        dashboardsService.saveAppliedFilterDetails(filter);
        respJson.put("status", HttpStatus.OK.value());
        respJson.put("message", "Saved Successfully");
        return ResponseEntity.status(HttpStatus.OK).body(respJson.toString());
    }

    @RequestMapping(value = "/saveDashColumnsConfig", method = {RequestMethod.GET, RequestMethod.POST}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<String> saveUserDefinedColumnConfig(@RequestBody String columnNames, @RequestParam boolean isLineItemReport) throws JSONException {
        JSONObject respJson = new JSONObject();
        UserProfileDto user = getUserProfile();
        dashboardsService.saveUserDefinedColumnConfig(user.getUserId(), columnNames, isLineItemReport);
        DashboardsFilterCriteria filter = loadAppliedFilters(user.getUserId());
        respJson.put("data", dashboardsService.getDashboardReportCustomColumnNames(filter));
        respJson.put("status", HttpStatus.OK.value());
        respJson.put("message", "Saved Successfully");
        return ResponseEntity.status(HttpStatus.OK).body(respJson.toString());
    }

    @RequestMapping(value = "/deleteFilter", method = {RequestMethod.GET, RequestMethod.OPTIONS}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Map<String, Object>> deleteSavedFilter(@RequestParam long filterId){
        Map<String, Object> respMap = new HashMap<String, Object>();
        UserProfileDto user = getUserProfile();

        dashboardsService.deleteSavedFilter(filterId);
        respMap.put("status", HttpStatus.OK.value());
        respMap.put("message", "Deleted Successfully");
        respMap.put("savedFilterNames", dashboardsService.getSavedFiltersByUser(user.getUserId()));
        return ResponseEntity.status(HttpStatus.OK).body(respMap);
    }

    @RequestMapping(value = "/saveFilter", method = {RequestMethod.POST}, produces = {MediaType.APPLICATION_JSON_VALUE}, consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Map<String, Object>> updateSavedFilterDetails(@RequestBody DashSavedFilterDto savedFilter) throws JSONException {
        Map<String, Object> respMap = new HashMap<String, Object>();
        UserProfileDto user = getUserProfile();
        boolean validationError = false;
        JSONObject validationMsg = new JSONObject();
        if(savedFilter.getFilterName() == null || savedFilter.getFilterName().isEmpty()){
            validationError = true;
            validationMsg.put("filterName", "Filter name should not be empty.");
        }
        if(savedFilter.getFilterId() == null || savedFilter.getFilterId() == 0L){
            if(savedFilter.getFilterName() != null || !savedFilter.getFilterName().isEmpty()){
                List<DashSavedFilterDto> filters = dashboardsService.getUserFilterByName(user.getUserId(), savedFilter.getFilterName());
                if(filters != null && !filters.isEmpty()){
                    validationError = true;
                    validationMsg.put("filterName", "Filter with name '" + savedFilter.getFilterName() + "' already exists.");
                }
            }
        }else{
            List<DashSavedFilterDto> filters = dashboardsService.getSavedFiltersByUser(user.getUserId());
            for(DashSavedFilterDto filter : filters){
                if(filter != null && filter.getFilterId() != null && !filter.getFilterId().equals(savedFilter.getFilterId()) && filter.getFilterName() != null && savedFilter.getFilterName() != null
                        && filter.getFilterName().trim().equals(savedFilter.getFilterName().trim())){
                    validationError = true;
                    validationMsg.put("filterName", "Filter with name '" + savedFilter.getFilterName() + "' already exists.");
                }
            }
        }
        if(validationError){
            respMap.put("status", HttpStatus.EXPECTATION_FAILED.value());
            respMap.put("validationError", validationMsg);
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(respMap);
        }
        savedFilter.setUserId(user.getUserId());
        savedFilter.setCreateDate(new Date());
        DashSavedFilterDto updatedFilter = dashboardsService.updateSavedFilter(savedFilter);
        respMap.put("status", HttpStatus.OK.value());
        respMap.put("message", "Updated Successfully");
        respMap.put("savedFilterNames", dashboardsService.getSavedFiltersByUser(user.getUserId()));
        return ResponseEntity.status(HttpStatus.OK).body(respMap);
    }

    @RequestMapping(value = "/setDefaultFilter", method = {RequestMethod.GET}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Map<String, Object>> makeDefaultFilter(@RequestParam long filterId){
        Map<String, Object> respMap = new HashMap<String, Object>();
        UserProfileDto user = getUserProfile();
        dashboardsService.makeDefaultSavedFilter(filterId, user.getUserId());
        respMap.put("status", HttpStatus.OK.value());
        respMap.put("message", "Updated Successfully");
        respMap.put("savedFilterNames", dashboardsService.getSavedFiltersByUser(user.getUserId()));
        return ResponseEntity.status(HttpStatus.OK).body(respMap);
    }

    @RequestMapping(value = "/savedFilters", method = {RequestMethod.GET}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Map<String, Object>> getDashSavedFilters(){
        Map<String, Object> respMap = new HashMap<String, Object>();
        UserProfileDto user = getUserProfile();
        respMap.put("status", HttpStatus.OK.value());
        respMap.put("savedFilterNames", dashboardsService.getSavedFiltersByUser(user.getUserId()));
        return ResponseEntity.status(HttpStatus.OK).body(respMap);
    }

    @RequestMapping(value = "/pkgDistrCount", method = {RequestMethod.GET}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Map<String, Object>> getPackageDistributionCount() throws JSONException {
        Map<String, Object> respMap = new HashMap<String, Object>();
        JSONObject pkgDistrJson = new JSONObject();
        UserProfileDto user = getUserProfile();
        DashboardsFilterCriteria filter = loadAppliedFilters(user.getUserId());
        List<ShipmentDto> pkgDistrData = dashboardsService.getPackageDistributionCount(filter);
        if(pkgDistrData != null && !pkgDistrData.isEmpty()){
            Set<String> addresses = new HashSet<String>();
            for(ShipmentDto pkgDistr : pkgDistrData){
                if(pkgDistr != null){
                    String receiverCity = pkgDistr.getReceiverCity() != null ? pkgDistr.getReceiverCity() : "";
                    String receiverState = pkgDistr.getReceiverState() != null ? pkgDistr.getReceiverState() : "";
                    String receiverCountry = pkgDistr.getReceiverCountry() != null ? pkgDistr.getReceiverCountry() : "";
                    addresses.add(receiverCity + "," + receiverState + "," + receiverCountry);
                }
            }
            Set<MapCoordinatesDto> mapCoordinates = dashboardsService.getMapCoordinates(addresses);
            pkgDistrJson = JSONUtil.preparePackageDistributionCountJson(pkgDistrData, mapCoordinates);
        }
        respMap.put("status", HttpStatus.OK.value());
        respMap.put("packageDistributionData", pkgDistrJson);
        return ResponseEntity.status(HttpStatus.OK).body(respMap);
    }
}