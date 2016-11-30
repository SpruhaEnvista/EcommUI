package com.envista.msi.api.domain.freight;

import java.io.Serializable;
import javax.persistence.*;

/*import org.codehaus.jackson.annotate.JsonBackReference;
import org.codehaus.jackson.annotate.JsonManagedReference;*/

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the SHP_NSP_INVOICE_DETAILS_TB database table.
 * 
 */
@Entity
@Table(name="SHP_NSP_INVOICE_DETAILS_TB")
@NamedQuery(name="ShpNspInvoiceDetailsTb.findAll", query="SELECT s FROM ShpNspInvoiceDetailsTb s")
public class ShpNspInvoiceDetailsTb implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="NSP_INVOICE_DETAILS_ID", unique=true, nullable=false, precision=22)
	private long nspInvoiceDetailsId;

	@Column(name="ACCOUNT_NUMBER", length=100)
	private String accountNumber;

	@Column(name="ACTUAL_KILOMETERS", precision=22)
	private BigDecimal actualKilometers;

	@Column(name="ACTUAL_MILES", precision=22)
	private BigDecimal actualMiles;

	@Column(name="ADDRESS_TYPE", length=1)
	private String addressType;

	@Column(name="AUDITOR_COMMENTS", length=2500)
	private String auditorComments;

	@Column(name="AVERAGE_TRANSIT_DAYS", precision=22)
	private BigDecimal averageTransitDays;

	@Column(name="BALANCE_AMOUNT", precision=22)
	private BigDecimal balanceAmount;

	@Temporal(TemporalType.DATE)
	@Column(name="BILL_DATE")
	private Date billDate;

	@Column(name="BILL_TO_LOCATION_CODE", length=20)
	private String billToLocationCode;

	@Column(name="BILLED_MILES", precision=22)
	private BigDecimal billedMiles;

	@Column(name="BILLTO_ADDRESS_1", length=100)
	private String billtoAddress1;

	@Column(name="BILLTO_ADDRESS_2", length=100)
	private String billtoAddress2;

	@Column(name="BILLTO_ADDRESS_3", length=100)
	private String billtoAddress3;

	@Column(name="BILLTO_CITY", length=100)
	private String billtoCity;

	@Column(name="BILLTO_COUNTRY", length=100)
	private String billtoCountry;

	@Column(name="BILLTO_NAME", length=100)
	private String billtoName;

	@Column(name="BILLTO_STATE", length=100)
	private String billtoState;

	@Column(name="BILLTO_ZIPCODE", length=100)
	private String billtoZipcode;

	@Column(name="BOL_NUMBER", length=100)
	private String bolNumber;

	@Column(name="BROK_PARENT_INV_ID", precision=22)
	private BigDecimal brokParentInvId;

	@Column(name="CALCULATED_MILES", precision=22)
	private BigDecimal calculatedMiles;

	@Column(name="CARRIER_COMMENTS", length=3000)
	private String carrierComments;

	@Column(name="CHECK_AMOUNT", precision=22)
	private BigDecimal checkAmount;

	@Temporal(TemporalType.DATE)
	@Column(name="CHECK_DATE")
	private Date checkDate;

	@Column(name="CHECK_NO", length=20)
	private String checkNo;

	@Column(name="CHILD_CARRIER_NAME", length=500)
	private String childCarrierName;

	@Column(name="CLOSED_BY", length=250)
	private String closedBy;

	@Temporal(TemporalType.DATE)
	@Column(name="CLOSED_DATE")
	private Date closedDate;

	@Column(name="COD_AMOUNT", precision=22)
	private BigDecimal codAmount;

	@Column(name="CONSOL_PARENT_ID", precision=22)
	private BigDecimal consolParentId;

	@Column(name="COUNT_OF_PALLETS_RELOADED", precision=22)
	private BigDecimal countOfPalletsReloaded;

	@Temporal(TemporalType.DATE)
	@Column(name="CREATE_DATE")
	private Date createDate;

	@Column(name="CREATE_USER", length=100)
	private String createUser;

	@Column(name="CREDIT_MEMO", length=100)
	private String creditMemo;

	@Column(name="CURRENCY_CODE", precision=22)
	private BigDecimal currencyCode;

	@Temporal(TemporalType.DATE)
	@Column(name="CURRENCY_EXCHANGE_DATE")
	private Date currencyExchangeDate;

	@Column(name="CURRENCY_EXCHANGE_RATE", precision=22)
	private BigDecimal currencyExchangeRate;

	@Column(name="CUST_ACTION", precision=22)
	private BigDecimal custAction;

	@Column(name="CUST_ACTION_BY", length=20)
	private String custActionBy;

	@Temporal(TemporalType.DATE)
	@Column(name="CUST_ACTION_DATE")
	private Date custActionDate;

	@Column(name="CUST_ACTIVITY_HIST_ID", precision=22)
	private BigDecimal custActivityHistId;

	@Column(name="CUSTOMER_COMMENTS", length=2500)
	private String customerComments;

	@Column(name="CUSTOMER_DEFINED_1", length=50)
	private String customerDefined1;

	@Column(name="CUSTOMER_DEFINED_2", length=50)
	private String customerDefined2;

	@Column(name="CUSTOMER_DEFINED_3", length=50)
	private String customerDefined3;

	@Column(name="CUSTOMS_DOCUMENT_NUMBER", length=20)
	private String customsDocumentNumber;

	@Column(name="DATA_ENTRY_COMMENTS", length=2500)
	private String dataEntryComments;

	@Temporal(TemporalType.DATE)
	@Column(name="DELIVERY_DATE")
	private Date deliveryDate;

	@Column(length=20)
	private String department;

	@Column(length=50)
	private String direction;

	@Column(name="DISTANCE_UOM", precision=5)
	private BigDecimal distanceUom;

	@Column(name="DUP_CHECK_QUERY", length=4000)
	private String dupCheckQuery;

	@Column(name="DUP_CHK_INVOICE_ID", length=500)
	private String dupChkInvoiceId;

	@Column(name="DW_EXTRACT_FLAG", precision=22)
	private BigDecimal dwExtractFlag;

	@Column(name="EDI_FILE_NAME", length=1500)
	private String ediFileName;

	@Column(name="EQUIPMENT_TYPE", precision=22)
	private BigDecimal equipmentType;

	@Column(name="EXCEPTION_REASON", precision=22)
	private BigDecimal exceptionReason;

	@Column(name="EXPORT_CODE", length=100)
	private String exportCode;

	/*@Column(name="FAILED_FORMAT", length=300)
	private String failedFormat;*/

	@Column(name="FREIGHT_INVOICE_TYPE", precision=22)
	private BigDecimal freightInvoiceType;

	@Column(name="FTPSERVER_LOG_ID", precision=22)
	private BigDecimal ftpserverLogId;

	@Column(name="GAIN_SHARE", precision=22)
	private BigDecimal gainShare;

	@Column(name="GL_ACCOUNTS_CODE", length=1000)
	private String glAccountsCode;

	@Temporal(TemporalType.DATE)
	@Column(name="GL_APPLIED_DATE")
	private Date glAppliedDate;

	@Column(name="GL_CODING_ATTEMPTED", precision=22)
	private BigDecimal glCodingAttempted;

	@Column(name="GL_SOURCE", length=20)
	private String glSource;

	@Column(name="GLB_EQUIP_TYPE", length=100)
	private String glbEquipType;

	@Column(name="GSA_CARRIER", length=20)
	private String gsaCarrier;

	@Temporal(TemporalType.DATE)
	@Column(name="GUARANTEED_DATE")
	private Date guaranteedDate;

	@Column(name="GUARANTEED_SERVICE", precision=22)
	private BigDecimal guaranteedService;

	@Column(precision=22)
	private BigDecimal hazmat;

	@Temporal(TemporalType.DATE)
	@Column(name="HOUSE_BOL_DATE")
	private Date houseBolDate;

	@Column(name="HOUSE_BOL_NUMBER", length=20)
	private String houseBolNumber;

	@Column(name="IMPORT_CODE", length=100)
	private String importCode;

	@Column(name="INCO_TERM_CODE", precision=22)
	private BigDecimal incoTermCode;

	@Column(name="INCO_TERM_POINT", length=20)
	private String incoTermPoint;

	@Temporal(TemporalType.DATE)
	@Column(name="INTERLINE_BOL_DATE")
	private Date interlineBolDate;

	@Column(name="INTERLINE_BOL_NUMBER", length=20)
	private String interlineBolNumber;

	@Column(name="INTERLINE_CARRIER", precision=22)
	private BigDecimal interlineCarrier;

	@Temporal(TemporalType.DATE)
	@Column(name="INTERLINE_DELIVERY_DATE")
	private Date interlineDeliveryDate;

	@Column(name="INTERLINE_VESSEL_NAME", length=500)
	private String interlineVesselName;

	@Column(name="INTERLINE_VOYAGE_NUMBER", length=500)
	private String interlineVoyageNumber;

	@Column(name="INV_PAGE_COUNT", precision=22)
	private BigDecimal invPageCount;

	@Temporal(TemporalType.DATE)
	@Column(name="INVOICE_DUE_DATE")
	private Date invoiceDueDate;

	@Column(name="INVOICE_HS_TAX_AMOUNT", precision=22)
	private BigDecimal invoiceHsTaxAmount;

	@Column(name="INVOICE_ISA_NUMBER", length=250)
	private String invoiceIsaNumber;

	@Column(name="INVOICE_NUMBER", length=100)
	private String invoiceNumber;

	@Column(name="INVOICE_TAX1_AMOUNT", precision=22)
	private BigDecimal invoiceTax1Amount;

	@Column(name="INVOICE_TAX2_AMOUNT", precision=22)
	private BigDecimal invoiceTax2Amount;

	@Column(name="INVOICE_TYPE", length=2)
	private String invoiceType;

	@Column(name="IS_ACCESSORIALS_CHECKED", precision=22)
	private BigDecimal isAccessorialsChecked;

	@Column(name="IS_BILL_OPTION_CORRECT", precision=22)
	private BigDecimal isBillOptionCorrect;

	/*@Column(name="IS_BLACKLISTED", precision=22)
	private BigDecimal isBlacklisted;*/

	@Column(name="IS_DOCUMENTATION_CHECKED", precision=22)
	private BigDecimal isDocumentationChecked;

	@Column(name="IS_DUPLICATE_BILLING_CHECKED", precision=22)
	private BigDecimal isDuplicateBillingChecked;

	@Column(name="IS_FREIGHT_CHARGE_CHECKED", precision=22)
	private BigDecimal isFreightChargeChecked;

	@Column(name="IS_FUEL_SURCHARGES_CHECKED", precision=22)
	private BigDecimal isFuelSurchargesChecked;

	@Column(name="LADING_DESCRIPTION", length=4000)
	private String ladingDescription;

	@Column(name="LANE_DESCRIPTION", length=400)
	private String laneDescription;

	@Column(name="LANE_ID", length=400)
	private String laneId;

	@Temporal(TemporalType.DATE)
	@Column(name="LAST_UPDATE_DATE")
	private Date lastUpdateDate;

	@Column(name="LAST_UPDATE_USER", length=100)
	private String lastUpdateUser;

	@Column(name="LINKED_DOCUMENTS", length=4000)
	private String linkedDocuments;

	@Temporal(TemporalType.DATE)
	@Column(name="LOAD_MATCH_DATE")
	private Date loadMatchDate;

	@Temporal(TemporalType.DATE)
	@Column(name="LOAD_MATCH_EXCP_DATE")
	private Date loadMatchExcpDate;

	@Column(name="LOAD_MATCHED", precision=22)
	private BigDecimal loadMatched;

	@Column(name="LOAD_MATCHING_EXCP_COMMENTS", length=2500)
	private String loadMatchingExcpComments;

	@Column(name="LOOK_UP_BOL_NO", length=100)
	private String lookUpBolNo;

	@Column(name="LOOK_UP_INVOICE_NO", length=100)
	private String lookUpInvoiceNo;

	@Column(name="LOOK_UP_PRO_NO", length=100)
	private String lookUpProNo;

	@Temporal(TemporalType.DATE)
	@Column(name="MASTER_BOL_DATE")
	private Date masterBolDate;

	@Column(name="MASTER_BOL_NUMBER", length=20)
	private String masterBolNumber;

	@Column(name="MERCHANDISE_CATEGORY", length=20)
	private String merchandiseCategory;

	@Column(name="NON_MERCHANDISE_CATEGORY", length=20)
	private String nonMerchandiseCategory;

	@Column(name="ORIG_INVOICE_ID", precision=22)
	private BigDecimal origInvoiceId;

	@Column(name="PAID_AMOUNT", precision=22)
	private BigDecimal paidAmount;

	@Column(name="PAYMENT_TYPE", length=10)
	private String paymentType;

	@Column(name="PO_NUMBER", length=250)
	private String poNumber;

	@Column(name="PORT_OF_DESTINATION", length=100)
	private String portOfDestination;

	/*@Column(name="PORT_OF_DESTINATION_X", precision=22)
	private BigDecimal portOfDestinationX;*/

/*	@Column(name="PORT_OF_DISCHARGE_CODE", length=50)
	private String portOfDischargeCode;*/

	@Column(name="PORT_OF_DISCHARGE_COUNTRY", length=20)
	private String portOfDischargeCountry;

	/*@Column(name="PORT_OF_LOADING_CODE", length=50)
	private String portOfLoadingCode;*/

	@Column(name="PORT_OF_LOADING_COUNTRY", length=20)
	private String portOfLoadingCountry;

	@Column(name="PORT_OF_ORIGIN", length=100)
	private String portOfOrigin;

	/*@Column(name="PORT_OF_ORIGIN_X", precision=22)
	private BigDecimal portOfOriginX;*/

	@Temporal(TemporalType.DATE)
	@Column(name="PRO_DATE")
	private Date proDate;

	@Column(name="PRO_NUMBER", length=500)
	private String proNumber;

	@Column(name="PRODUCT_CODE", length=20)
	private String productCode;

	@Column(name="PRODUCT_DESCRIPTION", length=20)
	private String productDescription;

	@Column(name="PROOF_OF_DELIVERY", length=20)
	private String proofOfDelivery;

	@Column(name="RATED_MILES", precision=22)
	private BigDecimal ratedMiles;

	@Column(name="RATING_COMMENTS", length=4000)
	private String ratingComments;

	@Column(name="RATING_CONTRACT", length=100)
	private String ratingContract;

	@Column(name="RECEIPT_NUMBER", length=20)
	private String receiptNumber;

	@Temporal(TemporalType.DATE)
	@Column(name="RECEIVED_DATE")
	private Date receivedDate;

	@Column(name="RECEIVER_ADDRESS_1", length=100)
	private String receiverAddress1;

	@Column(name="RECEIVER_ADDRESS_2", length=100)
	private String receiverAddress2;

	@Column(name="RECEIVER_ADDRESS_3", length=100)
	private String receiverAddress3;

	@Column(name="RECEIVER_CITY", length=100)
	private String receiverCity;

	@Column(name="RECEIVER_COUNTRY", length=100)
	private String receiverCountry;

	@Column(name="RECEIVER_LOCATION_CODE", length=20)
	private String receiverLocationCode;

	@Column(name="RECEIVER_NAME", length=100)
	private String receiverName;

	@Column(name="RECEIVER_REGION", length=25)
	private String receiverRegion;

	@Column(name="RECEIVER_STATE", length=100)
	private String receiverState;

	@Column(name="RECEIVER_VATIN", length=50)
	private String receiverVatin;

	@Column(name="RECEIVER_ZIPCODE", length=100)
	private String receiverZipcode;

	@Column(length=4000)
	private String reference1;

	@Column(length=2500)
	private String reference2;

	@Column(length=2500)
	private String reference3;

	@Column(length=2500)
	private String reference4;

	@Column(name="REMIT_COMMENTS", length=4000)
	private String remitComments;

	@Column(name="REMIT_TO_ADDRESS", length=500)
	private String remitToAddress;

	@Column(name="ROUTING_NOTES", length=20)
	private String routingNotes;

	@Column(name="RTP_FLAG", length=10)
	private String rtpFlag;

/*	@Column(name="RTP_FLAG_1", precision=22)
	private BigDecimal rtpFlag1;*/

	@Column(name="RUN_NO", length=20)
	private String runNo;

	@Column(name="SCANNED_INVOICE_ID", precision=22)
	private BigDecimal scannedInvoiceId;

	@Column(name="SCANNER_COMMENTS", length=2500)
	private String scannerComments;

	@Column(name="SERVICE_CODE_ID", precision=22)
	private BigDecimal serviceCodeId;

	@Temporal(TemporalType.DATE)
	@Column(name="SHIP_DATE")
	private Date shipDate;

	@Column(name="SHIPMENT_DIRECTION", length=20)
	private String shipmentDirection;

	@Column(name="SHIPPER_ADDRESS_1", length=100)
	private String shipperAddress1;

	@Column(name="SHIPPER_ADDRESS_2", length=100)
	private String shipperAddress2;

	@Column(name="SHIPPER_ADDRESS_3", length=100)
	private String shipperAddress3;

	@Column(name="SHIPPER_CITY", length=100)
	private String shipperCity;

	@Column(name="SHIPPER_COUNTRY", length=100)
	private String shipperCountry;

	@Column(name="SHIPPER_LOCATION_CODE", length=20)
	private String shipperLocationCode;

	@Column(name="SHIPPER_NAME", length=100)
	private String shipperName;

	@Column(name="SHIPPER_REGION", length=25)
	private String shipperRegion;

	@Column(name="SHIPPER_STATE", length=100)
	private String shipperState;

	@Column(name="SHIPPER_VATIN", length=50)
	private String shipperVatin;

	@Column(name="SHIPPER_ZIPCODE", length=100)
	private String shipperZipcode;

	@Column(name="STANDARD_TRANSIT_DAYS", precision=22)
	private BigDecimal standardTransitDays;

	@Column(name="SUBTOTAL_AMOUNT", precision=22)
	private BigDecimal subtotalAmount;

	@Column(name="TARRIF_CODE", precision=22)
	private BigDecimal tarrifCode;

	@Column(name="TEMP_1", length=1500)
	private String temp1;

	@Column(name="TOTAL_CHARGES", precision=22)
	private BigDecimal totalCharges;

	@Column(name="TOTAL_CHARGES_2", precision=22)
	private BigDecimal totalCharges2;

	@Column(name="TOTAL_CHARGES_2_CURRENCY", precision=22)
	private BigDecimal totalCharges2Currency;

	@Column(name="TOTAL_CHARGES_3", precision=22)
	private BigDecimal totalCharges3;

	@Column(name="TOTAL_CHARGES_3_CURRENCY", precision=22)
	private BigDecimal totalCharges3Currency;

	@Column(name="TOTAL_CHARGES_4", precision=22)
	private BigDecimal totalCharges4;

	@Column(name="TOTAL_CHARGES_4_CURRENCY", precision=22)
	private BigDecimal totalCharges4Currency;

	@Column(name="TOTAL_DUE_AMOUNT", precision=22)
	private BigDecimal totalDueAmount;

	@Column(name="TOTAL_INVOICED_UOM", precision=22)
	private BigDecimal totalInvoicedUom;

	@Column(name="TOTAL_INVOICED_VOLUME", precision=22)
	private BigDecimal totalInvoicedVolume;

	@Column(name="TOTAL_QTY", precision=22)
	private BigDecimal totalQty;

	@Column(name="TOTAL_SHIP_UNITS", precision=22)
	private BigDecimal totalShipUnits;

	@Column(name="TOTAL_SHIP_UOM", precision=22)
	private BigDecimal totalShipUom;

	@Column(name="TOTAL_WEIGHT", precision=22)
	private BigDecimal totalWeight;

	@Column(name="USD_APPROVED_CHARGES", precision=22)
	private BigDecimal usdApprovedCharges;

	@Column(name="USD_TOTAL_CHARGES", precision=22)
	private BigDecimal usdTotalCharges;

	@Column(name="VENDOR_NUMBER", length=20)
	private String vendorNumber;

	//bi-directional many-to-one association to ShpCarrierTb
	@ManyToOne(fetch=FetchType.EAGER)//(fetch=FetchType.LAZY)
	@JsonManagedReference
	@JoinColumn(name="CARRIER_ID")
	private ShpCarrierTb shpCarrierTb;

	//bi-directional many-to-one association to ShpCustomerProfileTb
	@ManyToOne(fetch=FetchType.EAGER)//(fetch=FetchType.LAZY)
	@JsonIgnore
	@JoinColumn(name="CUSTOMER_ID")
	private ShpCustomerProfileTb shpCustomerProfileTb;

	//bi-directional many-to-one association to ShpNspCodeValuesTb
	@ManyToOne(fetch=FetchType.EAGER)//(fetch=FetchType.LAZY)
	@JsonManagedReference
	@JoinColumn(name="INVOICE_STATUS")
	//Group Id = 1 for Invoice status list; map between details.INVOICE_STATUSTb=NSPCodevaletb.nspCodeValueId
	private ShpNspCodeValuesTb shpNspCodeValuesTb1;

	//bi-directional many-to-one association to ShpNspCodeValuesTb
	@ManyToOne(fetch=FetchType.EAGER)//(fetch=FetchType.LAZY)
	@JsonManagedReference
	@JoinColumn(name="BILL_OPTION")
	//
	private ShpNspCodeValuesTb shpNspCodeValuesTb2;

	//bi-directional many-to-one association to ShpNspCodeValuesTb
	@ManyToOne(fetch=FetchType.EAGER)//(fetch=FetchType.LAZY)
	@JsonManagedReference
	@JoinColumn(name="INVOICE_MODE")
	private ShpNspCodeValuesTb shpNspCodeValuesTb3;

	//bi-directional many-to-one association to ShpNspCodeValuesTb
	@ManyToOne(fetch=FetchType.EAGER)//(fetch=FetchType.LAZY)
	@JsonManagedReference
	@JoinColumn(name="TOTAL_WEIGHT_UOM")
	private ShpNspCodeValuesTb shpNspCodeValuesTb4;

	//bi-directional many-to-one association to ShpNspTaskTb
	@OneToMany(mappedBy="shpNspInvoiceDetailsTb")
	@JsonBackReference
	private List<ShpNspTaskTb> shpNspTaskTbs;

	public ShpNspInvoiceDetailsTb() {
	}

	public long getNspInvoiceDetailsId() {
		return this.nspInvoiceDetailsId;
	}

	public void setNspInvoiceDetailsId(long nspInvoiceDetailsId) {
		this.nspInvoiceDetailsId = nspInvoiceDetailsId;
	}

	public String getAccountNumber() {
		return this.accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public BigDecimal getActualKilometers() {
		return this.actualKilometers;
	}

	public void setActualKilometers(BigDecimal actualKilometers) {
		this.actualKilometers = actualKilometers;
	}

	public BigDecimal getActualMiles() {
		return this.actualMiles;
	}

	public void setActualMiles(BigDecimal actualMiles) {
		this.actualMiles = actualMiles;
	}

	public String getAddressType() {
		return this.addressType;
	}

	public void setAddressType(String addressType) {
		this.addressType = addressType;
	}

	public String getAuditorComments() {
		return this.auditorComments;
	}

	public void setAuditorComments(String auditorComments) {
		this.auditorComments = auditorComments;
	}

	public BigDecimal getAverageTransitDays() {
		return this.averageTransitDays;
	}

	public void setAverageTransitDays(BigDecimal averageTransitDays) {
		this.averageTransitDays = averageTransitDays;
	}

	public BigDecimal getBalanceAmount() {
		return this.balanceAmount;
	}

	public void setBalanceAmount(BigDecimal balanceAmount) {
		this.balanceAmount = balanceAmount;
	}

	public Date getBillDate() {
		return this.billDate;
	}

	public void setBillDate(Date billDate) {
		this.billDate = billDate;
	}

	public String getBillToLocationCode() {
		return this.billToLocationCode;
	}

	public void setBillToLocationCode(String billToLocationCode) {
		this.billToLocationCode = billToLocationCode;
	}

	public BigDecimal getBilledMiles() {
		return this.billedMiles;
	}

	public void setBilledMiles(BigDecimal billedMiles) {
		this.billedMiles = billedMiles;
	}

	public String getBilltoAddress1() {
		return this.billtoAddress1;
	}

	public void setBilltoAddress1(String billtoAddress1) {
		this.billtoAddress1 = billtoAddress1;
	}

	public String getBilltoAddress2() {
		return this.billtoAddress2;
	}

	public void setBilltoAddress2(String billtoAddress2) {
		this.billtoAddress2 = billtoAddress2;
	}

	public String getBilltoAddress3() {
		return this.billtoAddress3;
	}

	public void setBilltoAddress3(String billtoAddress3) {
		this.billtoAddress3 = billtoAddress3;
	}

	public String getBilltoCity() {
		return this.billtoCity;
	}

	public void setBilltoCity(String billtoCity) {
		this.billtoCity = billtoCity;
	}

	public String getBilltoCountry() {
		return this.billtoCountry;
	}

	public void setBilltoCountry(String billtoCountry) {
		this.billtoCountry = billtoCountry;
	}

	public String getBilltoName() {
		return this.billtoName;
	}

	public void setBilltoName(String billtoName) {
		this.billtoName = billtoName;
	}

	public String getBilltoState() {
		return this.billtoState;
	}

	public void setBilltoState(String billtoState) {
		this.billtoState = billtoState;
	}

	public String getBilltoZipcode() {
		return this.billtoZipcode;
	}

	public void setBilltoZipcode(String billtoZipcode) {
		this.billtoZipcode = billtoZipcode;
	}

	public String getBolNumber() {
		return this.bolNumber;
	}

	public void setBolNumber(String bolNumber) {
		this.bolNumber = bolNumber;
	}

	public BigDecimal getBrokParentInvId() {
		return this.brokParentInvId;
	}

	public void setBrokParentInvId(BigDecimal brokParentInvId) {
		this.brokParentInvId = brokParentInvId;
	}

	public BigDecimal getCalculatedMiles() {
		return this.calculatedMiles;
	}

	public void setCalculatedMiles(BigDecimal calculatedMiles) {
		this.calculatedMiles = calculatedMiles;
	}

	public String getCarrierComments() {
		return this.carrierComments;
	}

	public void setCarrierComments(String carrierComments) {
		this.carrierComments = carrierComments;
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

	public String getCheckNo() {
		return this.checkNo;
	}

	public void setCheckNo(String checkNo) {
		this.checkNo = checkNo;
	}

	public String getChildCarrierName() {
		return this.childCarrierName;
	}

	public void setChildCarrierName(String childCarrierName) {
		this.childCarrierName = childCarrierName;
	}

	public String getClosedBy() {
		return this.closedBy;
	}

	public void setClosedBy(String closedBy) {
		this.closedBy = closedBy;
	}

	public Date getClosedDate() {
		return this.closedDate;
	}

	public void setClosedDate(Date closedDate) {
		this.closedDate = closedDate;
	}

	public BigDecimal getCodAmount() {
		return this.codAmount;
	}

	public void setCodAmount(BigDecimal codAmount) {
		this.codAmount = codAmount;
	}

	public BigDecimal getConsolParentId() {
		return this.consolParentId;
	}

	public void setConsolParentId(BigDecimal consolParentId) {
		this.consolParentId = consolParentId;
	}

	public BigDecimal getCountOfPalletsReloaded() {
		return this.countOfPalletsReloaded;
	}

	public void setCountOfPalletsReloaded(BigDecimal countOfPalletsReloaded) {
		this.countOfPalletsReloaded = countOfPalletsReloaded;
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

	public String getCreditMemo() {
		return this.creditMemo;
	}

	public void setCreditMemo(String creditMemo) {
		this.creditMemo = creditMemo;
	}

	public BigDecimal getCurrencyCode() {
		return this.currencyCode;
	}

	public void setCurrencyCode(BigDecimal currencyCode) {
		this.currencyCode = currencyCode;
	}

	public Date getCurrencyExchangeDate() {
		return this.currencyExchangeDate;
	}

	public void setCurrencyExchangeDate(Date currencyExchangeDate) {
		this.currencyExchangeDate = currencyExchangeDate;
	}

	public BigDecimal getCurrencyExchangeRate() {
		return this.currencyExchangeRate;
	}

	public void setCurrencyExchangeRate(BigDecimal currencyExchangeRate) {
		this.currencyExchangeRate = currencyExchangeRate;
	}

	public BigDecimal getCustAction() {
		return this.custAction;
	}

	public void setCustAction(BigDecimal custAction) {
		this.custAction = custAction;
	}

	public String getCustActionBy() {
		return this.custActionBy;
	}

	public void setCustActionBy(String custActionBy) {
		this.custActionBy = custActionBy;
	}

	public Date getCustActionDate() {
		return this.custActionDate;
	}

	public void setCustActionDate(Date custActionDate) {
		this.custActionDate = custActionDate;
	}

	public BigDecimal getCustActivityHistId() {
		return this.custActivityHistId;
	}

	public void setCustActivityHistId(BigDecimal custActivityHistId) {
		this.custActivityHistId = custActivityHistId;
	}

	public String getCustomerComments() {
		return this.customerComments;
	}

	public void setCustomerComments(String customerComments) {
		this.customerComments = customerComments;
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

	public String getCustomsDocumentNumber() {
		return this.customsDocumentNumber;
	}

	public void setCustomsDocumentNumber(String customsDocumentNumber) {
		this.customsDocumentNumber = customsDocumentNumber;
	}

	public String getDataEntryComments() {
		return this.dataEntryComments;
	}

	public void setDataEntryComments(String dataEntryComments) {
		this.dataEntryComments = dataEntryComments;
	}

	public Date getDeliveryDate() {
		return this.deliveryDate;
	}

	public void setDeliveryDate(Date deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	public String getDepartment() {
		return this.department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getDirection() {
		return this.direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}

	public BigDecimal getDistanceUom() {
		return this.distanceUom;
	}

	public void setDistanceUom(BigDecimal distanceUom) {
		this.distanceUom = distanceUom;
	}

	public String getDupCheckQuery() {
		return this.dupCheckQuery;
	}

	public void setDupCheckQuery(String dupCheckQuery) {
		this.dupCheckQuery = dupCheckQuery;
	}

	public String getDupChkInvoiceId() {
		return this.dupChkInvoiceId;
	}

	public void setDupChkInvoiceId(String dupChkInvoiceId) {
		this.dupChkInvoiceId = dupChkInvoiceId;
	}

	public BigDecimal getDwExtractFlag() {
		return this.dwExtractFlag;
	}

	public void setDwExtractFlag(BigDecimal dwExtractFlag) {
		this.dwExtractFlag = dwExtractFlag;
	}

	public String getEdiFileName() {
		return this.ediFileName;
	}

	public void setEdiFileName(String ediFileName) {
		this.ediFileName = ediFileName;
	}

	public BigDecimal getEquipmentType() {
		return this.equipmentType;
	}

	public void setEquipmentType(BigDecimal equipmentType) {
		this.equipmentType = equipmentType;
	}

	public BigDecimal getExceptionReason() {
		return this.exceptionReason;
	}

	public void setExceptionReason(BigDecimal exceptionReason) {
		this.exceptionReason = exceptionReason;
	}

	public String getExportCode() {
		return this.exportCode;
	}

	public void setExportCode(String exportCode) {
		this.exportCode = exportCode;
	}

	/*public String getFailedFormat() {
		return this.failedFormat;
	}

	public void setFailedFormat(String failedFormat) {
		this.failedFormat = failedFormat;
	}*/

	public BigDecimal getFreightInvoiceType() {
		return this.freightInvoiceType;
	}

	public void setFreightInvoiceType(BigDecimal freightInvoiceType) {
		this.freightInvoiceType = freightInvoiceType;
	}

	public BigDecimal getFtpserverLogId() {
		return this.ftpserverLogId;
	}

	public void setFtpserverLogId(BigDecimal ftpserverLogId) {
		this.ftpserverLogId = ftpserverLogId;
	}

	public BigDecimal getGainShare() {
		return this.gainShare;
	}

	public void setGainShare(BigDecimal gainShare) {
		this.gainShare = gainShare;
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

	public BigDecimal getGlCodingAttempted() {
		return this.glCodingAttempted;
	}

	public void setGlCodingAttempted(BigDecimal glCodingAttempted) {
		this.glCodingAttempted = glCodingAttempted;
	}

	public String getGlSource() {
		return this.glSource;
	}

	public void setGlSource(String glSource) {
		this.glSource = glSource;
	}

	public String getGlbEquipType() {
		return this.glbEquipType;
	}

	public void setGlbEquipType(String glbEquipType) {
		this.glbEquipType = glbEquipType;
	}

	public String getGsaCarrier() {
		return this.gsaCarrier;
	}

	public void setGsaCarrier(String gsaCarrier) {
		this.gsaCarrier = gsaCarrier;
	}

	public Date getGuaranteedDate() {
		return this.guaranteedDate;
	}

	public void setGuaranteedDate(Date guaranteedDate) {
		this.guaranteedDate = guaranteedDate;
	}

	public BigDecimal getGuaranteedService() {
		return this.guaranteedService;
	}

	public void setGuaranteedService(BigDecimal guaranteedService) {
		this.guaranteedService = guaranteedService;
	}

	public BigDecimal getHazmat() {
		return this.hazmat;
	}

	public void setHazmat(BigDecimal hazmat) {
		this.hazmat = hazmat;
	}

	public Date getHouseBolDate() {
		return this.houseBolDate;
	}

	public void setHouseBolDate(Date houseBolDate) {
		this.houseBolDate = houseBolDate;
	}

	public String getHouseBolNumber() {
		return this.houseBolNumber;
	}

	public void setHouseBolNumber(String houseBolNumber) {
		this.houseBolNumber = houseBolNumber;
	}

	public String getImportCode() {
		return this.importCode;
	}

	public void setImportCode(String importCode) {
		this.importCode = importCode;
	}

	public BigDecimal getIncoTermCode() {
		return this.incoTermCode;
	}

	public void setIncoTermCode(BigDecimal incoTermCode) {
		this.incoTermCode = incoTermCode;
	}

	public String getIncoTermPoint() {
		return this.incoTermPoint;
	}

	public void setIncoTermPoint(String incoTermPoint) {
		this.incoTermPoint = incoTermPoint;
	}

	public Date getInterlineBolDate() {
		return this.interlineBolDate;
	}

	public void setInterlineBolDate(Date interlineBolDate) {
		this.interlineBolDate = interlineBolDate;
	}

	public String getInterlineBolNumber() {
		return this.interlineBolNumber;
	}

	public void setInterlineBolNumber(String interlineBolNumber) {
		this.interlineBolNumber = interlineBolNumber;
	}

	public BigDecimal getInterlineCarrier() {
		return this.interlineCarrier;
	}

	public void setInterlineCarrier(BigDecimal interlineCarrier) {
		this.interlineCarrier = interlineCarrier;
	}

	public Date getInterlineDeliveryDate() {
		return this.interlineDeliveryDate;
	}

	public void setInterlineDeliveryDate(Date interlineDeliveryDate) {
		this.interlineDeliveryDate = interlineDeliveryDate;
	}

	public String getInterlineVesselName() {
		return this.interlineVesselName;
	}

	public void setInterlineVesselName(String interlineVesselName) {
		this.interlineVesselName = interlineVesselName;
	}

	public String getInterlineVoyageNumber() {
		return this.interlineVoyageNumber;
	}

	public void setInterlineVoyageNumber(String interlineVoyageNumber) {
		this.interlineVoyageNumber = interlineVoyageNumber;
	}

	public BigDecimal getInvPageCount() {
		return this.invPageCount;
	}

	public void setInvPageCount(BigDecimal invPageCount) {
		this.invPageCount = invPageCount;
	}

	public Date getInvoiceDueDate() {
		return this.invoiceDueDate;
	}

	public void setInvoiceDueDate(Date invoiceDueDate) {
		this.invoiceDueDate = invoiceDueDate;
	}

	public BigDecimal getInvoiceHsTaxAmount() {
		return this.invoiceHsTaxAmount;
	}

	public void setInvoiceHsTaxAmount(BigDecimal invoiceHsTaxAmount) {
		this.invoiceHsTaxAmount = invoiceHsTaxAmount;
	}

	public String getInvoiceIsaNumber() {
		return this.invoiceIsaNumber;
	}

	public void setInvoiceIsaNumber(String invoiceIsaNumber) {
		this.invoiceIsaNumber = invoiceIsaNumber;
	}

	public String getInvoiceNumber() {
		return this.invoiceNumber;
	}

	public void setInvoiceNumber(String invoiceNumber) {
		this.invoiceNumber = invoiceNumber;
	}

	public BigDecimal getInvoiceTax1Amount() {
		return this.invoiceTax1Amount;
	}

	public void setInvoiceTax1Amount(BigDecimal invoiceTax1Amount) {
		this.invoiceTax1Amount = invoiceTax1Amount;
	}

	public BigDecimal getInvoiceTax2Amount() {
		return this.invoiceTax2Amount;
	}

	public void setInvoiceTax2Amount(BigDecimal invoiceTax2Amount) {
		this.invoiceTax2Amount = invoiceTax2Amount;
	}

	public String getInvoiceType() {
		return this.invoiceType;
	}

	public void setInvoiceType(String invoiceType) {
		this.invoiceType = invoiceType;
	}

	public BigDecimal getIsAccessorialsChecked() {
		return this.isAccessorialsChecked;
	}

	public void setIsAccessorialsChecked(BigDecimal isAccessorialsChecked) {
		this.isAccessorialsChecked = isAccessorialsChecked;
	}

	public BigDecimal getIsBillOptionCorrect() {
		return this.isBillOptionCorrect;
	}

	public void setIsBillOptionCorrect(BigDecimal isBillOptionCorrect) {
		this.isBillOptionCorrect = isBillOptionCorrect;
	}

	/*public BigDecimal getIsBlacklisted() {
		return this.isBlacklisted;
	}

	public void setIsBlacklisted(BigDecimal isBlacklisted) {
		this.isBlacklisted = isBlacklisted;
	}*/

	public BigDecimal getIsDocumentationChecked() {
		return this.isDocumentationChecked;
	}

	public void setIsDocumentationChecked(BigDecimal isDocumentationChecked) {
		this.isDocumentationChecked = isDocumentationChecked;
	}

	public BigDecimal getIsDuplicateBillingChecked() {
		return this.isDuplicateBillingChecked;
	}

	public void setIsDuplicateBillingChecked(BigDecimal isDuplicateBillingChecked) {
		this.isDuplicateBillingChecked = isDuplicateBillingChecked;
	}

	public BigDecimal getIsFreightChargeChecked() {
		return this.isFreightChargeChecked;
	}

	public void setIsFreightChargeChecked(BigDecimal isFreightChargeChecked) {
		this.isFreightChargeChecked = isFreightChargeChecked;
	}

	public BigDecimal getIsFuelSurchargesChecked() {
		return this.isFuelSurchargesChecked;
	}

	public void setIsFuelSurchargesChecked(BigDecimal isFuelSurchargesChecked) {
		this.isFuelSurchargesChecked = isFuelSurchargesChecked;
	}

	public String getLadingDescription() {
		return this.ladingDescription;
	}

	public void setLadingDescription(String ladingDescription) {
		this.ladingDescription = ladingDescription;
	}

	public String getLaneDescription() {
		return this.laneDescription;
	}

	public void setLaneDescription(String laneDescription) {
		this.laneDescription = laneDescription;
	}

	public String getLaneId() {
		return this.laneId;
	}

	public void setLaneId(String laneId) {
		this.laneId = laneId;
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

	public Date getLoadMatchDate() {
		return this.loadMatchDate;
	}

	public void setLoadMatchDate(Date loadMatchDate) {
		this.loadMatchDate = loadMatchDate;
	}

	public Date getLoadMatchExcpDate() {
		return this.loadMatchExcpDate;
	}

	public void setLoadMatchExcpDate(Date loadMatchExcpDate) {
		this.loadMatchExcpDate = loadMatchExcpDate;
	}

	public BigDecimal getLoadMatched() {
		return this.loadMatched;
	}

	public void setLoadMatched(BigDecimal loadMatched) {
		this.loadMatched = loadMatched;
	}

	public String getLoadMatchingExcpComments() {
		return this.loadMatchingExcpComments;
	}

	public void setLoadMatchingExcpComments(String loadMatchingExcpComments) {
		this.loadMatchingExcpComments = loadMatchingExcpComments;
	}

	public String getLookUpBolNo() {
		return this.lookUpBolNo;
	}

	public void setLookUpBolNo(String lookUpBolNo) {
		this.lookUpBolNo = lookUpBolNo;
	}

	public String getLookUpInvoiceNo() {
		return this.lookUpInvoiceNo;
	}

	public void setLookUpInvoiceNo(String lookUpInvoiceNo) {
		this.lookUpInvoiceNo = lookUpInvoiceNo;
	}

	public String getLookUpProNo() {
		return this.lookUpProNo;
	}

	public void setLookUpProNo(String lookUpProNo) {
		this.lookUpProNo = lookUpProNo;
	}

	public Date getMasterBolDate() {
		return this.masterBolDate;
	}

	public void setMasterBolDate(Date masterBolDate) {
		this.masterBolDate = masterBolDate;
	}

	public String getMasterBolNumber() {
		return this.masterBolNumber;
	}

	public void setMasterBolNumber(String masterBolNumber) {
		this.masterBolNumber = masterBolNumber;
	}

	public String getMerchandiseCategory() {
		return this.merchandiseCategory;
	}

	public void setMerchandiseCategory(String merchandiseCategory) {
		this.merchandiseCategory = merchandiseCategory;
	}

	public String getNonMerchandiseCategory() {
		return this.nonMerchandiseCategory;
	}

	public void setNonMerchandiseCategory(String nonMerchandiseCategory) {
		this.nonMerchandiseCategory = nonMerchandiseCategory;
	}

	public BigDecimal getOrigInvoiceId() {
		return this.origInvoiceId;
	}

	public void setOrigInvoiceId(BigDecimal origInvoiceId) {
		this.origInvoiceId = origInvoiceId;
	}

	public BigDecimal getPaidAmount() {
		return this.paidAmount;
	}

	public void setPaidAmount(BigDecimal paidAmount) {
		this.paidAmount = paidAmount;
	}

	public String getPaymentType() {
		return this.paymentType;
	}

	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}

	public String getPoNumber() {
		return this.poNumber;
	}

	public void setPoNumber(String poNumber) {
		this.poNumber = poNumber;
	}

	public String getPortOfDestination() {
		return this.portOfDestination;
	}

	public void setPortOfDestination(String portOfDestination) {
		this.portOfDestination = portOfDestination;
	}

	/*public BigDecimal getPortOfDestinationX() {
		return this.portOfDestinationX;
	}

	public void setPortOfDestinationX(BigDecimal portOfDestinationX) {
		this.portOfDestinationX = portOfDestinationX;
	}

	public String getPortOfDischargeCode() {
		return this.portOfDischargeCode;
	}

	public void setPortOfDischargeCode(String portOfDischargeCode) {
		this.portOfDischargeCode = portOfDischargeCode;
	}*/

	public String getPortOfDischargeCountry() {
		return this.portOfDischargeCountry;
	}

	public void setPortOfDischargeCountry(String portOfDischargeCountry) {
		this.portOfDischargeCountry = portOfDischargeCountry;
	}

	/*public String getPortOfLoadingCode() {
		return this.portOfLoadingCode;
	}

	public void setPortOfLoadingCode(String portOfLoadingCode) {
		this.portOfLoadingCode = portOfLoadingCode;
	}*/

	public String getPortOfLoadingCountry() {
		return this.portOfLoadingCountry;
	}

	public void setPortOfLoadingCountry(String portOfLoadingCountry) {
		this.portOfLoadingCountry = portOfLoadingCountry;
	}

	public String getPortOfOrigin() {
		return this.portOfOrigin;
	}

	public void setPortOfOrigin(String portOfOrigin) {
		this.portOfOrigin = portOfOrigin;
	}

	/*public BigDecimal getPortOfOriginX() {
		return this.portOfOriginX;
	}

	public void setPortOfOriginX(BigDecimal portOfOriginX) {
		this.portOfOriginX = portOfOriginX;
	}*/

	public Date getProDate() {
		return this.proDate;
	}

	public void setProDate(Date proDate) {
		this.proDate = proDate;
	}

	public String getProNumber() {
		return this.proNumber;
	}

	public void setProNumber(String proNumber) {
		this.proNumber = proNumber;
	}

	public String getProductCode() {
		return this.productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public String getProductDescription() {
		return this.productDescription;
	}

	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}

	public String getProofOfDelivery() {
		return this.proofOfDelivery;
	}

	public void setProofOfDelivery(String proofOfDelivery) {
		this.proofOfDelivery = proofOfDelivery;
	}

	public BigDecimal getRatedMiles() {
		return this.ratedMiles;
	}

	public void setRatedMiles(BigDecimal ratedMiles) {
		this.ratedMiles = ratedMiles;
	}

	public String getRatingComments() {
		return this.ratingComments;
	}

	public void setRatingComments(String ratingComments) {
		this.ratingComments = ratingComments;
	}

	public String getRatingContract() {
		return this.ratingContract;
	}

	public void setRatingContract(String ratingContract) {
		this.ratingContract = ratingContract;
	}

	public String getReceiptNumber() {
		return this.receiptNumber;
	}

	public void setReceiptNumber(String receiptNumber) {
		this.receiptNumber = receiptNumber;
	}

	public Date getReceivedDate() {
		return this.receivedDate;
	}

	public void setReceivedDate(Date receivedDate) {
		this.receivedDate = receivedDate;
	}

	public String getReceiverAddress1() {
		return this.receiverAddress1;
	}

	public void setReceiverAddress1(String receiverAddress1) {
		this.receiverAddress1 = receiverAddress1;
	}

	public String getReceiverAddress2() {
		return this.receiverAddress2;
	}

	public void setReceiverAddress2(String receiverAddress2) {
		this.receiverAddress2 = receiverAddress2;
	}

	public String getReceiverAddress3() {
		return this.receiverAddress3;
	}

	public void setReceiverAddress3(String receiverAddress3) {
		this.receiverAddress3 = receiverAddress3;
	}

	public String getReceiverCity() {
		return this.receiverCity;
	}

	public void setReceiverCity(String receiverCity) {
		this.receiverCity = receiverCity;
	}

	public String getReceiverCountry() {
		return this.receiverCountry;
	}

	public void setReceiverCountry(String receiverCountry) {
		this.receiverCountry = receiverCountry;
	}

	public String getReceiverLocationCode() {
		return this.receiverLocationCode;
	}

	public void setReceiverLocationCode(String receiverLocationCode) {
		this.receiverLocationCode = receiverLocationCode;
	}

	public String getReceiverName() {
		return this.receiverName;
	}

	public void setReceiverName(String receiverName) {
		this.receiverName = receiverName;
	}

	public String getReceiverRegion() {
		return this.receiverRegion;
	}

	public void setReceiverRegion(String receiverRegion) {
		this.receiverRegion = receiverRegion;
	}

	public String getReceiverState() {
		return this.receiverState;
	}

	public void setReceiverState(String receiverState) {
		this.receiverState = receiverState;
	}

	public String getReceiverVatin() {
		return this.receiverVatin;
	}

	public void setReceiverVatin(String receiverVatin) {
		this.receiverVatin = receiverVatin;
	}

	public String getReceiverZipcode() {
		return this.receiverZipcode;
	}

	public void setReceiverZipcode(String receiverZipcode) {
		this.receiverZipcode = receiverZipcode;
	}

	public String getReference1() {
		return this.reference1;
	}

	public void setReference1(String reference1) {
		this.reference1 = reference1;
	}

	public String getReference2() {
		return this.reference2;
	}

	public void setReference2(String reference2) {
		this.reference2 = reference2;
	}

	public String getReference3() {
		return this.reference3;
	}

	public void setReference3(String reference3) {
		this.reference3 = reference3;
	}

	public String getReference4() {
		return this.reference4;
	}

	public void setReference4(String reference4) {
		this.reference4 = reference4;
	}

	public String getRemitComments() {
		return this.remitComments;
	}

	public void setRemitComments(String remitComments) {
		this.remitComments = remitComments;
	}

	public String getRemitToAddress() {
		return this.remitToAddress;
	}

	public void setRemitToAddress(String remitToAddress) {
		this.remitToAddress = remitToAddress;
	}

	public String getRoutingNotes() {
		return this.routingNotes;
	}

	public void setRoutingNotes(String routingNotes) {
		this.routingNotes = routingNotes;
	}

	public String getRtpFlag() {
		return this.rtpFlag;
	}

	public void setRtpFlag(String rtpFlag) {
		this.rtpFlag = rtpFlag;
	}

	/*public BigDecimal getRtpFlag1() {
		return this.rtpFlag1;
	}

	public void setRtpFlag1(BigDecimal rtpFlag1) {
		this.rtpFlag1 = rtpFlag1;
	}*/

	public String getRunNo() {
		return this.runNo;
	}

	public void setRunNo(String runNo) {
		this.runNo = runNo;
	}

	public BigDecimal getScannedInvoiceId() {
		return this.scannedInvoiceId;
	}

	public void setScannedInvoiceId(BigDecimal scannedInvoiceId) {
		this.scannedInvoiceId = scannedInvoiceId;
	}

	public String getScannerComments() {
		return this.scannerComments;
	}

	public void setScannerComments(String scannerComments) {
		this.scannerComments = scannerComments;
	}

	public BigDecimal getServiceCodeId() {
		return this.serviceCodeId;
	}

	public void setServiceCodeId(BigDecimal serviceCodeId) {
		this.serviceCodeId = serviceCodeId;
	}

	public Date getShipDate() {
		return this.shipDate;
	}

	public void setShipDate(Date shipDate) {
		this.shipDate = shipDate;
	}

	public String getShipmentDirection() {
		return this.shipmentDirection;
	}

	public void setShipmentDirection(String shipmentDirection) {
		this.shipmentDirection = shipmentDirection;
	}

	public String getShipperAddress1() {
		return this.shipperAddress1;
	}

	public void setShipperAddress1(String shipperAddress1) {
		this.shipperAddress1 = shipperAddress1;
	}

	public String getShipperAddress2() {
		return this.shipperAddress2;
	}

	public void setShipperAddress2(String shipperAddress2) {
		this.shipperAddress2 = shipperAddress2;
	}

	public String getShipperAddress3() {
		return this.shipperAddress3;
	}

	public void setShipperAddress3(String shipperAddress3) {
		this.shipperAddress3 = shipperAddress3;
	}

	public String getShipperCity() {
		return this.shipperCity;
	}

	public void setShipperCity(String shipperCity) {
		this.shipperCity = shipperCity;
	}

	public String getShipperCountry() {
		return this.shipperCountry;
	}

	public void setShipperCountry(String shipperCountry) {
		this.shipperCountry = shipperCountry;
	}

	public String getShipperLocationCode() {
		return this.shipperLocationCode;
	}

	public void setShipperLocationCode(String shipperLocationCode) {
		this.shipperLocationCode = shipperLocationCode;
	}

	public String getShipperName() {
		return this.shipperName;
	}

	public void setShipperName(String shipperName) {
		this.shipperName = shipperName;
	}

	public String getShipperRegion() {
		return this.shipperRegion;
	}

	public void setShipperRegion(String shipperRegion) {
		this.shipperRegion = shipperRegion;
	}

	public String getShipperState() {
		return this.shipperState;
	}

	public void setShipperState(String shipperState) {
		this.shipperState = shipperState;
	}

	public String getShipperVatin() {
		return this.shipperVatin;
	}

	public void setShipperVatin(String shipperVatin) {
		this.shipperVatin = shipperVatin;
	}

	public String getShipperZipcode() {
		return this.shipperZipcode;
	}

	public void setShipperZipcode(String shipperZipcode) {
		this.shipperZipcode = shipperZipcode;
	}

	public BigDecimal getStandardTransitDays() {
		return this.standardTransitDays;
	}

	public void setStandardTransitDays(BigDecimal standardTransitDays) {
		this.standardTransitDays = standardTransitDays;
	}

	public BigDecimal getSubtotalAmount() {
		return this.subtotalAmount;
	}

	public void setSubtotalAmount(BigDecimal subtotalAmount) {
		this.subtotalAmount = subtotalAmount;
	}

	public BigDecimal getTarrifCode() {
		return this.tarrifCode;
	}

	public void setTarrifCode(BigDecimal tarrifCode) {
		this.tarrifCode = tarrifCode;
	}

	public String getTemp1() {
		return this.temp1;
	}

	public void setTemp1(String temp1) {
		this.temp1 = temp1;
	}

	public BigDecimal getTotalCharges() {
		return this.totalCharges;
	}

	public void setTotalCharges(BigDecimal totalCharges) {
		this.totalCharges = totalCharges;
	}

	public BigDecimal getTotalCharges2() {
		return this.totalCharges2;
	}

	public void setTotalCharges2(BigDecimal totalCharges2) {
		this.totalCharges2 = totalCharges2;
	}

	public BigDecimal getTotalCharges2Currency() {
		return this.totalCharges2Currency;
	}

	public void setTotalCharges2Currency(BigDecimal totalCharges2Currency) {
		this.totalCharges2Currency = totalCharges2Currency;
	}

	public BigDecimal getTotalCharges3() {
		return this.totalCharges3;
	}

	public void setTotalCharges3(BigDecimal totalCharges3) {
		this.totalCharges3 = totalCharges3;
	}

	public BigDecimal getTotalCharges3Currency() {
		return this.totalCharges3Currency;
	}

	public void setTotalCharges3Currency(BigDecimal totalCharges3Currency) {
		this.totalCharges3Currency = totalCharges3Currency;
	}

	public BigDecimal getTotalCharges4() {
		return this.totalCharges4;
	}

	public void setTotalCharges4(BigDecimal totalCharges4) {
		this.totalCharges4 = totalCharges4;
	}

	public BigDecimal getTotalCharges4Currency() {
		return this.totalCharges4Currency;
	}

	public void setTotalCharges4Currency(BigDecimal totalCharges4Currency) {
		this.totalCharges4Currency = totalCharges4Currency;
	}

	public BigDecimal getTotalDueAmount() {
		return this.totalDueAmount;
	}

	public void setTotalDueAmount(BigDecimal totalDueAmount) {
		this.totalDueAmount = totalDueAmount;
	}

	public BigDecimal getTotalInvoicedUom() {
		return this.totalInvoicedUom;
	}

	public void setTotalInvoicedUom(BigDecimal totalInvoicedUom) {
		this.totalInvoicedUom = totalInvoicedUom;
	}

	public BigDecimal getTotalInvoicedVolume() {
		return this.totalInvoicedVolume;
	}

	public void setTotalInvoicedVolume(BigDecimal totalInvoicedVolume) {
		this.totalInvoicedVolume = totalInvoicedVolume;
	}

	public BigDecimal getTotalQty() {
		return this.totalQty;
	}

	public void setTotalQty(BigDecimal totalQty) {
		this.totalQty = totalQty;
	}

	public BigDecimal getTotalShipUnits() {
		return this.totalShipUnits;
	}

	public void setTotalShipUnits(BigDecimal totalShipUnits) {
		this.totalShipUnits = totalShipUnits;
	}

	public BigDecimal getTotalShipUom() {
		return this.totalShipUom;
	}

	public void setTotalShipUom(BigDecimal totalShipUom) {
		this.totalShipUom = totalShipUom;
	}

	public BigDecimal getTotalWeight() {
		return this.totalWeight;
	}

	public void setTotalWeight(BigDecimal totalWeight) {
		this.totalWeight = totalWeight;
	}

	public BigDecimal getUsdApprovedCharges() {
		return this.usdApprovedCharges;
	}

	public void setUsdApprovedCharges(BigDecimal usdApprovedCharges) {
		this.usdApprovedCharges = usdApprovedCharges;
	}

	public BigDecimal getUsdTotalCharges() {
		return this.usdTotalCharges;
	}

	public void setUsdTotalCharges(BigDecimal usdTotalCharges) {
		this.usdTotalCharges = usdTotalCharges;
	}

	public String getVendorNumber() {
		return this.vendorNumber;
	}

	public void setVendorNumber(String vendorNumber) {
		this.vendorNumber = vendorNumber;
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

	public ShpNspCodeValuesTb getShpNspCodeValuesTb4() {
		return this.shpNspCodeValuesTb4;
	}

	public void setShpNspCodeValuesTb4(ShpNspCodeValuesTb shpNspCodeValuesTb4) {
		this.shpNspCodeValuesTb4 = shpNspCodeValuesTb4;
	}

	public List<ShpNspTaskTb> getShpNspTaskTbs() {
		return this.shpNspTaskTbs;
	}

	public void setShpNspTaskTbs(List<ShpNspTaskTb> shpNspTaskTbs) {
		this.shpNspTaskTbs = shpNspTaskTbs;
	}

	public ShpNspTaskTb addShpNspTaskTb(ShpNspTaskTb shpNspTaskTb) {
		getShpNspTaskTbs().add(shpNspTaskTb);
		shpNspTaskTb.setShpNspInvoiceDetailsTb(this);

		return shpNspTaskTb;
	}

	public ShpNspTaskTb removeShpNspTaskTb(ShpNspTaskTb shpNspTaskTb) {
		getShpNspTaskTbs().remove(shpNspTaskTb);
		shpNspTaskTb.setShpNspInvoiceDetailsTb(null);

		return shpNspTaskTb;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		final int maxLen = 10;
		return "ShpNspInvoiceDetailsTb [nspInvoiceDetailsId=" + nspInvoiceDetailsId + ", "
				+ (accountNumber != null ? "accountNumber=" + accountNumber + ", " : "")
				+ (actualKilometers != null ? "actualKilometers=" + actualKilometers + ", " : "")
				+ (actualMiles != null ? "actualMiles=" + actualMiles + ", " : "")
				+ (addressType != null ? "addressType=" + addressType + ", " : "")
				+ (auditorComments != null ? "auditorComments=" + auditorComments + ", " : "")
				+ (averageTransitDays != null ? "averageTransitDays=" + averageTransitDays + ", " : "")
				+ (balanceAmount != null ? "balanceAmount=" + balanceAmount + ", " : "")
				+ (billDate != null ? "billDate=" + billDate + ", " : "")
				+ (billToLocationCode != null ? "billToLocationCode=" + billToLocationCode + ", " : "")
				+ (billedMiles != null ? "billedMiles=" + billedMiles + ", " : "")
				+ (billtoAddress1 != null ? "billtoAddress1=" + billtoAddress1 + ", " : "")
				+ (billtoAddress2 != null ? "billtoAddress2=" + billtoAddress2 + ", " : "")
				+ (billtoAddress3 != null ? "billtoAddress3=" + billtoAddress3 + ", " : "")
				+ (billtoCity != null ? "billtoCity=" + billtoCity + ", " : "")
				+ (billtoCountry != null ? "billtoCountry=" + billtoCountry + ", " : "")
				+ (billtoName != null ? "billtoName=" + billtoName + ", " : "")
				+ (billtoState != null ? "billtoState=" + billtoState + ", " : "")
				+ (billtoZipcode != null ? "billtoZipcode=" + billtoZipcode + ", " : "")
				+ (bolNumber != null ? "bolNumber=" + bolNumber + ", " : "")
				+ (brokParentInvId != null ? "brokParentInvId=" + brokParentInvId + ", " : "")
				+ (calculatedMiles != null ? "calculatedMiles=" + calculatedMiles + ", " : "")
				+ (carrierComments != null ? "carrierComments=" + carrierComments + ", " : "")
				+ (checkAmount != null ? "checkAmount=" + checkAmount + ", " : "")
				+ (checkDate != null ? "checkDate=" + checkDate + ", " : "")
				+ (checkNo != null ? "checkNo=" + checkNo + ", " : "")
				+ (childCarrierName != null ? "childCarrierName=" + childCarrierName + ", " : "")
				+ (closedBy != null ? "closedBy=" + closedBy + ", " : "")
				+ (closedDate != null ? "closedDate=" + closedDate + ", " : "")
				+ (codAmount != null ? "codAmount=" + codAmount + ", " : "")
				+ (consolParentId != null ? "consolParentId=" + consolParentId + ", " : "")
				+ (countOfPalletsReloaded != null ? "countOfPalletsReloaded=" + countOfPalletsReloaded + ", " : "")
				+ (createDate != null ? "createDate=" + createDate + ", " : "")
				+ (createUser != null ? "createUser=" + createUser + ", " : "")
				+ (creditMemo != null ? "creditMemo=" + creditMemo + ", " : "")
				+ (currencyCode != null ? "currencyCode=" + currencyCode + ", " : "")
				+ (currencyExchangeDate != null ? "currencyExchangeDate=" + currencyExchangeDate + ", " : "")
				+ (currencyExchangeRate != null ? "currencyExchangeRate=" + currencyExchangeRate + ", " : "")
				+ (custAction != null ? "custAction=" + custAction + ", " : "")
				+ (custActionBy != null ? "custActionBy=" + custActionBy + ", " : "")
				+ (custActionDate != null ? "custActionDate=" + custActionDate + ", " : "")
				+ (custActivityHistId != null ? "custActivityHistId=" + custActivityHistId + ", " : "")
				+ (customerComments != null ? "customerComments=" + customerComments + ", " : "")
				+ (customerDefined1 != null ? "customerDefined1=" + customerDefined1 + ", " : "")
				+ (customerDefined2 != null ? "customerDefined2=" + customerDefined2 + ", " : "")
				+ (customerDefined3 != null ? "customerDefined3=" + customerDefined3 + ", " : "")
				+ (customsDocumentNumber != null ? "customsDocumentNumber=" + customsDocumentNumber + ", " : "")
				+ (dataEntryComments != null ? "dataEntryComments=" + dataEntryComments + ", " : "")
				+ (deliveryDate != null ? "deliveryDate=" + deliveryDate + ", " : "")
				+ (department != null ? "department=" + department + ", " : "")
				+ (direction != null ? "direction=" + direction + ", " : "")
				+ (distanceUom != null ? "distanceUom=" + distanceUom + ", " : "")
				+ (dupCheckQuery != null ? "dupCheckQuery=" + dupCheckQuery + ", " : "")
				+ (dupChkInvoiceId != null ? "dupChkInvoiceId=" + dupChkInvoiceId + ", " : "")
				+ (dwExtractFlag != null ? "dwExtractFlag=" + dwExtractFlag + ", " : "")
				+ (ediFileName != null ? "ediFileName=" + ediFileName + ", " : "")
				+ (equipmentType != null ? "equipmentType=" + equipmentType + ", " : "")
				+ (exceptionReason != null ? "exceptionReason=" + exceptionReason + ", " : "")
				+ (exportCode != null ? "exportCode=" + exportCode + ", " : "")
			//	+ (failedFormat != null ? "failedFormat=" + failedFormat + ", " : "")
				+ (freightInvoiceType != null ? "freightInvoiceType=" + freightInvoiceType + ", " : "")
				+ (ftpserverLogId != null ? "ftpserverLogId=" + ftpserverLogId + ", " : "")
				+ (gainShare != null ? "gainShare=" + gainShare + ", " : "")
				+ (glAccountsCode != null ? "glAccountsCode=" + glAccountsCode + ", " : "")
				+ (glAppliedDate != null ? "glAppliedDate=" + glAppliedDate + ", " : "")
				+ (glCodingAttempted != null ? "glCodingAttempted=" + glCodingAttempted + ", " : "")
				+ (glSource != null ? "glSource=" + glSource + ", " : "")
				+ (glbEquipType != null ? "glbEquipType=" + glbEquipType + ", " : "")
				+ (gsaCarrier != null ? "gsaCarrier=" + gsaCarrier + ", " : "")
				+ (guaranteedDate != null ? "guaranteedDate=" + guaranteedDate + ", " : "")
				+ (guaranteedService != null ? "guaranteedService=" + guaranteedService + ", " : "")
				+ (hazmat != null ? "hazmat=" + hazmat + ", " : "")
				+ (houseBolDate != null ? "houseBolDate=" + houseBolDate + ", " : "")
				+ (houseBolNumber != null ? "houseBolNumber=" + houseBolNumber + ", " : "")
				+ (importCode != null ? "importCode=" + importCode + ", " : "")
				+ (incoTermCode != null ? "incoTermCode=" + incoTermCode + ", " : "")
				+ (incoTermPoint != null ? "incoTermPoint=" + incoTermPoint + ", " : "")
				+ (interlineBolDate != null ? "interlineBolDate=" + interlineBolDate + ", " : "")
				+ (interlineBolNumber != null ? "interlineBolNumber=" + interlineBolNumber + ", " : "")
				+ (interlineCarrier != null ? "interlineCarrier=" + interlineCarrier + ", " : "")
				+ (interlineDeliveryDate != null ? "interlineDeliveryDate=" + interlineDeliveryDate + ", " : "")
				+ (interlineVesselName != null ? "interlineVesselName=" + interlineVesselName + ", " : "")
				+ (interlineVoyageNumber != null ? "interlineVoyageNumber=" + interlineVoyageNumber + ", " : "")
				+ (invPageCount != null ? "invPageCount=" + invPageCount + ", " : "")
				+ (invoiceDueDate != null ? "invoiceDueDate=" + invoiceDueDate + ", " : "")
				+ (invoiceHsTaxAmount != null ? "invoiceHsTaxAmount=" + invoiceHsTaxAmount + ", " : "")
				+ (invoiceIsaNumber != null ? "invoiceIsaNumber=" + invoiceIsaNumber + ", " : "")
				+ (invoiceNumber != null ? "invoiceNumber=" + invoiceNumber + ", " : "")
				+ (invoiceTax1Amount != null ? "invoiceTax1Amount=" + invoiceTax1Amount + ", " : "")
				+ (invoiceTax2Amount != null ? "invoiceTax2Amount=" + invoiceTax2Amount + ", " : "")
				+ (invoiceType != null ? "invoiceType=" + invoiceType + ", " : "")
				+ (isAccessorialsChecked != null ? "isAccessorialsChecked=" + isAccessorialsChecked + ", " : "")
				+ (isBillOptionCorrect != null ? "isBillOptionCorrect=" + isBillOptionCorrect + ", " : "")
			//	+ (isBlacklisted != null ? "isBlacklisted=" + isBlacklisted + ", " : "")
				+ (isDocumentationChecked != null ? "isDocumentationChecked=" + isDocumentationChecked + ", " : "")
				+ (isDuplicateBillingChecked != null ? "isDuplicateBillingChecked=" + isDuplicateBillingChecked + ", "
						: "")
				+ (isFreightChargeChecked != null ? "isFreightChargeChecked=" + isFreightChargeChecked + ", " : "")
				+ (isFuelSurchargesChecked != null ? "isFuelSurchargesChecked=" + isFuelSurchargesChecked + ", " : "")
				+ (ladingDescription != null ? "ladingDescription=" + ladingDescription + ", " : "")
				+ (laneDescription != null ? "laneDescription=" + laneDescription + ", " : "")
				+ (laneId != null ? "laneId=" + laneId + ", " : "")
				+ (lastUpdateDate != null ? "lastUpdateDate=" + lastUpdateDate + ", " : "")
				+ (lastUpdateUser != null ? "lastUpdateUser=" + lastUpdateUser + ", " : "")
				+ (linkedDocuments != null ? "linkedDocuments=" + linkedDocuments + ", " : "")
				+ (loadMatchDate != null ? "loadMatchDate=" + loadMatchDate + ", " : "")
				+ (loadMatchExcpDate != null ? "loadMatchExcpDate=" + loadMatchExcpDate + ", " : "")
				+ (loadMatched != null ? "loadMatched=" + loadMatched + ", " : "")
				+ (loadMatchingExcpComments != null ? "loadMatchingExcpComments=" + loadMatchingExcpComments + ", "
						: "")
				+ (lookUpBolNo != null ? "lookUpBolNo=" + lookUpBolNo + ", " : "")
				+ (lookUpInvoiceNo != null ? "lookUpInvoiceNo=" + lookUpInvoiceNo + ", " : "")
				+ (lookUpProNo != null ? "lookUpProNo=" + lookUpProNo + ", " : "")
				+ (masterBolDate != null ? "masterBolDate=" + masterBolDate + ", " : "")
				+ (masterBolNumber != null ? "masterBolNumber=" + masterBolNumber + ", " : "")
				+ (merchandiseCategory != null ? "merchandiseCategory=" + merchandiseCategory + ", " : "")
				+ (nonMerchandiseCategory != null ? "nonMerchandiseCategory=" + nonMerchandiseCategory + ", " : "")
				+ (origInvoiceId != null ? "origInvoiceId=" + origInvoiceId + ", " : "")
				+ (paidAmount != null ? "paidAmount=" + paidAmount + ", " : "")
				+ (paymentType != null ? "paymentType=" + paymentType + ", " : "")
				+ (poNumber != null ? "poNumber=" + poNumber + ", " : "")
				+ (portOfDestination != null ? "portOfDestination=" + portOfDestination + ", " : "")
			//	+ (portOfDestinationX != null ? "portOfDestinationX=" + portOfDestinationX + ", " : "")
			//	+ (portOfDischargeCode != null ? "portOfDischargeCode=" + portOfDischargeCode + ", " : "")
				+ (portOfDischargeCountry != null ? "portOfDischargeCountry=" + portOfDischargeCountry + ", " : "")
			//	+ (portOfLoadingCode != null ? "portOfLoadingCode=" + portOfLoadingCode + ", " : "")
				+ (portOfLoadingCountry != null ? "portOfLoadingCountry=" + portOfLoadingCountry + ", " : "")
				+ (portOfOrigin != null ? "portOfOrigin=" + portOfOrigin + ", " : "")
			//	+ (portOfOriginX != null ? "portOfOriginX=" + portOfOriginX + ", " : "")
				+ (proDate != null ? "proDate=" + proDate + ", " : "")
				+ (proNumber != null ? "proNumber=" + proNumber + ", " : "")
				+ (productCode != null ? "productCode=" + productCode + ", " : "")
				+ (productDescription != null ? "productDescription=" + productDescription + ", " : "")
				+ (proofOfDelivery != null ? "proofOfDelivery=" + proofOfDelivery + ", " : "")
				+ (ratedMiles != null ? "ratedMiles=" + ratedMiles + ", " : "")
				+ (ratingComments != null ? "ratingComments=" + ratingComments + ", " : "")
				+ (ratingContract != null ? "ratingContract=" + ratingContract + ", " : "")
				+ (receiptNumber != null ? "receiptNumber=" + receiptNumber + ", " : "")
				+ (receivedDate != null ? "receivedDate=" + receivedDate + ", " : "")
				+ (receiverAddress1 != null ? "receiverAddress1=" + receiverAddress1 + ", " : "")
				+ (receiverAddress2 != null ? "receiverAddress2=" + receiverAddress2 + ", " : "")
				+ (receiverAddress3 != null ? "receiverAddress3=" + receiverAddress3 + ", " : "")
				+ (receiverCity != null ? "receiverCity=" + receiverCity + ", " : "")
				+ (receiverCountry != null ? "receiverCountry=" + receiverCountry + ", " : "")
				+ (receiverLocationCode != null ? "receiverLocationCode=" + receiverLocationCode + ", " : "")
				+ (receiverName != null ? "receiverName=" + receiverName + ", " : "")
				+ (receiverRegion != null ? "receiverRegion=" + receiverRegion + ", " : "")
				+ (receiverState != null ? "receiverState=" + receiverState + ", " : "")
				+ (receiverVatin != null ? "receiverVatin=" + receiverVatin + ", " : "")
				+ (receiverZipcode != null ? "receiverZipcode=" + receiverZipcode + ", " : "")
				+ (reference1 != null ? "reference1=" + reference1 + ", " : "")
				+ (reference2 != null ? "reference2=" + reference2 + ", " : "")
				+ (reference3 != null ? "reference3=" + reference3 + ", " : "")
				+ (reference4 != null ? "reference4=" + reference4 + ", " : "")
				+ (remitComments != null ? "remitComments=" + remitComments + ", " : "")
				+ (remitToAddress != null ? "remitToAddress=" + remitToAddress + ", " : "")
				+ (routingNotes != null ? "routingNotes=" + routingNotes + ", " : "")
				+ (rtpFlag != null ? "rtpFlag=" + rtpFlag + ", " : "")
			//	+ (rtpFlag1 != null ? "rtpFlag1=" + rtpFlag1 + ", " : "")
				+ (runNo != null ? "runNo=" + runNo + ", " : "")
				+ (scannedInvoiceId != null ? "scannedInvoiceId=" + scannedInvoiceId + ", " : "")
				+ (scannerComments != null ? "scannerComments=" + scannerComments + ", " : "")
				+ (serviceCodeId != null ? "serviceCodeId=" + serviceCodeId + ", " : "")
				+ (shipDate != null ? "shipDate=" + shipDate + ", " : "")
				+ (shipmentDirection != null ? "shipmentDirection=" + shipmentDirection + ", " : "")
				+ (shipperAddress1 != null ? "shipperAddress1=" + shipperAddress1 + ", " : "")
				+ (shipperAddress2 != null ? "shipperAddress2=" + shipperAddress2 + ", " : "")
				+ (shipperAddress3 != null ? "shipperAddress3=" + shipperAddress3 + ", " : "")
				+ (shipperCity != null ? "shipperCity=" + shipperCity + ", " : "")
				+ (shipperCountry != null ? "shipperCountry=" + shipperCountry + ", " : "")
				+ (shipperLocationCode != null ? "shipperLocationCode=" + shipperLocationCode + ", " : "")
				+ (shipperName != null ? "shipperName=" + shipperName + ", " : "")
				+ (shipperRegion != null ? "shipperRegion=" + shipperRegion + ", " : "")
				+ (shipperState != null ? "shipperState=" + shipperState + ", " : "")
				+ (shipperVatin != null ? "shipperVatin=" + shipperVatin + ", " : "")
				+ (shipperZipcode != null ? "shipperZipcode=" + shipperZipcode + ", " : "")
				+ (standardTransitDays != null ? "standardTransitDays=" + standardTransitDays + ", " : "")
				+ (subtotalAmount != null ? "subtotalAmount=" + subtotalAmount + ", " : "")
				+ (tarrifCode != null ? "tarrifCode=" + tarrifCode + ", " : "")
				+ (temp1 != null ? "temp1=" + temp1 + ", " : "")
				+ (totalCharges != null ? "totalCharges=" + totalCharges + ", " : "")
				+ (totalCharges2 != null ? "totalCharges2=" + totalCharges2 + ", " : "")
				+ (totalCharges2Currency != null ? "totalCharges2Currency=" + totalCharges2Currency + ", " : "")
				+ (totalCharges3 != null ? "totalCharges3=" + totalCharges3 + ", " : "")
				+ (totalCharges3Currency != null ? "totalCharges3Currency=" + totalCharges3Currency + ", " : "")
				+ (totalCharges4 != null ? "totalCharges4=" + totalCharges4 + ", " : "")
				+ (totalCharges4Currency != null ? "totalCharges4Currency=" + totalCharges4Currency + ", " : "")
				+ (totalDueAmount != null ? "totalDueAmount=" + totalDueAmount + ", " : "")
				+ (totalInvoicedUom != null ? "totalInvoicedUom=" + totalInvoicedUom + ", " : "")
				+ (totalInvoicedVolume != null ? "totalInvoicedVolume=" + totalInvoicedVolume + ", " : "")
				+ (totalQty != null ? "totalQty=" + totalQty + ", " : "")
				+ (totalShipUnits != null ? "totalShipUnits=" + totalShipUnits + ", " : "")
				+ (totalShipUom != null ? "totalShipUom=" + totalShipUom + ", " : "")
				+ (totalWeight != null ? "totalWeight=" + totalWeight + ", " : "")
				+ (usdApprovedCharges != null ? "usdApprovedCharges=" + usdApprovedCharges + ", " : "")
				+ (usdTotalCharges != null ? "usdTotalCharges=" + usdTotalCharges + ", "
						: "")
				+ (vendorNumber != null ? "vendorNumber=" + vendorNumber + ", " : "")
				+ (shpCarrierTb != null ? "shpCarrierTb=" + shpCarrierTb + ", "
						: "")
				+ (shpCustomerProfileTb != null ? "shpCustomerProfileTb=" + shpCustomerProfileTb + ", "
						: "")
				+ (shpNspCodeValuesTb1 != null ? "shpNspCodeValuesTb1=" + shpNspCodeValuesTb1 + ", " : "")
				+ (shpNspCodeValuesTb2 != null ? "shpNspCodeValuesTb2=" + shpNspCodeValuesTb2 + ", " : "")
				+ (shpNspCodeValuesTb3 != null ? "shpNspCodeValuesTb3=" + shpNspCodeValuesTb3 + ", " : "")
				+ (shpNspCodeValuesTb4 != null ? "shpNspCodeValuesTb4=" + shpNspCodeValuesTb4 + ", " : "")
				+ (shpNspTaskTbs != null
						? "shpNspTaskTbs=" + shpNspTaskTbs.subList(0, Math.min(shpNspTaskTbs.size(), maxLen)) : "")
				+ "]";
	}

}