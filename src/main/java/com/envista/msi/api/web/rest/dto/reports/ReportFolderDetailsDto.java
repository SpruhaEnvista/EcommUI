package com.envista.msi.api.web.rest.dto.reports;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Sreedhar.T on 3/22/2017.
 */
@NamedStoredProcedureQueries({
        @NamedStoredProcedureQuery(name="ReportFolderDtls.createRow",procedureName = "shp_rpt_report_fldr_dtls_proc",
                resultSetMappings = "inserCount",
                parameters = {
                        @StoredProcedureParameter(mode= ParameterMode.REF_CURSOR, name = "refCur", type = Void.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "rptFolderId", type = Long.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "savedSchRptId", type = Long.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "crud" , type = Long.class)
        })
})
@SqlResultSetMappings({
        @SqlResultSetMapping(name="inserCount", classes = {
                @ConstructorResult(
                        targetClass = ReportFolderDetailsDto.class,
                        columns = {
                                @ColumnResult(name = "counts" , type = Long.class)
                        }
                )
        })
})
@Entity
public class ReportFolderDetailsDto implements Serializable {

    @Id
    @Column(name="FOLDER_DETAILS_ID")
    private Long folderDetailsId;

    @Column(name="RPT_FOLDER_ID")
    private Long reportFolderId;

    @Column(name="SAVED_SCHED_RPT_ID")
    private Long savedSchdReportId;

    @Column(name="counts")
    private Long count;

    public ReportFolderDetailsDto(){}

    public ReportFolderDetailsDto(Long count){this.count = count;}

    public Long getFolderDetailsId() {
        return folderDetailsId;
    }

    public void setFolderDetailsId(Long folderDetailsId) {
        this.folderDetailsId = folderDetailsId;
    }

    public Long getReportFolderId() {
        return reportFolderId;
    }

    public void setReportFolderId(Long reportFolderId) {
        this.reportFolderId = reportFolderId;
    }

    public Long getSavedSchdReportId() {
        return savedSchdReportId;
    }

    public void setSavedSchdReportId(Long savedSchdReportId) {
        this.savedSchdReportId = savedSchdReportId;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }
}
