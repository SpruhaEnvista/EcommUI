package com.envista.msi.api.web.rest.dto.dashboard.annualsummary;

import com.envista.msi.api.domain.util.DashboardStoredProcParam;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by Sujit kumar on 20/02/2017.
 */
@NamedStoredProcedureQueries({
        @NamedStoredProcedureQuery(name = AccountSummaryDto.Config.StoredProcedureQueryName.ACCOUNT_SUMMARY, procedureName = AccountSummaryDto.Config.StoredProcedureName.ACCOUNT_SUMMARY,
        resultSetMappings = AccountSummaryDto.Config.ResultMappings.ACCOUNT_SUMMARY_MAPPING,
        parameters = {
                @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.AccountSummaryParams.DATE_TYPE_PARAM, type = String.class),
                @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.AccountSummaryParams.CONVERTED_CURRENCY_ID_PARAM, type = Long.class),
                @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.AccountSummaryParams.CONVERTED_CURRENCY_CODE_PARAM, type = String.class),
                @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.AccountSummaryParams.CUSTOMER_IDS_CSV_PARAM, type = String.class),
                @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.AccountSummaryParams.CARRIER_IDS_PARAM, type = String.class),
                @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.AccountSummaryParams.MODES_PARAM, type = String.class),
                @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.AccountSummaryParams.SERVICES_PARAM, type = String.class),
                @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.AccountSummaryParams.LANES_PARAM, type = String.class),
                @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.AccountSummaryParams.FROM_DATE_PARAM, type = String.class),
                @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.AccountSummaryParams.TO_DATE_PARAM, type = String.class),
                @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.AccountSummaryParams.ACCESSORIAL_NAME_PARAM, type = String.class),
                @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.AccountSummaryParams.TOP_TEN_ACCESSORIAL_PARAM, type = Long.class),
                @StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, name = DashboardStoredProcParam.AccountSummaryParams.ACCOUNT_SUMMARY_DATA_PARAM, type = Void.class)
        }),
        @NamedStoredProcedureQuery(name = AccountSummaryDto.Config.StoredProcedureQueryName.PARCEL_ACCOUNT_SUMMARY, procedureName = AccountSummaryDto.Config.StoredProcedureName.PARCEL_ACCOUNT_SUMMARY,
                resultSetMappings = AccountSummaryDto.Config.ResultMappings.PARCEL_ACCOUNT_SUMMARY_MAPPING,
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.AccountSummaryParams.DATE_TYPE_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.AccountSummaryParams.CONVERTED_CURRENCY_ID_PARAM, type = Long.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.AccountSummaryParams.CONVERTED_CURRENCY_CODE_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.AccountSummaryParams.CUSTOMER_IDS_CSV_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.AccountSummaryParams.CARRIER_IDS_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.AccountSummaryParams.MODES_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.AccountSummaryParams.SERVICES_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.AccountSummaryParams.LANES_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.AccountSummaryParams.FROM_DATE_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.AccountSummaryParams.TO_DATE_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.AccountSummaryParams.ACCESSORIAL_NAME_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.AccountSummaryParams.TOP_TEN_ACCESSORIAL_PARAM, type = Long.class),
                        @StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, name = DashboardStoredProcParam.AccountSummaryParams.ACCOUNT_SUMMARY_DATA_PARAM, type = Void.class)
                })
})

@SqlResultSetMappings({
        @SqlResultSetMapping(name = AccountSummaryDto.Config.ResultMappings.ACCOUNT_SUMMARY_MAPPING, classes = {
                @ConstructorResult(targetClass = AccountSummaryDto.class,
                columns = {
                        @ColumnResult(name = "BILL_YEAR", type = String.class),
                        @ColumnResult(name = "IS_LTL", type = Integer.class),
                        @ColumnResult(name = "AMOUNT", type = BigDecimal.class),
                        @ColumnResult(name = "CATEGORY", type = String.class),
                })
        })
})

@Entity
public class AccountSummaryDto implements Serializable {
    @Id
    private Long id;

    @Column(name = "BILL_YEAR")
    private String billYear;

    @Column(name = "IS_LTL")
    private Integer isLtl;

    @Column(name = "AMOUNT")
    private BigDecimal amount;

    @Column(name = "CATEGORY")
    private String category;

    public AccountSummaryDto(String billYear, Integer isLtl, BigDecimal amount, String category) {
        this.billYear = billYear;
        this.isLtl = isLtl;
        this.amount = amount;
        this.category = category;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBillYear() {
        return billYear;
    }

    public void setBillYear(String billYear) {
        this.billYear = billYear;
    }

    public Integer getLtl() {
        return isLtl;
    }

    public void setLtl(Integer ltl) {
        isLtl = ltl;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public static class Config{
        static class ResultMappings{
            static final String ACCOUNT_SUMMARY_MAPPING = "AccountSummaryDto.AccountSummaryMapping";
            static final String PARCEL_ACCOUNT_SUMMARY_MAPPING = ACCOUNT_SUMMARY_MAPPING;
        }

        static class StoredProcedureName{
            static final String ACCOUNT_SUMMARY = "SHP_DB_ACCOUNT_SUMMRY_PROC";
            static final String PARCEL_ACCOUNT_SUMMARY = "SHP_DB_PARCEL_ACC_SUMMARY_PROC";
        }

        public static class StoredProcedureQueryName{
            public static final String ACCOUNT_SUMMARY = "AccountSummaryDto.getAccountSummary";
            public static final String PARCEL_ACCOUNT_SUMMARY = "AccountSummaryDto.getParcelAccountSummary";
        }
    }
}
