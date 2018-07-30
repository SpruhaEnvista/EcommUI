package com.envista.msi.rating.bean;

import java.util.Date;

/**
 * Created by Sujit kumar on 05/06/2018.
 */
public class ParcelRatingInputCriteriaBean {
    private Long taskId;
    private String fromShipDate;
    private String toShipDate;
    private String fromInvoiceDate;
    private String toInvoiceDate;
    private String customerId;
    private Long carrierId;
    private String status;
    private String rateSetName;
    private String rateTo;
    private String invoiceIds;
    private String trackingNumbers;
    private String thresholdValue;
    private String thresholdType;
    private String serviceLevel;

    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    public String getFromShipDate() {
        return fromShipDate;
    }

    public void setFromShipDate(String fromShipDate) {
        this.fromShipDate = fromShipDate;
    }

    public String getToShipDate() {
        return toShipDate;
    }

    public void setToShipDate(String toShipDate) {
        this.toShipDate = toShipDate;
    }

    public String getFromInvoiceDate() {
        return fromInvoiceDate;
    }

    public void setFromInvoiceDate(String fromInvoiceDate) {
        this.fromInvoiceDate = fromInvoiceDate;
    }

    public String getToInvoiceDate() {
        return toInvoiceDate;
    }

    public void setToInvoiceDate(String toInvoiceDate) {
        this.toInvoiceDate = toInvoiceDate;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
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

    public String getRateTo() {
        return rateTo;
    }

    public void setRateTo(String rateTo) {
        this.rateTo = rateTo;
    }

    public String getInvoiceIds() {
        return invoiceIds;
    }

    public void setInvoiceIds(String invoiceIds) {
        this.invoiceIds = invoiceIds;
    }

    public String getTrackingNumbers() {
        return trackingNumbers;
    }

    public void setTrackingNumbers(String trackingNumbers) {
        this.trackingNumbers = trackingNumbers;
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
