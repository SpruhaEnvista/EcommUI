package com.envista.msi.api.service.freight;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.envista.msi.api.domain.freight.ShpCarrierTb;
import com.envista.msi.api.repository.freight.ShpCarrierTbRepository;

@Service
@Transactional
public class CarrierService {
	
	private final Logger log = LoggerFactory.getLogger(CarrierService.class);
	
	@Inject
	private ShpCarrierTbRepository shpCarrierTbRepository;
	
	public ShpCarrierTb getBycarrierId(long carrierId) throws Exception {
		
		return shpCarrierTbRepository.findOne(carrierId);
	}
}
