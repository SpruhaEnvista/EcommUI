package com.envista.msi.api.web.rest.dto.dashboard.auditactivity;

/**
 * Created by Sujit kumar on 03/01/2018.
 */
public class CustomisedFreightAuditSavingDto {
    private String carrierName;

    private String invoicedAmount;

    private String approvedAmount;

    private String freightSaving;

    private String savingPercentage;

    private String adjustmentReason;

    private Integer adjustedInvoiceCount;

    public String getCarrierName() {
        return carrierName;
    }

    public void setCarrierName(String carrierName) {
        this.carrierName = carrierName;
    }

    public String getInvoicedAmount() {
        return invoicedAmount;
    }

    public void setInvoicedAmount(String invoicedAmount) {
        this.invoicedAmount = invoicedAmount;
    }

    public String getApprovedAmount() {
        return approvedAmount;
    }

    public void setApprovedAmount(String approvedAmount) {
        this.approvedAmount = approvedAmount;
    }

    public String getFreightSaving() {
        return freightSaving;
    }

    public void setFreightSaving(String freightSaving) {
        this.freightSaving = freightSaving;
    }

    public String getSavingPercentage() {
        return savingPercentage;
    }

    public void setSavingPercentage(String savingPercentage) {
        this.savingPercentage = savingPercentage;
    }

    public String getAdjustmentReason() {
        return adjustmentReason;
    }

    public void setAdjustmentReason(String adjustmentReason) {
        this.adjustmentReason = adjustmentReason;
    }

    public Integer getAdjustedInvoiceCount() {
        return adjustedInvoiceCount;
    }

    public void setAdjustedInvoiceCount(Integer adjustedInvoiceCount) {
        this.adjustedInvoiceCount = adjustedInvoiceCount;
    }
}
