package com.envista.msi.api.web.rest.dto.reports;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Sreenivas on 8/23/2017.
 */
@NamedStoredProcedureQueries({
        @NamedStoredProcedureQuery(name = "SavedUserGenReports.saveUserGen", procedureName = "shp_rpt_users_gen_status_proc",
                resultSetMappings = "SavedUserGen",
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, name = "p_userGenStatusCur", type = Void.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "savedSchedRptId", type = Long.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "userId", type = Long.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "createUser", type = String.class)
                })
})
@SqlResultSetMappings({
        @SqlResultSetMapping(name = "SavedUserGen", classes = {
                @ConstructorResult(
                        targetClass = ReportUserGenStatusDto.class,
                        columns = {
                                @ColumnResult(name = "user_gen_status_id", type = Long.class)
                        }
                )
        })
})
@Entity
public class ReportUserGenStatusDto {

    @Id
    @Column(name="user_gen_status_id")
    private Long userGenStatusId;

    @Column(name="saved_sched_rpt_id")
    private Long savedSchedRptId;

    @Column(name="user_id")
    private Long userId;

    @Column(name="create_user")
    private String createUser;

    @Column(name="create_date")
    private Date createDate;

    @Column(name="last_update_user")
    private String lastUpdateUser;

    @Column(name="last_update_date")
    private Date lastUpdateDate;

    public Long getUserGenStatusId() {
        return userGenStatusId;
    }

    public void setUserGenStatusId(Long userGenStatusId) {
        this.userGenStatusId = userGenStatusId;
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

    public ReportUserGenStatusDto(){}

    public ReportUserGenStatusDto(Long savedSchdUsersId){
        this.userGenStatusId = userGenStatusId;
    }

    public ReportUserGenStatusDto(Long userGenStatusId,Long savedSchedRptId, Long userId,String createUser,Date createDate,
                                   String lastUpdateUser,Date lastUpdateDate){
        this.userGenStatusId = userGenStatusId;
        this.savedSchedRptId = savedSchedRptId;
        this.userId = userId;
        this.createUser = createUser;
        this.createDate = createDate;
        this.lastUpdateUser = lastUpdateUser;
        this.lastUpdateDate = lastUpdateDate;
    }

}
