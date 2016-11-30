package com.envista.msi.api.domain.freight;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * The persistent class for the SHP_NSP_AWB_DTL_TB database table.
 * 
 */
@Entity
@Table(name = "SHP_NSP_AWB_DTL_TB")
@NamedQuery(name = "ShpNspAwbDtlTb.findAll", query = "SELECT s FROM ShpNspAwbDtlTb s")
public class ShpNspAwbDtlTb implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "NSP_AWB_DTL_ID", unique = true, nullable = false, precision = 22)
	private long nspAwbDtlId;

	@Column(name = "AWB_NUMBER", length = 50)
	private String awbNumber;

	@Column(name = "CONTAINER_NUM", length = 100)
	private String containerNum;

	@Column(name = "COST_CENTER", length = 50)
	private String costCenter;

	@Temporal(TemporalType.DATE)
	@Column(name = "CREATE_DATE")
	private Date createDate;

	@Column(name = "CREATE_USER", length = 250)
	private String createUser;

	@Temporal(TemporalType.DATE)
	@Column(name = "LAST_UPDATE_DATE")
	private Date lastUpdateDate;

	@Column(name = "LAST_UPDATE_USER", length = 250)
	private String lastUpdateUser;

	@Column(precision = 22)
	private BigDecimal qty;

	@Column(name = "QTY_UOM", precision = 22)
	private BigDecimal qtyUom;

	@Column(name = "SERVICE_LEVEL", precision = 22)
	private BigDecimal serviceLevel;

	@Column(name = "TOTAL_PALLETS", precision = 22)
	private BigDecimal totalPallets;

	@Column(precision = 22)
	private BigDecimal weight;

	@Column(name = "WEIGHT_UOM", precision = 22)
	private BigDecimal weightUom;

	// bi-directional many-to-one association to ShpNspInvoiceChargesTb
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "NSP_INVOICE_CHARGES_ID")
	private ShpNspInvoiceChargesTb shpNspInvoiceChargesTb;

	public ShpNspAwbDtlTb() {
	}

	public long getNspAwbDtlId() {
		return this.nspAwbDtlId;
	}

	public void setNspAwbDtlId(long nspAwbDtlId) {
		this.nspAwbDtlId = nspAwbDtlId;
	}

	public String getAwbNumber() {
		return this.awbNumber;
	}

	public void setAwbNumber(String awbNumber) {
		this.awbNumber = awbNumber;
	}

	public String getContainerNum() {
		return this.containerNum;
	}

	public void setContainerNum(String containerNum) {
		this.containerNum = containerNum;
	}

	public String getCostCenter() {
		return this.costCenter;
	}

	public void setCostCenter(String costCenter) {
		this.costCenter = costCenter;
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

	public BigDecimal getQty() {
		return this.qty;
	}

	public void setQty(BigDecimal qty) {
		this.qty = qty;
	}

	public BigDecimal getQtyUom() {
		return this.qtyUom;
	}

	public void setQtyUom(BigDecimal qtyUom) {
		this.qtyUom = qtyUom;
	}

	public BigDecimal getServiceLevel() {
		return this.serviceLevel;
	}

	public void setServiceLevel(BigDecimal serviceLevel) {
		this.serviceLevel = serviceLevel;
	}

	public BigDecimal getTotalPallets() {
		return this.totalPallets;
	}

	public void setTotalPallets(BigDecimal totalPallets) {
		this.totalPallets = totalPallets;
	}

	public BigDecimal getWeight() {
		return this.weight;
	}

	public void setWeight(BigDecimal weight) {
		this.weight = weight;
	}

	public BigDecimal getWeightUom() {
		return this.weightUom;
	}

	public void setWeightUom(BigDecimal weightUom) {
		this.weightUom = weightUom;
	}

	public ShpNspInvoiceChargesTb getShpNspInvoiceChargesTb() {
		return this.shpNspInvoiceChargesTb;
	}

	public void setShpNspInvoiceChargesTb(ShpNspInvoiceChargesTb shpNspInvoiceChargesTb) {
		this.shpNspInvoiceChargesTb = shpNspInvoiceChargesTb;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ShpNspAwbDtlTb [nspAwbDtlId=" + nspAwbDtlId + ", "
				+ (awbNumber != null ? "awbNumber=" + awbNumber + ", " : "")
				+ (containerNum != null ? "containerNum=" + containerNum + ", " : "")
				+ (costCenter != null ? "costCenter=" + costCenter + ", " : "")
				+ (createDate != null ? "createDate=" + createDate + ", " : "")
				+ (createUser != null ? "createUser=" + createUser + ", " : "")
				+ (lastUpdateDate != null ? "lastUpdateDate=" + lastUpdateDate + ", " : "")
				+ (lastUpdateUser != null ? "lastUpdateUser=" + lastUpdateUser + ", " : "")
				+ (qty != null ? "qty=" + qty + ", " : "") + (qtyUom != null ? "qtyUom=" + qtyUom + ", " : "")
				+ (serviceLevel != null ? "serviceLevel=" + serviceLevel + ", " : "")
				+ (totalPallets != null ? "totalPallets=" + totalPallets + ", " : "")
				+ (weight != null ? "weight=" + weight + ", " : "")
				+ (weightUom != null ? "weightUom=" + weightUom + ", " : "")
				+ (shpNspInvoiceChargesTb != null ? "shpNspInvoiceChargesTb=" + shpNspInvoiceChargesTb : "") + "]";
	}
}