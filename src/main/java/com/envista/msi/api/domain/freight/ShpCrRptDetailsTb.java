package com.envista.msi.api.domain.freight;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the SHP_CR_RPT_DETAILS_TB database table.
 * 
 */
@Entity
@Table(name="SHP_CR_RPT_DETAILS_TB")
@NamedQuery(name="ShpCrRptDetailsTb.findAll", query="SELECT s FROM ShpCrRptDetailsTb s")
public class ShpCrRptDetailsTb implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="RPT_DETAILS_ID", unique=true, precision=22)
	private long rptDetailsId;

	@Column(name="AGGREGATE_TYPE", length=15)
	private String aggregateType;

	@Column(name="CARRIER_ID", length=50)
	private String carrierId;

	@Column(name="COLUMN_DATA_TYPE", length=10)
	private String columnDataType;

	@Column(name="COLUMN_NAME", length=30)
	private String columnName;

	@Column(length=100)
	private String format;

	@Column(name="IS_DEFAULT_EXCLUDED", precision=22)
	private BigDecimal isDefaultExcluded;

	@Column(name="IS_EXCLUDABLE", precision=22)
	private BigDecimal isExcludable;

	@Column(name="IS_INNER", precision=22)
	private BigDecimal isInner;

	@Column(name="IS_SELECT_CRITERIA", precision=22)
	private BigDecimal isSelectCriteria;

	@Column(name="IS_SORTABLE", precision=22)
	private BigDecimal isSortable;

	@Column(length=70)
	private String query;

	@Column(name="SELECT_CLAUSE", length=350)
	private String selectClause;

	@Column(name="\"SEQUENCE\"", length=20)
	private String sequence;

	@Column(name="SHOW_WHEN_NO_INCL_COLMNS", precision=22)
	private BigDecimal showWhenNoInclColmns;

	@Column(name="SPECIAL_TYPE_OPTIONS", length=1000)
	private String specialTypeOptions;

	//bi-directional many-to-one association to ShpCrRptTb
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="RPT_ID")
	private ShpCrRptTb shpCrRptTb;

	public ShpCrRptDetailsTb() {
	}

	public long getRptDetailsId() {
		return this.rptDetailsId;
	}

	public void setRptDetailsId(long rptDetailsId) {
		this.rptDetailsId = rptDetailsId;
	}

	public String getAggregateType() {
		return this.aggregateType;
	}

	public void setAggregateType(String aggregateType) {
		this.aggregateType = aggregateType;
	}

	public String getCarrierId() {
		return this.carrierId;
	}

	public void setCarrierId(String carrierId) {
		this.carrierId = carrierId;
	}

	public String getColumnDataType() {
		return this.columnDataType;
	}

	public void setColumnDataType(String columnDataType) {
		this.columnDataType = columnDataType;
	}

	public String getColumnName() {
		return this.columnName;
	}

	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}

	public String getFormat() {
		return this.format;
	}

	public void setFormat(String format) {
		this.format = format;
	}

	public BigDecimal getIsDefaultExcluded() {
		return this.isDefaultExcluded;
	}

	public void setIsDefaultExcluded(BigDecimal isDefaultExcluded) {
		this.isDefaultExcluded = isDefaultExcluded;
	}

	public BigDecimal getIsExcludable() {
		return this.isExcludable;
	}

	public void setIsExcludable(BigDecimal isExcludable) {
		this.isExcludable = isExcludable;
	}

	public BigDecimal getIsInner() {
		return this.isInner;
	}

	public void setIsInner(BigDecimal isInner) {
		this.isInner = isInner;
	}

	public BigDecimal getIsSelectCriteria() {
		return this.isSelectCriteria;
	}

	public void setIsSelectCriteria(BigDecimal isSelectCriteria) {
		this.isSelectCriteria = isSelectCriteria;
	}

	public BigDecimal getIsSortable() {
		return this.isSortable;
	}

	public void setIsSortable(BigDecimal isSortable) {
		this.isSortable = isSortable;
	}

	public String getQuery() {
		return this.query;
	}

	public void setQuery(String query) {
		this.query = query;
	}

	public String getSelectClause() {
		return this.selectClause;
	}

	public void setSelectClause(String selectClause) {
		this.selectClause = selectClause;
	}

	public String getSequence() {
		return this.sequence;
	}

	public void setSequence(String sequence) {
		this.sequence = sequence;
	}

	public BigDecimal getShowWhenNoInclColmns() {
		return this.showWhenNoInclColmns;
	}

	public void setShowWhenNoInclColmns(BigDecimal showWhenNoInclColmns) {
		this.showWhenNoInclColmns = showWhenNoInclColmns;
	}

	public String getSpecialTypeOptions() {
		return this.specialTypeOptions;
	}

	public void setSpecialTypeOptions(String specialTypeOptions) {
		this.specialTypeOptions = specialTypeOptions;
	}

	public ShpCrRptTb getShpCrRptTb() {
		return this.shpCrRptTb;
	}

	public void setShpCrRptTb(ShpCrRptTb shpCrRptTb) {
		this.shpCrRptTb = shpCrRptTb;
	}

}