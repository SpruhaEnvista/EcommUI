package com.envista.msi.api.rest.reports;

import com.envista.msi.api.rest.WebappTestEnvironment;
import org.junit.Test;
import org.springframework.http.MediaType;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


/**
 * Created by Sreenivas on 2/17/2017.
 */
public class ReportsApiTest extends WebappTestEnvironment {

    private static final String SEARCH_API_BASE_PATH_VALUE = "/api/reports";

    @Test
    public void testReportResultsByUserId() throws Exception {
        mockRestMvc().perform(get(SEARCH_API_BASE_PATH_VALUE+"/results/reportslist/{userId}" , 23910)).andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE));
    }
    @Test
    public void updateExpiryDate() throws Exception {
        mockRestMvc().perform(get(SEARCH_API_BASE_PATH_VALUE + "/results/updateexpirydate")
                .param("generatedRptId", "1247400").param("expiryDate", String.valueOf(System.currentTimeMillis()))).andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE));
    }
}
