package com.envista.msi.rating.bean;

import java.io.Serializable;

/**
 * Created by KRISHNAREDDYM on 8/6/2018.
 */
public class ServiceFlagAccessorialBean implements Serializable {

    private Long lookUpId;

    private String moduleName;

    private String columnName;

    private String lookUpCode;

    private String lookUpValue;

    private String customDefined1;

    private String customDefined2;


    public Long getLookUpId() {
        return lookUpId;
    }

    public void setLookUpId(Long lookUpId) {
        this.lookUpId = lookUpId;
    }

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public String getLookUpCode() {
        return lookUpCode;
    }

    public void setLookUpCode(String lookUpCode) {
        this.lookUpCode = lookUpCode;
    }

    public String getLookUpValue() {
        return lookUpValue;
    }

    public void setLookUpValue(String lookUpValue) {
        this.lookUpValue = lookUpValue;
    }

    public String getCustomDefined1() {
        return customDefined1;
    }

    public void setCustomDefined1(String customDefined1) {
        this.customDefined1 = customDefined1;
    }

    public String getCustomDefined2() {
        return customDefined2;
    }

    public void setCustomDefined2(String customDefined2) {
        this.customDefined2 = customDefined2;
    }
}
