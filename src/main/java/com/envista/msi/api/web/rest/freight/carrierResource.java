package com.envista.msi.api.web.rest.freight;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.envista.msi.api.domain.freight.ShpCarrierTb;
import com.envista.msi.api.repository.freight.ShpCarrierTbRepository;
import com.envista.msi.api.service.freight.CarrierService;

@RestController
@RequestMapping("/api/carrier")
public class carrierResource {

	private final Logger log = LoggerFactory.getLogger(carrierResource.class);

	@Inject
	private ShpCarrierTbRepository shpCarrierTbRepository;

	@Inject
	private CarrierService carrierService;

	@RequestMapping(value = "{carrierId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)

	public ShpCarrierTb getListByCarrierId(@PathVariable Long carrierId) throws Exception {

		log.debug("REST request to get all Users");
		try {
			return carrierService.getBycarrierId(carrierId);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return null;
	}
}
