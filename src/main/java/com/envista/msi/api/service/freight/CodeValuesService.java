package com.envista.msi.api.service.freight;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.envista.msi.api.domain.freight.ShpNspCodeValuesTb;
import com.envista.msi.api.repository.freight.ShpNspCodeValuesTbRepository;

@Service
@Transactional
public class CodeValuesService {

	private final Logger log = LoggerFactory.getLogger(CodeValuesService.class);
	
	@Inject
	private ShpNspCodeValuesTbRepository shpNspCodeValuesTbRepository;
	
	public List<ShpNspCodeValuesTb> getAllByShpNspCodeValueGroupsTb_NspCodeGroupIdAndActive(long nspCodeGroupId,String active) {
		
		return shpNspCodeValuesTbRepository.findAllByShpNspCodeValueGroupsTb_NspCodeGroupIdAndActive(nspCodeGroupId,active);
	}
	
	public List<ShpNspCodeValuesTb> getByNspCodeValueId(long nspcodevalueid) {
		
		return shpNspCodeValuesTbRepository.findByNspCodeValueId(nspcodevalueid);
	}

	public List<ShpNspCodeValuesTb> getAllByShpNspCodeValueGroupsTb_NspCodeGroupId(long nspCodeGroupId) {
		
		return shpNspCodeValuesTbRepository.findAllByShpNspCodeValueGroupsTb_NspCodeGroupId(nspCodeGroupId);
	}
	
	public List<ShpNspCodeValuesTb> getAllByShpNspCodeValueGroupsTb_CodeGroupName(String codeGroupName) {
		
		List<ShpNspCodeValuesTb> shpCustomerProfileTblist = shpNspCodeValuesTbRepository.findAllByShpNspCodeValueGroupsTb_CodeGroupName(codeGroupName);
		
		return shpNspCodeValuesTbRepository.findAllByShpNspCodeValueGroupsTb_NspCodeGroupId(shpCustomerProfileTblist.get(0).getShpNspCodeValueGroupsTb().getNspCodeGroupId());
	}
}
