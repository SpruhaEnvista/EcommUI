package com.envista.msi.api.web.rest.dto.dashboard.auditactivity;

import com.envista.msi.api.domain.util.DashboardStoredProcParam;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Sujit kumar on 02/01/2018.
 */
@NamedStoredProcedureQueries({
        @NamedStoredProcedureQuery(
                name = FreightAuditSavingDto.Config.FreightAuditSaving.STORED_PROCEDURE_QUERY_NAME,
                procedureName = FreightAuditSavingDto.Config.FreightAuditSaving.STORED_PROCEDURE_NAME,
                resultSetMappings = {FreightAuditSavingDto.Config.FreightAuditSaving.STORED_PROCEDURE_QUERY_MAPPING},
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.DashboardFilterParams.DATE_TYPE_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.DashboardFilterParams.CONVERTED_CURRENCY_ID_PARAM, type = Long.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.DashboardFilterParams.CONVERTED_CURRENCY_CODE_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.DashboardFilterParams.CUSTOMER_IDS_CSV_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.DashboardFilterParams.CARRIER_IDS_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.DashboardFilterParams.MODES_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.DashboardFilterParams.SERVICES_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.DashboardFilterParams.LANES_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.DashboardFilterParams.FROM_DATE_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.DashboardFilterParams.TO_DATE_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.DashboardFilterParams.MODE_NAMES_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, name = "p_frt_saving", type = Void.class)
                }
        ),
        @NamedStoredProcedureQuery(
                name = FreightAuditSavingDto.Config.FreightAuditSavingStage2.STORED_PROCEDURE_QUERY_NAME,
                procedureName = FreightAuditSavingDto.Config.FreightAuditSavingStage2.STORED_PROCEDURE_NAME,
                resultSetMappings = {FreightAuditSavingDto.Config.FreightAuditSavingStage2.STORED_PROCEDURE_QUERY_MAPPING},
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.DashboardFilterParams.DATE_TYPE_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.DashboardFilterParams.CONVERTED_CURRENCY_ID_PARAM, type = Long.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.DashboardFilterParams.CONVERTED_CURRENCY_CODE_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.DashboardFilterParams.CUSTOMER_IDS_CSV_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.DashboardFilterParams.CARRIER_IDS_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.DashboardFilterParams.MODES_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.DashboardFilterParams.SERVICES_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.DashboardFilterParams.LANES_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.DashboardFilterParams.FROM_DATE_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.DashboardFilterParams.TO_DATE_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.DashboardFilterParams.MODE_NAMES_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, name = "p_frt_saving", type = Void.class)
                }
        )
})

@SqlResultSetMappings({
        @SqlResultSetMapping(
                name = FreightAuditSavingDto.Config.FreightAuditSaving.STORED_PROCEDURE_QUERY_MAPPING,
                classes = {
                        @ConstructorResult(
                                targetClass = FreightAuditSavingDto.class,
                                columns = {
                                        @ColumnResult(name = "CARRIER_NAME", type = String.class),
                                        @ColumnResult(name = "INVOICED_AMOUNT", type = Double.class),
                                        @ColumnResult(name = "APPROVED_AMOUNT", type = Double.class),
                                        @ColumnResult(name = "FREIGHT_SAVING", type = Double.class),
                                        @ColumnResult(name = "SAVINGS_PERCENTAGE", type = Double.class)
                                }
                        )
                }
        ),
        @SqlResultSetMapping(
                name = FreightAuditSavingDto.Config.FreightAuditSavingStage2.STORED_PROCEDURE_QUERY_MAPPING,
                classes = {
                        @ConstructorResult(
                                targetClass = FreightAuditSavingDto.class,
                                columns = {
                                        @ColumnResult(name = "CARRIER_NAME", type = String.class),
                                        @ColumnResult(name = "FREIGHT_SAVING", type = Double.class),
                                        @ColumnResult(name = "ADJUSTMENT_REASON", type = String.class),
                                        @ColumnResult(name = "ADJUSTED_INVOICE", type = Integer.class),
                                }
                        )
                }
        )
})

@Entity
public class FreightAuditSavingDto implements Serializable{
    @Id
    private Long id;

    @Column(name = "CARRIER_NAME")
    private String carrierName;

    @Column(name = "INVOICED_AMOUNT")
    private Double invoicedAmount;

    @Column(name = "APPROVED_AMOUNT")
    private Double approvedAmount;

    @Column(name = "FREIGHT_SAVING")
    private Double freightSaving;

    @Column(name = "SAVINGS_PERCENTAGE")
    private Double savingPercentage;

    @Column(name = "ADJUSTMENT_REASON")
    private String adjustmentReason;

    @Column(name = "ADJUSTED_INVOICE")
    private Integer adjustedInvoiceCount;

    public FreightAuditSavingDto() {
    }

    public FreightAuditSavingDto(String carrierName, Double invoicedAmount, Double approvedAmount, Double freightSaving, Double savingPercentage) {
        this.carrierName = carrierName;
        this.invoicedAmount = invoicedAmount;
        this.approvedAmount = approvedAmount;
        this.freightSaving = freightSaving;
        this.savingPercentage = savingPercentage;
    }

    public FreightAuditSavingDto(String carrierName, Double freightSaving, String adjustmentReason, Integer adjustedInvoiceCount) {
        this.carrierName = carrierName;
        this.freightSaving = freightSaving;
        this.adjustmentReason = adjustmentReason;
        this.adjustedInvoiceCount = adjustedInvoiceCount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCarrierName() {
        return carrierName;
    }

    public void setCarrierName(String carrierName) {
        this.carrierName = carrierName;
    }

    public Double getInvoicedAmount() {
        return invoicedAmount;
    }

    public void setInvoicedAmount(Double invoicedAmount) {
        this.invoicedAmount = invoicedAmount;
    }

    public Double getApprovedAmount() {
        return approvedAmount;
    }

    public void setApprovedAmount(Double approvedAmount) {
        this.approvedAmount = approvedAmount;
    }

    public Double getFreightSaving() {
        return freightSaving;
    }

    public void setFreightSaving(Double freightSaving) {
        this.freightSaving = freightSaving;
    }

    public Double getSavingPercentage() {
        return savingPercentage;
    }

    public void setSavingPercentage(Double savingPercentage) {
        this.savingPercentage = savingPercentage;
    }

    public String getAdjustmentReason() {
        return adjustmentReason;
    }

    public void setAdjustmentReason(String adjustmentReason) {
        this.adjustmentReason = adjustmentReason;
    }

    public Integer getAdjustedInvoiceCount() {
        return adjustedInvoiceCount;
    }

    public void setAdjustedInvoiceCount(Integer adjustedInvoiceCount) {
        this.adjustedInvoiceCount = adjustedInvoiceCount;
    }

    public static class Config{
        public static class FreightAuditSaving {
            public static final String STORED_PROCEDURE_QUERY_NAME = "FreightAuditSavingDto.getFreightAuditSavings";
            public static final String STORED_PROCEDURE_NAME = "SHP_DB_FRT_AUDIT_SAVING_PROC";
            public static final String STORED_PROCEDURE_QUERY_MAPPING = "FreightAuditSavingDto.getFreightAuditSavingsMapping";
        }

        public static class FreightAuditSavingStage2 {
            public static final String STORED_PROCEDURE_QUERY_NAME = "FreightAuditSavingDto.getFreightAuditSavingsStage2";
            public static final String STORED_PROCEDURE_NAME = "SHP_DB_FRT_AUDIT_SAVING_2_PROC";
            public static final String STORED_PROCEDURE_QUERY_MAPPING = "FreightAuditSavingDto.getFreightAuditSavingsStage2Mapping";
        }
    }
}
