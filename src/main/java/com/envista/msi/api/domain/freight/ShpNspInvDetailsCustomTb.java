package com.envista.msi.api.domain.freight;

import java.io.Serializable;
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
 * The persistent class for the SHP_NSP_INV_DETAILS_CUSTOM_TB database table.
 * 
 */
@Entity
@Table(name = "SHP_NSP_INV_DETAILS_CUSTOM_TB")
@NamedQuery(name = "ShpNspInvDetailsCustomTb.findAll", query = "SELECT s FROM ShpNspInvDetailsCustomTb s")
public class ShpNspInvDetailsCustomTb implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "NSP_INV_DETAILS_CUSTOM_ID", unique = true, precision = 22)
	private long nspInvDetailsCustomId;

	@Column(length = 50)
	private String category;

	@Temporal(TemporalType.DATE)
	@Column(name = "CREATE_DATE")
	private Date createDate;

	@Column(name = "CREATE_USER", length = 100)
	private String createUser;

	@Column(name = "CUSTOM_DEFINED_1", length = 1000)
	private String customDefined1;

	@Column(name = "CUSTOM_DEFINED_10", length = 750)
	private String customDefined10;

	@Column(name = "CUSTOM_DEFINED_11", length = 100)
	private String customDefined11;

	@Column(name = "CUSTOM_DEFINED_12", length = 100)
	private String customDefined12;

	@Column(name = "CUSTOM_DEFINED_13", length = 100)
	private String customDefined13;

	@Column(name = "CUSTOM_DEFINED_14", length = 100)
	private String customDefined14;

	@Column(name = "CUSTOM_DEFINED_15", length = 100)
	private String customDefined15;

	@Column(name = "CUSTOM_DEFINED_16", length = 100)
	private String customDefined16;

	@Column(name = "CUSTOM_DEFINED_17", length = 100)
	private String customDefined17;

	@Column(name = "CUSTOM_DEFINED_18", length = 100)
	private String customDefined18;

	@Column(name = "CUSTOM_DEFINED_19", length = 100)
	private String customDefined19;

	@Column(name = "CUSTOM_DEFINED_2", length = 1000)
	private String customDefined2;

	@Column(name = "CUSTOM_DEFINED_20", length = 100)
	private String customDefined20;

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

	@Column(name = "CUSTOM_DEFINED_8", length = 750)
	private String customDefined8;

	@Column(name = "CUSTOM_DEFINED_9", length = 750)
	private String customDefined9;

	@Column(name = "INVOICE_ID", precision = 22)
	private BigDecimal invoiceId;

	@Temporal(TemporalType.DATE)
	@Column(name = "LAST_UPDATE_DATE")
	private Date lastUpdateDate;

	@Column(name = "LAST_UPDATE_USER", length = 100)
	private String lastUpdateUser;

	public ShpNspInvDetailsCustomTb() {
	}

	public long getNspInvDetailsCustomId() {
		return this.nspInvDetailsCustomId;
	}

	public void setNspInvDetailsCustomId(long nspInvDetailsCustomId) {
		this.nspInvDetailsCustomId = nspInvDetailsCustomId;
	}

	public String getCategory() {
		return this.category;
	}

	public void setCategory(String category) {
		this.category = category;
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

	public String getCustomDefined1() {
		return this.customDefined1;
	}

	public void setCustomDefined1(String customDefined1) {
		this.customDefined1 = customDefined1;
	}

	public String getCustomDefined10() {
		return this.customDefined10;
	}

	public void setCustomDefined10(String customDefined10) {
		this.customDefined10 = customDefined10;
	}

	public String getCustomDefined11() {
		return this.customDefined11;
	}

	public void setCustomDefined11(String customDefined11) {
		this.customDefined11 = customDefined11;
	}

	public String getCustomDefined12() {
		return this.customDefined12;
	}

	public void setCustomDefined12(String customDefined12) {
		this.customDefined12 = customDefined12;
	}

	public String getCustomDefined13() {
		return this.customDefined13;
	}

	public void setCustomDefined13(String customDefined13) {
		this.customDefined13 = customDefined13;
	}

	public String getCustomDefined14() {
		return this.customDefined14;
	}

	public void setCustomDefined14(String customDefined14) {
		this.customDefined14 = customDefined14;
	}

	public String getCustomDefined15() {
		return this.customDefined15;
	}

	public void setCustomDefined15(String customDefined15) {
		this.customDefined15 = customDefined15;
	}

	public String getCustomDefined16() {
		return this.customDefined16;
	}

	public void setCustomDefined16(String customDefined16) {
		this.customDefined16 = customDefined16;
	}

	public String getCustomDefined17() {
		return this.customDefined17;
	}

	public void setCustomDefined17(String customDefined17) {
		this.customDefined17 = customDefined17;
	}

	public String getCustomDefined18() {
		return this.customDefined18;
	}

	public void setCustomDefined18(String customDefined18) {
		this.customDefined18 = customDefined18;
	}

	public String getCustomDefined19() {
		return this.customDefined19;
	}

	public void setCustomDefined19(String customDefined19) {
		this.customDefined19 = customDefined19;
	}

	public String getCustomDefined2() {
		return this.customDefined2;
	}

	public void setCustomDefined2(String customDefined2) {
		this.customDefined2 = customDefined2;
	}

	public String getCustomDefined20() {
		return this.customDefined20;
	}

	public void setCustomDefined20(String customDefined20) {
		this.customDefined20 = customDefined20;
	}

	public String getCustomDefined3() {
		return this.customDefined3;
	}

	public void setCustomDefined3(String customDefined3) {
		this.customDefined3 = customDefined3;
	}

	public String getCustomDefined4() {
		return this.customDefined4;
	}

	public void setCustomDefined4(String customDefined4) {
		this.customDefined4 = customDefined4;
	}

	public String getCustomDefined5() {
		return this.customDefined5;
	}

	public void setCustomDefined5(String customDefined5) {
		this.customDefined5 = customDefined5;
	}

	public String getCustomDefined6() {
		return this.customDefined6;
	}

	public void setCustomDefined6(String customDefined6) {
		this.customDefined6 = customDefined6;
	}

	public String getCustomDefined7() {
		return this.customDefined7;
	}

	public void setCustomDefined7(String customDefined7) {
		this.customDefined7 = customDefined7;
	}

	public String getCustomDefined8() {
		return this.customDefined8;
	}

	public void setCustomDefined8(String customDefined8) {
		this.customDefined8 = customDefined8;
	}

	public String getCustomDefined9() {
		return this.customDefined9;
	}

	public void setCustomDefined9(String customDefined9) {
		this.customDefined9 = customDefined9;
	}

	public BigDecimal getInvoiceId() {
		return this.invoiceId;
	}

	public void setInvoiceId(BigDecimal invoiceId) {
		this.invoiceId = invoiceId;
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ShpNspInvDetailsCustomTb [nspInvDetailsCustomId=" + nspInvDetailsCustomId + ", "
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
				+ (lastUpdateDate != null ? "lastUpdateDate=" + lastUpdateDate + ", " : "")
				+ (lastUpdateUser != null ? "lastUpdateUser=" + lastUpdateUser : "") + "]";
	}

}