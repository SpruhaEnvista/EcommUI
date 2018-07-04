package com.envista.msi.api.web.rest.dto.rtr;

import javax.persistence.*;
import java.io.Serializable;



@NamedStoredProcedureQueries({
        @NamedStoredProcedureQuery(name = "StoreRatingDetailsDto.saveRatingDetailsList", procedureName = "shp_store_rating_details_proc",
                resultClasses = StoreRatingDetailsDto.class,
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_customer_id", type = Long.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_carrier_id", type = Long.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_from_invoice_date", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_to_invoice_date", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_from_ship_date", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_to_ship_date", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_rate_set", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_service_levels_id", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_invoice_rate", type = Boolean.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_flagged_shipments", type = Boolean.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_threshold_value", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_threshold_type", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_rate", type = Boolean.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_info_lookup", type = Boolean.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_user_name", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_status", type = String.class)

                })
})


@Entity
public class StoreRatingDetailsDto implements Serializable {

    @Id
    //@Column(name="PARCEL_RATING_INPUT_ID")
    private Long jobId;

    @Column(name = "p_customer_id")
    private Long customerId;

    @Column(name = "p_carrier_id")
    private Long carrierIds;

    @Column(name = "p_from_invoice_date")
    private String fromInvoiceDate;

    @Column(name = "p_to_invoice_date")
    private String toInvoiceDate;

    @Column(name = "p_from_ship_date")
    private String fromShipDate;

    @Column(name="p_to_ship_date")
    private String toShipDate;

    @Column(name="p_rate_set")
    private String rateSet;

    @Column(name="p_service_levels_id")
    private String serviceLevelsId;


    @Column(name="p_invoice_rate")
    private boolean invoiceRate =false;

    @Column(name="p_flagged_shipments")
    private boolean flaggedShipments =false;

    @Column(name="p_threshold_value")
    private String thresholdValue;

    @Column(name="p_threshold_type")
    private String thresholdType;

    @Column(name="p_rate")
    private boolean rate =false;

    @Column(name="p_info_lookup")
    private boolean infoLookUp =false;

    @Column(name="p_status")
    private String status;

    @Column(name="p_user_name")
    private String createUser;


    public StoreRatingDetailsDto() {

    }

    public Long getCustomerId() {
        return customerId;
    }

    public Long getCarrierIds() {
        return carrierIds;
    }

    public String getFromInvoiceDate() {
        return fromInvoiceDate;
    }

    public String getToInvoiceDate() {
        return toInvoiceDate;
    }

    public String getFromShipDate() {
        return fromShipDate;
    }

    public String getToShipDate() {
        return toShipDate;
    }

    public String getRateSet() {
        return rateSet;
    }


    public boolean isInvoiceRate() {
        return invoiceRate;
    }

    public boolean isFlaggedShipments() {
        return flaggedShipments;
    }

    public String getThresholdValue() {
        return thresholdValue;
    }

    public String getThresholdType() {
        return thresholdType;
    }

    public boolean isRate() {
        return rate;
    }

    public boolean isInfoLookUp() {
        return infoLookUp;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public void setCarrierIds(Long carrierIds) {
        this.carrierIds = carrierIds;
    }

    public void setFromInvoiceDate(String fromInvoiceDate) {
        this.fromInvoiceDate = fromInvoiceDate;
    }

    public void setToInvoiceDate(String toInvoiceDate) {
        this.toInvoiceDate = toInvoiceDate;
    }

    public void setFromShipDate(String fromShipDate) {
        this.fromShipDate = fromShipDate;
    }

    public void setToShipDate(String toShipDate) {
        this.toShipDate = toShipDate;
    }

    public void setRateSet(String rateSet) {
        this.rateSet = rateSet;
    }



    public void setInvoiceRate(boolean invoiceRate) {
        this.invoiceRate = invoiceRate;
    }

    public void setFlaggedShipments(boolean flaggedShipments) {
        this.flaggedShipments = flaggedShipments;
    }

    public void setThresholdValue(String thresholdValue) {
        this.thresholdValue = thresholdValue;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public void setThresholdType(String thresholdType) {
        this.thresholdType = thresholdType;
    }

    public void setRate(boolean rate) {
        this.rate = rate;
    }

    public void setInfoLookUp(boolean infoLookUp) {
        this.infoLookUp = infoLookUp;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getJobId() {
        return jobId;
    }

    public void setJobId(Long jobId) {
        this.jobId = jobId;
    }

    public String getServiceLevelsId() {
        return serviceLevelsId;
    }

    public void setServiceLevelsId(String serviceLevelsId) {
        this.serviceLevelsId = serviceLevelsId;
    }

}
