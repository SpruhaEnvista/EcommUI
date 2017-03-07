package com.envista.msi.api.web.rest.dto.reports;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by Sreenivas on 2/28/2017.
 */
@NamedStoredProcedureQueries({
        @NamedStoredProcedureQuery(name = "SavedReports.deleteUserSavedSchedReport", procedureName = "shp_rpt_deleteuser_saved_proc",
                resultSetMappings = "UpdateSavedReports",
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, name = "updateReturn", type = Void.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "userId", type = Long.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "savedSchedId", type = Long.class)
                }),
        @NamedStoredProcedureQuery(name = "SavedReports.deleteAllSavedSchedReport", procedureName = "shp_rpt_deleteall_saved_proc",
                resultSetMappings = "UpdateSavedReports",
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, name = "updateReturn", type = Void.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "userId", type = Long.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "savedSchedId", type = Long.class)
                }),
        @NamedStoredProcedureQuery(name = "SavedReports.addUserToSavedReport", procedureName = "shp_rpt_adduser_saved_proc",
                resultSetMappings = "UpdateSavedReports",
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, name = "updateReturn", type = Void.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "userId", type = Long.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "savedSchedId", type = Long.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "createUser", type = String.class)
                }),
        @NamedStoredProcedureQuery(name = "SavedReports.runSavedSchedReport", procedureName = "shp_rpt_saved_sched_run_proc",
                resultSetMappings = "UpdateSavedReports",
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, name = "updateReturn", type = Void.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "userId", type = Long.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "savedSchedId", type = Long.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "createUser", type = String.class)
                }),
        @NamedStoredProcedureQuery(name = "SavedReports.saveFromReportResults", procedureName = "shp_rpt_save_results_proc",
                resultSetMappings = "UpdateSavedReports",
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, name = "updateReturn", type = Void.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "userId", type = Long.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "savedSchedId", type = Long.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "createUser", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "reportName", type = String.class)
                })
})
@SqlResultSetMappings({
        @SqlResultSetMapping(name = "UpdateSavedReports", classes = {
                @ConstructorResult(
                        targetClass = UpdateSavedSchedReportDto.class,
                        columns = {
                                @ColumnResult(name = "updateCount", type = Long.class)
                        }
                )
        })
})

@Entity
public class UpdateSavedSchedReportDto implements Serializable {

    private long loggedinuserId;

    private long sharetoUserId;

    private long savedSchedRptId;

    private boolean allowedit;

    private boolean deleteAll;

    private String createUser;

    private String reportName;

    @Id
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

    public String getReportName() {
        return reportName;
    }

    public void setReportName(String reportName) {
        this.reportName = reportName;
    }

    public UpdateSavedSchedReportDto(Long updateCount) {
        this.updateCount = updateCount;
    }
    public UpdateSavedSchedReportDto() {    }
}
