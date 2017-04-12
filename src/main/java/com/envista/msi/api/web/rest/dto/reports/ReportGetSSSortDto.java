package com.envista.msi.api.web.rest.dto.reports;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by user1 on 4/8/2017.
 */
@NamedStoredProcedureQueries({
        @NamedStoredProcedureQuery(name = "ReportGetSSSort.getReportSortList", procedureName = "shp_get_rpt_ss_sort_proc",
                resultClasses = ReportGetSSSortDto.class,
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, name = "p_savedSchedSortCur", type = Void.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "savedSchedRptId", type = Long.class)
                })
})

@Entity
public class ReportGetSSSortDto {

    @Id
    @Column(name = "saved_sched_users_id")
    private Long ssUserId;

    @Column(name = "saved_sched_rpt_id")
    private Long savedSchdRptId;

    @Column(name="sort_by_column")
    private Long sortByColumn;

    @Column(name="is_sub_tot_required")
    private Boolean isSubTotRequired;

    @Column(name="is_ascending")
    private Boolean isAscending;

    @Column(name="sort_order")
    private Long sortOrder;

    @Column(name="create_user")
    private String createUser;

    @Column(name="create_date")
    @Temporal(TemporalType.DATE)
    @JsonFormat(pattern="MM/dd/yyyy hh:mm a ")
    private Date createDate;

    @Column(name="last_update_user")
    private String lastUpdateUser;

    @Column(name ="last_update_date")
    @Temporal(TemporalType.DATE)
    @JsonFormat(pattern="MM/dd/yyyy hh:mm a ")
    private Date lastUpdateDate;

    @Column(name="group_by_col")
    private Long groupByCol;

    public Long getSsUserId() {
        return ssUserId;
    }

    public void setSsUserId(Long ssUserId) {
        this.ssUserId = ssUserId;
    }

    public Long getSavedSchdRptId() {
        return savedSchdRptId;
    }

    public void setSavedSchdRptId(Long savedSchdRptId) {
        this.savedSchdRptId = savedSchdRptId;
    }

    public Long getSortByColumn() {
        return sortByColumn;
    }

    public void setSortByColumn(Long sortByColumn) {
        this.sortByColumn = sortByColumn;
    }

    public Boolean getSubTotRequired() {
        return isSubTotRequired;
    }

    public void setSubTotRequired(Boolean subTotRequired) {
        isSubTotRequired = subTotRequired;
    }

    public Boolean getAscending() {
        return isAscending;
    }

    public void setAscending(Boolean ascending) {
        isAscending = ascending;
    }

    public Long getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(Long sortOrder) {
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

    public Long getGroupByCol() {
        return groupByCol;
    }

    public void setGroupByCol(Long groupByCol) {
        this.groupByCol = groupByCol;
    }
}
