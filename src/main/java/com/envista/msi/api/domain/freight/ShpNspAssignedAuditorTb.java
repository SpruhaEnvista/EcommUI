package com.envista.msi.api.domain.freight;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the SHP_NSP_ASSIGNED_AUDITOR_TB database table.
 * 
 */
@Entity
@Table(name="SHP_NSP_ASSIGNED_AUDITOR_TB")
@NamedQuery(name="ShpNspAssignedAuditorTb.findAll", query="SELECT s FROM ShpNspAssignedAuditorTb s")
public class ShpNspAssignedAuditorTb implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="NSP_ASSIGNED_AUDITOR_ID", unique=true, precision=22)
	private long nspAssignedAuditorId;

	@Column(name="ASSIGNED_TYPE", length=45)
	private String assignedType;

	@Temporal(TemporalType.DATE)
	@Column(name="CREATE_DATE")
	private Date createDate;

	@Column(name="CREATE_USER", length=100)
	private String createUser;

	@Column(name="CUSTOMER_ID", precision=22)
	private BigDecimal customerId;

	@Column(name="INVOICE_MODE", precision=22)
	private BigDecimal invoiceMode;

	@Temporal(TemporalType.DATE)
	@Column(name="LAST_UPDATE_DATE")
	private Date lastUpdateDate;

	@Column(name="LAST_UPDATE_USER", length=100)
	private String lastUpdateUser;

	@Column(name="USER_ID", precision=22)
	private BigDecimal userId;

	public ShpNspAssignedAuditorTb() {
	}

	public long getNspAssignedAuditorId() {
		return this.nspAssignedAuditorId;
	}

	public void setNspAssignedAuditorId(long nspAssignedAuditorId) {
		this.nspAssignedAuditorId = nspAssignedAuditorId;
	}

	public String getAssignedType() {
		return this.assignedType;
	}

	public void setAssignedType(String assignedType) {
		this.assignedType = assignedType;
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

	public BigDecimal getInvoiceMode() {
		return this.invoiceMode;
	}

	public void setInvoiceMode(BigDecimal invoiceMode) {
		this.invoiceMode = invoiceMode;
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

	public BigDecimal getUserId() {
		return this.userId;
	}

	public void setUserId(BigDecimal userId) {
		this.userId = userId;
	}

}