package com.envista.msi.api.rest;

import java.io.IOException;
import java.util.Arrays;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.security.crypto.codec.Base64;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.request.RequestPostProcessor;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;
import org.springframework.web.client.ResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

/**
 * @author SANKER
 *
 */
@TestPropertySource(locations = "classpath:application_test.properties")
public abstract class AbstractApiIntegrationTests {

	private static final Logger log = LoggerFactory.getLogger(AbstractApiIntegrationTests.class);

	private static final String globalTokenPath = "msioauthapi/oauth/token";
	private static final String globalCheckTokenPath = "msioauthapi/oauth/check_token";

	private static final Integer DEFAULT_PORT = 80;
	private static final String DEFAULT_HOST = "msiuat02";

	private static final String AUTH_HEADER = "Authorization";
	private static final String DEFAULT_AUTH_HEADER_VALUE = "Basic bXNpb2F1dGh0ZXN0YXBwOm15LXNlY3JldC10b2tlbi10by1jaGFuZ2UtaW4tcHJvZHVjdGlvbg==";

	@Value("${auth.server.port:}")
	private Integer port;
	@Value("${auth.server.host:}")
	private String hostName;
	@Value("${auth.security.clientId:}")
	private String clientId;
	@Value("${auth.security.clientSecret:}")
	private String clientSecret;

	private String authHeaderValue;

	private OAuth2AccessToken accessToken;

	@Bean
	RestTemplate restTemplate() {
		HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
		RestTemplate restTemplate = new RestTemplate(new BufferingClientHttpRequestFactory(requestFactory));
		restTemplate.setErrorHandler(new OAuthResponseErrorHandler());
		return restTemplate;
	}

	@Autowired(required = false)
	RestTemplate restTemplate;

	private String obtainAccessToken(boolean refresh, String clientId, String clientSecret, String username,
			String password) {

		if (restTemplate == null) {
			restTemplate = restTemplate();
		}

		MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();

		if (refresh) {
			params.add("grant_type", "refresh_token");
			params.add("refresh_token", OAuth2AccessToken.REFRESH_TOKEN);
		} else {
			params.add("grant_type", "password");
			params.add("username", username);
			params.add("password", password);
		}
		HttpHeaders retHeaders = new HttpHeaders();
		retHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		retHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

		if (!StringUtils.isEmpty(clientId)) {
			retHeaders.set(AUTH_HEADER,
					"Basic " + new String(Base64.encode(String.format("%s:%s", clientId, clientSecret).getBytes())));
		} else {
			String oauthHeader = DEFAULT_AUTH_HEADER_VALUE;
			if (StringUtils.isEmpty(this.authHeaderValue)) {
				if (!StringUtils.isEmpty(this.clientId) && !StringUtils.isEmpty(this.clientSecret)) {
					this.authHeaderValue = "Basic " + new String(
							Base64.encode(String.format("%s:%s", this.clientId, this.clientSecret).getBytes()));
				}
			}
			if (!StringUtils.isEmpty(this.authHeaderValue)) {
				oauthHeader = this.authHeaderValue;
			}
			retHeaders.add(AUTH_HEADER, oauthHeader);
		}

		HttpEntity<MultiValueMap<String, String>> templateRequest = new HttpEntity<MultiValueMap<String, String>>(
				params, retHeaders);
		ResponseEntity<Map> response = restTemplate.exchange(tokenPath(), HttpMethod.POST, templateRequest, Map.class);

		try {
			if (isError(response.getStatusCode())) {
				this.accessToken = null;
				throw new Exception(response.getStatusCode() + " : " + response.getBody());
			} else {
				// @SuppressWarnings("unchecked")
				synchronized (this) {
					this.accessToken = DefaultOAuth2AccessToken.valueOf(response.getBody());
				}

				return accessToken.getValue();
			}
		} catch (Exception e) {
			log.error("", e);
		}

		return null;
	}

	private static boolean isError(HttpStatus status) {
		HttpStatus.Series series = status.series();
		return (HttpStatus.Series.CLIENT_ERROR.equals(series) || HttpStatus.Series.SERVER_ERROR.equals(series));
	}

	// For use with MockMvc
	RequestPostProcessor bearerToken(String clientId, String clientSecret, String username, String password) {
		OAuth2AccessToken token;
		if (this.accessToken != null && !this.accessToken.isExpired()) {
			token = new DefaultOAuth2AccessToken(obtainAccessToken(true, clientId, clientSecret, username, password));
		} else {
			token = new DefaultOAuth2AccessToken(obtainAccessToken(false, clientId, clientSecret, username, password));
		}
		return mockRequest -> {
			mockRequest.addHeader(AUTH_HEADER, "Bearer " + token.getValue());
			return mockRequest;
		};
	}

	RequestPostProcessor bearerToken(final String username, final String password) {
		return bearerToken(null, null, username, password);
	}

	private String tokenPath() {
		return getUrl(globalTokenPath);
	}

	private String checkTokenPath() {
		return getUrl(globalCheckTokenPath);
	}

	private String getBaseUrl() {
		return "http://" + getHostName() + ":" + getPort();
	}

	private String getUrl(String path) {
		if (path.startsWith("http")) {
			return path;
		}
		if (!path.startsWith("/")) {
			path = "/" + path;
		}
		return getBaseUrl() + path;
	}

	private String getHostName() {
		if (StringUtils.isEmpty(this.hostName)) {
			this.hostName = DEFAULT_HOST;
		}
		return this.hostName;
	}

	private Integer getPort() {
		if (this.port == null) {
			this.port = DEFAULT_PORT;
		}
		return this.port;
	}

	/**
	 * tests a happy-day flow of the refresh token provider.
	 */
	/*
	 * @Test public void testHappyDay() throws Exception {
	 * 
	 * OAuth2AccessToken accessToken = getAccessToken("read write",
	 * "my-trusted-client");
	 * 
	 * // now use the refresh token to get a new access token.
	 * assertNotNull(accessToken.getRefreshToken()); OAuth2AccessToken
	 * newAccessToken =
	 * refreshAccessToken(accessToken.getRefreshToken().getValue());
	 * assertFalse(newAccessToken.getValue().equals(accessToken.getValue()));
	 * 
	 * verifyAccessTokens(accessToken, newAccessToken);
	 * 
	 * }
	 * 
	 * private void verifyAccessTokens(OAuth2AccessToken oldAccessToken,
	 * OAuth2AccessToken newAccessToken) { // make sure the new access token can
	 * be used. verifyTokenResponse(newAccessToken.getValue(), HttpStatus.OK);
	 * // make sure the old access token isn't valid anymore.
	 * verifyTokenResponse(oldAccessToken.getValue(), HttpStatus.UNAUTHORIZED);
	 * }
	 * 
	 * private void verifyTokenResponse(String accessToken, HttpStatus status) {
	 * HttpHeaders headers = new HttpHeaders(); headers.set("Authorization",
	 * String.format("%s %s", OAuth2AccessToken.BEARER_TYPE, accessToken));
	 * assertEquals(status, http.getStatusCode("/admin/beans", headers)); }
	 */

	/**
	 * @author SANKER
	 *
	 */
	class OAuthResponseErrorHandler implements ResponseErrorHandler {
		@Override
		public void handleError(ClientHttpResponse response) throws IOException {
			log.error("Response error: {} {}", response.getStatusCode(), response.getStatusText());
		}

		@Override
		public boolean hasError(ClientHttpResponse response) throws IOException {
			return isError(response.getStatusCode());
		}
	}
}
