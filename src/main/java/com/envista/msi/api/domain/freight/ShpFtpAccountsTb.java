package com.envista.msi.api.domain.freight;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the SHP_FTP_ACCOUNTS_TB database table.
 * 
 */
@Entity
@Table(name="SHP_FTP_ACCOUNTS_TB")
@NamedQuery(name="ShpFtpAccountsTb.findAll", query="SELECT s FROM ShpFtpAccountsTb s")
public class ShpFtpAccountsTb implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="FTP_ACCOUNTS_ID", unique=true, precision=22)
	private long ftpAccountsId;

	@Temporal(TemporalType.DATE)
	@Column(name="CREATE_DATE")
	private Date createDate;

	@Column(name="CREATE_USER", length=15)
	private String createUser;

	@Column(name="FTP_ACCOUNT_NAME", length=30)
	private String ftpAccountName;

	@Column(name="FTP_SERVER", length=30)
	private String ftpServer;

	@Column(name="INITIAL_DIRECTORY", length=50)
	private String initialDirectory;

	@Column(name="IS_ACTIVE", precision=22)
	private BigDecimal isActive;

	@Temporal(TemporalType.DATE)
	@Column(name="LAST_UPDATE_DATE")
	private Date lastUpdateDate;

	@Column(name="LAST_UPDATE_USER", length=16)
	private String lastUpdateUser;

	@Column(length=20)
	private String login;

	@Column(length=20)
	private String password;

	@Column(precision=22)
	private BigDecimal port;

	@Column(name="SECURITY_KEY", length=10)
	private String securityKey;

	@Column(name="SECURITY_TYPE", length=5)
	private String securityType;

	//bi-directional many-to-one association to ShpCustomerProfileTb
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="CUSTOMER_ID")
	private ShpCustomerProfileTb shpCustomerProfileTb;

	public ShpFtpAccountsTb() {
	}

	public long getFtpAccountsId() {
		return this.ftpAccountsId;
	}

	public void setFtpAccountsId(long ftpAccountsId) {
		this.ftpAccountsId = ftpAccountsId;
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

	public String getFtpAccountName() {
		return this.ftpAccountName;
	}

	public void setFtpAccountName(String ftpAccountName) {
		this.ftpAccountName = ftpAccountName;
	}

	public String getFtpServer() {
		return this.ftpServer;
	}

	public void setFtpServer(String ftpServer) {
		this.ftpServer = ftpServer;
	}

	public String getInitialDirectory() {
		return this.initialDirectory;
	}

	public void setInitialDirectory(String initialDirectory) {
		this.initialDirectory = initialDirectory;
	}

	public BigDecimal getIsActive() {
		return this.isActive;
	}

	public void setIsActive(BigDecimal isActive) {
		this.isActive = isActive;
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

	public String getLogin() {
		return this.login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public BigDecimal getPort() {
		return this.port;
	}

	public void setPort(BigDecimal port) {
		this.port = port;
	}

	public String getSecurityKey() {
		return this.securityKey;
	}

	public void setSecurityKey(String securityKey) {
		this.securityKey = securityKey;
	}

	public String getSecurityType() {
		return this.securityType;
	}

	public void setSecurityType(String securityType) {
		this.securityType = securityType;
	}

	public ShpCustomerProfileTb getShpCustomerProfileTb() {
		return this.shpCustomerProfileTb;
	}

	public void setShpCustomerProfileTb(ShpCustomerProfileTb shpCustomerProfileTb) {
		this.shpCustomerProfileTb = shpCustomerProfileTb;
	}

}