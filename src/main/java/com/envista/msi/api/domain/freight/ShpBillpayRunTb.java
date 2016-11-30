package com.envista.msi.api.domain.freight;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the SHP_BILLPAY_RUN_TB database table.
 * 
 */
@Entity
@Table(name="SHP_BILLPAY_RUN_TB")
@NamedQuery(name="ShpBillpayRunTb.findAll", query="SELECT s FROM ShpBillpayRunTb s")
public class ShpBillpayRunTb implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="BILLPAY_RUN_ID", unique=true, precision=22)
	private long billpayRunId;

	@Column(length=1000)
	private String comments;

	@Temporal(TemporalType.DATE)
	@Column(name="CREATE_DATE")
	private Date createDate;

	@Column(name="CREATE_USER", length=20)
	private String createUser;

	@Column(name="CUSTOMER_ID", precision=22)
	private BigDecimal customerId;

	@Column(name="FUNDS_RECEIVED_AMOUNT", precision=22)
	private BigDecimal fundsReceivedAmount;

	@Temporal(TemporalType.DATE)
	@Column(name="FUNDS_RECEIVED_DATE")
	private Date fundsReceivedDate;

	@Temporal(TemporalType.DATE)
	@Column(name="RUN_DATE")
	private Date runDate;

	@Column(name="RUN_NUMBER", length=20)
	private String runNumber;

	@Temporal(TemporalType.DATE)
	@Column(name="UPDATE_DATE")
	private Date updateDate;

	@Column(name="UPDATE_USER", length=20)
	private String updateUser;

	public ShpBillpayRunTb() {
	}

	public long getBillpayRunId() {
		return this.billpayRunId;
	}

	public void setBillpayRunId(long billpayRunId) {
		this.billpayRunId = billpayRunId;
	}

	public String getComments() {
		return this.comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public Date getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getCreateUser() {
		return this.createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	public BigDecimal getCustomerId() {
		return this.customerId;
	}

	public void setCustomerId(BigDecimal customerId) {
		this.customerId = customerId;
	}

	public BigDecimal getFundsReceivedAmount() {
		return this.fundsReceivedAmount;
	}

	public void setFundsReceivedAmount(BigDecimal fundsReceivedAmount) {
		this.fundsReceivedAmount = fundsReceivedAmount;
	}

	public Date getFundsReceivedDate() {
		return this.fundsReceivedDate;
	}

	public void setFundsReceivedDate(Date fundsReceivedDate) {
		this.fundsReceivedDate = fundsReceivedDate;
	}

	public Date getRunDate() {
		return this.runDate;
	}

	public void setRunDate(Date runDate) {
		this.runDate = runDate;
	}

	public String getRunNumber() {
		return this.runNumber;
	}

	public void setRunNumber(String runNumber) {
		this.runNumber = runNumber;
	}

	public Date getUpdateDate() {
		return this.updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public String getUpdateUser() {
		return this.updateUser;
	}

	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}

}