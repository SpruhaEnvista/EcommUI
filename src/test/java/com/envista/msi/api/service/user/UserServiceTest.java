package com.envista.msi.api.service.user;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import javax.inject.Inject;

import org.junit.Test;

import com.envista.msi.api.domain.freight.ShpUserProfileTb;
import com.envista.msi.api.repository.UserRepository;
import com.envista.msi.api.rest.WebappTestEnvironment;
import com.envista.msi.api.service.UserService;

/**
 * Test class for the UserService REST controller.
 *
 * @see UserService
 */

public class UserServiceTest extends WebappTestEnvironment {

	@Inject
	private UserRepository userRepository;

	@Inject
	private UserService userService;

	@Test
	public void testFindOne() {
		ShpUserProfileTb user = new ShpUserProfileTb();
		user.setFullname("sreedhart");
		user.setUserName("sreedhart");
		Optional<ShpUserProfileTb> users = userRepository.findOneByUserName("sreedhart");
		assertThat(user.getUserName()).isEqualTo(users.get().getUserName());
	}

	@Test
	public void testUserFromService() {
		ShpUserProfileTb user = new ShpUserProfileTb();
		user.setFullname("sreedhart");
		user.setUserName("sreedhart");
		Optional<ShpUserProfileTb> userFromService = userService.getUserWithAuthoritiesByUserName("sreedhart");
		assertThat(user.getUserName()).isEqualTo(userFromService.get().getUserName());
	}
}
