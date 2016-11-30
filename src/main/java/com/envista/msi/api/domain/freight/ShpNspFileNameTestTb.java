package com.envista.msi.api.domain.freight;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the SHP_NSP_FILE_NAME_TEST_TB database table.
 * 
 */
@Entity
@Table(name="SHP_NSP_FILE_NAME_TEST_TB")
@NamedQuery(name="ShpNspFileNameTestTb.findAll", query="SELECT s FROM ShpNspFileNameTestTb s")
public class ShpNspFileNameTestTb implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="SHP_NSP_FILE_ID", unique=true, precision=22)
	private long shpNspFileId;

	@Column(name="FILE_NAME", length=500)
	private String fileName;

	@Temporal(TemporalType.DATE)
	@Column(name="LOAD_DATE")
	private Date loadDate;

	@Column(name="RECEIVER_ID", length=100)
	private String receiverId;

	@Column(name="SEND_997", precision=22)
	private BigDecimal send997;

	@Column(name="SENDER_ID", length=100)
	private String senderId;

	@Column(length=50)
	private String status;

	@Column(name="TEST_STATUS", precision=22)
	private BigDecimal testStatus;

	public ShpNspFileNameTestTb() {
	}

	public long getShpNspFileId() {
		return this.shpNspFileId;
	}

	public void setShpNspFileId(long shpNspFileId) {
		this.shpNspFileId = shpNspFileId;
	}

	public String getFileName() {
		return this.fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public Date getLoadDate() {
		return this.loadDate;
	}

	public void setLoadDate(Date loadDate) {
		this.loadDate = loadDate;
	}

	public String getReceiverId() {
		return this.receiverId;
	}

	public void setReceiverId(String receiverId) {
		this.receiverId = receiverId;
	}

	public BigDecimal getSend997() {
		return this.send997;
	}

	public void setSend997(BigDecimal send997) {
		this.send997 = send997;
	}

	public String getSenderId() {
		return this.senderId;
	}

	public void setSenderId(String senderId) {
		this.senderId = senderId;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public BigDecimal getTestStatus() {
		return this.testStatus;
	}

	public void setTestStatus(BigDecimal testStatus) {
		this.testStatus = testStatus;
	}

}