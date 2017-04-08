package com.envista.msi.api.web.rest.dto.reports;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.TreeSet;

/**
 * Created by Siddhant on 05/04/2017.
 */
@NamedStoredProcedureQueries({
        @NamedStoredProcedureQuery(name = "ReportFolder.getReportFolder", procedureName = "shp_rpt_folder_proc",
                resultSetMappings = "ReportFolder",
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_user_id", type = Long.class),
                        @StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, name = "p_refcur_folder_level_info", type = Void.class)
                }),
        @NamedStoredProcedureQuery(name = "ReportFolder.getReportFolder", procedureName = "shp_rpt_folder_proc",
                resultSetMappings = "ReportFolder",
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_user_id", type = Long.class),
                        @StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, name = "p_refcur_folder_level_info", type = Void.class)
                })
})
@SqlResultSetMappings({
        @SqlResultSetMapping(name = "ReportFolder", classes = {
                @ConstructorResult(
                        targetClass = ReportFolderDto.class,
                        columns = {
                                @ColumnResult(name = "RPT_FOLDER_ID", type = Long.class),
                                @ColumnResult(name = "FOLDER_NAME", type = String.class),
                                @ColumnResult(name = "PARENT_ID", type = Long.class)
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
public class ReportFolderDto implements Serializable,Comparable<ReportFolderDto> {
    @Id
    @Column(name="RPT_FOLDER_ID")
    private Long rptFolderId;

    @Column(name="FOLDER_NAME")
    private String rptFolderName;

    @Column(name="PARENT_ID")
    private Long parentId;

    @Column(name="value")
    private String folderIdNameValue;

    @Column(name="counts")
    private Long updateCount;

    private TreeSet<ReportFolderDto> collection = new TreeSet<ReportFolderDto>();

    public ReportFolderDto() { }

    public ReportFolderDto(String folderIdNameValue) {
        this.folderIdNameValue = folderIdNameValue;
    }

    public ReportFolderDto(Long rptFolderId, String rptFolderName,Long parentId) {
        this.rptFolderId = rptFolderId;
        this.rptFolderName = rptFolderName;
        this.parentId=parentId;
    }

    public Long getRptFolderId() { return rptFolderId; }

    public void setRptFolderId(Long rptFolderId) { this.rptFolderId = rptFolderId; }

    public String getRptFolderName() { return rptFolderName; }

    public void setRptFolderName(String rptFolderName) { this.rptFolderName = rptFolderName;  }

    public Long getParentId() { return parentId; }

    public void setParentId(Long parentId) { this.parentId = parentId; }

    public String getFolderIdNameValue() { return folderIdNameValue;  }

    public void setFolderIdNameValue(String folderIdNameValue) { this.folderIdNameValue = folderIdNameValue; }

    public TreeSet<ReportFolderDto> getCollection() { return collection;  }

    public void setCollection(TreeSet<ReportFolderDto> collection) { this.collection = collection;  }

    @Override
    public int compareTo(ReportFolderDto dto) {
        return this.getRptFolderId().compareTo(dto.getRptFolderId());
    }

    public ReportFolderDto(Long updateCount){
        this.updateCount = updateCount;
    }

    public Long getUpdateCount() {
        return updateCount;
    }

    public void setUpdateCount(Long updateCount) {
        this.updateCount = updateCount;
    }

    public Long getReportFolderId() {
        return rptFolderId;
    }

    public void setReportFolderId(Long rptFolderId) {
        this.rptFolderId = rptFolderId;
    }

    public String getReportFolderName() {
        return rptFolderName;
    }

    public void setReportFolderName(String rptFolderName) {
        this.rptFolderName = rptFolderName;
    }

}
