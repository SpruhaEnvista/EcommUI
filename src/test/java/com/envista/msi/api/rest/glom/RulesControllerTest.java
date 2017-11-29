package com.envista.msi.api.rest.glom;

import com.envista.msi.api.rest.WebappTestEnvironment;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by KRISHNAREDDYM on 11/22/2017.
 */
public class RulesControllerTest extends WebappTestEnvironment {

    protected final Logger logger = LoggerFactory.getLogger(RulesControllerTest.class);

    private static final String SEARCH_API_BASE_PATH_VALUE = "/api/rules";

    @Test
    public void getAll() throws Exception {
        MvcResult result = mockRestMvc().perform(get(SEARCH_API_BASE_PATH_VALUE + "/getAll?scriptId=143")
        ).andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)).andReturn();

        logger.info("****getAllTest result is****" + result.getResponse().getContentAsString());

        //System.out.println("***getAllTest result is***" + result.getResponse().getContentAsString());
    }

    @Test
    public void getById() throws Exception {
        MvcResult result = mockRestMvc().perform(get(SEARCH_API_BASE_PATH_VALUE + "/2")
        ).andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)).andReturn();

        logger.info("****getById result is****" + result.getResponse().getContentAsString());

        //System.out.println("***getById result is***" + result.getResponse().getContentAsString());
    }


    @Test
    public void insert() throws Exception {

        String inputJson = "{\"ruleId\":2,\"scriptId\":142,\"ruleName\":\"testing from junit\",\"dataObjectId\":-1,\"sequence\":1,\"scope\":null,\"identity\":null" +
                ",\"comments\":\"Test Comments\",\"action\":null,\"column1\":null,\"column2\":null,\"column3\":null,\"column4\":null,\"column5\":null,\"column6\":null" +
                ",\"column7\":null,\"column8\":null,\"column9\":null,\"column10\":null,\"isActive\":1}";


        MvcResult result = mockRestMvc().perform(post(SEARCH_API_BASE_PATH_VALUE + "/insert").content(inputJson).contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk()).andReturn();


        logger.info("****insert result is****" + result.getResponse().getContentAsString());

        //System.out.println("***insert result is***" + result.getResponse().getContentAsString());
    }

    @Test
    public void update() throws Exception {

        String inputJson = "{\"ruleId\":2,\"scriptId\":142,\"ruleName\":\"testing from junit update\",\"dataObjectId\":-1,\"sequence\":1,\"scope\":null,\"identity\":null" +
                ",\"comments\":\"Test Comments update\",\"action\":null,\"column1\":null,\"column2\":null,\"column3\":null,\"column4\":null,\"column5\":null,\"column6\":null" +
                ",\"column7\":null,\"column8\":null,\"column9\":null,\"column10\":null,\"isActive\":1}";


        MvcResult result = mockRestMvc().perform(put(SEARCH_API_BASE_PATH_VALUE + "/update").content(inputJson).contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk()).andReturn();


        logger.info("****update result is****" + result.getResponse().getContentAsString());

        //System.out.println("***update result is***" + result.getResponse().getContentAsString());
    }

    @Test
    public void findByRuleName() throws Exception {

        MvcResult result = mockRestMvc().perform(get(SEARCH_API_BASE_PATH_VALUE + "/findByRuleName?ruleName=System Generated&prevRuleName=null&scriptId=143")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn();


        logger.info("****findByRuleName result is****" + result.getResponse().getContentAsString());

        //System.out.println("***findByRuleName result is***" + result.getResponse().getContentAsString());
    }

    @Test
    public void insertAbove() throws Exception {

        String inputJson = "{\"ruleId\":6,\"scriptId\":142,\"ruleName\":\"testing from junit INSERT ABOVE\",\"dataObjectId\":-1,\"sequence\":1,\"scope\":null,\"identity\":null" +
                ",\"comments\":\"Test Comments INSERT ABOVE\",\"action\":null,\"column1\":null,\"column2\":null,\"column3\":null,\"column4\":null,\"column5\":null,\"column6\":null" +
                ",\"column7\":null,\"column8\":null,\"column9\":null,\"column10\":null,\"isActive\":1,\"insertType\":\"INSERTABOVE\"}";


        MvcResult result = mockRestMvc().perform(post(SEARCH_API_BASE_PATH_VALUE + "/insert").content(inputJson).contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk()).andReturn();


        logger.info("****insertAbove result is****" + result.getResponse().getContentAsString());

        //System.out.println("***insertAbove result is***" + result.getResponse().getContentAsString());
    }

    @Test
    public void insertBelow() throws Exception {

        String inputJson = "{\"ruleId\":12,\"scriptId\":142,\"ruleName\":\"testing from junit INSERT BELOW\",\"dataObjectId\":-1,\"sequence\":1,\"scope\":null,\"identity\":null" +
                ",\"comments\":\"Test Comments INSERT BELOW\",\"action\":null,\"column1\":null,\"column2\":null,\"column3\":null,\"column4\":null,\"column5\":null,\"column6\":null" +
                ",\"column7\":null,\"column8\":null,\"column9\":null,\"column10\":null,\"isActive\":1,\"insertType\":\"INSERTBELOW\"}";


        MvcResult result = mockRestMvc().perform(post(SEARCH_API_BASE_PATH_VALUE + "/insert").content(inputJson).contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk()).andReturn();


        logger.info("****insertBelow result is****" + result.getResponse().getContentAsString());

        //System.out.println("***insertBelow result is***" + result.getResponse().getContentAsString());
    }
}
