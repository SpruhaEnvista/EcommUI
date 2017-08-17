package com.envista.msi.api.web.rest.dto.invoicing;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by KRISHNAREDDYM on 5/11/2017.
 */
@NamedStoredProcedureQueries({
        @NamedStoredProcedureQuery(name = "CreditsPRDto.getSearchInfo", procedureName = "SHP_INV_CREDITS_PR_SEARCH_PRO",
                resultClasses = CreditsPRDto.class,
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_BUSINESS_PARTNER_ID", type = Long.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_CUSTOMER_IDS", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_SAVED_FILTER", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_INV_STATUS_ID", type = Long.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_INV_CATAGORY_ID", type = Long.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_INV_WEEK_END_ID", type = Long.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_INVOICE_MODE_ID", type = Long.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_CARRIER_ID", type = Long.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_CREDIT_CLASS_ID", type = Long.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_OMIT_FLAG", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_REVIEW_FLAG", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_CREATE_DATE", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_INVOICE_DATE", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_CLOSE_DATE", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_INVOICE_NUMBERS", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_TRACKING_NUMBERS", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_INTERNAL_KEY_IDS", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_INVOICE_METHOD_ID", type = Long.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_PAYRUN_NOS", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_CONTROL_NUMS", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_ADJ_REASONS", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_INV_COMMENTS", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_OFFSET", type = Integer.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_PAGE_SIZE", type = Integer.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_ACTION_TYPE", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, name = "P_REFCUR_CREDITS_PR_INFO", type = Void.class)
                }),
        @NamedStoredProcedureQuery(name = "CreditsPRDto.getSearCount", procedureName = "SHP_INV_CREDITS_PR_SEARCH_PRO",
                resultSetMappings = "getTotalCount",
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_BUSINESS_PARTNER_ID", type = Long.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_CUSTOMER_IDS", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_SAVED_FILTER", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_INV_STATUS_ID", type = Long.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_INV_CATAGORY_ID", type = Long.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_INV_WEEK_END_ID", type = Long.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_INVOICE_MODE_ID", type = Long.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_CARRIER_ID", type = Long.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_CREDIT_CLASS_ID", type = Long.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_OMIT_FLAG", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_REVIEW_FLAG", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_CREATE_DATE", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_INVOICE_DATE", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_CLOSE_DATE", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_INVOICE_NUMBERS", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_TRACKING_NUMBERS", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_INTERNAL_KEY_IDS", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_INVOICE_METHOD_ID", type = Long.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_PAYRUN_NOS", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_CONTROL_NUMS", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_ADJ_REASONS", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_INV_COMMENTS", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_OFFSET", type = Integer.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_PAGE_SIZE", type = Integer.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_ACTION_TYPE", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, name = "P_REFCUR_CREDITS_PR_INFO", type = Void.class)
                }),
        @NamedStoredProcedureQuery(name = "CreditsPRDto.updateIdsStatus", procedureName = "SHP_INV_UPDATE_PR_STATUS_PRO",
                resultSetMappings = "prUpdateCount",
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_EBILL_MANIFEST_IDS", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_USER_NAME", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_ACTION_TYPE", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, name = "P_REFCUR_CREDITS_PR_INFO", type = Void.class)
                })
})
@SqlResultSetMappings({
        @SqlResultSetMapping(name = "prUpdateCount", classes = {
                @ConstructorResult(
                        targetClass = CustomOmitsDto.class,
                        columns = {
                                @ColumnResult(name = "UPDATE_COUNT", type = int.class)
                        })
        }),
        @SqlResultSetMapping(name = "getTotalCount", classes = {
                @ConstructorResult(
                        targetClass = TotalCountDto.class,
                        columns = {
                                @ColumnResult(name = "TOTAL_COUNT", type = int.class)
                        })
        })
})
@Entity
public class CreditsPRDto implements Serializable {

    @Id
    @Column(name = "EBILL_MANIFEST_ID")
    private Long id;

    @Column(name = "CUSTOMER_CODE")
    private String customerCode;

    @Column(name = "CARRIER_NAME")
    private String carrierName;

    @Column(name = "TRACKING_NUMBER")
    private String trackingNumber;

    @Column(name = "SHIPPER_CODE")
    private String shipperNumber;

    @Column(name = "INVOICE_NUMBER")
    private String invoiceNumber;

    @Column(name = "BILL_DATE")
    private String invoiceDate;

    @Column(name = "SHIP_FROM")
    private String shipFrom;

    @Column(name = "SHIP_TO")
    private String shipTo;

    @Column(name = "REFERENCE_NUMBER1")
    private String refNumber;

    @Column(name = "ADJ_REASONS")
    private String reason;

    @Column(name = "WEEK_END_DATE")
    private String weekEndDate;

    @Column(name = "CREDIT_AMOUNT")
    private String creditAmount;

    @Column(name = "OMIT_FLAG")
    private String omitFlag;

    @Column(name = "REVIEW_FLAG")
    private String reviewFlag;

    @Column(name = "INTERNAL_INVOICING_COMMENTS")
    private String comments;

    @Column(name = "UPDATE_COUNT")
    private int updateCount;


    public CreditsPRDto() {
    }

    public CreditsPRDto(int updateCount) {
        this.updateCount = updateCount;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(String customerCode) {
        this.customerCode = customerCode;
    }

    public String getCarrierName() {
        return carrierName;
    }

    public void setCarrierName(String carrierName) {
        this.carrierName = carrierName;
    }

    public String getTrackingNumber() {
        return trackingNumber;
    }

    public void setTrackingNumber(String trackingNumber) {
        this.trackingNumber = trackingNumber;
    }

    public String getShipperNumber() {
        return shipperNumber;
    }

    public void setShipperNumber(String shipperNumber) {
        this.shipperNumber = shipperNumber;
    }

    public String getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setInvoiceNumber(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    public String getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(String invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public String getShipFrom() {
        return shipFrom;
    }

    public void setShipFrom(String shipFrom) {
        this.shipFrom = shipFrom;
    }

    public String getShipTo() {
        return shipTo;
    }

    public void setShipTo(String shipTo) {
        this.shipTo = shipTo;
    }

    public String getRefNumber() {
        return refNumber;
    }

    public void setRefNumber(String refNumber) {
        this.refNumber = refNumber;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getWeekEndDate() {
        return weekEndDate;
    }

    public void setWeekEndDate(String weekEndDate) {
        this.weekEndDate = weekEndDate;
    }

    public String getCreditAmount() {
        return creditAmount;
    }

    public void setCreditAmount(String creditAmount) {
        this.creditAmount = creditAmount;
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

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public int getUpdateCount() {
        return updateCount;
    }

    public void setUpdateCount(int updateCount) {
        this.updateCount = updateCount;
    }
}
