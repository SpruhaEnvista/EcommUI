package com.envista.msi.api.service.dashboards;

import com.envista.msi.api.dao.DashboardsDao;
import com.envista.msi.api.rest.WebappTestEnvironment;
import com.envista.msi.api.web.rest.dto.DashboardAppliedFilterDto;
import org.junit.Before;
import org.junit.Test;

import javax.inject.Inject;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

/**
 * Test class for the DashboardsDao DAO.
 *
 *
 */

public class DashboardsDaoTest extends WebappTestEnvironment {

	@Inject
	private DashboardsDao dashboardsDao;
	private DashboardAppliedFilterDto expectedDashboardsDto;
	private long loginUserId = 23166;

	@Before
	public void init() {
		this.expectedDashboardsDto = new DashboardAppliedFilterDto();
		this.expectedDashboardsDto.setUserName("sarvesh");
	}

	@Test
	public void testGetUserAppliedFilter() {
		dashboardsDao.getUserAppliedFilter(loginUserId);
	}

	@Test
	public void testUserNameFromAppliedFilter() {
		assertThat(this.expectedDashboardsDto.getUserName())
				.isEqualTo(dashboardsDao.getUserAppliedFilter(loginUserId).getUserName());
	}

}