package com.envista.msi.api.service.freight;

import java.math.BigDecimal;
import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.envista.msi.api.domain.freight.ShpNspInvoiceFilterTb;
import com.envista.msi.api.repository.freight.ShpNspInvoiceFilterTbRepository;

@Service
@Transactional
public class InvoiceFilterService {
	
	private final Logger log = LoggerFactory.getLogger(InvoiceFilterService.class);
	
	@Inject
	private ShpNspInvoiceFilterTbRepository shpNspInvoiceFilterTbRepository;
	
	public List<ShpNspInvoiceFilterTb> getInvFilterByCustomerId(long customerId) {
		return shpNspInvoiceFilterTbRepository.findByStatusAndFilterGroupAndCustomerId(new BigDecimal(1), "auditrule", new BigDecimal(customerId));
	}
}
