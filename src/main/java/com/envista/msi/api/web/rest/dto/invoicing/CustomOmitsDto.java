package com.envista.msi.api.web.rest.dto.invoicing;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Created by KRISHNAREDDYM on 4/20/2017.
 */
@NamedStoredProcedureQueries({
        @NamedStoredProcedureQuery(name = "CustomOmitsDto.getCutomOmitsList", procedureName = "SHP_INV_GET_CUSTOM_OMITS_LIST",
                resultClasses = CustomOmitsDto.class,
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_USER_ID", type = Long.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_OMIT_ID", type = Long.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_ACTION_TYPE", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, name = "P_REFCUR_OMITS_INFO", type = Void.class)
                }),
        @NamedStoredProcedureQuery(name = "CustomOmitsDto.insertOrUpdateCustomOmit", procedureName = "SHP_INV_INSERT_UPDATE_OMITS",
                resultClasses = CustomOmitsDto.class,
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_CUSTOM_OMITS_ID", type = Long.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_CUSTOMER_NAME", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_TRACKING_NUMBER", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_COMMENTS", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_EBILL_MANIFEST_ID", type = Long.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_IS_ACTIVE", type = Boolean.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_CARRIER_ID", type = Long.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_CUSTOMER_ID", type = Long.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_PARENT_CUSTOMER_ID", type = Long.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_CUSTOMER_IDS", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_CREDIT_TYPE_ID", type = Long.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_ACTION_TYPE", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, name = "P_REFCUR_OMITS_INFO", type = Void.class)
                }),
        @NamedStoredProcedureQuery(name = "CustomOmitsDto.findBySearchCriteria", procedureName = "SHP_INV_SEARCH_OMITS",
                resultClasses = CustomOmitsDto.class,
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_TRACKING_NUMBER", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_CREDIT_TYPE_ID", type = Long.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_CUSTOMER_IDS", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_COMMENTS", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_CARRIER_ID", type = Long.class),
                        @StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, name = "P_REFCUR_OMITS_INFO", type = Void.class)
                }),
        @NamedStoredProcedureQuery(name = "CustomOmitsDto.deleteCustomOmits", procedureName = "SHP_INV_DEL_OMITS",
                resultClasses = CustomOmitsDto.class,
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_CUSTOM_OMITS_IDS", type = Long.class),
                        @StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, name = "P_REFCUR_OMITS_INFO", type = Void.class)
                })
})
@Entity
public class CustomOmitsDto implements Serializable {


    @Id
    @Column(name = "CUSTOM_OMITS_ID")
    private Long customOmitsId;

    @Column(name = "CUSTOMER_NAME", nullable = true)
    private String customerName;

    @Column(name = "TRACKING_NUMBER", nullable = true)
    private String trackingNumber;

    @Column(name = "CREDIT_TYPE", nullable = true)
    private String creditType;

    @Column(name = "PICKUP_DATE", nullable = true)
    private Timestamp pickupDate;

    @Column(name = "ENTERED_DATE", nullable = true)
    private Timestamp enteredDate;

    @Column(name = "COMMENTS", nullable = true)
    private String comments;

    @Column(name = "SRUB_DATE", nullable = true)
    private Timestamp scrubDate;

    @Column(name = "INVOICE_DATE", nullable = true)
    private Timestamp invoiceDate;

    @Column(name = "EBILL_MANIFEST_ID")
    private Long ebillManifestId;

    @Column(name = "IS_ACTIVE")
    private boolean active;

    @Column(name = "CARRIER_ID")
    private Long carrierId;

    @Column(name = "EXPIRY_DATE")
    private Timestamp expiryDate;

    @Column(name = "CUSTOMER_ID")
    private Long customerId;

    @Column(name = "PARENT_CUSTOMER_ID")
    private Long parentCustomerId;

    @Column(name = "CUSTOMER_IDS")
    private String customerIds;

    @Column(name = "CREDIT_TYPE_ID", nullable = true)
    private Long creditTypeId;

    public CustomOmitsDto() {
    }

    /*    @OneToOne(fetch=FetchType.EAGER, cascade=CascadeType.ALL, targetEntity=CodeValuesTb.class)
    @JoinColumn(name="CREDIT_TYPE_ID", insertable=false, updatable = false)
    //@JsonIgnore
    private CodeValuesTb codeValuesTb;

    @OneToOne(fetch=FetchType.EAGER, cascade=CascadeType.ALL, targetEntity=CarrierTb.class)
    @JoinColumn(name="CARRIER_ID", insertable=false, updatable = false)
    //@JsonIgnore
    private CarrierTb carrierTb;*/

    @Column(name = "USER_ID")
    private Long userId;

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    /**
     * @return the customOmitsId
     */
    public Long getCustomOmitsId() {
        return customOmitsId;
    }

    /**
     * @param customOmitsId the customOmitsId to set
     */
    public void setCustomOmitsId(Long customOmitsId) {
        this.customOmitsId = customOmitsId;
    }

    /**
     * @return the customerCode
     */
    public String getCustomerName() {
        return customerName;
    }

    /**
     * @param customerName the customerName to set
     */
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    /**
     * @return the trackingNumber
     */
    public String getTrackingNumber() {
        return trackingNumber;
    }

    /**
     * @param trackingNumber the trackingNumber to set
     */
    public void setTrackingNumber(String trackingNumber) {
        this.trackingNumber = trackingNumber;
    }

    /**
     * @return the creditType
     */
    public String getCreditType() {
        return creditType;
    }

    /**
     * @param creditType the creditType to set
     */
    public void setCreditType(String creditType) {
        this.creditType = creditType;
    }

    /**
     * @return the pickupDate
     */
    public Timestamp getPickupDate() {
        return pickupDate;
    }

    /**
     * @param pickupDate the pickupDate to set
     */
    public void setPickupDate(Timestamp pickupDate) {
        this.pickupDate = pickupDate;
    }

    /**
     * @return the enteredDate
     */
    public Timestamp getEnteredDate() {
        return enteredDate;
    }

    /**
     * @param enteredDate the enteredDate to set
     */
    public void setEnteredDate(Timestamp enteredDate) {
        this.enteredDate = enteredDate;
    }

    /**
     * @return the comments
     */
    public String getComments() {
        return comments;
    }

    /**
     * @param comments the comments to set
     */
    public void setComments(String comments) {
        this.comments = comments;
    }

    /**
     * @return the scrubDate
     */
    public Timestamp getScrubDate() {
        return scrubDate;
    }

    /**
     * @param scrubDate the scrubDate to set
     */
    public void setScrubDate(Timestamp scrubDate) {
        this.scrubDate = scrubDate;
    }

    /**
     * @return the invoiceDate
     */
    public Timestamp getInvoiceDate() {
        return invoiceDate;
    }

    /**
     * @param invoiceDate the invoiceDate to set
     */
    public void setInvoiceDate(Timestamp invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    /**
     * @return the ebillManifestId
     */
    public Long getEbillManifestId() {
        return ebillManifestId;
    }

    /**
     * @param ebillManifestId the ebillManifestId to set
     */
    public void setEbillManifestId(Long ebillManifestId) {
        this.ebillManifestId = ebillManifestId;
    }

    /**
     * @return the carrierId
     */
    public Long getCarrierId() {
        return carrierId;
    }

    /**
     * @param carrierId the carrierId to set
     */
    public void setCarrierId(Long carrierId) {
        this.carrierId = carrierId;
    }

    /**
     * @return the expiryDate
     */
    public Timestamp getExpiryDate() {
        return expiryDate;
    }

    /**
     * @param expiryDate the expiryDate to set
     */
    public void setExpiryDate(Timestamp expiryDate) {
        this.expiryDate = expiryDate;
    }

    /**
     * @return the customerId
     */
    public Long getCustomerId() {
        return customerId;
    }

    /**
     * @param customerId the customerId to set
     */
    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    /**
     * @return the parentCustomerId
     */
    public Long getParentCustomerId() {
        return parentCustomerId;
    }

    /**
     * @param parentCustomerId the parentCustomerId to set
     */
    public void setParentCustomerId(Long parentCustomerId) {
        this.parentCustomerId = parentCustomerId;
    }

    /**
     * @return the customerIds
     */
    public String getCustomerIds() {
        return customerIds;
    }

    /**
     * @param customerIds the customerIds to set
     */
    public void setCustomerIds(String customerIds) {
        this.customerIds = customerIds;
    }

    /**
     * @return the creditTypeId
     */
    public Long getCreditTypeId() {
        return creditTypeId;
    }

    /**
     * @param creditTypeId the creditTypeId to set
     */
    public void setCreditTypeId(Long creditTypeId) {
        this.creditTypeId = creditTypeId;
    }

  /*  *//**
     * @return the codeValuesTb
     *//*
    public CodeValuesTb getCodeValuesTb() {
        return codeValuesTb;
    }

    *//**
     * @param codeValuesTb the codeValuesTb to set
     *//*
    public void setCodeValuesTb(CodeValuesTb codeValuesTb) {
        this.codeValuesTb = codeValuesTb;
    }

    *//**
     * @return the carrierTb
     *//*
    public CarrierTb getCarrierTb() {
        return carrierTb;
    }

    *//**
     * @param carrierTb the carrierTb to set
     *//*
    public void setCarrierTb(CarrierTb carrierTb) {
        this.carrierTb = carrierTb;
    }
*/

    /**
     * @return the userId
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * @param userId the userId to set
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
