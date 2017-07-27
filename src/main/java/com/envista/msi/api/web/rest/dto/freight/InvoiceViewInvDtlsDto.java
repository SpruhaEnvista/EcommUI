package com.envista.msi.api.web.rest.dto.freight;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by Sreedhar.T on 7/20/2017.
 */
@NamedStoredProcedureQueries({
        @NamedStoredProcedureQuery(name = "InvViewInvDtls.invoiceDetails", procedureName = "shp_frt_inv_htab_invdtls_proc",
                resultClasses = InvoiceViewInvDtlsDto.class,
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, name = "refCur", type = Void.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "invoiceId", type = Long.class)
               })
})
@Entity
public class InvoiceViewInvDtlsDto  implements Serializable{

    @Id
    @Column(name = "rownum")
    private Long rowNum;

    @Column(name = "scac")
    private String scacCode;

    @Column(name = "name1")
    private String carrierName;

    @Column(name = "modes")
    private String modes;

    @Column(name = "remit")
    private String remitAddress;

    @Column(name = "account_number")
    private String accountNumber;

    @Column(name = "interline_carrier")
    private String interlineCarrier;

    @Column(name = "due_date")
    private String dueDate;

    @Column(name = "delivery_date")
    private String deliveryDate;

    @Column(name = "intl_delivery_date")
    private String interlineDeliveryDate;

    @Column(name="ship_date")
    private String shipDate;

    @Column(name = "received_date")
    private String receivedDate;

    @Column(name = "check_date")
    private String checkDate;

    @Column(name = "inv_number")
    private String invoiceNumber;

    @Column(name = "bol_number")
    private String bolNumber;

    @Column(name = "po_number")
    private String poNumber;

    @Column(name = "pro_number")
    private String proNumber;

    @Column(name = "ref1")
    private String reference1;

    @Column(name = "ref2")
    private String reference2;

    public Long getRowNum() {
        return rowNum;
    }

    public void setRowNum(Long rowNum) {
        this.rowNum = rowNum;
    }

    public String getScacCode() {
        return scacCode;
    }

    public void setScacCode(String scacCode) {
        this.scacCode = scacCode;
    }

    public String getCarrierName() {
        return carrierName;
    }

    public void setCarrierName(String carrierName) {
        this.carrierName = carrierName;
    }

    public String getModes() {
        return modes;
    }

    public void setModes(String modes) {
        this.modes = modes;
    }

    public String getRemitAddress() {
        return remitAddress;
    }

    public void setRemitAddress(String remitAddress) {
        this.remitAddress = remitAddress;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getInterlineCarrier() {
        return interlineCarrier;
    }

    public void setInterlineCarrier(String interlineCarrier) {
        this.interlineCarrier = interlineCarrier;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public String getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(String deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public String getInterlineDeliveryDate() {
        return interlineDeliveryDate;
    }

    public void setInterlineDeliveryDate(String interlineDeliveryDate) {
        this.interlineDeliveryDate = interlineDeliveryDate;
    }

    public String getShipDate() {
        return shipDate;
    }

    public void setShipDate(String shipDate) {
        this.shipDate = shipDate;
    }

    public String getReceivedDate() {
        return receivedDate;
    }

    public void setReceivedDate(String receivedDate) {
        this.receivedDate = receivedDate;
    }

    public String getCheckDate() {
        return checkDate;
    }

    public void setCheckDate(String checkDate) {
        this.checkDate = checkDate;
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

    public String getPoNumber() {
        return poNumber;
    }

    public void setPoNumber(String poNumber) {
        this.poNumber = poNumber;
    }

    public String getProNumber() {
        return proNumber;
    }

    public void setProNumber(String proNumber) {
        this.proNumber = proNumber;
    }

    public String getReference1() {
        return reference1;
    }

    public void setReference1(String reference1) {
        this.reference1 = reference1;
    }

    public String getReference2() {
        return reference2;
    }

    public void setReference2(String reference2) {
        this.reference2 = reference2;
    }
}

