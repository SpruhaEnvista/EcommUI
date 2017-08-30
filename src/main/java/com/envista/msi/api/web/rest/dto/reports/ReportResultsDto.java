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
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "userId", type = Long.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "orderBy", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "ascDesc", type = String.class)
                }),
        @NamedStoredProcedureQuery(name = "ReportResults.updateExpiryDate", procedureName = "shp_rpt_update_expirydate_proc",
                resultSetMappings = "UpdateCount",
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, name = "updateReturn", type = Void.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "expiryDate", type = Date.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "generatedRptId", type = Long.class)
                }),
        @NamedStoredProcedureQuery(name = "ReportResults.deleteResultReport", procedureName = "SHP_RPT_RESULT_DELETE_PROC",
                resultSetMappings = "UpdateCount",
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "generatedRptId", type = Long.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "userId", type = Long.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "userName", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, name = "deleteReturn", type = Void.class)
                }),
        @NamedStoredProcedureQuery(name = "ReportResults.userPermissionsForRepors", procedureName = "SHP_RPT_GET_USER_PERMIS_PROC",
                resultSetMappings = "UserPermissions",
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "userId", type = Long.class),
                        @StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, name = "userPermission", type = Void.class)
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
                                @ColumnResult(name = "file_size", type = Long.class),
                                @ColumnResult(name = "criteria", type = String.class),
                                @ColumnResult(name = "completion_date", type = Date.class),
                                @ColumnResult(name = "expires_date", type = Date.class),
                                @ColumnResult(name = "type_desc", type = String.class)
                        }
                )
        }),
        @SqlResultSetMapping(name = "UserPermissions", classes = {
                @ConstructorResult(
                        targetClass = ReportResultsDto.class,
                        columns = {
                                @ColumnResult(name = "user_allowed", type = Boolean.class),
                                @ColumnResult(name = "user_allowed_forpush", type = Boolean.class)
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

    @Column(name = "file_size")
    private Long fileSize;

    @Column(name = "criteria")
    private String criteria;

    @Column(name = "completion_date")
    private Date completedDate;

    @Column(name = "expires_date")
    private Date expiryDate;

    @Column(name = "type_desc")
    private String typeDesc;

    @Column(name="updateCount")
    private Long updateCount;

    @Column(name="user_allowed")
    private Boolean userAllowed;

    @Column(name="user_allowed_forpush")
    private Boolean userAllowedForPush;

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

    public String getTypeDesc() {
        return typeDesc;
    }

    public void setTypeDesc(String typeDesc) {
        this.typeDesc = typeDesc;
    }

    public Long getUpdateCount() {  return updateCount; }

    public void setUpdateCount(Long updateCount) { this.updateCount = updateCount; }

    public int getType() {   return type;   }

    public void setType(int type) { this.type = type;   }

    public Long getFileSize() {
        return fileSize;
    }

    public void setFileSize(Long fileSize) {
        this.fileSize = fileSize;
    }

    public String getCriteria() {
        return criteria;
    }

    public void setCriteria(String criteria) {
        this.criteria = criteria;
    }

    public Boolean getUserAllowed() {
        return userAllowed;
    }

    public void setUserAllowed(Boolean userAllowed) {
        this.userAllowed = userAllowed;
    }

    public Boolean getUserAllowedForPush() {
        return userAllowedForPush;
    }

    public void setUserAllowedForPush(Boolean userAllowedForPush) {
        this.userAllowedForPush = userAllowedForPush;
    }

    public ReportResultsDto(){}

    public ReportResultsDto(Long updateCount) {

        this.updateCount = updateCount;
    }
    public ReportResultsDto(Long generatedRptId, Long savedRptId, String fileName, String fileType,String runDate,
                            String expireDate,String status,int type,Long fileSize,String criteria,Date completedDate,Date expiryDate,String typeDesc) {
        this.generatedRptId = generatedRptId;
        this.savedRptId = savedRptId;
        this.fileName = fileName;
        this.fileType = fileType;
        this.runDate = runDate;
        this.expireDate = expireDate;
        this.status = status;
        this.type = type;
        this.fileSize = fileSize;
        this.criteria = criteria;
        this.completedDate = completedDate;
        this.expiryDate = expiryDate;
        this.typeDesc = typeDesc;
    }

    public ReportResultsDto(Boolean userAllowed, Boolean userAllowedForPush) {
        this.userAllowed = userAllowed;
        this.userAllowedForPush = userAllowedForPush;
    }
}

