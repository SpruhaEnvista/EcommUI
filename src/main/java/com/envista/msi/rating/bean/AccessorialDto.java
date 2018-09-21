package com.envista.msi.rating.bean;

import java.math.BigDecimal;

/**
 * Created by admin on 9/20/2018.
 */
public class AccessorialDto {

    private Long id;

    private Long parentId;

    private BigDecimal rtrAmount;

    private String type;

    private String name;

    private String code;

    private Long ebillGffId;

    private BigDecimal baseDis;

    private BigDecimal earnedDis;

    private BigDecimal minMaxDis;

    private BigDecimal resDis;

    private BigDecimal dasDis;

    private BigDecimal fuleSurDis;

    private BigDecimal custFuleSurDis;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public BigDecimal getRtrAmount() {
        return rtrAmount;
    }

    public void setRtrAmount(BigDecimal rtrAmount) {
        this.rtrAmount = rtrAmount;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Long getEbillGffId() {
        return ebillGffId;
    }

    public void setEbillGffId(Long ebillGffId) {
        this.ebillGffId = ebillGffId;
    }

    public BigDecimal getBaseDis() {
        return baseDis;
    }

    public void setBaseDis(BigDecimal baseDis) {
        this.baseDis = baseDis;
    }

    public BigDecimal getEarnedDis() {
        return earnedDis;
    }

    public void setEarnedDis(BigDecimal earnedDis) {
        this.earnedDis = earnedDis;
    }

    public BigDecimal getMinMaxDis() {
        return minMaxDis;
    }

    public void setMinMaxDis(BigDecimal minMaxDis) {
        this.minMaxDis = minMaxDis;
    }

    public BigDecimal getResDis() {
        return resDis;
    }

    public void setResDis(BigDecimal resDis) {
        this.resDis = resDis;
    }

    public BigDecimal getDasDis() {
        return dasDis;
    }

    public void setDasDis(BigDecimal dasDis) {
        this.dasDis = dasDis;
    }

    public BigDecimal getFuleSurDis() {
        return fuleSurDis;
    }

    public void setFuleSurDis(BigDecimal fuleSurDis) {
        this.fuleSurDis = fuleSurDis;
    }

    public BigDecimal getCustFuleSurDis() {
        return custFuleSurDis;
    }

    public void setCustFuleSurDis(BigDecimal custFuleSurDis) {
        this.custFuleSurDis = custFuleSurDis;
    }
}
