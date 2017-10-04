package com.envista.msi.api.service.glom;

import com.envista.msi.api.dao.glom.ScriptDao;
import com.envista.msi.api.web.rest.dto.CustomerDto;
import com.envista.msi.api.web.rest.dto.glom.ScriptBean;
import com.envista.msi.api.web.rest.dto.glom.ScriptDto;
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
 * Created by KRISHNAREDDYM on 9/14/2017.
 */
@Service
@Transactional
public class ScriptService {

    private final Logger log = LoggerFactory.getLogger(ScriptService.class);

    @Inject
    private ScriptDao dao;

    public List<ScriptDto> getAll(ScriptBean bean) {

        bean.setActionType("getall");

        return dao.getAll(bean);
    }

    public int getCount(ScriptBean bean) {

        bean.setActionType("getcount");

        return dao.getCount(bean);
    }

    public ScriptDto findById(ScriptBean bean) {

        return dao.findById(bean);
    }

    public ScriptDto insertOrUpdate(ScriptBean bean) {

        return dao.insertOrUpdate(bean);
    }

    public ScriptDto findByScriptName(String scriptName, String prevScriptName, int customerId, int prevCustomerId) {

        return dao.findByScriptName(scriptName, prevScriptName, customerId, prevCustomerId);
    }

    public int delete(String scriptIds) {

        return dao.delete(scriptIds);
    }

    public PaginationBean getAllPaginationData(ScriptBean bean) throws Exception {
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


    public List<CustomerDto> getAllCustomers(int userId) {

        return dao.getAllCustomers(userId);
    }
}
