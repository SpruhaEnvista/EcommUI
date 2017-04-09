package com.envista.msi.api.web.rest.dto.reports;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Sreenivas on 4/6/2017.
 */

@NamedStoredProcedureQueries({
        @NamedStoredProcedureQuery(name="ReportSortCol.insertRecord" , procedureName = "shp_rpt_savesched_sort_proc",
                resultSetMappings = "updatedSortCol",
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.REF_CURSOR , name="p_savedSchedSortCur", type= Void.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name="savedSchedRptId", type= Long.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name="rptDetailsId", type = Long.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name="isSubTotRequired", type = Boolean.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name="isAscending", type = Boolean.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name="sortOrder", type = Integer.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name="groupByCol", type = Boolean.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name="createUser", type = String.class)

                })
})

@SqlResultSetMappings({
        @SqlResultSetMapping(name = "updatedSortCol", classes = {
                @ConstructorResult(
                        targetClass = ReportsSortDto.class,
                        columns = {
                                @ColumnResult(name = "saved_sched_sort_id", type = Long.class)
                        }
                )
        })
})

@Entity
public class ReportsSortDto {

    @Id
    @Column(name = "saved_sched_sort_id")
    private Long savedSchedSortId;

    @Column(name = "saved_sched_rpt_id")
    private Long savedSchedRptId;

    @Column(name = "sort_by_column")
    private Long rptDetailsId;

    @Column(name = "is_sub_tot_required")
    private Boolean subTotRequired;

    @Column(name = "is_Ascending")
    private Boolean ascending;

    @Column(name = "sort_order")
    private Integer sortOrder;

    @Column(name = "create_user")
    private String createUser;

    @Column(name = "create_date")
    private Date createDate;

    @Column(name = "last_update_user")
    private String lastUpdateUser;

    @Column(name = "last_update_date")
    private Date lastUpdateDate;

    @Column(name = "group_by_col")
    private Boolean groupByCol;

    public Long getSavedSchedSortId() {
        return savedSchedSortId;
    }

    public void setSavedSchedSortId(Long savedSchedSortId) {
        this.savedSchedSortId = savedSchedSortId;
    }

    public Long getSavedSchedRptId() {
        return savedSchedRptId;
    }

    public void setSavedSchedRptId(Long savedSchedRptId) {
        this.savedSchedRptId = savedSchedRptId;
    }

    public Long getRptDetailsId() {
        return rptDetailsId;
    }

    public void setRptDetailsId(Long rptDetailsId) {
        this.rptDetailsId = rptDetailsId;
    }

    public Boolean getSubTotRequired() {
        return subTotRequired;
    }

    public void setSubTotRequired(Boolean subTotRequired) {
        this.subTotRequired = subTotRequired;
    }

    public Boolean getAscending() {
        return ascending;
    }

    public void setAscending(Boolean ascending) {
        this.ascending = ascending;
    }

    public Integer getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(Integer sortOrder) {
        this.sortOrder = sortOrder;
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

    public Boolean getGroupByCol() {
        return groupByCol;
    }

    public void setGroupByCol(Boolean groupByCol) {
        this.groupByCol = groupByCol;
    }

    public ReportsSortDto(){}

    public ReportsSortDto(Long savedSchedSortId){
        this.savedSchedSortId = savedSchedSortId;
    }
}
