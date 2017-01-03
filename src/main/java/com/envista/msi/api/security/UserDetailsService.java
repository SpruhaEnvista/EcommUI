package com.envista.msi.api.security;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.envista.msi.api.dao.UserProfileDao;
import com.envista.msi.api.web.rest.dto.UserProfileDto;

/**
 * Authenticate a user from the database.
 */
@Component("userDetailsService")
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

	private final Logger log = LoggerFactory.getLogger(UserDetailsService.class);

	@Inject
	private UserProfileDao userProfileDao;

	@Override
	@Transactional
	public UserDetails loadUserByUsername(final String login) {
		log.debug("Authenticating {}", login);
		String lowercaseLogin = login.toLowerCase(Locale.ENGLISH);
		Optional<UserProfileDto> userFromDatabase = userProfileDao.findUserProfileTbUsingProc(lowercaseLogin);
		return userFromDatabase.map(user -> {
			List<GrantedAuthority> grantedAuthorities = new ArrayList<GrantedAuthority>();
			grantedAuthorities.add(new SimpleGrantedAuthority("ADMIN"));
			return new org.springframework.security.core.userdetails.User(lowercaseLogin, user.getPasswd(),
					grantedAuthorities);
		}).orElseThrow(
				() -> new UsernameNotFoundException("User " + lowercaseLogin + " was not found in the " + "database"));
	}
}
