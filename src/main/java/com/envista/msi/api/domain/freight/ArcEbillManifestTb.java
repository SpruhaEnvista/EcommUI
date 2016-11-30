package com.envista.msi.api.domain.freight;

import java.io.Serializable;
import javax.persistence.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the ARC_EBILL_MANIFEST_TB database table.
 * 
 */
@Entity
@Table(name="ARC_EBILL_MANIFEST_TB")
@NamedQuery(name="ArcEbillManifestTb.findAll", query="SELECT a FROM ArcEbillManifestTb a")
public class ArcEbillManifestTb implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="EBILL_MANIFEST_ID", unique=true, precision=22)
	private long ebillManifestId;

	@Column(name="ACT_WEIGHT", precision=22)
	private BigDecimal actWeight;

	@Column(name="ADDTL_HAND", length=1)
	private String addtlHand;

	@Column(name="ADJUST_REASON_ID", precision=22)
	private BigDecimal adjustReasonId;

	@Column(precision=22)
	private BigDecimal allocated;

	@Column(name="APPROVED_AMOUNT", precision=22)
	private BigDecimal approvedAmount;

	@Column(name="APPROVED_FLAG", precision=22)
	private BigDecimal approvedFlag;

	@Temporal(TemporalType.DATE)
	@Column(name="BILL_DATE")
	private Date billDate;

	@Column(name="BILL_OPT", precision=22)
	private BigDecimal billOpt;

	@Column(name="BILL_WEIGHT", precision=22)
	private BigDecimal billWeight;

	@Column(name="BILLING_IND", length=1)
	private String billingInd;

	@Column(name="BILLING_SOURCE", precision=22)
	private BigDecimal billingSource;

	@Column(name="BUNDLE_NUMBER", length=30)
	private String bundleNumber;

	@Column(name="CAL_CHARGES", precision=22)
	private BigDecimal calCharges;

	@Column(name="CALL_TAG", length=1)
	private String callTag;

	@Column(name="CARRIER_ID", precision=22)
	private BigDecimal carrierId;

	@Column(name="CHARGE_CODE", length=5)
	private String chargeCode;

	@Column(length=1)
	private String chargeback;

	@Temporal(TemporalType.DATE)
	@Column(name="CLOSE_EXCEPTION_DATE")
	private Date closeExceptionDate;

	@Column(name="CLOSE_EXCEPTION_REASON", length=400)
	private String closeExceptionReason;

	@Column(length=1)
	private String cod;

	@Column(name="COMPANY_ID", precision=22)
	private BigDecimal companyId;

	@Column(name="COMPOSITION_CODE", length=3)
	private String compositionCode;

	@Column(name="CONF_IND", length=1)
	private String confInd;

	@Column(name="CONS_CLEAR", length=1)
	private String consClear;

	@Column(length=200)
	private String consignee;

	@Column(name="CONSIGNEE_ADDRESS", length=200)
	private String consigneeAddress;

	@Column(name="CONSIGNEE_CITY", length=200)
	private String consigneeCity;

	@Column(name="CONSIGNEE_COMPANY", length=200)
	private String consigneeCompany;

	@Column(name="CONSIGNEE_COUNTRY", length=200)
	private String consigneeCountry;

	@Column(name="CONSIGNEE_SHIPPER_CODE", length=10)
	private String consigneeShipperCode;

	@Column(name="CONSIGNEE_ST", length=200)
	private String consigneeSt;

	@Column(name="CONSIGNEE_ZIP", length=200)
	private String consigneeZip;

	@Column(name="CONTRACT_NUMBER", length=10)
	private String contractNumber;

	@Temporal(TemporalType.DATE)
	@Column(name="CREATE_DATE")
	private Date createDate;

	@Column(name="CREATE_USER", length=15)
	private String createUser;

	@Column(precision=22)
	private BigDecimal credit;

	@Column(name="CURR_SUR", length=1)
	private String currSur;

	@Column(name="CURRENCY_COUNTRY_CODE", length=2)
	private String currencyCountryCode;

	@Column(name="CUSTOM_DEC_VAL", precision=22)
	private BigDecimal customDecVal;

	@Column(name="CUSTOMER_DEFINED_1", length=50)
	private String customerDefined1;

	@Column(name="CUSTOMER_DEFINED_2", length=50)
	private String customerDefined2;

	@Column(name="CUSTOMER_DEFINED_3", length=50)
	private String customerDefined3;

	@Column(name="CUSTOMER_DEPT_NUMBER", length=25)
	private String customerDeptNumber;

	@Column(name="CUSTOMER_INVOICE_NUMBER", length=25)
	private String customerInvoiceNumber;

	@Column(name="CUSTOMER_PO_NUMBER", length=30)
	private String customerPoNumber;

	@Column(name="CUSTOMS_ENTRY_NUMBER", length=100)
	private String customsEntryNumber;

	@Column(name="DEC_VALUE", length=1)
	private String decValue;

	@Column(name="DECLARED_VALUE", precision=22)
	private BigDecimal declaredValue;

	@Column(name="DEL_SUR", length=1)
	private String delSur;

	@Temporal(TemporalType.DATE)
	@Column(name="DELIVERY_DATE")
	private Date deliveryDate;

	@Column(name="DELIVERY_TIME", length=4)
	private String deliveryTime;

	@Column(length=25)
	private String department;

	@Column(name="DEPARTMENT_ID", precision=22)
	private BigDecimal departmentId;

	@Column(name="DEST_SUR", length=1)
	private String destSur;

	@Column(name="DETAIL_KEYED_DIM", length=17)
	private String detailKeyedDim;

	@Column(name="DEVICE_NAME", length=5)
	private String deviceName;

	@Column(name="DIM_DIVISOR", precision=22)
	private BigDecimal dimDivisor;

	@Column(name="DIM_LENGTH", precision=22)
	private BigDecimal dimLength;

	@Column(length=50)
	private String direction;

	@Column(name="DW_FIELD_INFORMATION", length=50)
	private String dwFieldInformation;

	@Column(name="EARLY_AM", length=1)
	private String earlyAm;

	@Column(name="EDI_TYPE", length=3)
	private String ediType;

	@Column(name="EXCHANGE_RATE", precision=22)
	private BigDecimal exchangeRate;

	@Column(length=1)
	private String export;

	@Column(name="FEDEX_GRD_EXP_IND", precision=22)
	private BigDecimal fedexGrdExpInd;

	@Column(precision=22)
	private BigDecimal gainshare;

	@Column(name="GL_ACCOUNT_CODE", length=1000)
	private String glAccountCode;

	@Column(name="GL_ACCOUNT_ID", precision=22)
	private BigDecimal glAccountId;

	@Temporal(TemporalType.DATE)
	@Column(name="GL_APPLIED_DATE")
	private Date glAppliedDate;

	@Column(name="GL_ATTEMPTED_FLAG", precision=22)
	private BigDecimal glAttemptedFlag;

	@Column(name="GL_SOURCE", length=30)
	private String glSource;

	@Temporal(TemporalType.DATE)
	@Column(name="GSR_FILE_DATE")
	private Date gsrFileDate;

	@Column(length=1)
	private String hazmat;

	@Column(precision=22)
	private BigDecimal height;

	@Column(name="HUND_PROCESS_FLAG", precision=22)
	private BigDecimal hundProcessFlag;

	@Column(name="HUNDRED_EBILL_MANIFEST_ID", precision=22)
	private BigDecimal hundredEbillManifestId;

	@Temporal(TemporalType.DATE)
	@Column(name="IMPORT_SCAN_DATE")
	private Date importScanDate;

	@Column(name="IMPORT_SCAN_LOCATION", length=200)
	private String importScanLocation;

	@Column(precision=22)
	private BigDecimal incentive;

	@Column(length=1)
	private String ind;

	@Column(length=1)
	private String indb;

	@Column(length=1)
	private String indc;

	@Column(length=1)
	private String indd;

	@Column(name="INTERNET_ID", length=10)
	private String internetId;

	@Column(name="INVALID_ACCT", length=1)
	private String invalidAcct;

	@Column(name="INVOICE_AMOUNT", precision=22)
	private BigDecimal invoiceAmount;

	@Column(name="INVOICE_BILLING_CURRENCY_CODE", length=3)
	private String invoiceBillingCurrencyCode;

	@Column(name="INVOICE_NUMBER", length=50)
	private String invoiceNumber;

	@Column(name="INVOICE_PAYMENT_CURRENCY_CODE", length=3)
	private String invoicePaymentCurrencyCode;

	@Column(name="IS_DOM", precision=1)
	private BigDecimal isDom;

	@Column(length=200)
	private String junk1;

	@Column(length=200)
	private String junk2;

	@Column(length=200)
	private String junk3;

	@Column(length=200)
	private String junk4;

	@Column(length=200)
	private String junk5;

	@Column(length=400)
	private String junk6;

	@Column(length=200)
	private String junk7;

	@Temporal(TemporalType.DATE)
	@Column(name="LAST_UPDATE_DATE")
	private Date lastUpdateDate;

	@Column(name="LAST_UPDATE_USER", length=15)
	private String lastUpdateUser;

	@Column(name="LINK_INVOICE_ID", precision=22)
	private BigDecimal linkInvoiceId;

	@Column(length=30)
	private String location;

	@Column(name="MANUAL_GL_CODE", length=40)
	private String manualGlCode;

	@Column(name="MISC_REASONS", length=800)
	private String miscReasons;

	@Column(length=80)
	private String miscellaneous1;

	@Column(length=80)
	private String miscellaneous2;

	@Column(length=80)
	private String miscellaneous3;

	@Column(length=80)
	private String miscellaneous4;

	@Column(length=80)
	private String miscellaneous5;

	@Column(precision=22)
	private BigDecimal na;

	@Column(name="NET_CHARGES", precision=22)
	private BigDecimal netCharges;

	@Column(name="NET_DUE", precision=22)
	private BigDecimal netDue;

	@Column(name="NET_DUE_FLAG", precision=22)
	private BigDecimal netDueFlag;

	@Column(name="PACKAGE_TYPE", precision=22)
	private BigDecimal packageType;

	@Column(precision=22)
	private BigDecimal paid;

	@Temporal(TemporalType.DATE)
	@Column(name="PAID_DATE")
	private Date paidDate;

	@Column(name="PARENT_ID", precision=22)
	private BigDecimal parentId;

	@Column(name="PASS_FLAG", precision=22)
	private BigDecimal passFlag;

	@Temporal(TemporalType.DATE)
	@Column(name="PICKUP_DATE")
	private Date pickupDate;

	@Column(precision=22)
	private BigDecimal pieces;

	@Column(name="PU_FEE", length=1)
	private String puFee;

	@Column(name="PU_RECORD", length=20)
	private String puRecord;

	@Column(precision=22)
	private BigDecimal qty;

	@Column(name="RECEIVED_BY", length=30)
	private String receivedBy;

	@Column(name="REFERENCE_NUMBER1", length=100)
	private String referenceNumber1;

	@Column(name="REFERENCE_NUMBER2", length=100)
	private String referenceNumber2;

	@Column(name="REFERENCE_NUMBER3", length=40)
	private String referenceNumber3;

	@Column(name="REFERENCE_NUMBER4", length=40)
	private String referenceNumber4;

	@Column(name="REFERENCE_NUMBER5", length=100)
	private String referenceNumber5;

	@Column(name="REMIT_CODE", length=40)
	private String remitCode;

	@Column(name="RMA_NUMBER", length=20)
	private String rmaNumber;

	@Column(name="RTP_FLAG", length=10)
	private String rtpFlag;

	@Column(name="SAT_DEL", length=1)
	private String satDel;

	@Column(name="SAT_PU", length=1)
	private String satPu;

	@Column(length=2)
	private String segment1;

	@Column(length=3)
	private String segment2;

	@Column(length=3)
	private String segment3;

	@Column(length=200)
	private String sender;

	@Column(name="SENDER_ADDRESS", length=200)
	private String senderAddress;

	@Column(name="SENDER_CITY", length=200)
	private String senderCity;

	@Column(name="SENDER_COMPANY", length=200)
	private String senderCompany;

	@Column(name="SENDER_COUNTRY", length=2)
	private String senderCountry;

	@Column(name="SENDER_SHIPPER_CODE", length=10)
	private String senderShipperCode;

	@Column(name="SENDER_ST", length=200)
	private String senderSt;

	@Column(name="SENDER_ZIP", length=200)
	private String senderZip;

	@Column(name="\"SERVICE\"", length=200)
	private String service;

	@Column(name="SHIPMENT_REFERENCE_NUMBER1", length=50)
	private String shipmentReferenceNumber1;

	@Column(name="SHIPMENT_REFERENCE_NUMBER2", length=50)
	private String shipmentReferenceNumber2;

	@Column(name="SHIPPER_CODE", length=12)
	private String shipperCode;

	@Column(name="SPLIT_DUTY", length=1)
	private String splitDuty;

	@Column(length=20)
	private String status;

	@Column(name="TRACKING_NUMBER", length=40)
	private String trackingNumber;

	@Column(name="TRAN_CODE", length=3)
	private String tranCode;

	@Column(name="UNIT_OF_ACTUAL_WEIGHT", length=1)
	private String unitOfActualWeight;

	@Column(name="UNIT_OF_BILL_WEIGHT", length=10)
	private String unitOfBillWeight;

	@Column(name="UNIT_OF_DIM", length=1)
	private String unitOfDim;

	@Column(precision=22)
	private BigDecimal width;

	@Column(name="\"ZONE\"", length=10)
	private String zone;

	//bi-directional many-to-one association to ArcEbillInvoiceTb
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="INVOICE_ID")
	private ArcEbillInvoiceTb arcEbillInvoiceTb;

	//bi-directional many-to-one association to ShpEbillManifestCustomTb
	@OneToMany(mappedBy="arcEbillManifestTb")
	private List<ShpEbillManifestCustomTb> shpEbillManifestCustomTbs;

	public ArcEbillManifestTb() {
	}

	public long getEbillManifestId() {
		return this.ebillManifestId;
	}

	public void setEbillManifestId(long ebillManifestId) {
		this.ebillManifestId = ebillManifestId;
	}

	public BigDecimal getActWeight() {
		return this.actWeight;
	}

	public void setActWeight(BigDecimal actWeight) {
		this.actWeight = actWeight;
	}

	public String getAddtlHand() {
		return this.addtlHand;
	}

	public void setAddtlHand(String addtlHand) {
		this.addtlHand = addtlHand;
	}

	public BigDecimal getAdjustReasonId() {
		return this.adjustReasonId;
	}

	public void setAdjustReasonId(BigDecimal adjustReasonId) {
		this.adjustReasonId = adjustReasonId;
	}

	public BigDecimal getAllocated() {
		return this.allocated;
	}

	public void setAllocated(BigDecimal allocated) {
		this.allocated = allocated;
	}

	public BigDecimal getApprovedAmount() {
		return this.approvedAmount;
	}

	public void setApprovedAmount(BigDecimal approvedAmount) {
		this.approvedAmount = approvedAmount;
	}

	public BigDecimal getApprovedFlag() {
		return this.approvedFlag;
	}

	public void setApprovedFlag(BigDecimal approvedFlag) {
		this.approvedFlag = approvedFlag;
	}

	public Date getBillDate() {
		return this.billDate;
	}

	public void setBillDate(Date billDate) {
		this.billDate = billDate;
	}

	public BigDecimal getBillOpt() {
		return this.billOpt;
	}

	public void setBillOpt(BigDecimal billOpt) {
		this.billOpt = billOpt;
	}

	public BigDecimal getBillWeight() {
		return this.billWeight;
	}

	public void setBillWeight(BigDecimal billWeight) {
		this.billWeight = billWeight;
	}

	public String getBillingInd() {
		return this.billingInd;
	}

	public void setBillingInd(String billingInd) {
		this.billingInd = billingInd;
	}

	public BigDecimal getBillingSource() {
		return this.billingSource;
	}

	public void setBillingSource(BigDecimal billingSource) {
		this.billingSource = billingSource;
	}

	public String getBundleNumber() {
		return this.bundleNumber;
	}

	public void setBundleNumber(String bundleNumber) {
		this.bundleNumber = bundleNumber;
	}

	public BigDecimal getCalCharges() {
		return this.calCharges;
	}

	public void setCalCharges(BigDecimal calCharges) {
		this.calCharges = calCharges;
	}

	public String getCallTag() {
		return this.callTag;
	}

	public void setCallTag(String callTag) {
		this.callTag = callTag;
	}

	public BigDecimal getCarrierId() {
		return this.carrierId;
	}

	public void setCarrierId(BigDecimal carrierId) {
		this.carrierId = carrierId;
	}

	public String getChargeCode() {
		return this.chargeCode;
	}

	public void setChargeCode(String chargeCode) {
		this.chargeCode = chargeCode;
	}

	public String getChargeback() {
		return this.chargeback;
	}

	public void setChargeback(String chargeback) {
		this.chargeback = chargeback;
	}

	public Date getCloseExceptionDate() {
		return this.closeExceptionDate;
	}

	public void setCloseExceptionDate(Date closeExceptionDate) {
		this.closeExceptionDate = closeExceptionDate;
	}

	public String getCloseExceptionReason() {
		return this.closeExceptionReason;
	}

	public void setCloseExceptionReason(String closeExceptionReason) {
		this.closeExceptionReason = closeExceptionReason;
	}

	public String getCod() {
		return this.cod;
	}

	public void setCod(String cod) {
		this.cod = cod;
	}

	public BigDecimal getCompanyId() {
		return this.companyId;
	}

	public void setCompanyId(BigDecimal companyId) {
		this.companyId = companyId;
	}

	public String getCompositionCode() {
		return this.compositionCode;
	}

	public void setCompositionCode(String compositionCode) {
		this.compositionCode = compositionCode;
	}

	public String getConfInd() {
		return this.confInd;
	}

	public void setConfInd(String confInd) {
		this.confInd = confInd;
	}

	public String getConsClear() {
		return this.consClear;
	}

	public void setConsClear(String consClear) {
		this.consClear = consClear;
	}

	public String getConsignee() {
		return this.consignee;
	}

	public void setConsignee(String consignee) {
		this.consignee = consignee;
	}

	public String getConsigneeAddress() {
		return this.consigneeAddress;
	}

	public void setConsigneeAddress(String consigneeAddress) {
		this.consigneeAddress = consigneeAddress;
	}

	public String getConsigneeCity() {
		return this.consigneeCity;
	}

	public void setConsigneeCity(String consigneeCity) {
		this.consigneeCity = consigneeCity;
	}

	public String getConsigneeCompany() {
		return this.consigneeCompany;
	}

	public void setConsigneeCompany(String consigneeCompany) {
		this.consigneeCompany = consigneeCompany;
	}

	public String getConsigneeCountry() {
		return this.consigneeCountry;
	}

	public void setConsigneeCountry(String consigneeCountry) {
		this.consigneeCountry = consigneeCountry;
	}

	public String getConsigneeShipperCode() {
		return this.consigneeShipperCode;
	}

	public void setConsigneeShipperCode(String consigneeShipperCode) {
		this.consigneeShipperCode = consigneeShipperCode;
	}

	public String getConsigneeSt() {
		return this.consigneeSt;
	}

	public void setConsigneeSt(String consigneeSt) {
		this.consigneeSt = consigneeSt;
	}

	public String getConsigneeZip() {
		return this.consigneeZip;
	}

	public void setConsigneeZip(String consigneeZip) {
		this.consigneeZip = consigneeZip;
	}

	public String getContractNumber() {
		return this.contractNumber;
	}

	public void setContractNumber(String contractNumber) {
		this.contractNumber = contractNumber;
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

	public BigDecimal getCredit() {
		return this.credit;
	}

	public void setCredit(BigDecimal credit) {
		this.credit = credit;
	}

	public String getCurrSur() {
		return this.currSur;
	}

	public void setCurrSur(String currSur) {
		this.currSur = currSur;
	}

	public String getCurrencyCountryCode() {
		return this.currencyCountryCode;
	}

	public void setCurrencyCountryCode(String currencyCountryCode) {
		this.currencyCountryCode = currencyCountryCode;
	}

	public BigDecimal getCustomDecVal() {
		return this.customDecVal;
	}

	public void setCustomDecVal(BigDecimal customDecVal) {
		this.customDecVal = customDecVal;
	}

	public String getCustomerDefined1() {
		return this.customerDefined1;
	}

	public void setCustomerDefined1(String customerDefined1) {
		this.customerDefined1 = customerDefined1;
	}

	public String getCustomerDefined2() {
		return this.customerDefined2;
	}

	public void setCustomerDefined2(String customerDefined2) {
		this.customerDefined2 = customerDefined2;
	}

	public String getCustomerDefined3() {
		return this.customerDefined3;
	}

	public void setCustomerDefined3(String customerDefined3) {
		this.customerDefined3 = customerDefined3;
	}

	public String getCustomerDeptNumber() {
		return this.customerDeptNumber;
	}

	public void setCustomerDeptNumber(String customerDeptNumber) {
		this.customerDeptNumber = customerDeptNumber;
	}

	public String getCustomerInvoiceNumber() {
		return this.customerInvoiceNumber;
	}

	public void setCustomerInvoiceNumber(String customerInvoiceNumber) {
		this.customerInvoiceNumber = customerInvoiceNumber;
	}

	public String getCustomerPoNumber() {
		return this.customerPoNumber;
	}

	public void setCustomerPoNumber(String customerPoNumber) {
		this.customerPoNumber = customerPoNumber;
	}

	public String getCustomsEntryNumber() {
		return this.customsEntryNumber;
	}

	public void setCustomsEntryNumber(String customsEntryNumber) {
		this.customsEntryNumber = customsEntryNumber;
	}

	public String getDecValue() {
		return this.decValue;
	}

	public void setDecValue(String decValue) {
		this.decValue = decValue;
	}

	public BigDecimal getDeclaredValue() {
		return this.declaredValue;
	}

	public void setDeclaredValue(BigDecimal declaredValue) {
		this.declaredValue = declaredValue;
	}

	public String getDelSur() {
		return this.delSur;
	}

	public void setDelSur(String delSur) {
		this.delSur = delSur;
	}

	public Date getDeliveryDate() {
		return this.deliveryDate;
	}

	public void setDeliveryDate(Date deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	public String getDeliveryTime() {
		return this.deliveryTime;
	}

	public void setDeliveryTime(String deliveryTime) {
		this.deliveryTime = deliveryTime;
	}

	public String getDepartment() {
		return this.department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public BigDecimal getDepartmentId() {
		return this.departmentId;
	}

	public void setDepartmentId(BigDecimal departmentId) {
		this.departmentId = departmentId;
	}

	public String getDestSur() {
		return this.destSur;
	}

	public void setDestSur(String destSur) {
		this.destSur = destSur;
	}

	public String getDetailKeyedDim() {
		return this.detailKeyedDim;
	}

	public void setDetailKeyedDim(String detailKeyedDim) {
		this.detailKeyedDim = detailKeyedDim;
	}

	public String getDeviceName() {
		return this.deviceName;
	}

	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}

	public BigDecimal getDimDivisor() {
		return this.dimDivisor;
	}

	public void setDimDivisor(BigDecimal dimDivisor) {
		this.dimDivisor = dimDivisor;
	}

	public BigDecimal getDimLength() {
		return this.dimLength;
	}

	public void setDimLength(BigDecimal dimLength) {
		this.dimLength = dimLength;
	}

	public String getDirection() {
		return this.direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}

	public String getDwFieldInformation() {
		return this.dwFieldInformation;
	}

	public void setDwFieldInformation(String dwFieldInformation) {
		this.dwFieldInformation = dwFieldInformation;
	}

	public String getEarlyAm() {
		return this.earlyAm;
	}

	public void setEarlyAm(String earlyAm) {
		this.earlyAm = earlyAm;
	}

	public String getEdiType() {
		return this.ediType;
	}

	public void setEdiType(String ediType) {
		this.ediType = ediType;
	}

	public BigDecimal getExchangeRate() {
		return this.exchangeRate;
	}

	public void setExchangeRate(BigDecimal exchangeRate) {
		this.exchangeRate = exchangeRate;
	}

	public String getExport() {
		return this.export;
	}

	public void setExport(String export) {
		this.export = export;
	}

	public BigDecimal getFedexGrdExpInd() {
		return this.fedexGrdExpInd;
	}

	public void setFedexGrdExpInd(BigDecimal fedexGrdExpInd) {
		this.fedexGrdExpInd = fedexGrdExpInd;
	}

	public BigDecimal getGainshare() {
		return this.gainshare;
	}

	public void setGainshare(BigDecimal gainshare) {
		this.gainshare = gainshare;
	}

	public String getGlAccountCode() {
		return this.glAccountCode;
	}

	public void setGlAccountCode(String glAccountCode) {
		this.glAccountCode = glAccountCode;
	}

	public BigDecimal getGlAccountId() {
		return this.glAccountId;
	}

	public void setGlAccountId(BigDecimal glAccountId) {
		this.glAccountId = glAccountId;
	}

	public Date getGlAppliedDate() {
		return this.glAppliedDate;
	}

	public void setGlAppliedDate(Date glAppliedDate) {
		this.glAppliedDate = glAppliedDate;
	}

	public BigDecimal getGlAttemptedFlag() {
		return this.glAttemptedFlag;
	}

	public void setGlAttemptedFlag(BigDecimal glAttemptedFlag) {
		this.glAttemptedFlag = glAttemptedFlag;
	}

	public String getGlSource() {
		return this.glSource;
	}

	public void setGlSource(String glSource) {
		this.glSource = glSource;
	}

	public Date getGsrFileDate() {
		return this.gsrFileDate;
	}

	public void setGsrFileDate(Date gsrFileDate) {
		this.gsrFileDate = gsrFileDate;
	}

	public String getHazmat() {
		return this.hazmat;
	}

	public void setHazmat(String hazmat) {
		this.hazmat = hazmat;
	}

	public BigDecimal getHeight() {
		return this.height;
	}

	public void setHeight(BigDecimal height) {
		this.height = height;
	}

	public BigDecimal getHundProcessFlag() {
		return this.hundProcessFlag;
	}

	public void setHundProcessFlag(BigDecimal hundProcessFlag) {
		this.hundProcessFlag = hundProcessFlag;
	}

	public BigDecimal getHundredEbillManifestId() {
		return this.hundredEbillManifestId;
	}

	public void setHundredEbillManifestId(BigDecimal hundredEbillManifestId) {
		this.hundredEbillManifestId = hundredEbillManifestId;
	}

	public Date getImportScanDate() {
		return this.importScanDate;
	}

	public void setImportScanDate(Date importScanDate) {
		this.importScanDate = importScanDate;
	}

	public String getImportScanLocation() {
		return this.importScanLocation;
	}

	public void setImportScanLocation(String importScanLocation) {
		this.importScanLocation = importScanLocation;
	}

	public BigDecimal getIncentive() {
		return this.incentive;
	}

	public void setIncentive(BigDecimal incentive) {
		this.incentive = incentive;
	}

	public String getInd() {
		return this.ind;
	}

	public void setInd(String ind) {
		this.ind = ind;
	}

	public String getIndb() {
		return this.indb;
	}

	public void setIndb(String indb) {
		this.indb = indb;
	}

	public String getIndc() {
		return this.indc;
	}

	public void setIndc(String indc) {
		this.indc = indc;
	}

	public String getIndd() {
		return this.indd;
	}

	public void setIndd(String indd) {
		this.indd = indd;
	}

	public String getInternetId() {
		return this.internetId;
	}

	public void setInternetId(String internetId) {
		this.internetId = internetId;
	}

	public String getInvalidAcct() {
		return this.invalidAcct;
	}

	public void setInvalidAcct(String invalidAcct) {
		this.invalidAcct = invalidAcct;
	}

	public BigDecimal getInvoiceAmount() {
		return this.invoiceAmount;
	}

	public void setInvoiceAmount(BigDecimal invoiceAmount) {
		this.invoiceAmount = invoiceAmount;
	}

	public String getInvoiceBillingCurrencyCode() {
		return this.invoiceBillingCurrencyCode;
	}

	public void setInvoiceBillingCurrencyCode(String invoiceBillingCurrencyCode) {
		this.invoiceBillingCurrencyCode = invoiceBillingCurrencyCode;
	}

	public String getInvoiceNumber() {
		return this.invoiceNumber;
	}

	public void setInvoiceNumber(String invoiceNumber) {
		this.invoiceNumber = invoiceNumber;
	}

	public String getInvoicePaymentCurrencyCode() {
		return this.invoicePaymentCurrencyCode;
	}

	public void setInvoicePaymentCurrencyCode(String invoicePaymentCurrencyCode) {
		this.invoicePaymentCurrencyCode = invoicePaymentCurrencyCode;
	}

	public BigDecimal getIsDom() {
		return this.isDom;
	}

	public void setIsDom(BigDecimal isDom) {
		this.isDom = isDom;
	}

	public String getJunk1() {
		return this.junk1;
	}

	public void setJunk1(String junk1) {
		this.junk1 = junk1;
	}

	public String getJunk2() {
		return this.junk2;
	}

	public void setJunk2(String junk2) {
		this.junk2 = junk2;
	}

	public String getJunk3() {
		return this.junk3;
	}

	public void setJunk3(String junk3) {
		this.junk3 = junk3;
	}

	public String getJunk4() {
		return this.junk4;
	}

	public void setJunk4(String junk4) {
		this.junk4 = junk4;
	}

	public String getJunk5() {
		return this.junk5;
	}

	public void setJunk5(String junk5) {
		this.junk5 = junk5;
	}

	public String getJunk6() {
		return this.junk6;
	}

	public void setJunk6(String junk6) {
		this.junk6 = junk6;
	}

	public String getJunk7() {
		return this.junk7;
	}

	public void setJunk7(String junk7) {
		this.junk7 = junk7;
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

	public BigDecimal getLinkInvoiceId() {
		return this.linkInvoiceId;
	}

	public void setLinkInvoiceId(BigDecimal linkInvoiceId) {
		this.linkInvoiceId = linkInvoiceId;
	}

	public String getLocation() {
		return this.location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getManualGlCode() {
		return this.manualGlCode;
	}

	public void setManualGlCode(String manualGlCode) {
		this.manualGlCode = manualGlCode;
	}

	public String getMiscReasons() {
		return this.miscReasons;
	}

	public void setMiscReasons(String miscReasons) {
		this.miscReasons = miscReasons;
	}

	public String getMiscellaneous1() {
		return this.miscellaneous1;
	}

	public void setMiscellaneous1(String miscellaneous1) {
		this.miscellaneous1 = miscellaneous1;
	}

	public String getMiscellaneous2() {
		return this.miscellaneous2;
	}

	public void setMiscellaneous2(String miscellaneous2) {
		this.miscellaneous2 = miscellaneous2;
	}

	public String getMiscellaneous3() {
		return this.miscellaneous3;
	}

	public void setMiscellaneous3(String miscellaneous3) {
		this.miscellaneous3 = miscellaneous3;
	}

	public String getMiscellaneous4() {
		return this.miscellaneous4;
	}

	public void setMiscellaneous4(String miscellaneous4) {
		this.miscellaneous4 = miscellaneous4;
	}

	public String getMiscellaneous5() {
		return this.miscellaneous5;
	}

	public void setMiscellaneous5(String miscellaneous5) {
		this.miscellaneous5 = miscellaneous5;
	}

	public BigDecimal getNa() {
		return this.na;
	}

	public void setNa(BigDecimal na) {
		this.na = na;
	}

	public BigDecimal getNetCharges() {
		return this.netCharges;
	}

	public void setNetCharges(BigDecimal netCharges) {
		this.netCharges = netCharges;
	}

	public BigDecimal getNetDue() {
		return this.netDue;
	}

	public void setNetDue(BigDecimal netDue) {
		this.netDue = netDue;
	}

	public BigDecimal getNetDueFlag() {
		return this.netDueFlag;
	}

	public void setNetDueFlag(BigDecimal netDueFlag) {
		this.netDueFlag = netDueFlag;
	}

	public BigDecimal getPackageType() {
		return this.packageType;
	}

	public void setPackageType(BigDecimal packageType) {
		this.packageType = packageType;
	}

	public BigDecimal getPaid() {
		return this.paid;
	}

	public void setPaid(BigDecimal paid) {
		this.paid = paid;
	}

	public Date getPaidDate() {
		return this.paidDate;
	}

	public void setPaidDate(Date paidDate) {
		this.paidDate = paidDate;
	}

	public BigDecimal getParentId() {
		return this.parentId;
	}

	public void setParentId(BigDecimal parentId) {
		this.parentId = parentId;
	}

	public BigDecimal getPassFlag() {
		return this.passFlag;
	}

	public void setPassFlag(BigDecimal passFlag) {
		this.passFlag = passFlag;
	}

	public Date getPickupDate() {
		return this.pickupDate;
	}

	public void setPickupDate(Date pickupDate) {
		this.pickupDate = pickupDate;
	}

	public BigDecimal getPieces() {
		return this.pieces;
	}

	public void setPieces(BigDecimal pieces) {
		this.pieces = pieces;
	}

	public String getPuFee() {
		return this.puFee;
	}

	public void setPuFee(String puFee) {
		this.puFee = puFee;
	}

	public String getPuRecord() {
		return this.puRecord;
	}

	public void setPuRecord(String puRecord) {
		this.puRecord = puRecord;
	}

	public BigDecimal getQty() {
		return this.qty;
	}

	public void setQty(BigDecimal qty) {
		this.qty = qty;
	}

	public String getReceivedBy() {
		return this.receivedBy;
	}

	public void setReceivedBy(String receivedBy) {
		this.receivedBy = receivedBy;
	}

	public String getReferenceNumber1() {
		return this.referenceNumber1;
	}

	public void setReferenceNumber1(String referenceNumber1) {
		this.referenceNumber1 = referenceNumber1;
	}

	public String getReferenceNumber2() {
		return this.referenceNumber2;
	}

	public void setReferenceNumber2(String referenceNumber2) {
		this.referenceNumber2 = referenceNumber2;
	}

	public String getReferenceNumber3() {
		return this.referenceNumber3;
	}

	public void setReferenceNumber3(String referenceNumber3) {
		this.referenceNumber3 = referenceNumber3;
	}

	public String getReferenceNumber4() {
		return this.referenceNumber4;
	}

	public void setReferenceNumber4(String referenceNumber4) {
		this.referenceNumber4 = referenceNumber4;
	}

	public String getReferenceNumber5() {
		return this.referenceNumber5;
	}

	public void setReferenceNumber5(String referenceNumber5) {
		this.referenceNumber5 = referenceNumber5;
	}

	public String getRemitCode() {
		return this.remitCode;
	}

	public void setRemitCode(String remitCode) {
		this.remitCode = remitCode;
	}

	public String getRmaNumber() {
		return this.rmaNumber;
	}

	public void setRmaNumber(String rmaNumber) {
		this.rmaNumber = rmaNumber;
	}

	public String getRtpFlag() {
		return this.rtpFlag;
	}

	public void setRtpFlag(String rtpFlag) {
		this.rtpFlag = rtpFlag;
	}

	public String getSatDel() {
		return this.satDel;
	}

	public void setSatDel(String satDel) {
		this.satDel = satDel;
	}

	public String getSatPu() {
		return this.satPu;
	}

	public void setSatPu(String satPu) {
		this.satPu = satPu;
	}

	public String getSegment1() {
		return this.segment1;
	}

	public void setSegment1(String segment1) {
		this.segment1 = segment1;
	}

	public String getSegment2() {
		return this.segment2;
	}

	public void setSegment2(String segment2) {
		this.segment2 = segment2;
	}

	public String getSegment3() {
		return this.segment3;
	}

	public void setSegment3(String segment3) {
		this.segment3 = segment3;
	}

	public String getSender() {
		return this.sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public String getSenderAddress() {
		return this.senderAddress;
	}

	public void setSenderAddress(String senderAddress) {
		this.senderAddress = senderAddress;
	}

	public String getSenderCity() {
		return this.senderCity;
	}

	public void setSenderCity(String senderCity) {
		this.senderCity = senderCity;
	}

	public String getSenderCompany() {
		return this.senderCompany;
	}

	public void setSenderCompany(String senderCompany) {
		this.senderCompany = senderCompany;
	}

	public String getSenderCountry() {
		return this.senderCountry;
	}

	public void setSenderCountry(String senderCountry) {
		this.senderCountry = senderCountry;
	}

	public String getSenderShipperCode() {
		return this.senderShipperCode;
	}

	public void setSenderShipperCode(String senderShipperCode) {
		this.senderShipperCode = senderShipperCode;
	}

	public String getSenderSt() {
		return this.senderSt;
	}

	public void setSenderSt(String senderSt) {
		this.senderSt = senderSt;
	}

	public String getSenderZip() {
		return this.senderZip;
	}

	public void setSenderZip(String senderZip) {
		this.senderZip = senderZip;
	}

	public String getService() {
		return this.service;
	}

	public void setService(String service) {
		this.service = service;
	}

	public String getShipmentReferenceNumber1() {
		return this.shipmentReferenceNumber1;
	}

	public void setShipmentReferenceNumber1(String shipmentReferenceNumber1) {
		this.shipmentReferenceNumber1 = shipmentReferenceNumber1;
	}

	public String getShipmentReferenceNumber2() {
		return this.shipmentReferenceNumber2;
	}

	public void setShipmentReferenceNumber2(String shipmentReferenceNumber2) {
		this.shipmentReferenceNumber2 = shipmentReferenceNumber2;
	}

	public String getShipperCode() {
		return this.shipperCode;
	}

	public void setShipperCode(String shipperCode) {
		this.shipperCode = shipperCode;
	}

	public String getSplitDuty() {
		return this.splitDuty;
	}

	public void setSplitDuty(String splitDuty) {
		this.splitDuty = splitDuty;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getTrackingNumber() {
		return this.trackingNumber;
	}

	public void setTrackingNumber(String trackingNumber) {
		this.trackingNumber = trackingNumber;
	}

	public String getTranCode() {
		return this.tranCode;
	}

	public void setTranCode(String tranCode) {
		this.tranCode = tranCode;
	}

	public String getUnitOfActualWeight() {
		return this.unitOfActualWeight;
	}

	public void setUnitOfActualWeight(String unitOfActualWeight) {
		this.unitOfActualWeight = unitOfActualWeight;
	}

	public String getUnitOfBillWeight() {
		return this.unitOfBillWeight;
	}

	public void setUnitOfBillWeight(String unitOfBillWeight) {
		this.unitOfBillWeight = unitOfBillWeight;
	}

	public String getUnitOfDim() {
		return this.unitOfDim;
	}

	public void setUnitOfDim(String unitOfDim) {
		this.unitOfDim = unitOfDim;
	}

	public BigDecimal getWidth() {
		return this.width;
	}

	public void setWidth(BigDecimal width) {
		this.width = width;
	}

	public String getZone() {
		return this.zone;
	}

	public void setZone(String zone) {
		this.zone = zone;
	}

	public ArcEbillInvoiceTb getArcEbillInvoiceTb() {
		return this.arcEbillInvoiceTb;
	}

	public void setArcEbillInvoiceTb(ArcEbillInvoiceTb arcEbillInvoiceTb) {
		this.arcEbillInvoiceTb = arcEbillInvoiceTb;
	}

	public List<ShpEbillManifestCustomTb> getShpEbillManifestCustomTbs() {
		return this.shpEbillManifestCustomTbs;
	}

	public void setShpEbillManifestCustomTbs(List<ShpEbillManifestCustomTb> shpEbillManifestCustomTbs) {
		this.shpEbillManifestCustomTbs = shpEbillManifestCustomTbs;
	}

	public ShpEbillManifestCustomTb addShpEbillManifestCustomTb(ShpEbillManifestCustomTb shpEbillManifestCustomTb) {
		getShpEbillManifestCustomTbs().add(shpEbillManifestCustomTb);
		shpEbillManifestCustomTb.setArcEbillManifestTb(this);

		return shpEbillManifestCustomTb;
	}

	public ShpEbillManifestCustomTb removeShpEbillManifestCustomTb(ShpEbillManifestCustomTb shpEbillManifestCustomTb) {
		getShpEbillManifestCustomTbs().remove(shpEbillManifestCustomTb);
		shpEbillManifestCustomTb.setArcEbillManifestTb(null);

		return shpEbillManifestCustomTb;
	}

}