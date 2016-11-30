package com.envista.msi.api.web.rest.dto;

import java.math.BigDecimal;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;

import com.envista.msi.api.config.Constants;
import com.envista.msi.api.domain.freight.ShpUserProfileTb;
import com.envista.msi.api.domain.freight.ShpUserRoleTb;
/**
 * A DTO representing a user, with his authorities.
 */
/**
 * @author SANKER
 *
 */
public class UserDTO {

    @NotNull
   // @Pattern(regexp = Constants.LOGIN_REGEX)
    @Size(min = 1, max = 50)
    private String userName;

    @Size(max = 50)
    private String fullName;

    private boolean accounts;
    
    @Email
    @Size(min = 5, max = 100)
    private String email;

    private boolean activated;
  
    private Set<String> authorities;
    
    private Set<Long> userRoleIds;

    public UserDTO() {
    }

    public UserDTO(ShpUserProfileTb user) {
        
    	this(user.getUserName(), 
        	user.getFullname(), 
        	user.isAccounts(),
        	user.getEmail(), 
        	user.isActive(), 
            user.getShpUserRoleTbs().stream().map(ShpUserRoleTb::getUserRoleId)
                .collect(Collectors.toSet()));
    }

    public UserDTO(String userName, String fullName, boolean accounts,
        String email, boolean activated, Set<Long> userRoleIds) {

        this.userName = userName;
        this.fullName = fullName;
        this.accounts = accounts;
        this.email = email;
        this.activated = activated;
        this.userRoleIds = userRoleIds;
    }

    /*public UserDTO(String userName, String fullname, BigDecimal isAccounts, BigDecimal isActive, BigDecimal isAuditor,
			BigDecimal isCanAnalyst, String email2, BigDecimal isEmailToBeSent, BigDecimal accessQueues,
			Set<Long> collect) {
		
	}*/
    

    public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public boolean isAccounts() {
		return accounts;
	}

	public void setAccounts(boolean accounts) {
		this.accounts = accounts;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean getActivated() {
		return activated;
	}

	public void setActivated(boolean activated) {
		this.activated = activated;
	}

	public Set<String> getAuthorities() {
		return authorities;
	}

	public void setAuthorities(Set<String> authorities) {
		this.authorities = authorities;
	}

	public Set<Long> getUserRoleIds() {
		return userRoleIds;
	}

	public void setUserRoleIds(Set<Long> userRoleIds) {
		this.userRoleIds = userRoleIds;
	}

	@Override
	public String toString() {
		return "UserDTO [userName=" + userName + ", fullName=" + fullName + ", accounts=" + accounts + ", email="
				+ email + ", activated=" + activated + ", authorities=" + authorities + ", userRoleIds=" + userRoleIds
				+ "]";
	}
}
