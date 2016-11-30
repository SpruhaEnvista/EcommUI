package com.envista.msi.api.domain.freight;

import java.io.Serializable;

import javax.persistence.*;

import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the SHP_NSP_INVOICE_HISTORY_TB database table.
 * 
 */
@Entity
@Table(name="SHP_NSP_INVOICE_HISTORY_TB")
@NamedQuery(name="ShpNspInvoiceHistoryTb.findAll", query="SELECT s FROM ShpNspInvoiceHistoryTb s")
public class ShpNspInvoiceHistoryTb implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="NSP_INVOICE_HISTORY_ID", unique=true, precision=22)
	private long nspInvoiceHistoryId;

	@Column(length=200)
	private String comments;

	@Temporal(TemporalType.DATE)
	@Column(name="CREATE_DATE")
	private Date createDate;

	@Column(name="CREDIT_ID", precision=22)
	private BigDecimal creditId;

	@Temporal(TemporalType.DATE)
	@Column(name="FUNDS_RECEIVE_DATE")
	private Date fundsReceiveDate;

	@Column(name="HISTORY_TEXT", length=3000)
	private String historyText;

	@Column(name="INVOICE_ID", precision=22)
	private BigDecimal invoiceId;

	@Column(name="RUN_NUMBER", length=250)
	private String runNumber;

	public ShpNspInvoiceHistoryTb() {
	}

	public long getNspInvoiceHistoryId() {
		return this.nspInvoiceHistoryId;
	}

	public void setNspInvoiceHistoryId(long nspInvoiceHistoryId) {
		this.nspInvoiceHistoryId = nspInvoiceHistoryId;
	}

	public String getComments() {
		return this.comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public Date getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public BigDecimal getCreditId() {
		return this.creditId;
	}

	public void setCreditId(BigDecimal creditId) {
		this.creditId = creditId;
	}

	public Date getFundsReceiveDate() {
		return this.fundsReceiveDate;
	}

	public void setFundsReceiveDate(Date fundsReceiveDate) {
		this.fundsReceiveDate = fundsReceiveDate;
	}

	public String getHistoryText() {
		return this.historyText;
	}

	public void setHistoryText(String historyText) {
		this.historyText = historyText;
	}

	public BigDecimal getInvoiceId() {
		return this.invoiceId;
	}

	public void setInvoiceId(BigDecimal invoiceId) {
		this.invoiceId = invoiceId;
	}

	public String getRunNumber() {
		return this.runNumber;
	}

	public void setRunNumber(String runNumber) {
		this.runNumber = runNumber;
	}
	
	
	@Override
	public String toString() {
		return "ShpNspInvoiceHistoryTb [nspInvoiceHistoryId=" + nspInvoiceHistoryId + ", "
				+ (comments != null ? "comments=" + comments + ", " : "")
				+ (createDate != null ? "createDate=" + createDate + ", " : "")
				+ (creditId != null ? "creditId=" + creditId + ", " : "")
				+ (fundsReceiveDate != null ? "fundsReceiveDate=" + fundsReceiveDate + ", " : "")
				+ (historyText != null ? "historyText=" + historyText + ", " : "")
				+ (invoiceId != null ? "invoiceId=" + invoiceId + ", " : "")
				+ (runNumber != null ? "runNumber=" + runNumber + ", " : "")
				+ "]";
	}

}