package com.envista.msi.api.web.rest.dto.reports;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by user1 on 4/6/2017.
 */
@NamedStoredProcedureQueries({
        @NamedStoredProcedureQuery(name="ReportSavedSchdAcc.insertRecord" , procedureName = "shp_rpt_savesched_accts_proc",
                resultSetMappings = "updatedSSA",
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.REF_CURSOR , name="p_savedSchedAcctsCur", type= Void.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name="savedSchedRptId", type= Long.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name="customerId", type = Long.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name="shipperGroupId", type = Long.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "shipperId", type = Long.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "createUser", type = String.class)

                })
})

@SqlResultSetMappings({
        @SqlResultSetMapping(name = "updatedSSA", classes = {
                @ConstructorResult(
                        targetClass = ReportsSavedSchdAccountDto.class,
                        columns = {
                                @ColumnResult(name = "saved_sched_account_id", type = Long.class)
                        }
                )
        })
})
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

    private Long savedSchedAcctsId;

    public ReportsSavedSchdAccountDto(){}

    public ReportsSavedSchdAccountDto(Long savedSchedAcctsId){
        this.savedSchedAcctsId = savedSchedAcctsId;
    }

    public Long getSavedSchedAcctsId() {
        return savedSchedAcctsId;
    }

    public void setSavedSchedAcctsId(Long savedSchedAcctsId) {
        this.savedSchedAcctsId = savedSchedAcctsId;
    }

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
