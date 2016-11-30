package com.envista.msi.api.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.envista.msi.api.domain.freight.ShpUserProfileTb;

/**
 * Spring Data JPA repository for the User entity.
 */
/**
 * @author SANKER
 *
 */
public interface UserRepository extends JpaRepository<ShpUserProfileTb, Long> {

	Optional<ShpUserProfileTb> findOneByEmail(String email);

	Optional<ShpUserProfileTb> findOneByUserName(String userName);

	Optional<ShpUserProfileTb> findOneByFullname(String fullName);

	Optional<ShpUserProfileTb> findOneByUserId(Long userId);
	
/*	Optional<ShpUserProfileTb> findOneById(Long userId);*/

	@Override
	void delete(ShpUserProfileTb t);

}
