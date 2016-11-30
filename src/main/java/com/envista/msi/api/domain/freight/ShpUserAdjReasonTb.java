package com.envista.msi.api.domain.freight;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the SHP_USER_ADJ_REASON_TB database table.
 * 
 */
@Entity
@Table(name="SHP_USER_ADJ_REASON_TB")
@NamedQuery(name="ShpUserAdjReasonTb.findAll", query="SELECT s FROM ShpUserAdjReasonTb s")
public class ShpUserAdjReasonTb implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="USER_ADJ_REASON_ID", unique=true, precision=22)
	private long userAdjReasonId;

	@Column(name="ADJ_REASON_ID", precision=22)
	private BigDecimal adjReasonId;

	@Column(name="INVOICE_FILTER_ID", precision=22)
	private BigDecimal invoiceFilterId;

	@Column(name="RULE_TYPE", length=100)
	private String ruleType;

	@Column(name="USER_ID", precision=22)
	private BigDecimal userId;

	public ShpUserAdjReasonTb() {
	}

	public long getUserAdjReasonId() {
		return this.userAdjReasonId;
	}

	public void setUserAdjReasonId(long userAdjReasonId) {
		this.userAdjReasonId = userAdjReasonId;
	}

	public BigDecimal getAdjReasonId() {
		return this.adjReasonId;
	}

	public void setAdjReasonId(BigDecimal adjReasonId) {
		this.adjReasonId = adjReasonId;
	}

	public BigDecimal getInvoiceFilterId() {
		return this.invoiceFilterId;
	}

	public void setInvoiceFilterId(BigDecimal invoiceFilterId) {
		this.invoiceFilterId = invoiceFilterId;
	}

	public String getRuleType() {
		return this.ruleType;
	}

	public void setRuleType(String ruleType) {
		this.ruleType = ruleType;
	}

	public BigDecimal getUserId() {
		return this.userId;
	}

	public void setUserId(BigDecimal userId) {
		this.userId = userId;
	}

}