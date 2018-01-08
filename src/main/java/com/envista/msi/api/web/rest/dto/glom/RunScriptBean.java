package com.envista.msi.api.web.rest.dto.glom;

import java.io.Serializable;

/**
 * Created by Ramu Adepu on 29-12-2017.
 */
public class RunScriptBean implements Serializable {
    private long runId;
    private long scriptId;
    private int dataObjectId;
    private String comments;
    private String resultType;
    private int isActive;
    private String fromDate;
    private String toDate;
    private int userId;
    private String actionType;
    private int modeValue;

    public long getRunId() {
        return runId;
    }

    public void setRunId(long runId) {
        this.runId = runId;
    }

    public long getScriptId() {
        return scriptId;
    }

    public void setScriptId(long scriptId) {
        this.scriptId = scriptId;
    }

    public int getDataObjectId() {
        return dataObjectId;
    }

    public void setDataObjectId(int dataObjectId) {
        this.dataObjectId = dataObjectId;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getResultType() {
        return resultType;
    }

    public void setResultType(String resultType) {
        this.resultType = resultType;
    }

    public int getIsActive() {
        return isActive;
    }

    public void setIsActive(int isActive) {
        this.isActive = isActive;
    }

    public String getFromDate() {
        return fromDate;
    }

    public void setFromDate(String fromDate) {
        this.fromDate = fromDate;
    }

    public String getToDate() {
        return toDate;
    }

    public void setToDate(String toDate) {
        this.toDate = toDate;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getActionType() {
        return actionType;
    }

    public void setActionType(String actionType) {
        this.actionType = actionType;
    }
    public int getModeValue() {
        return modeValue;
    }

    public void setMode(int modeValue) {
        this.modeValue = modeValue;
    }
}