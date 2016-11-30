package com.envista.msi.api.domain.freight;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the SHP_CUST_COL_DEFN_TB database table.
 * 
 */
@Entity
@Table(name="SHP_CUST_COL_DEFN_TB")
@NamedQuery(name="ShpCustColDefnTb.findAll", query="SELECT s FROM ShpCustColDefnTb s")
public class ShpCustColDefnTb implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="SHP_CUST_COL_DEF_ID", unique=true, precision=22)
	private long shpCustColDefId;

	@Column(name="ACTION_TYPE", precision=22)
	private BigDecimal actionType;

	@Temporal(TemporalType.DATE)
	@Column(name="CREATE_DATE")
	private Date createDate;

	@Column(name="CREATE_USER", length=50)
	private String createUser;

	@Column(name="CUSTOM_FIELD_NAME", length=100)
	private String customFieldName;

	@Column(name="CUSTOMER_ID", precision=22)
	private BigDecimal customerId;

	@Column(name="DATA_FORMAT", length=50)
	private String dataFormat;

	@Column(name="DATA_TYPE", length=30)
	private String dataType;

	@Column(name="DEFAULT_FIELD_NAME", length=50)
	private String defaultFieldName;

	@Column(name="IS_ACTIVE", precision=22)
	private BigDecimal isActive;

	@Column(name="IS_CUST_EDITABLE", precision=22)
	private BigDecimal isCustEditable;

	@Temporal(TemporalType.DATE)
	@Column(name="LAST_UPDATE_DATE")
	private Date lastUpdateDate;

	@Column(name="LAST_UPDATE_USER", length=50)
	private String lastUpdateUser;

	@Column(name="REPORT_FIELD_NAME", length=50)
	private String reportFieldName;

	@Column(name="RPT_ID", length=500)
	private String rptId;

	@Column(name="RPT_TYPE", precision=22)
	private BigDecimal rptType;

	public ShpCustColDefnTb() {
	}

	public long getShpCustColDefId() {
		return this.shpCustColDefId;
	}

	public void setShpCustColDefId(long shpCustColDefId) {
		this.shpCustColDefId = shpCustColDefId;
	}

	public BigDecimal getActionType() {
		return this.actionType;
	}

	public void setActionType(BigDecimal actionType) {
		this.actionType = actionType;
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

	public String getCustomFieldName() {
		return this.customFieldName;
	}

	public void setCustomFieldName(String customFieldName) {
		this.customFieldName = customFieldName;
	}

	public BigDecimal getCustomerId() {
		return this.customerId;
	}

	public void setCustomerId(BigDecimal customerId) {
		this.customerId = customerId;
	}

	public String getDataFormat() {
		return this.dataFormat;
	}

	public void setDataFormat(String dataFormat) {
		this.dataFormat = dataFormat;
	}

	public String getDataType() {
		return this.dataType;
	}

	public void setDataType(String dataType) {
		this.dataType = dataType;
	}

	public String getDefaultFieldName() {
		return this.defaultFieldName;
	}

	public void setDefaultFieldName(String defaultFieldName) {
		this.defaultFieldName = defaultFieldName;
	}

	public BigDecimal getIsActive() {
		return this.isActive;
	}

	public void setIsActive(BigDecimal isActive) {
		this.isActive = isActive;
	}

	public BigDecimal getIsCustEditable() {
		return this.isCustEditable;
	}

	public void setIsCustEditable(BigDecimal isCustEditable) {
		this.isCustEditable = isCustEditable;
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

	public String getReportFieldName() {
		return this.reportFieldName;
	}

	public void setReportFieldName(String reportFieldName) {
		this.reportFieldName = reportFieldName;
	}

	public String getRptId() {
		return this.rptId;
	}

	public void setRptId(String rptId) {
		this.rptId = rptId;
	}

	public BigDecimal getRptType() {
		return this.rptType;
	}

	public void setRptType(BigDecimal rptType) {
		this.rptType = rptType;
	}

}