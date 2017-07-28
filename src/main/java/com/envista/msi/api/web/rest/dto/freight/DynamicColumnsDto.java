package com.envista.msi.api.web.rest.dto.freight;

import java.io.Serializable;

/**
 * Created by Sujit kumar on 26/07/2017.
 */

public class DynamicColumnsDto implements Serializable {
    private Long id;

    private Long filterId = 0L;

    private Long userId = 0L;

    private String userName;

    private String includedColumns;

    private String excludedColumns;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getFilterId() {
        return filterId;
    }

    public void setFilterId(Long filterId) {
        this.filterId = filterId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getIncludedColumns() {
        return includedColumns;
    }

    public void setIncludedColumns(String includedColumns) {
        this.includedColumns = includedColumns;
    }

    public String getExcludedColumns() {
        return excludedColumns;
    }

    public void setExcludedColumns(String excludedColumns) {
        this.excludedColumns = excludedColumns;
    }
}
