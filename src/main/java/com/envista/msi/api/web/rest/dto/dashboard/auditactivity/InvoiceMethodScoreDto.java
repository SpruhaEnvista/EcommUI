package com.envista.msi.api.web.rest.dto.dashboard.auditactivity;

import com.envista.msi.api.domain.util.DashboardSroredProcParam;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by Sujit kumar on 09/02/2017.
 */
@NamedStoredProcedureQueries({
        @NamedStoredProcedureQuery(name = InvoiceMethodScoreDto.Config.StoredProcedureQueryName.INVOICE_METHOD_SCORE,
                procedureName = InvoiceMethodScoreDto.Config.StoredProcedureName.INVOICE_METHOD_SCORE,
                resultSetMappings = {InvoiceMethodScoreDto.Config.ResultMappings.INVOICE_METHOD_SCORE_MAPPING},
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.InvoiceMethodScoreParams.DATE_TYPE_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.InvoiceMethodScoreParams.CUSTOMER_IDS_CSV_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.InvoiceMethodScoreParams.CARRIER_IDS_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.InvoiceMethodScoreParams.MODES_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.InvoiceMethodScoreParams.SERVICES_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.InvoiceMethodScoreParams.LANES_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.InvoiceMethodScoreParams.FROM_DATE_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.InvoiceMethodScoreParams.TO_DATE_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.InvoiceMethodScoreParams.MODE_NAMES_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, name = DashboardSroredProcParam.InvoiceMethodScoreParams.INVOICE_METHOD_SCORE_DATA_PARAM, type = Void.class)
                }),
        @NamedStoredProcedureQuery(name = InvoiceMethodScoreDto.Config.StoredProcedureQueryName.INVOICE_METHOD_SCORE_BY_CARRIER,
                procedureName = InvoiceMethodScoreDto.Config.StoredProcedureName.INVOICE_METHOD_SCORE_BY_CARRIER,
                resultSetMappings = {InvoiceMethodScoreDto.Config.ResultMappings.INVOICE_METHOD_SCORE_BY_CARRIER_MAPPING},
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.InvoiceMethodScoreParams.DATE_TYPE_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.InvoiceMethodScoreParams.CUSTOMER_IDS_CSV_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.InvoiceMethodScoreParams.CARRIER_IDS_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.InvoiceMethodScoreParams.MODES_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.InvoiceMethodScoreParams.SERVICES_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.InvoiceMethodScoreParams.LANES_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.InvoiceMethodScoreParams.FROM_DATE_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.InvoiceMethodScoreParams.TO_DATE_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.InvoiceMethodScoreParams.MODE_NAMES_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.InvoiceMethodScoreParams.INVOICE_METHOD_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, name = DashboardSroredProcParam.InvoiceMethodScoreParams.INVOICE_METHOD_SCORE_DATA_PARAM, type = Void.class)
                }),
        @NamedStoredProcedureQuery(name = InvoiceMethodScoreDto.Config.StoredProcedureQueryName.INVOICE_METHOD_SCORE_BY_MONTH,
                procedureName = InvoiceMethodScoreDto.Config.StoredProcedureName.INVOICE_METHOD_SCORE_BY_MONTH,
                resultSetMappings = {InvoiceMethodScoreDto.Config.ResultMappings.INVOICE_METHOD_SCORE_BY_MONTH_MAPPING},
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.InvoiceMethodScoreParams.DATE_TYPE_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.InvoiceMethodScoreParams.CUSTOMER_IDS_CSV_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.InvoiceMethodScoreParams.CARRIER_IDS_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.InvoiceMethodScoreParams.MODES_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.InvoiceMethodScoreParams.SERVICES_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.InvoiceMethodScoreParams.LANES_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.InvoiceMethodScoreParams.FROM_DATE_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.InvoiceMethodScoreParams.TO_DATE_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.InvoiceMethodScoreParams.MODE_NAMES_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.InvoiceMethodScoreParams.INVOICE_METHOD_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, name = DashboardSroredProcParam.InvoiceMethodScoreParams.INVOICE_METHOD_SCORE_DATA_PARAM, type = Void.class)
                })
})

@SqlResultSetMappings({
        @SqlResultSetMapping(name = InvoiceMethodScoreDto.Config.ResultMappings.INVOICE_METHOD_SCORE_MAPPING, classes = {
                @ConstructorResult(
                        targetClass = InvoiceMethodScoreDto.class,
                        columns = {
                                @ColumnResult(name = "INVOICE_METHOD_ID", type = String.class),
                                @ColumnResult(name = "NAME", type = String.class),
                                @ColumnResult(name = "VALUE", type = Double.class)
                        })
        }),
        @SqlResultSetMapping(name = InvoiceMethodScoreDto.Config.ResultMappings.INVOICE_METHOD_SCORE_BY_CARRIER_MAPPING, classes = {
                @ConstructorResult(
                        targetClass = InvoiceMethodScoreDto.class,
                        columns = {
                                @ColumnResult(name = "ID", type = Long.class),
                                @ColumnResult(name = "NAME", type = String.class),
                                @ColumnResult(name = "VALUE", type = Double.class)
                        })
        }),
        @SqlResultSetMapping(name = InvoiceMethodScoreDto.Config.ResultMappings.INVOICE_METHOD_SCORE_BY_MONTH_MAPPING, classes = {
                @ConstructorResult(
                        targetClass = InvoiceMethodScoreDto.class,
                        columns = {
                                @ColumnResult(name = "BILL_DATE", type = Date.class),
                                @ColumnResult(name = "AMOUNT", type = Double.class)
                        })
        })
})
@Entity
public class InvoiceMethodScoreDto implements Serializable {
    @Id
    @Column(name = "ID")
    private Long id;

    @Column(name = "INVOICE_METHOD_ID")
    private String invoiceMethodId;

    @Column(name = "NAME")
    private String name;

    @Column(name = "VALUE")
    private Double value;

    @Column(name = "BILL_DATE")
    private Date billDate;

    @Column(name = "AMOUNT")
    private Double amount;

    public InvoiceMethodScoreDto(){}

    public InvoiceMethodScoreDto(String invoiceMethodId, String name, Double value) {
        this.invoiceMethodId = invoiceMethodId;
        this.name = name;
        this.value = value;
    }

    public InvoiceMethodScoreDto(Long id, String name, Double value) {
        this.id = id;
        this.name = name;
        this.value = value;
    }

    public InvoiceMethodScoreDto(Date billDate, Double amount) {
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

    public String getInvoiceMethodId() {
        return invoiceMethodId;
    }

    public void setInvoiceMethodId(String invoiceMethodId) {
        this.invoiceMethodId = invoiceMethodId;
    }

    public static class Config{
        static class ResultMappings{
            static final String INVOICE_METHOD_SCORE_MAPPING = "InvoiceMethodScoreDto.InvoiceMethodScoreMapping";
            static final String INVOICE_METHOD_SCORE_BY_CARRIER_MAPPING = "InvoiceMethodScoreDto.InvoiceMethodScoreByCarrierMapping";
            static final String INVOICE_METHOD_SCORE_BY_MONTH_MAPPING = "InvoiceMethodScoreDto.InvoiceMethodScoreByMonthMapping";
        }

        static class StoredProcedureName{
            static final String INVOICE_METHOD_SCORE = "SHP_DB_INV_MTD_SCORE_PROC";
            static final String INVOICE_METHOD_SCORE_BY_CARRIER = "SHP_DB_INV_MTD_SCORE_CARR_PROC";
            static final String INVOICE_METHOD_SCORE_BY_MONTH = "SHP_DB_INV_MTD_SCORE_MNTH_PROC";
        }

        public static class StoredProcedureQueryName{
            public static final String INVOICE_METHOD_SCORE = "InvoiceMethodScoreDto.getInvoiceMethodScore";
            public static final String INVOICE_METHOD_SCORE_BY_CARRIER = "InvoiceMethodScoreDto.getInvoiceMethodScoreByCarrier";
            public static final String INVOICE_METHOD_SCORE_BY_MONTH = "InvoiceMethodScoreDto.getInvoiceMethodScoreByMonth";
        }
    }
}
