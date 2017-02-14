package com.envista.msi.api.rest.dashboards;

import com.envista.msi.api.dao.DashboardsDao;
import com.envista.msi.api.rest.WebappTestEnvironment;
import com.envista.msi.api.web.rest.dto.dashboard.DashboardAppliedFilterDto;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;

import javax.inject.Inject;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Test class for the DashboardsDao DAO.
 *
 *
 */

public class DashboardsApiTest extends WebappTestEnvironment {


    @Before
    public void init() {
    }
    /*
        @Test
        public void testGetUserAppliedFilter() {
            dashboardsDao.getUserAppliedFilter(loginUserId);
        }
    */

    @Test
    public void testShipmentByRegion() throws Exception {
        mockRestMvc().perform(get("/api/dashboards/shipmentsByRegion")).andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE));

    }

    @Test
    public void testShipmentByRegionByCarrier() throws Exception {
        mockRestMvc().perform(get("/api/dashboards/shipmentRegionByCarrier")).andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE));

    }

    @Test
    public void testShipmentByRegionByMonth() throws Exception {
        mockRestMvc().perform(get("/api/dashboards/shipmentRegionByCarrier")).andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE));

    }



}