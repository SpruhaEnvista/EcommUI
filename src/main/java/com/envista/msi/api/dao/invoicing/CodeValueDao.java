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

    public String getClaimFlagByVoiceId(Long voiceId) {

        QueryParameter queryParameter = StoredProcedureParameter.with("P_VOICE_ID", voiceId);

        List<CodeValueDto> dtos = persistentContext.findEntities("CodeValueDto.getClaimFlagByVoiceId", queryParameter);
        String claimFlag = null;
        if (null != dtos && dtos.size() > 0) {
            claimFlag = dtos.get(0).getProperty2();
        }

        return claimFlag;
    }
    public List<CodeValueDto> getDataObjectFilter() {

        QueryParameter queryParameter = StoredProcedureParameter.with("P_CODE_GROUP_NAME", "GLOM Data Objects Modal Filter");

        List<CodeValueDto> codeValueDtos = persistentContext.findEntities(CodeValueDto.Config.DataObjectFilter.STORED_PROCEDURE_QUERY_NAME, queryParameter);

        return codeValueDtos;
    }

}
