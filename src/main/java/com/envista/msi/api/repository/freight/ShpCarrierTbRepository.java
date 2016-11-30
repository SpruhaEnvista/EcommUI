package com.envista.msi.api.repository.freight;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.envista.msi.api.domain.freight.ShpCarrierTb;



/**
 * Spring Data JPA repository for the User entity.
 */
public interface ShpCarrierTbRepository extends PagingAndSortingRepository<ShpCarrierTb, Long> {
	// select * from shp_carrier_tb where carrier_id = ?;
	@Query(value=" SELECT * FROM SHP_NSP_CUST_CARRIER_RELN_TB RELNTB,SHP_CARRIER_TB CRR WHERE RELNTB.CUSTOMER_ID = ?1 and CRR.CARRIER_ID = RELNTB.CARRIER_ID ",nativeQuery = true)
	List<ShpCarrierTb> findByCarrierId(long carrierId);
}
