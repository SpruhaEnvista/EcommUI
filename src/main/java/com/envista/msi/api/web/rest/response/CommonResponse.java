package com.envista.msi.api.web.rest.response;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by Sujit kumar on 17/05/2017.
 */
public class CommonResponse {
    @JsonProperty("status_code")
    protected int statusCode;
    protected String message;
    protected Object data;

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
