package com.envista.msi.api.dao.invoicing;

import com.envista.msi.api.dao.type.GenericObject;
import com.envista.msi.api.domain.PersistentContext;
import com.envista.msi.api.domain.util.QueryParameter;
import com.envista.msi.api.domain.util.StoredProcedureParameter;
import com.envista.msi.api.web.rest.dto.invoicing.CreditResponseDto;
import org.springframework.stereotype.Repository;

import javax.inject.Inject;
import javax.persistence.ParameterMode;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by KRISHNAREDDYM on 5/11/2017.
 */
@Repository("creditResponseDao")
public class CreditResponseDao {

    @Inject
    private PersistentContext persistentContext;

    public int insert(List<CreditResponseDto> dtos) throws SQLException {

/*        int count = 0;
        for (CreditResponseDto dto : dtos) {
            QueryParameter queryParameter = StoredProcedureParameter.with("P_CREDIT_RESP_ID", 0L)
                    .and("P_CUSTOMER_CODE", dto.getCustomerCode()).and("P_TRACKING_NUMBER", dto.getTrackingNumber())
                    .and("P_NOTES", dto.getNotes()).and("P_STATUS", dto.getStatus()).and("P_FILE_INFO_ID", fileInfoId)
                    .and("P_FILE_TYPE_ID", dto.getFileTypeId()).and("P_CREDIT_CLASS", dto.getCreditClass()).and("P_ACTION_TYPE", "insert");

            persistentContext.findEntities("CreditResponseDto.insertOrUpdate", queryParameter);

            count++;

        }*/

        QueryParameter queryParameter = StoredProcedureParameter.withPosition(1, ParameterMode.IN, CreditResponseDto[].class, dtos)
                .andPosition(2, ParameterMode.REF_CURSOR, void.class, null);

        persistentContext.executeStoredProcedureListType("SHP_INV_INSERT_CREDIT_RESP_PRO", queryParameter, "CREDITRESPOBJECT", "CREDITRESPOBJECT_ARRAY");

        return dtos.size();
    }

}
