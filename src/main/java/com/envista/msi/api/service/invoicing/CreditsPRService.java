package com.envista.msi.api.service.invoicing;

import com.envista.msi.api.dao.invoicing.CreditsPRDao;
import com.envista.msi.api.dao.invoicing.VoiceDao;
import com.envista.msi.api.web.rest.dto.invoicing.CreditsPRDto;
import com.envista.msi.api.web.rest.dto.invoicing.CreditsPRSearchBean;
import com.envista.msi.api.web.rest.dto.invoicing.VoiceSearchBean;
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
 * Created by KRISHNAREDDYM on 5/11/2017.
 */
@Service
@Transactional
public class CreditsPRService {

    private final Logger log = LoggerFactory.getLogger(CreditsPRService.class);

    @Inject
    private CreditsPRDao dao;

/*    public List<CreditsPRDto> search(CreditsPRSearchBean bean, int offset, int limit) {

        return dao.search(bean,offset,limit);
    }*/

    public int update(String ebillManifestIds, String actionType, String userName) {

        return dao.update(ebillManifestIds, actionType, userName);
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
