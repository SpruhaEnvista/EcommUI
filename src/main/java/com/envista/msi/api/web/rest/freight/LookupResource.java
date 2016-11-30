package com.envista.msi.api.web.rest.freight;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.envista.msi.api.domain.freight.ShpLookupTb;
import com.envista.msi.api.repository.freight.ShpLookupTbRepository;
import com.envista.msi.api.service.freight.LookupService;


@RestController
@RequestMapping("/api/freight/lookupFilterDeltaAirportCodes")
public class LookupResource {

	private final Logger log = LoggerFactory.getLogger(LookupResource.class);
	
	@Inject
	private ShpLookupTbRepository shpLookupTbRepository;
	
	@Inject
	private LookupService lookupService;
	
	@RequestMapping(
	        method = RequestMethod.GET,
	        produces = MediaType.APPLICATION_JSON_VALUE)
	
	public List<ShpLookupTb> getDeltaAirportCodes() {
        log.debug("REST request to get all Users");
        System.out.println("Getting Delta Airport codes : ");
        try {
			return lookupService.getByModuleNameandCustomDefined8("DELTAAIRPORTCODES", "Y");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return null;
    }
}
