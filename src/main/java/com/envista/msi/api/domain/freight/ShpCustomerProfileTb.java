package com.envista.msi.api.domain.freight;

import java.io.Serializable;
import javax.persistence.*;

import com.envista.msi.api.domain.util.BasicNameValuePair;
/*import org.codehaus.jackson.annotate.JsonBackReference;
import org.codehaus.jackson.annotate.JsonManagedReference;*/

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the SHP_CUSTOMER_PROFILE_TB database table.
 * 
 */
@Entity
@Table(name="SHP_CUSTOMER_PROFILE_TB")
@SqlResultSetMapping(
	    name="customerIdNameMapping",
	    classes={
	        @ConstructorResult(
	            targetClass=BasicNameValuePair.class,
	            columns={
	                @ColumnResult(name="CUSTOMER_ID"),
	                @ColumnResult(name="CUSTOMER_NAME")
	            }
	        )
	    }
	)
@NamedQuery(name="ShpCustomerProfileTb.findAll", query="SELECT s FROM ShpCustomerProfileTb s")
@NamedNativeQuery(name="customerIdName",query="SELECT cp.CUSTOMER_ID CUSTOMER_ID, cp.CUSTOMER_NAME CUSTOMER_NAME FROM SHP_CUSTOMER_PROFILE_TB cp, SHP_USER_CUST_SHIPPER_TB ucs WHERE ucs.CUSTOMER_ID=cp.CUSTOMER_ID AND ucs.USER_ID = ?1", resultSetMapping="customerIdNameMapping")
public class ShpCustomerProfileTb implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="CUSTOMER_ID", unique=true, precision=22)
	private Long customerId;

	@Column(name="ABOVE_TOLERANCE_LIMIT", precision=22)
	private BigDecimal aboveToleranceLimit;

	@Column(name="ADD_TO_DB", precision=1)
	private BigDecimal addToDb;

	@Column(length=100)
	private String address1;

	@Column(length=100)
	private String address2;

	@Column(name="BELOW_TOLERANCE_LIMIT", precision=22)
	private BigDecimal belowToleranceLimit;

	@Column(name="BILLPAY_DAY_OF_WEEK", precision=22)
	private BigDecimal billpayDayOfWeek;

	@Column(name="BROKERAGE_CHILD_TYPE", precision=1)
	private BigDecimal brokerageChildType;

	@Column(name="BROKERAGE_PARENT", precision=22)
	private BigDecimal brokerageParent;

	@Column(name="BROKERAGE_PARENT_TYPE", precision=1)
	private BigDecimal brokerageParentType;

	@Column(name="BUSINESS_PARTNER_ID", precision=22)
	private BigDecimal businessPartnerId;

	@Column(name="CAN_FREIGHT", precision=22)
	private BigDecimal canFreight;

	@Column(name="CAN_PARCEL", precision=22)
	private BigDecimal canParcel;

	@Column(length=40)
	private String city;

	@Column(name="COST_CENTER_NUMBER_FORMAT", length=500)
	private String costCenterNumberFormat;

	@Column(length=30)
	private String country;

	@Column(name="CREATED_BY", length=15)
	private String createdBy;

	@Temporal(TemporalType.DATE)
	@Column(name="CREATED_ON")
	private Date createdOn;

	@Column(name="CUST_QA_PERCENT", precision=22)
	private BigDecimal custQaPercent;

	@Column(name="CUST_QA_PERCENT_COUNTER", precision=22)
	private BigDecimal custQaPercentCounter;

	@Column(name="CUSTOMER_CODE", length=10)
	private String customerCode;

	@Column(name="CUSTOMER_DEFINED_NAME1", length=50)
	private String customerDefinedName1;

	@Column(name="CUSTOMER_DEFINED_NAME2", length=50)
	private String customerDefinedName2;

	@Column(name="CUSTOMER_DEFINED_NAME3", length=50)
	private String customerDefinedName3;

	@Column(name="CUSTOMER_DESCRIPTION", length=100)
	private String customerDescription;

	@Column(name="CUSTOMER_NAME", length=100)
	private String customerName;

	@Column(name="CUSTOMER_NUMBER", length=100)
	private String customerNumber;

	@Column(name="CUSTOMER_STATUS_ID", precision=22)
	private BigDecimal customerStatusId;

	@Temporal(TemporalType.DATE)
	@Column(name="DHL_ENGAGE_DATE")
	private Date dhlEngageDate;

	@Column(name="DHL_PASSWORD", length=50)
	private String dhlPassword;

	@Column(name="DHL_PAYMENT_OPTION", length=1)
	private String dhlPaymentOption;

	@Column(name="DHL_USER_NAME", length=50)
	private String dhlUserName;

	@Column(name="DOMESTIC_CURRENCY", precision=22)
	private BigDecimal domesticCurrency;

	@Column(length=150)
	private String email;

	@Column(name="EMAIL_UPS_FROM_ADDRESS", length=200)
	private String emailUpsFromAddress;

	@Temporal(TemporalType.DATE)
	@Column(name="EMAIL_UPS_GSR_DATE")
	private Date emailUpsGsrDate;

	@Column(name="EMAIL_UPS_GSR_FLAG", precision=22)
	private BigDecimal emailUpsGsrFlag;

	@Temporal(TemporalType.DATE)
	@Column(name="EMAIL_UPS_INVOICE_DATE")
	private Date emailUpsInvoiceDate;

	@Column(name="EMAIL_UPS_INVOICE_FLAG", precision=22)
	private BigDecimal emailUpsInvoiceFlag;

	@Column(name="EMAIL_UPS_TO_ADDRESS", length=200)
	private String emailUpsToAddress;

	@Column(name="EMEA_CUSTOMER_FLAG", precision=22)
	private BigDecimal emeaCustomerFlag;

	@Temporal(TemporalType.DATE)
	@Column(name="ENGAGE_DATE")
	private Date engageDate;

	@Column(length=20)
	private String fax;

	@Column(name="FDX_EXPRESS_DELAY_TIME", precision=22)
	private BigDecimal fdxExpressDelayTime;

	@Column(name="FDX_GROUND_DELAY_TIME", precision=22)
	private BigDecimal fdxGroundDelayTime;

	@Column(name="FDX_INTL_DELAY_TIME", precision=22)
	private BigDecimal fdxIntlDelayTime;

	@Column(name="FDX_PAYMENT_OPTION", length=1)
	private String fdxPaymentOption;

	@Column(name="FDX_PICKUP_SCAN_AS_ORIGIN_ZIP", precision=22)
	private BigDecimal fdxPickupScanAsOriginZip;

	@Column(name="FEDEX_CLOSE_CYCLE", precision=22)
	private BigDecimal fedexCloseCycle;

	@Column(name="FEDEX_CLOSE_DAY_OF_WEEK", precision=22)
	private BigDecimal fedexCloseDayOfWeek;

	@Column(name="FEDEX_EDI_TYPE", precision=22)
	private BigDecimal fedexEdiType;

	@Temporal(TemporalType.DATE)
	@Column(name="FEDEX_ENGAGE_DATE")
	private Date fedexEngageDate;

	@Column(name="FEDEX_EXPRESS_GSR_ONLINE", precision=22)
	private BigDecimal fedexExpressGsrOnline;

	@Column(name="FEDEX_GROUND_GSR_ONLINE", precision=22)
	private BigDecimal fedexGroundGsrOnline;

	@Column(name="FEDEX_NOTES", length=400)
	private String fedexNotes;

	@Column(name="FEDEX_ONLINE_FILING", precision=22)
	private BigDecimal fedexOnlineFiling;

	@Column(name="FEDEX_REMITTANCE_FLAG", precision=1)
	private BigDecimal fedexRemittanceFlag;

	@Temporal(TemporalType.DATE)
	@Column(name="FREIGHT_ENGAGE_DATE")
	private Date freightEngageDate;

	@Column(name="GL_CODE_FORMAT", length=100)
	private String glCodeFormat;

	@Column(name="GLCODE_METHOD", precision=22)
	private BigDecimal glcodeMethod;

	@Column(name="GLCODING_ATTEMPT", precision=22)
	private BigDecimal glcodingAttempt;

	@Column(name="INVOICED_ON_TRANSACTION_FEE", precision=22)
	private BigDecimal invoicedOnTransactionFee;

	@Column(name="IS_ACTIVE", precision=1)
	private Boolean isActive;

	@Column(name="IS_BILLPAY", precision=22)
	private BigDecimal isBillpay;

	@Column(name="IS_GL_NULL", precision=22)
	private BigDecimal isGlNull;

	@Column(name="IS_NSP_AUDIT", precision=22)
	private BigDecimal isNspAudit;

	@Column(name="IS_PARCEL_AUDIT", precision=22)
	private BigDecimal isParcelAudit;

	@Column(name="IS_PARCEL_BILLPAY", precision=22)
	private BigDecimal isParcelBillpay;

	@Column(name="IS_PARCEL_GL_NULL", precision=1)
	private BigDecimal isParcelGlNull;

	@Column(name="LATE_MANIFEST", precision=1)
	private BigDecimal lateManifest;

	@Column(name="LOAD_CONSOLIDATION", precision=22)
	private BigDecimal loadConsolidation;

	@Column(name="LOAD_ONLY_INTL", precision=22)
	private BigDecimal loadOnlyIntl;

	@Column(name="MANUAL_CARRIER_PREF", precision=22)
	private BigDecimal manualCarrierPref;

	@Column(precision=22)
	private BigDecimal markup;

	@Column(name="MARKUP_TYPE", precision=22)
	private BigDecimal markupType;

	@Column(name="MBA_CLIENT", precision=22)
	private BigDecimal mbaClient;

	@Column(name="NEXT_AVAILABLE_PO", length=500)
	private String nextAvailablePo;

	@Column(name="NSP_DAYS_FOR_PAY", precision=22)
	private BigDecimal nspDaysForPay;

	@Column(name="NSP_GROUP_CUSTOMER_ID", precision=22)
	private BigDecimal nspGroupCustomerId;

	@Column(name="PAID_CUST", precision=22)
	private BigDecimal paidCust;

	@Column(name="PAYRUN_BASED_ON", precision=22)
	private BigDecimal payrunBasedOn;

	@Column(name="PCMILER_VERSION", length=20)
	private String pcmilerVersion;

	@Column(length=20)
	private String phone;

	@Column(name="PO_FORMAT", length=500)
	private String poFormat;

	@Column(name="POD_REQUIRED", precision=22)
	private BigDecimal podRequired;

	@Column(name="POOL_POINT", precision=22)
	private BigDecimal poolPoint;

	@Column(name="POOL_POINT_UPS", precision=22)
	private BigDecimal poolPointUps;

	@Column(name="PRIMARY_BUSSINESS", length=150)
	private String primaryBussiness;

	@Column(name="RECEIVER_ID", length=15)
	private String receiverId;

	@Column(name="RESIDENTIAL_AUDIT_FOR_FREIGHT", precision=22)
	private BigDecimal residentialAuditForFreight;

	@Column(name="SHIPDATE_ON_ORIGIN", precision=22)
	private BigDecimal shipdateOnOrigin;

	@Column(name="SHIPPER_UNIQ_SH_GRP", precision=22)
	private BigDecimal shipperUniqShGrp;

	@Column(name="SHOW_PUB_RATES", precision=22)
	private BigDecimal showPubRates;

	/*@Column(name="\"STATE\"", length=20)
	private String state;*/

	@Column(precision=22)
	private BigDecimal tolerance;

	@Column(name="TOLERANCE_UNIT", precision=22)
	private BigDecimal toleranceUnit;

	@Column(name="UPDATED_BY", length=15)
	private String updatedBy;

	@Temporal(TemporalType.DATE)
	@Column(name="UPDATED_ON")
	private Date updatedOn;

	@Column(precision=22)
	private BigDecimal uplift;

	@Column(name="UPS_ADC_DELAY", precision=22)
	private BigDecimal upsAdcDelay;

	@Column(name="UPS_COMMENTS", length=400)
	private String upsComments;

	@Column(name="UPS_DUP_DELAY", precision=22)
	private BigDecimal upsDupDelay;

	@Temporal(TemporalType.DATE)
	@Column(name="UPS_ENGAGE_DATE")
	private Date upsEngageDate;

	@Column(name="UPS_EXPRESS_DELAY_TIME", precision=22)
	private BigDecimal upsExpressDelayTime;

	@Column(name="UPS_GSR_FLAG", precision=22)
	private BigDecimal upsGsrFlag;

	@Column(name="UPS_INTL_DELAY_TIME", precision=22)
	private BigDecimal upsIntlDelayTime;

	@Column(name="UPS_INVOICES", precision=22)
	private BigDecimal upsInvoices;

	@Column(name="UPS_NOTES", length=400)
	private String upsNotes;

	@Column(name="UPS_OMIT_ADC_AUDIT", precision=1)
	private BigDecimal upsOmitAdcAudit;

	@Column(name="UPS_OMIT_DUP_AUDIT", precision=1)
	private BigDecimal upsOmitDupAudit;

	@Column(name="UPS_OMIT_RES_AUDIT", precision=1)
	private BigDecimal upsOmitResAudit;

	@Column(name="UPS_OMIT_SAT_AUDIT", precision=1)
	private BigDecimal upsOmitSatAudit;

	@Column(name="UPS_OMIT_VOID_AUDIT", precision=1)
	private BigDecimal upsOmitVoidAudit;

	@Column(name="UPS_PAYMENT_OPTION", length=1)
	private String upsPaymentOption;

	@Column(name="UPS_RES_DELAY", precision=22)
	private BigDecimal upsResDelay;

	@Column(name="UPS_SAT_DELAY", precision=22)
	private BigDecimal upsSatDelay;

	@Column(name="UPS_SHORT_PAY", precision=22)
	private BigDecimal upsShortPay;

	@Column(name="UPS_VOID_DELAY", precision=22)
	private BigDecimal upsVoidDelay;

	@Column(name="UPS_VOID_IN_DAYS", precision=22)
	private BigDecimal upsVoidInDays;

	@Column(name="USE_TRAN_COUNT", precision=22)
	private BigDecimal useTranCount;

	@Column(name="VALIDATE_MULTISTOP_QTY", precision=22)
	private BigDecimal validateMultistopQty;

	@Column(length=10)
	private String zipcode;

	//bi-directional many-to-one association to ShpCustomerProfileTb
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="PARENT_CUSTOMER_ID")
	@JsonManagedReference
	private ShpCustomerProfileTb shpCustomerProfileTb;

	//bi-directional many-to-one association to ShpCustomerProfileTb
	@OneToMany(mappedBy="shpCustomerProfileTb")
	/*@JsonBackReference*/
	@JsonIgnore
	private List<ShpCustomerProfileTb> shpCustomerProfileTbs;

	//bi-directional many-to-one association to ShpUserProfileTb
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="ASSIGNED_AUDITOR")
	private ShpUserProfileTb shpUserProfileTb;

	//bi-directional many-to-one association to ShpFtpAccountsTb
	@OneToMany(mappedBy="shpCustomerProfileTb")
	private List<ShpFtpAccountsTb> shpFtpAccountsTbs;

	//bi-directional many-to-one association to ShpNspCustCarrierRelnTb
	@OneToMany(mappedBy="shpCustomerProfileTb")
	private List<ShpNspCustCarrierRelnTb> shpNspCustCarrierRelnTbs;

	//bi-directional many-to-one association to ShpNspInvoiceDetailsTb
	@JsonBackReference
	@OneToMany(mappedBy="shpCustomerProfileTb")
	private List<ShpNspInvoiceDetailsTb> shpNspInvoiceDetailsTbs;

	//bi-directional many-to-one association to ShpNspScannedInvoiceTb
	@OneToMany(mappedBy="shpCustomerProfileTb")
	@JsonBackReference
	private List<ShpNspScannedInvoiceTb> shpNspScannedInvoiceTbs;

	//bi-directional many-to-one association to ShpNspStandardFeeTb
	@OneToMany(mappedBy="shpCustomerProfileTb")
	@JsonBackReference
	private List<ShpNspStandardFeeTb> shpNspStandardFeeTbs;

	//bi-directional many-to-one association to ShpNspTaskTb
	@OneToMany(mappedBy="shpCustomerProfileTb")
	@JsonBackReference
	private List<ShpNspTaskTb> shpNspTaskTbs;

	//bi-directional many-to-one association to ShpShipperGroupTb
	@OneToMany(mappedBy="shpCustomerProfileTb")
	@JsonBackReference
	private List<ShpShipperGroupTb> shpShipperGroupTbs;

	//bi-directional many-to-one association to ShpShipperTb
	@OneToMany(mappedBy="shpCustomerProfileTb")
	@JsonBackReference
	private List<ShpShipperTb> shpShipperTbs;

	//bi-directional many-to-one association to ShpUserCustShipperTb
	@OneToMany(mappedBy="shpCustomerProfileTb")
	@JsonBackReference
	private List<ShpUserCustShipperTb> shpUserCustShipperTbs;

	public ShpCustomerProfileTb() {
	}

	public long getCustomerId() {
		return this.customerId;
	}

	public void setCustomerId(long customerId) {
		this.customerId = customerId;
	}

	public BigDecimal getAboveToleranceLimit() {
		return this.aboveToleranceLimit;
	}

	public void setAboveToleranceLimit(BigDecimal aboveToleranceLimit) {
		this.aboveToleranceLimit = aboveToleranceLimit;
	}

	public BigDecimal getAddToDb() {
		return this.addToDb;
	}

	public void setAddToDb(BigDecimal addToDb) {
		this.addToDb = addToDb;
	}

	public String getAddress1() {
		return this.address1;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	public String getAddress2() {
		return this.address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	public BigDecimal getBelowToleranceLimit() {
		return this.belowToleranceLimit;
	}

	public void setBelowToleranceLimit(BigDecimal belowToleranceLimit) {
		this.belowToleranceLimit = belowToleranceLimit;
	}

	public BigDecimal getBillpayDayOfWeek() {
		return this.billpayDayOfWeek;
	}

	public void setBillpayDayOfWeek(BigDecimal billpayDayOfWeek) {
		this.billpayDayOfWeek = billpayDayOfWeek;
	}

	public BigDecimal getBrokerageChildType() {
		return this.brokerageChildType;
	}

	public void setBrokerageChildType(BigDecimal brokerageChildType) {
		this.brokerageChildType = brokerageChildType;
	}

	public BigDecimal getBrokerageParent() {
		return this.brokerageParent;
	}

	public void setBrokerageParent(BigDecimal brokerageParent) {
		this.brokerageParent = brokerageParent;
	}

	public BigDecimal getBrokerageParentType() {
		return this.brokerageParentType;
	}

	public void setBrokerageParentType(BigDecimal brokerageParentType) {
		this.brokerageParentType = brokerageParentType;
	}

	public BigDecimal getBusinessPartnerId() {
		return this.businessPartnerId;
	}

	public void setBusinessPartnerId(BigDecimal businessPartnerId) {
		this.businessPartnerId = businessPartnerId;
	}

	public BigDecimal getCanFreight() {
		return this.canFreight;
	}

	public void setCanFreight(BigDecimal canFreight) {
		this.canFreight = canFreight;
	}

	public BigDecimal getCanParcel() {
		return this.canParcel;
	}

	public void setCanParcel(BigDecimal canParcel) {
		this.canParcel = canParcel;
	}

	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCostCenterNumberFormat() {
		return this.costCenterNumberFormat;
	}

	public void setCostCenterNumberFormat(String costCenterNumberFormat) {
		this.costCenterNumberFormat = costCenterNumberFormat;
	}

	public String getCountry() {
		return this.country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedOn() {
		return this.createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	public BigDecimal getCustQaPercent() {
		return this.custQaPercent;
	}

	public void setCustQaPercent(BigDecimal custQaPercent) {
		this.custQaPercent = custQaPercent;
	}

	public BigDecimal getCustQaPercentCounter() {
		return this.custQaPercentCounter;
	}

	public void setCustQaPercentCounter(BigDecimal custQaPercentCounter) {
		this.custQaPercentCounter = custQaPercentCounter;
	}

	public String getCustomerCode() {
		return this.customerCode;
	}

	public void setCustomerCode(String customerCode) {
		this.customerCode = customerCode;
	}

	public String getCustomerDefinedName1() {
		return this.customerDefinedName1;
	}

	public void setCustomerDefinedName1(String customerDefinedName1) {
		this.customerDefinedName1 = customerDefinedName1;
	}

	public String getCustomerDefinedName2() {
		return this.customerDefinedName2;
	}

	public void setCustomerDefinedName2(String customerDefinedName2) {
		this.customerDefinedName2 = customerDefinedName2;
	}

	public String getCustomerDefinedName3() {
		return this.customerDefinedName3;
	}

	public void setCustomerDefinedName3(String customerDefinedName3) {
		this.customerDefinedName3 = customerDefinedName3;
	}

	public String getCustomerDescription() {
		return this.customerDescription;
	}

	public void setCustomerDescription(String customerDescription) {
		this.customerDescription = customerDescription;
	}

	public String getCustomerName() {
		return this.customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCustomerNumber() {
		return this.customerNumber;
	}

	public void setCustomerNumber(String customerNumber) {
		this.customerNumber = customerNumber;
	}

	public BigDecimal getCustomerStatusId() {
		return this.customerStatusId;
	}

	public void setCustomerStatusId(BigDecimal customerStatusId) {
		this.customerStatusId = customerStatusId;
	}

	public Date getDhlEngageDate() {
		return this.dhlEngageDate;
	}

	public void setDhlEngageDate(Date dhlEngageDate) {
		this.dhlEngageDate = dhlEngageDate;
	}

	public String getDhlPassword() {
		return this.dhlPassword;
	}

	public void setDhlPassword(String dhlPassword) {
		this.dhlPassword = dhlPassword;
	}

	public String getDhlPaymentOption() {
		return this.dhlPaymentOption;
	}

	public void setDhlPaymentOption(String dhlPaymentOption) {
		this.dhlPaymentOption = dhlPaymentOption;
	}

	public String getDhlUserName() {
		return this.dhlUserName;
	}

	public void setDhlUserName(String dhlUserName) {
		this.dhlUserName = dhlUserName;
	}

	public BigDecimal getDomesticCurrency() {
		return this.domesticCurrency;
	}

	public void setDomesticCurrency(BigDecimal domesticCurrency) {
		this.domesticCurrency = domesticCurrency;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEmailUpsFromAddress() {
		return this.emailUpsFromAddress;
	}

	public void setEmailUpsFromAddress(String emailUpsFromAddress) {
		this.emailUpsFromAddress = emailUpsFromAddress;
	}

	public Date getEmailUpsGsrDate() {
		return this.emailUpsGsrDate;
	}

	public void setEmailUpsGsrDate(Date emailUpsGsrDate) {
		this.emailUpsGsrDate = emailUpsGsrDate;
	}

	public BigDecimal getEmailUpsGsrFlag() {
		return this.emailUpsGsrFlag;
	}

	public void setEmailUpsGsrFlag(BigDecimal emailUpsGsrFlag) {
		this.emailUpsGsrFlag = emailUpsGsrFlag;
	}

	public Date getEmailUpsInvoiceDate() {
		return this.emailUpsInvoiceDate;
	}

	public void setEmailUpsInvoiceDate(Date emailUpsInvoiceDate) {
		this.emailUpsInvoiceDate = emailUpsInvoiceDate;
	}

	public BigDecimal getEmailUpsInvoiceFlag() {
		return this.emailUpsInvoiceFlag;
	}

	public void setEmailUpsInvoiceFlag(BigDecimal emailUpsInvoiceFlag) {
		this.emailUpsInvoiceFlag = emailUpsInvoiceFlag;
	}

	public String getEmailUpsToAddress() {
		return this.emailUpsToAddress;
	}

	public void setEmailUpsToAddress(String emailUpsToAddress) {
		this.emailUpsToAddress = emailUpsToAddress;
	}

	public BigDecimal getEmeaCustomerFlag() {
		return this.emeaCustomerFlag;
	}

	public void setEmeaCustomerFlag(BigDecimal emeaCustomerFlag) {
		this.emeaCustomerFlag = emeaCustomerFlag;
	}

	public Date getEngageDate() {
		return this.engageDate;
	}

	public void setEngageDate(Date engageDate) {
		this.engageDate = engageDate;
	}

	public String getFax() {
		return this.fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public BigDecimal getFdxExpressDelayTime() {
		return this.fdxExpressDelayTime;
	}

	public void setFdxExpressDelayTime(BigDecimal fdxExpressDelayTime) {
		this.fdxExpressDelayTime = fdxExpressDelayTime;
	}

	public BigDecimal getFdxGroundDelayTime() {
		return this.fdxGroundDelayTime;
	}

	public void setFdxGroundDelayTime(BigDecimal fdxGroundDelayTime) {
		this.fdxGroundDelayTime = fdxGroundDelayTime;
	}

	public BigDecimal getFdxIntlDelayTime() {
		return this.fdxIntlDelayTime;
	}

	public void setFdxIntlDelayTime(BigDecimal fdxIntlDelayTime) {
		this.fdxIntlDelayTime = fdxIntlDelayTime;
	}

	public String getFdxPaymentOption() {
		return this.fdxPaymentOption;
	}

	public void setFdxPaymentOption(String fdxPaymentOption) {
		this.fdxPaymentOption = fdxPaymentOption;
	}

	public BigDecimal getFdxPickupScanAsOriginZip() {
		return this.fdxPickupScanAsOriginZip;
	}

	public void setFdxPickupScanAsOriginZip(BigDecimal fdxPickupScanAsOriginZip) {
		this.fdxPickupScanAsOriginZip = fdxPickupScanAsOriginZip;
	}

	public BigDecimal getFedexCloseCycle() {
		return this.fedexCloseCycle;
	}

	public void setFedexCloseCycle(BigDecimal fedexCloseCycle) {
		this.fedexCloseCycle = fedexCloseCycle;
	}

	public BigDecimal getFedexCloseDayOfWeek() {
		return this.fedexCloseDayOfWeek;
	}

	public void setFedexCloseDayOfWeek(BigDecimal fedexCloseDayOfWeek) {
		this.fedexCloseDayOfWeek = fedexCloseDayOfWeek;
	}

	public BigDecimal getFedexEdiType() {
		return this.fedexEdiType;
	}

	public void setFedexEdiType(BigDecimal fedexEdiType) {
		this.fedexEdiType = fedexEdiType;
	}

	public Date getFedexEngageDate() {
		return this.fedexEngageDate;
	}

	public void setFedexEngageDate(Date fedexEngageDate) {
		this.fedexEngageDate = fedexEngageDate;
	}

	public BigDecimal getFedexExpressGsrOnline() {
		return this.fedexExpressGsrOnline;
	}

	public void setFedexExpressGsrOnline(BigDecimal fedexExpressGsrOnline) {
		this.fedexExpressGsrOnline = fedexExpressGsrOnline;
	}

	public BigDecimal getFedexGroundGsrOnline() {
		return this.fedexGroundGsrOnline;
	}

	public void setFedexGroundGsrOnline(BigDecimal fedexGroundGsrOnline) {
		this.fedexGroundGsrOnline = fedexGroundGsrOnline;
	}

	public String getFedexNotes() {
		return this.fedexNotes;
	}

	public void setFedexNotes(String fedexNotes) {
		this.fedexNotes = fedexNotes;
	}

	public BigDecimal getFedexOnlineFiling() {
		return this.fedexOnlineFiling;
	}

	public void setFedexOnlineFiling(BigDecimal fedexOnlineFiling) {
		this.fedexOnlineFiling = fedexOnlineFiling;
	}

	public BigDecimal getFedexRemittanceFlag() {
		return this.fedexRemittanceFlag;
	}

	public void setFedexRemittanceFlag(BigDecimal fedexRemittanceFlag) {
		this.fedexRemittanceFlag = fedexRemittanceFlag;
	}

	public Date getFreightEngageDate() {
		return this.freightEngageDate;
	}

	public void setFreightEngageDate(Date freightEngageDate) {
		this.freightEngageDate = freightEngageDate;
	}

	public String getGlCodeFormat() {
		return this.glCodeFormat;
	}

	public void setGlCodeFormat(String glCodeFormat) {
		this.glCodeFormat = glCodeFormat;
	}

	public BigDecimal getGlcodeMethod() {
		return this.glcodeMethod;
	}

	public void setGlcodeMethod(BigDecimal glcodeMethod) {
		this.glcodeMethod = glcodeMethod;
	}

	public BigDecimal getGlcodingAttempt() {
		return this.glcodingAttempt;
	}

	public void setGlcodingAttempt(BigDecimal glcodingAttempt) {
		this.glcodingAttempt = glcodingAttempt;
	}

	public BigDecimal getInvoicedOnTransactionFee() {
		return this.invoicedOnTransactionFee;
	}

	public void setInvoicedOnTransactionFee(BigDecimal invoicedOnTransactionFee) {
		this.invoicedOnTransactionFee = invoicedOnTransactionFee;
	}

	/**
	 * @return the isActive
	 */
	public Boolean getIsActive() {
		return isActive;
	}

	/**
	 * @param isActive the isActive to set
	 */
	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public BigDecimal getIsBillpay() {
		return this.isBillpay;
	}

	public void setIsBillpay(BigDecimal isBillpay) {
		this.isBillpay = isBillpay;
	}

	public BigDecimal getIsGlNull() {
		return this.isGlNull;
	}

	public void setIsGlNull(BigDecimal isGlNull) {
		this.isGlNull = isGlNull;
	}

	public BigDecimal getIsNspAudit() {
		return this.isNspAudit;
	}

	public void setIsNspAudit(BigDecimal isNspAudit) {
		this.isNspAudit = isNspAudit;
	}

	public BigDecimal getIsParcelAudit() {
		return this.isParcelAudit;
	}

	public void setIsParcelAudit(BigDecimal isParcelAudit) {
		this.isParcelAudit = isParcelAudit;
	}

	public BigDecimal getIsParcelBillpay() {
		return this.isParcelBillpay;
	}

	public void setIsParcelBillpay(BigDecimal isParcelBillpay) {
		this.isParcelBillpay = isParcelBillpay;
	}

	public BigDecimal getIsParcelGlNull() {
		return this.isParcelGlNull;
	}

	public void setIsParcelGlNull(BigDecimal isParcelGlNull) {
		this.isParcelGlNull = isParcelGlNull;
	}

	public BigDecimal getLateManifest() {
		return this.lateManifest;
	}

	public void setLateManifest(BigDecimal lateManifest) {
		this.lateManifest = lateManifest;
	}

	public BigDecimal getLoadConsolidation() {
		return this.loadConsolidation;
	}

	public void setLoadConsolidation(BigDecimal loadConsolidation) {
		this.loadConsolidation = loadConsolidation;
	}

	public BigDecimal getLoadOnlyIntl() {
		return this.loadOnlyIntl;
	}

	public void setLoadOnlyIntl(BigDecimal loadOnlyIntl) {
		this.loadOnlyIntl = loadOnlyIntl;
	}

	public BigDecimal getManualCarrierPref() {
		return this.manualCarrierPref;
	}

	public void setManualCarrierPref(BigDecimal manualCarrierPref) {
		this.manualCarrierPref = manualCarrierPref;
	}

	public BigDecimal getMarkup() {
		return this.markup;
	}

	public void setMarkup(BigDecimal markup) {
		this.markup = markup;
	}

	public BigDecimal getMarkupType() {
		return this.markupType;
	}

	public void setMarkupType(BigDecimal markupType) {
		this.markupType = markupType;
	}

	public BigDecimal getMbaClient() {
		return this.mbaClient;
	}

	public void setMbaClient(BigDecimal mbaClient) {
		this.mbaClient = mbaClient;
	}

	public String getNextAvailablePo() {
		return this.nextAvailablePo;
	}

	public void setNextAvailablePo(String nextAvailablePo) {
		this.nextAvailablePo = nextAvailablePo;
	}

	public BigDecimal getNspDaysForPay() {
		return this.nspDaysForPay;
	}

	public void setNspDaysForPay(BigDecimal nspDaysForPay) {
		this.nspDaysForPay = nspDaysForPay;
	}

	public BigDecimal getNspGroupCustomerId() {
		return this.nspGroupCustomerId;
	}

	public void setNspGroupCustomerId(BigDecimal nspGroupCustomerId) {
		this.nspGroupCustomerId = nspGroupCustomerId;
	}

	public BigDecimal getPaidCust() {
		return this.paidCust;
	}

	public void setPaidCust(BigDecimal paidCust) {
		this.paidCust = paidCust;
	}

	public BigDecimal getPayrunBasedOn() {
		return this.payrunBasedOn;
	}

	public void setPayrunBasedOn(BigDecimal payrunBasedOn) {
		this.payrunBasedOn = payrunBasedOn;
	}

	public String getPcmilerVersion() {
		return this.pcmilerVersion;
	}

	public void setPcmilerVersion(String pcmilerVersion) {
		this.pcmilerVersion = pcmilerVersion;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPoFormat() {
		return this.poFormat;
	}

	public void setPoFormat(String poFormat) {
		this.poFormat = poFormat;
	}

	public BigDecimal getPodRequired() {
		return this.podRequired;
	}

	public void setPodRequired(BigDecimal podRequired) {
		this.podRequired = podRequired;
	}

	public BigDecimal getPoolPoint() {
		return this.poolPoint;
	}

	public void setPoolPoint(BigDecimal poolPoint) {
		this.poolPoint = poolPoint;
	}

	public BigDecimal getPoolPointUps() {
		return this.poolPointUps;
	}

	public void setPoolPointUps(BigDecimal poolPointUps) {
		this.poolPointUps = poolPointUps;
	}

	public String getPrimaryBussiness() {
		return this.primaryBussiness;
	}

	public void setPrimaryBussiness(String primaryBussiness) {
		this.primaryBussiness = primaryBussiness;
	}

	public String getReceiverId() {
		return this.receiverId;
	}

	public void setReceiverId(String receiverId) {
		this.receiverId = receiverId;
	}

	public BigDecimal getResidentialAuditForFreight() {
		return this.residentialAuditForFreight;
	}

	public void setResidentialAuditForFreight(BigDecimal residentialAuditForFreight) {
		this.residentialAuditForFreight = residentialAuditForFreight;
	}

	public BigDecimal getShipdateOnOrigin() {
		return this.shipdateOnOrigin;
	}

	public void setShipdateOnOrigin(BigDecimal shipdateOnOrigin) {
		this.shipdateOnOrigin = shipdateOnOrigin;
	}

	public BigDecimal getShipperUniqShGrp() {
		return this.shipperUniqShGrp;
	}

	public void setShipperUniqShGrp(BigDecimal shipperUniqShGrp) {
		this.shipperUniqShGrp = shipperUniqShGrp;
	}

	public BigDecimal getShowPubRates() {
		return this.showPubRates;
	}

	public void setShowPubRates(BigDecimal showPubRates) {
		this.showPubRates = showPubRates;
	}

	/*public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}*/

	public BigDecimal getTolerance() {
		return this.tolerance;
	}

	public void setTolerance(BigDecimal tolerance) {
		this.tolerance = tolerance;
	}

	public BigDecimal getToleranceUnit() {
		return this.toleranceUnit;
	}

	public void setToleranceUnit(BigDecimal toleranceUnit) {
		this.toleranceUnit = toleranceUnit;
	}

	public String getUpdatedBy() {
		return this.updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Date getUpdatedOn() {
		return this.updatedOn;
	}

	public void setUpdatedOn(Date updatedOn) {
		this.updatedOn = updatedOn;
	}

	public BigDecimal getUplift() {
		return this.uplift;
	}

	public void setUplift(BigDecimal uplift) {
		this.uplift = uplift;
	}

	public BigDecimal getUpsAdcDelay() {
		return this.upsAdcDelay;
	}

	public void setUpsAdcDelay(BigDecimal upsAdcDelay) {
		this.upsAdcDelay = upsAdcDelay;
	}

	public String getUpsComments() {
		return this.upsComments;
	}

	public void setUpsComments(String upsComments) {
		this.upsComments = upsComments;
	}

	public BigDecimal getUpsDupDelay() {
		return this.upsDupDelay;
	}

	public void setUpsDupDelay(BigDecimal upsDupDelay) {
		this.upsDupDelay = upsDupDelay;
	}

	public Date getUpsEngageDate() {
		return this.upsEngageDate;
	}

	public void setUpsEngageDate(Date upsEngageDate) {
		this.upsEngageDate = upsEngageDate;
	}

	public BigDecimal getUpsExpressDelayTime() {
		return this.upsExpressDelayTime;
	}

	public void setUpsExpressDelayTime(BigDecimal upsExpressDelayTime) {
		this.upsExpressDelayTime = upsExpressDelayTime;
	}

	public BigDecimal getUpsGsrFlag() {
		return this.upsGsrFlag;
	}

	public void setUpsGsrFlag(BigDecimal upsGsrFlag) {
		this.upsGsrFlag = upsGsrFlag;
	}

	public BigDecimal getUpsIntlDelayTime() {
		return this.upsIntlDelayTime;
	}

	public void setUpsIntlDelayTime(BigDecimal upsIntlDelayTime) {
		this.upsIntlDelayTime = upsIntlDelayTime;
	}

	public BigDecimal getUpsInvoices() {
		return this.upsInvoices;
	}

	public void setUpsInvoices(BigDecimal upsInvoices) {
		this.upsInvoices = upsInvoices;
	}

	public String getUpsNotes() {
		return this.upsNotes;
	}

	public void setUpsNotes(String upsNotes) {
		this.upsNotes = upsNotes;
	}

	public BigDecimal getUpsOmitAdcAudit() {
		return this.upsOmitAdcAudit;
	}

	public void setUpsOmitAdcAudit(BigDecimal upsOmitAdcAudit) {
		this.upsOmitAdcAudit = upsOmitAdcAudit;
	}

	public BigDecimal getUpsOmitDupAudit() {
		return this.upsOmitDupAudit;
	}

	public void setUpsOmitDupAudit(BigDecimal upsOmitDupAudit) {
		this.upsOmitDupAudit = upsOmitDupAudit;
	}

	public BigDecimal getUpsOmitResAudit() {
		return this.upsOmitResAudit;
	}

	public void setUpsOmitResAudit(BigDecimal upsOmitResAudit) {
		this.upsOmitResAudit = upsOmitResAudit;
	}

	public BigDecimal getUpsOmitSatAudit() {
		return this.upsOmitSatAudit;
	}

	public void setUpsOmitSatAudit(BigDecimal upsOmitSatAudit) {
		this.upsOmitSatAudit = upsOmitSatAudit;
	}

	public BigDecimal getUpsOmitVoidAudit() {
		return this.upsOmitVoidAudit;
	}

	public void setUpsOmitVoidAudit(BigDecimal upsOmitVoidAudit) {
		this.upsOmitVoidAudit = upsOmitVoidAudit;
	}

	public String getUpsPaymentOption() {
		return this.upsPaymentOption;
	}

	public void setUpsPaymentOption(String upsPaymentOption) {
		this.upsPaymentOption = upsPaymentOption;
	}

	public BigDecimal getUpsResDelay() {
		return this.upsResDelay;
	}

	public void setUpsResDelay(BigDecimal upsResDelay) {
		this.upsResDelay = upsResDelay;
	}

	public BigDecimal getUpsSatDelay() {
		return this.upsSatDelay;
	}

	public void setUpsSatDelay(BigDecimal upsSatDelay) {
		this.upsSatDelay = upsSatDelay;
	}

	public BigDecimal getUpsShortPay() {
		return this.upsShortPay;
	}

	public void setUpsShortPay(BigDecimal upsShortPay) {
		this.upsShortPay = upsShortPay;
	}

	public BigDecimal getUpsVoidDelay() {
		return this.upsVoidDelay;
	}

	public void setUpsVoidDelay(BigDecimal upsVoidDelay) {
		this.upsVoidDelay = upsVoidDelay;
	}

	public BigDecimal getUpsVoidInDays() {
		return this.upsVoidInDays;
	}

	public void setUpsVoidInDays(BigDecimal upsVoidInDays) {
		this.upsVoidInDays = upsVoidInDays;
	}

	public BigDecimal getUseTranCount() {
		return this.useTranCount;
	}

	public void setUseTranCount(BigDecimal useTranCount) {
		this.useTranCount = useTranCount;
	}

	public BigDecimal getValidateMultistopQty() {
		return this.validateMultistopQty;
	}

	public void setValidateMultistopQty(BigDecimal validateMultistopQty) {
		this.validateMultistopQty = validateMultistopQty;
	}

	public String getZipcode() {
		return this.zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public ShpCustomerProfileTb getShpCustomerProfileTb() {
		return this.shpCustomerProfileTb;
	}

	public void setShpCustomerProfileTb(ShpCustomerProfileTb shpCustomerProfileTb) {
		this.shpCustomerProfileTb = shpCustomerProfileTb;
	}

	public List<ShpCustomerProfileTb> getShpCustomerProfileTbs() {
		return this.shpCustomerProfileTbs;
	}

	public void setShpCustomerProfileTbs(List<ShpCustomerProfileTb> shpCustomerProfileTbs) {
		this.shpCustomerProfileTbs = shpCustomerProfileTbs;
	}

	public ShpCustomerProfileTb addShpCustomerProfileTb(ShpCustomerProfileTb shpCustomerProfileTb) {
		getShpCustomerProfileTbs().add(shpCustomerProfileTb);
		shpCustomerProfileTb.setShpCustomerProfileTb(this);

		return shpCustomerProfileTb;
	}

	public ShpCustomerProfileTb removeShpCustomerProfileTb(ShpCustomerProfileTb shpCustomerProfileTb) {
		getShpCustomerProfileTbs().remove(shpCustomerProfileTb);
		shpCustomerProfileTb.setShpCustomerProfileTb(null);

		return shpCustomerProfileTb;
	}

	public ShpUserProfileTb getShpUserProfileTb() {
		return this.shpUserProfileTb;
	}

	public void setShpUserProfileTb(ShpUserProfileTb shpUserProfileTb) {
		this.shpUserProfileTb = shpUserProfileTb;
	}

	public List<ShpFtpAccountsTb> getShpFtpAccountsTbs() {
		return this.shpFtpAccountsTbs;
	}

	public void setShpFtpAccountsTbs(List<ShpFtpAccountsTb> shpFtpAccountsTbs) {
		this.shpFtpAccountsTbs = shpFtpAccountsTbs;
	}

	public ShpFtpAccountsTb addShpFtpAccountsTb(ShpFtpAccountsTb shpFtpAccountsTb) {
		getShpFtpAccountsTbs().add(shpFtpAccountsTb);
		shpFtpAccountsTb.setShpCustomerProfileTb(this);

		return shpFtpAccountsTb;
	}

	public ShpFtpAccountsTb removeShpFtpAccountsTb(ShpFtpAccountsTb shpFtpAccountsTb) {
		getShpFtpAccountsTbs().remove(shpFtpAccountsTb);
		shpFtpAccountsTb.setShpCustomerProfileTb(null);

		return shpFtpAccountsTb;
	}

	public List<ShpNspCustCarrierRelnTb> getShpNspCustCarrierRelnTbs() {
		return this.shpNspCustCarrierRelnTbs;
	}

	public void setShpNspCustCarrierRelnTbs(List<ShpNspCustCarrierRelnTb> shpNspCustCarrierRelnTbs) {
		this.shpNspCustCarrierRelnTbs = shpNspCustCarrierRelnTbs;
	}

	public ShpNspCustCarrierRelnTb addShpNspCustCarrierRelnTb(ShpNspCustCarrierRelnTb shpNspCustCarrierRelnTb) {
		getShpNspCustCarrierRelnTbs().add(shpNspCustCarrierRelnTb);
		shpNspCustCarrierRelnTb.setShpCustomerProfileTb(this);

		return shpNspCustCarrierRelnTb;
	}

	public ShpNspCustCarrierRelnTb removeShpNspCustCarrierRelnTb(ShpNspCustCarrierRelnTb shpNspCustCarrierRelnTb) {
		getShpNspCustCarrierRelnTbs().remove(shpNspCustCarrierRelnTb);
		shpNspCustCarrierRelnTb.setShpCustomerProfileTb(null);

		return shpNspCustCarrierRelnTb;
	}

	public List<ShpNspInvoiceDetailsTb> getShpNspInvoiceDetailsTbs() {
		return this.shpNspInvoiceDetailsTbs;
	}

	public void setShpNspInvoiceDetailsTbs(List<ShpNspInvoiceDetailsTb> shpNspInvoiceDetailsTbs) {
		this.shpNspInvoiceDetailsTbs = shpNspInvoiceDetailsTbs;
	}

	public ShpNspInvoiceDetailsTb addShpNspInvoiceDetailsTb(ShpNspInvoiceDetailsTb shpNspInvoiceDetailsTb) {
		getShpNspInvoiceDetailsTbs().add(shpNspInvoiceDetailsTb);
		shpNspInvoiceDetailsTb.setShpCustomerProfileTb(this);

		return shpNspInvoiceDetailsTb;
	}

	public ShpNspInvoiceDetailsTb removeShpNspInvoiceDetailsTb(ShpNspInvoiceDetailsTb shpNspInvoiceDetailsTb) {
		getShpNspInvoiceDetailsTbs().remove(shpNspInvoiceDetailsTb);
		shpNspInvoiceDetailsTb.setShpCustomerProfileTb(null);

		return shpNspInvoiceDetailsTb;
	}

	public List<ShpNspScannedInvoiceTb> getShpNspScannedInvoiceTbs() {
		return this.shpNspScannedInvoiceTbs;
	}

	public void setShpNspScannedInvoiceTbs(List<ShpNspScannedInvoiceTb> shpNspScannedInvoiceTbs) {
		this.shpNspScannedInvoiceTbs = shpNspScannedInvoiceTbs;
	}

	public ShpNspScannedInvoiceTb addShpNspScannedInvoiceTb(ShpNspScannedInvoiceTb shpNspScannedInvoiceTb) {
		getShpNspScannedInvoiceTbs().add(shpNspScannedInvoiceTb);
		shpNspScannedInvoiceTb.setShpCustomerProfileTb(this);

		return shpNspScannedInvoiceTb;
	}

	public ShpNspScannedInvoiceTb removeShpNspScannedInvoiceTb(ShpNspScannedInvoiceTb shpNspScannedInvoiceTb) {
		getShpNspScannedInvoiceTbs().remove(shpNspScannedInvoiceTb);
		shpNspScannedInvoiceTb.setShpCustomerProfileTb(null);

		return shpNspScannedInvoiceTb;
	}

	public List<ShpNspStandardFeeTb> getShpNspStandardFeeTbs() {
		return this.shpNspStandardFeeTbs;
	}

	public void setShpNspStandardFeeTbs(List<ShpNspStandardFeeTb> shpNspStandardFeeTbs) {
		this.shpNspStandardFeeTbs = shpNspStandardFeeTbs;
	}

	public ShpNspStandardFeeTb addShpNspStandardFeeTb(ShpNspStandardFeeTb shpNspStandardFeeTb) {
		getShpNspStandardFeeTbs().add(shpNspStandardFeeTb);
		shpNspStandardFeeTb.setShpCustomerProfileTb(this);

		return shpNspStandardFeeTb;
	}

	public ShpNspStandardFeeTb removeShpNspStandardFeeTb(ShpNspStandardFeeTb shpNspStandardFeeTb) {
		getShpNspStandardFeeTbs().remove(shpNspStandardFeeTb);
		shpNspStandardFeeTb.setShpCustomerProfileTb(null);

		return shpNspStandardFeeTb;
	}

	public List<ShpNspTaskTb> getShpNspTaskTbs() {
		return this.shpNspTaskTbs;
	}

	public void setShpNspTaskTbs(List<ShpNspTaskTb> shpNspTaskTbs) {
		this.shpNspTaskTbs = shpNspTaskTbs;
	}

	public ShpNspTaskTb addShpNspTaskTb(ShpNspTaskTb shpNspTaskTb) {
		getShpNspTaskTbs().add(shpNspTaskTb);
		shpNspTaskTb.setShpCustomerProfileTb(this);

		return shpNspTaskTb;
	}

	public ShpNspTaskTb removeShpNspTaskTb(ShpNspTaskTb shpNspTaskTb) {
		getShpNspTaskTbs().remove(shpNspTaskTb);
		shpNspTaskTb.setShpCustomerProfileTb(null);

		return shpNspTaskTb;
	}

	public List<ShpShipperGroupTb> getShpShipperGroupTbs() {
		return this.shpShipperGroupTbs;
	}

	public void setShpShipperGroupTbs(List<ShpShipperGroupTb> shpShipperGroupTbs) {
		this.shpShipperGroupTbs = shpShipperGroupTbs;
	}

	public ShpShipperGroupTb addShpShipperGroupTb(ShpShipperGroupTb shpShipperGroupTb) {
		getShpShipperGroupTbs().add(shpShipperGroupTb);
		shpShipperGroupTb.setShpCustomerProfileTb(this);

		return shpShipperGroupTb;
	}

	public ShpShipperGroupTb removeShpShipperGroupTb(ShpShipperGroupTb shpShipperGroupTb) {
		getShpShipperGroupTbs().remove(shpShipperGroupTb);
		shpShipperGroupTb.setShpCustomerProfileTb(null);

		return shpShipperGroupTb;
	}

	public List<ShpShipperTb> getShpShipperTbs() {
		return this.shpShipperTbs;
	}

	public void setShpShipperTbs(List<ShpShipperTb> shpShipperTbs) {
		this.shpShipperTbs = shpShipperTbs;
	}

	public ShpShipperTb addShpShipperTb(ShpShipperTb shpShipperTb) {
		getShpShipperTbs().add(shpShipperTb);
		shpShipperTb.setShpCustomerProfileTb(this);

		return shpShipperTb;
	}

	public ShpShipperTb removeShpShipperTb(ShpShipperTb shpShipperTb) {
		getShpShipperTbs().remove(shpShipperTb);
		shpShipperTb.setShpCustomerProfileTb(null);

		return shpShipperTb;
	}

	public List<ShpUserCustShipperTb> getShpUserCustShipperTbs() {
		return this.shpUserCustShipperTbs;
	}

	public void setShpUserCustShipperTbs(List<ShpUserCustShipperTb> shpUserCustShipperTbs) {
		this.shpUserCustShipperTbs = shpUserCustShipperTbs;
	}

	public ShpUserCustShipperTb addShpUserCustShipperTb(ShpUserCustShipperTb shpUserCustShipperTb) {
		getShpUserCustShipperTbs().add(shpUserCustShipperTb);
		shpUserCustShipperTb.setShpCustomerProfileTb(this);

		return shpUserCustShipperTb;
	}

	public ShpUserCustShipperTb removeShpUserCustShipperTb(ShpUserCustShipperTb shpUserCustShipperTb) {
		getShpUserCustShipperTbs().remove(shpUserCustShipperTb);
		shpUserCustShipperTb.setShpCustomerProfileTb(null);

		return shpUserCustShipperTb;
	}

}