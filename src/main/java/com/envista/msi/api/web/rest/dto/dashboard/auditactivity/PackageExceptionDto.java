package com.envista.msi.api.web.rest.dto.dashboard.auditactivity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by Sujit kumar on 13/02/2017.
 */

@Entity
public class PackageExceptionDto implements Serializable{
    @Id
    @Column(name = "ID")
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "VALUE")
    private Double value;

    @Column(name = "BILL_DATE")
    private Date billDate;

    @Column(name = "AMOUNT")
    private Double amount;

    @Column(name = "COUNT_DEL_FLAG")
    private Integer deliveryFlagCount;

    public PackageExceptionDto(){}

    public PackageExceptionDto(Date billDate, Integer deliveryFlagCount) {
        this.billDate = billDate;
        this.deliveryFlagCount = deliveryFlagCount;
    }

    public PackageExceptionDto(Date billDate, Double amount) {
        this.billDate = billDate;
        this.amount = amount;
    }

    public PackageExceptionDto(Long id, String name, Double value) {
        this.id = id;
        this.name = name;
        this.value = value;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public Date getBillDate() {
        return billDate;
    }

    public void setBillDate(Date billDate) {
        this.billDate = billDate;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Integer getDeliveryFlagCount() {
        return deliveryFlagCount;
    }

    public void setDeliveryFlagCount(Integer deliveryFlagCount) {
        this.deliveryFlagCount = deliveryFlagCount;
    }
}
