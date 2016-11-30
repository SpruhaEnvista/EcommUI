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

import com.envista.msi.api.domain.freight.ShpNspInvDetailsCustomTb;
import com.envista.msi.api.repository.freight.ShpNspInvDetailsCustomTbRepository;
import com.envista.msi.api.service.freight.InvoiceDetailsCustomService;

@RestController
@RequestMapping("/api/freight/invDetailsCustom")
public class InvoiceDetailsCustomResource {

	private final Logger log = LoggerFactory.getLogger(InvoiceDetailsCustomResource.class);
	
	@Inject
	private ShpNspInvDetailsCustomTbRepository shpNspInvDetailsCustomTbRepository;
	
	@Inject
	private InvoiceDetailsCustomService invoiceDetailsCustomService;
	
	/**
	 * GET - to get all custom records for that invoiceId
	 */
	@RequestMapping(value = "{invoiceId}",
	        method = RequestMethod.GET,
	        produces = MediaType.APPLICATION_JSON_VALUE)
	
	public List<ShpNspInvDetailsCustomTb> getInvoiceDetailsCustomData(@PathVariable Long invoiceId) {
        log.debug("REST request to get all Users");
        System.out.println("invoiceId: " + invoiceId);
        try {
			return invoiceDetailsCustomService.getInvoiceCustomDetails(invoiceId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return null;
    }
	    
}
