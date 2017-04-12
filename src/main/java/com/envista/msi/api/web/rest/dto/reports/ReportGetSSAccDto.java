package com.envista.msi.api.web.rest.dto.reports;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by user1 on 4/8/2017.
 */
@NamedStoredProcedureQueries({
        @NamedStoredProcedureQuery(name = "ReportGetSSAcc.getReportAccList", procedureName = "shp_get_rpt_ss_accts_proc",
                resultClasses = ReportGetSSAccDto.class,
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, name = "p_savedSchedAcctsCur", type = Void.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "savedSchedRptId", type = Long.class)
                })
})

@Entity
public class ReportGetSSAccDto {

    @Id
    @Column(name = "saved_sched_account_id")
    private Long ssAccId;

    @Column(name = "saved_sched_rpt_id")
    private Long savedSchdRptId;

    @Column(name="customer_id")
    private Long customerId;

    @Column(name="shipper_group_id")
    private Long shipperGroupId;

    @Column(name="shipper_id")
    private Long shipperId;

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

    public Long getSsAccId() {
        return ssAccId;
    }

    public void setSsAccId(Long ssAccId) {
        this.ssAccId = ssAccId;
    }

    public Long getSavedSchdRptId() {
        return savedSchdRptId;
    }

    public void setSavedSchdRptId(Long savedSchdRptId) {
        this.savedSchdRptId = savedSchdRptId;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Long getShipperGroupId() {
        return shipperGroupId;
    }

    public void setShipperGroupId(Long shipperGroupId) {
        this.shipperGroupId = shipperGroupId;
    }

    public Long getShipperId() {
        return shipperId;
    }

    public void setShipperId(Long shipperId) {
        this.shipperId = shipperId;
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
