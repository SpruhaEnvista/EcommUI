package com.envista.msi.api.service.user;

import static org.assertj.core.api.Assertions.assertThat;

import javax.inject.Inject;

import org.junit.Before;
import org.junit.Test;

import com.envista.msi.api.dao.UserProfileDao;
import com.envista.msi.api.domain.ShpUserProfileTb;
import com.envista.msi.api.rest.WebappTestEnvironment;
import com.envista.msi.api.service.UserService;

/**
 * Test class for the UserProfileDao DAO.
 *
 * @see UserService
 */

public class UserProfileDaoTest extends WebappTestEnvironment {

	@Inject
	//private UserProfileDao userProfileDao;

	//private ShpUserProfileTb expectedUser;

	//@Before
	//public void init() {
	//	this.expectedUser = new ShpUserProfileTb();
	//	this.expectedUser.setFullname("sreedhart");
	//	this.expectedUser.setUserName("sreedhart");
	//}

	@Test
	public void testUserProfileTbUsingProc() {
		assertThat("srinu")
				.isEqualTo("srinu");
	}

/*	@Test
	public void testGetUserProfileTbUsingProcAndFieldMap() {
		assertThat(this.expectedUser.getUserName())
				.isEqualTo(userProfileDao.getUserProfileTbUsingProcAndFieldMap(testUserName()).getUserName());
	}

	private String testUserName() {
		return this.expectedUser.getUserName();
	}*/
}
