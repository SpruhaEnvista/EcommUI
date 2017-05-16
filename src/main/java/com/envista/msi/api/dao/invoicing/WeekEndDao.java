package com.envista.msi.api.dao.invoicing;

import com.envista.msi.api.domain.PersistentContext;
import com.envista.msi.api.domain.util.QueryParameter;
import com.envista.msi.api.domain.util.StoredProcedureParameter;
import com.envista.msi.api.web.rest.dto.invoicing.WeekEndDto;
import org.springframework.stereotype.Repository;

import javax.inject.Inject;
import java.util.List;

/**
 * Created by admin on 5/15/2017.
 */
@Repository("weekEndDao")
public class WeekEndDao {

    @Inject
    private PersistentContext persistentContext;

    public List<WeekEndDto> getAll() {

        QueryParameter queryParameter = StoredProcedureParameter.with("P_WEEK_END_DATE", null).and("P_ACTION_TYPE", "getAll");
        return persistentContext.findEntities("WeekEndDto.getAll", queryParameter);
    }

    public WeekEndDto findByWeekEndDate(String weekEndDate) {

        QueryParameter queryParameter = StoredProcedureParameter.with("P_WEEK_END_DATE", weekEndDate).and("P_ACTION_TYPE", "getByWeekEndDate");
        List<WeekEndDto> dtos = persistentContext.findEntities("WeekEndDto.getAll", queryParameter);
        WeekEndDto weekEndDto = null;
        if (null != dtos && dtos.size() > 0) {
            weekEndDto = dtos.get(0);
        }
        return weekEndDto;
    }
}
