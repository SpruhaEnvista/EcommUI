package com.envista.msi.api.web.rest.util.pagination;

import java.util.Map;

/**
 * This class represents an generic implementation of pagination. The data returned by this pagination
 * is designed based on the enspire-ui related pagination.
 *
 * Created by Sujit kumar on 16/03/2017.
 */
public abstract class EnspirePagination {
    protected abstract int getTotalRowCount(final Map<String, Object> paginationFilterMap);

    protected abstract Object loadPaginationData(final Map<String, Object> paginationFilterMap, int offset, int limit, String sortOrder) throws Exception;

    public final PaginationBean preparePaginationData(final Map<String, Object> paginationFilterMap, final Integer offset, final Integer limit) throws Exception {
        return preparePaginationData(paginationFilterMap, offset, limit, null);
    }


    public final PaginationBean preparePaginationData(final Map<String, Object> paginationFilterMap, final Integer offset, final Integer limit, String sortOrder) throws Exception {
        PaginationBean paginationBean = new PaginationBean();
        final int totalRecords = getTotalRowCount(paginationFilterMap);
        if(totalRecords <= 0) {
            return paginationBean;
        }

        paginationBean.setLimit(limit);
        paginationBean.setOffset(offset);
        paginationBean.setSort(null == sortOrder ? "" : sortOrder);
        paginationBean.setTotal(totalRecords);
        paginationBean.setData(loadPaginationData(paginationFilterMap, offset, limit, sortOrder));
        return paginationBean;
    }
}
