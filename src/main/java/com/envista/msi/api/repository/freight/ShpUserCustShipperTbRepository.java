package com.envista.msi.api.repository.freight;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.envista.msi.api.domain.freight.ShpCustomerProfileTb;
import com.envista.msi.api.domain.freight.ShpUserCustShipperTb;

/**
 * Spring Data JPA repository for the User entity.
 */
public interface ShpUserCustShipperTbRepository extends PagingAndSortingRepository<ShpUserCustShipperTb, Long> {

	/**
	 * @param customerId
	 * @return
	 */
	List<ShpUserCustShipperTb> getByShpCustomerProfileTbCustomerId(Long customerId);
	
	@Query(value = "SELECT * FROM SHP_CUSTOMER_PROFILE_TB cp, SHP_USER_CUST_SHIPPER_TB ucs WHERE ucs.CUSTOMER_ID=cp.CUSTOMER_ID AND ucs.USER_ID = ?1", nativeQuery = true)
	ShpCustomerProfileTb findByUserId(Long userId);
}
