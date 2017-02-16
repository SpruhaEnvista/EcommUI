package com.envista.msi.api.dao;

import java.util.List;

import main.java.com.envista.msi.api.domain.PersistentContext;
import main.java.com.envista.msi.api.domain.util.DashboardSroredProcParam;
import main.java.com.envista.msi.api.domain.util.QueryParameter;
import main.java.com.envista.msi.api.domain.util.StoredProcedureParameter;
import main.java.com.envista.msi.api.web.rest.dto.MapCoordinatesDto;
import main.java.com.envista.msi.api.web.rest.dto.ZipCodesTimeZonesDto;
import main.java.com.envista.msi.api.web.rest.dto.dashboard.DashboardAppliedFilterDto;
import main.java.com.envista.msi.api.web.rest.dto.dashboard.DashboardsFilterCriteria;
import main.java.com.envista.msi.api.web.rest.dto.dashboard.accessorialspend.AccessorialSpendDto;
import main.java.com.envista.msi.api.web.rest.dto.dashboard.auditactivity.BilledVsApprovedDto;
import main.java.com.envista.msi.api.web.rest.dto.dashboard.auditactivity.InvoiceMethodScoreDto;
import main.java.com.envista.msi.api.web.rest.dto.dashboard.auditactivity.InvoiceStatusAmountDto;
import main.java.com.envista.msi.api.web.rest.dto.dashboard.auditactivity.InvoiceStatusCountDto;
import main.java.com.envista.msi.api.web.rest.dto.dashboard.auditactivity.OrderMatchDto;
import main.java.com.envista.msi.api.web.rest.dto.dashboard.auditactivity.PackageExceptionDto;
import main.java.com.envista.msi.api.web.rest.dto.dashboard.auditactivity.RecoveryAdjustmentDto;
import main.java.com.envista.msi.api.web.rest.dto.dashboard.auditactivity.RecoveryServiceDto;
import main.java.com.envista.msi.api.web.rest.dto.dashboard.netspend.NetSpendByCarrierDto;
import main.java.com.envista.msi.api.web.rest.dto.dashboard.netspend.NetSpendByModeDto;
import main.java.com.envista.msi.api.web.rest.dto.dashboard.netspend.NetSpendByMonthDto;
import main.java.com.envista.msi.api.web.rest.dto.dashboard.netspend.NetSpendOverTimeByMonthDto;
import main.java.com.envista.msi.api.web.rest.dto.dashboard.netspend.NetSpendOverTimeDto;
import main.java.com.envista.msi.api.web.rest.dto.dashboard.networkanalysis.ShipmentRegionDto;
import main.java.com.envista.msi.api.web.rest.dto.dashboard.networkanalysis.ShippingLanesDto;
import main.java.com.envista.msi.api.web.rest.dto.dashboard.shipmentoverview.AverageSpendPerShipmentDto;
import main.java.com.envista.msi.api.web.rest.dto.dashboard.shipmentoverview.AverageWeightModeShipmtDto;
import main.java.com.envista.msi.api.web.rest.dto.dashboard.shipmentoverview.InboundSpendDto;
import main.java.com.envista.msi.api.web.rest.dto.dashboard.shipmentoverview.OutboundSpendDto;
import main.java.com.envista.msi.api.web.rest.dto.dashboard.shipmentoverview.ServiceLevelUsageAndPerformanceDto;
import main.java.com.envista.msi.api.web.rest.dto.dashboard.taxspend.TaxSpendByCarrierDto;
import main.java.com.envista.msi.api.web.rest.dto.dashboard.taxspend.TaxSpendByMonthDto;
import main.java.com.envista.msi.api.web.rest.dto.dashboard.taxspend.TaxSpendDto;

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
     * @param filter
     * @param isTopTenAccessorial
     * @return
     */
    @Transactional( readOnly = true )
    public List<NetSpendByModeDto> getNetSpendByModes(DashboardsFilterCriteria filter, boolean isTopTenAccessorial){
        QueryParameter queryParameter = StoredProcedureParameter.with(DashboardSroredProcParam.NetSpendParams.DATE_TYPE_PARAM, filter.getDateType())
                .and(DashboardSroredProcParam.NetSpendParams.CURRENCY_ID_PARAM, filter.getConvertCurrencyId())
                .and(DashboardSroredProcParam.NetSpendParams.CONVERTED_CURRENCY_CODE_PARAM, filter.getConvertCurrencyCode())
                .and(DashboardSroredProcParam.NetSpendParams.CUSTOMER_IDS_CSV_PARAM, filter.getCustomerIdsCSV())
                .and(DashboardSroredProcParam.NetSpendParams.CARRIER_ID_PARAM, filter.getCarriers())
                .and(DashboardSroredProcParam.NetSpendParams.MODES_PARAM, filter.getModes())
                .and(DashboardSroredProcParam.NetSpendParams.SERVICES_PARAM, filter.getServices())
                .and(DashboardSroredProcParam.NetSpendParams.LANES_PARAM, filter.getLanes())
                .and(DashboardSroredProcParam.NetSpendParams.ACCESSORIAL_NAME_PARAM, filter.getAccessorialName())
                .and(DashboardSroredProcParam.NetSpendParams.FROM_DATE_PARAM, filter.getFromDate())
                .and(DashboardSroredProcParam.NetSpendParams.TO_DATE_PARAM, filter.getToDate())
                .and(DashboardSroredProcParam.NetSpendParams.TOP_TEN_ACCESSORIAL_PARAM, isTopTenAccessorial ? 1L : 0L);

        return persistentContext.findEntities("NetSpendByModeDto.getNetSpendByMode", queryParameter);
    }

    /**
     *
     * @param filter
     * @param isTopTenAccessorial
     * @return
     */
    public List<NetSpendOverTimeByMonthDto> getNetSpendOverTimeByMonth(DashboardsFilterCriteria filter, boolean isTopTenAccessorial){
        QueryParameter queryParameter = StoredProcedureParameter.with(DashboardSroredProcParam.NetSpendParams.DATE_TYPE_PARAM, filter.getDateType())
                .and(DashboardSroredProcParam.NetSpendParams.CURRENCY_ID_PARAM, filter.getConvertCurrencyId())
                .and(DashboardSroredProcParam.NetSpendParams.CONVERTED_CURRENCY_CODE_PARAM, filter.getConvertCurrencyCode())
                .and(DashboardSroredProcParam.NetSpendParams.CUSTOMER_IDS_CSV_PARAM, filter.getCustomerIdsCSV())
                .and(DashboardSroredProcParam.NetSpendParams.CARRIER_ID_PARAM, filter.getCarriers())
                .and(DashboardSroredProcParam.NetSpendParams.MODES_PARAM, filter.getModes())
                .and(DashboardSroredProcParam.NetSpendParams.SERVICES_PARAM, filter.getServices())
                .and(DashboardSroredProcParam.NetSpendParams.LANES_PARAM, filter.getLanes())
                .and(DashboardSroredProcParam.NetSpendParams.ACCESSORIAL_NAME_PARAM, filter.getAccessorialName())
                .and(DashboardSroredProcParam.NetSpendParams.FROM_DATE_PARAM, filter.getFromDate())
                .and(DashboardSroredProcParam.NetSpendParams.TO_DATE_PARAM, filter.getToDate())
                .and(DashboardSroredProcParam.NetSpendParams.TOP_TEN_ACCESSORIAL_PARAM, isTopTenAccessorial ? 1L : 0L);

        return persistentContext.findEntities("NetSpendOverTimeByMonthDto.getNetSpendOverTimeByMonth", queryParameter);
    }

    /**
     *
     * @param filter
     * @param isTopTenAccessorial
     * @return
     */
    public List<NetSpendOverTimeDto> getNetSpendByOverTime(DashboardsFilterCriteria filter, boolean isTopTenAccessorial){
        QueryParameter queryParameter = StoredProcedureParameter.with(DashboardSroredProcParam.NetSpendParams.DATE_TYPE_PARAM, filter.getDateType())
                .and(DashboardSroredProcParam.NetSpendParams.CURRENCY_ID_PARAM, filter.getConvertCurrencyId())
                .and(DashboardSroredProcParam.NetSpendParams.CONVERTED_CURRENCY_CODE_PARAM, filter.getConvertCurrencyCode())
                .and(DashboardSroredProcParam.NetSpendParams.CUSTOMER_IDS_CSV_PARAM, filter.getCustomerIdsCSV())
                .and(DashboardSroredProcParam.NetSpendParams.CARRIER_ID_PARAM, filter.getCarriers())
                .and(DashboardSroredProcParam.NetSpendParams.MODES_PARAM, filter.getModes())
                .and(DashboardSroredProcParam.NetSpendParams.SERVICES_PARAM, filter.getServices())
                .and(DashboardSroredProcParam.NetSpendParams.LANES_PARAM, filter.getLanes())
                .and(DashboardSroredProcParam.NetSpendParams.ACCESSORIAL_NAME_PARAM, filter.getAccessorialName())
                .and(DashboardSroredProcParam.NetSpendParams.FROM_DATE_PARAM, filter.getFromDate())
                .and(DashboardSroredProcParam.NetSpendParams.TO_DATE_PARAM, filter.getToDate())
                .and(DashboardSroredProcParam.NetSpendParams.TOP_TEN_ACCESSORIAL_PARAM, isTopTenAccessorial ? 1L : 0L);

        return persistentContext.findEntities("NetSpendOverTimeDto.getNetSpendByOverTime", queryParameter);
    }

    /**
     *
     * @param filter
     * @param isTopTenAccessorial
     * @return
     */
    @Transactional( readOnly = true )
    public List<NetSpendByCarrierDto> getNetSpendByCarrier(DashboardsFilterCriteria filter, boolean isTopTenAccessorial){
        QueryParameter queryParameter = StoredProcedureParameter.with(DashboardSroredProcParam.NetSpendParams.DATE_TYPE_PARAM, filter.getDateType())
                .and(DashboardSroredProcParam.NetSpendParams.CURRENCY_ID_PARAM, filter.getConvertCurrencyId())
                .and(DashboardSroredProcParam.NetSpendParams.CONVERTED_CURRENCY_CODE_PARAM, filter.getConvertCurrencyCode())
                .and(DashboardSroredProcParam.NetSpendParams.CUSTOMER_IDS_CSV_PARAM, filter.getCustomerIdsCSV())
                .and(DashboardSroredProcParam.NetSpendParams.CARRIER_ID_PARAM, filter.getCarriers())
                .and(DashboardSroredProcParam.NetSpendParams.MODES_PARAM, filter.getModes())
                .and(DashboardSroredProcParam.NetSpendParams.SERVICES_PARAM, filter.getServices())
                .and(DashboardSroredProcParam.NetSpendParams.LANES_PARAM, filter.getLanes())
                .and(DashboardSroredProcParam.NetSpendParams.ACCESSORIAL_NAME_PARAM, filter.getAccessorialName())
                .and(DashboardSroredProcParam.NetSpendParams.FROM_DATE_PARAM, filter.getFromDate())
                .and(DashboardSroredProcParam.NetSpendParams.TO_DATE_PARAM, filter.getToDate())
                .and(DashboardSroredProcParam.NetSpendParams.TOP_TEN_ACCESSORIAL_PARAM, isTopTenAccessorial ? 1L : 0L)
                .and(DashboardSroredProcParam.NetSpendParams.MODE_NAMES_PARAM, filter.getModeNames());

        return persistentContext.findEntities("NetSpendByCarrierDto.getNetSpendByCarrier", queryParameter);
    }

    /**
     *
     * @param filter
     * @param isTopTenAccessorial
     * @return
     */
    public List<NetSpendByMonthDto> getNetSpendByMonth(DashboardsFilterCriteria filter, boolean isTopTenAccessorial){
        QueryParameter queryParameter = StoredProcedureParameter.with(DashboardSroredProcParam.NetSpendParams.DATE_TYPE_PARAM, filter.getDateType())
                .and(DashboardSroredProcParam.NetSpendParams.CURRENCY_ID_PARAM, filter.getConvertCurrencyId())
                .and(DashboardSroredProcParam.NetSpendParams.CONVERTED_CURRENCY_CODE_PARAM, filter.getConvertCurrencyCode())
                .and(DashboardSroredProcParam.NetSpendParams.CUSTOMER_IDS_CSV_PARAM, filter.getCustomerIdsCSV())
                .and(DashboardSroredProcParam.NetSpendParams.CARRIER_ID_PARAM, filter.getCarriers())
                .and(DashboardSroredProcParam.NetSpendParams.MODES_PARAM, filter.getModes())
                .and(DashboardSroredProcParam.NetSpendParams.SERVICES_PARAM, filter.getServices())
                .and(DashboardSroredProcParam.NetSpendParams.LANES_PARAM, filter.getLanes())
                .and(DashboardSroredProcParam.NetSpendParams.ACCESSORIAL_NAME_PARAM, filter.getAccessorialName())
                .and(DashboardSroredProcParam.NetSpendParams.FROM_DATE_PARAM, filter.getFromDate())
                .and(DashboardSroredProcParam.NetSpendParams.TO_DATE_PARAM, filter.getToDate())
                .and(DashboardSroredProcParam.NetSpendParams.TOP_TEN_ACCESSORIAL_PARAM, isTopTenAccessorial ? 1L : 0L)
                .and(DashboardSroredProcParam.NetSpendParams.MODE_NAMES_PARAM, filter.getModeNames())
                .and(DashboardSroredProcParam.NetSpendParams.SCORE_TYPE_PARAM, filter.getScoreType());

        return persistentContext.findEntities("NetSpendByMonthDto.getNetSpendByMonth", queryParameter);
    }

    /**
     *
     * @param filter
     * @return
     */
    public List<TaxSpendDto> getTaxSpend(DashboardsFilterCriteria filter){
        QueryParameter queryParameter = StoredProcedureParameter.with(DashboardSroredProcParam.NetSpendParams.DATE_TYPE_PARAM, filter.getDateType())
                .and(DashboardSroredProcParam.TaxSpendParams.CURRENCY_ID_PARAM, filter.getConvertCurrencyId())
                .and(DashboardSroredProcParam.TaxSpendParams.CUSTOMER_IDS_CSV_PARAM, filter.getCustomerIdsCSV())
                .and(DashboardSroredProcParam.TaxSpendParams.CARRIER_ID_PARAM, filter.getCarriers())
                .and(DashboardSroredProcParam.TaxSpendParams.MODES_PARAM, filter.getModes())
                .and(DashboardSroredProcParam.TaxSpendParams.SERVICES_PARAM, filter.getServices())
                .and(DashboardSroredProcParam.TaxSpendParams.LANES_PARAM, filter.getLanes())
                .and(DashboardSroredProcParam.TaxSpendParams.FROM_DATE_PARAM, filter.getFromDate())
                .and(DashboardSroredProcParam.TaxSpendParams.TO_DATE_PARAM, filter.getToDate())
                .and(DashboardSroredProcParam.TaxSpendParams.MODE_NAMES_PARAM, filter.getModeNames());

        return persistentContext.findEntities("TaxSpendDto.getTaxSpend", queryParameter);
    }

    /**
     *
     * @param filter
     * @return
     */
    public List<TaxSpendByCarrierDto> getTaxSpendByCarrier(DashboardsFilterCriteria filter){
        QueryParameter queryParameter = StoredProcedureParameter.with(DashboardSroredProcParam.NetSpendParams.DATE_TYPE_PARAM, filter.getDateType())
                .and(DashboardSroredProcParam.TaxSpendParams.CURRENCY_ID_PARAM, filter.getConvertCurrencyId())
                .and(DashboardSroredProcParam.TaxSpendParams.CUSTOMER_IDS_CSV_PARAM, filter.getCustomerIdsCSV())
                .and(DashboardSroredProcParam.TaxSpendParams.CARRIER_ID_PARAM, filter.getCarriers())
                .and(DashboardSroredProcParam.TaxSpendParams.MODES_PARAM, filter.getModes())
                .and(DashboardSroredProcParam.TaxSpendParams.SERVICES_PARAM, filter.getServices())
                .and(DashboardSroredProcParam.TaxSpendParams.LANES_PARAM, filter.getLanes())
                .and(DashboardSroredProcParam.TaxSpendParams.FROM_DATE_PARAM, filter.getFromDate())
                .and(DashboardSroredProcParam.TaxSpendParams.TO_DATE_PARAM, filter.getToDate())
                .and(DashboardSroredProcParam.TaxSpendParams.MODE_NAMES_PARAM, filter.getModeNames())
                .and(DashboardSroredProcParam.TaxSpendParams.TAX_PARAM, filter.getTax());

        return persistentContext.findEntities("TaxSpendByCarrierDto.getTaxSpendByCarrier", queryParameter);
    }

    /**
     *
     * @param filter
     * @return
     */
    public List<TaxSpendByMonthDto> getTaxSpendByMonth(DashboardsFilterCriteria filter){
        QueryParameter queryParameter = StoredProcedureParameter.with(DashboardSroredProcParam.NetSpendParams.DATE_TYPE_PARAM, filter.getDateType())
                .and(DashboardSroredProcParam.TaxSpendParams.CURRENCY_ID_PARAM, filter.getConvertCurrencyId())
                .and(DashboardSroredProcParam.TaxSpendParams.CUSTOMER_IDS_CSV_PARAM, filter.getCustomerIdsCSV())
                .and(DashboardSroredProcParam.TaxSpendParams.CARRIER_ID_PARAM, filter.getCarriers())
                .and(DashboardSroredProcParam.TaxSpendParams.MODES_PARAM, filter.getModes())
                .and(DashboardSroredProcParam.TaxSpendParams.SERVICES_PARAM, filter.getServices())
                .and(DashboardSroredProcParam.TaxSpendParams.LANES_PARAM, filter.getLanes())
                .and(DashboardSroredProcParam.TaxSpendParams.FROM_DATE_PARAM, filter.getFromDate())
                .and(DashboardSroredProcParam.TaxSpendParams.TO_DATE_PARAM, filter.getToDate())
                .and(DashboardSroredProcParam.TaxSpendParams.MODE_NAMES_PARAM, filter.getModeNames())
                .and(DashboardSroredProcParam.TaxSpendParams.TAX_PARAM, filter.getTax());;

        return persistentContext.findEntities("TaxSpendByMonthDto.getTaxSpendByMonth", queryParameter);
    }

    /**
     *
     * @param filter
     * @param isTopTenAccessorial
     * @return
     */
    public List<AccessorialSpendDto> getTopAccessorialSpend(DashboardsFilterCriteria filter, boolean isTopTenAccessorial){
        QueryParameter queryParameter = StoredProcedureParameter.with(DashboardSroredProcParam.AccessorialSpendParams.DATE_TYPE_PARAM, filter.getDateType())
                .and(DashboardSroredProcParam.AccessorialSpendParams.CURRENCY_ID_PARAM, filter.getConvertCurrencyId())
                .and(DashboardSroredProcParam.AccessorialSpendParams.CONVERTED_CURRENCY_CODE_PARAM, filter.getConvertCurrencyCode())
                .and(DashboardSroredProcParam.AccessorialSpendParams.CUSTOMER_IDS_CSV_PARAM, filter.getCustomerIdsCSV())
                .and(DashboardSroredProcParam.AccessorialSpendParams.CARRIER_ID_PARAM, filter.getCarriers())
                .and(DashboardSroredProcParam.AccessorialSpendParams.MODES_PARAM, filter.getModes())
                .and(DashboardSroredProcParam.AccessorialSpendParams.SERVICES_PARAM, filter.getServices())
                .and(DashboardSroredProcParam.AccessorialSpendParams.LANES_PARAM, filter.getLanes())
                .and(DashboardSroredProcParam.AccessorialSpendParams.ACCESSORIAL_NAME_PARAM, filter.getAccessorialName())
                .and(DashboardSroredProcParam.AccessorialSpendParams.FROM_DATE_PARAM, filter.getFromDate())
                .and(DashboardSroredProcParam.AccessorialSpendParams.TO_DATE_PARAM, filter.getToDate())
                .and(DashboardSroredProcParam.AccessorialSpendParams.TOP_TEN_ACCESSORIAL_PARAM, isTopTenAccessorial ? 1L : 0L);
        return persistentContext.findEntities("AccessorialSpendDto.getTopAccessorialSpend", queryParameter);
    }

    /**
     *
     * @param filter
     * @param isTopTenAccessorial
     * @return
     */
    public List<AccessorialSpendDto> getTopAccessorialSpendByCarrier(DashboardsFilterCriteria filter, boolean isTopTenAccessorial){
        QueryParameter queryParameter = StoredProcedureParameter.with(DashboardSroredProcParam.AccessorialSpendParams.DATE_TYPE_PARAM, filter.getDateType())
                .and(DashboardSroredProcParam.AccessorialSpendParams.CURRENCY_ID_PARAM, filter.getConvertCurrencyId())
                .and(DashboardSroredProcParam.AccessorialSpendParams.CONVERTED_CURRENCY_CODE_PARAM, filter.getConvertCurrencyCode())
                .and(DashboardSroredProcParam.AccessorialSpendParams.CUSTOMER_IDS_CSV_PARAM, filter.getCustomerIdsCSV())
                .and(DashboardSroredProcParam.AccessorialSpendParams.CARRIER_ID_PARAM, filter.getCarriers())
                .and(DashboardSroredProcParam.AccessorialSpendParams.MODES_PARAM, filter.getModes())
                .and(DashboardSroredProcParam.AccessorialSpendParams.SERVICES_PARAM, filter.getServices())
                .and(DashboardSroredProcParam.AccessorialSpendParams.LANES_PARAM, filter.getLanes())
                .and(DashboardSroredProcParam.AccessorialSpendParams.ACCESSORIAL_NAME_PARAM, filter.getAccessorialName())
                .and(DashboardSroredProcParam.AccessorialSpendParams.FROM_DATE_PARAM, filter.getFromDate())
                .and(DashboardSroredProcParam.AccessorialSpendParams.TO_DATE_PARAM, filter.getToDate())
                .and(DashboardSroredProcParam.AccessorialSpendParams.TOP_TEN_ACCESSORIAL_PARAM, isTopTenAccessorial ? 1L : 0L);
        return persistentContext.findEntities("AccessorialSpendDto.getTopAccessorialSpendByCarrier", queryParameter);
    }

    /**
     *
     * @param filter
     * @param isTopTenAccessorial
     * @return
     */
    public List<AccessorialSpendDto> getTopAccessorialSpendByMonth(DashboardsFilterCriteria filter, boolean isTopTenAccessorial){
        QueryParameter queryParameter = StoredProcedureParameter.with(DashboardSroredProcParam.AccessorialSpendParams.DATE_TYPE_PARAM, filter.getDateType())
                .and(DashboardSroredProcParam.AccessorialSpendParams.CURRENCY_ID_PARAM, filter.getConvertCurrencyId())
                .and(DashboardSroredProcParam.AccessorialSpendParams.CONVERTED_CURRENCY_CODE_PARAM, filter.getConvertCurrencyCode())
                .and(DashboardSroredProcParam.AccessorialSpendParams.CUSTOMER_IDS_CSV_PARAM, filter.getCustomerIdsCSV())
                .and(DashboardSroredProcParam.AccessorialSpendParams.CARRIER_ID_PARAM, filter.getCarriers())
                .and(DashboardSroredProcParam.AccessorialSpendParams.MODES_PARAM, filter.getModes())
                .and(DashboardSroredProcParam.AccessorialSpendParams.SERVICES_PARAM, filter.getServices())
                .and(DashboardSroredProcParam.AccessorialSpendParams.LANES_PARAM, filter.getLanes())
                .and(DashboardSroredProcParam.AccessorialSpendParams.ACCESSORIAL_NAME_PARAM, filter.getAccessorialName())
                .and(DashboardSroredProcParam.AccessorialSpendParams.FROM_DATE_PARAM, filter.getFromDate())
                .and(DashboardSroredProcParam.AccessorialSpendParams.TO_DATE_PARAM, filter.getToDate())
                .and(DashboardSroredProcParam.AccessorialSpendParams.TOP_TEN_ACCESSORIAL_PARAM, isTopTenAccessorial ? 1L : 0L);
        return persistentContext.findEntities("AccessorialSpendDto.getTopAccessorialSpendByMonth", queryParameter);
    }

    /**
     *
     * @param filter
     * @param isTopTenAccessorial
     * @return
     */
    public List<AccessorialSpendDto> getAccessorialSpend(DashboardsFilterCriteria filter, boolean isTopTenAccessorial){
        QueryParameter queryParameter = StoredProcedureParameter.with(DashboardSroredProcParam.AccessorialSpendParams.DATE_TYPE_PARAM, filter.getDateType())
                .and(DashboardSroredProcParam.AccessorialSpendParams.CURRENCY_ID_PARAM, filter.getConvertCurrencyId())
                .and(DashboardSroredProcParam.AccessorialSpendParams.CUSTOMER_IDS_CSV_PARAM, filter.getCustomerIdsCSV())
                .and(DashboardSroredProcParam.AccessorialSpendParams.CARRIER_ID_PARAM, filter.getCarriers())
                .and(DashboardSroredProcParam.AccessorialSpendParams.MODES_PARAM, filter.getModes())
                .and(DashboardSroredProcParam.AccessorialSpendParams.SERVICES_PARAM, filter.getServices())
                .and(DashboardSroredProcParam.AccessorialSpendParams.LANES_PARAM, filter.getLanes())
                .and(DashboardSroredProcParam.AccessorialSpendParams.FROM_DATE_PARAM, filter.getFromDate())
                .and(DashboardSroredProcParam.AccessorialSpendParams.TO_DATE_PARAM, filter.getToDate())
                .and(DashboardSroredProcParam.AccessorialSpendParams.MODE_NAMES_PARAM, filter.getModeNames());
        return persistentContext.findEntities("AccessorialSpendDto.getAccessorialSpend", queryParameter);
    }

    /**
     *
     * @param filter
     * @param isTopTenAccessorial
     * @return
     */
    public List<AccessorialSpendDto> getAccessorialSpendByCarrier(DashboardsFilterCriteria filter, boolean isTopTenAccessorial){
        QueryParameter queryParameter = StoredProcedureParameter.with(DashboardSroredProcParam.AccessorialSpendParams.DATE_TYPE_PARAM, filter.getDateType())
                .and(DashboardSroredProcParam.AccessorialSpendParams.CURRENCY_ID_PARAM, filter.getConvertCurrencyId())
                .and(DashboardSroredProcParam.AccessorialSpendParams.CUSTOMER_IDS_CSV_PARAM, filter.getCustomerIdsCSV())
                .and(DashboardSroredProcParam.AccessorialSpendParams.CARRIER_ID_PARAM, filter.getCarriers())
                .and(DashboardSroredProcParam.AccessorialSpendParams.MODES_PARAM, filter.getModes())
                .and(DashboardSroredProcParam.AccessorialSpendParams.SERVICES_PARAM, filter.getServices())
                .and(DashboardSroredProcParam.AccessorialSpendParams.LANES_PARAM, filter.getLanes())
                .and(DashboardSroredProcParam.AccessorialSpendParams.FROM_DATE_PARAM, filter.getFromDate())
                .and(DashboardSroredProcParam.AccessorialSpendParams.TO_DATE_PARAM, filter.getToDate())
                .and(DashboardSroredProcParam.AccessorialSpendParams.MODE_NAMES_PARAM, filter.getModeNames())
                .and(DashboardSroredProcParam.AccessorialSpendParams.ACCESSORIAL_DESC_PARAM, filter.getAccDesc());
        return persistentContext.findEntities("AccessorialSpendDto.getAccessorialSpendByCarrier", queryParameter);
    }

    /**
     *
     * @param filter
     * @param isTopTenAccessorial
     * @return
     */
    public List<AccessorialSpendDto> getAccessorialSpendByMonth(DashboardsFilterCriteria filter, boolean isTopTenAccessorial){
        QueryParameter queryParameter = StoredProcedureParameter.with(DashboardSroredProcParam.AccessorialSpendParams.DATE_TYPE_PARAM, filter.getDateType())
                .and(DashboardSroredProcParam.AccessorialSpendParams.CURRENCY_ID_PARAM, filter.getConvertCurrencyId())
                .and(DashboardSroredProcParam.AccessorialSpendParams.CUSTOMER_IDS_CSV_PARAM, filter.getCustomerIdsCSV())
                .and(DashboardSroredProcParam.AccessorialSpendParams.CARRIER_ID_PARAM, filter.getCarriers())
                .and(DashboardSroredProcParam.AccessorialSpendParams.MODES_PARAM, filter.getModes())
                .and(DashboardSroredProcParam.AccessorialSpendParams.SERVICES_PARAM, filter.getServices())
                .and(DashboardSroredProcParam.AccessorialSpendParams.LANES_PARAM, filter.getLanes())
                .and(DashboardSroredProcParam.AccessorialSpendParams.FROM_DATE_PARAM, filter.getFromDate())
                .and(DashboardSroredProcParam.AccessorialSpendParams.TO_DATE_PARAM, filter.getToDate())
                .and(DashboardSroredProcParam.AccessorialSpendParams.MODE_NAMES_PARAM, filter.getModeNames())
                .and(DashboardSroredProcParam.AccessorialSpendParams.ACCESSORIAL_DESC_PARAM, filter.getAccDesc());
        return persistentContext.findEntities("AccessorialSpendDto.getAccessorialSpendByMonth", queryParameter);
    }
    /**
     *
     * @param filter
     * @return
     */
    public List<AverageSpendPerShipmentDto>   getAvgSpendPerShipmt(DashboardsFilterCriteria filter, boolean isTopTenAccessorial){
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
     *
     * @param filter
     * @return
     */
    public List<AverageWeightModeShipmtDto>   getAverageWeightByModeShipmt(DashboardsFilterCriteria filter, boolean isTopTenAccessorial){
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

    public List<ServiceLevelUsageAndPerformanceDto> getServiceLevelUsageAndPerformance(DashboardsFilterCriteria filter, boolean isTopTenAccessorial){
        QueryParameter queryParameter = StoredProcedureParameter.with(DashboardSroredProcParam.ServiceLevelUsageAndPerformanceParams.DATE_TYPE_PARAM, filter.getDateType())
                .and(DashboardSroredProcParam.ServiceLevelUsageAndPerformanceParams.CUSTOMER_IDS_CSV_PARAM, filter.getCustomerIdsCSV())
                .and(DashboardSroredProcParam.ServiceLevelUsageAndPerformanceParams.CARRIER_IDS_PARAM, filter.getCarriers())
                .and(DashboardSroredProcParam.ServiceLevelUsageAndPerformanceParams.MODES_PARAM, filter.getModes())
                .and(DashboardSroredProcParam.ServiceLevelUsageAndPerformanceParams.SERVICES_PARAM, filter.getServices())
                .and(DashboardSroredProcParam.ServiceLevelUsageAndPerformanceParams.LANES_PARAM, filter.getLanes())
                .and(DashboardSroredProcParam.ServiceLevelUsageAndPerformanceParams.FROM_DATE_PARAM, filter.getFromDate())
                .and(DashboardSroredProcParam.ServiceLevelUsageAndPerformanceParams.TO_DATE_PARAM, filter.getToDate())
                .and(DashboardSroredProcParam.ServiceLevelUsageAndPerformanceParams.ACCESSORIAL_NAME_PARAM, filter.getAccessorialName())
                .and(DashboardSroredProcParam.ServiceLevelUsageAndPerformanceParams.TOP_TEN_ACCESSORIAL_PARAM, isTopTenAccessorial ? 1L : 0L);
        return persistentContext.findEntities("ServiceLevelUsageAndPerformanceDto.getServiceLevelUsageAndPerformance", queryParameter);
    }

    public List<InboundSpendDto> getInboundSpend(DashboardsFilterCriteria filter, boolean isTopTenAccessorial){
        QueryParameter queryParameter = StoredProcedureParameter.with(DashboardSroredProcParam.InboundSpendParams.DATE_TYPE_PARAM, filter.getDateType())
                .and(DashboardSroredProcParam.InboundSpendParams.CURRENCY_ID_PARAM, filter.getConvertCurrencyId())
                .and(DashboardSroredProcParam.InboundSpendParams.CONVERTED_CURRENCY_CODE_PARAM, filter.getConvertCurrencyCode())
                .and(DashboardSroredProcParam.InboundSpendParams.CUSTOMER_IDS_CSV_PARAM, filter.getCustomerIdsCSV())
                .and(DashboardSroredProcParam.InboundSpendParams.CARRIER_IDS_PARAM, filter.getCarriers())
                .and(DashboardSroredProcParam.InboundSpendParams.MODES_PARAM, filter.getModes())
                .and(DashboardSroredProcParam.InboundSpendParams.SERVICES_PARAM, filter.getServices())
                .and(DashboardSroredProcParam.InboundSpendParams.LANES_PARAM, filter.getLanes())
                .and(DashboardSroredProcParam.InboundSpendParams.FROM_DATE_PARAM, filter.getFromDate())
                .and(DashboardSroredProcParam.InboundSpendParams.TO_DATE_PARAM, filter.getToDate())
                .and(DashboardSroredProcParam.InboundSpendParams.ACCESSORIAL_NAME_PARAM, filter.getAccessorialName())
                .and(DashboardSroredProcParam.InboundSpendParams.TOP_TEN_ACCESSORIAL_PARAM, isTopTenAccessorial ? 1L : 0L);
        return persistentContext.findEntities("InboundSpendDto.getInboundSpend", queryParameter);
    }

    public List<InboundSpendDto> getInboundSpendByMonth(DashboardsFilterCriteria filter, boolean isTopTenAccessorial){
        QueryParameter queryParameter = StoredProcedureParameter.with(DashboardSroredProcParam.InboundSpendParams.DATE_TYPE_PARAM, filter.getDateType())
                .and(DashboardSroredProcParam.InboundSpendParams.CURRENCY_ID_PARAM, filter.getConvertCurrencyId())
                .and(DashboardSroredProcParam.InboundSpendParams.CONVERTED_CURRENCY_CODE_PARAM, filter.getConvertCurrencyCode())
                .and(DashboardSroredProcParam.InboundSpendParams.CUSTOMER_IDS_CSV_PARAM, filter.getCustomerIdsCSV())
                .and(DashboardSroredProcParam.InboundSpendParams.CARRIER_IDS_PARAM, filter.getCarriers())
                .and(DashboardSroredProcParam.InboundSpendParams.MODES_PARAM, filter.getModes())
                .and(DashboardSroredProcParam.InboundSpendParams.SERVICES_PARAM, filter.getServices())
                .and(DashboardSroredProcParam.InboundSpendParams.LANES_PARAM, filter.getLanes())
                .and(DashboardSroredProcParam.InboundSpendParams.FROM_DATE_PARAM, filter.getFromDate())
                .and(DashboardSroredProcParam.InboundSpendParams.TO_DATE_PARAM, filter.getToDate())
                .and(DashboardSroredProcParam.InboundSpendParams.ACCESSORIAL_NAME_PARAM, filter.getAccessorialName())
                .and(DashboardSroredProcParam.InboundSpendParams.TOP_TEN_ACCESSORIAL_PARAM, isTopTenAccessorial ? 1L : 0L);
        return persistentContext.findEntities("InboundSpendDto.getInboundSpendByMonth", queryParameter);
    }

    public List<OutboundSpendDto> getOutboundSpend(DashboardsFilterCriteria filter, boolean isTopTenAccessorial){
        QueryParameter queryParameter = StoredProcedureParameter.with(DashboardSroredProcParam.InboundSpendParams.DATE_TYPE_PARAM, filter.getDateType())
                .and(DashboardSroredProcParam.InboundSpendParams.CURRENCY_ID_PARAM, filter.getConvertCurrencyId())
                .and(DashboardSroredProcParam.InboundSpendParams.CONVERTED_CURRENCY_CODE_PARAM, filter.getConvertCurrencyCode())
                .and(DashboardSroredProcParam.InboundSpendParams.CUSTOMER_IDS_CSV_PARAM, filter.getCustomerIdsCSV())
                .and(DashboardSroredProcParam.InboundSpendParams.CARRIER_IDS_PARAM, filter.getCarriers())
                .and(DashboardSroredProcParam.InboundSpendParams.MODES_PARAM, filter.getModes())
                .and(DashboardSroredProcParam.InboundSpendParams.SERVICES_PARAM, filter.getServices())
                .and(DashboardSroredProcParam.InboundSpendParams.LANES_PARAM, filter.getLanes())
                .and(DashboardSroredProcParam.InboundSpendParams.FROM_DATE_PARAM, filter.getFromDate())
                .and(DashboardSroredProcParam.InboundSpendParams.TO_DATE_PARAM, filter.getToDate())
                .and(DashboardSroredProcParam.InboundSpendParams.ACCESSORIAL_NAME_PARAM, filter.getAccessorialName())
                .and(DashboardSroredProcParam.InboundSpendParams.TOP_TEN_ACCESSORIAL_PARAM, isTopTenAccessorial ? 1L : 0L);
        return persistentContext.findEntities("OutboundSpendDto.getOutboundSpend", queryParameter);
    }

    public List<OutboundSpendDto> getOutboundSpendByMonth(DashboardsFilterCriteria filter, boolean isTopTenAccessorial) {
        QueryParameter queryParameter = StoredProcedureParameter.with(DashboardSroredProcParam.InboundSpendParams.DATE_TYPE_PARAM, filter.getDateType())
                .and(DashboardSroredProcParam.InboundSpendParams.CURRENCY_ID_PARAM, filter.getConvertCurrencyId())
                .and(DashboardSroredProcParam.InboundSpendParams.CONVERTED_CURRENCY_CODE_PARAM, filter.getConvertCurrencyCode())
                .and(DashboardSroredProcParam.InboundSpendParams.CUSTOMER_IDS_CSV_PARAM, filter.getCustomerIdsCSV())
                .and(DashboardSroredProcParam.InboundSpendParams.CARRIER_IDS_PARAM, filter.getCarriers())
                .and(DashboardSroredProcParam.InboundSpendParams.MODES_PARAM, filter.getModes())
                .and(DashboardSroredProcParam.InboundSpendParams.SERVICES_PARAM, filter.getServices())
                .and(DashboardSroredProcParam.InboundSpendParams.LANES_PARAM, filter.getLanes())
                .and(DashboardSroredProcParam.InboundSpendParams.FROM_DATE_PARAM, filter.getFromDate())
                .and(DashboardSroredProcParam.InboundSpendParams.TO_DATE_PARAM, filter.getToDate())
                .and(DashboardSroredProcParam.InboundSpendParams.ACCESSORIAL_NAME_PARAM, filter.getAccessorialName())
                .and(DashboardSroredProcParam.InboundSpendParams.TOP_TEN_ACCESSORIAL_PARAM, isTopTenAccessorial ? 1L : 0L);
        return persistentContext.findEntities("OutboundSpendDto.getOutboundSpendByMonth", queryParameter);
    }

    public List<InvoiceStatusCountDto> getInvoiceStatusCount(DashboardsFilterCriteria filter){
        QueryParameter queryParameter = StoredProcedureParameter.with(DashboardSroredProcParam.InvoiceStatusCountParams.DATE_TYPE_PARAM, filter.getDateType())
                .and(DashboardSroredProcParam.InvoiceStatusCountParams.CUSTOMER_IDS_CSV_PARAM, filter.getCustomerIdsCSV())
                .and(DashboardSroredProcParam.InvoiceStatusCountParams.CARRIER_ID_PARAM, filter.getCarriers())
                .and(DashboardSroredProcParam.InvoiceStatusCountParams.MODES_PARAM, filter.getModes())
                .and(DashboardSroredProcParam.InvoiceStatusCountParams.SERVICES_PARAM, filter.getServices())
                .and(DashboardSroredProcParam.InvoiceStatusCountParams.LANES_PARAM, filter.getLanes())
                .and(DashboardSroredProcParam.InvoiceStatusCountParams.FROM_DATE_PARAM, filter.getFromDate())
                .and(DashboardSroredProcParam.InvoiceStatusCountParams.TO_DATE_PARAM, filter.getToDate())
                .and(DashboardSroredProcParam.InvoiceStatusCountParams.MODE_NAMES_PARAM, filter.getModeNames());
        return persistentContext.findEntities("InvoiceStatusCountDto.getInvoiceStatusCount", queryParameter);
    }

    public List<InvoiceStatusCountDto> getInvoiceStatusCountByCarrier(DashboardsFilterCriteria filter){
        QueryParameter queryParameter = StoredProcedureParameter.with(DashboardSroredProcParam.InvoiceStatusCountParams.DATE_TYPE_PARAM, filter.getDateType())
                .and(DashboardSroredProcParam.InvoiceStatusCountParams.CUSTOMER_IDS_CSV_PARAM, filter.getCustomerIdsCSV())
                .and(DashboardSroredProcParam.InvoiceStatusCountParams.CARRIER_ID_PARAM, filter.getCarriers())
                .and(DashboardSroredProcParam.InvoiceStatusCountParams.MODES_PARAM, filter.getModes())
                .and(DashboardSroredProcParam.InvoiceStatusCountParams.SERVICES_PARAM, filter.getServices())
                .and(DashboardSroredProcParam.InvoiceStatusCountParams.LANES_PARAM, filter.getLanes())
                .and(DashboardSroredProcParam.InvoiceStatusCountParams.FROM_DATE_PARAM, filter.getFromDate())
                .and(DashboardSroredProcParam.InvoiceStatusCountParams.TO_DATE_PARAM, filter.getToDate())
                .and(DashboardSroredProcParam.InvoiceStatusCountParams.MODE_NAMES_PARAM, filter.getModeNames())
                .and(DashboardSroredProcParam.InvoiceStatusCountParams.INVOICE_STATUS_ID_PARAM, filter.getInvoiceStatusId());
        return persistentContext.findEntities("InvoiceStatusCountDto.getInvoiceStatusCountByCarrier", queryParameter);
    }

    public List<InvoiceStatusCountDto> getInvoiceStatusCountByMonth(DashboardsFilterCriteria filter){
        QueryParameter queryParameter = StoredProcedureParameter.with(DashboardSroredProcParam.InvoiceStatusCountParams.DATE_TYPE_PARAM, filter.getDateType())
                .and(DashboardSroredProcParam.InvoiceStatusCountParams.CUSTOMER_IDS_CSV_PARAM, filter.getCustomerIdsCSV())
                .and(DashboardSroredProcParam.InvoiceStatusCountParams.CARRIER_ID_PARAM, filter.getCarriers())
                .and(DashboardSroredProcParam.InvoiceStatusCountParams.MODES_PARAM, filter.getModes())
                .and(DashboardSroredProcParam.InvoiceStatusCountParams.SERVICES_PARAM, filter.getServices())
                .and(DashboardSroredProcParam.InvoiceStatusCountParams.LANES_PARAM, filter.getLanes())
                .and(DashboardSroredProcParam.InvoiceStatusCountParams.FROM_DATE_PARAM, filter.getFromDate())
                .and(DashboardSroredProcParam.InvoiceStatusCountParams.TO_DATE_PARAM, filter.getToDate())
                .and(DashboardSroredProcParam.InvoiceStatusCountParams.MODE_NAMES_PARAM, filter.getModeNames())
                .and(DashboardSroredProcParam.InvoiceStatusCountParams.INVOICE_STATUS_ID_PARAM, filter.getInvoiceStatusId());
        return persistentContext.findEntities("InvoiceStatusCountDto.getInvoiceStatusCountByMonth", queryParameter);
    }

    public List<InvoiceStatusAmountDto> getInvoiceStatusAmount(DashboardsFilterCriteria filter){
        QueryParameter queryParameter = StoredProcedureParameter.with(DashboardSroredProcParam.InvoiceStatusAmountParams.DATE_TYPE_PARAM, filter.getDateType())
                .and(DashboardSroredProcParam.InvoiceStatusAmountParams.CURRENCY_ID_PARAM, filter.getConvertCurrencyId())
                .and(DashboardSroredProcParam.InvoiceStatusAmountParams.CUSTOMER_IDS_CSV_PARAM, filter.getCustomerIdsCSV())
                .and(DashboardSroredProcParam.InvoiceStatusAmountParams.CARRIER_ID_PARAM, filter.getCarriers())
                .and(DashboardSroredProcParam.InvoiceStatusAmountParams.MODES_PARAM, filter.getModes())
                .and(DashboardSroredProcParam.InvoiceStatusAmountParams.SERVICES_PARAM, filter.getServices())
                .and(DashboardSroredProcParam.InvoiceStatusAmountParams.LANES_PARAM, filter.getLanes())
                .and(DashboardSroredProcParam.InvoiceStatusAmountParams.FROM_DATE_PARAM, filter.getFromDate())
                .and(DashboardSroredProcParam.InvoiceStatusAmountParams.TO_DATE_PARAM, filter.getToDate())
                .and(DashboardSroredProcParam.InvoiceStatusAmountParams.MODE_NAMES_PARAM, filter.getModeNames());
        return persistentContext.findEntities("InvoiceStatusAmountDto.getInvoiceStatusAmount", queryParameter);
    }

    public List<InvoiceStatusAmountDto> getInvoiceStatusAmountByCarrier(DashboardsFilterCriteria filter){
        QueryParameter queryParameter = StoredProcedureParameter.with(DashboardSroredProcParam.InvoiceStatusAmountParams.DATE_TYPE_PARAM, filter.getDateType())
                .and(DashboardSroredProcParam.InvoiceStatusAmountParams.CURRENCY_ID_PARAM, filter.getConvertCurrencyId())
                .and(DashboardSroredProcParam.InvoiceStatusAmountParams.CUSTOMER_IDS_CSV_PARAM, filter.getCustomerIdsCSV())
                .and(DashboardSroredProcParam.InvoiceStatusAmountParams.CARRIER_ID_PARAM, filter.getCarriers())
                .and(DashboardSroredProcParam.InvoiceStatusAmountParams.MODES_PARAM, filter.getModes())
                .and(DashboardSroredProcParam.InvoiceStatusAmountParams.SERVICES_PARAM, filter.getServices())
                .and(DashboardSroredProcParam.InvoiceStatusAmountParams.LANES_PARAM, filter.getLanes())
                .and(DashboardSroredProcParam.InvoiceStatusAmountParams.FROM_DATE_PARAM, filter.getFromDate())
                .and(DashboardSroredProcParam.InvoiceStatusAmountParams.TO_DATE_PARAM, filter.getToDate())
                .and(DashboardSroredProcParam.InvoiceStatusAmountParams.MODE_NAMES_PARAM, filter.getModeNames())
                .and(DashboardSroredProcParam.InvoiceStatusAmountParams.INVOICE_STATUS_ID_PARAM, filter.getInvoiceStatusId());
        return persistentContext.findEntities("InvoiceStatusAmountDto.getInvoiceStatusAmountByCarrier", queryParameter);
    }

    public List<InvoiceStatusAmountDto> getInvoiceStatusAmountByMonth(DashboardsFilterCriteria filter){
        QueryParameter queryParameter = StoredProcedureParameter.with(DashboardSroredProcParam.InvoiceStatusAmountParams.DATE_TYPE_PARAM, filter.getDateType())
                .and(DashboardSroredProcParam.InvoiceStatusAmountParams.CURRENCY_ID_PARAM, filter.getConvertCurrencyId())
                .and(DashboardSroredProcParam.InvoiceStatusAmountParams.CUSTOMER_IDS_CSV_PARAM, filter.getCustomerIdsCSV())
                .and(DashboardSroredProcParam.InvoiceStatusAmountParams.CARRIER_ID_PARAM, filter.getCarriers())
                .and(DashboardSroredProcParam.InvoiceStatusAmountParams.MODES_PARAM, filter.getModes())
                .and(DashboardSroredProcParam.InvoiceStatusAmountParams.SERVICES_PARAM, filter.getServices())
                .and(DashboardSroredProcParam.InvoiceStatusAmountParams.LANES_PARAM, filter.getLanes())
                .and(DashboardSroredProcParam.InvoiceStatusAmountParams.FROM_DATE_PARAM, filter.getFromDate())
                .and(DashboardSroredProcParam.InvoiceStatusAmountParams.TO_DATE_PARAM, filter.getToDate())
                .and(DashboardSroredProcParam.InvoiceStatusAmountParams.MODE_NAMES_PARAM, filter.getModeNames())
                .and(DashboardSroredProcParam.InvoiceStatusAmountParams.INVOICE_STATUS_ID_PARAM, filter.getInvoiceStatusId());
        return persistentContext.findEntities("InvoiceStatusAmountDto.getInvoiceStatusAmountByMonth", queryParameter);
    }

    public List<InvoiceMethodScoreDto> getInvoiceMethodScore(DashboardsFilterCriteria filter){
        QueryParameter queryParameter = StoredProcedureParameter.with(DashboardSroredProcParam.InvoiceMethodScoreParams.DATE_TYPE_PARAM, filter.getDateType())
                .and(DashboardSroredProcParam.InvoiceMethodScoreParams.CUSTOMER_IDS_CSV_PARAM, filter.getCustomerIdsCSV())
                .and(DashboardSroredProcParam.InvoiceMethodScoreParams.CARRIER_ID_PARAM, filter.getCarriers())
                .and(DashboardSroredProcParam.InvoiceMethodScoreParams.MODES_PARAM, filter.getModes())
                .and(DashboardSroredProcParam.InvoiceMethodScoreParams.SERVICES_PARAM, filter.getServices())
                .and(DashboardSroredProcParam.InvoiceMethodScoreParams.LANES_PARAM, filter.getLanes())
                .and(DashboardSroredProcParam.InvoiceMethodScoreParams.FROM_DATE_PARAM, filter.getFromDate())
                .and(DashboardSroredProcParam.InvoiceMethodScoreParams.TO_DATE_PARAM, filter.getToDate())
                .and(DashboardSroredProcParam.InvoiceMethodScoreParams.MODE_NAMES_PARAM, filter.getModeNames());
        return persistentContext.findEntities("InvoiceMethodScoreDto.getInvoiceMethodScore", queryParameter);
    }

    public List<InvoiceMethodScoreDto> getInvoiceMethodScoreByCarrier(DashboardsFilterCriteria filter){
        QueryParameter queryParameter = StoredProcedureParameter.with(DashboardSroredProcParam.InvoiceMethodScoreParams.DATE_TYPE_PARAM, filter.getDateType())
                .and(DashboardSroredProcParam.InvoiceMethodScoreParams.CUSTOMER_IDS_CSV_PARAM, filter.getCustomerIdsCSV())
                .and(DashboardSroredProcParam.InvoiceMethodScoreParams.CARRIER_ID_PARAM, filter.getCarriers())
                .and(DashboardSroredProcParam.InvoiceMethodScoreParams.MODES_PARAM, filter.getModes())
                .and(DashboardSroredProcParam.InvoiceMethodScoreParams.SERVICES_PARAM, filter.getServices())
                .and(DashboardSroredProcParam.InvoiceMethodScoreParams.LANES_PARAM, filter.getLanes())
                .and(DashboardSroredProcParam.InvoiceMethodScoreParams.FROM_DATE_PARAM, filter.getFromDate())
                .and(DashboardSroredProcParam.InvoiceMethodScoreParams.TO_DATE_PARAM, filter.getToDate())
                .and(DashboardSroredProcParam.InvoiceMethodScoreParams.MODE_NAMES_PARAM, filter.getModeNames())
                .and(DashboardSroredProcParam.InvoiceMethodScoreParams.INVOICE_METHOD_PARAM, filter.getInvoiceMethod());
        return persistentContext.findEntities("InvoiceMethodScoreDto.getInvoiceMethodScoreByCarrier", queryParameter);
    }

    public List<InvoiceMethodScoreDto> getInvoiceMethodScoreByMonth(DashboardsFilterCriteria filter){
        QueryParameter queryParameter = StoredProcedureParameter.with(DashboardSroredProcParam.InvoiceMethodScoreParams.DATE_TYPE_PARAM, filter.getDateType())
                .and(DashboardSroredProcParam.InvoiceMethodScoreParams.CUSTOMER_IDS_CSV_PARAM, filter.getCustomerIdsCSV())
                .and(DashboardSroredProcParam.InvoiceMethodScoreParams.CARRIER_ID_PARAM, filter.getCarriers())
                .and(DashboardSroredProcParam.InvoiceMethodScoreParams.MODES_PARAM, filter.getModes())
                .and(DashboardSroredProcParam.InvoiceMethodScoreParams.SERVICES_PARAM, filter.getServices())
                .and(DashboardSroredProcParam.InvoiceMethodScoreParams.LANES_PARAM, filter.getLanes())
                .and(DashboardSroredProcParam.InvoiceMethodScoreParams.FROM_DATE_PARAM, filter.getFromDate())
                .and(DashboardSroredProcParam.InvoiceMethodScoreParams.TO_DATE_PARAM, filter.getToDate())
                .and(DashboardSroredProcParam.InvoiceMethodScoreParams.MODE_NAMES_PARAM, filter.getModeNames())
                .and(DashboardSroredProcParam.InvoiceMethodScoreParams.INVOICE_METHOD_PARAM, filter.getInvoiceMethod());
        return persistentContext.findEntities("InvoiceMethodScoreDto.getInvoiceMethodScoreByMonth", queryParameter);
    }

    public List<OrderMatchDto> getOrderMatchStatus(DashboardsFilterCriteria filter){
        QueryParameter queryParameter = StoredProcedureParameter.with(DashboardSroredProcParam.OrderMatchParams.DATE_TYPE_PARAM, filter.getDateType())
                .and(DashboardSroredProcParam.OrderMatchParams.CUSTOMER_IDS_CSV_PARAM, filter.getCustomerIdsCSV())
                .and(DashboardSroredProcParam.OrderMatchParams.CARRIER_ID_PARAM, filter.getCarriers())
                .and(DashboardSroredProcParam.OrderMatchParams.MODES_PARAM, filter.getModes())
                .and(DashboardSroredProcParam.OrderMatchParams.SERVICES_PARAM, filter.getServices())
                .and(DashboardSroredProcParam.OrderMatchParams.LANES_PARAM, filter.getLanes())
                .and(DashboardSroredProcParam.OrderMatchParams.FROM_DATE_PARAM, filter.getFromDate())
                .and(DashboardSroredProcParam.OrderMatchParams.TO_DATE_PARAM, filter.getToDate())
                .and(DashboardSroredProcParam.OrderMatchParams.MODE_NAMES_PARAM, filter.getModeNames());
        return persistentContext.findEntities("OrderMatchDto.getOrderMatch", queryParameter);
    }

    public List<OrderMatchDto> getOrderMatchByCarrier(DashboardsFilterCriteria filter){
        QueryParameter queryParameter = StoredProcedureParameter.with(DashboardSroredProcParam.OrderMatchParams.DATE_TYPE_PARAM, filter.getDateType())
                .and(DashboardSroredProcParam.OrderMatchParams.CUSTOMER_IDS_CSV_PARAM, filter.getCustomerIdsCSV())
                .and(DashboardSroredProcParam.OrderMatchParams.CARRIER_ID_PARAM, filter.getCarriers())
                .and(DashboardSroredProcParam.OrderMatchParams.MODES_PARAM, filter.getModes())
                .and(DashboardSroredProcParam.OrderMatchParams.SERVICES_PARAM, filter.getServices())
                .and(DashboardSroredProcParam.OrderMatchParams.LANES_PARAM, filter.getLanes())
                .and(DashboardSroredProcParam.OrderMatchParams.FROM_DATE_PARAM, filter.getFromDate())
                .and(DashboardSroredProcParam.OrderMatchParams.TO_DATE_PARAM, filter.getToDate())
                .and(DashboardSroredProcParam.OrderMatchParams.MODE_NAMES_PARAM, filter.getModeNames())
                .and(DashboardSroredProcParam.OrderMatchParams.ORDER_MATCH_PARAM, filter.getOrderMatch());
        return persistentContext.findEntities("OrderMatchDto.getOrderMatchByCarrier", queryParameter);
    }

    public List<OrderMatchDto> getOrderMatchByMonth(DashboardsFilterCriteria filter){
        QueryParameter queryParameter = StoredProcedureParameter.with(DashboardSroredProcParam.OrderMatchParams.DATE_TYPE_PARAM, filter.getDateType())
                .and(DashboardSroredProcParam.OrderMatchParams.CUSTOMER_IDS_CSV_PARAM, filter.getCustomerIdsCSV())
                .and(DashboardSroredProcParam.OrderMatchParams.CARRIER_ID_PARAM, filter.getCarriers())
                .and(DashboardSroredProcParam.OrderMatchParams.MODES_PARAM, filter.getModes())
                .and(DashboardSroredProcParam.OrderMatchParams.SERVICES_PARAM, filter.getServices())
                .and(DashboardSroredProcParam.OrderMatchParams.LANES_PARAM, filter.getLanes())
                .and(DashboardSroredProcParam.OrderMatchParams.FROM_DATE_PARAM, filter.getFromDate())
                .and(DashboardSroredProcParam.OrderMatchParams.TO_DATE_PARAM, filter.getToDate())
                .and(DashboardSroredProcParam.OrderMatchParams.MODE_NAMES_PARAM, filter.getModeNames())
                .and(DashboardSroredProcParam.OrderMatchParams.ORDER_MATCH_PARAM, filter.getOrderMatch());
        return persistentContext.findEntities("OrderMatchDto.getOrderMatchByMonth", queryParameter);
    }

    public List<BilledVsApprovedDto> getBilledVsApprovedData(DashboardsFilterCriteria filter){
        QueryParameter queryParameter = StoredProcedureParameter.with(DashboardSroredProcParam.BilledVsApprovedParams.DATE_TYPE_PARAM, filter.getDateType())
                .and(DashboardSroredProcParam.BilledVsApprovedParams.CURRENCY_ID_PARAM, filter.getConvertCurrencyId())
                .and(DashboardSroredProcParam.BilledVsApprovedParams.CUSTOMER_IDS_CSV_PARAM, filter.getCustomerIdsCSV())
                .and(DashboardSroredProcParam.BilledVsApprovedParams.CARRIER_IDS_PARAM, filter.getCarriers())
                .and(DashboardSroredProcParam.BilledVsApprovedParams.MODES_PARAM, filter.getModes())
                .and(DashboardSroredProcParam.BilledVsApprovedParams.SERVICES_PARAM, filter.getServices())
                .and(DashboardSroredProcParam.BilledVsApprovedParams.LANES_PARAM, filter.getLanes())
                .and(DashboardSroredProcParam.BilledVsApprovedParams.FROM_DATE_PARAM, filter.getFromDate())
                .and(DashboardSroredProcParam.BilledVsApprovedParams.TO_DATE_PARAM, filter.getToDate())
                .and(DashboardSroredProcParam.BilledVsApprovedParams.MODE_NAMES_PARAM, filter.getModeNames());
        return persistentContext.findEntities("BilledVsApprovedDto.getBilledVsApproved", queryParameter);
    }

    public List<BilledVsApprovedDto> getBilledVsApprovedByMonth(DashboardsFilterCriteria filter){
        QueryParameter queryParameter = StoredProcedureParameter.with(DashboardSroredProcParam.BilledVsApprovedParams.DATE_TYPE_PARAM, filter.getDateType())
                .and(DashboardSroredProcParam.BilledVsApprovedParams.CURRENCY_ID_PARAM, filter.getConvertCurrencyId())
                .and(DashboardSroredProcParam.BilledVsApprovedParams.CUSTOMER_IDS_CSV_PARAM, filter.getCustomerIdsCSV())
                .and(DashboardSroredProcParam.BilledVsApprovedParams.CARRIER_IDS_PARAM, filter.getCarriers())
                .and(DashboardSroredProcParam.BilledVsApprovedParams.MODES_PARAM, filter.getModes())
                .and(DashboardSroredProcParam.BilledVsApprovedParams.SERVICES_PARAM, filter.getServices())
                .and(DashboardSroredProcParam.BilledVsApprovedParams.LANES_PARAM, filter.getLanes())
                .and(DashboardSroredProcParam.BilledVsApprovedParams.FROM_DATE_PARAM, filter.getFromDate())
                .and(DashboardSroredProcParam.BilledVsApprovedParams.TO_DATE_PARAM, filter.getToDate())
                .and(DashboardSroredProcParam.BilledVsApprovedParams.MODE_NAMES_PARAM, filter.getModeNames())
                .and(DashboardSroredProcParam.BilledVsApprovedParams.BILLED_APPROVED_PARAM, filter.getBilledVsApproved());
        return persistentContext.findEntities("BilledVsApprovedDto.getBilledVsApprovedByMonth", queryParameter);
    }

    public List<RecoveryAdjustmentDto> getRecoveryAdjustment(DashboardsFilterCriteria filter, boolean isTopTenAccessorial){
        QueryParameter queryParameter = StoredProcedureParameter.with(DashboardSroredProcParam.RecoveryAdjustmentParams.DATE_TYPE_PARAM, filter.getDateType())
                .and(DashboardSroredProcParam.RecoveryAdjustmentParams.CURRENCY_ID_PARAM, filter.getConvertCurrencyId())
                .and(DashboardSroredProcParam.RecoveryAdjustmentParams.CONVERTED_CURRENCY_CODE_PARAM, filter.getConvertCurrencyCode())
                .and(DashboardSroredProcParam.RecoveryAdjustmentParams.CUSTOMER_IDS_CSV_PARAM, filter.getCustomerIdsCSV())
                .and(DashboardSroredProcParam.RecoveryAdjustmentParams.CARRIER_IDS_PARAM, filter.getCarriers())
                .and(DashboardSroredProcParam.RecoveryAdjustmentParams.MODES_PARAM, filter.getModes())
                .and(DashboardSroredProcParam.RecoveryAdjustmentParams.SERVICES_PARAM, filter.getServices())
                .and(DashboardSroredProcParam.RecoveryAdjustmentParams.LANES_PARAM, filter.getLanes())
                .and(DashboardSroredProcParam.RecoveryAdjustmentParams.FROM_DATE_PARAM, filter.getFromDate())
                .and(DashboardSroredProcParam.RecoveryAdjustmentParams.TO_DATE_PARAM, filter.getToDate())
                .and(DashboardSroredProcParam.RecoveryAdjustmentParams.ACCESSORIAL_NAME_PARAM, filter.getAccessorialName())
                .and(DashboardSroredProcParam.RecoveryAdjustmentParams.TOP_TEN_ACCESSORIAL_PARAM, isTopTenAccessorial ? 1L : 0L);
        return persistentContext.findEntities("RecoveryAdjustmentDto.getRecoveryAdjustment", queryParameter);
    }

    public List<RecoveryAdjustmentDto> getRecoveryAdjustmentByCarrier(DashboardsFilterCriteria filter, boolean isTopTenAccessorial){
        QueryParameter queryParameter = StoredProcedureParameter.with(DashboardSroredProcParam.RecoveryAdjustmentParams.DATE_TYPE_PARAM, filter.getDateType())
                .and(DashboardSroredProcParam.RecoveryAdjustmentParams.CURRENCY_ID_PARAM, filter.getConvertCurrencyId())
                .and(DashboardSroredProcParam.RecoveryAdjustmentParams.CONVERTED_CURRENCY_CODE_PARAM, filter.getConvertCurrencyCode())
                .and(DashboardSroredProcParam.RecoveryAdjustmentParams.CUSTOMER_IDS_CSV_PARAM, filter.getCustomerIdsCSV())
                .and(DashboardSroredProcParam.RecoveryAdjustmentParams.CARRIER_IDS_PARAM, filter.getCarriers())
                .and(DashboardSroredProcParam.RecoveryAdjustmentParams.MODES_PARAM, filter.getModes())
                .and(DashboardSroredProcParam.RecoveryAdjustmentParams.SERVICES_PARAM, filter.getServices())
                .and(DashboardSroredProcParam.RecoveryAdjustmentParams.LANES_PARAM, filter.getLanes())
                .and(DashboardSroredProcParam.RecoveryAdjustmentParams.FROM_DATE_PARAM, filter.getFromDate())
                .and(DashboardSroredProcParam.RecoveryAdjustmentParams.TO_DATE_PARAM, filter.getToDate())
                .and(DashboardSroredProcParam.RecoveryAdjustmentParams.ACCESSORIAL_NAME_PARAM, filter.getAccessorialName())
                .and(DashboardSroredProcParam.RecoveryAdjustmentParams.TOP_TEN_ACCESSORIAL_PARAM, isTopTenAccessorial ? 1L : 0L);
        return persistentContext.findEntities("RecoveryAdjustmentDto.getRecoveryAdjustmentByCarrier", queryParameter);
    }

    public List<RecoveryAdjustmentDto> getRecoveryAdjustmentByMonth(DashboardsFilterCriteria filter, boolean isTopTenAccessorial){
        QueryParameter queryParameter = StoredProcedureParameter.with(DashboardSroredProcParam.RecoveryAdjustmentParams.DATE_TYPE_PARAM, filter.getDateType())
                .and(DashboardSroredProcParam.RecoveryAdjustmentParams.CURRENCY_ID_PARAM, filter.getConvertCurrencyId())
                .and(DashboardSroredProcParam.RecoveryAdjustmentParams.CONVERTED_CURRENCY_CODE_PARAM, filter.getConvertCurrencyCode())
                .and(DashboardSroredProcParam.RecoveryAdjustmentParams.CUSTOMER_IDS_CSV_PARAM, filter.getCustomerIdsCSV())
                .and(DashboardSroredProcParam.RecoveryAdjustmentParams.CARRIER_IDS_PARAM, filter.getCarriers())
                .and(DashboardSroredProcParam.RecoveryAdjustmentParams.MODES_PARAM, filter.getModes())
                .and(DashboardSroredProcParam.RecoveryAdjustmentParams.SERVICES_PARAM, filter.getServices())
                .and(DashboardSroredProcParam.RecoveryAdjustmentParams.LANES_PARAM, filter.getLanes())
                .and(DashboardSroredProcParam.RecoveryAdjustmentParams.FROM_DATE_PARAM, filter.getFromDate())
                .and(DashboardSroredProcParam.RecoveryAdjustmentParams.TO_DATE_PARAM, filter.getToDate())
                .and(DashboardSroredProcParam.RecoveryAdjustmentParams.ACCESSORIAL_NAME_PARAM, filter.getAccessorialName())
                .and(DashboardSroredProcParam.RecoveryAdjustmentParams.TOP_TEN_ACCESSORIAL_PARAM, isTopTenAccessorial ? 1L : 0L);
        return persistentContext.findEntities("RecoveryAdjustmentDto.getRecoveryAdjustmentByMonth", queryParameter);
    }

    public List<RecoveryServiceDto> getRecoveryServices(DashboardsFilterCriteria filter, boolean isTopTenAccessorial){
        QueryParameter queryParameter = StoredProcedureParameter.with(DashboardSroredProcParam.RecoveryServiceParams.DATE_TYPE_PARAM, filter.getDateType())
                .and(DashboardSroredProcParam.RecoveryServiceParams.CURRENCY_ID_PARAM, filter.getConvertCurrencyId())
                .and(DashboardSroredProcParam.RecoveryServiceParams.CONVERTED_CURRENCY_CODE_PARAM, filter.getConvertCurrencyCode())
                .and(DashboardSroredProcParam.RecoveryServiceParams.CUSTOMER_IDS_CSV_PARAM, filter.getCustomerIdsCSV())
                .and(DashboardSroredProcParam.RecoveryServiceParams.CARRIER_IDS_PARAM, filter.getCarriers())
                .and(DashboardSroredProcParam.RecoveryServiceParams.MODES_PARAM, filter.getModes())
                .and(DashboardSroredProcParam.RecoveryServiceParams.SERVICES_PARAM, filter.getServices())
                .and(DashboardSroredProcParam.RecoveryServiceParams.LANES_PARAM, filter.getLanes())
                .and(DashboardSroredProcParam.RecoveryServiceParams.FROM_DATE_PARAM, filter.getFromDate())
                .and(DashboardSroredProcParam.RecoveryServiceParams.TO_DATE_PARAM, filter.getToDate())
                .and(DashboardSroredProcParam.RecoveryServiceParams.ACCESSORIAL_NAME_PARAM, filter.getAccessorialName())
                .and(DashboardSroredProcParam.RecoveryServiceParams.MODE_NAMES_PARAM, filter.getModeNames())
                .and(DashboardSroredProcParam.RecoveryServiceParams.TOP_TEN_ACCESSORIAL_PARAM, isTopTenAccessorial ? 1L : 0L);
        return persistentContext.findEntities("RecoveryServiceDto.getRecoveryService", queryParameter);
    }

    public List<RecoveryServiceDto> getRecoveryServicesByMonth(DashboardsFilterCriteria filter, boolean isTopTenAccessorial){
        QueryParameter queryParameter = StoredProcedureParameter.with(DashboardSroredProcParam.RecoveryServiceParams.DATE_TYPE_PARAM, filter.getDateType())
                .and(DashboardSroredProcParam.RecoveryServiceParams.CURRENCY_ID_PARAM, filter.getConvertCurrencyId())
                .and(DashboardSroredProcParam.RecoveryServiceParams.CONVERTED_CURRENCY_CODE_PARAM, filter.getConvertCurrencyCode())
                .and(DashboardSroredProcParam.RecoveryServiceParams.CUSTOMER_IDS_CSV_PARAM, filter.getCustomerIdsCSV())
                .and(DashboardSroredProcParam.RecoveryServiceParams.CARRIER_IDS_PARAM, filter.getCarriers())
                .and(DashboardSroredProcParam.RecoveryServiceParams.MODES_PARAM, filter.getModes())
                .and(DashboardSroredProcParam.RecoveryServiceParams.SERVICES_PARAM, filter.getServices())
                .and(DashboardSroredProcParam.RecoveryServiceParams.LANES_PARAM, filter.getLanes())
                .and(DashboardSroredProcParam.RecoveryServiceParams.FROM_DATE_PARAM, filter.getFromDate())
                .and(DashboardSroredProcParam.RecoveryServiceParams.TO_DATE_PARAM, filter.getToDate())
                .and(DashboardSroredProcParam.RecoveryServiceParams.ACCESSORIAL_NAME_PARAM, filter.getAccessorialName())
                .and(DashboardSroredProcParam.RecoveryServiceParams.MODE_NAMES_PARAM, filter.getModeNames())
                .and(DashboardSroredProcParam.RecoveryServiceParams.TOP_TEN_ACCESSORIAL_PARAM, isTopTenAccessorial ? 1L : 0L)
                .and(DashboardSroredProcParam.RecoveryServiceParams.SERVICE_PARAM, filter.getService());
        return persistentContext.findEntities("RecoveryServiceDto.getRecoveryServiceByMonth", queryParameter);
    }

    @Transactional( readOnly = true )
    public List<ShipmentRegionDto> getShipmentByRegion(DashboardsFilterCriteria filter){
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

    public List<MapCoordinatesDto> getMapCoordinates(String address)  {
        return persistentContext.findEntities("MapCoordinatesDto.getMapCooridantes",
                StoredProcedureParameter.with("p_address", address));
    }

    public List<ZipCodesTimeZonesDto> getMapCoordinates(String city, String state) {
        return  persistentContext.findEntities("ZipCodesTimeZonesDto.getMapCooridantes",
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

    public List<ShippingLanesDto> getTopShippingLanesJsonData(DashboardsFilterCriteria filter){
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

    public List<ShippingLanesDto> getShippingLanesByCarrierJson(DashboardsFilterCriteria filter){
        QueryParameter queryParameter = StoredProcedureParameter.with(DashboardSroredProcParam.ShippingLanesParams.DATE_TYPE_PARAM, filter.getDateType())
                .and(DashboardSroredProcParam.ShippingLanesParams.CURRENCY_ID_PARAM, filter.getConvertCurrencyId())
                .and(DashboardSroredProcParam.ShippingLanesParams.CUSTOMER_IDS_CSV_PARAM, filter.getCustomerIdsCSV())
                .and(DashboardSroredProcParam.ShippingLanesParams.CARRIER_ID_PARAM, filter.getCarriers())
                .and(DashboardSroredProcParam.ShippingLanesParams.MODES_PARAM, filter.getModes())
                .and(DashboardSroredProcParam.ShippingLanesParams.SERVICES_PARAM, filter.getServices())
                .and(DashboardSroredProcParam.ShippingLanesParams.LANES_PARAM, filter.getLanes())
                .and(DashboardSroredProcParam.ShippingLanesParams.FROM_DATE_PARAM, filter.getFromDate())
                .and(DashboardSroredProcParam.ShippingLanesParams.TO_DATE_PARAM, filter.getToDate())
                .and(DashboardSroredProcParam.ShippingLanesParams.SHIPPER_CITY, filter.getShipperAddress().split(",")[0].replace("'","''"))
                .and(DashboardSroredProcParam.ShippingLanesParams.SHIPPER_STATE, filter.getShipperAddress().split(",")[1].replace("'","''"))
                .and(DashboardSroredProcParam.ShippingLanesParams.SHIPPER_COUNTRY, filter.getShipperAddress().split(",")[2].replace("'","''"))
                .and(DashboardSroredProcParam.ShippingLanesParams.RECEIVER_CITY, filter.getReceiverAddress().split(",")[0].replace("'","''"))
                .and(DashboardSroredProcParam.ShippingLanesParams.RECEIVER_STATE, filter.getReceiverAddress().split(",")[1].replace("'","''"))
                .and(DashboardSroredProcParam.ShippingLanesParams.RECEIVER_COUNTRY, filter.getReceiverAddress().split(",")[2].replace("'","''"));

        return persistentContext.findEntities("ShippingLanesDto.getShippingLanesByCarrier", queryParameter);
    }

    public List<ShippingLanesDto> getShippingLanesByMonthJson(DashboardsFilterCriteria filter){
        QueryParameter queryParameter = StoredProcedureParameter.with(DashboardSroredProcParam.ShippingLanesParams.DATE_TYPE_PARAM, filter.getDateType())
                .and(DashboardSroredProcParam.ShippingLanesParams.CURRENCY_ID_PARAM, filter.getConvertCurrencyId())
                .and(DashboardSroredProcParam.ShippingLanesParams.CUSTOMER_IDS_CSV_PARAM, filter.getCustomerIdsCSV())
                .and(DashboardSroredProcParam.ShippingLanesParams.CARRIER_ID_PARAM, filter.getCarriers())
                .and(DashboardSroredProcParam.ShippingLanesParams.MODES_PARAM, filter.getModes())
                .and(DashboardSroredProcParam.ShippingLanesParams.SERVICES_PARAM, filter.getServices())
                .and(DashboardSroredProcParam.ShippingLanesParams.LANES_PARAM, filter.getLanes())
                .and(DashboardSroredProcParam.ShippingLanesParams.FROM_DATE_PARAM, filter.getFromDate())
                .and(DashboardSroredProcParam.ShippingLanesParams.TO_DATE_PARAM, filter.getToDate())
                .and(DashboardSroredProcParam.ShippingLanesParams.SHIPPER_CITY, filter.getShipperAddress().split(",")[0].replace("'","''"))
                .and(DashboardSroredProcParam.ShippingLanesParams.SHIPPER_STATE, filter.getShipperAddress().split(",")[1].replace("'","''"))
                .and(DashboardSroredProcParam.ShippingLanesParams.SHIPPER_COUNTRY, filter.getShipperAddress().split(",")[2].replace("'","''"))
                .and(DashboardSroredProcParam.ShippingLanesParams.RECEIVER_CITY, filter.getReceiverAddress().split(",")[0].replace("'","''"))
                .and(DashboardSroredProcParam.ShippingLanesParams.RECEIVER_STATE, filter.getReceiverAddress().split(",")[1].replace("'","''"))
                .and(DashboardSroredProcParam.ShippingLanesParams.RECEIVER_COUNTRY, filter.getReceiverAddress().split(",")[2].replace("'","''"));

        return persistentContext.findEntities("ShippingLanesDto.getShippingLanesByMonth", queryParameter);
    }

    public List<PackageExceptionDto> getPackageExceptions(DashboardsFilterCriteria filter, boolean isTopTenAccessorial){
        QueryParameter queryParameter = StoredProcedureParameter.with(DashboardSroredProcParam.PackageExceptionParams.DATE_TYPE_PARAM, filter.getDateType())
                .and(DashboardSroredProcParam.PackageExceptionParams.CUSTOMER_IDS_CSV_PARAM, filter.getCustomerIdsCSV())
                .and(DashboardSroredProcParam.PackageExceptionParams.CARRIER_IDS_PARAM, filter.getCarriers())
                .and(DashboardSroredProcParam.PackageExceptionParams.MODES_PARAM, filter.getModes())
                .and(DashboardSroredProcParam.PackageExceptionParams.SERVICES_PARAM, filter.getServices())
                .and(DashboardSroredProcParam.PackageExceptionParams.LANES_PARAM, filter.getLanes())
                .and(DashboardSroredProcParam.PackageExceptionParams.FROM_DATE_PARAM, filter.getFromDate())
                .and(DashboardSroredProcParam.PackageExceptionParams.TO_DATE_PARAM, filter.getToDate())
                .and(DashboardSroredProcParam.PackageExceptionParams.ACCESSORIAL_NAME_PARAM, filter.getAccessorialName())
                .and(DashboardSroredProcParam.PackageExceptionParams.TOP_TEN_ACCESSORIAL_PARAM, isTopTenAccessorial ? 1L : 0L);
        return persistentContext.findEntities("PackageExceptionDto.getPackageException", queryParameter);
    }

    public List<PackageExceptionDto> getPackageExceptionsByCarrier(DashboardsFilterCriteria filter, boolean isTopTenAccessorial){
        QueryParameter queryParameter = StoredProcedureParameter.with(DashboardSroredProcParam.PackageExceptionParams.DATE_TYPE_PARAM, filter.getDateType())
                .and(DashboardSroredProcParam.PackageExceptionParams.CUSTOMER_IDS_CSV_PARAM, filter.getCustomerIdsCSV())
                .and(DashboardSroredProcParam.PackageExceptionParams.CARRIER_IDS_PARAM, filter.getCarriers())
                .and(DashboardSroredProcParam.PackageExceptionParams.MODES_PARAM, filter.getModes())
                .and(DashboardSroredProcParam.PackageExceptionParams.SERVICES_PARAM, filter.getServices())
                .and(DashboardSroredProcParam.PackageExceptionParams.LANES_PARAM, filter.getLanes())
                .and(DashboardSroredProcParam.PackageExceptionParams.FROM_DATE_PARAM, filter.getFromDate())
                .and(DashboardSroredProcParam.PackageExceptionParams.TO_DATE_PARAM, filter.getToDate())
                .and(DashboardSroredProcParam.PackageExceptionParams.ACCESSORIAL_NAME_PARAM, filter.getAccessorialName())
                .and(DashboardSroredProcParam.PackageExceptionParams.TOP_TEN_ACCESSORIAL_PARAM, isTopTenAccessorial ? 1L : 0L)
                .and(DashboardSroredProcParam.PackageExceptionParams.DELIVERY_FLAG_PARAM, filter.getDeliveryFlag());
        return persistentContext.findEntities("PackageExceptionDto.getPackageExceptionByCarrier", queryParameter);
    }

    public List<PackageExceptionDto> getPackageExceptionsByMonth(DashboardsFilterCriteria filter, boolean isTopTenAccessorial){
        QueryParameter queryParameter = StoredProcedureParameter.with(DashboardSroredProcParam.PackageExceptionParams.DATE_TYPE_PARAM, filter.getDateType())
                .and(DashboardSroredProcParam.PackageExceptionParams.CUSTOMER_IDS_CSV_PARAM, filter.getCustomerIdsCSV())
                .and(DashboardSroredProcParam.PackageExceptionParams.CARRIER_IDS_PARAM, filter.getCarriers())
                .and(DashboardSroredProcParam.PackageExceptionParams.MODES_PARAM, filter.getModes())
                .and(DashboardSroredProcParam.PackageExceptionParams.SERVICES_PARAM, filter.getServices())
                .and(DashboardSroredProcParam.PackageExceptionParams.LANES_PARAM, filter.getLanes())
                .and(DashboardSroredProcParam.PackageExceptionParams.FROM_DATE_PARAM, filter.getFromDate())
                .and(DashboardSroredProcParam.PackageExceptionParams.TO_DATE_PARAM, filter.getToDate())
                .and(DashboardSroredProcParam.PackageExceptionParams.ACCESSORIAL_NAME_PARAM, filter.getAccessorialName())
                .and(DashboardSroredProcParam.PackageExceptionParams.TOP_TEN_ACCESSORIAL_PARAM, isTopTenAccessorial ? 1L : 0L)
                .and(DashboardSroredProcParam.PackageExceptionParams.DELIVERY_FLAG_PARAM, filter.getDeliveryFlag());
        return persistentContext.findEntities("PackageExceptionDto.getPackageExceptionByMonth", queryParameter);

    }
}
