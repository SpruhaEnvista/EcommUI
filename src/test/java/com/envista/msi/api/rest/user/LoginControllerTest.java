package com.envista.msi.api.rest.user;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import javax.inject.Inject;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

//import com.envista.msi.api.repository.UserRepository;
import com.envista.msi.api.rest.WebappTestEnvironment;
import com.envista.msi.api.service.UserService;
import com.envista.msi.api.web.rest.AccountResource;

public class LoginControllerTest extends WebappTestEnvironment {

	protected MockMvc mockRestMvc;

	private String userInTest = "santhoshi";

	//@Inject
//	private UserRepository userRepository;
	@Inject
	private UserService userService;

	@Mock
	private UserService mockUserService;

	@Test
	public void testNonAuthenticatedUser() throws Exception {
		mockRestMvc(false).perform(get("/api/authenticate").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(content().string(""));
	}

	@Test
	public void testAuthenticatedUser() throws Exception {
		mockRestMvc().perform(get("/api/authenticate").with(request -> {
			request.setRemoteUser(userInTest);
			return request;
		}).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andExpect(content().string(userInTest));
	}

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		AccountResource accountUserMockResource = new AccountResource();
		this.mockRestMvc = MockMvcBuilders.standaloneSetup(accountUserMockResource).build();
	}
}
