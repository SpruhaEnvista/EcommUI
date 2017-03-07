package com.envista.msi.api.service;

import com.envista.msi.api.dao.reports.ReportsDao;
import com.envista.msi.api.web.rest.dto.dashboard.DashboardAppliedFilterDto;
import com.envista.msi.api.web.rest.dto.reports.ReportResultsDto;
import com.envista.msi.api.web.rest.dto.reports.ReportResultsUsersListDto;
import com.envista.msi.api.web.rest.dto.reports.SavedSchedReportsDto;
import com.envista.msi.api.web.rest.dto.reports.UpdateSavedSchedReportDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.Date;
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
    public ReportResultsDto updateExpiryDate(Long generatedRptId,String expiryDate) {
        return  reportsDao.updateExpiryDate(generatedRptId,expiryDate);
    }

    public ReportResultsDto deleteReportInResults(long generatedRptId, long userId, String userName) {
        return  reportsDao.deleteReportInResults(generatedRptId, userId, userName);
    }
    public List<ReportResultsUsersListDto> getUsersList() {
        return  reportsDao.getUsersList();
    }

    public List<SavedSchedReportsDto> getSavedSchedReports(long userId){
        return reportsDao.getSavedSchedReports(userId);
    }

    public UpdateSavedSchedReportDto updateSavedSchedReport(UpdateSavedSchedReportDto updateSavedSchedReportDto){
        return reportsDao.updateSavedSchedReport(updateSavedSchedReportDto);
    }
    public UpdateSavedSchedReportDto runSavedSchedReport(UpdateSavedSchedReportDto updateSavedSchedReportDto){
        return reportsDao.runSavedSchedReport(updateSavedSchedReportDto);
    }
    public UpdateSavedSchedReportDto saveFromReportResults(UpdateSavedSchedReportDto updateSavedSchedReportDto){
        return reportsDao.saveFromReportResults(updateSavedSchedReportDto);
    }
    public ReportResultsUsersListDto pushToUser(List<ReportResultsUsersListDto> reportResultsUsersListDto){
        return reportsDao.pushToUser(reportResultsUsersListDto);
    }
}
