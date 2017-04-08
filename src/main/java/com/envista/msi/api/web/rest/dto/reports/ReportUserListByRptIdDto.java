package com.envista.msi.api.web.rest.dto.reports;

import javax.persistence.*;

/**
 * Created by Sreedhar.T on 4/8/2017.
 */
@NamedStoredProcedureQueries({
        @NamedStoredProcedureQuery(name = "UserListByRptId.getUsers", procedureName = "shp_rpt_userlist_specRpt_proc",
                resultClasses =  ReportUserListByRptIdDto.class,
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, name = "p_usersList", type = Void.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "rptId", type = Long.class)
                })
})
@Entity
public class ReportUserListByRptIdDto {

    @Id
    @Column(name="user_id")
    private Long userId;

    @Column(name="user_name")
    private String userName;

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
}
