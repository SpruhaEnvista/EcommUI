package com.envista.msi.api.web.rest.dto.dashboard.report;

import com.envista.msi.api.domain.util.DashboardStoredProcParam;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Sujit kumar on 02/03/2017.
 */

@NamedStoredProcedureQueries({
        @NamedStoredProcedureQuery(
                name = DashboardReportDto.Config.StoredProcedureQueryName.PARCEL_AND_FREIGHT_REPORT,
                procedureName = DashboardReportDto.Config.StoredProcedureName.PARCEL_AND_FREIGHT_REPORT,
                resultClasses = {DashboardReportDto.class},
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.DashboardReportParams.DATE_TYPE_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.DashboardReportParams.CARRIER_IDS_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.DashboardReportParams.DASHLETTE_NAME_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.DashboardReportParams.CONVERTED_CURRENCY_ID_PARAM, type = Long.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.DashboardReportParams.CONVERTED_CURRENCY_CODE_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.DashboardReportParams.CUSTOMER_IDS_CSV_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.DashboardReportParams.FROM_DATE_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.DashboardReportParams.TO_DATE_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.DashboardReportParams.CONVERTED_WEIGHT_UNIT_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.DashboardReportParams.DELIVERY_FLAG_DESC_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.DashboardReportParams.BOUND_TYPE_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.DashboardReportParams.POD, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.DashboardReportParams.POL, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.DashboardReportParams.MODES_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.DashboardReportParams.MODE_NAMES_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.DashboardReportParams.SERVICES_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.DashboardReportParams.LANES_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.DashboardReportParams.TAX_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.DashboardReportParams.ACC_DESC_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.DashboardReportParams.SERVICE_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.DashboardReportParams.INVOICE_STATUS_ID_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.DashboardReportParams.INVOICE_METHOD_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.DashboardReportParams.ORDER_MATCH_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.DashboardReportParams.SHIPPER_CITY_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.DashboardReportParams.SHIPPER_STATE_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.DashboardReportParams.SHIPPER_COUNTRY_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.DashboardReportParams.RECEIVER_CITY_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.DashboardReportParams.RECEIVER_STATE_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.DashboardReportParams.RECEIVER_COUNTRY_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.DashboardReportParams.REPORT_FOR_DASHLETTE_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.DashboardReportParams.PAGE_OFFSET_PARAM, type = Integer.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.DashboardReportParams.PAGE_SIZE_PARAM, type = Integer.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.DashboardReportParams.REPORT_TOTAL_ROW_COUNT_PARAM, type = Integer.class),
                        @StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, name = DashboardStoredProcParam.DashboardReportParams.REPORT_DATA_PARAM, type = Void.class)
                }),
        @NamedStoredProcedureQuery(
                name = DashboardReportDto.Config.StoredProcedureQueryName.LINE_ITEM_REPORT,
                procedureName = DashboardReportDto.Config.StoredProcedureName.LINE_ITEM_REPORT,
                resultClasses = {DashboardReportDto.class},
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.DashboardReportParams.DATE_TYPE_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.DashboardReportParams.CARRIER_IDS_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.DashboardReportParams.CONVERTED_CURRENCY_ID_PARAM, type = Long.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.DashboardReportParams.CONVERTED_CURRENCY_CODE_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.DashboardReportParams.CUSTOMER_IDS_CSV_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.DashboardReportParams.FROM_DATE_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.DashboardReportParams.TO_DATE_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.DashboardReportParams.CONVERTED_WEIGHT_UNIT_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.DashboardReportParams.MODES_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.DashboardReportParams.SERVICES_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.DashboardReportParams.TAX_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.DashboardReportParams.ACC_DESC_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.DashboardReportParams.PAGE_OFFSET_PARAM, type = Integer.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.DashboardReportParams.PAGE_SIZE_PARAM, type = Integer.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.DashboardReportParams.REPORT_TOTAL_ROW_COUNT_PARAM, type = Integer.class),
                        @StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, name = DashboardStoredProcParam.DashboardReportParams.REPORT_DATA_PARAM, type = Void.class)
                }
        )
})

@Entity
public class DashboardReportDto implements Serializable {
    @Id
    @Column(name = "INVOICE_NUMBER")
    private String invoiceNumber;

    @Column(name = "CARRIER_NAME")
    private String carrierName;

    @Column(name = "PRO_NUMBER")
    private String proNumber;

    @Column(name = "BOL_NUMBER")
    private String bolNumber;

    @Column(name = "BILL_OPTION")
    private String billOption;

    @Column(name = "BILL_DATE")
    private String billDate;

    @Column(name = "SHIP_DATE")
    private String shipDate;

    @Column(name = "DELIVERY_DATE")
    private String deliveryDate;

    @Column(name = "INVOICE_MODE")
    private String invoiceMode;

    @Column(name = "INVOICE_METHOD")
    private String invoiceMethod;

    @Column(name = "GL_ACCOUNTS_CODE")
    private String glAccountCode;

    @Column(name = "PO_NUMBER")
    private String poNumber;

    @Column(name = "REFERENCE1")
    private String reference1;

    @Column(name = "REFERENCE2")
    private String reference2;

    @Column(name = "SCAC_CODE")
    private String scacCode;

    @Column(name = "SHIPPER_NAME")
    private String shipperName;

    @Column(name = "SHIPPER_ADDRESS_1")
    private String shipperAddress1;

    @Column(name = "SHIPPER_CITY")
    private String shipperCity;

    @Column(name = "SHIPPER_STATE")
    private String shipperState;

    @Column(name = "SHIPPER_ZIPCODE")
    private String shipperZipCode;

    @Column(name = "SHIPPER_COUNTRY")
    private String shipperCountry;

    @Column(name = "RECEIVER_NAME")
    private String receiverName;

    @Column(name = "RECEIVER_ADDRESS_1")
    private String receiverAddress1;

    @Column(name = "RECEIVER_CITY")
    private String receiverCity;

    @Column(name = "RECEIVER_STATE")
    private String receiverState;

    @Column(name = "RECEIVER_ZIPCODE")
    private String receiverZipCode;

    @Column(name = "RECEIVER_COUNTRY")
    private String receiverCountry;

    @Column(name = "TOTAL_WEIGHT")
    private String totalWeight;

    @Column(name = "TOTAL_CHARGES")
    private String totalCharges;

    @Column(name = "LINE_HAUL")
    private String lineHaul;

    @Column(name = "FUEL_SURCHARGE")
    private String fuelCharges;

    @Column(name = "DISCOUNT")
    private String discount;

    @Column(name = "ACCESSORIALS")
    private String accessorial;

    @Column(name = "ADJUSTMENTS")
    private String adjustments;

    @Column(name = "TOTAL_DUE_AMOUNT")
    private String totalDueAmount;

    @Column(name = "INVOICE_STATUS")
    private String invoiceStatus;

    @Column(name = "CHECK_NO")
    private String checkNumber;

    @Column(name = "CHECK_DATE")
    private String checkDate;

    @Column(name = "CHECK_AMOUNT")
    private String checkAmount;

    @Column(name = "ADJUSTMENT_REASON")
    private String adjustmentReason;

    @Column(name = "SHIPPER_REGION")
    private String shipperRegion;

    @Column(name = "RECEIVER_REGION")
    private String receiverRegion;

    @Column(name = "MULTI_WT")
    private String multiWeight;

    @Column(name = "SERVICE_LEVEL")
    private String serviceLevel;

    @Column(name = "DELIVERY_FLAG")
    private String deliveryFlag;

    @Column(name = "CUSTOM_DEFINED_1")
    private String customDefined1;

    @Column(name = "CUSTOM_DEFINED_2")
    private String customDefined2;

    @Column(name = "CUSTOM_DEFINED_3")
    private String customDefined3;

    @Column(name = "CUSTOM_DEFINED_4")
    private String customDefined4;

    @Column(name = "CUSTOM_DEFINED_5")
    private String customDefined5;

    @Column(name = "CUSTOM_DEFINED_6")
    private String customDefined6;

    @Column(name = "CUSTOM_DEFINED_7")
    private String customDefined7;

    @Column(name = "CUSTOM_DEFINED_8")
    private String customDefined8;

    @Column(name = "CUSTOM_DEFINED_9")
    private String customDefined9;

    @Column(name = "CUSTOM_DEFINED_10")
    private String customDefined10;

    public String getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setInvoiceNumber(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    public String getCarrierName() {
        return carrierName;
    }

    public void setCarrierName(String carrierName) {
        this.carrierName = carrierName;
    }

    public String getProNumber() {
        return proNumber;
    }

    public void setProNumber(String proNumber) {
        this.proNumber = proNumber;
    }

    public String getBolNumber() {
        return bolNumber;
    }

    public void setBolNumber(String bolNumber) {
        this.bolNumber = bolNumber;
    }

    public String getBillOption() {
        return billOption;
    }

    public void setBillOption(String billOption) {
        this.billOption = billOption;
    }

    public String getBillDate() {
        return billDate;
    }

    public void setBillDate(String billDate) {
        this.billDate = billDate;
    }

    public String getShipDate() {
        return shipDate;
    }

    public void setShipDate(String shipDate) {
        this.shipDate = shipDate;
    }

    public String getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(String deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public String getInvoiceMode() {
        return invoiceMode;
    }

    public void setInvoiceMode(String invoiceMode) {
        this.invoiceMode = invoiceMode;
    }

    public String getInvoiceMethod() {
        return invoiceMethod;
    }

    public void setInvoiceMethod(String invoiceMethod) {
        this.invoiceMethod = invoiceMethod;
    }

    public String getGlAccountCode() {
        return glAccountCode;
    }

    public void setGlAccountCode(String glAccountCode) {
        this.glAccountCode = glAccountCode;
    }

    public String getPoNumber() {
        return poNumber;
    }

    public void setPoNumber(String poNumber) {
        this.poNumber = poNumber;
    }

    public String getReference1() {
        return reference1;
    }

    public void setReference1(String reference1) {
        this.reference1 = reference1;
    }

    public String getReference2() {
        return reference2;
    }

    public void setReference2(String reference2) {
        this.reference2 = reference2;
    }

    public String getScacCode() {
        return scacCode;
    }

    public void setScacCode(String scacCode) {
        this.scacCode = scacCode;
    }

    public String getShipperName() {
        return shipperName;
    }

    public void setShipperName(String shipperName) {
        this.shipperName = shipperName;
    }

    public String getShipperAddress1() {
        return shipperAddress1;
    }

    public void setShipperAddress1(String shipperAddress1) {
        this.shipperAddress1 = shipperAddress1;
    }

    public String getShipperCity() {
        return shipperCity;
    }

    public void setShipperCity(String shipperCity) {
        this.shipperCity = shipperCity;
    }

    public String getShipperState() {
        return shipperState;
    }

    public void setShipperState(String shipperState) {
        this.shipperState = shipperState;
    }

    public String getShipperZipCode() {
        return shipperZipCode;
    }

    public void setShipperZipCode(String shipperZipCode) {
        this.shipperZipCode = shipperZipCode;
    }

    public String getShipperCountry() {
        return shipperCountry;
    }

    public void setShipperCountry(String shipperCountry) {
        this.shipperCountry = shipperCountry;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public String getReceiverAddress1() {
        return receiverAddress1;
    }

    public void setReceiverAddress1(String receiverAddress1) {
        this.receiverAddress1 = receiverAddress1;
    }

    public String getReceiverCity() {
        return receiverCity;
    }

    public void setReceiverCity(String receiverCity) {
        this.receiverCity = receiverCity;
    }

    public String getReceiverState() {
        return receiverState;
    }

    public void setReceiverState(String receiverState) {
        this.receiverState = receiverState;
    }

    public String getReceiverZipCode() {
        return receiverZipCode;
    }

    public void setReceiverZipCode(String receiverZipCode) {
        this.receiverZipCode = receiverZipCode;
    }

    public String getReceiverCountry() {
        return receiverCountry;
    }

    public void setReceiverCountry(String receiverCountry) {
        this.receiverCountry = receiverCountry;
    }

    public String getTotalWeight() {
        return totalWeight;
    }

    public void setTotalWeight(String totalWeight) {
        this.totalWeight = totalWeight;
    }

    public String getTotalCharges() {
        return totalCharges;
    }

    public void setTotalCharges(String totalCharges) {
        this.totalCharges = totalCharges;
    }

    public String getLineHaul() {
        return lineHaul;
    }

    public void setLineHaul(String lineHaul) {
        this.lineHaul = lineHaul;
    }

    public String getFuelCharges() {
        return fuelCharges;
    }

    public void setFuelCharges(String fuelCharges) {
        this.fuelCharges = fuelCharges;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public String getAccessorial() {
        return accessorial;
    }

    public void setAccessorial(String accessorial) {
        this.accessorial = accessorial;
    }

    public String getAdjustments() {
        return adjustments;
    }

    public void setAdjustments(String adjustments) {
        this.adjustments = adjustments;
    }

    public String getTotalDueAmount() {
        return totalDueAmount;
    }

    public void setTotalDueAmount(String totalDueAmount) {
        this.totalDueAmount = totalDueAmount;
    }

    public String getInvoiceStatus() {
        return invoiceStatus;
    }

    public void setInvoiceStatus(String invoiceStatus) {
        this.invoiceStatus = invoiceStatus;
    }

    public String getCheckNumber() {
        return checkNumber;
    }

    public void setCheckNumber(String checkNumber) {
        this.checkNumber = checkNumber;
    }

    public String getCheckDate() {
        return checkDate;
    }

    public void setCheckDate(String checkDate) {
        this.checkDate = checkDate;
    }

    public String getCheckAmount() {
        return checkAmount;
    }

    public void setCheckAmount(String checkAmount) {
        this.checkAmount = checkAmount;
    }

    public String getAdjustmentReason() {
        return adjustmentReason;
    }

    public void setAdjustmentReason(String adjustmentReason) {
        this.adjustmentReason = adjustmentReason;
    }

    public String getShipperRegion() {
        return shipperRegion;
    }

    public void setShipperRegion(String shipperRegion) {
        this.shipperRegion = shipperRegion;
    }

    public String getReceiverRegion() {
        return receiverRegion;
    }

    public void setReceiverRegion(String receiverRegion) {
        this.receiverRegion = receiverRegion;
    }

    public String getMultiWeight() {
        return multiWeight;
    }

    public void setMultiWeight(String multiWeight) {
        this.multiWeight = multiWeight;
    }

    public String getServiceLevel() {
        return serviceLevel;
    }

    public void setServiceLevel(String serviceLevel) {
        this.serviceLevel = serviceLevel;
    }

    public String getDeliveryFlag() {
        return deliveryFlag;
    }

    public void setDeliveryFlag(String deliveryFlag) {
        this.deliveryFlag = deliveryFlag;
    }

    public String getCustomDefined1() {
        return customDefined1;
    }

    public void setCustomDefined1(String customDefined1) {
        this.customDefined1 = customDefined1;
    }

    public String getCustomDefined2() {
        return customDefined2;
    }

    public void setCustomDefined2(String customDefined2) {
        this.customDefined2 = customDefined2;
    }

    public String getCustomDefined3() {
        return customDefined3;
    }

    public void setCustomDefined3(String customDefined3) {
        this.customDefined3 = customDefined3;
    }

    public String getCustomDefined4() {
        return customDefined4;
    }

    public void setCustomDefined4(String customDefined4) {
        this.customDefined4 = customDefined4;
    }

    public String getCustomDefined5() {
        return customDefined5;
    }

    public void setCustomDefined5(String customDefined5) {
        this.customDefined5 = customDefined5;
    }

    public String getCustomDefined6() {
        return customDefined6;
    }

    public void setCustomDefined6(String customDefined6) {
        this.customDefined6 = customDefined6;
    }

    public String getCustomDefined7() {
        return customDefined7;
    }

    public void setCustomDefined7(String customDefined7) {
        this.customDefined7 = customDefined7;
    }

    public String getCustomDefined8() {
        return customDefined8;
    }

    public void setCustomDefined8(String customDefined8) {
        this.customDefined8 = customDefined8;
    }

    public String getCustomDefined9() {
        return customDefined9;
    }

    public void setCustomDefined9(String customDefined9) {
        this.customDefined9 = customDefined9;
    }

    public String getCustomDefined10() {
        return customDefined10;
    }

    public void setCustomDefined10(String customDefined10) {
        this.customDefined10 = customDefined10;
    }

    public static class Config{
        static class StoredProcedureName{
            static final String PARCEL_AND_FREIGHT_REPORT = "SHP_DB_PARCEL_FREIGHT_RPT_PROC";
            static final String LINE_ITEM_REPORT = "SHP_DB_LINE_ITEM_REPORT_PROC";
        }

        public static class StoredProcedureQueryName{
            public static final String PARCEL_AND_FREIGHT_REPORT = "DashboardReportDto.getDashboardReport";
            public static final String LINE_ITEM_REPORT = "DashboardReportDto.getLineItemReport";
        }
    }
}
