package com.envista.msi.api.dao;

import com.envista.msi.api.dao.type.GenericObject;
import com.envista.msi.api.domain.PersistentContext;
import com.envista.msi.api.domain.util.DashboardStoredProcParam;
import com.envista.msi.api.domain.util.DashboardUtil;
import com.envista.msi.api.domain.util.QueryParameter;
import com.envista.msi.api.domain.util.StoredProcedureParameter;
import com.envista.msi.api.web.rest.dto.MapCoordinatesDto;
import com.envista.msi.api.web.rest.dto.ZipCodesTimeZonesDto;
import com.envista.msi.api.web.rest.dto.dashboard.CodeValueDto;
import com.envista.msi.api.web.rest.dto.dashboard.DashboardAppliedFilterDto;
import com.envista.msi.api.web.rest.dto.dashboard.DashboardsFilterCriteria;
import com.envista.msi.api.web.rest.dto.dashboard.annualsummary.AccountSummaryDto;
import com.envista.msi.api.web.rest.dto.dashboard.annualsummary.AnnualSummaryDto;
import com.envista.msi.api.web.rest.dto.dashboard.annualsummary.CarrierWiseMonthlySpendDto;
import com.envista.msi.api.web.rest.dto.dashboard.annualsummary.MonthlySpendByModeDto;
import com.envista.msi.api.web.rest.dto.dashboard.auditactivity.*;
import com.envista.msi.api.web.rest.dto.dashboard.carrierspend.CarrierSpendAnalysisDto;
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
import com.envista.msi.api.web.rest.dto.dashboard.servicelevel.ServiceLevelDto;
import com.envista.msi.api.web.rest.dto.dashboard.shipmentoverview.*;
import com.envista.msi.api.web.rest.dto.reports.ReportCustomerCarrierDto;
import com.envista.msi.api.web.rest.util.WebConstants;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.inject.Inject;
import javax.persistence.ParameterMode;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.*;

/**
 * Created by Sarvesh on 1/19/2017.
 */

@Repository("dashboardsDao")
public class DashboardsDao {

    @Inject
    private PersistentContext persistentContext;

    public DashboardAppliedFilterDto getUserAppliedFilter(Long userId) {
        DashboardAppliedFilterDto appliedFilter = null;
        try{
            appliedFilter = persistentContext.findEntity("DashAppliedFilterTb.getUserAppliedFilter",
                    StoredProcedureParameter.with("p_user_id", userId));
            if(null == appliedFilter){
                throw new NoAppliedFilterFoundException("Applied Filter not Found");
            }
        }catch (Exception e){
            throw new NoAppliedFilterFoundException("Applied Filter not Found", e);
        }
        return appliedFilter;
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
        String[] paramNames = {DashboardStoredProcParam.NetSpendParams.DATE_TYPE_PARAM, DashboardStoredProcParam.NetSpendParams.CONVERTED_CURRENCY_ID_PARAM,
                DashboardStoredProcParam.NetSpendParams.CONVERTED_CURRENCY_CODE_PARAM, DashboardStoredProcParam.NetSpendParams.CUSTOMER_IDS_CSV_PARAM,
                DashboardStoredProcParam.NetSpendParams.CARRIER_IDS_PARAM, DashboardStoredProcParam.NetSpendParams.MODES_PARAM,
                DashboardStoredProcParam.NetSpendParams.SERVICES_PARAM, DashboardStoredProcParam.NetSpendParams.LANES_PARAM,
                DashboardStoredProcParam.NetSpendParams.ACCESSORIAL_NAME_PARAM,
                DashboardStoredProcParam.NetSpendParams.FROM_DATE_PARAM, DashboardStoredProcParam.NetSpendParams.TO_DATE_PARAM,
                DashboardStoredProcParam.NetSpendParams.TOP_TEN_ACCESSORIAL_PARAM
        };
        QueryParameter queryParameter = DashboardUtil.prepareDashboardFilterStoredProcParam(paramNames, filter);
        return persistentContext.findEntities(NetSpendByModeDto.Config.StoredProcedureQueryName.NET_SPEND_BY_MODE, queryParameter);
    }

    /**
     * @param filter
     * @param isTopTenAccessorial
     * @return
     */
    public List<NetSpendOverTimeDto> getNetSpendOverTimeByMonth(DashboardsFilterCriteria filter, boolean isTopTenAccessorial) {
        filter.setTopTenAccessorial(isTopTenAccessorial);
        String[] paramNames = {DashboardStoredProcParam.NetSpendParams.DATE_TYPE_PARAM, DashboardStoredProcParam.NetSpendParams.CONVERTED_CURRENCY_ID_PARAM,
                DashboardStoredProcParam.NetSpendParams.CONVERTED_CURRENCY_CODE_PARAM, DashboardStoredProcParam.NetSpendParams.CUSTOMER_IDS_CSV_PARAM,
                DashboardStoredProcParam.NetSpendParams.CARRIER_IDS_PARAM, DashboardStoredProcParam.NetSpendParams.MODES_PARAM,
                DashboardStoredProcParam.NetSpendParams.SERVICES_PARAM, DashboardStoredProcParam.NetSpendParams.LANES_PARAM,
                DashboardStoredProcParam.NetSpendParams.ACCESSORIAL_NAME_PARAM,
                DashboardStoredProcParam.NetSpendParams.FROM_DATE_PARAM, DashboardStoredProcParam.NetSpendParams.TO_DATE_PARAM,
                DashboardStoredProcParam.NetSpendParams.TOP_TEN_ACCESSORIAL_PARAM
        };
        QueryParameter queryParameter = DashboardUtil.prepareDashboardFilterStoredProcParam(paramNames, filter);
        return persistentContext.findEntities(NetSpendOverTimeDto.Config.StoredProcedureQueryName.NET_SPEND_OVER_TIME_BY_MONTH, queryParameter);
    }

    /**
     * @param filter
     * @param isTopTenAccessorial
     * @return
     */
    public List<NetSpendOverTimeDto> getNetSpendByOverTime(DashboardsFilterCriteria filter, boolean isTopTenAccessorial) {
        filter.setTopTenAccessorial(isTopTenAccessorial);
        String[] paramNames = {DashboardStoredProcParam.NetSpendParams.DATE_TYPE_PARAM, DashboardStoredProcParam.NetSpendParams.CONVERTED_CURRENCY_ID_PARAM,
                DashboardStoredProcParam.NetSpendParams.CONVERTED_CURRENCY_CODE_PARAM, DashboardStoredProcParam.NetSpendParams.CUSTOMER_IDS_CSV_PARAM,
                DashboardStoredProcParam.NetSpendParams.CARRIER_IDS_PARAM, DashboardStoredProcParam.NetSpendParams.MODES_PARAM,
                DashboardStoredProcParam.NetSpendParams.SERVICES_PARAM, DashboardStoredProcParam.NetSpendParams.LANES_PARAM,
                DashboardStoredProcParam.NetSpendParams.ACCESSORIAL_NAME_PARAM,
                DashboardStoredProcParam.NetSpendParams.FROM_DATE_PARAM, DashboardStoredProcParam.NetSpendParams.TO_DATE_PARAM,
                DashboardStoredProcParam.NetSpendParams.TOP_TEN_ACCESSORIAL_PARAM
        };
        QueryParameter queryParameter = DashboardUtil.prepareDashboardFilterStoredProcParam(paramNames, filter);
        return persistentContext.findEntities(NetSpendOverTimeDto.Config.StoredProcedureQueryName.NET_SPEND_OVER_TIME, queryParameter);
    }

    /**
     * @param filter
     * @param isTopTenAccessorial
     * @return
     */
    @Transactional(readOnly = true)
    public List<NetSpendByModeDto> getNetSpendByCarrier(DashboardsFilterCriteria filter, boolean isTopTenAccessorial) {
        filter.setTopTenAccessorial(isTopTenAccessorial);
        String[] paramNames = {DashboardStoredProcParam.NetSpendParams.DATE_TYPE_PARAM, DashboardStoredProcParam.NetSpendParams.CONVERTED_CURRENCY_ID_PARAM,
                DashboardStoredProcParam.NetSpendParams.CONVERTED_CURRENCY_CODE_PARAM, DashboardStoredProcParam.NetSpendParams.CUSTOMER_IDS_CSV_PARAM,
                DashboardStoredProcParam.NetSpendParams.CARRIER_IDS_PARAM, DashboardStoredProcParam.NetSpendParams.MODES_PARAM,
                DashboardStoredProcParam.NetSpendParams.SERVICES_PARAM, DashboardStoredProcParam.NetSpendParams.LANES_PARAM,
                DashboardStoredProcParam.NetSpendParams.ACCESSORIAL_NAME_PARAM,
                DashboardStoredProcParam.NetSpendParams.FROM_DATE_PARAM, DashboardStoredProcParam.NetSpendParams.TO_DATE_PARAM,
                DashboardStoredProcParam.NetSpendParams.TOP_TEN_ACCESSORIAL_PARAM,
                DashboardStoredProcParam.NetSpendParams.MODE_NAMES_PARAM
        };
        QueryParameter queryParameter = DashboardUtil.prepareDashboardFilterStoredProcParam(paramNames, filter);
        return persistentContext.findEntities(NetSpendByModeDto.Config.StoredProcedureQueryName.NET_SPEND_BY_CARRIER, queryParameter);
    }


    @Transactional(readOnly = true)
    public List<NetSpendByModeDto> getOverallSpendByMonth(DashboardsFilterCriteria filter, boolean isTopTenAccessorial) {
        //filter.setTopTenAccessorial(isTopTenAccessorial);
        String[] paramNames = {DashboardStoredProcParam.NetSpendParams.DATE_TYPE_PARAM, DashboardStoredProcParam.NetSpendParams.CONVERTED_CURRENCY_ID_PARAM,
                DashboardStoredProcParam.NetSpendParams.CONVERTED_CURRENCY_CODE_PARAM, DashboardStoredProcParam.NetSpendParams.CUSTOMER_IDS_CSV_PARAM,
                DashboardStoredProcParam.NetSpendParams.CARRIER_IDS_PARAM, DashboardStoredProcParam.NetSpendParams.MODES_PARAM,
                DashboardStoredProcParam.NetSpendParams.SERVICES_PARAM, DashboardStoredProcParam.NetSpendParams.LANES_PARAM,
                DashboardStoredProcParam.NetSpendParams.FROM_DATE_PARAM, DashboardStoredProcParam.NetSpendParams.TO_DATE_PARAM
        };
        QueryParameter queryParameter = DashboardUtil.prepareDashboardFilterStoredProcParam(paramNames, filter);
        return persistentContext.findEntities(NetSpendByModeDto.Config.StoredProcedureQueryName.OVERALL_SPEND_BY_MONTH, queryParameter);
    }


    @Transactional(readOnly = true)
    public List<NetSpendByModeDto> getRelSpendByCarrier(DashboardsFilterCriteria filter, boolean isTopTenAccessorial) {
        //filter.setTopTenAccessorial(isTopTenAccessorial);
        String[] paramNames = {DashboardStoredProcParam.NetSpendParams.DATE_TYPE_PARAM, DashboardStoredProcParam.NetSpendParams.CONVERTED_CURRENCY_ID_PARAM,
                DashboardStoredProcParam.NetSpendParams.CONVERTED_CURRENCY_CODE_PARAM, DashboardStoredProcParam.NetSpendParams.CUSTOMER_IDS_CSV_PARAM,
                DashboardStoredProcParam.NetSpendParams.CARRIER_IDS_PARAM, DashboardStoredProcParam.NetSpendParams.MODES_PARAM,
                DashboardStoredProcParam.NetSpendParams.SERVICES_PARAM, DashboardStoredProcParam.NetSpendParams.LANES_PARAM,
                DashboardStoredProcParam.NetSpendParams.FROM_DATE_PARAM, DashboardStoredProcParam.NetSpendParams.TO_DATE_PARAM
        };
        QueryParameter queryParameter = DashboardUtil.prepareDashboardFilterStoredProcParam(paramNames, filter);
        return persistentContext.findEntities(NetSpendByModeDto.Config.StoredProcedureQueryName.REL_SPEND_BY_CARR, queryParameter);
    }

    @Transactional(readOnly = true)
    public List<ServiceLevelDto> getTotalSpendByService(DashboardsFilterCriteria filter, boolean isTopTenAccessorial) {
        //filter.setTopTenAccessorial(isTopTenAccessorial);
        String[] paramNames = {DashboardStoredProcParam.NetSpendParams.DATE_TYPE_PARAM, DashboardStoredProcParam.NetSpendParams.CONVERTED_CURRENCY_ID_PARAM,
                DashboardStoredProcParam.NetSpendParams.CONVERTED_CURRENCY_CODE_PARAM, DashboardStoredProcParam.NetSpendParams.CUSTOMER_IDS_CSV_PARAM,
                DashboardStoredProcParam.NetSpendParams.CARRIER_IDS_PARAM, DashboardStoredProcParam.NetSpendParams.MODES_PARAM,
                DashboardStoredProcParam.NetSpendParams.SERVICES_PARAM, DashboardStoredProcParam.NetSpendParams.LANES_PARAM,
                DashboardStoredProcParam.NetSpendParams.FROM_DATE_PARAM, DashboardStoredProcParam.NetSpendParams.TO_DATE_PARAM,
                DashboardStoredProcParam.DashboardFilterParams.CONVERTED_WEIGHT_UNIT_PARAM
        };
        QueryParameter queryParameter = DashboardUtil.prepareDashboardFilterStoredProcParam(paramNames, filter);
        return persistentContext.findEntities(ServiceLevelDto.Config.StoredProcedureQueryName.TOTAL_SPEND_SERVICE_LEVEL, queryParameter);
    }

    @Transactional(readOnly = true)
    public List<ServiceLevelDto> getTotalPckgsByService(DashboardsFilterCriteria filter, boolean isTopTenAccessorial) {
        //filter.setTopTenAccessorial(isTopTenAccessorial);
        String[] paramNames = {DashboardStoredProcParam.NetSpendParams.DATE_TYPE_PARAM, DashboardStoredProcParam.NetSpendParams.CONVERTED_CURRENCY_ID_PARAM,
                DashboardStoredProcParam.NetSpendParams.CONVERTED_CURRENCY_CODE_PARAM, DashboardStoredProcParam.NetSpendParams.CUSTOMER_IDS_CSV_PARAM,
                DashboardStoredProcParam.NetSpendParams.CARRIER_IDS_PARAM, DashboardStoredProcParam.NetSpendParams.MODES_PARAM,
                DashboardStoredProcParam.NetSpendParams.SERVICES_PARAM, DashboardStoredProcParam.NetSpendParams.LANES_PARAM,
                DashboardStoredProcParam.NetSpendParams.FROM_DATE_PARAM, DashboardStoredProcParam.NetSpendParams.TO_DATE_PARAM,
                DashboardStoredProcParam.DashboardFilterParams.CONVERTED_WEIGHT_UNIT_PARAM
        };
        QueryParameter queryParameter = DashboardUtil.prepareDashboardFilterStoredProcParam(paramNames, filter);
        return persistentContext.findEntities(ServiceLevelDto.Config.StoredProcedureQueryName.TOTAL_PCKG_SERVICE_LEVEL, queryParameter);
    }

    /**
     * @param filter
     * @param isTopTenAccessorial
     * @return
     */
    public List<NetSpendByModeDto> getNetSpendByMonth(DashboardsFilterCriteria filter, boolean isTopTenAccessorial) {
        filter.setTopTenAccessorial(isTopTenAccessorial);
        String[] paramNames = {DashboardStoredProcParam.NetSpendParams.DATE_TYPE_PARAM, DashboardStoredProcParam.NetSpendParams.CONVERTED_CURRENCY_ID_PARAM,
                DashboardStoredProcParam.NetSpendParams.CONVERTED_CURRENCY_CODE_PARAM, DashboardStoredProcParam.NetSpendParams.CUSTOMER_IDS_CSV_PARAM,
                DashboardStoredProcParam.NetSpendParams.CARRIER_IDS_PARAM, DashboardStoredProcParam.NetSpendParams.MODES_PARAM,
                DashboardStoredProcParam.NetSpendParams.SERVICES_PARAM, DashboardStoredProcParam.NetSpendParams.LANES_PARAM,
                DashboardStoredProcParam.NetSpendParams.ACCESSORIAL_NAME_PARAM,
                DashboardStoredProcParam.NetSpendParams.FROM_DATE_PARAM, DashboardStoredProcParam.NetSpendParams.TO_DATE_PARAM,
                DashboardStoredProcParam.NetSpendParams.TOP_TEN_ACCESSORIAL_PARAM,
                DashboardStoredProcParam.NetSpendParams.MODE_NAMES_PARAM, DashboardStoredProcParam.NetSpendParams.SCORE_TYPE_PARAM
        };
        QueryParameter queryParameter = DashboardUtil.prepareDashboardFilterStoredProcParam(paramNames, filter);
        return persistentContext.findEntities(NetSpendByModeDto.Config.StoredProcedureQueryName.NET_SPEND_BY_MONTH, queryParameter);
    }

    public List<NetSpendByModeDto> getCarrierSpendByMonth(DashboardsFilterCriteria filter, boolean isTopTenAccessorial) {
        filter.setTopTenAccessorial(isTopTenAccessorial);
        String[] paramNames = {DashboardStoredProcParam.NetSpendParams.DATE_TYPE_PARAM, DashboardStoredProcParam.NetSpendParams.CONVERTED_CURRENCY_ID_PARAM,
                DashboardStoredProcParam.NetSpendParams.CONVERTED_CURRENCY_CODE_PARAM, DashboardStoredProcParam.NetSpendParams.CUSTOMER_IDS_CSV_PARAM,
                DashboardStoredProcParam.NetSpendParams.CARRIER_IDS_PARAM, DashboardStoredProcParam.NetSpendParams.MODES_PARAM,
                DashboardStoredProcParam.NetSpendParams.SERVICES_PARAM, DashboardStoredProcParam.NetSpendParams.LANES_PARAM,
                DashboardStoredProcParam.NetSpendParams.ACCESSORIAL_NAME_PARAM,
                DashboardStoredProcParam.NetSpendParams.FROM_DATE_PARAM, DashboardStoredProcParam.NetSpendParams.TO_DATE_PARAM,
                DashboardStoredProcParam.NetSpendParams.TOP_TEN_ACCESSORIAL_PARAM,
                DashboardStoredProcParam.NetSpendParams.MODE_NAMES_PARAM, DashboardStoredProcParam.NetSpendParams.SCORE_TYPE_PARAM
        };
        QueryParameter queryParameter = DashboardUtil.prepareDashboardFilterStoredProcParam(paramNames, filter);
        return persistentContext.findEntities(NetSpendByModeDto.Config.StoredProcedureQueryName.NET_SPEND_BY_MONTH, queryParameter);
    }

    /**
     * @param filter
     * @return
     */
    public List<TaxSpendDto> getTaxSpend(DashboardsFilterCriteria filter) {
        String[] paramNames = {DashboardStoredProcParam.TaxSpendParams.DATE_TYPE_PARAM, DashboardStoredProcParam.TaxSpendParams.CONVERTED_CURRENCY_ID_PARAM,
                DashboardStoredProcParam.TaxSpendParams.CUSTOMER_IDS_CSV_PARAM,
                DashboardStoredProcParam.TaxSpendParams.CARRIER_IDS_PARAM, DashboardStoredProcParam.TaxSpendParams.MODES_PARAM,
                DashboardStoredProcParam.TaxSpendParams.SERVICES_PARAM, DashboardStoredProcParam.TaxSpendParams.LANES_PARAM,
                DashboardStoredProcParam.TaxSpendParams.FROM_DATE_PARAM, DashboardStoredProcParam.TaxSpendParams.TO_DATE_PARAM,
                DashboardStoredProcParam.TaxSpendParams.MODE_NAMES_PARAM
        };
        QueryParameter queryParameter = DashboardUtil.prepareDashboardFilterStoredProcParam(paramNames, filter);
        return persistentContext.findEntities(TaxSpendDto.Config.StoredProcedureQueryName.TAX_SPEND, queryParameter);
    }

    /**
     * @param filter
     * @return
     */
    public List<TaxSpendDto> getTaxSpendByCarrier(DashboardsFilterCriteria filter) {
        String[] paramNames = {DashboardStoredProcParam.TaxSpendParams.DATE_TYPE_PARAM, DashboardStoredProcParam.TaxSpendParams.CONVERTED_CURRENCY_ID_PARAM,
                DashboardStoredProcParam.TaxSpendParams.CUSTOMER_IDS_CSV_PARAM,
                DashboardStoredProcParam.TaxSpendParams.CARRIER_IDS_PARAM, DashboardStoredProcParam.TaxSpendParams.MODES_PARAM,
                DashboardStoredProcParam.TaxSpendParams.SERVICES_PARAM, DashboardStoredProcParam.TaxSpendParams.LANES_PARAM,
                DashboardStoredProcParam.TaxSpendParams.FROM_DATE_PARAM, DashboardStoredProcParam.TaxSpendParams.TO_DATE_PARAM,
                DashboardStoredProcParam.TaxSpendParams.MODE_NAMES_PARAM, DashboardStoredProcParam.TaxSpendParams.TAX_PARAM
        };
        QueryParameter queryParameter = DashboardUtil.prepareDashboardFilterStoredProcParam(paramNames, filter);
        return persistentContext.findEntities(TaxSpendDto.Config.StoredProcedureQueryName.TAX_SPEND_BY_CARRIER, queryParameter);
    }

    /**
     * @param filter
     * @return
     */
    public List<TaxSpendDto> getTaxSpendByMonth(DashboardsFilterCriteria filter) {
        String[] paramNames = {DashboardStoredProcParam.TaxSpendParams.DATE_TYPE_PARAM, DashboardStoredProcParam.TaxSpendParams.CONVERTED_CURRENCY_ID_PARAM,
                DashboardStoredProcParam.TaxSpendParams.CUSTOMER_IDS_CSV_PARAM,
                DashboardStoredProcParam.TaxSpendParams.CARRIER_IDS_PARAM, DashboardStoredProcParam.TaxSpendParams.MODES_PARAM,
                DashboardStoredProcParam.TaxSpendParams.SERVICES_PARAM, DashboardStoredProcParam.TaxSpendParams.LANES_PARAM,
                DashboardStoredProcParam.TaxSpendParams.FROM_DATE_PARAM, DashboardStoredProcParam.TaxSpendParams.TO_DATE_PARAM,
                DashboardStoredProcParam.TaxSpendParams.MODE_NAMES_PARAM, DashboardStoredProcParam.TaxSpendParams.TAX_PARAM
        };
        QueryParameter queryParameter = DashboardUtil.prepareDashboardFilterStoredProcParam(paramNames, filter);
        return persistentContext.findEntities(TaxSpendDto.Config.StoredProcedureQueryName.TAX_SPEND_BY_MONTH, queryParameter);
    }

    /**
     * @param filter
     * @param isTopTenAccessorial
     * @return
     */
    public List<AccessorialSpendDto> getTopAccessorialSpend(DashboardsFilterCriteria filter, boolean isTopTenAccessorial) {
        filter.setTopTenAccessorial(isTopTenAccessorial);
        String[] paramNames = {DashboardStoredProcParam.AccessorialSpendParams.DATE_TYPE_PARAM, DashboardStoredProcParam.AccessorialSpendParams.CONVERTED_CURRENCY_ID_PARAM,
                DashboardStoredProcParam.AccessorialSpendParams.CONVERTED_CURRENCY_CODE_PARAM, DashboardStoredProcParam.AccessorialSpendParams.CUSTOMER_IDS_CSV_PARAM,
                DashboardStoredProcParam.AccessorialSpendParams.CARRIER_IDS_PARAM, DashboardStoredProcParam.AccessorialSpendParams.MODES_PARAM,
                DashboardStoredProcParam.AccessorialSpendParams.SERVICES_PARAM, DashboardStoredProcParam.AccessorialSpendParams.LANES_PARAM,
                DashboardStoredProcParam.AccessorialSpendParams.ACCESSORIAL_NAME_PARAM, DashboardStoredProcParam.AccessorialSpendParams.FROM_DATE_PARAM,
                DashboardStoredProcParam.AccessorialSpendParams.TO_DATE_PARAM, DashboardStoredProcParam.AccessorialSpendParams.TOP_TEN_ACCESSORIAL_PARAM
        };
        QueryParameter queryParameter = DashboardUtil.prepareDashboardFilterStoredProcParam(paramNames, filter);
        return persistentContext.findEntities(AccessorialSpendDto.Config.StoredProcedureQueryName.TOP_ACCESSORIAL_SPEND, queryParameter);
    }

    /**
     * @param filter
     * @param isTopTenAccessorial
     * @return
     */
    public List<AccessorialSpendDto> getTopAccessorialSpendByCarrier(DashboardsFilterCriteria filter, boolean isTopTenAccessorial) {
        filter.setTopTenAccessorial(isTopTenAccessorial);
        String[] paramNames = {DashboardStoredProcParam.AccessorialSpendParams.DATE_TYPE_PARAM, DashboardStoredProcParam.AccessorialSpendParams.CONVERTED_CURRENCY_ID_PARAM,
                DashboardStoredProcParam.AccessorialSpendParams.CONVERTED_CURRENCY_CODE_PARAM, DashboardStoredProcParam.AccessorialSpendParams.CUSTOMER_IDS_CSV_PARAM,
                DashboardStoredProcParam.AccessorialSpendParams.CARRIER_IDS_PARAM, DashboardStoredProcParam.AccessorialSpendParams.MODES_PARAM,
                DashboardStoredProcParam.AccessorialSpendParams.SERVICES_PARAM, DashboardStoredProcParam.AccessorialSpendParams.LANES_PARAM,
                DashboardStoredProcParam.AccessorialSpendParams.ACCESSORIAL_NAME_PARAM, DashboardStoredProcParam.AccessorialSpendParams.FROM_DATE_PARAM,
                DashboardStoredProcParam.AccessorialSpendParams.TO_DATE_PARAM, DashboardStoredProcParam.AccessorialSpendParams.TOP_TEN_ACCESSORIAL_PARAM
        };
        QueryParameter queryParameter = DashboardUtil.prepareDashboardFilterStoredProcParam(paramNames, filter);
        return persistentContext.findEntities(AccessorialSpendDto.Config.StoredProcedureQueryName.TOP_ACCESSORIAL_SPEND_BY_CARRIER, queryParameter);
    }

    /**
     * @param filter
     * @param isTopTenAccessorial
     * @return
     */
    public List<AccessorialSpendDto> getTopAccessorialSpendByMonth(DashboardsFilterCriteria filter, boolean isTopTenAccessorial) {
        filter.setTopTenAccessorial(isTopTenAccessorial);
        String[] paramNames = {DashboardStoredProcParam.AccessorialSpendParams.DATE_TYPE_PARAM, DashboardStoredProcParam.AccessorialSpendParams.CONVERTED_CURRENCY_ID_PARAM,
                DashboardStoredProcParam.AccessorialSpendParams.CONVERTED_CURRENCY_CODE_PARAM, DashboardStoredProcParam.AccessorialSpendParams.CUSTOMER_IDS_CSV_PARAM,
                DashboardStoredProcParam.AccessorialSpendParams.CARRIER_IDS_PARAM, DashboardStoredProcParam.AccessorialSpendParams.MODES_PARAM,
                DashboardStoredProcParam.AccessorialSpendParams.SERVICES_PARAM, DashboardStoredProcParam.AccessorialSpendParams.LANES_PARAM,
                DashboardStoredProcParam.AccessorialSpendParams.ACCESSORIAL_NAME_PARAM, DashboardStoredProcParam.AccessorialSpendParams.FROM_DATE_PARAM,
                DashboardStoredProcParam.AccessorialSpendParams.TO_DATE_PARAM, DashboardStoredProcParam.AccessorialSpendParams.TOP_TEN_ACCESSORIAL_PARAM
        };
        QueryParameter queryParameter = DashboardUtil.prepareDashboardFilterStoredProcParam(paramNames, filter);
        return persistentContext.findEntities(AccessorialSpendDto.Config.StoredProcedureQueryName.TOP_ACCESSORIAL_SPEND_BY_MONTH, queryParameter);
    }

    public List<AccessorialSpendDto> getTopAccessorialSpendByAccessorial(DashboardsFilterCriteria filter, boolean isTopTenAccessorial) {
        filter.setTopTenAccessorial(isTopTenAccessorial);
        String[] paramNames = {DashboardStoredProcParam.AccessorialSpendParams.DATE_TYPE_PARAM, DashboardStoredProcParam.AccessorialSpendParams.CONVERTED_CURRENCY_ID_PARAM,
                DashboardStoredProcParam.AccessorialSpendParams.CONVERTED_CURRENCY_CODE_PARAM, DashboardStoredProcParam.AccessorialSpendParams.CUSTOMER_IDS_CSV_PARAM,
                DashboardStoredProcParam.AccessorialSpendParams.CARRIER_IDS_PARAM, DashboardStoredProcParam.AccessorialSpendParams.MODES_PARAM,
                DashboardStoredProcParam.AccessorialSpendParams.SERVICES_PARAM, DashboardStoredProcParam.AccessorialSpendParams.LANES_PARAM,
                DashboardStoredProcParam.AccessorialSpendParams.FROM_DATE_PARAM, DashboardStoredProcParam.AccessorialSpendParams.TO_DATE_PARAM,
                DashboardStoredProcParam.AccessorialSpendParams.TOP_TEN_ACCESSORIAL_PARAM
        };
        QueryParameter queryParameter = DashboardUtil.prepareDashboardFilterStoredProcParam(paramNames, filter);
        return persistentContext.findEntities(AccessorialSpendDto.Config.StoredProcedureQueryName.TOP_ACCESSORIAL_SPEND_BY_ACCESSORIAL, queryParameter);
    }

    /**
     * @param filter
     * @param isTopTenAccessorial
     * @return
     */
    public List<AccessorialSpendDto> getAccessorialSpend(DashboardsFilterCriteria filter, boolean isTopTenAccessorial) {
        filter.setTopTenAccessorial(isTopTenAccessorial);
        String[] paramNames = {DashboardStoredProcParam.AccessorialSpendParams.DATE_TYPE_PARAM, DashboardStoredProcParam.AccessorialSpendParams.CONVERTED_CURRENCY_ID_PARAM,
                DashboardStoredProcParam.AccessorialSpendParams.CUSTOMER_IDS_CSV_PARAM,
                DashboardStoredProcParam.AccessorialSpendParams.CARRIER_IDS_PARAM, DashboardStoredProcParam.AccessorialSpendParams.MODES_PARAM,
                DashboardStoredProcParam.AccessorialSpendParams.SERVICES_PARAM, DashboardStoredProcParam.AccessorialSpendParams.LANES_PARAM,
                DashboardStoredProcParam.AccessorialSpendParams.FROM_DATE_PARAM, DashboardStoredProcParam.AccessorialSpendParams.TO_DATE_PARAM,
                DashboardStoredProcParam.AccessorialSpendParams.MODE_NAMES_PARAM
        };
        QueryParameter queryParameter = DashboardUtil.prepareDashboardFilterStoredProcParam(paramNames, filter);
        return persistentContext.findEntities(AccessorialSpendDto.Config.StoredProcedureQueryName.ACCESSORIAL_SPEND, queryParameter);
    }

    /**
     * @param filter
     * @param isTopTenAccessorial
     * @return
     */
    public List<AccessorialSpendDto> getAccessorialSpendByCarrier(DashboardsFilterCriteria filter, boolean isTopTenAccessorial) {
        filter.setTopTenAccessorial(isTopTenAccessorial);
        String[] paramNames = {DashboardStoredProcParam.AccessorialSpendParams.DATE_TYPE_PARAM, DashboardStoredProcParam.AccessorialSpendParams.CONVERTED_CURRENCY_ID_PARAM,
                DashboardStoredProcParam.AccessorialSpendParams.CUSTOMER_IDS_CSV_PARAM,
                DashboardStoredProcParam.AccessorialSpendParams.CARRIER_IDS_PARAM, DashboardStoredProcParam.AccessorialSpendParams.MODES_PARAM,
                DashboardStoredProcParam.AccessorialSpendParams.SERVICES_PARAM, DashboardStoredProcParam.AccessorialSpendParams.LANES_PARAM,
                DashboardStoredProcParam.AccessorialSpendParams.FROM_DATE_PARAM, DashboardStoredProcParam.AccessorialSpendParams.TO_DATE_PARAM,
                DashboardStoredProcParam.AccessorialSpendParams.MODE_NAMES_PARAM, DashboardStoredProcParam.AccessorialSpendParams.ACCESSORIAL_DESC_PARAM
        };
        QueryParameter queryParameter = DashboardUtil.prepareDashboardFilterStoredProcParam(paramNames, filter);
        return persistentContext.findEntities(AccessorialSpendDto.Config.StoredProcedureQueryName.ACCESSORIAL_SPEND_BY_CARRIER, queryParameter);
    }

    /**
     * @param filter
     * @param isTopTenAccessorial
     * @return
     */
    public List<AccessorialSpendDto> getAccessorialSpendByMonth(DashboardsFilterCriteria filter, boolean isTopTenAccessorial) {
        filter.setTopTenAccessorial(isTopTenAccessorial);
        String[] paramNames = {DashboardStoredProcParam.AccessorialSpendParams.DATE_TYPE_PARAM, DashboardStoredProcParam.AccessorialSpendParams.CONVERTED_CURRENCY_ID_PARAM,
                DashboardStoredProcParam.AccessorialSpendParams.CUSTOMER_IDS_CSV_PARAM,
                DashboardStoredProcParam.AccessorialSpendParams.CARRIER_IDS_PARAM, DashboardStoredProcParam.AccessorialSpendParams.MODES_PARAM,
                DashboardStoredProcParam.AccessorialSpendParams.SERVICES_PARAM, DashboardStoredProcParam.AccessorialSpendParams.LANES_PARAM,
                DashboardStoredProcParam.AccessorialSpendParams.FROM_DATE_PARAM, DashboardStoredProcParam.AccessorialSpendParams.TO_DATE_PARAM,
                DashboardStoredProcParam.AccessorialSpendParams.MODE_NAMES_PARAM, DashboardStoredProcParam.AccessorialSpendParams.ACCESSORIAL_DESC_PARAM
        };
        QueryParameter queryParameter = DashboardUtil.prepareDashboardFilterStoredProcParam(paramNames, filter);
        return persistentContext.findEntities(AccessorialSpendDto.Config.StoredProcedureQueryName.ACCESSORIAL_SPEND_BY_MONTH, queryParameter);
    }

    /**
     * @param filter
     * @return
     */
    public List<AverageSpendPerShipmentDto> getAvgSpendPerShipmt(DashboardsFilterCriteria filter, boolean isTopTenAccessorial) {
        filter.setTopTenAccessorial(isTopTenAccessorial);
        String[] paramNames = {
                DashboardStoredProcParam.AverageSpendShipmentParam.DATE_TYPE_PARAM, DashboardStoredProcParam.AverageSpendShipmentParam.CONVERTED_CURRENCY_ID_PARAM,
                DashboardStoredProcParam.AverageSpendShipmentParam.CONVERTED_CURRENCY_CODE_PARAM, DashboardStoredProcParam.AverageSpendShipmentParam.MODES_PARAM,
                DashboardStoredProcParam.AverageSpendShipmentParam.SERVICES_PARAM, DashboardStoredProcParam.AverageSpendShipmentParam.ACCESSORIAL_NAME_PARAM,
                DashboardStoredProcParam.AverageSpendShipmentParam.LANES_PARAM, DashboardStoredProcParam.AverageSpendShipmentParam.FROM_DATE_PARAM,
                DashboardStoredProcParam.AverageSpendShipmentParam.TO_DATE_PARAM, DashboardStoredProcParam.AverageSpendShipmentParam.CARRIER_IDS_PARAM,
                DashboardStoredProcParam.AverageSpendShipmentParam.CUSTOMER_IDS_CSV_PARAM, DashboardStoredProcParam.AverageSpendShipmentParam.TOP_TEN_ACCESSORIAL_PARAM
        };
        QueryParameter queryParameter = DashboardUtil.prepareDashboardFilterStoredProcParam(paramNames, filter);
        return persistentContext.findEntities(AverageSpendPerShipmentDto.Config.StoredProcedureQueryName.AVG_SPEND_PER_SHIPMENT, queryParameter);
    }

    /**
     * @param filter
     * @return
     */
    public List<AverageWeightModeShipmtDto> getAverageWeightByModeShipmt(DashboardsFilterCriteria filter, boolean isTopTenAccessorial) {
        filter.setTopTenAccessorial(isTopTenAccessorial);
        String[] paramNames = {
                DashboardStoredProcParam.AverageWeightByModeShipmtParam.DATE_TYPE_PARAM, DashboardStoredProcParam.AverageWeightByModeShipmtParam.CONVERTED_WEIGHT_UNIT_PARAM,
                DashboardStoredProcParam.AverageWeightByModeShipmtParam.MODES_PARAM, DashboardStoredProcParam.AverageWeightByModeShipmtParam.SERVICES_PARAM,
                DashboardStoredProcParam.AverageWeightByModeShipmtParam.ACCESSORIAL_NAME_PARAM, DashboardStoredProcParam.AverageWeightByModeShipmtParam.LANES_PARAM,
                DashboardStoredProcParam.AverageWeightByModeShipmtParam.FROM_DATE_PARAM, DashboardStoredProcParam.AverageWeightByModeShipmtParam.TO_DATE_PARAM,
                DashboardStoredProcParam.AverageWeightByModeShipmtParam.CARRIER_IDS_PARAM, DashboardStoredProcParam.AverageWeightByModeShipmtParam.CUSTOMER_IDS_CSV_PARAM,
                DashboardStoredProcParam.AverageWeightByModeShipmtParam.TOP_TEN_ACCESSORIAL_PARAM
        };
        QueryParameter queryParameter = DashboardUtil.prepareDashboardFilterStoredProcParam(paramNames, filter);
        return persistentContext.findEntities(AverageWeightModeShipmtDto.Config.StoredProcedureQueryName.AVG_WEIGHT_MODE_SHIPMENT, queryParameter);
    }

    public List<ServiceLevelUsageAndPerformanceDto> getServiceLevelUsageAndPerformance(DashboardsFilterCriteria filter, boolean isTopTenAccessorial) {
        filter.setTopTenAccessorial(isTopTenAccessorial);
        String[] paramNames = {DashboardStoredProcParam.ServiceLevelUsageAndPerformanceParams.DATE_TYPE_PARAM, DashboardStoredProcParam.ServiceLevelUsageAndPerformanceParams.CUSTOMER_IDS_CSV_PARAM,
                DashboardStoredProcParam.ServiceLevelUsageAndPerformanceParams.CARRIER_IDS_PARAM, DashboardStoredProcParam.ServiceLevelUsageAndPerformanceParams.MODES_PARAM,
                DashboardStoredProcParam.ServiceLevelUsageAndPerformanceParams.SERVICES_PARAM, DashboardStoredProcParam.ServiceLevelUsageAndPerformanceParams.LANES_PARAM,
                DashboardStoredProcParam.ServiceLevelUsageAndPerformanceParams.FROM_DATE_PARAM, DashboardStoredProcParam.ServiceLevelUsageAndPerformanceParams.TO_DATE_PARAM,
                DashboardStoredProcParam.ServiceLevelUsageAndPerformanceParams.ACCESSORIAL_NAME_PARAM, DashboardStoredProcParam.ServiceLevelUsageAndPerformanceParams.TOP_TEN_ACCESSORIAL_PARAM
        };
        QueryParameter queryParameter = DashboardUtil.prepareDashboardFilterStoredProcParam(paramNames, filter);
        return persistentContext.findEntities(ServiceLevelUsageAndPerformanceDto.Config.StoredProcedureQueryName.SERVICE_LEVEL_USAGE_AND_PERFORMANCE, queryParameter);
    }

    public List<InboundSpendDto> getInboundSpend(DashboardsFilterCriteria filter, boolean isTopTenAccessorial) {
        filter.setTopTenAccessorial(isTopTenAccessorial);
        String[] paramNames = {DashboardStoredProcParam.InboundSpendParams.DATE_TYPE_PARAM, DashboardStoredProcParam.InboundSpendParams.CONVERTED_CURRENCY_ID_PARAM,
                DashboardStoredProcParam.InboundSpendParams.CONVERTED_CURRENCY_CODE_PARAM, DashboardStoredProcParam.InboundSpendParams.CUSTOMER_IDS_CSV_PARAM,
                DashboardStoredProcParam.InboundSpendParams.CARRIER_IDS_PARAM, DashboardStoredProcParam.InboundSpendParams.MODES_PARAM,
                DashboardStoredProcParam.InboundSpendParams.SERVICES_PARAM, DashboardStoredProcParam.InboundSpendParams.LANES_PARAM,
                DashboardStoredProcParam.InboundSpendParams.FROM_DATE_PARAM, DashboardStoredProcParam.InboundSpendParams.TO_DATE_PARAM,
                DashboardStoredProcParam.InboundSpendParams.ACCESSORIAL_NAME_PARAM, DashboardStoredProcParam.InboundSpendParams.TOP_TEN_ACCESSORIAL_PARAM
        };
        QueryParameter queryParameter = DashboardUtil.prepareDashboardFilterStoredProcParam(paramNames, filter);
        return persistentContext.findEntities(InboundSpendDto.Config.StoredProcedureQueryName.INBOUND_SPEND, queryParameter);
    }

    public List<InboundSpendDto> getInboundSpendByMonth(DashboardsFilterCriteria filter, boolean isTopTenAccessorial) {
        filter.setTopTenAccessorial(isTopTenAccessorial);
        String[] paramNames = {DashboardStoredProcParam.InboundSpendParams.DATE_TYPE_PARAM, DashboardStoredProcParam.InboundSpendParams.CONVERTED_CURRENCY_ID_PARAM,
                DashboardStoredProcParam.InboundSpendParams.CONVERTED_CURRENCY_CODE_PARAM, DashboardStoredProcParam.InboundSpendParams.CUSTOMER_IDS_CSV_PARAM,
                DashboardStoredProcParam.InboundSpendParams.CARRIER_IDS_PARAM, DashboardStoredProcParam.InboundSpendParams.MODES_PARAM,
                DashboardStoredProcParam.InboundSpendParams.SERVICES_PARAM, DashboardStoredProcParam.InboundSpendParams.LANES_PARAM,
                DashboardStoredProcParam.InboundSpendParams.FROM_DATE_PARAM, DashboardStoredProcParam.InboundSpendParams.TO_DATE_PARAM,
                DashboardStoredProcParam.InboundSpendParams.ACCESSORIAL_NAME_PARAM, DashboardStoredProcParam.InboundSpendParams.TOP_TEN_ACCESSORIAL_PARAM
        };
        QueryParameter queryParameter = DashboardUtil.prepareDashboardFilterStoredProcParam(paramNames, filter);
        return persistentContext.findEntities(InboundSpendDto.Config.StoredProcedureQueryName.INBOUND_SPEND_BY_MONTH, queryParameter);
    }

    public List<OutboundSpendDto> getOutboundSpend(DashboardsFilterCriteria filter, boolean isTopTenAccessorial) {
        filter.setTopTenAccessorial(isTopTenAccessorial);
        String[] paramNames = {DashboardStoredProcParam.OutboundSpendParams.DATE_TYPE_PARAM, DashboardStoredProcParam.OutboundSpendParams.CONVERTED_CURRENCY_ID_PARAM,
                DashboardStoredProcParam.OutboundSpendParams.CONVERTED_CURRENCY_CODE_PARAM, DashboardStoredProcParam.OutboundSpendParams.CUSTOMER_IDS_CSV_PARAM,
                DashboardStoredProcParam.OutboundSpendParams.CARRIER_IDS_PARAM, DashboardStoredProcParam.OutboundSpendParams.MODES_PARAM,
                DashboardStoredProcParam.OutboundSpendParams.SERVICES_PARAM, DashboardStoredProcParam.OutboundSpendParams.LANES_PARAM,
                DashboardStoredProcParam.OutboundSpendParams.FROM_DATE_PARAM, DashboardStoredProcParam.OutboundSpendParams.TO_DATE_PARAM,
                DashboardStoredProcParam.OutboundSpendParams.ACCESSORIAL_NAME_PARAM, DashboardStoredProcParam.OutboundSpendParams.TOP_TEN_ACCESSORIAL_PARAM
        };
        QueryParameter queryParameter = DashboardUtil.prepareDashboardFilterStoredProcParam(paramNames, filter);
        return persistentContext.findEntities(OutboundSpendDto.Config.StoredProcedureQueryName.OUTBOUND_SPEND, queryParameter);
    }

    public List<OutboundSpendDto> getOutboundSpendByMonth(DashboardsFilterCriteria filter, boolean isTopTenAccessorial) {
        filter.setTopTenAccessorial(isTopTenAccessorial);
        String[] paramNames = {DashboardStoredProcParam.OutboundSpendParams.DATE_TYPE_PARAM, DashboardStoredProcParam.OutboundSpendParams.CONVERTED_CURRENCY_ID_PARAM,
                DashboardStoredProcParam.OutboundSpendParams.CONVERTED_CURRENCY_CODE_PARAM, DashboardStoredProcParam.OutboundSpendParams.CUSTOMER_IDS_CSV_PARAM,
                DashboardStoredProcParam.OutboundSpendParams.CARRIER_IDS_PARAM, DashboardStoredProcParam.OutboundSpendParams.MODES_PARAM,
                DashboardStoredProcParam.OutboundSpendParams.SERVICES_PARAM, DashboardStoredProcParam.OutboundSpendParams.LANES_PARAM,
                DashboardStoredProcParam.OutboundSpendParams.FROM_DATE_PARAM, DashboardStoredProcParam.OutboundSpendParams.TO_DATE_PARAM,
                DashboardStoredProcParam.OutboundSpendParams.ACCESSORIAL_NAME_PARAM, DashboardStoredProcParam.OutboundSpendParams.TOP_TEN_ACCESSORIAL_PARAM
        };
        QueryParameter queryParameter = DashboardUtil.prepareDashboardFilterStoredProcParam(paramNames, filter);
        return persistentContext.findEntities(OutboundSpendDto.Config.StoredProcedureQueryName.OUTBOUND_SPEND_BY_MONTH, queryParameter);
    }

    public List<InvoiceStatusCountDto> getInvoiceStatusCount(DashboardsFilterCriteria filter) {
        String[] paramNames = {DashboardStoredProcParam.InvoiceStatusCountParams.DATE_TYPE_PARAM,
                DashboardStoredProcParam.InvoiceStatusCountParams.CUSTOMER_IDS_CSV_PARAM,
                DashboardStoredProcParam.InvoiceStatusCountParams.CARRIER_IDS_PARAM, DashboardStoredProcParam.InvoiceStatusCountParams.MODES_PARAM,
                DashboardStoredProcParam.InvoiceStatusCountParams.SERVICES_PARAM, DashboardStoredProcParam.InvoiceStatusCountParams.LANES_PARAM,
                DashboardStoredProcParam.InvoiceStatusCountParams.FROM_DATE_PARAM, DashboardStoredProcParam.InvoiceStatusCountParams.TO_DATE_PARAM,
                DashboardStoredProcParam.InvoiceStatusCountParams.MODE_NAMES_PARAM
        };
        QueryParameter queryParameter = DashboardUtil.prepareDashboardFilterStoredProcParam(paramNames, filter);
        return persistentContext.findEntities(InvoiceStatusCountDto.Config.StoredProcedureQueryName.INVOICE_STATUS_COUNT, queryParameter);
    }

    public List<InvoiceStatusCountDto> getInvoiceStatusCountByCarrier(DashboardsFilterCriteria filter) {
        String[] paramNames = {DashboardStoredProcParam.InvoiceStatusCountParams.DATE_TYPE_PARAM,
                DashboardStoredProcParam.InvoiceStatusCountParams.CUSTOMER_IDS_CSV_PARAM,
                DashboardStoredProcParam.InvoiceStatusCountParams.CARRIER_IDS_PARAM, DashboardStoredProcParam.InvoiceStatusCountParams.MODES_PARAM,
                DashboardStoredProcParam.InvoiceStatusCountParams.SERVICES_PARAM, DashboardStoredProcParam.InvoiceStatusCountParams.LANES_PARAM,
                DashboardStoredProcParam.InvoiceStatusCountParams.FROM_DATE_PARAM, DashboardStoredProcParam.InvoiceStatusCountParams.TO_DATE_PARAM,
                DashboardStoredProcParam.InvoiceStatusCountParams.MODE_NAMES_PARAM, DashboardStoredProcParam.InvoiceStatusCountParams.INVOICE_STATUS_ID_PARAM
        };
        QueryParameter queryParameter = DashboardUtil.prepareDashboardFilterStoredProcParam(paramNames, filter);
        return persistentContext.findEntities(InvoiceStatusCountDto.Config.StoredProcedureQueryName.INVOICE_STATUS_COUNT_BY_CARRIER, queryParameter);
    }

    public List<InvoiceStatusCountDto> getInvoiceStatusCountByMonth(DashboardsFilterCriteria filter) {
        String[] paramNames = {DashboardStoredProcParam.InvoiceStatusCountParams.DATE_TYPE_PARAM,
                DashboardStoredProcParam.InvoiceStatusCountParams.CUSTOMER_IDS_CSV_PARAM,
                DashboardStoredProcParam.InvoiceStatusCountParams.CARRIER_IDS_PARAM, DashboardStoredProcParam.InvoiceStatusCountParams.MODES_PARAM,
                DashboardStoredProcParam.InvoiceStatusCountParams.SERVICES_PARAM, DashboardStoredProcParam.InvoiceStatusCountParams.LANES_PARAM,
                DashboardStoredProcParam.InvoiceStatusCountParams.FROM_DATE_PARAM, DashboardStoredProcParam.InvoiceStatusCountParams.TO_DATE_PARAM,
                DashboardStoredProcParam.InvoiceStatusCountParams.MODE_NAMES_PARAM, DashboardStoredProcParam.InvoiceStatusCountParams.INVOICE_STATUS_ID_PARAM
        };
        QueryParameter queryParameter = DashboardUtil.prepareDashboardFilterStoredProcParam(paramNames, filter);
        return persistentContext.findEntities(InvoiceStatusCountDto.Config.StoredProcedureQueryName.INVOICE_STATUS_COUNT_BY_MONTH, queryParameter);
    }

    public List<InvoiceStatusAmountDto> getInvoiceStatusAmount(DashboardsFilterCriteria filter) {
        String[] paramNames = {DashboardStoredProcParam.InvoiceStatusAmountParams.DATE_TYPE_PARAM, DashboardStoredProcParam.InvoiceStatusAmountParams.CONVERTED_CURRENCY_ID_PARAM,
                DashboardStoredProcParam.InvoiceStatusAmountParams.CUSTOMER_IDS_CSV_PARAM,
                DashboardStoredProcParam.InvoiceStatusAmountParams.CARRIER_IDS_PARAM, DashboardStoredProcParam.InvoiceStatusAmountParams.MODES_PARAM,
                DashboardStoredProcParam.InvoiceStatusAmountParams.SERVICES_PARAM, DashboardStoredProcParam.InvoiceStatusAmountParams.LANES_PARAM,
                DashboardStoredProcParam.InvoiceStatusAmountParams.FROM_DATE_PARAM, DashboardStoredProcParam.InvoiceStatusAmountParams.TO_DATE_PARAM,
                DashboardStoredProcParam.InvoiceStatusAmountParams.MODE_NAMES_PARAM
        };
        QueryParameter queryParameter = DashboardUtil.prepareDashboardFilterStoredProcParam(paramNames, filter);
        return persistentContext.findEntities(InvoiceStatusAmountDto.Config.StoredProcedureQueryName.INVOICE_STATUS_AMOUNT, queryParameter);
    }

    public List<InvoiceStatusAmountDto> getInvoiceStatusAmountByCarrier(DashboardsFilterCriteria filter) {
        String[] paramNames = {DashboardStoredProcParam.InvoiceStatusAmountParams.DATE_TYPE_PARAM, DashboardStoredProcParam.InvoiceStatusAmountParams.CONVERTED_CURRENCY_ID_PARAM,
                DashboardStoredProcParam.InvoiceStatusAmountParams.CUSTOMER_IDS_CSV_PARAM,
                DashboardStoredProcParam.InvoiceStatusAmountParams.CARRIER_IDS_PARAM, DashboardStoredProcParam.InvoiceStatusAmountParams.MODES_PARAM,
                DashboardStoredProcParam.InvoiceStatusAmountParams.SERVICES_PARAM, DashboardStoredProcParam.InvoiceStatusAmountParams.LANES_PARAM,
                DashboardStoredProcParam.InvoiceStatusAmountParams.FROM_DATE_PARAM, DashboardStoredProcParam.InvoiceStatusAmountParams.TO_DATE_PARAM,
                DashboardStoredProcParam.InvoiceStatusAmountParams.MODE_NAMES_PARAM, DashboardStoredProcParam.InvoiceStatusAmountParams.INVOICE_STATUS_ID_PARAM
        };
        QueryParameter queryParameter = DashboardUtil.prepareDashboardFilterStoredProcParam(paramNames, filter);
        return persistentContext.findEntities(InvoiceStatusAmountDto.Config.StoredProcedureQueryName.INVOICE_STATUS_AMOUNT_BY_CARRIER, queryParameter);
    }

    public List<InvoiceStatusAmountDto> getInvoiceStatusAmountByMonth(DashboardsFilterCriteria filter) {
        String[] paramNames = {DashboardStoredProcParam.InvoiceStatusAmountParams.DATE_TYPE_PARAM, DashboardStoredProcParam.InvoiceStatusAmountParams.CONVERTED_CURRENCY_ID_PARAM,
                DashboardStoredProcParam.InvoiceStatusAmountParams.CUSTOMER_IDS_CSV_PARAM,
                DashboardStoredProcParam.InvoiceStatusAmountParams.CARRIER_IDS_PARAM, DashboardStoredProcParam.InvoiceStatusAmountParams.MODES_PARAM,
                DashboardStoredProcParam.InvoiceStatusAmountParams.SERVICES_PARAM, DashboardStoredProcParam.InvoiceStatusAmountParams.LANES_PARAM,
                DashboardStoredProcParam.InvoiceStatusAmountParams.FROM_DATE_PARAM, DashboardStoredProcParam.InvoiceStatusAmountParams.TO_DATE_PARAM,
                DashboardStoredProcParam.InvoiceStatusAmountParams.MODE_NAMES_PARAM, DashboardStoredProcParam.InvoiceStatusAmountParams.INVOICE_STATUS_ID_PARAM
        };
        QueryParameter queryParameter = DashboardUtil.prepareDashboardFilterStoredProcParam(paramNames, filter);
        return persistentContext.findEntities(InvoiceStatusAmountDto.Config.StoredProcedureQueryName.INVOICE_STATUS_AMOUNT_BY_MONTH, queryParameter);
    }

    public List<InvoiceMethodScoreDto> getInvoiceMethodScore(DashboardsFilterCriteria filter) {
        String[] paramNames = {DashboardStoredProcParam.InvoiceMethodScoreParams.DATE_TYPE_PARAM, DashboardStoredProcParam.InvoiceMethodScoreParams.CUSTOMER_IDS_CSV_PARAM,
                DashboardStoredProcParam.InvoiceMethodScoreParams.CARRIER_IDS_PARAM, DashboardStoredProcParam.InvoiceMethodScoreParams.MODES_PARAM,
                DashboardStoredProcParam.InvoiceMethodScoreParams.SERVICES_PARAM, DashboardStoredProcParam.InvoiceMethodScoreParams.LANES_PARAM,
                DashboardStoredProcParam.InvoiceMethodScoreParams.FROM_DATE_PARAM, DashboardStoredProcParam.InvoiceMethodScoreParams.TO_DATE_PARAM,
                DashboardStoredProcParam.InvoiceMethodScoreParams.MODE_NAMES_PARAM
        };
        QueryParameter queryParameter = DashboardUtil.prepareDashboardFilterStoredProcParam(paramNames, filter);
        return persistentContext.findEntities(InvoiceMethodScoreDto.Config.StoredProcedureQueryName.INVOICE_METHOD_SCORE, queryParameter);
    }

    public List<InvoiceMethodScoreDto> getInvoiceMethodScoreByCarrier(DashboardsFilterCriteria filter) {
        String[] paramNames = {DashboardStoredProcParam.InvoiceMethodScoreParams.DATE_TYPE_PARAM, DashboardStoredProcParam.InvoiceMethodScoreParams.CUSTOMER_IDS_CSV_PARAM,
                DashboardStoredProcParam.InvoiceMethodScoreParams.CARRIER_IDS_PARAM, DashboardStoredProcParam.InvoiceMethodScoreParams.MODES_PARAM,
                DashboardStoredProcParam.InvoiceMethodScoreParams.SERVICES_PARAM, DashboardStoredProcParam.InvoiceMethodScoreParams.LANES_PARAM,
                DashboardStoredProcParam.InvoiceMethodScoreParams.FROM_DATE_PARAM, DashboardStoredProcParam.InvoiceMethodScoreParams.TO_DATE_PARAM,
                DashboardStoredProcParam.InvoiceMethodScoreParams.MODE_NAMES_PARAM, DashboardStoredProcParam.InvoiceMethodScoreParams.INVOICE_METHOD_PARAM
        };
        QueryParameter queryParameter = DashboardUtil.prepareDashboardFilterStoredProcParam(paramNames, filter);
        return persistentContext.findEntities(InvoiceMethodScoreDto.Config.StoredProcedureQueryName.INVOICE_METHOD_SCORE_BY_CARRIER, queryParameter);
    }

    public List<InvoiceMethodScoreDto> getInvoiceMethodScoreByMonth(DashboardsFilterCriteria filter) {
        String[] paramNames = {DashboardStoredProcParam.InvoiceMethodScoreParams.DATE_TYPE_PARAM, DashboardStoredProcParam.InvoiceMethodScoreParams.CUSTOMER_IDS_CSV_PARAM,
                DashboardStoredProcParam.InvoiceMethodScoreParams.CARRIER_IDS_PARAM, DashboardStoredProcParam.InvoiceMethodScoreParams.MODES_PARAM,
                DashboardStoredProcParam.InvoiceMethodScoreParams.SERVICES_PARAM, DashboardStoredProcParam.InvoiceMethodScoreParams.LANES_PARAM,
                DashboardStoredProcParam.InvoiceMethodScoreParams.FROM_DATE_PARAM, DashboardStoredProcParam.InvoiceMethodScoreParams.TO_DATE_PARAM,
                DashboardStoredProcParam.InvoiceMethodScoreParams.MODE_NAMES_PARAM, DashboardStoredProcParam.InvoiceMethodScoreParams.INVOICE_METHOD_PARAM
        };
        QueryParameter queryParameter = DashboardUtil.prepareDashboardFilterStoredProcParam(paramNames, filter);
        return persistentContext.findEntities(InvoiceMethodScoreDto.Config.StoredProcedureQueryName.INVOICE_METHOD_SCORE_BY_MONTH, queryParameter);
    }

    public List<OrderMatchDto> getOrderMatchStatus(DashboardsFilterCriteria filter) {
        String[] paramNames = {DashboardStoredProcParam.OrderMatchParams.DATE_TYPE_PARAM, DashboardStoredProcParam.OrderMatchParams.CUSTOMER_IDS_CSV_PARAM,
                DashboardStoredProcParam.OrderMatchParams.CARRIER_IDS_PARAM, DashboardStoredProcParam.OrderMatchParams.MODES_PARAM,
                DashboardStoredProcParam.OrderMatchParams.SERVICES_PARAM, DashboardStoredProcParam.OrderMatchParams.LANES_PARAM,
                DashboardStoredProcParam.OrderMatchParams.FROM_DATE_PARAM, DashboardStoredProcParam.OrderMatchParams.TO_DATE_PARAM,
                DashboardStoredProcParam.OrderMatchParams.MODE_NAMES_PARAM
        };
        QueryParameter queryParameter = DashboardUtil.prepareDashboardFilterStoredProcParam(paramNames, filter);
        return persistentContext.findEntities(OrderMatchDto.Config.StoredProcedureQueryName.ORDER_MATCH, queryParameter);
    }

    public List<OrderMatchDto> getOrderMatchByCarrier(DashboardsFilterCriteria filter) {
        String[] paramNames = {DashboardStoredProcParam.OrderMatchParams.DATE_TYPE_PARAM, DashboardStoredProcParam.OrderMatchParams.CUSTOMER_IDS_CSV_PARAM,
                DashboardStoredProcParam.OrderMatchParams.CARRIER_IDS_PARAM, DashboardStoredProcParam.OrderMatchParams.MODES_PARAM,
                DashboardStoredProcParam.OrderMatchParams.SERVICES_PARAM, DashboardStoredProcParam.OrderMatchParams.LANES_PARAM,
                DashboardStoredProcParam.OrderMatchParams.FROM_DATE_PARAM, DashboardStoredProcParam.OrderMatchParams.TO_DATE_PARAM,
                DashboardStoredProcParam.OrderMatchParams.MODE_NAMES_PARAM, DashboardStoredProcParam.OrderMatchParams.ORDER_MATCH_PARAM
        };
        QueryParameter queryParameter = DashboardUtil.prepareDashboardFilterStoredProcParam(paramNames, filter);
        return persistentContext.findEntities(OrderMatchDto.Config.StoredProcedureQueryName.ORDER_MATCH_BY_CARRIER, queryParameter);
    }

    public List<OrderMatchDto> getOrderMatchByMonth(DashboardsFilterCriteria filter) {
        String[] paramNames = {DashboardStoredProcParam.OrderMatchParams.DATE_TYPE_PARAM, DashboardStoredProcParam.OrderMatchParams.CUSTOMER_IDS_CSV_PARAM,
                DashboardStoredProcParam.OrderMatchParams.CARRIER_IDS_PARAM, DashboardStoredProcParam.OrderMatchParams.MODES_PARAM,
                DashboardStoredProcParam.OrderMatchParams.SERVICES_PARAM, DashboardStoredProcParam.OrderMatchParams.LANES_PARAM,
                DashboardStoredProcParam.OrderMatchParams.FROM_DATE_PARAM, DashboardStoredProcParam.OrderMatchParams.TO_DATE_PARAM,
                DashboardStoredProcParam.OrderMatchParams.MODE_NAMES_PARAM, DashboardStoredProcParam.OrderMatchParams.ORDER_MATCH_PARAM
        };
        QueryParameter queryParameter = DashboardUtil.prepareDashboardFilterStoredProcParam(paramNames, filter);
        return persistentContext.findEntities(OrderMatchDto.Config.StoredProcedureQueryName.ORDER_MATCH_BY_MONTH, queryParameter);
    }

    public List<BilledVsApprovedDto> getBilledVsApprovedData(DashboardsFilterCriteria filter) {
        String[] paramNames = {DashboardStoredProcParam.BilledVsApprovedParams.DATE_TYPE_PARAM, DashboardStoredProcParam.BilledVsApprovedParams.CONVERTED_CURRENCY_ID_PARAM,
                DashboardStoredProcParam.BilledVsApprovedParams.CUSTOMER_IDS_CSV_PARAM,
                DashboardStoredProcParam.BilledVsApprovedParams.CARRIER_IDS_PARAM, DashboardStoredProcParam.BilledVsApprovedParams.MODES_PARAM,
                DashboardStoredProcParam.BilledVsApprovedParams.SERVICES_PARAM, DashboardStoredProcParam.BilledVsApprovedParams.LANES_PARAM,
                DashboardStoredProcParam.BilledVsApprovedParams.FROM_DATE_PARAM, DashboardStoredProcParam.BilledVsApprovedParams.TO_DATE_PARAM,
                DashboardStoredProcParam.BilledVsApprovedParams.MODE_NAMES_PARAM
        };
        QueryParameter queryParameter = DashboardUtil.prepareDashboardFilterStoredProcParam(paramNames, filter);
        return persistentContext.findEntities(BilledVsApprovedDto.Config.StoredProcedureQueryName.BILLED_VS_APPROVED, queryParameter);
    }

    public List<BilledVsApprovedDto> getBilledVsApprovedByMonth(DashboardsFilterCriteria filter) {
        String[] paramNames = {DashboardStoredProcParam.BilledVsApprovedParams.DATE_TYPE_PARAM, DashboardStoredProcParam.BilledVsApprovedParams.CONVERTED_CURRENCY_ID_PARAM,
                DashboardStoredProcParam.BilledVsApprovedParams.CUSTOMER_IDS_CSV_PARAM,
                DashboardStoredProcParam.BilledVsApprovedParams.CARRIER_IDS_PARAM, DashboardStoredProcParam.BilledVsApprovedParams.MODES_PARAM,
                DashboardStoredProcParam.BilledVsApprovedParams.SERVICES_PARAM, DashboardStoredProcParam.BilledVsApprovedParams.LANES_PARAM,
                DashboardStoredProcParam.BilledVsApprovedParams.FROM_DATE_PARAM, DashboardStoredProcParam.BilledVsApprovedParams.TO_DATE_PARAM,
                DashboardStoredProcParam.BilledVsApprovedParams.MODE_NAMES_PARAM, DashboardStoredProcParam.BilledVsApprovedParams.BILLED_APPROVED_PARAM
        };
        QueryParameter queryParameter = DashboardUtil.prepareDashboardFilterStoredProcParam(paramNames, filter);
        return persistentContext.findEntities(BilledVsApprovedDto.Config.StoredProcedureQueryName.BILLED_VS_APPROVED_BY_MONTH, queryParameter);
    }

    public List<RecoveryAdjustmentDto> getRecoveryAdjustment(DashboardsFilterCriteria filter, boolean isTopTenAccessorial) {
        filter.setTopTenAccessorial(isTopTenAccessorial);
        String[] paramNames = {DashboardStoredProcParam.RecoveryAdjustmentParams.DATE_TYPE_PARAM, DashboardStoredProcParam.RecoveryAdjustmentParams.CONVERTED_CURRENCY_ID_PARAM,
                DashboardStoredProcParam.RecoveryAdjustmentParams.CONVERTED_CURRENCY_CODE_PARAM, DashboardStoredProcParam.RecoveryAdjustmentParams.CUSTOMER_IDS_CSV_PARAM,
                DashboardStoredProcParam.RecoveryAdjustmentParams.CARRIER_IDS_PARAM, DashboardStoredProcParam.RecoveryAdjustmentParams.MODES_PARAM,
                DashboardStoredProcParam.RecoveryAdjustmentParams.SERVICES_PARAM, DashboardStoredProcParam.RecoveryAdjustmentParams.LANES_PARAM,
                DashboardStoredProcParam.RecoveryAdjustmentParams.FROM_DATE_PARAM, DashboardStoredProcParam.RecoveryAdjustmentParams.TO_DATE_PARAM,
                DashboardStoredProcParam.RecoveryAdjustmentParams.ACCESSORIAL_NAME_PARAM, DashboardStoredProcParam.RecoveryAdjustmentParams.TOP_TEN_ACCESSORIAL_PARAM
        };
        QueryParameter queryParameter = DashboardUtil.prepareDashboardFilterStoredProcParam(paramNames, filter);
        return persistentContext.findEntities(RecoveryAdjustmentDto.Config.StoredProcedureQueryName.RECOVERY_ADJUSTMENT, queryParameter);
    }

    public List<RecoveryAdjustmentDto> getRecoveryAdjustmentByCarrier(DashboardsFilterCriteria filter, boolean isTopTenAccessorial) {
        filter.setTopTenAccessorial(isTopTenAccessorial);
        String[] paramNames = {DashboardStoredProcParam.RecoveryAdjustmentParams.DATE_TYPE_PARAM, DashboardStoredProcParam.RecoveryAdjustmentParams.CONVERTED_CURRENCY_ID_PARAM,
                DashboardStoredProcParam.RecoveryAdjustmentParams.CONVERTED_CURRENCY_CODE_PARAM, DashboardStoredProcParam.RecoveryAdjustmentParams.CUSTOMER_IDS_CSV_PARAM,
                DashboardStoredProcParam.RecoveryAdjustmentParams.CARRIER_IDS_PARAM, DashboardStoredProcParam.RecoveryAdjustmentParams.MODES_PARAM,
                DashboardStoredProcParam.RecoveryAdjustmentParams.SERVICES_PARAM, DashboardStoredProcParam.RecoveryAdjustmentParams.LANES_PARAM,
                DashboardStoredProcParam.RecoveryAdjustmentParams.FROM_DATE_PARAM, DashboardStoredProcParam.RecoveryAdjustmentParams.TO_DATE_PARAM,
                DashboardStoredProcParam.RecoveryAdjustmentParams.ACCESSORIAL_NAME_PARAM, DashboardStoredProcParam.RecoveryAdjustmentParams.TOP_TEN_ACCESSORIAL_PARAM,
                DashboardStoredProcParam.RecoveryAdjustmentParams.SERVICE_PARAM
        };
        QueryParameter queryParameter = DashboardUtil.prepareDashboardFilterStoredProcParam(paramNames, filter);
        return persistentContext.findEntities(RecoveryAdjustmentDto.Config.StoredProcedureQueryName.RECOVERY_ADJUSTMENT_BY_CARRIER, queryParameter);
    }

    public List<RecoveryAdjustmentDto> getRecoveryAdjustmentByMonth(DashboardsFilterCriteria filter, boolean isTopTenAccessorial) {
        filter.setTopTenAccessorial(isTopTenAccessorial);
        String[] paramNames = {DashboardStoredProcParam.RecoveryAdjustmentParams.DATE_TYPE_PARAM, DashboardStoredProcParam.RecoveryAdjustmentParams.CONVERTED_CURRENCY_ID_PARAM,
                DashboardStoredProcParam.RecoveryAdjustmentParams.CONVERTED_CURRENCY_CODE_PARAM, DashboardStoredProcParam.RecoveryAdjustmentParams.CUSTOMER_IDS_CSV_PARAM,
                DashboardStoredProcParam.RecoveryAdjustmentParams.CARRIER_IDS_PARAM, DashboardStoredProcParam.RecoveryAdjustmentParams.MODES_PARAM,
                DashboardStoredProcParam.RecoveryAdjustmentParams.SERVICES_PARAM, DashboardStoredProcParam.RecoveryAdjustmentParams.LANES_PARAM,
                DashboardStoredProcParam.RecoveryAdjustmentParams.FROM_DATE_PARAM, DashboardStoredProcParam.RecoveryAdjustmentParams.TO_DATE_PARAM,
                DashboardStoredProcParam.RecoveryAdjustmentParams.ACCESSORIAL_NAME_PARAM, DashboardStoredProcParam.RecoveryAdjustmentParams.TOP_TEN_ACCESSORIAL_PARAM,
                DashboardStoredProcParam.RecoveryAdjustmentParams.SERVICE_PARAM
        };
        QueryParameter queryParameter = DashboardUtil.prepareDashboardFilterStoredProcParam(paramNames, filter);
        return persistentContext.findEntities(RecoveryAdjustmentDto.Config.StoredProcedureQueryName.RECOVERY_ADJUSTMENT_BY_MONTH, queryParameter);
    }

    /**
     * Method to get Recovery service details.
     *
     * @param filter
     * @param isTopTenAccessorial
     * @return
     */
    public List<RecoveryServiceDto> getRecoveryServices(DashboardsFilterCriteria filter, boolean isTopTenAccessorial) {
        filter.setTopTenAccessorial(isTopTenAccessorial);
        String[] paramNames = {DashboardStoredProcParam.RecoveryServiceParams.DATE_TYPE_PARAM, DashboardStoredProcParam.RecoveryServiceParams.CONVERTED_CURRENCY_ID_PARAM,
                DashboardStoredProcParam.RecoveryServiceParams.CONVERTED_CURRENCY_CODE_PARAM, DashboardStoredProcParam.RecoveryServiceParams.CUSTOMER_IDS_CSV_PARAM,
                DashboardStoredProcParam.RecoveryServiceParams.CARRIER_IDS_PARAM, DashboardStoredProcParam.RecoveryServiceParams.MODES_PARAM,
                DashboardStoredProcParam.RecoveryServiceParams.SERVICES_PARAM, DashboardStoredProcParam.RecoveryServiceParams.LANES_PARAM,
                DashboardStoredProcParam.RecoveryServiceParams.FROM_DATE_PARAM, DashboardStoredProcParam.RecoveryServiceParams.TO_DATE_PARAM,
                DashboardStoredProcParam.RecoveryServiceParams.ACCESSORIAL_NAME_PARAM, DashboardStoredProcParam.RecoveryServiceParams.MODE_NAMES_PARAM,
                DashboardStoredProcParam.RecoveryServiceParams.TOP_TEN_ACCESSORIAL_PARAM
        };
        QueryParameter queryParameter = DashboardUtil.prepareDashboardFilterStoredProcParam(paramNames, filter);
        return persistentContext.findEntities(RecoveryServiceDto.Config.StoredProcedureQueryName.RECOVERY_SERVICE, queryParameter);
    }

    /**
     * Method to get recovery service details by month.
     * @param filter
     * @param isTopTenAccessorial
     * @return
     */
    public List<RecoveryServiceDto> getRecoveryServicesByMonth(DashboardsFilterCriteria filter, boolean isTopTenAccessorial) {
        filter.setTopTenAccessorial(isTopTenAccessorial);
        String[] paramNames = {DashboardStoredProcParam.RecoveryServiceParams.DATE_TYPE_PARAM, DashboardStoredProcParam.RecoveryServiceParams.CONVERTED_CURRENCY_ID_PARAM,
                DashboardStoredProcParam.RecoveryServiceParams.CONVERTED_CURRENCY_CODE_PARAM, DashboardStoredProcParam.RecoveryServiceParams.CUSTOMER_IDS_CSV_PARAM,
                DashboardStoredProcParam.RecoveryServiceParams.CARRIER_IDS_PARAM, DashboardStoredProcParam.RecoveryServiceParams.MODES_PARAM,
                DashboardStoredProcParam.RecoveryServiceParams.SERVICES_PARAM, DashboardStoredProcParam.RecoveryServiceParams.LANES_PARAM,
                DashboardStoredProcParam.RecoveryServiceParams.FROM_DATE_PARAM, DashboardStoredProcParam.RecoveryServiceParams.TO_DATE_PARAM,
                DashboardStoredProcParam.RecoveryServiceParams.ACCESSORIAL_NAME_PARAM, DashboardStoredProcParam.RecoveryServiceParams.MODE_NAMES_PARAM,
                DashboardStoredProcParam.RecoveryServiceParams.TOP_TEN_ACCESSORIAL_PARAM, DashboardStoredProcParam.RecoveryServiceParams.SERVICE_PARAM
        };
        QueryParameter queryParameter = DashboardUtil.prepareDashboardFilterStoredProcParam(paramNames, filter);
        return persistentContext.findEntities(RecoveryServiceDto.Config.StoredProcedureQueryName.RECOVERY_SERVICE_BY_MONTH, queryParameter);
    }

    public List<PackageExceptionDto> getPackageExceptions(DashboardsFilterCriteria filter, boolean isTopTenAccessorial) {
        filter.setTopTenAccessorial(isTopTenAccessorial);
        String[] paramNames = {DashboardStoredProcParam.PackageExceptionParams.DATE_TYPE_PARAM, DashboardStoredProcParam.PackageExceptionParams.CUSTOMER_IDS_CSV_PARAM,
                DashboardStoredProcParam.PackageExceptionParams.CARRIER_IDS_PARAM, DashboardStoredProcParam.PackageExceptionParams.MODES_PARAM,
                DashboardStoredProcParam.PackageExceptionParams.SERVICES_PARAM, DashboardStoredProcParam.PackageExceptionParams.LANES_PARAM,
                DashboardStoredProcParam.PackageExceptionParams.FROM_DATE_PARAM, DashboardStoredProcParam.PackageExceptionParams.TO_DATE_PARAM,
                DashboardStoredProcParam.PackageExceptionParams.ACCESSORIAL_NAME_PARAM, DashboardStoredProcParam.PackageExceptionParams.TOP_TEN_ACCESSORIAL_PARAM
        };
        QueryParameter queryParameter = DashboardUtil.prepareDashboardFilterStoredProcParam(paramNames, filter);
        return persistentContext.findEntities(PackageExceptionDto.Config.StoredProcedureQueryName.PACKAGE_EXCEPTION, queryParameter);
    }

    @Transactional(readOnly = true)
    public List<ShipmentRegionDto> getShipmentByRegion(DashboardsFilterCriteria filter) {
        QueryParameter queryParameter = StoredProcedureParameter.with(DashboardStoredProcParam.ShipmentRegionParams.DATE_TYPE_PARAM, filter.getDateType())
                .and(DashboardStoredProcParam.ShipmentRegionParams.CUSTOMER_IDS_CSV_PARAM, filter.getCustomerIdsCSV())
                .and(DashboardStoredProcParam.ShipmentRegionParams.CARRIER_ID_PARAM, filter.getCarriers())
                .and(DashboardStoredProcParam.ShipmentRegionParams.MODES_PARAM, filter.getModes())
                .and(DashboardStoredProcParam.ShipmentRegionParams.SERVICES_PARAM, filter.getServices())
                .and(DashboardStoredProcParam.ShipmentRegionParams.LANES_PARAM, filter.getLanes())
                .and(DashboardStoredProcParam.ShipmentRegionParams.ACCESSORIAL_NAME_PARAM, filter.getAccessorialName())
                .and(DashboardStoredProcParam.ShipmentRegionParams.FROM_DATE_PARAM, filter.getFromDate())
                .and(DashboardStoredProcParam.ShipmentRegionParams.TO_DATE_PARAM, filter.getToDate())
                .and(DashboardStoredProcParam.ShipmentRegionParams.NO_OF_LANES_PARAM, filter.getNoOfLanes());
        return persistentContext.findEntities(ShipmentRegionDto.Config.StoredProcedureQueryName.SHIPMENT_BY_REGION, queryParameter);
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

    public void insertMapCoordinatesBatch(List<GenericObject> mapCoordinatesList) throws SQLException {
        QueryParameter queryParameter = StoredProcedureParameter.withPosition(1, ParameterMode.IN, GenericObject[].class, mapCoordinatesList);
        persistentContext.executeStoredProcedure("SHP_PAR_INS_LAT_LONG_BULK_PROC",queryParameter);

    }


    public List<ShipmentRegionDto> getShipmentRegionByCarrier(DashboardsFilterCriteria filterCriteria) {
        QueryParameter queryParameter = StoredProcedureParameter.with(DashboardStoredProcParam.ShipmentRegionParams.DATE_TYPE_PARAM, filterCriteria.getDateType())
                .and(DashboardStoredProcParam.ShipmentRegionParams.CONVERTED_CURRENCY_ID_PARAM, filterCriteria.getConvertCurrencyId())
                .and(DashboardStoredProcParam.ShipmentRegionParams.CONVERTED_CURRENCY_CODE_PARAM, filterCriteria.getConvertCurrencyCode())
                .and(DashboardStoredProcParam.ShipmentRegionParams.CUSTOMER_IDS_CSV_PARAM, filterCriteria.getCustomerIdsCSV())
                .and(DashboardStoredProcParam.ShipmentRegionParams.CARRIER_ID_PARAM, filterCriteria.getCarriers())
                .and(DashboardStoredProcParam.ShipmentRegionParams.MODES_PARAM, filterCriteria.getModes())
                .and(DashboardStoredProcParam.ShipmentRegionParams.SERVICES_PARAM, filterCriteria.getServices())
                .and(DashboardStoredProcParam.ShipmentRegionParams.LANES_PARAM, filterCriteria.getLanes())
                .and(DashboardStoredProcParam.ShipmentRegionParams.ACCESSORIAL_NAME_PARAM, filterCriteria.getAccessorialName())
                .and(DashboardStoredProcParam.ShipmentRegionParams.FROM_DATE_PARAM, filterCriteria.getFromDate())
                .and(DashboardStoredProcParam.ShipmentRegionParams.TO_DATE_PARAM, filterCriteria.getToDate())
                .and(DashboardStoredProcParam.ShipmentRegionParams.SHIPPER_CITY, filterCriteria.getShipperCity())
                .and(DashboardStoredProcParam.ShipmentRegionParams.RECEIVER_CITY, filterCriteria.getReceiverCity());
        return persistentContext.findEntities(ShipmentRegionDto.Config.StoredProcedureQueryName.SHIPMENT_BY_REGION_BY_CARRIER, queryParameter);
    }

    public List<ShipmentRegionDto> getShipmentRegionByMonth(DashboardsFilterCriteria filterCriteria) {
        QueryParameter queryParameter = StoredProcedureParameter.with(DashboardStoredProcParam.ShipmentRegionParams.DATE_TYPE_PARAM, filterCriteria.getDateType())
                .and(DashboardStoredProcParam.ShipmentRegionParams.CUSTOMER_IDS_CSV_PARAM, filterCriteria.getCustomerIdsCSV())
                .and(DashboardStoredProcParam.ShipmentRegionParams.CONVERTED_CURRENCY_ID_PARAM, filterCriteria.getConvertCurrencyId())
                .and(DashboardStoredProcParam.ShipmentRegionParams.CONVERTED_CURRENCY_CODE_PARAM, filterCriteria.getConvertCurrencyCode())
                .and(DashboardStoredProcParam.ShipmentRegionParams.CARRIER_ID_PARAM, filterCriteria.getCarriers())
                .and(DashboardStoredProcParam.ShipmentRegionParams.MODES_PARAM, filterCriteria.getModes())
                .and(DashboardStoredProcParam.ShipmentRegionParams.SERVICES_PARAM, filterCriteria.getServices())
                .and(DashboardStoredProcParam.ShipmentRegionParams.LANES_PARAM, filterCriteria.getLanes())
                .and(DashboardStoredProcParam.ShipmentRegionParams.ACCESSORIAL_NAME_PARAM, filterCriteria.getAccessorialName())
                .and(DashboardStoredProcParam.ShipmentRegionParams.FROM_DATE_PARAM, filterCriteria.getFromDate())
                .and(DashboardStoredProcParam.ShipmentRegionParams.TO_DATE_PARAM, filterCriteria.getToDate())
                .and(DashboardStoredProcParam.ShipmentRegionParams.SHIPPER_CITY, filterCriteria.getShipperCity())
                .and(DashboardStoredProcParam.ShipmentRegionParams.RECEIVER_CITY, filterCriteria.getReceiverCity());
        return persistentContext.findEntities(ShipmentRegionDto.Config.StoredProcedureQueryName.SHIPMENT_BY_REGION_BY_MONTH, queryParameter);
    }

    public List<ShippingLanesDto> getTopShippingLanes(DashboardsFilterCriteria filter) {
        QueryParameter queryParameter = StoredProcedureParameter.with(DashboardStoredProcParam.ShippingLanesParams.DATE_TYPE_PARAM, filter.getDateType())
                .and(DashboardStoredProcParam.ShippingLanesParams.CONVERTED_CURRENCY_ID_PARAM, filter.getConvertCurrencyId())
                .and(DashboardStoredProcParam.ShippingLanesParams.CUSTOMER_IDS_CSV_PARAM, filter.getCustomerIdsCSV())
                .and(DashboardStoredProcParam.ShippingLanesParams.CARRIER_ID_PARAM, filter.getCarriers())
                .and(DashboardStoredProcParam.ShippingLanesParams.MODES_PARAM, filter.getModes())
                .and(DashboardStoredProcParam.ShippingLanesParams.SERVICES_PARAM, filter.getServices())
                .and(DashboardStoredProcParam.ShippingLanesParams.LANES_PARAM, filter.getLanes())
                .and(DashboardStoredProcParam.ShippingLanesParams.FROM_DATE_PARAM, filter.getFromDate())
                .and(DashboardStoredProcParam.ShippingLanesParams.TO_DATE_PARAM, filter.getToDate());

        return persistentContext.findEntities(ShippingLanesDto.Config.StoredProcedureQueryName.SHIPPING_LANES, queryParameter);
    }

    public List<ShippingLanesDto> getShippingLanesByCarrier(DashboardsFilterCriteria filter) {
        QueryParameter queryParameter = StoredProcedureParameter.with(DashboardStoredProcParam.ShippingLanesParams.DATE_TYPE_PARAM, filter.getDateType())
                .and(DashboardStoredProcParam.ShippingLanesParams.CONVERTED_CURRENCY_ID_PARAM, filter.getConvertCurrencyId())
                .and(DashboardStoredProcParam.ShippingLanesParams.CUSTOMER_IDS_CSV_PARAM, filter.getCustomerIdsCSV())
                .and(DashboardStoredProcParam.ShippingLanesParams.CARRIER_ID_PARAM, filter.getCarriers())
                .and(DashboardStoredProcParam.ShippingLanesParams.MODES_PARAM, filter.getModes())
                .and(DashboardStoredProcParam.ShippingLanesParams.SERVICES_PARAM, filter.getServices())
                .and(DashboardStoredProcParam.ShippingLanesParams.LANES_PARAM, filter.getLanes())
                .and(DashboardStoredProcParam.ShippingLanesParams.FROM_DATE_PARAM, filter.getFromDate())
                .and(DashboardStoredProcParam.ShippingLanesParams.TO_DATE_PARAM, filter.getToDate())
                .and(DashboardStoredProcParam.ShippingLanesParams.SHIPPER_CITY, filter.getShipperAddress().split(",")[0].replace("'", "''"))
                .and(DashboardStoredProcParam.ShippingLanesParams.SHIPPER_STATE, filter.getShipperAddress().split(",")[1].replace("'", "''"))
                .and(DashboardStoredProcParam.ShippingLanesParams.SHIPPER_COUNTRY, filter.getShipperAddress().split(",")[2].replace("'", "''"))
                .and(DashboardStoredProcParam.ShippingLanesParams.RECEIVER_CITY, filter.getReceiverAddress().split(",")[0].replace("'", "''"))
                .and(DashboardStoredProcParam.ShippingLanesParams.RECEIVER_STATE, filter.getReceiverAddress().split(",")[1].replace("'", "''"))
                .and(DashboardStoredProcParam.ShippingLanesParams.RECEIVER_COUNTRY, filter.getReceiverAddress().split(",")[2].replace("'", "''"));

        return persistentContext.findEntities(ShippingLanesDto.Config.StoredProcedureQueryName.SHIPPING_LANES_BY_CARRIER, queryParameter);
    }

    public List<ShippingLanesDto> getShippingLanesByMonth(DashboardsFilterCriteria filter) {
        QueryParameter queryParameter = StoredProcedureParameter.with(DashboardStoredProcParam.ShippingLanesParams.DATE_TYPE_PARAM, filter.getDateType())
                .and(DashboardStoredProcParam.ShippingLanesParams.CONVERTED_CURRENCY_ID_PARAM, filter.getConvertCurrencyId())
                .and(DashboardStoredProcParam.ShippingLanesParams.CUSTOMER_IDS_CSV_PARAM, filter.getCustomerIdsCSV())
                .and(DashboardStoredProcParam.ShippingLanesParams.CARRIER_ID_PARAM, filter.getCarriers())
                .and(DashboardStoredProcParam.ShippingLanesParams.MODES_PARAM, filter.getModes())
                .and(DashboardStoredProcParam.ShippingLanesParams.SERVICES_PARAM, filter.getServices())
                .and(DashboardStoredProcParam.ShippingLanesParams.LANES_PARAM, filter.getLanes())
                .and(DashboardStoredProcParam.ShippingLanesParams.FROM_DATE_PARAM, filter.getFromDate())
                .and(DashboardStoredProcParam.ShippingLanesParams.TO_DATE_PARAM, filter.getToDate())
                .and(DashboardStoredProcParam.ShippingLanesParams.SHIPPER_CITY, filter.getShipperAddress().split(",")[0].replace("'", "''"))
                .and(DashboardStoredProcParam.ShippingLanesParams.SHIPPER_STATE, filter.getShipperAddress().split(",")[1].replace("'", "''"))
                .and(DashboardStoredProcParam.ShippingLanesParams.SHIPPER_COUNTRY, filter.getShipperAddress().split(",")[2].replace("'", "''"))
                .and(DashboardStoredProcParam.ShippingLanesParams.RECEIVER_CITY, filter.getReceiverAddress().split(",")[0].replace("'", "''"))
                .and(DashboardStoredProcParam.ShippingLanesParams.RECEIVER_STATE, filter.getReceiverAddress().split(",")[1].replace("'", "''"))
                .and(DashboardStoredProcParam.ShippingLanesParams.RECEIVER_COUNTRY, filter.getReceiverAddress().split(",")[2].replace("'", "''"));

        return persistentContext.findEntities(ShippingLanesDto.Config.StoredProcedureQueryName.SHIPPING_LANES_BY_MONTH, queryParameter);
    }

    public List<PortLanesDto> getTopPortLanes(DashboardsFilterCriteria filter) {
        String[] paramNames = {
                DashboardStoredProcParam.PortLanesParams.DATE_TYPE_PARAM, DashboardStoredProcParam.PortLanesParams.CONVERTED_CURRENCY_ID_PARAM,
                DashboardStoredProcParam.PortLanesParams.CUSTOMER_IDS_CSV_PARAM, DashboardStoredProcParam.PortLanesParams.CARRIER_ID_PARAM,
                DashboardStoredProcParam.PortLanesParams.MODES_PARAM, DashboardStoredProcParam.PortLanesParams.SERVICES_PARAM,
                DashboardStoredProcParam.PortLanesParams.LANES_PARAM, DashboardStoredProcParam.PortLanesParams.FROM_DATE_PARAM,
                DashboardStoredProcParam.PortLanesParams.TO_DATE_PARAM
        };
        QueryParameter queryParameter = DashboardUtil.prepareDashboardFilterStoredProcParam(paramNames, filter);
        return persistentContext.findEntities(PortLanesDto.Config.StoredProcedureQueryName.TOP_PORT_LANES, queryParameter);
    }

    public List<PortLanesDto> getPortLanesByCarrier(DashboardsFilterCriteria filter) {
        String[] paramNames = {
                DashboardStoredProcParam.PortLanesParams.DATE_TYPE_PARAM, DashboardStoredProcParam.PortLanesParams.CONVERTED_CURRENCY_ID_PARAM,
                DashboardStoredProcParam.PortLanesParams.CUSTOMER_IDS_CSV_PARAM, DashboardStoredProcParam.PortLanesParams.CARRIER_ID_PARAM,
                DashboardStoredProcParam.PortLanesParams.MODES_PARAM, DashboardStoredProcParam.PortLanesParams.SERVICES_PARAM,
                DashboardStoredProcParam.PortLanesParams.LANES_PARAM, DashboardStoredProcParam.PortLanesParams.FROM_DATE_PARAM,
                DashboardStoredProcParam.PortLanesParams.TO_DATE_PARAM, DashboardStoredProcParam.PortLanesParams.POL,
                DashboardStoredProcParam.PortLanesParams.POD
        };
        QueryParameter queryParameter = DashboardUtil.prepareDashboardFilterStoredProcParam(paramNames, filter);
        return persistentContext.findEntities(PortLanesDto.Config.StoredProcedureQueryName.TOP_PORT_LANES_BY_CARRIER, queryParameter);
    }

    public List<PortLanesDto> getPortLanesByMonth(DashboardsFilterCriteria filter) {
        String[] paramNames = {
                DashboardStoredProcParam.PortLanesParams.DATE_TYPE_PARAM, DashboardStoredProcParam.PortLanesParams.CONVERTED_CURRENCY_ID_PARAM,
                DashboardStoredProcParam.PortLanesParams.CUSTOMER_IDS_CSV_PARAM, DashboardStoredProcParam.PortLanesParams.CARRIER_ID_PARAM,
                DashboardStoredProcParam.PortLanesParams.MODES_PARAM, DashboardStoredProcParam.PortLanesParams.SERVICES_PARAM,
                DashboardStoredProcParam.PortLanesParams.LANES_PARAM, DashboardStoredProcParam.PortLanesParams.FROM_DATE_PARAM,
                DashboardStoredProcParam.PortLanesParams.TO_DATE_PARAM, DashboardStoredProcParam.PortLanesParams.POL,
                DashboardStoredProcParam.PortLanesParams.POD
        };
        QueryParameter queryParameter = DashboardUtil.prepareDashboardFilterStoredProcParam(paramNames, filter);
        return persistentContext.findEntities(PortLanesDto.Config.StoredProcedureQueryName.TOP_PORT_LANES_BY_MONTH, queryParameter);
    }

    public List<PackageExceptionDto> getPackageExceptionsByCarrier(DashboardsFilterCriteria filter, boolean isTopTenAccessorial) {
        filter.setTopTenAccessorial(isTopTenAccessorial);
        String[] paramNames = {DashboardStoredProcParam.PackageExceptionParams.DATE_TYPE_PARAM, DashboardStoredProcParam.PackageExceptionParams.CUSTOMER_IDS_CSV_PARAM,
                DashboardStoredProcParam.PackageExceptionParams.CARRIER_IDS_PARAM, DashboardStoredProcParam.PackageExceptionParams.MODES_PARAM,
                DashboardStoredProcParam.PackageExceptionParams.SERVICES_PARAM, DashboardStoredProcParam.PackageExceptionParams.LANES_PARAM,
                DashboardStoredProcParam.PackageExceptionParams.FROM_DATE_PARAM, DashboardStoredProcParam.PackageExceptionParams.TO_DATE_PARAM,
                DashboardStoredProcParam.PackageExceptionParams.ACCESSORIAL_NAME_PARAM, DashboardStoredProcParam.PackageExceptionParams.TOP_TEN_ACCESSORIAL_PARAM,
                DashboardStoredProcParam.PackageExceptionParams.DELIVERY_FLAG_PARAM
        };
        QueryParameter queryParameter = DashboardUtil.prepareDashboardFilterStoredProcParam(paramNames, filter);
        return persistentContext.findEntities(PackageExceptionDto.Config.StoredProcedureQueryName.PACKAGE_EXCEPTION_BY_CARRIER, queryParameter);
    }

    public List<PackageExceptionDto> getPackageExceptionsByMonth(DashboardsFilterCriteria filter, boolean isTopTenAccessorial) {
        filter.setTopTenAccessorial(isTopTenAccessorial);
        String[] paramNames = {DashboardStoredProcParam.PackageExceptionParams.DATE_TYPE_PARAM, DashboardStoredProcParam.PackageExceptionParams.CUSTOMER_IDS_CSV_PARAM,
                DashboardStoredProcParam.PackageExceptionParams.CARRIER_IDS_PARAM, DashboardStoredProcParam.PackageExceptionParams.MODES_PARAM,
                DashboardStoredProcParam.PackageExceptionParams.SERVICES_PARAM, DashboardStoredProcParam.PackageExceptionParams.LANES_PARAM,
                DashboardStoredProcParam.PackageExceptionParams.FROM_DATE_PARAM, DashboardStoredProcParam.PackageExceptionParams.TO_DATE_PARAM,
                DashboardStoredProcParam.PackageExceptionParams.ACCESSORIAL_NAME_PARAM, DashboardStoredProcParam.PackageExceptionParams.TOP_TEN_ACCESSORIAL_PARAM,
                DashboardStoredProcParam.PackageExceptionParams.DELIVERY_FLAG_PARAM
        };
        QueryParameter queryParameter = DashboardUtil.prepareDashboardFilterStoredProcParam(paramNames, filter);
        return persistentContext.findEntities(PackageExceptionDto.Config.StoredProcedureQueryName.PACKAGE_EXCEPTION_BY_MONTH, queryParameter);
    }

    /**
     * @param filter
     * @param isTopTenAccessorial
     * @return
     */
    public List<AverageSpendPerShipmentDto> getAverageSpendPerShipmentByCarrier(DashboardsFilterCriteria filter, boolean isTopTenAccessorial) {
        filter.setTopTenAccessorial(isTopTenAccessorial);
        String[] paramNames = {
                DashboardStoredProcParam.AverageSpendShipmentByCarrierParam.DATE_TYPE_PARAM, DashboardStoredProcParam.AverageSpendShipmentByCarrierParam.CONVERTED_CURRENCY_ID_PARAM,
                DashboardStoredProcParam.AverageSpendShipmentByCarrierParam.CONVERTED_CURRENCY_CODE_PARAM, DashboardStoredProcParam.AverageSpendShipmentByCarrierParam.MODES_PARAM,
                DashboardStoredProcParam.AverageSpendShipmentByCarrierParam.SERVICES_PARAM, DashboardStoredProcParam.AverageSpendShipmentByCarrierParam.ACCESSORIAL_NAME_PARAM,
                DashboardStoredProcParam.AverageSpendShipmentByCarrierParam.LANES_PARAM, DashboardStoredProcParam.AverageSpendShipmentByCarrierParam.FROM_DATE_PARAM,
                DashboardStoredProcParam.AverageSpendShipmentByCarrierParam.TO_DATE_PARAM, DashboardStoredProcParam.AverageSpendShipmentByCarrierParam.CARRIER_IDS_PARAM,
                DashboardStoredProcParam.AverageSpendShipmentByCarrierParam.CUSTOMER_IDS_CSV_PARAM, DashboardStoredProcParam.AverageSpendShipmentByCarrierParam.ORIGINAL_FROM_DATE_PARAM,
                DashboardStoredProcParam.AverageSpendShipmentByCarrierParam.ORIGINAL_TO_DATE_PARAM, DashboardStoredProcParam.AverageSpendShipmentByCarrierParam.TOP_TEN_ACCESSORIAL_PARAM,
                DashboardStoredProcParam.AverageSpendShipmentByCarrierParam.MODE_NAMES_PARAM
        };
        QueryParameter queryParameter = DashboardUtil.prepareDashboardFilterStoredProcParam(paramNames, filter);
        return persistentContext.findEntities(AverageSpendPerShipmentDto.Config.StoredProcedureQueryName.AVG_SPEND_PER_SHIPMENT_BY_CARRIER, queryParameter);
    }

    /**
     * @param filter
     * @param isTopTenAccessorial
     * @return
     */
    public List<AverageSpendPerShipmentDto> getAverageSpendPerShipmentByMonth(DashboardsFilterCriteria filter, boolean isTopTenAccessorial) {
        filter.setTopTenAccessorial(isTopTenAccessorial);
        String[] paramNames = {
                DashboardStoredProcParam.AverageSpendShipmentByMonthParam.DATE_TYPE_PARAM, DashboardStoredProcParam.AverageSpendShipmentByMonthParam.CONVERTED_CURRENCY_ID_PARAM,
                DashboardStoredProcParam.AverageSpendShipmentByMonthParam.CONVERTED_CURRENCY_CODE_PARAM, DashboardStoredProcParam.AverageSpendShipmentByMonthParam.MODES_PARAM,
                DashboardStoredProcParam.AverageSpendShipmentByMonthParam.SERVICES_PARAM, DashboardStoredProcParam.AverageSpendShipmentByMonthParam.ACCESSORIAL_NAME_PARAM,
                DashboardStoredProcParam.AverageSpendShipmentByMonthParam.LANES_PARAM, DashboardStoredProcParam.AverageSpendShipmentByMonthParam.FROM_DATE_PARAM,
                DashboardStoredProcParam.AverageSpendShipmentByMonthParam.TO_DATE_PARAM, DashboardStoredProcParam.AverageSpendShipmentByMonthParam.CARRIER_IDS_PARAM,
                DashboardStoredProcParam.AverageSpendShipmentByMonthParam.CUSTOMER_IDS_CSV_PARAM, DashboardStoredProcParam.AverageSpendShipmentByMonthParam.TOP_TEN_ACCESSORIAL_PARAM,
                DashboardStoredProcParam.AverageSpendShipmentByCarrierParam.MODE_NAMES_PARAM
        };
        QueryParameter queryParameter = DashboardUtil.prepareDashboardFilterStoredProcParam(paramNames, filter);
        return persistentContext.findEntities(AverageSpendPerShipmentDto.Config.StoredProcedureQueryName.AVG_SPEND_PER_SHIPMENT_BY_MONTH, queryParameter);
    }

    /**
     *
     * @param filter
     * @param isTopTenAccessorial
     * @return
     */
    public List<AverageWeightModeShipmtDto> getAverageWeightModeByCarrier(DashboardsFilterCriteria filter, boolean isTopTenAccessorial){
        filter.setTopTenAccessorial(isTopTenAccessorial);
        String[] paramNames = {
                DashboardStoredProcParam.AverageWeightShipmentByCarrierParam.DATE_TYPE_PARAM, DashboardStoredProcParam.AverageWeightShipmentByCarrierParam.MODES_PARAM,
                DashboardStoredProcParam.AverageWeightShipmentByCarrierParam.SERVICES_PARAM, DashboardStoredProcParam.AverageWeightShipmentByCarrierParam.ACCESSORIAL_NAME_PARAM,
                DashboardStoredProcParam.AverageWeightShipmentByCarrierParam.LANES_PARAM, DashboardStoredProcParam.AverageWeightShipmentByCarrierParam.FROM_DATE_PARAM,
                DashboardStoredProcParam.AverageWeightShipmentByCarrierParam.TO_DATE_PARAM, DashboardStoredProcParam.AverageWeightShipmentByCarrierParam.ORIGINAL_FROM_DATE_PARAM,
                DashboardStoredProcParam.AverageWeightShipmentByCarrierParam.ORIGINAL_TO_DATE_PARAM, DashboardStoredProcParam.AverageWeightShipmentByCarrierParam.CONVERTED_WEIGHT_UNIT_PARAM,
                DashboardStoredProcParam.AverageWeightShipmentByCarrierParam.CARRIER_IDS_PARAM, DashboardStoredProcParam.AverageWeightShipmentByCarrierParam.CUSTOMER_IDS_CSV_PARAM,
                DashboardStoredProcParam.AverageWeightShipmentByCarrierParam.TOP_TEN_ACCESSORIAL_PARAM, DashboardStoredProcParam.AverageWeightShipmentByCarrierParam.MODE_NAMES_PARAM
        };
        QueryParameter queryParameter = DashboardUtil.prepareDashboardFilterStoredProcParam(paramNames, filter);
        return persistentContext.findEntities(AverageWeightModeShipmtDto.Config.StoredProcedureQueryName.AVG_WEIGHT_MODE_SHIPMENT_BY_CARRIER, queryParameter);
    }

    /**
     *
     * @param filter
     * @param isTopTenAccessorial
     * @return
     */
    public List<AverageWeightModeShipmtDto> getAverageWeightModeByMonth(DashboardsFilterCriteria filter, boolean isTopTenAccessorial){
        filter.setTopTenAccessorial(isTopTenAccessorial);
        String[] paramNames = {
                DashboardStoredProcParam.AverageWeightShipmentByMonthParam.DATE_TYPE_PARAM, DashboardStoredProcParam.AverageWeightShipmentByMonthParam.MODES_PARAM,
                DashboardStoredProcParam.AverageWeightShipmentByMonthParam.SERVICES_PARAM, DashboardStoredProcParam.AverageWeightShipmentByMonthParam.ACCESSORIAL_NAME_PARAM,
                DashboardStoredProcParam.AverageWeightShipmentByMonthParam.LANES_PARAM, DashboardStoredProcParam.AverageWeightShipmentByMonthParam.FROM_DATE_PARAM,
                DashboardStoredProcParam.AverageWeightShipmentByMonthParam.TO_DATE_PARAM, DashboardStoredProcParam.AverageWeightShipmentByMonthParam.CONVERTED_WEIGHT_UNIT_PARAM,
                DashboardStoredProcParam.AverageWeightShipmentByMonthParam.CARRIER_IDS_PARAM, DashboardStoredProcParam.AverageWeightShipmentByMonthParam.CUSTOMER_IDS_CSV_PARAM,
                DashboardStoredProcParam.AverageWeightShipmentByMonthParam.TOP_TEN_ACCESSORIAL_PARAM, DashboardStoredProcParam.AverageWeightShipmentByMonthParam.MODE_NAMES_PARAM
        };
        QueryParameter queryParameter = DashboardUtil.prepareDashboardFilterStoredProcParam(paramNames, filter);
        return persistentContext.findEntities(AverageWeightModeShipmtDto.Config.StoredProcedureQueryName.AVG_WEIGHT_MODE_SHIPMENT_BY_MONTH, queryParameter);
    }


    /**
     * Method to get Annual summary.
     * @param filter
     * @param isTopTenAccessorial
     * @return
     */
    public List<AnnualSummaryDto> getAnnualSummary(DashboardsFilterCriteria filter, boolean isTopTenAccessorial) {
        filter.setTopTenAccessorial(isTopTenAccessorial);
        String[] paramNames = {DashboardStoredProcParam.AnnualSummaryParams.DATE_TYPE_PARAM, DashboardStoredProcParam.AnnualSummaryParams.CONVERTED_CURRENCY_ID_PARAM,
                DashboardStoredProcParam.AnnualSummaryParams.CONVERTED_CURRENCY_CODE_PARAM, DashboardStoredProcParam.AnnualSummaryParams.CUSTOMER_IDS_CSV_PARAM,
                DashboardStoredProcParam.AnnualSummaryParams.CARRIER_IDS_PARAM, DashboardStoredProcParam.AnnualSummaryParams.MODES_PARAM,
                DashboardStoredProcParam.AnnualSummaryParams.SERVICES_PARAM, DashboardStoredProcParam.AnnualSummaryParams.LANES_PARAM,
                DashboardStoredProcParam.AnnualSummaryParams.FROM_DATE_PARAM, DashboardStoredProcParam.AnnualSummaryParams.TO_DATE_PARAM,
                DashboardStoredProcParam.AnnualSummaryParams.ACCESSORIAL_NAME_PARAM, DashboardStoredProcParam.AnnualSummaryParams.TOP_TEN_ACCESSORIAL_PARAM
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
        String[] paramNames = {DashboardStoredProcParam.AnnualSummaryParams.DATE_TYPE_PARAM, DashboardStoredProcParam.AnnualSummaryParams.CONVERTED_CURRENCY_ID_PARAM,
                DashboardStoredProcParam.AnnualSummaryParams.CONVERTED_CURRENCY_CODE_PARAM, DashboardStoredProcParam.AnnualSummaryParams.CUSTOMER_IDS_CSV_PARAM,
                DashboardStoredProcParam.AnnualSummaryParams.CARRIER_IDS_PARAM, DashboardStoredProcParam.AnnualSummaryParams.MODES_PARAM,
                DashboardStoredProcParam.AnnualSummaryParams.SERVICES_PARAM, DashboardStoredProcParam.AnnualSummaryParams.LANES_PARAM,
                DashboardStoredProcParam.AnnualSummaryParams.FROM_DATE_PARAM, DashboardStoredProcParam.AnnualSummaryParams.TO_DATE_PARAM,
                DashboardStoredProcParam.AnnualSummaryParams.ACCESSORIAL_NAME_PARAM, DashboardStoredProcParam.AnnualSummaryParams.TOP_TEN_ACCESSORIAL_PARAM,
                DashboardStoredProcParam.AnnualSummaryParams.SERVICE_PARAM
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
        String[] paramNames = {DashboardStoredProcParam.AnnualSummaryParams.DATE_TYPE_PARAM, DashboardStoredProcParam.AnnualSummaryParams.CONVERTED_CURRENCY_ID_PARAM,
                DashboardStoredProcParam.AnnualSummaryParams.CONVERTED_CURRENCY_CODE_PARAM, DashboardStoredProcParam.AnnualSummaryParams.CUSTOMER_IDS_CSV_PARAM,
                DashboardStoredProcParam.AnnualSummaryParams.CARRIER_IDS_PARAM, DashboardStoredProcParam.AnnualSummaryParams.MODES_PARAM,
                DashboardStoredProcParam.AnnualSummaryParams.SERVICES_PARAM, DashboardStoredProcParam.AnnualSummaryParams.LANES_PARAM,
                DashboardStoredProcParam.AnnualSummaryParams.FROM_DATE_PARAM, DashboardStoredProcParam.AnnualSummaryParams.TO_DATE_PARAM,
                DashboardStoredProcParam.AnnualSummaryParams.ACCESSORIAL_NAME_PARAM, DashboardStoredProcParam.AnnualSummaryParams.TOP_TEN_ACCESSORIAL_PARAM,
                DashboardStoredProcParam.AnnualSummaryParams.SERVICE_PARAM, DashboardStoredProcParam.AnnualSummaryParams.MODE_NAMES_PARAM
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
        String[] paramNames = {DashboardStoredProcParam.AnnualSummaryParams.DATE_TYPE_PARAM, DashboardStoredProcParam.AnnualSummaryParams.CONVERTED_CURRENCY_ID_PARAM,
                DashboardStoredProcParam.AnnualSummaryParams.CONVERTED_CURRENCY_CODE_PARAM, DashboardStoredProcParam.AnnualSummaryParams.CUSTOMER_IDS_CSV_PARAM,
                DashboardStoredProcParam.AnnualSummaryParams.CARRIER_IDS_PARAM, DashboardStoredProcParam.AnnualSummaryParams.MODES_PARAM,
                DashboardStoredProcParam.AnnualSummaryParams.SERVICES_PARAM, DashboardStoredProcParam.AnnualSummaryParams.LANES_PARAM,
                DashboardStoredProcParam.AnnualSummaryParams.FROM_DATE_PARAM, DashboardStoredProcParam.AnnualSummaryParams.TO_DATE_PARAM,
                DashboardStoredProcParam.AnnualSummaryParams.ACCESSORIAL_NAME_PARAM, DashboardStoredProcParam.AnnualSummaryParams.TOP_TEN_ACCESSORIAL_PARAM,
                DashboardStoredProcParam.AnnualSummaryParams.SERVICE_PARAM, DashboardStoredProcParam.AnnualSummaryParams.MODE_NAMES_PARAM
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
        String[] paramNames = {DashboardStoredProcParam.MonthlySpendByModeParams.DATE_TYPE_PARAM, DashboardStoredProcParam.MonthlySpendByModeParams.CONVERTED_CURRENCY_ID_PARAM,
                DashboardStoredProcParam.MonthlySpendByModeParams.CONVERTED_CURRENCY_CODE_PARAM, DashboardStoredProcParam.MonthlySpendByModeParams.CUSTOMER_IDS_CSV_PARAM,
                DashboardStoredProcParam.MonthlySpendByModeParams.CARRIER_IDS_PARAM, DashboardStoredProcParam.MonthlySpendByModeParams.MODES_PARAM,
                DashboardStoredProcParam.MonthlySpendByModeParams.SERVICES_PARAM, DashboardStoredProcParam.MonthlySpendByModeParams.LANES_PARAM,
                DashboardStoredProcParam.MonthlySpendByModeParams.FROM_DATE_PARAM, DashboardStoredProcParam.MonthlySpendByModeParams.TO_DATE_PARAM,
                DashboardStoredProcParam.MonthlySpendByModeParams.ACCESSORIAL_NAME_PARAM, DashboardStoredProcParam.MonthlySpendByModeParams.TOP_TEN_ACCESSORIAL_PARAM
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
        String[] paramNames = {DashboardStoredProcParam.MonthlySpendByModeParams.DATE_TYPE_PARAM, DashboardStoredProcParam.MonthlySpendByModeParams.CONVERTED_CURRENCY_ID_PARAM,
                DashboardStoredProcParam.MonthlySpendByModeParams.CONVERTED_CURRENCY_CODE_PARAM, DashboardStoredProcParam.MonthlySpendByModeParams.CUSTOMER_IDS_CSV_PARAM,
                DashboardStoredProcParam.MonthlySpendByModeParams.CARRIER_IDS_PARAM, DashboardStoredProcParam.MonthlySpendByModeParams.MODES_PARAM,
                DashboardStoredProcParam.MonthlySpendByModeParams.SERVICES_PARAM, DashboardStoredProcParam.MonthlySpendByModeParams.LANES_PARAM,
                DashboardStoredProcParam.MonthlySpendByModeParams.FROM_DATE_PARAM, DashboardStoredProcParam.MonthlySpendByModeParams.TO_DATE_PARAM,
                DashboardStoredProcParam.MonthlySpendByModeParams.ACCESSORIAL_NAME_PARAM, DashboardStoredProcParam.MonthlySpendByModeParams.TOP_TEN_ACCESSORIAL_PARAM,
                DashboardStoredProcParam.MonthlySpendByModeParams.SERVICE_PARAM
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
        String[] paramNames = {DashboardStoredProcParam.AccountSummaryParams.DATE_TYPE_PARAM, DashboardStoredProcParam.AccountSummaryParams.CONVERTED_CURRENCY_ID_PARAM,
                DashboardStoredProcParam.AccountSummaryParams.CONVERTED_CURRENCY_CODE_PARAM, DashboardStoredProcParam.AccountSummaryParams.CUSTOMER_IDS_CSV_PARAM,
                DashboardStoredProcParam.AccountSummaryParams.CARRIER_IDS_PARAM, DashboardStoredProcParam.AccountSummaryParams.MODES_PARAM,
                DashboardStoredProcParam.AccountSummaryParams.SERVICES_PARAM, DashboardStoredProcParam.AccountSummaryParams.LANES_PARAM,
                DashboardStoredProcParam.AccountSummaryParams.FROM_DATE_PARAM, DashboardStoredProcParam.AccountSummaryParams.TO_DATE_PARAM,
                DashboardStoredProcParam.AccountSummaryParams.ACCESSORIAL_NAME_PARAM, DashboardStoredProcParam.AccountSummaryParams.TOP_TEN_ACCESSORIAL_PARAM
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
        String[] paramNames = {DashboardStoredProcParam.AccountSummaryParams.DATE_TYPE_PARAM, DashboardStoredProcParam.AccountSummaryParams.CONVERTED_CURRENCY_ID_PARAM,
                DashboardStoredProcParam.AccountSummaryParams.CONVERTED_CURRENCY_CODE_PARAM, DashboardStoredProcParam.AccountSummaryParams.CUSTOMER_IDS_CSV_PARAM,
                DashboardStoredProcParam.AccountSummaryParams.CARRIER_IDS_PARAM, DashboardStoredProcParam.AccountSummaryParams.MODES_PARAM,
                DashboardStoredProcParam.AccountSummaryParams.SERVICES_PARAM, DashboardStoredProcParam.AccountSummaryParams.LANES_PARAM,
                DashboardStoredProcParam.AccountSummaryParams.FROM_DATE_PARAM, DashboardStoredProcParam.AccountSummaryParams.TO_DATE_PARAM,
                DashboardStoredProcParam.AccountSummaryParams.ACCESSORIAL_NAME_PARAM, DashboardStoredProcParam.AccountSummaryParams.TOP_TEN_ACCESSORIAL_PARAM
        };
        QueryParameter queryParameter = DashboardUtil.prepareDashboardFilterStoredProcParam(paramNames, filter);
        return persistentContext.findEntities(AccountSummaryDto.Config.StoredProcedureQueryName.PARCEL_ACCOUNT_SUMMARY, queryParameter);
    }

    public List<DashboardReportUtilityDataDto> getReportColumnNames(boolean isLineItemReport){
        QueryParameter queryParameter = StoredProcedureParameter.with(DashboardStoredProcParam.DashboardReportUtilityDataParams.IS_LINE_ITEM_REPORT_PARAM, isLineItemReport ? 1L : 0L);
        return persistentContext.findEntities(DashboardReportUtilityDataDto.Config.StoredProcedureQueryName.DASHBOARD_REPORT_COLUMNS, queryParameter);
    }

    /**
     * Get custom defined fields mapped with customer.
     * @param filter
     * @param reportId
     * @return
     */
    public List<DashboardReportUtilityDataDto> getCustomDefinedLabelsByCustomer(DashboardsFilterCriteria filter, Long reportId){
        String uniqueCustomerIds = "";
        Set<String> customersSet = new HashSet<String>();
        for(String customerId : filter.getCustomerIdsCSV().split(",")){
            customersSet.add(customerId);
        }
        uniqueCustomerIds = StringUtils.collectionToCommaDelimitedString(customersSet);
        QueryParameter queryParameter = StoredProcedureParameter.with(DashboardStoredProcParam.DashboardReportUtilityDataParams.CUSTOMER_IDS_CSV_PARAM, uniqueCustomerIds)
                .and(DashboardStoredProcParam.DashboardReportUtilityDataParams.REPORT_ID_PARAM, reportId);
        return persistentContext.findEntities(DashboardReportUtilityDataDto.Config.StoredProcedureQueryName.DASHBOARD_REPORT_CUST_DEF_LBL, queryParameter);
    }

    public int getDashboardReportTotalRecordCount(DashboardsFilterCriteria filter, Map<String, Object> paginationFilterMap){
        DashboardReportUtilityDataDto reportDataCount = persistentContext.findEntity(DashboardReportUtilityDataDto.Config.StoredProcedureQueryName.RECORD_COUNT, prepareDashboardReportQueryParam(filter, paginationFilterMap, true));
        return reportDataCount.getRecordCount();
    }

    public List<DashboardReportDto> getDashboardReport(DashboardsFilterCriteria filter, Map<String, Object> paginationFilterMap){
        return persistentContext.findEntities(DashboardReportDto.Config.StoredProcedureQueryName.PARCEL_AND_FREIGHT_REPORT, prepareDashboardReportQueryParam(filter, paginationFilterMap, false));
    }

    private QueryParameter prepareDashboardReportQueryParam(DashboardsFilterCriteria filter, Map<String, Object> paginationFilterMap, boolean forCount){
        QueryParameter queryParameter = StoredProcedureParameter.with(DashboardStoredProcParam.DashboardReportParams.DATE_TYPE_PARAM, filter.getDateType())
                .and(DashboardStoredProcParam.DashboardReportParams.CARRIER_IDS_PARAM, filter.getCarriers())
                .and(DashboardStoredProcParam.DashboardReportParams.DASHLETTE_NAME_PARAM, filter.getDashletteName())
                .and(DashboardStoredProcParam.DashboardReportParams.CONVERTED_CURRENCY_ID_PARAM, filter.getConvertCurrencyId())
                .and(DashboardStoredProcParam.DashboardReportParams.CONVERTED_CURRENCY_CODE_PARAM, filter.getConvertCurrencyCode())
                .and(DashboardStoredProcParam.DashboardReportParams.CUSTOMER_IDS_CSV_PARAM, filter.getCustomerIdsCSV())
                .and(DashboardStoredProcParam.DashboardReportParams.FROM_DATE_PARAM, filter.getFromDate())
                .and(DashboardStoredProcParam.DashboardReportParams.TO_DATE_PARAM, filter.getToDate())
                .and(DashboardStoredProcParam.DashboardReportParams.CONVERTED_WEIGHT_UNIT_PARAM, filter.getConvertWeightUnit())
                .and(DashboardStoredProcParam.DashboardReportParams.DELIVERY_FLAG_DESC_PARAM, filter.getDeliveryFlagDesc())
                .and(DashboardStoredProcParam.DashboardReportParams.BOUND_TYPE_PARAM, filter.getBoundType())
                .and(DashboardStoredProcParam.DashboardReportParams.POD, filter.getPod())
                .and(DashboardStoredProcParam.DashboardReportParams.POL, filter.getPol())
                .and(DashboardStoredProcParam.DashboardReportParams.MODES_PARAM, filter.getModes())
                .and(DashboardStoredProcParam.DashboardReportParams.MODE_NAMES_PARAM, filter.getModeNames())
                .and(DashboardStoredProcParam.DashboardReportParams.SERVICES_PARAM, filter.getServices())
                .and(DashboardStoredProcParam.DashboardReportParams.LANES_PARAM, filter.getLanes())
                .and(DashboardStoredProcParam.DashboardReportParams.TAX_PARAM, filter.getTax())
                .and(DashboardStoredProcParam.DashboardReportParams.ACC_DESC_PARAM, filter.getAccDesc())
                .and(DashboardStoredProcParam.DashboardReportParams.SERVICE_PARAM, filter.getService())
                .and(DashboardStoredProcParam.DashboardReportParams.INVOICE_STATUS_ID_PARAM, filter.getInvoiceStatusId())
                .and(DashboardStoredProcParam.DashboardReportParams.INVOICE_METHOD_PARAM, filter.getInvoiceMethod())
                .and(DashboardStoredProcParam.DashboardReportParams.ORDER_MATCH_PARAM, filter.getOrderMatch());

        if(filter.getShipperAddress() != null && !filter.getShipperAddress().isEmpty()){
            queryParameter.and(DashboardStoredProcParam.DashboardReportParams.SHIPPER_CITY_PARAM, filter.getShipperAddress().split(",")[0].replace("'", "''"))
                    .and(DashboardStoredProcParam.DashboardReportParams.SHIPPER_STATE_PARAM, filter.getShipperAddress().split(",")[1].replace("'", "''"))
                    .and(DashboardStoredProcParam.DashboardReportParams.SHIPPER_COUNTRY_PARAM, filter.getShipperAddress().split(",")[2].replace("'", "''"));
        }else{
            queryParameter.and(DashboardStoredProcParam.DashboardReportParams.SHIPPER_CITY_PARAM, "")
                    .and(DashboardStoredProcParam.DashboardReportParams.SHIPPER_STATE_PARAM, "")
                    .and(DashboardStoredProcParam.DashboardReportParams.SHIPPER_COUNTRY_PARAM, "");
        }
        if(filter.getReceiverAddress() != null && !filter.getReceiverAddress().isEmpty()){
            queryParameter.and(DashboardStoredProcParam.DashboardReportParams.RECEIVER_CITY_PARAM, filter.getReceiverAddress().split(",")[0].replace("'", "''"))
                    .and(DashboardStoredProcParam.DashboardReportParams.RECEIVER_STATE_PARAM, filter.getReceiverAddress().split(",")[1].replace("'", "''"))
                    .and(DashboardStoredProcParam.DashboardReportParams.RECEIVER_COUNTRY_PARAM, filter.getReceiverAddress().split(",")[2].replace("'", "''"));
        }else{
            queryParameter.and(DashboardStoredProcParam.DashboardReportParams.RECEIVER_CITY_PARAM, "")
                    .and(DashboardStoredProcParam.DashboardReportParams.RECEIVER_STATE_PARAM, "")
                    .and(DashboardStoredProcParam.DashboardReportParams.RECEIVER_COUNTRY_PARAM, "");
        }
        queryParameter.and(DashboardStoredProcParam.DashboardReportParams.REPORT_FOR_DASHLETTE_PARAM, filter.getReportForDashlette())
                .and(DashboardStoredProcParam.DashboardReportParams.PAGE_OFFSET_PARAM, filter.getOffset())
                .and(DashboardStoredProcParam.DashboardReportParams.PAGE_SIZE_PARAM, filter.getPageSize())
                .and(DashboardStoredProcParam.DashboardReportParams.REPORT_TOTAL_ROW_COUNT_PARAM, forCount ? 1 : 0);

        boolean searchFilterConditionAdded = false;
        if(paginationFilterMap != null){
            if(paginationFilterMap.containsKey(WebConstants.SEARCH_FILTER_CONDITION)){
                queryParameter.and(DashboardStoredProcParam.DashboardReportParams.SEARCH_FILTER_CONDITION_PARAM, paginationFilterMap.get(WebConstants.SEARCH_FILTER_CONDITION));
                searchFilterConditionAdded = true;
            }
        }
        if(!searchFilterConditionAdded){
            queryParameter.and(DashboardStoredProcParam.DashboardReportParams.SEARCH_FILTER_CONDITION_PARAM, "");
        }
        return queryParameter;
    }

    public List<DashboardReportDto> getLineItemReportDetails(DashboardsFilterCriteria filter){
        QueryParameter queryParameter = StoredProcedureParameter.with(DashboardStoredProcParam.DashboardReportParams.DATE_TYPE_PARAM, filter.getDateType())
                .and(DashboardStoredProcParam.DashboardReportParams.CARRIER_IDS_PARAM, filter.getCarriers())
                .and(DashboardStoredProcParam.DashboardReportParams.CONVERTED_CURRENCY_ID_PARAM, filter.getConvertCurrencyId())
                .and(DashboardStoredProcParam.DashboardReportParams.CONVERTED_CURRENCY_CODE_PARAM, filter.getConvertCurrencyCode())
                .and(DashboardStoredProcParam.DashboardReportParams.CUSTOMER_IDS_CSV_PARAM, filter.getCustomerIdsCSV())
                .and(DashboardStoredProcParam.DashboardReportParams.FROM_DATE_PARAM, filter.getFromDate())
                .and(DashboardStoredProcParam.DashboardReportParams.TO_DATE_PARAM, filter.getToDate())
                .and(DashboardStoredProcParam.DashboardReportParams.CONVERTED_WEIGHT_UNIT_PARAM, filter.getConvertWeightUnit())
                .and(DashboardStoredProcParam.DashboardReportParams.MODES_PARAM, filter.getModes())
                .and(DashboardStoredProcParam.DashboardReportParams.SERVICES_PARAM, filter.getServices())
                .and(DashboardStoredProcParam.DashboardReportParams.TAX_PARAM, filter.getTax())
                .and(DashboardStoredProcParam.DashboardReportParams.ACC_DESC_PARAM, filter.getAccDesc())
                .and(DashboardStoredProcParam.DashboardReportParams.PAGE_OFFSET_PARAM, filter.getOffset())
                .and(DashboardStoredProcParam.DashboardReportParams.PAGE_SIZE_PARAM, filter.getPageSize());

        return persistentContext.findEntities(DashboardReportDto.Config.StoredProcedureQueryName.LINE_ITEM_REPORT, queryParameter);
    }

    public List<DashboardReportUtilityDataDto> getColumnConfigByUser(Long userId, Long reportId){
        QueryParameter queryParameter = StoredProcedureParameter.with(DashboardStoredProcParam.DashboardReportUtilityDataParams.USER_ID_PARAM, userId)
                .and(DashboardStoredProcParam.DashboardReportUtilityDataParams.REPORT_ID_PARAM, reportId);
        return persistentContext.findEntities(DashboardReportUtilityDataDto.Config.StoredProcedureQueryName.DASHBOARD_REPORT_COL_CONFIG_BY_USER, queryParameter);
    }

    public List<ActualVsBilledWeightDto> getActualVsBilledWeight(DashboardsFilterCriteria filter, boolean isTopTenAccessorial){
        filter.setTopTenAccessorial(isTopTenAccessorial);
        String[] paramNames = {
                DashboardStoredProcParam.NetSpendParams.DATE_TYPE_PARAM, DashboardStoredProcParam.NetSpendParams.CUSTOMER_IDS_CSV_PARAM,
                DashboardStoredProcParam.NetSpendParams.CARRIER_IDS_PARAM, DashboardStoredProcParam.NetSpendParams.MODES_PARAM,
                DashboardStoredProcParam.NetSpendParams.SERVICES_PARAM, DashboardStoredProcParam.NetSpendParams.LANES_PARAM,
                DashboardStoredProcParam.NetSpendParams.ACCESSORIAL_NAME_PARAM, DashboardStoredProcParam.NetSpendParams.FROM_DATE_PARAM,
                DashboardStoredProcParam.NetSpendParams.TO_DATE_PARAM, DashboardStoredProcParam.NetSpendParams.TOP_TEN_ACCESSORIAL_PARAM
        };
        QueryParameter queryParameter = DashboardUtil.prepareDashboardFilterStoredProcParam(paramNames, filter);
        return persistentContext.findEntities(ActualVsBilledWeightDto.Config.StoredProcedureQueryName.ACTUAL_VS_BILLED_WEIGHT, queryParameter);
    }

    public List<ActualVsBilledWeightDto> getActualVsBilledWeightByCarrier(DashboardsFilterCriteria filter, boolean isTopTenAccessorial){
        filter.setTopTenAccessorial(isTopTenAccessorial);
        String[] paramNames = {
                DashboardStoredProcParam.NetSpendParams.DATE_TYPE_PARAM, DashboardStoredProcParam.NetSpendParams.CUSTOMER_IDS_CSV_PARAM,
                DashboardStoredProcParam.NetSpendParams.CARRIER_IDS_PARAM, DashboardStoredProcParam.NetSpendParams.MODES_PARAM,
                DashboardStoredProcParam.NetSpendParams.SERVICES_PARAM, DashboardStoredProcParam.NetSpendParams.LANES_PARAM,
                DashboardStoredProcParam.NetSpendParams.ACCESSORIAL_NAME_PARAM, DashboardStoredProcParam.NetSpendParams.FROM_DATE_PARAM,
                DashboardStoredProcParam.NetSpendParams.TO_DATE_PARAM, DashboardStoredProcParam.NetSpendParams.TOP_TEN_ACCESSORIAL_PARAM
        };
        QueryParameter queryParameter = DashboardUtil.prepareDashboardFilterStoredProcParam(paramNames, filter);
        return persistentContext.findEntities(ActualVsBilledWeightDto.Config.StoredProcedureQueryName.ACTUAL_VS_BILLED_WEIGHT_BY_CARRIER, queryParameter);
    }

    public List<ActualVsBilledWeightDto> getActualVsBilledWeightByMonth(DashboardsFilterCriteria filter, boolean isTopTenAccessorial){
        filter.setTopTenAccessorial(isTopTenAccessorial);
        String[] paramNames = {
                DashboardStoredProcParam.NetSpendParams.DATE_TYPE_PARAM, DashboardStoredProcParam.NetSpendParams.CUSTOMER_IDS_CSV_PARAM,
                DashboardStoredProcParam.NetSpendParams.CARRIER_IDS_PARAM, DashboardStoredProcParam.NetSpendParams.MODES_PARAM,
                DashboardStoredProcParam.NetSpendParams.SERVICES_PARAM, DashboardStoredProcParam.NetSpendParams.LANES_PARAM,
                DashboardStoredProcParam.NetSpendParams.ACCESSORIAL_NAME_PARAM, DashboardStoredProcParam.NetSpendParams.FROM_DATE_PARAM,
                DashboardStoredProcParam.NetSpendParams.TO_DATE_PARAM, DashboardStoredProcParam.NetSpendParams.TOP_TEN_ACCESSORIAL_PARAM
        };
        QueryParameter queryParameter = DashboardUtil.prepareDashboardFilterStoredProcParam(paramNames, filter);
        return persistentContext.findEntities(ActualVsBilledWeightDto.Config.StoredProcedureQueryName.ACTUAL_VS_BILLED_WEIGHT_BY_MONTH, queryParameter);
    }

    public DashSavedFilterDto getFilterById(Long filterId){
        QueryParameter queryParameter = StoredProcedureParameter.with(DashboardStoredProcParam.DashSavedFilterParam.FILTER_ID_PARAM, filterId);
        return persistentContext.findEntity(DashSavedFilterDto.Config.StoredProcedureQueryName.GET_FILTER_BY_ID, queryParameter);
    }

    public List<DashSavedFilterDto> getSavedFiltersByUser(Long userId){
        QueryParameter queryParameter = StoredProcedureParameter.with(DashboardStoredProcParam.DashSavedFilterParam.USER_ID_PARAM, userId);
        List<DashSavedFilterDto> userSavedFilters = persistentContext.findEntities(DashSavedFilterDto.Config.StoredProcedureQueryName.GET_FILTER_BY_USER, queryParameter);
        if(userSavedFilters != null && !userSavedFilters.isEmpty()){
            for(DashSavedFilterDto savedFilter :userSavedFilters){
                if(savedFilter != null){
                    persistentContext.getHibernateSession().evict(savedFilter);
                }
            }
        }
        return userSavedFilters;
    }

    public List<CodeValueDto> getCodeValuesByCodeGroup(Long codeGroupId){
        QueryParameter queryParameter = StoredProcedureParameter.with(DashboardStoredProcParam.CodeValueParam.CODE_GROUP_ID_PARAM, codeGroupId);
        return persistentContext.findEntities(CodeValueDto.Config.StoredProcedureQueryName.CODE_VALUE_BY_CODE_GROUP_ID, queryParameter);
    }

    public List<UserFilterUtilityDataDto> getCarrierByCustomer(String customerIds, boolean isParcelDashlettes){
        QueryParameter queryParameter = StoredProcedureParameter.with(DashboardStoredProcParam.UserFilterUtilityDataParam.CUSTOMER_IDS_CSV_PARAM, customerIds)
                .and(DashboardStoredProcParam.UserFilterUtilityDataParam.IS_PARCEL_DASHLETTES_PARAM, isParcelDashlettes ? 1 : 0);
        return persistentContext.findEntities(UserFilterUtilityDataDto.Config.StoredProcedureQueryName.CARRIER_BY_CUSTOMER, queryParameter);
    }

    public List<UserFilterUtilityDataDto> getFilterModes(DashboardsFilterCriteria filter, boolean isParcelDashlettes){
        String[] paramNames = {
                DashboardStoredProcParam.NetSpendParams.DATE_TYPE_PARAM, DashboardStoredProcParam.NetSpendParams.CUSTOMER_IDS_CSV_PARAM,
                DashboardStoredProcParam.NetSpendParams.CARRIER_IDS_PARAM
        };
        QueryParameter queryParameter = DashboardUtil.prepareDashboardFilterStoredProcParam(paramNames, filter);
        queryParameter.and(DashboardStoredProcParam.UserFilterUtilityDataParam.IS_PARCEL_DASHLETTES_PARAM, isParcelDashlettes ? 1 : 0);
        return persistentContext.findEntities(UserFilterUtilityDataDto.Config.StoredProcedureQueryName.MODES_BY_CARRIER, queryParameter);
    }

    public List<UserFilterUtilityDataDto> getFilterServices(DashboardsFilterCriteria filter, boolean isParcelDashlettes){
        filter.setTopTenAccessorial(false);
        String[] paramNames = {
                DashboardStoredProcParam.NetSpendParams.DATE_TYPE_PARAM, DashboardStoredProcParam.NetSpendParams.CUSTOMER_IDS_CSV_PARAM,
                DashboardStoredProcParam.NetSpendParams.CARRIER_IDS_PARAM, DashboardStoredProcParam.NetSpendParams.MODES_PARAM
        };
        QueryParameter queryParameter = DashboardUtil.prepareDashboardFilterStoredProcParam(paramNames, filter);
        queryParameter.and(DashboardStoredProcParam.UserFilterUtilityDataParam.IS_PARCEL_DASHLETTES_PARAM, isParcelDashlettes ? 1 : 0);
        return persistentContext.findEntities(UserFilterUtilityDataDto.Config.StoredProcedureQueryName.SERVICE_BY_GROUP, queryParameter);
    }

    public List<UserFilterUtilityDataDto> getCarrierDetailsByIds(String carrierIds){
        QueryParameter queryParameter = StoredProcedureParameter.with(DashboardStoredProcParam.UserFilterUtilityDataParam.CARRIER_IDS_PARAM, carrierIds);
        return persistentContext.findEntities(UserFilterUtilityDataDto.Config.StoredProcedureQueryName.CARRIER_BY_IDS, queryParameter);
    }

    public List<MapCoordinatesDto> getLocationGeoCoordinates(String[] addresses){
        List<MapCoordinatesDto> mapCoordinatesDtoList = null;
        try{
            for(int i = 0; i < addresses.length; i++){
                if(null == addresses[i]){
                    addresses[i] = "";
                }
            }
            QueryParameter queryParameter = StoredProcedureParameter.withPosition(1, ParameterMode.IN, String[].class, addresses)
                    .andPosition(2, ParameterMode.REF_CURSOR, void.class, null);
            List<List<Object>> mapCoordinateList = persistentContext.executeStoredProcedure("SHP_PARCEL_GEO_COORDINATE_PROC", queryParameter);
            if(mapCoordinateList != null && !mapCoordinateList.isEmpty()){
                mapCoordinatesDtoList = new ArrayList<MapCoordinatesDto>();
                for(List<Object> mapRec : mapCoordinateList){
                    if(mapRec != null){
                        MapCoordinatesDto mapCoordinatesDto = new MapCoordinatesDto();
                        mapCoordinatesDto.setAddress(mapRec.get(0) != null ? mapRec.get(0).toString() : "");
                        mapCoordinatesDto.setLatitude(mapRec.get(1) != null && !mapRec.get(1).toString().isEmpty() ? ((BigDecimal)mapRec.get(1)).doubleValue() : 0);
                        mapCoordinatesDto.setLongitude(mapRec.get(2) != null && !mapRec.get(2).toString().isEmpty() ? ((BigDecimal)mapRec.get(2)).doubleValue() : 0);
                        mapCoordinatesDtoList.add(mapCoordinatesDto);
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return mapCoordinatesDtoList;
    }

    public List<ShipmentDto> getShipmentCountByZone(DashboardsFilterCriteria filter){
        String[] paramNames = {
                DashboardStoredProcParam.ShipmentParam.DATE_TYPE_PARAM, DashboardStoredProcParam.ShipmentParam.CUSTOMER_IDS_CSV_PARAM,
                DashboardStoredProcParam.ShipmentParam.CARRIER_IDS_PARAM, DashboardStoredProcParam.ShipmentParam.MODES_PARAM,
                DashboardStoredProcParam.ShipmentParam.SERVICES_PARAM, DashboardStoredProcParam.ShipmentParam.LANES_PARAM,
                DashboardStoredProcParam.ShipmentParam.ACCESSORIAL_NAME_PARAM, DashboardStoredProcParam.ShipmentParam.FROM_DATE_PARAM,
                DashboardStoredProcParam.ShipmentParam.TO_DATE_PARAM, DashboardStoredProcParam.ShipmentParam.TOP_TEN_ACCESSORIAL_PARAM
        };
        QueryParameter queryParameter = DashboardUtil.prepareDashboardFilterStoredProcParam(paramNames, filter);
        return persistentContext.findEntities(ShipmentDto.Config.StoredProcedureQueryName.SHIPMENT_BY_ZONE, queryParameter);
    }

    public List<ReportCustomerCarrierDto> getDashboardCustomers(long userId){
        return persistentContext.findEntities("ReportCustomerCarrierDto.getDashboardCustomers", QueryParameter.with("p_user_id", userId));
    }

    @Transactional
    public void saveAppliedFilterDetails(DashboardAppliedFilterDto appliedFilter) {
        try{
            QueryParameter queryParameter = QueryParameter.with(DashboardStoredProcParam.AppliedFilterParam.CUSTOMER_IDS_CSV_PARAM, appliedFilter.getCustomerIds())
                    .and(DashboardStoredProcParam.AppliedFilterParam.CARRIER_IDS_PARAM, appliedFilter.getCarrierIds())
                    .and(DashboardStoredProcParam.AppliedFilterParam.FROM_DATE_PARAM, appliedFilter.getFromDate())
                    .and(DashboardStoredProcParam.AppliedFilterParam.TO_DATE_PARAM, appliedFilter.getToDate())
                    .and(DashboardStoredProcParam.AppliedFilterParam.MODES_PARAM, appliedFilter.getModes())
                    .and(DashboardStoredProcParam.AppliedFilterParam.SERVICES_PARAM, appliedFilter.getServices())
                    .and(DashboardStoredProcParam.AppliedFilterParam.DATE_TYPE_PARAM, appliedFilter.getDateType())
                    .and(DashboardStoredProcParam.AppliedFilterParam.LANES_PARAM, appliedFilter.getLanes())
                    .and(DashboardStoredProcParam.AppliedFilterParam.USER_ID_PARAM, appliedFilter.getLoginUserId())
                    .and(DashboardStoredProcParam.AppliedFilterParam.CONVERTED_CURRENCY_ID_PARAM, appliedFilter.getCurrencyId() == null || appliedFilter.getCurrencyId().isEmpty() ? 0L : Long.parseLong(appliedFilter.getCurrencyId()))
                    .and(DashboardStoredProcParam.AppliedFilterParam.WEIGHT_UNIT_PARAM, appliedFilter.getWeightUnit())
                    .and(DashboardStoredProcParam.AppliedFilterParam.CURRENCY_CODE_PARAM, appliedFilter.getCurrencyCode());

            persistentContext.findEntities("DashboardAppliedFilterDto.saveAppliedFilter", queryParameter);
        }catch (Exception e){
            throw new DaoException("Failed to Save Applied Filter Details", e);
        }
    }

    @Transactional
    public void saveUserDefinedColumnConfig(DashCustomColumnConfigDto customColumnConfig){
        try{
            QueryParameter queryParameter = QueryParameter.with(DashboardStoredProcParam.DashCustomColumnParam.USER_ID_PARAM, customColumnConfig.getUserId())
                    .and(DashboardStoredProcParam.DashCustomColumnParam.REPORT_ID_PARAM, customColumnConfig.getReportId())
                    .and(DashboardStoredProcParam.DashCustomColumnParam.CUSTOM_DEF_1_PARAM, customColumnConfig.getColumnDefined1())
                    .and(DashboardStoredProcParam.DashCustomColumnParam.CUSTOM_DEF_2_PARAM, customColumnConfig.getColumnDefined2())
                    .and(DashboardStoredProcParam.DashCustomColumnParam.CUSTOM_DEF_3_PARAM, customColumnConfig.getColumnDefined3())
                    .and(DashboardStoredProcParam.DashCustomColumnParam.CUSTOM_DEF_4_PARAM, customColumnConfig.getColumnDefined4());
            persistentContext.findEntities(DashCustomColumnConfigDto.Config.StoredProcedureQueryName.SAVE_DASH_COLUMN_CONFIG, queryParameter);
        }catch (Exception e){
            throw new DaoException("Failed to Save Custom Column Details", e);
        }
    }

    @Transactional
    public void deleteSavedFilter(long filterId){
        try{
            persistentContext.findEntities(DashSavedFilterDto.Config.StoredProcedureQueryName.DELETE_SAVED_FILTER, QueryParameter.with(DashboardStoredProcParam.UserFilterParam.FILTER_ID_PARAM, filterId));
        }catch (Exception e){
            throw new DaoException("Failed to delete saved filter with filter_id = " + filterId, e);
        }
    }

    @Transactional
    public DashSavedFilterDto updateSavedFilter(DashSavedFilterDto savedFilter){
        try{
            QueryParameter queryParameter = QueryParameter.with(DashboardStoredProcParam.DashSavedFilterParam.FILTER_ID_PARAM, null == savedFilter.getFilterId() ? 0L : savedFilter.getFilterId())
                    .and(DashboardStoredProcParam.DashSavedFilterParam.FILTER_NAME_PARAM, savedFilter.getFilterName() != null ? savedFilter.getFilterName().trim() : null)
                    .and(DashboardStoredProcParam.DashSavedFilterParam.USER_ID_PARAM, savedFilter.getUserId())
                    .and(DashboardStoredProcParam.DashSavedFilterParam.CUSTOMER_IDS_CSV_PARAM, savedFilter.getCustomerIds())
                    .and(DashboardStoredProcParam.DashSavedFilterParam.CARRIER_IDS_PARAM, savedFilter.getCarrierIds())
                    .and(DashboardStoredProcParam.DashSavedFilterParam.DATE_TYPE_PARAM, savedFilter.getDateType())
                    .and(DashboardStoredProcParam.DashSavedFilterParam.FROM_DATE_PARAM, savedFilter.getFromDate())
                    .and(DashboardStoredProcParam.DashSavedFilterParam.TO_DATE_PARAM, savedFilter.getToDate())
                    .and(DashboardStoredProcParam.DashSavedFilterParam.MODES_PARAM, savedFilter.getModes())
                    .and(DashboardStoredProcParam.DashSavedFilterParam.SERVICES_PARAM, savedFilter.getServices())
                    .and(DashboardStoredProcParam.DashSavedFilterParam.CONVERTED_CURRENCY_ID_PARAM, null == savedFilter.getCurrencyId() ? 0L : savedFilter.getCurrencyId())
                    .and(DashboardStoredProcParam.DashSavedFilterParam.WEIGHT_UNIT_PARAM, savedFilter.getWeightUnit())
                    .and(DashboardStoredProcParam.DashSavedFilterParam.SHIPPER_CITIES_PARAM, savedFilter.getShipperCities())
                    .and(DashboardStoredProcParam.DashSavedFilterParam.SHIPPER_STATES_PARAM, savedFilter.getShipperStates())
                    .and(DashboardStoredProcParam.DashSavedFilterParam.SHIPPER_COUNTRIES_PARAM, savedFilter.getShipperCountries())
                    .and(DashboardStoredProcParam.DashSavedFilterParam.RECEIVER_CITIES_PARAM, savedFilter.getReceiverCities())
                    .and(DashboardStoredProcParam.DashSavedFilterParam.RECEIVER_STATES_PARAM, savedFilter.getReceiverStates())
                    .and(DashboardStoredProcParam.DashSavedFilterParam.RECEIVER_COUNTRIES_PARAM, savedFilter.getReceiverCountries())
                    .and(DashboardStoredProcParam.DashSavedFilterParam.DEFAULT_FILTER_PARAM, null == savedFilter.getDefaultFilter() ? 0 : savedFilter.getDefaultFilter())
                    .and(DashboardStoredProcParam.DashSavedFilterParam.CREATE_DATE_PARAM, savedFilter.getCreateDate());
            return persistentContext.findEntity(DashSavedFilterDto.Config.StoredProcedureQueryName.UPDATE_SAVED_FILTER, queryParameter);
        }catch (Exception e){
            throw new DaoException("Failed to update saved filter details", e);
        }
    }

    @Transactional
    public void makeDefaultSavedFilter(long filterId, long userId){
        try{
            QueryParameter queryParameter = QueryParameter.with(DashboardStoredProcParam.DashSavedFilterParam.FILTER_ID_PARAM, filterId)
                    .and(DashboardStoredProcParam.DashSavedFilterParam.USER_ID_PARAM, userId);
            persistentContext.findEntities(DashSavedFilterDto.Config.StoredProcedureQueryName.MAKE_DEFAULT_FILTER, queryParameter);
        }catch (Exception e){
            throw new DaoException("Failed to set default filter", e);
        }
    }

    public List<DashSavedFilterDto> getUserFilterByName(long userId, String filterName){
        try{
            QueryParameter queryParameter = QueryParameter.with(DashboardStoredProcParam.DashSavedFilterParam.USER_ID_PARAM, userId)
                    .and(DashboardStoredProcParam.DashSavedFilterParam.FILTER_NAME_PARAM, filterName);
            return persistentContext.findEntities(DashSavedFilterDto.Config.StoredProcedureQueryName.GET_USER_FILTER_NY_FILTER_NAME, queryParameter);
        }catch (Exception e){
            throw new DaoException("Failed to get filter by name", e);
        }
    }

    public List<ShipmentDto> getPackageDistributionCount(DashboardsFilterCriteria filter){
        String[] paramNames = {
                DashboardStoredProcParam.ShipmentParam.DATE_TYPE_PARAM, DashboardStoredProcParam.ShipmentParam.CUSTOMER_IDS_CSV_PARAM,
                DashboardStoredProcParam.ShipmentParam.CARRIER_IDS_PARAM, DashboardStoredProcParam.ShipmentParam.MODES_PARAM,
                DashboardStoredProcParam.ShipmentParam.SERVICES_PARAM, DashboardStoredProcParam.ShipmentParam.LANES_PARAM,
                DashboardStoredProcParam.ShipmentParam.FROM_DATE_PARAM, DashboardStoredProcParam.ShipmentParam.TO_DATE_PARAM
        };
        QueryParameter queryParameter = DashboardUtil.prepareDashboardFilterStoredProcParam(paramNames, filter);
        return persistentContext.findEntities(ShipmentDto.Config.StoredProcedureQueryName.PACKAGE_DISTRIBUTION_COUNT, queryParameter);
    }

    public List<AverageWeightModeShipmtDto> getAverageWeightModeByPeriod(DashboardsFilterCriteria filter, boolean isTopTenAccessorial){
        filter.setTopTenAccessorial(isTopTenAccessorial);
        String[] paramNames = {
                DashboardStoredProcParam.AverageWeightShipmentByPeriodParam.DATE_TYPE_PARAM, DashboardStoredProcParam.AverageWeightShipmentByPeriodParam.MODES_PARAM,
                DashboardStoredProcParam.AverageWeightShipmentByPeriodParam.SERVICES_PARAM, DashboardStoredProcParam.AverageWeightShipmentByPeriodParam.ACCESSORIAL_NAME_PARAM,
                DashboardStoredProcParam.AverageWeightShipmentByPeriodParam.LANES_PARAM, DashboardStoredProcParam.AverageWeightShipmentByPeriodParam.FROM_DATE_PARAM,
                DashboardStoredProcParam.AverageWeightShipmentByPeriodParam.TO_DATE_PARAM, DashboardStoredProcParam.AverageWeightShipmentByPeriodParam.CONVERTED_WEIGHT_UNIT_PARAM,
                DashboardStoredProcParam.AverageWeightShipmentByPeriodParam.CARRIER_IDS_PARAM, DashboardStoredProcParam.AverageWeightShipmentByPeriodParam.CUSTOMER_IDS_CSV_PARAM,
                DashboardStoredProcParam.AverageWeightShipmentByPeriodParam.TOP_TEN_ACCESSORIAL_PARAM, DashboardStoredProcParam.AverageWeightShipmentByPeriodParam.MODE_NAMES_PARAM
        };
        QueryParameter queryParameter = DashboardUtil.prepareDashboardFilterStoredProcParam(paramNames, filter);
        return persistentContext.findEntities(AverageWeightModeShipmtDto.Config.StoredProcedureQueryName.AVG_WEIGHT_MODE_SHIPMENT_BY_PERIOD, queryParameter);
    }

    public List<AverageWeightModeShipmtDto> getAverageWeightModeByWeek(DashboardsFilterCriteria filter, boolean isTopTenAccessorial){
        filter.setTopTenAccessorial(isTopTenAccessorial);
        String[] paramNames = {
                DashboardStoredProcParam.AverageWeightShipmentByPeriodParam.DATE_TYPE_PARAM, DashboardStoredProcParam.AverageWeightShipmentByPeriodParam.MODES_PARAM,
                DashboardStoredProcParam.AverageWeightShipmentByPeriodParam.SERVICES_PARAM, DashboardStoredProcParam.AverageWeightShipmentByPeriodParam.ACCESSORIAL_NAME_PARAM,
                DashboardStoredProcParam.AverageWeightShipmentByPeriodParam.LANES_PARAM, DashboardStoredProcParam.AverageWeightShipmentByPeriodParam.FROM_DATE_PARAM,
                DashboardStoredProcParam.AverageWeightShipmentByPeriodParam.TO_DATE_PARAM, DashboardStoredProcParam.AverageWeightShipmentByPeriodParam.CONVERTED_WEIGHT_UNIT_PARAM,
                DashboardStoredProcParam.AverageWeightShipmentByPeriodParam.CARRIER_IDS_PARAM, DashboardStoredProcParam.AverageWeightShipmentByPeriodParam.CUSTOMER_IDS_CSV_PARAM,
                DashboardStoredProcParam.AverageWeightShipmentByPeriodParam.TOP_TEN_ACCESSORIAL_PARAM, DashboardStoredProcParam.AverageWeightShipmentByPeriodParam.MODE_NAMES_PARAM
        };
        QueryParameter queryParameter = DashboardUtil.prepareDashboardFilterStoredProcParam(paramNames, filter);
        return persistentContext.findEntities(AverageWeightModeShipmtDto.Config.StoredProcedureQueryName.AVG_WEIGHT_MODE_SHIPMENT_BY_WEEK, queryParameter);
    }

    public List<AverageSpendPerShipmentDto> getAverageSpendPerShipmentByPeriod(DashboardsFilterCriteria filter, boolean isTopTenAccessorial) {
        filter.setTopTenAccessorial(isTopTenAccessorial);
        String[] paramNames = {
                DashboardStoredProcParam.AverageSpendShipmentByPeriodParam.DATE_TYPE_PARAM, DashboardStoredProcParam.AverageSpendShipmentByPeriodParam.CONVERTED_CURRENCY_ID_PARAM,
                DashboardStoredProcParam.AverageSpendShipmentByPeriodParam.CONVERTED_CURRENCY_CODE_PARAM, DashboardStoredProcParam.AverageSpendShipmentByPeriodParam.MODES_PARAM,
                DashboardStoredProcParam.AverageSpendShipmentByPeriodParam.SERVICES_PARAM, DashboardStoredProcParam.AverageSpendShipmentByPeriodParam.ACCESSORIAL_NAME_PARAM,
                DashboardStoredProcParam.AverageSpendShipmentByPeriodParam.LANES_PARAM, DashboardStoredProcParam.AverageSpendShipmentByPeriodParam.FROM_DATE_PARAM,
                DashboardStoredProcParam.AverageSpendShipmentByPeriodParam.TO_DATE_PARAM, DashboardStoredProcParam.AverageSpendShipmentByPeriodParam.CARRIER_IDS_PARAM,
                DashboardStoredProcParam.AverageSpendShipmentByPeriodParam.CUSTOMER_IDS_CSV_PARAM, DashboardStoredProcParam.AverageSpendShipmentByPeriodParam.TOP_TEN_ACCESSORIAL_PARAM,
                DashboardStoredProcParam.AverageSpendShipmentByPeriodParam.MODE_NAMES_PARAM
        };
        QueryParameter queryParameter = DashboardUtil.prepareDashboardFilterStoredProcParam(paramNames, filter);
        return persistentContext.findEntities(AverageSpendPerShipmentDto.Config.StoredProcedureQueryName.AVG_SPEND_PER_SHIPMENT_BY_PERIOD, queryParameter);
    }

    public List<AverageSpendPerShipmentDto> getAverageSpendPerShipmentByWeek(DashboardsFilterCriteria filter, boolean isTopTenAccessorial) {
        filter.setTopTenAccessorial(isTopTenAccessorial);
        String[] paramNames = {
                DashboardStoredProcParam.AverageSpendShipmentByWeekParam.DATE_TYPE_PARAM, DashboardStoredProcParam.AverageSpendShipmentByWeekParam.CONVERTED_CURRENCY_ID_PARAM,
                DashboardStoredProcParam.AverageSpendShipmentByWeekParam.CONVERTED_CURRENCY_CODE_PARAM, DashboardStoredProcParam.AverageSpendShipmentByWeekParam.MODES_PARAM,
                DashboardStoredProcParam.AverageSpendShipmentByWeekParam.SERVICES_PARAM, DashboardStoredProcParam.AverageSpendShipmentByWeekParam.ACCESSORIAL_NAME_PARAM,
                DashboardStoredProcParam.AverageSpendShipmentByWeekParam.LANES_PARAM, DashboardStoredProcParam.AverageSpendShipmentByWeekParam.FROM_DATE_PARAM,
                DashboardStoredProcParam.AverageSpendShipmentByWeekParam.TO_DATE_PARAM, DashboardStoredProcParam.AverageSpendShipmentByWeekParam.CARRIER_IDS_PARAM,
                DashboardStoredProcParam.AverageSpendShipmentByWeekParam.CUSTOMER_IDS_CSV_PARAM, DashboardStoredProcParam.AverageSpendShipmentByWeekParam.TOP_TEN_ACCESSORIAL_PARAM,
                DashboardStoredProcParam.AverageSpendShipmentByWeekParam.MODE_NAMES_PARAM
        };
        QueryParameter queryParameter = DashboardUtil.prepareDashboardFilterStoredProcParam(paramNames, filter);
        return persistentContext.findEntities(AverageSpendPerShipmentDto.Config.StoredProcedureQueryName.AVG_SPEND_PER_SHIPMENT_BY_WEEK, queryParameter);
    }

    public List<CarrierWiseMonthlySpendDto> getCarrierWiseMonthlySpend(DashboardsFilterCriteria filter, boolean isTopTenAccessorial) {
        String[] paramNames = {DashboardStoredProcParam.CarrierWiseMonthlySpend.DATE_TYPE_PARAM, DashboardStoredProcParam.CarrierWiseMonthlySpend.CONVERTED_CURRENCY_ID_PARAM,
                DashboardStoredProcParam.CarrierWiseMonthlySpend.CONVERTED_CURRENCY_CODE_PARAM, DashboardStoredProcParam.CarrierWiseMonthlySpend.CUSTOMER_IDS_CSV_PARAM,
                DashboardStoredProcParam.CarrierWiseMonthlySpend.CARRIER_IDS_PARAM, DashboardStoredProcParam.CarrierWiseMonthlySpend.MODES_PARAM,
                DashboardStoredProcParam.CarrierWiseMonthlySpend.SERVICES_PARAM, DashboardStoredProcParam.CarrierWiseMonthlySpend.LANES_PARAM,
                DashboardStoredProcParam.CarrierWiseMonthlySpend.ACCESSORIAL_NAME_PARAM,
                DashboardStoredProcParam.CarrierWiseMonthlySpend.FROM_DATE_PARAM, DashboardStoredProcParam.CarrierWiseMonthlySpend.TO_DATE_PARAM,
                DashboardStoredProcParam.CarrierWiseMonthlySpend.TOP_TEN_ACCESSORIAL_PARAM
        };
        QueryParameter queryParameter = DashboardUtil.prepareDashboardFilterStoredProcParam(paramNames, filter);
        return persistentContext.findEntities(CarrierWiseMonthlySpendDto.Config.CarrierWiseMonthlySpend.STORED_PROCEDURE_QUERY_NAME, queryParameter);
    }

    public List<CarrierSpendAnalysisDto> getcarrSpendAnalysis(DashboardsFilterCriteria filter, boolean isTopTenAccessorial) {
        String[] paramNames = {DashboardStoredProcParam.DashboardFilterParams.DATE_TYPE_PARAM, DashboardStoredProcParam.DashboardFilterParams.CONVERTED_CURRENCY_ID_PARAM,
                DashboardStoredProcParam.DashboardFilterParams.CONVERTED_CURRENCY_CODE_PARAM, DashboardStoredProcParam.DashboardFilterParams.CUSTOMER_IDS_CSV_PARAM,
                DashboardStoredProcParam.DashboardFilterParams.CARRIER_IDS_PARAM, DashboardStoredProcParam.DashboardFilterParams.MODES_PARAM,
                DashboardStoredProcParam.DashboardFilterParams.SERVICES_PARAM, DashboardStoredProcParam.DashboardFilterParams.LANES_PARAM,
                DashboardStoredProcParam.DashboardFilterParams.FROM_DATE_PARAM, DashboardStoredProcParam.DashboardFilterParams.TO_DATE_PARAM,
                DashboardStoredProcParam.DashboardFilterParams.CONVERTED_WEIGHT_UNIT_PARAM
        };
        QueryParameter queryParameter = DashboardUtil.prepareDashboardFilterStoredProcParam(paramNames, filter);
        return persistentContext.findEntities(CarrierSpendAnalysisDto.Config.StoredProcedureQueryName.CARR_SPND_Analysis, queryParameter);
    }

    public List<ServiceLevelDto> getServiceLevAnalysis(DashboardsFilterCriteria filter, boolean isTopTenAccessorial) {
        String[] paramNames = {DashboardStoredProcParam.DashboardFilterParams.DATE_TYPE_PARAM, DashboardStoredProcParam.DashboardFilterParams.CONVERTED_CURRENCY_ID_PARAM,
                DashboardStoredProcParam.DashboardFilterParams.CONVERTED_CURRENCY_CODE_PARAM, DashboardStoredProcParam.DashboardFilterParams.CUSTOMER_IDS_CSV_PARAM,
                DashboardStoredProcParam.DashboardFilterParams.CARRIER_IDS_PARAM, DashboardStoredProcParam.DashboardFilterParams.MODES_PARAM,
                DashboardStoredProcParam.DashboardFilterParams.SERVICES_PARAM, DashboardStoredProcParam.DashboardFilterParams.LANES_PARAM,
                DashboardStoredProcParam.DashboardFilterParams.FROM_DATE_PARAM, DashboardStoredProcParam.DashboardFilterParams.TO_DATE_PARAM,
                DashboardStoredProcParam.DashboardFilterParams.CONVERTED_WEIGHT_UNIT_PARAM
        };
        QueryParameter queryParameter = DashboardUtil.prepareDashboardFilterStoredProcParam(paramNames, filter);
        return persistentContext.findEntities(ServiceLevelDto.Config.StoredProcedureQueryName.SERVICE_LEVEL_ANALYSIS, queryParameter);
    }

}
