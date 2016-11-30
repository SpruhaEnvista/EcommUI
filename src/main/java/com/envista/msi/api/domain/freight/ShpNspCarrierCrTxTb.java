package com.envista.msi.api.domain.freight;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the SHP_NSP_CARRIER_CR_TX_TB database table.
 * 
 */
@Entity
@Table(name="SHP_NSP_CARRIER_CR_TX_TB")
@NamedQuery(name="ShpNspCarrierCrTxTb.findAll", query="SELECT s FROM ShpNspCarrierCrTxTb s")
public class ShpNspCarrierCrTxTb implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="CARRIER_CR_TX_ID", unique=true, precision=22)
	private long carrierCrTxId;

	@Column(name="CARRIER_ID", precision=22)
	private BigDecimal carrierId;

	@Column(name="CONSUMED_AMOUNT", precision=22)
	private BigDecimal consumedAmount;

	@Temporal(TemporalType.DATE)
	@Column(name="CREATE_DATE")
	private Date createDate;

	@Column(name="CREATE_USER", length=250)
	private String createUser;

	@Column(name="CREDIT_ID", precision=22)
	private BigDecimal creditId;

	@Column(name="CREDIT_TYPE", length=10)
	private String creditType;

	@Column(name="CUSTOMER_ID", precision=22)
	private BigDecimal customerId;

	@Column(name="RUN_NO", length=100)
	private String runNo;

	public ShpNspCarrierCrTxTb() {
	}

	public long getCarrierCrTxId() {
		return this.carrierCrTxId;
	}

	public void setCarrierCrTxId(long carrierCrTxId) {
		this.carrierCrTxId = carrierCrTxId;
	}

	public BigDecimal getCarrierId() {
		return this.carrierId;
	}

	public void setCarrierId(BigDecimal carrierId) {
		this.carrierId = carrierId;
	}

	public BigDecimal getConsumedAmount() {
		return this.consumedAmount;
	}

	public void setConsumedAmount(BigDecimal consumedAmount) {
		this.consumedAmount = consumedAmount;
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

	public BigDecimal getCreditId() {
		return this.creditId;
	}

	public void setCreditId(BigDecimal creditId) {
		this.creditId = creditId;
	}

	public String getCreditType() {
		return this.creditType;
	}

	public void setCreditType(String creditType) {
		this.creditType = creditType;
	}

	public BigDecimal getCustomerId() {
		return this.customerId;
	}

	public void setCustomerId(BigDecimal customerId) {
		this.customerId = customerId;
	}

	public String getRunNo() {
		return this.runNo;
	}

	public void setRunNo(String runNo) {
		this.runNo = runNo;
	}

}