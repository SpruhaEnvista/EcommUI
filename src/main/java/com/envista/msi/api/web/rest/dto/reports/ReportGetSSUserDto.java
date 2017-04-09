package com.envista.msi.api.web.rest.dto.reports;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by user1 on 4/8/2017.
 */
@NamedStoredProcedureQueries({
        @NamedStoredProcedureQuery(name = "ReportGetSSUser.getReportUserList", procedureName = "shp_get_rpt_ss_users_proc",
                resultClasses = ReportGetSSUserDto.class,
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, name = "p_savedSchedUserCur", type = Void.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "savedSchedRptId", type = Long.class)
                })
})

@Entity
public class ReportGetSSUserDto {

    @Id
    @Column(name = "saved_sched_users_id")
    private Long ssUserId;

    @Column(name = "saved_sched_rpt_id")
    private Long savedSchdRptId;

    @Column(name="user_id")
    private Long userId;

    @Column(name="is_email_template_to_be_sent")
    private Boolean emailTemplateToBeSent;

    @Column(name="is_report_attach_email")
    private Boolean isReportAttachEmail;

    @Column(name="is_report_subscribed")
    private Boolean isReportSubscribed;

    @Column(name="create_user")
    private String createUser;

    @Column(name="create_date")
    @Temporal(TemporalType.DATE)
    @JsonFormat(pattern="MM/dd/yyyy hh:mm a ")
    private Date createDate;

    @Column(name="last_update_user")
    private String lastUpdateUser;

    @Column(name ="last_update_date")
    @Temporal(TemporalType.DATE)
    @JsonFormat(pattern="MM/dd/yyyy hh:mm a ")
    private Date lastUpdateDate;

    @Column(name="is_shared")
    private Boolean isShared;

    @Column(name="can_edit")
    private Boolean canEdit;

    public Long getSsUserId() {
        return ssUserId;
    }

    public void setSsUserId(Long ssUserId) {
        this.ssUserId = ssUserId;
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

    public Boolean getEmailTemplateToBeSent() {
        return emailTemplateToBeSent;
    }

    public void setEmailTemplateToBeSent(Boolean emailTemplateToBeSent) {
        this.emailTemplateToBeSent = emailTemplateToBeSent;
    }

    public Boolean getReportAttachEmail() {
        return isReportAttachEmail;
    }

    public void setReportAttachEmail(Boolean reportAttachEmail) {
        isReportAttachEmail = reportAttachEmail;
    }

    public Boolean getReportSubscribed() {
        return isReportSubscribed;
    }

    public void setReportSubscribed(Boolean reportSubscribed) {
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

    public Boolean getShared() {
        return isShared;
    }

    public void setShared(Boolean shared) {
        isShared = shared;
    }

    public Boolean getCanEdit() {
        return canEdit;
    }

    public void setCanEdit(Boolean canEdit) {
        this.canEdit = canEdit;
    }
}
