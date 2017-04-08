package com.envista.msi.api.web.rest.dto.reports;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.TreeSet;

/**
 * Created by Sreedhar.T on 3/21/2017.
 */
@NamedStoredProcedureQueries({
        @NamedStoredProcedureQuery(name = "ReportFolder.createFolder", procedureName = "shp_rpt_report_folder_proc",
                resultSetMappings = "insertCount",
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, name = "refCur", type = Void.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "folderName", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name="createUser", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name="userId", type= Long.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name="parentId", type = Long.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name="crud", type = Long.class)

                }),
        @NamedStoredProcedureQuery(name = "ReportFolder.getReportFolder", procedureName = "shp_rpt_folder_proc",
                resultSetMappings = "ReportFolder",
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_user_id", type = Long.class),
                        @StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, name = "p_refcur_folder_level_info", type = Void.class)
                })
})
@SqlResultSetMappings({
        @SqlResultSetMapping(name = "insertCount", classes = {
                @ConstructorResult(
                        targetClass = ReportFolderDto.class,
                        columns = {
                                @ColumnResult(name = "counts", type = Long.class)
                        })
        }),
        @SqlResultSetMapping(name = "ReportFolder", classes = {
                @ConstructorResult(
                        targetClass = ReportFolderDto.class,
                        columns = {
                                @ColumnResult(name = "RPT_FOLDER_ID", type = Long.class),
                                @ColumnResult(name = "FOLDER_NAME", type = String.class),
                                @ColumnResult(name = "PARENT_ID", type = Long.class)
                        })
        })
})
@Entity
public class ReportFolderDto implements Serializable {
    @Id
    @Column(name = "RPT_FOLDER_ID")
    private Long rptFolderId;

    @Column(name="FOLDER_NAME")
    private String rptFolderName;

    @Column(name="PARENT_ID")
    private Long parentId;

    @Column(name="counts")
    private Long updateCount;

    public ReportFolderDto() { }

    public ReportFolderDto(Long count) {
        this.updateCount = count;
    }

    public ReportFolderDto(Long rptFolderId, String rptFolderName,Long parentId) {
        this.rptFolderId = rptFolderId;
        this.rptFolderName = rptFolderName;
        this.parentId=parentId;
    }

    public Long getRptFolderId() {
        return rptFolderId;
    }

    public void setRptFolderId(Long rptFolderId) {
        this.rptFolderId = rptFolderId;
    }

    public String getRptFolderName() {
        return rptFolderName;
    }

    public void setRptFolderName(String rptFolderName) {
        this.rptFolderName = rptFolderName;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public Long getUpdateCount() {
        return updateCount;
    }

    public void setUpdateCount(Long updateCount) {
        this.updateCount = updateCount;
    }


}