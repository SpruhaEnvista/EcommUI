package com.envista.msi.api.rest.invoiceLookup;

import static org.junit.Assert.assertFalse;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.UnsupportedEncodingException;

import org.junit.Test;
import org.springframework.http.MediaType;

import com.envista.msi.api.rest.WebappTestEnvironment;
import com.envista.msi.api.web.rest.dto.ApiException;
import com.envista.msi.api.web.rest.dto.SearchFilter;
import com.envista.msi.api.web.rest.dto.SearchResults;
import com.envista.msi.api.web.rest.util.JSONUtil;

/**
 * @author SANKER
 *
 */

public class SearchMetaTest extends WebappTestEnvironment {

	private static final String SEARCH_API_BASE_PATH_VALUE = "/api/search";
	private static final String META_API_BASE_PATH_VALUE = SEARCH_API_BASE_PATH_VALUE + "/meta";

	/**
	 * 
	 *
	 * Gets &#x60;UsserAssignedCustomersIdNamePairs&#x60; objects.
	 *
	 * @throws Exception
	 *             if the Api call fails
	 */
	@Test
	public void testAssignedCustomers() throws Exception {
		mockRestMvc().perform(get(META_API_BASE_PATH_VALUE + "/assinedcustomers")).andExpect(status().isOk());
		// TODO: test content validations
		/*
		 * .andExpect(content().contentType(MediaType.
		 * APPLICATION_JSON_UTF8_VALUE))
		 */
	}

	/**
	 * 
	 *
	 * Gets List of CarrierModes.
	 * 
	 * @throws Exception
	 *
	 * @throws Exception
	 *             if the Api call fails
	 */
	@Test
	public void testCarrierModes() throws Exception {
		mockRestMvc().perform(get(META_API_BASE_PATH_VALUE + "/carriermodes")).andExpect(status().isOk());
		// TODO: test content validations
		/*
		 * .andExpect(content().contentType(MediaType.
		 * APPLICATION_JSON_UTF8_VALUE))
		 */
	}

	/**
	 * 
	 *
	 * Gets &#x60;CarrierScacName&#x60; objects.
	 *
	 * @throws Exception
	 *             if the Api call fails
	 */
	@Test
	public void testCarrierScac() throws Exception {
		mockRestMvc().perform(get(META_API_BASE_PATH_VALUE + "/carrierscac{id}", 17980)).andExpect(status().isOk());
		// TODO: test content validations
		/*
		 * .andExpect(content().contentType(MediaType.
		 * APPLICATION_JSON_UTF8_VALUE))
		 */
	}

	/**
	 * 
	 *
	 * Gets allowed list of datecriteria objects for invoice search.
	 *
	 * @throws Exception
	 *             if the Api call fails
	 */
	@Test
	public void testDateCriteria() throws Exception {
		mockRestMvc().perform(get(META_API_BASE_PATH_VALUE + "/datecriteria")).andExpect(status().isOk());
		// TODO: test content validations
		/*
		 * .andExpect(content().contentType(MediaType.
		 * APPLICATION_JSON_UTF8_VALUE))
		 */
	}

	/**
	 * 
	 *
	 * Gets List of allowed invoicetypes.
	 *
	 * @throws ApiException
	 *             if the Api call fails
	 */
	@Test
	public void testFreightInvoiceTypes() throws Exception {
		mockRestMvc().perform(get(META_API_BASE_PATH_VALUE + "/freightinvoicetypes")).andExpect(status().isOk());
		// TODO: test content validations
		/*
		 * .andExpect(content().contentType(MediaType.
		 * APPLICATION_JSON_UTF8_VALUE))
		 */
	}

	/**
	 * 
	 *
	 * Gets List of Invoice Status Reports.
	 *
	 * @throws Exception
	 *             if the Api call fails
	 */
	@Test
	public void testInvoiceStatusReports() throws Exception {
		mockRestMvc().perform(get(META_API_BASE_PATH_VALUE + "/invoicestatusreports")).andExpect(status().isOk());
		// TODO: test content validations
		/*
		 * .andExpect(content().contentType(MediaType.
		 * APPLICATION_JSON_UTF8_VALUE))
		 */
	}

	/**
	 * 
	 *
	 * Gets List of Service Levels
	 *
	 * @throws Exception
	 *             if the Api call fails
	 */
	@Test
	public void testServicelevels() throws Exception {
		mockRestMvc().perform(get(META_API_BASE_PATH_VALUE + "/servicelevels")).andExpect(status().isOk());
		// TODO: test content validations
		/*
		 * .andExpect(content().contentType(MediaType.
		 * APPLICATION_JSON_UTF8_VALUE))
		 */
	}

	/**
	 * Returns list of invoices
	 * 
	 * @throws UnsupportedEncodingException
	 *
	 * @throws Exception
	 *             if the Api call fails
	 */
	@Test
	public void testFindInvoicesByFilters() throws UnsupportedEncodingException, Exception {
		SearchFilter body = new SearchFilter();
		// Todo add some filters to body

		/* MvcResult response */
		String responseContent = mockRestMvc()
				.perform(post(SEARCH_API_BASE_PATH_VALUE + "/findbyfilters").contentType(MediaType.APPLICATION_JSON)
						.content(JSONUtil.ConvertObject2JSON(body)))
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

		SearchResults searchResults = JSONUtil.ConvertJSON2Object(responseContent, SearchResults.class);
		assertFalse(searchResults.getPage() < 0);

		// TODO: test more validations

	}

}