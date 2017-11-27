package com.envista.msi.api.dao.glom;

import com.envista.msi.api.domain.PersistentContext;
import com.envista.msi.api.domain.util.QueryParameter;
import com.envista.msi.api.domain.util.StoredProcedureParameter;
import com.envista.msi.api.web.rest.dto.glom.RulesBean;
import com.envista.msi.api.web.rest.dto.glom.RulesDto;
import org.springframework.stereotype.Repository;

import javax.inject.Inject;
import java.util.List;

/**
 * Created by KRISHNAREDDYM on 9/19/2017.
 */
@Repository("rulesDao")
public class RulesDao {

    @Inject
    private PersistentContext persistentContext;


    public List<RulesDto> getAll(RulesBean bean) {

        QueryParameter queryParameter = StoredProcedureParameter.with("P_RULE_ID", bean.getRuleId())
                .and("P_SCRIPT_ID", bean.getScriptId()).and("P_OFFSET", bean.getOffset()).and("P_PAGE_SIZE", bean.getPageSize())
                .and("P_SORT_COLUMN", bean.getSortColumn()).and("P_IS_ACTIVE", bean.getIsActive()).and("P_ACTION_TYPE", bean.getActionType());

        List<RulesDto> dtos = persistentContext.findEntities("RulesDto.getAll", queryParameter);

        return dtos;
    }

    public int getCount(RulesBean bean) {

        QueryParameter queryParameter = StoredProcedureParameter.with("P_RULE_ID", bean.getRuleId())
                .and("P_SCRIPT_ID", bean.getScriptId()).and("P_OFFSET", bean.getOffset()).and("P_PAGE_SIZE", bean.getPageSize())
                .and("P_SORT_COLUMN", bean.getSortColumn()).and("P_IS_ACTIVE", bean.getIsActive()).and("P_ACTION_TYPE", bean.getActionType());

        List<RulesDto> dtos = persistentContext.findEntities("RulesDto.getCount", queryParameter);
        int count = 0;
        if (null != dtos && dtos.size() > 0) {
            count = dtos.get(0).getTotalCount();
        }
        return count;
    }


    public RulesDto findById(RulesBean bean) {

        QueryParameter queryParameter = StoredProcedureParameter.with("P_RULE_ID", bean.getRuleId())
                .and("P_SCRIPT_ID", bean.getScriptId()).and("P_OFFSET", bean.getOffset()).and("P_PAGE_SIZE", bean.getPageSize())
                .and("P_SORT_COLUMN", bean.getSortColumn()).and("P_IS_ACTIVE", bean.getIsActive()).and("P_ACTION_TYPE", bean.getActionType());

        List<RulesDto> dtos = persistentContext.findEntities("RulesDto.getAll", queryParameter);

        RulesDto dto = null;
        if (dtos.size() > 0)
            dto = dtos.get(0);
        return dto;
    }

    public RulesDto insertOrUpdate(RulesBean bean) {

        QueryParameter queryParameter = StoredProcedureParameter.with("P_RULE_ID", bean.getRuleId())
                .and("P_SCRIPT_ID", bean.getScriptId()).and("P_RULE_NAME", bean.getRuleName())
                .and("P_DATA_OBJECT_ID", bean.getDataObjectId()).and("P_SEQUENCE_ID", bean.getSequenceId())
                .and("P_SCOPE_NAME", bean.getScopeName()).and("P_IDENTITY_NAME", bean.getIdentityName())
                .and("P_COMMENTS", bean.getComments()).and("P_ACTION", bean.getAction())
                .and("P_COLUMN_1", bean.getColumn1()).and("P_COLUMN_2", bean.getColumn2())
                .and("P_COLUMN_3", bean.getColumn3()).and("P_COLUMN_4", bean.getColumn4())
                .and("P_COLUMN_5", bean.getColumn5()).and("P_COLUMN_6", bean.getColumn6())
                .and("P_COLUMN_7", bean.getColumn7()).and("P_COLUMN_8", bean.getColumn8())
                .and("P_COLUMN_9", bean.getColumn9()).and("P_COLUMN_10", bean.getColumn10())
                .and("P_IS_ACTIVE", bean.getIsActive()).and("P_USER_ID", bean.getUserId())
                .and("P_INSERT_TYPE", bean.getInsertType()).and("P_ACTION_TYPE", bean.getActionType());


        return persistentContext.findEntityAndMapFields("RulesDto.insertOrUpdate", queryParameter);
    }

    public RulesDto findByRuleName(String ruleName, String prevRuleName, Long scriptId) {

        QueryParameter queryParameter = StoredProcedureParameter.with("P_RULE_NAME", ruleName)
                .and("P_PREV_RULE_NAME", prevRuleName).and("P_SCRIPT_ID", scriptId);


        List<RulesDto> dtos = persistentContext.findEntities("RulesDto.findByRuleName", queryParameter);

        RulesDto dto = null;
        if (dtos.size() > 0)
            dto = dtos.get(0);

        return dto;

    }

    public int delete(String ruleIds) {

        QueryParameter queryParameter = StoredProcedureParameter.with("P_RULE_IDS", ruleIds);

        List<RulesDto> dtos = persistentContext.findEntities("RulesDto.delete", queryParameter);

        int count = 0;
        if (dtos != null)
            count = dtos.size();

        return count;

    }


}
