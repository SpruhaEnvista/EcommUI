package com.envista.msi.api.dao;

import com.envista.msi.api.domain.PersistentContext;
import com.envista.msi.api.domain.util.DashboardSroredProcParam;
import com.envista.msi.api.domain.util.DashboardUtil;
import com.envista.msi.api.domain.util.QueryParameter;
import com.envista.msi.api.domain.util.StoredProcedureParameter;
import com.envista.msi.api.web.rest.dto.MapCoordinatesDto;
import com.envista.msi.api.web.rest.dto.ZipCodesTimeZonesDto;
import com.envista.msi.api.web.rest.dto.dashboard.DashboardAppliedFilterDto;
import com.envista.msi.api.web.rest.dto.dashboard.DashboardsFilterCriteria;
import com.envista.msi.api.web.rest.dto.dashboard.accessorialspend.AccessorialSpendDto;
import com.envista.msi.api.web.rest.dto.dashboard.annualsummary.AccountSummaryDto;
import com.envista.msi.api.web.rest.dto.dashboard.annualsummary.AnnualSummaryDto;
import com.envista.msi.api.web.rest.dto.dashboard.annualsummary.MonthlySpendByModeDto;
import com.envista.msi.api.web.rest.dto.dashboard.auditactivity.*;
import com.envista.msi.api.web.rest.dto.dashboard.netspend.*;
import com.envista.msi.api.web.rest.dto.dashboard.networkanalysis.PortLanesDto;
import com.envista.msi.api.web.rest.dto.dashboard.networkanalysis.ShipmentRegionDto;
import com.envista.msi.api.web.rest.dto.dashboard.networkanalysis.ShippingLanesDto;
import com.envista.msi.api.web.rest.dto.dashboard.shipmentoverview.*;
import com.envista.msi.api.web.rest.dto.dashboard.taxspend.TaxSpendByCarrierDto;
import com.envista.msi.api.web.rest.dto.dashboard.taxspend.TaxSpendByMonthDto;
import com.envista.msi.api.web.rest.dto.dashboard.taxspend.TaxSpendDto;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.List;

/**
 * Created by Sarvesh on 1/19/2017.
 */

@Repository("dashboardsDao")
public class DashboardsDao {

    @Inject
    private PersistentContext persistentContext;

    public DashboardAppliedFilterDto getUserAppliedFilter(Long userId) {
        return persistentContext.findEntity("DashAppliedFilterTb.getUserAppliedFilter",
                StoredProcedureParameter.with("p_user_id", userId));
    }

    /**
     * Get bet spend based on the applied filter.
     *
     * @param filter
     * @param isTopTenAccessorial
     * @return
     */
    @Transactional(readOnly = true)
    public List<NetSpendByModeDto> getNetSpendByModes(DashboardsFilterCriteria filter, boolean isTopTenAccessorial) {
        filter.setTopTenAccessorial(isTopTenAccessorial);
        String[] paramNames = {DashboardSroredProcParam.NetSpendParams.DATE_TYPE_PARAM, DashboardSroredProcParam.NetSpendParams.CONVERTED_CURRENCY_ID_PARAM,
                DashboardSroredProcParam.NetSpendParams.CONVERTED_CURRENCY_CODE_PARAM, DashboardSroredProcParam.NetSpendParams.CUSTOMER_IDS_CSV_PARAM,
                DashboardSroredProcParam.NetSpendParams.CARRIER_IDS_PARAM, DashboardSroredProcParam.NetSpendParams.MODES_PARAM,
                DashboardSroredProcParam.NetSpendParams.SERVICES_PARAM, DashboardSroredProcParam.NetSpendParams.LANES_PARAM,
                DashboardSroredProcParam.NetSpendParams.ACCESSORIAL_NAME_PARAM,
                DashboardSroredProcParam.NetSpendParams.FROM_DATE_PARAM, DashboardSroredProcParam.NetSpendParams.TO_DATE_PARAM,
                DashboardSroredProcParam.NetSpendParams.TOP_TEN_ACCESSORIAL_PARAM
        };
        QueryParameter queryParameter = DashboardUtil.prepareDashboardFilterStoredProcParam(paramNames, filter);
        return persistentContext.findEntities("NetSpendByModeDto.getNetSpendByMode", queryParameter);
    }

    /**
     * @param filter
     * @param isTopTenAccessorial
     * @return
     */
    public List<NetSpendOverTimeByMonthDto> getNetSpendOverTimeByMonth(DashboardsFilterCriteria filter, boolean isTopTenAccessorial) {
        filter.setTopTenAccessorial(isTopTenAccessorial);
        String[] paramNames = {DashboardSroredProcParam.NetSpendParams.DATE_TYPE_PARAM, DashboardSroredProcParam.NetSpendParams.CONVERTED_CURRENCY_ID_PARAM,
                DashboardSroredProcParam.NetSpendParams.CONVERTED_CURRENCY_CODE_PARAM, DashboardSroredProcParam.NetSpendParams.CUSTOMER_IDS_CSV_PARAM,
                DashboardSroredProcParam.NetSpendParams.CARRIER_IDS_PARAM, DashboardSroredProcParam.NetSpendParams.MODES_PARAM,
                DashboardSroredProcParam.NetSpendParams.SERVICES_PARAM, DashboardSroredProcParam.NetSpendParams.LANES_PARAM,
                DashboardSroredProcParam.NetSpendParams.ACCESSORIAL_NAME_PARAM,
                DashboardSroredProcParam.NetSpendParams.FROM_DATE_PARAM, DashboardSroredProcParam.NetSpendParams.TO_DATE_PARAM,
                DashboardSroredProcParam.NetSpendParams.TOP_TEN_ACCESSORIAL_PARAM
        };
        QueryParameter queryParameter = DashboardUtil.prepareDashboardFilterStoredProcParam(paramNames, filter);
        return persistentContext.findEntities("NetSpendOverTimeByMonthDto.getNetSpendOverTimeByMonth", queryParameter);
    }

    /**
     * @param filter
     * @param isTopTenAccessorial
     * @return
     */
    public List<NetSpendOverTimeDto> getNetSpendByOverTime(DashboardsFilterCriteria filter, boolean isTopTenAccessorial) {
        filter.setTopTenAccessorial(isTopTenAccessorial);
        String[] paramNames = {DashboardSroredProcParam.NetSpendParams.DATE_TYPE_PARAM, DashboardSroredProcParam.NetSpendParams.CONVERTED_CURRENCY_ID_PARAM,
                DashboardSroredProcParam.NetSpendParams.CONVERTED_CURRENCY_CODE_PARAM, DashboardSroredProcParam.NetSpendParams.CUSTOMER_IDS_CSV_PARAM,
                DashboardSroredProcParam.NetSpendParams.CARRIER_IDS_PARAM, DashboardSroredProcParam.NetSpendParams.MODES_PARAM,
                DashboardSroredProcParam.NetSpendParams.SERVICES_PARAM, DashboardSroredProcParam.NetSpendParams.LANES_PARAM,
                DashboardSroredProcParam.NetSpendParams.ACCESSORIAL_NAME_PARAM,
                DashboardSroredProcParam.NetSpendParams.FROM_DATE_PARAM, DashboardSroredProcParam.NetSpendParams.TO_DATE_PARAM,
                DashboardSroredProcParam.NetSpendParams.TOP_TEN_ACCESSORIAL_PARAM
        };
        QueryParameter queryParameter = DashboardUtil.prepareDashboardFilterStoredProcParam(paramNames, filter);
        return persistentContext.findEntities("NetSpendOverTimeDto.getNetSpendByOverTime", queryParameter);
    }

    /**
     * @param filter
     * @param isTopTenAccessorial
     * @return
     */
    @Transactional(readOnly = true)
    public List<NetSpendByCarrierDto> getNetSpendByCarrier(DashboardsFilterCriteria filter, boolean isTopTenAccessorial) {
        filter.setTopTenAccessorial(isTopTenAccessorial);
        String[] paramNames = {DashboardSroredProcParam.NetSpendParams.DATE_TYPE_PARAM, DashboardSroredProcParam.NetSpendParams.CONVERTED_CURRENCY_ID_PARAM,
                DashboardSroredProcParam.NetSpendParams.CONVERTED_CURRENCY_CODE_PARAM, DashboardSroredProcParam.NetSpendParams.CUSTOMER_IDS_CSV_PARAM,
                DashboardSroredProcParam.NetSpendParams.CARRIER_IDS_PARAM, DashboardSroredProcParam.NetSpendParams.MODES_PARAM,
                DashboardSroredProcParam.NetSpendParams.SERVICES_PARAM, DashboardSroredProcParam.NetSpendParams.LANES_PARAM,
                DashboardSroredProcParam.NetSpendParams.ACCESSORIAL_NAME_PARAM,
                DashboardSroredProcParam.NetSpendParams.FROM_DATE_PARAM, DashboardSroredProcParam.NetSpendParams.TO_DATE_PARAM,
                DashboardSroredProcParam.NetSpendParams.TOP_TEN_ACCESSORIAL_PARAM,
                DashboardSroredProcParam.NetSpendParams.MODE_NAMES_PARAM
        };
        QueryParameter queryParameter = DashboardUtil.prepareDashboardFilterStoredProcParam(paramNames, filter);
        return persistentContext.findEntities("NetSpendByCarrierDto.getNetSpendByCarrier", queryParameter);
    }

    /**
     * @param filter
     * @param isTopTenAccessorial
     * @return
     */
    public List<NetSpendByMonthDto> getNetSpendByMonth(DashboardsFilterCriteria filter, boolean isTopTenAccessorial) {
        filter.setTopTenAccessorial(isTopTenAccessorial);
        String[] paramNames = {DashboardSroredProcParam.NetSpendParams.DATE_TYPE_PARAM, DashboardSroredProcParam.NetSpendParams.CONVERTED_CURRENCY_ID_PARAM,
                DashboardSroredProcParam.NetSpendParams.CONVERTED_CURRENCY_CODE_PARAM, DashboardSroredProcParam.NetSpendParams.CUSTOMER_IDS_CSV_PARAM,
                DashboardSroredProcParam.NetSpendParams.CARRIER_IDS_PARAM, DashboardSroredProcParam.NetSpendParams.MODES_PARAM,
                DashboardSroredProcParam.NetSpendParams.SERVICES_PARAM, DashboardSroredProcParam.NetSpendParams.LANES_PARAM,
                DashboardSroredProcParam.NetSpendParams.ACCESSORIAL_NAME_PARAM,
                DashboardSroredProcParam.NetSpendParams.FROM_DATE_PARAM, DashboardSroredProcParam.NetSpendParams.TO_DATE_PARAM,
                DashboardSroredProcParam.NetSpendParams.TOP_TEN_ACCESSORIAL_PARAM,
                DashboardSroredProcParam.NetSpendParams.MODE_NAMES_PARAM, DashboardSroredProcParam.NetSpendParams.SCORE_TYPE_PARAM
        };
        QueryParameter queryParameter = DashboardUtil.prepareDashboardFilterStoredProcParam(paramNames, filter);
        return persistentContext.findEntities("NetSpendByMonthDto.getNetSpendByMonth", queryParameter);
    }

    /**
     * @param filter
     * @return
     */
    public List<TaxSpendDto> getTaxSpend(DashboardsFilterCriteria filter) {
        String[] paramNames = {DashboardSroredProcParam.TaxSpendParams.DATE_TYPE_PARAM, DashboardSroredProcParam.TaxSpendParams.CONVERTED_CURRENCY_ID_PARAM,
                DashboardSroredProcParam.TaxSpendParams.CUSTOMER_IDS_CSV_PARAM,
                DashboardSroredProcParam.TaxSpendParams.CARRIER_IDS_PARAM, DashboardSroredProcParam.TaxSpendParams.MODES_PARAM,
                DashboardSroredProcParam.TaxSpendParams.SERVICES_PARAM, DashboardSroredProcParam.TaxSpendParams.LANES_PARAM,
                DashboardSroredProcParam.TaxSpendParams.FROM_DATE_PARAM, DashboardSroredProcParam.TaxSpendParams.TO_DATE_PARAM,
                DashboardSroredProcParam.TaxSpendParams.MODE_NAMES_PARAM
        };
        QueryParameter queryParameter = DashboardUtil.prepareDashboardFilterStoredProcParam(paramNames, filter);
        return persistentContext.findEntities("TaxSpendDto.getTaxSpend", queryParameter);
    }

    /**
     * @param filter
     * @return
     */
    public List<TaxSpendByCarrierDto> getTaxSpendByCarrier(DashboardsFilterCriteria filter) {
        String[] paramNames = {DashboardSroredProcParam.TaxSpendParams.DATE_TYPE_PARAM, DashboardSroredProcParam.TaxSpendParams.CONVERTED_CURRENCY_ID_PARAM,
                DashboardSroredProcParam.TaxSpendParams.CUSTOMER_IDS_CSV_PARAM,
                DashboardSroredProcParam.TaxSpendParams.CARRIER_IDS_PARAM, DashboardSroredProcParam.TaxSpendParams.MODES_PARAM,
                DashboardSroredProcParam.TaxSpendParams.SERVICES_PARAM, DashboardSroredProcParam.TaxSpendParams.LANES_PARAM,
                DashboardSroredProcParam.TaxSpendParams.FROM_DATE_PARAM, DashboardSroredProcParam.TaxSpendParams.TO_DATE_PARAM,
                DashboardSroredProcParam.TaxSpendParams.MODE_NAMES_PARAM, DashboardSroredProcParam.TaxSpendParams.TAX_PARAM
        };
        QueryParameter queryParameter = DashboardUtil.prepareDashboardFilterStoredProcParam(paramNames, filter);
        return persistentContext.findEntities("TaxSpendByCarrierDto.getTaxSpendByCarrier", queryParameter);
    }

    /**
     * @param filter
     * @return
     */
    public List<TaxSpendByMonthDto> getTaxSpendByMonth(DashboardsFilterCriteria filter) {
        String[] paramNames = {DashboardSroredProcParam.TaxSpendParams.DATE_TYPE_PARAM, DashboardSroredProcParam.TaxSpendParams.CONVERTED_CURRENCY_ID_PARAM,
                DashboardSroredProcParam.TaxSpendParams.CUSTOMER_IDS_CSV_PARAM,
                DashboardSroredProcParam.TaxSpendParams.CARRIER_IDS_PARAM, DashboardSroredProcParam.TaxSpendParams.MODES_PARAM,
                DashboardSroredProcParam.TaxSpendParams.SERVICES_PARAM, DashboardSroredProcParam.TaxSpendParams.LANES_PARAM,
                DashboardSroredProcParam.TaxSpendParams.FROM_DATE_PARAM, DashboardSroredProcParam.TaxSpendParams.TO_DATE_PARAM,
                DashboardSroredProcParam.TaxSpendParams.MODE_NAMES_PARAM, DashboardSroredProcParam.TaxSpendParams.TAX_PARAM
        };
        QueryParameter queryParameter = DashboardUtil.prepareDashboardFilterStoredProcParam(paramNames, filter);
        return persistentContext.findEntities("TaxSpendByMonthDto.getTaxSpendByMonth", queryParameter);
    }

    /**
     * @param filter
     * @param isTopTenAccessorial
     * @return
     */
    public List<AccessorialSpendDto> getTopAccessorialSpend(DashboardsFilterCriteria filter, boolean isTopTenAccessorial) {
        filter.setTopTenAccessorial(isTopTenAccessorial);
        String[] paramNames = {DashboardSroredProcParam.AccessorialSpendParams.DATE_TYPE_PARAM, DashboardSroredProcParam.AccessorialSpendParams.CONVERTED_CURRENCY_ID_PARAM,
                DashboardSroredProcParam.AccessorialSpendParams.CONVERTED_CURRENCY_CODE_PARAM, DashboardSroredProcParam.AccessorialSpendParams.CUSTOMER_IDS_CSV_PARAM,
                DashboardSroredProcParam.AccessorialSpendParams.CARRIER_IDS_PARAM, DashboardSroredProcParam.AccessorialSpendParams.MODES_PARAM,
                DashboardSroredProcParam.AccessorialSpendParams.SERVICES_PARAM, DashboardSroredProcParam.AccessorialSpendParams.LANES_PARAM,
                DashboardSroredProcParam.AccessorialSpendParams.ACCESSORIAL_NAME_PARAM, DashboardSroredProcParam.AccessorialSpendParams.FROM_DATE_PARAM,
                DashboardSroredProcParam.AccessorialSpendParams.TO_DATE_PARAM, DashboardSroredProcParam.AccessorialSpendParams.TOP_TEN_ACCESSORIAL_PARAM
        };
        QueryParameter queryParameter = DashboardUtil.prepareDashboardFilterStoredProcParam(paramNames, filter);
        return persistentContext.findEntities("AccessorialSpendDto.getTopAccessorialSpend", queryParameter);
    }

    /**
     * @param filter
     * @param isTopTenAccessorial
     * @return
     */
    public List<AccessorialSpendDto> getTopAccessorialSpendByCarrier(DashboardsFilterCriteria filter, boolean isTopTenAccessorial) {
        filter.setTopTenAccessorial(isTopTenAccessorial);
        String[] paramNames = {DashboardSroredProcParam.AccessorialSpendParams.DATE_TYPE_PARAM, DashboardSroredProcParam.AccessorialSpendParams.CONVERTED_CURRENCY_ID_PARAM,
                DashboardSroredProcParam.AccessorialSpendParams.CONVERTED_CURRENCY_CODE_PARAM, DashboardSroredProcParam.AccessorialSpendParams.CUSTOMER_IDS_CSV_PARAM,
                DashboardSroredProcParam.AccessorialSpendParams.CARRIER_IDS_PARAM, DashboardSroredProcParam.AccessorialSpendParams.MODES_PARAM,
                DashboardSroredProcParam.AccessorialSpendParams.SERVICES_PARAM, DashboardSroredProcParam.AccessorialSpendParams.LANES_PARAM,
                DashboardSroredProcParam.AccessorialSpendParams.ACCESSORIAL_NAME_PARAM, DashboardSroredProcParam.AccessorialSpendParams.FROM_DATE_PARAM,
                DashboardSroredProcParam.AccessorialSpendParams.TO_DATE_PARAM, DashboardSroredProcParam.AccessorialSpendParams.TOP_TEN_ACCESSORIAL_PARAM
        };
        QueryParameter queryParameter = DashboardUtil.prepareDashboardFilterStoredProcParam(paramNames, filter);
        return persistentContext.findEntities("AccessorialSpendDto.getTopAccessorialSpendByCarrier", queryParameter);
    }

    /**
     * @param filter
     * @param isTopTenAccessorial
     * @return
     */
    public List<AccessorialSpendDto> getTopAccessorialSpendByMonth(DashboardsFilterCriteria filter, boolean isTopTenAccessorial) {
        filter.setTopTenAccessorial(isTopTenAccessorial);
        String[] paramNames = {DashboardSroredProcParam.AccessorialSpendParams.DATE_TYPE_PARAM, DashboardSroredProcParam.AccessorialSpendParams.CONVERTED_CURRENCY_ID_PARAM,
                DashboardSroredProcParam.AccessorialSpendParams.CONVERTED_CURRENCY_CODE_PARAM, DashboardSroredProcParam.AccessorialSpendParams.CUSTOMER_IDS_CSV_PARAM,
                DashboardSroredProcParam.AccessorialSpendParams.CARRIER_IDS_PARAM, DashboardSroredProcParam.AccessorialSpendParams.MODES_PARAM,
                DashboardSroredProcParam.AccessorialSpendParams.SERVICES_PARAM, DashboardSroredProcParam.AccessorialSpendParams.LANES_PARAM,
                DashboardSroredProcParam.AccessorialSpendParams.ACCESSORIAL_NAME_PARAM, DashboardSroredProcParam.AccessorialSpendParams.FROM_DATE_PARAM,
                DashboardSroredProcParam.AccessorialSpendParams.TO_DATE_PARAM, DashboardSroredProcParam.AccessorialSpendParams.TOP_TEN_ACCESSORIAL_PARAM
        };
        QueryParameter queryParameter = DashboardUtil.prepareDashboardFilterStoredProcParam(paramNames, filter);
        return persistentContext.findEntities("AccessorialSpendDto.getTopAccessorialSpendByMonth", queryParameter);
    }

    /**
     * @param filter
     * @param isTopTenAccessorial
     * @return
     */
    public List<AccessorialSpendDto> getAccessorialSpend(DashboardsFilterCriteria filter, boolean isTopTenAccessorial) {
        filter.setTopTenAccessorial(isTopTenAccessorial);
        String[] paramNames = {DashboardSroredProcParam.AccessorialSpendParams.DATE_TYPE_PARAM, DashboardSroredProcParam.AccessorialSpendParams.CONVERTED_CURRENCY_ID_PARAM,
                DashboardSroredProcParam.AccessorialSpendParams.CUSTOMER_IDS_CSV_PARAM,
                DashboardSroredProcParam.AccessorialSpendParams.CARRIER_IDS_PARAM, DashboardSroredProcParam.AccessorialSpendParams.MODES_PARAM,
                DashboardSroredProcParam.AccessorialSpendParams.SERVICES_PARAM, DashboardSroredProcParam.AccessorialSpendParams.LANES_PARAM,
                DashboardSroredProcParam.AccessorialSpendParams.FROM_DATE_PARAM, DashboardSroredProcParam.AccessorialSpendParams.TO_DATE_PARAM,
                DashboardSroredProcParam.AccessorialSpendParams.MODE_NAMES_PARAM
        };
        QueryParameter queryParameter = DashboardUtil.prepareDashboardFilterStoredProcParam(paramNames, filter);
        return persistentContext.findEntities("AccessorialSpendDto.getAccessorialSpend", queryParameter);
    }

    /**
     * @param filter
     * @param isTopTenAccessorial
     * @return
     */
    public List<AccessorialSpendDto> getAccessorialSpendByCarrier(DashboardsFilterCriteria filter, boolean isTopTenAccessorial) {
        filter.setTopTenAccessorial(isTopTenAccessorial);
        String[] paramNames = {DashboardSroredProcParam.AccessorialSpendParams.DATE_TYPE_PARAM, DashboardSroredProcParam.AccessorialSpendParams.CONVERTED_CURRENCY_ID_PARAM,
                DashboardSroredProcParam.AccessorialSpendParams.CUSTOMER_IDS_CSV_PARAM,
                DashboardSroredProcParam.AccessorialSpendParams.CARRIER_IDS_PARAM, DashboardSroredProcParam.AccessorialSpendParams.MODES_PARAM,
                DashboardSroredProcParam.AccessorialSpendParams.SERVICES_PARAM, DashboardSroredProcParam.AccessorialSpendParams.LANES_PARAM,
                DashboardSroredProcParam.AccessorialSpendParams.FROM_DATE_PARAM, DashboardSroredProcParam.AccessorialSpendParams.TO_DATE_PARAM,
                DashboardSroredProcParam.AccessorialSpendParams.MODE_NAMES_PARAM, DashboardSroredProcParam.AccessorialSpendParams.ACCESSORIAL_DESC_PARAM
        };
        QueryParameter queryParameter = DashboardUtil.prepareDashboardFilterStoredProcParam(paramNames, filter);
        return persistentContext.findEntities("AccessorialSpendDto.getAccessorialSpendByCarrier", queryParameter);
    }

    /**
     * @param filter
     * @param isTopTenAccessorial
     * @return
     */
    public List<AccessorialSpendDto> getAccessorialSpendByMonth(DashboardsFilterCriteria filter, boolean isTopTenAccessorial) {
        filter.setTopTenAccessorial(isTopTenAccessorial);
        String[] paramNames = {DashboardSroredProcParam.AccessorialSpendParams.DATE_TYPE_PARAM, DashboardSroredProcParam.AccessorialSpendParams.CONVERTED_CURRENCY_ID_PARAM,
                DashboardSroredProcParam.AccessorialSpendParams.CUSTOMER_IDS_CSV_PARAM,
                DashboardSroredProcParam.AccessorialSpendParams.CARRIER_IDS_PARAM, DashboardSroredProcParam.AccessorialSpendParams.MODES_PARAM,
                DashboardSroredProcParam.AccessorialSpendParams.SERVICES_PARAM, DashboardSroredProcParam.AccessorialSpendParams.LANES_PARAM,
                DashboardSroredProcParam.AccessorialSpendParams.FROM_DATE_PARAM, DashboardSroredProcParam.AccessorialSpendParams.TO_DATE_PARAM,
                DashboardSroredProcParam.AccessorialSpendParams.MODE_NAMES_PARAM, DashboardSroredProcParam.AccessorialSpendParams.ACCESSORIAL_DESC_PARAM
        };
        QueryParameter queryParameter = DashboardUtil.prepareDashboardFilterStoredProcParam(paramNames, filter);
        return persistentContext.findEntities("AccessorialSpendDto.getAccessorialSpendByMonth", queryParameter);
    }

    /**
     * @param filter
     * @return
     */
    public List<AverageSpendPerShipmentDto> getAvgSpendPerShipmt(DashboardsFilterCriteria filter, boolean isTopTenAccessorial) {
        QueryParameter queryParameter = StoredProcedureParameter.with(DashboardSroredProcParam.AverageSpendShipmentParam.DATE_TYPE_PARAM, filter.getDateType())
                .and(DashboardSroredProcParam.AverageSpendShipmentParam.CONVERTED_CURRENCY_ID_PARAM, filter.getConvertCurrencyId())
                .and(DashboardSroredProcParam.AverageSpendShipmentParam.CONVERTED_CURRENCY_CODE_PARAM, filter.getConvertCurrencyCode())
                .and(DashboardSroredProcParam.AverageSpendShipmentParam.MODES_PARAM, filter.getModes())
                .and(DashboardSroredProcParam.AverageSpendShipmentParam.SERVICES_PARAM, filter.getServices())
                .and(DashboardSroredProcParam.AverageSpendShipmentParam.ACCESSORIAL_NAME_PARAM, filter.getAccessorialName())
                .and(DashboardSroredProcParam.AverageSpendShipmentParam.LANES_PARAM, filter.getLanes())
                .and(DashboardSroredProcParam.AverageSpendShipmentParam.FROM_DATE_PARAM, filter.getFromDate())
                .and(DashboardSroredProcParam.AverageSpendShipmentParam.TO_DATE_PARAM, filter.getToDate())
                .and(DashboardSroredProcParam.AverageSpendShipmentParam.CARRIER_ID_PARAM, filter.getCarriers())
                .and(DashboardSroredProcParam.AverageSpendShipmentParam.CUSTOMER_IDS_CSV_PARAM, filter.getCustomerIdsCSV())
                .and(DashboardSroredProcParam.AverageSpendShipmentParam.TOP_ACCESSORIAL_SPEND_PARAM, isTopTenAccessorial ? 1L : 0L);

        return persistentContext.findEntities("AverageSpendPerShipmentDto.getAverageSpendPerShipment", queryParameter);
    }

    /**
     * @param filter
     * @return
     */
    public List<AverageWeightModeShipmtDto> getAverageWeightByModeShipmt(DashboardsFilterCriteria filter, boolean isTopTenAccessorial) {
        QueryParameter queryParameter = StoredProcedureParameter.with(DashboardSroredProcParam.AverageWeightByModeShipmtParam.DATE_TYPE_PARAM, filter.getDateType())
                .and(DashboardSroredProcParam.AverageWeightByModeShipmtParam.CONVERTED_WEIGHT_UNIT_PARAM, filter.getConvertWeightUnit())
                .and(DashboardSroredProcParam.AverageWeightByModeShipmtParam.MODES_PARAM, filter.getModes())
                .and(DashboardSroredProcParam.AverageWeightByModeShipmtParam.SERVICES_PARAM, filter.getServices())
                .and(DashboardSroredProcParam.AverageWeightByModeShipmtParam.ACCESSORIAL_NAME_PARAM, filter.getAccessorialName())
                .and(DashboardSroredProcParam.AverageWeightByModeShipmtParam.LANES_PARAM, filter.getLanes())
                .and(DashboardSroredProcParam.AverageWeightByModeShipmtParam.FROM_DATE_PARAM, filter.getFromDate())
                .and(DashboardSroredProcParam.AverageWeightByModeShipmtParam.TO_DATE_PARAM, filter.getToDate())
                .and(DashboardSroredProcParam.AverageWeightByModeShipmtParam.CARRIER_ID_PARAM, filter.getCarriers())
                .and(DashboardSroredProcParam.AverageWeightByModeShipmtParam.CUSTOMER_IDS_CSV_PARAM, filter.getCustomerIdsCSV())
                .and(DashboardSroredProcParam.AverageWeightByModeShipmtParam.TOP_ACCESSORIAL_SPEND_PARAM, isTopTenAccessorial ? 1L : 0L);

        return persistentContext.findEntities("AverageWeightModeShipmtDto.AvgWeightByModeShipmentMapping", queryParameter);
    }

    public List<ServiceLevelUsageAndPerformanceDto> getServiceLevelUsageAndPerformance(DashboardsFilterCriteria filter, boolean isTopTenAccessorial) {
        filter.setTopTenAccessorial(isTopTenAccessorial);
        String[] paramNames = {DashboardSroredProcParam.ServiceLevelUsageAndPerformanceParams.DATE_TYPE_PARAM, DashboardSroredProcParam.ServiceLevelUsageAndPerformanceParams.CUSTOMER_IDS_CSV_PARAM,
                DashboardSroredProcParam.ServiceLevelUsageAndPerformanceParams.CARRIER_IDS_PARAM, DashboardSroredProcParam.ServiceLevelUsageAndPerformanceParams.MODES_PARAM,
                DashboardSroredProcParam.ServiceLevelUsageAndPerformanceParams.SERVICES_PARAM, DashboardSroredProcParam.ServiceLevelUsageAndPerformanceParams.LANES_PARAM,
                DashboardSroredProcParam.ServiceLevelUsageAndPerformanceParams.FROM_DATE_PARAM, DashboardSroredProcParam.ServiceLevelUsageAndPerformanceParams.TO_DATE_PARAM,
                DashboardSroredProcParam.ServiceLevelUsageAndPerformanceParams.ACCESSORIAL_NAME_PARAM, DashboardSroredProcParam.ServiceLevelUsageAndPerformanceParams.TOP_TEN_ACCESSORIAL_PARAM
        };
        QueryParameter queryParameter = DashboardUtil.prepareDashboardFilterStoredProcParam(paramNames, filter);
        return persistentContext.findEntities(ServiceLevelUsageAndPerformanceDto.Config.StoredProcedureQueryName.SERVICE_LEVEL_USAGE_AND_PERFORMANCE, queryParameter);
    }

    public List<InboundSpendDto> getInboundSpend(DashboardsFilterCriteria filter, boolean isTopTenAccessorial) {
        filter.setTopTenAccessorial(isTopTenAccessorial);
        String[] paramNames = {DashboardSroredProcParam.InboundSpendParams.DATE_TYPE_PARAM, DashboardSroredProcParam.InboundSpendParams.CONVERTED_CURRENCY_ID_PARAM,
                DashboardSroredProcParam.InboundSpendParams.CONVERTED_CURRENCY_CODE_PARAM, DashboardSroredProcParam.InboundSpendParams.CUSTOMER_IDS_CSV_PARAM,
                DashboardSroredProcParam.InboundSpendParams.CARRIER_IDS_PARAM, DashboardSroredProcParam.InboundSpendParams.MODES_PARAM,
                DashboardSroredProcParam.InboundSpendParams.SERVICES_PARAM, DashboardSroredProcParam.InboundSpendParams.LANES_PARAM,
                DashboardSroredProcParam.InboundSpendParams.FROM_DATE_PARAM, DashboardSroredProcParam.InboundSpendParams.TO_DATE_PARAM,
                DashboardSroredProcParam.InboundSpendParams.ACCESSORIAL_NAME_PARAM, DashboardSroredProcParam.InboundSpendParams.TOP_TEN_ACCESSORIAL_PARAM
        };
        QueryParameter queryParameter = DashboardUtil.prepareDashboardFilterStoredProcParam(paramNames, filter);
        return persistentContext.findEntities(InboundSpendDto.Config.StoredProcedureQueryName.INBOUND_SPEND, queryParameter);
    }

    public List<InboundSpendDto> getInboundSpendByMonth(DashboardsFilterCriteria filter, boolean isTopTenAccessorial) {
        filter.setTopTenAccessorial(isTopTenAccessorial);
        String[] paramNames = {DashboardSroredProcParam.InboundSpendParams.DATE_TYPE_PARAM, DashboardSroredProcParam.InboundSpendParams.CONVERTED_CURRENCY_ID_PARAM,
                DashboardSroredProcParam.InboundSpendParams.CONVERTED_CURRENCY_CODE_PARAM, DashboardSroredProcParam.InboundSpendParams.CUSTOMER_IDS_CSV_PARAM,
                DashboardSroredProcParam.InboundSpendParams.CARRIER_IDS_PARAM, DashboardSroredProcParam.InboundSpendParams.MODES_PARAM,
                DashboardSroredProcParam.InboundSpendParams.SERVICES_PARAM, DashboardSroredProcParam.InboundSpendParams.LANES_PARAM,
                DashboardSroredProcParam.InboundSpendParams.FROM_DATE_PARAM, DashboardSroredProcParam.InboundSpendParams.TO_DATE_PARAM,
                DashboardSroredProcParam.InboundSpendParams.ACCESSORIAL_NAME_PARAM, DashboardSroredProcParam.InboundSpendParams.TOP_TEN_ACCESSORIAL_PARAM
        };
        QueryParameter queryParameter = DashboardUtil.prepareDashboardFilterStoredProcParam(paramNames, filter);
        return persistentContext.findEntities(InboundSpendDto.Config.StoredProcedureQueryName.INBOUND_SPEND_BY_MONTH, queryParameter);
    }

    public List<OutboundSpendDto> getOutboundSpend(DashboardsFilterCriteria filter, boolean isTopTenAccessorial) {
        filter.setTopTenAccessorial(isTopTenAccessorial);
        String[] paramNames = {DashboardSroredProcParam.OutboundSpendParams.DATE_TYPE_PARAM, DashboardSroredProcParam.OutboundSpendParams.CONVERTED_CURRENCY_ID_PARAM,
                DashboardSroredProcParam.OutboundSpendParams.CONVERTED_CURRENCY_CODE_PARAM, DashboardSroredProcParam.OutboundSpendParams.CUSTOMER_IDS_CSV_PARAM,
                DashboardSroredProcParam.OutboundSpendParams.CARRIER_IDS_PARAM, DashboardSroredProcParam.OutboundSpendParams.MODES_PARAM,
                DashboardSroredProcParam.OutboundSpendParams.SERVICES_PARAM, DashboardSroredProcParam.OutboundSpendParams.LANES_PARAM,
                DashboardSroredProcParam.OutboundSpendParams.FROM_DATE_PARAM, DashboardSroredProcParam.OutboundSpendParams.TO_DATE_PARAM,
                DashboardSroredProcParam.OutboundSpendParams.ACCESSORIAL_NAME_PARAM, DashboardSroredProcParam.OutboundSpendParams.TOP_TEN_ACCESSORIAL_PARAM
        };
        QueryParameter queryParameter = DashboardUtil.prepareDashboardFilterStoredProcParam(paramNames, filter);
        return persistentContext.findEntities(OutboundSpendDto.Config.StoredProcedureQueryName.OUTBOUND_SPEND, queryParameter);
    }

    public List<OutboundSpendDto> getOutboundSpendByMonth(DashboardsFilterCriteria filter, boolean isTopTenAccessorial) {
        filter.setTopTenAccessorial(isTopTenAccessorial);
        String[] paramNames = {DashboardSroredProcParam.OutboundSpendParams.DATE_TYPE_PARAM, DashboardSroredProcParam.OutboundSpendParams.CONVERTED_CURRENCY_ID_PARAM,
                DashboardSroredProcParam.OutboundSpendParams.CONVERTED_CURRENCY_CODE_PARAM, DashboardSroredProcParam.OutboundSpendParams.CUSTOMER_IDS_CSV_PARAM,
                DashboardSroredProcParam.OutboundSpendParams.CARRIER_IDS_PARAM, DashboardSroredProcParam.OutboundSpendParams.MODES_PARAM,
                DashboardSroredProcParam.OutboundSpendParams.SERVICES_PARAM, DashboardSroredProcParam.OutboundSpendParams.LANES_PARAM,
                DashboardSroredProcParam.OutboundSpendParams.FROM_DATE_PARAM, DashboardSroredProcParam.OutboundSpendParams.TO_DATE_PARAM,
                DashboardSroredProcParam.OutboundSpendParams.ACCESSORIAL_NAME_PARAM, DashboardSroredProcParam.OutboundSpendParams.TOP_TEN_ACCESSORIAL_PARAM
        };
        QueryParameter queryParameter = DashboardUtil.prepareDashboardFilterStoredProcParam(paramNames, filter);
        return persistentContext.findEntities(OutboundSpendDto.Config.StoredProcedureQueryName.OUTBOUND_SPEND_BY_MONTH, queryParameter);
    }

    public List<InvoiceStatusCountDto> getInvoiceStatusCount(DashboardsFilterCriteria filter) {
        String[] paramNames = {DashboardSroredProcParam.InvoiceStatusCountParams.DATE_TYPE_PARAM,
                DashboardSroredProcParam.InvoiceStatusCountParams.CUSTOMER_IDS_CSV_PARAM,
                DashboardSroredProcParam.InvoiceStatusCountParams.CARRIER_IDS_PARAM, DashboardSroredProcParam.InvoiceStatusCountParams.MODES_PARAM,
                DashboardSroredProcParam.InvoiceStatusCountParams.SERVICES_PARAM, DashboardSroredProcParam.InvoiceStatusCountParams.LANES_PARAM,
                DashboardSroredProcParam.InvoiceStatusCountParams.FROM_DATE_PARAM, DashboardSroredProcParam.InvoiceStatusCountParams.TO_DATE_PARAM,
                DashboardSroredProcParam.InvoiceStatusCountParams.MODE_NAMES_PARAM
        };
        QueryParameter queryParameter = DashboardUtil.prepareDashboardFilterStoredProcParam(paramNames, filter);
        return persistentContext.findEntities("InvoiceStatusCountDto.getInvoiceStatusCount", queryParameter);
    }

    public List<InvoiceStatusCountDto> getInvoiceStatusCountByCarrier(DashboardsFilterCriteria filter) {
        String[] paramNames = {DashboardSroredProcParam.InvoiceStatusCountParams.DATE_TYPE_PARAM,
                DashboardSroredProcParam.InvoiceStatusCountParams.CUSTOMER_IDS_CSV_PARAM,
                DashboardSroredProcParam.InvoiceStatusCountParams.CARRIER_IDS_PARAM, DashboardSroredProcParam.InvoiceStatusCountParams.MODES_PARAM,
                DashboardSroredProcParam.InvoiceStatusCountParams.SERVICES_PARAM, DashboardSroredProcParam.InvoiceStatusCountParams.LANES_PARAM,
                DashboardSroredProcParam.InvoiceStatusCountParams.FROM_DATE_PARAM, DashboardSroredProcParam.InvoiceStatusCountParams.TO_DATE_PARAM,
                DashboardSroredProcParam.InvoiceStatusCountParams.MODE_NAMES_PARAM, DashboardSroredProcParam.InvoiceStatusCountParams.INVOICE_STATUS_ID_PARAM
        };
        QueryParameter queryParameter = DashboardUtil.prepareDashboardFilterStoredProcParam(paramNames, filter);
        return persistentContext.findEntities("InvoiceStatusCountDto.getInvoiceStatusCountByCarrier", queryParameter);
    }

    public List<InvoiceStatusCountDto> getInvoiceStatusCountByMonth(DashboardsFilterCriteria filter) {
        String[] paramNames = {DashboardSroredProcParam.InvoiceStatusCountParams.DATE_TYPE_PARAM,
                DashboardSroredProcParam.InvoiceStatusCountParams.CUSTOMER_IDS_CSV_PARAM,
                DashboardSroredProcParam.InvoiceStatusCountParams.CARRIER_IDS_PARAM, DashboardSroredProcParam.InvoiceStatusCountParams.MODES_PARAM,
                DashboardSroredProcParam.InvoiceStatusCountParams.SERVICES_PARAM, DashboardSroredProcParam.InvoiceStatusCountParams.LANES_PARAM,
                DashboardSroredProcParam.InvoiceStatusCountParams.FROM_DATE_PARAM, DashboardSroredProcParam.InvoiceStatusCountParams.TO_DATE_PARAM,
                DashboardSroredProcParam.InvoiceStatusCountParams.MODE_NAMES_PARAM, DashboardSroredProcParam.InvoiceStatusCountParams.INVOICE_STATUS_ID_PARAM
        };
        QueryParameter queryParameter = DashboardUtil.prepareDashboardFilterStoredProcParam(paramNames, filter);
        return persistentContext.findEntities("InvoiceStatusCountDto.getInvoiceStatusCountByMonth", queryParameter);
    }

    public List<InvoiceStatusAmountDto> getInvoiceStatusAmount(DashboardsFilterCriteria filter) {
        String[] paramNames = {DashboardSroredProcParam.InvoiceStatusAmountParams.DATE_TYPE_PARAM, DashboardSroredProcParam.InvoiceStatusAmountParams.CONVERTED_CURRENCY_ID_PARAM,
                DashboardSroredProcParam.InvoiceStatusAmountParams.CUSTOMER_IDS_CSV_PARAM,
                DashboardSroredProcParam.InvoiceStatusAmountParams.CARRIER_IDS_PARAM, DashboardSroredProcParam.InvoiceStatusAmountParams.MODES_PARAM,
                DashboardSroredProcParam.InvoiceStatusAmountParams.SERVICES_PARAM, DashboardSroredProcParam.InvoiceStatusAmountParams.LANES_PARAM,
                DashboardSroredProcParam.InvoiceStatusAmountParams.FROM_DATE_PARAM, DashboardSroredProcParam.InvoiceStatusAmountParams.TO_DATE_PARAM,
                DashboardSroredProcParam.InvoiceStatusAmountParams.MODE_NAMES_PARAM
        };
        QueryParameter queryParameter = DashboardUtil.prepareDashboardFilterStoredProcParam(paramNames, filter);
        return persistentContext.findEntities("InvoiceStatusAmountDto.getInvoiceStatusAmount", queryParameter);
    }

    public List<InvoiceStatusAmountDto> getInvoiceStatusAmountByCarrier(DashboardsFilterCriteria filter) {
        String[] paramNames = {DashboardSroredProcParam.InvoiceStatusAmountParams.DATE_TYPE_PARAM, DashboardSroredProcParam.InvoiceStatusAmountParams.CONVERTED_CURRENCY_ID_PARAM,
                DashboardSroredProcParam.InvoiceStatusAmountParams.CUSTOMER_IDS_CSV_PARAM,
                DashboardSroredProcParam.InvoiceStatusAmountParams.CARRIER_IDS_PARAM, DashboardSroredProcParam.InvoiceStatusAmountParams.MODES_PARAM,
                DashboardSroredProcParam.InvoiceStatusAmountParams.SERVICES_PARAM, DashboardSroredProcParam.InvoiceStatusAmountParams.LANES_PARAM,
                DashboardSroredProcParam.InvoiceStatusAmountParams.FROM_DATE_PARAM, DashboardSroredProcParam.InvoiceStatusAmountParams.TO_DATE_PARAM,
                DashboardSroredProcParam.InvoiceStatusAmountParams.MODE_NAMES_PARAM, DashboardSroredProcParam.InvoiceStatusAmountParams.INVOICE_STATUS_ID_PARAM
        };
        QueryParameter queryParameter = DashboardUtil.prepareDashboardFilterStoredProcParam(paramNames, filter);
        return persistentContext.findEntities("InvoiceStatusAmountDto.getInvoiceStatusAmountByCarrier", queryParameter);
    }

    public List<InvoiceStatusAmountDto> getInvoiceStatusAmountByMonth(DashboardsFilterCriteria filter) {
        String[] paramNames = {DashboardSroredProcParam.InvoiceStatusAmountParams.DATE_TYPE_PARAM, DashboardSroredProcParam.InvoiceStatusAmountParams.CONVERTED_CURRENCY_ID_PARAM,
                DashboardSroredProcParam.InvoiceStatusAmountParams.CUSTOMER_IDS_CSV_PARAM,
                DashboardSroredProcParam.InvoiceStatusAmountParams.CARRIER_IDS_PARAM, DashboardSroredProcParam.InvoiceStatusAmountParams.MODES_PARAM,
                DashboardSroredProcParam.InvoiceStatusAmountParams.SERVICES_PARAM, DashboardSroredProcParam.InvoiceStatusAmountParams.LANES_PARAM,
                DashboardSroredProcParam.InvoiceStatusAmountParams.FROM_DATE_PARAM, DashboardSroredProcParam.InvoiceStatusAmountParams.TO_DATE_PARAM,
                DashboardSroredProcParam.InvoiceStatusAmountParams.MODE_NAMES_PARAM, DashboardSroredProcParam.InvoiceStatusAmountParams.INVOICE_STATUS_ID_PARAM
        };
        QueryParameter queryParameter = DashboardUtil.prepareDashboardFilterStoredProcParam(paramNames, filter);
        return persistentContext.findEntities("InvoiceStatusAmountDto.getInvoiceStatusAmountByMonth", queryParameter);
    }

    public List<InvoiceMethodScoreDto> getInvoiceMethodScore(DashboardsFilterCriteria filter) {
        String[] paramNames = {DashboardSroredProcParam.InvoiceMethodScoreParams.DATE_TYPE_PARAM, DashboardSroredProcParam.InvoiceMethodScoreParams.CUSTOMER_IDS_CSV_PARAM,
                DashboardSroredProcParam.InvoiceMethodScoreParams.CARRIER_IDS_PARAM, DashboardSroredProcParam.InvoiceMethodScoreParams.MODES_PARAM,
                DashboardSroredProcParam.InvoiceMethodScoreParams.SERVICES_PARAM, DashboardSroredProcParam.InvoiceMethodScoreParams.LANES_PARAM,
                DashboardSroredProcParam.InvoiceMethodScoreParams.FROM_DATE_PARAM, DashboardSroredProcParam.InvoiceMethodScoreParams.TO_DATE_PARAM,
                DashboardSroredProcParam.InvoiceMethodScoreParams.MODE_NAMES_PARAM
        };
        QueryParameter queryParameter = DashboardUtil.prepareDashboardFilterStoredProcParam(paramNames, filter);
        return persistentContext.findEntities("InvoiceMethodScoreDto.getInvoiceMethodScore", queryParameter);
    }

    public List<InvoiceMethodScoreDto> getInvoiceMethodScoreByCarrier(DashboardsFilterCriteria filter) {
        String[] paramNames = {DashboardSroredProcParam.InvoiceMethodScoreParams.DATE_TYPE_PARAM, DashboardSroredProcParam.InvoiceMethodScoreParams.CUSTOMER_IDS_CSV_PARAM,
                DashboardSroredProcParam.InvoiceMethodScoreParams.CARRIER_IDS_PARAM, DashboardSroredProcParam.InvoiceMethodScoreParams.MODES_PARAM,
                DashboardSroredProcParam.InvoiceMethodScoreParams.SERVICES_PARAM, DashboardSroredProcParam.InvoiceMethodScoreParams.LANES_PARAM,
                DashboardSroredProcParam.InvoiceMethodScoreParams.FROM_DATE_PARAM, DashboardSroredProcParam.InvoiceMethodScoreParams.TO_DATE_PARAM,
                DashboardSroredProcParam.InvoiceMethodScoreParams.MODE_NAMES_PARAM, DashboardSroredProcParam.InvoiceMethodScoreParams.INVOICE_METHOD_PARAM
        };
        QueryParameter queryParameter = DashboardUtil.prepareDashboardFilterStoredProcParam(paramNames, filter);
        return persistentContext.findEntities("InvoiceMethodScoreDto.getInvoiceMethodScoreByCarrier", queryParameter);
    }

    public List<InvoiceMethodScoreDto> getInvoiceMethodScoreByMonth(DashboardsFilterCriteria filter) {
        String[] paramNames = {DashboardSroredProcParam.InvoiceMethodScoreParams.DATE_TYPE_PARAM, DashboardSroredProcParam.InvoiceMethodScoreParams.CUSTOMER_IDS_CSV_PARAM,
                DashboardSroredProcParam.InvoiceMethodScoreParams.CARRIER_IDS_PARAM, DashboardSroredProcParam.InvoiceMethodScoreParams.MODES_PARAM,
                DashboardSroredProcParam.InvoiceMethodScoreParams.SERVICES_PARAM, DashboardSroredProcParam.InvoiceMethodScoreParams.LANES_PARAM,
                DashboardSroredProcParam.InvoiceMethodScoreParams.FROM_DATE_PARAM, DashboardSroredProcParam.InvoiceMethodScoreParams.TO_DATE_PARAM,
                DashboardSroredProcParam.InvoiceMethodScoreParams.MODE_NAMES_PARAM, DashboardSroredProcParam.InvoiceMethodScoreParams.INVOICE_METHOD_PARAM
        };
        QueryParameter queryParameter = DashboardUtil.prepareDashboardFilterStoredProcParam(paramNames, filter);
        return persistentContext.findEntities("InvoiceMethodScoreDto.getInvoiceMethodScoreByMonth", queryParameter);
    }

    public List<OrderMatchDto> getOrderMatchStatus(DashboardsFilterCriteria filter) {
        String[] paramNames = {DashboardSroredProcParam.OrderMatchParams.DATE_TYPE_PARAM, DashboardSroredProcParam.OrderMatchParams.CUSTOMER_IDS_CSV_PARAM,
                DashboardSroredProcParam.OrderMatchParams.CARRIER_IDS_PARAM, DashboardSroredProcParam.OrderMatchParams.MODES_PARAM,
                DashboardSroredProcParam.OrderMatchParams.SERVICES_PARAM, DashboardSroredProcParam.OrderMatchParams.LANES_PARAM,
                DashboardSroredProcParam.OrderMatchParams.FROM_DATE_PARAM, DashboardSroredProcParam.OrderMatchParams.TO_DATE_PARAM,
                DashboardSroredProcParam.OrderMatchParams.MODE_NAMES_PARAM
        };
        QueryParameter queryParameter = DashboardUtil.prepareDashboardFilterStoredProcParam(paramNames, filter);
        return persistentContext.findEntities("OrderMatchDto.getOrderMatch", queryParameter);
    }

    public List<OrderMatchDto> getOrderMatchByCarrier(DashboardsFilterCriteria filter) {
        String[] paramNames = {DashboardSroredProcParam.OrderMatchParams.DATE_TYPE_PARAM, DashboardSroredProcParam.OrderMatchParams.CUSTOMER_IDS_CSV_PARAM,
                DashboardSroredProcParam.OrderMatchParams.CARRIER_IDS_PARAM, DashboardSroredProcParam.OrderMatchParams.MODES_PARAM,
                DashboardSroredProcParam.OrderMatchParams.SERVICES_PARAM, DashboardSroredProcParam.OrderMatchParams.LANES_PARAM,
                DashboardSroredProcParam.OrderMatchParams.FROM_DATE_PARAM, DashboardSroredProcParam.OrderMatchParams.TO_DATE_PARAM,
                DashboardSroredProcParam.OrderMatchParams.MODE_NAMES_PARAM, DashboardSroredProcParam.OrderMatchParams.ORDER_MATCH_PARAM
        };
        QueryParameter queryParameter = DashboardUtil.prepareDashboardFilterStoredProcParam(paramNames, filter);
        return persistentContext.findEntities("OrderMatchDto.getOrderMatchByCarrier", queryParameter);
    }

    public List<OrderMatchDto> getOrderMatchByMonth(DashboardsFilterCriteria filter) {
        String[] paramNames = {DashboardSroredProcParam.OrderMatchParams.DATE_TYPE_PARAM, DashboardSroredProcParam.OrderMatchParams.CUSTOMER_IDS_CSV_PARAM,
                DashboardSroredProcParam.OrderMatchParams.CARRIER_IDS_PARAM, DashboardSroredProcParam.OrderMatchParams.MODES_PARAM,
                DashboardSroredProcParam.OrderMatchParams.SERVICES_PARAM, DashboardSroredProcParam.OrderMatchParams.LANES_PARAM,
                DashboardSroredProcParam.OrderMatchParams.FROM_DATE_PARAM, DashboardSroredProcParam.OrderMatchParams.TO_DATE_PARAM,
                DashboardSroredProcParam.OrderMatchParams.MODE_NAMES_PARAM, DashboardSroredProcParam.OrderMatchParams.ORDER_MATCH_PARAM
        };
        QueryParameter queryParameter = DashboardUtil.prepareDashboardFilterStoredProcParam(paramNames, filter);
        return persistentContext.findEntities("OrderMatchDto.getOrderMatchByMonth", queryParameter);
    }

    public List<BilledVsApprovedDto> getBilledVsApprovedData(DashboardsFilterCriteria filter) {
        String[] paramNames = {DashboardSroredProcParam.BilledVsApprovedParams.DATE_TYPE_PARAM, DashboardSroredProcParam.BilledVsApprovedParams.CONVERTED_CURRENCY_ID_PARAM,
                DashboardSroredProcParam.BilledVsApprovedParams.CUSTOMER_IDS_CSV_PARAM,
                DashboardSroredProcParam.BilledVsApprovedParams.CARRIER_IDS_PARAM, DashboardSroredProcParam.BilledVsApprovedParams.MODES_PARAM,
                DashboardSroredProcParam.BilledVsApprovedParams.SERVICES_PARAM, DashboardSroredProcParam.BilledVsApprovedParams.LANES_PARAM,
                DashboardSroredProcParam.BilledVsApprovedParams.FROM_DATE_PARAM, DashboardSroredProcParam.BilledVsApprovedParams.TO_DATE_PARAM,
                DashboardSroredProcParam.BilledVsApprovedParams.MODE_NAMES_PARAM
        };
        QueryParameter queryParameter = DashboardUtil.prepareDashboardFilterStoredProcParam(paramNames, filter);
        return persistentContext.findEntities("BilledVsApprovedDto.getBilledVsApproved", queryParameter);
    }

    public List<BilledVsApprovedDto> getBilledVsApprovedByMonth(DashboardsFilterCriteria filter) {
        String[] paramNames = {DashboardSroredProcParam.BilledVsApprovedParams.DATE_TYPE_PARAM, DashboardSroredProcParam.BilledVsApprovedParams.CONVERTED_CURRENCY_ID_PARAM,
                DashboardSroredProcParam.BilledVsApprovedParams.CUSTOMER_IDS_CSV_PARAM,
                DashboardSroredProcParam.BilledVsApprovedParams.CARRIER_IDS_PARAM, DashboardSroredProcParam.BilledVsApprovedParams.MODES_PARAM,
                DashboardSroredProcParam.BilledVsApprovedParams.SERVICES_PARAM, DashboardSroredProcParam.BilledVsApprovedParams.LANES_PARAM,
                DashboardSroredProcParam.BilledVsApprovedParams.FROM_DATE_PARAM, DashboardSroredProcParam.BilledVsApprovedParams.TO_DATE_PARAM,
                DashboardSroredProcParam.BilledVsApprovedParams.MODE_NAMES_PARAM, DashboardSroredProcParam.BilledVsApprovedParams.BILLED_APPROVED_PARAM
        };
        QueryParameter queryParameter = DashboardUtil.prepareDashboardFilterStoredProcParam(paramNames, filter);
        return persistentContext.findEntities("BilledVsApprovedDto.getBilledVsApprovedByMonth", queryParameter);
    }

    public List<RecoveryAdjustmentDto> getRecoveryAdjustment(DashboardsFilterCriteria filter, boolean isTopTenAccessorial) {
        filter.setTopTenAccessorial(isTopTenAccessorial);
        String[] paramNames = {DashboardSroredProcParam.RecoveryAdjustmentParams.DATE_TYPE_PARAM, DashboardSroredProcParam.RecoveryAdjustmentParams.CONVERTED_CURRENCY_ID_PARAM,
                DashboardSroredProcParam.RecoveryAdjustmentParams.CONVERTED_CURRENCY_CODE_PARAM, DashboardSroredProcParam.RecoveryAdjustmentParams.CUSTOMER_IDS_CSV_PARAM,
                DashboardSroredProcParam.RecoveryAdjustmentParams.CARRIER_IDS_PARAM, DashboardSroredProcParam.RecoveryAdjustmentParams.MODES_PARAM,
                DashboardSroredProcParam.RecoveryAdjustmentParams.SERVICES_PARAM, DashboardSroredProcParam.RecoveryAdjustmentParams.LANES_PARAM,
                DashboardSroredProcParam.RecoveryAdjustmentParams.FROM_DATE_PARAM, DashboardSroredProcParam.RecoveryAdjustmentParams.TO_DATE_PARAM,
                DashboardSroredProcParam.RecoveryAdjustmentParams.ACCESSORIAL_NAME_PARAM, DashboardSroredProcParam.RecoveryAdjustmentParams.TOP_TEN_ACCESSORIAL_PARAM
        };
        QueryParameter queryParameter = DashboardUtil.prepareDashboardFilterStoredProcParam(paramNames, filter);
        return persistentContext.findEntities("RecoveryAdjustmentDto.getRecoveryAdjustment", queryParameter);
    }

    public List<RecoveryAdjustmentDto> getRecoveryAdjustmentByCarrier(DashboardsFilterCriteria filter, boolean isTopTenAccessorial) {
        filter.setTopTenAccessorial(isTopTenAccessorial);
        String[] paramNames = {DashboardSroredProcParam.RecoveryAdjustmentParams.DATE_TYPE_PARAM, DashboardSroredProcParam.RecoveryAdjustmentParams.CONVERTED_CURRENCY_ID_PARAM,
                DashboardSroredProcParam.RecoveryAdjustmentParams.CONVERTED_CURRENCY_CODE_PARAM, DashboardSroredProcParam.RecoveryAdjustmentParams.CUSTOMER_IDS_CSV_PARAM,
                DashboardSroredProcParam.RecoveryAdjustmentParams.CARRIER_IDS_PARAM, DashboardSroredProcParam.RecoveryAdjustmentParams.MODES_PARAM,
                DashboardSroredProcParam.RecoveryAdjustmentParams.SERVICES_PARAM, DashboardSroredProcParam.RecoveryAdjustmentParams.LANES_PARAM,
                DashboardSroredProcParam.RecoveryAdjustmentParams.FROM_DATE_PARAM, DashboardSroredProcParam.RecoveryAdjustmentParams.TO_DATE_PARAM,
                DashboardSroredProcParam.RecoveryAdjustmentParams.ACCESSORIAL_NAME_PARAM, DashboardSroredProcParam.RecoveryAdjustmentParams.TOP_TEN_ACCESSORIAL_PARAM
        };
        QueryParameter queryParameter = DashboardUtil.prepareDashboardFilterStoredProcParam(paramNames, filter);
        return persistentContext.findEntities("RecoveryAdjustmentDto.getRecoveryAdjustmentByCarrier", queryParameter);
    }

    public List<RecoveryAdjustmentDto> getRecoveryAdjustmentByMonth(DashboardsFilterCriteria filter, boolean isTopTenAccessorial) {
        filter.setTopTenAccessorial(isTopTenAccessorial);
        String[] paramNames = {DashboardSroredProcParam.RecoveryAdjustmentParams.DATE_TYPE_PARAM, DashboardSroredProcParam.RecoveryAdjustmentParams.CONVERTED_CURRENCY_ID_PARAM,
                DashboardSroredProcParam.RecoveryAdjustmentParams.CONVERTED_CURRENCY_CODE_PARAM, DashboardSroredProcParam.RecoveryAdjustmentParams.CUSTOMER_IDS_CSV_PARAM,
                DashboardSroredProcParam.RecoveryAdjustmentParams.CARRIER_IDS_PARAM, DashboardSroredProcParam.RecoveryAdjustmentParams.MODES_PARAM,
                DashboardSroredProcParam.RecoveryAdjustmentParams.SERVICES_PARAM, DashboardSroredProcParam.RecoveryAdjustmentParams.LANES_PARAM,
                DashboardSroredProcParam.RecoveryAdjustmentParams.FROM_DATE_PARAM, DashboardSroredProcParam.RecoveryAdjustmentParams.TO_DATE_PARAM,
                DashboardSroredProcParam.RecoveryAdjustmentParams.ACCESSORIAL_NAME_PARAM, DashboardSroredProcParam.RecoveryAdjustmentParams.TOP_TEN_ACCESSORIAL_PARAM
        };
        QueryParameter queryParameter = DashboardUtil.prepareDashboardFilterStoredProcParam(paramNames, filter);
        return persistentContext.findEntities("RecoveryAdjustmentDto.getRecoveryAdjustmentByMonth", queryParameter);
    }

    public List<RecoveryServiceDto> getRecoveryServices(DashboardsFilterCriteria filter, boolean isTopTenAccessorial) {
        filter.setTopTenAccessorial(isTopTenAccessorial);
        String[] paramNames = {DashboardSroredProcParam.RecoveryServiceParams.DATE_TYPE_PARAM, DashboardSroredProcParam.RecoveryServiceParams.CONVERTED_CURRENCY_ID_PARAM,
                DashboardSroredProcParam.RecoveryServiceParams.CONVERTED_CURRENCY_CODE_PARAM, DashboardSroredProcParam.RecoveryServiceParams.CUSTOMER_IDS_CSV_PARAM,
                DashboardSroredProcParam.RecoveryServiceParams.CARRIER_IDS_PARAM, DashboardSroredProcParam.RecoveryServiceParams.MODES_PARAM,
                DashboardSroredProcParam.RecoveryServiceParams.SERVICES_PARAM, DashboardSroredProcParam.RecoveryServiceParams.LANES_PARAM,
                DashboardSroredProcParam.RecoveryServiceParams.FROM_DATE_PARAM, DashboardSroredProcParam.RecoveryServiceParams.TO_DATE_PARAM,
                DashboardSroredProcParam.RecoveryServiceParams.ACCESSORIAL_NAME_PARAM, DashboardSroredProcParam.RecoveryServiceParams.MODE_NAMES_PARAM,
                DashboardSroredProcParam.RecoveryServiceParams.TOP_TEN_ACCESSORIAL_PARAM
        };
        QueryParameter queryParameter = DashboardUtil.prepareDashboardFilterStoredProcParam(paramNames, filter);
        return persistentContext.findEntities("RecoveryServiceDto.getRecoveryService", queryParameter);
    }

    public List<RecoveryServiceDto> getRecoveryServicesByMonth(DashboardsFilterCriteria filter, boolean isTopTenAccessorial) {
        filter.setTopTenAccessorial(isTopTenAccessorial);
        String[] paramNames = {DashboardSroredProcParam.RecoveryServiceParams.DATE_TYPE_PARAM, DashboardSroredProcParam.RecoveryServiceParams.CONVERTED_CURRENCY_ID_PARAM,
                DashboardSroredProcParam.RecoveryServiceParams.CONVERTED_CURRENCY_CODE_PARAM, DashboardSroredProcParam.RecoveryServiceParams.CUSTOMER_IDS_CSV_PARAM,
                DashboardSroredProcParam.RecoveryServiceParams.CARRIER_IDS_PARAM, DashboardSroredProcParam.RecoveryServiceParams.MODES_PARAM,
                DashboardSroredProcParam.RecoveryServiceParams.SERVICES_PARAM, DashboardSroredProcParam.RecoveryServiceParams.LANES_PARAM,
                DashboardSroredProcParam.RecoveryServiceParams.FROM_DATE_PARAM, DashboardSroredProcParam.RecoveryServiceParams.TO_DATE_PARAM,
                DashboardSroredProcParam.RecoveryServiceParams.ACCESSORIAL_NAME_PARAM, DashboardSroredProcParam.RecoveryServiceParams.MODE_NAMES_PARAM,
                DashboardSroredProcParam.RecoveryServiceParams.TOP_TEN_ACCESSORIAL_PARAM, DashboardSroredProcParam.RecoveryServiceParams.SERVICE_PARAM
        };
        QueryParameter queryParameter = DashboardUtil.prepareDashboardFilterStoredProcParam(paramNames, filter);
        return persistentContext.findEntities("RecoveryServiceDto.getRecoveryServiceByMonth", queryParameter);
    }

    public List<PackageExceptionDto> getPackageExceptions(DashboardsFilterCriteria filter, boolean isTopTenAccessorial) {
        filter.setTopTenAccessorial(isTopTenAccessorial);
        String[] paramNames = {DashboardSroredProcParam.PackageExceptionParams.DATE_TYPE_PARAM, DashboardSroredProcParam.PackageExceptionParams.CUSTOMER_IDS_CSV_PARAM,
                DashboardSroredProcParam.PackageExceptionParams.CARRIER_IDS_PARAM, DashboardSroredProcParam.PackageExceptionParams.MODES_PARAM,
                DashboardSroredProcParam.PackageExceptionParams.SERVICES_PARAM, DashboardSroredProcParam.PackageExceptionParams.LANES_PARAM,
                DashboardSroredProcParam.PackageExceptionParams.FROM_DATE_PARAM, DashboardSroredProcParam.PackageExceptionParams.TO_DATE_PARAM,
                DashboardSroredProcParam.PackageExceptionParams.ACCESSORIAL_NAME_PARAM, DashboardSroredProcParam.PackageExceptionParams.TOP_TEN_ACCESSORIAL_PARAM
        };
        QueryParameter queryParameter = DashboardUtil.prepareDashboardFilterStoredProcParam(paramNames, filter);
        return persistentContext.findEntities("PackageExceptionDto.getPackageException", queryParameter);
    }

    @Transactional(readOnly = true)
    public List<ShipmentRegionDto> getShipmentByRegion(DashboardsFilterCriteria filter) {
        QueryParameter queryParameter = StoredProcedureParameter.with(DashboardSroredProcParam.ShipmentRegionParams.DATE_TYPE_PARAM, filter.getDateType())
                .and(DashboardSroredProcParam.ShipmentRegionParams.CUSTOMER_IDS_CSV_PARAM, filter.getCustomerIdsCSV())
                .and(DashboardSroredProcParam.ShipmentRegionParams.CARRIER_ID_PARAM, filter.getCarriers())
                .and(DashboardSroredProcParam.ShipmentRegionParams.MODES_PARAM, filter.getModes())
                .and(DashboardSroredProcParam.ShipmentRegionParams.SERVICES_PARAM, filter.getServices())
                .and(DashboardSroredProcParam.ShipmentRegionParams.LANES_PARAM, filter.getLanes())
                .and(DashboardSroredProcParam.ShipmentRegionParams.ACCESSORIAL_NAME_PARAM, filter.getAccessorialName())
                .and(DashboardSroredProcParam.ShipmentRegionParams.FROM_DATE_PARAM, filter.getFromDate())
                .and(DashboardSroredProcParam.ShipmentRegionParams.TO_DATE_PARAM, filter.getToDate())
                .and(DashboardSroredProcParam.ShipmentRegionParams.NO_OF_LANES_PARAM, filter.getNoOfLanes());

        return persistentContext.findEntities("ShipmentRegionDto.getShipmentByRegion", queryParameter);
    }

    public List<MapCoordinatesDto> getMapCoordinates(String address) {
        return persistentContext.findEntities("MapCoordinatesDto.getMapCooridantes",
                StoredProcedureParameter.with("p_address", address));
    }

    public List<ZipCodesTimeZonesDto> getMapCoordinates(String city, String state) {
        return persistentContext.findEntities("ZipCodesTimeZonesDto.getMapCooridantes",
                StoredProcedureParameter.with("p_city", city)
                        .and("p_state", state));
    }

    public void insertMapCoordinates(MapCoordinatesDto mapCoordinatesDto) {
        persistentContext.findEntities("MapCoordinatesDto.insertMapCooridantes",
                StoredProcedureParameter.with("p_address", mapCoordinatesDto.getAddress())
                        .and("p_latitude", mapCoordinatesDto.getLatitude())
                        .and("p_longitude", mapCoordinatesDto.getLongitude()));
    }

    public List<ShipmentRegionDto> getShipmentRegionByCarrierJson(DashboardsFilterCriteria filterCriteria) {
        QueryParameter queryParameter = StoredProcedureParameter.with(DashboardSroredProcParam.ShipmentRegionParams.DATE_TYPE_PARAM, filterCriteria.getDateType())
                .and(DashboardSroredProcParam.ShipmentRegionParams.CURRENCY_ID_PARAM, filterCriteria.getConvertCurrencyId())
                .and(DashboardSroredProcParam.ShipmentRegionParams.CURRENCY_CODE_PARAM, filterCriteria.getConvertCurrencyCode())
                .and(DashboardSroredProcParam.ShipmentRegionParams.CUSTOMER_IDS_CSV_PARAM, filterCriteria.getCustomerIdsCSV())
                .and(DashboardSroredProcParam.ShipmentRegionParams.CARRIER_ID_PARAM, filterCriteria.getCarriers())
                .and(DashboardSroredProcParam.ShipmentRegionParams.MODES_PARAM, filterCriteria.getModes())
                .and(DashboardSroredProcParam.ShipmentRegionParams.SERVICES_PARAM, filterCriteria.getServices())
                .and(DashboardSroredProcParam.ShipmentRegionParams.LANES_PARAM, filterCriteria.getLanes())
                .and(DashboardSroredProcParam.ShipmentRegionParams.ACCESSORIAL_NAME_PARAM, filterCriteria.getAccessorialName())
                .and(DashboardSroredProcParam.ShipmentRegionParams.FROM_DATE_PARAM, filterCriteria.getFromDate())
                .and(DashboardSroredProcParam.ShipmentRegionParams.TO_DATE_PARAM, filterCriteria.getToDate())
                .and(DashboardSroredProcParam.ShipmentRegionParams.SHIPPER_CITY, filterCriteria.getShipperCity())
                .and(DashboardSroredProcParam.ShipmentRegionParams.RECEIVER_CITY, filterCriteria.getReceiverCity());

        return persistentContext.findEntities("ShipmentRegionDto.getShipmentRegionByCarrier", queryParameter);

    }

    public List<ShipmentRegionDto> getShipmentRegionByMonthJson(DashboardsFilterCriteria filterCriteria) {
        QueryParameter queryParameter = StoredProcedureParameter.with(DashboardSroredProcParam.ShipmentRegionParams.DATE_TYPE_PARAM, filterCriteria.getDateType())
                .and(DashboardSroredProcParam.ShipmentRegionParams.CUSTOMER_IDS_CSV_PARAM, filterCriteria.getCustomerIdsCSV())
                .and(DashboardSroredProcParam.ShipmentRegionParams.CURRENCY_ID_PARAM, filterCriteria.getConvertCurrencyId())
                .and(DashboardSroredProcParam.ShipmentRegionParams.CURRENCY_CODE_PARAM, filterCriteria.getConvertCurrencyCode())
                .and(DashboardSroredProcParam.ShipmentRegionParams.CARRIER_ID_PARAM, filterCriteria.getCarriers())
                .and(DashboardSroredProcParam.ShipmentRegionParams.MODES_PARAM, filterCriteria.getModes())
                .and(DashboardSroredProcParam.ShipmentRegionParams.SERVICES_PARAM, filterCriteria.getServices())
                .and(DashboardSroredProcParam.ShipmentRegionParams.LANES_PARAM, filterCriteria.getLanes())
                .and(DashboardSroredProcParam.ShipmentRegionParams.ACCESSORIAL_NAME_PARAM, filterCriteria.getAccessorialName())
                .and(DashboardSroredProcParam.ShipmentRegionParams.FROM_DATE_PARAM, filterCriteria.getFromDate())
                .and(DashboardSroredProcParam.ShipmentRegionParams.TO_DATE_PARAM, filterCriteria.getToDate())
                .and(DashboardSroredProcParam.ShipmentRegionParams.SHIPPER_CITY, filterCriteria.getShipperCity())
                .and(DashboardSroredProcParam.ShipmentRegionParams.RECEIVER_CITY, filterCriteria.getReceiverCity());

        return persistentContext.findEntities("ShipmentRegionDto.getShipmentRegionByMonth", queryParameter);

    }

    public List<ShippingLanesDto> getTopShippingLanesJsonData(DashboardsFilterCriteria filter) {
        QueryParameter queryParameter = StoredProcedureParameter.with(DashboardSroredProcParam.ShippingLanesParams.DATE_TYPE_PARAM, filter.getDateType())
                .and(DashboardSroredProcParam.ShippingLanesParams.CURRENCY_ID_PARAM, filter.getConvertCurrencyId())
                .and(DashboardSroredProcParam.ShippingLanesParams.CUSTOMER_IDS_CSV_PARAM, filter.getCustomerIdsCSV())
                .and(DashboardSroredProcParam.ShippingLanesParams.CARRIER_ID_PARAM, filter.getCarriers())
                .and(DashboardSroredProcParam.ShippingLanesParams.MODES_PARAM, filter.getModes())
                .and(DashboardSroredProcParam.ShippingLanesParams.SERVICES_PARAM, filter.getServices())
                .and(DashboardSroredProcParam.ShippingLanesParams.LANES_PARAM, filter.getLanes())
                .and(DashboardSroredProcParam.ShippingLanesParams.FROM_DATE_PARAM, filter.getFromDate())
                .and(DashboardSroredProcParam.ShippingLanesParams.TO_DATE_PARAM, filter.getToDate());

        return persistentContext.findEntities("ShippingLanesDto.getTopShippingLanes", queryParameter);
    }

    public List<ShippingLanesDto> getShippingLanesByCarrierJson(DashboardsFilterCriteria filter) {
        QueryParameter queryParameter = StoredProcedureParameter.with(DashboardSroredProcParam.ShippingLanesParams.DATE_TYPE_PARAM, filter.getDateType())
                .and(DashboardSroredProcParam.ShippingLanesParams.CURRENCY_ID_PARAM, filter.getConvertCurrencyId())
                .and(DashboardSroredProcParam.ShippingLanesParams.CUSTOMER_IDS_CSV_PARAM, filter.getCustomerIdsCSV())
                .and(DashboardSroredProcParam.ShippingLanesParams.CARRIER_ID_PARAM, filter.getCarriers())
                .and(DashboardSroredProcParam.ShippingLanesParams.MODES_PARAM, filter.getModes())
                .and(DashboardSroredProcParam.ShippingLanesParams.SERVICES_PARAM, filter.getServices())
                .and(DashboardSroredProcParam.ShippingLanesParams.LANES_PARAM, filter.getLanes())
                .and(DashboardSroredProcParam.ShippingLanesParams.FROM_DATE_PARAM, filter.getFromDate())
                .and(DashboardSroredProcParam.ShippingLanesParams.TO_DATE_PARAM, filter.getToDate())
                .and(DashboardSroredProcParam.ShippingLanesParams.SHIPPER_CITY, filter.getShipperAddress().split(",")[0].replace("'", "''"))
                .and(DashboardSroredProcParam.ShippingLanesParams.SHIPPER_STATE, filter.getShipperAddress().split(",")[1].replace("'", "''"))
                .and(DashboardSroredProcParam.ShippingLanesParams.SHIPPER_COUNTRY, filter.getShipperAddress().split(",")[2].replace("'", "''"))
                .and(DashboardSroredProcParam.ShippingLanesParams.RECEIVER_CITY, filter.getReceiverAddress().split(",")[0].replace("'", "''"))
                .and(DashboardSroredProcParam.ShippingLanesParams.RECEIVER_STATE, filter.getReceiverAddress().split(",")[1].replace("'", "''"))
                .and(DashboardSroredProcParam.ShippingLanesParams.RECEIVER_COUNTRY, filter.getReceiverAddress().split(",")[2].replace("'", "''"));

        return persistentContext.findEntities("ShippingLanesDto.getShippingLanesByCarrier", queryParameter);
    }

    public List<ShippingLanesDto> getShippingLanesByMonthJson(DashboardsFilterCriteria filter) {
        QueryParameter queryParameter = StoredProcedureParameter.with(DashboardSroredProcParam.ShippingLanesParams.DATE_TYPE_PARAM, filter.getDateType())
                .and(DashboardSroredProcParam.ShippingLanesParams.CURRENCY_ID_PARAM, filter.getConvertCurrencyId())
                .and(DashboardSroredProcParam.ShippingLanesParams.CUSTOMER_IDS_CSV_PARAM, filter.getCustomerIdsCSV())
                .and(DashboardSroredProcParam.ShippingLanesParams.CARRIER_ID_PARAM, filter.getCarriers())
                .and(DashboardSroredProcParam.ShippingLanesParams.MODES_PARAM, filter.getModes())
                .and(DashboardSroredProcParam.ShippingLanesParams.SERVICES_PARAM, filter.getServices())
                .and(DashboardSroredProcParam.ShippingLanesParams.LANES_PARAM, filter.getLanes())
                .and(DashboardSroredProcParam.ShippingLanesParams.FROM_DATE_PARAM, filter.getFromDate())
                .and(DashboardSroredProcParam.ShippingLanesParams.TO_DATE_PARAM, filter.getToDate())
                .and(DashboardSroredProcParam.ShippingLanesParams.SHIPPER_CITY, filter.getShipperAddress().split(",")[0].replace("'", "''"))
                .and(DashboardSroredProcParam.ShippingLanesParams.SHIPPER_STATE, filter.getShipperAddress().split(",")[1].replace("'", "''"))
                .and(DashboardSroredProcParam.ShippingLanesParams.SHIPPER_COUNTRY, filter.getShipperAddress().split(",")[2].replace("'", "''"))
                .and(DashboardSroredProcParam.ShippingLanesParams.RECEIVER_CITY, filter.getReceiverAddress().split(",")[0].replace("'", "''"))
                .and(DashboardSroredProcParam.ShippingLanesParams.RECEIVER_STATE, filter.getReceiverAddress().split(",")[1].replace("'", "''"))
                .and(DashboardSroredProcParam.ShippingLanesParams.RECEIVER_COUNTRY, filter.getReceiverAddress().split(",")[2].replace("'", "''"));

        return persistentContext.findEntities("ShippingLanesDto.getShippingLanesByMonth", queryParameter);
    }

    public List<PortLanesDto> getTopPortLanesJsonData(DashboardsFilterCriteria filter) {
        QueryParameter queryParameter = StoredProcedureParameter.with(DashboardSroredProcParam.PortLanesParams.DATE_TYPE_PARAM, filter.getDateType())
                .and(DashboardSroredProcParam.PortLanesParams.CURRENCY_ID_PARAM, filter.getConvertCurrencyId())
                .and(DashboardSroredProcParam.PortLanesParams.CUSTOMER_IDS_CSV_PARAM, filter.getCustomerIdsCSV())
                .and(DashboardSroredProcParam.PortLanesParams.CARRIER_ID_PARAM, filter.getCarriers())
                .and(DashboardSroredProcParam.PortLanesParams.MODES_PARAM, filter.getModes())
                .and(DashboardSroredProcParam.PortLanesParams.SERVICES_PARAM, filter.getServices())
                .and(DashboardSroredProcParam.PortLanesParams.LANES_PARAM, filter.getLanes())
                .and(DashboardSroredProcParam.PortLanesParams.FROM_DATE_PARAM, filter.getFromDate())
                .and(DashboardSroredProcParam.PortLanesParams.TO_DATE_PARAM, filter.getToDate());

        return persistentContext.findEntities("PortLanesDto.getTopPortLanes", queryParameter);
    }

    public List<PortLanesDto> getPortLanesByCarrierJson(DashboardsFilterCriteria filter) {
        QueryParameter queryParameter = StoredProcedureParameter.with(DashboardSroredProcParam.PortLanesParams.DATE_TYPE_PARAM, filter.getDateType())
                .and(DashboardSroredProcParam.PortLanesParams.CURRENCY_ID_PARAM, filter.getConvertCurrencyId())
                .and(DashboardSroredProcParam.PortLanesParams.CUSTOMER_IDS_CSV_PARAM, filter.getCustomerIdsCSV())
                .and(DashboardSroredProcParam.PortLanesParams.CARRIER_ID_PARAM, filter.getCarriers())
                .and(DashboardSroredProcParam.PortLanesParams.MODES_PARAM, filter.getModes())
                .and(DashboardSroredProcParam.PortLanesParams.SERVICES_PARAM, filter.getServices())
                .and(DashboardSroredProcParam.PortLanesParams.LANES_PARAM, filter.getLanes())
                .and(DashboardSroredProcParam.PortLanesParams.FROM_DATE_PARAM, filter.getFromDate())
                .and(DashboardSroredProcParam.PortLanesParams.TO_DATE_PARAM, filter.getToDate())
                .and(DashboardSroredProcParam.PortLanesParams.POL, filter.getPol())
                .and(DashboardSroredProcParam.PortLanesParams.POD, filter.getPod());

        return persistentContext.findEntities("PortLanesDto.getPortLanesByCarrier", queryParameter);
    }

    public List<PortLanesDto> getPortLanesByMonthJson(DashboardsFilterCriteria filter) {
        QueryParameter queryParameter = StoredProcedureParameter.with(DashboardSroredProcParam.PortLanesParams.DATE_TYPE_PARAM, filter.getDateType())
                .and(DashboardSroredProcParam.PortLanesParams.CURRENCY_ID_PARAM, filter.getConvertCurrencyId())
                .and(DashboardSroredProcParam.PortLanesParams.CUSTOMER_IDS_CSV_PARAM, filter.getCustomerIdsCSV())
                .and(DashboardSroredProcParam.PortLanesParams.CARRIER_ID_PARAM, filter.getCarriers())
                .and(DashboardSroredProcParam.PortLanesParams.MODES_PARAM, filter.getModes())
                .and(DashboardSroredProcParam.PortLanesParams.SERVICES_PARAM, filter.getServices())
                .and(DashboardSroredProcParam.PortLanesParams.LANES_PARAM, filter.getLanes())
                .and(DashboardSroredProcParam.PortLanesParams.FROM_DATE_PARAM, filter.getFromDate())
                .and(DashboardSroredProcParam.PortLanesParams.TO_DATE_PARAM, filter.getToDate())
                .and(DashboardSroredProcParam.PortLanesParams.POL, filter.getPol())
                .and(DashboardSroredProcParam.PortLanesParams.POD, filter.getPod());

        return persistentContext.findEntities("PortLanesDto.getPortLanesByMonth", queryParameter);
    }

    public List<PackageExceptionDto> getPackageExceptionsByCarrier(DashboardsFilterCriteria filter, boolean isTopTenAccessorial) {
        filter.setTopTenAccessorial(isTopTenAccessorial);
        String[] paramNames = {DashboardSroredProcParam.PackageExceptionParams.DATE_TYPE_PARAM, DashboardSroredProcParam.PackageExceptionParams.CUSTOMER_IDS_CSV_PARAM,
                DashboardSroredProcParam.PackageExceptionParams.CARRIER_IDS_PARAM, DashboardSroredProcParam.PackageExceptionParams.MODES_PARAM,
                DashboardSroredProcParam.PackageExceptionParams.SERVICES_PARAM, DashboardSroredProcParam.PackageExceptionParams.LANES_PARAM,
                DashboardSroredProcParam.PackageExceptionParams.FROM_DATE_PARAM, DashboardSroredProcParam.PackageExceptionParams.TO_DATE_PARAM,
                DashboardSroredProcParam.PackageExceptionParams.ACCESSORIAL_NAME_PARAM, DashboardSroredProcParam.PackageExceptionParams.TOP_TEN_ACCESSORIAL_PARAM,
                DashboardSroredProcParam.PackageExceptionParams.DELIVERY_FLAG_PARAM
        };
        QueryParameter queryParameter = DashboardUtil.prepareDashboardFilterStoredProcParam(paramNames, filter);
        return persistentContext.findEntities("PackageExceptionDto.getPackageExceptionByCarrier", queryParameter);
    }

    public List<PackageExceptionDto> getPackageExceptionsByMonth(DashboardsFilterCriteria filter, boolean isTopTenAccessorial) {
        filter.setTopTenAccessorial(isTopTenAccessorial);
        String[] paramNames = {DashboardSroredProcParam.PackageExceptionParams.DATE_TYPE_PARAM, DashboardSroredProcParam.PackageExceptionParams.CUSTOMER_IDS_CSV_PARAM,
                DashboardSroredProcParam.PackageExceptionParams.CARRIER_IDS_PARAM, DashboardSroredProcParam.PackageExceptionParams.MODES_PARAM,
                DashboardSroredProcParam.PackageExceptionParams.SERVICES_PARAM, DashboardSroredProcParam.PackageExceptionParams.LANES_PARAM,
                DashboardSroredProcParam.PackageExceptionParams.FROM_DATE_PARAM, DashboardSroredProcParam.PackageExceptionParams.TO_DATE_PARAM,
                DashboardSroredProcParam.PackageExceptionParams.ACCESSORIAL_NAME_PARAM, DashboardSroredProcParam.PackageExceptionParams.TOP_TEN_ACCESSORIAL_PARAM,
                DashboardSroredProcParam.PackageExceptionParams.DELIVERY_FLAG_PARAM
        };
        QueryParameter queryParameter = DashboardUtil.prepareDashboardFilterStoredProcParam(paramNames, filter);
        return persistentContext.findEntities("PackageExceptionDto.getPackageExceptionByMonth", queryParameter);
    }

    /**
     * @param filter
     * @param isTopTenAccessorial
     * @return
     */
    public List<AverageSpendPerShipmentByCarrierDto> getAverageSpendPerShipmentByCarrier(DashboardsFilterCriteria filter, boolean isTopTenAccessorial) {
        QueryParameter queryParameter = StoredProcedureParameter.with(DashboardSroredProcParam.AverageSpendShipmentByCarrierParam.DATE_TYPE_PARAM, filter.getDateType())
                .and(DashboardSroredProcParam.AverageSpendShipmentByCarrierParam.CONVERTED_CURRENCY_ID_PARAM, filter.getConvertCurrencyId())
                .and(DashboardSroredProcParam.AverageSpendShipmentByCarrierParam.CONVERTED_CURRENCY_CODE_PARAM, filter.getConvertCurrencyCode())
                .and(DashboardSroredProcParam.AverageSpendShipmentByCarrierParam.MODES_PARAM, filter.getModes())
                .and(DashboardSroredProcParam.AverageSpendShipmentByCarrierParam.SERVICES_PARAM, filter.getServices())
                .and(DashboardSroredProcParam.AverageSpendShipmentByCarrierParam.ACCESSORIAL_NAME_PARAM, filter.getAccessorialName())
                .and(DashboardSroredProcParam.AverageSpendShipmentByCarrierParam.LANES_PARAM, filter.getLanes())
                .and(DashboardSroredProcParam.AverageSpendShipmentByCarrierParam.FROM_DATE_PARAM, filter.getFromDate())
                .and(DashboardSroredProcParam.AverageSpendShipmentByCarrierParam.TO_DATE_PARAM, filter.getToDate())
                .and(DashboardSroredProcParam.AverageSpendShipmentByCarrierParam.CARRIER_ID_PARAM, filter.getCarriers())
                .and(DashboardSroredProcParam.AverageSpendShipmentByCarrierParam.CUSTOMER_IDS_CSV_PARAM, filter.getCustomerIdsCSV())
                .and(DashboardSroredProcParam.AverageSpendShipmentByCarrierParam.ORIGINAL_FROM_DATE_PARAM, filter.getOriginalFromDate())
                .and(DashboardSroredProcParam.AverageSpendShipmentByCarrierParam.ORIGINAL_TO_DATE_PARAM, filter.getOriginalToDate())
                .and(DashboardSroredProcParam.AverageSpendShipmentByCarrierParam.TOP_ACCESSORIAL_SPEND_PARAM, isTopTenAccessorial ? 1L : 0L);

        return persistentContext.findEntities("AverageSpendPerShipmentByCarrierDto.getAverageSpendPerShipmentByCarrier", queryParameter);
    }

    /**
     * @param filter
     * @param isTopTenAccessorial
     * @return
     */
    public List<AverageSpendPerShipmentByMonthDto> getAverageSpendPerShipmentByMonth(DashboardsFilterCriteria filter, boolean isTopTenAccessorial) {
        QueryParameter queryParameter = StoredProcedureParameter.with(DashboardSroredProcParam.AverageSpendShipmentByMonthParam.DATE_TYPE_PARAM, filter.getDateType())
                .and(DashboardSroredProcParam.AverageSpendShipmentByMonthParam.CONVERTED_CURRENCY_ID_PARAM, filter.getConvertCurrencyId())
                .and(DashboardSroredProcParam.AverageSpendShipmentByMonthParam.CONVERTED_CURRENCY_CODE_PARAM, filter.getConvertCurrencyCode())
                .and(DashboardSroredProcParam.AverageSpendShipmentByMonthParam.MODES_PARAM, filter.getModes())
                .and(DashboardSroredProcParam.AverageSpendShipmentByMonthParam.SERVICES_PARAM, filter.getServices())
                .and(DashboardSroredProcParam.AverageSpendShipmentByMonthParam.ACCESSORIAL_NAME_PARAM, filter.getAccessorialName())
                .and(DashboardSroredProcParam.AverageSpendShipmentByMonthParam.LANES_PARAM, filter.getLanes())
                .and(DashboardSroredProcParam.AverageSpendShipmentByMonthParam.FROM_DATE_PARAM, filter.getFromDate())
                .and(DashboardSroredProcParam.AverageSpendShipmentByMonthParam.TO_DATE_PARAM, filter.getToDate())
                .and(DashboardSroredProcParam.AverageSpendShipmentByMonthParam.CARRIER_ID_PARAM, filter.getCarriers())
                .and(DashboardSroredProcParam.AverageSpendShipmentByMonthParam.CUSTOMER_IDS_CSV_PARAM, filter.getCustomerIdsCSV())
                .and(DashboardSroredProcParam.AverageSpendShipmentByMonthParam.TOP_ACCESSORIAL_SPEND_PARAM, isTopTenAccessorial ? 1L : 0L);

        return persistentContext.findEntities("AverageSpendPerShipmentByMonthDto.getAverageSpendPerShipmentByMonth", queryParameter);
    }

    /**
     * Method to get Annual summary.
     * @param filter
     * @param isTopTenAccessorial
     * @return
     */
    public List<AnnualSummaryDto> getAnnualSummary(DashboardsFilterCriteria filter, boolean isTopTenAccessorial) {
        filter.setTopTenAccessorial(isTopTenAccessorial);
        String[] paramNames = {DashboardSroredProcParam.AnnualSummaryParams.DATE_TYPE_PARAM, DashboardSroredProcParam.AnnualSummaryParams.CONVERTED_CURRENCY_ID_PARAM,
                DashboardSroredProcParam.AnnualSummaryParams.CONVERTED_CURRENCY_CODE_PARAM, DashboardSroredProcParam.AnnualSummaryParams.CUSTOMER_IDS_CSV_PARAM,
                DashboardSroredProcParam.AnnualSummaryParams.CARRIER_IDS_PARAM, DashboardSroredProcParam.AnnualSummaryParams.MODES_PARAM,
                DashboardSroredProcParam.AnnualSummaryParams.SERVICES_PARAM, DashboardSroredProcParam.AnnualSummaryParams.LANES_PARAM,
                DashboardSroredProcParam.AnnualSummaryParams.FROM_DATE_PARAM, DashboardSroredProcParam.AnnualSummaryParams.TO_DATE_PARAM,
                DashboardSroredProcParam.AnnualSummaryParams.ACCESSORIAL_NAME_PARAM, DashboardSroredProcParam.AnnualSummaryParams.TOP_TEN_ACCESSORIAL_PARAM
        };
        QueryParameter queryParameter = DashboardUtil.prepareDashboardFilterStoredProcParam(paramNames, filter);
        return persistentContext.findEntities(AnnualSummaryDto.Config.StoredProcedureQueryName.ANNUAL_SUMMARY, queryParameter);
    }

    /**
     * Method to get Annual sumary by service.
     * @param filter
     * @param isTopTenAccessorial
     * @return
     */
    public List<AnnualSummaryDto> getAnnualSummaryByService(DashboardsFilterCriteria filter, boolean isTopTenAccessorial) {
        filter.setTopTenAccessorial(isTopTenAccessorial);
        String[] paramNames = {DashboardSroredProcParam.AnnualSummaryParams.DATE_TYPE_PARAM, DashboardSroredProcParam.AnnualSummaryParams.CONVERTED_CURRENCY_ID_PARAM,
                DashboardSroredProcParam.AnnualSummaryParams.CONVERTED_CURRENCY_CODE_PARAM, DashboardSroredProcParam.AnnualSummaryParams.CUSTOMER_IDS_CSV_PARAM,
                DashboardSroredProcParam.AnnualSummaryParams.CARRIER_IDS_PARAM, DashboardSroredProcParam.AnnualSummaryParams.MODES_PARAM,
                DashboardSroredProcParam.AnnualSummaryParams.SERVICES_PARAM, DashboardSroredProcParam.AnnualSummaryParams.LANES_PARAM,
                DashboardSroredProcParam.AnnualSummaryParams.FROM_DATE_PARAM, DashboardSroredProcParam.AnnualSummaryParams.TO_DATE_PARAM,
                DashboardSroredProcParam.AnnualSummaryParams.ACCESSORIAL_NAME_PARAM, DashboardSroredProcParam.AnnualSummaryParams.TOP_TEN_ACCESSORIAL_PARAM,
                DashboardSroredProcParam.AnnualSummaryParams.SERVICE_PARAM
        };
        QueryParameter queryParameter = DashboardUtil.prepareDashboardFilterStoredProcParam(paramNames, filter);
        return persistentContext.findEntities(AnnualSummaryDto.Config.StoredProcedureQueryName.ANNUAL_SUMMARY_BY_SERVICE, queryParameter);
    }

    /**
     * Method to get Annual summary by carrier.
     * @param filter
     * @param isTopTenAccessorial
     * @return
     */
    public List<AnnualSummaryDto> getAnnualSummaryByCarrier(DashboardsFilterCriteria filter, boolean isTopTenAccessorial) {
        filter.setTopTenAccessorial(isTopTenAccessorial);
        String[] paramNames = {DashboardSroredProcParam.AnnualSummaryParams.DATE_TYPE_PARAM, DashboardSroredProcParam.AnnualSummaryParams.CONVERTED_CURRENCY_ID_PARAM,
                DashboardSroredProcParam.AnnualSummaryParams.CONVERTED_CURRENCY_CODE_PARAM, DashboardSroredProcParam.AnnualSummaryParams.CUSTOMER_IDS_CSV_PARAM,
                DashboardSroredProcParam.AnnualSummaryParams.CARRIER_IDS_PARAM, DashboardSroredProcParam.AnnualSummaryParams.MODES_PARAM,
                DashboardSroredProcParam.AnnualSummaryParams.SERVICES_PARAM, DashboardSroredProcParam.AnnualSummaryParams.LANES_PARAM,
                DashboardSroredProcParam.AnnualSummaryParams.FROM_DATE_PARAM, DashboardSroredProcParam.AnnualSummaryParams.TO_DATE_PARAM,
                DashboardSroredProcParam.AnnualSummaryParams.ACCESSORIAL_NAME_PARAM, DashboardSroredProcParam.AnnualSummaryParams.TOP_TEN_ACCESSORIAL_PARAM,
                DashboardSroredProcParam.AnnualSummaryParams.SERVICE_PARAM, DashboardSroredProcParam.AnnualSummaryParams.MODE_NAMES_PARAM
        };
        QueryParameter queryParameter = DashboardUtil.prepareDashboardFilterStoredProcParam(paramNames, filter);
        return persistentContext.findEntities(AnnualSummaryDto.Config.StoredProcedureQueryName.ANNUAL_SUMMARY_BY_CARRIER, queryParameter);
    }

    /**
     * Method to get Annual summary by month.
     * @param filter
     * @param isTopTenAccessorial
     * @return
     */
    public List<AnnualSummaryDto> getAnnualSummaryByMonth(DashboardsFilterCriteria filter, boolean isTopTenAccessorial) {
        filter.setTopTenAccessorial(isTopTenAccessorial);
        String[] paramNames = {DashboardSroredProcParam.AnnualSummaryParams.DATE_TYPE_PARAM, DashboardSroredProcParam.AnnualSummaryParams.CONVERTED_CURRENCY_ID_PARAM,
                DashboardSroredProcParam.AnnualSummaryParams.CONVERTED_CURRENCY_CODE_PARAM, DashboardSroredProcParam.AnnualSummaryParams.CUSTOMER_IDS_CSV_PARAM,
                DashboardSroredProcParam.AnnualSummaryParams.CARRIER_IDS_PARAM, DashboardSroredProcParam.AnnualSummaryParams.MODES_PARAM,
                DashboardSroredProcParam.AnnualSummaryParams.SERVICES_PARAM, DashboardSroredProcParam.AnnualSummaryParams.LANES_PARAM,
                DashboardSroredProcParam.AnnualSummaryParams.FROM_DATE_PARAM, DashboardSroredProcParam.AnnualSummaryParams.TO_DATE_PARAM,
                DashboardSroredProcParam.AnnualSummaryParams.ACCESSORIAL_NAME_PARAM, DashboardSroredProcParam.AnnualSummaryParams.TOP_TEN_ACCESSORIAL_PARAM,
                DashboardSroredProcParam.AnnualSummaryParams.SERVICE_PARAM, DashboardSroredProcParam.AnnualSummaryParams.MODE_NAMES_PARAM
        };
        QueryParameter queryParameter = DashboardUtil.prepareDashboardFilterStoredProcParam(paramNames, filter);
        return persistentContext.findEntities(AnnualSummaryDto.Config.StoredProcedureQueryName.ANNUAL_SUMMARY_BY_MONTH, queryParameter);
    }

    /**
     * Method to get monthly spend by mode.
     * @param filter
     * @param isTopTenAccessorial
     * @return
     */
    public List<MonthlySpendByModeDto> getMonthlySpendByMode(DashboardsFilterCriteria filter, boolean isTopTenAccessorial){
        filter.setTopTenAccessorial(isTopTenAccessorial);
        String[] paramNames = {DashboardSroredProcParam.MonthlySpendByModeParams.DATE_TYPE_PARAM, DashboardSroredProcParam.MonthlySpendByModeParams.CONVERTED_CURRENCY_ID_PARAM,
                DashboardSroredProcParam.MonthlySpendByModeParams.CONVERTED_CURRENCY_CODE_PARAM, DashboardSroredProcParam.MonthlySpendByModeParams.CUSTOMER_IDS_CSV_PARAM,
                DashboardSroredProcParam.MonthlySpendByModeParams.CARRIER_IDS_PARAM, DashboardSroredProcParam.MonthlySpendByModeParams.MODES_PARAM,
                DashboardSroredProcParam.MonthlySpendByModeParams.SERVICES_PARAM, DashboardSroredProcParam.MonthlySpendByModeParams.LANES_PARAM,
                DashboardSroredProcParam.MonthlySpendByModeParams.FROM_DATE_PARAM, DashboardSroredProcParam.MonthlySpendByModeParams.TO_DATE_PARAM,
                DashboardSroredProcParam.MonthlySpendByModeParams.ACCESSORIAL_NAME_PARAM, DashboardSroredProcParam.MonthlySpendByModeParams.TOP_TEN_ACCESSORIAL_PARAM
        };
        QueryParameter queryParameter = DashboardUtil.prepareDashboardFilterStoredProcParam(paramNames, filter);
        return persistentContext.findEntities(MonthlySpendByModeDto.Config.StoredProcedureQueryName.MONTHLY_SPEND_BY_MODE, queryParameter);
    }

    /**
     * Method to get monthly spend by mode by service.
     * @param filter
     * @param isTopTenAccessorial
     * @return
     */
    public List<MonthlySpendByModeDto> getMonthlySpendByModeByService(DashboardsFilterCriteria filter, boolean isTopTenAccessorial){
        filter.setTopTenAccessorial(isTopTenAccessorial);
        String[] paramNames = {DashboardSroredProcParam.MonthlySpendByModeParams.DATE_TYPE_PARAM, DashboardSroredProcParam.MonthlySpendByModeParams.CONVERTED_CURRENCY_ID_PARAM,
                DashboardSroredProcParam.MonthlySpendByModeParams.CONVERTED_CURRENCY_CODE_PARAM, DashboardSroredProcParam.MonthlySpendByModeParams.CUSTOMER_IDS_CSV_PARAM,
                DashboardSroredProcParam.MonthlySpendByModeParams.CARRIER_IDS_PARAM, DashboardSroredProcParam.MonthlySpendByModeParams.MODES_PARAM,
                DashboardSroredProcParam.MonthlySpendByModeParams.SERVICES_PARAM, DashboardSroredProcParam.MonthlySpendByModeParams.LANES_PARAM,
                DashboardSroredProcParam.MonthlySpendByModeParams.FROM_DATE_PARAM, DashboardSroredProcParam.MonthlySpendByModeParams.TO_DATE_PARAM,
                DashboardSroredProcParam.MonthlySpendByModeParams.ACCESSORIAL_NAME_PARAM, DashboardSroredProcParam.MonthlySpendByModeParams.TOP_TEN_ACCESSORIAL_PARAM,
                DashboardSroredProcParam.MonthlySpendByModeParams.SERVICE_PARAM
        };
        QueryParameter queryParameter = DashboardUtil.prepareDashboardFilterStoredProcParam(paramNames, filter);
        return persistentContext.findEntities(MonthlySpendByModeDto.Config.StoredProcedureQueryName.MONTHLY_SPEND_BY_MODE_BY_SERVICE, queryParameter);
    }

    /**
     * Method to get Account summary.
     * @param filter
     * @param isTopTenAccessorial
     * @return
     */
    public List<AccountSummaryDto> getAccountSummary(DashboardsFilterCriteria filter, boolean isTopTenAccessorial){
        filter.setTopTenAccessorial(isTopTenAccessorial);
        String[] paramNames = {DashboardSroredProcParam.AccountSummaryParams.DATE_TYPE_PARAM, DashboardSroredProcParam.AccountSummaryParams.CONVERTED_CURRENCY_ID_PARAM,
                DashboardSroredProcParam.AccountSummaryParams.CONVERTED_CURRENCY_CODE_PARAM, DashboardSroredProcParam.AccountSummaryParams.CUSTOMER_IDS_CSV_PARAM,
                DashboardSroredProcParam.AccountSummaryParams.CARRIER_IDS_PARAM, DashboardSroredProcParam.AccountSummaryParams.MODES_PARAM,
                DashboardSroredProcParam.AccountSummaryParams.SERVICES_PARAM, DashboardSroredProcParam.AccountSummaryParams.LANES_PARAM,
                DashboardSroredProcParam.AccountSummaryParams.FROM_DATE_PARAM, DashboardSroredProcParam.AccountSummaryParams.TO_DATE_PARAM,
                DashboardSroredProcParam.AccountSummaryParams.ACCESSORIAL_NAME_PARAM, DashboardSroredProcParam.AccountSummaryParams.TOP_TEN_ACCESSORIAL_PARAM
        };
        QueryParameter queryParameter = DashboardUtil.prepareDashboardFilterStoredProcParam(paramNames, filter);
        return persistentContext.findEntities(AccountSummaryDto.Config.StoredProcedureQueryName.ACCOUNT_SUMMARY, queryParameter);
    }

    /**
     * Method to get parcel Account summary.
     * @param filter
     * @param isTopTenAccessorial
     * @return
     */
    public List<AccountSummaryDto> getParcelAccountSummary(DashboardsFilterCriteria filter, boolean isTopTenAccessorial){
        filter.setTopTenAccessorial(isTopTenAccessorial);
        String[] paramNames = {DashboardSroredProcParam.AccountSummaryParams.DATE_TYPE_PARAM, DashboardSroredProcParam.AccountSummaryParams.CONVERTED_CURRENCY_ID_PARAM,
                DashboardSroredProcParam.AccountSummaryParams.CONVERTED_CURRENCY_CODE_PARAM, DashboardSroredProcParam.AccountSummaryParams.CUSTOMER_IDS_CSV_PARAM,
                DashboardSroredProcParam.AccountSummaryParams.CARRIER_IDS_PARAM, DashboardSroredProcParam.AccountSummaryParams.MODES_PARAM,
                DashboardSroredProcParam.AccountSummaryParams.SERVICES_PARAM, DashboardSroredProcParam.AccountSummaryParams.LANES_PARAM,
                DashboardSroredProcParam.AccountSummaryParams.FROM_DATE_PARAM, DashboardSroredProcParam.AccountSummaryParams.TO_DATE_PARAM,
                DashboardSroredProcParam.AccountSummaryParams.ACCESSORIAL_NAME_PARAM, DashboardSroredProcParam.AccountSummaryParams.TOP_TEN_ACCESSORIAL_PARAM
        };
        QueryParameter queryParameter = DashboardUtil.prepareDashboardFilterStoredProcParam(paramNames, filter);
        return persistentContext.findEntities(AccountSummaryDto.Config.StoredProcedureQueryName.PARCEL_ACCOUNT_SUMMARY, queryParameter);
    }
}
