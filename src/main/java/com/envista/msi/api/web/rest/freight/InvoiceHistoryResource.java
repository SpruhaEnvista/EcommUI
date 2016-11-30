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

import com.envista.msi.api.domain.freight.ShpNspInvoiceHistoryTb;
import com.envista.msi.api.repository.freight.ShpNspInvoiceHistoryTbRepository;
import com.envista.msi.api.service.freight.InvoiceHistoryService;

@RestController
@RequestMapping("/api/freight/invoiceHistoryDetails")
public class InvoiceHistoryResource {

	private final Logger log = LoggerFactory.getLogger(InvoiceHistoryResource.class);
	
	@Inject
	private ShpNspInvoiceHistoryTbRepository shpNspInvoiceHistoryTbRepository;
	
	@Inject
	private InvoiceHistoryService invoiceHistoryService;
	
	@RequestMapping(value = "{invoiceId}",
	        method = RequestMethod.GET,
	        produces = MediaType.APPLICATION_JSON_VALUE)
	
	 public List<ShpNspInvoiceHistoryTb> getInvoiceHistoryListByInvoiceId(@PathVariable Long invoiceId) throws Exception {
			
			log.debug("REST request to get Invoice History Details ");
	        System.out.println("Invoice Id: " + invoiceId);
	        try {
				return invoiceHistoryService.getHistoryDetailsByInvoiceId(invoiceId);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        return null;
		}
}
