package com.envista.msi.api.service.invoicing;

import com.envista.msi.api.dao.invoicing.UvCreditsDao;
import com.envista.msi.api.web.rest.dto.invoicing.CreditsPRDto;
import com.envista.msi.api.web.rest.dto.invoicing.CreditsPRSearchBean;
import com.envista.msi.api.web.rest.dto.invoicing.UvCreditsDto;
import com.envista.msi.api.web.rest.dto.invoicing.UvVoiceUpdateBean;
import com.envista.msi.api.web.rest.util.pagination.EnspirePagination;
import com.envista.msi.api.web.rest.util.pagination.PaginationBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by KRISHNAREDDYM on 5/12/2017.
 */
@Service
@Transactional
public class UvCreditsService {

    private final Logger log = LoggerFactory.getLogger(UvCreditsService.class);

    @Inject
    private UvCreditsDao dao;

/*    public List<UvCreditsDto> search(CreditsPRSearchBean bean) {

        return dao.search(bean);
    }*/

    public int update(List<UvVoiceUpdateBean> beans) {
        int count = 0;
        for (UvVoiceUpdateBean bean : beans) {
            dao.update(bean);
            count++;
        }
        dao.updateDashBoardSummary();
        return count;
    }

    public PaginationBean getSearchPaginationData(CreditsPRSearchBean filter, int offset, int limit) throws Exception {
        Map<String, Object> paginationFilterMap = new HashMap<String, Object>();
        paginationFilterMap.put("filter", filter);

        return new EnspirePagination() {
            @Override
            protected int getTotalRowCount(Map<String, Object> paginationFilterMap) {
                return dao.getSearchCount(filter);
            }

            @Override
            protected Object loadPaginationData(Map<String, Object> paginationFilterMap, int offset, int limit, String sortOrder) throws Exception {
                return dao.search(filter, paginationFilterMap, offset, limit);
            }
        }.preparePaginationData(paginationFilterMap, offset, limit);
    }

}
