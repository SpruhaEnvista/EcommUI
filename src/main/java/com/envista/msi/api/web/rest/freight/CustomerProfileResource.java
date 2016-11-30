package com.envista.msi.api.web.rest.freight;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.envista.msi.api.domain.freight.ShpCustomerProfileTb;
import com.envista.msi.api.repository.freight.ShpCustomerProfileTbRepository;
import com.envista.msi.api.service.freight.CustomerProfileService;

@RestController
@RequestMapping("/api/freight/customerProfile")
public class CustomerProfileResource {

	private final Logger log = LoggerFactory.getLogger(CustomerProfileResource.class);

	@Inject
	private ShpCustomerProfileTbRepository shpCustomerProfileTbRepository;

	@Inject
	private CustomerProfileService customerProfileService;

	@RequestMapping(value = "{customerId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ShpCustomerProfileTb getCustomerDetailsByCustomerId(@PathVariable Long customerId) {
		log.debug("REST request to get all Users");
		try {
			return customerProfileService.getByCustomerProfileId(customerId);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return null;
	}
}
