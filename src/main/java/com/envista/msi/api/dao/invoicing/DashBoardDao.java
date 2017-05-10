package com.envista.msi.api.dao.invoicing;

import com.envista.msi.api.domain.PersistentContext;
import com.envista.msi.api.domain.util.QueryParameter;
import com.envista.msi.api.domain.util.StoredProcedureParameter;
import com.envista.msi.api.web.rest.dto.invoicing.DashBoardDto;
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

    public int getPendingCredits(String fromDate, String toDate, String actionType) {

        QueryParameter queryParameter = StoredProcedureParameter.with("P_FROM_DATE", fromDate)
                .and("P_TO_DATE", toDate).and("P_ACTION_TYPE", actionType);

        List<DashBoardDto> dtos = persistentContext.findEntities("DashBoardDto.getPendingCreditsCount", queryParameter);

        int count = 0;
        if (null != dtos && dtos.size() > 0) {
            count = dtos.get(0).getPendingCreditsCount();
        }

        return count;
    }
}
