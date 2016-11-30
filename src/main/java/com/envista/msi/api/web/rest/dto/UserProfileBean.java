package com.envista.msi.api.web.rest.dto;

import java.util.List;
import com.envista.msi.api.web.rest.util.pac.GenericBean;
import com.envista.msi.api.web.rest.util.pac.IBean;

/**
 * <p>
 *
 * @author Sreedhar Mallepaddi
 * @Version : 1.0 Sreedhar Mallepaddi 10-Mar-2003 Created
 *          </p>
 *          <p>
 *          This Bean class represent the table User_profile.
 *          </p>
 */
public class UserProfileBean extends GenericBean {

	// /////////////////////////////////////
	// attributes

	private static final long serialVersionUID = -8977143908029350464L;

	/**
	 * <p>
	 * Represents ...
	 * </p>
	 */
	private long userId;

	/**
	 * <p>
	 * Represents ...
	 * </p>
	 */
	private String userName;

	/**
	 * <p>
	 * Represents ...
	 * </p>
	 */
	private String password;

	/**
	 * <p>
	 * Represents ...
	 * </p>
	 */
	private String fullName;

	/**
	 * <p>
	 * Represents ...
	 * </p>
	 */
	private String userInfo;

	/**
	 * <p>
	 * Represents ...
	 * </p>
	 */
	private String email;

	/**
	 * <p>
	 * Represents ...
	 * </p>
	 */
	private java.sql.Date startingDateActive;

	/**
	 * <p>
	 * Represents ...
	 * </p>
	 */
	private java.sql.Date endingDateActive;

	/**
	 * <p>
	 * Represents ...
	 * </p>
	 */
	private String fromEmail;

	/**
	 * <p>
	 * Represents ...
	 * </p>
	 */
	private boolean isActive;

	/**
	 * <p>
	 * Represents ...
	 * </p>
	 */
	private boolean onlineNotify;

	/**
	 * <p>
	 * Represents ...
	 * </p>
	 */
	private boolean updateDelete;

	/**
	 * <p>
	 * Represents ...
	 * </p>
	 */
	private long typeId;

	/**
	 * <p>
	 * Represents ...
	 * </p>
	 */
	private Long loginAttempts;

	/**
	 * <p>
	 * Represents ...
	 * </p>
	 */
	private Long parentUserId;
	/**
	 * <p>
	 * Represents ...
	 * </p>
	 */
	private boolean isUser;
	private boolean isDataEntry;
	private boolean isAuditor;
	private boolean isAccounts;
	private boolean eBillAudit;
	private boolean reopenInvoice;
	private boolean showNetCharges;
	private boolean dashboardAccess;
	private boolean canKeyFreightInvoices;
	private java.sql.Date passwordExpiryDate;
	private boolean isQa;
	private boolean landOnReportsPage;
	private boolean buyer;
	private boolean msAccess;
	private boolean routePlanner;
	private boolean accessRateinfo;
	private boolean accessDataNorm;
	private boolean canAccessUplift;
	private String phone;
	private boolean canAccessSSInvoices; // Speard Sheet Invoices Added by
											// Sarvesh for (MSI-9475) Restrict
											// Access to SS Invoice Loading
	private boolean shipper;
	private boolean canUploadCarrPayment; // Added by Sarvesh for (MSI-10193)
											// Restrict User Access to Upload
											// Carrier Payment Information
	private Long sendMailWeekDay;
	private boolean canSendtoCustomerReasonsList; // Added By Balakrisha
													// MSI-11550 Add Flag to
													// User Profile for 'Send to
													// Customer Reasons List'
	private String defaultCustomer;
	private boolean isUtilityAccess;// 11025 - Create a Utilities menu for
									// hidden URL access and other cleanup
									// resolution tools - added by sandya
	private boolean hideInvoiceDetails;
	private boolean hideDashboardSetupLink;// MSI-13853 Hide Dashboard Setup
											// Link from XFSH user(Surya)
	private boolean viewCustomerAgreements;// MSI-14489 Add Flag to User Profile
											// for 'Can View Customer
											// Agreements' (Surya)
	private String dashBoardSetUp; // Added by Sarvesh for new dash board set up
									// page (MSI-13668).
	private Long defaultFilter; // Added by Sarvesh for MSI-13668
	private boolean isPreAuditor; // MSI-11437 as requested role defined by
									// Vinod
	private boolean isEdiAnalyst; // MSI-8553 : by Vinod
	private boolean isCcrValidator;// Added by santhoshi for MSI-17900

	private boolean closeFedExInvoice;

	public boolean isCloseFedExInvoice() {
		return closeFedExInvoice;
	}

	public void setCloseFedExInvoice(boolean closeFedExInvoice) {
		this.closeFedExInvoice = closeFedExInvoice;
	}

	private boolean spotQuoteAccess;

	public boolean isSpotQuoteAccess() {
		return spotQuoteAccess;
	}

	public void setSpotQuoteAccess(boolean spotQuoteAccess) {
		this.spotQuoteAccess = spotQuoteAccess;
	}

	public boolean isCcrValidator() {
		return isCcrValidator;
	}

	public void setCcrValidator(boolean isCcrValidator) {
		this.isCcrValidator = isCcrValidator;
	}

	public String getDefaultCustomer() {
		return defaultCustomer;
	}

	public void setDefaultCustomer(String defaultCustomer) {
		this.defaultCustomer = defaultCustomer;
	}

	public boolean isCanSendtoCustomerReasonsList() {
		return canSendtoCustomerReasonsList;
	}

	public void setCanSendtoCustomerReasonsList(boolean canSendtoCustomerReasonsList) {
		this.canSendtoCustomerReasonsList = canSendtoCustomerReasonsList;
	}

	public boolean isAccessDataNorm() {
		return accessDataNorm;
	}

	public void setAccessDataNorm(boolean accessDataNorm) {
		this.accessDataNorm = accessDataNorm;
	}

	public boolean isMsAccess() {
		return msAccess;
	}

	public void setMsAccess(boolean msAccess) {
		this.msAccess = msAccess;
	}

	public boolean isLandOnReportsPage() {
		return landOnReportsPage;
	}

	public void setLandOnReportsPage(boolean landOnReportsPage) {
		this.landOnReportsPage = landOnReportsPage;
	}

	public boolean isIsDataEntry() {
		return isDataEntry;
	}

	public void setIsDataEntry(boolean isDataEntry) {
		this.isDataEntry = isDataEntry;
	}

	public boolean isQa() {
		return isQa;
	}

	public void setQa(boolean isQa) {
		this.isQa = isQa;
	}

	public boolean isIsAuditor() {
		return isAuditor;
	}

	public void setIsAuditor(boolean isAuditor) {
		this.isAuditor = isAuditor;
	}

	public boolean isIsAccounts() {
		return isAccounts;
	}

	public void setIsAccounts(boolean isAccounts) {
		this.isAccounts = isAccounts;
	}

	private long accessFrom;
	private boolean envistaEmployee;
	private Long homePageId;
	private String homePageRedirectString;
	private List<IBean> userDashletteChoices;
	private boolean isEmailToBeSent;
	private Long carrierId;
	private boolean uncodedInvoiceAccess;
	private boolean taskListAccess;
	private boolean accessQueues;
	private boolean pushReportsAccess;
	private boolean LMEAccess;
	private boolean nSPRateAccess;
	private boolean cANAnalyst;
	private boolean isAuditManager;
	private boolean sendEmailRemainder;
	private List<IBean> utilityLinks;
	// Added by Ravinder for MSI-11957-Please add Carrier Portal access in the
	// Freight Audit drop down tab in MSI for enVista Users
	private boolean carrierPortalAccess;
	private boolean carrierTaskList;
	private boolean allowCarrsForCustTaskList;
	
	/**
	 * <p>
	 * Represents ...
	 * </p>
	 */
	private String id;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public boolean isAllowCarrsForCustTaskList() {
		return allowCarrsForCustTaskList;
	}

	public void setAllowCarrsForCustTaskList(boolean allowCarrsForCustTaskList) {
		this.allowCarrsForCustTaskList = allowCarrsForCustTaskList;
	}

	public boolean isCarrierTaskList() {
		return carrierTaskList;
	}

	public void setCarrierTaskList(boolean carrierTaskList) {
		this.carrierTaskList = carrierTaskList;
	}

	public boolean isCarrierPortalAccess() {
		return carrierPortalAccess;
	}

	public void setCarrierPortalAccess(boolean carrierPortalAccess) {
		this.carrierPortalAccess = carrierPortalAccess;
	}

	public List<IBean> getUtilityLinks() {
		return utilityLinks;
	}

	public void setUtilityLinks(List<IBean> utilityLinks) {
		this.utilityLinks = utilityLinks;
	}

	public boolean isSendEmailRemainder() {
		return sendEmailRemainder;
	}

	public void setSendEmailRemainder(boolean sendEmailRemainder) {
		this.sendEmailRemainder = sendEmailRemainder;
	}

	public boolean isPushReportsAccess() {
		return pushReportsAccess;
	}

	public boolean isAuditManager() {
		return isAuditManager;
	}

	public void setAuditManager(boolean isAuditManager) {
		this.isAuditManager = isAuditManager;
	}

	public void setPushReportsAccess(boolean pushReportsAccess) {
		this.pushReportsAccess = pushReportsAccess;
	}

	public boolean isAccessQueues() {
		return accessQueues;
	}

	public void setAccessQueues(boolean accessQueues) {
		this.accessQueues = accessQueues;
	}

	public boolean isUncodedInvoiceAccess() {
		return uncodedInvoiceAccess;
	}

	public void setUncodedInvoiceAccess(boolean uncodedInvoiceAccess) {
		this.uncodedInvoiceAccess = uncodedInvoiceAccess;
	}

	public boolean isTaskListAccess() {
		return taskListAccess;
	}

	public void setTaskListAccess(boolean taskListAccess) {
		this.taskListAccess = taskListAccess;
	}

	private boolean customerPortalAccess;

	public boolean isCustomerPortalAccess() {
		return customerPortalAccess;
	}

	public void setCustomerPortalAccess(boolean custPortalAccess) {
		this.customerPortalAccess = custPortalAccess;
	}

	public Long getCarrierId() {
		return carrierId;
	}

	public void setCarrierId(Long carrierId) {
		this.carrierId = carrierId;
	}

	public boolean isEmailToBeSent() {
		return isEmailToBeSent;
	}

	public void setEmailToBeSent(boolean isEmailToBeSent) {
		this.isEmailToBeSent = isEmailToBeSent;
	}

	/**
	 * <p>
	 * Does ...
	 * </p>
	 * <p>
	 *
	 * @return a String with ...
	 *         </p>
	 */

	// /////////////////////////////////////
	// operations

	public String getPassword() {
		return password;
	} // end getPassword

	/**
	 * <p>
	 * Does ...
	 * </p>
	 * <p>
	 *
	 * </p>
	 * <p>
	 *
	 * @param _password
	 *            ...
	 *            </p>
	 */
	public void setPassword(String _password) {
		password = _password;
	} // end setPassword

	/**
	 * <p>
	 * Does ...
	 * </p>
	 * <p>
	 *
	 * @return a String with ...
	 *         </p>
	 */
	public String getEmail() {
		return email;
	} // end getEmail

	/**
	 * <p>
	 * Does ...
	 * </p>
	 * <p>
	 *
	 * </p>
	 * <p>
	 *
	 * @param _email
	 *            ...
	 *            </p>
	 */
	public void setEmail(String _email) {
		email = _email;
	} // end setEmail

	/**
	 * <p>
	 * Does ...
	 * </p>
	 * <p>
	 *
	 * @return a java.util.Date with ...
	 *         </p>
	 */
	public java.sql.Date getStartingDateActive() {
		return startingDateActive;
	} // end getStartingDateActive

	/**
	 * <p>
	 * Does ...
	 * </p>
	 * <p>
	 *
	 * </p>
	 * <p>
	 *
	 * @param _startingDateActive
	 *            ...
	 *            </p>
	 */
	public void setStartingDateActive(java.sql.Date _startingDateActive) {
		startingDateActive = _startingDateActive;
	} // end setStartingDateActive

	/**
	 * <p>
	 * Does ...
	 * </p>
	 * <p>
	 *
	 * @return a java.util.Date with ...
	 *         </p>
	 */
	public java.sql.Date getEndingDateActive() {
		return endingDateActive;
	} // end getEndingDateActive

	/**
	 * <p>
	 * Does ...
	 * </p>
	 * <p>
	 *
	 * </p>
	 * <p>
	 *
	 * @param _endingDateActive
	 *            ...
	 *            </p>
	 */
	public void setEndingDateActive(java.sql.Date _endingDateActive) {
		endingDateActive = _endingDateActive;
	} // end setEndingDateActive

	/**
	 * <p>
	 * Does ...
	 * </p>
	 * <p>
	 *
	 * @return a String with ...
	 *         </p>
	 */
	public String getFromEmail() {
		return fromEmail;
	} // end getFromEmail

	/**
	 * <p>
	 * Does ...
	 * </p>
	 * <p>
	 *
	 * </p>
	 * <p>
	 *
	 * @param _fromEmail
	 *            ...
	 *            </p>
	 */
	public void setFromEmail(String _fromEmail) {
		fromEmail = _fromEmail;
	} // end setFromEmail

	/**
	 * <p>
	 * Does ...
	 * </p>
	 * <p>
	 *
	 * @return a boolean with ...
	 *         </p>
	 */
	public boolean isIsActive() {
		return isActive;
	} // end getIsActive

	/**
	 * <p>
	 * Does ...
	 * </p>
	 * <p>
	 *
	 * </p>
	 * <p>
	 *
	 * @param _isActive
	 *            ...
	 *            </p>
	 */
	public void setIsActive(boolean _isActive) {
		isActive = _isActive;
	} // end setIsActive

	/**
	 * <p>
	 * Does ...
	 * </p>
	 * <p>
	 *
	 * @return a bolean with ...
	 *         </p>
	 */
	public boolean isOnlineNotify() {
		return onlineNotify;
	} // end getOnlineNotify

	/**
	 * <p>
	 * Does ...
	 * </p>
	 * <p>
	 *
	 * </p>
	 * <p>
	 *
	 * @param _onlineNotify
	 *            ...
	 *            </p>
	 */
	public void setOnlineNotify(boolean _onlineNotify) {
		onlineNotify = _onlineNotify;
	} // end setOnlineNotify

	/**
	 * <p>
	 * Does ...
	 * </p>
	 * <p>
	 *
	 * @return a boolean with ...
	 *         </p>
	 */
	public boolean isUpdateDelete() {
		return updateDelete;
	} // end isUpdateDelete

	/**
	 * <p>
	 * Does ...
	 * </p>
	 * <p>
	 *
	 * </p>
	 * <p>
	 *
	 * @param _updateDelete
	 *            ...
	 *            </p>
	 */
	public void setUpdateDelete(boolean _updateDelete) {
		updateDelete = _updateDelete;
	} // end setUpdateDelete

	/**
	 * <p>
	 * Does ...
	 * </p>
	 * <p>
	 *
	 * @return a int with ...
	 *         </p>
	 */
	public long getTypeId() {
		return typeId;
	} // end getTypeId

	/**
	 * <p>
	 * Does ...
	 * </p>
	 * <p>
	 *
	 * </p>
	 * <p>
	 *
	 * @param _typeId
	 *            ...
	 *            </p>
	 */
	public void setTypeId(long _typeId) {
		typeId = _typeId;
	} // end setTypeId

	/**
	 * <p>
	 * Does ...
	 * </p>
	 * <p>
	 *
	 * @return a String with ...
	 *         </p>
	 */
	public String getUserInfo() {
		return userInfo;
	} // end getUserInfo

	/**
	 * <p>
	 * Does ...
	 * </p>
	 * <p>
	 *
	 * </p>
	 * <p>
	 *
	 * @param _userInfo
	 *            ...
	 *            </p>
	 */
	public void setUserInfo(String _userInfo) {
		userInfo = _userInfo;
	} // end setUserInfo

	/**
	 * <p>
	 * Does ...
	 * </p>
	 * <p>
	 *
	 * @return a String with ...
	 *         </p>
	 */
	public String getUserName() {
		return userName;
	} // end getUserName

	/**
	 * <p>
	 * Does ...
	 * </p>
	 * <p>
	 *
	 * </p>
	 * <p>
	 *
	 * @param _userName
	 *            ...
	 *            </p>
	 */
	public void setUserName(String _userName) {
		userName = _userName;
	} // end setUserName

	/**
	 * <p>
	 * Does ...
	 * </p>
	 * <p>
	 *
	 * @return a int with ...
	 *         </p>
	 */
	public Long getLoginAttempts() {
		return loginAttempts;
	} // end getLoginAttempts

	/**
	 * <p>
	 * Does ...
	 * </p>
	 * <p>
	 *
	 * </p>
	 * <p>
	 *
	 * @param _loginAttempts
	 *            ...
	 *            </p>
	 */
	public void setLoginAttempts(Long _loginAttempts) {
		loginAttempts = _loginAttempts;
	} // end setLoginAttempts

	/**
	 * <p>
	 * Does ...
	 * </p>
	 * <p>
	 *
	 * @return a long with ...
	 *         </p>
	 */
	public long getUserId() {
		return userId;
	} // end getUserId

	/**
	 * <p>
	 * Does ...
	 * </p>
	 * <p>
	 *
	 * @param _userId
	 *            ...
	 *            </p>
	 *            <p>
	 *
	 *            </p>
	 */
	public void setUserId(long _userId) {
		userId = _userId;
	} // end setUserId

	/**
	 * <p>
	 * Does ...
	 * </p>
	 * <p>
	 *
	 * @return a String for a full name
	 *         </p>
	 */
	public String getFullName() {
		return fullName;
	} // end getUserId

	/**
	 * <p>
	 * Does ...
	 * </p>
	 * <p>
	 *
	 * @param _userId
	 *            ...
	 *            </p>
	 *            <p>
	 *
	 *            </p>
	 */
	public void setFullName(String _fullName) {
		fullName = _fullName;
	} // end setUserId

	public Long getParentUserId() {
		return parentUserId;
	}

	public void setParentUserId(Long _parentUserId) {
		parentUserId = _parentUserId;
	}

	public boolean isIsUser() {
		return isUser;
	}

	public void setIsUser(boolean _isUser) {
		isUser = _isUser;
	}

	public boolean isEBillAudit() {
		return eBillAudit;
	}

	public void setEBillAudit(boolean newEBillAudit) {
		eBillAudit = newEBillAudit;
	}

	public boolean isReopenInvoice() {
		return reopenInvoice;
	}

	public void setReopenInvoice(boolean newReopenInvoice) {
		reopenInvoice = newReopenInvoice;
	}

	public boolean isShowNetCharges() {
		return showNetCharges;
	}

	public void setShowNetCharges(boolean newShowNetCharges) {
		showNetCharges = newShowNetCharges;
	}

	public long getAccessFrom() {
		return accessFrom;
	}

	public void setAccessFrom(long newAccessFrom) {
		accessFrom = newAccessFrom;
	}

	public Long getHomePageId() {
		return homePageId;
	}

	public void setHomePageId(Long newHomePageId) {
		homePageId = newHomePageId;
	}

	/**
	 * <p>
	 * Does ...
	 * </p>
	 * <p>
	 *
	 * @return a String with ...
	 *         </p>
	 *
	 *         public String toString() { // your code here return new String(
	 *         "User id = "+ userId + ";\n\r userName =" + userName +
	 *         ";\n\r fullName" + fullName + ";\n\r userInfo = "+ userInfo +
	 *         ";\n\r email = " + email + ";\n\r starting date = " +
	 *         startingDateActive+ ";\n\r ending date = " + endingDateActive +
	 *         ";\n\r from email = " + fromEmail + ";\n\r is active = " +
	 *         isActive + ";\n\r online notify = " + onlineNotify +
	 *         ";\n\r update delete  = " + updateDelete + ";\n\r type id = " +
	 *         typeId + ";\n\r login attempts = " + loginAttempts +
	 *         ";\n\r parent id = " + parentUserId + ";\n\r isUser = " + isUser
	 *         ); } // end toString
	 */

	/**
	 * <p>
	 * Does conversion to XML
	 * </p>
	 * <p>
	 * It is to be implemented later. @
	 * </p>
	 *
	 *
	 * public String convertToXML() { return null; }
	 */
	public boolean isEnvistaEmployee() {
		return envistaEmployee;
	}

	public void setEnvistaEmployee(boolean envistaEmployee) {
		this.envistaEmployee = envistaEmployee;
	}

	public List<IBean> getUserDashletteChoices() {
		return userDashletteChoices;
	}

	public void setUserDashletteChoices(List<IBean> userDashletteChoices) {
		this.userDashletteChoices = userDashletteChoices;
	}

	public String getHomePageRedirectString() {
		return homePageRedirectString;
	}

	public void setHomePageRedirectString(String homePageRedirectString) {
		this.homePageRedirectString = homePageRedirectString;
	}

	public boolean isDashboardAccess() {
		return dashboardAccess;
	}

	public void setDashboardAccess(boolean dashboardAccess) {
		this.dashboardAccess = dashboardAccess;
	}

	public java.sql.Date getPasswordExpiryDate() {
		return passwordExpiryDate;
	}

	public void setPasswordExpiryDate(java.sql.Date passwordExpiryDate) {
		this.passwordExpiryDate = passwordExpiryDate;
	}

	/**
	 * @param buyer
	 *            the buyer to set
	 */
	public void setBuyer(boolean buyer) {
		this.buyer = buyer;
	}

	/**
	 * @return the buyer
	 */
	public boolean isBuyer() {
		return buyer;
	}

	public boolean isRoutePlanner() {
		return routePlanner;
	}

	public void setRoutePlanner(boolean routePlanner) {
		this.routePlanner = routePlanner;
	}

	public boolean isAccessRateinfo() {
		return accessRateinfo;
	}

	public void setAccessRateinfo(boolean accessRateinfo) {
		this.accessRateinfo = accessRateinfo;
	}

	public boolean isLMEAccess() {
		return LMEAccess;
	}

	public void setLMEAccess(boolean lMEAccess) {
		LMEAccess = lMEAccess;
	}

	public boolean isnSPRateAccess() {
		return nSPRateAccess;
	}

	public void setnSPRateAccess(boolean nSPRateAccess) {
		this.nSPRateAccess = nSPRateAccess;
	}

	public boolean iscANAnalyst() {
		return cANAnalyst;
	}

	public void setcANAnalyst(boolean cANAnalyst) {
		this.cANAnalyst = cANAnalyst;
	}

	public boolean isCanAccessUplift() {
		return canAccessUplift;
	}

	public void setCanAccessUplift(boolean canAccessUplift) {
		this.canAccessUplift = canAccessUplift;
	}

	public boolean isCanKeyFreightInvoices() {
		return canKeyFreightInvoices;
	}

	public void setCanKeyFreightInvoices(boolean canKeyFreightInvoices) {
		this.canKeyFreightInvoices = canKeyFreightInvoices;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public boolean isCanAccessSSInvoices() {
		return canAccessSSInvoices;
	}

	public void setCanAccessSSInvoices(boolean canAccessSSInvoices) {
		this.canAccessSSInvoices = canAccessSSInvoices;
	}

	public boolean isShipper() {
		return shipper;
	}

	public void setShipper(boolean shipper) {
		this.shipper = shipper;
	}

	public boolean isCanUploadCarrPayment() {
		return canUploadCarrPayment;
	}

	public void setCanUploadCarrPayment(boolean canUploadCarrPayment) {
		this.canUploadCarrPayment = canUploadCarrPayment;
	}

	public Long getSendMailWeekDay() {
		return sendMailWeekDay;
	}

	public void setSendMailWeekDay(Long sendMailWeekDay) {
		this.sendMailWeekDay = sendMailWeekDay;
	}

	public boolean isUtilityAccess() {
		return isUtilityAccess;
	}

	public void setUtilityAccess(boolean isUtilityAccess) {
		this.isUtilityAccess = isUtilityAccess;
	}

	public boolean isHideInvoiceDetails() {
		return hideInvoiceDetails;
	}

	public boolean isHideDashboardSetupLink() {
		return hideDashboardSetupLink;
	}

	public void setHideDashboardSetupLink(boolean hideDashboardSetupLink) {
		this.hideDashboardSetupLink = hideDashboardSetupLink;
	}

	public void setHideInvoiceDetails(boolean hideInvoiceDetails) {
		this.hideInvoiceDetails = hideInvoiceDetails;
	}

	public boolean isViewCustomerAgreements() {
		return viewCustomerAgreements;
	}

	public void setViewCustomerAgreements(boolean viewCustomerAgreements) {
		this.viewCustomerAgreements = viewCustomerAgreements;
	}

	public String getDashBoardSetUp() {
		return dashBoardSetUp;
	}

	public void setDashBoardSetUp(String dashBoardSetUp) {
		this.dashBoardSetUp = dashBoardSetUp;
	}

	public Long getDefaultFilter() {
		return defaultFilter;
	}

	public void setDefaultFilter(Long defaultFilter) {
		this.defaultFilter = defaultFilter;
	}

	public boolean isPreAuditor() {
		return isPreAuditor;
	}

	public void setPreAuditor(boolean isPreAuditor) {
		this.isPreAuditor = isPreAuditor;
	}

	public boolean isEdiAnalyst() {
		return isEdiAnalyst;
	}

	public void setEdiAnalyst(boolean isEdiAnalyst) {
		this.isEdiAnalyst = isEdiAnalyst;
	}

} // end UserProfileBean
