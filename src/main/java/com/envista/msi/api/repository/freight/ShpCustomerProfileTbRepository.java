package com.envista.msi.api.repository.freight;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.envista.msi.api.domain.freight.ShpCustomerProfileTb;

/**
 * Spring Data JPA repository for the User entity.
 */
public interface ShpCustomerProfileTbRepository extends PagingAndSortingRepository<ShpCustomerProfileTb, Long> {

	/**
	 * @param customerId
	 * @return
	 */
	ShpCustomerProfileTb getByCustomerId(Long customerId);

	@Query(value = "SELECT * FROM SHP_CUSTOMER_PROFILE_TB cp, SHP_USER_CUST_SHIPPER_TB ucs WHERE ucs.CUSTOMER_ID=cp.CUSTOMER_ID AND ucs.USER_ID = ?1", nativeQuery = true)
	List<ShpCustomerProfileTb> findByUserId(Long userId);

	@Query(value = "SELECT * FROM SHP_CUSTOMER_PROFILE_TB cp, SHP_USER_CUST_SHIPPER_TB ucs WHERE ucs.CUSTOMER_ID=cp.CUSTOMER_ID AND ucs.USER_ID in (select u.USER_ID from SHP_USER_PROFILE_TB u where u.USER_NAME = ?1) AND cp.IS_ACTIVE = ?2 ORDER BY lower(cp.CUSTOMER_NAME)", nativeQuery = true)
	List<ShpCustomerProfileTb> findByUserNameAndIsActive(String userName, Boolean isActive);
}
