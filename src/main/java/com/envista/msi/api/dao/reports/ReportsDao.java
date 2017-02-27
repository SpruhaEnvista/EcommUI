package com.envista.msi.api.dao.reports;

import com.envista.msi.api.domain.PersistentContext;
import com.envista.msi.api.domain.util.DashboardSroredProcParam;
import com.envista.msi.api.domain.util.QueryParameter;
import com.envista.msi.api.domain.util.StoredProcedureParameter;
import com.envista.msi.api.web.rest.dto.UserProfileDto;
import com.envista.msi.api.web.rest.dto.dashboard.DashboardsFilterCriteria;
import com.envista.msi.api.web.rest.dto.dashboard.netspend.NetSpendByModeDto;
import com.envista.msi.api.web.rest.dto.reports.ReportResultsDto;
import com.envista.msi.api.web.rest.dto.reports.ReportResultsUsersListDto;
import com.envista.msi.api.web.rest.dto.reports.SavedSchedReportsDto;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by Sreenivas on 2/17/2017.
 */

@Repository("ReportsDao")
public class ReportsDao {

    @Inject
    private PersistentContext persistentContext;
    /**
     * Get bet reports based on userId.
     * @param userId
     * @return list<ReportResultsDto>
     */
    @Transactional( readOnly = true )
    public List<ReportResultsDto> getReportResults(Long userId) {
        return persistentContext.findEntitiesAndMapFields("ReportResults.getReportResults",
                StoredProcedureParameter.with("userId", userId));
    }
    /**
     * Update Expiry Date.
     * @param generatedRptId
     * @param expiryDate
     * @return list<ReportResultsDto>
     */
    @Transactional
    public ReportResultsDto updateExpiryDate(Long generatedRptId,String expiryDate) {
        Date toexpiryDate =convertDateFullYear(expiryDate);
        QueryParameter queryParameter = StoredProcedureParameter.with("expiryDate", toexpiryDate)
                .and("generatedRptId", generatedRptId);
        return persistentContext.findEntityAndMapFields("ReportResults.updateExpiryDate",queryParameter);
    }
    @Transactional
    public List<ReportResultsUsersListDto> getUsersList() {
        return persistentContext.findEntitiesAndMapFields("ReportResultsUsersList.getUsersList",null);
    }

    public static Date convertDateFullYear(String date)  {
        Date sdate = new Date(System.currentTimeMillis());
        try {
            sdate = new Date(Long.parseLong(date));
        }catch(Exception e){
            e.printStackTrace();
        }
        return sdate;
    }

    /**
     * Update Expiry Date.
     * @param generatedRptId
     * @param userId
     *  @param userName
     * @return
     */
    @Transactional
    public ReportResultsDto deleteReportInResults(long generatedRptId, long userId, String userName){
        QueryParameter queryParameter = StoredProcedureParameter.with("generatedRptId", generatedRptId)
                                        .and("userId", userId)
                                        .and("userName", userName);
        return persistentContext.findEntityAndMapFields("ReportResults.deleteResultReport",queryParameter);
    }
    /**
     * Get Saved Sched Reports.
     * @param userId
     * @return
     */

    @Transactional( readOnly = true )
    public List<SavedSchedReportsDto> getSavedSchedReports(long userId) {
        return persistentContext.findEntitiesAndMapFields("SavedSchedReports.gerSavedSchedReports",
                StoredProcedureParameter.with("userId", userId));
    }
}
