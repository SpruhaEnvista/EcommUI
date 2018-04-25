package com.envista.msi.api.web.rest.dto.rtr;

import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by Sujit kumar on 08/06/2017.
 */
@NamedStoredProcedureQueries({
        @NamedStoredProcedureQuery(
                name = ParcelAuditDetailsDto.Config.StoredProcedureQueryName.AUDIT_UPS_PARCEL_DETAILS,
                procedureName = ParcelAuditDetailsDto.Config.StoredProcedureName.AUDIT_UPS_PARCEL_DETAILS,
                resultClasses = {ParcelAuditDetailsDto.class},
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_customer_CSV", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_from_date", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_to_date", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_tracking_numbers", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_invoice_id", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, name = "p_audit_parcel_details", type = Void.class)
                }
        ),
        @NamedStoredProcedureQuery(
                name = ParcelAuditDetailsDto.Config.StoredProcedureQueryName.AUDIT_NOT_UPS_PARCEL_DETAILS,
                procedureName = ParcelAuditDetailsDto.Config.StoredProcedureName.AUDIT_NOT_UPS_PARCEL_DETAILS,
                resultClasses = {ParcelAuditDetailsDto.class},
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_customer_CSV", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_carrier_ids", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_from_date", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_to_date", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_tracking_numbers", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_invoice_id", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, name = "p_audit_parcel_details", type = Void.class)
                }
        ),
        @NamedStoredProcedureQuery(
                name = ParcelAuditDetailsDto.Config.StoredProcedureQueryName.LOAD_INVOICE_IDS,
                procedureName = ParcelAuditDetailsDto.Config.StoredProcedureName.LOAD_INVOICE_IDS,
                resultSetMappings = {ParcelAuditDetailsDto.Config.SqlResultSetMapping.LOAD_INVOICE_IDS_MAPPING},
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_from_date", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_to_date", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_customer_id", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_invoice_ids", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_limit", type = Integer.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_rate_to", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, name = "p_inv_list", type = Void.class)
                }
        )
})

@SqlResultSetMappings({
        @SqlResultSetMapping(
                name = ParcelAuditDetailsDto.Config.SqlResultSetMapping.LOAD_INVOICE_IDS_MAPPING,
                classes = {
                        @ConstructorResult(
                                targetClass = ParcelAuditDetailsDto.class,
                                columns = {
                                        @ColumnResult(name = "INVOICE_ID", type = Long.class),
                                }
                        )
                }
        )
})

@Entity
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ParcelAuditDetailsDto {
    @Id
    private Long id;

    @Column(name = "CUSTOMER_ID")
    private Long customerId;

    @Column(name = "CUSTOMER_CODE")
    private String customerCode;

    @Column(name = "CARRIER_ID")
    private Long carrierId;

    @Column(name = "CONTRACT_NUMBER")
    private String contractNumber;

    @Column(name = "BILL_OPTION_CODE")
    private String billOption;

    @Column(name = "RTR_SCAC_CODE")
    private String rtrScacCode;

    @Column(name = "SCAC_CODE")
    private String scacCode;

    @Column(name = "TRAN_CODE")
    private String tranCode;

    @Column(name = "CHARGE_CLASSIFICATION_CODE")
    private String chargeClassificationCode;

    @Column(name = "CHARGE_DESCRIPTION_CODE")
    private String chargeDescriptionCode;

    @Column(name = "CHARGE_DESCRIPTION")
    private String chargeDescription;

    @Column(name = "PACKAGE_WEIGHT")
    private String packageWeight;

    @Column(name = "TRACKING_NUMBER")
    private String trackingNumber;

    @Column(name = "RESIDENTIAL_INDICATOR")
    private String residentialIndicator;

    @Column(name = "PICKUP_DATE")
    private Date pickupDate;

    @Column(name = "DELIVERY_DATE")
    private Date deliveryDate;

    @Column(name = "SENDER_COUNTRY")
    private String senderCountry;

    @Column(name = "SENDER_STATE")
    private String senderState;

    @Column(name = "SENDER_CITY")
    private String senderCity;

    @Column(name = "SENDER_ZIP_CODE")
    private String senderZipCode;

    @Column(name = "RECEIVER_COUNTRY")
    private String receiverCountry;

    @Column(name = "RECEIVER_STATE")
    private String receiverState;

    @Column(name = "RECEIVER_CITY")
    private String receiverCity;

    @Column(name = "RECEIVER_ZIP_CODE")
    private String receiverZipCode;

    @Column(name = "NET_AMOUNT")
    private String netAmount;

    @Column(name = "ITEM_QUANTITY")
    private String itemQuantity;

    @Column(name = "QUANTITY_UNIT")
    private String quantityUnit;

    @Column(name = "WEIGHT_UNIT")
    private String weightUnit;

    @Column(name = "DIM_LENGTH")
    private String dimLength;

    @Column(name = "DIM_HEIGHT")
    private String dimHeight;

    @Column(name = "DIM_WIDTH")
    private String dimWidth;

    @Column(name = "UNIT_OF_DIM")
    private String unitOfDim;

    @Column(name = "CURRENCY")
    private String currency;

    @Column(name = "INVOICE_ID")
    private Long invoiceId;

    @Column(name = "SERVICE_LEVEL")
    private String serviceLevel;

    @Column(name = "DW_FIELD_INFORMATION")
    private String dwFieldInformation;

    @Column(name = "SHIPPER_NUMBER")
    private String shipperNumber;

    @Column(name = "PARENT_ID")
    private Long parentId;

    @Column(name = "PACKAGE_DIMENSION")
    private String packageDimension;

    @Column(name = "ACTUAL_WEIGHT")
    private BigDecimal actualWeight;

    public ParcelAuditDetailsDto() {
    }

    public ParcelAuditDetailsDto(Long invoiceId) {
        this.invoiceId = invoiceId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(String customerCode) {
        this.customerCode = customerCode;
    }

    public Long getCarrierId() {
        return carrierId;
    }

    public void setCarrierId(Long carrierId) {
        this.carrierId = carrierId;
    }

    public String getBillOption() {
        return billOption;
    }

    public void setBillOption(String billOption) {
        this.billOption = billOption;
    }

    public String getRtrScacCode() {
        return rtrScacCode;
    }

    public void setRtrScacCode(String rtrScacCode) {
        this.rtrScacCode = rtrScacCode;
    }

    public String getScacCode() {
        return scacCode;
    }

    public void setScacCode(String scacCode) {
        this.scacCode = scacCode;
    }

    public String getChargeClassificationCode() {
        return chargeClassificationCode;
    }

    public void setChargeClassificationCode(String chargeClassificationCode) {
        this.chargeClassificationCode = chargeClassificationCode;
    }

    public String getChargeDescriptionCode() {
        return chargeDescriptionCode;
    }

    public void setChargeDescriptionCode(String chargeDescriptionCode) {
        this.chargeDescriptionCode = chargeDescriptionCode;
    }

    public String getChargeDescription() {
        return chargeDescription;
    }

    public void setChargeDescription(String chargeDescription) {
        this.chargeDescription = chargeDescription;
    }

    public Date getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(Date deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public String getSenderCountry() {
        return senderCountry;
    }

    public void setSenderCountry(String senderCountry) {
        this.senderCountry = senderCountry;
    }

    public String getSenderState() {
        return senderState;
    }

    public void setSenderState(String senderState) {
        this.senderState = senderState;
    }

    public String getSenderCity() {
        return senderCity;
    }

    public void setSenderCity(String senderCity) {
        this.senderCity = senderCity;
    }

    public String getReceiverCountry() {
        return receiverCountry;
    }

    public void setReceiverCountry(String receiverCountry) {
        this.receiverCountry = receiverCountry;
    }

    public String getReceiverState() {
        return receiverState;
    }

    public void setReceiverState(String receiverState) {
        this.receiverState = receiverState;
    }

    public String getReceiverCity() {
        return receiverCity;
    }

    public void setReceiverCity(String receiverCity) {
        this.receiverCity = receiverCity;
    }

    public Date getPickupDate() {
        return pickupDate;
    }

    public void setPickupDate(Date pickupDate) {
        this.pickupDate = pickupDate;
    }

    public String getContractNumber() {
        return contractNumber;
    }

    public void setContractNumber(String contractNumber) {
        this.contractNumber = contractNumber;
    }

    public String getTranCode() {
        return tranCode;
    }

    public void setTranCode(String tranCode) {
        this.tranCode = tranCode;
    }

    public String getPackageWeight() {
        return packageWeight;
    }

    public void setPackageWeight(String packageWeight) {
        this.packageWeight = packageWeight;
    }

    public String getTrackingNumber() {
        return trackingNumber;
    }

    public void setTrackingNumber(String trackingNumber) {
        this.trackingNumber = trackingNumber;
    }

    public String getResidentialIndicator() {
        return residentialIndicator;
    }

    public void setResidentialIndicator(String residentialIndicator) {
        this.residentialIndicator = residentialIndicator;
    }

    public String getSenderZipCode() {
        return senderZipCode;
    }

    public void setSenderZipCode(String senderZipCode) {
        this.senderZipCode = senderZipCode;
    }

    public String getReceiverZipCode() {
        return receiverZipCode;
    }

    public void setReceiverZipCode(String receiverZipCode) {
        this.receiverZipCode = receiverZipCode;
    }

    public String getNetAmount() {
        return netAmount;
    }

    public void setNetAmount(String netAmount) {
        this.netAmount = netAmount;
    }

    public String getItemQuantity() {
        return itemQuantity;
    }

    public void setItemQuantity(String itemQuantity) {
        this.itemQuantity = itemQuantity;
    }

    public String getQuantityUnit() {
        return quantityUnit;
    }

    public void setQuantityUnit(String quantityUnit) {
        this.quantityUnit = quantityUnit;
    }

    public String getWeightUnit() {
        return weightUnit;
    }

    public void setWeightUnit(String weightUnit) {
        this.weightUnit = weightUnit;
    }

    public String getDimLength() {
        return dimLength;
    }

    public void setDimLength(String dimLength) {
        this.dimLength = dimLength;
    }

    public String getDimHeight() {
        return dimHeight;
    }

    public void setDimHeight(String dimHeight) {
        this.dimHeight = dimHeight;
    }

    public String getDimWidth() {
        return dimWidth;
    }

    public void setDimWidth(String dimWidth) {
        this.dimWidth = dimWidth;
    }

    public String getUnitOfDim() {
        return unitOfDim;
    }

    public void setUnitOfDim(String unitOfDim) {
        this.unitOfDim = unitOfDim;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Long getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(Long invoiceId) {
        this.invoiceId = invoiceId;
    }

    public String getServiceLevel() {
        return serviceLevel;
    }

    public void setServiceLevel(String serviceLevel) {
        this.serviceLevel = serviceLevel;
    }

    public String getDwFieldInformation() {
        return dwFieldInformation;
    }

    public void setDwFieldInformation(String dwFieldInformation) {
        this.dwFieldInformation = dwFieldInformation;
    }

    public String getShipperNumber() {
        return shipperNumber;
    }

    public void setShipperNumber(String shipperNumber) {
        this.shipperNumber = shipperNumber;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getPackageDimension() {
        return packageDimension;
    }

    public void setPackageDimension(String packageDimension) {
        this.packageDimension = packageDimension;
    }

    public BigDecimal getActualWeight() {
        return actualWeight;
    }

    public void setActualWeight(BigDecimal actualWeight) {
        this.actualWeight = actualWeight;
    }

    public static class Config{
        public static class StoredProcedureName{
            static final String AUDIT_UPS_PARCEL_DETAILS = "SHP_AUDIT_UPS_PARCEL_PROC";
            static final String AUDIT_NOT_UPS_PARCEL_DETAILS = "SHP_AUDIT_NON_UPS_PRCEL_PROC";
            static final String LOAD_INVOICE_IDS = "SHP_AUDIT_GET_INVOICE_PROC";
        }

        public static class StoredProcedureQueryName{
            public static final String AUDIT_UPS_PARCEL_DETAILS = "ParcelAuditDetailsDto.loadUpsParcelAuditDetails";
            public static final String AUDIT_NOT_UPS_PARCEL_DETAILS = "ParcelAuditDetailsDto.loadNonUpsParcelAuditDetails";
            public static final String LOAD_INVOICE_IDS = "ParcelAuditDetailsDto.loadInvoiceIds";
        }

        public static class SqlResultSetMapping{
            public static final String LOAD_INVOICE_IDS_MAPPING = "ParcelAuditDetailsDto.loadInvoiceIdsMapping";
        }
    }
}
