package com.envista.msi.api.rest.dashboards;

import com.envista.msi.api.rest.WebappTestEnvironment;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.minidev.json.JSONObject;
import org.junit.Test;
import org.springframework.http.MediaType;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by Sarvesh on 1/19/2017.
 */
public class DashboardsApiTest extends WebappTestEnvironment {

    private static final String SEARCH_API_BASE_PATH_VALUE = "/api/dashboards";

   /* @Test
    public void testGetUserAppliedFilter() throws Exception {
        mockRestMvc().perform(get(SEARCH_API_BASE_PATH_VALUE + "/appliedFilter").param("userId","23166")).andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE));

        System.out.println("Output:"+get(SEARCH_API_BASE_PATH_VALUE + "/appliedFilter").param("userId","23166").toString());
    }*/

    @Test
    public void testGetNetSpendByMode() throws Exception {
        mockRestMvc().perform(get(SEARCH_API_BASE_PATH_VALUE + "/netSpendByMode").param("dateType", "INVOICE_DATE")/*.param("convertCurrencyId", "0")
                .param("convertCurrencyCode", "").param("customerIdsCSV", "17980")
                .param("carriers", "23,101301,100679,22,21,87273").param("modes", "")
                .param("services", "").param("lanes", "")
                .param("accessorialName", "").param("fromDate", "1-JAN-2015")
                .param("toDate", "23-DEC-2015")*/).andExpect(status().isOk());

        /*mockRestMvc().perform(get(SEARCH_API_BASE_PATH_VALUE + "/netSpendByMode").param("dateType", "INVOICE_DATE")).andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE));*/

        /*JSONObject params = new JSONObject();
        params.put("dateType", "INVOICE_DATE");
        params.put("convertCurrencyId", "0");
        params.put("convertCurrencyCode", "");
        params.put("customerIdsCSV", "17980");
        params.put("carriers", "23,101301,100679,22,21,87273");
        params.put("modes", "");
        params.put("services", "");
        params.put("fromDate", "1-JAN-2015");
        params.put("toDate", "23-DEC-2015");

        mockRestMvc().perform(post(SEARCH_API_BASE_PATH_VALUE + "/netSpendByMode").contentType(MediaType.APPLICATION_JSON)
        .content(asJsonString(params))).andExpect(status().isOk()).andExpect(content().contentType(MediaType.TEXT_HTML_VALUE));*/
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
