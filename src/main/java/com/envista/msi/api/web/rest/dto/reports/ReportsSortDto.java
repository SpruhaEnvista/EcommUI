package com.envista.msi.api.web.rest.dto.reports;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

/**
 * Created by Sreenivas on 4/6/2017.
 */

@Entity
public class ReportsSortDto {

    @Id
    @Column(name = "saved_sched_sort_id")
    private long savedSchedSortId;

    @Column(name = "saved_sched_rpt_id")
    private long savedSchedRptId;

    @Column(name = "sort_by_column")
    private long rptDetailsId;

    @Column(name = "is_sub_tot_required")
    private boolean subTotRequired;

    @Column(name = "is_Ascending")
    private boolean ascending;

    @Column(name = "sort_order")
    private int sortOrder;

    @Column(name = "create_user")
    private String createUser;

    @Column(name = "create_date")
    private Date createDate;

    @Column(name = "last_update_user")
    private String lastUpdateUser;

    @Column(name = "last_update_date")
    private Date lastUpdateDate;

    @Column(name = "group_by_col")
    private boolean groupByCol;

    public long getSavedSchedSortId() {
        return savedSchedSortId;
    }

    public void setSavedSchedSortId(long savedSchedSortId) {
        this.savedSchedSortId = savedSchedSortId;
    }

    public long getSavedSchedRptId() {
        return savedSchedRptId;
    }

    public void setSavedSchedRptId(long savedSchedRptId) {
        this.savedSchedRptId = savedSchedRptId;
    }

    public long getRptDetailsId() {
        return rptDetailsId;
    }

    public void setRptDetailsId(long rptDetailsId) {
        this.rptDetailsId = rptDetailsId;
    }

    public boolean isSubTotRequired() {
        return subTotRequired;
    }

    public void setSubTotRequired(boolean subTotRequired) {
        this.subTotRequired = subTotRequired;
    }

    public boolean isAscending() {
        return ascending;
    }

    public void setAscending(boolean ascending) {
        this.ascending = ascending;
    }

    public int getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(int sortOrder) {
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

    public boolean isGroupByCol() {
        return groupByCol;
    }

    public void setGroupByCol(boolean groupByCol) {
        this.groupByCol = groupByCol;
    }
}
