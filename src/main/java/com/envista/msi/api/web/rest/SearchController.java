/**
 * 
 */
package com.envista.msi.api.web.rest;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.envista.msi.api.service.SearchService;
import com.envista.msi.api.web.rest.dto.CarrierScacName;
import com.envista.msi.api.web.rest.dto.InvoiceDetails;
import com.envista.msi.api.web.rest.dto.SearchFilter;
import com.envista.msi.api.web.rest.dto.SearchResults;
import com.envista.msi.api.web.rest.dto.UserAssignedCustomer;

/**
 * @author SANKER
 *
 */
@RestController
@RequestMapping("/api/search/")
public class SearchController {

	@Autowired
	private SearchService searchService;

	private final Logger log = LoggerFactory.getLogger(SearchController.class);

	@RequestMapping(value = "/meta/assinedcustomers", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<UserAssignedCustomer>> getAssinedCustomers() {
		// do some magic!
		return new ResponseEntity<List<UserAssignedCustomer>>(HttpStatus.OK);
	}

	@RequestMapping(value = "/meta/carriermodes", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<List<String>> getCarrierModes() {
		// do some magic!
		return new ResponseEntity<List<String>>(HttpStatus.OK);
	}

	@RequestMapping(value = "/meta/carrierscac{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<List<CarrierScacName>> getCarrierScac(@PathVariable("id") Long customerId) {
		return new ResponseEntity<List<CarrierScacName>>(HttpStatus.OK);
	}

	@RequestMapping(value = "/meta/datecriteria", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<List<String>> getDatecriteria() {
		return new ResponseEntity<List<String>>(HttpStatus.OK);
	}

	@RequestMapping(value = "/meta/freightinvoicetypes", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<List<String>> getFreightInvoiceTypes() {
		return new ResponseEntity<List<String>>(HttpStatus.OK);
	}

	@RequestMapping(value = "/meta/invoicestatusreports", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<List<String>> getInvoiceStatusReports() {
		return new ResponseEntity<List<String>>(HttpStatus.OK);
	}

	@RequestMapping(value = "/meta/servicelevels", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<List<String>> getServicelevels() {
		return new ResponseEntity<List<String>>(HttpStatus.OK);
	}

	@RequestMapping(value = {"/findByFilters", "/findbyfilters"}, produces = { "application/json" }, consumes = {
			"application/json" }, method = RequestMethod.POST)
	ResponseEntity<SearchResults> findInvoicesByFilters(@RequestBody SearchFilter body) {
		SearchResults empty = new SearchResults();
		empty.page(1l);
		empty.setInvoices(new ArrayList<InvoiceDetails>());
		return new ResponseEntity<SearchResults>(empty, HttpStatus.OK);
	}
}
