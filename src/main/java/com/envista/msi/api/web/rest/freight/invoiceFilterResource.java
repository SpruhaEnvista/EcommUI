package com.envista.msi.api.web.rest.freight;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.envista.msi.api.domain.freight.ShpNspInvoiceFilterTb;
import com.envista.msi.api.repository.freight.ShpNspInvoiceFilterTbRepository;
import com.envista.msi.api.service.freight.InvoiceFilterService;


@RestController
@RequestMapping("/api/freight/invoiceFilter")
public class invoiceFilterResource {

	private final Logger log = LoggerFactory.getLogger(invoiceFilterResource.class);
	
	@Inject
	private ShpNspInvoiceFilterTbRepository shpNspInvoiceFilterTbRepository;
	
	@Inject
	private InvoiceFilterService invoiceFilterService;
	
	@RequestMapping(value = "{customerId}",
	        method = RequestMethod.GET,
	        produces = MediaType.APPLICATION_JSON_VALUE)
	
	public List<ShpNspInvoiceFilterTb> getFilterDetailsByCustomerId(@PathVariable Long customerId) {
        log.debug("REST request to get all Users");
        System.out.println("customer Id is : " + customerId);
        try {
			return invoiceFilterService.getInvFilterByCustomerId(customerId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return null;
    }
}
