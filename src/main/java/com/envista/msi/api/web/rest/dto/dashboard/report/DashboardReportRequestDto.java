package com.envista.msi.api.web.rest.dto.dashboard.report;

import javax.persistence.Entity;
import java.io.Serializable;

/**
 * Created by Sujit kumar on 06/03/2017.
 */
public class DashboardReportRequestDto implements Serializable {
    private Long carrierId;
    private String service;
    private String invoiceDate;
    private String carrier;
    private String carrScoretype;
    private String deliveryFlagDecs;
    private String accessorialName;
    private String dashletteName;
    private String mode;
    private String shipperCity;
    private String receiverCity;
    private String reportForDashlette;
    private Boolean isLineItemReport;
    private String boundType;
    private Integer offset;
    private Integer pageSize;

    public Long getCarrierId() {
        return carrierId;
    }

    public void setCarrierId(Long carrierId) {
        this.carrierId = carrierId;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public String getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(String invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public String getCarrier() {
        return carrier;
    }

    public void setCarrier(String carrier) {
        this.carrier = carrier;
    }

    public String getCarrScoretype() {
        return carrScoretype;
    }

    public void setCarrScoretype(String carrScoretype) {
        this.carrScoretype = carrScoretype;
    }

    public String getDeliveryFlagDecs() {
        return deliveryFlagDecs;
    }

    public void setDeliveryFlagDecs(String deliveryFlagDecs) {
        this.deliveryFlagDecs = deliveryFlagDecs;
    }

    public String getAccessorialName() {
        return accessorialName;
    }

    public void setAccessorialName(String accessorialName) {
        this.accessorialName = accessorialName;
    }

    public String getDashletteName() {
        return dashletteName;
    }

    public void setDashletteName(String dashletteName) {
        this.dashletteName = dashletteName;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public String getShipperCity() {
        return shipperCity;
    }

    public void setShipperCity(String shipperCity) {
        this.shipperCity = shipperCity;
    }

    public String getReceiverCity() {
        return receiverCity;
    }

    public void setReceiverCity(String receiverCity) {
        this.receiverCity = receiverCity;
    }

    public String getReportForDashlette() {
        return reportForDashlette;
    }

    public void setReportForDashlette(String reportForDashlette) {
        this.reportForDashlette = reportForDashlette;
    }

    public Boolean getLineItemReport() {
        return isLineItemReport;
    }

    public void setLineItemReport(Boolean lineItemReport) {
        isLineItemReport = lineItemReport;
    }

    public String getBoundType() {
        return boundType;
    }

    public void setBoundType(String boundType) {
        this.boundType = boundType;
    }

    public Integer getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
}
