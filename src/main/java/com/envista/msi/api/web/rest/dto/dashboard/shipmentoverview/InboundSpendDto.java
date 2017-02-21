package com.envista.msi.api.web.rest.dto.dashboard.shipmentoverview;

import com.envista.msi.api.domain.util.DashboardSroredProcParam;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Sujit kumar on 15/02/2017.
 */
@NamedStoredProcedureQueries({
        @NamedStoredProcedureQuery(name = InboundSpendDto.Config.StoredProcedureQueryName.INBOUND_SPEND, procedureName = InboundSpendDto.Config.StoredProcedureName.INBOUND_SPEND,
            resultSetMappings = InboundSpendDto.Config.ResultMappings.INBOUND_SPEND_MAPPING,
            parameters = {
                    @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.InboundSpendParams.DATE_TYPE_PARAM, type = String.class),
                    @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.InboundSpendParams.CONVERTED_CURRENCY_ID_PARAM, type = Long.class),
                    @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.InboundSpendParams.CONVERTED_CURRENCY_CODE_PARAM, type = String.class),
                    @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.InboundSpendParams.CUSTOMER_IDS_CSV_PARAM, type = String.class),
                    @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.InboundSpendParams.CARRIER_IDS_PARAM, type = String.class),
                    @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.InboundSpendParams.MODES_PARAM, type = String.class),
                    @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.InboundSpendParams.SERVICES_PARAM, type = String.class),
                    @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.InboundSpendParams.LANES_PARAM, type = String.class),
                    @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.InboundSpendParams.FROM_DATE_PARAM, type = String.class),
                    @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.InboundSpendParams.TO_DATE_PARAM, type = String.class),
                    @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.InboundSpendParams.ACCESSORIAL_NAME_PARAM, type = String.class),
                    @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.InboundSpendParams.TOP_TEN_ACCESSORIAL_PARAM, type = Long.class),
                    @StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, name = DashboardSroredProcParam.InboundSpendParams.INBOUND_SPEND_DATA, type = Void.class)
            }),
        @NamedStoredProcedureQuery(name = InboundSpendDto.Config.StoredProcedureQueryName.INBOUND_SPEND_BY_MONTH, procedureName = InboundSpendDto.Config.StoredProcedureName.INBOUND_SPEND_BY_MONTH,
                resultSetMappings = InboundSpendDto.Config.ResultMappings.INBOUND_SPEND_BY_MONTH_MAPPING,
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.InboundSpendParams.DATE_TYPE_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.InboundSpendParams.CONVERTED_CURRENCY_ID_PARAM, type = Long.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.InboundSpendParams.CONVERTED_CURRENCY_CODE_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.InboundSpendParams.CUSTOMER_IDS_CSV_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.InboundSpendParams.CARRIER_IDS_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.InboundSpendParams.MODES_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.InboundSpendParams.SERVICES_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.InboundSpendParams.LANES_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.InboundSpendParams.FROM_DATE_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.InboundSpendParams.TO_DATE_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.InboundSpendParams.ACCESSORIAL_NAME_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.InboundSpendParams.TOP_TEN_ACCESSORIAL_PARAM, type = Long.class),
                        @StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, name = DashboardSroredProcParam.InboundSpendParams.INBOUND_SPEND_DATA, type = Void.class)
                })
})

@SqlResultSetMappings({
        @SqlResultSetMapping(name = InboundSpendDto.Config.ResultMappings.INBOUND_SPEND_MAPPING,
                classes = {
                        @ConstructorResult(targetClass = InboundSpendDto.class,
                        columns = {
                                @ColumnResult(name = "BILLING_DATE", type = String.class),
                                @ColumnResult(name = "CARRIER_ID", type = Long.class),
                                @ColumnResult(name = "CARRIER_NAME", type = String.class),
                                @ColumnResult(name = "NET_DUE_AMOUNT", type = Double.class)
                        })
                }),
        @SqlResultSetMapping(name = InboundSpendDto.Config.ResultMappings.INBOUND_SPEND_BY_MONTH_MAPPING,
                classes = {
                        @ConstructorResult(targetClass = InboundSpendDto.class,
                                columns = {
                                        @ColumnResult(name = "BILL_DATE", type = Date.class),
                                        @ColumnResult(name = "AMOUNT", type = Double.class)
                                })
                })
})

@Entity
public class InboundSpendDto implements Serializable {
    @Id
    private Long id;

    @Column(name = "BILLING_DATE")
    private String billingDate;

    @Column(name = "CARRIER_ID")
    private Long carrierId;

    @Column(name = "CARRIER_NAME")
    private String carrierName;

    @Column(name = "NET_DUE_AMOUNT")
    private Double netDueAmount;

    @Column(name = "BILL_DATE")
    private Date billDate;

    @Column(name = "AMOUNT")
    private Double amount;

    public InboundSpendDto(){}

    public InboundSpendDto(String billingDate, Long carrierId, String carrierName, Double netDueAmount) {
        this.billingDate = billingDate;
        this.carrierId = carrierId;
        this.carrierName = carrierName;
        this.netDueAmount = netDueAmount;
    }

    public InboundSpendDto(Date billDate, Double amount) {
        this.billDate = billDate;
        this.amount = amount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBillingDate() {
        return billingDate;
    }

    public void setBillingDate(String billingDate) {
        this.billingDate = billingDate;
    }

    public String getCarrierName() {
        return carrierName;
    }

    public void setCarrierName(String carrierName) {
        this.carrierName = carrierName;
    }

    public Double getNetDueAmount() {
        return netDueAmount;
    }

    public void setNetDueAmount(Double netDueAmount) {
        this.netDueAmount = netDueAmount;
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

    public Long getCarrierId() {
        return carrierId;
    }

    public void setCarrierId(Long carrierId) {
        this.carrierId = carrierId;
    }

    public static class Config{
        static class ResultMappings{
            static final String INBOUND_SPEND_MAPPING = "InboundSpendDto.InboundSpendMapping";
            static final String INBOUND_SPEND_BY_MONTH_MAPPING = "InboundSpendDto.InboundSpendByMonthMapping";
        }

        static class StoredProcedureName{
            static final String INBOUND_SPEND = "SHP_DB_INBND_SPEND_PROC";
            static final String INBOUND_SPEND_BY_MONTH = "SHP_DB_INBND_SPEND_MNTH_PROC";
        }

        public static class StoredProcedureQueryName{
            public static final String INBOUND_SPEND = "InboundSpendDto.getInboundSpend";
            public static final String INBOUND_SPEND_BY_MONTH = "InboundSpendDto.getInboundSpendByMonth";
        }
    }
}
