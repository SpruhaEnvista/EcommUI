package com.envista.msi.api.web.rest.util.pac;

public class GlobalConstants {
	public static final String LINESEPERATOR = System.getProperty("line.separator");
	public static final String ASC = "asc";
	public static final String DESC = "des";
	public static final int MAX_FILE_ATTACHMENT = 2000000;
	public static final String ARIALFONT_S = "<FONT face=\"Arial\">";
	public static final String FONT_E = "</FONT>";
	public static final String STYLETAG = "<style type=\"text/css\">" + "body { font-size: 12px; font-family: Arial, Helvetica, sans-serif; }"
			+ "td { font-size: 12px; font-family: Arial, Helvetica, sans-serif; }" + "th { font-size: 13px; font-family: Arial, Helvetica, sans-serif; }" + "</style>";

	public static final String ZERO_PREPEND = "0";
	public static final int STRING_NOT_FOUND_INDEX = -1;
	public static final int ZIP_CODE_LEN = 5;

	public static final int HTML_IND = 0;
	public static final int XML_IND = 1;

	public static final int NULL_BEAN_EXCEPTION = -999999;
	public static final String PING_POOL_DSNAME = "pingpool";
	public static final String APACHE_JDBC_DRIVER_URL = "jdbc:apache:commons:dbcp:";
	public static final String APACHE_JDBC_DRIVER = "org.apache.commons.dbcp.PoolingDriver";

	// Comment before release done to get the Db values to test.
	public static final int UPS_CARRIER_ID = 21;
	public static final int FEDEX_CARRIER_ID = 22;
	public static final int ABX_CARRIER_ID = 23;
	public static final String AUDITOR_VALUE = "auditor";
	public static final String AUDIT_MANAGER_VALUE = "auditmanager";

	public final static String EMPTYSPACE = new String();

	public static final String DASHBOARDS_LINE_ITEM_INCLUDED_COLS = "Scac Code,Carrier Name,Invoice Number,PRO #,BOL #,Bill Option,Invoice Date,Ship Date,Delivery Date,Mode,Invoice Method,Invoice GL Code,PO #,Reference 1,Reference 2,Shipper Name,Shipper Address,Shipper City,Shipper State,Shipper Postal Code,Shipper Country,Receiver Name,Receiver Address,Receiver City,Receiver State,Receiver Postal Code,Receiver Country,Line Item #,Qty,UOM,Weight,Description,Freight Class,Rate,Rating Method,Invoice Amount,Total Due Amount,Line GL Code,Line Charges,Approved Line Charges,Adjustment,Adjustment Reason,Audit Charge Code,Service Level ";
	public static final String DASHBOARDS_SHIPMENT_DETAIL_INCLUDED_COLS = "Carrier Name,Invoice #,PRO #,BOL #,Bill Option,Invoice Date,Ship Date,Delivery Date,Invoice Mode,Invoice Method,GL Code,PO #,Reference1,Reference2,Scac Code,Shipper Name,Shipper Address 1,Shipper City,Shipper State,Shipper Postal Code,Shipper Country,Receiver Name,Receiver Address 1,Receiver City,Receiver State,Receiver Postal Code,Receiver Country,Total Weight,Total Charges,Line Haul,Fuel Surcharge,Discount,Accessorials,Adjustments,Total Due Amount,Invoice Status,Check #,Check Date,Check Amount,Adjustment Reason,Shipper Region,Receiver Region,Cwt Weight,Service Level";

}