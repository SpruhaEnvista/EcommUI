package com.envista.msi.api.repository.freight;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.envista.msi.api.domain.freight.ShpNspAwbDtlTb;

/**
 * Spring Data JPA repository for the User entity.
 */
/**
 * @author SANKER
 *
 */
public interface ShpNspAwbDtlTbRepository extends PagingAndSortingRepository<ShpNspAwbDtlTb, Long> {
	
	List<ShpNspAwbDtlTb> findAllByShpNspInvoiceChargesTb_InvoiceIdAndShpNspInvoiceChargesTb_NspInvoiceChargesId(
			BigDecimal invoiceId, long nspInvoiceChargesId);
	
	List<ShpNspAwbDtlTb> findAllByShpNspInvoiceChargesTb(long nspInvoiceChargesId);
}
