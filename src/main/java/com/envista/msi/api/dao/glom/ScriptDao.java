package com.envista.msi.api.dao.glom;

import com.envista.msi.api.domain.PersistentContext;
import com.envista.msi.api.domain.util.QueryParameter;
import com.envista.msi.api.domain.util.StoredProcedureParameter;
import com.envista.msi.api.web.rest.dto.CustomerDto;
import com.envista.msi.api.web.rest.dto.glom.ScriptBean;
import com.envista.msi.api.web.rest.dto.glom.ScriptDto;
import org.springframework.stereotype.Repository;

import javax.inject.Inject;
import java.util.List;
import java.util.Map;

/**
 * Created by KRISHNAREDDYM on 9/14/2017.
 */
@Repository("scriptDao")
public class ScriptDao {

    @Inject
    private PersistentContext persistentContext;


    public List<ScriptDto> getAll(Map<String, Object> paginationFilterMap, ScriptBean bean) {

        QueryParameter queryParameter = StoredProcedureParameter.with("P_SCRIPT_ID", bean.getScriptId())
                .and("P_CUSTOMER_ID", bean.getCustomerId()).and("P_OFFSET", bean.getOffset()).and("P_PAGE_SIZE", bean.getPageSize())
                .and("P_SORT_COLUMN", bean.getSortColumn()).and("P_ACTION_TYPE", bean.getActionType());

        List<ScriptDto> scriptDtos = persistentContext.findEntities("ScriptDto.getAll", queryParameter);

        return scriptDtos;
    }

    public int getCount(ScriptBean bean) {

        QueryParameter queryParameter = StoredProcedureParameter.with("P_SCRIPT_ID", bean.getScriptId())
                .and("P_CUSTOMER_ID", bean.getCustomerId()).and("P_OFFSET", bean.getOffset()).and("P_PAGE_SIZE", bean.getPageSize())
                .and("P_SORT_COLUMN", bean.getSortColumn()).and("P_ACTION_TYPE", bean.getActionType());

        List<ScriptDto> dtos = persistentContext.findEntities("ScriptDto.getCount", queryParameter);
        int count = 0;
        if (null != dtos && dtos.size() > 0) {
            count = dtos.size();
        }
        return count;
    }


    public ScriptDto findById(ScriptBean bean) {

        QueryParameter queryParameter = StoredProcedureParameter.with("P_SCRIPT_ID", bean.getScriptId())
                .and("P_CUSTOMER_ID", bean.getCustomerId()).and("P_OFFSET", bean.getOffset()).and("P_PAGE_SIZE", bean.getPageSize())
                .and("P_SORT_COLUMN", bean.getSortColumn()).and("P_ACTION_TYPE", bean.getActionType());

        List<ScriptDto> dtos = persistentContext.findEntities("ScriptDto.getAll", queryParameter);

        ScriptDto dto = null;
        if (dtos.size() > 0)
            dto = dtos.get(0);
        return dto;
    }

    public ScriptDto insertOrUpdate(ScriptBean bean) {

        QueryParameter queryParameter = StoredProcedureParameter.with("P_SCRIPT_ID", bean.getScriptId())
                .and("P_SCRIPT_NAME", bean.getScriptName()).and("P_CUSTOMER_ID", bean.getCustomerId())
                .and("P_DESCRIPTION", bean.getDescription()).and("P_EFFECTIVE_DATE", bean.getEffectiveDate())
                .and("P_EXPIRY_DATE", bean.getExpiryDate()).and("P_IS_ACTIVE", bean.getIsActive())
                .and("P_IS_FAVORITE", bean.getIsFavorite()).and("P_USER_ID", bean.getUserId())
                .and("P_ACTION_TYPE", bean.getActionType());


        return persistentContext.findEntityAndMapFields("ScriptDto.insertOrUpdate", queryParameter);
    }

    public ScriptDto findByScriptName(String scriptName, String prevScriptName) {

        QueryParameter queryParameter = StoredProcedureParameter.with("P_SCRIPT_NAME", scriptName)
                .and("P_PREV_SCRIPT_NAME", prevScriptName);

        // return persistentContext.findEntity("VoiceDto.findByVoiceName", queryParameter);

        List<ScriptDto> dtos = persistentContext.findEntities("ScriptDto.findByScriptName", queryParameter);

        ScriptDto dto = null;
        if (dtos.size() > 0)
            dto = dtos.get(0);

        return dto;

    }

    public int delete(String scriptIds) {

        QueryParameter queryParameter = StoredProcedureParameter.with("P_SCRIPT_IDS", scriptIds);

        List<ScriptDto> dtos = persistentContext.findEntities("ScriptDto.delete", queryParameter);

        int count = 0;
        if (dtos != null)
            count = dtos.size();

        return count;

    }

    public List<CustomerDto> getAllCustomers(int userId) {

        QueryParameter queryParameter = StoredProcedureParameter.with("P_USER_ID", userId);

        return persistentContext.findEntities("ScriptDto.getCustomers", queryParameter);
    }

}
