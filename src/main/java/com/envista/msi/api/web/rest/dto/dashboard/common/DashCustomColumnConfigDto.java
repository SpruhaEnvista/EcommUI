package com.envista.msi.api.web.rest.dto.dashboard.common;

import com.envista.msi.api.domain.util.DashboardStoredProcParam;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Sujit kumar on 06/04/2017.
 */
@NamedStoredProcedureQueries({
        @NamedStoredProcedureQuery(
                name = DashCustomColumnConfigDto.Config.StoredProcedureQueryName.SAVE_DASH_COLUMN_CONFIG,
                procedureName = DashCustomColumnConfigDto.Config.StoredProcedureName.SAVE_DASH_COLUMN_CONFIG,
                resultClasses = DashCustomColumnConfigDto.class,
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.DashCustomColumnParam.USER_ID_PARAM, type = Long.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.DashCustomColumnParam.REPORT_ID_PARAM, type = Long.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.DashCustomColumnParam.CUSTOM_DEF_1_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.DashCustomColumnParam.CUSTOM_DEF_2_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.DashCustomColumnParam.CUSTOM_DEF_3_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.DashCustomColumnParam.CUSTOM_DEF_4_PARAM, type = String.class)
                }
        )
})

@Entity
public class DashCustomColumnConfigDto implements Serializable {
    @Id
    private Long id;

    @Column(name = "USER_ID")
    private Long userId;

    @Column(name = "RPT_ID")
    private Long reportId;

    @Column(name = "COLUMNS_DEFINED_1")
    private String columnDefined1;

    @Column(name = "COLUMNS_DEFINED_2")
    private String columnDefined2;

    @Column(name = "COLUMNS_DEFINED_3")
    private String columnDefined3;

    @Column(name = "COLUMNS_DEFINED_4")
    private String columnDefined4;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getColumnDefined1() {
        return columnDefined1;
    }

    public void setColumnDefined1(String columnDefined1) {
        this.columnDefined1 = columnDefined1;
    }

    public String getColumnDefined2() {
        return columnDefined2;
    }

    public void setColumnDefined2(String columnDefined2) {
        this.columnDefined2 = columnDefined2;
    }

    public String getColumnDefined3() {
        return columnDefined3;
    }

    public void setColumnDefined3(String columnDefined3) {
        this.columnDefined3 = columnDefined3;
    }

    public String getColumnDefined4() {
        return columnDefined4;
    }

    public void setColumnDefined4(String columnDefined4) {
        this.columnDefined4 = columnDefined4;
    }

    public static class Config {
        static class ResultMappings {
            static final String SAVE_DASH_COLUMN_CONFIG_MAPPING = "DashCustomColumnConfigDto.saveUserDefinedColumnConfigMapping";
        }

        static class StoredProcedureName{
            static final String SAVE_DASH_COLUMN_CONFIG = "SHP_DB_SAVE_COLUMN_CONFIG_PROC";
        }

        public static class StoredProcedureQueryName{
            public static final String SAVE_DASH_COLUMN_CONFIG = "DashCustomColumnConfigDto.saveUserDefinedColumnConfig";
        }
    }
}
