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
        @NamedStoredProcedureQuery(name = "InboundSpendDto.getInboundSpend", procedureName = "SHP_DB_INBND_SPEND_PROC",
            resultSetMappings = "InboundSpendDto.InboundSpendMapping",
            parameters = {
                    @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.InboundSpendParams.DATE_TYPE_PARAM, type = String.class),
                    @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.InboundSpendParams.CURRENCY_ID_PARAM, type = Long.class),
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
        @NamedStoredProcedureQuery(name = "InboundSpendDto.getInboundSpendByMonth", procedureName = "SHP_DB_INBND_SPEND_MNTH_PROC",
                resultSetMappings = "InboundSpendDto.InboundSpendByMonthMapping",
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.InboundSpendParams.DATE_TYPE_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.InboundSpendParams.CURRENCY_ID_PARAM, type = Long.class),
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
        @SqlResultSetMapping(name = "InboundSpendDto.InboundSpendMapping",
                classes = {
                        @ConstructorResult(targetClass = InboundSpendDto.class,
                        columns = {
                                @ColumnResult(name = "BILLING_DATE", type = String.class),
                                @ColumnResult(name = "CARRIER_ID", type = Long.class),
                                @ColumnResult(name = "CARRIER_NAME", type = String.class),
                                @ColumnResult(name = "NET_DUE_AMOUNT", type = Double.class)
                        })
                }),
        @SqlResultSetMapping(name = "InboundSpendDto.InboundSpendByMonthMapping",
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
}
