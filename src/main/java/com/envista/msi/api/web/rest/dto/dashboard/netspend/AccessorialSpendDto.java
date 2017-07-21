package com.envista.msi.api.web.rest.dto.dashboard.netspend;

import com.envista.msi.api.domain.util.DashboardStoredProcParam;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by Sujit kumar on 06/02/2017.
 */
@NamedStoredProcedureQueries({
        @NamedStoredProcedureQuery(name = AccessorialSpendDto.Config.StoredProcedureQueryName.TOP_ACCESSORIAL_SPEND,
                procedureName = AccessorialSpendDto.Config.StoredProcedureName.TOP_ACCESSORIAL_SPEND,
                resultSetMappings = AccessorialSpendDto.Config.ResultMappings.TOP_ACCESSORIAL_SPEND_MAPPING,
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.AccessorialSpendParams.DATE_TYPE_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.AccessorialSpendParams.CONVERTED_CURRENCY_ID_PARAM, type = Long.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.AccessorialSpendParams.CONVERTED_CURRENCY_CODE_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.AccessorialSpendParams.CUSTOMER_IDS_CSV_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.AccessorialSpendParams.CARRIER_IDS_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.AccessorialSpendParams.MODES_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.AccessorialSpendParams.SERVICES_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.AccessorialSpendParams.LANES_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.AccessorialSpendParams.ACCESSORIAL_NAME_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.AccessorialSpendParams.FROM_DATE_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.AccessorialSpendParams.TO_DATE_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.AccessorialSpendParams.TOP_TEN_ACCESSORIAL_PARAM, type = Long.class),
                        @StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, name = DashboardStoredProcParam.AccessorialSpendParams.TOP_ACCESSORIAL_SPEND_PARAM, type = Void.class)
                }),
        @NamedStoredProcedureQuery(name = AccessorialSpendDto.Config.StoredProcedureQueryName.TOP_ACCESSORIAL_SPEND_BY_CARRIER,
                procedureName = AccessorialSpendDto.Config.StoredProcedureName.TOP_ACCESSORIAL_SPEND_BY_CARRIER,
                resultSetMappings = AccessorialSpendDto.Config.ResultMappings.TOP_ACCESSORIAL_SPEND_BY_CARRIER_MAPPING,
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.AccessorialSpendParams.DATE_TYPE_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.AccessorialSpendParams.CONVERTED_CURRENCY_ID_PARAM, type = Long.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.AccessorialSpendParams.CONVERTED_CURRENCY_CODE_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.AccessorialSpendParams.CUSTOMER_IDS_CSV_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.AccessorialSpendParams.CARRIER_IDS_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.AccessorialSpendParams.MODES_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.AccessorialSpendParams.SERVICES_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.AccessorialSpendParams.LANES_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.AccessorialSpendParams.ACCESSORIAL_NAME_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.AccessorialSpendParams.FROM_DATE_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.AccessorialSpendParams.TO_DATE_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.AccessorialSpendParams.TOP_TEN_ACCESSORIAL_PARAM, type = Long.class),
                        @StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, name = DashboardStoredProcParam.AccessorialSpendParams.TOP_ACCESSORIAL_SPEND_PARAM, type = Void.class)
                }),
        @NamedStoredProcedureQuery(name = AccessorialSpendDto.Config.StoredProcedureQueryName.TOP_ACCESSORIAL_SPEND_BY_MONTH,
                procedureName = AccessorialSpendDto.Config.StoredProcedureName.TOP_ACCESSORIAL_SPEND_BY_MONTH,
                resultSetMappings = AccessorialSpendDto.Config.ResultMappings.TOP_ACCESSORIAL_SPEND_BY_MONTH_MAPPING,
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.AccessorialSpendParams.DATE_TYPE_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.AccessorialSpendParams.CONVERTED_CURRENCY_ID_PARAM, type = Long.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.AccessorialSpendParams.CONVERTED_CURRENCY_CODE_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.AccessorialSpendParams.CUSTOMER_IDS_CSV_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.AccessorialSpendParams.CARRIER_IDS_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.AccessorialSpendParams.MODES_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.AccessorialSpendParams.SERVICES_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.AccessorialSpendParams.LANES_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.AccessorialSpendParams.ACCESSORIAL_NAME_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.AccessorialSpendParams.FROM_DATE_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.AccessorialSpendParams.TO_DATE_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.AccessorialSpendParams.TOP_TEN_ACCESSORIAL_PARAM, type = Long.class),
                        @StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, name = DashboardStoredProcParam.AccessorialSpendParams.TOP_ACCESSORIAL_SPEND_PARAM, type = Void.class)
                }),
        @NamedStoredProcedureQuery(name = AccessorialSpendDto.Config.StoredProcedureQueryName.TOP_ACCESSORIAL_SPEND_BY_ACCESSORIAL,
                procedureName = AccessorialSpendDto.Config.StoredProcedureName.TOP_ACCESSORIAL_SPEND_BY_ACCESSORIAL,
                resultSetMappings = AccessorialSpendDto.Config.ResultMappings.TOP_ACCESSORIAL_SPEND_BY_ACCESSORIAL_MAPPING,
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.AccessorialSpendParams.DATE_TYPE_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.AccessorialSpendParams.CONVERTED_CURRENCY_ID_PARAM, type = Long.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.AccessorialSpendParams.CONVERTED_CURRENCY_CODE_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.AccessorialSpendParams.CUSTOMER_IDS_CSV_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.AccessorialSpendParams.CARRIER_IDS_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.AccessorialSpendParams.MODES_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.AccessorialSpendParams.SERVICES_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.AccessorialSpendParams.LANES_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.AccessorialSpendParams.FROM_DATE_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.AccessorialSpendParams.TO_DATE_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.AccessorialSpendParams.TOP_TEN_ACCESSORIAL_PARAM, type = Long.class),
                        @StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, name = DashboardStoredProcParam.AccessorialSpendParams.TOP_ACCESSORIAL_SPEND_PARAM, type = Void.class)
                }),
        @NamedStoredProcedureQuery(name = AccessorialSpendDto.Config.StoredProcedureQueryName.ACCESSORIAL_SPEND,
                procedureName = AccessorialSpendDto.Config.StoredProcedureName.ACCESSORIAL_SPEND,
                resultSetMappings = AccessorialSpendDto.Config.ResultMappings.ACCESSORIAL_SPEND_MAPPING,
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.AccessorialSpendParams.DATE_TYPE_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.AccessorialSpendParams.CONVERTED_CURRENCY_ID_PARAM, type = Long.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.AccessorialSpendParams.CUSTOMER_IDS_CSV_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.AccessorialSpendParams.CARRIER_IDS_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.AccessorialSpendParams.MODES_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.AccessorialSpendParams.SERVICES_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.AccessorialSpendParams.LANES_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.AccessorialSpendParams.FROM_DATE_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.AccessorialSpendParams.TO_DATE_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.AccessorialSpendParams.MODE_NAMES_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, name = DashboardStoredProcParam.AccessorialSpendParams.ACCESSORIAL_SPEND_PARAM, type = Void.class)
                }),
        @NamedStoredProcedureQuery(name = AccessorialSpendDto.Config.StoredProcedureQueryName.ACCESSORIAL_SPEND_BY_CARRIER,
                procedureName = AccessorialSpendDto.Config.StoredProcedureName.ACCESSORIAL_SPEND_BY_CARRIER,
                resultSetMappings = AccessorialSpendDto.Config.ResultMappings.ACCESSORIAL_SPEND_BY_CARRIER_MAPPING,
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.AccessorialSpendParams.DATE_TYPE_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.AccessorialSpendParams.CONVERTED_CURRENCY_ID_PARAM, type = Long.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.AccessorialSpendParams.CUSTOMER_IDS_CSV_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.AccessorialSpendParams.CARRIER_IDS_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.AccessorialSpendParams.MODES_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.AccessorialSpendParams.SERVICES_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.AccessorialSpendParams.LANES_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.AccessorialSpendParams.FROM_DATE_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.AccessorialSpendParams.TO_DATE_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.AccessorialSpendParams.MODE_NAMES_PARAM, type = Long.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.AccessorialSpendParams.ACCESSORIAL_DESC_PARAM, type = Long.class),
                        @StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, name = DashboardStoredProcParam.AccessorialSpendParams.ACCESSORIAL_SPEND_PARAM, type = Void.class)
                }),
        @NamedStoredProcedureQuery(name = AccessorialSpendDto.Config.StoredProcedureQueryName.ACCESSORIAL_SPEND_BY_MONTH,
                procedureName = AccessorialSpendDto.Config.StoredProcedureName.ACCESSORIAL_SPEND_BY_MONTH,
                resultSetMappings = AccessorialSpendDto.Config.ResultMappings.ACCESSORIAL_SPEND_BY_MONTH_MAPPING,
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.AccessorialSpendParams.DATE_TYPE_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.AccessorialSpendParams.CONVERTED_CURRENCY_ID_PARAM, type = Long.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.AccessorialSpendParams.CUSTOMER_IDS_CSV_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.AccessorialSpendParams.CARRIER_IDS_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.AccessorialSpendParams.MODES_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.AccessorialSpendParams.SERVICES_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.AccessorialSpendParams.LANES_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.AccessorialSpendParams.FROM_DATE_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.AccessorialSpendParams.TO_DATE_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.AccessorialSpendParams.MODE_NAMES_PARAM, type = Long.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.AccessorialSpendParams.ACCESSORIAL_DESC_PARAM, type = Long.class),
                        @StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, name = DashboardStoredProcParam.AccessorialSpendParams.ACCESSORIAL_SPEND_PARAM, type = Void.class)
                })
})

@SqlResultSetMappings({
        @SqlResultSetMapping(name = AccessorialSpendDto.Config.ResultMappings.TOP_ACCESSORIAL_SPEND_MAPPING, classes = {
                @ConstructorResult(
                        targetClass = AccessorialSpendDto.class,
                        columns = {
                                @ColumnResult(name = "BILL_DATE", type = String.class),
                                @ColumnResult(name = "ACCESSORIAL_NAME", type = String.class),
                                @ColumnResult(name = "SPEND", type = Double.class)
                        }
                )
        }),
        @SqlResultSetMapping(name = AccessorialSpendDto.Config.ResultMappings.TOP_ACCESSORIAL_SPEND_BY_CARRIER_MAPPING, classes = {
                @ConstructorResult(
                        targetClass = AccessorialSpendDto.class,
                        columns = {
                                @ColumnResult(name = "SPEND", type = Double.class),
                                @ColumnResult(name = "CARRIER_NAME", type = String.class),
                                @ColumnResult(name = "CARRIER_ID", type = Long.class)
                        }
                )
        }),
        @SqlResultSetMapping(name = AccessorialSpendDto.Config.ResultMappings.TOP_ACCESSORIAL_SPEND_BY_MONTH_MAPPING, classes = {
                @ConstructorResult(
                        targetClass = AccessorialSpendDto.class,
                        columns = {
                                @ColumnResult(name = "BILLING_DATE", type = Date.class),
                                @ColumnResult(name = "AMOUNT", type = Double.class)
                        }
                )
        }),
        @SqlResultSetMapping(name = AccessorialSpendDto.Config.ResultMappings.TOP_ACCESSORIAL_SPEND_BY_ACCESSORIAL_MAPPING, classes = {
                @ConstructorResult(
                        targetClass = AccessorialSpendDto.class,
                        columns = {
                                @ColumnResult(name = "ACCESSORIAL_NAME", type = String.class),
                                @ColumnResult(name = "SPEND", type = Double.class)
                        }
                )
        }),
        @SqlResultSetMapping(name = AccessorialSpendDto.Config.ResultMappings.ACCESSORIAL_SPEND_MAPPING, classes = {
                @ConstructorResult(
                        targetClass = AccessorialSpendDto.class,
                        columns = {
                                @ColumnResult(name = "SPEND", type = Double.class),
                                @ColumnResult(name = "RANK", type = Long.class),
                                @ColumnResult(name = "ACC_DESC", type = String.class)
                        }
                )
        }),
        @SqlResultSetMapping(name = AccessorialSpendDto.Config.ResultMappings.ACCESSORIAL_SPEND_BY_CARRIER_MAPPING, classes = {
                @ConstructorResult(
                        targetClass = AccessorialSpendDto.class,
                        columns = {
                                @ColumnResult(name = "SPEND", type = Double.class),
                                @ColumnResult(name = "CARRIER_NAME", type = String.class),
                                @ColumnResult(name = "CARRIER_ID", type = Long.class)
                        }
                )
        }),
        @SqlResultSetMapping(name = AccessorialSpendDto.Config.ResultMappings.ACCESSORIAL_SPEND_BY_MONTH_MAPPING, classes = {
                @ConstructorResult(
                        targetClass = AccessorialSpendDto.class,
                        columns = {
                                @ColumnResult(name = "BILLING_DATE", type = Date.class),
                                @ColumnResult(name = "AMOUNT", type = Double.class)
                        }
                )
        })
})

@Entity
public class AccessorialSpendDto implements Serializable{
    @Id
    private Long id;

    @Column(name = "BILL_DATE")
    private String billDate;

    @Column(name = "ACCESSORIAL_NAME")
    private String accessorialName;

    @Column(name = "SPEND")
    private Double spend;

    @Column(name = "CARRIER_NAME")
    private String carrierName;

    @Column(name = "CARRIER_ID")
    private Long carrierId;

    @Column(name = "AMOUNT")
    private Double amount;

    @Column(name = "BILLING_DATE")
    private Date billingDate;

    @Column(name = "RANK")
    private Long rank;

    @Column(name = "ACC_DESC")
    private String accessorialDescription;

    public AccessorialSpendDto(){}

    public AccessorialSpendDto(String billDate, String accessorialName, Double spend) {
        this.billDate = billDate;
        this.accessorialName = accessorialName;
        this.spend = spend;
    }

    public AccessorialSpendDto(Long id, String billDate, String accessorialName, Double spend) {
        this.id = id;
        this.billDate = billDate;
        this.accessorialName = accessorialName;
        this.spend = spend;
    }

    public AccessorialSpendDto(Double spend, String carrierName, Long carrierId) {
        this.spend = spend;
        this.carrierName = carrierName;
        this.carrierId = carrierId;
    }

    public AccessorialSpendDto(Date billingDate, Double amount) {
        this.billingDate = billingDate;
        this.amount = amount;
    }

    public AccessorialSpendDto(Double spend, Long rank, String accessorialDescription) {
        this.spend = spend;
        this.rank = rank;
        this.accessorialDescription = accessorialDescription;
    }

    public AccessorialSpendDto(String accessorialName, Double spend) {
        this.accessorialName = accessorialName;
        this.spend = spend;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBillDate() {
        return billDate;
    }

    public void setBillDate(String billDate) {
        this.billDate = billDate;
    }

    public String getAccessorialName() {
        return accessorialName;
    }

    public void setAccessorialName(String accessorialName) {
        this.accessorialName = accessorialName;
    }

    public Double getSpend() {
        return spend;
    }

    public void setSpend(Double spend) {
        this.spend = spend;
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

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Date getBillingDate() {
        return billingDate;
    }

    public void setBillingDate(Date billingDate) {
        this.billingDate = billingDate;
    }

    public Long getRank() {
        return rank;
    }

    public void setRank(Long rank) {
        this.rank = rank;
    }

    public String getAccessorialDescription() {
        return accessorialDescription;
    }

    public void setAccessorialDescription(String accessorialDescription) {
        this.accessorialDescription = accessorialDescription;
    }

    public static class Config{
        static class ResultMappings{
            static final String ACCESSORIAL_SPEND_MAPPING = "AccessorialSpendDto.AccessorialSpendMapping";
            static final String ACCESSORIAL_SPEND_BY_CARRIER_MAPPING = "AccessorialSpendDto.AccessorialSpendByCarrierMapping";
            static final String ACCESSORIAL_SPEND_BY_MONTH_MAPPING = "AccessorialSpendDto.AccessorialSpendByMonthMapping";
            static final String TOP_ACCESSORIAL_SPEND_MAPPING = "AccessorialSpendDto.TopAccessorialSpendMapping";
            static final String TOP_ACCESSORIAL_SPEND_BY_CARRIER_MAPPING = "AccessorialSpendDto.TopAccessorialSpendByCarrierMapping";
            static final String TOP_ACCESSORIAL_SPEND_BY_MONTH_MAPPING = "AccessorialSpendDto.TopAccessorialSpendByMonthMapping";
            static final String TOP_ACCESSORIAL_SPEND_BY_ACCESSORIAL_MAPPING = "AccessorialSpendDto.TopAccessorialSpendByAccessorialMapping";
        }

        static class StoredProcedureName{
            static final String ACCESSORIAL_SPEND = "SHP_DB_ACC_SPEND_PROC";
            static final String ACCESSORIAL_SPEND_BY_CARRIER = "SHP_DB_ACC_SPEND_CARR_PROC";
            static final String ACCESSORIAL_SPEND_BY_MONTH = "SHP_DB_ACC_SPEND_MNTH_PROC";
            static final String TOP_ACCESSORIAL_SPEND = "SHP_DB_TOP_ACC_SPEND_PROC";
            static final String TOP_ACCESSORIAL_SPEND_BY_CARRIER = "SHP_DB_TOP_ACC_SPEND_CARR_PROC";
            static final String TOP_ACCESSORIAL_SPEND_BY_MONTH = "SHP_DB_TOP_ACC_SPEND_MNTH_PROC";
            static final String TOP_ACCESSORIAL_SPEND_BY_ACCESSORIAL = "SHP_DB_TOP_ACC_SPEND_ACC_PROC";
        }

        public static class StoredProcedureQueryName{
            public static final String ACCESSORIAL_SPEND = "AccessorialSpendDto.getAccessorialSpend";
            public static final String ACCESSORIAL_SPEND_BY_CARRIER = "AccessorialSpendDto.getAccessorialSpendByCarrier";
            public static final String ACCESSORIAL_SPEND_BY_MONTH = "AccessorialSpendDto.getAccessorialSpendByMonth";
            public static final String TOP_ACCESSORIAL_SPEND = "AccessorialSpendDto.getTopAccessorialSpend";
            public static final String TOP_ACCESSORIAL_SPEND_BY_CARRIER = "AccessorialSpendDto.getTopAccessorialSpendByCarrier";
            public static final String TOP_ACCESSORIAL_SPEND_BY_MONTH = "AccessorialSpendDto.getTopAccessorialSpendByMonth";
            public static final String TOP_ACCESSORIAL_SPEND_BY_ACCESSORIAL = "AccessorialSpendDto.getTopAccessorialSpendByAccessorial";
        }
    }
}
