package com.envista.msi.api.web.rest.dto.reports;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Sreenivas on 2/21/2017.
 */
@NamedStoredProcedureQueries({
        @NamedStoredProcedureQuery(name = "ReportResultsUsersList.getUsersList", procedureName = "shp_rpt_userslist_proc",
                resultClasses = ReportResultsUsersListDto.class,
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, name = "p_usersList", type = Void.class)
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
}
