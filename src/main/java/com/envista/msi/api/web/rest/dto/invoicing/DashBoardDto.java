package com.envista.msi.api.web.rest.dto.invoicing;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by KRISHNAREDDYM on 5/8/2017.
 */
@NamedStoredProcedureQueries({
        @NamedStoredProcedureQuery(name = "DashBoardDto.getDashBoardInfo", procedureName = "SHP_INV_GET_DASHBOARD_INFO_PRO",
                resultClasses = DashBoardDto.class,
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_FROM_DATE", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_TO_DATE", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_ACTION_TYPE", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, name = "P_REFCUR_DAS_INFO", type = Void.class)
                }),
        @NamedStoredProcedureQuery(name = "DashBoardDto.getPendingCreditsCount", procedureName = "SHP_INV_GET_DASHBOARD_INFO_PRO",
                resultSetMappings = "pendingCreditsEbillIds",
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_FROM_DATE", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_TO_DATE", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_ACTION_TYPE", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, name = "P_REFCUR_DAS_INFO", type = Void.class)
                }),
        @NamedStoredProcedureQuery(name = "DashBoardDto.closeCurrentWeek", procedureName = "SHP_INV_CLOSE_CUR_WEEK_PRO",

                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_ACTION", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_EBILL_MANIFEST_IDS", type = String.class)
                       /* @StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, name = "P_REFCUR_DAS_INFO", type = Void.class)*/
                })
})
@SqlResultSetMappings({
        @SqlResultSetMapping(name = "pendingCreditsCount", classes = {
                @ConstructorResult(
                        targetClass = DashBoardDto.class,
                        columns = {
                                @ColumnResult(name = "PENDING_CREDITS_COUNT", type = int.class)
                        })
        }),
        @SqlResultSetMapping(name = "pendingCreditsEbillIds", classes = {
                @ConstructorResult(
                        targetClass = DashBoardDto.class,
                        columns = {
                                @ColumnResult(name = "EBILL_MANIFEST_ID", type = Long.class)
                        })
        })
})
@Entity
public class DashBoardDto implements Serializable {

    @Id
    @Column(name = "ROW_NUM")
    private Long id;

    @Column(name = "BUSINESS_PARTNER_NAME")
    private String businessPartnerName;

    @Column(name = "BUSINESS_PARTNER_ID")
    private int businessPartnerId;

    @Column(name = "CUSTOMERS_COUNT")
    private int customersCount;

    @Column(name = "TOTAL_CREDITS")
    private int totalCredits;

    @Column(name = "TOTAL_CREDIT_AMOUNT")
    private Float totalCreditAmt;

    @Column(name = "OMITTED")
    private int omitted;

    @Column(name = "OMITTED_PER")
    private int omittedPercentage;

    @Column(name = "OMITTED_AMOUNT")
    private Float omittedAmt;

    @Column(name = "OMITTED_AMOUNT_PER")
    private int omittedAmtPer;

    @Column(name = "CLAIMED")
    private int claimed;

    @Column(name = "CLAIMED_PER")
    private int claimedPercentage;

    @Column(name = "CLAIMED_AMOUNT")
    private Float claimedAmt;

    @Column(name = "CLAIMED_AMOUNT_PER")
    private int claimedAmtPer;

    @Column(name = "PENDING_REVIEW")
    private int pendingReview;

    @Column(name = "PENDING_REVIEW_PER")
    private int pendingReviewPer;

    @Column(name = "PENDING_REVIEW_AMOUNT")
    private Float pendingReviewAmt;

    @Column(name = "PENDING_REVIEW_AMOUNT_PER")
    private int pendingReviewAmtPer;

    @Column(name = "CARRIER_NAME")
    private String carrierName;

    @Column(name = "INV_CATAGORY")
    private String invCatagory;

    @Column(name = "CREDIT_CLASS")
    private String creditClass;

    @Column(name = "PENDING_CREDITS_COUNT")
    private int pendingCreditsCount;

    @Column(name = "CARRIER_ID")
    private Long carrierId;

    @Column(name = "INTERNAL_INVOICING_CATEGORY_ID")
    private Long invCategoryId;

    @Column(name = "CREDIT_CLASS_ID")
    private Long creditClassId;

    @Id
    @Column(name = "EBILL_MANIFEST_ID")
    private Long ebillManifestId;


    public DashBoardDto() {
    }

    public DashBoardDto(int pendingCreditsCount) {
        this.pendingCreditsCount = pendingCreditsCount;
    }

    public DashBoardDto(Long ebillManifestId) {
        this.ebillManifestId = ebillManifestId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBusinessPartnerName() {
        return businessPartnerName;
    }

    public void setBusinessPartnerName(String businessPartnerName) {
        this.businessPartnerName = businessPartnerName;
    }

    public int getBusinessPartnerId() {
        return businessPartnerId;
    }

    public void setBusinessPartnerId(int businessPartnerId) {
        this.businessPartnerId = businessPartnerId;
    }

    public int getCustomersCount() {
        return customersCount;
    }

    public void setCustomersCount(int customersCount) {
        this.customersCount = customersCount;
    }

    public int getTotalCredits() {
        return totalCredits;
    }

    public void setTotalCredits(int totalCredits) {
        this.totalCredits = totalCredits;
    }

    public Float getTotalCreditAmt() {
        return totalCreditAmt;
    }

    public void setTotalCreditAmt(Float totalCreditAmt) {
        this.totalCreditAmt = totalCreditAmt;
    }

    public int getOmitted() {
        return omitted;
    }

    public void setOmitted(int omitted) {
        this.omitted = omitted;
    }

    public int getOmittedPercentage() {
        return omittedPercentage;
    }

    public void setOmittedPercentage(int omittedPercentage) {
        this.omittedPercentage = omittedPercentage;
    }

    public Float getOmittedAmt() {
        return omittedAmt;
    }

    public void setOmittedAmt(Float omittedAmt) {
        this.omittedAmt = omittedAmt;
    }

    public int getOmittedAmtPer() {
        return omittedAmtPer;
    }

    public void setOmittedAmtPer(int omittedAmtPer) {
        this.omittedAmtPer = omittedAmtPer;
    }

    public int getClaimed() {
        return claimed;
    }

    public void setClaimed(int claimed) {
        this.claimed = claimed;
    }

    public int getClaimedPercentage() {
        return claimedPercentage;
    }

    public void setClaimedPercentage(int claimedPercentage) {
        this.claimedPercentage = claimedPercentage;
    }

    public Float getClaimedAmt() {
        return claimedAmt;
    }

    public void setClaimedAmt(Float claimedAmt) {
        this.claimedAmt = claimedAmt;
    }

    public int getClaimedAmtPer() {
        return claimedAmtPer;
    }

    public void setClaimedAmtPer(int claimedAmtPer) {
        this.claimedAmtPer = claimedAmtPer;
    }

    public int getPendingReview() {
        return pendingReview;
    }

    public void setPendingReview(int pendingReview) {
        this.pendingReview = pendingReview;
    }

    public int getPendingReviewPer() {
        return pendingReviewPer;
    }

    public void setPendingReviewPer(int pendingReviewPer) {
        this.pendingReviewPer = pendingReviewPer;
    }

    public Float getPendingReviewAmt() {
        return pendingReviewAmt;
    }

    public void setPendingReviewAmt(Float pendingReviewAmt) {
        this.pendingReviewAmt = pendingReviewAmt;
    }

    public int getPendingReviewAmtPer() {
        return pendingReviewAmtPer;
    }

    public void setPendingReviewAmtPer(int pendingReviewAmtPer) {
        this.pendingReviewAmtPer = pendingReviewAmtPer;
    }

    public String getCarrierName() {
        return carrierName;
    }

    public void setCarrierName(String carrierName) {
        this.carrierName = carrierName;
    }

    public String getInvCatagory() {
        return invCatagory;
    }

    public void setInvCatagory(String invCatagory) {
        this.invCatagory = invCatagory;
    }

    public String getCreditClass() {
        return creditClass;
    }

    public void setCreditClass(String creditClass) {
        this.creditClass = creditClass;
    }

    public int getPendingCreditsCount() {
        return pendingCreditsCount;
    }

    public void setPendingCreditsCount(int pendingCreditsCount) {
        this.pendingCreditsCount = pendingCreditsCount;
    }

    public Long getCarrierId() {
        return carrierId;
    }

    public void setCarrierId(Long carrierId) {
        this.carrierId = carrierId;
    }

    public Long getInvCategoryId() {
        return invCategoryId;
    }

    public void setInvCategoryId(Long invCategoryId) {
        this.invCategoryId = invCategoryId;
    }

    public Long getCreditClassId() {
        return creditClassId;
    }

    public void setCreditClassId(Long creditClassId) {
        this.creditClassId = creditClassId;
    }

    public Long getEbillManifestId() {
        return ebillManifestId;
    }

    public void setEbillManifestId(Long ebillManifestId) {
        this.ebillManifestId = ebillManifestId;
    }
}
