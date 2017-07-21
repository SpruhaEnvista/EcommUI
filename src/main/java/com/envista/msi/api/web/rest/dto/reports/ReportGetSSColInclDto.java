package com.envista.msi.api.web.rest.dto.reports;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by user1 on 4/8/2017.
 */
@NamedStoredProcedureQueries({
        @NamedStoredProcedureQuery(name = "ReportGetSSColIncl.getReportColInclList", procedureName = "shp_get_rpt_ss_colincl_proc",
                resultClasses = ReportGetSSColInclDto.class,
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, name = "p_savedSchedColInclCur", type = Void.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "savedSchdRptId", type = Long.class)
                })
})

@Entity
public class ReportGetSSColInclDto {

    @Id
    @Column(name = "saved_sched_col_incl_id")
    private Long ssColInclId;

    @Column(name = "saved_sched_rpt_id")
    private Long savedSchdRptId;

    @Column(name="rpt_details_id")
    private Long rptDetailsId;

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


    public Long getSsColInclId() {
        return ssColInclId;
    }

    public void setSsColInclId(Long ssColInclId) {
        this.ssColInclId = ssColInclId;
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
