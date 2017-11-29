package com.envista.msi.api.dao.glom;

import com.envista.msi.api.domain.PersistentContext;
import com.envista.msi.api.domain.util.QueryParameter;
import com.envista.msi.api.domain.util.StoredProcedureParameter;
import com.envista.msi.api.web.rest.dto.glom.DataObjectDto;
import org.springframework.stereotype.Repository;

import javax.inject.Inject;
import java.util.List;

/**
 * Created by KRISHNAREDDYM on 11/29/2017.
 */
@Repository("dataObjectDao")
public class DataObjectDao {

    @Inject
    private PersistentContext persistentContext;


    public List<DataObjectDto> getAll(int offSet, int pageSize, int isActive, String sortColumn) {

        QueryParameter queryParameter = StoredProcedureParameter.with("P_DATA_OBJECT_ID", 0L)
                .and("P_OFFSET", offSet).and("P_PAGE_SIZE", pageSize)
                .and("P_SORT_COLUMN", sortColumn).and("P_IS_ACTIVE", isActive).and("P_ACTION_TYPE", "getall");

        List<DataObjectDto> dataObjectDtos = persistentContext.findEntities("DataObjectDto.getAll", queryParameter);

        return dataObjectDtos;
    }

    public int getCount(int isActive) {

        QueryParameter queryParameter = StoredProcedureParameter.with("P_DATA_OBJECT_ID", 0L)
                .and("P_OFFSET", 0).and("P_PAGE_SIZE", 0)
                .and("P_SORT_COLUMN", null).and("P_IS_ACTIVE", isActive).and("P_ACTION_TYPE", "getcount");

        List<DataObjectDto> dtos = persistentContext.findEntities("DataObjectDto.getCount", queryParameter);
        int count = 0;
        if (null != dtos && dtos.size() > 0) {
            count = dtos.get(0).getTotalCount();
        }
        return count;
    }
}
