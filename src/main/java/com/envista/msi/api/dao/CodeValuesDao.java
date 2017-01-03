package com.envista.msi.api.dao;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.envista.msi.api.domain.PersistentContext;

@Service
@Transactional
public class CodeValuesDao {

	private final Logger log = LoggerFactory.getLogger(CodeValuesDao.class);
	
	@Inject
	private PersistentContext persistentContext;
	
	public List/*<ShpNspCodeValuesTb>*/ getAllByShpNspCodeValueGroupsTb_NspCodeGroupIdAndActive(long nspCodeGroupId,String active) {
		
		return null;/*shpNspCodeValuesTbRepository.findAllByShpNspCodeValueGroupsTb_NspCodeGroupIdAndActive(nspCodeGroupId,active)*/
	}
	
	public List getByNspCodeValueId(long nspcodevalueid) {
		
		return null;/*shpNspCodeValuesTbRepository.findByNspCodeValueId(nspcodevalueid);*/
	}

	public List getAllByShpNspCodeValueGroupsTb_NspCodeGroupId(long nspCodeGroupId) {
		
		return null;/*shpNspCodeValuesTbRepository.findAllByShpNspCodeValueGroupsTb_NspCodeGroupId(nspCodeGroupId);*/
	}
	
	public List getAllByShpNspCodeValueGroupsTb_CodeGroupName(String codeGroupName) {
		return null;
		
		/*List<ShpNspCodeValuesTb> shpCustomerProfileTblist = shpNspCodeValuesTbRepository.findAllByShpNspCodeValueGroupsTb_CodeGroupName(codeGroupName);
		
		return shpNspCodeValuesTbRepository.findAllByShpNspCodeValueGroupsTb_NspCodeGroupId(shpCustomerProfileTblist.get(0).getShpNspCodeValueGroupsTb().getNspCodeGroupId());*/
	}
}
