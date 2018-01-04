package com.envista.msi.api.web.rest.dto.glom;

import com.envista.msi.api.web.rest.dto.invoicing.InvoicingRuleDto;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by KRISHNAREDDYM on 1/4/2018.
 */
@NamedStoredProcedureQueries({
        @NamedStoredProcedureQuery(name = "RunReportDto.runScript", procedureName = "SHP_GLM_RUN_SCRIPT_PRO",
                resultClasses = InvoicingRuleDto.class,
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_RUN_SCRIPT_ID", type = Long.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_ACTION_TYPE", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, name = "P_REFCUR_RUN_SCRIPT_INFO", type = Void.class)
                })
})
@Entity
public class RunReportDto implements Serializable {

    @Id
    @Column(name = "NSP_INVOICE_DETAILS_ID")
    private Long invoiceId;

    @Column(name = "INVOICE_NUMBER")
    private String invoiceNumber;

    @Column(name = "BOL_NUMBER")
    private String bolNumber;

    @Column(name = "PRO_NUMBER")
    private String proNumber;

    @Column(name = "CREATE_DATE")
    private String createDate;

    @Column(name = "GL_ACCOUNTS_CODE")
    private String glAccountCode;

    @Column(name = "SHIPPER_NAME")
    private String shipperName;

    @Column(name = "INVOICE_MODE_VAL")
    private String invoiceMode;

    @Column(name = "CUSTOMER_NAME")
    private String customerName;

    @Column(name = "CARRIER_NAME")
    private String carrierName;

    public RunReportDto() {
    }

    public Long getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(Long invoiceId) {
        this.invoiceId = invoiceId;
    }

    public String getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setInvoiceNumber(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    public String getBolNumber() {
        return bolNumber;
    }

    public void setBolNumber(String bolNumber) {
        this.bolNumber = bolNumber;
    }

    public String getProNumber() {
        return proNumber;
    }

    public void setProNumber(String proNumber) {
        this.proNumber = proNumber;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getGlAccountCode() {
        return glAccountCode;
    }

    public void setGlAccountCode(String glAccountCode) {
        this.glAccountCode = glAccountCode;
    }

    public String getShipperName() {
        return shipperName;
    }

    public void setShipperName(String shipperName) {
        this.shipperName = shipperName;
    }

    public String getInvoiceMode() {
        return invoiceMode;
    }

    public void setInvoiceMode(String invoiceMode) {
        this.invoiceMode = invoiceMode;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCarrierName() {
        return carrierName;
    }

    public void setCarrierName(String carrierName) {
        this.carrierName = carrierName;
    }
}
