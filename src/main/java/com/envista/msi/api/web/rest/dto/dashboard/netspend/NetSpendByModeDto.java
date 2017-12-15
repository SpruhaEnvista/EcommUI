package com.envista.msi.api.web.rest.dto.dashboard.netspend;

import com.envista.msi.api.domain.util.DashboardStoredProcParam;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by Sujit kumar on 30/01/2017.
 */
@NamedStoredProcedureQueries({
        @NamedStoredProcedureQuery(name = NetSpendByModeDto.Config.StoredProcedureQueryName.NET_SPEND_BY_MODE,
                procedureName = NetSpendByModeDto.Config.StoredProcedureName.NET_SPEND_BY_MODE,
                resultSetMappings = {NetSpendByModeDto.Config.ResultMappings.NET_SPEND_BY_MODE_MAPPING},
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
        @NamedStoredProcedureQuery(name = NetSpendByModeDto.Config.StoredProcedureQueryName.NET_SPEND_BY_CARRIER,
                procedureName = NetSpendByModeDto.Config.StoredProcedureName.NET_SPEND_BY_CARRIER,
                resultSetMappings = {NetSpendByModeDto.Config.ResultMappings.NET_SPEND_BY_CARRIER_MAPPING},
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
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.NetSpendParams.MODE_NAMES_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, name = DashboardStoredProcParam.NetSpendParams.NET_SPEND_PARAM, type = Void.class)
                }),
        @NamedStoredProcedureQuery(name = NetSpendByModeDto.Config.StoredProcedureQueryName.OVERALL_SPEND_BY_MONTH,
                procedureName = NetSpendByModeDto.Config.StoredProcedureName.OVERALL_SPEND_BY_MONTH,
                resultSetMappings = {NetSpendByModeDto.Config.ResultMappings.OVERALL_SPEND_BY_MONTH_MAPPING},
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.NetSpendParams.DATE_TYPE_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.NetSpendParams.CONVERTED_CURRENCY_ID_PARAM, type = Long.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.NetSpendParams.CONVERTED_CURRENCY_CODE_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.NetSpendParams.CUSTOMER_IDS_CSV_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.NetSpendParams.CARRIER_IDS_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.NetSpendParams.MODES_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.NetSpendParams.SERVICES_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.NetSpendParams.LANES_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.NetSpendParams.FROM_DATE_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.NetSpendParams.TO_DATE_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, name = DashboardStoredProcParam.NetSpendParams.NET_SPEND_PARAM, type = Void.class)
                }),
        @NamedStoredProcedureQuery(name = NetSpendByModeDto.Config.StoredProcedureQueryName.REL_SPEND_BY_CARR,
                procedureName = NetSpendByModeDto.Config.StoredProcedureName.REL_SPEND_BY_CARR,
                resultSetMappings = {NetSpendByModeDto.Config.ResultMappings.REL_SPEND_BY_CARR_MAPPING},
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.NetSpendParams.DATE_TYPE_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.NetSpendParams.CONVERTED_CURRENCY_ID_PARAM, type = Long.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.NetSpendParams.CONVERTED_CURRENCY_CODE_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.NetSpendParams.CUSTOMER_IDS_CSV_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.NetSpendParams.CARRIER_IDS_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.NetSpendParams.MODES_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.NetSpendParams.SERVICES_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.NetSpendParams.LANES_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.NetSpendParams.FROM_DATE_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.NetSpendParams.TO_DATE_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, name = DashboardStoredProcParam.RelSpendParams.REL_SPEND_PARAM, type = Void.class)
                }),
        @NamedStoredProcedureQuery(name = NetSpendByModeDto.Config.StoredProcedureQueryName.NET_SPEND_BY_MONTH,
                procedureName = NetSpendByModeDto.Config.StoredProcedureName.NET_SPEND_BY_MONTH,
                resultSetMappings = {NetSpendByModeDto.Config.ResultMappings.NET_SPEND_BY_MONTH_MAPPING},
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
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.NetSpendParams.MODE_NAMES_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.NetSpendParams.SCORE_TYPE_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, name = DashboardStoredProcParam.NetSpendParams.NET_SPEND_PARAM, type = Void.class)
                })
})

@SqlResultSetMappings({
        @SqlResultSetMapping(name = NetSpendByModeDto.Config.ResultMappings.NET_SPEND_BY_MODE_MAPPING,
        classes = {
                @ConstructorResult(
                        targetClass = NetSpendByModeDto.class,
                        columns = {
                                @ColumnResult(name = "MODES", type = String.class),
                                @ColumnResult(name = "SPEND", type = Double.class),
                                @ColumnResult(name = "SCORE_TYPE", type = String.class)
                        }
                )
        }),
        @SqlResultSetMapping(name = NetSpendByModeDto.Config.ResultMappings.NET_SPEND_BY_CARRIER_MAPPING,
                classes = {
                        @ConstructorResult(
                                targetClass = NetSpendByModeDto.class,
                                columns = {
                                        @ColumnResult(name = "CARRIER_ID", type = Long.class),
                                        @ColumnResult(name = "CARRIER_NAME", type = String.class),
                                        @ColumnResult(name = "SPEND", type = Double.class)
                                }
                        )
                }),
        @SqlResultSetMapping(name = NetSpendByModeDto.Config.ResultMappings.OVERALL_SPEND_BY_MONTH_MAPPING,
                classes = {
                        @ConstructorResult(
                                targetClass = NetSpendByModeDto.class,
                                columns = {
                                        @ColumnResult(name = "BILLING_DATE", type = String.class),
                                        @ColumnResult(name = "AMOUNT", type = Double.class)
                                }
                        )
                }),
        @SqlResultSetMapping(name = NetSpendByModeDto.Config.ResultMappings.REL_SPEND_BY_CARR_MAPPING,
                classes = {
                        @ConstructorResult(
                                targetClass = NetSpendByModeDto.class,
                                columns = {
                                        @ColumnResult(name = "CARRIER_ID", type = Long.class),
                                        @ColumnResult(name = "AMOUNT", type = Double.class),
                                        @ColumnResult(name = "CARRIER_NAME", type = String.class)


                                }
                        )
                }),
        @SqlResultSetMapping(name = NetSpendByModeDto.Config.ResultMappings.NET_SPEND_BY_MONTH_MAPPING,
                classes = {
                        @ConstructorResult(
                                targetClass = NetSpendByModeDto.class,
                                columns = {
                                        @ColumnResult(name = "BILL_DATE", type = Date.class),
                                        @ColumnResult(name = "AMOUNT", type = Double.class)
                                }
                        )
                })
})

@Entity
public class NetSpendByModeDto implements Serializable{
    @Id
    @Column(name = "NET_SPEND_ID")
    private Long id;

    @Column(name = "MODES")
    private String modes;

    @Column(name = "SPEND")
    private Double spend;

    @Column(name = "SCORE_TYPE")
    private String scoreType;

    @Column(name = "CARRIER_ID")
    private Long carrierId;

    @Column(name = "CARRIER_NAME")
    private String carrierName;

    @Column(name = "BILL_DATE")
    private Date billDate;

    @Column(name = "BILLING_DATE")
    private String billingDate;

    @Column(name = "AMOUNT")
    private Double amount;

    public NetSpendByModeDto(){}

    public NetSpendByModeDto(String modes, Double spend, String scoreType) {
        this.modes = modes;
        this.spend = spend;
        this.scoreType = scoreType;
    }

    public NetSpendByModeDto(Long carrierId, String carrierName, Double spend) {
        this.carrierId = carrierId;
        this.carrierName = carrierName;
        this.spend = spend;
    }

    public NetSpendByModeDto(Long carrierId,Double amount, String carrierName ) {
        this.carrierId = carrierId;
        this.amount = amount;
        this.carrierName = carrierName;

    }

    public NetSpendByModeDto(String billingDate, Double amount) {
        this.billingDate = billingDate;
        this.amount = amount;
    }


    public NetSpendByModeDto(Date billDate, Double amount) {
        this.billDate = billDate;
        this.amount = amount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getModes() {
        return modes;
    }

    public void setModes(String modes) {
        this.modes = modes;
    }

    public Double getSpend() {
        return spend;
    }

    public void setSpend(Double spend) {
        this.spend = spend;
    }

    public String getScoreType() {
        return scoreType;
    }

    public void setScoreType(String scoreType) {
        this.scoreType = scoreType;
    }

    public String getBillingDate() {
        return billingDate;
    }

    public void setBillingDate(String billingDate) {
        this.billingDate = billingDate;
    }

    public Long getCarrierId() {
        return carrierId;
    }

    public void setCarrierId(Long carrierId) {
        this.carrierId = carrierId;
    }

    public String getCarrierName() {
        return carrierName;
    }

    public void setCarrierName(String carrierName) {
        this.carrierName = carrierName;
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
            static final String NET_SPEND_BY_MODE_MAPPING = "NetSpendByModeDto.NetSpendByModeMapping";
            static final String NET_SPEND_BY_CARRIER_MAPPING = "NetSpendByModeDto.getNetSpendByCarrierMapping";
            static final String OVERALL_SPEND_BY_MONTH_MAPPING = "SHP_DB_OVRL_SPEND_BY_MNTH_PROC";
            static final String NET_SPEND_BY_MONTH_MAPPING = "NetSpendByModeDto.getNetSpendByMonthMapping";
            static final String REL_SPEND_BY_CARR_MAPPING = "SHP_DB_REL_SPEND_BY_CARR_PROC";
        }

        static class StoredProcedureName{
            static final String NET_SPEND_BY_MODE = "SHP_DB_NET_SPEND_BY_MODE_PROC";
            static final String NET_SPEND_BY_CARRIER = "SHP_DB_NET_SPEND_BY_CARR_PROC";
            static final String NET_SPEND_BY_MONTH = "SHP_DB_NET_SPEND_BY_MNTH_PROC";
            static final String OVERALL_SPEND_BY_MONTH = "SHP_DB_OVRL_SPEND_BY_MNTH_PROC";
            static final String REL_SPEND_BY_CARR = "SHP_DB_REL_SPEND_BY_CARR_PROC";
        }

        public static class StoredProcedureQueryName{
            public static final String NET_SPEND_BY_MODE = "NetSpendByModeDto.getNetSpendByMode";
            public static final String NET_SPEND_BY_CARRIER = "NetSpendByModeDto.getNetSpendByCarrier";
            public static final String NET_SPEND_BY_MONTH = "NetSpendByModeDto.getNetSpendByMonth";
            public static final String OVERALL_SPEND_BY_MONTH = "SHP_DB_OVRL_SPEND_BY_MNTH_PROC";
            public static final String REL_SPEND_BY_CARR = "SHP_DB_REL_SPEND_BY_CARR_PROC";
        }
    }
}
