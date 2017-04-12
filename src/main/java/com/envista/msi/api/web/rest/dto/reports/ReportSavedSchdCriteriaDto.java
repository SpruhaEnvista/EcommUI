package com.envista.msi.api.web.rest.dto.reports;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by user1 on 4/6/2017.
 */
@NamedStoredProcedureQueries({
            @NamedStoredProcedureQuery(name="ReportSavedSchdCrit.insertRecord" , procedureName = "shp_rpt_savesched_crit_proc",
                    resultSetMappings = "updatedRssc",
                    parameters = {
                            @StoredProcedureParameter(mode = ParameterMode.REF_CURSOR , name="p_savedSchedCritCur", type= Void.class),
                            @StoredProcedureParameter(mode = ParameterMode.IN, name="savedSchedRptId", type= Long.class),
                            @StoredProcedureParameter(mode = ParameterMode.IN, name="rptDetailsId", type = Long.class),
                            @StoredProcedureParameter(mode = ParameterMode.IN, name="assignOperator", type = String.class),
                            @StoredProcedureParameter(mode = ParameterMode.IN, name="value", type=String.class),
                            @StoredProcedureParameter(mode = ParameterMode.IN, name="matchCase", type = Boolean.class),
                            @StoredProcedureParameter(mode = ParameterMode.IN, name="createUser", type=String.class),
                            @StoredProcedureParameter(mode = ParameterMode.IN, name="andOrOperator", type=String.class)
                    })
})

@SqlResultSetMappings({
        @SqlResultSetMapping(name = "updatedRssc", classes = {
                @ConstructorResult(
                        targetClass = ReportSavedSchdCriteriaDto.class,
                        columns = {
                                @ColumnResult(name = "saved_sched_criteria_id", type = Long.class)
                        }
                )
        })
})
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
    private Boolean matchCase;

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

    private Long savedSchedCritId;

    public Long getSavedSchedCritId() {
        return savedSchedCritId;
    }

    public void setSavedSchedCritId(Long savedSchedCritId) {
        this.savedSchedCritId = savedSchedCritId;
    }

    public ReportSavedSchdCriteriaDto(){}

    public ReportSavedSchdCriteriaDto(Long savedSchedCritId){
        this.savedSchedCritId = savedSchedCritId;
    }

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

    public Boolean getMatchCase() {
        return matchCase;
    }

    public void setMatchCase(Boolean matchCase) {
        this.matchCase = matchCase;
    }

}
