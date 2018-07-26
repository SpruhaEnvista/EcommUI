package com.envista.msi.rating.entity;

import java.util.Date;

/**
 * Created by Sujit kumar on 04/06/2018.
 */
public class ParcelRatingInputCriteriaDto {
    private Long id;
    private Long taskId;
    private Date fromShipDate;
    private Date toShipDate;
    private Date fromInvoiceDate;
    private Date toInvoiceDate;
    private Long customerId;
    private Long carrierId;
    private String status;
    private String rateSetName;
    private String thresholdValue;
    private String thresholdType;
    private String serviceLevel;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    public Date getFromShipDate() {
        return fromShipDate;
    }

    public void setFromShipDate(Date fromShipDate) {
        this.fromShipDate = fromShipDate;
    }

    public Date getToShipDate() {
        return toShipDate;
    }

    public void setToShipDate(Date toShipDate) {
        this.toShipDate = toShipDate;
    }

    public Date getFromInvoiceDate() {
        return fromInvoiceDate;
    }

    public void setFromInvoiceDate(Date fromInvoiceDate) {
        this.fromInvoiceDate = fromInvoiceDate;
    }

    public Date getToInvoiceDate() {
        return toInvoiceDate;
    }

    public void setToInvoiceDate(Date toInvoiceDate) {
        this.toInvoiceDate = toInvoiceDate;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Long getCarrierId() {
        return carrierId;
    }

    public void setCarrierId(Long carrierId) {
        this.carrierId = carrierId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRateSetName() {
        return rateSetName;
    }

    public void setRateSetName(String rateSetName) {
        this.rateSetName = rateSetName;
    }

    public String getThresholdValue() {
        return thresholdValue;
    }

    public void setThresholdValue(String thresholdValue) {
        this.thresholdValue = thresholdValue;
    }

    public String getThresholdType() {
        return thresholdType;
    }

    public void setThresholdType(String thresholdType) {
        this.thresholdType = thresholdType;
    }

    public String getServiceLevel() {
        return serviceLevel;
    }

    public void setServiceLevel(String serviceLevel) {
        this.serviceLevel = serviceLevel;
    }
}
