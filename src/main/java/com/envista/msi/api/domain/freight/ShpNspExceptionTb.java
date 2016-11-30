package com.envista.msi.api.domain.freight;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the SHP_NSP_EXCEPTION_TB database table.
 * 
 */
@Entity
@Table(name="SHP_NSP_EXCEPTION_TB")
@NamedQuery(name="ShpNspExceptionTb.findAll", query="SELECT s FROM ShpNspExceptionTb s")
public class ShpNspExceptionTb implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="NSP_EXCEPTION_ID", unique=true, precision=22)
	private long nspExceptionId;

	@Temporal(TemporalType.DATE)
	@Column(name="CREATE_DATE")
	private Date createDate;

	@Column(name="CREATE_USER", length=200)
	private String createUser;

	@Column(name="\"EXCEPTION\"", length=4000)
	private String exception;

	@Column(name="EXCEPTION_TYPE", length=4000)
	private String exceptionType;

	@Column(name="FILE_NAME", length=500)
	private String fileName;

	public ShpNspExceptionTb() {
	}

	public long getNspExceptionId() {
		return this.nspExceptionId;
	}

	public void setNspExceptionId(long nspExceptionId) {
		this.nspExceptionId = nspExceptionId;
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

	public String getException() {
		return this.exception;
	}

	public void setException(String exception) {
		this.exception = exception;
	}

	public String getExceptionType() {
		return this.exceptionType;
	}

	public void setExceptionType(String exceptionType) {
		this.exceptionType = exceptionType;
	}

	public String getFileName() {
		return this.fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

}