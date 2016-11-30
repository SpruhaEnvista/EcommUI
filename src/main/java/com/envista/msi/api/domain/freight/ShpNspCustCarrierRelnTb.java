package com.envista.msi.api.domain.freight;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the SHP_NSP_CUST_CARRIER_RELN_TB database table.
 * 
 */
@Entity
@Table(name="SHP_NSP_CUST_CARRIER_RELN_TB")
@NamedQuery(name="ShpNspCustCarrierRelnTb.findAll", query="SELECT s FROM ShpNspCustCarrierRelnTb s")
public class ShpNspCustCarrierRelnTb implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="NSP_CUST_CARRIER_RELN_ID", unique=true, nullable=false, precision=22)
	private long nspCustCarrierRelnId;

	@Column(name="ABOVE_TOLERANCE_LIMIT", precision=22)
	private BigDecimal aboveToleranceLimit;

	@Column(precision=22)
	private BigDecimal active;

	@Column(name="ALLOW_AUTOCLOSE_FLAG", precision=2)
	private BigDecimal allowAutocloseFlag;

	@Column(name="ALLOW_SHORTPAY_FLAG", precision=22)
	private BigDecimal allowShortpayFlag;

	@Column(name="ALWAYS_USED", precision=22)
	private BigDecimal alwaysUsed;

	@Column(name="AR_EMAIL", length=500)
	private String arEmail;

	@Column(name="AR_NAME", length=50)
	private String arName;

	@Column(name="AR_PHONE", length=50)
	private String arPhone;

	@Column(name="AWB_NUMBER_FORMAT", length=500)
	private String awbNumberFormat;

	@Column(name="BELOW_TOLERANCE_LIMIT", precision=22)
	private BigDecimal belowToleranceLimit;

	@Column(length=250)
	private String bol;

	@Column(name="CARRIER_NUMBER", length=100)
	private String carrierNumber;

	@Column(name="CARRIER_REP_EMAIL", length=500)
	private String carrierRepEmail;

	@Column(name="CARRIER_REP_NAME", length=100)
	private String carrierRepName;

	@Column(name="CARRIER_REP_PHONE", length=50)
	private String carrierRepPhone;

	@Column(name="CARRIER_TYPE", precision=22)
	private BigDecimal carrierType;

	@Column(name="CCR_PRIMARY_MODE", precision=22)
	private BigDecimal ccrPrimaryMode;

	@Column(name="CCR_QA_PERCENT", precision=22)
	private BigDecimal ccrQaPercent;

	@Column(name="CCR_QA_PERCENT_COUNTER", precision=22)
	private BigDecimal ccrQaPercentCounter;

	@Column(name="CODE_VALUE_PAY_METHOD", precision=22)
	private BigDecimal codeValuePayMethod;

	@Column(length=500)
	private String comments;

	@Column(name="CONTRACT_FILES", length=1000)
	private String contractFiles;

	@Column(name="CONTRACT_PRESENT", precision=1)
	private BigDecimal contractPresent;

	@Column(name="COST_CENTER_NUMBER_FORMAT", length=500)
	private String costCenterNumberFormat;

	@Temporal(TemporalType.DATE)
	@Column(name="CREATE_DATE")
	private Date createDate;

	@Column(name="CREATE_USER", length=50)
	private String createUser;

	@Column(length=500)
	private String custom1;

	@Column(length=500)
	private String custom10;

	@Column(length=500)
	private String custom11;

	@Column(length=500)
	private String custom12;

	@Column(length=500)
	private String custom13;

	@Column(length=500)
	private String custom14;

	@Column(length=500)
	private String custom15;

	@Column(length=500)
	private String custom16;

	@Column(length=500)
	private String custom17;

	@Column(length=500)
	private String custom18;

	@Column(length=500)
	private String custom19;

	@Column(length=500)
	private String custom2;

	@Column(length=500)
	private String custom20;

	@Column(length=500)
	private String custom3;

	@Column(length=500)
	private String custom4;

	@Column(length=500)
	private String custom5;

	@Column(length=500)
	private String custom6;

	@Column(length=500)
	private String custom7;

	@Column(length=500)
	private String custom8;

	@Column(length=500)
	private String custom9;

	@Column(name="DATAENTRY_RULES", length=1000)
	private String dataentryRules;

	@Column(name="DUP_GROUP_CARRIER_ID", precision=22)
	private BigDecimal dupGroupCarrierId;

	@Column(name="DUPCHK_STR_LEN", length=5)
	private String dupchkStrLen;

	@Column(name="EDI_EMAIL", length=500)
	private String ediEmail;

	@Column(name="EDI_NAME", length=100)
	private String ediName;

	@Column(name="EDI_PHONE", length=15)
	private String ediPhone;

	@Temporal(TemporalType.DATE)
	@Column(name="EFFECTIVE_DATE")
	private Date effectiveDate;

	@Column(name="INVOICE_DUE_DATE_OPTION", precision=22)
	private BigDecimal invoiceDueDateOption;

	@Column(name="INVOICE_DUE_DAYS", precision=22)
	private BigDecimal invoiceDueDays;

	@Column(name="INVOICE_PRO_FORMA", length=500)
	private String invoiceProForma;

	@Column(name="IS_MULTI_STOP", precision=1)
	private BigDecimal isMultiStop;

	@Column(name="IS_OVERRIDE_QA_PERCENT", precision=1)
	private BigDecimal isOverrideQaPercent;

	@Temporal(TemporalType.DATE)
	@Column(name="LAST_UPDATE_DATE")
	private Date lastUpdateDate;

	@Column(name="LAST_UPDATE_USER", length=50)
	private String lastUpdateUser;

	@Column(name="OVERRIDE_CUST_TOLERANCE", precision=22)
	private BigDecimal overrideCustTolerance;

	@Column(length=250)
	private String ref1;

	@Column(length=250)
	private String ref2;

	@Column(length=250)
	private String ref3;

	@Column(length=250)
	private String ref4;

	@Column(name="REMIT_TO_ADDRESS", length=200)
	private String remitToAddress;

	@Column(name="RESTRICT_PAPER_INVOICE_EDITING", precision=1)
	private BigDecimal restrictPaperInvoiceEditing;

	@Column(name="SEND_ENVISTA_997", precision=22)
	private BigDecimal sendEnvista997;

	@Column(name="SEND_REM_EMAIL", precision=22)
	private BigDecimal sendRemEmail;

	@Column(name="TOLERANCE_QUANTITY", precision=22)
	private BigDecimal toleranceQuantity;

	@Column(name="TOLERANCE_UNIT", precision=22)
	private BigDecimal toleranceUnit;

	//bi-directional many-to-one association to ShpNspCustCarrierContTb
	@OneToMany(mappedBy="shpNspCustCarrierRelnTb")
	private List<ShpNspCustCarrierContTb> shpNspCustCarrierContTbs;

	//bi-directional many-to-one association to ShpCarrierTb
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="CARRIER_ID")
	private ShpCarrierTb shpCarrierTb;
	
	
	//bi-directional many-to-one association to ShpCustomerProfileTb
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="CUSTOMER_ID")
	private ShpCustomerProfileTb shpCustomerProfileTb;

	//bi-directional many-to-one association to ShpNspCodeValuesTb
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="CODE_VALUE_REFERENCE2")
	private ShpNspCodeValuesTb shpNspCodeValuesTb1;

	//bi-directional many-to-one association to ShpNspCodeValuesTb
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="CODE_VALUE_PAY_TERMS")
	private ShpNspCodeValuesTb shpNspCodeValuesTb2;

	//bi-directional many-to-one association to ShpNspCodeValuesTb
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="CODE_VALUE_REFERENCE1")
	private ShpNspCodeValuesTb shpNspCodeValuesTb3;

	public ShpNspCustCarrierRelnTb() {
	}

	public long getNspCustCarrierRelnId() {
		return this.nspCustCarrierRelnId;
	}

	public void setNspCustCarrierRelnId(long nspCustCarrierRelnId) {
		this.nspCustCarrierRelnId = nspCustCarrierRelnId;
	}

	public BigDecimal getAboveToleranceLimit() {
		return this.aboveToleranceLimit;
	}

	public void setAboveToleranceLimit(BigDecimal aboveToleranceLimit) {
		this.aboveToleranceLimit = aboveToleranceLimit;
	}

	public BigDecimal getActive() {
		return this.active;
	}

	public void setActive(BigDecimal active) {
		this.active = active;
	}

	public BigDecimal getAllowAutocloseFlag() {
		return this.allowAutocloseFlag;
	}

	public void setAllowAutocloseFlag(BigDecimal allowAutocloseFlag) {
		this.allowAutocloseFlag = allowAutocloseFlag;
	}

	public BigDecimal getAllowShortpayFlag() {
		return this.allowShortpayFlag;
	}

	public void setAllowShortpayFlag(BigDecimal allowShortpayFlag) {
		this.allowShortpayFlag = allowShortpayFlag;
	}

	public BigDecimal getAlwaysUsed() {
		return this.alwaysUsed;
	}

	public void setAlwaysUsed(BigDecimal alwaysUsed) {
		this.alwaysUsed = alwaysUsed;
	}

	public String getArEmail() {
		return this.arEmail;
	}

	public void setArEmail(String arEmail) {
		this.arEmail = arEmail;
	}

	public String getArName() {
		return this.arName;
	}

	public void setArName(String arName) {
		this.arName = arName;
	}

	public String getArPhone() {
		return this.arPhone;
	}

	public void setArPhone(String arPhone) {
		this.arPhone = arPhone;
	}

	public String getAwbNumberFormat() {
		return this.awbNumberFormat;
	}

	public void setAwbNumberFormat(String awbNumberFormat) {
		this.awbNumberFormat = awbNumberFormat;
	}

	public BigDecimal getBelowToleranceLimit() {
		return this.belowToleranceLimit;
	}

	public void setBelowToleranceLimit(BigDecimal belowToleranceLimit) {
		this.belowToleranceLimit = belowToleranceLimit;
	}

	public String getBol() {
		return this.bol;
	}

	public void setBol(String bol) {
		this.bol = bol;
	}

	public String getCarrierNumber() {
		return this.carrierNumber;
	}

	public void setCarrierNumber(String carrierNumber) {
		this.carrierNumber = carrierNumber;
	}

	public String getCarrierRepEmail() {
		return this.carrierRepEmail;
	}

	public void setCarrierRepEmail(String carrierRepEmail) {
		this.carrierRepEmail = carrierRepEmail;
	}

	public String getCarrierRepName() {
		return this.carrierRepName;
	}

	public void setCarrierRepName(String carrierRepName) {
		this.carrierRepName = carrierRepName;
	}

	public String getCarrierRepPhone() {
		return this.carrierRepPhone;
	}

	public void setCarrierRepPhone(String carrierRepPhone) {
		this.carrierRepPhone = carrierRepPhone;
	}

	public BigDecimal getCarrierType() {
		return this.carrierType;
	}

	public void setCarrierType(BigDecimal carrierType) {
		this.carrierType = carrierType;
	}

	public BigDecimal getCcrPrimaryMode() {
		return this.ccrPrimaryMode;
	}

	public void setCcrPrimaryMode(BigDecimal ccrPrimaryMode) {
		this.ccrPrimaryMode = ccrPrimaryMode;
	}

	public BigDecimal getCcrQaPercent() {
		return this.ccrQaPercent;
	}

	public void setCcrQaPercent(BigDecimal ccrQaPercent) {
		this.ccrQaPercent = ccrQaPercent;
	}

	public BigDecimal getCcrQaPercentCounter() {
		return this.ccrQaPercentCounter;
	}

	public void setCcrQaPercentCounter(BigDecimal ccrQaPercentCounter) {
		this.ccrQaPercentCounter = ccrQaPercentCounter;
	}

	public BigDecimal getCodeValuePayMethod() {
		return this.codeValuePayMethod;
	}

	public void setCodeValuePayMethod(BigDecimal codeValuePayMethod) {
		this.codeValuePayMethod = codeValuePayMethod;
	}

	public String getComments() {
		return this.comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getContractFiles() {
		return this.contractFiles;
	}

	public void setContractFiles(String contractFiles) {
		this.contractFiles = contractFiles;
	}

	public BigDecimal getContractPresent() {
		return this.contractPresent;
	}

	public void setContractPresent(BigDecimal contractPresent) {
		this.contractPresent = contractPresent;
	}

	public String getCostCenterNumberFormat() {
		return this.costCenterNumberFormat;
	}

	public void setCostCenterNumberFormat(String costCenterNumberFormat) {
		this.costCenterNumberFormat = costCenterNumberFormat;
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

	public String getCustom1() {
		return this.custom1;
	}

	public void setCustom1(String custom1) {
		this.custom1 = custom1;
	}

	public String getCustom10() {
		return this.custom10;
	}

	public void setCustom10(String custom10) {
		this.custom10 = custom10;
	}

	public String getCustom11() {
		return this.custom11;
	}

	public void setCustom11(String custom11) {
		this.custom11 = custom11;
	}

	public String getCustom12() {
		return this.custom12;
	}

	public void setCustom12(String custom12) {
		this.custom12 = custom12;
	}

	public String getCustom13() {
		return this.custom13;
	}

	public void setCustom13(String custom13) {
		this.custom13 = custom13;
	}

	public String getCustom14() {
		return this.custom14;
	}

	public void setCustom14(String custom14) {
		this.custom14 = custom14;
	}

	public String getCustom15() {
		return this.custom15;
	}

	public void setCustom15(String custom15) {
		this.custom15 = custom15;
	}

	public String getCustom16() {
		return this.custom16;
	}

	public void setCustom16(String custom16) {
		this.custom16 = custom16;
	}

	public String getCustom17() {
		return this.custom17;
	}

	public void setCustom17(String custom17) {
		this.custom17 = custom17;
	}

	public String getCustom18() {
		return this.custom18;
	}

	public void setCustom18(String custom18) {
		this.custom18 = custom18;
	}

	public String getCustom19() {
		return this.custom19;
	}

	public void setCustom19(String custom19) {
		this.custom19 = custom19;
	}

	public String getCustom2() {
		return this.custom2;
	}

	public void setCustom2(String custom2) {
		this.custom2 = custom2;
	}

	public String getCustom20() {
		return this.custom20;
	}

	public void setCustom20(String custom20) {
		this.custom20 = custom20;
	}

	public String getCustom3() {
		return this.custom3;
	}

	public void setCustom3(String custom3) {
		this.custom3 = custom3;
	}

	public String getCustom4() {
		return this.custom4;
	}

	public void setCustom4(String custom4) {
		this.custom4 = custom4;
	}

	public String getCustom5() {
		return this.custom5;
	}

	public void setCustom5(String custom5) {
		this.custom5 = custom5;
	}

	public String getCustom6() {
		return this.custom6;
	}

	public void setCustom6(String custom6) {
		this.custom6 = custom6;
	}

	public String getCustom7() {
		return this.custom7;
	}

	public void setCustom7(String custom7) {
		this.custom7 = custom7;
	}

	public String getCustom8() {
		return this.custom8;
	}

	public void setCustom8(String custom8) {
		this.custom8 = custom8;
	}

	public String getCustom9() {
		return this.custom9;
	}

	public void setCustom9(String custom9) {
		this.custom9 = custom9;
	}

	public String getDataentryRules() {
		return this.dataentryRules;
	}

	public void setDataentryRules(String dataentryRules) {
		this.dataentryRules = dataentryRules;
	}

	public BigDecimal getDupGroupCarrierId() {
		return this.dupGroupCarrierId;
	}

	public void setDupGroupCarrierId(BigDecimal dupGroupCarrierId) {
		this.dupGroupCarrierId = dupGroupCarrierId;
	}

	public String getDupchkStrLen() {
		return this.dupchkStrLen;
	}

	public void setDupchkStrLen(String dupchkStrLen) {
		this.dupchkStrLen = dupchkStrLen;
	}

	public String getEdiEmail() {
		return this.ediEmail;
	}

	public void setEdiEmail(String ediEmail) {
		this.ediEmail = ediEmail;
	}

	public String getEdiName() {
		return this.ediName;
	}

	public void setEdiName(String ediName) {
		this.ediName = ediName;
	}

	public String getEdiPhone() {
		return this.ediPhone;
	}

	public void setEdiPhone(String ediPhone) {
		this.ediPhone = ediPhone;
	}

	public Date getEffectiveDate() {
		return this.effectiveDate;
	}

	public void setEffectiveDate(Date effectiveDate) {
		this.effectiveDate = effectiveDate;
	}

	public BigDecimal getInvoiceDueDateOption() {
		return this.invoiceDueDateOption;
	}

	public void setInvoiceDueDateOption(BigDecimal invoiceDueDateOption) {
		this.invoiceDueDateOption = invoiceDueDateOption;
	}

	public BigDecimal getInvoiceDueDays() {
		return this.invoiceDueDays;
	}

	public void setInvoiceDueDays(BigDecimal invoiceDueDays) {
		this.invoiceDueDays = invoiceDueDays;
	}

	public String getInvoiceProForma() {
		return this.invoiceProForma;
	}

	public void setInvoiceProForma(String invoiceProForma) {
		this.invoiceProForma = invoiceProForma;
	}

	public BigDecimal getIsMultiStop() {
		return this.isMultiStop;
	}

	public void setIsMultiStop(BigDecimal isMultiStop) {
		this.isMultiStop = isMultiStop;
	}

	public BigDecimal getIsOverrideQaPercent() {
		return this.isOverrideQaPercent;
	}

	public void setIsOverrideQaPercent(BigDecimal isOverrideQaPercent) {
		this.isOverrideQaPercent = isOverrideQaPercent;
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

	public BigDecimal getOverrideCustTolerance() {
		return this.overrideCustTolerance;
	}

	public void setOverrideCustTolerance(BigDecimal overrideCustTolerance) {
		this.overrideCustTolerance = overrideCustTolerance;
	}

	public String getRef1() {
		return this.ref1;
	}

	public void setRef1(String ref1) {
		this.ref1 = ref1;
	}

	public String getRef2() {
		return this.ref2;
	}

	public void setRef2(String ref2) {
		this.ref2 = ref2;
	}

	public String getRef3() {
		return this.ref3;
	}

	public void setRef3(String ref3) {
		this.ref3 = ref3;
	}

	public String getRef4() {
		return this.ref4;
	}

	public void setRef4(String ref4) {
		this.ref4 = ref4;
	}

	public String getRemitToAddress() {
		return this.remitToAddress;
	}

	public void setRemitToAddress(String remitToAddress) {
		this.remitToAddress = remitToAddress;
	}

	public BigDecimal getRestrictPaperInvoiceEditing() {
		return this.restrictPaperInvoiceEditing;
	}

	public void setRestrictPaperInvoiceEditing(BigDecimal restrictPaperInvoiceEditing) {
		this.restrictPaperInvoiceEditing = restrictPaperInvoiceEditing;
	}

	public BigDecimal getSendEnvista997() {
		return this.sendEnvista997;
	}

	public void setSendEnvista997(BigDecimal sendEnvista997) {
		this.sendEnvista997 = sendEnvista997;
	}

	public BigDecimal getSendRemEmail() {
		return this.sendRemEmail;
	}

	public void setSendRemEmail(BigDecimal sendRemEmail) {
		this.sendRemEmail = sendRemEmail;
	}

	public BigDecimal getToleranceQuantity() {
		return this.toleranceQuantity;
	}

	public void setToleranceQuantity(BigDecimal toleranceQuantity) {
		this.toleranceQuantity = toleranceQuantity;
	}

	public BigDecimal getToleranceUnit() {
		return this.toleranceUnit;
	}

	public void setToleranceUnit(BigDecimal toleranceUnit) {
		this.toleranceUnit = toleranceUnit;
	}

	public List<ShpNspCustCarrierContTb> getShpNspCustCarrierContTbs() {
		return this.shpNspCustCarrierContTbs;
	}

	public void setShpNspCustCarrierContTbs(List<ShpNspCustCarrierContTb> shpNspCustCarrierContTbs) {
		this.shpNspCustCarrierContTbs = shpNspCustCarrierContTbs;
	}

	public ShpNspCustCarrierContTb addShpNspCustCarrierContTb(ShpNspCustCarrierContTb shpNspCustCarrierContTb) {
		getShpNspCustCarrierContTbs().add(shpNspCustCarrierContTb);
		shpNspCustCarrierContTb.setShpNspCustCarrierRelnTb(this);

		return shpNspCustCarrierContTb;
	}

	public ShpNspCustCarrierContTb removeShpNspCustCarrierContTb(ShpNspCustCarrierContTb shpNspCustCarrierContTb) {
		getShpNspCustCarrierContTbs().remove(shpNspCustCarrierContTb);
		shpNspCustCarrierContTb.setShpNspCustCarrierRelnTb(null);

		return shpNspCustCarrierContTb;
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

	public ShpNspCodeValuesTb getShpNspCodeValuesTb1() {
		return this.shpNspCodeValuesTb1;
	}

	public void setShpNspCodeValuesTb1(ShpNspCodeValuesTb shpNspCodeValuesTb1) {
		this.shpNspCodeValuesTb1 = shpNspCodeValuesTb1;
	}

	public ShpNspCodeValuesTb getShpNspCodeValuesTb2() {
		return this.shpNspCodeValuesTb2;
	}

	public void setShpNspCodeValuesTb2(ShpNspCodeValuesTb shpNspCodeValuesTb2) {
		this.shpNspCodeValuesTb2 = shpNspCodeValuesTb2;
	}

	public ShpNspCodeValuesTb getShpNspCodeValuesTb3() {
		return this.shpNspCodeValuesTb3;
	}

	public void setShpNspCodeValuesTb3(ShpNspCodeValuesTb shpNspCodeValuesTb3) {
		this.shpNspCodeValuesTb3 = shpNspCodeValuesTb3;
	}

}