package com.envista.msi.api.domain.freight;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the SHP_LOOKUP_TB database table.
 * 
 */
@Entity
@Table(name="SHP_LOOKUP_TB")
@NamedQuery(name="ShpLookupTb.findAll", query="SELECT s FROM ShpLookupTb s")
public class ShpLookupTb implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="LOOKUP_ID", unique=true, precision=22)
	private long lookupId;

	@Column(name="COLUMN_NAME", length=1000)
	private String columnName;

	@Temporal(TemporalType.DATE)
	@Column(name="CREATE_DATE")
	private Date createDate;

	@Column(name="CREATE_USER", length=50)
	private String createUser;

	@Column(name="CUSTOM_DEFINED_1", length=1000)
	private String customDefined1;

	@Column(name="CUSTOM_DEFINED_10", length=1000)
	private String customDefined10;

	@Column(name="CUSTOM_DEFINED_2", length=1000)
	private String customDefined2;

	@Column(name="CUSTOM_DEFINED_3", length=1000)
	private String customDefined3;

	@Column(name="CUSTOM_DEFINED_4", length=1000)
	private String customDefined4;

	@Column(name="CUSTOM_DEFINED_5", length=1000)
	private String customDefined5;

	@Column(name="CUSTOM_DEFINED_6", length=1000)
	private String customDefined6;

	@Column(name="CUSTOM_DEFINED_7", length=1000)
	private String customDefined7;

	@Column(name="CUSTOM_DEFINED_8", length=1000)
	private String customDefined8;

	@Column(name="CUSTOM_DEFINED_9", length=1000)
	private String customDefined9;

	@Temporal(TemporalType.DATE)
	@Column(name="LAST_UPDATE_DATE")
	private Date lastUpdateDate;

	@Column(name="LAST_UPDATE_USER", length=50)
	private String lastUpdateUser;

	@Column(name="LOOKUP_CODE", length=1000)
	private String lookupCode;

	@Column(name="LOOKUP_VALUE", length=1000)
	private String lookupValue;

	@Column(name="MODULE_NAME", length=1000)
	private String moduleName;

	public ShpLookupTb() {
	}

	public long getLookupId() {
		return this.lookupId;
	}

	public void setLookupId(long lookupId) {
		this.lookupId = lookupId;
	}

	public String getColumnName() {
		return this.columnName;
	}

	public void setColumnName(String columnName) {
		this.columnName = columnName;
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

	public String getCustomDefined2() {
		return this.customDefined2;
	}

	public void setCustomDefined2(String customDefined2) {
		this.customDefined2 = customDefined2;
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

	public String getLookupCode() {
		return this.lookupCode;
	}

	public void setLookupCode(String lookupCode) {
		this.lookupCode = lookupCode;
	}

	public String getLookupValue() {
		return this.lookupValue;
	}

	public void setLookupValue(String lookupValue) {
		this.lookupValue = lookupValue;
	}

	public String getModuleName() {
		return this.moduleName;
	}

	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}

}