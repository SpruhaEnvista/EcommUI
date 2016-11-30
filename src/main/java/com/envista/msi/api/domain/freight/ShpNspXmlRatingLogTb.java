package com.envista.msi.api.domain.freight;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the SHP_NSP_XML_RATING_LOG_TB database table.
 * 
 */
@Entity
@Table(name="SHP_NSP_XML_RATING_LOG_TB")
@NamedQuery(name="ShpNspXmlRatingLogTb.findAll", query="SELECT s FROM ShpNspXmlRatingLogTb s")
public class ShpNspXmlRatingLogTb implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="NSP_XML_RATING_LOG_ID", unique=true, precision=22)
	private long nspXmlRatingLogId;

	@Temporal(TemporalType.DATE)
	@Column(name="CREATE_DATE")
	private Date createDate;

	@Column(name="CREATE_USER", length=50)
	private String createUser;

	@Column(name="NSP_INVOICE_DETAILS_ID", precision=22)
	private BigDecimal nspInvoiceDetailsId;

	@Column(name="PO_LOAD_HDR_ID", precision=22)
	private BigDecimal poLoadHdrId;

	@Column(name="RATE_REQ_XML", length=4000)
	private String rateReqXml;

	@Column(name="RATE_REQ_XML_1", length=4000)
	private String rateReqXml1;

	@Lob
	@Column(name="RATE_REQ_XML_CLOB")
	private String rateReqXmlClob;

	@Column(name="RATE_RES_XML", length=4000)
	private String rateResXml;

	@Column(name="RATE_RES_XML_1", length=4000)
	private String rateResXml1;

	@Column(name="RATE_RES_XML_2", length=4000)
	private String rateResXml2;

	@Lob
	@Column(name="RATE_RES_XML_CLOB")
	private String rateResXmlClob;

	public ShpNspXmlRatingLogTb() {
	}

	public long getNspXmlRatingLogId() {
		return this.nspXmlRatingLogId;
	}

	public void setNspXmlRatingLogId(long nspXmlRatingLogId) {
		this.nspXmlRatingLogId = nspXmlRatingLogId;
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

	public BigDecimal getNspInvoiceDetailsId() {
		return this.nspInvoiceDetailsId;
	}

	public void setNspInvoiceDetailsId(BigDecimal nspInvoiceDetailsId) {
		this.nspInvoiceDetailsId = nspInvoiceDetailsId;
	}

	public BigDecimal getPoLoadHdrId() {
		return this.poLoadHdrId;
	}

	public void setPoLoadHdrId(BigDecimal poLoadHdrId) {
		this.poLoadHdrId = poLoadHdrId;
	}

	public String getRateReqXml() {
		return this.rateReqXml;
	}

	public void setRateReqXml(String rateReqXml) {
		this.rateReqXml = rateReqXml;
	}

	public String getRateReqXml1() {
		return this.rateReqXml1;
	}

	public void setRateReqXml1(String rateReqXml1) {
		this.rateReqXml1 = rateReqXml1;
	}

	public String getRateReqXmlClob() {
		return this.rateReqXmlClob;
	}

	public void setRateReqXmlClob(String rateReqXmlClob) {
		this.rateReqXmlClob = rateReqXmlClob;
	}

	public String getRateResXml() {
		return this.rateResXml;
	}

	public void setRateResXml(String rateResXml) {
		this.rateResXml = rateResXml;
	}

	public String getRateResXml1() {
		return this.rateResXml1;
	}

	public void setRateResXml1(String rateResXml1) {
		this.rateResXml1 = rateResXml1;
	}

	public String getRateResXml2() {
		return this.rateResXml2;
	}

	public void setRateResXml2(String rateResXml2) {
		this.rateResXml2 = rateResXml2;
	}

	public String getRateResXmlClob() {
		return this.rateResXmlClob;
	}

	public void setRateResXmlClob(String rateResXmlClob) {
		this.rateResXmlClob = rateResXmlClob;
	}

}