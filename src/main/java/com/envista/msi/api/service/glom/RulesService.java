package com.envista.msi.api.service.glom;

import com.envista.msi.api.dao.glom.RulesDao;
import com.envista.msi.api.web.rest.dto.glom.RulesBean;
import com.envista.msi.api.web.rest.dto.glom.RulesDto;
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
 * Created by KRISHNAREDDYM on 9/18/2017.
 */
@Service
@Transactional
public class RulesService {

    private final Logger log = LoggerFactory.getLogger(RulesService.class);

    @Inject
    private RulesDao dao;

    public List<RulesDto> getAll(RulesBean bean) {

        bean.setActionType("getall");

        return dao.getAll(bean);
    }

    public int getCount(RulesBean bean) {

        bean.setActionType("getcount");

        return dao.getCount(bean);
    }

    public RulesDto findById(RulesBean bean) {

        return dao.findById(bean);
    }

    public RulesDto insertOrUpdate(RulesBean bean) {

        return dao.insertOrUpdate(bean);
    }

    public RulesDto findByRuleName(String ruleName, String prevRuleName, Long scriptId) {

        return dao.findByRuleName(ruleName, prevRuleName, scriptId);
    }

    public int delete(String scriptIds) {

        return dao.delete(scriptIds);
    }

    public PaginationBean getAllPaginationData(RulesBean bean) throws Exception {
        Map<String, Object> paginationFilterMap = new HashMap<String, Object>();

        return new EnspirePagination() {
            @Override
            protected int getTotalRowCount(Map<String, Object> paginationFilterMap) {
                return getCount(bean);
            }

            @Override
            protected Object loadPaginationData(Map<String, Object> paginationFilterMap, int offset, int limit, String sort) throws Exception {
                return getAll(bean);
            }
        }.preparePaginationData(paginationFilterMap, bean.getOffset(), bean.getPageSize(), bean.getSortColumn());
    }
}
