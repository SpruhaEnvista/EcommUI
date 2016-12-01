package com.envista.msi.api.service;

import java.math.BigDecimal;
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
import org.springframework.util.StringUtils;

import com.envista.msi.api.domain.freight.ShpCarrierTb;
import com.envista.msi.api.domain.freight.ShpCustomerProfileTb;
import com.envista.msi.api.domain.freight.ShpNspCodeValuesTb;
import com.envista.msi.api.domain.freight.ShpNspInvoiceDetailsTb;
import com.envista.msi.api.domain.util.BasicIdNamePair;
import com.envista.msi.api.repository.freight.InvoiceDetailsSpecs;
import com.envista.msi.api.repository.freight.ShpCarrierTbRepository;
import com.envista.msi.api.repository.freight.ShpCustomerProfileTbRepository;
import com.envista.msi.api.repository.freight.ShpNspInvoiceDetailsTbRepository;
import com.envista.msi.api.service.freight.CodeValuesService;
import com.envista.msi.api.web.rest.dto.CarrierScacName;
import com.envista.msi.api.web.rest.dto.InvoiceDetails;
import com.envista.msi.api.web.rest.dto.SearchCriteria;
import com.envista.msi.api.web.rest.dto.SearchMetadata;
import com.envista.msi.api.web.rest.dto.UserAssignedCustomer;
import com.envista.msi.api.web.rest.mapper.InvoiceDetailsMapper;
import com.envista.msi.api.web.rest.util.PaginationUtil;
import com.envista.msi.api.web.rest.util.WebConstants;

/**
 * Service class that creates searchable lists for UI display in presearch form.
 *
 * @author SANKER
 *
 */
@Service
@Transactional
public class SearchService {

	private final Logger log = LoggerFactory.getLogger(SearchService.class);

	private static final String COLUMN_SPLITTER = "_999_";

	/**
	 * Repository to fetch user assigned customers
	 */
	@Inject
	private ShpCustomerProfileTbRepository customerProfileRepo;
	@Inject
	private ShpCarrierTbRepository carrierTbRepo;
	@Inject
	private CodeValuesService codeValuesService;
	@Inject
	private ShpNspInvoiceDetailsTbRepository invoiceDetailsRepo;

	/**
	 * @param userId
	 * @return
	 */
	public SearchMetadata getStaticSearchMetadata(String userName) {
		SearchMetadata searchMetadata = new SearchMetadata();

		List<UserAssignedCustomer> customers = getUserAssignedCustomerIdNames(userName);
		searchMetadata.setCustomers(customers);
		// Lazy loading.. by another api call
		// searchMetadata.setCustomerCarriers(customerCarriers);
		// searchMetadata.setCarrierScacs(carrierIdScacs);

		searchMetadata
				.setDateCriterias(getCodeValuesByCodeGroupId(WebConstants.InvoiceLookup.INVOICE_LOOKUP_DATE_CRITERIA));
		searchMetadata.setCarrierModes(getCodeValuesByCodeGroupId(WebConstants.InvoiceLookup.CARRIER_MODES));
		searchMetadata
				.setFreightInvoiceTypes(getCodeValuesByCodeGroupId(WebConstants.InvoiceLookup.FREIGHT_INVOICE_TYPE));
		searchMetadata
				.setInvoiceStatusReports(getCodeValuesByCodeGroupId(WebConstants.InvoiceLookup.INVOICE_STATUS_REPORT));
		searchMetadata.setServiceLevels(getCodeValuesByCodeGroupId(WebConstants.InvoiceLookup.SERVICE_LEVEL));

		return searchMetadata;
	}

	/**
	 * @param userId
	 * @return
	 */
	@Transactional(readOnly = true)
	public List<UserAssignedCustomer> getUserAssignedCustomerIdNames(String userName) {
		List<UserAssignedCustomer> customerIdNames = new ArrayList<UserAssignedCustomer>();
		List<ShpCustomerProfileTb> assignedCustomers = customerProfileRepo.findByUserNameAndIsActive(userName,
				Boolean.TRUE);
		for (ShpCustomerProfileTb shpCustomerProfileTb : assignedCustomers) {
			UserAssignedCustomer uac = new UserAssignedCustomer(shpCustomerProfileTb.getCustomerId(),
					shpCustomerProfileTb.getCustomerName());
			ShpCustomerProfileTb parent = shpCustomerProfileTb.getShpCustomerProfileTb();
			if (parent != null) {
				uac.setParent(new BasicIdNamePair<Long>(parent.getCustomerId(), parent.getCustomerName()));
			}
			customerIdNames.add(uac);
		}
		return customerIdNames;
	}

	/**
	 * @param customerId
	 * @return
	 */
	@Transactional(readOnly = true)
	public List<CarrierScacName> getCarrierScac() {
		List<CarrierScacName> carrierIdNames = new ArrayList<CarrierScacName>();
		for (ShpCarrierTb shpCarrierTb : carrierTbRepo.findAll()) {
			if (shpCarrierTb.getIsActive() != null && shpCarrierTb.getIsActive().compareTo(BigDecimal.ZERO) > 0) {
				carrierIdNames.add(new CarrierScacName(shpCarrierTb.getCarrierId(), shpCarrierTb.getCarrierName(),
						shpCarrierTb.getScacCode()));
			}
		}
		return carrierIdNames;
	}

	/**
	 * @param customerId
	 * @return
	 */
	@Transactional(readOnly = true)
	public List<CarrierScacName> getCarrierScac(Long customerId) {

		List<CarrierScacName> carrierIdNames = new ArrayList<CarrierScacName>();
		List<ShpCarrierTb> carriers = carrierTbRepo.findByCarrierId(customerId);
		for (ShpCarrierTb shpCarrierTb : carriers) {
			if (shpCarrierTb.getIsActive() != null && shpCarrierTb.getIsActive().compareTo(BigDecimal.ZERO) > 0) {
				carrierIdNames.add(new CarrierScacName(shpCarrierTb.getCarrierId(), shpCarrierTb.getCarrierName(),
						shpCarrierTb.getScacCode()));
			}
		}
		return carrierIdNames;
	}

	/**
	 * @param codeGroupName
	 * @return
	 */
	@Transactional(readOnly = true)
	private ArrayList<String> getCodeValuesByCodeGroupId(String codeGroupName) {
		ArrayList<String> codeValues = new ArrayList<String>();

		for (ShpNspCodeValuesTb shpNspCodeValuesTb : codeValuesService
				.getAllByShpNspCodeValueGroupsTb_CodeGroupName(codeGroupName)) {
			codeValues.add(shpNspCodeValuesTb.getCodeValue());
		}

		return codeValues;
	}

	public List<ShpNspInvoiceDetailsTb> getInvoiceDetails(int pageNumber, int totalRecords) {
		if (totalRecords < 1) {
			totalRecords = PaginationUtil.DEFAULT_PAGE_SIZE;
		}
		PageRequest request = new PageRequest(pageNumber - 1, totalRecords, Sort.Direction.DESC, "nspInvoiceDetailsId");
		Page<ShpNspInvoiceDetailsTb> currPage = invoiceDetailsRepo.findAll(request);
		if (currPage != null) {
			return currPage.getContent();
		}

		return new ArrayList<ShpNspInvoiceDetailsTb>();
	}

	@Transactional(readOnly = true)
	public Page<InvoiceDetails> findInvoiceDetails(int pageNumber, int numberOfElements,
			final SearchCriteria searchCriteria) throws Exception {
		return findInvoiceDetails(
				new PageRequest(pageNumber - 1, numberOfElements, new Sort(Sort.Direction.ASC, "nspInvoiceDetailsId")),
				searchCriteria);
	}

	@Transactional(readOnly = true)
	public Page<InvoiceDetails> findInvoiceDetails(final PageRequest request, final SearchCriteria searchCriteria)
			throws Exception {

		PageRequest pageRequest = request;
		if (pageRequest == null) {
			pageRequest = new PageRequest(0, 50, new Sort(Sort.Direction.ASC, "nspInvoiceDetailsId")
					.and(new Sort(Sort.Direction.ASC, "shpCustomerProfileTb.customerName")));
		}

		Page<ShpNspInvoiceDetailsTb> searchResultPage = invoiceDetailsRepo
				.findAll(InvoiceDetailsSpecs.buildCriteria(searchCriteria), pageRequest);

		return InvoiceDetailsMapper.mapToDTO(searchResultPage.getContent(), pageRequest,
				searchResultPage.getTotalElements());

	}

	@Transactional(readOnly = true)
	public Page<InvoiceDetails> findInvoiceDetails(int pageNumber, int numberOfElements, String sortColumn,
			String sortOrder, SearchCriteria searchCriteria) throws Exception {

		String sortableColumn = InvoiceDetailsMapper.getSortableProperty(sortColumn);
		Sort sort = sortableColumn == null ? null
				: new Sort(!StringUtils.isEmpty(sortOrder) && sortOrder.toUpperCase().startsWith("DESC")
						? Sort.Direction.DESC : Sort.Direction.ASC, sortableColumn);
		return sort == null ? findInvoiceDetails(pageNumber, numberOfElements, searchCriteria)
				: findInvoiceDetails(new PageRequest(pageNumber - 1, numberOfElements, sort), searchCriteria);

	}
}
