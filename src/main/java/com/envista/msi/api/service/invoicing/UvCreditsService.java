package com.envista.msi.api.service.invoicing;

import com.envista.msi.api.dao.invoicing.UvCreditsDao;
import com.envista.msi.api.web.rest.dto.invoicing.CreditsPRDto;
import com.envista.msi.api.web.rest.dto.invoicing.CreditsPRSearchBean;
import com.envista.msi.api.web.rest.dto.invoicing.UvCreditsDto;
import com.envista.msi.api.web.rest.dto.invoicing.UvVoiceUpdateBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.List;

/**
 * Created by KRISHNAREDDYM on 5/12/2017.
 */
@Service
@Transactional
public class UvCreditsService {

    private final Logger log = LoggerFactory.getLogger(UvCreditsService.class);

    @Inject
    private UvCreditsDao dao;

    public List<UvCreditsDto> search(CreditsPRSearchBean bean) {

        return dao.search(bean);
    }

    public int update(List<UvVoiceUpdateBean> beans) {
        int count = 0;
        for (UvVoiceUpdateBean bean : beans) {
            dao.update(bean);
            count++;
        }
        dao.updateDashBoardSummary();
        return count;
    }

}
