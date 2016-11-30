package com.envista.msi.api.web.filter;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.stereotype.Component;

import com.envista.msi.api.config.MSIPasswordEncoder;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

//public class CustomUsernamePasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
//	private String jsonUsername;
//	private String jsonPassword;

	/*@Override
	protected String obtainPassword(HttpServletRequest request) {
		String password = null;

		if ("application/json".equals(request.getHeader("Content-Type"))) {
			password = this.jsonPassword;
		} else {
			password = super.obtainPassword(request);
		}

		return password;
	}

	@Override
	protected String obtainUsername(HttpServletRequest request) {
		String username = null;

		if ("application/json".equals(request.getHeader("Content-Type"))) {
			username = this.jsonUsername;
		} else {
			username = super.obtainUsername(request);
		}

		return username;
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) {
		if ("application/json".equals(request.getHeader("Content-Type"))) {
			try {
				
				 * HttpServletRequest can be read only once
				 
				StringBuffer sb = new StringBuffer();
				String line = null;

				BufferedReader reader = request.getReader();
				while ((line = reader.readLine()) != null) {
					sb.append(line);
				}

				// json transformation
				ObjectMapper mapper = new ObjectMapper();
				LoginRequest loginRequest = mapper.readValue(sb.toString(), LoginRequest.class);

				this.jsonUsername = loginRequest.getUsername();
				this.jsonPassword = loginRequest.getPassword();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return super.attemptAuthentication(request, response);
	}*/
	
	//public class JsonUsernamePasswordAuthenticationFilter extends AbstractAuthenticationProcessingFilter {
//@Component
public class CustomUsernamePasswordAuthenticationFilter extends AbstractAuthenticationProcessingFilter {
	private String usernameParameter = UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_USERNAME_KEY;
	private String passwordParameter = UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_PASSWORD_KEY;

	private boolean postOnly = true;
	private ObjectMapper objectMapper = new ObjectMapper();
	
	public CustomUsernamePasswordAuthenticationFilter() {
		super("/api/authentication");
	}

	/**
	 * The constructor.
	 *
	 * @param requiresAuthenticationRequestMatcher
	 *            the {@link RequestMatcher} used to determine if authentication
	 *            is required. Cannot be null.
	 */
	public CustomUsernamePasswordAuthenticationFilter(RequestMatcher requiresAuthenticationRequestMatcher) {
		super(requiresAuthenticationRequestMatcher);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException, IOException, ServletException {

		if (this.postOnly && !request.getMethod().equals("POST")) {
			throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());
		}

		final UsernameAndPasswordParser usernameAndPasswordParser = new UsernameAndPasswordParser(request);
		usernameAndPasswordParser.parse();
		String pass = usernameAndPasswordParser.getPassword();
		try {
			pass = MSIPasswordEncoder.encrypt(pass);
		} catch (Exception e) {
			// Log and ignore
			;//e.printStackTrace();
		}
		UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(
				usernameAndPasswordParser.getTrimmedUsername(), pass);
		// authRequest.setDetails(this.authenticationDetailsSource.buildDetails(request));
		
		return getAuthenticationManager().authenticate(authRequest);
	}

	/**
	 * @return Value of usernameParameter
	 */
	public String getUsernameParameter() {
		return this.usernameParameter;
	}

	/**
	 * @param usernameParameter
	 *            new value for usernameParameter
	 */
	public void setUsernameParameter(String usernameParameter) {
		this.usernameParameter = usernameParameter;
	}

	/**
	 * @return Value of passwordParameter
	 */
	public String getPasswordParameter() {
		return this.passwordParameter;
	}

	/**
	 * @param passwordParameter
	 *            new value for passwordParameter
	 */
	public void setPasswordParameter(String passwordParameter) {
		this.passwordParameter = passwordParameter;
	}

	/**
	 * @return value of postOnly
	 */
	public boolean isPostOnly() {
		return this.postOnly;
	}

	/**
	 * @param postOnly
	 *            new value for postOnly
	 */
	public void setPostOnly(boolean postOnly) {
		this.postOnly = postOnly;
	}

	private class UsernameAndPasswordParser {
		private String username;
		private String password;
		private final HttpServletRequest request;
		private JsonNode credentialsNode;

		private UsernameAndPasswordParser(HttpServletRequest request) {
			this.request = request;
		}

		public void parse() {
			parseJsonFromRequestBody();
			if (jsonParsedSuccessfully()) {
				extractUsername();
				extractPassword();
			}
		}

		private void extractPassword() {
			this.password = extractValueByName(CustomUsernamePasswordAuthenticationFilter.this.passwordParameter);
		}

		private void extractUsername() {
			this.username = extractValueByName(CustomUsernamePasswordAuthenticationFilter.this.usernameParameter);
			if(null == this.username){
				this.username = extractValueByName("userId");
			}
		}

		private String extractValueByName(String name) {
			String value = null;
			if (this.credentialsNode.has(name)) {
				JsonNode node = this.credentialsNode.get(name);
				if (node != null) {
					value = node.asText();
				}
			}
			return value;
		}

		private boolean jsonParsedSuccessfully() {
			return this.credentialsNode != null;
		}

		private void parseJsonFromRequestBody() {
			try {
				final ServletServerHttpRequest servletServerHttpRequest = new ServletServerHttpRequest(this.request);
				this.credentialsNode = CustomUsernamePasswordAuthenticationFilter.this.objectMapper
						.readTree(servletServerHttpRequest.getBody());
			} catch (IOException e) {
				// ignoring
			}
		}

		private String getTrimmedUsername() {
			return this.username == null ? "" : this.username.trim();
		}

		private String getPassword() {
			return this.password == null ? "" : this.password;
		}
	}
}
