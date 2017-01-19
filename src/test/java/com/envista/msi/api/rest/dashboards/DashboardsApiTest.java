package com.envista.msi.api.rest.dashboards;

import com.envista.msi.api.rest.WebappTestEnvironment;
import org.junit.Test;
import org.springframework.http.MediaType;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by Sarvesh on 1/19/2017.
 */
public class DashboardsApiTest extends WebappTestEnvironment {

    private static final String SEARCH_API_BASE_PATH_VALUE = "/api/dashboards";

    @Test
    public void testGetUserAppliedFilter() throws Exception {
        mockRestMvc().perform(get(SEARCH_API_BASE_PATH_VALUE + "/appliedFilter").param("userId","23166")).andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE));
    }

}
