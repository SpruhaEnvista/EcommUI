package com.envista.msi.api.repository.freight;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.envista.msi.api.domain.freight.ShpNspInvDetailsCustomTb;

/**
 * Spring Data JPA repository for the User entity.
 */
public interface ShpNspInvDetailsCustomTbRepository extends PagingAndSortingRepository<ShpNspInvDetailsCustomTb, Long> {
	// select * from shp_nsp_inv_details_custom_tb where invoice_id = ?;
	List<ShpNspInvDetailsCustomTb> findByInvoiceId(BigDecimal invoiceId);
}
