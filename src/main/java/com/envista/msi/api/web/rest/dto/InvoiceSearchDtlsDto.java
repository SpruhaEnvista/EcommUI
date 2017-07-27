package com.envista.msi.api.web.rest.dto;

import com.envista.msi.api.domain.util.FreightStoreProcParam;
import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by user1 on 2/2/2017.
 */
@NamedStoredProcedureQueries({
        @NamedStoredProcedureQuery(name = "InvoiceSearchDtlsDto.getInvoiceDetails", procedureName = "SHP_FRT_INVOICE_LOOKUP_PROC",
                resultClasses = InvoiceSearchDtlsDto.class,
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = FreightStoreProcParam.InvoiceLookupParam.CUSTOMER_ID_CSV_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = FreightStoreProcParam.InvoiceLookupParam.INVOICE_NUMBER_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = FreightStoreProcParam.InvoiceLookupParam.PRO_NUMBER_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = FreightStoreProcParam.InvoiceLookupParam.BOL_NUMBER_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = FreightStoreProcParam.InvoiceLookupParam.INVOICE_ID_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = FreightStoreProcParam.InvoiceLookupParam.ACCOUNT_NUMBER_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = FreightStoreProcParam.InvoiceLookupParam.CARRIER_ID_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = FreightStoreProcParam.InvoiceLookupParam.DATE_TYPE_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = FreightStoreProcParam.InvoiceLookupParam.FROM_DATE_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = FreightStoreProcParam.InvoiceLookupParam.TO_DATE_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = FreightStoreProcParam.InvoiceLookupParam.SHIPPER_ZIP_CODE_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = FreightStoreProcParam.InvoiceLookupParam.RECEIVER_ZIP_CODE_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = FreightStoreProcParam.InvoiceLookupParam.BILLTO_ZIP_CODE_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = FreightStoreProcParam.InvoiceLookupParam.INVOICE_STATUS_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = FreightStoreProcParam.InvoiceLookupParam.CHECK_NUMBER_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = FreightStoreProcParam.InvoiceLookupParam.RUN_NUMBER_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = FreightStoreProcParam.InvoiceLookupParam.INVOICE_MODE_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = FreightStoreProcParam.InvoiceLookupParam.INVOICE_METHOD_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = FreightStoreProcParam.InvoiceLookupParam.PO_NUMBER_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = FreightStoreProcParam.InvoiceLookupParam.INCLUDE_EXCP_INV_PARAM, type = Integer.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = FreightStoreProcParam.InvoiceLookupParam.SERVICE_CODE_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = FreightStoreProcParam.InvoiceLookupParam.CHECK_AMOUNT_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = FreightStoreProcParam.InvoiceLookupParam.CHECK_DATE_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = FreightStoreProcParam.InvoiceLookupParam.CURRENCY_CODE_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = FreightStoreProcParam.InvoiceLookupParam.IS_LOOKUP_PARAM, type = Integer.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = FreightStoreProcParam.InvoiceLookupParam.IS_COUNT_PARAM, type = Integer.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = FreightStoreProcParam.InvoiceLookupParam.OFFSET_PARAM, type = Integer.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = FreightStoreProcParam.InvoiceLookupParam.LIMIT_PARAM, type = Integer.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = FreightStoreProcParam.InvoiceLookupParam.USER_ID_PARAM, type = Long.class),
                        @StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, name = FreightStoreProcParam.InvoiceLookupParam.INVOICE_DETAILS_PARAM, type = void.class)
                }
        ),
        @NamedStoredProcedureQuery(name = "InvoiceSearchDtlsDto.getInvoiceDetailsCount", procedureName = "SHP_FRT_INVOICE_LOOKUP_PROC",
                resultSetMappings = {"InvoiceSearchDtlsDto.getInvoiceDetailsCountMapping"},
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = FreightStoreProcParam.InvoiceLookupParam.CUSTOMER_ID_CSV_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = FreightStoreProcParam.InvoiceLookupParam.INVOICE_NUMBER_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = FreightStoreProcParam.InvoiceLookupParam.PRO_NUMBER_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = FreightStoreProcParam.InvoiceLookupParam.BOL_NUMBER_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = FreightStoreProcParam.InvoiceLookupParam.INVOICE_ID_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = FreightStoreProcParam.InvoiceLookupParam.ACCOUNT_NUMBER_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = FreightStoreProcParam.InvoiceLookupParam.CARRIER_ID_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = FreightStoreProcParam.InvoiceLookupParam.DATE_TYPE_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = FreightStoreProcParam.InvoiceLookupParam.FROM_DATE_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = FreightStoreProcParam.InvoiceLookupParam.TO_DATE_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = FreightStoreProcParam.InvoiceLookupParam.SHIPPER_ZIP_CODE_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = FreightStoreProcParam.InvoiceLookupParam.RECEIVER_ZIP_CODE_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = FreightStoreProcParam.InvoiceLookupParam.BILLTO_ZIP_CODE_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = FreightStoreProcParam.InvoiceLookupParam.INVOICE_STATUS_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = FreightStoreProcParam.InvoiceLookupParam.CHECK_NUMBER_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = FreightStoreProcParam.InvoiceLookupParam.RUN_NUMBER_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = FreightStoreProcParam.InvoiceLookupParam.INVOICE_MODE_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = FreightStoreProcParam.InvoiceLookupParam.INVOICE_METHOD_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = FreightStoreProcParam.InvoiceLookupParam.PO_NUMBER_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = FreightStoreProcParam.InvoiceLookupParam.INCLUDE_EXCP_INV_PARAM, type = Integer.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = FreightStoreProcParam.InvoiceLookupParam.SERVICE_CODE_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = FreightStoreProcParam.InvoiceLookupParam.CHECK_AMOUNT_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = FreightStoreProcParam.InvoiceLookupParam.CHECK_DATE_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = FreightStoreProcParam.InvoiceLookupParam.CURRENCY_CODE_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = FreightStoreProcParam.InvoiceLookupParam.IS_LOOKUP_PARAM, type = Integer.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = FreightStoreProcParam.InvoiceLookupParam.IS_COUNT_PARAM, type = Integer.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = FreightStoreProcParam.InvoiceLookupParam.OFFSET_PARAM, type = Integer.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = FreightStoreProcParam.InvoiceLookupParam.LIMIT_PARAM, type = Integer.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = FreightStoreProcParam.InvoiceLookupParam.USER_ID_PARAM, type = Long.class),
                        @StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, name = FreightStoreProcParam.InvoiceLookupParam.INVOICE_DETAILS_PARAM, type = void.class)
                }
        )
})

@SqlResultSetMappings({
        @SqlResultSetMapping(name = "InvoiceSearchDtlsDto.getInvoiceDetailsCountMapping",
                classes = {
                        @ConstructorResult(
                                targetClass = InvoiceSearchDtlsCount.class,
                                columns = {
                                        @ColumnResult(name = "ROWCOUNT", type = Integer.class)
                                }
                        )
                })
})
@Entity
@JsonInclude(JsonInclude.Include.NON_NULL)
public class InvoiceSearchDtlsDto implements Serializable {

    @Id
    @Column(name="RN",unique = true)
    private Long Id;

    @Column(name="nsp_invoice_details_id")
    private Long invoiceId;

    @Column(name="consol_parent_id")
    private Long consolParentId;

    @Column(name="carrier_id")
    private Long carrierId;

    @Column(name="customer_id")
    private Long customerId;

    @Column(name="scann_id")
    private Long scanId;

    @Column(name="service_code_id")
    private Long serviceCodeId;

    @Column(name="auditor_comments")
    private String auditorComments;

    @Column(name="bol_number")
    private String bolNumber;

    @Column(name="invoice_number")
    private String invoiceNumber;

    @Column(name="pro_number")
    private String proNumber;

    @Column(name="shipper_country")
    private String shipperCountry;

    @Column(name="credit_memo")
    private String creditMemo;

    @Column(name="carrier_comments")
    private String carrierComments;

    @Column(name="shipper_state")
    private String shipperState;

    @Column(name="receiver_country")
    private String receiverCountry;

    @Column(name="receiver_state")
    private String receiverState;

    @Column(name="total_weight")
    private BigDecimal totalWeight;

    @Column(name="subtotal_amount")
    private BigDecimal subTotalAmt;

    @Column(name="gl_accounts_code")
    private Long glAccountCode;

    @Column(name="total_due_amount")
    private BigDecimal totalDueAmt;

    @Temporal(TemporalType.DATE)
    @Column(name="invoice_due_date")
    private Date invoiceDueDate;

    @Column(name="shipper_name")
    private String shipperName;

    @Column(name="shipper_address_1")
    private String shipperAddress1;

    @Column(name="shipper_city")
    private String shipperCity;

    @Column(name="receiver_city")
    private String receiverCity;

    @Column(name="receiver_name")
    private String receiverName;

    @Column(name="receiver_address_1")
    private String receiverAddress;

    @Column(name="invoice_type")
    private String invoiceType;

    @Temporal(TemporalType.DATE)
    @Column(name="ship_date")
    private Date shipDate;

    @Column(name="shipper_zipcode")
    private String shipperZipCode;

    @Column(name="receiver_zipcode")
    private String receiverZipCode;

    @Column(name = "FREIGHT_INVOICE_TYPE")
    private String freightInvoiceType;

    @Column(name="run_no")
    private String runNo;

    @Column(name="check_no")
    private String checkNumber;

    @Temporal(TemporalType.DATE)
    @Column(name="check_date")
    private Date checkDate;

    @Column(name = "CHECK_AMOUNT")
    private Double checkAmount;

    @Column(name = "TOTAL_CHARGES")
    private Double totalCharges;

    @Column(name = "INVOICE_MODE_AL")
    private String invoiceModeAl;

    @Column(name = "INVOICE_STATUS_AL")
    private String invoiceStatusAl;

    @Temporal(TemporalType.DATE)
    @Column(name = "BILL_DATE")
    private Date billDate;

    @Temporal(TemporalType.DATE)
    @Column(name = "CLOSED_DATE")
    private Date closedDate;

    @Temporal(TemporalType.DATE)
    @Column(name = "CREATE_DATE")
    private Date createDate;

    @Column(name = "VARIANCE")
    private Double variance;

    @Column(name = "GAIN_SHARE")
    private String gainShare;

    @Temporal(TemporalType.DATE)
    @Column(name = "LOAD_MATCH_DATE")
    private Date loadMatchDate;

    @Column(name = "LOAD_MATCHED")
    private String loadMatched;

    @Column(name = "REFERENCE1")
    private String reference1;

    @Column(name = "REFERENCE2")
    private String reference2;

    @Column(name = "REFERENCE3")
    private String reference3;

    @Column(name = "REFERENCE4")
    private String reference4;

    @Column(name = "PO_NUMBER")
    private String poNumber;

    @Column(name = "ACCOUNT_NUMBER")
    private String accountNumber;

    @Temporal(TemporalType.DATE)
    @Column(name = "DELIVERY_DATE")
    private Date deliveryDate;

    @Temporal(TemporalType.DATE)
    @Column(name = "RECEIVED_DATE")
    private Date receivedDate;

    @Column(name = "BILL_OPTION_AL")
    private String billOptionAl;

    @Column(name = "CURRENCY_CODE_AL")
    private String currencyCodeAl;

    @Column(name = "CUSTOMER_COMMENTS")
    private String customerComments;

    @Column(name = "PORT_OF_ORIGIN")
    private String portOfOrigin;

    @Column(name = "PORT_OF_DESTINATION")
    private String portOfDestination;

    @Column(name = "BILLED_MILES")
    private Double billedMiles;

    @Column(name = "RATED_MILES")
    private Double ratedMiles;

    @Column(name = "EXCEPTION_REASON_AL")
    private String exceptionReasonAl;

    @Column(name = "LOAD_MATCH_EXCP_DATE")
    private Date loadMatchExceptionDate;

    @Column(name = "CUSTOMER_NAME")
    private String customerName;

    @Column(name = "CUSTOMER_CODE")
    private String customerCode;

    @Column(name = "CARRIER_NAME")
    private String carrierName;

    @Column(name = "SCAC_CODE")
    private String scacCode;

    @Column(name = "CCR_ACTIVE")
    private Boolean ccrActive;

    @Column(name = "BILLTO_ADDRESS_1")
    private String billToAddress1;

    @Column(name = "BILLTO_NAME")
    private String billToName;

    @Column(name = "BILLTO_CITY")
    private String billToCity;

    @Column(name = "BILLTO_STATE")
    private String billToState;

    @Column(name = "BILLTO_COUNTRY")
    private String billToCountry;

    @Column(name = "BILLTO_ZIPCODE")
    private String billToZipCode;

    @Column(name = "LANE_ID")
    private String laneId;

    @Column(name = "REMIT_COMMENTS")
    private String remitComments;

    @Column(name = "PORT_OF_ORIGIN_CODE")
    private String portOfOriginCode;

    @Column(name = "PORT_OF_DESTINATION_CODE")
    private String portOfDestinationCode;

    @Column(name = "INCO_TERM_CODE")
    private String incoTermCode;

    @Column(name = "INCO_TERM_POINT")
    private String incoTermPoint;

    @Column(name = "SERVICE_LEVEL")
    private String serviceLevel;

    @Column(name = "EXCEPTION_REASON")
    private String exceptionReason;

    @Column(name = "BILL_OPTION")
    private String billOption;

    @Column(name = "CURRENCY_CODE")
    private String currencyCode;

    @Column(name = "AUDIT_ADJUSTMENT_REASON")
    private String auditAdjustmentReason;

    @Column(name = "INVOICE_MODE")
    private String invoiceMode;

    @Column(name = "INVOICE_STATUS")
    private String invoiceStatus;

    @Column(name = "APPROVED_AMOUNT")
    private Double approvedAmount;

    @Column(name = "FUNDS_RECEIVED_DATE")
    private Date fundsReceivedDate;

    @Column(name = "INVOICE_METHOD")
    private String invoiceMethod;

    @Column(name = "EX_RATE")
    private Double exchangeRate;

    @Column(name = "CONVERTED_APPROVED_CHARGES")
    private Double convertedApprovedCharges;

    @Column(name = "CONVERTED_TOTAL_CHARGES")
    private Double convertedTotalCharges;

    @Column(name = "CONVERTED_CURRENCY_CODE")
    private String convertedCurrencyCode;

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public Long getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(Long invoiceId) {
        this.invoiceId = invoiceId;
    }

    public Long getConsolParentId() {
        return consolParentId;
    }

    public void setConsolParentId(Long consolParentId) {
        this.consolParentId = consolParentId;
    }

    public Long getCarrierId() {
        return carrierId;
    }

    public void setCarrierId(Long carrierId) {
        this.carrierId = carrierId;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Long getScanId() {
        return scanId;
    }

    public void setScanId(Long scanId) {
        this.scanId = scanId;
    }

    public Long getServiceCodeId() {
        return serviceCodeId;
    }

    public void setServiceCodeId(Long serviceCodeId) {
        this.serviceCodeId = serviceCodeId;
    }

    public String getAuditorComments() {
        return auditorComments;
    }

    public void setAuditorComments(String auditorComments) {
        this.auditorComments = auditorComments;
    }

    public String getBolNumber() {
        return bolNumber;
    }

    public void setBolNumber(String bolNumber) {
        this.bolNumber = bolNumber;
    }

    public String getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setInvoiceNumber(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    public String getProNumber() {
        return proNumber;
    }

    public void setProNumber(String proNumber) {
        this.proNumber = proNumber;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getShipperCountry() {
        return shipperCountry;
    }

    public void setShipperCountry(String shipperCountry) {
        this.shipperCountry = shipperCountry;
    }

    public String getCreditMemo() {
        return creditMemo;
    }

    public void setCreditMemo(String creditMemo) {
        this.creditMemo = creditMemo;
    }

    public String getCarrierComments() {
        return carrierComments;
    }

    public void setCarrierComments(String carrierComments) {
        this.carrierComments = carrierComments;
    }

    public String getShipperState() {
        return shipperState;
    }

    public void setShipperState(String shipperState) {
        this.shipperState = shipperState;
    }

    public String getReceiverCountry() {
        return receiverCountry;
    }

    public void setReceiverCountry(String receiverCountry) {
        this.receiverCountry = receiverCountry;
    }

    public String getReceiverState() {
        return receiverState;
    }

    public void setReceiverState(String receiverState) {
        this.receiverState = receiverState;
    }

    public BigDecimal getTotalWeight() {
        return totalWeight;
    }

    public void setTotalWeight(BigDecimal totalWeight) {
        this.totalWeight = totalWeight;
    }

    public BigDecimal getSubTotalAmt() {
        return subTotalAmt;
    }

    public void setSubTotalAmt(BigDecimal subTotalAmt) {
        this.subTotalAmt = subTotalAmt;
    }

    public Long getGlAccountCode() {
        return glAccountCode;
    }

    public void setGlAccountCode(Long glAccountCode) {
        this.glAccountCode = glAccountCode;
    }

    public BigDecimal getTotalDueAmt() {
        return totalDueAmt;
    }

    public void setTotalDueAmt(BigDecimal totalDueAmt) {
        this.totalDueAmt = totalDueAmt;
    }

    public Date getInvoiceDueDate() {
        return invoiceDueDate;
    }

    public void setInvoiceDueDate(Date invoiceDueDate) {
        this.invoiceDueDate = invoiceDueDate;
    }

    public String getShipperName() {
        return shipperName;
    }

    public void setShipperName(String shipperName) {
        this.shipperName = shipperName;
    }

    public String getShipperAddress1() {
        return shipperAddress1;
    }

    public void setShipperAddress1(String shipperAddress1) {
        this.shipperAddress1 = shipperAddress1;
    }

    public String getShipperCity() {
        return shipperCity;
    }

    public void setShipperCity(String shipperCity) {
        this.shipperCity = shipperCity;
    }

    public String getReceiverCity() {
        return receiverCity;
    }

    public void setReceiverCity(String receiverCity) {
        this.receiverCity = receiverCity;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public String getReceiverAddress() {
        return receiverAddress;
    }

    public void setReceiverAddress(String receiverAddress) {
        this.receiverAddress = receiverAddress;
    }

    public String getInvoiceType() {
        return invoiceType;
    }

    public void setInvoiceType(String invoiceType) {
        this.invoiceType = invoiceType;
    }

    public Date getShipDate() {
        return shipDate;
    }

    public void setShipDate(Date shipDate) {
        this.shipDate = shipDate;
    }

    public String getShipperZipCode() {
        return shipperZipCode;
    }

    public void setShipperZipCode(String shipperZipCode) {
        this.shipperZipCode = shipperZipCode;
    }

    public String getReceiverZipCode() {
        return receiverZipCode;
    }

    public void setReceiverZipCode(String receiverZipCode) {
        this.receiverZipCode = receiverZipCode;
    }

    public String getRunNo() {
        return runNo;
    }

    public void setRunNo(String runNo) {
        this.runNo = runNo;
    }

    public String getCheckNumber() {
        return checkNumber;
    }

    public void setCheckNumber(String checkNumber) {
        this.checkNumber = checkNumber;
    }

    public Date getCheckDate() {
        return checkDate;
    }

    public void setCheckDate(Date checkDate) {
        this.checkDate = checkDate;
    }

    public String getFreightInvoiceType() {
        return freightInvoiceType;
    }

    public void setFreightInvoiceType(String freightInvoiceType) {
        this.freightInvoiceType = freightInvoiceType;
    }

    public Double getCheckAmount() {
        return checkAmount;
    }

    public void setCheckAmount(Double checkAmount) {
        this.checkAmount = checkAmount;
    }

    public Double getTotalCharges() {
        return totalCharges;
    }

    public void setTotalCharges(Double totalCharges) {
        this.totalCharges = totalCharges;
    }

    public String getInvoiceModeAl() {
        return invoiceModeAl;
    }

    public void setInvoiceModeAl(String invoiceModeAl) {
        this.invoiceModeAl = invoiceModeAl;
    }

    public String getInvoiceStatusAl() {
        return invoiceStatusAl;
    }

    public void setInvoiceStatusAl(String invoiceStatusAl) {
        this.invoiceStatusAl = invoiceStatusAl;
    }

    public Date getBillDate() {
        return billDate;
    }

    public void setBillDate(Date billDate) {
        this.billDate = billDate;
    }

    public Date getClosedDate() {
        return closedDate;
    }

    public void setClosedDate(Date closedDate) {
        this.closedDate = closedDate;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Double getVariance() {
        return variance;
    }

    public void setVariance(Double variance) {
        this.variance = variance;
    }

    public String getGainShare() {
        return gainShare;
    }

    public void setGainShare(String gainShare) {
        this.gainShare = gainShare;
    }

    public Date getLoadMatchDate() {
        return loadMatchDate;
    }

    public void setLoadMatchDate(Date loadMatchDate) {
        this.loadMatchDate = loadMatchDate;
    }

    public String getLoadMatched() {
        return loadMatched;
    }

    public void setLoadMatched(String loadMatched) {
        this.loadMatched = loadMatched;
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

    public String getReference3() {
        return reference3;
    }

    public void setReference3(String reference3) {
        this.reference3 = reference3;
    }

    public String getReference4() {
        return reference4;
    }

    public void setReference4(String reference4) {
        this.reference4 = reference4;
    }

    public String getPoNumber() {
        return poNumber;
    }

    public void setPoNumber(String poNumber) {
        this.poNumber = poNumber;
    }

    public Date getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(Date deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public Date getReceivedDate() {
        return receivedDate;
    }

    public void setReceivedDate(Date receivedDate) {
        this.receivedDate = receivedDate;
    }

    public String getBillOptionAl() {
        return billOptionAl;
    }

    public void setBillOptionAl(String billOptionAl) {
        this.billOptionAl = billOptionAl;
    }

    public String getCurrencyCodeAl() {
        return currencyCodeAl;
    }

    public void setCurrencyCodeAl(String currencyCodeAl) {
        this.currencyCodeAl = currencyCodeAl;
    }

    public String getCustomerComments() {
        return customerComments;
    }

    public void setCustomerComments(String customerComments) {
        this.customerComments = customerComments;
    }

    public String getPortOfOrigin() {
        return portOfOrigin;
    }

    public void setPortOfOrigin(String portOfOrigin) {
        this.portOfOrigin = portOfOrigin;
    }

    public String getPortOfDestination() {
        return portOfDestination;
    }

    public void setPortOfDestination(String portOfDestination) {
        this.portOfDestination = portOfDestination;
    }

    public Double getBilledMiles() {
        return billedMiles;
    }

    public void setBilledMiles(Double billedMiles) {
        this.billedMiles = billedMiles;
    }

    public Double getRatedMiles() {
        return ratedMiles;
    }

    public void setRatedMiles(Double ratedMiles) {
        this.ratedMiles = ratedMiles;
    }

    public String getExceptionReasonAl() {
        return exceptionReasonAl;
    }

    public void setExceptionReasonAl(String exceptionReasonAl) {
        this.exceptionReasonAl = exceptionReasonAl;
    }

    public Date getLoadMatchExceptionDate() {
        return loadMatchExceptionDate;
    }

    public void setLoadMatchExceptionDate(Date loadMatchExceptionDate) {
        this.loadMatchExceptionDate = loadMatchExceptionDate;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
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

    public String getScacCode() {
        return scacCode;
    }

    public void setScacCode(String scacCode) {
        this.scacCode = scacCode;
    }

    public Boolean getCcrActive() {
        return ccrActive;
    }

    public void setCcrActive(Boolean ccrActive) {
        this.ccrActive = ccrActive;
    }

    public String getBillToAddress1() {
        return billToAddress1;
    }

    public void setBillToAddress1(String billToAddress1) {
        this.billToAddress1 = billToAddress1;
    }

    public String getBillToName() {
        return billToName;
    }

    public void setBillToName(String billToName) {
        this.billToName = billToName;
    }

    public String getBillToCity() {
        return billToCity;
    }

    public void setBillToCity(String billToCity) {
        this.billToCity = billToCity;
    }

    public String getBillToState() {
        return billToState;
    }

    public void setBillToState(String billToState) {
        this.billToState = billToState;
    }

    public String getBillToCountry() {
        return billToCountry;
    }

    public void setBillToCountry(String billToCountry) {
        this.billToCountry = billToCountry;
    }

    public String getBillToZipCode() {
        return billToZipCode;
    }

    public void setBillToZipCode(String billToZipCode) {
        this.billToZipCode = billToZipCode;
    }

    public String getLaneId() {
        return laneId;
    }

    public void setLaneId(String laneId) {
        this.laneId = laneId;
    }

    public String getRemitComments() {
        return remitComments;
    }

    public void setRemitComments(String remitComments) {
        this.remitComments = remitComments;
    }

    public String getPortOfOriginCode() {
        return portOfOriginCode;
    }

    public void setPortOfOriginCode(String portOfOriginCode) {
        this.portOfOriginCode = portOfOriginCode;
    }

    public String getPortOfDestinationCode() {
        return portOfDestinationCode;
    }

    public void setPortOfDestinationCode(String portOfDestinationCode) {
        this.portOfDestinationCode = portOfDestinationCode;
    }

    public String getIncoTermCode() {
        return incoTermCode;
    }

    public void setIncoTermCode(String incoTermCode) {
        this.incoTermCode = incoTermCode;
    }

    public String getIncoTermPoint() {
        return incoTermPoint;
    }

    public void setIncoTermPoint(String incoTermPoint) {
        this.incoTermPoint = incoTermPoint;
    }

    public String getServiceLevel() {
        return serviceLevel;
    }

    public void setServiceLevel(String serviceLevel) {
        this.serviceLevel = serviceLevel;
    }

    public String getExceptionReason() {
        return exceptionReason;
    }

    public void setExceptionReason(String exceptionReason) {
        this.exceptionReason = exceptionReason;
    }

    public String getBillOption() {
        return billOption;
    }

    public void setBillOption(String billOption) {
        this.billOption = billOption;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public String getAuditAdjustmentReason() {
        return auditAdjustmentReason;
    }

    public void setAuditAdjustmentReson(String auditAdjustmentReson) {
        this.auditAdjustmentReason = auditAdjustmentReson;
    }

    public String getInvoiceMode() {
        return invoiceMode;
    }

    public void setInvoiceMode(String invoiceMode) {
        this.invoiceMode = invoiceMode;
    }

    public String getInvoiceStatus() {
        return invoiceStatus;
    }

    public void setInvoiceStatus(String invoiceStatus) {
        this.invoiceStatus = invoiceStatus;
    }

    public Double getApprovedAmount() {
        return approvedAmount;
    }

    public void setApprovedAmount(Double approvedAmount) {
        this.approvedAmount = approvedAmount;
    }

    public Date getFundsReceivedDate() {
        return fundsReceivedDate;
    }

    public void setFundsReceivedDate(Date fundsReceivedDate) {
        this.fundsReceivedDate = fundsReceivedDate;
    }

    public String getInvoiceMethod() {
        return invoiceMethod;
    }

    public void setInvoiceMethod(String invoiceMethod) {
        this.invoiceMethod = invoiceMethod;
    }

    public void setAuditAdjustmentReason(String auditAdjustmentReason) {
        this.auditAdjustmentReason = auditAdjustmentReason;
    }

    public Double getExchangeRate() {
        return exchangeRate;
    }

    public void setExchangeRate(Double exchangeRate) {
        this.exchangeRate = exchangeRate;
    }

    public Double getConvertedApprovedCharges() {
        return convertedApprovedCharges;
    }

    public void setConvertedApprovedCharges(Double convertedApprovedCharges) {
        this.convertedApprovedCharges = convertedApprovedCharges;
    }

    public Double getConvertedTotalCharges() {
        return convertedTotalCharges;
    }

    public void setConvertedTotalCharges(Double convertedTotalCharges) {
        this.convertedTotalCharges = convertedTotalCharges;
    }

    public String getConvertedCurrencyCode() {
        return convertedCurrencyCode;
    }

    public void setConvertedCurrencyCode(String convertedCurrencyCode) {
        this.convertedCurrencyCode = convertedCurrencyCode;
    }
}
