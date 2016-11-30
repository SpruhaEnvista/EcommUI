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

import com.envista.msi.api.domain.freight.ShpNspCustCarrierRelnTb;
import com.envista.msi.api.repository.freight.ShpCustColDefnTbRepository;
import com.envista.msi.api.service.freight.CustCarrierRelationService;

@RestController
@RequestMapping("/api/freight/custCarrierReln")
public class CustomerCarrierRelnResource {

	private final Logger log = LoggerFactory.getLogger(CustomerCarrierRelnResource.class);
	
	@Inject
	private ShpCustColDefnTbRepository shpCustColDefnTbRepository;
	
	@Inject
	private CustCarrierRelationService custCarrierRelationService;
	
	@RequestMapping(value = "{customerId}/{carrierId}",
	        method = RequestMethod.GET,
	        produces = MediaType.APPLICATION_JSON_VALUE)
	
	public List<ShpNspCustCarrierRelnTb> getCustCarrierRelnList(@PathVariable Long customerId,@PathVariable Long carrierId) {
		
		 log.debug("REST request to get all Users");
	        System.out.println("customer Id is : " + customerId);
	        System.out.println("carrier Id is : "+carrierId);
	        try {
				return custCarrierRelationService.getCustCarrReln(customerId,carrierId);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        return null;
	}
}
