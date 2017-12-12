package com.envista.msi.api.dao.glom;

import com.envista.msi.api.domain.PersistentContext;
import com.envista.msi.api.domain.util.QueryParameter;
import com.envista.msi.api.domain.util.StoredProcedureParameter;
import com.envista.msi.api.web.rest.dto.glom.DataObjectBean;
import com.envista.msi.api.web.rest.dto.glom.DataObjectDto;
import com.envista.msi.api.web.rest.dto.glom.GlmGenericTypeBean;
import org.springframework.stereotype.Repository;

import javax.inject.Inject;
import javax.persistence.ParameterMode;
import java.sql.SQLException;
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

    public void insertOrUpdate(DataObjectBean bean, List<GlmGenericTypeBean> genericTypeBeans) throws SQLException {

        QueryParameter queryParameter = StoredProcedureParameter.withPosition(1, ParameterMode.IN, Long.class, bean.getDataObjectId())
                .andPosition(2, ParameterMode.IN, String.class, bean.getDataObjectName())
                .andPosition(3, ParameterMode.IN, String.class, bean.getDescription())
                .andPosition(4, ParameterMode.IN, Long.class, bean.getUserId())
                .andPosition(5, ParameterMode.IN, GlmGenericTypeBean[].class, genericTypeBeans)
                .andPosition(6, ParameterMode.IN, String.class, bean.getActionType())
                .andPosition(7, ParameterMode.REF_CURSOR, void.class, null);

        persistentContext.executeStoredProcedureGlmGeneric("SHP_GLM_INST_UPD_DATA_OBJ_PRO", queryParameter, "GLM_GENERIC_OBJ", "GLM_GENERIC_OBJ_ARRAY");

    }
    public int delete(String DataObjectId) {

        QueryParameter queryParameter = StoredProcedureParameter.with("P_DATA_OBJECT_ID", DataObjectId);

        List<DataObjectDto> dtos = persistentContext.findEntities("DataObjectDto.delete", queryParameter);

        int count = 0;
        if (dtos != null)
            count = dtos.size();

        return count;

    }

}
