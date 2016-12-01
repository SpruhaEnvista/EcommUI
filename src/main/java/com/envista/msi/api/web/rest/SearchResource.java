/**
 * 
 */
package com.envista.msi.api.web.rest;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.envista.msi.api.security.SecurityUtils;
import com.envista.msi.api.service.SearchService;
import com.envista.msi.api.web.rest.dto.CarrierScacName;
import com.envista.msi.api.web.rest.dto.InvoiceDetails;
import com.envista.msi.api.web.rest.dto.SearchCriteria;
import com.envista.msi.api.web.rest.dto.SearchMetadata;

/**
 * @author SANKER
 *
 */
@RestController
@RequestMapping("/api/search/")
public class SearchResource {

	@Autowired
	private SearchService searchService;

	private final Logger log = LoggerFactory.getLogger(SearchResource.class);

	@RequestMapping(value = "/meta/staticdata", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<SearchMetadata> getAssignedCustomers() {
		try {
			return new ResponseEntity<SearchMetadata>(
					searchService.getStaticSearchMetadata(SecurityUtils.getCurrentUserLogin()), HttpStatus.OK);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return new ResponseEntity<SearchMetadata>(new SearchMetadata(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(value = "/meta/carriers/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<CarrierScacName>> getCarrierScacNames(@PathVariable("id") Long customerId) {
		try {
			return new ResponseEntity<List<CarrierScacName>>(searchService.getCarrierScac(customerId), HttpStatus.OK);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return new ResponseEntity<List<CarrierScacName>>(new ArrayList<CarrierScacName>(),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * get invoicedetails for given criteria
	 */
	@RequestMapping(value = "/pageBySearchCriteria", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public Page<InvoiceDetails> getPageBySearchCriteria(@RequestBody SearchCriteria criteria,
			@RequestParam(value = "pageNumber", required = false) Integer pageNumber,
			@RequestParam(value = "numberOfElements", required = false) Integer numberOfElements,
			@RequestParam(value = "sortColumn", required = false) String sortColumn,
			@RequestParam(value = "sortOrder", required = false) String sortOrder) {
		// log.debug("REST request to get all Users");

		try {
			return searchService.findInvoiceDetails(pageNumber == null ? 1 : pageNumber,
					numberOfElements == null ? 20 : numberOfElements, sortColumn, sortOrder, criteria);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return null;
		}
	}
}
