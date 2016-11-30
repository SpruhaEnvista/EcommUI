package com.envista.msi.api.security;

import org.apache.commons.lang.StringUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.web.authentication.AbstractAuthenticationTargetUrlRequestHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import com.envista.msi.api.web.rest.util.JSONUtil;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Spring Security logout handler, specialized for Ajax requests.
 */
@Component
public class AjaxLogoutSuccessHandler extends AbstractAuthenticationTargetUrlRequestHandler
    implements LogoutSuccessHandler {
	
	 public static final String BEARER_AUTHENTICATION = "Bearer ";

	   @Inject
	    private TokenStore tokenStore;

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response,
        Authentication authentication)
        throws IOException, ServletException {
    	// Request the token
        String token = request.getHeader("authorization");
       if (token != null && token.startsWith(BEARER_AUTHENTICATION)) {
            final OAuth2AccessToken oAuth2AccessToken = tokenStore.readAccessToken(StringUtils.substringAfter(token, BEARER_AUTHENTICATION));

            if (oAuth2AccessToken != null) {
                tokenStore.removeAccessToken(oAuth2AccessToken);
            }
        }
        response.setStatus(HttpServletResponse.SC_OK);
        Cookie cookie = new Cookie("currentUser", JSONUtil.ConvertObject2JSON(""));
        cookie.setMaxAge(-1);
        cookie.setHttpOnly(false);
        cookie.setPath("/");
        response.addCookie(cookie);
        Cookie currentId = new Cookie("currentId", JSONUtil.ConvertObject2JSON(""));
        currentId.setMaxAge(-1);
        currentId.setHttpOnly(false);
        currentId.setPath("/");
        response.addCookie(currentId);
    }
}
