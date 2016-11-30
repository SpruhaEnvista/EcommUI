package com.envista.msi.api.domain.freight;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the ARC_EBILL_INVOICE_TB database table.
 * 
 */
@Entity
@Table(name="ARC_EBILL_INVOICE_TB")
@NamedQuery(name="ArcEbillInvoiceTb.findAll", query="SELECT a FROM ArcEbillInvoiceTb a")
public class ArcEbillInvoiceTb implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="INVOICE_ID", unique=true, precision=22)
	private long invoiceId;

	@Column(name="APPROVED_AMOUNT", precision=22)
	private BigDecimal approvedAmount;

	@Temporal(TemporalType.DATE)
	@Column(name="AUDIT_FILE_CREATE_DATE")
	private Date auditFileCreateDate;

	@Column(name="BILLPAY_STATUS", length=50)
	private String billpayStatus;

	@Column(name="CHECK_AMOUNT", precision=22)
	private BigDecimal checkAmount;

	@Temporal(TemporalType.DATE)
	@Column(name="CHECK_DATE")
	private Date checkDate;

	@Column(name="CHECK_NUMBER", length=50)
	private String checkNumber;

	@Temporal(TemporalType.DATE)
	@Column(name="CLOSE_DATE")
	private Date closeDate;

	@Column(name="CONTROL_NUMBER", length=8)
	private String controlNumber;

	@Temporal(TemporalType.DATE)
	@Column(name="CREATE_DATE")
	private Date createDate;

	@Column(name="CREATE_USER", length=15)
	private String createUser;

	@Temporal(TemporalType.DATE)
	@Column(name="CUST_REMIT_DATE")
	private Date custRemitDate;

	@Temporal(TemporalType.DATE)
	@Column(name="DUE_DATE")
	private Date dueDate;

	@Temporal(TemporalType.DATE)
	@Column(name="FILE_CREATED")
	private Date fileCreated;

	@Column(length=10)
	private String filename;

	@Temporal(TemporalType.DATE)
	@Column(name="INV_BILL_DATE")
	private Date invBillDate;

	@Column(name="INV_CARRIER_ID", precision=22)
	private BigDecimal invCarrierId;

	@Column(name="INV_CONTRACT_NUMBER", length=20)
	private String invContractNumber;

	@Column(name="INVOICE_AMOUNT", precision=22)
	private BigDecimal invoiceAmount;

	@Column(name="INVOICE_NUMBER", length=50)
	private String invoiceNumber;

	@Column(name="INVOICE_TYPE", length=1)
	private String invoiceType;

	@Temporal(TemporalType.DATE)
	@Column(name="LAST_UPDATE_DATE")
	private Date lastUpdateDate;

	@Column(name="LAST_UPDATE_USER", length=15)
	private String lastUpdateUser;

	@Column(name="MASTER_EDI_NUMBER", length=9)
	private String masterEdiNumber;

	@Column(name="ORIG_INVOICE_AMOUNT", precision=22)
	private BigDecimal origInvoiceAmount;

	@Column(name="PAYMENT_TYPE", length=10)
	private String paymentType;

	@Temporal(TemporalType.DATE)
	@Column(name="REMIT_CREATE_DATE")
	private Date remitCreateDate;

	@Column(name="RUN_NO", length=20)
	private String runNo;

	@Column(name="SHIPPER_CODE", length=12)
	private String shipperCode;

	@Column(length=20)
	private String status;

	//bi-directional many-to-one association to ArcEbillManifestTb
	@OneToMany(mappedBy="arcEbillInvoiceTb")
	private List<ArcEbillManifestTb> arcEbillManifestTbs;

	public ArcEbillInvoiceTb() {
	}

	public long getInvoiceId() {
		return this.invoiceId;
	}

	public void setInvoiceId(long invoiceId) {
		this.invoiceId = invoiceId;
	}

	public BigDecimal getApprovedAmount() {
		return this.approvedAmount;
	}

	public void setApprovedAmount(BigDecimal approvedAmount) {
		this.approvedAmount = approvedAmount;
	}

	public Date getAuditFileCreateDate() {
		return this.auditFileCreateDate;
	}

	public void setAuditFileCreateDate(Date auditFileCreateDate) {
		this.auditFileCreateDate = auditFileCreateDate;
	}

	public String getBillpayStatus() {
		return this.billpayStatus;
	}

	public void setBillpayStatus(String billpayStatus) {
		this.billpayStatus = billpayStatus;
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

	public Date getCloseDate() {
		return this.closeDate;
	}

	public void setCloseDate(Date closeDate) {
		this.closeDate = closeDate;
	}

	public String getControlNumber() {
		return this.controlNumber;
	}

	public void setControlNumber(String controlNumber) {
		this.controlNumber = controlNumber;
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

	public Date getCustRemitDate() {
		return this.custRemitDate;
	}

	public void setCustRemitDate(Date custRemitDate) {
		this.custRemitDate = custRemitDate;
	}

	public Date getDueDate() {
		return this.dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	public Date getFileCreated() {
		return this.fileCreated;
	}

	public void setFileCreated(Date fileCreated) {
		this.fileCreated = fileCreated;
	}

	public String getFilename() {
		return this.filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public Date getInvBillDate() {
		return this.invBillDate;
	}

	public void setInvBillDate(Date invBillDate) {
		this.invBillDate = invBillDate;
	}

	public BigDecimal getInvCarrierId() {
		return this.invCarrierId;
	}

	public void setInvCarrierId(BigDecimal invCarrierId) {
		this.invCarrierId = invCarrierId;
	}

	public String getInvContractNumber() {
		return this.invContractNumber;
	}

	public void setInvContractNumber(String invContractNumber) {
		this.invContractNumber = invContractNumber;
	}

	public BigDecimal getInvoiceAmount() {
		return this.invoiceAmount;
	}

	public void setInvoiceAmount(BigDecimal invoiceAmount) {
		this.invoiceAmount = invoiceAmount;
	}

	public String getInvoiceNumber() {
		return this.invoiceNumber;
	}

	public void setInvoiceNumber(String invoiceNumber) {
		this.invoiceNumber = invoiceNumber;
	}

	public String getInvoiceType() {
		return this.invoiceType;
	}

	public void setInvoiceType(String invoiceType) {
		this.invoiceType = invoiceType;
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

	public String getMasterEdiNumber() {
		return this.masterEdiNumber;
	}

	public void setMasterEdiNumber(String masterEdiNumber) {
		this.masterEdiNumber = masterEdiNumber;
	}

	public BigDecimal getOrigInvoiceAmount() {
		return this.origInvoiceAmount;
	}

	public void setOrigInvoiceAmount(BigDecimal origInvoiceAmount) {
		this.origInvoiceAmount = origInvoiceAmount;
	}

	public String getPaymentType() {
		return this.paymentType;
	}

	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}

	public Date getRemitCreateDate() {
		return this.remitCreateDate;
	}

	public void setRemitCreateDate(Date remitCreateDate) {
		this.remitCreateDate = remitCreateDate;
	}

	public String getRunNo() {
		return this.runNo;
	}

	public void setRunNo(String runNo) {
		this.runNo = runNo;
	}

	public String getShipperCode() {
		return this.shipperCode;
	}

	public void setShipperCode(String shipperCode) {
		this.shipperCode = shipperCode;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<ArcEbillManifestTb> getArcEbillManifestTbs() {
		return this.arcEbillManifestTbs;
	}

	public void setArcEbillManifestTbs(List<ArcEbillManifestTb> arcEbillManifestTbs) {
		this.arcEbillManifestTbs = arcEbillManifestTbs;
	}

	public ArcEbillManifestTb addArcEbillManifestTb(ArcEbillManifestTb arcEbillManifestTb) {
		getArcEbillManifestTbs().add(arcEbillManifestTb);
		arcEbillManifestTb.setArcEbillInvoiceTb(this);

		return arcEbillManifestTb;
	}

	public ArcEbillManifestTb removeArcEbillManifestTb(ArcEbillManifestTb arcEbillManifestTb) {
		getArcEbillManifestTbs().remove(arcEbillManifestTb);
		arcEbillManifestTb.setArcEbillInvoiceTb(null);

		return arcEbillManifestTb;
	}

}