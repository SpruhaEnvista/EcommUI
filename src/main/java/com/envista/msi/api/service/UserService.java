package com.envista.msi.api.service;

import com.envista.msi.api.dao.UserProfileDao;
import com.envista.msi.api.web.rest.dto.UserProfileDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.Optional;

/**
 * Service class for managing users.
 */
/**
 * @author SANKER
 *
 */
@Service
@Transactional
public class UserService {

	private final Logger log = LoggerFactory.getLogger(UserService.class);

//	@Inject
//	private UserRepository userRepository;
	
	@Inject
	private UserProfileDao userProfileDao;

	/**
	 * @param userName
	 * @return UserProfileDto
	 */
	@Transactional(readOnly = true)
	public Optional<UserProfileDto> getUserWithAuthoritiesByUserName(String userName) {
		return userProfileDao.findUserProfileTbUsingProc(userName);
	}

	/*@Transactional(readOnly = true)
	public ShpUserProfileTb getUserWithAuthorities(Long id) {
		ShpUserProfileTb user = userRepository.findOne(id);
		user.getShpUserRoleTbs().size(); // eagerly load the association
		return user;
	}

	@Transactional(readOnly = true)
	public ShpUserProfileTb getUserWithAuthorities() {
		ShpUserProfileTb user = userRepository.findOneByUserName(SecurityUtils.getCurrentUserLogin()).get();
		user.getShpUserRoleTbs().size(); // eagerly load the association
		return user;
	}

	@Transactional(readOnly = true)
	public ShpUserProfileTb getUserByUserName(String userName) throws Exception {
		return getUserWithAuthoritiesByUserName(userName).orElse(null);
	}

*/
	public UserProfileDto getUserProfileByUserName(String userName) throws Exception {
		/*if(SecurityUtils.getUserCache().containsKey(userName)){
			return (UserProfileDto) SecurityUtils.getUserCache().get(userName);
		}else{
			UserProfileDto user = null;
			user = getUserWithAuthoritiesByUserName(userName).orElse(null);
			if(null != user){
				SecurityUtils.getUserCache().put(userName, user);
			}
			return user;
		}*/

		return getUserWithAuthoritiesByUserName(userName).orElse(null);
	}
}
