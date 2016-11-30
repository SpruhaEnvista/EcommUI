package com.envista.msi.api.service.freight;

import java.math.BigDecimal;
import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.envista.msi.api.domain.freight.ShpNspInvDetailsCustomTb;
import com.envista.msi.api.repository.freight.ShpNspInvDetailsCustomTbRepository;

@Service
@Transactional
public class InvoiceDetailsCustomService {

	private final Logger log = LoggerFactory.getLogger(InvoiceDetailsCustomService.class);
	
	@Inject
	private ShpNspInvDetailsCustomTbRepository shpNspInvDetailsCustomTbRepository;
	
	public List<ShpNspInvDetailsCustomTb> getInvoiceCustomDetails(long invoiceId){
		return shpNspInvDetailsCustomTbRepository.findByInvoiceId(new BigDecimal(invoiceId));
	}
}
