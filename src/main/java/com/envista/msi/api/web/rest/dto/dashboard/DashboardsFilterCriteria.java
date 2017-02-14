package com.envista.msi.api.web.rest.dto.dashboard;

import java.io.Serializable;

/**
 * Created by Sarvesh on 1/20/2017.
 */
public class DashboardsFilterCriteria implements Serializable {

    private String customerIdsCSV;
    private String shipperGroupIdsCSV;
    private String carriers;
    private String fromDate;
    private String toDate;
    private String modes;
    private String modeNames;
    private String services;
    private String serviceNames;
    private String dateType;
    private String lanes;
    private String shipperIdsCSV;
    private long   userId;
    private FreightDashboardsDataBean freightDashboardsDataBean;
    private String invoiceStatusId;
    private String SpendByMonth;
    private String invoiceMethod;
    private String orderMatch;
    private String tax;
    private String accDesc;
    private String service;
    private String scoreType;
    private String billedVsApproved;
    private String deliveryFlag;
    private String accessorialName;
    private String dashletteName;
    private String openInvoiceStatusIds;
    private String shipperCity;
    private String receiverCity;
    private String feedBackType;
    private String feedBackDescription;
    private String feedBackTabName;
    private String reportForDashlette;
    private String originalFromDate;
    private String originalToDate;
    private boolean isLineItemReport;
    private long convertCurrencyId;
    private String convertWeightUnit;
    private String deliveryFlagDesc;
    private String locale;
    private String convertCurrencyCode;
    private String boundType;
    private String originalDateType;
    private boolean handleParcelServices;
    private boolean parcelDashlettes;
    private long noOfLanes;

    public String getCustomerIdsCSV() {
        return customerIdsCSV;
    }

    public void setCustomerIdsCSV(String customerIdsCSV) {
        this.customerIdsCSV = customerIdsCSV;
    }

    public String getShipperGroupIdsCSV() {
        return shipperGroupIdsCSV;
    }

    public void setShipperGroupIdsCSV(String shipperGroupIdsCSV) {
        this.shipperGroupIdsCSV = shipperGroupIdsCSV;
    }

    public String getCarriers() {
        return carriers;
    }

    public void setCarriers(String carriers) {
        this.carriers = carriers;
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

    public String getModes() {
        return modes;
    }

    public void setModes(String modes) {
        this.modes = modes;
    }

    public String getModeNames() {
        return modeNames;
    }

    public void setModeNames(String modeNames) {
        this.modeNames = modeNames;
    }

    public String getServices() {
        return services;
    }

    public void setServices(String services) {
        this.services = services;
    }

    public String getServiceNames() {
        return serviceNames;
    }

    public void setServiceNames(String serviceNames) {
        this.serviceNames = serviceNames;
    }

    public String getDateType() {
        return dateType;
    }

    public void setDateType(String dateType) {
        this.dateType = dateType;
    }

    public String getLanes() {
        return lanes;
    }

    public void setLanes(String lanes) {
        this.lanes = lanes;
    }

    public String getShipperIdsCSV() {
        return shipperIdsCSV;
    }

    public void setShipperIdsCSV(String shipperIdsCSV) {
        this.shipperIdsCSV = shipperIdsCSV;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public FreightDashboardsDataBean getFreightDashboardsDataBean() {
        return freightDashboardsDataBean;
    }

    public void setFreightDashboardsDataBean(FreightDashboardsDataBean freightDashboardsDataBean) {
        this.freightDashboardsDataBean = freightDashboardsDataBean;
    }

    public String getInvoiceStatusId() {
        return invoiceStatusId;
    }

    public void setInvoiceStatusId(String invoiceStatusId) {
        this.invoiceStatusId = invoiceStatusId;
    }

    public String getSpendByMonth() {
        return SpendByMonth;
    }

    public void setSpendByMonth(String spendByMonth) {
        SpendByMonth = spendByMonth;
    }

    public String getInvoiceMethod() {
        return invoiceMethod;
    }

    public void setInvoiceMethod(String invoiceMethod) {
        this.invoiceMethod = invoiceMethod;
    }

    public String getOrderMatch() {
        return orderMatch;
    }

    public void setOrderMatch(String orderMatch) {
        this.orderMatch = orderMatch;
    }

    public String getTax() {
        return tax;
    }

    public void setTax(String tax) {
        this.tax = tax;
    }

    public String getAccDesc() {
        return accDesc;
    }

    public void setAccDesc(String accDesc) {
        this.accDesc = accDesc;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public String getScoreType() {
        return scoreType;
    }

    public void setScoreType(String scoreType) {
        this.scoreType = scoreType;
    }

    public String getBilledVsApproved() {
        return billedVsApproved;
    }

    public void setBilledVsApproved(String billedVsApproved) {
        this.billedVsApproved = billedVsApproved;
    }

    public String getDeliveryFlag() {
        return deliveryFlag;
    }

    public void setDeliveryFlag(String deliveryFlag) {
        this.deliveryFlag = deliveryFlag;
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

    public String getOpenInvoiceStatusIds() {
        return openInvoiceStatusIds;
    }

    public void setOpenInvoiceStatusIds(String openInvoiceStatusIds) {
        this.openInvoiceStatusIds = openInvoiceStatusIds;
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

    public String getFeedBackType() {
        return feedBackType;
    }

    public void setFeedBackType(String feedBackType) {
        this.feedBackType = feedBackType;
    }

    public String getFeedBackDescription() {
        return feedBackDescription;
    }

    public void setFeedBackDescription(String feedBackDescription) {
        this.feedBackDescription = feedBackDescription;
    }

    public String getFeedBackTabName() {
        return feedBackTabName;
    }

    public void setFeedBackTabName(String feedBackTabName) {
        this.feedBackTabName = feedBackTabName;
    }

    public String getReportForDashlette() {
        return reportForDashlette;
    }

    public void setReportForDashlette(String reportForDashlette) {
        this.reportForDashlette = reportForDashlette;
    }

    public String getOriginalFromDate() {
        return originalFromDate;
    }

    public void setOriginalFromDate(String originalFromDate) {
        this.originalFromDate = originalFromDate;
    }

    public String getOriginalToDate() {
        return originalToDate;
    }

    public void setOriginalToDate(String originalToDate) {
        this.originalToDate = originalToDate;
    }

    public boolean isLineItemReport() {
        return isLineItemReport;
    }

    public void setLineItemReport(boolean lineItemReport) {
        isLineItemReport = lineItemReport;
    }

    public long getConvertCurrencyId() {
        return convertCurrencyId;
    }

    public void setConvertCurrencyId(long convertCurrencyId) {
        this.convertCurrencyId = convertCurrencyId;
    }

    public String getConvertWeightUnit() {
        return convertWeightUnit;
    }

    public void setConvertWeightUnit(String convertWeightUnit) {
        this.convertWeightUnit = convertWeightUnit;
    }

    public String getDeliveryFlagDesc() {
        return deliveryFlagDesc;
    }

    public void setDeliveryFlagDesc(String deliveryFlagDesc) {
        this.deliveryFlagDesc = deliveryFlagDesc;
    }

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

    public String getConvertCurrencyCode() {
        return convertCurrencyCode;
    }

    public void setConvertCurrencyCode(String convertCurrencyCode) {
        this.convertCurrencyCode = convertCurrencyCode;
    }

    public String getBoundType() {
        return boundType;
    }

    public void setBoundType(String boundType) {
        this.boundType = boundType;
    }

    public String getOriginalDateType() {
        return originalDateType;
    }

    public void setOriginalDateType(String originalDateType) {
        this.originalDateType = originalDateType;
    }

    public boolean isHandleParcelServices() {
        return handleParcelServices;
    }

    public void setHandleParcelServices(boolean handleParcelServices) {
        this.handleParcelServices = handleParcelServices;
    }

    public boolean isParcelDashlettes() {
        return parcelDashlettes;
    }

    public void setParcelDashlettes(boolean parcelDashlettes) {
        this.parcelDashlettes = parcelDashlettes;
    }

    public long getNoOfLanes() {
        return noOfLanes;
    }

    public void setNoOfLanes(long noOfLanes) {
        this.noOfLanes = noOfLanes;
    }

    public class FreightDashboardsDataBean {

        private String carrierName;
        private String invoiceStatus;
        private String shipperAddress;
        private String receiverAddress;
        private String pol;
        private String pod;
        private boolean amountChart;
        private String modes;
        private String services;


        public String getCarrierName() {
            return carrierName;
        }

        public void setCarrierName(String carrierName) {
            this.carrierName = carrierName;
        }

        public String getInvoiceStatus() {
            return invoiceStatus;
        }

        public void setInvoiceStatus(String invoiceStatus) {
            this.invoiceStatus = invoiceStatus;
        }

        public String getShipperAddress() {
            return shipperAddress;
        }

        public void setShipperAddress(String shipperAddress) {
            this.shipperAddress = shipperAddress;
        }

        public String getReceiverAddress() {
            return receiverAddress;
        }

        public void setReceiverAddress(String receiverAddress) {
            this.receiverAddress = receiverAddress;
        }

        public String getPol() {
            return pol;
        }

        public void setPol(String pol) {
            this.pol = pol;
        }

        public String getPod() {
            return pod;
        }

        public void setPod(String pod) {
            this.pod = pod;
        }

        public boolean isAmountChart() {
            return amountChart;
        }

        public void setAmountChart(boolean amountChart) {
            this.amountChart = amountChart;
        }

        public String getModes() {
            return modes;
        }

        public void setModes(String modes) {
            this.modes = modes;
        }

        public String getServices() {
            return services;
        }

        public void setServices(String services) {
            this.services = services;
        }

    }


}
