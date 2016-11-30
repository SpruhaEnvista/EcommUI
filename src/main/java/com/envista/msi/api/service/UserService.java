package com.envista.msi.api.service;

import java.util.Optional;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.envista.msi.api.domain.freight.ShpUserProfileTb;
import com.envista.msi.api.repository.UserRepository;
import com.envista.msi.api.security.SecurityUtils;
import com.envista.msi.api.web.rest.dto.UserProfileBean;
import com.envista.msi.api.web.rest.mapper.UserMapper;

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

	@Inject
	private UserRepository userRepository;

	@Transactional(readOnly = true)
	public Optional<ShpUserProfileTb> getUserWithAuthoritiesByUserName(String userName) {
		return userRepository.findOneByUserName(userName).map(u -> {
			u.getShpUserRoleTbs().size();
			return u;
		});
	}

	@Transactional(readOnly = true)
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
	public ShpUserProfileTb getUserByUserName(String userName) throws Exception{
		return getUserWithAuthoritiesByUserName(userName).orElse(null);
	}
	
	public UserProfileBean getUserProfileByUserName(String userName) throws Exception{
		return UserMapper.buildBean(getUserWithAuthoritiesByUserName(userName).orElse(null));
	}
}
