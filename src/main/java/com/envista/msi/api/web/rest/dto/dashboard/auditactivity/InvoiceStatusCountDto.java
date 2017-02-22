package com.envista.msi.api.web.rest.dto.dashboard.auditactivity;

import com.envista.msi.api.domain.util.DashboardSroredProcParam;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by Sujit kumar on 09/02/2017.
 */

@NamedStoredProcedureQueries({
        @NamedStoredProcedureQuery(name = InvoiceStatusCountDto.Config.StoredProcedureQueryName.INVOICE_STATUS_COUNT,
                procedureName = InvoiceStatusCountDto.Config.StoredProcedureName.INVOICE_STATUS_COUNT,
                resultSetMappings = {InvoiceStatusCountDto.Config.ResultMappings.INVOICE_STATUS_COUNT_MAPPING},
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.InvoiceStatusCountParams.DATE_TYPE_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.InvoiceStatusCountParams.CUSTOMER_IDS_CSV_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.InvoiceStatusCountParams.CARRIER_IDS_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.InvoiceStatusCountParams.MODES_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.InvoiceStatusCountParams.SERVICES_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.InvoiceStatusCountParams.LANES_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.InvoiceStatusCountParams.FROM_DATE_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.InvoiceStatusCountParams.TO_DATE_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.InvoiceStatusCountParams.MODE_NAMES_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, name = DashboardSroredProcParam.InvoiceStatusCountParams.INVOICE_COUNT_DATA_PARAM, type = Void.class)
                }),
        @NamedStoredProcedureQuery(name = InvoiceStatusCountDto.Config.StoredProcedureQueryName.INVOICE_STATUS_COUNT_BY_CARRIER,
                procedureName = InvoiceStatusCountDto.Config.StoredProcedureName.INVOICE_STATUS_COUNT_BY_CARRIER,
                resultSetMappings = InvoiceStatusCountDto.Config.ResultMappings.INVOICE_STATUS_COUNT_BY_CARRIER_MAPPING,
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.InvoiceStatusCountParams.DATE_TYPE_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.InvoiceStatusCountParams.CUSTOMER_IDS_CSV_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.InvoiceStatusCountParams.CARRIER_IDS_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.InvoiceStatusCountParams.MODES_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.InvoiceStatusCountParams.SERVICES_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.InvoiceStatusCountParams.LANES_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.InvoiceStatusCountParams.FROM_DATE_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.InvoiceStatusCountParams.TO_DATE_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.InvoiceStatusCountParams.MODE_NAMES_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.InvoiceStatusCountParams.INVOICE_STATUS_ID_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, name = DashboardSroredProcParam.InvoiceStatusCountParams.INVOICE_COUNT_DATA_PARAM, type = Void.class)
                }),
        @NamedStoredProcedureQuery(name = InvoiceStatusCountDto.Config.StoredProcedureQueryName.INVOICE_STATUS_COUNT_BY_MONTH,
                procedureName = InvoiceStatusCountDto.Config.StoredProcedureName.INVOICE_STATUS_COUNT_BY_MONTH,
                resultSetMappings = InvoiceStatusCountDto.Config.ResultMappings.INVOICE_STATUS_COUNT_BY_MONTH_MAPPING,
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.InvoiceStatusCountParams.DATE_TYPE_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.InvoiceStatusCountParams.CUSTOMER_IDS_CSV_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.InvoiceStatusCountParams.CARRIER_IDS_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.InvoiceStatusCountParams.MODES_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.InvoiceStatusCountParams.SERVICES_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.InvoiceStatusCountParams.LANES_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.InvoiceStatusCountParams.FROM_DATE_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.InvoiceStatusCountParams.TO_DATE_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.InvoiceStatusCountParams.MODE_NAMES_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.InvoiceStatusCountParams.INVOICE_STATUS_ID_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, name = DashboardSroredProcParam.InvoiceStatusCountParams.INVOICE_COUNT_DATA_PARAM, type = Void.class)
                })
})

@SqlResultSetMappings({
        @SqlResultSetMapping(name = InvoiceStatusCountDto.Config.ResultMappings.INVOICE_STATUS_COUNT_MAPPING, classes = {
                @ConstructorResult(
                        targetClass = InvoiceStatusCountDto.class,
                        columns = {
                                @ColumnResult(name = "ID", type = Long.class),
                                @ColumnResult(name = "NAME", type = String.class),
                                @ColumnResult(name = "VALUE", type = Double.class)
                        })
        }),
        @SqlResultSetMapping(name = InvoiceStatusCountDto.Config.ResultMappings.INVOICE_STATUS_COUNT_BY_MONTH_MAPPING, classes = {
                @ConstructorResult(
                        targetClass = InvoiceStatusCountDto.class,
                        columns = {
                                @ColumnResult(name = "BILL_DATE", type = Date.class),
                                @ColumnResult(name = "AMOUNT", type = Double.class)
                        })
        })
})

@Entity
public class InvoiceStatusCountDto implements Serializable {

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

    public InvoiceStatusCountDto(){}

    public InvoiceStatusCountDto(Long id, String name, Double value) {
        this.id = id;
        this.name = name;
        this.value = value;
    }

    public InvoiceStatusCountDto(Date billDate, Double amount) {
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
            static final String INVOICE_STATUS_COUNT_MAPPING = "InvoiceStatusCountDto.InvoiceStatusCountMapping";
            static final String INVOICE_STATUS_COUNT_BY_CARRIER_MAPPING = INVOICE_STATUS_COUNT_MAPPING;
            static final String INVOICE_STATUS_COUNT_BY_MONTH_MAPPING = "InvoiceStatusCountDto.InvoiceStatusCountByMonthMapping";
        }

        static class StoredProcedureName{
            static final String INVOICE_STATUS_COUNT = "SHP_DB_INV_STS_CNT_PROC";
            static final String INVOICE_STATUS_COUNT_BY_CARRIER = "SHP_DB_INV_STS_CNT_CARR_PROC";
            static final String INVOICE_STATUS_COUNT_BY_MONTH = "SHP_DB_INV_STS_CNT_MNTH_PROC";
        }

        public static class StoredProcedureQueryName{
            public static final String INVOICE_STATUS_COUNT = "InvoiceStatusCountDto.getInvoiceStatusCount";
            public static final String INVOICE_STATUS_COUNT_BY_CARRIER = "InvoiceStatusCountDto.getInvoiceStatusCountByCarrier";
            public static final String INVOICE_STATUS_COUNT_BY_MONTH = "InvoiceStatusCountDto.getInvoiceStatusCountByMonth";
        }
    }
}
