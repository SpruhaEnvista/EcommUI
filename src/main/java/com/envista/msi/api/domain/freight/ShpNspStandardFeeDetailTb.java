package com.envista.msi.api.domain.freight;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the SHP_NSP_STANDARD_FEE_DETAIL_TB database table.
 * 
 */
@Entity
@Table(name="SHP_NSP_STANDARD_FEE_DETAIL_TB")
@NamedQuery(name="ShpNspStandardFeeDetailTb.findAll", query="SELECT s FROM ShpNspStandardFeeDetailTb s")
public class ShpNspStandardFeeDetailTb implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="NSP_STANDARD_FEE_DETAIL_ID", unique=true, precision=22)
	private long nspStandardFeeDetailId;

	@Column(name="CHECK_AMOUNT", precision=22)
	private BigDecimal checkAmount;

	@Temporal(TemporalType.DATE)
	@Column(name="CHECK_DATE")
	private Date checkDate;

	@Column(name="CHECK_NUMBER", length=20)
	private String checkNumber;

	@Temporal(TemporalType.DATE)
	@Column(name="CREATE_DATE")
	private Date createDate;

	@Column(name="CREATE_USER", length=100)
	private String createUser;

	@Column(name="CUSTOMER_ID", precision=22)
	private BigDecimal customerId;

	@Temporal(TemporalType.DATE)
	@Column(name="LAST_UPDATE_DATE")
	private Date lastUpdateDate;

	@Column(name="LAST_UPDATE_USER", length=100)
	private String lastUpdateUser;

	@Column(name="RUN_NO", length=11)
	private String runNo;

	public ShpNspStandardFeeDetailTb() {
	}

	public long getNspStandardFeeDetailId() {
		return this.nspStandardFeeDetailId;
	}

	public void setNspStandardFeeDetailId(long nspStandardFeeDetailId) {
		this.nspStandardFeeDetailId = nspStandardFeeDetailId;
	}

	public BigDecimal getCheckAmount() {
		return this.checkAmount;
	}

	public void setCheckAmount(BigDecimal checkAmount) {
		this.checkAmount = checkAmount;
	}

	public Date getCheckDate() {
		return this.checkDate;
	}

	public void setCheckDate(Date checkDate) {
		this.checkDate = checkDate;
	}

	public String getCheckNumber() {
		return this.checkNumber;
	}

	public void setCheckNumber(String checkNumber) {
		this.checkNumber = checkNumber;
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

	public Date getLastUpdateDate() {
		return this.lastUpdateDate;
	}

	public void setLastUpdateDate(Date lastUpdateDate) {
		this.lastUpdateDate = lastUpdateDate;
	}

	public String getLastUpdateUser() {
		return this.lastUpdateUser;
	}

	public void setLastUpdateUser(String lastUpdateUser) {
		this.lastUpdateUser = lastUpdateUser;
	}

	public String getRunNo() {
		return this.runNo;
	}

	public void setRunNo(String runNo) {
		this.runNo = runNo;
	}

}