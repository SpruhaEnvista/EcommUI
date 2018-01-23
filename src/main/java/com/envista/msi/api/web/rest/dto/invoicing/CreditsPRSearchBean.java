package com.envista.msi.api.web.rest.dto.invoicing;

import java.io.Serializable;

/**
 * Created by KRISHNAREDDYM on 5/11/2017.
 */
public class CreditsPRSearchBean implements Serializable {

    private Long businessPartnerId;

    private String customerIds;

    private String savedFilter;

    private Long invoicingStatus;

    private Long invCatagoryId;

    private Long invWeekEnId;

    private Long invoiceModeId;

    private Long carrierId;

    private Long creditClassId;

    private String claimFlag;

    private String reviewFlag;

    private String createDateFrom;

    private String createDateTo;

    private String invoiceDateFrom;

    private String invoiceDateTo;

    private String closeDateFrom;

    private String closeDateTo;

    private String invoiceNumbers;

    private String trackingNumbers;

    private String internalKeyIds;

    private Long invoiceMethodId;

    private String payRunNos;

    private String controlNums;

    private String adjReasons;

    private String invComments;

    private String sort;

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

    public Long getInvoicingStatus() {
        return invoicingStatus;
    }

    public void setInvoicingStatus(Long invoicingStatus) {
        this.invoicingStatus = invoicingStatus;
    }

    public Long getInvCatagoryId() {
        return invCatagoryId;
    }

    public void setInvCatagoryId(Long invCatagoryId) {
        this.invCatagoryId = invCatagoryId;
    }

    public Long getInvWeekEnId() {
        return invWeekEnId;
    }

    public void setInvWeekEnId(Long invWeekEnId) {
        this.invWeekEnId = invWeekEnId;
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

    public String getClaimFlag() {
        return claimFlag;
    }

    public void setClaimFlag(String claimFlag) {
        this.claimFlag = claimFlag;
    }

    public String getReviewFlag() {
        return reviewFlag;
    }

    public void setReviewFlag(String reviewFlag) {
        this.reviewFlag = reviewFlag;
    }

    public String getCreateDateFrom() {
        return createDateFrom;
    }

    public void setCreateDateFrom(String createDateFrom) {
        this.createDateFrom = createDateFrom;
    }

    public String getInvoiceDateFrom() {
        return invoiceDateFrom;
    }

    public void setInvoiceDateFrom(String invoiceDateFrom) {
        this.invoiceDateFrom = invoiceDateFrom;
    }

    public String getCloseDateFrom() {
        return closeDateFrom;
    }

    public void setCloseDateFrom(String closeDateFrom) {
        this.closeDateFrom = closeDateFrom;
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

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }
    public String getCreateDateTo() {
        return createDateTo;
    }

    public void setCreateDateTo(String createDateTo) {
        this.createDateTo = createDateTo;
    }

    public String getInvoiceDateTo() {
        return invoiceDateTo;
    }

    public void setInvoiceDateTo(String invoiceDateTo) {
        this.invoiceDateTo = invoiceDateTo;
    }

    public String getCloseDateTo() {
        return closeDateTo;
    }

    public void setCloseDateTo(String closeDateTo) {
        this.closeDateTo = closeDateTo;
    }
}
