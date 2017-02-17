package com.envista.msi.api.dao.reports;

import com.envista.msi.api.domain.PersistentContext;
import com.envista.msi.api.domain.util.DashboardSroredProcParam;
import com.envista.msi.api.domain.util.QueryParameter;
import com.envista.msi.api.domain.util.StoredProcedureParameter;
import com.envista.msi.api.web.rest.dto.UserProfileDto;
import com.envista.msi.api.web.rest.dto.dashboard.DashboardsFilterCriteria;
import com.envista.msi.api.web.rest.dto.dashboard.netspend.NetSpendByModeDto;
import com.envista.msi.api.web.rest.dto.reports.ReportResultsDto;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.List;

/**
 * Created by Sreenivas on 2/17/2017.
 */

@Repository("ReportsDao")
public class ReportsDao {

    @Inject
    private PersistentContext persistentContext;
    /**
     * Get bet spend based on the applied filter.
     * @param userId
     * @return
     */
    @Transactional( readOnly = true )
    public List<ReportResultsDto> getReportResults(Long userId) {
        return persistentContext.findEntitiesAndMapFields("ReportResults.getReportResults",
                StoredProcedureParameter.with("userId", userId));
    }
}
