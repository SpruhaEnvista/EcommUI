package com.envista.msi.api.repository.freight;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.envista.msi.api.domain.freight.ShpNspMultiStopTb;

/**
 * Spring Data JPA repository for the User entity.
 */
public interface ShpNspMultiStopTbRepository extends PagingAndSortingRepository<ShpNspMultiStopTb, Long> {
	/* select * from SHP_NSP_MULTI_STOP_TB where SCANNED_INVOICE_ID = ?; */
	List<ShpNspMultiStopTb> findById_ScannedInvoiceId(BigDecimal scannedInvoiceId);

	/* select * from SHP_NSP_MULTI_STOP_TB where INVOICE_DETAILS_ID = ?; */
	List<ShpNspMultiStopTb> findById_InvoiceDetailsId(BigDecimal scannedInvoiceId);
}
