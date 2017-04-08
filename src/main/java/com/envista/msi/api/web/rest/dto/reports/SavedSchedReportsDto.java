package com.envista.msi.api.web.rest.dto.reports;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import jdk.nashorn.internal.objects.annotations.Constructor;
import org.hibernate.annotations.JoinColumnOrFormula;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by Sreenivas on 2/24/2017.
 */
@NamedStoredProcedureQueries({
        @NamedStoredProcedureQuery(name = "SavedSchedReports.gerSavedSchedReports", procedureName = "shp_rpt_saved_sched_proc",
                resultSetMappings = "savedSchedReportsList",
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, name = "reportsList", type = Void.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "userId", type = Long.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "folderId", type = Long.class)
                }),
        @NamedStoredProcedureQuery(name="SavedSchedReports.changeOwnerBasedOnSSRptId", procedureName = "shp_rpt_chng_owner_proc",
                resultSetMappings = "updateOwnerResults",
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, name = "updateCount", type = Void.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "currentUserName" , type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "currentUserId" ,type = Long.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "newUserName", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "newUserId" , type = Long.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "ssRptId" , type = Long.class)
                })
})

@SqlResultSetMappings({
        @SqlResultSetMapping(name = "updateOwnerResults", classes = {
                @ConstructorResult(
                        targetClass = SavedSchedReportsDto.class,
                        columns = {
                                @ColumnResult(name = "ssRptTbCnt", type = Long.class),
                                @ColumnResult(name = "ssUserRptTbCnt", type = Long.class)
                        }
                )
        }),
        @SqlResultSetMapping(name = "savedSchedReportsList", classes = {
                @ConstructorResult(
                       targetClass = SavedSchedReportsDto.class,
                        columns = {
                               @ColumnResult(name = "saved_sched_rpt_id",type = Long.class),
                                @ColumnResult(name = "rpt_id",type = Long.class),
                                @ColumnResult(name = "is_scheduled",type = Boolean.class),
                                @ColumnResult(name = "report_file_name",type = String.class),
                                @ColumnResult(name = "sv_report_status",type = String.class),
                                @ColumnResult(name = "create_user",type = String.class),
                                @ColumnResult(name = "create_date",type = Date.class),
                                @ColumnResult(name = "last_update_user",type = String.class),
                                @ColumnResult(name = "last_update_date",type = Date.class),
                                @ColumnResult(name = "userscount",type = Integer.class),
                                @ColumnResult(name = "is_folder",type = Long.class),
                        }

                )
        })
})

@Entity
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SavedSchedReportsDto implements Serializable{

    @Id
    @Column(name = "saved_sched_rpt_id")
    private long savedSchedRptId;

    @Column(name = "rpt_id")
    private long rptId;

    @Column(name = "is_scheduled")
    private boolean scheduled;

    @Column(name = "report_file_name")
    private String reportName;

    @Column(name = "sv_report_status")
    private String reportStatus;

    @Column(name = "create_user")
    private String createUser;

    @Column(name = "create_date")
    @JsonFormat(pattern="MM/dd/yyyy hh:mm a ")
    private Date createDate;

    @Column(name = "last_update_user")
    private String lastUpdateUser;

    @Column(name = "last_update_date")
    @JsonFormat(pattern="MM/dd/yyyy hh:mm a ")
    private Date lastUpdateDate;

    @Column(name = "userscount")
    private int usersCount;

    @Column(name="is_folder")
    private Long isFolder;

    @Column(name="ssRptTbCnt")
    private Long reportTbUpdatCount;

    @Column(name="ssUserRptTbCnt")
    private Long reportUsersTbUpdateCount;

    public SavedSchedReportsDto(){}

    public SavedSchedReportsDto(Long reportTbUpdatCount,Long reportUsersTbUpdateCount){
        this.reportTbUpdatCount = reportTbUpdatCount;
        this.reportUsersTbUpdateCount = reportUsersTbUpdateCount;
    }

    public SavedSchedReportsDto(long savedSchedRptId,long rptId,boolean scheduled,String reportName,String reportStatus,
                                String createUser,Date createDate,String lastUpdateUser,Date lastUpdateDate,int usersCount,Long isFolder){
        this.savedSchedRptId = savedSchedRptId;
        this.rptId = rptId;
        this.scheduled = scheduled;
        this.reportName = reportName;
        this.reportStatus = reportStatus;
        this.createUser = createUser;
        this.createDate = createDate;
        this.lastUpdateUser = lastUpdateUser;
        this.lastUpdateDate = lastUpdateDate;
        this.usersCount = usersCount;
        this.isFolder = isFolder;
    }

    public Long getReportTbUpdatCount() {
        return reportTbUpdatCount;
    }

    public void setReportTbUpdatCount(Long reportTbUpdatCount) {
        this.reportTbUpdatCount = reportTbUpdatCount;
    }

    public Long getReportUsersTbUpdateCount() {
        return reportUsersTbUpdateCount;
    }

    public void setReportUsersTbUpdateCount(Long reportUsersTbUpdateCount) {
        this.reportUsersTbUpdateCount = reportUsersTbUpdateCount;
    }

    public long getSavedSchedRptId() {
        return savedSchedRptId;
    }

    public void setSavedSchedRptId(long savedSchedRptId) {
        this.savedSchedRptId = savedSchedRptId;
    }

    public long getRptId() {
        return rptId;
    }

    public void setRptId(long rptId) {
        this.rptId = rptId;
    }

    public boolean isScheduled() {
        return scheduled;
    }

    public void setScheduled(boolean scheduled) {
        this.scheduled = scheduled;
    }

    public String getReportName() {
        return reportName;
    }

    public void setReportName(String reportName) {
        this.reportName = reportName;
    }

    public String getReportStatus() {
        return reportStatus;
    }

    public void setReportStatus(String reportStatus) {
        this.reportStatus = reportStatus;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
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

    public int getUsersCount() {
        return usersCount;
    }

    public void setUsersCount(int usersCount) {
        this.usersCount = usersCount;
    }

    public Long getIsFolder() {
        return isFolder;
    }

    public void setIsFolder(Long isFolder) {
        this.isFolder = isFolder;
    }
}


