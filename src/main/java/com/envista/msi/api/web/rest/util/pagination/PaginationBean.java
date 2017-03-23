package com.envista.msi.api.web.rest.util.pagination;

import java.io.Serializable;

/**
 * Created by Sujit kumar on 16/03/2017.
 */
public class PaginationBean implements Serializable {
    private Integer offset = 0;

    private Integer limit = 0;

    private Integer total = 0;

    private String sort;

    private Object data;

    public Integer getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
