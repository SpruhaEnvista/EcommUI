package com.envista.msi.api.web.rest.dto.reports;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Sreenivas on 2/24/2017.
 */
@NamedStoredProcedureQueries({
        @NamedStoredProcedureQuery(name = "EmailReports.getGenReportDetPath", procedureName = "shp_rpt_generated_det_proc",
                resultClasses = ReportGeneratedDetailsDto.class,
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, name = "p_genDetCusr", type = Void.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "crGeneratedRptId", type = Long.class)
                })
})
@SqlResultSetMappings({
        @SqlResultSetMapping(name = "GenRptDetails", classes = {
                @ConstructorResult(
                        targetClass = ReportGeneratedDetailsDto.class,
                        columns = {
                                @ColumnResult(name = "generated_rpt_det_id", type = Long.class),
                                @ColumnResult(name = "saved_sched_rpt_id", type = String.class),
                                @ColumnResult(name = "report_file_name", type = String.class),
                                @ColumnResult(name = "report_sent_email_ids", type = String.class),
                                @ColumnResult(name = "create_user", type = String.class),
                                @ColumnResult(name = "cost", type = Long.class),
                                @ColumnResult(name = "file_size", type = Long.class),
                                @ColumnResult(name = "completion_Date", type = Date.class),
                                @ColumnResult(name = "expires_date", type = Date.class),
                                @ColumnResult(name = "criteria", type = String.class),
                                @ColumnResult(name = "last_notified", type = Date.class),
                                @ColumnResult(name = "report_generated_status", type = Integer.class),
                                @ColumnResult(name = "email_subject", type = String.class),
                                @ColumnResult(name = "email_body", type = String.class),
                                @ColumnResult(name = "physical_file_name", type = String.class),
                                @ColumnResult(name = "report_type_id", type = Long.class),
                                @ColumnResult(name = "create_date", type = Date.class),
                                @ColumnResult(name = "status", type = String.class),
                                @ColumnResult(name = "exception_details", type = String.class),
                                @ColumnResult(name = "last_update_user", type = String.class),
                                @ColumnResult(name = "last_update_date", type = Date.class),
                                @ColumnResult(name = "next_submit_date_estimated", type = Date.class),
                                @ColumnResult(name = "file_location_server", type = Integer.class),
                                @ColumnResult(name = "running_on_hostname", type = String.class)
                        }
                )
        })
})
@Entity
public class ReportGeneratedDetailsDto {

    @Id
    @Column(name = "generated_rpt_det_id")
    private Long generatedRptId;

    @Column(name = "saved_sched_rpt_id")
    private Long savedSchedRptId;

    @Column(name = "report_file_name")
    private String reportFileName;

    @Column(name = "report_sent_email_ids")
    private String reportSentEmailIds;

    @Column(name = "create_user")
    private String createUser;

    @Column(name = "cost")
    private Long cost;

    @Column(name = "file_size")
    private Long fileSize;

    @Column(name = "completion_date")
    private Date completionDate;

    @Column(name = "expires_date")
    private Date expiresDate;

    @Column(name = "criteria")
    private String criteria;

    @Column(name = "last_notified")
    private Date lastNotified;

    @Column(name = "report_generated_status")
    private Integer reportGeneratedStatus;

    @Column(name = "email_subject")
    private String emailSubject;

    @Column(name = "email_body")
    private String emailBody;

    @Column(name = "physical_file_name")
    private String physicalFileName;

    @Column(name = "report_type_id")
    private Long reportTypeId;

    @Column(name = "create_date")
    private Date createDate;

    @Column(name = "status")
    private String status;

    @Column(name = "exception_details")
    private String exceptionDetails;

    @Column(name = "last_update_user")
    private String lastUpdateUser;

    @Column(name = "last_update_date")
    private Date lastUpdateDate;

    @Column(name = "next_submit_date_estimated")
    private Date nextSubmitDateEstimated;

    @Column(name = "file_location_server")
    private Integer fileLocationServer;

    @Column(name = "running_on_hostname")
    private String runningOnHostname;

    public Long getGeneratedRptId() {
        return generatedRptId;
    }

    public void setGeneratedRptId(Long generatedRptId) {
        this.generatedRptId = generatedRptId;
    }

    public Long getSavedSchedRptId() {
        return savedSchedRptId;
    }

    public void setSavedSchedRptId(Long savedSchedRptId) {
        this.savedSchedRptId = savedSchedRptId;
    }

    public String getReportFileName() {
        return reportFileName;
    }

    public void setReportFileName(String reportFileName) {
        this.reportFileName = reportFileName;
    }

    public String getReportSentEmailIds() {
        return reportSentEmailIds;
    }

    public void setReportSentEmailIds(String reportSentEmailIds) {
        this.reportSentEmailIds = reportSentEmailIds;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public Long getCost() {
        return cost;
    }

    public void setCost(Long cost) {
        this.cost = cost;
    }

    public Long getFileSize() {
        return fileSize;
    }

    public void setFileSize(Long fileSize) {
        this.fileSize = fileSize;
    }

    public Date getCompletionDate() {
        return completionDate;
    }

    public void setCompletionDate(Date completionDate) {
        this.completionDate = completionDate;
    }

    public Date getExpiresDate() {
        return expiresDate;
    }

    public void setExpiresDate(Date expiresDate) {
        this.expiresDate = expiresDate;
    }

    public String getCriteria() {
        return criteria;
    }

    public void setCriteria(String criteria) {
        this.criteria = criteria;
    }

    public Date getLastNotified() {
        return lastNotified;
    }

    public void setLastNotified(Date lastNotified) {
        this.lastNotified = lastNotified;
    }

    public Integer getReportGeneratedStatus() {
        return reportGeneratedStatus;
    }

    public void setReportGeneratedStatus(Integer reportGeneratedStatus) {
        this.reportGeneratedStatus = reportGeneratedStatus;
    }

    public String getEmailSubject() {
        return emailSubject;
    }

    public void setEmailSubject(String emailSubject) {
        this.emailSubject = emailSubject;
    }

    public String getEmailBody() {
        return emailBody;
    }

    public void setEmailBody(String emailBody) {
        this.emailBody = emailBody;
    }

    public String getPhysicalFileName() {
        return physicalFileName;
    }

    public void setPhysicalFileName(String physicalFileName) {
        this.physicalFileName = physicalFileName;
    }

    public Long getReportTypeId() {
        return reportTypeId;
    }

    public void setReportTypeId(Long reportTypeId) {
        this.reportTypeId = reportTypeId;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getExceptionDetails() {
        return exceptionDetails;
    }

    public void setExceptionDetails(String exceptionDetails) {
        this.exceptionDetails = exceptionDetails;
    }

    public String getLastUpdateUser() {
        return lastUpdateUser;
    }

    public void setLastUpdateUser(String lastUpdateUser) {
        this.lastUpdateUser = lastUpdateUser;
    }

    public Date getLastUpdateDate() {
        return lastUpdateDate;
    }

    public void setLastUpdateDate(Date lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }

    public Date getNextSubmitDateEstimated() {
        return nextSubmitDateEstimated;
    }

    public void setNextSubmitDateEstimated(Date nextSubmitDateEstimated) {
        this.nextSubmitDateEstimated = nextSubmitDateEstimated;
    }

    public Integer getFileLocationServer() {
        return fileLocationServer;
    }

    public void setFileLocationServer(Integer fileLocationServer) {
        this.fileLocationServer = fileLocationServer;
    }

    public String getRunningOnHostname() {
        return runningOnHostname;
    }

    public void setRunningOnHostname(String runningOnHostname) {
        this.runningOnHostname = runningOnHostname;
    }

    public ReportGeneratedDetailsDto(){}

    public ReportGeneratedDetailsDto(Long generatedRptId,Long savedSchedRptId, String reportFileName, String reportSentEmailIds ,String createUser,
                                  Long cost,Long fileSize,Date completionDate,Date expiresDate,String criteria,Date lastNotified,
                                  Integer reportGeneratedStatus,String emailSubject,String emailBody,String physicalFileName,
                                     Long reportTypeId,Date createDate,String status,String exceptionDetails,String lastUpdateUser,
                                  Date lastUpdateDate,Date nextSubmitDateEstimated,Integer fileLocationServer,String runningOnHostname) {
        this.generatedRptId = generatedRptId;
        this.savedSchedRptId = savedSchedRptId;
        this.reportFileName = reportFileName;
        this.reportSentEmailIds = reportSentEmailIds;
        this.createUser = createUser;
        this.cost = cost;
        this.fileSize = fileSize;
        this.completionDate = completionDate;
        this.expiresDate = expiresDate;
        this.criteria = criteria;
        this.lastNotified = lastNotified;
        this.reportGeneratedStatus = reportGeneratedStatus;
        this.emailSubject = emailSubject;
        this.emailBody = emailBody;
        this.physicalFileName = physicalFileName;
        this.reportTypeId = reportTypeId;
        this.createDate = createDate;
        this.status = status;
        this.exceptionDetails = exceptionDetails;
        this.lastUpdateUser = lastUpdateUser;
        this.lastUpdateDate = lastUpdateDate;
        this.nextSubmitDateEstimated = nextSubmitDateEstimated;
        this.fileLocationServer = fileLocationServer;
        this.runningOnHostname = runningOnHostname;
    }
}