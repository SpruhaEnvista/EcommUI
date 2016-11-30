package com.envista.msi.api.domain.freight;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the SHP_NSP_STANDARD_FEE_TB database table.
 * 
 */
@Entity
@Table(name="SHP_NSP_STANDARD_FEE_TB")
@NamedQuery(name="ShpNspStandardFeeTb.findAll", query="SELECT s FROM ShpNspStandardFeeTb s")
public class ShpNspStandardFeeTb implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="NSP_STANDARD_FEE_ID", unique=true, precision=22)
	private long nspStandardFeeId;

	@Column(precision=22)
	private BigDecimal amount;

	@Column(length=200)
	private String comments;

	@Temporal(TemporalType.DATE)
	@Column(name="CREATE_DATE")
	private Date createDate;

	@Column(name="CREATE_USER", length=50)
	private String createUser;

	@Temporal(TemporalType.DATE)
	@Column(name="EXPIRY_DATE")
	private Date expiryDate;

	@Column(name="GL_ACCOUNT_CODE", length=100)
	private String glAccountCode;

	@Column(name="IS_ACTIVE", precision=22)
	private BigDecimal isActive;

	@Temporal(TemporalType.DATE)
	@Column(name="LAST_UPDATE_DATE")
	private Date lastUpdateDate;

	@Column(name="LAST_UPDATE_USER", length=50)
	private String lastUpdateUser;

	//bi-directional many-to-one association to ShpCustomerProfileTb
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="CUSTOMER_ID")
	private ShpCustomerProfileTb shpCustomerProfileTb;

	//bi-directional many-to-one association to ShpNspCodeValuesTb
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="MSI_FEE_ID")
	private ShpNspCodeValuesTb shpNspCodeValuesTb;

	public ShpNspStandardFeeTb() {
	}

	public long getNspStandardFeeId() {
		return this.nspStandardFeeId;
	}

	public void setNspStandardFeeId(long nspStandardFeeId) {
		this.nspStandardFeeId = nspStandardFeeId;
	}

	public BigDecimal getAmount() {
		return this.amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
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

	public Date getExpiryDate() {
		return this.expiryDate;
	}

	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}

	public String getGlAccountCode() {
		return this.glAccountCode;
	}

	public void setGlAccountCode(String glAccountCode) {
		this.glAccountCode = glAccountCode;
	}

	public BigDecimal getIsActive() {
		return this.isActive;
	}

	public void setIsActive(BigDecimal isActive) {
		this.isActive = isActive;
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

	public ShpCustomerProfileTb getShpCustomerProfileTb() {
		return this.shpCustomerProfileTb;
	}

	public void setShpCustomerProfileTb(ShpCustomerProfileTb shpCustomerProfileTb) {
		this.shpCustomerProfileTb = shpCustomerProfileTb;
	}

	public ShpNspCodeValuesTb getShpNspCodeValuesTb() {
		return this.shpNspCodeValuesTb;
	}

	public void setShpNspCodeValuesTb(ShpNspCodeValuesTb shpNspCodeValuesTb) {
		this.shpNspCodeValuesTb = shpNspCodeValuesTb;
	}

}