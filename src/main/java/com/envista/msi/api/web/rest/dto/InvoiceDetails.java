package com.envista.msi.api.web.rest.dto;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

//import com.envista.msi.api.domain.freight.ShpCarrierTb;
//import com.envista.msi.api.domain.freight.ShpCustomerProfileTb;
//import com.envista.msi.api.domain.freight.ShpNspTaskTb;

public class InvoiceDetails{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7054166858214786538L;

	//adding the attributes which are in shpnspinvoicedetailstb.java

	private long nspInvoiceDetailsId;
	private String accountNumber;
	private BigDecimal actualKilometers;
	private BigDecimal actualMiles;
	private String addressType;
	private String auditorComments;
	private BigDecimal averageTransitDays;
	private BigDecimal balanceAmount;
	private Date billDate;
	private String billToLocationCode;
	private BigDecimal billedMiles;
	private String billtoAddress1;
	private String billtoAddress2;
	private String billtoAddress3;
	private String billtoCity;
	private String billtoCountry;
	private String billtoName;
	private String billtoState;
	private String billtoZipcode;
	private String bolNumber;
	private BigDecimal brokParentInvId;
	private BigDecimal calculatedMiles;
	private String carrierComments;
	private BigDecimal checkAmount;
	private Date checkDate;
	private String checkNo;
	private String childCarrierName;
	private String closedBy;
	private Date closedDate;
	private BigDecimal codAmount;
	private BigDecimal consolParentId;
	private BigDecimal countOfPalletsReloaded;
	private Date createDate;
	private String createUser;
	private String creditMemo;
	private BigDecimal currencyCode;
	private Date currencyExchangeDate;
	private BigDecimal currencyExchangeRate;
	private BigDecimal custAction;
	private String custActionBy;
	private Date custActionDate;
	private BigDecimal custActivityHistId;
	private String customerComments;
	private String customerDefined1;
	private String customerDefined2;
	private String customerDefined3;
	private String customsDocumentNumber;
	private String dataEntryComments;
	private Date deliveryDate;
	private String department;
	private String direction;
	private BigDecimal distanceUom;
	private String dupCheckQuery;
	private String dupChkInvoiceId;
	private BigDecimal dwExtractFlag;
	private String ediFileName;
	private BigDecimal equipmentType;
	private BigDecimal exceptionReason;
	private String exportCode;
	private String failedFormat;
	private BigDecimal freightInvoiceType;
	private BigDecimal ftpserverLogId;
	private BigDecimal gainShare;
	private String glAccountsCode;
	private Date glAppliedDate;
	private BigDecimal glCodingAttempted;
	private String glSource;
	private String glbEquipType;
	private String gsaCarrier;
	private Date guaranteedDate;
	private BigDecimal guaranteedService;
	private BigDecimal hazmat;
	private Date houseBolDate;
	private String houseBolNumber;
	private String importCode;
	private BigDecimal incoTermCode;
	private String incoTermPoint;
	private Date interlineBolDate;
	private String interlineBolNumber;
	private BigDecimal interlineCarrier;
	private Date interlineDeliveryDate;
	private String interlineVesselName;
	private String interlineVoyageNumber;
	private BigDecimal invPageCount;
	private Date invoiceDueDate;
	private BigDecimal invoiceHsTaxAmount;
	private String invoiceIsaNumber;
	private String invoiceNumber;
	private BigDecimal invoiceTax1Amount;
	private BigDecimal invoiceTax2Amount;
	private String invoiceType;
	private BigDecimal isAccessorialsChecked;
	private BigDecimal isBillOptionCorrect;
	private BigDecimal isBlacklisted;
	private BigDecimal isDocumentationChecked;
	private BigDecimal isDuplicateBillingChecked;
	private BigDecimal isFreightChargeChecked;
	private BigDecimal isFuelSurchargesChecked;
	private String ladingDescription;
	private String laneDescription;
	private String laneId;
	private Timestamp lastUpdateDate;
	private String lastUpdateUser;
	private String linkedDocuments;
	private Date loadMatchDate;
	private Date loadMatchExcpDate;
	private BigDecimal loadMatched;
	private String loadMatchingExcpComments;
	private String lookUpBolNo;
	private String lookUpInvoiceNo;
	private String lookUpProNo;
	private Date masterBolDate;
	private String masterBolNumber;
	private String merchandiseCategory;
	private String nonMerchandiseCategory;
	private BigDecimal origInvoiceId;
	private BigDecimal paidAmount;
	private String paymentType;
	private String poNumber;
	private String portOfDestination;
	private BigDecimal portOfDestinationX;
	private String portOfDischargeCode;
	private String portOfDischargeCountry;
	private String portOfLoadingCode;
	private String portOfLoadingCountry;
	private String portOfOrigin;
	private BigDecimal portOfOriginX;
	private Date proDate;
	private String proNumber;
	private String productCode;
	private String productDescription;
	private String proofOfDelivery;
	private BigDecimal ratedMiles;
	private String ratingComments;
	private String ratingContract;
	private String receiptNumber;
	private Date receivedDate;
	private String receiverAddress1;
	private String receiverAddress2;
	private String receiverAddress3;
	private String receiverCity;
	private String receiverCountry;
	private String receiverLocationCode;
	private String receiverName;
	private String receiverRegion;
	private String receiverState;
	private String receiverVatin;
	private String receiverZipcode;
	private String reference1;
	private String reference2;
	private String reference3;
	private String reference4;
	private String remitComments;
	private String remitToAddress;
	private String routingNotes;
	private String rtpFlag;
	private BigDecimal rtpFlag1;
	private String runNo;
	//Shp_NSP_Scanned_Invoice_TB
	private BigDecimal scannedInvoiceId;
	private String scannerComments;
	private BigDecimal serviceCodeId;
	private Date shipDate;
	private String shipmentDirection;
	private String shipperAddress1;
	private String shipperAddress2;
	private String shipperAddress3;
	private String shipperCity;
	private String shipperCountry;
	private String shipperLocationCode;
	private String shipperName;
	private String shipperRegion;
	private String shipperState;
	private String shipperVatin;
	private String shipperZipcode;
	private BigDecimal standardTransitDays;
	private BigDecimal subtotalAmount;
	private BigDecimal tarrifCode;
	private String temp1;
	private BigDecimal totalCharges;
	private BigDecimal totalCharges2;
	private BigDecimal totalCharges2Currency;
	private BigDecimal totalCharges3;
	private BigDecimal totalCharges3Currency;
	private BigDecimal totalCharges4;
	private BigDecimal totalCharges4Currency;
	private BigDecimal totalDueAmount;
	private BigDecimal totalInvoicedUom;
	private BigDecimal totalInvoicedVolume;
	private BigDecimal totalQty;
	private BigDecimal totalShipUnits;
	private BigDecimal totalShipUom;
	private BigDecimal totalWeight;
	private BigDecimal usdApprovedCharges;
	private BigDecimal usdTotalCharges;
	private String vendorNumber;
	
	/*//join column attributes
	private ShpCarrierTb carrier;
	private ShpCustomerProfileTb customerProfile;*/
	/*private ShpNspCodeValuesTb shpNspCodeValuesTb1;*/
	private String invoiceStatus;
//	private ShpNspCodeValuesTb shpNspCodeValuesTb2;
	private String billOption;
	//private ShpNspCodeValuesTb shpNspCodeValuesTb3;
	private String invoiceMode;
	//private ShpNspCodeValuesTb shpNspCodeValuesTb4;
	private String totalWeightUOM;
	/*private List<ShpNspTaskTb> shpNspTaskTbs;*/
	
	
	
	
	//Setter and Getter Methods
	public long getNspInvoiceDetailsId() {
		return nspInvoiceDetailsId;
	}
	public void setNspInvoiceDetailsId(long nspInvoiceDetailsId) {
		this.nspInvoiceDetailsId = nspInvoiceDetailsId;
	}
	public String getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
	public BigDecimal getActualKilometers() {
		return actualKilometers;
	}
	public void setActualKilometers(BigDecimal actualKilometers) {
		this.actualKilometers = actualKilometers;
	}
	public BigDecimal getActualMiles() {
		return actualMiles;
	}
	public void setActualMiles(BigDecimal actualMiles) {
		this.actualMiles = actualMiles;
	}
	public String getAddressType() {
		return addressType;
	}
	public void setAddressType(String addressType) {
		this.addressType = addressType;
	}
	public String getAuditorComments() {
		return auditorComments;
	}
	public void setAuditorComments(String auditorComments) {
		this.auditorComments = auditorComments;
	}
	public BigDecimal getAverageTransitDays() {
		return averageTransitDays;
	}
	public void setAverageTransitDays(BigDecimal averageTransitDays) {
		this.averageTransitDays = averageTransitDays;
	}
	public BigDecimal getBalanceAmount() {
		return balanceAmount;
	}
	public void setBalanceAmount(BigDecimal balanceAmount) {
		this.balanceAmount = balanceAmount;
	}
	public Date getBillDate() {
		return billDate;
	}
	public void setBillDate(Date billDate) {
		this.billDate = billDate;
	}
	public String getBillToLocationCode() {
		return billToLocationCode;
	}
	public void setBillToLocationCode(String billToLocationCode) {
		this.billToLocationCode = billToLocationCode;
	}
	public BigDecimal getBilledMiles() {
		return billedMiles;
	}
	public void setBilledMiles(BigDecimal billedMiles) {
		this.billedMiles = billedMiles;
	}
	public String getBilltoAddress1() {
		return billtoAddress1;
	}
	public void setBilltoAddress1(String billtoAddress1) {
		this.billtoAddress1 = billtoAddress1;
	}
	public String getBilltoAddress2() {
		return billtoAddress2;
	}
	public void setBilltoAddress2(String billtoAddress2) {
		this.billtoAddress2 = billtoAddress2;
	}
	public String getBilltoAddress3() {
		return billtoAddress3;
	}
	public void setBilltoAddress3(String billtoAddress3) {
		this.billtoAddress3 = billtoAddress3;
	}
	public String getBilltoCity() {
		return billtoCity;
	}
	public void setBilltoCity(String billtoCity) {
		this.billtoCity = billtoCity;
	}
	public String getBilltoCountry() {
		return billtoCountry;
	}
	public void setBilltoCountry(String billtoCountry) {
		this.billtoCountry = billtoCountry;
	}
	public String getBilltoName() {
		return billtoName;
	}
	public void setBilltoName(String billtoName) {
		this.billtoName = billtoName;
	}
	public String getBilltoState() {
		return billtoState;
	}
	public void setBilltoState(String billtoState) {
		this.billtoState = billtoState;
	}
	public String getBilltoZipcode() {
		return billtoZipcode;
	}
	public void setBilltoZipcode(String billtoZipcode) {
		this.billtoZipcode = billtoZipcode;
	}
	public String getBolNumber() {
		return bolNumber;
	}
	public void setBolNumber(String bolNumber) {
		this.bolNumber = bolNumber;
	}
	public BigDecimal getBrokParentInvId() {
		return brokParentInvId;
	}
	public void setBrokParentInvId(BigDecimal brokParentInvId) {
		this.brokParentInvId = brokParentInvId;
	}
	public BigDecimal getCalculatedMiles() {
		return calculatedMiles;
	}
	public void setCalculatedMiles(BigDecimal calculatedMiles) {
		this.calculatedMiles = calculatedMiles;
	}
	public String getCarrierComments() {
		return carrierComments;
	}
	public void setCarrierComments(String carrierComments) {
		this.carrierComments = carrierComments;
	}
	public BigDecimal getCheckAmount() {
		return checkAmount;
	}
	public void setCheckAmount(BigDecimal checkAmount) {
		this.checkAmount = checkAmount;
	}
	public Date getCheckDate() {
		return checkDate;
	}
	public void setCheckDate(Date checkDate) {
		this.checkDate = checkDate;
	}
	public String getCheckNo() {
		return checkNo;
	}
	public void setCheckNo(String checkNo) {
		this.checkNo = checkNo;
	}
	public String getChildCarrierName() {
		return childCarrierName;
	}
	public void setChildCarrierName(String childCarrierName) {
		this.childCarrierName = childCarrierName;
	}
	public String getClosedBy() {
		return closedBy;
	}
	public void setClosedBy(String closedBy) {
		this.closedBy = closedBy;
	}
	public Date getClosedDate() {
		return closedDate;
	}
	public void setClosedDate(Date closedDate) {
		this.closedDate = closedDate;
	}
	public BigDecimal getCodAmount() {
		return codAmount;
	}
	public void setCodAmount(BigDecimal codAmount) {
		this.codAmount = codAmount;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public BigDecimal getConsolParentId() {
		return consolParentId;
	}
	public void setConsolParentId(BigDecimal consolParentId) {
		this.consolParentId = consolParentId;
	}
	public BigDecimal getCountOfPalletsReloaded() {
		return countOfPalletsReloaded;
	}
	public void setCountOfPalletsReloaded(BigDecimal countOfPalletsReloaded) {
		this.countOfPalletsReloaded = countOfPalletsReloaded;
	}
	
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public String getCreateUser() {
		return createUser;
	}
	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}
	public String getCreditMemo() {
		return creditMemo;
	}
	public void setCreditMemo(String creditMemo) {
		this.creditMemo = creditMemo;
	}
	public BigDecimal getCurrencyCode() {
		return currencyCode;
	}
	public void setCurrencyCode(BigDecimal currencyCode) {
		this.currencyCode = currencyCode;
	}
	public Date getCurrencyExchangeDate() {
		return currencyExchangeDate;
	}
	public void setCurrencyExchangeDate(Date currencyExchangeDate) {
		this.currencyExchangeDate = currencyExchangeDate;
	}
	public BigDecimal getCurrencyExchangeRate() {
		return currencyExchangeRate;
	}
	public void setCurrencyExchangeRate(BigDecimal currencyExchangeRate) {
		this.currencyExchangeRate = currencyExchangeRate;
	}
	public BigDecimal getCustAction() {
		return custAction;
	}
	public void setCustAction(BigDecimal custAction) {
		this.custAction = custAction;
	}
	public String getCustActionBy() {
		return custActionBy;
	}
	public void setCustActionBy(String custActionBy) {
		this.custActionBy = custActionBy;
	}
	public Date getCustActionDate() {
		return custActionDate;
	}
	public void setCustActionDate(Date custActionDate) {
		this.custActionDate = custActionDate;
	}
	public BigDecimal getCustActivityHistId() {
		return custActivityHistId;
	}
	public void setCustActivityHistId(BigDecimal custActivityHistId) {
		this.custActivityHistId = custActivityHistId;
	}
	public String getCustomerComments() {
		return customerComments;
	}
	public void setCustomerComments(String customerComments) {
		this.customerComments = customerComments;
	}
	public String getCustomerDefined1() {
		return customerDefined1;
	}
	public void setCustomerDefined1(String customerDefined1) {
		this.customerDefined1 = customerDefined1;
	}
	public String getCustomerDefined2() {
		return customerDefined2;
	}
	public void setCustomerDefined2(String customerDefined2) {
		this.customerDefined2 = customerDefined2;
	}
	public String getCustomerDefined3() {
		return customerDefined3;
	}
	public void setCustomerDefined3(String customerDefined3) {
		this.customerDefined3 = customerDefined3;
	}
	public String getCustomsDocumentNumber() {
		return customsDocumentNumber;
	}
	public void setCustomsDocumentNumber(String customsDocumentNumber) {
		this.customsDocumentNumber = customsDocumentNumber;
	}
	public String getDataEntryComments() {
		return dataEntryComments;
	}
	public void setDataEntryComments(String dataEntryComments) {
		this.dataEntryComments = dataEntryComments;
	}
	public Date getDeliveryDate() {
		return deliveryDate;
	}
	public void setDeliveryDate(Date deliveryDate) {
		this.deliveryDate = deliveryDate;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getDirection() {
		return direction;
	}
	public void setDirection(String direction) {
		this.direction = direction;
	}
	public BigDecimal getDistanceUom() {
		return distanceUom;
	}
	public void setDistanceUom(BigDecimal distanceUom) {
		this.distanceUom = distanceUom;
	}
	public String getDupCheckQuery() {
		return dupCheckQuery;
	}
	public void setDupCheckQuery(String dupCheckQuery) {
		this.dupCheckQuery = dupCheckQuery;
	}
	public String getDupChkInvoiceId() {
		return dupChkInvoiceId;
	}
	public void setDupChkInvoiceId(String dupChkInvoiceId) {
		this.dupChkInvoiceId = dupChkInvoiceId;
	}
	public BigDecimal getDwExtractFlag() {
		return dwExtractFlag;
	}
	public void setDwExtractFlag(BigDecimal dwExtractFlag) {
		this.dwExtractFlag = dwExtractFlag;
	}
	public String getEdiFileName() {
		return ediFileName;
	}
	public void setEdiFileName(String ediFileName) {
		this.ediFileName = ediFileName;
	}
	public BigDecimal getEquipmentType() {
		return equipmentType;
	}
	public void setEquipmentType(BigDecimal equipmentType) {
		this.equipmentType = equipmentType;
	}
	public BigDecimal getExceptionReason() {
		return exceptionReason;
	}
	public void setExceptionReason(BigDecimal exceptionReason) {
		this.exceptionReason = exceptionReason;
	}
	public String getExportCode() {
		return exportCode;
	}
	public void setExportCode(String exportCode) {
		this.exportCode = exportCode;
	}
	public String getFailedFormat() {
		return failedFormat;
	}
	public void setFailedFormat(String failedFormat) {
		this.failedFormat = failedFormat;
	}
	public BigDecimal getFreightInvoiceType() {
		return freightInvoiceType;
	}
	public void setFreightInvoiceType(BigDecimal freightInvoiceType) {
		this.freightInvoiceType = freightInvoiceType;
	}
	public BigDecimal getFtpserverLogId() {
		return ftpserverLogId;
	}
	public void setFtpserverLogId(BigDecimal ftpserverLogId) {
		this.ftpserverLogId = ftpserverLogId;
	}
	public BigDecimal getGainShare() {
		return gainShare;
	}
	public void setGainShare(BigDecimal gainShare) {
		this.gainShare = gainShare;
	}
	public String getGlAccountsCode() {
		return glAccountsCode;
	}
	public void setGlAccountsCode(String glAccountsCode) {
		this.glAccountsCode = glAccountsCode;
	}
	public Date getGlAppliedDate() {
		return glAppliedDate;
	}
	public void setGlAppliedDate(Date glAppliedDate) {
		this.glAppliedDate = glAppliedDate;
	}
	public BigDecimal getGlCodingAttempted() {
		return glCodingAttempted;
	}
	public void setGlCodingAttempted(BigDecimal glCodingAttempted) {
		this.glCodingAttempted = glCodingAttempted;
	}
	public String getGlSource() {
		return glSource;
	}
	public void setGlSource(String glSource) {
		this.glSource = glSource;
	}
	public String getGlbEquipType() {
		return glbEquipType;
	}
	public void setGlbEquipType(String glbEquipType) {
		this.glbEquipType = glbEquipType;
	}
	public String getGsaCarrier() {
		return gsaCarrier;
	}
	public void setGsaCarrier(String gsaCarrier) {
		this.gsaCarrier = gsaCarrier;
	}
	public Date getGuaranteedDate() {
		return guaranteedDate;
	}
	public void setGuaranteedDate(Date guaranteedDate) {
		this.guaranteedDate = guaranteedDate;
	}
	public BigDecimal getGuaranteedService() {
		return guaranteedService;
	}
	public void setGuaranteedService(BigDecimal guaranteedService) {
		this.guaranteedService = guaranteedService;
	}
	public BigDecimal getHazmat() {
		return hazmat;
	}
	public void setHazmat(BigDecimal hazmat) {
		this.hazmat = hazmat;
	}
	public Date getHouseBolDate() {
		return houseBolDate;
	}
	public void setHouseBolDate(Date houseBolDate) {
		this.houseBolDate = houseBolDate;
	}
	public String getHouseBolNumber() {
		return houseBolNumber;
	}
	public void setHouseBolNumber(String houseBolNumber) {
		this.houseBolNumber = houseBolNumber;
	}
	public String getImportCode() {
		return importCode;
	}
	public void setImportCode(String importCode) {
		this.importCode = importCode;
	}
	public BigDecimal getIncoTermCode() {
		return incoTermCode;
	}
	public void setIncoTermCode(BigDecimal incoTermCode) {
		this.incoTermCode = incoTermCode;
	}
	public String getIncoTermPoint() {
		return incoTermPoint;
	}
	public void setIncoTermPoint(String incoTermPoint) {
		this.incoTermPoint = incoTermPoint;
	}
	public Date getInterlineBolDate() {
		return interlineBolDate;
	}
	public void setInterlineBolDate(Date interlineBolDate) {
		this.interlineBolDate = interlineBolDate;
	}
	public String getInterlineBolNumber() {
		return interlineBolNumber;
	}
	public void setInterlineBolNumber(String interlineBolNumber) {
		this.interlineBolNumber = interlineBolNumber;
	}
	public BigDecimal getInterlineCarrier() {
		return interlineCarrier;
	}
	public void setInterlineCarrier(BigDecimal interlineCarrier) {
		this.interlineCarrier = interlineCarrier;
	}
	public Date getInterlineDeliveryDate() {
		return interlineDeliveryDate;
	}
	public void setInterlineDeliveryDate(Date interlineDeliveryDate) {
		this.interlineDeliveryDate = interlineDeliveryDate;
	}
	public String getInterlineVesselName() {
		return interlineVesselName;
	}
	public void setInterlineVesselName(String interlineVesselName) {
		this.interlineVesselName = interlineVesselName;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public String getInterlineVoyageNumber() {
		return interlineVoyageNumber;
	}
	public void setInterlineVoyageNumber(String interlineVoyageNumber) {
		this.interlineVoyageNumber = interlineVoyageNumber;
	}
	public BigDecimal getInvPageCount() {
		return invPageCount;
	}
	public void setInvPageCount(BigDecimal invPageCount) {
		this.invPageCount = invPageCount;
	}
	public Date getInvoiceDueDate() {
		return invoiceDueDate;
	}
	public void setInvoiceDueDate(Date invoiceDueDate) {
		this.invoiceDueDate = invoiceDueDate;
	}
	public BigDecimal getInvoiceHsTaxAmount() {
		return invoiceHsTaxAmount;
	}
	public void setInvoiceHsTaxAmount(BigDecimal invoiceHsTaxAmount) {
		this.invoiceHsTaxAmount = invoiceHsTaxAmount;
	}
	public String getInvoiceIsaNumber() {
		return invoiceIsaNumber;
	}
	public void setInvoiceIsaNumber(String invoiceIsaNumber) {
		this.invoiceIsaNumber = invoiceIsaNumber;
	}
	public String getInvoiceNumber() {
		return invoiceNumber;
	}
	public void setInvoiceNumber(String invoiceNumber) {
		this.invoiceNumber = invoiceNumber;
	}
	public BigDecimal getInvoiceTax1Amount() {
		return invoiceTax1Amount;
	}
	public void setInvoiceTax1Amount(BigDecimal invoiceTax1Amount) {
		this.invoiceTax1Amount = invoiceTax1Amount;
	}
	public BigDecimal getInvoiceTax2Amount() {
		return invoiceTax2Amount;
	}
	public void setInvoiceTax2Amount(BigDecimal invoiceTax2Amount) {
		this.invoiceTax2Amount = invoiceTax2Amount;
	}
	public String getInvoiceType() {
		return invoiceType;
	}
	public void setInvoiceType(String invoiceType) {
		this.invoiceType = invoiceType;
	}
	public BigDecimal getIsAccessorialsChecked() {
		return isAccessorialsChecked;
	}
	public void setIsAccessorialsChecked(BigDecimal isAccessorialsChecked) {
		this.isAccessorialsChecked = isAccessorialsChecked;
	}
	public BigDecimal getIsBillOptionCorrect() {
		return isBillOptionCorrect;
	}
	public void setIsBillOptionCorrect(BigDecimal isBillOptionCorrect) {
		this.isBillOptionCorrect = isBillOptionCorrect;
	}
	public BigDecimal getIsBlacklisted() {
		return isBlacklisted;
	}
	public void setIsBlacklisted(BigDecimal isBlacklisted) {
		this.isBlacklisted = isBlacklisted;
	}
	public BigDecimal getIsDocumentationChecked() {
		return isDocumentationChecked;
	}
	public void setIsDocumentationChecked(BigDecimal isDocumentationChecked) {
		this.isDocumentationChecked = isDocumentationChecked;
	}
	public BigDecimal getIsDuplicateBillingChecked() {
		return isDuplicateBillingChecked;
	}
	public void setIsDuplicateBillingChecked(BigDecimal isDuplicateBillingChecked) {
		this.isDuplicateBillingChecked = isDuplicateBillingChecked;
	}
	public BigDecimal getIsFreightChargeChecked() {
		return isFreightChargeChecked;
	}
	public void setIsFreightChargeChecked(BigDecimal isFreightChargeChecked) {
		this.isFreightChargeChecked = isFreightChargeChecked;
	}
	public BigDecimal getIsFuelSurchargesChecked() {
		return isFuelSurchargesChecked;
	}
	public void setIsFuelSurchargesChecked(BigDecimal isFuelSurchargesChecked) {
		this.isFuelSurchargesChecked = isFuelSurchargesChecked;
	}
	public String getLadingDescription() {
		return ladingDescription;
	}
	public void setLadingDescription(String ladingDescription) {
		this.ladingDescription = ladingDescription;
	}
	public String getLaneDescription() {
		return laneDescription;
	}
	public void setLaneDescription(String laneDescription) {
		this.laneDescription = laneDescription;
	}
	public String getLaneId() {
		return laneId;
	}
	public void setLaneId(String laneId) {
		this.laneId = laneId;
	}
	public Timestamp getLastUpdateDate() {
		return lastUpdateDate;
	}
	public void setLastUpdateDate(Timestamp lastUpdateDate) {
		this.lastUpdateDate = lastUpdateDate;
	}
	public String getLastUpdateUser() {
		return lastUpdateUser;
	}
	public void setLastUpdateUser(String lastUpdateUser) {
		this.lastUpdateUser = lastUpdateUser;
	}
	public String getLinkedDocuments() {
		return linkedDocuments;
	}
	public void setLinkedDocuments(String linkedDocuments) {
		this.linkedDocuments = linkedDocuments;
	}
	public Date getLoadMatchDate() {
		return loadMatchDate;
	}
	public void setLoadMatchDate(Date loadMatchDate) {
		this.loadMatchDate = loadMatchDate;
	}
	public Date getLoadMatchExcpDate() {
		return loadMatchExcpDate;
	}
	public void setLoadMatchExcpDate(Date loadMatchExcpDate) {
		this.loadMatchExcpDate = loadMatchExcpDate;
	}
	public BigDecimal getLoadMatched() {
		return loadMatched;
	}
	public void setLoadMatched(BigDecimal loadMatched) {
		this.loadMatched = loadMatched;
	}
	public String getLoadMatchingExcpComments() {
		return loadMatchingExcpComments;
	}
	public void setLoadMatchingExcpComments(String loadMatchingExcpComments) {
		this.loadMatchingExcpComments = loadMatchingExcpComments;
	}
	public String getLookUpBolNo() {
		return lookUpBolNo;
	}
	public void setLookUpBolNo(String lookUpBolNo) {
		this.lookUpBolNo = lookUpBolNo;
	}
	public String getLookUpInvoiceNo() {
		return lookUpInvoiceNo;
	}
	public void setLookUpInvoiceNo(String lookUpInvoiceNo) {
		this.lookUpInvoiceNo = lookUpInvoiceNo;
	}
	public String getLookUpProNo() {
		return lookUpProNo;
	}
	public void setLookUpProNo(String lookUpProNo) {
		this.lookUpProNo = lookUpProNo;
	}
	public Date getMasterBolDate() {
		return masterBolDate;
	}
	public void setMasterBolDate(Date masterBolDate) {
		this.masterBolDate = masterBolDate;
	}
	public String getMasterBolNumber() {
		return masterBolNumber;
	}
	public void setMasterBolNumber(String masterBolNumber) {
		this.masterBolNumber = masterBolNumber;
	}
	public String getMerchandiseCategory() {
		return merchandiseCategory;
	}
	public void setMerchandiseCategory(String merchandiseCategory) {
		this.merchandiseCategory = merchandiseCategory;
	}
	public String getNonMerchandiseCategory() {
		return nonMerchandiseCategory;
	}
	public void setNonMerchandiseCategory(String nonMerchandiseCategory) {
		this.nonMerchandiseCategory = nonMerchandiseCategory;
	}
	public BigDecimal getOrigInvoiceId() {
		return origInvoiceId;
	}
	public void setOrigInvoiceId(BigDecimal origInvoiceId) {
		this.origInvoiceId = origInvoiceId;
	}
	public BigDecimal getPaidAmount() {
		return paidAmount;
	}
	public void setPaidAmount(BigDecimal paidAmount) {
		this.paidAmount = paidAmount;
	}
	public String getPaymentType() {
		return paymentType;
	}
	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}
	public String getPoNumber() {
		return poNumber;
	}
	public void setPoNumber(String poNumber) {
		this.poNumber = poNumber;
	}
	public String getPortOfDestination() {
		return portOfDestination;
	}
	public void setPortOfDestination(String portOfDestination) {
		this.portOfDestination = portOfDestination;
	}
	public BigDecimal getPortOfDestinationX() {
		return portOfDestinationX;
	}
	public void setPortOfDestinationX(BigDecimal portOfDestinationX) {
		this.portOfDestinationX = portOfDestinationX;
	}
	public String getPortOfDischargeCode() {
		return portOfDischargeCode;
	}
	public void setPortOfDischargeCode(String portOfDischargeCode) {
		this.portOfDischargeCode = portOfDischargeCode;
	}
	public String getPortOfDischargeCountry() {
		return portOfDischargeCountry;
	}
	public void setPortOfDischargeCountry(String portOfDischargeCountry) {
		this.portOfDischargeCountry = portOfDischargeCountry;
	}
	public String getPortOfLoadingCode() {
		return portOfLoadingCode;
	}
	public void setPortOfLoadingCode(String portOfLoadingCode) {
		this.portOfLoadingCode = portOfLoadingCode;
	}
	public String getPortOfLoadingCountry() {
		return portOfLoadingCountry;
	}
	public void setPortOfLoadingCountry(String portOfLoadingCountry) {
		this.portOfLoadingCountry = portOfLoadingCountry;
	}
	public String getPortOfOrigin() {
		return portOfOrigin;
	}
	public void setPortOfOrigin(String portOfOrigin) {
		this.portOfOrigin = portOfOrigin;
	}
	public BigDecimal getPortOfOriginX() {
		return portOfOriginX;
	}
	public void setPortOfOriginX(BigDecimal portOfOriginX) {
		this.portOfOriginX = portOfOriginX;
	}
	public Date getProDate() {
		return proDate;
	}
	public void setProDate(Date proDate) {
		this.proDate = proDate;
	}
	public String getProNumber() {
		return proNumber;
	}
	public void setProNumber(String proNumber) {
		this.proNumber = proNumber;
	}
	public String getProductCode() {
		return productCode;
	}
	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}
	public String getProductDescription() {
		return productDescription;
	}
	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}
	public String getProofOfDelivery() {
		return proofOfDelivery;
	}
	public void setProofOfDelivery(String proofOfDelivery) {
		this.proofOfDelivery = proofOfDelivery;
	}
	public BigDecimal getRatedMiles() {
		return ratedMiles;
	}
	public void setRatedMiles(BigDecimal ratedMiles) {
		this.ratedMiles = ratedMiles;
	}
	public String getRatingComments() {
		return ratingComments;
	}
	public void setRatingComments(String ratingComments) {
		this.ratingComments = ratingComments;
	}
	public String getRatingContract() {
		return ratingContract;
	}
	public void setRatingContract(String ratingContract) {
		this.ratingContract = ratingContract;
	}
	public String getReceiptNumber() {
		return receiptNumber;
	}
	public void setReceiptNumber(String receiptNumber) {
		this.receiptNumber = receiptNumber;
	}
	public Date getReceivedDate() {
		return receivedDate;
	}
	public void setReceivedDate(Date receivedDate) {
		this.receivedDate = receivedDate;
	}
	public String getReceiverAddress1() {
		return receiverAddress1;
	}
	public void setReceiverAddress1(String receiverAddress1) {
		this.receiverAddress1 = receiverAddress1;
	}
	public String getReceiverAddress2() {
		return receiverAddress2;
	}
	public void setReceiverAddress2(String receiverAddress2) {
		this.receiverAddress2 = receiverAddress2;
	}
	public String getReceiverAddress3() {
		return receiverAddress3;
	}
	public void setReceiverAddress3(String receiverAddress3) {
		this.receiverAddress3 = receiverAddress3;
	}
	public String getReceiverCity() {
		return receiverCity;
	}
	public void setReceiverCity(String receiverCity) {
		this.receiverCity = receiverCity;
	}
	public String getReceiverCountry() {
		return receiverCountry;
	}
	public void setReceiverCountry(String receiverCountry) {
		this.receiverCountry = receiverCountry;
	}
	public String getReceiverLocationCode() {
		return receiverLocationCode;
	}
	public void setReceiverLocationCode(String receiverLocationCode) {
		this.receiverLocationCode = receiverLocationCode;
	}
	public String getReceiverName() {
		return receiverName;
	}
	public void setReceiverName(String receiverName) {
		this.receiverName = receiverName;
	}
	public String getReceiverRegion() {
		return receiverRegion;
	}
	public void setReceiverRegion(String receiverRegion) {
		this.receiverRegion = receiverRegion;
	}
	public String getReceiverState() {
		return receiverState;
	}
	public void setReceiverState(String receiverState) {
		this.receiverState = receiverState;
	}
	public String getReceiverVatin() {
		return receiverVatin;
	}
	public void setReceiverVatin(String receiverVatin) {
		this.receiverVatin = receiverVatin;
	}
	public String getReceiverZipcode() {
		return receiverZipcode;
	}
	public void setReceiverZipcode(String receiverZipcode) {
		this.receiverZipcode = receiverZipcode;
	}
	public String getReference1() {
		return reference1;
	}
	public void setReference1(String reference1) {
		this.reference1 = reference1;
	}
	public String getReference2() {
		return reference2;
	}
	public void setReference2(String reference2) {
		this.reference2 = reference2;
	}
	public String getReference3() {
		return reference3;
	}
	public void setReference3(String reference3) {
		this.reference3 = reference3;
	}
	public String getReference4() {
		return reference4;
	}
	public void setReference4(String reference4) {
		this.reference4 = reference4;
	}
	public String getRemitComments() {
		return remitComments;
	}
	public void setRemitComments(String remitComments) {
		this.remitComments = remitComments;
	}
	public String getRemitToAddress() {
		return remitToAddress;
	}
	public void setRemitToAddress(String remitToAddress) {
		this.remitToAddress = remitToAddress;
	}
	public String getRoutingNotes() {
		return routingNotes;
	}
	public void setRoutingNotes(String routingNotes) {
		this.routingNotes = routingNotes;
	}
	public String getRtpFlag() {
		return rtpFlag;
	}
	public void setRtpFlag(String rtpFlag) {
		this.rtpFlag = rtpFlag;
	}
	public BigDecimal getRtpFlag1() {
		return rtpFlag1;
	}
	public void setRtpFlag1(BigDecimal rtpFlag1) {
		this.rtpFlag1 = rtpFlag1;
	}
	public String getRunNo() {
		return runNo;
	}
	public void setRunNo(String runNo) {
		this.runNo = runNo;
	}
	public BigDecimal getScannedInvoiceId() {
		return scannedInvoiceId;
	}
	public void setScannedInvoiceId(BigDecimal scannedInvoiceId) {
		this.scannedInvoiceId = scannedInvoiceId;
	}
	public String getScannerComments() {
		return scannerComments;
	}
	public void setScannerComments(String scannerComments) {
		this.scannerComments = scannerComments;
	}
	public BigDecimal getServiceCodeId() {
		return serviceCodeId;
	}
	public void setServiceCodeId(BigDecimal serviceCodeId) {
		this.serviceCodeId = serviceCodeId;
	}
	public Date getShipDate() {
		return shipDate;
	}
	public void setShipDate(Date shipDate) {
		this.shipDate = shipDate;
	}
	public String getShipmentDirection() {
		return shipmentDirection;
	}
	public void setShipmentDirection(String shipmentDirection) {
		this.shipmentDirection = shipmentDirection;
	}
	public String getShipperAddress1() {
		return shipperAddress1;
	}
	public void setShipperAddress1(String shipperAddress1) {
		this.shipperAddress1 = shipperAddress1;
	}
	public String getShipperAddress2() {
		return shipperAddress2;
	}
	public void setShipperAddress2(String shipperAddress2) {
		this.shipperAddress2 = shipperAddress2;
	}
	public String getShipperAddress3() {
		return shipperAddress3;
	}
	public void setShipperAddress3(String shipperAddress3) {
		this.shipperAddress3 = shipperAddress3;
	}
	public String getShipperCity() {
		return shipperCity;
	}
	public void setShipperCity(String shipperCity) {
		this.shipperCity = shipperCity;
	}
	public String getShipperCountry() {
		return shipperCountry;
	}
	public void setShipperCountry(String shipperCountry) {
		this.shipperCountry = shipperCountry;
	}
	public String getShipperLocationCode() {
		return shipperLocationCode;
	}
	public void setShipperLocationCode(String shipperLocationCode) {
		this.shipperLocationCode = shipperLocationCode;
	}
	public String getShipperName() {
		return shipperName;
	}
	public void setShipperName(String shipperName) {
		this.shipperName = shipperName;
	}
	public String getShipperRegion() {
		return shipperRegion;
	}
	public void setShipperRegion(String shipperRegion) {
		this.shipperRegion = shipperRegion;
	}
	public String getShipperState() {
		return shipperState;
	}
	public void setShipperState(String shipperState) {
		this.shipperState = shipperState;
	}
	public String getShipperVatin() {
		return shipperVatin;
	}
	public void setShipperVatin(String shipperVatin) {
		this.shipperVatin = shipperVatin;
	}
	public String getShipperZipcode() {
		return shipperZipcode;
	}
	public void setShipperZipcode(String shipperZipcode) {
		this.shipperZipcode = shipperZipcode;
	}
	public BigDecimal getStandardTransitDays() {
		return standardTransitDays;
	}
	public void setStandardTransitDays(BigDecimal standardTransitDays) {
		this.standardTransitDays = standardTransitDays;
	}
	public BigDecimal getSubtotalAmount() {
		return subtotalAmount;
	}
	public void setSubtotalAmount(BigDecimal subtotalAmount) {
		this.subtotalAmount = subtotalAmount;
	}
	public BigDecimal getTarrifCode() {
		return tarrifCode;
	}
	public void setTarrifCode(BigDecimal tarrifCode) {
		this.tarrifCode = tarrifCode;
	}
	public String getTemp1() {
		return temp1;
	}
	public void setTemp1(String temp1) {
		this.temp1 = temp1;
	}
	public BigDecimal getTotalCharges() {
		return totalCharges;
	}
	public void setTotalCharges(BigDecimal totalCharges) {
		this.totalCharges = totalCharges;
	}
	public BigDecimal getTotalCharges2() {
		return totalCharges2;
	}
	public void setTotalCharges2(BigDecimal totalCharges2) {
		this.totalCharges2 = totalCharges2;
	}
	public BigDecimal getTotalCharges2Currency() {
		return totalCharges2Currency;
	}
	public void setTotalCharges2Currency(BigDecimal totalCharges2Currency) {
		this.totalCharges2Currency = totalCharges2Currency;
	}
	public BigDecimal getTotalCharges3() {
		return totalCharges3;
	}
	public void setTotalCharges3(BigDecimal totalCharges3) {
		this.totalCharges3 = totalCharges3;
	}
	public BigDecimal getTotalCharges3Currency() {
		return totalCharges3Currency;
	}
	public void setTotalCharges3Currency(BigDecimal totalCharges3Currency) {
		this.totalCharges3Currency = totalCharges3Currency;
	}
	public BigDecimal getTotalCharges4() {
		return totalCharges4;
	}
	public void setTotalCharges4(BigDecimal totalCharges4) {
		this.totalCharges4 = totalCharges4;
	}
	public BigDecimal getTotalCharges4Currency() {
		return totalCharges4Currency;
	}
	public void setTotalCharges4Currency(BigDecimal totalCharges4Currency) {
		this.totalCharges4Currency = totalCharges4Currency;
	}
	public BigDecimal getTotalDueAmount() {
		return totalDueAmount;
	}
	public void setTotalDueAmount(BigDecimal totalDueAmount) {
		this.totalDueAmount = totalDueAmount;
	}
	public BigDecimal getTotalInvoicedUom() {
		return totalInvoicedUom;
	}
	public void setTotalInvoicedUom(BigDecimal totalInvoicedUom) {
		this.totalInvoicedUom = totalInvoicedUom;
	}
	public BigDecimal getTotalInvoicedVolume() {
		return totalInvoicedVolume;
	}
	public void setTotalInvoicedVolume(BigDecimal totalInvoicedVolume) {
		this.totalInvoicedVolume = totalInvoicedVolume;
	}
	public BigDecimal getTotalQty() {
		return totalQty;
	}
	public void setTotalQty(BigDecimal totalQty) {
		this.totalQty = totalQty;
	}
	public BigDecimal getTotalShipUnits() {
		return totalShipUnits;
	}
	public void setTotalShipUnits(BigDecimal totalShipUnits) {
		this.totalShipUnits = totalShipUnits;
	}
	public BigDecimal getTotalShipUom() {
		return totalShipUom;
	}
	public void setTotalShipUom(BigDecimal totalShipUom) {
		this.totalShipUom = totalShipUom;
	}
	public BigDecimal getTotalWeight() {
		return totalWeight;
	}
	public void setTotalWeight(BigDecimal totalWeight) {
		this.totalWeight = totalWeight;
	}
	public BigDecimal getUsdApprovedCharges() {
		return usdApprovedCharges;
	}
	public void setUsdApprovedCharges(BigDecimal usdApprovedCharges) {
		this.usdApprovedCharges = usdApprovedCharges;
	}
	public BigDecimal getUsdTotalCharges() {
		return usdTotalCharges;
	}
	public void setUsdTotalCharges(BigDecimal usdTotalCharges) {
		this.usdTotalCharges = usdTotalCharges;
	}
	public String getVendorNumber() {
		return vendorNumber;
	}
	public void setVendorNumber(String vendorNumber) {
		this.vendorNumber = vendorNumber;
	}
	/*public ShpCarrierTb getShpCarrierTb() {
		return carrier;
	}
	public void setShpCarrierTb(ShpCarrierTb shpCarrierTb) {
		this.carrier = shpCarrierTb;
	}
	public ShpCustomerProfileTb getShpCustomerProfileTb() {
		return customerProfile;
	}
	public void setShpCustomerProfileTb(ShpCustomerProfileTb shpCustomerProfileTb) {
		this.customerProfile = shpCustomerProfileTb;
	}*/
	/*public ShpNspCodeValuesTb getShpNspCodeValuesTb1() {
		return shpNspCodeValuesTb1;
	}
	public void setShpNspCodeValuesTb1(ShpNspCodeValuesTb shpNspCodeValuesTb1) {
		this.shpNspCodeValuesTb1 = shpNspCodeValuesTb1;
	}*/
	
	/*public ShpNspCodeValuesTb getShpNspCodeValuesTb2() {
		return shpNspCodeValuesTb2;
	}*/
	public String getInvoiceStatus() {
		return invoiceStatus;
	}
	public void setInvoiceStatus(String invoiceStatus) {
		this.invoiceStatus = invoiceStatus;
	}
	
	public String getBillOption() {
		return billOption;
	}
	public void setBillOption(String billOption) {
		this.billOption = billOption;
	}
	public String getInvoiceMode() {
		return invoiceMode;
	}
	public void setInvoiceMode(String invoiceMode) {
		this.invoiceMode = invoiceMode;
	}
	public String getTotalWeightUOM() {
		return totalWeightUOM;
	}
	public void setTotalWeightUOM(String totalWeightUOM) {
		this.totalWeightUOM = totalWeightUOM;
	}
	/*public void setShpNspCodeValuesTb2(ShpNspCodeValuesTb shpNspCodeValuesTb2) {
		this.shpNspCodeValuesTb2 = shpNspCodeValuesTb2;
	}
	public ShpNspCodeValuesTb getShpNspCodeValuesTb3() {
		return shpNspCodeValuesTb3;
	}
	public void setShpNspCodeValuesTb3(ShpNspCodeValuesTb shpNspCodeValuesTb3) {
		this.shpNspCodeValuesTb3 = shpNspCodeValuesTb3;
	}
	public ShpNspCodeValuesTb getShpNspCodeValuesTb4() {
		return shpNspCodeValuesTb4;
	}
	public void setShpNspCodeValuesTb4(ShpNspCodeValuesTb shpNspCodeValuesTb4) {
		this.shpNspCodeValuesTb4 = shpNspCodeValuesTb4;
	}*/
	/*public List<ShpNspTaskTb> getShpNspTaskTbs() {
		return shpNspTaskTbs;
	}
	public void setShpNspTaskTbs(List<ShpNspTaskTb> shpNspTaskTbs) {
		this.shpNspTaskTbs = shpNspTaskTbs;
	}*/
}
