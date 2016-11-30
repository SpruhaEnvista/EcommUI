package com.envista.msi.api.repository.freight;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.envista.msi.api.domain.freight.ShpCustColDefnTb;

/**
 * Spring Data JPA repository for the User entity.
 */
public interface ShpCustColDefnTbRepository extends PagingAndSortingRepository<ShpCustColDefnTb, Long> {
	
	List<ShpCustColDefnTb> findAllByCustomerIdAndRptType(BigDecimal customerId, BigDecimal rptType);
}
