package com.envista.msi.api.service.freight;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.envista.msi.api.domain.freight.ShpNspCustCarrierRelnTb;
import com.envista.msi.api.repository.freight.ShpNspCustCarrierRelnTbRepository;

@Service
@Transactional
public class CustCarrierRelationService {
	
	private final Logger log = LoggerFactory.getLogger(CustCarrierRelationService.class);
	
	@Inject
	private ShpNspCustCarrierRelnTbRepository shpNspCustCarrierRelnTbRepository;
	
	public List<ShpNspCustCarrierRelnTb> getCustCarrReln( Long customerId, Long carrierId){
		return shpNspCustCarrierRelnTbRepository.findAllByShpCustomerProfileTb_CustomerIdAndShpCarrierTb_CarrierId(customerId, carrierId);
	}
}
