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

import com.envista.msi.api.domain.freight.ShpNspInvoiceChargesTb;
import com.envista.msi.api.repository.freight.ShpNspInvoiceChargesTbRepository;
import com.envista.msi.api.service.freight.InvoiceChargesService;

@RestController
@RequestMapping("/api/freight/invoiceChargesList")
public class InvoiceChargesResource {

	private final Logger log = LoggerFactory.getLogger(InvoiceChargesResource.class);

	@Inject
	private ShpNspInvoiceChargesTbRepository shpNspInvoiceChargesTbRepository;

	@Inject
	private InvoiceChargesService invoiceChargesService;

	@RequestMapping(value = "{invoiceId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)

	public List<ShpNspInvoiceChargesTb> getInvoiceChargesList(@PathVariable Long invoiceId) {
		try {
			return invoiceChargesService.getAllByInvoiceIdOrderByXmlRatingSeqAsc(invoiceId);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return null;
	}
}
