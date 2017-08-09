package com.envista.msi.api.web.rest.dto.freight;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by Sreedhar.T on 7/19/2017.
 */
@NamedStoredProcedureQueries({
        @NamedStoredProcedureQuery(name = "InvSummInvDtls.invoiceDetails", procedureName = "shp_frt_inv_summ_inv_dtls",
                resultClasses = InvoiceSummaryInvDtlsDto.class,
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, name = "refCur", type = Void.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "invoiceId", type = Long.class)
                })
})
@Entity
public class InvoiceSummaryInvDtlsDto implements Serializable {

    @Id
    @Column(name = "rownum")
    private Long rowNum;

    @Column(name = "gl_accounts_code")
    private String glAccountsCode;

    @Column(name = "run_no")
    private String runNumber;

    @Column(name = "payment_type")
    private String paymentType;

    @Column(name="check_no")
    private String checkNo;

    @Column(name = "taxable_total")
    private BigDecimal taxableTotal;

    @Column(name = "non_taxable_total")
    private BigDecimal nonTaxableTotal;

    @Column(name = "tax_total")
    private BigDecimal taxTotal;

    @Column(name = "total_due_amount")
    private BigDecimal totalDueAmount;

    @Column(name = "currency")
    private String currency;

    @Column(name = "remit_comments")
    private String remitComments;

    public Long getRowNum() {
        return rowNum;
    }

    public void setRowNum(Long rowNum) {
        this.rowNum = rowNum;
    }

    public String getGlAccountsCode() {
        return glAccountsCode;
    }

    public void setGlAccountsCode(String glAccountsCode) {
        this.glAccountsCode = glAccountsCode;
    }

    public String getRunNumber() {
        return runNumber;
    }

    public void setRunNumber(String runNumber) {
        this.runNumber = runNumber;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public String getCheckNo() {
        return checkNo;
    }

    public void setCheckNo(String checkNo) {
        this.checkNo = checkNo;
    }

    public BigDecimal getTaxableTotal() {
        return taxableTotal;
    }

    public void setTaxableTotal(BigDecimal taxableTotal) {
        this.taxableTotal = taxableTotal;
    }

    public BigDecimal getNonTaxableTotal() {
        return nonTaxableTotal;
    }

    public void setNonTaxableTotal(BigDecimal nonTaxableTotal) {
        this.nonTaxableTotal = nonTaxableTotal;
    }

    public BigDecimal getTaxTotal() {
        return taxTotal;
    }

    public void setTaxTotal(BigDecimal taxTotal) {
        this.taxTotal = taxTotal;
    }

    public BigDecimal getTotalDueAmount() {
        return totalDueAmount;
    }

    public void setTotalDueAmount(BigDecimal totalDueAmount) {
        this.totalDueAmount = totalDueAmount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getRemitComments() {
        return remitComments;
    }

    public void setRemitComments(String remitComments) {
        this.remitComments = remitComments;
    }
}
