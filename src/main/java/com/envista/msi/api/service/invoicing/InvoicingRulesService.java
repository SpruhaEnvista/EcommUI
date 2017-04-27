package com.envista.msi.api.service.invoicing;

import com.envista.msi.api.dao.invoicing.CustomOmitsDao;
import com.envista.msi.api.dao.invoicing.InvoicingRulesDao;
import com.envista.msi.api.web.rest.dto.invoicing.InvoicingRuleDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.List;

/**
 * Created by KRISHNAREDDYM on 4/27/2017.
 */
@Service
@Transactional
public class InvoicingRulesService {

    private final Logger log = LoggerFactory.getLogger(InvoicingRulesService.class);

    @Inject
    private InvoicingRulesDao dao;

    public List<InvoicingRuleDto> findByCustomerIds(String customOmitIds) {

        return dao.findByCustomerIds(customOmitIds);
    }

    public List<InvoicingRuleDto> findById(Long id) {

        return dao.findById(id);
    }
}
