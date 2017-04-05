package com.envista.msi.api.web.rest.dto.reports;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by Sreenivas on 2/17/2017.
 */

@NamedStoredProcedureQueries({
        @NamedStoredProcedureQuery(name = "ReportResults.getReportResults", procedureName = "shp_rpt_results_proc",
                resultSetMappings = "ReportResults",
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, name = "reportsList", type = Void.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "userId", type = Long.class)
                }),
        @NamedStoredProcedureQuery(name = "ReportResults.updateExpiryDate", procedureName = "shp_rpt_update_expirydate_proc",
                resultSetMappings = "UpdateCount",
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, name = "updateReturn", type = Void.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "expiryDate", type = Date.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "generatedRptId", type = Long.class)
                }),
        @NamedStoredProcedureQuery(name = "ReportResults.deleteResultReport", procedureName = "SHP_RPT_RESULT_DELETE_PROC",
                resultSetMappings = "DeleteCount",
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "generatedRptId", type = Long.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "userId", type = Long.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "userName", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, name = "deleteReturn", type = Void.class)
                })
})
@SqlResultSetMappings({
        @SqlResultSetMapping(name = "UpdateCount", classes = {
                @ConstructorResult(
                        targetClass = ReportResultsDto.class,
                        columns = {
                                @ColumnResult(name = "updateCount", type = Long.class)
                        }
                )
        }),
        @SqlResultSetMapping(name = "ReportResults", classes = {
                @ConstructorResult(
                        targetClass = ReportResultsDto.class,
                        columns = {
                                @ColumnResult(name = "generated_rpt_det_id", type = Long.class),
                                @ColumnResult(name = "saved_sched_rpt_id", type = Long.class),
                                @ColumnResult(name = "file_name", type = String.class),
                                @ColumnResult(name = "type_name", type = String.class),
                                @ColumnResult(name = "run_date", type = String.class),
                                @ColumnResult(name = "expire_date", type = String.class),
                                @ColumnResult(name = "running_status", type = String.class),
                                @ColumnResult(name = "type", type = Integer.class),
                                @ColumnResult(name = "completion_date", type = Date.class),
                                @ColumnResult(name = "expires_date", type = Date.class)
                        }
                )
        }),
        @SqlResultSetMapping(name = "DeleteCount", classes = {
                @ConstructorResult(
                        targetClass = ReportResultsDto.class,
                        columns = {
                                @ColumnResult(name = "updateCount", type = Integer.class)
                        }
                )
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

    @Column(name = "type")
    private int type;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "completion_date")
    @JsonFormat(pattern="MM/dd/yyyy")
    private Date completedDate;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "expires_date")
    @JsonFormat(pattern="MM/dd/yyyy")
    private Date expiryDate;

    @Column(name="updateCount")
    private Long updateCount;

    public Long getGeneratedRptId() {
        return generatedRptId;
    }

    public void setGeneratedRptId(Long generatedRptId) { this.generatedRptId = generatedRptId; }

    public Long getSavedRptId() { return savedRptId; }

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

    public Long getUpdateCount() {  return updateCount; }

    public void setUpdateCount(Long updateCount) { this.updateCount = updateCount; }

    public int getType() {   return type;   }

    public void setType(int type) { this.type = type;   }

    public ReportResultsDto(){}

    public ReportResultsDto(Long updateCount) {

        this.updateCount = updateCount;
    }
    public ReportResultsDto(Long generatedRptId, Long savedRptId, String fileName, String fileType,String runDate,
                            String expireDate,String status,int type,Date completedDate,Date expiryDate) {
        this.generatedRptId = generatedRptId;
        this.savedRptId = savedRptId;
        this.fileName = fileName;
        this.fileType = fileType;
        this.runDate = runDate;
        this.expireDate = expireDate;
        this.status = status;
        this.type = type;
        this.completedDate = completedDate;
        this.expiryDate = expiryDate;
    }
}

