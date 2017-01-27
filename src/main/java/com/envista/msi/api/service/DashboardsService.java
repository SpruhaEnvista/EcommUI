package com.envista.msi.api.service;

import com.envista.msi.api.dao.DashboardsDao;
import com.envista.msi.api.web.rest.dto.DashboardAppliedFilterDto;
import com.envista.msi.api.web.rest.dto.DashboardDataDto;
import com.envista.msi.api.web.rest.dto.NetSpendDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.List;

/**
 * Created by Sarvesh on 1/19/2017.
 */


@Service
@Transactional
public class DashboardsService {

    @Inject
    private DashboardsDao dashboardsDao;

    @Transactional(readOnly = true)
    public DashboardAppliedFilterDto getUserAppliedFilter(long userId) {
        return  dashboardsDao.getUserAppliedFilter(userId);
    }

    @Transactional(readOnly = true)
    public List<NetSpendDto> getNetSpendByModes(DashboardDataDto dbDto, boolean isTopTenAccessorial) {
        return  dashboardsDao.getNetSpendByModes(dbDto, isTopTenAccessorial);
    }

    @Transactional(readOnly = true)
    public List<NetSpendDto> getNetSpendByMonth(DashboardDataDto dbDto, boolean isTopTenAccessorial) {
        return  dashboardsDao.getNetSpendByMonth(dbDto, isTopTenAccessorial);
    }

    @Transactional(readOnly = true)
    public List<NetSpendDto> getNetSpendByOverTime(DashboardDataDto dbDto, boolean isTopTenAccessorial) {
        return  dashboardsDao.getNetSpendByOverTime(dbDto, isTopTenAccessorial);
    }
}
