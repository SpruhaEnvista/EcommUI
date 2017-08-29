package com.envista.msi.api.service;

import com.envista.msi.api.dao.DashboardsDao;
import com.envista.msi.api.dao.type.GenericObject;
import com.envista.msi.api.domain.util.DashboardUtil;
import com.envista.msi.api.geocode.AddressConverter;
import com.envista.msi.api.geocode.GoogleResponse;
import com.envista.msi.api.geocode.Result;
import com.envista.msi.api.web.rest.dto.MapCoordinatesDto;
import com.envista.msi.api.web.rest.dto.ZipCodesTimeZonesDto;
import com.envista.msi.api.web.rest.dto.dashboard.CodeValueDto;
import com.envista.msi.api.web.rest.dto.dashboard.DashboardAppliedFilterDto;
import com.envista.msi.api.web.rest.dto.dashboard.DashboardsFilterCriteria;
import com.envista.msi.api.web.rest.dto.dashboard.annualsummary.AccountSummaryDto;
import com.envista.msi.api.web.rest.dto.dashboard.annualsummary.AnnualSummaryDto;
import com.envista.msi.api.web.rest.dto.dashboard.annualsummary.MonthlySpendByModeDto;
import com.envista.msi.api.web.rest.dto.dashboard.auditactivity.*;
import com.envista.msi.api.web.rest.dto.dashboard.common.DashCustomColumnConfigDto;
import com.envista.msi.api.web.rest.dto.dashboard.filter.DashSavedFilterDto;
import com.envista.msi.api.web.rest.dto.dashboard.filter.UserFilterUtilityDataDto;
import com.envista.msi.api.web.rest.dto.dashboard.netspend.*;
import com.envista.msi.api.web.rest.dto.dashboard.networkanalysis.PortLanesDto;
import com.envista.msi.api.web.rest.dto.dashboard.networkanalysis.ShipmentDto;
import com.envista.msi.api.web.rest.dto.dashboard.networkanalysis.ShipmentRegionDto;
import com.envista.msi.api.web.rest.dto.dashboard.networkanalysis.ShippingLanesDto;
import com.envista.msi.api.web.rest.dto.dashboard.report.DashboardReportDto;
import com.envista.msi.api.web.rest.dto.dashboard.report.DashboardReportUtilityDataDto;
import com.envista.msi.api.web.rest.dto.dashboard.shipmentoverview.*;
import com.envista.msi.api.web.rest.dto.reports.ReportCustomerCarrierDto;
import com.envista.msi.api.web.rest.util.JSONUtil;
import com.envista.msi.api.web.rest.util.WebConstants;
import com.envista.msi.api.web.rest.util.pac.GlobalConstants;
import com.envista.msi.api.web.rest.util.pagination.EnspirePagination;
import com.envista.msi.api.web.rest.util.pagination.PaginationBean;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.sql.SQLException;
import java.util.*;

/**
 * Created by Sarvesh on 1/19/2017.
 */


@Service
@Transactional
public class DashboardsService {

    @Inject
    private DashboardsDao dashboardsDao;

    private final Logger log = LoggerFactory.getLogger(DashboardsService.class);

    /**
     *
     * @param userId
     * @return
     */
    @Transactional(readOnly = true)
    public DashboardAppliedFilterDto getUserAppliedFilter(long userId) {
        return  dashboardsDao.getUserAppliedFilter(userId);
    }

    /**
     *
     * @param filter
     * @param isTopTenAccessorial
     * @return
     */
    @Transactional(readOnly = true)
    public List<NetSpendByModeDto> getNetSpendByModes(DashboardsFilterCriteria filter, boolean isTopTenAccessorial) {
        return  dashboardsDao.getNetSpendByModes(filter, isTopTenAccessorial);
    }

    /**
     *
     * @param filter
     * @param isTopTenAccessorial
     * @return
     */
    @Transactional(readOnly = true)
    public List<NetSpendOverTimeDto> getNetSpendOverTimeByMonth(DashboardsFilterCriteria filter, boolean isTopTenAccessorial) {
        return  dashboardsDao.getNetSpendOverTimeByMonth(filter, isTopTenAccessorial);
    }

    /**
     *
     * @param filter
     * @param isTopTenAccessorial
     * @return
     */
    @Transactional(readOnly = true)
    public List<NetSpendOverTimeDto> getNetSpendByOverTime(DashboardsFilterCriteria filter, boolean isTopTenAccessorial) {
        return  dashboardsDao.getNetSpendByOverTime(filter, isTopTenAccessorial);
    }

    /**
     *
     * @param filter
     * @param isTopTenAccessorial
     * @return
     */
    public List<NetSpendByModeDto> getNetSpendByCarrier(DashboardsFilterCriteria filter, boolean isTopTenAccessorial){
        return dashboardsDao.getNetSpendByCarrier(filter, isTopTenAccessorial);
    }

    /**
     *
     * @param filter
     * @param isTopTenAccessorial
     * @return
     */
    public List<NetSpendByModeDto> getNetSpendByMonth(DashboardsFilterCriteria filter, boolean isTopTenAccessorial){
        return dashboardsDao.getNetSpendByMonth(filter, isTopTenAccessorial);
    }

    /**
     *
     * @param filter
     * @return
     */
    public List<TaxSpendDto> getTaxSpend(DashboardsFilterCriteria filter){
        return dashboardsDao.getTaxSpend(filter);
    }

    /**
     *
     * @param filter
     * @return
     */
    public List<TaxSpendDto> getTaxSpendByCarrier(DashboardsFilterCriteria filter){
        return dashboardsDao.getTaxSpendByCarrier(filter);
    }

    /**
     *
     * @param filter
     * @return
     */
    public List<TaxSpendDto> getTaxSpendByMonth(DashboardsFilterCriteria filter){
        return dashboardsDao.getTaxSpendByMonth(filter);
    }

    /**
     *
     * @param filter
     * @param isTopTenAccessorial
     * @return
     */
    public List<AccessorialSpendDto> getTopAccessorialSpend(DashboardsFilterCriteria filter, boolean isTopTenAccessorial){
        return dashboardsDao.getTopAccessorialSpend(filter, isTopTenAccessorial);
    }

    /**
     *
     * @param filter
     * @param isTopTenAccessorial
     * @return
     */
    public List<AccessorialSpendDto> getTopAccessorialSpendByCarrier(DashboardsFilterCriteria filter, boolean isTopTenAccessorial){
        return dashboardsDao.getTopAccessorialSpendByCarrier(filter, isTopTenAccessorial);
    }

    /**
     *
     * @param filter
     * @param isTopTenAccessorial
     * @return
     */
    public List<AccessorialSpendDto> getTopAccessorialSpendByMonth(DashboardsFilterCriteria filter, boolean isTopTenAccessorial){
        return dashboardsDao.getTopAccessorialSpendByMonth(filter, isTopTenAccessorial);
    }

    /**
     * Get top accessorial spend for all accessorial.
     * @param filter
     * @param isTopTenAccessorial
     * @return
     */
    public List<AccessorialSpendDto> getTopAccessorialSpendByAccessorial(DashboardsFilterCriteria filter, boolean isTopTenAccessorial){
        return dashboardsDao.getTopAccessorialSpendByAccessorial(filter, isTopTenAccessorial);
    }

    /**
     *
     * @param filter
     * @param isTopTenAccessorial
     * @return
     */
    public List<AccessorialSpendDto> getAccessorialSpend(DashboardsFilterCriteria filter, boolean isTopTenAccessorial){
        return dashboardsDao.getAccessorialSpend(filter, isTopTenAccessorial);
    }

    /**
     *
     * @param filter
     * @param isTopTenAccessorial
     * @return
     */
    public List<AccessorialSpendDto> getAccessorialSpendByCarrier(DashboardsFilterCriteria filter, boolean isTopTenAccessorial){
        return dashboardsDao.getTopAccessorialSpendByCarrier(filter, isTopTenAccessorial);
    }

    /**
     *
     * @param filter
     * @param isTopTenAccessorial
     * @return
     */
    public List<AccessorialSpendDto> getAccessorialSpendByMonth(DashboardsFilterCriteria filter, boolean isTopTenAccessorial){
        return dashboardsDao.getTopAccessorialSpendByMonth(filter, isTopTenAccessorial);
    }

    public List<AverageSpendPerShipmentDto> getAvgSpendPerShipment(DashboardsFilterCriteria filter , boolean isTopTenAccessorial){
        return dashboardsDao.getAvgSpendPerShipmt(filter,  isTopTenAccessorial);
    }

    public List<AverageWeightModeShipmtDto> getAverageWeightByModeShipmt(DashboardsFilterCriteria filter , boolean isTopTenAccessorial){
        return dashboardsDao.getAverageWeightByModeShipmt(filter,  isTopTenAccessorial);
    }

    /**
     * Method to get service level usage performance details.
     *
     * @param filter
     * @param isTopTenAccessorial
     * @return
     */
    public List<ServiceLevelUsageAndPerformanceDto> getServiceLevelUsageAndPerformance(DashboardsFilterCriteria filter , boolean isTopTenAccessorial){
        return dashboardsDao.getServiceLevelUsageAndPerformance(filter, isTopTenAccessorial);
    }

    /**
     * Method to get Inbound spend details.
     * @param filter
     * @param isTopTenAccessorial
     * @return
     */
    public List<InboundSpendDto> getInboundSpend(DashboardsFilterCriteria filter , boolean isTopTenAccessorial){
        return dashboardsDao.getInboundSpend(filter, isTopTenAccessorial);
    }

    /**
     * Method to get Inbound spend details by month.
     * @param filter
     * @param isTopTenAccessorial
     * @return
     */
    public List<InboundSpendDto> getInboundSpendByMonth(DashboardsFilterCriteria filter , boolean isTopTenAccessorial){
        return dashboardsDao.getInboundSpendByMonth(filter, isTopTenAccessorial);
    }

    /**
     * Method to get Outbound spend details.
     * @param filter
     * @param isTopTenAccessorial
     * @return
     */
    public List<OutboundSpendDto> getOutboundSpend(DashboardsFilterCriteria filter , boolean isTopTenAccessorial){
        return dashboardsDao.getOutboundSpend(filter, isTopTenAccessorial);
    }

    /**
     * Method to get Outbound spend details by month.
     * @param filter
     * @param isTopTenAccessorial
     * @return
     */
    public List<OutboundSpendDto> getOutboundSpendByMonth(DashboardsFilterCriteria filter , boolean isTopTenAccessorial){
        return dashboardsDao.getOutboundSpendByMonth(filter, isTopTenAccessorial);
    }
    /**
     * Mthod to get Invoice Status count.
     * @param filter
     * @return
     */
    public List<InvoiceStatusCountDto> getInvoiceStatusCount(DashboardsFilterCriteria filter){
        return dashboardsDao.getInvoiceStatusCount(filter);
    }

    /**
     * Method to get Invoice Status Count By Carrier
     * @param filter
     * @return
     */
    public List<InvoiceStatusCountDto> getInvoiceStatusCountByCarrier(DashboardsFilterCriteria filter){
        return dashboardsDao.getInvoiceStatusCountByCarrier(filter);
    }

    /**
     * Method to get Invoice Status Count By Month
     * @param filter
     * @return
     */
    public List<InvoiceStatusCountDto> getInvoiceStatusCountByMonth(DashboardsFilterCriteria filter){
        return dashboardsDao.getInvoiceStatusCountByMonth(filter);
    }

    /**
     * Method to get Invoice Status Amount
     * @param filter
     * @return
     */
    public List<InvoiceStatusAmountDto> getInvoiceStatusAmount(DashboardsFilterCriteria filter){
        return dashboardsDao.getInvoiceStatusAmount(filter);
    }

    /**
     * Method to get Invoice Status Amount By Carrier
     * @param filter
     * @return
     */
    public List<InvoiceStatusAmountDto> getInvoiceStatusAmountByCarrier(DashboardsFilterCriteria filter){
        return dashboardsDao.getInvoiceStatusAmountByCarrier(filter);
    }

    /**
     * Method to get Invoice Status Amount By Month
     * @param filter
     * @return
     */
    public List<InvoiceStatusAmountDto> getInvoiceStatusAmountByMonth(DashboardsFilterCriteria filter){
        return dashboardsDao.getInvoiceStatusAmountByMonth(filter);
    }

    /**
     * Method to get Invoice Method Score Details.
     * @param filter
     * @return
     */
    public List<InvoiceMethodScoreDto> getInvoiceMethodScore(DashboardsFilterCriteria filter){
        return dashboardsDao.getInvoiceMethodScore(filter);
    }

    /**
     * Method to get Invoice Method Score details By Carrier.
     * @param filter
     * @return
     */
    public List<InvoiceMethodScoreDto> getInvoiceMethodScoreByCarrier(DashboardsFilterCriteria filter){
        return dashboardsDao.getInvoiceMethodScoreByCarrier(filter);
    }

    /**
     * Method to get Invoice Method Score details By Month.
     * @param filter
     * @return
     */
    public List<InvoiceMethodScoreDto> getInvoiceMethodScoreByMonth(DashboardsFilterCriteria filter){
        return dashboardsDao.getInvoiceMethodScoreByMonth(filter);
    }

    /**
     * Method to get Order Match Status details.
     * @param filter
     * @return
     */
    public List<OrderMatchDto> getOrderMatchStatus(DashboardsFilterCriteria filter){
        return dashboardsDao.getOrderMatchStatus(filter);
    }

    /**
     * Method to get Order Match Status details by carrier.
     * @param filter
     * @return
     */
    public List<OrderMatchDto> getOrderMatchByCarrier(DashboardsFilterCriteria filter){
        return dashboardsDao.getOrderMatchByCarrier(filter);
    }

    /**
     * Method to get Order Match Status details by month.
     * @param filter
     * @return
     */
    public List<OrderMatchDto> getOrderMatchByMonth(DashboardsFilterCriteria filter){
        return dashboardsDao.getOrderMatchByMonth(filter);
    }

    /**
     * Method to get Billed Vs Approved Data.
     * @param filter
     * @return
     */
    public List<BilledVsApprovedDto> getBilledVsApprovedData(DashboardsFilterCriteria filter){
        return dashboardsDao.getBilledVsApprovedData(filter);
    }

    public List<BilledVsApprovedDto> getBilledVsApprovedByMonth(DashboardsFilterCriteria filter){
        return dashboardsDao.getBilledVsApprovedByMonth(filter);
    }

    public List<RecoveryAdjustmentDto> getRecoveryAdjustment(DashboardsFilterCriteria filter, boolean isTopTenAccessorial){
        return dashboardsDao.getRecoveryAdjustment(filter, isTopTenAccessorial);
    }

    public List<RecoveryAdjustmentDto> getRecoveryAdjustmentByCarrier(DashboardsFilterCriteria filter, boolean isTopTenAccessorial){
        return dashboardsDao.getRecoveryAdjustmentByCarrier(filter, isTopTenAccessorial);
    }

    public List<RecoveryAdjustmentDto> getRecoveryAdjustmentByMonth(DashboardsFilterCriteria filter, boolean isTopTenAccessorial){
        return dashboardsDao.getRecoveryAdjustmentByMonth(filter, isTopTenAccessorial);
    }

    public List<RecoveryServiceDto> getRecoveryServices(DashboardsFilterCriteria filter, boolean isTopTenAccessorial){
        return dashboardsDao.getRecoveryServices(filter, isTopTenAccessorial);
    }

    public List<RecoveryServiceDto> getRecoveryServicesByMonth(DashboardsFilterCriteria filter, boolean isTopTenAccessorial){
        return dashboardsDao.getRecoveryServicesByMonth(filter, isTopTenAccessorial);
    }

    public List<ShipmentRegionDto> getShipmentByRegion(DashboardsFilterCriteria dashboardsFilterCriteria ) {
        return dashboardsDao.getShipmentByRegion(dashboardsFilterCriteria);
    }

    public List<MapCoordinatesDto> getMapCooridantes(String address) {
        return dashboardsDao.getMapCoordinates(address);
    }

    public List<ZipCodesTimeZonesDto> getMapCooridantes(String city, String state) {
        return dashboardsDao.getMapCoordinates(city,state);
    }

    public void insertMapCoordinates(MapCoordinatesDto mapCoordinatesDto) {
        dashboardsDao.insertMapCoordinates(mapCoordinatesDto);
    }

    public List<ShipmentRegionDto> getShipmentRegionByCarrier (DashboardsFilterCriteria filterCriteria) {
        return dashboardsDao.getShipmentRegionByCarrier(filterCriteria);
    }

    public List<ShipmentRegionDto> getShipmentRegionByMonth(DashboardsFilterCriteria filterCriteria) {
        return dashboardsDao.getShipmentRegionByMonth(filterCriteria);
    }

    public List<ShippingLanesDto> loadTopShippingLanes (DashboardsFilterCriteria filterCriteria) {
        return dashboardsDao.getTopShippingLanes(filterCriteria);
    }

    public List<ShippingLanesDto> getShippingLanesByCarrier (DashboardsFilterCriteria filterCriteria) {
        return dashboardsDao.getShippingLanesByCarrier(filterCriteria);
    }

    public List<ShippingLanesDto> getShippingLanesByMonth (DashboardsFilterCriteria filterCriteria) {
        return dashboardsDao.getShippingLanesByMonth(filterCriteria);
    }

    public List<PortLanesDto> loadTopPortLanes (DashboardsFilterCriteria filterCriteria) {
        return dashboardsDao.getTopPortLanes(filterCriteria);
    }

    public List<PortLanesDto> getPortLanesByCarrier (DashboardsFilterCriteria filterCriteria) {
        return dashboardsDao.getPortLanesByCarrier(filterCriteria);
    }

    public List<PortLanesDto> getPortLanesByMonth (DashboardsFilterCriteria filterCriteria) {
        return dashboardsDao.getPortLanesByMonth(filterCriteria);
    }

    public List<PackageExceptionDto> getPackageExceptions(DashboardsFilterCriteria filter, boolean isTopTenAccessorial){
        return dashboardsDao.getPackageExceptions(filter, isTopTenAccessorial);
    }

    public List<PackageExceptionDto> getPackageExceptionsByCarrier(DashboardsFilterCriteria filter, boolean isTopTenAccessorial){
        return dashboardsDao.getPackageExceptionsByCarrier(filter, isTopTenAccessorial);
    }

    public List<PackageExceptionDto> getPackageExceptionsByMonth(DashboardsFilterCriteria filter, boolean isTopTenAccessorial){
        return dashboardsDao.getPackageExceptionsByMonth(filter, isTopTenAccessorial);
    }

    public List<AverageSpendPerShipmentDto> getAvgSpendPerShipmtByCarrier(DashboardsFilterCriteria filter , boolean isTopTenAccessorial){
        return dashboardsDao.getAverageSpendPerShipmentByCarrier(filter,  isTopTenAccessorial);
    }

    public List<AverageSpendPerShipmentDto> getAvgSpendPerShipmtByMonth(DashboardsFilterCriteria filter , boolean isTopTenAccessorial){
        return dashboardsDao.getAverageSpendPerShipmentByMonth(filter,  isTopTenAccessorial);
    }

    public List<AverageWeightModeShipmtDto> getAverageWeightModeByCarrier(DashboardsFilterCriteria filter , boolean isTopTenAccessorial){
        return dashboardsDao.getAverageWeightModeByCarrier(filter,  isTopTenAccessorial);
    }

    public List<AverageWeightModeShipmtDto> getAverageWeightModeByMonth(DashboardsFilterCriteria filter , boolean isTopTenAccessorial){
        return dashboardsDao.getAverageWeightModeByMonth(filter,  isTopTenAccessorial);
    }

    /**
     * Method to get Annual summary.
     * @param filter
     * @param isTopTenAccessorial
     * @return
     */
    public List<AnnualSummaryDto> getAnnualSummary(DashboardsFilterCriteria filter , boolean isTopTenAccessorial){
        return dashboardsDao.getAnnualSummary(filter, isTopTenAccessorial);
    }

    /**
     * Method to get Annual summary by service.
     * @param filter
     * @param isTopTenAccessorial
     * @return
     */
    public List<AnnualSummaryDto> getAnnualSummaryByService(DashboardsFilterCriteria filter , boolean isTopTenAccessorial){
        return dashboardsDao.getAnnualSummaryByService(filter, isTopTenAccessorial);
    }

    /**
     * Method to get Annual summary by carrier.
     * @param filter
     * @param isTopTenAccessorial
     * @return
     */
    public List<AnnualSummaryDto> getAnnualSummaryByCarrier(DashboardsFilterCriteria filter , boolean isTopTenAccessorial){
        return dashboardsDao.getAnnualSummaryByCarrier(filter, isTopTenAccessorial);
    }

    /**
     * Method to get Annual summary by month.
     * @param filter
     * @param isTopTenAccessorial
     * @return
     */
    public List<AnnualSummaryDto> getAnnualSummaryByMonth(DashboardsFilterCriteria filter , boolean isTopTenAccessorial){
        return dashboardsDao.getAnnualSummaryByMonth(filter, isTopTenAccessorial);
    }

    /**
     * Method to get monthly spend by mode.
     * @param filter
     * @param isTopTenAccessorial
     * @return
     */
    public List<MonthlySpendByModeDto> getMonthlySpendByMode(DashboardsFilterCriteria filter , boolean isTopTenAccessorial){
        return dashboardsDao.getMonthlySpendByMode(filter, isTopTenAccessorial);
    }

    /**
     * Method to get monthly spend by mode by service.
     * @param filter
     * @param isTopTenAccessorial
     * @return
     */
    public List<MonthlySpendByModeDto> getMonthlySpendByModeByService(DashboardsFilterCriteria filter , boolean isTopTenAccessorial){
        return dashboardsDao.getMonthlySpendByModeByService(filter, isTopTenAccessorial);
    }

    /**
     * Method to get account summary details.
     * @param filter
     * @param isTopTenAccessorial
     * @return
     */
    public List<AccountSummaryDto> getAccountSummary(DashboardsFilterCriteria filter , boolean isTopTenAccessorial){
        return dashboardsDao.getAccountSummary(filter, isTopTenAccessorial);
    }

    /**
     * Method to get parcel account summary details.
     * @param filter
     * @param isTopTenAccessorial
     * @return
     */
    public List<AccountSummaryDto> getParcelAccountSummary(DashboardsFilterCriteria filter, boolean isTopTenAccessorial){
        return dashboardsDao.getParcelAccountSummary(filter, isTopTenAccessorial);
    }

    /**
     * Get custom defined field names by customer.
     * @param filter
     * @param reportId
     * @return
     */
    public Map<String, String> getCustomDefinedLabelsByCustomer(DashboardsFilterCriteria filter, Long reportId){
        Map<String, String> customFieldsMap = null;
        List<DashboardReportUtilityDataDto> custDefLblList = dashboardsDao.getCustomDefinedLabelsByCustomer(filter, reportId);
        if(custDefLblList != null && !custDefLblList.isEmpty()){
            customFieldsMap = new HashMap<String, String>();
            for(DashboardReportUtilityDataDto custDefLbl : custDefLblList){
                if(custDefLbl != null && custDefLbl.getReportFieldName() != null){
                    customFieldsMap.put(custDefLbl.getReportFieldName().toUpperCase(), custDefLbl.getCustomFieldName());
                }
            }
        }
        return customFieldsMap;
    }

    /**
     * Get Dashboard report column names.
     * @param filter
     * @return
     */
    public Map<String, String> getReportColumnNames(DashboardsFilterCriteria filter){
        List<DashboardReportUtilityDataDto> reportColumnNames = dashboardsDao.getReportColumnNames(filter.isLineItemReport());
        Map<String, String> columnMap = null;
        if(reportColumnNames != null && !reportColumnNames.isEmpty()){
            columnMap = new LinkedHashMap<String, String>();
            for(DashboardReportUtilityDataDto columnName : reportColumnNames){
                if(columnName != null){
                    columnMap.put(columnName.getSelectClause().trim(), columnName.getColumnName().trim());
                }
            }
            columnMap.put("SERVICE_LEVEL", "Service Level");
            Set<String> selectColumns = columnMap.keySet();

            Map<String, String> customFieldsMap = getCustomDefinedLabelsByCustomer(filter, 100L);
            if(customFieldsMap != null){
                for(String selectCol : selectColumns){
                    if(customFieldsMap.containsKey(selectCol)){
                        columnMap.put(selectCol, customFieldsMap.get(selectCol));
                    }
                }
            }
        }
        return columnMap;
    }

    public PaginationBean getDashboardReportPaginationData(DashboardsFilterCriteria filter, int offset, int limit) throws Exception {
        return getDashboardReportPaginationData(filter, offset, limit, null);
    }

    public PaginationBean getDashboardReportPaginationData(DashboardsFilterCriteria filter, int offset, int limit, String searchFilter) throws Exception {
        Map<String, Object> paginationFilterMap = new HashMap<String, Object>();
        filter.setOffset(offset);
        filter.setPageSize(limit);
        paginationFilterMap.put("filter", filter);

        if(searchFilter != null && !searchFilter.isEmpty()){
            paginationFilterMap.put(WebConstants.SEARCH_FILTER_CONDITION, searchFilter);
        }
        return new EnspirePagination() {
            @Override
            protected int getTotalRowCount(Map<String, Object> paginationFilterMap) {
                return dashboardsDao.getDashboardReportTotalRecordCount(filter, paginationFilterMap);
            }

            @Override
            protected Object loadPaginationData(Map<String, Object> paginationFilterMap, int offset, int limit, String sortOrder) throws Exception {
                return loadDashboardReportJson(filter, paginationFilterMap);
            }
        }.preparePaginationData(paginationFilterMap, offset, limit);
    }

    private JSONArray loadDashboardReportJson(DashboardsFilterCriteria filter, Map<String, Object> paginationFilterMap) throws JSONException {
        JSONArray dashboardReportJson = null;
        List<DashboardReportDto> reportDataList = null;
        if(filter.isLineItemReport()){
            reportDataList = getLineItemReportDetails(filter);
        }else{
            reportDataList = getDashboardReport(filter, paginationFilterMap);
        }
        if(reportDataList != null && !reportDataList.isEmpty()){
            Map<String, String> resultColumnsMap = getReportColumnNames(filter);
            dashboardReportJson = JSONUtil.prepareDashboardReportJson(reportDataList, resultColumnsMap);
        }
        return dashboardReportJson;
    }

    /**
     * Get Dashboard report for Parcel and Freight.
     * @param filter
     * @return
     */
    public List<DashboardReportDto> getDashboardReport(DashboardsFilterCriteria filter, Map<String, Object> paginationFilterMap){
        return dashboardsDao.getDashboardReport(filter, paginationFilterMap);
    }

    /**
     * Get dashboard line item report details.
     * @param filter
     * @return
     */
    public List<DashboardReportDto> getLineItemReportDetails(DashboardsFilterCriteria filter){
        return dashboardsDao.getLineItemReportDetails(filter);
    }

    /**
     * Get custom columns mapped against the user.
     * @param userId
     * @param reportId
     * @return
     */
    public List<String> getColumnConfigByUser(Long userId, Long reportId){
        List<String> columnNames = new ArrayList<String>();
        List<DashboardReportUtilityDataDto> custColumnList = dashboardsDao.getColumnConfigByUser(userId, reportId);
        if(custColumnList != null){
            StringBuffer finalColumns = new StringBuffer();
            for(DashboardReportUtilityDataDto custCol : custColumnList){
                if(custCol != null){
                    if(custCol.getColumnsDefined1() != null){
                        finalColumns.append(custCol.getColumnsDefined1());
                    }
                    if(custCol.getColumnsDefined2() != null){
                        finalColumns.append(custCol.getColumnsDefined2());
                    }
                    if(custCol.getColumnsDefined3() != null){
                        finalColumns.append(custCol.getColumnsDefined3());
                    }
                    if(custCol.getColumnsDefined4() != null){
                        finalColumns.append(custCol.getColumnsDefined4());
                    }
                    for (String columnName : finalColumns.toString().split(",")) {
                        columnNames.add(columnName);
                    }
                }
            }
        }
        return columnNames;
    }

    /**
     * Get Actual Vs Billed weight data.
     * @param filter
     * @param isTopTenAccessorial
     * @return
     */
    public List<ActualVsBilledWeightDto> getActualVsBilledWeight(DashboardsFilterCriteria filter, boolean isTopTenAccessorial){
        return dashboardsDao.getActualVsBilledWeight(filter, isTopTenAccessorial);
    }

    /**
     * Get Actual Vs Billed weight data by carrier.
     * @param filter
     * @param isTopTenAccessorial
     * @return
     */
    public List<ActualVsBilledWeightDto> getActualVsBilledWeightByCarrier(DashboardsFilterCriteria filter, boolean isTopTenAccessorial){
        return dashboardsDao.getActualVsBilledWeightByCarrier(filter, isTopTenAccessorial);
    }

    /**
     * Get Actual Vs Billed weight data by month.
     * @param filter
     * @param isTopTenAccessorial
     * @return
     */
    public List<ActualVsBilledWeightDto> getActualVsBilledWeightByMonth(DashboardsFilterCriteria filter, boolean isTopTenAccessorial){
        return dashboardsDao.getActualVsBilledWeightByMonth(filter, isTopTenAccessorial);
    }

    public Map<String, Object> getNewUserFilterDetails(DashSavedFilterDto userFilter, boolean isParcelDashlettes, DashboardsFilterCriteria filter) throws JSONException {
        List<Long> carrList = new ArrayList<Long>();
        List<Long> modeList = new ArrayList<Long>();
        List<Long> servicesList = new ArrayList<Long>();
        List<UserFilterUtilityDataDto> carriers = getCarrierByCustomer(String.valueOf(userFilter.getCustomerIds()), isParcelDashlettes);
        StringJoiner carrierCSV = new StringJoiner(",");
        for(UserFilterUtilityDataDto car : carriers){
            if(car != null){
                carrList.add(car.getCarrierId());
                carrierCSV.add(car.getCarrierId().toString());
            }
        }
        filter.setCarriers(carrierCSV.toString());
        userFilter.setCarrierIds(carrierCSV.toString());

        List<UserFilterUtilityDataDto> modes = getFilterModes(filter, isParcelDashlettes);
        if(modes != null && !modes.isEmpty()){
            StringJoiner modeCSV = new StringJoiner(",");
            for(UserFilterUtilityDataDto mode : modes){
                if(mode != null){
                    modeCSV.add(mode.getId().toString());
                }
            }
            filter.setModes(modeCSV.toString());
            userFilter.setModes(modeCSV.toString());
        }

        List<UserFilterUtilityDataDto> services = getFilterServices(filter, isParcelDashlettes);
        if(services != null && !services.isEmpty()){
            StringJoiner servicesCSV = new StringJoiner(",");
            for(UserFilterUtilityDataDto service : services){
                if(service != null){
                    servicesList.add(service.getId());
                    servicesCSV.add(service.getId().toString());
                }
            }
            filter.setServices(servicesCSV.toString());
            userFilter.setServices(servicesCSV.toString());
        }


        return DashboardUtil.prepareFilterDetails(carriers, services,
                modes, carrList, modeList, servicesList, userFilter, getModeWiseCarrier(carrierCSV.toString()), isParcelDashlettes, true);
    }

    public Map<String, Object> getUserFilterDetails(Long filterId, boolean isParcelDashlettes) throws JSONException {
        DashSavedFilterDto userFilter = getFilterById(filterId);
        DashboardsFilterCriteria filter = DashboardUtil.prepareDashboardFilterCriteria(userFilter);
        return getUserFilterDetails(userFilter, isParcelDashlettes, filter);
    }

    public Map<String, Object> getUserFilterDetails(Long filterId, boolean isParcelDashlettes, DashboardsFilterCriteria filter) throws JSONException {
        DashSavedFilterDto userFilter = getFilterById(filterId);
        return getUserFilterDetails(userFilter, isParcelDashlettes, filter);
    }

    public Map<String, Object> getUserFilterDetails(DashSavedFilterDto userFilter, boolean isParcelDashlettes, DashboardsFilterCriteria filter) throws JSONException {
        Map<String, Object> userFilterDetailsMap = new HashMap<String, Object>();

        if(userFilter != null){
            List<Long> carrList = new ArrayList<Long>();
            List<Long> servicesList = new ArrayList<Long>();
            List<Long> modeList = new ArrayList<Long>();

            String customerIds = userFilter.getCustomerIds();
            String carrierIds = userFilter.getCarrierIds();
            String services = userFilter.getServices();
            String modes = userFilter.getModes();

            if(carrierIds != null){
                for(String carrId : carrierIds.split(",")){
                    if(carrId != null && !carrId.isEmpty()){
                        carrList.add(Long.parseLong(carrId));
                    }
                }
            }

            if(modes != null){
                for(String mode : modes.split(",")){
                    if(mode != null && !mode.isEmpty()){
                        modeList.add(Long.parseLong(mode));
                    }
                }
            }

            if(services != null){
                for(String service : services.split(",")){
                    if(service != null && !service.isEmpty()){
                        servicesList.add(Long.parseLong(service));
                    }
                }
            }

            userFilterDetailsMap = DashboardUtil.prepareFilterDetails(getCarrierByCustomer(customerIds, isParcelDashlettes), getFilterServices(filter, isParcelDashlettes),
                    getFilterModes(filter, isParcelDashlettes), carrList, modeList, servicesList, userFilter, getModeWiseCarrier(carrierIds), isParcelDashlettes, false);
        }
        return userFilterDetailsMap;
    }

    /**
     * Get user filter by filter id.
     * @param userFilterId
     * @return
     */
    public DashSavedFilterDto getFilterById(Long userFilterId){
        return dashboardsDao.getFilterById(userFilterId);
    }

    /**
     * Get filter details by user id.
     * @param userId
     * @return
     */
    public List<DashSavedFilterDto> getSavedFiltersByUser(Long userId){
        return dashboardsDao.getSavedFiltersByUser(userId);
    }

    /**
     * Get carrier by customer.
     * @param customerIds
     * @param isParcelDashlettes
     * @return
     */
    public List<UserFilterUtilityDataDto> getCarrierByCustomer(String customerIds, boolean isParcelDashlettes){
        return dashboardsDao.getCarrierByCustomer(customerIds, isParcelDashlettes);
    }

    /**
     * Get filter modes.
     * @param filter
     * @return
     */
    public List<UserFilterUtilityDataDto> getFilterModes(DashboardsFilterCriteria filter, boolean isParcelDashlettes){
        if(filter.getCarriers() == null || filter.getCarriers().isEmpty()) return null;
        return dashboardsDao.getFilterModes(filter, isParcelDashlettes);
    }

    /**
     * Get filter service details.
     * @param filter
     * @return
     */
    public List<UserFilterUtilityDataDto> getFilterServices(DashboardsFilterCriteria filter, boolean isParcelDashlettes){
        if(filter.getModes() == null || filter.getModes().isEmpty()) return null;
        return dashboardsDao.getFilterServices(filter, isParcelDashlettes);
    }

    public Map<String, String> getModeWiseCarrier(String carrierIds){
        Map<String, String> modeWiseCarrMap = new HashMap<String, String>();
        if(carrierIds != null && !carrierIds.isEmpty()){
            List<UserFilterUtilityDataDto> carrierDataList = dashboardsDao.getCarrierDetailsByIds(carrierIds);
            if(carrierDataList != null && !carrierDataList.isEmpty()){
                StringJoiner carrierCsv = new StringJoiner(",");
                for(UserFilterUtilityDataDto carrierData : carrierDataList){
                    if(carrierData != null && carrierData.getLtl() != null){
                        if(carrierData.getLtl() == 0){
                            carrierCsv.add(carrierData.getId().toString());
                        }else{
                            modeWiseCarrMap.put("freightCarrier", "freightCarrier");
                        }
                    }
                }
                if (carrierCsv.length() > 0) {
                    modeWiseCarrMap.put("parcelCarrier", carrierIds.toString());
                }
            }
        }
        return modeWiseCarrMap;
    }

    /**
     * Get code values by code group.
     * @param codeGroupId
     * @return
     */
    public List<CodeValueDto> getCodeValuesByCodeGroup(Long codeGroupId){
        return dashboardsDao.getCodeValuesByCodeGroup(codeGroupId);
    }

    /**
     * Get Map co-ordinates based on the list of address.
     * Each address is combination of city, state and country comma separated.
     * @param addresses
     * @return
     */
    public List<MapCoordinatesDto> getLocationGeoCoordinates(String[] addresses){
        return dashboardsDao.getLocationGeoCoordinates(addresses);
    }

    public Set<MapCoordinatesDto> getMapCoordinates(Set<String> addresses){
        long starttime = System.currentTimeMillis();
        Set<MapCoordinatesDto>  mapCoordinates = new HashSet<MapCoordinatesDto>(getLocationGeoCoordinates(addresses.toArray(new String[addresses.size()])));

        return mapCoordinates;
    }

    /**
     * Get zone wise shipment count.
     * @param filter
     * @return
     */
    public List<ShipmentDto> getShipmentCountByZone(DashboardsFilterCriteria filter){
        return dashboardsDao.getShipmentCountByZone(filter);
    }

    /**
     * Get Dashboard customer details.
     * @param userId
     * @return
     */
    public List<ReportCustomerCarrierDto> getDashboardCustomers(long userId){
        return dashboardsDao.getDashboardCustomers(userId);
    }

    /**
     * Save applied filter details.
     * @param appliedFilter
     */
    public void saveAppliedFilterDetails(DashboardAppliedFilterDto appliedFilter){
        dashboardsDao.saveAppliedFilterDetails(appliedFilter);
    }

    /**
     * Get custom column details.
     * @param filter
     * @return
     * @throws JSONException
     */
    public JSONObject getDashboardReportCustomColumnNames(DashboardsFilterCriteria filter) throws JSONException {
        JSONObject colJson = new JSONObject();
        colJson.put("shipmentColumns", getCustomColumnDetails(filter, GlobalConstants.DASHBOARDS_SHIPMENT_DETAIL_INCLUDED_COLS, 100L));
        colJson.put("lineItemColumns", getCustomColumnDetails(filter, GlobalConstants.DASHBOARDS_LINE_ITEM_INCLUDED_COLS, 197L));
        return colJson;
    }

    /**
     * Get custom column details.
     * @param filter
     * @param originalColumnNames
     * @param reportId
     * @return
     * @throws JSONException
     */
    public JSONArray getCustomColumnDetails(DashboardsFilterCriteria filter, String originalColumnNames, long reportId) throws JSONException {
        Map<String, String> customDefinedColsByCustomer = null;
        if (filter != null) {
            customDefinedColsByCustomer = getCustomDefinedLabelsByCustomer(filter, reportId);
        }

        Set<String> reqColList = new TreeSet<String>();
        for(String colName : originalColumnNames.split(",")){
            reqColList.add(colName);
        }

        Map<String, String> allColumnNames = getReportColumnNames(filter);
        Map<String, String> reqColumnNames = new TreeMap<String, String>();
        if(allColumnNames != null){
            for(Map.Entry<String, String> colEntry : allColumnNames.entrySet()){
                if(reqColList.contains(colEntry.getValue())){
                    reqColumnNames.put(colEntry.getKey(), colEntry.getValue());
                }
            }
        }

        Map<String, String> customFieldsMap = new HashMap<String, String>();
        customFieldsMap.put("CUSTOM_DEFINED_1", "Custom Defined 1");
        customFieldsMap.put("CUSTOM_DEFINED_2", "Custom Defined 2");
        customFieldsMap.put("CUSTOM_DEFINED_3", "Custom Defined 3");
        customFieldsMap.put("CUSTOM_DEFINED_4", "Custom Defined 4");
        customFieldsMap.put("CUSTOM_DEFINED_5", "Custom Defined 5");
        customFieldsMap.put("CUSTOM_DEFINED_6", "Custom Defined 6");
        customFieldsMap.put("CUSTOM_DEFINED_7", "Custom Defined 7");
        customFieldsMap.put("CUSTOM_DEFINED_8", "Custom Defined 8");
        customFieldsMap.put("CUSTOM_DEFINED_9", "Custom Defined 9");
        customFieldsMap.put("CUSTOM_DEFINED_10", "Custom Defined 10");

        for (Map.Entry<String, String> entry : customFieldsMap.entrySet()) {
            if (customDefinedColsByCustomer != null && customDefinedColsByCustomer.size() > 0 && customDefinedColsByCustomer.containsKey(entry.getKey())) {
                reqColumnNames.put(entry.getKey(), customDefinedColsByCustomer.get(entry.getKey()));
            } else {
                reqColumnNames.put(entry.getKey(), entry.getValue());
            }
        }

        List<String> savedColumns = getColumnConfigByUser(filter.getUserId(), reportId);
        if(null == savedColumns || savedColumns.size() == 0){
            savedColumns = new ArrayList<String>(reqColList);
        }

        JSONArray columnsDetailsJson = new JSONArray();
        for(Map.Entry<String, String> colEntry : reqColumnNames.entrySet()){
            JSONObject columnData = new JSONObject();
            columnData.put("columnName", colEntry.getValue());
            columnData.put("selectClause", colEntry.getKey());
            columnData.put("labelsColumnName",colEntry.getValue().replaceAll("\\s+","").toLowerCase() );
            columnData.put("originalColumnName", colEntry.getValue().toUpperCase());
            columnData.put("checked", savedColumns.contains(colEntry.getValue().toUpperCase()));
            columnsDetailsJson.put(columnData);
        }
        return columnsDetailsJson;
    }

    /**
     * Save user column config.
     * @param userId
     * @param columnNames
     * @param isLineItemReport
     */
    public void saveUserDefinedColumnConfig(long userId, String columnNames, boolean isLineItemReport){
        DashCustomColumnConfigDto columnConfig = new DashCustomColumnConfigDto();
        columnConfig.setReportId(isLineItemReport ? 197l : 100l);
        columnConfig.setUserId(userId);

        int columnsLen = columnNames.length();
        if (columnsLen <= 4000) {
            columnConfig.setColumnDefined1(columnNames);
        } else if (columnsLen <= 8000) {
            columnConfig.setColumnDefined1(columnNames.substring(0, 3999));
            columnConfig.setColumnDefined2(columnNames.substring(4000, columnsLen));
        } else if (columnsLen <= 12000) {
            columnConfig.setColumnDefined1(columnNames.substring(0, 3999));
            columnConfig.setColumnDefined2(columnNames.substring(4000, 7999));
            columnConfig.setColumnDefined3(columnNames.substring(8000, columnsLen));
        } else if (columnsLen <= 16000) {
            columnConfig.setColumnDefined1(columnNames.substring(0, 3999));
            columnConfig.setColumnDefined2(columnNames.substring(4000, 7999));
            columnConfig.setColumnDefined3(columnNames.substring(8000, 11999));
            columnConfig.setColumnDefined4(columnNames.substring(12000, columnsLen));
        }
        dashboardsDao.saveUserDefinedColumnConfig(columnConfig);
    }

    /**
     * Delete saved filter by id.
     * @param filterId
     */
    public void deleteSavedFilter(long filterId){
        dashboardsDao.deleteSavedFilter(filterId);
    }

    /**
     * save/update savedfilter details.
     * @param savedFilter
     */
    public DashSavedFilterDto updateSavedFilter(DashSavedFilterDto savedFilter){
        return dashboardsDao.updateSavedFilter(savedFilter);
    }

    /**
     * To set default filter.
     * @param filterId
     * @param userId
     */
    public void makeDefaultSavedFilter(long filterId, long userId){
        dashboardsDao.makeDefaultSavedFilter(filterId, userId);
    }

    /**
     * Get saved filter by name for this userId.
     * @param userId
     * @param filterName
     * @return
     */
    public List<DashSavedFilterDto> getUserFilterByName(long userId, String filterName){
        return dashboardsDao.getUserFilterByName(userId, filterName);
    }

    /**
     * Get package distribution count details.
     * @param filter
     * @return
     */
    public List<ShipmentDto> getPackageDistributionCount(DashboardsFilterCriteria filter){
        return dashboardsDao.getPackageDistributionCount(filter);
    }

    public List<AverageWeightModeShipmtDto> getAverageWeightModeByPeriod(DashboardsFilterCriteria filter, boolean isTopTenAccessorial){
        return dashboardsDao.getAverageWeightModeByPeriod(filter, isTopTenAccessorial);
    }

    public List<AverageWeightModeShipmtDto> getAverageWeightModeByWeek(DashboardsFilterCriteria filter, boolean isTopTenAccessorial){
        return dashboardsDao.getAverageWeightModeByWeek(filter, isTopTenAccessorial);
    }

    public List<AverageSpendPerShipmentDto> getAverageSpendPerShipmentByPeriod(DashboardsFilterCriteria filter, boolean isTopTenAccessorial) {
        return dashboardsDao.getAverageSpendPerShipmentByPeriod(filter, isTopTenAccessorial);
    }

    public List<AverageSpendPerShipmentDto> getAverageSpendPerShipmentByWeek(DashboardsFilterCriteria filter, boolean isTopTenAccessorial) {
        return dashboardsDao.getAverageSpendPerShipmentByWeek(filter, isTopTenAccessorial);
    }
}
