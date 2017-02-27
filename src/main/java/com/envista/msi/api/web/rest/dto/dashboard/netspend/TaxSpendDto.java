package com.envista.msi.api.web.rest.dto.dashboard.netspend;

import com.envista.msi.api.domain.util.DashboardStoredProcParam;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Sujit kumar on 01/02/2017.
 */
@NamedStoredProcedureQueries({
        @NamedStoredProcedureQuery(name = TaxSpendDto.Config.StoredProcedureQueryName.TAX_SPEND,
                procedureName = TaxSpendDto.Config.StoredProcedureName.TAX_SPEND,
                resultSetMappings = {TaxSpendDto.Config.ResultMappings.TAX_SPEND_MAPPING},
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.TaxSpendParams.DATE_TYPE_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.TaxSpendParams.CONVERTED_CURRENCY_ID_PARAM, type = Long.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.TaxSpendParams.CUSTOMER_IDS_CSV_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.TaxSpendParams.CARRIER_IDS_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.TaxSpendParams.MODES_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.TaxSpendParams.SERVICES_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.TaxSpendParams.LANES_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.TaxSpendParams.FROM_DATE_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.TaxSpendParams.TO_DATE_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.TaxSpendParams.MODE_NAMES_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, name = DashboardStoredProcParam.TaxSpendParams.TAX_SPEND_PARAM, type = Void.class)
                }),
        @NamedStoredProcedureQuery(name = TaxSpendDto.Config.StoredProcedureQueryName.TAX_SPEND_BY_CARRIER,
                procedureName = TaxSpendDto.Config.StoredProcedureName.TAX_SPEND_BY_CARRIER,
                resultSetMappings = {TaxSpendDto.Config.ResultMappings.TAX_SPEND_BY_CARRIER_MAPPING},
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.TaxSpendParams.DATE_TYPE_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.TaxSpendParams.CONVERTED_CURRENCY_ID_PARAM, type = Long.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.TaxSpendParams.CUSTOMER_IDS_CSV_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.TaxSpendParams.CARRIER_IDS_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.TaxSpendParams.MODES_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.TaxSpendParams.SERVICES_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.TaxSpendParams.LANES_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.TaxSpendParams.FROM_DATE_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.TaxSpendParams.TO_DATE_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.TaxSpendParams.MODE_NAMES_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.TaxSpendParams.TAX_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, name = DashboardStoredProcParam.TaxSpendParams.TAX_SPEND_PARAM, type = Void.class)
                }),
        @NamedStoredProcedureQuery(name = TaxSpendDto.Config.StoredProcedureQueryName.TAX_SPEND_BY_MONTH,
                procedureName = TaxSpendDto.Config.StoredProcedureName.TAX_SPEND_BY_MONTH,
                resultSetMappings = {TaxSpendDto.Config.ResultMappings.TAX_SPEND_BY_MONTH_MAPPING},
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.TaxSpendParams.DATE_TYPE_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.TaxSpendParams.CONVERTED_CURRENCY_ID_PARAM, type = Long.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.TaxSpendParams.CUSTOMER_IDS_CSV_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.TaxSpendParams.CARRIER_IDS_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.TaxSpendParams.MODES_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.TaxSpendParams.SERVICES_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.TaxSpendParams.LANES_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.TaxSpendParams.FROM_DATE_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.TaxSpendParams.TO_DATE_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.TaxSpendParams.MODE_NAMES_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.TaxSpendParams.TAX_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, name = DashboardStoredProcParam.TaxSpendParams.TAX_SPEND_PARAM, type = Void.class)
                })
})

@SqlResultSetMappings({
        @SqlResultSetMapping(name = TaxSpendDto.Config.ResultMappings.TAX_SPEND_MAPPING,
                classes = {
                        @ConstructorResult(targetClass = TaxSpendDto.class,
                        columns = {
                                @ColumnResult(name = "RANK", type = Long.class),
                                @ColumnResult(name = "TAX", type = String.class),
                                @ColumnResult(name = "SPEND", type = Double.class)
                        })
                }),
        @SqlResultSetMapping(name = TaxSpendDto.Config.ResultMappings.TAX_SPEND_BY_CARRIER_MAPPING,
                classes = {
                        @ConstructorResult(targetClass = TaxSpendDto.class,
                                columns = {
                                        @ColumnResult(name = "NAME", type = String.class),
                                        @ColumnResult(name = "VALUE", type = Double.class),
                                        @ColumnResult(name = "CARRIER_ID", type = Long.class)
                                })
                }),
        @SqlResultSetMapping(name = TaxSpendDto.Config.ResultMappings.TAX_SPEND_BY_MONTH_MAPPING,
                classes = {
                        @ConstructorResult(targetClass = TaxSpendDto.class,
                                columns = {
                                        @ColumnResult(name = "BILL_DATE", type = Date.class),
                                        @ColumnResult(name = "AMOUNT", type = Double.class)
                                })
                })
})
@Entity
public class TaxSpendDto {
    @Id
    private Long id;

    @Column(name = "RANK")
    private Long rank;

    @Column(name = "TAX")
    private String tax;

    @Column(name = "SPEND")
    private Double spend;

    @Column(name = "NAME")
    private String name;

    @Column(name = "VALUE")
    private Double value;

    @Column(name = "CARRIER_ID")
    private Long carrierId;

    @Column(name = "BILL_DATE")
    private Date billDate;

    @Column(name = "AMOUNT")
    private Double amount;

    public TaxSpendDto(Long rank, String tax, Double spend) {
        this.rank = rank;
        this.tax = tax;
        this.spend = spend;
    }

    public TaxSpendDto(String name, Double value, Long carrierId) {
        this.name = name;
        this.value = value;
        this.carrierId = carrierId;
    }

    public TaxSpendDto(Date billDate, Double amount) {
        this.billDate = billDate;
        this.amount = amount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRank() {
        return rank;
    }

    public void setRank(Long rank) {
        this.rank = rank;
    }

    public String getTax() {
        return tax;
    }

    public void setTax(String tax) {
        this.tax = tax;
    }

    public Double getSpend() {
        return spend;
    }

    public void setSpend(Double spend) {
        this.spend = spend;
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

    public Long getCarrierId() {
        return carrierId;
    }

    public void setCarrierId(Long carrierId) {
        this.carrierId = carrierId;
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
            static final String TAX_SPEND_MAPPING = "TaxSpendDto.TaxSpendMapping";
            static final String TAX_SPEND_BY_CARRIER_MAPPING = "TaxSpendDto.TaxSpendByCarrierMapping";
            static final String TAX_SPEND_BY_MONTH_MAPPING = "TaxSpendDto.TaxSpendByMonthMapping";
        }

        static class StoredProcedureName{
            static final String TAX_SPEND = "SHP_DB_TAX_SPEND_PROC";
            static final String TAX_SPEND_BY_CARRIER = "SHP_DB_TAX_SPEND_BY_CARR_PROC";
            static final String TAX_SPEND_BY_MONTH = "SHP_DB_TAX_SPEND_BY_MONTH_PROC";
        }

        public static class StoredProcedureQueryName{
            public static final String TAX_SPEND = "TaxSpendDto.getTaxSpend";
            public static final String TAX_SPEND_BY_CARRIER = "TaxSpendDto.getTaxSpendByCarrier";
            public static final String TAX_SPEND_BY_MONTH = "TaxSpendDto.getTaxSpendByMonth";
        }
    }
}
