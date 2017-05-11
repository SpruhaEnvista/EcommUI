package com.envista.msi.api.web.rest.dto.invoicing;

import java.io.Serializable;

/**
 * Created by KRISHNAREDDYM on 5/11/2017.
 */
public class CreditsPRSearchBean implements Serializable {

    private Long businessPartnerId;

    private String customerIds;

    private String savedFilter;

    private String invoicingStatus;

    private Long invCatagoryId;

    private String invWeekEndDate;

    private Long invoiceModeId;

    private Long carrierId;

    private Long creditClassId;

    private String omitFlag;

    private String reviewFlag;

    private String createDate;

    private String invoiceDate;

    private String closeDate;

    private String invoiceNumbers;

    private String trackingNumbers;

    private String internalKeyIds;

    private Long invoiceMethodId;

    private String payRunNos;

    private String controlNums;

    private String adjReasons;

    private String invComments;

    public Long getBusinessPartnerId() {
        return businessPartnerId;
    }

    public void setBusinessPartnerId(Long businessPartnerId) {
        this.businessPartnerId = businessPartnerId;
    }

    public String getCustomerIds() {
        return customerIds;
    }

    public void setCustomerIds(String customerIds) {
        this.customerIds = customerIds;
    }

    public String getSavedFilter() {
        return savedFilter;
    }

    public void setSavedFilter(String savedFilter) {
        this.savedFilter = savedFilter;
    }

    public String getInvoicingStatus() {
        return invoicingStatus;
    }

    public void setInvoicingStatus(String invoicingStatus) {
        this.invoicingStatus = invoicingStatus;
    }

    public Long getInvCatagoryId() {
        return invCatagoryId;
    }

    public void setInvCatagoryId(Long invCatagoryId) {
        this.invCatagoryId = invCatagoryId;
    }

    public String getInvWeekEndDate() {
        return invWeekEndDate;
    }

    public void setInvWeekEndDate(String invWeekEndDate) {
        this.invWeekEndDate = invWeekEndDate;
    }

    public Long getInvoiceModeId() {
        return invoiceModeId;
    }

    public void setInvoiceModeId(Long invoiceModeId) {
        this.invoiceModeId = invoiceModeId;
    }

    public Long getCarrierId() {
        return carrierId;
    }

    public void setCarrierId(Long carrierId) {
        this.carrierId = carrierId;
    }

    public Long getCreditClassId() {
        return creditClassId;
    }

    public void setCreditClassId(Long creditClassId) {
        this.creditClassId = creditClassId;
    }

    public String getOmitFlag() {
        return omitFlag;
    }

    public void setOmitFlag(String omitFlag) {
        this.omitFlag = omitFlag;
    }

    public String getReviewFlag() {
        return reviewFlag;
    }

    public void setReviewFlag(String reviewFlag) {
        this.reviewFlag = reviewFlag;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(String invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public String getCloseDate() {
        return closeDate;
    }

    public void setCloseDate(String closeDate) {
        this.closeDate = closeDate;
    }

    public String getInvoiceNumbers() {
        return invoiceNumbers;
    }

    public void setInvoiceNumbers(String invoiceNumbers) {
        this.invoiceNumbers = invoiceNumbers;
    }

    public String getTrackingNumbers() {
        return trackingNumbers;
    }

    public void setTrackingNumbers(String trackingNumbers) {
        this.trackingNumbers = trackingNumbers;
    }

    public String getInternalKeyIds() {
        return internalKeyIds;
    }

    public void setInternalKeyIds(String internalKeyIds) {
        this.internalKeyIds = internalKeyIds;
    }

    public Long getInvoiceMethodId() {
        return invoiceMethodId;
    }

    public void setInvoiceMethodId(Long invoiceMethodId) {
        this.invoiceMethodId = invoiceMethodId;
    }

    public String getPayRunNos() {
        return payRunNos;
    }

    public void setPayRunNos(String payRunNos) {
        this.payRunNos = payRunNos;
    }

    public String getControlNums() {
        return controlNums;
    }

    public void setControlNums(String controlNums) {
        this.controlNums = controlNums;
    }

    public String getAdjReasons() {
        return adjReasons;
    }

    public void setAdjReasons(String adjReasons) {
        this.adjReasons = adjReasons;
    }

    public String getInvComments() {
        return invComments;
    }

    public void setInvComments(String invComments) {
        this.invComments = invComments;
    }
}
