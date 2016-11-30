/**
 * 
 */
package com.envista.msi.api.web.rest.dto;

import java.io.Serializable;
import java.util.List;

import com.envista.msi.api.domain.util.BasicIdNamePair;
import com.envista.msi.api.domain.util.BasicNameValuePair;

/**
 * @author SANKER
 *
 */
public class SearchMetadata implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Login user assigned customers
	 */
	private List<UserAssignedCustomer> customers;
	
	/**
	 * Carriers List for each customers
	 */
	private List<BasicNameValuePair<List<BasicNameValuePair<String>>>> customerCarriers;
	/**
	 * Carrier and SCAC code List
	 */
	private List<BasicNameValuePair<String>> carrierScacs;
	
	private List<String> dateCriterias; //INVOICE_LOOKUP_DATE_CRITERIA;
	private List<String> carrierModes;//Carrier Modes
	
	private List<String> serviceLevels;//Service Level
	private List<String> freightInvoiceTypes;//Freight%20Invoice%20Type
	private List<String> invoiceStatusReports;//Carrier Modes
	/**
	 * @return the userCustomers
	 */
	public List<UserAssignedCustomer> getCustomers() {
		return customers;
	}
	/**
	 * @param userCustomers the userCustomers to set
	 *//*
	public void setCustomers(List<BasicNameValuePair<String>> customers) {
		this.customers = customers;
	}*/
	/**
	 * @param userCustomers the userCustomers to set
	 */
	public void setCustomers(List<UserAssignedCustomer> customers) {
		this.customers = customers;
	}
	/**
	 * @return the customerCarriers
	 */
	public List<BasicNameValuePair<List<BasicNameValuePair<String>>>> getCustomerCarriers() {
		return customerCarriers;
	}
	/**
	 * @param customerCarriers the customerCarriers to set
	 */
	public void setCustomerCarriers(List<BasicNameValuePair<List<BasicNameValuePair<String>>>> customerCarriers) {
		this.customerCarriers = customerCarriers;
	}
	/**
	 * @return the carrierScacs
	 */
	public List<BasicNameValuePair<String>> getCarrierScacs() {
		return carrierScacs;
	}
	/**
	 * @param carrierScacs the carrierScacs to set
	 */
	public void setCarrierScacs(List<BasicNameValuePair<String>> carrierScacs) {
		this.carrierScacs = carrierScacs;
	}
	/**
	 * @return the dateCriterias
	 */
	public List<String> getDateCriterias() {
		return dateCriterias;
	}
	/**
	 * @param dateCriterias the dateCriterias to set
	 */
	public void setDateCriterias(List<String> dateCriterias) {
		this.dateCriterias = dateCriterias;
	}
	/**
	 * @return the carrierModes
	 */
	public List<String> getCarrierModes() {
		return carrierModes;
	}
	/**
	 * @param carrierModes the carrierModes to set
	 */
	public void setCarrierModes(List<String> carrierModes) {
		this.carrierModes = carrierModes;
	}
	/**
	 * @return the serviceLevels
	 */
	public List<String> getServiceLevels() {
		return serviceLevels;
	}
	/**
	 * @param serviceLevels the serviceLevels to set
	 */
	public void setServiceLevels(List<String> serviceLevels) {
		this.serviceLevels = serviceLevels;
	}
	/**
	 * @return the freightInvoiceTypes
	 */
	public List<String> getFreightInvoiceTypes() {
		return freightInvoiceTypes;
	}
	/**
	 * @param freightInvoiceTypes the freightInvoiceTypes to set
	 */
	public void setFreightInvoiceTypes(List<String> freightInvoiceTypes) {
		this.freightInvoiceTypes = freightInvoiceTypes;
	}
	/**
	 * @return the invoiceStatusReports
	 */
	public List<String> getInvoiceStatusReports() {
		return invoiceStatusReports;
	}
	/**
	 * @param invoiceStatusReports the invoiceStatusReports to set
	 */
	public void setInvoiceStatusReports(List<String> invoiceStatusReports) {
		this.invoiceStatusReports = invoiceStatusReports;
	}
	
	

}
