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

import com.envista.msi.api.domain.freight.ShpUserProfileTb;
import com.envista.msi.api.repository.UserRepository;

/**
 * Authenticate a user from the database.
 */
@Component("userDetailsService")
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

	private final Logger log = LoggerFactory.getLogger(UserDetailsService.class);

	@Inject
	private UserRepository userRepository;

	@Override
	@Transactional
	public UserDetails loadUserByUsername(final String login) {
		log.debug("Authenticating {}", login);
		String lowercaseLogin = login.toLowerCase(Locale.ENGLISH);
		Optional<ShpUserProfileTb> userFromDatabase = userRepository.findOneByUserName(lowercaseLogin);
		return userFromDatabase.map(user -> {
			List<GrantedAuthority> grantedAuthorities = new ArrayList<GrantedAuthority>();
			grantedAuthorities.add(new SimpleGrantedAuthority("ADMIN"));
			return new org.springframework.security.core.userdetails.User(lowercaseLogin,
					user.getPasswd(), grantedAuthorities);
		}).orElseThrow(
				() -> new UsernameNotFoundException("User " + lowercaseLogin + " was not found in the " + "database"));
	}
}
