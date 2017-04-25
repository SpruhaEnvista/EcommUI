package com.envista.msi.api.dao.invoicing;

import com.envista.msi.api.domain.PersistentContext;
import com.envista.msi.api.domain.util.QueryParameter;
import com.envista.msi.api.domain.util.StoredProcedureParameter;
import com.envista.msi.api.web.rest.dto.invoicing.CodeValueDto;
import org.springframework.stereotype.Repository;

import javax.inject.Inject;
import java.util.List;

/**
 * Created by KRISHNAREDDYM on 4/25/2017.
 */
@Repository("codeValueDao")
public class CodeValueDao {

    @Inject
    private PersistentContext persistentContext;

    public List<CodeValueDto> getCodeValues(Long carrierId, Long codeGroupId) {

        QueryParameter queryParameter = StoredProcedureParameter.with("P_CARRIER_ID", carrierId).and("P_CODE_GROUP_ID", codeGroupId);

        return persistentContext.findEntities("CodeValueDto.getCodeValues", queryParameter);
    }
}
