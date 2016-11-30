package com.envista.msi.api.rest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import java.util.Enumeration;

import javax.annotation.Resource;
import javax.naming.NamingException;
import javax.servlet.http.Cookie;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.envista.msi.api.MsiApp;

/**
 * @author SANKER
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@SpringApplicationConfiguration(classes = MsiApp.class)
@IntegrationTest
public class AbstractOAuth2SupportTest {

	private static final Logger log = LoggerFactory.getLogger(AbstractOAuth2SupportTest.class);

	@Resource
	private FilterChainProxy springSecurityFilterChain;

	@Autowired
	protected UserDetailsService userDetailsService;

	@Autowired
	private WebApplicationContext wac;

	/*
	 * @Autowired protected DataSource dataSource;
	 */

	protected MockMvc mockMvc;

	private final static String USER_IN_TEST = "sreedhart";
	private final static String password = "S7reedhar";
	private static HttpSessionCsrfTokenRepository httpSessionCsrfTokenRepository = new HttpSessionCsrfTokenRepository();
	private static MockHttpSession mockSession = new MockHttpSession();
	private static CsrfToken csrfToken;

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	protected UsernamePasswordAuthenticationToken getPrincipal(String username) {
		UserDetails user = this.userDetailsService.loadUserByUsername(username);
		UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(user,
				user.getPassword(), user.getAuthorities());
		return authentication;
	}

	@Before
	public void setupMockMvc() throws NamingException {

		// setup mock MVC
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).addFilters(this.springSecurityFilterChain).build();
		try {
			this.signedIn();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void signedIn() throws Exception {

		ResultActions rootRequest = mockMvc.perform(get("/"));

		// HttpSessionCsrfTokenRepository httpSessionCsrfTokenRepository = new
		// HttpSessionCsrfTokenRepository();
		/* CsrfToken */
		csrfToken = httpSessionCsrfTokenRepository
				.generateToken(/* ((ResultActions) mockMvc.perform(get("/"))) */rootRequest.andReturn().getRequest());
		Cookie[] cookies = rootRequest.andReturn().getResponse().getCookies();
		for (Cookie cookie : cookies) {
			log.debug("ResultActions cookie: " + cookie);
			log.debug(" cookie name: " + cookie.getName() + " value:  " + cookie.getValue());
		}

		mockSession.setAttribute("org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository.CSRF_TOKEN",
				csrfToken);
		String userPassJsonToken = "{ \"userId\": \"" + USER_IN_TEST + "\", \"password\": \"" + password + "\" }";
		log.debug("userPassJsonToken: " + userPassJsonToken);
		ResultActions something2 = mockMvc
				.perform(post("/api/authentication")/* .with(csrf()) */
						.header("X-CSRF-TOKEN", csrfToken.getToken()).param("_csrf", csrfToken.getToken())
						.contentType(MediaType.APPLICATION_JSON).content(userPassJsonToken.getBytes())
						.session(mockSession));

		log.debug("ResultActions path: " + something2.andReturn().getRequest().getPathInfo());
		log.debug("ResultActions remote user: " + something2.andReturn().getRequest().getRemoteUser());
		log.debug("ResultActions status: " + something2.andReturn().getResponse().getStatus());
		Cookie[] cookies2 = something2.andReturn().getResponse().getCookies();
		for (Cookie cookie : cookies2) {
			log.debug("ResultActions cookie: " + cookie);
			log.debug("ResultActions cookie: " + cookie.getName() + "  " + cookie.getValue());
		}
		// mockSession.
		log.debug("mockSession: " + mockSession);
		log.debug("mockSessionSESSION_COOKIE_NAME: " + mockSession.SESSION_COOKIE_NAME);
		log.debug("mockSession SESSION id: " + mockSession.getId());
		log.debug("mockSession SESSION hashcode: " + mockSession.hashCode());
	}

	/**
	 * @return the csrfToken
	 */
	public static CsrfToken getCsrfToken() {
		return csrfToken;
	}

	/**
	 * @return the csrfToken
	 */
	public static String sessionId() {
		return mockSession.getId();
	}

	/**
	 * @return the csrfToken
	 */
	public static String sessionName() {
		log.debug("mockSession.getAttributeNames()" + mockSession.getAttributeNames());
		Enumeration<String> attribs = mockSession.getAttributeNames();
		while (attribs.hasMoreElements()) {
			String name = attribs.nextElement();
			log.debug("name: " + name + " Value: " + mockSession.getValue(name));
		}
		return mockSession.SESSION_COOKIE_NAME;
	}
}