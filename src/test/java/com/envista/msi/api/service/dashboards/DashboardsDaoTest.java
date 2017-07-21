package com.envista.msi.api.service.dashboards;

import com.envista.msi.api.dao.DashboardsDao;
import com.envista.msi.api.rest.WebappTestEnvironment;
import com.envista.msi.api.web.rest.dto.dashboard.DashboardAppliedFilterDto;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Test class for the DashboardsDao DAO.
 *
 *
 */

public class DashboardsDaoTest extends WebappTestEnvironment {

	private DashboardsDao dashboardsDao = new DashboardsDao();
	private DashboardAppliedFilterDto expectedDashboardsDto;
	private long loginUserId = 23166;

	@Before
	public void init() {
		this.expectedDashboardsDto = new DashboardAppliedFilterDto();
		//this.expectedDashboardsDto.setUserName("sarvesh");
	}

	@Test
	public void testUserNameFromAppliedFilter() {
		/*assertThat(this.expectedDashboardsDto.getUserName())
				.isEqualTo("sarvesh");*/
	}

}