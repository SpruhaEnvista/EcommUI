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

import com.envista.msi.api.domain.freight.ShpNspCodeValuesTb;
import com.envista.msi.api.repository.freight.ShpNspCodeValuesTbRepository;
import com.envista.msi.api.service.freight.CodeValuesService;

@RestController
@RequestMapping("/api/freight/codeValuesDetails")
public class NspCodeValuesResource {

	private final Logger log = LoggerFactory.getLogger(NspCodeValuesResource.class);
	
	@Inject
	private ShpNspCodeValuesTbRepository shpNspCodeValuesTbRepository;
	
	@Inject
	private CodeValuesService codeValuesService;
	
	
	@RequestMapping(value = "{nspCodeGroupId}",
	        method = RequestMethod.GET,
	        produces = MediaType.APPLICATION_JSON_VALUE)
	
	public List<ShpNspCodeValuesTb> getNspCodeValuesList(@PathVariable Long nspCodeGroupId) {
		
		System.out.println("Nsp Code Group Id  is :"+nspCodeGroupId);
		try {
			return codeValuesService.getAllByShpNspCodeValueGroupsTb_NspCodeGroupIdAndActive(nspCodeGroupId, "Y");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	/*@RequestMapping(value = "/codeValue/{nspCodeValueId}",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	@Timed
	public List<ShpNspCodeValuesTb> getNspcodeValueDetails(@PathVariable Long nspcodevalueid) {
		
		System.out.println("Nsp Code Value Id is :"+nspcodevalueid);
		try{
			return codeValuesService.getByNspCodeValueId(nspcodevalueid);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}*/
}
