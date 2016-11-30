package com.envista.msi.api.repository.freight;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.envista.msi.api.domain.freight.ShpNspCustCarrierRelnTb;

/**
 * Spring Data JPA repository for the User entity.
 */
public interface ShpNspCustCarrierRelnTbRepository extends PagingAndSortingRepository<ShpNspCustCarrierRelnTb, Long> {

	// select a.carrier_id,a.carrier_description,a.carrier_name,a.scac_code from
	// shp_carrier_tb a, shp_nsp_cust_carrier_reln_tb b where a.CARRIER_ID =
	// b.carrier_id and a.scac_code is not null and a.is_active = 1 and b.active
	// = 1 and b.CUSTOMER_ID = ? order by carrier_name asc
	List<ShpNspCustCarrierRelnTb> findAllByShpCustomerProfileTb_CustomerId(long customerId);

	// select * from SHP_NSP_CUST_CARRIER_RELN_TB where customer_id = ? and
	// carrier_id = ?;
	List<ShpNspCustCarrierRelnTb> findAllByShpCustomerProfileTb_CustomerIdAndShpCarrierTb_CarrierId(long customerId,
			long carrierId);
}
