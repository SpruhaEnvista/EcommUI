package com.envista.msi.api.service.freight;

import java.math.BigDecimal;
import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.envista.msi.api.domain.freight.ShpNspInvChrgCustomTb;
import com.envista.msi.api.repository.freight.ShpNspInvChrgCustomTbRepository;

@Service
@Transactional
public class ShpNspInvChrgCustomService {
	
	private final Logger log = LoggerFactory.getLogger(ShpNspInvChrgCustomService.class);
	
	@Inject
	private ShpNspInvChrgCustomTbRepository shpNspInvChrgCustomTbRepository;
	
	public List<ShpNspInvChrgCustomTb> getInvoiceChargesCustomDetails(long invoiceId,long invoiceChargesId){
		return shpNspInvChrgCustomTbRepository.findByInvoiceIdAndNspInvoiceChargesId(new BigDecimal(invoiceId), new BigDecimal(invoiceChargesId));
	}
}
