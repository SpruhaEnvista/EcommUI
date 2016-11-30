package com.envista.msi.api.domain.freight;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the SHP_NSP_TASK_TB database table.
 * 
 */
@Entity
@Table(name="SHP_NSP_TASK_TB")
@NamedQuery(name="ShpNspTaskTb.findAll", query="SELECT s FROM ShpNspTaskTb s")
public class ShpNspTaskTb implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="NSP_TASK_ID", unique=true, precision=22)
	private long nspTaskId;

	@Column(name="ASSIGNED_BY", precision=22)
	private BigDecimal assignedBy;

	@Temporal(TemporalType.DATE)
	@Column(name="ASSIGNED_DATE")
	private Date assignedDate;

	@Column(name="ASSIGNED_TO", precision=22)
	private BigDecimal assignedTo;

	@Column(name="INVOICE_FILTER_ID", precision=22)
	private BigDecimal invoiceFilterId;

	@Column(name="INVOICE_NUMBER", length=50)
	private String invoiceNumber;

	@Column(name="IS_ACTIVE", precision=22)
	private BigDecimal isActive;

	@Column(name="IS_CLOSED", precision=22)
	private BigDecimal isClosed;

	@Column(name="REASON_ID", precision=22)
	private BigDecimal reasonId;

	@Column(name="TASK_DESC", length=2500)
	private String taskDesc;

	//bi-directional many-to-one association to ShpCarrierTb
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="CARRIER_ID")
	private ShpCarrierTb shpCarrierTb;

	//bi-directional many-to-one association to ShpCustomerProfileTb
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="CUSTOMER_ID")
	private ShpCustomerProfileTb shpCustomerProfileTb;

	//bi-directional many-to-one association to ShpNspInvoiceDetailsTb
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="NSP_INVOICE_DETAILS_ID")
	private ShpNspInvoiceDetailsTb shpNspInvoiceDetailsTb;

	public ShpNspTaskTb() {
	}

	public long getNspTaskId() {
		return this.nspTaskId;
	}

	public void setNspTaskId(long nspTaskId) {
		this.nspTaskId = nspTaskId;
	}

	public BigDecimal getAssignedBy() {
		return this.assignedBy;
	}

	public void setAssignedBy(BigDecimal assignedBy) {
		this.assignedBy = assignedBy;
	}

	public Date getAssignedDate() {
		return this.assignedDate;
	}

	public void setAssignedDate(Date assignedDate) {
		this.assignedDate = assignedDate;
	}

	public BigDecimal getAssignedTo() {
		return this.assignedTo;
	}

	public void setAssignedTo(BigDecimal assignedTo) {
		this.assignedTo = assignedTo;
	}

	public BigDecimal getInvoiceFilterId() {
		return this.invoiceFilterId;
	}

	public void setInvoiceFilterId(BigDecimal invoiceFilterId) {
		this.invoiceFilterId = invoiceFilterId;
	}

	public String getInvoiceNumber() {
		return this.invoiceNumber;
	}

	public void setInvoiceNumber(String invoiceNumber) {
		this.invoiceNumber = invoiceNumber;
	}

	public BigDecimal getIsActive() {
		return this.isActive;
	}

	public void setIsActive(BigDecimal isActive) {
		this.isActive = isActive;
	}

	public BigDecimal getIsClosed() {
		return this.isClosed;
	}

	public void setIsClosed(BigDecimal isClosed) {
		this.isClosed = isClosed;
	}

	public BigDecimal getReasonId() {
		return this.reasonId;
	}

	public void setReasonId(BigDecimal reasonId) {
		this.reasonId = reasonId;
	}

	public String getTaskDesc() {
		return this.taskDesc;
	}

	public void setTaskDesc(String taskDesc) {
		this.taskDesc = taskDesc;
	}

	public ShpCarrierTb getShpCarrierTb() {
		return this.shpCarrierTb;
	}

	public void setShpCarrierTb(ShpCarrierTb shpCarrierTb) {
		this.shpCarrierTb = shpCarrierTb;
	}

	public ShpCustomerProfileTb getShpCustomerProfileTb() {
		return this.shpCustomerProfileTb;
	}

	public void setShpCustomerProfileTb(ShpCustomerProfileTb shpCustomerProfileTb) {
		this.shpCustomerProfileTb = shpCustomerProfileTb;
	}

	public ShpNspInvoiceDetailsTb getShpNspInvoiceDetailsTb() {
		return this.shpNspInvoiceDetailsTb;
	}

	public void setShpNspInvoiceDetailsTb(ShpNspInvoiceDetailsTb shpNspInvoiceDetailsTb) {
		this.shpNspInvoiceDetailsTb = shpNspInvoiceDetailsTb;
	}

}