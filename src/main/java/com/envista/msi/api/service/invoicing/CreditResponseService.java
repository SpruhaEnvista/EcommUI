package com.envista.msi.api.service.invoicing;

import com.envista.msi.api.dao.invoicing.CreditResponseDao;
import com.envista.msi.api.web.rest.dto.invoicing.CreditResponseDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.List;

/**
 * Created by admin on 5/11/2017.
 */
@Service
@Transactional
public class CreditResponseService {

    @Inject
    private CreditResponseDao dao;

    public int insert(List<CreditResponseDto> dtos) {

        return dao.insert(dtos);
    }
}
