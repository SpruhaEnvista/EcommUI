package com.envista.msi.api.domain;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the SHP_USER_PROFILE_TB database table.
 * 
 */
@Entity
@Table(name="SHP_USER_PROFILE_TB")
@NamedQuery(name="ShpUserProfileTb.findAll", query="SELECT s FROM ShpUserProfileTb s")
public class ShpUserProfileTb implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="USER_ID", unique=true, precision=22)
	private Long userId;

	@Column(name="ACCESS_DATA_NORM", precision=22)
	private Boolean accessDataNorm;

	@Column(name="ACCESS_FROM", precision=22)
	private Long accessFrom;

	@Column(name="ACCESS_QUEUES", precision=1)
	private Boolean accessQueues;

	@Column(name="ACCESS_RATEINFO", precision=22)
	private Boolean accessRateinfo;

	@Column(precision=22)
	private Boolean buyer;

	@Column(name="CAN_ACCESS_SS_INVOICES", precision=22)
	private Boolean canAccessSsInvoices;

	@Column(name="CAN_ACCESS_UPLIFT", precision=22)
	private Boolean canAccessUplift;

	@Column(precision=1)
	private Boolean cankeyfreightinvoices;

	@Column(name="CARRIER_TASKLIST_ACCESS", precision=1)
	private Boolean carrierTasklistAccess;

	@Temporal(TemporalType.DATE)
	@Column(name="CREATED_DATE")
	private Date createdDate;

	@Column(name="CREATED_USER", length=15)
	private String createdUser;

	@Lob
	@Column(name="DASH_BOARD_SETUP")
	private String dashBoardSetup;

	@Column(precision=1)
	private Boolean dashboardaccess;

	@Column(name="DB_DEF_CUST", length=250)
	private String dbDefCust;

	@Column(name="DEFAULT_FILTER", precision=22)
	private BigDecimal defaultFilter;

	@Column(name="EBILL_AUDIT", precision=1)
	private BigDecimal ebillAudit;

	@Column(length=1000)
	private String email;

	@Column(name="EMAIL_REMINDER", precision=1)
	private Boolean emailReminder;

	@Temporal(TemporalType.DATE)
	@Column(name="END_DATE_ACTIVE")
	private Date endDateActive;

	@Column(name="ENVISTA_EMPLOYEE", precision=1)
	private Boolean envistaEmployee;

	@Column(name="FLAGS_JSON", length=4000)
	private String flagsJson;

	@Column(name="FROM_EMAIL", length=50)
	private String fromEmail;

	@Column(length=30)
	private String fullname;

	@Column(name="IS_ACCOUNTS", precision=22)
	private Boolean isAccounts;

	@Column(name="IS_ACTIVE", precision=22)
	private Boolean isActive;

	@Column(name="IS_AUDIT_MANAGER", precision=22)
	private Boolean isAuditManager;

	@Column(name="IS_AUDITOR", precision=22)
	private Boolean isAuditor;

	@Column(name="IS_CAN_ANALYST", precision=22)
	private Boolean isCanAnalyst;

	@Column(name="IS_CCR_VALIDATOR", precision=1)
	private Boolean isCcrValidator;

	@Column(name="IS_CUST_REASONS_ACCESS", precision=1)
	private Boolean isCustReasonsAccess;

	@Column(name="IS_DATAENTRY", precision=22)
	private Boolean isDataentry;

	@Column(name="IS_EDI_ANALYST", precision=1)
	private Boolean isEdiAnalyst;

	@Column(name="IS_EMAIL_TO_BE_SENT", precision=22)
	private Boolean isEmailToBeSent;

	@Column(name="IS_PRE_AUDITOR", precision=1)
	private Boolean isPreAuditor;

	@Column(name="IS_QA", precision=22)
	private Boolean isQa;

	@Column(name="IS_USER", precision=1)
	private Boolean isUser;

	@Column(name="IS_UTILITY_ACCESS", precision=22)
	private Boolean isUtilityAccess;

	@Column(name="LAND_ON_REPORTS_PAGE", precision=22)
	private Boolean landOnReportsPage;

	@Temporal(TemporalType.DATE)
	@Column(name="LAST_UPDATED_DATE")
	private Date lastUpdatedDate;

	@Column(name="LAST_UPDATED_USER", length=15)
	private String lastUpdatedUser;

	@Column(name="LME_ACCESS", precision=22)
	private Boolean lmeAccess;

	@Column(name="LOGIN_ATTEMPTS", precision=22)
	private Long loginAttempts;

	@Column(precision=22)
	private Boolean msaccess;

	@Column(name="NSP_RATE_ACCESS", precision=22)
	private Boolean nspRateAccess;

	@Column(name="ONLINE_NOTIFY", precision=1)
	private Boolean onlineNotify;

	@JsonIgnore
    @NotNull
	@Column(length=30)
	private String passwd;

	@JsonIgnore
	@Temporal(TemporalType.DATE)
	@Column(name="PASSWORD_EXPIRY_DATE")
	private Date passwordExpiryDate;

	@Column(length=20)
	private String phone;

	@Column(name="PUSH_REPORTS", precision=1)
	private Boolean pushReports;

	@Column(name="REOPEN_INVOICE", precision=1)
	private Boolean reopenInvoice;

	@Column(name="ROUTE_PLANNER", precision=22)
	private Boolean routePlanner;

	@JsonIgnore
	@Column(name="SEND_MAIL_WEEKDAY", precision=22)
	private BigDecimal sendMailWeekday;

	@Column(precision=22)
	private Boolean shipper;

	@Column(name="SHOW_NET_CHARGES", precision=1)
	private Boolean showNetCharges;

	@Temporal(TemporalType.DATE)
	@Column(name="START_DATE_ACTIVE")
	private Date startDateActive;

	@Column(name="TASKLIST_ACCESS", precision=1)
	private Boolean tasklistAccess;

	@Column(name="TYPE_ID", precision=22)
	private Long typeId;

	@Column(name="UNCODED_INVOICE_ACCESS", precision=1)
	private Boolean uncodedInvoiceAccess;

	@Column(name="UPDATE_DELETE", precision=1)
	private Boolean updateDelete;

	@Column(name="UPLOAD_CARRIER_PAYMENT", precision=22)
	private Boolean uploadCarrierPayment;

	@Column(name="USER_NAME", length=15)
	private String userName;

	@Column(length=30)
	private String userinfo;

	public ShpUserProfileTb() {
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Boolean isAccessDataNorm() {
		return accessDataNorm;
	}

	public void setAccessDataNorm(Boolean accessDataNorm) {
		this.accessDataNorm = accessDataNorm;
	}

	public Long getAccessFrom() {
		return accessFrom;
	}

	public void setAccessFrom(Long accessFrom) {
		this.accessFrom = accessFrom;
	}

	public Boolean isAccessQueues() {
		return accessQueues;
	}

	public void setAccessQueues(Boolean accessQueues) {
		this.accessQueues = accessQueues;
	}

	public Boolean isAccessRateinfo() {
		return accessRateinfo;
	}

	public void setAccessRateinfo(Boolean accessRateinfo) {
		this.accessRateinfo = accessRateinfo;
	}

	public Boolean isBuyer() {
		return buyer;
	}

	public void setBuyer(Boolean buyer) {
		this.buyer = buyer;
	}

	public Boolean isCanAccessSsInvoices() {
		return canAccessSsInvoices;
	}

	public void setCanAccessSsInvoices(Boolean canAccessSsInvoices) {
		this.canAccessSsInvoices = canAccessSsInvoices;
	}

	public Boolean isCanAccessUplift() {
		return canAccessUplift;
	}

	public void setCanAccessUplift(Boolean canAccessUplift) {
		this.canAccessUplift = canAccessUplift;
	}

	public Boolean isCankeyfreightinvoices() {
		return cankeyfreightinvoices;
	}

	public void setCankeyfreightinvoices(Boolean cankeyfreightinvoices) {
		this.cankeyfreightinvoices = cankeyfreightinvoices;
	}

	public Boolean isCarrierTasklistAccess() {
		return carrierTasklistAccess;
	}

	public void setCarrierTasklistAccess(Boolean carrierTasklistAccess) {
		this.carrierTasklistAccess = carrierTasklistAccess;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getCreatedUser() {
		return createdUser;
	}

	public void setCreatedUser(String createdUser) {
		this.createdUser = createdUser;
	}

	public String getDashBoardSetup() {
		return dashBoardSetup;
	}

	public void setDashBoardSetup(String dashBoardSetup) {
		this.dashBoardSetup = dashBoardSetup;
	}

	public Boolean isDashboardaccess() {
		return dashboardaccess;
	}

	public void setDashboardaccess(Boolean dashboardaccess) {
		this.dashboardaccess = dashboardaccess;
	}

	public String getDbDefCust() {
		return dbDefCust;
	}

	public void setDbDefCust(String dbDefCust) {
		this.dbDefCust = dbDefCust;
	}

	public BigDecimal getDefaultFilter() {
		return defaultFilter;
	}

	public void setDefaultFilter(BigDecimal defaultFilter) {
		this.defaultFilter = defaultFilter;
	}

	public BigDecimal getEbillAudit() {
		return ebillAudit;
	}

	public void setEbillAudit(BigDecimal ebillAudit) {
		this.ebillAudit = ebillAudit;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Boolean isEmailReminder() {
		return emailReminder;
	}

	public void setEmailReminder(Boolean emailReminder) {
		this.emailReminder = emailReminder;
	}

	public Date getEndDateActive() {
		return endDateActive;
	}

	public void setEndDateActive(Date endDateActive) {
		this.endDateActive = endDateActive;
	}

	public Boolean isEnvistaEmployee() {
		return envistaEmployee;
	}

	public void setEnvistaEmployee(Boolean envistaEmployee) {
		this.envistaEmployee = envistaEmployee;
	}

	public String getFlagsJson() {
		return flagsJson;
	}

	public void setFlagsJson(String flagsJson) {
		this.flagsJson = flagsJson;
	}

	public String getFromEmail() {
		return fromEmail;
	}

	public void setFromEmail(String fromEmail) {
		this.fromEmail = fromEmail;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public Boolean isAccounts() {
		return isAccounts;
	}

	public void setAccounts(Boolean isAccounts) {
		this.isAccounts = isAccounts;
	}

	public Boolean isActive() {
		return isActive;
	}

	public void setActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public Boolean isAuditManager() {
		return isAuditManager;
	}

	public void setAuditManager(Boolean isAuditManager) {
		this.isAuditManager = isAuditManager;
	}

	public Boolean isAuditor() {
		return isAuditor;
	}

	public void setAuditor(Boolean isAuditor) {
		this.isAuditor = isAuditor;
	}

	public Boolean isCanAnalyst() {
		return isCanAnalyst;
	}

	public void setCanAnalyst(Boolean isCanAnalyst) {
		this.isCanAnalyst = isCanAnalyst;
	}

	public Boolean isCcrValidator() {
		return isCcrValidator;
	}

	public void setCcrValidator(Boolean isCcrValidator) {
		this.isCcrValidator = isCcrValidator;
	}

	public Boolean isCustReasonsAccess() {
		return isCustReasonsAccess;
	}

	public void setCustReasonsAccess(Boolean isCustReasonsAccess) {
		this.isCustReasonsAccess = isCustReasonsAccess;
	}

	public Boolean isDataentry() {
		return isDataentry;
	}

	public void setDataentry(Boolean isDataentry) {
		this.isDataentry = isDataentry;
	}

	public Boolean isEdiAnalyst() {
		return isEdiAnalyst;
	}

	public void setEdiAnalyst(Boolean isEdiAnalyst) {
		this.isEdiAnalyst = isEdiAnalyst;
	}

	public Boolean isEmailToBeSent() {
		return isEmailToBeSent;
	}

	public void setEmailToBeSent(Boolean isEmailToBeSent) {
		this.isEmailToBeSent = isEmailToBeSent;
	}

	public Boolean isPreAuditor() {
		return isPreAuditor;
	}

	public void setPreAuditor(Boolean isPreAuditor) {
		this.isPreAuditor = isPreAuditor;
	}

	public Boolean isQa() {
		return isQa;
	}

	public void setQa(Boolean isQa) {
		this.isQa = isQa;
	}

	public Boolean isUser() {
		return isUser;
	}

	public void setUser(Boolean isUser) {
		this.isUser = isUser;
	}

	public Boolean isUtilityAccess() {
		return isUtilityAccess;
	}

	public void setUtilityAccess(Boolean isUtilityAccess) {
		this.isUtilityAccess = isUtilityAccess;
	}

	public Boolean isLandOnReportsPage() {
		return landOnReportsPage;
	}

	public void setLandOnReportsPage(Boolean landOnReportsPage) {
		this.landOnReportsPage = landOnReportsPage;
	}

	public Date getLastUpdatedDate() {
		return lastUpdatedDate;
	}

	public void setLastUpdatedDate(Date lastUpdatedDate) {
		this.lastUpdatedDate = lastUpdatedDate;
	}

	public String getLastUpdatedUser() {
		return lastUpdatedUser;
	}

	public void setLastUpdatedUser(String lastUpdatedUser) {
		this.lastUpdatedUser = lastUpdatedUser;
	}

	public Boolean isLmeAccess() {
		return lmeAccess;
	}

	public void setLmeAccess(Boolean lmeAccess) {
		this.lmeAccess = lmeAccess;
	}

	public Long getLoginAttempts() {
		return loginAttempts;
	}

	public void setLoginAttempts(Long loginAttempts) {
		this.loginAttempts = loginAttempts;
	}

	public Boolean isMsaccess() {
		return msaccess;
	}

	public void setMsaccess(Boolean msaccess) {
		this.msaccess = msaccess;
	}

	public Boolean isNspRateAccess() {
		return nspRateAccess;
	}

	public void setNspRateAccess(Boolean nspRateAccess) {
		this.nspRateAccess = nspRateAccess;
	}

	public Boolean isOnlineNotify() {
		return onlineNotify;
	}

	public void setOnlineNotify(Boolean onlineNotify) {
		this.onlineNotify = onlineNotify;
	}

	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	public Date getPasswordExpiryDate() {
		return passwordExpiryDate;
	}

	public void setPasswordExpiryDate(Date passwordExpiryDate) {
		this.passwordExpiryDate = passwordExpiryDate;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Boolean isPushReports() {
		return pushReports;
	}

	public void setPushReports(Boolean pushReports) {
		this.pushReports = pushReports;
	}

	public Boolean isReopenInvoice() {
		return reopenInvoice;
	}

	public void setReopenInvoice(Boolean reopenInvoice) {
		this.reopenInvoice = reopenInvoice;
	}

	public Boolean isRoutePlanner() {
		return routePlanner;
	}

	public void setRoutePlanner(Boolean routePlanner) {
		this.routePlanner = routePlanner;
	}

	public BigDecimal getSendMailWeekday() {
		return sendMailWeekday;
	}

	public void setSendMailWeekday(BigDecimal sendMailWeekday) {
		this.sendMailWeekday = sendMailWeekday;
	}

	public Boolean isShipper() {
		return shipper;
	}

	public void setShipper(Boolean shipper) {
		this.shipper = shipper;
	}

	public Boolean isShowNetCharges() {
		return showNetCharges;
	}

	public void setShowNetCharges(Boolean showNetCharges) {
		this.showNetCharges = showNetCharges;
	}

	public Date getStartDateActive() {
		return startDateActive;
	}

	public void setStartDateActive(Date startDateActive) {
		this.startDateActive = startDateActive;
	}

	public Boolean isTasklistAccess() {
		return tasklistAccess;
	}

	public void setTasklistAccess(Boolean tasklistAccess) {
		this.tasklistAccess = tasklistAccess;
	}

	public Long getTypeId() {
		return typeId;
	}

	public void setTypeId(Long typeId) {
		this.typeId = typeId;
	}

	public Boolean isUncodedInvoiceAccess() {
		return uncodedInvoiceAccess;
	}

	public void setUncodedInvoiceAccess(Boolean uncodedInvoiceAccess) {
		this.uncodedInvoiceAccess = uncodedInvoiceAccess;
	}

	public Boolean isUpdateDelete() {
		return updateDelete;
	}

	public void setUpdateDelete(Boolean updateDelete) {
		this.updateDelete = updateDelete;
	}

	public Boolean isUploadCarrierPayment() {
		return uploadCarrierPayment;
	}

	public void setUploadCarrierPayment(Boolean uploadCarrierPayment) {
		this.uploadCarrierPayment = uploadCarrierPayment;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserinfo() {
		return userinfo;
	}

	public void setUserinfo(String userinfo) {
		this.userinfo = userinfo;
	}
}