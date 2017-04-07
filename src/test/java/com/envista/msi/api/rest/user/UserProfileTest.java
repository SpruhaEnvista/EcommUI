package com.envista.msi.api.rest.user;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.springframework.http.MediaType;

import com.envista.msi.api.rest.WebappTestEnvironment;

/**
 * @author SANKER
 *
 */

public class UserProfileTest extends WebappTestEnvironment {

    @Test
    public void testUserProfile() throws Exception {
        mockRestMvc().perform(get("/api/user/profile").param("id", "admin")).andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE));
    }
}