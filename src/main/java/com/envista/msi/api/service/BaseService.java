package com.envista.msi.api.service;

import com.envista.msi.api.security.SecurityUtils;
import com.envista.msi.api.web.rest.dto.UserProfileDto;
import org.springframework.stereotype.Service;

import javax.inject.Inject;

/**
 * Created by Sujit kumar on 12/07/2017.
 */
@Service
public class BaseService {
    @Inject
    private UserService userService;

    public UserProfileDto getLoggedInUser() throws Exception {
        return userService.getUserProfileByUserName(SecurityUtils.getCurrentUserLogin());
    }
}
