package com.envista.msi.api.web.rest.dto.reports;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by Sreenivas on 2/24/2017.
 */
@NamedStoredProcedureQueries({
        @NamedStoredProcedureQuery(name = "SavedSchedReports.gerSavedSchedReports", procedureName = "shp_rpt_saved_sched_proc",
                resultSetMappings = "savedSchedList",
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, name = "reportsList", type = Void.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "userId", type = Long.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "folderId", type = Long.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "orderBy", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "ascDesc", type = String.class)
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
                }),
        @NamedStoredProcedureQuery(name = "SavedSchedReports.gerSavedSchedTemplates", procedureName = "shp_rpt_saved_sched_temp_proc",
                resultSetMappings = "savedSchedReportsList",
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, name = "reportsList", type = Void.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "userId", type = Long.class)
                }),
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
                                @ColumnResult(name = "is_packet",type = Boolean.class)
                        }
                )
        }),
        @SqlResultSetMapping(name = "savedSchedList", classes = {
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
                                @ColumnResult(name = "is_packet",type = Boolean.class),
                                @ColumnResult(name = "can_edit",type = Boolean.class),
                                @ColumnResult(name = "is_shared",type = Boolean.class),
                                @ColumnResult(name = "packets_count",type = Integer.class)
                        }
                )
        })
})

@Entity
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SavedSchedReportsDto implements Serializable{

    @Id
    @Column(name = "saved_sched_rpt_id")
    private Long savedSchedRptId;

    @Column(name = "rpt_id")
    private Long rptId;

    @Column(name = "is_scheduled")
    private Boolean scheduled;

    @Column(name = "report_file_name")
    private String reportFileName;

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
    private Integer usersCount;

    @Column(name = "rpt_descr")
    private String reportDescr;

    @Column(name="is_folder")
    private Long isFolder;

    @Column(name="is_packet")
    private Boolean packet;

    @Column(name="ssRptTbCnt")
    private Long reportTbUpdatCount;

    @Column(name="ssUserRptTbCnt")
    private Long reportUsersTbUpdateCount;

    @Column(name = "can_edit")
    private Boolean canEdit;

    @Column(name = "is_shared")
    private Boolean shared;

    @Column(name="packets_count")
    private Integer packetsCount;

    public SavedSchedReportsDto(){}

    public SavedSchedReportsDto(Long reportTbUpdatCount,Long reportUsersTbUpdateCount){
        this.reportTbUpdatCount = reportTbUpdatCount;
        this.reportUsersTbUpdateCount = reportUsersTbUpdateCount;
    }

    public SavedSchedReportsDto(Long savedSchedRptId,Long rptId,Boolean scheduled,String reportFileName,String reportStatus,
                                String createUser,Date createDate,String lastUpdateUser,Date lastUpdateDate,Integer usersCount,
                                Long isFolder, Boolean packet){
        this.savedSchedRptId = savedSchedRptId;
        this.rptId = rptId;
        this.scheduled = scheduled;
        this.reportFileName = reportFileName;
        this.reportStatus = reportStatus;
        this.createUser = createUser;
        this.createDate = createDate;
        this.lastUpdateUser = lastUpdateUser;
        this.lastUpdateDate = lastUpdateDate;
        this.usersCount = usersCount;
        this.isFolder = isFolder;
        this.packet = packet;
    }
    public SavedSchedReportsDto(Long savedSchedRptId,Long rptId,Boolean scheduled,String reportFileName,String reportStatus,
                                String createUser,Date createDate,String lastUpdateUser,Date lastUpdateDate,Integer usersCount,
                                Long isFolder, Boolean packet,Boolean canEdit, Boolean shared){
        this.savedSchedRptId = savedSchedRptId;
        this.rptId = rptId;
        this.scheduled = scheduled;
        this.reportFileName = reportFileName;
        this.reportStatus = reportStatus;
        this.createUser = createUser;
        this.createDate = createDate;
        this.lastUpdateUser = lastUpdateUser;
        this.lastUpdateDate = lastUpdateDate;
        this.usersCount = usersCount;
        this.isFolder = isFolder;
        this.packet = packet;
        this.canEdit = canEdit;
        this.shared = shared;
    }
    public SavedSchedReportsDto(Long savedSchedRptId,Long rptId,Boolean scheduled,String reportFileName,String reportStatus,
                                String createUser,Date createDate,String lastUpdateUser,Date lastUpdateDate,Integer usersCount,
                                Long isFolder, Boolean packet,Boolean canEdit, Boolean shared,Integer packetsCount){
        this.savedSchedRptId = savedSchedRptId;
        this.rptId = rptId;
        this.scheduled = scheduled;
        this.reportFileName = reportFileName;
        this.reportStatus = reportStatus;
        this.createUser = createUser;
        this.createDate = createDate;
        this.lastUpdateUser = lastUpdateUser;
        this.lastUpdateDate = lastUpdateDate;
        this.usersCount = usersCount;
        this.isFolder = isFolder;
        this.packet = packet;
        this.canEdit = canEdit;
        this.shared = shared;
        this.packetsCount = packetsCount;
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

    public Long getSavedSchedRptId() {
        return savedSchedRptId;
    }

    public void setSavedSchedRptId(Long savedSchedRptId) {
        this.savedSchedRptId = savedSchedRptId;
    }

    public Long getRptId() {
        return rptId;
    }

    public void setRptId(Long rptId) {
        this.rptId = rptId;
    }

    public Boolean getScheduled() {
        return scheduled;
    }

    public void setScheduled(Boolean scheduled) {
        this.scheduled = scheduled;
    }

    public String getReportFileName() {
        return reportFileName;
    }

    public void setReportFileName(String reportFileName) {
        this.reportFileName = reportFileName;
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

    public Integer getUsersCount() {
        return usersCount;
    }

    public void setUsersCount(Integer usersCount) {
        this.usersCount = usersCount;
    }

    public String getReportDescr() { return reportDescr;  }
    public Long getIsFolder() {
        return isFolder;
    }

    public void setReportDescr(String reportDescr) { this.reportDescr = reportDescr;  }
    public void setIsFolder(Long isFolder) {
        this.isFolder = isFolder;
    }

    public Boolean getPacket() {
        return packet;
    }

    public void setPacket(Boolean packet) {
        this.packet = packet;
    }

    public Boolean getCanEdit() {
        return canEdit;
    }

    public void setCanEdit(Boolean canEdit) {
        this.canEdit = canEdit;
    }

    public Boolean getShared() {
        return shared;
    }

    public void setShared(Boolean shared) {
        this.shared = shared;
    }

    public Integer getPacketsCount() {
        return packetsCount;
    }

    public void setPacketsCount(Integer packetsCount) {
        this.packetsCount = packetsCount;
    }

}


