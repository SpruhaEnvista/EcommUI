package com.envista.msi.api.service.freight;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.envista.msi.api.domain.freight.ShpNspInvoiceDetailsTb;
import com.envista.msi.api.repository.freight.ShpNspInvoiceDetailsTbRepository;

import com.envista.msi.api.web.rest.util.PaginationUtil;;
/**
 * Service class for managing users.
 */
@Service
@Transactional
public class InvoiceDetailService {

	private final Logger log = LoggerFactory.getLogger(InvoiceDetailService.class);

	@Inject
	private ShpNspInvoiceDetailsTbRepository shpNspInvoiceDetailsTbRepository;

	public ShpNspInvoiceDetailsTb getInvoiceDetails(long invoiceId) {
		return shpNspInvoiceDetailsTbRepository.findOne(invoiceId);
	}

	public List<ShpNspInvoiceDetailsTb> getInvoiceDetails(int pageNumber, int totalRecords) {
		if (totalRecords < 1) {
			totalRecords = PaginationUtil.DEFAULT_PAGE_SIZE;
		}
		PageRequest request = new PageRequest(pageNumber - 1, totalRecords, Sort.Direction.DESC, "nspInvoiceDetailsId");
		Page<ShpNspInvoiceDetailsTb> currPage = shpNspInvoiceDetailsTbRepository.findAll(request);
		if (currPage != null) {
			return currPage.getContent();
		}

		return new ArrayList<ShpNspInvoiceDetailsTb>();
	}
}
