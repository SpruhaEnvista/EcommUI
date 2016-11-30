package com.envista.msi.api.service.freight;

import java.math.BigDecimal;
import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.envista.msi.api.domain.freight.ShpNspInvoiceChargesTb;
import com.envista.msi.api.repository.freight.ShpNspInvoiceChargesTbRepository;

@Service
@Transactional
public class InvoiceChargesService {

	private final Logger log = LoggerFactory.getLogger(InvoiceChargesService.class);
	
	@Inject
	private ShpNspInvoiceChargesTbRepository shpNspInvoiceChargesTbRepository;

	public List<ShpNspInvoiceChargesTb> getAllByByInvoiceIdNatOrdered(long invoiceId) {
		return shpNspInvoiceChargesTbRepository.findAllByInvoiceIdNatOrdered(new BigDecimal(invoiceId));
	}
	
	public List<ShpNspInvoiceChargesTb> getAllByInvoiceIdOrderByXmlRatingSeqAsc(long invoiceId) {
		return shpNspInvoiceChargesTbRepository.findAllByInvoiceIdOrderByXmlRatingSeqAsc(new BigDecimal(invoiceId));
	}
	
		
}
