package com.envista.msi.api.web.rest.dto.reports;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by Sreedhar.T on 3/21/2017.
 */
@NamedStoredProcedureQueries({
        @NamedStoredProcedureQuery(name = "ReportFolder.createFolder", procedureName = "shp_rpt_report_folder_proc",
                resultSetMappings = "insertCount",
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, name = "refCur", type = Void.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "folderName", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name="user1", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name="parentId", type = Long.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name="crud", type = Long.class)
                })
})
@SqlResultSetMappings({
        @SqlResultSetMapping(name = "insertCount", classes = {
                @ConstructorResult(
                        targetClass = ReportFolderDto.class,
                        columns = {
                                @ColumnResult(name = "counts", type = Long.class)
                        }
                )
        })
})
@Entity
public class ReportFolderDto implements Serializable{

    @Id
    @Column(name = "RPT_FOLDER_ID")
    private Long reportFolderId;

    @Column(name="FOLDER_NAME")
    private String reportFolderName;

    @Column(name="PARENT_ID")
    private Long parentId;

    @Column(name="CREATE_USER")
    private String createUser;

    @Column(name="CREATE_DATE")
    private Date createDate;

    @Column(name="LAST_UPDATE_USER")
    private String lastUpdateUser;

    @Column(name="LAST_UPDATE_DATE")
    private Date lastUpdateDate;

    @Column(name="counts")
    private Long count;

    public ReportFolderDto(){}

    public ReportFolderDto(Long count){
        this.count = count;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public Long getReportFolderId() {
        return reportFolderId;
    }

    public void setReportFolderId(Long reportFolderId) {
        this.reportFolderId = reportFolderId;
    }

    public String getReportFolderName() {
        return reportFolderName;
    }

    public void setReportFolderName(String reportFolderName) {
        this.reportFolderName = reportFolderName;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
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
}
