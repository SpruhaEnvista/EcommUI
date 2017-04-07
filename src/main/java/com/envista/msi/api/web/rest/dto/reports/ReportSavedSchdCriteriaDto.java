package com.envista.msi.api.web.rest.dto.reports;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

/**
 * Created by SreedharT on 4/6/2017.
 */
@Entity
public class ReportSavedSchdCriteriaDto {

    @Id
    @Column(name="saved_sched_criteria_id")
    private Long savedSchdCriteriaId;

    @Column(name="saved_sched_rpt_id")
    private Long savedSchdRptId;

    @Column(name="rpt_details_id")
    private Long rptDetailsId;

    @Column(name="assign_operator")
    private String assignOperator;

    @Column(name="value")
    private String value;

    @Column(name="is_match_case")
    private Long isMatchCase;

    @Column(name="create_user")
    private String createUser;

    @Column(name="create_date")
    private Date creatDate;

    @Column(name="last_update_user")
    private String lastUpdateUser;

    @Column(name="last_update_date")
    private Date lastUpdateDate;

    @Column(name="and_or_operator")
    private String andOrOperator;

    public Long getSavedSchdCriteriaId() {
        return savedSchdCriteriaId;
    }

    public void setSavedSchdCriteriaId(Long savedSchdCriteriaId) {
        this.savedSchdCriteriaId = savedSchdCriteriaId;
    }

    public Long getSavedSchdRptId() {
        return savedSchdRptId;
    }

    public void setSavedSchdRptId(Long savedSchdRptId) {
        this.savedSchdRptId = savedSchdRptId;
    }

    public Long getRptDetailsId() {
        return rptDetailsId;
    }

    public void setRptDetailsId(Long rptDetailsId) {
        this.rptDetailsId = rptDetailsId;
    }

    public String getAssignOperator() {
        return assignOperator;
    }

    public void setAssignOperator(String assignOperator) {
        this.assignOperator = assignOperator;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Long getIsMatchCase() {
        return isMatchCase;
    }

    public void setIsMatchCase(Long isMatchCase) {
        this.isMatchCase = isMatchCase;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public Date getCreatDate() {
        return creatDate;
    }

    public void setCreatDate(Date creatDate) {
        this.creatDate = creatDate;
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

    public String getAndOrOperator() {
        return andOrOperator;
    }

    public void setAndOrOperator(String andOrOperator) {
        this.andOrOperator = andOrOperator;
    }
}
