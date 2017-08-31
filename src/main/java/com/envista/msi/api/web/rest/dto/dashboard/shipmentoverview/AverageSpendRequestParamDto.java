package com.envista.msi.api.web.rest.dto.dashboard.shipmentoverview;

/**
 * Created by Sujit kumar on 28/08/2017.
 */
public class AverageSpendRequestParamDto {
    private String carrierId;
    private String invoiceDate;
    private String modeName;
    private String fromDate;
    private String toDate;
    private String lastDay;
    private String customisedDisplayUnits;
    private Boolean isFromChartEvent = false;

    public String getCarrierId() {
        return carrierId;
    }

    public void setCarrierId(String carrierId) {
        this.carrierId = carrierId;
    }

    public String getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(String invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public String getModeName() {
        return modeName;
    }

    public void setModeName(String modeName) {
        this.modeName = modeName;
    }

    public String getFromDate() {
        return fromDate;
    }

    public void setFromDate(String fromDate) {
        this.fromDate = fromDate;
    }

    public String getToDate() {
        return toDate;
    }

    public void setToDate(String toDate) {
        this.toDate = toDate;
    }

    public String getLastDay() {
        return lastDay;
    }

    public void setLastDay(String lastDay) {
        this.lastDay = lastDay;
    }

    public String getCustomisedDisplayUnits() {
        return customisedDisplayUnits;
    }

    public void setCustomisedDisplayUnits(String customisedDisplayUnits) {
        this.customisedDisplayUnits = customisedDisplayUnits;
    }

    public Boolean getFromChartEvent() {
        return isFromChartEvent;
    }

    public void setFromChartEvent(Boolean fromChartEvent) {
        isFromChartEvent = fromChartEvent;
    }
}
