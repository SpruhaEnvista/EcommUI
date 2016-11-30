package com.envista.msi.api.web.rest;

import java.util.Optional;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.envista.msi.api.security.SecurityUtils;
import com.envista.msi.api.service.UserService;
import com.envista.msi.api.web.rest.dto.UserProfileBean;

/**
 * REST controller for managing the current user's account.
 * 
 * @author SANKER
 *
 */
@RestController
@RequestMapping("/api")
public class AccountResource {

	private final Logger log = LoggerFactory.getLogger(AccountResource.class);

	@Inject
	private UserService userService;

	/**
	 * GET /authenticate : check if the user is authenticated, and return its
	 * login.
	 *
	 * @param request
	 *            the HTTP request
	 * @return the login if the user is authenticated
	 */
	@RequestMapping(value = "/authenticate", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)

	public String isAuthenticated(HttpServletRequest request) {
		log.debug("REST request to check if the current user is authenticated");
		return request.getRemoteUser();
	}

	/**
	 * GET /account : get the current user.
	 *
	 * @return the ResponseEntity with status 200 (OK) and the current user in
	 *         body, or status 500 (Internal Server Error) if the user couldn't
	 *         be returned
	 * @throws Exception
	 */
	@RequestMapping(value = "/account", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)

	public ResponseEntity<UserProfileBean> getAccount() throws Exception {
		if (null == SecurityUtils.getCurrentUserLogin())
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		return Optional.ofNullable(userService.getUserProfileByUserName(SecurityUtils.getCurrentUserLogin()))
				.map(user -> new ResponseEntity<>(user, HttpStatus.OK))
				.orElse(new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR));
	}
}
