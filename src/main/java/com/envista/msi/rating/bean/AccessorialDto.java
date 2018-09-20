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
}
