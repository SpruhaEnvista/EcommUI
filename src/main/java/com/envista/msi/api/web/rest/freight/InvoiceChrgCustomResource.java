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

import com.envista.msi.api.domain.freight.ShpNspInvChrgCustomTb;
import com.envista.msi.api.repository.freight.ShpNspInvChrgCustomTbRepository;
import com.envista.msi.api.service.freight.ShpNspInvChrgCustomService;

@RestController
@RequestMapping("/api/freight/invChrgCustomDetails")
public class InvoiceChrgCustomResource {

	private final Logger log = LoggerFactory.getLogger(InvoiceChrgCustomResource.class);
	
	@Inject
	private ShpNspInvChrgCustomTbRepository shpNspInvChrgCustomTbRepository;
	
	@Inject
	private ShpNspInvChrgCustomService shpNspInvChrgCustomService;
	
	/**
	 * GET - to get all custom records for that charges id
	 */
	@RequestMapping(value = "{invoiceId}/{invoiceChargesId}",
	        method = RequestMethod.GET,
	        produces = MediaType.APPLICATION_JSON_VALUE)
	
	public List<ShpNspInvChrgCustomTb> getInvoiceChrgCustomDetails(@PathVariable Long invoiceId,@PathVariable Long invoiceChargesId) {
        log.debug("REST request to get all Users");
        System.out.println("invoiceId is : " + invoiceId);
        System.out.println("invoiceChargesId is : "+invoiceChargesId);
        try {
			return shpNspInvChrgCustomService.getInvoiceChargesCustomDetails(invoiceId, invoiceChargesId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return null;
    }
}
