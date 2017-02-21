package com.envista.msi.api.web.rest.dto.dashboard.annualsummary;

import com.envista.msi.api.domain.util.DashboardSroredProcParam;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by Sujit kumar on 20/02/2017.
 */

@NamedStoredProcedureQueries({
        @NamedStoredProcedureQuery(name = AnnualSummaryDto.Config.StoredProcedureQueryName.ANNUAL_SUMMARY, procedureName = AnnualSummaryDto.Config.StoredProcedureName.ANNUAL_SUMMARY,
                resultSetMappings = AnnualSummaryDto.Config.ResultMappings.ANNUAL_SUMMARY_MAPPING,
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.AnnualSummaryParams.DATE_TYPE_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.AnnualSummaryParams.CONVERTED_CURRENCY_ID_PARAM, type = Long.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.AnnualSummaryParams.CONVERTED_CURRENCY_CODE_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.AnnualSummaryParams.CUSTOMER_IDS_CSV_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.AnnualSummaryParams.CARRIER_IDS_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.AnnualSummaryParams.MODES_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.AnnualSummaryParams.SERVICES_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.AnnualSummaryParams.LANES_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.AnnualSummaryParams.FROM_DATE_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.AnnualSummaryParams.TO_DATE_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.AnnualSummaryParams.ACCESSORIAL_NAME_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.AnnualSummaryParams.TOP_TEN_ACCESSORIAL_PARAM, type = Long.class),
                        @StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, name = DashboardSroredProcParam.AnnualSummaryParams.ANNUAL_SUMMARY_DATA_PARAM, type = Void.class)
        }),
        @NamedStoredProcedureQuery(name = AnnualSummaryDto.Config.StoredProcedureQueryName.ANNUAL_SUMMARY_BY_SERVICE, procedureName = AnnualSummaryDto.Config.StoredProcedureName.ANNUAL_SUMMARY_BY_SERVICE,
                resultSetMappings = AnnualSummaryDto.Config.ResultMappings.ANNUAL_SUMMARY_BY_SERVICE_MAPPING,
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.AnnualSummaryParams.DATE_TYPE_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.AnnualSummaryParams.CONVERTED_CURRENCY_ID_PARAM, type = Long.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.AnnualSummaryParams.CONVERTED_CURRENCY_CODE_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.AnnualSummaryParams.CUSTOMER_IDS_CSV_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.AnnualSummaryParams.CARRIER_IDS_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.AnnualSummaryParams.MODES_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.AnnualSummaryParams.SERVICES_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.AnnualSummaryParams.LANES_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.AnnualSummaryParams.FROM_DATE_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.AnnualSummaryParams.TO_DATE_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.AnnualSummaryParams.ACCESSORIAL_NAME_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.AnnualSummaryParams.TOP_TEN_ACCESSORIAL_PARAM, type = Long.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.AnnualSummaryParams.SERVICE_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, name = DashboardSroredProcParam.AnnualSummaryParams.ANNUAL_SUMMARY_DATA_PARAM, type = Void.class)
        }),
        @NamedStoredProcedureQuery(name = AnnualSummaryDto.Config.StoredProcedureQueryName.ANNUAL_SUMMARY_BY_CARRIER, procedureName = AnnualSummaryDto.Config.StoredProcedureName.ANNUAL_SUMMARY_BY_CARRIER,
                resultSetMappings = AnnualSummaryDto.Config.ResultMappings.ANNUAL_SUMMARY_BY_CARRIER_MAPPING,
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.AnnualSummaryParams.DATE_TYPE_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.AnnualSummaryParams.CONVERTED_CURRENCY_ID_PARAM, type = Long.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.AnnualSummaryParams.CONVERTED_CURRENCY_CODE_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.AnnualSummaryParams.CUSTOMER_IDS_CSV_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.AnnualSummaryParams.CARRIER_IDS_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.AnnualSummaryParams.MODES_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.AnnualSummaryParams.SERVICES_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.AnnualSummaryParams.LANES_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.AnnualSummaryParams.FROM_DATE_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.AnnualSummaryParams.TO_DATE_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.AnnualSummaryParams.ACCESSORIAL_NAME_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.AnnualSummaryParams.TOP_TEN_ACCESSORIAL_PARAM, type = Long.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.AnnualSummaryParams.SERVICE_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.AnnualSummaryParams.MODE_NAMES_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, name = DashboardSroredProcParam.AnnualSummaryParams.ANNUAL_SUMMARY_DATA_PARAM, type = Void.class)
                }),
        @NamedStoredProcedureQuery(name = AnnualSummaryDto.Config.StoredProcedureQueryName.ANNUAL_SUMMARY_BY_MONTH, procedureName = AnnualSummaryDto.Config.StoredProcedureName.ANNUAL_SUMMARY_BY_MONTH,
                resultSetMappings = AnnualSummaryDto.Config.ResultMappings.ANNUAL_SUMMARY_BY_MONTH_MAPPING,
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.AnnualSummaryParams.DATE_TYPE_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.AnnualSummaryParams.CONVERTED_CURRENCY_ID_PARAM, type = Long.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.AnnualSummaryParams.CONVERTED_CURRENCY_CODE_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.AnnualSummaryParams.CUSTOMER_IDS_CSV_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.AnnualSummaryParams.CARRIER_IDS_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.AnnualSummaryParams.MODES_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.AnnualSummaryParams.SERVICES_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.AnnualSummaryParams.LANES_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.AnnualSummaryParams.FROM_DATE_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.AnnualSummaryParams.TO_DATE_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.AnnualSummaryParams.ACCESSORIAL_NAME_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.AnnualSummaryParams.TOP_TEN_ACCESSORIAL_PARAM, type = Long.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.AnnualSummaryParams.SERVICE_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.AnnualSummaryParams.MODE_NAMES_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, name = DashboardSroredProcParam.AnnualSummaryParams.ANNUAL_SUMMARY_DATA_PARAM, type = Void.class)
                })
})

@SqlResultSetMappings({
        @SqlResultSetMapping(name = AnnualSummaryDto.Config.ResultMappings.ANNUAL_SUMMARY_MAPPING,
                classes = {
                        @ConstructorResult(targetClass = AnnualSummaryDto.class,
                                columns = {
                                        @ColumnResult(name = "QUARTER", type = String.class),
                                        @ColumnResult(name = "MODES", type = String.class),
                                        @ColumnResult(name = "SERVICE", type = String.class),
                                        @ColumnResult(name = "IS_HUNDRED_WEIGHT_OR_RETURN", type = Boolean.class),
                                        @ColumnResult(name = "SPEND", type = Double.class),
                                        @ColumnResult(name = "NO_OF_SHIPMENTS", type = Integer.class)
                                })
        }),
        @SqlResultSetMapping(name = AnnualSummaryDto.Config.ResultMappings.ANNUAL_SUMMARY_BY_CARRIER_MAPPING,
                classes = {
                        @ConstructorResult(targetClass = AnnualSummaryDto.class,
                                columns = {
                                        @ColumnResult(name = "ID", type = String.class),
                                        @ColumnResult(name = "NAME", type = String.class),
                                        @ColumnResult(name = "VALUE", type = Double.class)
                                })
                }),
        @SqlResultSetMapping(name = AnnualSummaryDto.Config.ResultMappings.ANNUAL_SUMMARY_BY_MONTH_MAPPING,
                classes = {
                        @ConstructorResult(targetClass = AnnualSummaryDto.class,
                                columns = {
                                        @ColumnResult(name = "BILL_DATE", type = Date.class),
                                        @ColumnResult(name = "AMOUNT", type = Double.class)
                                })
                })
})

@Entity
public class AnnualSummaryDto implements Serializable {
    @Id
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "VALUE")
    private Double value;

    @Column(name = "QUARTER")
    private String quarter;

    @Column(name = "MODES")
    private String modes;

    @Column(name = "SERVICE")
    private String service;

    @Column(name = "IS_HUNDRED_WEIGHT_OR_RETURN")
    private Boolean isHundredWeightOrReturn;

    @Column(name = "SPEND")
    private Double spend;

    @Column(name = "NO_OF_SHIPMENTS")
    private Integer noOfShipments;

    @Column(name = "BILL_DATE")
    private Date billDate;

    @Column(name = "AMOUNT")
    private Double amount;

    public AnnualSummaryDto(){}

    public AnnualSummaryDto(String quarter, String modes, String service, Boolean isHundredWeightOrReturn, Double spend, Integer noOfShipments) {
        this.quarter = quarter;
        this.modes = modes;
        this.service = service;
        this.isHundredWeightOrReturn = isHundredWeightOrReturn;
        this.spend = spend;
        this.noOfShipments = noOfShipments;
    }

    public AnnualSummaryDto(Long id, String name, Double value) {
        this.id = id;
        this.name = name;
        this.value = value;
    }

    public AnnualSummaryDto(Date billDate, Double amount) {
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

    public String getQuarter() {
        return quarter;
    }

    public void setQuarter(String quarter) {
        this.quarter = quarter;
    }

    public String getModes() {
        return modes;
    }

    public void setModes(String modes) {
        this.modes = modes;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public Boolean getHundredWeightOrReturn() {
        return isHundredWeightOrReturn;
    }

    public void setHundredWeightOrReturn(Boolean hundredWeightOrReturn) {
        isHundredWeightOrReturn = hundredWeightOrReturn;
    }

    public Double getSpend() {
        return spend;
    }

    public void setSpend(Double spend) {
        this.spend = spend;
    }

    public Integer getNoOfShipments() {
        return noOfShipments;
    }

    public void setNoOfShipments(Integer noOfShipments) {
        this.noOfShipments = noOfShipments;
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
            static final String ANNUAL_SUMMARY_MAPPING = "AnnualSummaryDto.AnnualSummaryMapping";
            static final String ANNUAL_SUMMARY_BY_SERVICE_MAPPING = ANNUAL_SUMMARY_MAPPING;
            static final String ANNUAL_SUMMARY_BY_CARRIER_MAPPING = "AnnualSummaryDto.AnnualSummaryByCarrierMapping";
            static final String ANNUAL_SUMMARY_BY_MONTH_MAPPING = "AnnualSummaryDto.AnnualSummaryByMonthMapping";
        }

        static class StoredProcedureName{
            static final String ANNUAL_SUMMARY = "SHP_DB_ANNUAL_SUMMRY_PROC";
            static final String ANNUAL_SUMMARY_BY_SERVICE = "SHP_DB_ANNUAL_SUMMRY_SERV_PROC";
            static final String ANNUAL_SUMMARY_BY_CARRIER = "SHP_DB_ANNUAL_SUMMRY_CARR_PROC";
            static final String ANNUAL_SUMMARY_BY_MONTH = "SHP_DB_ANNUAL_SUMMRY_MNTH_PROC";
        }

        public static class StoredProcedureQueryName{
            public static final String ANNUAL_SUMMARY = "AnnualSummaryDto.getAnnualSummary";
            public static final String ANNUAL_SUMMARY_BY_SERVICE = "AnnualSummaryDto.getAnnualSummaryByService";
            public static final String ANNUAL_SUMMARY_BY_CARRIER = "AnnualSummaryDto.getAnnualSummaryByCarrier";
            public static final String ANNUAL_SUMMARY_BY_MONTH = "AnnualSummaryDto.getAnnualSummaryByMonth";
        }
    }
}
