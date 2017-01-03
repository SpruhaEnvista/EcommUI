/**
 * 
 */
package com.envista.msi.api.dao;

import java.util.List;
import java.util.Optional;

import javax.inject.Inject;

import org.springframework.stereotype.Repository;

import com.envista.msi.api.domain.PersistentContext;
import com.envista.msi.api.domain.util.StoredProcedureParameter;
import com.envista.msi.api.web.rest.dto.UserProfileDto;

/**
 * 
 * It is just sample Dao that showcases calling storedProcedure. Dont copy and
 * paste while creating new Dao. This class will be removed or modified once
 * concrete Dao are created.
 * 
 * @author SANKER
 *
 */
@Repository("userProfileDao")
public class UserProfileDao {

	@Inject
	private PersistentContext persistentContext;

	/**
	 * @param userName
	 * @return
	 */
	public Optional<UserProfileDto> findUserProfileTbUsingProc(String userName) {
		Optional<UserProfileDto> user = Optional.ofNullable(persistentContext.findEntity("UserProfileTb.getUserByProcUserEntity",
				StoredProcedureParameter.with("userNameInParam", userName)));
		return user;
	}
  
	/**
	 * @param userName
	 * @return
	 */
	public UserProfileDto getUserProfileTbUsingProcAndFieldMap(String userName) {
		return persistentContext.findEntityAndMapFields("UserProfileTb.getUserByProcUserEntityMapping",
				StoredProcedureParameter.with("userNameInParam", userName));
	}

	public List<UserProfileDto> getUserProfilsUsingProcAndFieldMap(String userName) {
		return persistentContext.findEntitiesAndMapFields("UserProfileTb.getUserByProcUserEntityMapping",
				StoredProcedureParameter.with("userNameInParam", userName));
	}
	
	public Optional<UserProfileDto> getUserWithAuthoritiesByUserName(String userName){
		return null;
		
	}
}
