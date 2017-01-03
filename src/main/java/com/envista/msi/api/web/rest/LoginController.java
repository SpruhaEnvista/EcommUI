/**
 * 
 */
package com.envista.msi.api.web.rest;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.envista.msi.api.security.SecurityUtils;
import com.envista.msi.api.service.UserService;
import com.envista.msi.api.web.rest.dto.UserProfileDto;
import com.envista.msi.api.web.rest.util.WebConstants;

/**
 * @author SANKER
 *
 */
@RestController
@RequestMapping("/api/user")
public class LoginController {
	@Autowired
	private HttpSession httpSession;

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/profile", method = { RequestMethod.GET, RequestMethod.POST,
			RequestMethod.OPTIONS }, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UserProfileDto> authorize() {
		if (SecurityUtils.isAuthenticated()) {
			if (null == httpSession.getAttribute(WebConstants.USER_IN_SESSION)) {
				try {
					httpSession.setAttribute(WebConstants.USER_IN_SESSION,
							userService.getUserProfileByUserName(SecurityUtils.getCurrentUserLogin()));
				} catch (Exception exp) {
					exp.printStackTrace();
					return new ResponseEntity<UserProfileDto>(
							(UserProfileDto) httpSession.getAttribute(WebConstants.USER_IN_SESSION_OTHERWISE),
							HttpStatus.INTERNAL_SERVER_ERROR);
				}
			}
			return new ResponseEntity<UserProfileDto>(
					(UserProfileDto) httpSession.getAttribute(WebConstants.USER_IN_SESSION), HttpStatus.OK);
		}
		return new ResponseEntity<UserProfileDto>(
				(UserProfileDto) httpSession.getAttribute(WebConstants.USER_IN_SESSION_OTHERWISE),
				HttpStatus.UNAUTHORIZED);
	}

}
