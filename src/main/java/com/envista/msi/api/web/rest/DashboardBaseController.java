package com.envista.msi.api.web.rest;

import com.envista.msi.api.security.SecurityUtils;
import com.envista.msi.api.service.UserService;
import com.envista.msi.api.web.rest.dto.DashboardAppliedFilterDto;
import com.envista.msi.api.web.rest.dto.DashboardsFilterCriteria;
import com.envista.msi.api.web.rest.dto.UserProfileDto;
import org.springframework.beans.factory.annotation.Autowired;

import javax.inject.Inject;
import javax.servlet.ServletRequest;
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
        if(null == dashboardAppliedFilterDto){ return null; }

        DashboardsFilterCriteria dashboardsFilterCriteria = new DashboardsFilterCriteria();
        dashboardsFilterCriteria.setConvertCurrencyCode(dashboardAppliedFilterDto.getCurrencyCode());
        if(dashboardAppliedFilterDto.getCurrencyId() != null && !dashboardAppliedFilterDto.getCurrencyId().isEmpty()){
            dashboardsFilterCriteria.setConvertCurrencyId(Long.parseLong(dashboardAppliedFilterDto.getCurrencyId()));
        }
        dashboardsFilterCriteria.setCarriers(dashboardAppliedFilterDto.getCarrierIds());
        dashboardsFilterCriteria.setDateType(dashboardAppliedFilterDto.getDateType());
        dashboardsFilterCriteria.setFromDate(dashboardAppliedFilterDto.getFromDate());
        dashboardsFilterCriteria.setLanes(dashboardAppliedFilterDto.getLanes());
        dashboardsFilterCriteria.setModes(dashboardAppliedFilterDto.getModes());
        dashboardsFilterCriteria.setService(dashboardAppliedFilterDto.getServices());
        dashboardsFilterCriteria.setToDate(dashboardAppliedFilterDto.getToDate());
        dashboardsFilterCriteria.setShipperGroupIdsCSV(dashboardAppliedFilterDto.getShipperGroupId());
        dashboardsFilterCriteria.setConvertWeightUnit(dashboardAppliedFilterDto.getWeightUnit());
        dashboardsFilterCriteria.setUserId(dashboardAppliedFilterDto.getLoginUserId());
        dashboardsFilterCriteria.setCustomerIdsCSV(dashboardAppliedFilterDto.getCustomerIds());
        dashboardsFilterCriteria.setTax("");
        return dashboardsFilterCriteria;
    }
}
