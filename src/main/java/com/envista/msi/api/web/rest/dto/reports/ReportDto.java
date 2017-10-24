package com.envista.msi.api.web.rest.dto.reports;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Sujit kumar on 24/10/2017.
 */

@NamedStoredProcedureQueries({
        @NamedStoredProcedureQuery(
                name = "ReportDto.getReportTemplates",
                procedureName = "SHP_RPT_GET_RPT_TEMPLATE_PROC",
                resultSetMappings = {"ReportDto.getReportTemplatesMapping"},
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_rpt_id", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, name = "p_report_list", type = Void.class)
                }
        )
})

@SqlResultSetMappings({
        @SqlResultSetMapping(
                name = "ReportDto.getReportTemplatesMapping",
                classes = {
                        @ConstructorResult(
                                targetClass = ReportDto.class,
                                columns = {
                                        @ColumnResult(name = "RPT_ID", type = Long.class),
                                        @ColumnResult(name = "REPORT_NAME", type = String.class)
                                }
                        )
                }
        )
})

@Entity
public class ReportDto implements Serializable {
    @Id
    @Column(name = "RPT_ID")
    private Long rptId;

    @Column(name = "REPORT_NAME")
    private String reportName;

    public ReportDto() {
    }

    public ReportDto(Long rptId, String reportName) {
        this.rptId = rptId;
        this.reportName = reportName;
    }

    public Long getRptId() {
        return rptId;
    }

    public void setRptId(Long rptId) {
        this.rptId = rptId;
    }

    public String getReportName() {
        return reportName;
    }

    public void setReportName(String reportName) {
        this.reportName = reportName;
    }
}
