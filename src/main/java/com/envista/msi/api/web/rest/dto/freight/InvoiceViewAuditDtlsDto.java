package com.envista.msi.api.web.rest.dto.freight;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedStoredProcedureQueries;
import javax.persistence.NamedStoredProcedureQuery;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureParameter;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by Sreedhar.T on 7/25/2017.
 */
@NamedStoredProcedureQueries({
        @NamedStoredProcedureQuery(name = "InvViewDtls.auditDetails", procedureName = "shp_frt_inv_audit_chrgs_proc",
                resultClasses = InvoiceViewAuditDtlsDto.class,
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, name = "refCur", type = Void.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "invoiceId", type = Long.class)
                })
})

@Entity
public class InvoiceViewAuditDtlsDto implements Serializable{

    @Id
    @Column(name = "rownum")
    private Long rowNum;

    @Column(name = "description")
    private String carrierChargeDesc;

    @Column(name = "invweight")
    private Long invoiceWeight;

    @Column(name = "invweightuom")
    private String invoiceWeightUom;

    @Column(name = "invqty")
    private Long invQty;

    @Column(name = "invqtyuom")
    private String invoiceQtyUom;

    @Column(name = "crrfreight")
    private String carrierFreight;

    @Column(name = "invrate")
    private BigDecimal invoiceRate;

    @Column(name = "invratingmethod")
    private String ratingMethod;

    @Column(name = "amount")
    private BigDecimal invoiceAmount;

    @Column(name = "currency")
    private String currency;

    @Column(name = "auditchargedesc")
    private String auditChargeDesc;

    @Column(name ="auditweight")
    private BigDecimal auditWeight;

    @Column(name = "auditweightuom")
    private String auditWeightUom;

    @Column(name = "auditqty")
    private Long auditQty;

    @Column(name = "auditqtyuom")
    private String auditQtyUom;

    @Column(name = "auditfreight")
    private String auditFrtClass;

    @Column(name = "auditrate")
    private BigDecimal auditRate;

    @Column(name = "auditratemethod")
    private String auditRateMethod;

    @Column(name = "ratedamount")
    private BigDecimal auditRatedCharges;

    @Column(name = "auditcharges")
    private BigDecimal approvedCharges;

    @Column(name = "adjustmentreason")
    private String adjustmentReason;

    @Column(name = "ratingcomments")
    private String ratingComments;

    @Column(name = "loadmatchcomments")
    private String loadMatchComments;

    public Long getRowNum() {
        return rowNum;
    }

    public void setRowNum(Long rowNum) {
        this.rowNum = rowNum;
    }

    public String getCarrierChargeDesc() {
        return carrierChargeDesc;
    }

    public void setCarrierChargeDesc(String carrierChargeDesc) {
        this.carrierChargeDesc = carrierChargeDesc;
    }

    public Long getInvoiceWeight() {
        return invoiceWeight;
    }

    public void setInvoiceWeight(Long invoiceWeight) {
        this.invoiceWeight = invoiceWeight;
    }

    public String getInvoiceWeightUom() {
        return invoiceWeightUom;
    }

    public void setInvoiceWeightUom(String invoiceWeightUom) {
        this.invoiceWeightUom = invoiceWeightUom;
    }

    public Long getInvQty() {
        return invQty;
    }

    public void setInvQty(Long invQty) {
        this.invQty = invQty;
    }

    public String getInvoiceQtyUom() {
        return invoiceQtyUom;
    }

    public void setInvoiceQtyUom(String invoiceQtyUom) {
        this.invoiceQtyUom = invoiceQtyUom;
    }

    public String getCarrierFreight() {
        return carrierFreight;
    }

    public void setCarrierFreight(String carrierFreight) {
        this.carrierFreight = carrierFreight;
    }

    public BigDecimal getInvoiceRate() {
        return invoiceRate;
    }

    public void setInvoiceRate(BigDecimal invoiceRate) {
        this.invoiceRate = invoiceRate;
    }

    public String getRatingMethod() {
        return ratingMethod;
    }

    public void setRatingMethod(String ratingMethod) {
        this.ratingMethod = ratingMethod;
    }

   public BigDecimal getInvoiceAmount() {
        return invoiceAmount;
    }

    public void setInvoiceAmount(BigDecimal invoiceAmount) {
        this.invoiceAmount = invoiceAmount;
    }

     public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getAuditChargeDesc() {
        return auditChargeDesc;
    }

    public void setAuditChargeDesc(String auditChargeDesc) {
        this.auditChargeDesc = auditChargeDesc;
    }

    public BigDecimal getAuditWeight() {
        return auditWeight;
    }

    public void setAuditWeight(BigDecimal auditWeight) {
        this.auditWeight = auditWeight;
    }

    public String getAuditWeightUom() {
        return auditWeightUom;
    }

    public void setAuditWeightUom(String auditWeightUom) {
        this.auditWeightUom = auditWeightUom;
    }

    public Long getAuditQty() {
        return auditQty;
    }

    public void setAuditQty(Long auditQty) {
        this.auditQty = auditQty;
    }

    public String getAuditQtyUom() {
        return auditQtyUom;
    }

    public void setAuditQtyUom(String auditQtyUom) {
        this.auditQtyUom = auditQtyUom;
    }

    public String getAuditFrtClass() {
        return auditFrtClass;
    }

    public void setAuditFrtClass(String auditFrtClass) {
        this.auditFrtClass = auditFrtClass;
    }

    public BigDecimal getAuditRate() {
        return auditRate;
    }

    public void setAuditRate(BigDecimal auditRate) {
        this.auditRate = auditRate;
    }

    public String getAuditRateMethod() {
        return auditRateMethod;
    }

    public void setAuditRateMethod(String auditRateMethod) {
        this.auditRateMethod = auditRateMethod;
    }

    public BigDecimal getAuditRatedCharges() {
        return auditRatedCharges;
    }

    public void setAuditRatedCharges(BigDecimal auditRatedCharges) {
        this.auditRatedCharges = auditRatedCharges;
    }

    public BigDecimal getApprovedCharges() {
        return approvedCharges;
    }

    public void setApprovedCharges(BigDecimal approvedCharges) {
        this.approvedCharges = approvedCharges;
    }

    public String getAdjustmentReason() {
        return adjustmentReason;
    }

    public void setAdjustmentReason(String adjustmentReason) {
        this.adjustmentReason = adjustmentReason;
    }

    public String getRatingComments() {
        return ratingComments;
    }

    public void setRatingComments(String ratingComments) {
        this.ratingComments = ratingComments;
    }

    public String getLoadMatchComments() {
        return loadMatchComments;
    }

    public void setLoadMatchComments(String loadMatchComments) {
        this.loadMatchComments = loadMatchComments;
    }
}
