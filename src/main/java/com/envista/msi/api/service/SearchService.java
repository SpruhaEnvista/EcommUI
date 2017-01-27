package com.envista.msi.api.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.envista.msi.api.web.rest.dto.CarrierScacName;
import com.envista.msi.api.web.rest.dto.InvoiceDetails;
import com.envista.msi.api.web.rest.dto.UserAssignedCustomer;
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
	 * @param userName
	 * @return
	 */
	@Transactional(readOnly = true)
	public List<UserAssignedCustomer> getUserAssignedCustomerIdNames(String userName) {
		List<UserAssignedCustomer> customerIdNames = new ArrayList<UserAssignedCustomer>();
		return customerIdNames;
	}

	/**
	 * @return
	 */
	@Transactional(readOnly = true)
	public List<CarrierScacName> getCarrierScac() {
		List<CarrierScacName> carrierIdNames = new ArrayList<CarrierScacName>();
		return carrierIdNames;
	}

	/**
	 * @param customerId
	 * @return
	 */
	@Transactional(readOnly = true)
	public List<CarrierScacName> getCarrierScac(Long customerId) {

		List<CarrierScacName> carrierIdNames = new ArrayList<CarrierScacName>();
		return carrierIdNames;
	}

	/**
	 * @param codeGroupName
	 * @return
	 */
	@Transactional(readOnly = true)
	private ArrayList<String> getCodeValuesByCodeGroupId(String codeGroupName) {
		ArrayList<String> codeValues = new ArrayList<String>();
		return codeValues;
	}

	public List getInvoiceDetails(int pageNumber, int totalRecords) {
		return new ArrayList();
	}
}
