package com.envista.msi.api.web.rest.dto;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityResult;
import javax.persistence.FieldResult;
import javax.persistence.Id;
import javax.persistence.NamedStoredProcedureQueries;
import javax.persistence.NamedStoredProcedureQuery;
import javax.persistence.ParameterMode;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.StoredProcedureParameter;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
		                )
	            }
	        )}
		)
@NamedStoredProcedureQueries({
	@NamedStoredProcedureQuery(name = "UserProfileTb.getUserByProcUserEntity", procedureName = "getUserByUserName", 
	resultClasses = UserProfileDto.class,
	parameters = {
			@StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, name = "RetUser", type = void.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "userNameInParam", type = String.class)
			 })
	,
	@NamedStoredProcedureQuery(name = "UserProfileTb.getUserByProcUserEntityMapping", procedureName = "getUserByUserName", resultSetMappings = "UserProfileTbMapper",
	parameters = {
			@StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, name = "RetUser", type = void.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "userNameInParam", type = String.class)
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

	@JsonIgnore
	private String passwd;

	@Column ( name = "ISPARCELDASHLETTES")
	private Boolean parcelDashlettes ;

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

	@Override
	public String toString() {
		return "UserProfileTb [userId=" + userId + ", email=" + email + ", fullname=" + fullname + ", isAccounts="
				+ isAccounts + ", userName=" + userName + ", userinfo=" + userinfo + ", passwd=" + passwd + "]";
	}

}