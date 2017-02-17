package com.envista.msi.api.service;

import com.envista.msi.api.dao.reports.ReportsDao;
import com.envista.msi.api.web.rest.dto.dashboard.DashboardAppliedFilterDto;
import com.envista.msi.api.web.rest.dto.reports.ReportResultsDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.List;

/**
 * Created by Sreenivas on 2/17/2017.
 */

@Service
@Transactional
public class ReportsService {

    @Inject
    private ReportsDao reportsDao;

    public List<ReportResultsDto> getReportResults(long userId) {
        return  reportsDao.getReportResults(userId);
    }
}
