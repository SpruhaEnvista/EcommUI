package com.envista.msi.api.domain.freight;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * The persistent class for the SHP_NSP_INV_CHRG_CUSTOM_TB database table.
 * 
 */
@Entity
@Table(name = "SHP_NSP_INV_CHRG_CUSTOM_TB")
@NamedQuery(name = "ShpNspInvChrgCustomTb.findAll", query = "SELECT s FROM ShpNspInvChrgCustomTb s")
public class ShpNspInvChrgCustomTb implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "NSP_INV_CHRG_CUST_ID", unique = true, precision = 22)
	private long nspInvChrgCustId;
	
	@Column(name = "INVOICE_ID", precision = 22)
	private BigDecimal invoiceId;
	
	@Column(name = "NSP_INVOICE_CHARGES_ID", precision = 22)
	private BigDecimal nspInvoiceChargesId;
		
	@Column(name = "CATEGORY" , length = 50)
	private String category;
	
	@Column(name = "CREATE_USER" , length = 50)
	private String createUser;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "CREATE_DATE")
	private Date createDate;	
	
	@Column(name = "LAST_UPDATE_USER" , length = 50)
	private String lastUpdateUser;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "LAST_UPDATE_DATE")
	private Date lastUpdateDate;
	
	@Column(name = "CUSTOM_DEFINED_1", length = 1000)
	private String customDefined1;
	
	@Column(name = "CUSTOM_DEFINED_2", length = 1000)
	private String customDefined2;
	
	@Column(name = "CUSTOM_DEFINED_3", length = 1000)
	private String customDefined3;
	
	@Column(name = "CUSTOM_DEFINED_4", length = 1000)
	private String customDefined4;
	
	@Column(name = "CUSTOM_DEFINED_5", length = 1000)
	private String customDefined5;
	
	@Column(name = "CUSTOM_DEFINED_6", length = 1000)
	private String customDefined6;
	
	@Column(name = "CUSTOM_DEFINED_7", length = 1000)
	private String customDefined7;
	
	@Column(name = "CUSTOM_DEFINED_8", length = 1000)
	private String customDefined8;
	
	@Column(name = "CUSTOM_DEFINED_9", length = 1000)
	private String customDefined9;
	
	@Column(name = "CUSTOM_DEFINED_10", length = 1000)
	private String customDefined10;
	
	@Column(name = "CUSTOM_DEFINED_11", length = 1000)
	private String customDefined11;
	
	@Column(name = "CUSTOM_DEFINED_12", length = 1000)
	private String customDefined12;
	
	@Column(name = "CUSTOM_DEFINED_13", length = 1000)
	private String customDefined13;
	
	@Column(name = "CUSTOM_DEFINED_14", length = 1000)
	private String customDefined14;
	
	@Column(name = "CUSTOM_DEFINED_15", length = 1000)
	private String customDefined15;
	
	@Column(name = "CUSTOM_DEFINED_16", length = 1000)
	private String customDefined16;
	
	@Column(name = "CUSTOM_DEFINED_17", length = 1000)
	private String customDefined17;
	
	@Column(name = "CUSTOM_DEFINED_18", length = 1000)
	private String customDefined18;
	
	@Column(name = "CUSTOM_DEFINED_19", length = 1000)
	private String customDefined19;
	
	@Column(name = "CUSTOM_DEFINED_20", length = 1000)
	private String customDefined20;
	
	public long getNspInvChrgCustId() {
		return nspInvChrgCustId;
	}

	public void setNspInvChrgCustId(long nspInvChrgCustId) {
		this.nspInvChrgCustId = nspInvChrgCustId;
	}

	public BigDecimal getInvoiceId() {
		return invoiceId;
	}

	public void setInvoiceId(BigDecimal invoiceId) {
		this.invoiceId = invoiceId;
	}

	public BigDecimal getNspInvoiceChargesId() {
		return nspInvoiceChargesId;
	}

	public void setNspInvoiceChargesId(BigDecimal nspInvoiceChargesId) {
		this.nspInvoiceChargesId = nspInvoiceChargesId;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getCreateUser() {
		return createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getLastUpdateUser() {
		return lastUpdateUser;
	}

	public void setLastUpdateUser(String lastUpdateUser) {
		this.lastUpdateUser = lastUpdateUser;
	}

	public Date getLastUpdateDate() {
		return lastUpdateDate;
	}

	public void setLastUpdateDate(Date lastUpdateDate) {
		this.lastUpdateDate = lastUpdateDate;
	}

	public String getCustomDefined1() {
		return customDefined1;
	}

	public void setCustomDefined1(String customDefined1) {
		this.customDefined1 = customDefined1;
	}

	public String getCustomDefined2() {
		return customDefined2;
	}

	public void setCustomDefined2(String customDefined2) {
		this.customDefined2 = customDefined2;
	}

	public String getCustomDefined3() {
		return customDefined3;
	}

	public void setCustomDefined3(String customDefined3) {
		this.customDefined3 = customDefined3;
	}

	public String getCustomDefined4() {
		return customDefined4;
	}

	public void setCustomDefined4(String customDefined4) {
		this.customDefined4 = customDefined4;
	}

	public String getCustomDefined5() {
		return customDefined5;
	}

	public void setCustomDefined5(String customDefined5) {
		this.customDefined5 = customDefined5;
	}

	public String getCustomDefined6() {
		return customDefined6;
	}

	public void setCustomDefined6(String customDefined6) {
		this.customDefined6 = customDefined6;
	}

	public String getCustomDefined7() {
		return customDefined7;
	}

	public void setCustomDefined7(String customDefined7) {
		this.customDefined7 = customDefined7;
	}

	public String getCustomDefined8() {
		return customDefined8;
	}

	public void setCustomDefined8(String customDefined8) {
		this.customDefined8 = customDefined8;
	}

	public String getCustomDefined9() {
		return customDefined9;
	}

	public void setCustomDefined9(String customDefined9) {
		this.customDefined9 = customDefined9;
	}

	public String getCustomDefined10() {
		return customDefined10;
	}

	public void setCustomDefined10(String customDefined10) {
		this.customDefined10 = customDefined10;
	}

	public String getCustomDefined11() {
		return customDefined11;
	}

	public void setCustomDefined11(String customDefined11) {
		this.customDefined11 = customDefined11;
	}

	public String getCustomDefined12() {
		return customDefined12;
	}

	public void setCustomDefined12(String customDefined12) {
		this.customDefined12 = customDefined12;
	}

	public String getCustomDefined13() {
		return customDefined13;
	}

	public void setCustomDefined13(String customDefined13) {
		this.customDefined13 = customDefined13;
	}

	public String getCustomDefined14() {
		return customDefined14;
	}

	public void setCustomDefined14(String customDefined14) {
		this.customDefined14 = customDefined14;
	}

	public String getCustomDefined15() {
		return customDefined15;
	}

	public void setCustomDefined15(String customDefined15) {
		this.customDefined15 = customDefined15;
	}

	public String getCustomDefined16() {
		return customDefined16;
	}

	public void setCustomDefined16(String customDefined16) {
		this.customDefined16 = customDefined16;
	}

	public String getCustomDefined17() {
		return customDefined17;
	}

	public void setCustomDefined17(String customDefined17) {
		this.customDefined17 = customDefined17;
	}

	public String getCustomDefined18() {
		return customDefined18;
	}

	public void setCustomDefined18(String customDefined18) {
		this.customDefined18 = customDefined18;
	}

	public String getCustomDefined19() {
		return customDefined19;
	}

	public void setCustomDefined19(String customDefined19) {
		this.customDefined19 = customDefined19;
	}

	public String getCustomDefined20() {
		return customDefined20;
	}

	public void setCustomDefined20(String customDefined20) {
		this.customDefined20 = customDefined20;
	}

	@Override
	public String toString() {
		return "ShpNspInvChrgCustomTb [nspInvChrgCustId=" + nspInvChrgCustId + ", "
				+ (category != null ? "category=" + category + ", " : "")
				+ (createDate != null ? "createDate=" + createDate + ", " : "")
				+ (createUser != null ? "createUser=" + createUser + ", " : "")
				+ (customDefined1 != null ? "customDefined1=" + customDefined1 + ", " : "")
				+ (customDefined10 != null ? "customDefined10=" + customDefined10 + ", " : "")
				+ (customDefined11 != null ? "customDefined11=" + customDefined11 + ", " : "")
				+ (customDefined12 != null ? "customDefined12=" + customDefined12 + ", " : "")
				+ (customDefined13 != null ? "customDefined13=" + customDefined13 + ", " : "")
				+ (customDefined14 != null ? "customDefined14=" + customDefined14 + ", " : "")
				+ (customDefined15 != null ? "customDefined15=" + customDefined15 + ", " : "")
				+ (customDefined16 != null ? "customDefined16=" + customDefined16 + ", " : "")
				+ (customDefined17 != null ? "customDefined17=" + customDefined17 + ", " : "")
				+ (customDefined18 != null ? "customDefined18=" + customDefined18 + ", " : "")
				+ (customDefined19 != null ? "customDefined19=" + customDefined19 + ", " : "")
				+ (customDefined2 != null ? "customDefined2=" + customDefined2 + ", " : "")
				+ (customDefined20 != null ? "customDefined20=" + customDefined20 + ", " : "")
				+ (customDefined3 != null ? "customDefined3=" + customDefined3 + ", " : "")
				+ (customDefined4 != null ? "customDefined4=" + customDefined4 + ", " : "")
				+ (customDefined5 != null ? "customDefined5=" + customDefined5 + ", " : "")
				+ (customDefined6 != null ? "customDefined6=" + customDefined6 + ", " : "")
				+ (customDefined7 != null ? "customDefined7=" + customDefined7 + ", " : "")
				+ (customDefined8 != null ? "customDefined8=" + customDefined8 + ", " : "")
				+ (customDefined9 != null ? "customDefined9=" + customDefined9 + ", " : "")
				+ (invoiceId != null ? "invoiceId=" + invoiceId + ", " : "")
				+ (nspInvoiceChargesId != null ? "nspInvoiceChargesId="+nspInvoiceChargesId+", ": "")
				+ (lastUpdateDate != null ? "lastUpdateDate=" + lastUpdateDate + ", " : "")
				+ (lastUpdateUser != null ? "lastUpdateUser=" + lastUpdateUser : "") + "]";
	}
}