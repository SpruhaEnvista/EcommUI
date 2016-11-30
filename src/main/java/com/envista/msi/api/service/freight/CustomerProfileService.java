package com.envista.msi.api.service.freight;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.envista.msi.api.domain.freight.ShpCustomerProfileTb;
import com.envista.msi.api.repository.freight.ShpCustomerProfileTbRepository;

@Service
@Transactional
public class CustomerProfileService {

	private final Logger log = LoggerFactory.getLogger(CustomerProfileService.class);
	
	@Inject
	private ShpCustomerProfileTbRepository shpCustomerProfileTbRepository;
	
	public ShpCustomerProfileTb getByCustomerProfileId(long customerId) {
		return shpCustomerProfileTbRepository.getByCustomerId(new Long(customerId));
	}
}
