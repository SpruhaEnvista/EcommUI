package com.envista.msi.api.dao;

import com.envista.msi.api.domain.PersistentContext;
import com.envista.msi.api.domain.util.DashboardSroredProcParam;
import com.envista.msi.api.domain.util.QueryParameter;
import com.envista.msi.api.domain.util.StoredProcedureParameter;
import com.envista.msi.api.web.rest.dto.dashboard.DashboardAppliedFilterDto;
import com.envista.msi.api.web.rest.dto.dashboard.DashboardsFilterCriteria;
import com.envista.msi.api.web.rest.dto.dashboard.accessorialspend.AccessorialSpendDto;
import com.envista.msi.api.web.rest.dto.dashboard.netspend.*;
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

    public List<OutboundSpendDto> getOutboundSpendByMonth(DashboardsFilterCriteria filter, boolean isTopTenAccessorial){
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
}
