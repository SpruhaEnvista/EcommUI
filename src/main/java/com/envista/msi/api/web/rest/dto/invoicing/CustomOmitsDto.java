package com.envista.msi.api.web.rest.dto.invoicing;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by KRISHNAREDDYM on 4/20/2017.
 */
@NamedStoredProcedureQueries({
        @NamedStoredProcedureQuery(name = "CustomOmitsDto.getCutomOmitsList", procedureName = "SHP_INV_GET_CUSTOM_OMITS_PRO",
                resultClasses = CustomOmitsDto.class,
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_USER_ID", type = Long.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_OMIT_ID", type = Long.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_OFFSET", type = Integer.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_PAGE_SIZE", type = Integer.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_SORT_COLUMN", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_ACTION_TYPE", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, name = "P_REFCUR_OMITS_INFO", type = Void.class)
                }),
        @NamedStoredProcedureQuery(name = "CustomOmitsDto.getCount", procedureName = "SHP_INV_GET_CUSTOM_OMITS_PRO",
                resultSetMappings = "omitsCount",
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_USER_ID", type = Long.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_OMIT_ID", type = Long.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_OFFSET", type = Integer.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_PAGE_SIZE", type = Integer.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_SORT_COLUMN", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_ACTION_TYPE", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, name = "P_REFCUR_OMITS_INFO", type = Void.class)
                }),
        @NamedStoredProcedureQuery(name = "CustomOmitsDto.insertOrUpdateCustomOmit", procedureName = "SHP_INV_INST_UPDATE_OMITS_PRO",
                resultClasses = CustomOmitsDto.class,
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_CUSTOM_OMITS_ID", type = Long.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_CUSTOMER_NAME", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_TRACKING_NUMBER", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_COMMENTS", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_CARRIER_ID", type = Long.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_CUSTOMER_ID", type = Long.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_CUSTOMER_IDS", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_CREDIT_TYPE_ID", type = Long.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_EXPIRY_DATE", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_USER_ID", type = Long.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_ACTION_TYPE", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, name = "P_REFCUR_OMITS_INFO", type = Void.class)
                }),
        @NamedStoredProcedureQuery(name = "CustomOmitsDto.findBySearchCriteria", procedureName = "SHP_INV_SEARCH_OMITS_PRO",
                resultClasses = CustomOmitsDto.class,
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_TRACKING_NUMBER", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_CREDIT_TYPE_ID", type = Long.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_CUSTOMER_IDS", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_COMMENTS", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_CARRIER_ID", type = Long.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_USER_ID", type = Long.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_OFFSET", type = Integer.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_PAGE_SIZE", type = Integer.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_CUTOM_OMIT_ID", type = Long.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_SORT_COLUMN", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_ACTION_TYPE", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, name = "P_REFCUR_OMITS_INFO", type = Void.class)
                }),
        @NamedStoredProcedureQuery(name = "CustomOmitsDto.searchCount", procedureName = "SHP_INV_SEARCH_OMITS_PRO",
                resultSetMappings = "omitsCount",
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_TRACKING_NUMBER", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_CREDIT_TYPE_ID", type = Long.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_CUSTOMER_IDS", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_COMMENTS", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_CARRIER_ID", type = Long.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_USER_ID", type = Long.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_OFFSET", type = Integer.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_PAGE_SIZE", type = Integer.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_CUTOM_OMIT_ID", type = Long.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_SORT_COLUMN", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_ACTION_TYPE", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, name = "P_REFCUR_OMITS_INFO", type = Void.class)
                }),
        @NamedStoredProcedureQuery(name = "CustomOmitsDto.deleteCustomOmits", procedureName = "SHP_INV_DEL_OMITS_PRO",
                resultClasses = CustomOmitsDto.class,
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_CUSTOM_OMITS_IDS", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, name = "P_REFCUR_OMITS_INFO", type = Void.class)
                })
})
@SqlResultSetMappings({
        @SqlResultSetMapping(name = "omitsCount", classes = {
                @ConstructorResult(
                        targetClass = CustomOmitsDto.class,
                        columns = {
                                @ColumnResult(name = "TOTAL_COUNT", type = int.class)
                        })
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

    @Column(name = "PICKUP_DATE")
    private String pickupDate;

    @Column(name = "ENTERED_DATE", nullable = true)
    private String enteredDate;

    @Column(name = "COMMENTS", nullable = true)
    private String comments;

    @Column(name = "SRUB_DATE", nullable = true)
    private String scrubDate;

    @Column(name = "INVOICE_DATE", nullable = true)
    private String invoiceDate;

    @Column(name = "EBILL_MANIFEST_ID")
    private Long ebillManifestId;

    @Column(name = "IS_ACTIVE")
    private boolean active;

    @Column(name = "CARRIER_ID")
    private Long carrierId;

    @Column(name = "EXPIRY_DATE")
    private String expiryDate;

    @Column(name = "CUSTOMER_ID")
    private Long customerId;

    @Column(name = "CUSTOMER_IDS")
    private String customerIds;

    @Column(name = "CREDIT_TYPE_ID", nullable = true)
    private Long creditTypeId;

    @Column(name = "CARRIER_NAME")
    private String carrierName;

    @Column(name = "USER_ID")
    private Long userId;

    @Column(name = "TOTAL_COUNT")
    private int totalCount;

    private String sort;

    public CustomOmitsDto() {
    }

    public CustomOmitsDto(int totalCount) {
        this.totalCount = totalCount;
    }

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
    public String getPickupDate() {
        return pickupDate;
    }

    /**
     * @param pickupDate the pickupDate to set
     */
    public void setPickupDate(String pickupDate) {
        this.pickupDate = pickupDate;
    }

    /**
     * @return the enteredDate
     */
    public String getEnteredDate() {
        return enteredDate;
    }

    /**
     * @param enteredDate the enteredDate to set
     */
    public void setEnteredDate(String enteredDate) {
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
    public String getScrubDate() {
        return scrubDate;
    }

    /**
     * @param scrubDate the scrubDate to set
     */
    public void setScrubDate(String scrubDate) {
        this.scrubDate = scrubDate;
    }

    /**
     * @return the invoiceDate
     */
    public String getInvoiceDate() {
        return invoiceDate;
    }

    /**
     * @param invoiceDate the invoiceDate to set
     */
    public void setInvoiceDate(String invoiceDate) {
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
    public String getExpiryDate() {
        return expiryDate;
    }

    /**
     * @param expiryDate the expiryDate to set
     */
    public void setExpiryDate(String expiryDate) {
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

    public String getCarrierName() {
        return carrierName;
    }

    public void setCarrierName(String carrierName) {
        this.carrierName = carrierName;
    }


    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }


    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }
}
