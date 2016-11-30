package com.envista.msi.api.repository.freight;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.envista.msi.api.domain.freight.ShpNspInvoiceHistoryTb;

public interface ShpNspInvoiceHistoryTbRepository extends PagingAndSortingRepository<ShpNspInvoiceHistoryTb, Long> {

	List<ShpNspInvoiceHistoryTb> findByInvoiceId(BigDecimal invoiceId);
}
