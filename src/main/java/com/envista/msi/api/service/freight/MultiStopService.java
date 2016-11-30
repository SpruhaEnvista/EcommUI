package com.envista.msi.api.service.freight;

import java.math.BigDecimal;
import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.envista.msi.api.domain.freight.ShpNspMultiStopTb;
import com.envista.msi.api.repository.freight.ShpNspMultiStopTbRepository;

@Service
@Transactional
public class MultiStopService {

	private final Logger log = LoggerFactory.getLogger(MultiStopService.class);
	
	@Inject
	private ShpNspMultiStopTbRepository shpNspMultiStopTbRepository;
	
	public List<ShpNspMultiStopTb> getByScannedInvoiceId(long scannedInvoiceId) {
		return shpNspMultiStopTbRepository.findById_ScannedInvoiceId(new BigDecimal(scannedInvoiceId));
	}
	
	public List<ShpNspMultiStopTb> getByInvoiceDetailsId(long invoiceDetailsId) {
		return shpNspMultiStopTbRepository.findById_InvoiceDetailsId(new BigDecimal(invoiceDetailsId));
	}
}
