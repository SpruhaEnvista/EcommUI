/**
 * 
 */
package com.envista.msi.api.web.rest.util;

/**
 * @author SANKER
 *
 */
public final class WebConstants {

	/**
	 * Session attribute
	 */
	public static final String USER_IN_SESSION = "USER_IN_SESSION";
	public static final String USER_IN_SESSION_OTHERWISE = "USER_IN_SESSION_OTHERWISE";
		
	/**
	 * Prevents instantiation
	 */
	private WebConstants() {
	}
	
	public static class InvoiceLookup {
		/*** Invoice Lookup ***/
		public static final String INVOICE_LOOKUP_DATE_CRITERIA = "INVOICE_LOOKUP_DATE_CRITERIA";
		public static final String CARRIER_MODES = "Carrier Modes";
		public static final String SERVICE_LEVEL = "Service Level";
		public static final String FREIGHT_INVOICE_TYPE = "Freight Invoice Type";
		public static final String INVOICE_STATUS_REPORT = "Invoice Status Report";
	}

}
