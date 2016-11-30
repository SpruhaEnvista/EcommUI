/**
 * 
 */
package com.envista.msi.api.web.rest.mapper;

import java.sql.Date;
import java.sql.SQLException;

import org.apache.commons.beanutils.BeanUtils;
import org.json.JSONObject;

import com.envista.msi.api.domain.freight.ShpUserProfileTb;
import com.envista.msi.api.web.rest.dto.UserProfileBean;

/**
 * @author Vadada Prasanna
 *
 */
public class UserMapper {
		
	/**
	 * <p>
	 * Builds userprofile object from ShpUserProfileTb.
	 * Copied existing logic from UserDAO class of old MSI
	 * </p>
	 * <p>
	 * build a bean
	 * </p>
	 * <p>
	 *
	 * @param shpUserProfileTb
	 *            ...
	 *            </p>
	 *            <p>
	 * @return a bean
	 *         </p>
	 * @throws Exception 
	 */
	
	public static UserProfileBean buildBean(ShpUserProfileTb shpUserProfileTb) throws Exception {
		UserProfileBean userbean = new UserProfileBean();
		if (shpUserProfileTb != null) {
			userbean.setUserId(shpUserProfileTb.getUserId());
			userbean.setUserName(shpUserProfileTb.getUserName());
			userbean.setPassword(shpUserProfileTb.getPasswd());
			userbean.setFullName(shpUserProfileTb.getFullname());
			userbean.setUserInfo(shpUserProfileTb.getUserinfo());
			userbean.setEmail(shpUserProfileTb.getEmail());
			userbean.setId(shpUserProfileTb.getUserName());
			userbean.setStartingDateActive((Date) shpUserProfileTb.getStartDateActive());
			userbean.setEndingDateActive((Date) shpUserProfileTb.getEndDateActive());
			userbean.setFromEmail(shpUserProfileTb.getFromEmail());
			userbean.setIsActive(shpUserProfileTb.isActive()==null?false:shpUserProfileTb.isActive());
			userbean.setOnlineNotify(shpUserProfileTb.isOnlineNotify()==null?false:shpUserProfileTb.isOnlineNotify());
			userbean.setUpdateDelete(shpUserProfileTb.isUpdateDelete()==null?false:shpUserProfileTb.isUpdateDelete());
			//userbean.setTypeId(shpUserProfileTb.getTypeId());
			//userbean.setLoginAttempts(shpUserProfileTb.getLoginAttempts());
			/*long parentuserid = shpUserProfileTb.getLong(15);
			if (!shpUserProfileTb.wasNull())
				userbean.setParentUserId(new Long(parentuserid));
			userbean.setIsUser(shpUserProfileTb.isUser());
			userbean.setEBillAudit(shpUserProfileTb.IsEBillAudit());
			userbean.setReopenInvoice(shpUserProfileTb.isReopenInvoice());
			userbean.setShowNetCharges(shpUserProfileTb.isShowNetCharges());
			
			long accessFrom = shpUserProfileTb.getLong(m_listDBColumns.indexOf("access_from") + 1);
			if (!shpUserProfileTb.wasNull())
				userbean.setAccessFrom(accessFrom);
			// customerProfileBean.setBusinessPartnerId((rs.getLong(m_listDBColumns.indexOf("business_partner_id")
			// + 1)));
			long homePageId = shpUserProfileTb.getLong(m_listDBColumns.indexOf("home_page_id") + 1);
			if (!shpUserProfileTb.wasNull())
				userbean.setHomePageId(new Long(homePageId));
			userbean.setEnvistaEmployee(shpUserProfileTb.getBoolean(m_listDBColumns.indexOf("envista_employee") + 1));
			userbean.setEmailToBeSent(shpUserProfileTb.getBoolean(m_listDBColumns.indexOf("is_email_to_be_sent") + 1));
			
			long carrierId = shpUserProfileTb.getLong(m_listDBColumns.indexOf("carrier_id") + 1);
			if (!shpUserProfileTb.wasNull())
				userbean.setCarrierId(carrierId);
			userbean.setIsAuditor(shpUserProfileTb.isAuditor());
			userbean.setIsDataEntry(shpUserProfileTb.isDataentry());
			userbean.setIsAccounts(shpUserProfileTb.isAccounts());
			userbean.setDashboardAccess(shpUserProfileTb.isDashboardaccess());
			userbean.setPasswordExpiryDate((Date) shpUserProfileTb.getPasswordExpiryDate());
			userbean.setQa(shpUserProfileTb.getBoolean(m_listDBColumns.indexOf("is_qa") + 1));
			userbean.setLandOnReportsPage(shpUserProfileTb.getBoolean(m_listDBColumns.indexOf("land_on_reports_page") + 1));
			userbean.setBuyer(shpUserProfileTb.getBoolean(m_listDBColumns.indexOf("buyer") + 1));
			userbean.setUncodedInvoiceAccess(shpUserProfileTb.getBoolean(m_listDBColumns.indexOf("uncoded_invoice_access") + 1));
			userbean.setTaskListAccess(shpUserProfileTb.getBoolean(m_listDBColumns.indexOf("tasklist_access") + 1));
			userbean.setMsAccess(shpUserProfileTb.getBoolean(m_listDBColumns.indexOf("msAccess") + 1));
			userbean.setAccessQueues(shpUserProfileTb.getBoolean(m_listDBColumns.indexOf("access_queues") + 1));
			userbean.setPushReportsAccess(shpUserProfileTb.getBoolean(m_listDBColumns.indexOf("push_reports") + 1));
			userbean.setRoutePlanner(shpUserProfileTb.getBoolean(m_listDBColumns.indexOf("route_planner") + 1));
			userbean.setAccessRateinfo(shpUserProfileTb.getBoolean(m_listDBColumns.indexOf("access_rateinfo") + 1));
			userbean.setLMEAccess(shpUserProfileTb.getBoolean(m_listDBColumns.indexOf("lme_access") + 1));
			userbean.setnSPRateAccess(shpUserProfileTb.getBoolean(m_listDBColumns.indexOf("NSP_RATE_ACCESS") + 1));
			userbean.setcANAnalyst(shpUserProfileTb.getBoolean(m_listDBColumns.indexOf("is_can_analyst") + 1));
			userbean.setAccessDataNorm(shpUserProfileTb.getBoolean(m_listDBColumns.indexOf("access_data_norm") + 1));
			userbean.setCanAccessUplift(shpUserProfileTb.getBoolean(m_listDBColumns.indexOf("can_access_uplift") + 1));
			userbean.setAuditManager(shpUserProfileTb.getBoolean(m_listDBColumns.indexOf("is_audit_manager") + 1));
			userbean.setCanKeyFreightInvoices(shpUserProfileTb.getBoolean(m_listDBColumns.indexOf("cankeyfreightinvoices") + 1));
			userbean.setPhone(shpUserProfileTb.getString(m_listDBColumns.indexOf("phone") + 1));
			userbean.setCanAccessSSInvoices(shpUserProfileTb.getBoolean(m_listDBColumns.indexOf("can_access_ss_invoices") + 1)); // Added by Sarvesh for (MSI-9475) Restrict Access to SS Invoice Loading
			userbean.setSendEmailRemainder(shpUserProfileTb.getBoolean(m_listDBColumns.indexOf("email_reminder") + 1));
			userbean.setShipper(shpUserProfileTb.getBoolean(m_listDBColumns.indexOf("shipper") + 1));
			userbean.setCanUploadCarrPayment(shpUserProfileTb.getBoolean(m_listDBColumns.indexOf("upload_carrier_payment") + 1)); // Added by Sarvesh for (MSI-10193) Restrict User Access to Upload Carrier Payment
			userbean.setCarrierTaskList(shpUserProfileTb.getBoolean(m_listDBColumns.indexOf("carrier_tasklist_access")+1));//Added by Ravinder for MSI-11957																								// Information
			userbean.setSendMailWeekDay(shpUserProfileTb.getLong(m_listDBColumns.indexOf("send_mail_weekday") + 1));
			userbean.setCreateUser(shpUserProfileTb.getString(m_listDBColumns.indexOf("created_user") + 1));
			userbean.setLastUpdateUser(shpUserProfileTb.getString(m_listDBColumns.indexOf("last_updated_user") + 1));
			userbean.setCreateDate(shpUserProfileTb.getTimestamp(m_listDBColumns.indexOf("created_date") + 1));
			userbean.setLastUpdateDate(shpUserProfileTb.getLastUpdatedDate());
			userbean.setCanSendtoCustomerReasonsList(shpUserProfileTb.IsCanSendtoCustomerReasonsList()); // Added by Balakrishna for (MSI-11550) Add Flag to User Profile for 'Send to
																															// Customer Reasons List'
			userbean.setDefaultCustomer(shpUserProfileTb.getString(m_listDBColumns.indexOf("db_def_cust") + 1));
			userbean.setUtilityAccess(shpUserProfileTb.getBoolean(m_listDBColumns.indexOf("is_utility_access") + 1)); // 11025 - Create a Utilities menu for hidden URL access and other cleanup resolution tools - added
																										// by sandya
			*/
			setFlagsJsonToBean(userbean, shpUserProfileTb.getFlagsJson());
		}
		return userbean;
	}

	/**
	 * Method to create json string from the bean. 
	 * 
	 * @param userBean
	 * @return
	 * @throws SQLException
	 */
	/*public String getFlagsJson(UserProfileBean userBean) throws Exception {
		if (m_listFlagsJson != null && m_listFlagsJson.size() > 0) {
			JSONObject jsonObject = new JSONObject();
			boolean anyFlagPresent = false;
			for (String flagName : m_listFlagsJson) {
				boolean flagValue;
				try {
					flagValue = new Boolean(BeanUtils.getProperty(userBean, flagName));
					if (flagValue) {
						jsonObject.put(flagName, flagValue);
						anyFlagPresent = true;
					}
				} catch (Exception e) {
					e.printStackTrace();
					throw new Exception(e.getMessage());
				}

			}
			if(anyFlagPresent) {
				return jsonObject.toString();
			}
		} 
		return null;
	}*/

	/**
	 * Method to set properties in bean from flags_json in DB.
	 * 
	 * @param userBean
	 * @param flagsJson
	 * @throws Exception 
	 * @throws SQLException
	 */
	private static void setFlagsJsonToBean(UserProfileBean userBean, String flagsJson) throws Exception{
		if (flagsJson != null && !flagsJson.isEmpty()) {
			try {
				JSONObject jsonObject = new JSONObject(flagsJson);
				
					if (jsonObject.has(flagsJson)) {
						boolean flagValue = jsonObject.getBoolean(flagsJson);
						BeanUtils.setProperty(userBean, flagsJson, flagValue);
					}
				
			} catch (Exception e) {
				e.printStackTrace();
				throw new Exception();
			}
		}
	}

}
