package com.envista.msi.api.domain.freight;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * The persistent class for the SHP_NSP_INVOICE_CHARGES_TB database table.
 * 
 */
@Entity
@Table(name = "SHP_NSP_INVOICE_CHARGES_TB")
@NamedQuery(name = "ShpNspInvoiceChargesTb.findAll", query = "SELECT s FROM ShpNspInvoiceChargesTb s")
public class ShpNspInvoiceChargesTb implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "NSP_INVOICE_CHARGES_ID", unique = true, precision = 22)
	private long nspInvoiceChargesId;

	@Column(name = "ACCESSORIAL_CURRENCY", precision = 22)
	private BigDecimal accessorialCurrency;

	@Column(name = "AUDIT_APPROVED_CHARGES", precision = 22)
	private BigDecimal auditApprovedCharges;

	@Column(name = "AUDIT_CHARGE_CODE", precision = 22)
	private BigDecimal auditChargeCode;

	@Column(name = "AUDIT_FRT_CLASS", length = 10)
	private String auditFrtClass;

	@Column(name = "AUDIT_QTY", precision = 22)
	private BigDecimal auditQty;

	@Column(name = "AUDIT_RATE", precision = 22)
	private BigDecimal auditRate;

	@Column(name = "AUDIT_RATING_METHOD", precision = 22)
	private BigDecimal auditRatingMethod;

	@Column(name = "AUDIT_UOM", precision = 22)
	private BigDecimal auditUom;

	@Column(name = "AUDIT_WEIGHT", precision = 22)
	private BigDecimal auditWeight;

	@Column(name = "AUDIT_WEIGHT_UOM", precision = 22)
	private BigDecimal auditWeightUom;

	@Column(name = "AWB_NUMBER", length = 100)
	private String awbNumber;

	@Column(name = "BILLED_WEIGHT", precision = 22)
	private BigDecimal billedWeight;

	@Column(name = "CARRIER_CHARGE_DESC", length = 500)
	private String carrierChargeDesc;

	@Column(name = "CARRIER_CHARGES", precision = 22)
	private BigDecimal carrierCharges;

	@Column(name = "CARRIER_FRT_CLASS", length = 10)
	private String carrierFrtClass;

	@Column(name = "CARRIER_QTY", precision = 22)
	private BigDecimal carrierQty;

	@Column(name = "CARRIER_RATE", precision = 22)
	private BigDecimal carrierRate;

	@Column(name = "CARRIER_RATING_METHOD", precision = 22)
	private BigDecimal carrierRatingMethod;

	@Column(name = "CARRIER_UOM", precision = 22)
	private BigDecimal carrierUom;

	@Column(name = "CARRIER_WEIGHT", precision = 22)
	private BigDecimal carrierWeight;

	@Column(name = "CARRIER_WEIGHT_UOM", precision = 22)
	private BigDecimal carrierWeightUom;

	@Column(name = "CHARGE_IN_DOM_CURRENCY", precision = 22)
	private BigDecimal chargeInDomCurrency;

	@Column(name = "COMMODITY_CODE", length = 20)
	private String commodityCode;

	@Column(name = "COST_CENTER", length = 100)
	private String costCenter;

	@Temporal(TemporalType.DATE)
	@Column(name = "CREATE_DATE")
	private Date createDate;

	@Column(name = "CREATE_USER", length = 100)
	private String createUser;

	@Column(name = "DIM_CUBE", precision = 22)
	private BigDecimal dimCube;

	@Column(name = "DIM_HEIGHT", precision = 22)
	private BigDecimal dimHeight;

	@Column(name = "DIM_LENGTH", precision = 22)
	private BigDecimal dimLength;

	@Column(name = "DIM_QUANTITY", precision = 22)
	private BigDecimal dimQuantity;

	@Column(name = "DIM_UOM", precision = 22)
	private BigDecimal dimUom;

	@Column(name = "DIM_WIDTH", precision = 22)
	private BigDecimal dimWidth;

	@Column(name = "EDI_CHARGE_CODE", length = 33)
	private String ediChargeCode;

	@Column(name = "GL_ACCOUNTS_CODE", length = 35)
	private String glAccountsCode;

	@Temporal(TemporalType.DATE)
	@Column(name = "GL_APPLIED_DATE")
	private Date glAppliedDate;

	@Column(name = "GL_SOURCE", length = 20)
	private String glSource;

	@Column(name = "INVOICE_ID", precision = 22)
	private BigDecimal invoiceId;

	@Column(name = "INVOICED_UOM", precision = 22)
	private BigDecimal invoicedUom;

	@Column(name = "INVOICED_VOLUME", precision = 22)
	private BigDecimal invoicedVolume;

	@Temporal(TemporalType.DATE)
	@Column(name = "LAST_UPDATE_DATE")
	private Date lastUpdateDate;

	@Column(name = "LAST_UPDATE_USER", length = 100)
	private String lastUpdateUser;

	@Column(name = "XML_RATING_SEQ", precision = 22)
	private BigDecimal xmlRatingSeq;

	// bi-directional many-to-one association to ShpNspAwbDtlTb
	@OneToMany(mappedBy = "shpNspInvoiceChargesTb")
	private List<ShpNspAwbDtlTb> shpNspAwbDtlTbs;

	// bi-directional many-to-one association to ShpNspCodeValuesTb
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "AUDIT_ADJUSTMENT_REASON")
	private ShpNspCodeValuesTb shpNspCodeValuesTb;

	public ShpNspInvoiceChargesTb() {
	}

	public long getNspInvoiceChargesId() {
		return this.nspInvoiceChargesId;
	}

	public void setNspInvoiceChargesId(long nspInvoiceChargesId) {
		this.nspInvoiceChargesId = nspInvoiceChargesId;
	}

	public BigDecimal getAccessorialCurrency() {
		return this.accessorialCurrency;
	}

	public void setAccessorialCurrency(BigDecimal accessorialCurrency) {
		this.accessorialCurrency = accessorialCurrency;
	}

	public BigDecimal getAuditApprovedCharges() {
		return this.auditApprovedCharges;
	}

	public void setAuditApprovedCharges(BigDecimal auditApprovedCharges) {
		this.auditApprovedCharges = auditApprovedCharges;
	}

	public BigDecimal getAuditChargeCode() {
		return this.auditChargeCode;
	}

	public void setAuditChargeCode(BigDecimal auditChargeCode) {
		this.auditChargeCode = auditChargeCode;
	}

	public String getAuditFrtClass() {
		return this.auditFrtClass;
	}

	public void setAuditFrtClass(String auditFrtClass) {
		this.auditFrtClass = auditFrtClass;
	}

	public BigDecimal getAuditQty() {
		return this.auditQty;
	}

	public void setAuditQty(BigDecimal auditQty) {
		this.auditQty = auditQty;
	}

	public BigDecimal getAuditRate() {
		return this.auditRate;
	}

	public void setAuditRate(BigDecimal auditRate) {
		this.auditRate = auditRate;
	}

	public BigDecimal getAuditRatingMethod() {
		return this.auditRatingMethod;
	}

	public void setAuditRatingMethod(BigDecimal auditRatingMethod) {
		this.auditRatingMethod = auditRatingMethod;
	}

	public BigDecimal getAuditUom() {
		return this.auditUom;
	}

	public void setAuditUom(BigDecimal auditUom) {
		this.auditUom = auditUom;
	}

	public BigDecimal getAuditWeight() {
		return this.auditWeight;
	}

	public void setAuditWeight(BigDecimal auditWeight) {
		this.auditWeight = auditWeight;
	}

	public BigDecimal getAuditWeightUom() {
		return this.auditWeightUom;
	}

	public void setAuditWeightUom(BigDecimal auditWeightUom) {
		this.auditWeightUom = auditWeightUom;
	}

	public String getAwbNumber() {
		return this.awbNumber;
	}

	public void setAwbNumber(String awbNumber) {
		this.awbNumber = awbNumber;
	}

	public BigDecimal getBilledWeight() {
		return this.billedWeight;
	}

	public void setBilledWeight(BigDecimal billedWeight) {
		this.billedWeight = billedWeight;
	}

	public String getCarrierChargeDesc() {
		return this.carrierChargeDesc;
	}

	public void setCarrierChargeDesc(String carrierChargeDesc) {
		this.carrierChargeDesc = carrierChargeDesc;
	}

	public BigDecimal getCarrierCharges() {
		return this.carrierCharges;
	}

	public void setCarrierCharges(BigDecimal carrierCharges) {
		this.carrierCharges = carrierCharges;
	}

	public String getCarrierFrtClass() {
		return this.carrierFrtClass;
	}

	public void setCarrierFrtClass(String carrierFrtClass) {
		this.carrierFrtClass = carrierFrtClass;
	}

	public BigDecimal getCarrierQty() {
		return this.carrierQty;
	}

	public void setCarrierQty(BigDecimal carrierQty) {
		this.carrierQty = carrierQty;
	}

	public BigDecimal getCarrierRate() {
		return this.carrierRate;
	}

	public void setCarrierRate(BigDecimal carrierRate) {
		this.carrierRate = carrierRate;
	}

	public BigDecimal getCarrierRatingMethod() {
		return this.carrierRatingMethod;
	}

	public void setCarrierRatingMethod(BigDecimal carrierRatingMethod) {
		this.carrierRatingMethod = carrierRatingMethod;
	}

	public BigDecimal getCarrierUom() {
		return this.carrierUom;
	}

	public void setCarrierUom(BigDecimal carrierUom) {
		this.carrierUom = carrierUom;
	}

	public BigDecimal getCarrierWeight() {
		return this.carrierWeight;
	}

	public void setCarrierWeight(BigDecimal carrierWeight) {
		this.carrierWeight = carrierWeight;
	}

	public BigDecimal getCarrierWeightUom() {
		return this.carrierWeightUom;
	}

	public void setCarrierWeightUom(BigDecimal carrierWeightUom) {
		this.carrierWeightUom = carrierWeightUom;
	}

	public BigDecimal getChargeInDomCurrency() {
		return this.chargeInDomCurrency;
	}

	public void setChargeInDomCurrency(BigDecimal chargeInDomCurrency) {
		this.chargeInDomCurrency = chargeInDomCurrency;
	}

	public String getCommodityCode() {
		return this.commodityCode;
	}

	public void setCommodityCode(String commodityCode) {
		this.commodityCode = commodityCode;
	}

	public String getCostCenter() {
		return this.costCenter;
	}

	public void setCostCenter(String costCenter) {
		this.costCenter = costCenter;
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

	public BigDecimal getDimCube() {
		return this.dimCube;
	}

	public void setDimCube(BigDecimal dimCube) {
		this.dimCube = dimCube;
	}

	public BigDecimal getDimHeight() {
		return this.dimHeight;
	}

	public void setDimHeight(BigDecimal dimHeight) {
		this.dimHeight = dimHeight;
	}

	public BigDecimal getDimLength() {
		return this.dimLength;
	}

	public void setDimLength(BigDecimal dimLength) {
		this.dimLength = dimLength;
	}

	public BigDecimal getDimQuantity() {
		return this.dimQuantity;
	}

	public void setDimQuantity(BigDecimal dimQuantity) {
		this.dimQuantity = dimQuantity;
	}

	public BigDecimal getDimUom() {
		return this.dimUom;
	}

	public void setDimUom(BigDecimal dimUom) {
		this.dimUom = dimUom;
	}

	public BigDecimal getDimWidth() {
		return this.dimWidth;
	}

	public void setDimWidth(BigDecimal dimWidth) {
		this.dimWidth = dimWidth;
	}

	public String getEdiChargeCode() {
		return this.ediChargeCode;
	}

	public void setEdiChargeCode(String ediChargeCode) {
		this.ediChargeCode = ediChargeCode;
	}

	public String getGlAccountsCode() {
		return this.glAccountsCode;
	}

	public void setGlAccountsCode(String glAccountsCode) {
		this.glAccountsCode = glAccountsCode;
	}

	public Date getGlAppliedDate() {
		return this.glAppliedDate;
	}

	public void setGlAppliedDate(Date glAppliedDate) {
		this.glAppliedDate = glAppliedDate;
	}

	public String getGlSource() {
		return this.glSource;
	}

	public void setGlSource(String glSource) {
		this.glSource = glSource;
	}

	public BigDecimal getInvoiceId() {
		return this.invoiceId;
	}

	public void setInvoiceId(BigDecimal invoiceId) {
		this.invoiceId = invoiceId;
	}

	public BigDecimal getInvoicedUom() {
		return this.invoicedUom;
	}

	public void setInvoicedUom(BigDecimal invoicedUom) {
		this.invoicedUom = invoicedUom;
	}

	public BigDecimal getInvoicedVolume() {
		return this.invoicedVolume;
	}

	public void setInvoicedVolume(BigDecimal invoicedVolume) {
		this.invoicedVolume = invoicedVolume;
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

	public BigDecimal getXmlRatingSeq() {
		return this.xmlRatingSeq;
	}

	public void setXmlRatingSeq(BigDecimal xmlRatingSeq) {
		this.xmlRatingSeq = xmlRatingSeq;
	}

	public List<ShpNspAwbDtlTb> getShpNspAwbDtlTbs() {
		return this.shpNspAwbDtlTbs;
	}

	public void setShpNspAwbDtlTbs(List<ShpNspAwbDtlTb> shpNspAwbDtlTbs) {
		this.shpNspAwbDtlTbs = shpNspAwbDtlTbs;
	}

	public ShpNspAwbDtlTb addShpNspAwbDtlTb(ShpNspAwbDtlTb shpNspAwbDtlTb) {
		getShpNspAwbDtlTbs().add(shpNspAwbDtlTb);
		shpNspAwbDtlTb.setShpNspInvoiceChargesTb(this);

		return shpNspAwbDtlTb;
	}

	public ShpNspAwbDtlTb removeShpNspAwbDtlTb(ShpNspAwbDtlTb shpNspAwbDtlTb) {
		getShpNspAwbDtlTbs().remove(shpNspAwbDtlTb);
		shpNspAwbDtlTb.setShpNspInvoiceChargesTb(null);

		return shpNspAwbDtlTb;
	}

	public ShpNspCodeValuesTb getShpNspCodeValuesTb() {
		return this.shpNspCodeValuesTb;
	}

	public void setShpNspCodeValuesTb(ShpNspCodeValuesTb shpNspCodeValuesTb) {
		this.shpNspCodeValuesTb = shpNspCodeValuesTb;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		final int maxLen = 10;
		return "ShpNspInvoiceChargesTb [nspInvoiceChargesId=" + nspInvoiceChargesId + ", "
				+ (accessorialCurrency != null ? "accessorialCurrency=" + accessorialCurrency + ", " : "")
				+ (auditApprovedCharges != null ? "auditApprovedCharges=" + auditApprovedCharges + ", " : "")
				+ (auditChargeCode != null ? "auditChargeCode=" + auditChargeCode + ", " : "")
				+ (auditFrtClass != null ? "auditFrtClass=" + auditFrtClass + ", " : "")
				+ (auditQty != null ? "auditQty=" + auditQty + ", " : "")
				+ (auditRate != null ? "auditRate=" + auditRate + ", " : "")
				+ (auditRatingMethod != null ? "auditRatingMethod=" + auditRatingMethod + ", " : "")
				+ (auditUom != null ? "auditUom=" + auditUom + ", " : "")
				+ (auditWeight != null ? "auditWeight=" + auditWeight + ", " : "")
				+ (auditWeightUom != null ? "auditWeightUom=" + auditWeightUom + ", " : "")
				+ (awbNumber != null ? "awbNumber=" + awbNumber + ", " : "")
				+ (billedWeight != null ? "billedWeight=" + billedWeight + ", " : "")
				+ (carrierChargeDesc != null ? "carrierChargeDesc=" + carrierChargeDesc + ", " : "")
				+ (carrierCharges != null ? "carrierCharges=" + carrierCharges + ", " : "")
				+ (carrierFrtClass != null ? "carrierFrtClass=" + carrierFrtClass + ", " : "")
				+ (carrierQty != null ? "carrierQty=" + carrierQty + ", " : "")
				+ (carrierRate != null ? "carrierRate=" + carrierRate + ", " : "")
				+ (carrierRatingMethod != null ? "carrierRatingMethod=" + carrierRatingMethod + ", " : "")
				+ (carrierUom != null ? "carrierUom=" + carrierUom + ", " : "")
				+ (carrierWeight != null ? "carrierWeight=" + carrierWeight + ", " : "")
				+ (carrierWeightUom != null ? "carrierWeightUom=" + carrierWeightUom + ", " : "")
				+ (chargeInDomCurrency != null ? "chargeInDomCurrency=" + chargeInDomCurrency + ", " : "")
				+ (commodityCode != null ? "commodityCode=" + commodityCode + ", " : "")
				+ (costCenter != null ? "costCenter=" + costCenter + ", " : "")
				+ (createDate != null ? "createDate=" + createDate + ", " : "")
				+ (createUser != null ? "createUser=" + createUser + ", " : "")
				+ (dimCube != null ? "dimCube=" + dimCube + ", " : "")
				+ (dimHeight != null ? "dimHeight=" + dimHeight + ", " : "")
				+ (dimLength != null ? "dimLength=" + dimLength + ", " : "")
				+ (dimQuantity != null ? "dimQuantity=" + dimQuantity + ", " : "")
				+ (dimUom != null ? "dimUom=" + dimUom + ", " : "")
				+ (dimWidth != null ? "dimWidth=" + dimWidth + ", " : "")
				+ (ediChargeCode != null ? "ediChargeCode=" + ediChargeCode + ", " : "")
				+ (glAccountsCode != null ? "glAccountsCode=" + glAccountsCode + ", " : "")
				+ (glAppliedDate != null ? "glAppliedDate=" + glAppliedDate + ", " : "")
				+ (glSource != null ? "glSource=" + glSource + ", " : "")
				+ (invoiceId != null ? "invoiceId=" + invoiceId + ", "
						: "")
				+ (invoicedUom != null ? "invoicedUom=" + invoicedUom + ", "
						: "")
				+ (invoicedVolume != null
						? "invoicedVolume=" + invoicedVolume
								+ ", "
						: "")
				+ (lastUpdateDate != null ? "lastUpdateDate=" + lastUpdateDate + ", " : "")
				+ (lastUpdateUser != null ? "lastUpdateUser=" + lastUpdateUser + ", " : "")
				+ (xmlRatingSeq != null ? "xmlRatingSeq=" + xmlRatingSeq + ", " : "")
				+ (shpNspAwbDtlTbs != null ? "shpNspAwbDtlTbs="
						+ shpNspAwbDtlTbs.subList(0, Math.min(shpNspAwbDtlTbs.size(), maxLen)) + ", " : "")
				+ (shpNspCodeValuesTb != null ? "shpNspCodeValuesTb=" + shpNspCodeValuesTb : "") + "]";
	}

}