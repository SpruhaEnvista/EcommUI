package com.envista.msi.api.web.rest.dto.dashboard.auditactivity;

import com.envista.msi.api.domain.util.DashboardSroredProcParam;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by Sujit kumar on 09/02/2017.
 */
@NamedStoredProcedureQueries({
        @NamedStoredProcedureQuery(name = InvoiceStatusAmountDto.Config.StoredProcedureQueryName.INVOICE_STATUS_AMOUNT,
                procedureName = InvoiceStatusAmountDto.Config.StoredProcedureName.INVOICE_STATUS_AMOUNT,
                resultSetMappings = InvoiceStatusAmountDto.Config.ResultMappings.INVOICE_STATUS_AMOUNT_MAPPING,
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.InvoiceStatusAmountParams.DATE_TYPE_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.InvoiceStatusAmountParams.CONVERTED_CURRENCY_ID_PARAM, type = Long.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.InvoiceStatusAmountParams.CUSTOMER_IDS_CSV_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.InvoiceStatusAmountParams.CARRIER_IDS_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.InvoiceStatusAmountParams.MODES_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.InvoiceStatusAmountParams.SERVICES_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.InvoiceStatusAmountParams.LANES_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.InvoiceStatusAmountParams.FROM_DATE_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.InvoiceStatusAmountParams.TO_DATE_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.InvoiceStatusAmountParams.MODE_NAMES_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, name = DashboardSroredProcParam.InvoiceStatusAmountParams.INVOICE_AMOUNT_DATA_PARAM, type = Void.class)
                }),
        @NamedStoredProcedureQuery(name = InvoiceStatusAmountDto.Config.StoredProcedureQueryName.INVOICE_STATUS_AMOUNT_BY_CARRIER,
                procedureName = InvoiceStatusAmountDto.Config.StoredProcedureName.INVOICE_STATUS_AMOUNT_BY_CARRIER,
                resultSetMappings = InvoiceStatusAmountDto.Config.ResultMappings.INVOICE_STATUS_AMOUNT_BY_CARRIER_MAPPING,
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.InvoiceStatusAmountParams.DATE_TYPE_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.InvoiceStatusAmountParams.CONVERTED_CURRENCY_ID_PARAM, type = Long.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.InvoiceStatusAmountParams.CUSTOMER_IDS_CSV_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.InvoiceStatusAmountParams.CARRIER_IDS_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.InvoiceStatusAmountParams.MODES_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.InvoiceStatusAmountParams.SERVICES_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.InvoiceStatusAmountParams.LANES_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.InvoiceStatusAmountParams.FROM_DATE_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.InvoiceStatusAmountParams.TO_DATE_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.InvoiceStatusAmountParams.MODE_NAMES_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.InvoiceStatusAmountParams.INVOICE_STATUS_ID_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, name = DashboardSroredProcParam.InvoiceStatusAmountParams.INVOICE_AMOUNT_DATA_PARAM, type = Void.class)
                }),
        @NamedStoredProcedureQuery(name = InvoiceStatusAmountDto.Config.StoredProcedureQueryName.INVOICE_STATUS_AMOUNT_BY_MONTH,
                procedureName = InvoiceStatusAmountDto.Config.StoredProcedureName.INVOICE_STATUS_AMOUNT_BY_MONTH,
                resultSetMappings = InvoiceStatusAmountDto.Config.ResultMappings.INVOICE_STATUS_AMOUNT_BY_MONTH_MAPPING,
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.InvoiceStatusAmountParams.DATE_TYPE_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.InvoiceStatusAmountParams.CONVERTED_CURRENCY_ID_PARAM, type = Long.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.InvoiceStatusAmountParams.CUSTOMER_IDS_CSV_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.InvoiceStatusAmountParams.CARRIER_IDS_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.InvoiceStatusAmountParams.MODES_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.InvoiceStatusAmountParams.SERVICES_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.InvoiceStatusAmountParams.LANES_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.InvoiceStatusAmountParams.FROM_DATE_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.InvoiceStatusAmountParams.TO_DATE_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.InvoiceStatusAmountParams.MODE_NAMES_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.InvoiceStatusAmountParams.INVOICE_STATUS_ID_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, name = DashboardSroredProcParam.InvoiceStatusAmountParams.INVOICE_AMOUNT_DATA_PARAM, type = Void.class)
                })
})

@SqlResultSetMappings({
        @SqlResultSetMapping(name = InvoiceStatusAmountDto.Config.ResultMappings.INVOICE_STATUS_AMOUNT_MAPPING, classes = {
                @ConstructorResult(
                        targetClass = InvoiceStatusAmountDto.class,
                        columns = {
                                @ColumnResult(name = "ID", type = Long.class),
                                @ColumnResult(name = "NAME", type = String.class),
                                @ColumnResult(name = "VALUE", type = Double.class)
                        })
        }),
        @SqlResultSetMapping(name = InvoiceStatusAmountDto.Config.ResultMappings.INVOICE_STATUS_AMOUNT_BY_MONTH_MAPPING, classes = {
                @ConstructorResult(
                        targetClass = InvoiceStatusAmountDto.class,
                        columns = {
                                @ColumnResult(name = "BILL_DATE", type = Date.class),
                                @ColumnResult(name = "AMOUNT", type = Double.class)
                        })
        })
})

@Entity
public class InvoiceStatusAmountDto implements Serializable {
    @Id
    @Column(name = "ID")
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "VALUE")
    private Double value;

    @Column(name = "BILL_DATE")
    private Date billDate;

    @Column(name = "AMOUNT")
    private Double amount;

    public InvoiceStatusAmountDto(){}

    public InvoiceStatusAmountDto(Long id, String name, Double value) {
        this.id = id;
        this.name = name;
        this.value = value;
    }

    public InvoiceStatusAmountDto(Date billDate, Double amount) {
        this.billDate = billDate;
        this.amount = amount;
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
            static final String INVOICE_STATUS_AMOUNT_MAPPING = "InvoiceStatusAmountDto.InvoiceStatusAmountMapping";
            static final String INVOICE_STATUS_AMOUNT_BY_CARRIER_MAPPING = INVOICE_STATUS_AMOUNT_MAPPING;
            static final String INVOICE_STATUS_AMOUNT_BY_MONTH_MAPPING = "InvoiceStatusAmountDto.InvoiceStatusAmountByMonthMapping";
        }

        static class StoredProcedureName{
            static final String INVOICE_STATUS_AMOUNT = "SHP_DB_INV_STS_AMT_PROC";
            static final String INVOICE_STATUS_AMOUNT_BY_CARRIER = "SHP_DB_INV_STS_AMT_CARR_PROC";
            static final String INVOICE_STATUS_AMOUNT_BY_MONTH = "SHP_DB_INV_STS_AMT_MNTH_PROC";
        }

        public static class StoredProcedureQueryName{
            public static final String INVOICE_STATUS_AMOUNT = "InvoiceStatusAmountDto.getInvoiceStatusAmount";
            public static final String INVOICE_STATUS_AMOUNT_BY_CARRIER = "InvoiceStatusAmountDto.getInvoiceStatusAmountByCarrier";
            public static final String INVOICE_STATUS_AMOUNT_BY_MONTH = "InvoiceStatusAmountDto.getInvoiceStatusAmountByMonth";
        }
    }
}
