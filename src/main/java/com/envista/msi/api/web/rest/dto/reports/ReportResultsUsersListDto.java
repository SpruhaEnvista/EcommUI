package com.envista.msi.api.web.rest.dto.reports;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Sreenivas on 2/21/2017.
 */
@NamedStoredProcedureQueries({
        @NamedStoredProcedureQuery(name = "ReportResultsUsersList.getUsersList", procedureName = "shp_rpt_userslist_proc",
                resultSetMappings = "UsersList",
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, name = "p_usersList", type = Void.class)
                }),
        @NamedStoredProcedureQuery(name = "ReportResultsUsersList.pushToUser", procedureName = "shp_rpt_pushto_user_proc",
                resultSetMappings = "PushToUser",
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, name = "updateReturn", type = Void.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "userId", type = Long.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "savedSchedId", type = Long.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "generatedRptId", type = Long.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "createUser", type = String.class)
                }),
})
@SqlResultSetMappings({
        @SqlResultSetMapping(name = "PushToUser", classes = {
                @ConstructorResult(
                        targetClass = ReportResultsUsersListDto.class,
                        columns = {
                                @ColumnResult(name = "updateCount", type = Long.class)
                        }
                )
        }),
        @SqlResultSetMapping(name = "UsersList", classes = {
                @ConstructorResult(
                        targetClass = ReportResultsUsersListDto.class,
                         columns = {
                                @ColumnResult(name = "user_id", type = Long.class),
                                @ColumnResult(name = "user_name", type = String.class),
                                @ColumnResult(name = "email_alert", type = Boolean.class),
                                @ColumnResult(name = "attach_report", type = Boolean.class),
                                @ColumnResult(name = "saved_sched_rpt_id", type = Long.class),
                                @ColumnResult(name = "generated_rpt_id", type = Long.class),
                                @ColumnResult(name = "create_user", type = String.class)
                        }
                 )
        })
})

@Entity
public class ReportResultsUsersListDto implements Serializable {

    @Id
    @Column(name = "user_id")
    private Long userId;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "email_alert")
    private boolean emailAlert;

    @Column(name = "attach_report")
    private boolean attachReport;

    @Column(name = "saved_sched_rpt_id")
    private long savedSchedRptId;

    @Column(name = "generated_rpt_id")
    private long generatedRptId;

    @Column(name = "create_user")
    private String createUser;

    @Column(name = "updateCount")
    private long updateCount;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public boolean isEmailAlert() {
        return emailAlert;
    }

    public void setEmailAlert(boolean emailAlert) {
        this.emailAlert = emailAlert;
    }

    public boolean isAttachReport() {
        return attachReport;
    }

    public void setAttachReport(boolean attachReport) {
        this.attachReport = attachReport;
    }

    public long getSavedSchedRptId() {
        return savedSchedRptId;
    }

    public void setSavedSchedRptId(long savedSchedRptId) {
        this.savedSchedRptId = savedSchedRptId;
    }

    public long getGeneratedRptId() {
        return generatedRptId;
    }

    public void setGeneratedRptId(long generatedRptId) {
        this.generatedRptId = generatedRptId;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }
    public long getUpdateCount() {
        return updateCount;
    }

    public void setUpdateCount(long updateCount) {
        this.updateCount = updateCount;
    }
    public ReportResultsUsersListDto(Long updateCount) {
        this.updateCount = updateCount;
    }

    public ReportResultsUsersListDto(Long userId,String userName,boolean emailAlert,boolean attachReport,long savedSavedRptId,long generatedRptId,String createUser) {
        this.userId = userId;
        this.userName = userName;
        this.emailAlert = emailAlert;
        this.attachReport = attachReport;
        this.savedSchedRptId = savedSavedRptId;
        this.generatedRptId = generatedRptId;
        this.createUser = createUser;
    }

    public ReportResultsUsersListDto() {    }
}
