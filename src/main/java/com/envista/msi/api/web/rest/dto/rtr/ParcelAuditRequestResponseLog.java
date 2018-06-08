package com.envista.msi.api.web.rest.dto.rtr;

import java.io.Serializable;

/**
 * Created by Sujit kumar on 09/08/2017.
 */
public class ParcelAuditRequestResponseLog implements Serializable {
    private Long id;
    private String entityIds;
    private String tableName;
    private String requestXml1;
    private String requestXml2;
    private String requestXml3;
    private String responseXml1;
    private String responseXml2;
    private String responseXml3;
    private String createUser;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEntityIds() {
        return entityIds;
    }

    public void setEntityIds(String entityIds) {
        this.entityIds = entityIds;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getRequestXml2() {
        return requestXml2;
    }

    public void setRequestXml2(String requestXml2) {
        this.requestXml2 = requestXml2;
    }

    public String getRequestXml1() {
        return requestXml1;
    }

    public void setRequestXml1(String requestXml1) {
        this.requestXml1 = requestXml1;
    }

    public String getResponseXml3() {
        return responseXml3;
    }

    public void setResponseXml3(String responseXml3) {
        this.responseXml3 = responseXml3;
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

    public String getRequestXml3() {
        return requestXml3;
    }

    public void setRequestXml3(String requestXml3) {
        this.requestXml3 = requestXml3;
    }
}
