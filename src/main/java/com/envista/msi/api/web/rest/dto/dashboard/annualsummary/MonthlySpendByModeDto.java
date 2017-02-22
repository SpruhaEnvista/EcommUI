package com.envista.msi.api.web.rest.dto.dashboard.annualsummary;

import com.envista.msi.api.domain.util.DashboardSroredProcParam;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Sujit kumar on 20/02/2017.
 */

@NamedStoredProcedureQueries({
        @NamedStoredProcedureQuery(name = MonthlySpendByModeDto.Config.StoredProcedureQueryName.MONTHLY_SPEND_BY_MODE,
                procedureName = MonthlySpendByModeDto.Config.StoredProcedureName.MONTHLY_SPEND_BY_MODE,
        resultSetMappings = MonthlySpendByModeDto.Config.ResultMappings.MONTHLY_SPEND_BY_MODE_MAPPING,
        parameters = {
                @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.MonthlySpendByModeParams.DATE_TYPE_PARAM, type = String.class),
                @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.MonthlySpendByModeParams.CONVERTED_CURRENCY_ID_PARAM, type = Long.class),
                @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.MonthlySpendByModeParams.CONVERTED_CURRENCY_CODE_PARAM, type = String.class),
                @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.MonthlySpendByModeParams.CUSTOMER_IDS_CSV_PARAM, type = String.class),
                @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.MonthlySpendByModeParams.CARRIER_IDS_PARAM, type = String.class),
                @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.MonthlySpendByModeParams.MODES_PARAM, type = String.class),
                @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.MonthlySpendByModeParams.SERVICES_PARAM, type = String.class),
                @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.MonthlySpendByModeParams.LANES_PARAM, type = String.class),
                @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.MonthlySpendByModeParams.FROM_DATE_PARAM, type = String.class),
                @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.MonthlySpendByModeParams.TO_DATE_PARAM, type = String.class),
                @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.MonthlySpendByModeParams.ACCESSORIAL_NAME_PARAM, type = String.class),
                @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.MonthlySpendByModeParams.TOP_TEN_ACCESSORIAL_PARAM, type = Long.class),
                @StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, name = DashboardSroredProcParam.MonthlySpendByModeParams.MONTHLY_SPEND_DATA, type = Void.class)
        }),
        @NamedStoredProcedureQuery(name = MonthlySpendByModeDto.Config.StoredProcedureQueryName.MONTHLY_SPEND_BY_MODE_BY_SERVICE,
                procedureName = MonthlySpendByModeDto.Config.StoredProcedureName.MONTHLY_SPEND_BY_MODE_BY_SERVICE,
                resultSetMappings = MonthlySpendByModeDto.Config.ResultMappings.MONTHLY_SPEND_BY_MODE_MAPPING_BY_SERVICE,
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.MonthlySpendByModeParams.DATE_TYPE_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.MonthlySpendByModeParams.CONVERTED_CURRENCY_ID_PARAM, type = Long.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.MonthlySpendByModeParams.CONVERTED_CURRENCY_CODE_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.MonthlySpendByModeParams.CUSTOMER_IDS_CSV_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.MonthlySpendByModeParams.CARRIER_IDS_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.MonthlySpendByModeParams.MODES_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.MonthlySpendByModeParams.SERVICES_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.MonthlySpendByModeParams.LANES_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.MonthlySpendByModeParams.FROM_DATE_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.MonthlySpendByModeParams.TO_DATE_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.MonthlySpendByModeParams.ACCESSORIAL_NAME_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.MonthlySpendByModeParams.TOP_TEN_ACCESSORIAL_PARAM, type = Long.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.MonthlySpendByModeParams.SERVICE_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, name = DashboardSroredProcParam.MonthlySpendByModeParams.MONTHLY_SPEND_DATA, type = Void.class)
                })
})

@SqlResultSetMappings({
        @SqlResultSetMapping(name = MonthlySpendByModeDto.Config.ResultMappings.MONTHLY_SPEND_BY_MODE_MAPPING,
        classes = {
                @ConstructorResult(targetClass = MonthlySpendByModeDto.class,
                columns = {
                        @ColumnResult(name = "MONTH", type = String.class),
                        @ColumnResult(name = "MODES", type = String.class),
                        @ColumnResult(name = "SERVICE", type = String.class),
                        @ColumnResult(name = "SPEND", type = Double.class)
                })
        })
})

@Entity
public class MonthlySpendByModeDto implements Serializable {
    @Id
    private Long id;

    @Column(name = "MONTH")
    private String month;

    @Column(name = "MODES")
    private String modes;

    @Column(name = "SERVICE")
    private String service;

    @Column(name = "SPEND")
    private Double spend;

    public MonthlySpendByModeDto(){}

    public MonthlySpendByModeDto(String month, String modes, String service, Double spend) {
        this.month = month;
        this.modes = modes;
        this.service = service;
        this.spend = spend;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getModes() {
        return modes;
    }

    public void setModes(String modes) {
        this.modes = modes;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public Double getSpend() {
        return spend;
    }

    public void setSpend(Double spend) {
        this.spend = spend;
    }

    public static class Config{
        static class ResultMappings{
            static final String MONTHLY_SPEND_BY_MODE_MAPPING = "MonthlySpendByModeDto.MonthlySpendByModeMapping";
            static final String MONTHLY_SPEND_BY_MODE_MAPPING_BY_SERVICE = MONTHLY_SPEND_BY_MODE_MAPPING;
        }

        static class StoredProcedureName{
            static final String MONTHLY_SPEND_BY_MODE = "SHP_DB_MTHLY_SPND_MD_PROC";
            static final String MONTHLY_SPEND_BY_MODE_BY_SERVICE = "SHP_DB_MTHLY_SPND_MD_SERV_PROC";
        }

        public static class StoredProcedureQueryName{
            public static final String MONTHLY_SPEND_BY_MODE = "MonthlySpendByModeDto.getMonthlySpendByMode";
            public static final String MONTHLY_SPEND_BY_MODE_BY_SERVICE = "MonthlySpendByModeDto.getMonthlySpendByModeByService";
        }
    }
}
