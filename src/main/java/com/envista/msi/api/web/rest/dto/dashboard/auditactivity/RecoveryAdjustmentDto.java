package com.envista.msi.api.web.rest.dto.dashboard.auditactivity;

import com.envista.msi.api.domain.util.DashboardStoredProcParam;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by Sujit kumar on 13/02/2017.
 */

@NamedStoredProcedureQueries({
        @NamedStoredProcedureQuery(name = RecoveryAdjustmentDto.Config.StoredProcedureQueryName.RECOVERY_ADJUSTMENT,
                procedureName = RecoveryAdjustmentDto.Config.StoredProcedureName.RECOVERY_ADJUSTMENT,
                resultSetMappings = RecoveryAdjustmentDto.Config.ResultMappings.RECOVERY_ADJUSTMENT_MAPPING,
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.RecoveryAdjustmentParams.DATE_TYPE_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.RecoveryAdjustmentParams.CONVERTED_CURRENCY_ID_PARAM, type = Long.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.RecoveryAdjustmentParams.CONVERTED_CURRENCY_CODE_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.RecoveryAdjustmentParams.CUSTOMER_IDS_CSV_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.RecoveryAdjustmentParams.CARRIER_IDS_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.RecoveryAdjustmentParams.MODES_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.RecoveryAdjustmentParams.SERVICES_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.RecoveryAdjustmentParams.LANES_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.RecoveryAdjustmentParams.FROM_DATE_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.RecoveryAdjustmentParams.TO_DATE_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.RecoveryAdjustmentParams.ACCESSORIAL_NAME_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.RecoveryAdjustmentParams.TOP_TEN_ACCESSORIAL_PARAM, type = Long.class),
                        @StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, name = DashboardStoredProcParam.RecoveryAdjustmentParams.RECOVERY_ADJUSTMENT_DATA_PARAM, type = Void.class)
                }),
        @NamedStoredProcedureQuery(name = RecoveryAdjustmentDto.Config.StoredProcedureQueryName.RECOVERY_ADJUSTMENT_BY_CARRIER,
                procedureName = RecoveryAdjustmentDto.Config.StoredProcedureName.RECOVERY_ADJUSTMENT_BY_CARRIER,
                resultSetMappings = RecoveryAdjustmentDto.Config.ResultMappings.RECOVERY_ADJUSTMENT_BY_CARRIER_MAPPING,
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.RecoveryAdjustmentParams.DATE_TYPE_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.RecoveryAdjustmentParams.CONVERTED_CURRENCY_ID_PARAM, type = Long.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.RecoveryAdjustmentParams.CONVERTED_CURRENCY_CODE_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.RecoveryAdjustmentParams.CUSTOMER_IDS_CSV_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.RecoveryAdjustmentParams.CARRIER_IDS_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.RecoveryAdjustmentParams.MODES_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.RecoveryAdjustmentParams.SERVICES_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.RecoveryAdjustmentParams.LANES_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.RecoveryAdjustmentParams.FROM_DATE_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.RecoveryAdjustmentParams.TO_DATE_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.RecoveryAdjustmentParams.ACCESSORIAL_NAME_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.RecoveryAdjustmentParams.TOP_TEN_ACCESSORIAL_PARAM, type = Long.class),
                        @StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, name = DashboardStoredProcParam.RecoveryAdjustmentParams.RECOVERY_ADJUSTMENT_DATA_PARAM, type = Void.class)
                }),
        @NamedStoredProcedureQuery(name = RecoveryAdjustmentDto.Config.StoredProcedureQueryName.RECOVERY_ADJUSTMENT_BY_MONTH,
                procedureName = RecoveryAdjustmentDto.Config.StoredProcedureName.RECOVERY_ADJUSTMENT_BY_MONTH,
                resultSetMappings = RecoveryAdjustmentDto.Config.ResultMappings.RECOVERY_ADJUSTMENT_BY_MONTH_MAPPING,
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.RecoveryAdjustmentParams.DATE_TYPE_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.RecoveryAdjustmentParams.CONVERTED_CURRENCY_ID_PARAM, type = Long.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.RecoveryAdjustmentParams.CONVERTED_CURRENCY_CODE_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.RecoveryAdjustmentParams.CUSTOMER_IDS_CSV_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.RecoveryAdjustmentParams.CARRIER_IDS_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.RecoveryAdjustmentParams.MODES_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.RecoveryAdjustmentParams.SERVICES_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.RecoveryAdjustmentParams.LANES_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.RecoveryAdjustmentParams.FROM_DATE_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.RecoveryAdjustmentParams.TO_DATE_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.RecoveryAdjustmentParams.ACCESSORIAL_NAME_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.RecoveryAdjustmentParams.TOP_TEN_ACCESSORIAL_PARAM, type = Long.class),
                        @StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, name = DashboardStoredProcParam.RecoveryAdjustmentParams.RECOVERY_ADJUSTMENT_DATA_PARAM, type = Void.class)
                })
})

@SqlResultSetMappings({
        @SqlResultSetMapping(name = RecoveryAdjustmentDto.Config.ResultMappings.RECOVERY_ADJUSTMENT_MAPPING, classes = {
                @ConstructorResult(targetClass = RecoveryAdjustmentDto.class,
                    columns = {
                            @ColumnResult(name = "MONTH", type = String.class),
                            @ColumnResult(name = "SERVICE", type = String.class),
                            @ColumnResult(name = "SPEND", type = Double.class)
                    }
                )
        }),
        @SqlResultSetMapping(name = RecoveryAdjustmentDto.Config.ResultMappings.RECOVERY_ADJUSTMENT_BY_CARRIER_MAPPING, classes = {
                @ConstructorResult(targetClass = RecoveryAdjustmentDto.class,
                        columns = {
                                @ColumnResult(name = "ID", type = Long.class),
                                @ColumnResult(name = "NAME", type = String.class),
                                @ColumnResult(name = "VALUE", type = Double.class)
                        }
                )
        }),
        @SqlResultSetMapping(name = RecoveryAdjustmentDto.Config.ResultMappings.RECOVERY_ADJUSTMENT_BY_MONTH_MAPPING, classes = {
                @ConstructorResult(targetClass = RecoveryAdjustmentDto.class,
                        columns = {
                                @ColumnResult(name = "BILL_DATE", type = Date.class),
                                @ColumnResult(name = "AMOUNT", type = Double.class)
                        }
                )
        })
})

@Entity
public class RecoveryAdjustmentDto implements Serializable {
    @Id
    @Column(name = "ID")
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "VALUE")
    private Double value;

    @Column(name = "MONTH")
    private String month;

    @Column(name = "SERVICE")
    private String service;

    @Column(name = "SPEND")
    private Double spend;

    @Column(name = "BILL_DATE")
    private Date billDate;

    @Column(name = "AMOUNT")
    private Double amount;

    public RecoveryAdjustmentDto(){}

    public RecoveryAdjustmentDto(String month, String service, Double spend) {
        this.month = month;
        this.service = service;
        this.spend = spend;
    }

    public RecoveryAdjustmentDto(Date billDate, Double amount) {
        this.billDate = billDate;
        this.amount = amount;
    }

    public RecoveryAdjustmentDto(Long id, String name, Double value) {
        this.id = id;
        this.name = name;
        this.value = value;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
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

    public Date getBillDate() {
        return billDate;
    }

    public void setBillDate(Date billDate) {
        this.billDate = billDate;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public static class Config{
        static class ResultMappings{
            static final String RECOVERY_ADJUSTMENT_MAPPING = "RecoveryAdjustmentDto.RecoveryAdjustmentMapping";
            static final String RECOVERY_ADJUSTMENT_BY_CARRIER_MAPPING = "RecoveryAdjustmentDto.RecoveryAdjustmentByCarrierMapping";
            static final String RECOVERY_ADJUSTMENT_BY_MONTH_MAPPING = "RecoveryAdjustmentDto.RecoveryAdjustmentByMonthMapping";
        }

        static class StoredProcedureName{
            static final String RECOVERY_ADJUSTMENT = "SHP_DB_RECVRY_ADJ_PROC";
            static final String RECOVERY_ADJUSTMENT_BY_CARRIER = "SHP_DB_RECVRY_ADJ_CARR_PROC";
            static final String RECOVERY_ADJUSTMENT_BY_MONTH = "SHP_DB_RECVRY_ADJ_MNTH_PROC";
        }

        public static class StoredProcedureQueryName{
            public static final String RECOVERY_ADJUSTMENT = "RecoveryAdjustmentDto.getRecoveryAdjustment";
            public static final String RECOVERY_ADJUSTMENT_BY_CARRIER = "RecoveryAdjustmentDto.getRecoveryAdjustmentByCarrier";
            public static final String RECOVERY_ADJUSTMENT_BY_MONTH = "RecoveryAdjustmentDto.getRecoveryAdjustmentByMonth";
        }
    }
}
