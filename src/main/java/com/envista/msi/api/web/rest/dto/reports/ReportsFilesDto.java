package com.envista.msi.api.web.rest.dto.reports;

/**
 * Created by user on 4/5/2017.
 */

import javax.persistence.*;

/**
 * Created by Sreenivas on 2/24/2017.
 */
@NamedStoredProcedureQueries({
        @NamedStoredProcedureQuery(name = "EmailReports.getReportPath", procedureName = "shp_rpt_filepath_proc",
                resultSetMappings = "EmailPath",
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, name = "p_filePathCusr", type = Void.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "crGeneratedRptId", type = Long.class)
                })
})
@SqlResultSetMappings({
        @SqlResultSetMapping(name = "EmailPath", classes = {
                @ConstructorResult(
                        targetClass = ReportsFilesDto.class,
                        columns = {
                                @ColumnResult(name = "generated_rpt_det_id", type = Long.class),
                                @ColumnResult(name = "report_file_name", type = String.class),
                                @ColumnResult(name = "physical_file_name", type = String.class)
                        }
                )
        })
})
@Entity
public class ReportsFilesDto {

    @Id
    @Column(name = "generated_rpt_det_id")
    private long generatedRptId;

    @Column(name = "report_file_name")
    private String reportName;

    @Column(name = "physical_file_name")
    private String filePath;

    public long getGeneratedRptId() {
        return generatedRptId;
    }

    public void setGeneratedRptId(long generatedRptId) {
        this.generatedRptId = generatedRptId;
    }

    public String getReportName() {
        return reportName;
    }

    public void setReportName(String reportName) {
        this.reportName = reportName;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public ReportsFilesDto(){}

    public ReportsFilesDto(long generatedRptId, String reportName, String filePath) {
        this.generatedRptId = generatedRptId;
        this.reportName = reportName;
        this.filePath = filePath;
    }
}
