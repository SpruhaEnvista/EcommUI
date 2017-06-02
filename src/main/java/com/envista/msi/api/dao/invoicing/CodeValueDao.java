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

    public List<CodeValueDto> getCodeValues(String groupName, String property1, String codeValue, String actionType) {

        QueryParameter queryParameter = StoredProcedureParameter.with("P_CODE_GROUP_NAME", groupName).and("P_PROPERTY_1", property1)
                .and("P_CODE_VALUE", codeValue).and("P_ACTION_TYPE", actionType);

        return persistentContext.findEntities("CodeValueDto.getCodeValues", queryParameter);
    }
}
