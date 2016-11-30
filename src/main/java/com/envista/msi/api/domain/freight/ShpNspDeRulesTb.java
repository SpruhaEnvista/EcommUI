package com.envista.msi.api.domain.freight;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the SHP_NSP_DE_RULES_TB database table.
 * 
 */
@Entity
@Table(name="SHP_NSP_DE_RULES_TB")
@NamedQuery(name="ShpNspDeRulesTb.findAll", query="SELECT s FROM ShpNspDeRulesTb s")
public class ShpNspDeRulesTb implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="DE_RULES_ID", unique=true, precision=22)
	private long deRulesId;

	@Temporal(TemporalType.DATE)
	@Column(name="CREATE_DATE")
	private Date createDate;

	@Column(name="CREATE_USER", length=30)
	private String createUser;

	@Column(name="CUSTOMER_ID", precision=22)
	private BigDecimal customerId;

	@Temporal(TemporalType.DATE)
	@Column(name="EFFECTIVE_DATE")
	private Date effectiveDate;

	@Column(name="IS_ACTIVE", precision=1)
	private BigDecimal isActive;

	@Column(name="IS_MANDATE", precision=1)
	private BigDecimal isMandate;

	@Temporal(TemporalType.DATE)
	@Column(name="LAST_UPDATE_DATE")
	private Date lastUpdateDate;

	@Column(name="LAST_UPDATE_USER", length=30)
	private String lastUpdateUser;

	@Column(name="NSP_MANDATE_CAR_GRP_ID", precision=22)
	private BigDecimal nspMandateCarGrpId;

	@Column(name="NSP_ODE_CAR_GRP_ID", precision=22)
	private BigDecimal nspOdeCarGrpId;

	@Column(precision=22)
	private BigDecimal priority;

	@Column(name="RULE_NAME", length=50)
	private String ruleName;

	public ShpNspDeRulesTb() {
	}

	public long getDeRulesId() {
		return this.deRulesId;
	}

	public void setDeRulesId(long deRulesId) {
		this.deRulesId = deRulesId;
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

	public Date getEffectiveDate() {
		return this.effectiveDate;
	}

	public void setEffectiveDate(Date effectiveDate) {
		this.effectiveDate = effectiveDate;
	}

	public BigDecimal getIsActive() {
		return this.isActive;
	}

	public void setIsActive(BigDecimal isActive) {
		this.isActive = isActive;
	}

	public BigDecimal getIsMandate() {
		return this.isMandate;
	}

	public void setIsMandate(BigDecimal isMandate) {
		this.isMandate = isMandate;
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

	public BigDecimal getNspMandateCarGrpId() {
		return this.nspMandateCarGrpId;
	}

	public void setNspMandateCarGrpId(BigDecimal nspMandateCarGrpId) {
		this.nspMandateCarGrpId = nspMandateCarGrpId;
	}

	public BigDecimal getNspOdeCarGrpId() {
		return this.nspOdeCarGrpId;
	}

	public void setNspOdeCarGrpId(BigDecimal nspOdeCarGrpId) {
		this.nspOdeCarGrpId = nspOdeCarGrpId;
	}

	public BigDecimal getPriority() {
		return this.priority;
	}

	public void setPriority(BigDecimal priority) {
		this.priority = priority;
	}

	public String getRuleName() {
		return this.ruleName;
	}

	public void setRuleName(String ruleName) {
		this.ruleName = ruleName;
	}

}