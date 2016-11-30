package com.envista.msi.api.domain.freight;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the SHP_CAN_INV_FILTER_DETAILS_TB database table.
 * 
 */
@Entity
@Table(name="SHP_CAN_INV_FILTER_DETAILS_TB")
@NamedQuery(name="ShpCanInvFilterDetailsTb.findAll", query="SELECT s FROM ShpCanInvFilterDetailsTb s")
public class ShpCanInvFilterDetailsTb implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="INVOICE_FILTER_DETAILS_ID", unique=true, precision=22)
	private long invoiceFilterDetailsId;

	@Column(name="AND_OR_OPERATOR", length=10)
	private String andOrOperator;

	@Column(name="CODE_VALUE_ID", precision=22)
	private BigDecimal codeValueId;

	@Column(name="COLUMN_NAME", length=100)
	private String columnName;

	@Temporal(TemporalType.DATE)
	@Column(name="CREATED_DATE")
	private Date createdDate;

	@Column(name="CREATED_USER", length=100)
	private String createdUser;

	@Column(name="CRIT_OPERATOR", length=100)
	private String critOperator;

	@Column(name="FILTER_ID", precision=22)
	private BigDecimal filterId;

	@Column(name="IS_MATCH", precision=1)
	private BigDecimal isMatch;

	@Column(name="\"VALUE\"", length=100)
	private String value;

	public ShpCanInvFilterDetailsTb() {
	}

	public long getInvoiceFilterDetailsId() {
		return this.invoiceFilterDetailsId;
	}

	public void setInvoiceFilterDetailsId(long invoiceFilterDetailsId) {
		this.invoiceFilterDetailsId = invoiceFilterDetailsId;
	}

	public String getAndOrOperator() {
		return this.andOrOperator;
	}

	public void setAndOrOperator(String andOrOperator) {
		this.andOrOperator = andOrOperator;
	}

	public BigDecimal getCodeValueId() {
		return this.codeValueId;
	}

	public void setCodeValueId(BigDecimal codeValueId) {
		this.codeValueId = codeValueId;
	}

	public String getColumnName() {
		return this.columnName;
	}

	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}

	public Date getCreatedDate() {
		return this.createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getCreatedUser() {
		return this.createdUser;
	}

	public void setCreatedUser(String createdUser) {
		this.createdUser = createdUser;
	}

	public String getCritOperator() {
		return this.critOperator;
	}

	public void setCritOperator(String critOperator) {
		this.critOperator = critOperator;
	}

	public BigDecimal getFilterId() {
		return this.filterId;
	}

	public void setFilterId(BigDecimal filterId) {
		this.filterId = filterId;
	}

	public BigDecimal getIsMatch() {
		return this.isMatch;
	}

	public void setIsMatch(BigDecimal isMatch) {
		this.isMatch = isMatch;
	}

	public String getValue() {
		return this.value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}