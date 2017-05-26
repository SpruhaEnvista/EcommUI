package com.envista.msi.api.rest.invoicing;

import com.envista.msi.api.rest.WebappTestEnvironment;
import org.junit.Test;
import org.springframework.http.MediaType;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by admin on 4/13/2017.
 */

public class VoiceControllerTest  extends WebappTestEnvironment {

    private static final String SEARCH_API_BASE_PATH_VALUE = "/api/voice";

    /*@Test
    public void getVoices() throws Exception {
        mockRestMvc().perform(get(SEARCH_API_BASE_PATH_VALUE + "/getVoices?access_token=c121c5be-baf3-41fa-9d15-399030bcd4e5")
                ).andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE));
    }*/
}
