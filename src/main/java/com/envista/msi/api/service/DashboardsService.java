package com.envista.msi.api.service;

import com.envista.msi.api.dao.DashboardsDao;
import com.envista.msi.api.web.rest.dto.DashboardsDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;

/**
 * Created by Sarvesh on 1/19/2017.
 */


@Service
@Transactional
public class DashboardsService {

    @Inject
    private DashboardsDao dashboardsDao;

    @Transactional(readOnly = true)
    public DashboardsDto getUserAppliedFilter(long userId) {
        return  dashboardsDao.getUserAppliedFilter(userId);
    }
}
