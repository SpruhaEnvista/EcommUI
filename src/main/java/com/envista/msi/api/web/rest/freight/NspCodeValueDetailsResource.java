package com.envista.msi.api.web.rest.freight;

import java.util.ArrayList;
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
@RequestMapping("/api/freight/codeValues")
public class NspCodeValueDetailsResource {

private final Logger log = LoggerFactory.getLogger(NspCodeValueDetailsResource.class);
	
	@Inject
	private ShpNspCodeValuesTbRepository shpNspCodeValuesTbRepository;
	
	@Inject
	private CodeValuesService codeValuesService;
	
	@RequestMapping(value = "{nspcodevalueid}",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	
	public List<ShpNspCodeValuesTb> getNspcodeValueDetails(@PathVariable Long nspcodevalueid) {
		
		System.out.println("Nsp Code Value Id is :"+nspcodevalueid);
		try{
			return codeValuesService.getByNspCodeValueId(nspcodevalueid);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@RequestMapping(value = "codeGroups/{codeGroupName}",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public List<String> getCodeValuesByCodeGroupId(@PathVariable String codeGroupName) {
		ArrayList<String> codeValues = new ArrayList<String>();
		List<ShpNspCodeValuesTb> shpCustomerProfileTblist = codeValuesService.getAllByShpNspCodeValueGroupsTb_CodeGroupName(codeGroupName);
		
		for (ShpNspCodeValuesTb shpNspCodeValuesTb : shpCustomerProfileTblist) {
			codeValues.add(shpNspCodeValuesTb.getCodeValue());
		}
		
		return codeValues;
	
	
	
	}
		
}
	
	




