package com.envista.msi.api.web.rest.dto.dashboard.annualsummary;

import com.envista.msi.api.domain.util.DashboardSroredProcParam;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Sujit kumar on 20/02/2017.
 */
@NamedStoredProcedureQueries({
        @NamedStoredProcedureQuery(name = AccountSummaryDto.Config.StoredProcedureQueryName.ACCOUNT_SUMMARY, procedureName = AccountSummaryDto.Config.StoredProcedureName.ACCOUNT_SUMMARY,
        resultSetMappings = AccountSummaryDto.Config.ResultMappings.ACCOUNT_SUMMARY_MAPPING,
        parameters = {
                @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.AccountSummaryParams.DATE_TYPE_PARAM, type = String.class),
                @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.AccountSummaryParams.CONVERTED_CURRENCY_ID_PARAM, type = Long.class),
                @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.AccountSummaryParams.CONVERTED_CURRENCY_CODE_PARAM, type = String.class),
                @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.AccountSummaryParams.CUSTOMER_IDS_CSV_PARAM, type = String.class),
                @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.AccountSummaryParams.CARRIER_IDS_PARAM, type = String.class),
                @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.AccountSummaryParams.MODES_PARAM, type = String.class),
                @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.AccountSummaryParams.SERVICES_PARAM, type = String.class),
                @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.AccountSummaryParams.LANES_PARAM, type = String.class),
                @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.AccountSummaryParams.FROM_DATE_PARAM, type = String.class),
                @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.AccountSummaryParams.TO_DATE_PARAM, type = String.class),
                @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.AccountSummaryParams.ACCESSORIAL_NAME_PARAM, type = String.class),
                @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.AccountSummaryParams.TOP_TEN_ACCESSORIAL_PARAM, type = Long.class),
                @StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, name = DashboardSroredProcParam.AccountSummaryParams.ACCOUNT_SUMMARY_DATA_PARAM, type = Void.class)
        })
})

@SqlResultSetMappings({
        @SqlResultSetMapping(name = AccountSummaryDto.Config.ResultMappings.ACCOUNT_SUMMARY_MAPPING, classes = {
                @ConstructorResult(targetClass = AccountSummaryDto.class,
                columns = {
                        @ColumnResult(name = "BILL_YEAR", type = String.class),
                        @ColumnResult(name = "IS_LTL", type = Boolean.class),
                        @ColumnResult(name = "AMOUNT", type = Double.class),
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
    private Boolean isLtl;

    @Column(name = "AMOUNT")
    private Double amount;

    @Column(name = "CATEGORY")
    private String category;

    public AccountSummaryDto(String billYear, Boolean isLtl, Double amount, String category) {
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

    public Boolean getLtl() {
        return isLtl;
    }

    public void setLtl(Boolean ltl) {
        isLtl = ltl;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
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
        }

        static class StoredProcedureName{
            static final String ACCOUNT_SUMMARY = "SHP_DB_ACCOUNT_SUMMRY_PROC";
        }

        public static class StoredProcedureQueryName{
            public static final String ACCOUNT_SUMMARY = "AccountSummaryDto.getAccountSummary";
        }
    }
}
