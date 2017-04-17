package com.envista.msi.api.config;

import javax.inject.Inject;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.client.DefaultOAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.token.AccessTokenRequest;
import org.springframework.security.oauth2.client.token.DefaultAccessTokenRequest;
import org.springframework.security.oauth2.client.token.grant.password.ResourceOwnerPasswordResourceDetails;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.RemoteTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;
import org.springframework.util.StringUtils;

import com.envista.msi.api.security.AjaxLogoutSuccessHandler;
import com.envista.msi.api.security.AuthoritiesConstants;
import com.envista.msi.api.security.Http401UnauthorizedEntryPoint;

@Configuration
public class MSIClientConfiguration {

	@Configuration
	@EnableResourceServer
	protected static class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {

		@Inject
		private Http401UnauthorizedEntryPoint authenticationEntryPoint;

		@Inject
		private AjaxLogoutSuccessHandler ajaxLogoutSuccessHandler;

		@Bean
		public RemoteTokenServices remoteTokenServices() {

			ResourceOwnerPasswordResourceDetails resourceDetails = new ResourceOwnerPasswordResourceDetails();
			resourceDetails.setUsername("sreedhart");
			resourceDetails.setPassword("7Sreedhar");
			resourceDetails.setAccessTokenUri("http://localhost:8888/msioauthserver/oauth/token");
			resourceDetails.setClientId("msioauthtestapp");
			resourceDetails.setClientSecret("my-secret-token-to-change-in-production");
			resourceDetails.setGrantType("password");

			AccessTokenRequest atr = new DefaultAccessTokenRequest();
			OAuth2RestTemplate restTemplate = new OAuth2RestTemplate(resourceDetails,
					new DefaultOAuth2ClientContext(atr));
			RemoteTokenServices tokenService = new RemoteTokenServices();
			// tokenService..setRestTemplate(restTemplate);
			tokenService.setCheckTokenEndpointUrl(checkTokenPath());
			tokenService.setClientId(clientId);
			tokenService.setClientSecret(clientSecret);
			return tokenService;
		}

		@Override
		public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
			resources.resourceId("/oauth");
			resources.tokenServices(remoteTokenServices());
		}

		@Override
		public void configure(HttpSecurity http) throws Exception {
			http.exceptionHandling().authenticationEntryPoint(authenticationEntryPoint).and().logout()
					.logoutUrl("/api/logout").logoutSuccessHandler(ajaxLogoutSuccessHandler).and().csrf().disable()
					.headers().frameOptions().disable().and().sessionManagement()
					.sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().authorizeRequests()
					.antMatchers(HttpMethod.OPTIONS, "/**").permitAll().antMatchers("/api/authenticate").permitAll()
					.antMatchers("/api/register").permitAll().antMatchers("/api/**").authenticated()
					.antMatchers("/management/**").hasAuthority(AuthoritiesConstants.ADMIN)
					.antMatchers("/v2/api-docs/**").permitAll().antMatchers("/configuration/ui").permitAll()
					.antMatchers("/swagger-ui/index.html").hasAuthority(AuthoritiesConstants.ADMIN)
					.antMatchers("/apiFancy/**").access("#oauth2.hasScope('read')");
		}

		private String checkTokenPath() {
			String port = ":" + getPort();
			if (getPort() == 80 || getPort() < 1) {
				port = "";
			}
			return "http://" + getHostName() + port + "/" + getApi() + checkPath;
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
		
		private String getApi() {
			if (this.api == null || UNDEFINED.equalsIgnoreCase(api) ) {
				return "";
			}
			return api + "/";
		}

		private static final Integer DEFAULT_PORT = 80;
		private static final String DEFAULT_HOST = "localhost";
		private static final String UNDEFINED = "UNDEFINED";
		// "/msioauthserver/oauth/check_token" /oauth/check_token
		private static final String checkPath = "oauth/check_token";

		@Value("${spring.oauth2.sso.server.port:80}")
		private Integer port;
		@Value("${spring.oauth2.sso.server.host:localhost}")
		private String hostName;
		@Value("${spring.oauth2.sso.client.clientId:msioauthtestapp}")
		private String clientId;
		@Value("${spring.oauth2.sso.client.clientSecret:my-secret-token-to-change-in-production}")
		private String clientSecret;
		@Value("${spring.oauth2.sso.server.api:UNDEFINED}")
		private String api;
	}

	protected static class AuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter {

		@Inject
		private DataSource dataSource;

		@Inject
		private MSIAppProperties msiProperties;

		@Bean
		public TokenStore tokenStore() {
			return new JdbcTokenStore(dataSource);
		}

		@Inject
		@Qualifier("authenticationManagerBean")
		private AuthenticationManager authenticationManager;

		@Override
		public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {

			endpoints.tokenStore(tokenStore()).authenticationManager(authenticationManager);
		}

		@Override
		public void configure(AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
			oauthServer.allowFormAuthenticationForClients();
			oauthServer.passwordEncoder(new MSIPasswordEncoder());
		}

		@Override
		public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
			clients.inMemory().withClient(msiProperties.getSecurity().getAuthentication().getOauth().getClientid())
					.scopes("read", "write")
					.authorities(AuthoritiesConstants.ADMIN, AuthoritiesConstants.USER, AuthoritiesConstants.MSI_UI)
					.authorizedGrantTypes("password", "refresh_token", "authorization_code", "implicit")
					.secret(msiProperties.getSecurity().getAuthentication().getOauth().getSecret())
					.accessTokenValiditySeconds(
							msiProperties.getSecurity().getAuthentication().getOauth().getTokenValidityInSeconds())
					.and().withClient("Other").secret("Others_Secret")
					.authorizedGrantTypes("password", "authorization_code", "refresh_token")
					.authorities("ROLE_USER", "ROLE_CLIENT", "ROLE_TRUSTED_CLIENT").scopes("play", "trust")
					.redirectUris("https://www.myshipinfo.com/oauth_tool/callback").autoApprove(true);
		}
	}
}