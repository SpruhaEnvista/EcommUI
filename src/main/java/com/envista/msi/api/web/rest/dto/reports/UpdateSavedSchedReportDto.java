package com.envista.msi.api.web.rest.dto.reports;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by Sreenivas on 2/28/2017.
 */
@NamedStoredProcedureQueries({
        @NamedStoredProcedureQuery(name = "SavedReports.deleteUserSavedSchedReport", procedureName = "shp_rpt_deleteuser_saved_proc",
                resultSetMappings = "UpdateCount",
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, name = "updateReturn", type = Void.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "userId", type = Long.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "savedSchedId", type = Long.class)
                }),
        @NamedStoredProcedureQuery(name = "SavedReports.deleteAllSavedSchedReport", procedureName = "shp_rpt_deleteall_saved_proc",
                resultSetMappings = "UpdateCount",
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, name = "updateReturn", type = Void.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "userId", type = Long.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "savedSchedId", type = Long.class)
                }),
        @NamedStoredProcedureQuery(name = "SavedReports.addUserToSavedReport", procedureName = "shp_rpt_adduser_saved_proc",
                resultSetMappings = "UpdateCount",
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, name = "updateReturn", type = Void.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "userId", type = Long.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "savedSchedId", type = Long.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "createUser", type = String.class)
                })
})
@SqlResultSetMappings({
        @SqlResultSetMapping(name = "UpdateCount", classes = {
                @ConstructorResult(
                        targetClass = ReportResultsDto.class,
                        columns = {
                                @ColumnResult(name = "updateCount", type = Long.class)
                        }
                )
        })
})

@Entity
public class UpdateSavedSchedReportDto implements Serializable{

    private long loggedinuserId;

    private long sharetoUserId;

    private long savedSchedRptId;

    private boolean allowedit;

    private boolean deleteAll;

    private String createUser;

    @Column(name="updateCount")
    private Long updateCount;

    public Long getUpdateCount() {
        return updateCount;
    }

    public void setUpdateCount(Long updateCount) {
        this.updateCount = updateCount;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public long getLoggedinuserId() {
        return loggedinuserId;
    }

    public void setLoggedinuserId(long loggedinuserId) {
        this.loggedinuserId = loggedinuserId;
    }

    public long getSharetoUserId() {
        return sharetoUserId;
    }

    public void setSharetoUserId(long sharetoUserId) {
        this.sharetoUserId = sharetoUserId;
    }

    public long getSavedSchedRptId() {
        return savedSchedRptId;
    }

    public void setSavedSchedRptId(long savedSchedRptId) {
        this.savedSchedRptId = savedSchedRptId;
    }

    public boolean isAllowedit() {
        return allowedit;
    }

    public void setAllowedit(boolean allowedit) {
        this.allowedit = allowedit;
    }

    public boolean isDeleteAll() {
        return deleteAll;
    }

    public void setDeleteAll(boolean deleteAll) {
        this.deleteAll = deleteAll;
    }

    public UpdateSavedSchedReportDto(Long updateCount) {
        this.updateCount = updateCount;
    }
    public UpdateSavedSchedReportDto() {    }
}
