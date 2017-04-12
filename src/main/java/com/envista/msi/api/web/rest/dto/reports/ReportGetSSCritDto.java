package com.envista.msi.api.web.rest.dto.reports;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by user1 on 4/8/2017.
 */
@NamedStoredProcedureQueries({
        @NamedStoredProcedureQuery(name = "ReportGetSSCirt.getReportCritList", procedureName = "shp_get_rpt_ss_crit_proc",
                resultClasses = ReportGetSSCritDto.class,
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, name = "p_savedSchedCritCur", type = Void.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "savedSchedRptId", type = Long.class)
                })
})

@Entity
public class ReportGetSSCritDto {

    @Id
    @Column(name = "saved_sched_criteria_id")
    private Long ssCriteriaId;

    @Column(name = "saved_sched_rpt_id")
    private Long savedSchdRptId;

    @Column(name="rpt_details_id")
    private Long rptDetailsId;

    @Column(name="assign_operator")
    private String assignOperator;

    @Column(name="value")
    private String Value;

    @Column(name="is_match_case")
    private Boolean isMatchCase;

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

    @Column(name="and_or_operator")
    private String andOrOperator;

    public Long getSsCriteriaId() {
        return ssCriteriaId;
    }

    public void setSsCriteriaId(Long ssCriteriaId) {
        this.ssCriteriaId = ssCriteriaId;
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
        return Value;
    }

    public void setValue(String value) {
        Value = value;
    }

    public Boolean getMatchCase() {
        return isMatchCase;
    }

    public void setMatchCase(Boolean matchCase) {
        isMatchCase = matchCase;
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

    public String getAndOrOperator() {
        return andOrOperator;
    }

    public void setAndOrOperator(String andOrOperator) {
        this.andOrOperator = andOrOperator;
    }
}
