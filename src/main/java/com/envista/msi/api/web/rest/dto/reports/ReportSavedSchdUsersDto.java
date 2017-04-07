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
                })
})

@SqlResultSetMappings({
        @SqlResultSetMapping(name = "SavedSchedUsers", classes = {
                @ConstructorResult(
                        targetClass = ReportSavedSchdUsersDto.class,
                        columns = {
                                @ColumnResult(name = "savedSchedUsersId", type = Long.class)
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
    private Long savedSchdRptId;

    @Column(name="user_id")
    private Long userId;

    @Column(name="is_email_template_to_be_sent")
    private boolean isEmailTemplateToBeSent;

    @Column(name="is_report_attach_email")
    private boolean isReportAttachedMail;

    @Column(name="is_report_subscribed")
    private boolean isReportSubscribed;

    @Column(name="create_user")
    private String createUser;

    @Column(name="create_date")
    private Date createDate;

    @Column(name="last_update_user")
    private String lastUpdateUser;

    @Column(name="last_update_date")
    private Date lastUpdateDate;

    @Column(name="is_shared")
    private boolean shared;

    @Column(name="can_edit")
    private boolean canEdit;

    public Long getSavedSchdUsersId() {
        return savedSchdUsersId;
    }

    public void setSavedSchdUsersId(Long savedSchdUsersId) {
        this.savedSchdUsersId = savedSchdUsersId;
    }

    public Long getSavedSchdRptId() {
        return savedSchdRptId;
    }

    public void setSavedSchdRptId(Long savedSchdRptId) {
        this.savedSchdRptId = savedSchdRptId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public boolean isEmailTemplateToBeSent() {
        return isEmailTemplateToBeSent;
    }

    public void setEmailTemplateToBeSent(boolean emailTemplateToBeSent) {
        isEmailTemplateToBeSent = emailTemplateToBeSent;
    }

    public boolean isReportAttachedMail() {
        return isReportAttachedMail;
    }

    public void setReportAttachedMail(boolean reportAttachedMail) {
        isReportAttachedMail = reportAttachedMail;
    }

    public boolean isReportSubscribed() {
        return isReportSubscribed;
    }

    public void setReportSubscribed(boolean reportSubscribed) {
        isReportSubscribed = reportSubscribed;
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

    public boolean isShared() {
        return shared;
    }

    public void setShared(boolean shared) {
        shared = shared;
    }

    public boolean isCanEdit() {
        return canEdit;
    }

    public void setCanEdit(boolean canEdit) {
        this.canEdit = canEdit;
    }

}
