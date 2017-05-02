package com.envista.msi.api.web.rest;

import com.envista.msi.api.domain.util.DashboardUtil;
import com.envista.msi.api.security.SecurityUtils;
import com.envista.msi.api.service.UserService;
import com.envista.msi.api.web.rest.dto.UserProfileDto;
import com.envista.msi.api.web.rest.dto.dashboard.DashboardAppliedFilterDto;
import com.envista.msi.api.web.rest.dto.dashboard.DashboardsFilterCriteria;
import org.springframework.beans.factory.annotation.Autowired;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

/**
 * Created by Sujit kumar on 31/01/2017.
 */
public class DashboardBaseController {

    @Inject
    private UserService userService;

    @Autowired
    HttpSession session;

    protected UserProfileDto getUserProfile(){
        UserProfileDto user = null;
        try {
            user = userService.getUserProfileByUserName(SecurityUtils.getCurrentUserLogin());
        } catch (Exception e) {
            //nothing
        }
        return user;
    }

    protected DashboardsFilterCriteria populateDashboardFilterCriteria(DashboardAppliedFilterDto dashboardAppliedFilterDto){
        return DashboardUtil.prepareDashboardFilterCriteria(dashboardAppliedFilterDto);
    }
}
