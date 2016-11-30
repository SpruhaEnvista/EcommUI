package com.envista.msi.api.domain.freight;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the SHP_NSP_SCANNED_INVOICE_TB database table.
 * 
 */
@Entity
@Table(name="SHP_NSP_SCANNED_INVOICE_TB")
@NamedQuery(name="ShpNspScannedInvoiceTb.findAll", query="SELECT s FROM ShpNspScannedInvoiceTb s")
public class ShpNspScannedInvoiceTb implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="NSP_SCANNED_INVOICE_ID", unique=true, precision=22)
	private long nspScannedInvoiceId;

	@Column(name="BULK_INV_COUNT", precision=22)
	private BigDecimal bulkInvCount;

	@Column(name="CARRIER_ID", precision=22)
	private BigDecimal carrierId;

	@Column(name="CONSOL_PARENT_ID", precision=22)
	private BigDecimal consolParentId;

	@Temporal(TemporalType.DATE)
	@Column(name="CREATE_DATE")
	private Date createDate;

	@Column(name="CREATE_USER", length=100)
	private String createUser;

	@Temporal(TemporalType.DATE)
	@Column(name="DE_ASSIGNED_DATE")
	private Date deAssignedDate;

	@Column(name="DE_ASSIGNED_TO", length=100)
	private String deAssignedTo;

	@Column(name="DE_REASON_TEXT", length=100)
	private String deReasonText;

	@Column(name="INV_PAGE_COUNT", precision=22)
	private BigDecimal invPageCount;

	@Column(name="INVOICE_FILE_NAME", length=100)
	private String invoiceFileName;

	@Column(name="INVOICE_NUMBER", length=100)
	private String invoiceNumber;

	@Column(name="IS_ODE", precision=1)
	private BigDecimal isOde;

	@Temporal(TemporalType.DATE)
	@Column(name="LAST_UPDATE_DATE")
	private Date lastUpdateDate;

	@Column(name="LAST_UPDATE_USER", length=100)
	private String lastUpdateUser;

	@Column(name="LINKED_DOCUMENTS", length=4000)
	private String linkedDocuments;

	@Column(name="LOADED_MODE", precision=22)
	private BigDecimal loadedMode;

	@Temporal(TemporalType.DATE)
	@Column(name="QA_ASSIGNED_DATE")
	private Date qaAssignedDate;

	@Column(name="QA_ASSIGNED_TO", length=100)
	private String qaAssignedTo;

	@Column(name="REASON_ID", precision=22)
	private BigDecimal reasonId;

	@Temporal(TemporalType.DATE)
	@Column(name="RECEIVED_DATE")
	private Date receivedDate;

	@Column(name="TEMPLATE_ID", precision=22)
	private BigDecimal templateId;

	//bi-directional many-to-one association to ShpCustomerProfileTb
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="CUSTOMER_ID")
	private ShpCustomerProfileTb shpCustomerProfileTb;

	//bi-directional many-to-one association to ShpNspCodeValuesTb
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="INVOICE_STATUS")
	private ShpNspCodeValuesTb shpNspCodeValuesTb;

	public ShpNspScannedInvoiceTb() {
	}

	public long getNspScannedInvoiceId() {
		return this.nspScannedInvoiceId;
	}

	public void setNspScannedInvoiceId(long nspScannedInvoiceId) {
		this.nspScannedInvoiceId = nspScannedInvoiceId;
	}

	public BigDecimal getBulkInvCount() {
		return this.bulkInvCount;
	}

	public void setBulkInvCount(BigDecimal bulkInvCount) {
		this.bulkInvCount = bulkInvCount;
	}

	public BigDecimal getCarrierId() {
		return this.carrierId;
	}

	public void setCarrierId(BigDecimal carrierId) {
		this.carrierId = carrierId;
	}

	public BigDecimal getConsolParentId() {
		return this.consolParentId;
	}

	public void setConsolParentId(BigDecimal consolParentId) {
		this.consolParentId = consolParentId;
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

	public Date getDeAssignedDate() {
		return this.deAssignedDate;
	}

	public void setDeAssignedDate(Date deAssignedDate) {
		this.deAssignedDate = deAssignedDate;
	}

	public String getDeAssignedTo() {
		return this.deAssignedTo;
	}

	public void setDeAssignedTo(String deAssignedTo) {
		this.deAssignedTo = deAssignedTo;
	}

	public String getDeReasonText() {
		return this.deReasonText;
	}

	public void setDeReasonText(String deReasonText) {
		this.deReasonText = deReasonText;
	}

	public BigDecimal getInvPageCount() {
		return this.invPageCount;
	}

	public void setInvPageCount(BigDecimal invPageCount) {
		this.invPageCount = invPageCount;
	}

	public String getInvoiceFileName() {
		return this.invoiceFileName;
	}

	public void setInvoiceFileName(String invoiceFileName) {
		this.invoiceFileName = invoiceFileName;
	}

	public String getInvoiceNumber() {
		return this.invoiceNumber;
	}

	public void setInvoiceNumber(String invoiceNumber) {
		this.invoiceNumber = invoiceNumber;
	}

	public BigDecimal getIsOde() {
		return this.isOde;
	}

	public void setIsOde(BigDecimal isOde) {
		this.isOde = isOde;
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

	public String getLinkedDocuments() {
		return this.linkedDocuments;
	}

	public void setLinkedDocuments(String linkedDocuments) {
		this.linkedDocuments = linkedDocuments;
	}

	public BigDecimal getLoadedMode() {
		return this.loadedMode;
	}

	public void setLoadedMode(BigDecimal loadedMode) {
		this.loadedMode = loadedMode;
	}

	public Date getQaAssignedDate() {
		return this.qaAssignedDate;
	}

	public void setQaAssignedDate(Date qaAssignedDate) {
		this.qaAssignedDate = qaAssignedDate;
	}

	public String getQaAssignedTo() {
		return this.qaAssignedTo;
	}

	public void setQaAssignedTo(String qaAssignedTo) {
		this.qaAssignedTo = qaAssignedTo;
	}

	public BigDecimal getReasonId() {
		return this.reasonId;
	}

	public void setReasonId(BigDecimal reasonId) {
		this.reasonId = reasonId;
	}

	public Date getReceivedDate() {
		return this.receivedDate;
	}

	public void setReceivedDate(Date receivedDate) {
		this.receivedDate = receivedDate;
	}

	public BigDecimal getTemplateId() {
		return this.templateId;
	}

	public void setTemplateId(BigDecimal templateId) {
		this.templateId = templateId;
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