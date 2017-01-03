package com.envista.msi.api.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.envista.msi.api.domain.ShpUserProfileTb;
import com.envista.msi.api.web.rest.dto.UserProfileDto;

/**
 * Utility class for Spring Security.
 */
public final class SecurityUtils {

	private final static String USER_IN_SESSION = "USER_IN_SESSION";

	/*
	 * @Autowired private UserService userService;
	 */
	private SecurityUtils() {
	}

	/**
	 * Get the login of the current user.
	 *
	 * @return the login of the current user
	 */
	public static String getCurrentUserLogin() {
		SecurityContext securityContext = SecurityContextHolder.getContext();
		Authentication authentication = securityContext.getAuthentication();
		String userName = null;
		if (authentication != null) {
			if (authentication.getPrincipal() instanceof UserDetails) {
				UserDetails springSecurityUser = (UserDetails) authentication.getPrincipal();
				userName = springSecurityUser.getUsername();
			} else if (authentication.getPrincipal() instanceof String) {
				userName = (String) authentication.getPrincipal();
			}
		}
		return userName;
	}

	/**
	 * Get the login of the current user.
	 *
	 * @return the login of the current user
	 */
	private ShpUserProfileTb getCurrentUserFromName() {
		ShpUserProfileTb user = null;
		SecurityContext securityContext = SecurityContextHolder.getContext();
		Authentication authentication = securityContext.getAuthentication();
		String userName = null;
		if (authentication != null) {
			if (authentication.getPrincipal() instanceof UserDetails) {
				UserDetails springSecurityUser = (UserDetails) authentication.getPrincipal();
				userName = springSecurityUser.getUsername();
			} else if (authentication.getPrincipal() instanceof String) {
				userName = (String) authentication.getPrincipal();
			}
		}
		return user;
	}

	/**
	 * Get the login of the current user.
	 *
	 * @return the login of the current user
	 */
	public static ShpUserProfileTb getCurrentUser() {
		try {
			new SecurityUtils().getCurrentUserFromName();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Get the login of the current user.
	 *
	 * @return the login of the current user
	 */
	public static Collection<String> getCurrentUserRoles() {
		SecurityContext securityContext = SecurityContextHolder.getContext();
		Authentication authentication = securityContext.getAuthentication();
		List<String> roles = new ArrayList<String>();
		if (authentication != null) {
			if (authentication.getPrincipal() instanceof UserDetails) {
				UserDetails springSecurityUser = (UserDetails) authentication.getPrincipal();
				Collection<? extends GrantedAuthority> authorities = springSecurityUser.getAuthorities();

				for (GrantedAuthority grantedAuthority : authorities) {
					roles.add(grantedAuthority.getAuthority());
				}
			}
		}
		return roles;
	}

	/**
	 * Check if a user is authenticated.
	 *
	 * @return true if the user is authenticated, false otherwise
	 */
	public static boolean isAuthenticated() {
		SecurityContext securityContext = SecurityContextHolder.getContext();
		Collection<? extends GrantedAuthority> authorities = securityContext.getAuthentication().getAuthorities();
		if (authorities != null) {
			for (GrantedAuthority authority : authorities) {
				if (authority.getAuthority().equals(AuthoritiesConstants.ANONYMOUS)) {
					return false;
				}
			}
		}
		return true;
	}

	/**
	 * If the current user has a specific authority (security role).
	 *
	 * <p>
	 * The name of this method comes from the isUserInRole() method in the
	 * Servlet API
	 * </p>
	 *
	 * @param authority
	 *            the authorithy to check
	 * @return true if the current user has the authority, false otherwise
	 */
	public static boolean isCurrentUserInRole(String authority) {
		SecurityContext securityContext = SecurityContextHolder.getContext();
		Authentication authentication = securityContext.getAuthentication();
		if (authentication != null) {
			if (authentication.getPrincipal() instanceof UserDetails) {
				UserDetails springSecurityUser = (UserDetails) authentication.getPrincipal();
				return springSecurityUser.getAuthorities().contains(new SimpleGrantedAuthority(authority));
			}
		}
		return false;
	}

	/**
	 * Get the object of the current user.
	 *
	 * @return the login of the current user
	 */
	public static UserProfileDto getUserProfileBean() {
		ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		UserProfileDto user = null; // true == allow create
		HttpServletRequest httpSession = attr.getRequest();
		if (null != httpSession && null != httpSession.getSession()) {
			user = (UserProfileDto) httpSession.getAttribute(USER_IN_SESSION);
		}
		return user;
	}
}
