package com.envista.msi.api.repository.freight;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.envista.msi.api.domain.freight.ShpNspInvoiceChargesTb;

/**
 * Spring Data JPA repository for the User entity.
 */
public interface ShpNspInvoiceChargesTbRepository extends PagingAndSortingRepository<ShpNspInvoiceChargesTb, Long> {
	// select * from shp_nsp_invoice_charges_tb where invoice_id = ? order by
	// nvl(xml_rating_seq,100) , nsp_invoice_charges_id;
	// BigDecimal invoiceId,
	/***
	 * @Query("SELECT s FROM ShpNspInvoiceChargesTb s WHERE s.invoiceId=?0
	 * Orderby s.xmlRatingSeq, s.nspInvoiceCharges_id")
	 */
	@Query(value = "SELECT * FROM shp_nsp_invoice_charges_tb WHERE invoice_id=?0 Order by NVL(xml_rating_seq,100), nsp_invoice_charges_id", nativeQuery = true)
	List<ShpNspInvoiceChargesTb> findAllByInvoiceIdNatOrdered(BigDecimal invoiceId);

	List<ShpNspInvoiceChargesTb> findAllByInvoiceIdOrderByXmlRatingSeqAsc(BigDecimal invoiceId);
	
}
