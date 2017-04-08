package com.envista.msi.api.web.rest.dto.reports;

import javax.persistence.*;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

/**
 * Created by Sreenivas on 4/6/2017.
 */
@NamedStoredProcedureQueries({
        @NamedStoredProcedureQuery(name="ReportInclCol.insertRecord" , procedureName = "shp_rpt_savesched_colincl_proc",
                resultSetMappings = "updatedInclCol",
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.REF_CURSOR , name="p_savedSchedColInclCur", type= Void.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name="savedSchdRptId", type= Long.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name="rptDetailsId", type = Long.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name="createUser", type = String.class)

                })
})

@SqlResultSetMappings({
        @SqlResultSetMapping(name = "updatedInclCol", classes = {
                @ConstructorResult(
                        targetClass = ReportsInclColDto.class,
                        columns = {
                                @ColumnResult(name = "saved_sched_col_incl_id", type = Long.class)
                        }
                )
        })
})
@Entity
public class ReportsInclColDto {

    @Id
    @Column(name = "saved_sched_col_incl_id")
    private Long savedSchedColInclId;

    @Column(name = "saved_sched_rpt_id")
    private Long savedSchdRptId;

    @Column(name = "rpt_details_id")
    private Long rptDetailsId;

    @Column(name = "create_user")
    private String createUser;

    @Column(name = "create_date")
    private Date createDate;

    @Column(name = "last_update_user")
    private String lastUpdateUser;

    @Column(name = "last_update_date")
    private Date lastUpdateDate;

    public ReportsInclColDto(){}

    public ReportsInclColDto(Long savedSchedColInclId){
        this.savedSchedColInclId = savedSchedColInclId;
    }

    public Long getSavedSchedColInclId() {
        return savedSchedColInclId;
    }

    public void setSavedSchedColInclId(Long savedSchedColInclId) {
        this.savedSchedColInclId = savedSchedColInclId;
    }

    public Long getSavedSchdRptId() {
        return savedSchdRptId;
    }

    public void setSavedSchedRptId(Long savedSchdRptId) {
        this.savedSchdRptId = savedSchdRptId;
    }

    public Long getRptDetailsId() {
        return rptDetailsId;
    }

    public void setRptDetailsId(Long rptDetailsId) {
        this.rptDetailsId = rptDetailsId;
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

