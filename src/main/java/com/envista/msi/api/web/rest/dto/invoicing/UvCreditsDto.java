package com.envista.msi.api.web.rest.dto.invoicing;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by KRISHNAREDDYM on 5/12/2017.
 */
@NamedStoredProcedureQueries({
        @NamedStoredProcedureQuery(name = "UvCreditsDto.getSearchInfo", procedureName = "SHP_INV_UV_CREDITS_SEARCH_PRO",
                resultClasses = UvCreditsDto.class,
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
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_CLAIM_FLAG", type = String.class),
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
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_SORT_COLUMN", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_ACTION_TYPE", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, name = "P_REFCUR_CREDITS_PR_INFO", type = Void.class)
                }),
        @NamedStoredProcedureQuery(name = "UvCreditsDto.getSearCount", procedureName = "SHP_INV_UV_CREDITS_SEARCH_PRO",
                resultSetMappings = "UvCreditsDto.getTotalCount",
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
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_CLAIM_FLAG", type = String.class),
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
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_SORT_COLUMN", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_ACTION_TYPE", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, name = "P_REFCUR_CREDITS_PR_INFO", type = Void.class)
                }),
        @NamedStoredProcedureQuery(name = "UvCreditsDto.updateIdsStatus", procedureName = "SHP_INV_UPDATE_PR_STATUS_PRO",
                resultSetMappings = "prUpdateCount123",
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_EBILL_MANIFEST_IDS", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_ACTION_TYPE", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, name = "P_REFCUR_CREDITS_PR_INFO", type = Void.class)
                }),
        @NamedStoredProcedureQuery(name = "UvCreditsDto.update", procedureName = "SHP_INV_UPDATE_UV_PRO",
                /*resultSetMappings = "prUpdateCount123",*/
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_ACTION_NAME", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_EBILL_MANIFEST_ID", type = Long.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_INTERNAL_INV_COMMENTS", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_VOICE_ID", type = Long.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_NEW_VOICE_NAME", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_VOICE_COMMENTS", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_CLAIM_FLAG", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_USER_NAME", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_ACTION_TYPE", type = String.class)
                        //@StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, name = "P_REFCUR_UPDATE_UV_INFO", type = Void.class)
                }),
        @NamedStoredProcedureQuery(name = "UvCreditsDto.updateDashBoardSummary", procedureName = "SHP_INV_UPDATE_DAS_SUMMARY_PRO",
                parameters = {

                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_WEEK_END_ID", type = Integer.class)
                })
})
@SqlResultSetMappings({
        @SqlResultSetMapping(name = "prUpdateCount123", classes = {
                @ConstructorResult(
                        targetClass = CustomOmitsDto.class,
                        columns = {
                                @ColumnResult(name = "UPDATE_COUNT", type = int.class)
                        })
        }),
        @SqlResultSetMapping(name = "UvCreditsDto.getTotalCount", classes = {
                @ConstructorResult(
                        targetClass = TotalCountDto.class,
                        columns = {
                                @ColumnResult(name = "TOTAL_COUNT", type = int.class)
                        })
        })
})
@Entity
public class UvCreditsDto implements Serializable {

    @Id
    @Column(name = "EBILL_MANIFEST_ID")
    private Long id;

    @Column(name = "CUSTOMER_CODE")
    private String customerCode;

    @Column(name = "CARRIER_NAME")
    private String carrierName;

    @Column(name = "TRACKING_NUMBER")
    private String trackingNumber;

    @Column(name = "BUSINESS_PARTNER_NAME")
    private String businessPartner;

    @Column(name = "CREDIT_AMOUNT")
    private String creditAmount;

    @Column(name = "ADJ_REASONS")
    private String reason;

    @Column(name = "SERVICE")
    private String service;

    @Column(name = "REQUESTOR_NAME")
    private String requestorName;

    @Column(name = "CLAIM_FLAG_TEMP")
    private String claimFlagTemp;

    @Column(name = "INV_COMMENTS_TEMP")
    private String invCommentsTemp;

    public UvCreditsDto() {
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

    public String getBusinessPartner() {
        return businessPartner;
    }

    public void setBusinessPartner(String businessPartner) {
        this.businessPartner = businessPartner;
    }

    public String getCreditAmount() {
        return creditAmount;
    }

    public void setCreditAmount(String creditAmount) {
        this.creditAmount = creditAmount;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public String getRequestorName() {
        return requestorName;
    }

    public void setRequestorName(String requestorName) {
        this.requestorName = requestorName;
    }

    public String getClaimFlagTemp() {
        return claimFlagTemp;
    }

    public void setClaimFlagTemp(String claimFlagTemp) {
        this.claimFlagTemp = claimFlagTemp;
    }

    public String getInvCommentsTemp() {
        return invCommentsTemp;
    }

    public void setInvCommentsTemp(String invCommentsTemp) {
        this.invCommentsTemp = invCommentsTemp;
    }
}
