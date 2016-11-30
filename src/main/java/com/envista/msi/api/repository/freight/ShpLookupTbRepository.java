package com.envista.msi.api.repository.freight;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.envista.msi.api.domain.freight.ShpLookupTb;

/**
 * Spring Data JPA repository for the User entity.
 */
/**
 * @author SANKER
 *
 */
public interface ShpLookupTbRepository extends PagingAndSortingRepository<ShpLookupTb, Long> {
	/**
	 * @param moduleName
	 * @param customDefined8
	 * @return
	 */
	List<ShpLookupTb> findByModuleNameAndCustomDefined8(String moduleName, String customDefined8);
}
