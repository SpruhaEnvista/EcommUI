package com.envista.msi.api.web.rest.dto.freight.invoice;

import java.io.Serializable;

/**
 * Created by Sujit kumar on 20/07/2017.
 */
public class UserDefinedColumnsDto implements Serializable {
    private String columnName;
    private String labelsColumnName;
    private String originalColumnName;
    private String tableFieldName;
    private String jsonKey;
    private boolean checked;

    public UserDefinedColumnsDto() {
    }

    public UserDefinedColumnsDto(String columnName, String labelsColumnName, String originalColumnName, String tableFieldName) {
        this.columnName = columnName;
        this.labelsColumnName = labelsColumnName;
        this.originalColumnName = originalColumnName;
        this.tableFieldName = tableFieldName;
    }

    public UserDefinedColumnsDto(String columnName, String labelsColumnName, String originalColumnName, String tableFieldName, String jsonKey) {
        this.columnName = columnName;
        this.labelsColumnName = labelsColumnName;
        this.originalColumnName = originalColumnName;
        this.tableFieldName = tableFieldName;
        this.jsonKey = jsonKey;
    }

    public UserDefinedColumnsDto(String columnName, String labelsColumnName, String originalColumnName, String tableFieldName, String jsonKey, boolean checked) {
        this.columnName = columnName;
        this.labelsColumnName = labelsColumnName;
        this.originalColumnName = originalColumnName;
        this.tableFieldName = tableFieldName;
        this.jsonKey = jsonKey;
        this.checked = checked;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public String getLabelsColumnName() {
        return labelsColumnName;
    }

    public void setLabelsColumnName(String labelsColumnName) {
        this.labelsColumnName = labelsColumnName;
    }

    public String getOriginalColumnName() {
        return originalColumnName;
    }

    public void setOriginalColumnName(String originalColumnName) {
        this.originalColumnName = originalColumnName;
    }

    public String getTableFieldName() {
        return tableFieldName;
    }

    public void setTableFieldName(String tableFieldName) {
        this.tableFieldName = tableFieldName;
    }

    public String getJsonKey() {
        return jsonKey;
    }

    public void setJsonKey(String jsonKey) {
        this.jsonKey = jsonKey;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }
}
