package com.envista.msi.api.dao;

import com.envista.msi.api.domain.PersistentContext;
import com.envista.msi.api.domain.util.QueryParameter;
import com.envista.msi.api.domain.util.StoredProcedureParameter;
import com.envista.msi.api.web.rest.dto.DashboardAppliedFilterDto;
import com.envista.msi.api.web.rest.dto.DashboardDataDto;
import com.envista.msi.api.web.rest.dto.NetSpendDto;
import org.springframework.stereotype.Repository;

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

    public List<NetSpendDto> getNetSpendByModes(DashboardDataDto dbDto, boolean isTopTenAccessorial){
        QueryParameter queryParameter = StoredProcedureParameter.with("p_date_type", dbDto.getDateType())
                /*.and("p_currency_id", dbDto.getConvertCurrencyId())
                .and("p_converted_curr_code", dbDto.getConvertCurrencyCode())
                .and("p_customer_ids", dbDto.getCustomerIdsCSV())
                .and("p_carrier_id", dbDto.getCarriers())
                .and("p_modes", dbDto.getModes())
                .and("p_services", dbDto.getServices())
                .and("p_lanes_info", dbDto.getLanes())
                .and("p_accessorial_name", "")
                .and("p_from_date", dbDto.getFromDate())
                .and("p_to_date", dbDto.getToDate())
                .and("p_is_top_ten_accessorial", isTopTenAccessorial ? 1 : 0)*/;

        return persistentContext.findEntities("NetSpendDto.getNetSpendByMode", StoredProcedureParameter.with("p_date_type", dbDto.getDateType()));
    }

    public List<NetSpendDto> getNetSpendByMonth(DashboardDataDto dbDto, boolean isTopTenAccessorial){
        QueryParameter queryParameter = StoredProcedureParameter.with("p_date_type", dbDto.getDateType())
                .and("p_currency_id", dbDto.getConvertCurrencyId())
                .and("p_converted_curr_code", dbDto.getConvertCurrencyCode())
                .and("p_customer_ids", dbDto.getCustomerIdsCSV())
                .and("p_carrier_id", dbDto.getCarriers())
                .and("p_modes", dbDto.getModes())
                .and("p_services", dbDto.getServices())
                .and("p_lanes_info", dbDto.getLanes())
                .and("p_accessorial_name", "")
                .and("p_from_date", dbDto.getFromDate())
                .and("p_to_date", dbDto.getToDate())
                .and("p_is_top_ten_accessorial", isTopTenAccessorial ? 1 : 0);

        return persistentContext.findEntities("NetSpendDto.getNetSpendByMonth", queryParameter);
    }

    public List<NetSpendDto> getNetSpendByOverTime(DashboardDataDto dbDto, boolean isTopTenAccessorial){
        QueryParameter queryParameter = StoredProcedureParameter.with("p_date_type", dbDto.getDateType())
                .and("p_currency_id", dbDto.getConvertCurrencyId())
                .and("p_converted_curr_code", dbDto.getConvertCurrencyCode())
                .and("p_customer_ids", dbDto.getCustomerIdsCSV())
                .and("p_carrier_id", dbDto.getCarriers())
                .and("p_modes", dbDto.getModes())
                .and("p_services", dbDto.getServices())
                .and("p_lanes_info", dbDto.getLanes())
                .and("p_accessorial_name", "")
                .and("p_from_date", dbDto.getFromDate())
                .and("p_to_date", dbDto.getToDate())
                .and("p_is_top_ten_accessorial", isTopTenAccessorial ? 1 : 0);

        return persistentContext.findEntities("NetSpendDto.getNetSpendByOverTime", queryParameter);
    }
}
