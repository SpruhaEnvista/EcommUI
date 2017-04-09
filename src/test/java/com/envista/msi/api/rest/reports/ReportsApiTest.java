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
        mockRestMvc().perform(get(SEARCH_API_BASE_PATH_VALUE+"/results/updateexpirydate")
                .param("generatedRptId", "1247400").param("expiryDate", String.valueOf(System.currentTimeMillis()))).andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE));
    }
    @Test
    public void getReportForModes() throws  Exception {
        mockRestMvc().perform(get(SEARCH_API_BASE_PATH_VALUE+"/getModesReport")
                .param("userId", "23910")).andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE));
    }
    @Test
    public void getReportCustomers() throws  Exception {
        mockRestMvc().perform(get(SEARCH_API_BASE_PATH_VALUE+"/customercarrierlist")
                .param("rptId","197").param("userId","23910")).andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE));
    }
    @Test
    public void getReportFormat() throws  Exception{
        mockRestMvc().perform(get(SEARCH_API_BASE_PATH_VALUE+"/format")
                .param("rptId", "197")).andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE));
    }
    @Test
    public void getReportDateOptions() throws Exception {
        mockRestMvc().perform(get(SEARCH_API_BASE_PATH_VALUE+"/dateoptions")
                .param("rptId", "197")).andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE));
    }
    @Test
    public void getReportCriteria() throws Exception {
        mockRestMvc().perform(get(SEARCH_API_BASE_PATH_VALUE+"/criteriacolumn")
                .param("userId","23910").param("rptId", "197").param("carrierIds", " ")).andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE));
    }
    @Test
    public void getDefaultInclExclCol() throws Exception {
        mockRestMvc().perform(get(SEARCH_API_BASE_PATH_VALUE+"/defaultinclexclecol")
                .param("saveSchedId","12345").param("rptId", "197").param("createUser", "Siddhant")).andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE));
    }
}
