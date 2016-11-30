package com.envista.msi.api.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.envista.msi.api.web.rest.util.JSONUtil;

/**
 * Security success handler, specialized for Ajax requests.
 */
@Component
public class AjaxAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {

		response.setStatus(HttpServletResponse.SC_OK);
		SecurityUtils.getCurrentUserLogin();
		Cookie cookie = new Cookie("currentUser", JSONUtil.ConvertObject2JSON(SecurityUtils.getCurrentUserLogin()));
		cookie.setMaxAge(-1);
		cookie.setHttpOnly(false);
		cookie.setPath("/");
		response.addCookie(cookie);
		Cookie currentId = new Cookie("currentId", JSONUtil.ConvertObject2JSON(SecurityUtils.getCurrentUserLogin()));
		currentId.setMaxAge(-1);
		currentId.setHttpOnly(false);
		currentId.setPath("/");
		response.addCookie(currentId);
	}

}
