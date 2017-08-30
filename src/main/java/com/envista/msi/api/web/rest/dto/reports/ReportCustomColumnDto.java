package com.envista.msi.api.web.rest.dto.reports;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Sujit kumar on 22/08/2017.
 */
@NamedStoredProcedureQueries({
        @NamedStoredProcedureQuery(
                name = ReportCustomColumnDto.Config.CustomColumnNames.STORED_PROCEDURE_QUERY_NAME,
                procedureName = ReportCustomColumnDto.Config.CustomColumnNames.STORED_PROCEDURE_NAME,
                resultSetMappings = {ReportCustomColumnDto.Config.CustomColumnNames.STORED_PROCEDURE_QUERY_MAPPING},
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_customer_id", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_report_id", type = Long.class),
                        @StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, name = "p_cust_cols_recs", type = Void.class)
                }
        )
})

@SqlResultSetMappings({
        @SqlResultSetMapping(
                name = ReportCustomColumnDto.Config.CustomColumnNames.STORED_PROCEDURE_QUERY_MAPPING,
                classes = {
                        @ConstructorResult(
                                targetClass = ReportCustomColumnDto.class,
                                columns = {
                                        @ColumnResult(name = "REPORT_FIELD_NAME", type = String.class),
                                        @ColumnResult(name ="CUSTOM_FIELD_NAME", type = String.class)
                                }
                        )
                }
        )
})

@Entity
public class ReportCustomColumnDto implements Serializable {
    @Id
    @Column(name = "SHP_CUST_COL_DEF_ID")
    private Long id;

    @Column(name = "REPORT_FIELD_NAME")
    private String reportFieldName;

    @Column(name = "CUSTOM_FIELD_NAME")
    private String customFieldName;

    public ReportCustomColumnDto() {
    }

    public ReportCustomColumnDto(String reportFieldName, String customFieldName) {
        this.reportFieldName = reportFieldName;
        this.customFieldName = customFieldName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public static class Config{
        public static class CustomColumnNames{
            public static final String STORED_PROCEDURE_QUERY_NAME = "ReportCustomColumnDto.getReportCustomColumns";
            public static final String STORED_PROCEDURE_NAME = "SHP_RPT_GET_CUST_DEF_COLS_PROC";
            public static final String STORED_PROCEDURE_QUERY_MAPPING = "ReportCustomColumnDto.getReportCustomColumnsMapping";
        }
    }
}
