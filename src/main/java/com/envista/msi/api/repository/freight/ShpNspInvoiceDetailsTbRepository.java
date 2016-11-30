package com.envista.msi.api.repository.freight;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.envista.msi.api.domain.freight.ShpNspInvoiceDetailsTb;

/**
 * Spring Data JPA repository for the User entity.
 */
public interface ShpNspInvoiceDetailsTbRepository  extends PagingAndSortingRepository<ShpNspInvoiceDetailsTb,Long>, JpaSpecificationExecutor<ShpNspInvoiceDetailsTb>{
	// select * from shp_nsp_invoice_details_tb where nsp_invoice_details_id =
	// ?; (all details will be saved in InvoiceDetails)
}
