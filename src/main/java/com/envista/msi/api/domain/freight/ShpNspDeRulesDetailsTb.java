package com.envista.msi.api.domain.freight;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the SHP_NSP_DE_RULES_DETAILS_TB database table.
 * 
 */
@Entity
@Table(name="SHP_NSP_DE_RULES_DETAILS_TB")
@NamedQuery(name="ShpNspDeRulesDetailsTb.findAll", query="SELECT s FROM ShpNspDeRulesDetailsTb s")
public class ShpNspDeRulesDetailsTb implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="DE_RULES_DETAILS_ID", unique=true, precision=22)
	private long deRulesDetailsId;

	@Column(name="COL_ID", precision=22)
	private BigDecimal colId;

	@Column(name="DE_RULES_ID", precision=22)
	private BigDecimal deRulesId;

	@Column(name="IS_MANDATE_RULE", precision=1)
	private BigDecimal isMandateRule;

	public ShpNspDeRulesDetailsTb() {
	}

	public long getDeRulesDetailsId() {
		return this.deRulesDetailsId;
	}

	public void setDeRulesDetailsId(long deRulesDetailsId) {
		this.deRulesDetailsId = deRulesDetailsId;
	}

	public BigDecimal getColId() {
		return this.colId;
	}

	public void setColId(BigDecimal colId) {
		this.colId = colId;
	}

	public BigDecimal getDeRulesId() {
		return this.deRulesId;
	}

	public void setDeRulesId(BigDecimal deRulesId) {
		this.deRulesId = deRulesId;
	}

	public BigDecimal getIsMandateRule() {
		return this.isMandateRule;
	}

	public void setIsMandateRule(BigDecimal isMandateRule) {
		this.isMandateRule = isMandateRule;
	}

}