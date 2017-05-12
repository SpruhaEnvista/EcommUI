package com.envista.msi.api.service.invoicing;

import com.envista.msi.api.dao.invoicing.CreditsPRDao;
import com.envista.msi.api.dao.invoicing.VoiceDao;
import com.envista.msi.api.web.rest.dto.invoicing.CreditsPRDto;
import com.envista.msi.api.web.rest.dto.invoicing.CreditsPRSearchBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.List;

/**
 * Created by KRISHNAREDDYM on 5/11/2017.
 */
@Service
@Transactional
public class CreditsPRService {

    private final Logger log = LoggerFactory.getLogger(CreditsPRService.class);

    @Inject
    private CreditsPRDao dao;

    public List<CreditsPRDto> search(CreditsPRSearchBean bean) {

        return dao.search(bean);
    }

    public int update(String ebillManifestIds, String actionType) {

        return dao.update(ebillManifestIds, actionType);
    }
}
