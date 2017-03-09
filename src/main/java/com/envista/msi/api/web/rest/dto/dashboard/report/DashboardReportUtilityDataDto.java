package com.envista.msi.api.web.rest.dto.dashboard.report;

import com.envista.msi.api.domain.util.DashboardStoredProcParam;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Sujit kumar on 03/03/2017.
 */

@NamedStoredProcedureQueries({
        @NamedStoredProcedureQuery(
                name = DashboardReportUtilityDataDto.Config.StoredProcedureQueryName.DASHBOARD_REPORT_COLUMNS,
                procedureName = DashboardReportUtilityDataDto.Config.StoredProcedureName.DASHBOARD_REPORT_COLUMNS,
                resultSetMappings = {DashboardReportUtilityDataDto.Config.ResultMappings.DASHBOARD_REPORT_COLUMNS_MAPPING},
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.DashboardReportUtilityDataParams.IS_LINE_ITEM_REPORT_PARAM, type = Long.class),
                        @StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, name = DashboardStoredProcParam.DashboardReportUtilityDataParams.REPORT_COLUMN_NAME_DATA_PARAM, type = Void.class)
                }
        ),
        @NamedStoredProcedureQuery(
                name = DashboardReportUtilityDataDto.Config.StoredProcedureQueryName.DASHBOARD_REPORT_CUST_DEF_LBL,
                procedureName = DashboardReportUtilityDataDto.Config.StoredProcedureName.DASHBOARD_REPORT_CUST_DEF_LBL,
                resultSetMappings = {DashboardReportUtilityDataDto.Config.ResultMappings.DASHBOARD_REPORT_CUST_DEF_LBL_MAPPING},
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.DashboardReportUtilityDataParams.CUSTOMER_IDS_CSV_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.DashboardReportUtilityDataParams.REPORT_ID_PARAM, type = Long.class),
                        @StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, name = DashboardStoredProcParam.DashboardReportUtilityDataParams.CUST_DEF_LBL_DATA_PARAM, type = Void.class)
                }
        ),
        @NamedStoredProcedureQuery(
                name = DashboardReportUtilityDataDto.Config.StoredProcedureQueryName.DASHBOARD_REPORT_COL_CONFIG_BY_USER,
                procedureName = DashboardReportUtilityDataDto.Config.StoredProcedureName.DASHBOARD_REPORT_COL_CONFIG_BY_USER,
                resultSetMappings = {DashboardReportUtilityDataDto.Config.ResultMappings.DASHBOARD_REPORT_COL_CONFIG_BY_USER_MAPPING},
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.DashboardReportUtilityDataParams.USER_ID_PARAM, type = Long.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.DashboardReportUtilityDataParams.REPORT_ID_PARAM, type = Long.class),
                        @StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, name = DashboardStoredProcParam.DashboardReportUtilityDataParams.USER_COL_CONFIG_DATA, type = Void.class)
                }
        )
})

@SqlResultSetMappings({
        @SqlResultSetMapping(
                name = DashboardReportUtilityDataDto.Config.ResultMappings.DASHBOARD_REPORT_COLUMNS_MAPPING,
                classes = {
                        @ConstructorResult(
                                targetClass = DashboardReportUtilityDataDto.class,
                                columns = {
                                        @ColumnResult(name = "COLUMN_NAME", type = String.class),
                                        @ColumnResult(name = "SELECT_CLAUSE", type = String.class)
                                }
                        )
                }
        ),
        @SqlResultSetMapping(
                name = DashboardReportUtilityDataDto.Config.ResultMappings.DASHBOARD_REPORT_CUST_DEF_LBL_MAPPING,
                classes = {
                        @ConstructorResult(
                                targetClass = DashboardReportUtilityDataDto.class,
                                columns = {
                                        @ColumnResult(name = "FIELD_COUNT", type = Integer.class),
                                        @ColumnResult(name = "REPORT_FIELD_NAME", type = String.class),
                                        @ColumnResult(name = "CUSTOM_FIELD_NAME", type = String.class)
                                }
                        )
                }
        ),
        @SqlResultSetMapping(
                name = DashboardReportUtilityDataDto.Config.ResultMappings.DASHBOARD_REPORT_COL_CONFIG_BY_USER_MAPPING,
                classes = {
                        @ConstructorResult(
                                targetClass = DashboardReportUtilityDataDto.class,
                                columns = {
                                        @ColumnResult(name = "USER_ID", type = Long.class),
                                        @ColumnResult(name = "RPT_ID", type = Long.class),
                                        @ColumnResult(name = "COLUMNS_DEFINED_1", type = String.class),
                                        @ColumnResult(name = "COLUMNS_DEFINED_2", type = String.class),
                                        @ColumnResult(name = "COLUMNS_DEFINED_3", type = String.class),
                                        @ColumnResult(name = "COLUMNS_DEFINED_4", type = String.class)
                                }
                        )
                }
        )
})

@Entity
public class DashboardReportUtilityDataDto implements Serializable {
    @Id
    private Long id;

    @Column(name = "COLUMN_NAME")
    private String columnName;

    @Column(name = "SELECT_CLAUSE")
    private String selectClause;

    @Column(name = "FIELD_COUNT")
    private Integer fieldCount;

    @Column(name = "REPORT_FIELD_NAME")
    private String reportFieldName;

    @Column(name = "CUSTOM_FIELD_NAME")
    private String customFieldName;

    @Column(name = "USER_ID")
    private Long userId;

    @Column(name = "RPT_ID")
    private Long reportId;

    @Column(name = "COLUMNS_DEFINED_1")
    private String columnsDefined1;

    @Column(name = "COLUMNS_DEFINED_2")
    private String columnsDefined2;

    @Column(name = "COLUMNS_DEFINED_3")
    private String columnsDefined3;

    @Column(name = "COLUMNS_DEFINED_4")
    private String columnsDefined4;

    public DashboardReportUtilityDataDto(){}

    public DashboardReportUtilityDataDto(String columnName, String selectClause) {
        this.columnName = columnName;
        this.selectClause = selectClause;
    }

    public DashboardReportUtilityDataDto(Integer fieldCount, String reportFieldName, String customFieldName) {
        this.fieldCount = fieldCount;
        this.reportFieldName = reportFieldName;
        this.customFieldName = customFieldName;
    }

    public DashboardReportUtilityDataDto(Long userId, Long reportId, String columnsDefined1, String columnsDefined2, String columnsDefined3, String columnsDefined4) {
        this.userId = userId;
        this.reportId = reportId;
        this.columnsDefined1 = columnsDefined1;
        this.columnsDefined2 = columnsDefined2;
        this.columnsDefined3 = columnsDefined3;
        this.columnsDefined4 = columnsDefined4;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public String getSelectClause() {
        return selectClause;
    }

    public void setSelectClause(String selectClause) {
        this.selectClause = selectClause;
    }

    public String getReportFieldName() {
        return reportFieldName;
    }

    public void setReportFieldName(String reportFieldName) {
        this.reportFieldName = reportFieldName;
    }

    public String getCustomFieldName() {
        return customFieldName;
    }

    public void setCustomFieldName(String customFieldName) {
        this.customFieldName = customFieldName;
    }

    public Integer getFieldCount() {
        return fieldCount;
    }

    public void setFieldCount(Integer fieldCount) {
        this.fieldCount = fieldCount;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getReportId() {
        return reportId;
    }

    public void setReportId(Long reportId) {
        this.reportId = reportId;
    }

    public String getColumnsDefined1() {
        return columnsDefined1;
    }

    public void setColumnsDefined1(String columnsDefined1) {
        this.columnsDefined1 = columnsDefined1;
    }

    public String getColumnsDefined2() {
        return columnsDefined2;
    }

    public void setColumnsDefined2(String columnsDefined2) {
        this.columnsDefined2 = columnsDefined2;
    }

    public String getColumnsDefined3() {
        return columnsDefined3;
    }

    public void setColumnsDefined3(String columnsDefined3) {
        this.columnsDefined3 = columnsDefined3;
    }

    public String getColumnsDefined4() {
        return columnsDefined4;
    }

    public void setColumnsDefined4(String columnsDefined4) {
        this.columnsDefined4 = columnsDefined4;
    }

    public static class Config{
        static class ResultMappings{
            static final String DASHBOARD_REPORT_COLUMNS_MAPPING = "DashboardReportUtilityDataDto.ReportColumnNamesMapping";
            static final String DASHBOARD_REPORT_CUST_DEF_LBL_MAPPING = "DashboardReportUtilityDataDto.CustomDefinedLabelByCustomerMapping";
            static final String DASHBOARD_REPORT_COL_CONFIG_BY_USER_MAPPING = "DashboardReportUtilityDataDto.ColumnConfigByUserMapping";
        }

        static class StoredProcedureName{
            static final String DASHBOARD_REPORT_COLUMNS = "SHP_DB_REPORT_COLUMNS_PROC";
            static final String DASHBOARD_REPORT_CUST_DEF_LBL = "SHP_DB_CUST_DEF_LBL_CSTMR_PROC";
            static final String DASHBOARD_REPORT_COL_CONFIG_BY_USER = "SHP_DB_COL_CONFIG_BY_USER_PROC";
        }

        public static class StoredProcedureQueryName{
            public static final String DASHBOARD_REPORT_COLUMNS = "DashboardReportUtilityDataDto.getDashboardReportColumnNames";
            public static final String DASHBOARD_REPORT_CUST_DEF_LBL = "DashboardReportUtilityDataDto.getCustomDefinedLabelByCustomer";
            public static final String DASHBOARD_REPORT_COL_CONFIG_BY_USER = "DashboardReportUtilityDataDto.getColumnConfigByUser";
        }
    }
}
