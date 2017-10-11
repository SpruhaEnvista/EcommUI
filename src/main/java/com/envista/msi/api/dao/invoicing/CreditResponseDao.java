package com.envista.msi.api.dao.invoicing;

import com.envista.msi.api.domain.PersistentContext;
import com.envista.msi.api.domain.util.QueryParameter;
import com.envista.msi.api.domain.util.StoredProcedureParameter;
import com.envista.msi.api.web.rest.dto.invoicing.CreditResponseDto;
import org.springframework.stereotype.Repository;

import javax.inject.Inject;
import java.util.List;

/**
 * Created by KRISHNAREDDYM on 5/11/2017.
 */
@Repository("creditResponseDao")
public class CreditResponseDao {

    @Inject
    private PersistentContext persistentContext;

    public int insert(List<CreditResponseDto> dtos) {

        int count = 0;
        for (CreditResponseDto dto : dtos) {
            QueryParameter queryParameter = StoredProcedureParameter.with("P_CREDIT_RESP_ID", 0L)
                    .and("P_CUSTOMER_CODE", dto.getCustomerCode()).and("P_TRACKING_NUMBER", dto.getTrackingNumber())
                    .and("P_NOTES", dto.getNotes()).and("P_STATUS", dto.getStatus()).and("P_FILE_INFO_ID", dto.getFileInfoId())
                    .and("P_FILE_TYPE_ID", dto.getFileTypeId()).and("P_ACTION_TYPE", "insert");

            persistentContext.findEntities("CreditResponseDto.insertOrUpdate", queryParameter);

            count++;

        }
        return count;
    }

}
