package com.envista.msi.api.web.rest.dto.dashboard.netspend;

import com.envista.msi.api.domain.util.DashboardStoredProcParam;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Sujit kumar on 31/01/2017.
 */
@NamedStoredProcedureQueries({
        @NamedStoredProcedureQuery(name = NetSpendOverTimeDto.Config.StoredProcedureQueryName.NET_SPEND_OVER_TIME,
                procedureName = NetSpendOverTimeDto.Config.StoredProcedureName.NET_SPEND_OVER_TIME,
                resultSetMappings = NetSpendOverTimeDto.Config.ResultMappings.NET_SPEND_OVER_TIME_MAPPING,
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.NetSpendParams.DATE_TYPE_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.NetSpendParams.CONVERTED_CURRENCY_ID_PARAM, type = Long.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.NetSpendParams.CONVERTED_CURRENCY_CODE_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.NetSpendParams.CUSTOMER_IDS_CSV_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.NetSpendParams.CARRIER_IDS_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.NetSpendParams.MODES_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.NetSpendParams.SERVICES_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.NetSpendParams.LANES_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.NetSpendParams.ACCESSORIAL_NAME_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.NetSpendParams.FROM_DATE_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.NetSpendParams.TO_DATE_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.NetSpendParams.TOP_TEN_ACCESSORIAL_PARAM, type = Long.class),
                        @StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, name = DashboardStoredProcParam.NetSpendParams.NET_SPEND_PARAM, type = Void.class)
                }),
        @NamedStoredProcedureQuery(name = NetSpendOverTimeDto.Config.StoredProcedureQueryName.NET_SPEND_OVER_TIME_BY_MONTH,
                procedureName = NetSpendOverTimeDto.Config.StoredProcedureName.NET_SPEND_OVER_TIME_BY_MONTH,
                resultSetMappings = NetSpendOverTimeDto.Config.ResultMappings.NET_SPEND_OVER_TIME_BY_MONTH_MAPPING,
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.NetSpendParams.DATE_TYPE_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.NetSpendParams.CONVERTED_CURRENCY_ID_PARAM, type = Long.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.NetSpendParams.CONVERTED_CURRENCY_CODE_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.NetSpendParams.CUSTOMER_IDS_CSV_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.NetSpendParams.CARRIER_IDS_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.NetSpendParams.MODES_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.NetSpendParams.SERVICES_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.NetSpendParams.LANES_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.NetSpendParams.ACCESSORIAL_NAME_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.NetSpendParams.FROM_DATE_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.NetSpendParams.TO_DATE_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.NetSpendParams.TOP_TEN_ACCESSORIAL_PARAM, type = Long.class),
                        @StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, name = DashboardStoredProcParam.NetSpendParams.NET_SPEND_PARAM, type = Void.class)
                })
})

@SqlResultSetMappings({
        @SqlResultSetMapping(name = NetSpendOverTimeDto.Config.ResultMappings.NET_SPEND_OVER_TIME_MAPPING,
                classes = {
                        @ConstructorResult(targetClass = NetSpendOverTimeDto.class,
                        columns = {
                                @ColumnResult(name = "BILLING_DATE", type = String.class),
                                @ColumnResult(name = "CARRIER_NAME", type = String.class),
                                @ColumnResult(name = "CARRIER_ID", type = Long.class),
                                @ColumnResult(name = "NET_CHARGES", type = Double.class)
                        })
                }),
        @SqlResultSetMapping(name = NetSpendOverTimeDto.Config.ResultMappings.NET_SPEND_OVER_TIME_BY_MONTH_MAPPING,
                classes = {
                        @ConstructorResult(targetClass = NetSpendOverTimeDto.class,
                                columns = {
                                        @ColumnResult(name = "BILL_DATE", type = Date.class),
                                        @ColumnResult(name = "AMOUNT", type = Double.class)
                                })
                })
})

@Entity
public class NetSpendOverTimeDto {
    @Id
    private Long id;

    @Column(name = "BILLING_DATE")
    private String billingDate;

    @Column(name = "CARRIER_NAME")
    private String carrierName;

    @Column(name = "CARRIER_ID")
    private Long carrierId;

    @Column(name = "NET_CHARGES")
    private Double netCharges;

    @Column(name = "BILL_DATE")
    private Date billDate;

    @Column(name = "AMOUNT")
    private Double amount;

    public NetSpendOverTimeDto(String billingDate, String carrierName, Long carrierId, Double netCharges) {
        this.billingDate = billingDate;
        this.carrierName = carrierName;
        this.carrierId = carrierId;
        this.netCharges = netCharges;
    }

    public NetSpendOverTimeDto(Date billDate, Double amount) {
        this.billDate = billDate;
        this.amount = amount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCarrierName() {
        return carrierName;
    }

    public void setCarrierName(String carrierName) {
        this.carrierName = carrierName;
    }

    public Long getCarrierId() {
        return carrierId;
    }

    public void setCarrierId(Long carrierId) {
        this.carrierId = carrierId;
    }

    public Double getNetCharges() {
        return netCharges;
    }

    public void setNetCharges(Double netCharges) {
        this.netCharges = netCharges;
    }

    public String getBillingDate() {
        return billingDate;
    }

    public void setBillingDate(String billingDate) {
        this.billingDate = billingDate;
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
            static final String NET_SPEND_OVER_TIME_MAPPING = "NetSpendOverTimeDto.NetSpendOverTimeMapping";
            static final String NET_SPEND_OVER_TIME_BY_MONTH_MAPPING = "NetSpendOverTimeDto.NetSpendOverTimeByMonthMapping";
        }

        static class StoredProcedureName{
            static final String NET_SPEND_OVER_TIME = "SHP_DB_NET_OVER_TIME_PROC";
            static final String NET_SPEND_OVER_TIME_BY_MONTH = "SHP_DB_NET_OVR_TIME_MNTH_PROC";
        }

        public static class StoredProcedureQueryName{
            public static final String NET_SPEND_OVER_TIME = "NetSpendOverTimeDto.getNetSpendOverTime";
            public static final String NET_SPEND_OVER_TIME_BY_MONTH = "NetSpendOverTimeDto.getNetSpendOverByMonthTime";
        }
    }
}
