package com.envista.msi.api.web.rest.dto.glom;

import java.io.Serializable;
import java.util.List;

/**
 * Created by KRISHNAREDDYM on 11/30/2017.
 */
public class DataObjectBean implements Serializable {

    private Long dataObjectId;

    private String dataObjectName;

    private String description;

    private Long userId;

    private String actionType;

    private List<DataCriteriaDto> criteriaDtos;

    public Long getDataObjectId() {
        return dataObjectId;
    }

    public void setDataObjectId(Long dataObjectId) {
        this.dataObjectId = dataObjectId;
    }

    public String getDataObjectName() {
        return dataObjectName;
    }

    public void setDataObjectName(String dataObjectName) {
        this.dataObjectName = dataObjectName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getActionType() {
        return actionType;
    }

    public void setActionType(String actionType) {
        this.actionType = actionType;
    }

    public List<DataCriteriaDto> getCriteriaDtos() {
        return criteriaDtos;
    }

    public void setCriteriaDtos(List<DataCriteriaDto> criteriaDtos) {
        this.criteriaDtos = criteriaDtos;
    }
}
