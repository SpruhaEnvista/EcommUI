package com.envista.msi.api.web.rest.freight;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.envista.msi.api.domain.freight.ShpNspInvoiceDetailsTb;
import com.envista.msi.api.repository.freight.ShpNspInvoiceDetailsTbRepository;
import com.envista.msi.api.service.freight.InvoiceDetailService;

/**
 * REST controller for managing the current user's account.
 */
@RestController
@RequestMapping("/api/freight/invdetail")
public class InvoiceDetailResource {

	private final Logger log = LoggerFactory.getLogger(InvoiceDetailResource.class);

	@Inject
	private InvoiceDetailService invoiceDetailService;

	@Inject
	private ShpNspInvoiceDetailsTbRepository shpNspInvoiceDetailsTbRepository;

	/**
	 * GET invoicedetails for given invoice
	 */
	@RequestMapping(value = "{invoiceId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)

	// @RolesAllowed(AuthoritiesConstants.ADMIN)
	public ShpNspInvoiceDetailsTb get(@PathVariable Long invoiceId) {
		log.debug("REST request to get all Users");
		System.out.println("invoiceId: " + invoiceId);
		try {
			return invoiceDetailService.getInvoiceDetails(invoiceId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * GET invoicedetails for all invoice
	 */
	@RequestMapping(value = "/get/pagebytotal", method = { RequestMethod.GET,
			RequestMethod.POST }, produces = MediaType.APPLICATION_JSON_VALUE)

	// @RolesAllowed(AuthoritiesConstants.ADMIN)
	public List<ShpNspInvoiceDetailsTb> getAllByPage(@RequestParam int pageNumber, @RequestParam int totalRecords) {
		log.debug("REST request to get all Users");
		System.out.println("invoiceId: getAll");
		try {
			return invoiceDetailService.getInvoiceDetails(pageNumber, totalRecords);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
