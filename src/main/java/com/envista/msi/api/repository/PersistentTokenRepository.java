package com.envista.msi.api.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.envista.msi.api.domain.PersistentToken;
import com.envista.msi.api.domain.freight.ShpUserProfileTb;

/**
 * Spring Data JPA repository for the PersistentToken entity.
 */
public interface PersistentTokenRepository extends JpaRepository<PersistentToken, String> {

    List<PersistentToken> findByUser(ShpUserProfileTb user);

    List<PersistentToken> findByTokenDateBefore(LocalDate localDate);

}
