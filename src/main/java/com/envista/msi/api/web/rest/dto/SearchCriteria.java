/**
 * 
 */
package com.envista.msi.api.web.rest.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author SANKER
 *
 */
public class SearchCriteria implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Date checkDate;
	private Long checkAmount;
	private Long customerId;
	private String invoiceStatus;
	private String checkNumber;
	private String  runNumber;
	private String searchInvoiceId;
	private String bolNumber;
	private String invoiceNumber;
	private String invoiceMode;
	private String dateCriteriaId;
	private String invoiceMethod;
	private String poNumber;
	private String proNumber;
	private Boolean includeExceptionInvoices;
	private String receiverZip;
	private String billToZip;
	private String shipperZip;

	public SearchCriteria() {
		super();
	}

	
	/**
	 * @param checkDate
	 * @param checkAmount
	 * @param customerId
	 * @param invoiceStatus
	 * @param checkNumber
	 * @param runNumber
	 * @param searchInvoiceId
	 * @param bolNumber
	 * @param invoiceNumber
	 * @param invoiceMode
	 * @param dateCriteriaId
	 * @param invoiceMethod
	 * @param poNumber
	 * @param proNumber
	 * @param includeExceptionInvoices
	 * @param receiverZip
	 * @param billToZip
	 * @param shipperZip
	 */
	public SearchCriteria(Date checkDate, Long checkAmount, Long customerId, String invoiceStatus,
			String checkNumber, String runNumber, String searchInvoiceId, String bolNumber,
			String invoiceNumber, String invoiceMode, String dateCriteriaId, String invoiceMethod,
			String poNumber, String proNumber, Boolean includeExceptionInvoices, String receiverZip,
			String billToZip, String shipperZip) {
		super();
		this.checkDate = checkDate;
		this.checkAmount = checkAmount;
		this.customerId = customerId;
		this.invoiceStatus = invoiceStatus;
		this.checkNumber = checkNumber;
		this.runNumber = runNumber;
		this.searchInvoiceId = searchInvoiceId;
		this.bolNumber = bolNumber;
		this.invoiceNumber = invoiceNumber;
		this.invoiceMode = invoiceMode;
		this.dateCriteriaId = dateCriteriaId;
		this.invoiceMethod = invoiceMethod;
		this.poNumber = poNumber;
		this.proNumber = proNumber;
		this.includeExceptionInvoices = includeExceptionInvoices;
		this.receiverZip = receiverZip;
		this.billToZip = billToZip;
		this.shipperZip = shipperZip;
	}



	/**
	 * @return the checkDate
	 */
	public Date getCheckDate() {
		return checkDate;
	}

	/**
	 * @param checkDate
	 *            the checkDate to set
	 */
	public void setCheckDate(Date checkDate) {
		this.checkDate = checkDate;
	}

	/**
	 * @return the checkAmount
	 */
	public Long getCheckAmount() {
		return checkAmount;
	}

	/**
	 * @param checkAmount
	 *            the checkAmount to set
	 */
	public void setCheckAmount(Long checkAmount) {
		this.checkAmount = checkAmount;
	}

	/**
	 * @return the customerId
	 */
	public Long getCustomerId() {
		return customerId;
	}

	/**
	 * @param customerId
	 *            the customerId to set
	 */
	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}


	/**
	 * @return the invoiceStatus
	 */
	public String getInvoiceStatus() {
		return invoiceStatus;
	}


	/**
	 * @param invoiceStatus the invoiceStatus to set
	 */
	public void setInvoiceStatus(String invoiceStatus) {
		this.invoiceStatus = invoiceStatus;
	}


	/**
	 * @return the checkNumber
	 */
	public String getCheckNumber() {
		return checkNumber;
	}


	/**
	 * @param checkNumber the checkNumber to set
	 */
	public void setCheckNumber(String checkNumber) {
		this.checkNumber = checkNumber;
	}


	/**
	 * @return the runNumber
	 */
	public String getRunNumber() {
		return runNumber;
	}


	/**
	 * @param runNumber the runNumber to set
	 */
	public void setRunNumber(String runNumber) {
		this.runNumber = runNumber;
	}


	/**
	 * @return the searchInvoiceId
	 */
	public String getSearchInvoiceId() {
		return searchInvoiceId;
	}


	/**
	 * @param searchInvoiceId the searchInvoiceId to set
	 */
	public void setSearchInvoiceId(String searchInvoiceId) {
		this.searchInvoiceId = searchInvoiceId;
	}


	/**
	 * @return the bolNumber
	 */
	public String getBolNumber() {
		return bolNumber;
	}


	/**
	 * @param bolNumber the bolNumber to set
	 */
	public void setBolNumber(String bolNumber) {
		this.bolNumber = bolNumber;
	}


	/**
	 * @return the invoiceNumber
	 */
	public String getInvoiceNumber() {
		return invoiceNumber;
	}


	/**
	 * @param invoiceNumber the invoiceNumber to set
	 */
	public void setInvoiceNumber(String invoiceNumber) {
		this.invoiceNumber = invoiceNumber;
	}


	/**
	 * @return the invoiceMode
	 */
	public String getInvoiceMode() {
		return invoiceMode;
	}


	/**
	 * @param invoiceMode the invoiceMode to set
	 */
	public void setInvoiceMode(String invoiceMode) {
		this.invoiceMode = invoiceMode;
	}


	/**
	 * @return the dateCriteriaId
	 */
	public String getDateCriteriaId() {
		return dateCriteriaId;
	}


	/**
	 * @param dateCriteriaId the dateCriteriaId to set
	 */
	public void setDateCriteriaId(String dateCriteriaId) {
		this.dateCriteriaId = dateCriteriaId;
	}


	/**
	 * @return the invoiceMethod
	 */
	public String getInvoiceMethod() {
		return invoiceMethod;
	}


	/**
	 * @param invoiceMethod the invoiceMethod to set
	 */
	public void setInvoiceMethod(String invoiceMethod) {
		this.invoiceMethod = invoiceMethod;
	}


	/**
	 * @return the poNumber
	 */
	public String getPoNumber() {
		return poNumber;
	}


	/**
	 * @param poNumber the poNumber to set
	 */
	public void setPoNumber(String poNumber) {
		this.poNumber = poNumber;
	}


	/**
	 * @return the proNumber
	 */
	public String getProNumber() {
		return proNumber;
	}


	/**
	 * @param proNumber the proNumber to set
	 */
	public void setProNumber(String proNumber) {
		this.proNumber = proNumber;
	}


	/**
	 * @return the includeExceptionInvoices
	 */
	public Boolean getIncludeExceptionInvoices() {
		return includeExceptionInvoices;
	}


	/**
	 * @param includeExceptionInvoices the includeExceptionInvoices to set
	 */
	public void setIncludeExceptionInvoices(Boolean includeExceptionInvoices) {
		this.includeExceptionInvoices = includeExceptionInvoices;
	}


	/**
	 * @return the receiverZip
	 */
	public String getReceiverZip() {
		return receiverZip;
	}


	/**
	 * @param receiverZip the receiverZip to set
	 */
	public void setReceiverZip(String receiverZip) {
		this.receiverZip = receiverZip;
	}


	/**
	 * @return the billToZip
	 */
	public String getBillToZip() {
		return billToZip;
	}


	/**
	 * @param billToZip the billToZip to set
	 */
	public void setBillToZip(String billToZip) {
		this.billToZip = billToZip;
	}


	/**
	 * @return the shipperZip
	 */
	public String getShipperZip() {
		return shipperZip;
	}


	/**
	 * @param shipperZip the shipperZip to set
	 */
	public void setShipperZip(String shipperZip) {
		this.shipperZip = shipperZip;
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "SearchCriteria [" + (checkDate != null ? "checkDate=" + checkDate + ", " : "")
				+ (checkAmount != null ? "checkAmount=" + checkAmount + ", " : "")
				+ (customerId != null ? "customerId=" + customerId + ", " : "")
				+ (invoiceStatus != null ? "invoiceStatus=" + invoiceStatus + ", " : "")
				+ (checkNumber != null ? "checkNumber=" + checkNumber + ", " : "")
				+ (runNumber != null ? "runNumber=" + runNumber + ", " : "")
				+ (searchInvoiceId != null ? "searchInvoiceId=" + searchInvoiceId + ", " : "")
				+ (bolNumber != null ? "bolNumber=" + bolNumber + ", " : "")
				+ (invoiceNumber != null ? "invoiceNumber=" + invoiceNumber + ", " : "")
				+ (invoiceMode != null ? "invoiceMode=" + invoiceMode + ", " : "")
				+ (dateCriteriaId != null ? "dateCriteriaId=" + dateCriteriaId + ", " : "")
				+ (invoiceMethod != null ? "invoiceMethod=" + invoiceMethod + ", " : "")
				+ (poNumber != null ? "poNumber=" + poNumber + ", " : "")
				+ (proNumber != null ? "proNumber=" + proNumber + ", " : "")
				+ (includeExceptionInvoices != null ? "includeExceptionInvoices=" + includeExceptionInvoices + ", "
						: "")
				+ (receiverZip != null ? "receiverZip=" + receiverZip + ", " : "")
				+ (billToZip != null ? "billToZip=" + billToZip + ", " : "")
				+ (shipperZip != null ? "shipperZip=" + shipperZip : "") + "]";
	}
	
}
