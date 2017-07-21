package com.envista.msi.api.web.rest;

import java.util.*;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import com.envista.msi.api.web.rest.dto.UserDetailsDto;
import com.envista.msi.api.web.rest.dto.i18n.InternationalizationDto;
import com.envista.msi.api.web.rest.response.CommonResponse;
import com.envista.msi.api.web.rest.util.WebConstants;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.envista.msi.api.security.SecurityUtils;
import com.envista.msi.api.service.UserService;
import com.envista.msi.api.web.rest.dto.UserProfileDto;

/**
 * REST controller for managing the current user's account.
 * 
 * @author SANKER
 *
 */
@RestController
@RequestMapping("/api")
public class AccountController {

	private final Logger log = LoggerFactory.getLogger(AccountController.class);

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

	public ResponseEntity<UserProfileDto> getAccount() throws Exception {
		if (null == SecurityUtils.getCurrentUserLogin())
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		return Optional.ofNullable(userService.getUserProfileByUserName(SecurityUtils.getCurrentUserLogin()))
				.map(user -> new ResponseEntity<>(user, HttpStatus.OK))
				.orElse(new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR));
	}
	
	@RequestMapping(value = "/user/profile", method = { RequestMethod.GET, RequestMethod.POST,
			RequestMethod.OPTIONS }, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UserProfileDto> authorize() {
		if (SecurityUtils.isAuthenticated()) {
				try {
					return new ResponseEntity<UserProfileDto>(
							userService.getUserProfileByUserName(SecurityUtils.getCurrentUserLogin()), HttpStatus.OK);
				} catch (Exception exp) {
					exp.printStackTrace();
					return new ResponseEntity<UserProfileDto>(
							new UserProfileDto(),
							HttpStatus.INTERNAL_SERVER_ERROR);
				}
			}
			
		return new ResponseEntity<UserProfileDto>(new UserProfileDto(), HttpStatus.UNAUTHORIZED);
	}
	@RequestMapping(value = "/user/validatepassword", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<Map<String, Object>> validatePassword(@RequestParam String password, @RequestParam String userId){
		Map<String, Object> respMap = new HashMap<String, Object>();
		try {
			UserProfileDto userProfileDto = userService.validatePassword(password,Long.parseLong(userId));
			respMap.put("status", HttpStatus.OK.value());
			respMap.put("userProfileDto", userProfileDto);
		} catch (Exception e) {
			respMap.put("status", HttpStatus.EXPECTATION_FAILED.value());
			respMap.put("message", WebConstants.ResponseMessage.INVALID_PWD);
			respMap.put("ERROR", e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(respMap);
		}
		return ResponseEntity.status(HttpStatus.OK).body(respMap);
	}
	@RequestMapping(value = "/user/changepassword", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<Map<String, Object>> changePassword(@RequestParam String currentPassword,@RequestParam String newPassword, @RequestParam String userId){
		Map<String, Object> respMap = new HashMap<String, Object>();
		try {
			UserDetailsDto userDetailsDto = userService.changePassword(currentPassword,newPassword,Long.parseLong(userId));
			respMap.put("status", HttpStatus.OK.value());
			respMap.put("userProfileDto", userDetailsDto);
		} catch (Exception e) {
			respMap.put("status", HttpStatus.EXPECTATION_FAILED.value());
			respMap.put("message", WebConstants.ResponseMessage.INVALID_PWD);
			respMap.put("ERROR", e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(respMap);
		}
		return ResponseEntity.status(HttpStatus.OK).body(respMap);
	}
	@RequestMapping(value = "/user/updateuserprofile", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<Map<String, Object>> updateUserProfile(@RequestParam String fullname,@RequestParam String email,@RequestParam String phone, @RequestParam Long userId){
		Map<String, Object> respMap = new HashMap<String, Object>();
		try {
			UserDetailsDto updateUserProfileDto = userService.updateUserProfile(fullname.trim(),email.trim(),phone.trim(),userId);
			respMap.put("status", HttpStatus.OK.value());
			respMap.put("userProfileDto", updateUserProfileDto);
		} catch (Exception e) {
			respMap.put("status", HttpStatus.EXPECTATION_FAILED.value());
			respMap.put("message", WebConstants.ResponseMessage.INVALID_PWD);
			respMap.put("ERROR", e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(respMap);
		}
		return ResponseEntity.status(HttpStatus.OK).body(respMap);
	}

	@RequestMapping(value = "/public/user/labelsByLocale", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<CommonResponse> getI18nLabelsByLocale(@RequestParam String locale){
		CommonResponse response = new CommonResponse();
		try{
			response.setStatusCode(HttpStatus.OK.value());
			List<InternationalizationDto> keyValueList = userService.getI18nLabelsByLocale(locale);

			if(keyValueList != null && !keyValueList.isEmpty()){
				JSONObject keyValueJson = new JSONObject();
				for(InternationalizationDto keyVal : keyValueList){
					if(keyVal != null && keyVal.getKey() != null){
						keyValueJson.put(keyVal.getKey(), keyVal.getValue());
					}
				}
				response.setData(keyValueJson);
			}
		}catch(Exception e){
			response.setStatusCode(HttpStatus.EXPECTATION_FAILED.value());
			response.setMessage("Failed to load Labels by locale");
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		}
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}
}
