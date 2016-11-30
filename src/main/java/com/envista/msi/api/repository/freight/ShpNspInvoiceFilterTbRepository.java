package com.envista.msi.api.repository.freight;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.envista.msi.api.domain.freight.ShpNspInvoiceFilterTb;

/**
 * Spring Data JPA repository for the User entity.
 */
public interface ShpNspInvoiceFilterTbRepository extends PagingAndSortingRepository<ShpNspInvoiceFilterTb, Long> {
	// select * from shp_nsp_invoice_filter_tb where status=1 and filter_group =
	// 'auditrule' and customer_id = ? order by filter_name;
	List<ShpNspInvoiceFilterTb> findByStatusAndFilterGroupAndCustomerId(BigDecimal status, String filterGroup,
			BigDecimal customerId);
}
