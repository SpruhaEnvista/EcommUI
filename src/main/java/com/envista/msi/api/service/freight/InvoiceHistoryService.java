package com.envista.msi.api.service.freight;

import java.math.BigDecimal;
import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.envista.msi.api.domain.freight.ShpNspInvoiceHistoryTb;
import com.envista.msi.api.repository.freight.ShpNspInvoiceHistoryTbRepository;

@Service
@Transactional
public class InvoiceHistoryService {

	private final Logger log = LoggerFactory.getLogger(InvoiceHistoryService.class);
	
	@Inject
	private ShpNspInvoiceHistoryTbRepository shpNspInvoiceHistoryTbRepository;
	
	public List<ShpNspInvoiceHistoryTb> getHistoryDetailsByInvoiceId (long invoiceId) throws Exception {
		
		return shpNspInvoiceHistoryTbRepository.findByInvoiceId(new BigDecimal(invoiceId));
	}
}
