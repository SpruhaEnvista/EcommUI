package com.envista.msi.api.service.invoicing;

import com.envista.msi.api.dao.invoicing.CreditResponseDao;
import com.envista.msi.api.web.rest.dto.invoicing.CreditResponseDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by admin on 5/11/2017.
 */
@Service
@Transactional
public class CreditResponseService {

    @Inject
    private CreditResponseDao dao;

    public int insert(List<CreditResponseDto> dtos, Long fileInfoId) throws SQLException {

        for (CreditResponseDto dto : dtos) {
            dto.setId(0L);
            dto.setFileInfoId(fileInfoId);
            dto.setInsertCount(0);
        }

        return dao.insert(dtos);
    }
}
