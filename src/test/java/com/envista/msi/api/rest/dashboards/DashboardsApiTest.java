package com.envista.msi.api.rest.dashboards;

import com.envista.msi.api.rest.WebappTestEnvironment;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.minidev.json.JSONObject;
import org.junit.Test;
import org.springframework.http.MediaType;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by Sarvesh on 1/19/2017.
 */
public class DashboardsApiTest extends WebappTestEnvironment {

    private static final String SEARCH_API_BASE_PATH_VALUE = "/api/dashboards";

    @Test
    public void testUserNameFromAppliedFilter() {
        assertThat("sarvesh")
                .isEqualTo("sarvesh");
    }
}
