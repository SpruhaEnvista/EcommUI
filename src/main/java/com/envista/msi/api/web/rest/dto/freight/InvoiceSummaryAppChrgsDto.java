package com.envista.msi.api.web.rest.dto.freight;

import com.envista.msi.api.web.rest.dto.reports.ReportCodeValueDto;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by Sreedhar.T on 7/19/2017.
 */

@NamedStoredProcedureQueries({
        @NamedStoredProcedureQuery(name = "InvSummAppChrgs.getCharges", procedureName = "shp_frt_inv_summ_app_chrgs",
                resultClasses = InvoiceSummaryAppChrgsDto.class,
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, name = "refCur", type = Void.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "invoiceId", type = Long.class)
                })
})
@Entity
public class InvoiceSummaryAppChrgsDto implements Serializable {

    @Id
    @Column(name="ROWNUM")
    private Long rowNum;

    @Column(name="CARRIER_CHARGE_DESC")
    private String carrierChargeDesc;

    @Column(name="AUDIT_CHARGE_CODE")
    private String auditChargeCode;

    @Column(name="TAXABLE")
    private String taxable;

    @Column(name="QTY")
    private Long qty;

    @Column(name="WEIGHT")
    private BigDecimal weight;

    @Column(name="AMOUNT")
    private BigDecimal amount;


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

    public String getAuditChargeCode() {
        return auditChargeCode;
    }

    public void setAuditChargeCode(String auditChargeCode) {
        this.auditChargeCode = auditChargeCode;
    }

    public String getTaxable() {
        return taxable;
    }

    public void setTaxable(String taxable) {
        this.taxable = taxable;
    }

    public Long getQty() {
        return qty;
    }

    public void setQty(Long qty) {
        this.qty = qty;
    }

    public BigDecimal getWeight() {
        return weight;
    }

    public void setWeight(BigDecimal weight) {
        this.weight = weight;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
