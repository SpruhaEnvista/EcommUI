package com.envista.msi.api.web.rest.dto;

import java.io.Serializable;

import javax.persistence.*;

import com.envista.msi.api.web.rest.dto.reports.SavedSchedReportDto;
import com.fasterxml.jackson.annotation.JsonIgnore;

@SqlResultSetMappings({
		@SqlResultSetMapping(
				name = "UserProfileTbMapper",
				entities = {
						@EntityResult(
								entityClass = UserProfileDto.class,
								fields = {
										@FieldResult(
												name = "userId",
												column = "user_id"
										),
										@FieldResult(
												name = "email",
												column = "email"
										),
										@FieldResult(
												name = "isAccounts",
												column = "IS_ACCOUNTS"
										),
										@FieldResult(
												name = "fullname",
												column = "fullname"
										),
										@FieldResult(
												name = "userName",
												column = "USER_NAME"
										),
										@FieldResult(
												name = "userinfo",
												column = "userinfo"
										),
										@FieldResult(
												name = "passwd",
												column = "passwd"
										),
										@FieldResult(
												name = "phone",
												column = "phone"
										)
								}
						)}
		)
})
@NamedStoredProcedureQueries({
	@NamedStoredProcedureQuery(name = "UserProfileTb.getUserByProcUserEntity", procedureName = "shp_avtr_user_by_name_proc",
	resultClasses = UserProfileDto.class,
	parameters = {
			@StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, name = "RetUser", type = void.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "userNameInParam", type = String.class)
			 })
	,
	@NamedStoredProcedureQuery(name = "UserProfileTb.getUserByProcUserEntityMapping", procedureName = "shp_avtr_user_by_name_proc", resultSetMappings = "UserProfileTbMapper",
	parameters = {
			@StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, name = "RetUser", type = void.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "userNameInParam", type = String.class)
		  }
		),
	@NamedStoredProcedureQuery(name = "UserProfileTb.validatePassword", procedureName = "shp_avtr_user_validatepwd_proc",
			resultClasses = UserProfileDto.class,
				parameters = {
						@StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, name = "RetUser", type = void.class),
						@StoredProcedureParameter(mode = ParameterMode.IN, name = "inParamPassword", type = String.class),
						@StoredProcedureParameter(mode = ParameterMode.IN, name = "userId", type = Long.class)
				}
		)
})
@Entity
public class UserProfileDto implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "USER_ID", unique = true)
	private Long userId;

	private String email;

	private String fullname;

	@Column(name = "IS_ACCOUNTS")
	private Boolean isAccounts;

	@Column(name = "USER_NAME", length = 15)
	private String userName;

	@Column(length = 30)
	private String userinfo;

	@Column(name = "PHONE")
	private String phone;

	@JsonIgnore
	private String passwd;

	@Column ( name = "ISPARCELDASHLETTES")
	private Boolean parcelDashlettes ;

	@Column(name = "db_def_cust")
	private String defaultCustomer;

	@Column ( name = "is_invoicing_analyst")
	private Boolean invoicingAnalyst ;

	@Column ( name = "user_role_name")
	private String userRoleName;



	public UserProfileDto() {
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public Boolean getIsAccounts() {
		return isAccounts;
	}

	public void setIsAccounts(Boolean isAccounts) {
		this.isAccounts = isAccounts;
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

	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	public Boolean isParcelDashlettes() {
		return parcelDashlettes;
	}

	public void setParcelDashlettes(Boolean parcelDashlettes) {
		this.parcelDashlettes = parcelDashlettes;
	}

	public String getDefaultCustomer() {
		return defaultCustomer;
	}

	public void setDefaultCustomer(String defaultCustomer) {
		this.defaultCustomer = defaultCustomer;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Boolean getInvoicingAnalyst() {
		return invoicingAnalyst;
	}

	public void setInvoicingAnalyst(Boolean invoicingAnalyst) {
		this.invoicingAnalyst = invoicingAnalyst;
	}

	@Override
	public String toString() {
		return "UserProfileTb [userId=" + userId + ", email=" + email + ", fullname=" + fullname + ", isAccounts="
				+ isAccounts + ", userName=" + userName + ", userinfo=" + userinfo + ", passwd=" + passwd + "]";
	}


	public String getUserRoleName() {
		return userRoleName;
	}

	public void setUserRoleName(String userRoleName) {
		this.userRoleName = userRoleName;
	}
}