package com.envista.msi.api.rest.glom;

import com.envista.msi.api.rest.WebappTestEnvironment;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by KRISHNAREDDYM on 11/29/2017.
 */
public class DataObjectControllerTest extends WebappTestEnvironment {

    protected final Logger logger = LoggerFactory.getLogger(DataObjectControllerTest.class);

    private static final String SEARCH_API_BASE_PATH_VALUE = "/api/dataObject";

    @Test
    public void getAll() throws Exception {
        MvcResult result = mockRestMvc().perform(get(SEARCH_API_BASE_PATH_VALUE + "/getAll")
        ).andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)).andReturn();

        logger.info("****getAllTest result is****" + result.getResponse().getContentAsString());

        System.out.println("***getAllTest result is***" + result.getResponse().getContentAsString());
    }

    @Test
    public void insert() throws Exception {

        String inputJson = "{\"dataObjectName\":\"TestObj2\",\"description\":\"testdesc2\",\"userId\":23,\"criteriaDtos\":[{\"CODE_VALUE_ID\":12,\"columnName\":\"Invoice Number\"" +
                ",\"criOperator\":\">=\",\"andOrCondition\":\"and\",\"criValue\":\"inv123\"}]}";


        MvcResult result = mockRestMvc().perform(post(SEARCH_API_BASE_PATH_VALUE + "/insert").content(inputJson).contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk()).andReturn();


        logger.info("****insert result is****" + result.getResponse().getContentAsString());

        System.out.println("***insert result is***" + result.getResponse().getContentAsString());
    }

    @Test
    public void update() throws Exception {

        String inputJson = "{\"dataObjectId\":4,\"dataObjectName\":\"TestObj2update\",\"description\":\"testdesc2update\",\"userId\":21,\"criteriaDtos\":[{\"CODE_VALUE_ID\":13,\"columnName\":\"Invoice Number\"" +
                ",\"criOperator\":\">=\",\"andOrCondition\":\"and\",\"criValue\":\"inv123568\"}]}";


        MvcResult result = mockRestMvc().perform(put(SEARCH_API_BASE_PATH_VALUE + "/update").content(inputJson).contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk()).andReturn();


        logger.info("****update result is****" + result.getResponse().getContentAsString());

        System.out.println("***update result is***" + result.getResponse().getContentAsString());
    }
}
