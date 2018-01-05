package com.envista.msi.api.dao.glom;

import com.envista.msi.api.domain.PersistentContext;
import com.envista.msi.api.domain.util.QueryParameter;
import com.envista.msi.api.domain.util.StoredProcedureParameter;
import com.envista.msi.api.web.rest.dto.glom.RunScriptBean;
import com.envista.msi.api.web.rest.dto.glom.RunScriptDto;
import org.springframework.stereotype.Repository;
import javax.inject.Inject;
/**
 * Created by Ramu Adepu on 29-12-2017.
 */
@Repository("runScriptDao")
public class RunScriptDao {

    @Inject
    private PersistentContext persistentContext;
    public RunScriptDto insertRunScript(RunScriptBean bean) {

        QueryParameter queryParameter = StoredProcedureParameter.with("P_RUN_ID", bean.getRunId()).and("P_MODE_VALUE", bean.getModeValue())
                .and("P_SCRIPT_ID", bean.getScriptId()).and("P_DATA_OBJECT_ID", bean.getDataObjectId())
                .and("P_COMMENTS", bean.getComments()).and("P_RESULT_TYPE", bean.getResultType())
                .and("P_IS_ACTIVE", bean.getIsActive()).and("P_FROM_DATE", bean.getFromDate())
                .and("P_TO_DATE", bean.getToDate()).and("P_USER_ID", bean.getUserId())
                .and("P_ACTION_TYPE", bean.getActionType());

        return persistentContext.findEntityAndMapFields("RunScriptDto.insertRunScript", queryParameter);
    }


}