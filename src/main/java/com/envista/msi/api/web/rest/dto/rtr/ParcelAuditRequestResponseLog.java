package com.envista.msi.api.web.rest.dto.rtr;

import java.io.Serializable;

/**
 * Created by Sujit kumar on 09/08/2017.
 */
public class ParcelAuditRequestResponseLog implements Serializable {
    private Long id;
    private String requestXml;
    private String requestXml1;
    private String responseXml;
    private String responseXml1;
    private String responseXml2;
    private String createUser;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRequestXml() {
        return requestXml;
    }

    public void setRequestXml(String requestXml) {
        this.requestXml = requestXml;
    }

    public String getRequestXml1() {
        return requestXml1;
    }

    public void setRequestXml1(String requestXml1) {
        this.requestXml1 = requestXml1;
    }

    public String getResponseXml() {
        return responseXml;
    }

    public void setResponseXml(String responseXml) {
        this.responseXml = responseXml;
    }

    public String getResponseXml1() {
        return responseXml1;
    }

    public void setResponseXml1(String responseXml1) {
        this.responseXml1 = responseXml1;
    }

    public String getResponseXml2() {
        return responseXml2;
    }

    public void setResponseXml2(String responseXml2) {
        this.responseXml2 = responseXml2;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }
}
