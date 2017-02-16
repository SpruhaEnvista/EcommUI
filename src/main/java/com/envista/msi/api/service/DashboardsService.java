package com.envista.msi.api.service;

import com.envista.msi.api.dao.DashboardsDao;
import com.envista.msi.api.web.rest.dto.dashboard.DashboardAppliedFilterDto;
import com.envista.msi.api.web.rest.dto.dashboard.DashboardsFilterCriteria;
import com.envista.msi.api.web.rest.dto.dashboard.accessorialspend.AccessorialSpendDto;
import com.envista.msi.api.web.rest.dto.dashboard.auditactivity.*;
import com.envista.msi.api.web.rest.dto.dashboard.netspend.*;
import com.envista.msi.api.web.rest.dto.dashboard.shipmentoverview.*;
import com.envista.msi.api.web.rest.dto.dashboard.taxspend.TaxSpendByCarrierDto;
import com.envista.msi.api.web.rest.dto.dashboard.taxspend.TaxSpendByMonthDto;
import com.envista.msi.api.web.rest.dto.dashboard.taxspend.TaxSpendDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.List;

/**
 * Created by Sarvesh on 1/19/2017.
 */


@Service
@Transactional
public class DashboardsService {

    @Inject
    private DashboardsDao dashboardsDao;

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
    public List<NetSpendOverTimeByMonthDto> getNetSpendOverTimeByMonth(DashboardsFilterCriteria filter, boolean isTopTenAccessorial) {
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
    public List<NetSpendByCarrierDto> getNetSpendByCarrier(DashboardsFilterCriteria filter, boolean isTopTenAccessorial){
        return dashboardsDao.getNetSpendByCarrier(filter, isTopTenAccessorial);
    }

    /**
     *
     * @param filter
     * @param isTopTenAccessorial
     * @return
     */
    public List<NetSpendByMonthDto> getNetSpendByMonth(DashboardsFilterCriteria filter, boolean isTopTenAccessorial){
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
    public List<TaxSpendByCarrierDto> getTaxSpendByCarrier(DashboardsFilterCriteria filter){
        return dashboardsDao.getTaxSpendByCarrier(filter);
    }

    /**
     *
     * @param filter
     * @return
     */
    public List<TaxSpendByMonthDto> getTaxSpendByMonth(DashboardsFilterCriteria filter){
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

    public List<PackageExceptionDto> getPackageExceptions(DashboardsFilterCriteria filter, boolean isTopTenAccessorial){
        return dashboardsDao.getPackageExceptions(filter, isTopTenAccessorial);
    }

    public List<PackageExceptionDto> getPackageExceptionsByCarrier(DashboardsFilterCriteria filter, boolean isTopTenAccessorial){
        return dashboardsDao.getPackageExceptionsByCarrier(filter, isTopTenAccessorial);
    }

    public List<PackageExceptionDto> getPackageExceptionsByMonth(DashboardsFilterCriteria filter, boolean isTopTenAccessorial){
        return dashboardsDao.getPackageExceptionsByMonth(filter, isTopTenAccessorial);
    }

    public List<AverageSpendPerShipmentByCarrierDto> getAvgSpendPerShipmtByCarrier(DashboardsFilterCriteria filter , boolean isTopTenAccessorial){
        return dashboardsDao.getAverageSpendPerShipmentByCarrier(filter,  isTopTenAccessorial);
    }

    public List<AverageSpendPerShipmentByMonthDto> getAvgSpendPerShipmtByMonth(DashboardsFilterCriteria filter , boolean isTopTenAccessorial){
        return dashboardsDao.getAverageSpendPerShipmentByMonth(filter,  isTopTenAccessorial);
    }
}
