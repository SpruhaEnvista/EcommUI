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
        ),
        @NamedStoredProcedureQuery(
                name = DashboardReportUtilityDataDto.Config.StoredProcedureQueryName.RECORD_COUNT,
                procedureName = DashboardReportUtilityDataDto.Config.StoredProcedureName.DASHBOARD_REPORT_TOTAL_RECORD_COUNT,
                resultSetMappings = {DashboardReportUtilityDataDto.Config.ResultMappings.RECORD_COUNT},
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.DashboardReportParams.DATE_TYPE_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.DashboardReportParams.CARRIER_IDS_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.DashboardReportParams.DASHLETTE_NAME_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.DashboardReportParams.CONVERTED_CURRENCY_ID_PARAM, type = Long.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.DashboardReportParams.CONVERTED_CURRENCY_CODE_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.DashboardReportParams.CUSTOMER_IDS_CSV_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.DashboardReportParams.FROM_DATE_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.DashboardReportParams.TO_DATE_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.DashboardReportParams.CONVERTED_WEIGHT_UNIT_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.DashboardReportParams.DELIVERY_FLAG_DESC_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.DashboardReportParams.BOUND_TYPE_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.DashboardReportParams.POD, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.DashboardReportParams.POL, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.DashboardReportParams.MODES_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.DashboardReportParams.MODE_NAMES_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.DashboardReportParams.SERVICES_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.DashboardReportParams.LANES_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.DashboardReportParams.TAX_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.DashboardReportParams.ACC_DESC_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.DashboardReportParams.SERVICE_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.DashboardReportParams.INVOICE_STATUS_ID_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.DashboardReportParams.INVOICE_METHOD_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.DashboardReportParams.ORDER_MATCH_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.DashboardReportParams.SHIPPER_CITY_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.DashboardReportParams.SHIPPER_STATE_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.DashboardReportParams.SHIPPER_COUNTRY_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.DashboardReportParams.RECEIVER_CITY_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.DashboardReportParams.RECEIVER_STATE_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.DashboardReportParams.RECEIVER_COUNTRY_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.DashboardReportParams.REPORT_FOR_DASHLETTE_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.DashboardReportParams.PAGE_OFFSET_PARAM, type = Integer.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.DashboardReportParams.PAGE_SIZE_PARAM, type = Integer.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.DashboardReportParams.REPORT_TOTAL_ROW_COUNT_PARAM, type = Integer.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.DashboardReportParams.SEARCH_FILTER_CONDITION_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, name = DashboardStoredProcParam.DashboardReportParams.REPORT_DATA_PARAM, type = Void.class)
                })
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
        ),
        @SqlResultSetMapping(
                name = DashboardReportUtilityDataDto.Config.ResultMappings.RECORD_COUNT,
                classes = {
                        @ConstructorResult(
                                targetClass = DashboardReportUtilityDataDto.class,
                                columns = {
                                        @ColumnResult(name = "RECORD_COUNT", type = Integer.class)
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

    @Column(name = "RECORD_COUNT")
    private Integer recordCount;

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

    public DashboardReportUtilityDataDto(Integer recordCount) {
        this.recordCount = recordCount;
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

    public Integer getRecordCount() {
        return recordCount;
    }

    public void setRecordCount(Integer recordCount) {
        this.recordCount = recordCount;
    }

    public static class Config{
        static class ResultMappings{
            static final String DASHBOARD_REPORT_COLUMNS_MAPPING = "DashboardReportUtilityDataDto.ReportColumnNamesMapping";
            static final String DASHBOARD_REPORT_CUST_DEF_LBL_MAPPING = "DashboardReportUtilityDataDto.CustomDefinedLabelByCustomerMapping";
            static final String DASHBOARD_REPORT_COL_CONFIG_BY_USER_MAPPING = "DashboardReportUtilityDataDto.ColumnConfigByUserMapping";
            static final String RECORD_COUNT = "DashboardReportUtilityDataDto.RecordCountMapping";
        }

        static class StoredProcedureName{
            static final String DASHBOARD_REPORT_COLUMNS = "SHP_DB_REPORT_COLUMNS_PROC";
            static final String DASHBOARD_REPORT_CUST_DEF_LBL = "SHP_DB_CUST_DEF_LBL_CSTMR_PROC";
            static final String DASHBOARD_REPORT_COL_CONFIG_BY_USER = "SHP_DB_COL_CONFIG_BY_USER_PROC";
            static final String DASHBOARD_REPORT_TOTAL_RECORD_COUNT = "SHP_DB_PARCEL_FREIGHT_RPT_PROC";
        }

        public static class StoredProcedureQueryName{
            public static final String DASHBOARD_REPORT_COLUMNS = "DashboardReportUtilityDataDto.getDashboardReportColumnNames";
            public static final String DASHBOARD_REPORT_CUST_DEF_LBL = "DashboardReportUtilityDataDto.getCustomDefinedLabelByCustomer";
            public static final String DASHBOARD_REPORT_COL_CONFIG_BY_USER = "DashboardReportUtilityDataDto.getColumnConfigByUser";
            public static final String RECORD_COUNT = "DashboardReportUtilityDataDto.getRecordCount";
        }
    }
}
