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
                })
})
@SqlResultSetMappings({
        @SqlResultSetMapping(name = "ReportFormat", classes = {
                @ConstructorResult(
                        targetClass = ReportFormatDto.class,
                        columns = {
                                @ColumnResult(name ="type_id", type = Long.class),
                                @ColumnResult(name ="report_format", type = String.class),
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

    public ReportFormatDto() { }

    public ReportFormatDto(Long typeId, String reportFormat) {
        this.typeId = typeId;
        this.reportFormat = reportFormat;
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
}