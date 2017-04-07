package com.envista.msi.api.web.rest.dto.reports;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by SreedharT on 4/6/2017.
 */
@Entity
public class ReportsSavedSchdAccountDto {

    @Id
    @Column(name="saved_sched_account_id")
    private Long savedSchdAccountId;

    @Column(name="saved_sched_rpt_id")
    private Long savedSchdRptId;

    @Column(name="customer_id")
    private Long customerId;

    @Column(name="shipper_group_id")
    private Long shipperGroupId;

    @Column(name="shipper_id")
    private Long shipperId;

    @Column(name="create_user")
    private String createUser;

    @Temporal(TemporalType.DATE)
    @Column(name="create_date")
    private Date createDate;

    @Column(name="last_update_user")
    private String lastUpdateUser;

    @Temporal(TemporalType.DATE)
    @Column(name="last_update_date")
    private Date lastUpdateDate;

    public Long getSavedSchdAccountId() {
        return savedSchdAccountId;
    }

    public void setSavedSchdAccountId(Long savedSchdAccountId) {
        this.savedSchdAccountId = savedSchdAccountId;
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
