package com.envista.msi.api.web.rest.dto.dashboard.shipmentoverview;

import com.envista.msi.api.domain.util.DashboardStoredProcParam;


import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by Siddhant on 13/02/2017.
 */
@NamedStoredProcedureQueries({
        @NamedStoredProcedureQuery(name = AverageSpendPerShipmentDto.Config.StoredProcedureQueryName.AVG_SPEND_PER_SHIPMENT,
                procedureName = AverageSpendPerShipmentDto.Config.StoredProcedureName.AVG_SPEND_PER_SHIPMENT,
                resultSetMappings = {AverageSpendPerShipmentDto.Config.ResultMappings.AVG_SPEND_PER_SHIPMENT_MAPPING},
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.AverageSpendShipmentParam.DATE_TYPE_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.AverageSpendShipmentParam.CONVERTED_CURRENCY_ID_PARAM, type = Long.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.AverageSpendShipmentParam.CONVERTED_CURRENCY_CODE_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.AverageSpendShipmentParam.MODES_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.AverageSpendShipmentParam.SERVICES_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.AverageSpendShipmentParam.ACCESSORIAL_NAME_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.AverageSpendShipmentParam.LANES_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.AverageSpendShipmentParam.FROM_DATE_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.AverageSpendShipmentParam.TO_DATE_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.AverageSpendShipmentParam.CARRIER_IDS_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.AverageSpendShipmentParam.CUSTOMER_IDS_CSV_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.AverageSpendShipmentParam.TOP_TEN_ACCESSORIAL_PARAM, type = Long.class),
                        @StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, name = DashboardStoredProcParam.AverageSpendShipmentParam.AVG_SPEND_PER_SHIPMT_PARAM, type = Void.class)
                }),
        @NamedStoredProcedureQuery(name = AverageSpendPerShipmentDto.Config.StoredProcedureQueryName.AVG_SPEND_PER_SHIPMENT_BY_CARRIER,
                procedureName = AverageSpendPerShipmentDto.Config.StoredProcedureName.AVG_SPEND_PER_SHIPMENT_BY_CARRIER,
                resultSetMappings = AverageSpendPerShipmentDto.Config.ResultMappings.AVG_SPEND_PER_SHIPMENT_BY_CARRIER_MAPPING,
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.AverageSpendShipmentByCarrierParam.DATE_TYPE_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.AverageSpendShipmentByCarrierParam.CONVERTED_CURRENCY_ID_PARAM, type = Long.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.AverageSpendShipmentByCarrierParam.CONVERTED_CURRENCY_CODE_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.AverageSpendShipmentByCarrierParam.MODES_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.AverageSpendShipmentByCarrierParam.SERVICES_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.AverageSpendShipmentByCarrierParam.ACCESSORIAL_NAME_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.AverageSpendShipmentByCarrierParam.LANES_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.AverageSpendShipmentByCarrierParam.FROM_DATE_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.AverageSpendShipmentByCarrierParam.TO_DATE_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.AverageSpendShipmentByCarrierParam.CARRIER_IDS_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.AverageSpendShipmentByCarrierParam.CUSTOMER_IDS_CSV_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.AverageSpendShipmentByCarrierParam.ORIGINAL_FROM_DATE_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.AverageSpendShipmentByCarrierParam.ORIGINAL_TO_DATE_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.AverageSpendShipmentByCarrierParam.TOP_TEN_ACCESSORIAL_PARAM, type = Long.class),
                        @StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, name = DashboardStoredProcParam.AverageSpendShipmentByCarrierParam.AVG_SPEND_PER_SHIPMT_PARAM, type = Void.class)
                }),
        @NamedStoredProcedureQuery(name = AverageSpendPerShipmentDto.Config.StoredProcedureQueryName.AVG_SPEND_PER_SHIPMENT_BY_MONTH,
                procedureName = AverageSpendPerShipmentDto.Config.StoredProcedureName.AVG_SPEND_PER_SHIPMENT_BY_MONTH,
                resultSetMappings = AverageSpendPerShipmentDto.Config.ResultMappings.AVG_SPEND_PER_SHIPMENT_BY_MONTH_MAPPING,
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.AverageSpendShipmentByMonthParam.DATE_TYPE_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.AverageSpendShipmentByMonthParam.CONVERTED_CURRENCY_ID_PARAM, type = Long.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.AverageSpendShipmentByMonthParam.CONVERTED_CURRENCY_CODE_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.AverageSpendShipmentByMonthParam.MODES_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.AverageSpendShipmentByMonthParam.SERVICES_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.AverageSpendShipmentByMonthParam.ACCESSORIAL_NAME_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.AverageSpendShipmentByMonthParam.LANES_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.AverageSpendShipmentByMonthParam.FROM_DATE_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.AverageSpendShipmentByMonthParam.TO_DATE_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.AverageSpendShipmentByMonthParam.CARRIER_IDS_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.AverageSpendShipmentByMonthParam.CUSTOMER_IDS_CSV_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.AverageSpendShipmentByMonthParam.TOP_TEN_ACCESSORIAL_PARAM, type = Long.class),
                        @StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, name = DashboardStoredProcParam.AverageSpendShipmentByMonthParam.AVG_SPEND_PER_SHIPMT_PARAM, type = Void.class)
                })
})

@SqlResultSetMappings({
        @SqlResultSetMapping(name = AverageSpendPerShipmentDto.Config.ResultMappings.AVG_SPEND_PER_SHIPMENT_MAPPING, classes = {
                @ConstructorResult(
                        targetClass = AverageSpendPerShipmentDto.class,
                        columns = {
                                @ColumnResult(name = "BILLING_DATE", type = String.class),
                                @ColumnResult(name = "MODES", type = String.class),
                                @ColumnResult(name = "NET_WEIGHT", type = Double.class)
                        }
                )
        }),
        @SqlResultSetMapping(name = AverageSpendPerShipmentDto.Config.ResultMappings.AVG_SPEND_PER_SHIPMENT_BY_CARRIER_MAPPING, classes = {
                @ConstructorResult(
                        targetClass = AverageSpendPerShipmentDto.class,
                        columns = {
                                @ColumnResult(name = "ID", type = Long.class),
                                @ColumnResult(name = "NAME", type = String.class),
                                @ColumnResult(name = "VALUE", type = Double.class)
                        }
                )
        }),
        @SqlResultSetMapping(name = AverageSpendPerShipmentDto.Config.ResultMappings.AVG_SPEND_PER_SHIPMENT_BY_MONTH_MAPPING, classes = {
                @ConstructorResult(
                        targetClass = AverageSpendPerShipmentDto.class,
                        columns = {
                                @ColumnResult(name = "BILL_DATE", type = Date.class),
                                @ColumnResult(name = "AMOUNT", type = Double.class),
                        }
                )
        })
})

@Entity
public class AverageSpendPerShipmentDto implements Serializable{
    @Id
    @Column(name = "ID")
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "VALUE")
    private Double value;

    @Column(name = "BILLING_DATE")
    private String billingDate;

    @Column(name = "MODES")
    private String modes;

    @Column(name = "NET_WEIGHT")
    private Double netWeight;

    @Column(name = "BILLL_DATE")
    private Date billDate;

    @Column(name = "AMOUNT")
    private Double amount;

    public AverageSpendPerShipmentDto() {  }

    public AverageSpendPerShipmentDto(String billingDate, String modes, Double netWeight) {
        this.billingDate = billingDate;
        this.modes = modes;
        this.netWeight = netWeight;
    }

    public AverageSpendPerShipmentDto(Long id, String name, Double value) {
        this.id = id;
        this.name = name;
        this.value = value;
    }

    public AverageSpendPerShipmentDto(Date billDate, Double amount) {
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

    public String getModes() {
        return modes;
    }

    public void setModes(String modes) {
        this.modes = modes;
    }

    public Double getNetWeight() {
        return netWeight;
    }

    public void setNetWeight(Double netWeight) {
        this.netWeight = netWeight;
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
            static final String AVG_SPEND_PER_SHIPMENT_MAPPING = "AverageSpendPerShipmentDto.AvgSpendPerShipmentMapping";
            static final String AVG_SPEND_PER_SHIPMENT_BY_CARRIER_MAPPING = "AverageSpendPerShipmentDto.AvgSpendPerShipmentByCarrierMapping";
            static final String AVG_SPEND_PER_SHIPMENT_BY_MONTH_MAPPING = "AverageSpendPerShipmentDto.AvgSpendPerShipmentByMonthMapping";
        }

        static class StoredProcedureName{
            static final String AVG_SPEND_PER_SHIPMENT = "SHP_DB_AVG_SPEND_PROC";
            static final String AVG_SPEND_PER_SHIPMENT_BY_CARRIER = "SHP_DB_AVG_SPEND_BY_CARR_PROC";
            static final String AVG_SPEND_PER_SHIPMENT_BY_MONTH = "SHP_DB_AVG_SPEND_BY_MNTH_PROC";
        }

        public static class StoredProcedureQueryName{
            public static final String AVG_SPEND_PER_SHIPMENT = "AverageSpendPerShipmentDto.getAverageSpendPerShipment";
            public static final String AVG_SPEND_PER_SHIPMENT_BY_CARRIER = "AverageSpendPerShipmentDto.getAverageSpendPerShipmentByCarrier";
            public static final String AVG_SPEND_PER_SHIPMENT_BY_MONTH = "AverageSpendPerShipmentDto.getAverageSpendPerShipmentByMonth";
        }
    }
}
