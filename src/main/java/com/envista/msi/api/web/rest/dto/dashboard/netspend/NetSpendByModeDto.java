package com.envista.msi.api.web.rest.dto.dashboard.netspend;

import com.envista.msi.api.domain.util.DashboardSroredProcParam;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Sujit kumar on 30/01/2017.
 */
@NamedStoredProcedureQueries({
        @NamedStoredProcedureQuery(name = "NetSpendByModeDto.getNetSpendByMode", procedureName = "SHP_DB_NET_SPEND_BY_MODE_PROC",
                resultSetMappings = {"NetSpendByModeDto"},
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.NetSpendParams.DATE_TYPE_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.NetSpendParams.CONVERTED_CURRENCY_ID_PARAM, type = Long.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.NetSpendParams.CONVERTED_CURRENCY_CODE_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.NetSpendParams.CUSTOMER_IDS_CSV_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.NetSpendParams.CARRIER_IDS_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.NetSpendParams.MODES_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.NetSpendParams.SERVICES_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.NetSpendParams.LANES_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.NetSpendParams.ACCESSORIAL_NAME_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.NetSpendParams.FROM_DATE_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.NetSpendParams.TO_DATE_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.NetSpendParams.TOP_TEN_ACCESSORIAL_PARAM, type = Long.class),
                        @StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, name = DashboardSroredProcParam.NetSpendParams.NET_SPEND_PARAM, type = Void.class)
                })
})

@SqlResultSetMappings({
        @SqlResultSetMapping(name = "NetSpendByModeDto",
        classes = {
                @ConstructorResult(
                        targetClass = NetSpendByModeDto.class,
                        columns = {
                                @ColumnResult(name = "MODES", type = String.class),
                                @ColumnResult(name = "SPEND", type = Double.class),
                                @ColumnResult(name = "SCORE_TYPE", type = String.class)
                        }
                )
        })
})

@Entity
public class NetSpendByModeDto implements Serializable{
    @Id
    @Column(name = "NET_SPEND_ID")
    private Long id;

    @Column(name = "MODES")
    private String modes;

    @Column(name = "SPEND")
    private Double spend;

    @Column(name = "SCORE_TYPE")
    private String scoreType;

    public NetSpendByModeDto(){}

    public NetSpendByModeDto(String modes, Double spend, String scoreType) {
        this.modes = modes;
        this.spend = spend;
        this.scoreType = scoreType;
    }

    public NetSpendByModeDto(Long id, String modes, Double spend, String scoreType) {
        this.id = id;
        this.modes = modes;
        this.spend = spend;
        this.scoreType = scoreType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getModes() {
        return modes;
    }

    public void setModes(String modes) {
        this.modes = modes;
    }

    public Double getSpend() {
        return spend;
    }

    public void setSpend(Double spend) {
        this.spend = spend;
    }

    public String getScoreType() {
        return scoreType;
    }

    public void setScoreType(String scoreType) {
        this.scoreType = scoreType;
    }
}
