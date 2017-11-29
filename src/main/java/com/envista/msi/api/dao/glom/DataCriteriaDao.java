package com.envista.msi.api.dao.glom;

import com.envista.msi.api.domain.PersistentContext;
import com.envista.msi.api.domain.util.QueryParameter;
import com.envista.msi.api.domain.util.StoredProcedureParameter;
import com.envista.msi.api.web.rest.dto.glom.DataCriteriaDto;
import org.springframework.stereotype.Repository;

import javax.inject.Inject;
import java.util.List;

/**
 * Created by KRISHNAREDDYM on 11/29/2017.
 */
@Repository("dataCriteriaDao")
public class DataCriteriaDao {

    @Inject
    private PersistentContext persistentContext;

    public List<DataCriteriaDto> findById(long dataObjectId) {

        QueryParameter queryParameter = StoredProcedureParameter.with("P_DATA_OBJECT_ID", dataObjectId).and("P_ACTION_TYPE", "getByDataObjectId");

        List<DataCriteriaDto> dtos = persistentContext.findEntities("DataCriteriaDto.getByDataObjectId", queryParameter);

        return dtos;

    }
}
