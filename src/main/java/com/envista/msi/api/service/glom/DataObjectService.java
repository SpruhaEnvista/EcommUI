package com.envista.msi.api.service.glom;

import com.envista.msi.api.dao.glom.DataObjectDao;
import com.envista.msi.api.web.rest.dto.glom.DataObjectBean;
import com.envista.msi.api.web.rest.dto.glom.DataObjectDto;
import com.envista.msi.api.web.rest.dto.glom.GlmGenericTypeBean;
import com.envista.msi.api.web.rest.util.InvoicingUtilities;
import com.envista.msi.api.web.rest.util.pagination.EnspirePagination;
import com.envista.msi.api.web.rest.util.pagination.PaginationBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by KRISHNAREDDYM on 11/29/2017.
 */
@Service
@Transactional
public class DataObjectService {

    private final Logger log = LoggerFactory.getLogger(DataObjectService.class);

    @Inject
    private DataObjectDao dao;

    public List<DataObjectDto> getAll(int offSet, int pageSize, int isActive, String sortColumn) {

        return dao.getAll(offSet, pageSize, isActive, sortColumn);
    }

    public int getCount(int isActive) {

        return dao.getCount(isActive);
    }

    public void insertOrUpdate(DataObjectBean bean) throws SQLException {

        dao.insertOrUpdate(bean, InvoicingUtilities.prepareDataCriteria(bean.getCriteriaDtos()));
    }

    public PaginationBean getAllPaginationData(int offSet, int pageSize, int isActive, String sortColumn) throws Exception {
        Map<String, Object> paginationFilterMap = new HashMap<String, Object>();

        return new EnspirePagination() {
            @Override
            protected int getTotalRowCount(Map<String, Object> paginationFilterMap) {
                return getCount(isActive);
            }

            @Override
            protected Object loadPaginationData(Map<String, Object> paginationFilterMap, int offset, int limit, String sort) throws Exception {
                return getAll(offSet, pageSize, isActive, sortColumn);
            }
        }.preparePaginationData(paginationFilterMap, offSet, pageSize, sortColumn);
    }
    public int delete(String dataObjectId) {

        return dao.delete(dataObjectId);
    }

    public List<DataObjectDto> getActiveDataObjects() {

        return dao.getActiveDataObjects();
    }
}
