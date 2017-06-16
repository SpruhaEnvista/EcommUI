package com.envista.msi.api.dao.invoicing;

import com.envista.msi.api.domain.PersistentContext;
import com.envista.msi.api.domain.util.QueryParameter;
import com.envista.msi.api.domain.util.StoredProcedureParameter;
import com.envista.msi.api.web.rest.dto.invoicing.DashBoardDto;
import com.envista.msi.api.web.rest.dto.invoicing.FileInfoDto;
import com.envista.msi.api.web.rest.dto.invoicing.WeekEndDto;
import com.envista.msi.api.web.rest.dto.invoicing.WeekStatusDto;
import org.springframework.stereotype.Repository;

import javax.inject.Inject;
import java.util.List;

/**
 * Created by KRISHNAREDDYM on 5/8/2017.
 */
@Repository("dashBoardDAO")
public class DashBoardDao {

    @Inject
    private PersistentContext persistentContext;

    public List<DashBoardDto> getDashBoardInfo(String fromDate, String toDate, String actionType) {

        QueryParameter queryParameter = StoredProcedureParameter.with("P_FROM_DATE", fromDate)
                .and("P_TO_DATE", toDate).and("P_ACTION_TYPE", actionType);

        return persistentContext.findEntities("DashBoardDto.getDashBoardInfo", queryParameter);
    }

    public List<DashBoardDto> getPendingCredits(String fromDate, String toDate, String actionType) {

        QueryParameter queryParameter = StoredProcedureParameter.with("P_FROM_DATE", fromDate)
                .and("P_TO_DATE", toDate).and("P_ACTION_TYPE", actionType);

        return persistentContext.findEntities("DashBoardDto.getPendingCreditsCount", queryParameter);

    }

    public int closeCurrentWeekCredits(String ebillManifestIds, String action, Long weekEndId) {

        QueryParameter queryParameter = StoredProcedureParameter.with("P_WEEK_END_ID", weekEndId).and("P_ACTION", action)
                .and("P_EBILL_MANIFEST_IDS", ebillManifestIds);

        persistentContext.findEntities("DashBoardDto.closeCurrentWeek", queryParameter);


        return 0;
    }

    public int scrubCredits(Long weekEndId) {

        QueryParameter queryParameter = StoredProcedureParameter.with("P_WEEK_END_ID", weekEndId);
        persistentContext.findEntities("DashBoardDto.weeklyScrub", queryParameter);

        return 0;
    }

    public FileInfoDto insertFileInfo(String fileName, Long weekEndId) {

        QueryParameter queryParameter = StoredProcedureParameter.with("P_FILE_NAME", fileName).and("P_WEEK_END_ID", weekEndId);

        List<FileInfoDto> dtos = persistentContext.findEntities("DashBoardDto.insertFileInfo", queryParameter);
        FileInfoDto dto = null;
        if (null != dtos && dtos.size() > 0) {
            dto = dtos.get(0);
        }
        return dto;


    }

    public WeekStatusDto getWeekStatusInfo(String fromDate, String toDate) {

        QueryParameter queryParameter = StoredProcedureParameter.with("P_FROM_DATE", fromDate)
                .and("P_TO_DATE", toDate).and("P_ACTION_TYPE", null);

        List<WeekStatusDto> dtos = persistentContext.findEntities("WeekStatusDto.getWeekStatusInfo", queryParameter);
        WeekStatusDto dto = null;
        if (null != dtos && dtos.size() > 0) {
            dto = dtos.get(0);
        }
        return dto;
    }

}
