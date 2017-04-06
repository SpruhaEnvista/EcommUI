package com.envista.msi.api.web.rest.dto.reports;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

/**
 * Created by user1 on 4/6/2017.
 */
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
    private Long isEmailTemplateToBeSent;

    @Column(name="is_report_attach_email")
    private Long isReportAttachedMail;

    @Column(name="is_report_subscribed")
    private Long isReportSubscribed;

    @Column(name="create_user")
    private String createUser;

    @Column(name="create_date")
    private Date createDate;

    @Column(name="last_update_user")
    private String lastUpdateUser;

    @Column(name="last_update_date")
    private Date lastUpdateDate;

    @Column(name="is_shared")
    private Boolean isShared;

    @Column(name="can_edit")
    private Boolean canEdit;

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

    public Long getIsEmailTemplateToBeSent() {
        return isEmailTemplateToBeSent;
    }

    public void setIsEmailTemplateToBeSent(Long isEmailTemplateToBeSent) {
        this.isEmailTemplateToBeSent = isEmailTemplateToBeSent;
    }

    public Long getIsReportAttachedMail() {
        return isReportAttachedMail;
    }

    public void setIsReportAttachedMail(Long isReportAttachedMail) {
        this.isReportAttachedMail = isReportAttachedMail;
    }

    public Long getIsReportSubscribed() {
        return isReportSubscribed;
    }

    public void setIsReportSubscribed(Long isReportSubscribed) {
        this.isReportSubscribed = isReportSubscribed;
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
