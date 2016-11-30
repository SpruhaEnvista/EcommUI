package com.envista.msi.api.service.freight;

import java.math.BigDecimal;
import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.envista.msi.api.domain.freight.ShpNspAwbDtlTb;
import com.envista.msi.api.repository.freight.ShpNspAwbDtlTbRepository;

@Service
@Transactional
public class AwbDtlService {

	private final Logger log = LoggerFactory.getLogger(AwbDtlService.class);
	
	@Inject
	private ShpNspAwbDtlTbRepository shpNspAwbDtlTbRepository;
	
	public List<ShpNspAwbDtlTb> getAllByShpNspInvoiceChargesTb_InvoiceIdAndShpNspInvoiceChargesTb_NspInvoiceChargesId(long invoiceId,long invoiceChargesId) {
		return shpNspAwbDtlTbRepository.findAllByShpNspInvoiceChargesTb_InvoiceIdAndShpNspInvoiceChargesTb_NspInvoiceChargesId(new BigDecimal(invoiceId), invoiceChargesId);
	}
	
	public List<ShpNspAwbDtlTb> getAllByShpNspInvoiceChargesId(long invoiceChargesId) {
		return shpNspAwbDtlTbRepository.findAllByShpNspInvoiceChargesTb(invoiceChargesId);
	}
}
