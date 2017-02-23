package com.envista.msi.api.web.rest.dto.dashboard.netspend;

import com.envista.msi.api.domain.util.DashboardSroredProcParam;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by Sujit kumar on 06/02/2017.
 */
@NamedStoredProcedureQueries({
        @NamedStoredProcedureQuery(name = "AccessorialSpendDto.getTopAccessorialSpend", procedureName = "SHP_DB_TOP_ACC_SPEND_PROC",
                resultSetMappings = "TopAccessorialSpendMapping",
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.AccessorialSpendParams.DATE_TYPE_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.AccessorialSpendParams.CONVERTED_CURRENCY_ID_PARAM, type = Long.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.AccessorialSpendParams.CONVERTED_CURRENCY_CODE_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.AccessorialSpendParams.CUSTOMER_IDS_CSV_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.AccessorialSpendParams.CARRIER_IDS_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.AccessorialSpendParams.MODES_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.AccessorialSpendParams.SERVICES_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.AccessorialSpendParams.LANES_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.AccessorialSpendParams.ACCESSORIAL_NAME_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.AccessorialSpendParams.FROM_DATE_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.AccessorialSpendParams.TO_DATE_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.AccessorialSpendParams.TOP_TEN_ACCESSORIAL_PARAM, type = Long.class),
                        @StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, name = DashboardSroredProcParam.AccessorialSpendParams.TOP_ACCESSORIAL_SPEND_PARAM, type = Void.class)
                }),
        @NamedStoredProcedureQuery(name = "AccessorialSpendDto.getTopAccessorialSpendByCarrier", procedureName = "SHP_DB_TOP_ACC_SPEND_CARR_PROC",
                resultSetMappings = "TopAccessorialSpendByCarrierMapping",
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.AccessorialSpendParams.DATE_TYPE_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.AccessorialSpendParams.CONVERTED_CURRENCY_ID_PARAM, type = Long.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.AccessorialSpendParams.CONVERTED_CURRENCY_CODE_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.AccessorialSpendParams.CUSTOMER_IDS_CSV_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.AccessorialSpendParams.CONVERTED_CURRENCY_ID_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.AccessorialSpendParams.MODES_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.AccessorialSpendParams.SERVICES_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.AccessorialSpendParams.LANES_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.AccessorialSpendParams.ACCESSORIAL_NAME_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.AccessorialSpendParams.FROM_DATE_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.AccessorialSpendParams.TO_DATE_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.AccessorialSpendParams.TOP_TEN_ACCESSORIAL_PARAM, type = Long.class),
                        @StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, name = DashboardSroredProcParam.AccessorialSpendParams.TOP_ACCESSORIAL_SPEND_PARAM, type = Void.class)
                }),
        @NamedStoredProcedureQuery(name = "AccessorialSpendDto.getTopAccessorialSpendByMonth", procedureName = "SHP_DB_TOP_ACC_SPEND_MNTH_PROC",
                resultSetMappings = "TopAccessorialSpendByMonthMapping",
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.AccessorialSpendParams.DATE_TYPE_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.AccessorialSpendParams.CONVERTED_CURRENCY_ID_PARAM, type = Long.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.AccessorialSpendParams.CONVERTED_CURRENCY_CODE_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.AccessorialSpendParams.CUSTOMER_IDS_CSV_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.AccessorialSpendParams.CARRIER_IDS_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.AccessorialSpendParams.MODES_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.AccessorialSpendParams.SERVICES_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.AccessorialSpendParams.LANES_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.AccessorialSpendParams.ACCESSORIAL_NAME_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.AccessorialSpendParams.FROM_DATE_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.AccessorialSpendParams.TO_DATE_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.AccessorialSpendParams.TOP_TEN_ACCESSORIAL_PARAM, type = Long.class),
                        @StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, name = DashboardSroredProcParam.AccessorialSpendParams.TOP_ACCESSORIAL_SPEND_PARAM, type = Void.class)
                }),
        @NamedStoredProcedureQuery(name = "AccessorialSpendDto.getAccessorialSpend", procedureName = "SHP_DB_ACC_SPEND_PROC",
                resultSetMappings = "AccessorialSpendMapping",
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.AccessorialSpendParams.DATE_TYPE_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.AccessorialSpendParams.CONVERTED_CURRENCY_ID_PARAM, type = Long.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.AccessorialSpendParams.CUSTOMER_IDS_CSV_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.AccessorialSpendParams.CARRIER_IDS_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.AccessorialSpendParams.MODES_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.AccessorialSpendParams.SERVICES_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.AccessorialSpendParams.LANES_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.AccessorialSpendParams.FROM_DATE_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.AccessorialSpendParams.TO_DATE_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.AccessorialSpendParams.MODE_NAMES_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, name = DashboardSroredProcParam.AccessorialSpendParams.ACCESSORIAL_SPEND_PARAM, type = Void.class)
                }),
        @NamedStoredProcedureQuery(name = "AccessorialSpendDto.getAccessorialSpendByCarrier", procedureName = "SHP_DB_ACC_SPEND_CARR_PROC",
                resultSetMappings = "AccessorialSpendByCarrierMapping",
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.AccessorialSpendParams.DATE_TYPE_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.AccessorialSpendParams.CONVERTED_CURRENCY_ID_PARAM, type = Long.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.AccessorialSpendParams.CUSTOMER_IDS_CSV_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.AccessorialSpendParams.CARRIER_IDS_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.AccessorialSpendParams.MODES_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.AccessorialSpendParams.SERVICES_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.AccessorialSpendParams.LANES_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.AccessorialSpendParams.FROM_DATE_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.AccessorialSpendParams.TO_DATE_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.AccessorialSpendParams.MODE_NAMES_PARAM, type = Long.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.AccessorialSpendParams.ACCESSORIAL_DESC_PARAM, type = Long.class),
                        @StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, name = DashboardSroredProcParam.AccessorialSpendParams.ACCESSORIAL_SPEND_PARAM, type = Void.class)
                }),
        @NamedStoredProcedureQuery(name = "AccessorialSpendDto.getAccessorialSpendByMonth", procedureName = "SHP_DB_ACC_SPEND_MNTH_PROC",
                resultSetMappings = "AccessorialSpendByMonthMapping",
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.AccessorialSpendParams.DATE_TYPE_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.AccessorialSpendParams.CONVERTED_CURRENCY_ID_PARAM, type = Long.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.AccessorialSpendParams.CUSTOMER_IDS_CSV_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.AccessorialSpendParams.CARRIER_IDS_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.AccessorialSpendParams.MODES_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.AccessorialSpendParams.SERVICES_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.AccessorialSpendParams.LANES_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.AccessorialSpendParams.FROM_DATE_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.AccessorialSpendParams.TO_DATE_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.AccessorialSpendParams.MODE_NAMES_PARAM, type = Long.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.AccessorialSpendParams.ACCESSORIAL_DESC_PARAM, type = Long.class),
                        @StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, name = DashboardSroredProcParam.AccessorialSpendParams.ACCESSORIAL_SPEND_PARAM, type = Void.class)
                })
})

@SqlResultSetMappings({
        @SqlResultSetMapping(name = "TopAccessorialSpendMapping", classes = {
                @ConstructorResult(
                        targetClass = AccessorialSpendDto.class,
                        columns = {
                                @ColumnResult(name = "BILL_DATE", type = String.class),
                                @ColumnResult(name = "ACCESSORIAL_NAME", type = String.class),
                                @ColumnResult(name = "SPEND", type = Double.class)
                        }
                )
        }),
        @SqlResultSetMapping(name = "TopAccessorialSpendByCarrierMapping", classes = {
                @ConstructorResult(
                        targetClass = AccessorialSpendDto.class,
                        columns = {
                                @ColumnResult(name = "SPEND", type = Double.class),
                                @ColumnResult(name = "CARRIER_NAME", type = String.class),
                                @ColumnResult(name = "CARRIER_ID", type = Long.class)
                        }
                )
        }),
        @SqlResultSetMapping(name = "TopAccessorialSpendByMonthMapping", classes = {
                @ConstructorResult(
                        targetClass = AccessorialSpendDto.class,
                        columns = {
                                @ColumnResult(name = "BILLING_DATE", type = Date.class),
                                @ColumnResult(name = "AMOUNT", type = Double.class)
                        }
                )
        }),
        @SqlResultSetMapping(name = "AccessorialSpendMapping", classes = {
                @ConstructorResult(
                        targetClass = AccessorialSpendDto.class,
                        columns = {
                                @ColumnResult(name = "SPEND", type = Double.class),
                                @ColumnResult(name = "RANK", type = Long.class),
                                @ColumnResult(name = "ACC_DESC", type = String.class)
                        }
                )
        }),
        @SqlResultSetMapping(name = "AccessorialSpendByCarrierMapping", classes = {
                @ConstructorResult(
                        targetClass = AccessorialSpendDto.class,
                        columns = {
                                @ColumnResult(name = "SPEND", type = Double.class),
                                @ColumnResult(name = "CARRIER_NAME", type = String.class),
                                @ColumnResult(name = "CARRIER_ID", type = Long.class)
                        }
                )
        }),
        @SqlResultSetMapping(name = "AccessorialSpendByMonthMapping", classes = {
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
}
