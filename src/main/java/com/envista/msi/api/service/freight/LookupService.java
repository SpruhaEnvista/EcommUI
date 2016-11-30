package com.envista.msi.api.service.freight;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.envista.msi.api.domain.freight.ShpLookupTb;
import com.envista.msi.api.repository.freight.ShpLookupTbRepository;

@Service
@Transactional
public class LookupService {

	private final Logger log = LoggerFactory.getLogger(LookupService.class);
	
	@Inject
	private ShpLookupTbRepository shpLookupTbRepository;
	
	public List<ShpLookupTb> getByModuleNameandCustomDefined8(String moduleName,String customDefined8) {
		return shpLookupTbRepository.findByModuleNameAndCustomDefined8(moduleName, customDefined8);// module_name = 'DELTAAIRPORTCODES',   custom_defined_8 = 'Y'
	}
	
	
}
