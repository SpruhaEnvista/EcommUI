package com.envista.msi.api.rest.audit;

import com.envista.msi.api.rest.WebappTestEnvironment;
import org.junit.Test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by Sujit kumar on 06/07/2017.
 */
public class ParcelAuditTest extends WebappTestEnvironment {
    private static final String AUDIT_API_BASE_PATH_VALUE = "/api/parcel/rtr";

    @Test
    public void testUserProfileTbUsingProc() throws Exception {
        mockRestMvc().perform(get(AUDIT_API_BASE_PATH_VALUE)
                .param("fromDate", "30-MAY-2017").param("toDate", "31-MAY-2017").param("customerId", "18125")).andExpect(status().isOk());
    }
}
