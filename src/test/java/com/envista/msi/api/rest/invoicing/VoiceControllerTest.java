package com.envista.msi.api.rest.invoicing;

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
 * Created by KRISHNAREDDYM on 4/13/2017.
 */

public class VoiceControllerTest  extends WebappTestEnvironment {

    protected final Logger logger = LoggerFactory.getLogger(VoiceControllerTest.class);

    private static final String SEARCH_API_BASE_PATH_VALUE = "/api/voice";

    @Test
    public void getVoices() throws Exception {
        MvcResult result = mockRestMvc().perform(get(SEARCH_API_BASE_PATH_VALUE + "/getVoices")
                ).andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)).andReturn();

        logger.info("****getVoiceListTest result is****" + result.getResponse().getContentAsString());

        //System.out.println("***getVoiceListTest result is***" + result.getResponse().getContentAsString());
    }
}
