package com.envista.msi.api.domain.freight;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the SHP_FTPSERVER_LOG_TB database table.
 * 
 */
@Entity
@Table(name="SHP_FTPSERVER_LOG_TB")
@NamedQuery(name="ShpFtpserverLogTb.findAll", query="SELECT s FROM ShpFtpserverLogTb s")
public class ShpFtpserverLogTb implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="FTPSERVER_LOG_ID", unique=true, precision=22)
	private long ftpserverLogId;

	@Column(length=1000)
	private String comments;

	@Temporal(TemporalType.DATE)
	@Column(name="CREATE_DATE")
	private Date createDate;

	@Column(name="CREATE_USER", length=200)
	private String createUser;

	@Column(name="FILE_FROM", length=100)
	private String fileFrom;

	@Column(name="FILE_SIZE", length=20)
	private String fileSize;

	@Column(name="FILE_STATUS", precision=22)
	private BigDecimal fileStatus;

	@Column(name="FILE_TO", length=100)
	private String fileTo;

	@Column(name="FILE_TYPE", length=20)
	private String fileType;

	@Column(length=4000)
	private String filename;

	@Column(name="FTP_TYPE", length=20)
	private String ftpType;

	@Column(name="IS_TEST", precision=1)
	private BigDecimal isTest;

	@Column(name="ISA_SEQ_ID", precision=22)
	private BigDecimal isaSeqId;

	@Temporal(TemporalType.DATE)
	@Column(name="LAST_UPDATE_DATE")
	private Date lastUpdateDate;

	@Column(name="LAST_UPDATE_USER", length=20)
	private String lastUpdateUser;

	@Column(name="PARENT_FTPSERVER_LOG_ID", precision=22)
	private BigDecimal parentFtpserverLogId;

	@Temporal(TemporalType.DATE)
	@Column(name="POSTED_TIME")
	private Date postedTime;

	@Temporal(TemporalType.DATE)
	@Column(name="RECEIVED_TIME")
	private Date receivedTime;

	@Column(name="REJECTED_INVOICE_COUNT", precision=22)
	private BigDecimal rejectedInvoiceCount;

	@Column(name="SENT_997", precision=1)
	private BigDecimal sent997;

	public ShpFtpserverLogTb() {
	}

	public long getFtpserverLogId() {
		return this.ftpserverLogId;
	}

	public void setFtpserverLogId(long ftpserverLogId) {
		this.ftpserverLogId = ftpserverLogId;
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

	public String getCreateUser() {
		return this.createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	public String getFileFrom() {
		return this.fileFrom;
	}

	public void setFileFrom(String fileFrom) {
		this.fileFrom = fileFrom;
	}

	public String getFileSize() {
		return this.fileSize;
	}

	public void setFileSize(String fileSize) {
		this.fileSize = fileSize;
	}

	public BigDecimal getFileStatus() {
		return this.fileStatus;
	}

	public void setFileStatus(BigDecimal fileStatus) {
		this.fileStatus = fileStatus;
	}

	public String getFileTo() {
		return this.fileTo;
	}

	public void setFileTo(String fileTo) {
		this.fileTo = fileTo;
	}

	public String getFileType() {
		return this.fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public String getFilename() {
		return this.filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public String getFtpType() {
		return this.ftpType;
	}

	public void setFtpType(String ftpType) {
		this.ftpType = ftpType;
	}

	public BigDecimal getIsTest() {
		return this.isTest;
	}

	public void setIsTest(BigDecimal isTest) {
		this.isTest = isTest;
	}

	public BigDecimal getIsaSeqId() {
		return this.isaSeqId;
	}

	public void setIsaSeqId(BigDecimal isaSeqId) {
		this.isaSeqId = isaSeqId;
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

	public BigDecimal getParentFtpserverLogId() {
		return this.parentFtpserverLogId;
	}

	public void setParentFtpserverLogId(BigDecimal parentFtpserverLogId) {
		this.parentFtpserverLogId = parentFtpserverLogId;
	}

	public Date getPostedTime() {
		return this.postedTime;
	}

	public void setPostedTime(Date postedTime) {
		this.postedTime = postedTime;
	}

	public Date getReceivedTime() {
		return this.receivedTime;
	}

	public void setReceivedTime(Date receivedTime) {
		this.receivedTime = receivedTime;
	}

	public BigDecimal getRejectedInvoiceCount() {
		return this.rejectedInvoiceCount;
	}

	public void setRejectedInvoiceCount(BigDecimal rejectedInvoiceCount) {
		this.rejectedInvoiceCount = rejectedInvoiceCount;
	}

	public BigDecimal getSent997() {
		return this.sent997;
	}

	public void setSent997(BigDecimal sent997) {
		this.sent997 = sent997;
	}

}