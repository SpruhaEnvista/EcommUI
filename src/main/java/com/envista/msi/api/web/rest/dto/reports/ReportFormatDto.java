package com.envista.msi.api.web.rest.dto.reports;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Siddhant on 20/03/2017.
 */
@NamedStoredProcedureQueries({
        @NamedStoredProcedureQuery(name = "ReportFormat.getReportFormat", procedureName = "shp_rpt_report_format_proc",
                resultSetMappings = "ReportFormat",
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_rpt_id", type = Long.class),
                        @StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, name = "get_report_format", type = Void.class)
        }),
        @NamedStoredProcedureQuery(name = "ReportFormat.getReportDateOptions", procedureName = "shp_rpt_date_criteria_proc",
                resultSetMappings = "ReportDateOptions",
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_rpt_id", type = Long.class),
                        @StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, name = "p_refcur_date_criteria_info", type = Void.class)
                })
})
@SqlResultSetMappings({
        @SqlResultSetMapping(name = "ReportFormat", classes = {
                @ConstructorResult(
                        targetClass = ReportFormatDto.class,
                        columns = {
                                @ColumnResult(name ="type_id", type = Long.class),
                                @ColumnResult(name ="report_format", type = String.class),
                                @ColumnResult(name ="selected", type = Boolean.class)
                        })
        }),
        @SqlResultSetMapping(name = "ReportDateOptions", classes = {
                @ConstructorResult(
                        targetClass = ReportFormatDto.class,
                        columns = {
                                @ColumnResult(name ="date_criteria", type = String.class),
                                @ColumnResult(name ="rpt_date_options_id", type = Long.class),
                                @ColumnResult(name ="selected", type = Boolean.class)
                        })
        })
})
@Entity
public class ReportFormatDto implements Serializable {
    @Id
    private Long id;

    @Column(name="type_id")
    private Long typeId;

    @Column(name="report_format")
    private String reportFormat;

    @Column(name="selected")
    private Boolean selected;

    @Column(name="rpt_date_options_id")
    private Long rptDateOptionId;

    @Column(name="date_criteria")
    private String dateCriteriaName;

    public ReportFormatDto() { }

    public ReportFormatDto(Long typeId, String reportFormat,Boolean selected) {
        this.typeId = typeId;
        this.reportFormat = reportFormat;
        this.selected=selected;
    }

    public ReportFormatDto(String dateCriteriaName ,Long rptDateOptionId,Boolean selected) {
        this.rptDateOptionId = rptDateOptionId;
        this.dateCriteriaName = dateCriteriaName;
        this.selected=selected;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTypeId() {
        return typeId;
    }

    public void setTypeId(Long typeId) {
        this.typeId = typeId;
    }

    public String getReportFormat() {
        return reportFormat;
    }

    public void setReportFormat(String reportFormat) {
        this.reportFormat = reportFormat;
    }

    public Long getRptDateOptionId() { return rptDateOptionId;  }

    public void setRptDateOptionId(Long rptDateOptionId) {  this.rptDateOptionId = rptDateOptionId; }

    public String getDateCriteriaName() { return dateCriteriaName;  }

    public void setDateCriteriaName(String dateCriteriaName) { this.dateCriteriaName = dateCriteriaName; }

    public Boolean getSelected() { return selected; }

    public void setSelected(Boolean selected) { this.selected = selected;  }
}