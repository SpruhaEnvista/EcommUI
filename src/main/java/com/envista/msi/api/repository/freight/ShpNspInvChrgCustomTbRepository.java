package com.envista.msi.api.repository.freight;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.envista.msi.api.domain.freight.ShpNspInvChrgCustomTb;


/**
 * Spring Data JPA repository for the User entity.//findByStatusAndFilterGroupAndCustomerId
 */
public interface ShpNspInvChrgCustomTbRepository extends PagingAndSortingRepository<ShpNspInvChrgCustomTb, Long>{
	
	List<ShpNspInvChrgCustomTb> findByInvoiceIdAndNspInvoiceChargesId(BigDecimal invoiceId,BigDecimal nspInvoiceChargesId);
}
