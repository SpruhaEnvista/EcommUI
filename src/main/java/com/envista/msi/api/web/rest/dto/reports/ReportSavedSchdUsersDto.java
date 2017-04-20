package com.envista.msi.api.web.rest.dto.reports;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by SreedharT on 4/6/2017.
 */
@NamedStoredProcedureQueries({
        @NamedStoredProcedureQuery(name = "SavedSchedReports.saveUsers", procedureName = "shp_rpt_savesched_users_proc",
                resultSetMappings = "SavedSchedUsers",
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, name = "p_savedSchedUserCur", type = Void.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "savedSchedRptId", type = Long.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "userId", type = Long.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "isEmailTempTobeSent", type = Boolean.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "isReportAttachEmail", type = Boolean.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "isReportSubscribed", type = Boolean.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "createUser", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "isShared", type = Boolean.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "canEdit", type = Boolean.class)
                }),
        @NamedStoredProcedureQuery(name = "ReportGetSSUser.reportUsersList", procedureName = "shp_get_rpt_ss_users_proc",
                resultSetMappings = "GetSchedUsers",
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, name = "p_savedSchedUserCur", type = Void.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "savedSchedRptId", type = Long.class)
                })
})

@SqlResultSetMappings({
        @SqlResultSetMapping(name = "SavedSchedUsers", classes = {
                @ConstructorResult(
                        targetClass = ReportSavedSchdUsersDto.class,
                        columns = {
                                @ColumnResult(name = "saved_sched_users_id", type = Long.class)
                        }
                )
        }),
        @SqlResultSetMapping(name = "GetSchedUsers", classes = {
                @ConstructorResult(
                        targetClass = ReportSavedSchdUsersDto.class,
                        columns = {
                                @ColumnResult(name = "saved_sched_users_id", type = Long.class),
                                @ColumnResult(name = "saved_sched_rpt_id", type = Long.class),
                                @ColumnResult(name = "user_id", type = Long.class),
                                @ColumnResult(name = "is_email_template_to_be_sent", type = Boolean.class),
                                @ColumnResult(name = "is_report_attach_email", type = Boolean.class),
                                @ColumnResult(name = "is_report_subscribed", type = Boolean.class),
                                @ColumnResult(name = "create_user", type = String.class),
                                @ColumnResult(name = "create_date", type = Date.class),
                                @ColumnResult(name = "last_update_user", type = String.class),
                                @ColumnResult(name = "last_update_date", type = Date.class),
                                @ColumnResult(name = "is_shared", type = Boolean.class),
                                @ColumnResult(name = "can_edit", type = Boolean.class)
                        }
                )
        })
})
@Entity
public class ReportSavedSchdUsersDto {

    @Id
    @Column(name="saved_sched_users_id")
    private Long savedSchdUsersId;

    @Column(name="saved_sched_rpt_id")
    private Long savedSchedRptId;

    @Column(name="user_id")
    private Long userId;

    @Column(name="is_email_template_to_be_sent")
    private Boolean emailTemplateToBeSent;

    @Column(name="is_report_attach_email")
    private Boolean reportAttachedMail;

    @Column(name="is_report_subscribed")
    private Boolean reportSubscribed;

    @Column(name="create_user")
    private String createUser;

    @Column(name="create_date")
    private Date createDate;

    @Column(name="last_update_user")
    private String lastUpdateUser;

    @Column(name="last_update_date")
    private Date lastUpdateDate;

    @Column(name="is_shared")
    private Boolean shared;

    @Column(name="can_edit")
    private Boolean canEdit;

    public Long getSavedSchdUsersId() {
        return savedSchdUsersId;
    }

    public void setSavedSchdUsersId(Long savedSchdUsersId) {
        this.savedSchdUsersId = savedSchdUsersId;
    }

    public Long getSavedSchedRptId() {
        return savedSchedRptId;
    }

    public void setSavedSchedRptId(Long savedSchedRptId) {
        this.savedSchedRptId = savedSchedRptId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
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

    public Boolean getEmailTemplateToBeSent() {
        return emailTemplateToBeSent;
    }

    public void setEmailTemplateToBeSent(Boolean emailTemplateToBeSent) {
        this.emailTemplateToBeSent = emailTemplateToBeSent;
    }

    public Boolean getReportAttachedMail() {
        return reportAttachedMail;
    }

    public void setReportAttachedMail(Boolean reportAttachedMail) {
        this.reportAttachedMail = reportAttachedMail;
    }

    public Boolean getReportSubscribed() {
        return reportSubscribed;
    }

    public void setReportSubscribed(Boolean reportSubscribed) {
        this.reportSubscribed = reportSubscribed;
    }

    public Boolean getShared() {
        return shared;
    }

    public Boolean getCanEdit() {
        return canEdit;
    }

    public void setShared(Boolean shared) {
        this.shared = shared;
    }

    public void setCanEdit(Boolean canEdit) {
        this.canEdit = canEdit;
    }

    public ReportSavedSchdUsersDto(){}

    public ReportSavedSchdUsersDto(Long savedSchdUsersId){
        this.savedSchdUsersId = savedSchdUsersId;
    }

    public ReportSavedSchdUsersDto(Long savedSchdUsersId,Long savedSchedRptId, Long userId,Boolean emailTemplateToBeSent,
                                   Boolean reportAttachedMail,Boolean reportSubscribed,String createUser,Date createDate,
                                   String lastUpdateUser,Date lastUpdateDate,Boolean shared,Boolean canEdit){
        this.savedSchdUsersId = savedSchdUsersId;
        this.savedSchedRptId = savedSchedRptId;
        this.userId = userId;
        this.emailTemplateToBeSent = emailTemplateToBeSent;
        this.reportAttachedMail = reportAttachedMail;
        this.reportSubscribed = reportSubscribed;
        this.createUser = createUser;
        this.createDate = createDate;
        this.lastUpdateUser = lastUpdateUser;
        this.lastUpdateDate = lastUpdateDate;
        this.shared = shared;
        this.canEdit = canEdit;
    }
}
