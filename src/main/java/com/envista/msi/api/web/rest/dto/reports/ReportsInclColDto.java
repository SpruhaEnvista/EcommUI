package com.envista.msi.api.web.rest.dto.reports;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Sreenivas on 4/6/2017.
 */
@NamedStoredProcedureQueries({
        @NamedStoredProcedureQuery(name="ReportInclCol.insertRecord" , procedureName = "shp_rpt_savesched_colincl_proc",
                resultSetMappings = "updatedInclCol",
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.REF_CURSOR , name="p_savedSchedColInclCur", type= Void.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name="savedSchedRptId", type= Long.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name="rptDetailsId", type = Long.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name="createUser", type = String.class)

                })
})

@SqlResultSetMappings({
        @SqlResultSetMapping(name = "updatedInclCol", classes = {
                @ConstructorResult(
                        targetClass = ReportsInclColDto.class,
                        columns = {
                                @ColumnResult(name = "savedSchedColInclIdU", type = Long.class)
                        }
                )
        })
})
@Entity
public class ReportsInclColDto {

    @Id
    @Column(name = "saved_sched_col_incl_id")
    private long savedSchedSortId;

    @Column(name = "saved_sched_rpt_id")
    private long savedSchedRptId;

    @Column(name = "rpt_details_id")
    private long rptDetailsId;

    @Column(name = "create_user")
    private String createUser;

    @Column(name = "create_date")
    private Date createDate;

    @Column(name = "last_update_user")
    private String lastUpdateUser;

    @Column(name = "last_update_date")
    private Date lastUpdateDate;

    @Column(name="savedSchedColInclIdU")
    private Long savedSchedColInclIdU;

    public ReportsInclColDto(){}

    public ReportsInclColDto(Long savedSchedColInclIdU){
        this.savedSchedColInclIdU = savedSchedColInclIdU;
    }

    public Long getSavedSchedColInclIdU() {
        return savedSchedColInclIdU;
    }

    public void setSavedSchedColInclIdU(Long savedSchedColInclIdU) {
        this.savedSchedColInclIdU = savedSchedColInclIdU;
    }

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

