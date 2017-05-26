package com.envista.msi.api.web.rest.util.pagination;

import java.util.Map;

/**
 * This class represents an abstract implementation of pagination. Data returned by this pagination
 * is designed based on the enspire-ui pagination specification.
 *
 * Created by Sujit kumar on 16/03/2017.
 */
public abstract class EnspirePagination {
    /**
     * Get total record count.
     * @param paginationFilterMap
     * @return
     */
    protected abstract int getTotalRowCount(final Map<String, Object> paginationFilterMap);

    /**
     * Get actual data based on provided pagination criteria.
     * @param paginationFilterMap
     * @param offset
     * @param limit
     * @param sortOrder
     * @return
     * @throws Exception
     */
    protected abstract Object loadPaginationData(final Map<String, Object> paginationFilterMap, int offset, int limit, String sortOrder) throws Exception;

    /**
     * Get actual data based on provided pagination criteria.
     * @param paginationFilterMap
     * @param offset
     * @param limit
     * @return
     * @throws Exception
     */
    public final PaginationBean preparePaginationData(final Map<String, Object> paginationFilterMap, final Integer offset, final Integer limit) throws Exception {
        return preparePaginationData(paginationFilterMap, offset, limit, null);
    }

    /**
     * To prepare pagination data along with all the required parameters.
     * @param paginationFilterMap
     * @param offset
     * @param limit
     * @param sort
     * @return
     * @throws Exception
     */
    public final PaginationBean preparePaginationData(final Map<String, Object> paginationFilterMap, final Integer offset, final Integer limit, String sort) throws Exception {
        PaginationBean paginationBean = new PaginationBean();
        final int totalRecords = getTotalRowCount(paginationFilterMap);
        if(totalRecords <= 0) {
            return paginationBean;
        }

        paginationBean.setLimit(limit);
        paginationBean.setOffset(offset);
        paginationBean.setSort(null == sort ? "" : sort);
        paginationBean.setTotal(totalRecords);
        String sortOrder = (null == sort ? "" : sort.startsWith("-") ? " ORDER BY " + sort.replace("-", "") + " DESC " : " ORDER BY " + sort + " ASC ");
        paginationBean.setData(loadPaginationData(paginationFilterMap, offset, limit, sortOrder));
        return paginationBean;
    }
}
