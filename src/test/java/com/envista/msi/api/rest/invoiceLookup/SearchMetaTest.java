package com.envista.msi.api.rest.invoiceLookup;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.springframework.http.MediaType;

import com.envista.msi.api.rest.WebappTestEnvironment;
import com.envista.msi.api.web.rest.dto.SearchCriteria;

/**
 * @author SANKER
 *
 */

public class SearchMetaTest extends WebappTestEnvironment {

	private static final String SEARCH_API_BASE_PATH_VALUE = "/api/search";
	private static final String META_API_BASE_PATH_VALUE = SEARCH_API_BASE_PATH_VALUE + "/meta";
	private static final String RESULTS_API_BASE_PATH_VALUE = SEARCH_API_BASE_PATH_VALUE; //SEARCH_API_BASE_PATH_VALUE + "/results";;

	@Test
	public void testAssignedCustomers() throws Exception {
		mockRestMvc().perform(get(META_API_BASE_PATH_VALUE + "/staticdata")).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON));
	}

	@Test
	public void testCarrierScacNames() throws Exception {
		mockRestMvc().perform(get(META_API_BASE_PATH_VALUE + "/carriers/{id}", 17980)).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON));
	}

	@Test
	public void testResultsBySearchCriteria() throws Exception {
		SearchCriteria criteria = new SearchCriteria();
		// "nspInvoiceDetailsId":8318510,
		criteria.setSearchInvoiceId("8318510");
		// criteria.setCustomerId(customerId);
		mockRestMvc()
				.perform(post(RESULTS_API_BASE_PATH_VALUE + "/pageBySearchCriteria").param("pageNumber", "1")
						.param("numberOfElements", "4").contentType(MediaType.APPLICATION_JSON)
						.content(this.json(criteria)))
				.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON));
	}

	@Test
	public void testResultsBySearchCriteriaAndSortParam() throws Exception {
		SearchCriteria criteria = new SearchCriteria();
		// "nspInvoiceDetailsId":8318510,
		// criteria.setSearchInvoiceId("8318510");
		// criteria.setCustomerId(customerId);
		mockRestMvc()
				.perform(post(RESULTS_API_BASE_PATH_VALUE + "/pageBySearchCriteria").param("pageNumber", "1")
						.param("numberOfElements", "4").param("sortColumn", "invoiceDueDate").param("sortOrder", "DESC")
						.contentType(MediaType.APPLICATION_JSON).content(this.json(criteria)))
				.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON));
	}
}