package com.envista.msi.api.web.rest.dto.reports;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by Sreenivas on 2/17/2017.
 */
@NamedStoredProcedureQueries({
        @NamedStoredProcedureQuery(name = "ReportResults.getReportResults", procedureName = "shp_rpt_results_proc",
                resultClasses = ReportResultsDto.class,
                parameters = {

                        @StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, name = "reportsList", type = Void.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "userId", type = Long.class)
                })
})

@Entity
public class ReportResultsDto implements Serializable {

    @Id
    @Column(name = "generated_rpt_det_id")
    private Long generatedRptId;

    @Column(name = "saved_Sched_rpt_id")
    private Long savedRptId;

    @Column(name = "file_name")
    private String fileName;

    @Column(name = "type_name")
    private String fileType;

    @Column(name = "run_date")
    private String runDate;

    @Column(name = "expire_date")
    private String expireDate;

    @Column(name = "running_status")
    private String status;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "completion_date")
    private Date completedDate;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "expires_date")
    private Date expiryDate;


    public Long getGeneratedRptId() {
        return generatedRptId;
    }

    public void setGeneratedRptId(Long generatedRptId) {
        this.generatedRptId = generatedRptId;
    }

    public Long getSavedRptId() {
        return savedRptId;
    }

    public void setSavedRptId(Long savedRptId) {
        this.savedRptId = savedRptId;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

   public String getRunDate() {   return runDate;   }

    public void setRunDate(String runDate) {
        this.runDate = runDate;
    }

    public String getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(String expireDate) {  this.expireDate = expireDate; }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

     public Date getCompletedDate() {   return completedDate;    }

    public void setCompletedDate(Date completedDate) {
        this.completedDate = completedDate;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Date expiryDate) { this.expiryDate = expiryDate;   }

}

