package com.envista.msi.api.rest.glom;

import com.envista.msi.api.rest.WebappTestEnvironment;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by KRISHNAREDDYM on 11/29/2017.
 */
public class DataCriteriaControllerTest extends WebappTestEnvironment {

    protected final Logger logger = LoggerFactory.getLogger(DataObjectControllerTest.class);

    private static final String SEARCH_API_BASE_PATH_VALUE = "/api/dataCriteria";

    @Test
    public void getByDataObjectId() throws Exception {
        MvcResult result = mockRestMvc().perform(get(SEARCH_API_BASE_PATH_VALUE + "/getByDataObjectId?dataObjectId=2")
        ).andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)).andReturn();

        logger.info("****getByDataObjectId result is****" + result.getResponse().getContentAsString());

        System.out.println("***getByDataObjectId result is***" + result.getResponse().getContentAsString());
    }
}
