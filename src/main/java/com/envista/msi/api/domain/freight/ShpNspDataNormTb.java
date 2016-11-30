package com.envista.msi.api.domain.freight;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the SHP_NSP_DATA_NORM_TB database table.
 * 
 */
@Entity
@Table(name="SHP_NSP_DATA_NORM_TB")
@NamedQuery(name="ShpNspDataNormTb.findAll", query="SELECT s FROM ShpNspDataNormTb s")
public class ShpNspDataNormTb implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="NSP_DATA_NORM_ID", unique=true, precision=22)
	private long nspDataNormId;

	@Column(name="COLUMN_NAME_ID", precision=22)
	private BigDecimal columnNameId;

	@Temporal(TemporalType.DATE)
	@Column(name="CREATE_DATE")
	private Date createDate;

	@Column(name="CREATE_USER", length=50)
	private String createUser;

	@Column(name="CUSTOMER_ID", precision=22)
	private BigDecimal customerId;

	@Column(name="IS_ACTIVE", length=1)
	private String isActive;

	@Temporal(TemporalType.DATE)
	@Column(name="LAST_UPDATE_DATE")
	private Date lastUpdateDate;

	@Column(name="LAST_UPDATE_USER", length=50)
	private String lastUpdateUser;

	@Column(name="MATCH_VALUE", length=500)
	private String matchValue;

	@Column(name="NORMALIZED_VALUE", length=500)
	private String normalizedValue;

	public ShpNspDataNormTb() {
	}

	public long getNspDataNormId() {
		return this.nspDataNormId;
	}

	public void setNspDataNormId(long nspDataNormId) {
		this.nspDataNormId = nspDataNormId;
	}

	public BigDecimal getColumnNameId() {
		return this.columnNameId;
	}

	public void setColumnNameId(BigDecimal columnNameId) {
		this.columnNameId = columnNameId;
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

	public BigDecimal getCustomerId() {
		return this.customerId;
	}

	public void setCustomerId(BigDecimal customerId) {
		this.customerId = customerId;
	}

	public String getIsActive() {
		return this.isActive;
	}

	public void setIsActive(String isActive) {
		this.isActive = isActive;
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

	public String getMatchValue() {
		return this.matchValue;
	}

	public void setMatchValue(String matchValue) {
		this.matchValue = matchValue;
	}

	public String getNormalizedValue() {
		return this.normalizedValue;
	}

	public void setNormalizedValue(String normalizedValue) {
		this.normalizedValue = normalizedValue;
	}

}