package com.envista.msi.api.web.rest.dto.invoicing;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by KRISHNAREDDYM on 10/20/2017.
 */
@Entity
public class ScrubCreditsHisDto implements Serializable {

    @Id
    @Column(name = "CREDITS_HIS_ID")
    private Long creditsHisId;

    @Column(name = "WEEK_END_ID")
    private Long weekEndId;

    @Column(name = "TOTAL_CREDITS")
    private int totalCredits;

    @Column(name = "OMITTED")
    private int omitted;

    @Column(name = "CLAIMED")
    private int claimed;

    @Column(name = "PENDING_REVIEW")
    private int pendingReview;

    @Column(name = "UN_DECIDED")
    private int unDecided;

    @Column(name = "RUN_TYPE")
    private String runType;

    @Column(name = "CREATE_USER")
    private String createUser;

    @Column(name = "CREATE_DATE")
    private String createDate;


    public ScrubCreditsHisDto() {
    }

    public Long getCreditsHisId() {
        return creditsHisId;
    }

    public void setCreditsHisId(Long creditsHisId) {
        this.creditsHisId = creditsHisId;
    }

    public Long getWeekEndId() {
        return weekEndId;
    }

    public void setWeekEndId(Long weekEndId) {
        this.weekEndId = weekEndId;
    }

    public int getTotalCredits() {
        return totalCredits;
    }

    public void setTotalCredits(int totalCredits) {
        this.totalCredits = totalCredits;
    }

    public int getOmitted() {
        return omitted;
    }

    public void setOmitted(int omitted) {
        this.omitted = omitted;
    }

    public int getClaimed() {
        return claimed;
    }

    public void setClaimed(int claimed) {
        this.claimed = claimed;
    }

    public int getPendingReview() {
        return pendingReview;
    }

    public void setPendingReview(int pendingReview) {
        this.pendingReview = pendingReview;
    }

    public int getUnDecided() {
        return unDecided;
    }

    public void setUnDecided(int unDecided) {
        this.unDecided = unDecided;
    }

    public String getRunType() {
        return runType;
    }

    public void setRunType(String runType) {
        this.runType = runType;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }
}
