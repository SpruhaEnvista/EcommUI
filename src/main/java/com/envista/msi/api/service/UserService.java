package com.envista.msi.api.service;

import com.envista.msi.api.dao.DaoException;
import com.envista.msi.api.dao.UserDetailsDao;
import com.envista.msi.api.dao.UserProfileDao;
import com.envista.msi.api.domain.util.ReportsUtil;
import com.envista.msi.api.domain.util.StringEncrypter;
import com.envista.msi.api.web.rest.dto.UserDetailsDto;
import com.envista.msi.api.web.rest.dto.UserProfileDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.List;
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

	@Inject
	private UserDetailsDao userDetailsDao;

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
	public UserProfileDto validatePassword(String password, Long userId) throws Exception {
		StringEncrypter stringEncrypter = StringEncrypter.getInstance() ;
		String enCryptedPwd = ReportsUtil.encrypt(password);
		List<UserProfileDto> userDetails = userProfileDao.validatePassword(enCryptedPwd,userId);
		UserProfileDto userProfileDto = null;
		if(userDetails!= null && userDetails.size()>0){
			userProfileDto = userDetails.get(0);
			if (userProfileDto ==null || (userProfileDto != null && userProfileDto.getUserId()==0)) {
				throw new DaoException("Invalid Password");
			}
		}else{
			throw new DaoException("Invalid Password");
		}

		return userProfileDto;
	}
	public UserDetailsDto changePassword(String currentPassword, String newPassword,Long userId) throws Exception {
		StringEncrypter stringEncrypter = StringEncrypter.getInstance() ;
		String enCryptedPwd = ReportsUtil.encrypt(currentPassword);
		String enCryptedNewPwd = ReportsUtil.encrypt(newPassword);
		UserDetailsDto userDetails = userDetailsDao.changePassword(enCryptedPwd,enCryptedNewPwd,userId);
		return userDetails;
	}

	public UserDetailsDto updateUserProfile(String fullname,String email,String phone, Long userId) {
		return userDetailsDao.updateUserProfile(fullname,email,phone,userId);
	}
}
