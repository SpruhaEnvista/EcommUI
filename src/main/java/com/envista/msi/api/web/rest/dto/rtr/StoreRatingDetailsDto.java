package com.envista.msi.api.web.rest.dto.rtr;

import javax.persistence.*;
import java.io.Serializable;



@NamedStoredProcedureQueries({
        @NamedStoredProcedureQuery(name = "StoreRatingDetailsDto.saveRatingDetailsList", procedureName = "shp_store_rating_details_proc",
                resultClasses = StoreRatingDetailsDto.class,
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "customer_id", type = Long.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "carrier_id", type = Long.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "from_invoice_date", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "to_invoice_date", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "from_ship_date", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "to_ship_date", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "rate_set", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "service_levels_id", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "invoice_rate", type = Boolean.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "flagged_shipments", type = Boolean.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "threshold_value", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "threshold_type", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "rate", type = Boolean.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "info_lookup", type = Boolean.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "user_name", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "status", type = String.class)

                })
})


@Entity
public class StoreRatingDetailsDto implements Serializable {

    @Id
    //@Column(name="PARCEL_RATING_INPUT_ID")
    private Long jobId;

    @Column(name = "customer_id")
    private Long customerId;

    @Column(name = "carrier_id")
    private Long carrierIds;

    @Column(name = "from_invoice_date")
    private String fromInvoiceDate;

    @Column(name = "to_invoice_date")
    private String toInvoiceDate;

    @Column(name = "from_ship_date")
    private String fromShipDate;

    @Column(name="to_ship_date")
    private String toShipDate;

    @Column(name="rate_set")
    private String rateSet;

    @Column(name="service_levels_id")
    private String serviceLevelsId;

    @Column(name="invoice_rate")
    private boolean invoiceRate =false;

    @Column(name="flagged_shipments")
    private boolean flaggedShipments =false;

    @Column(name="threshold_value")
    private String thresholdValue;

    @Column(name="threshold_type")
    private String thresholdType;

    @Column(name="rate")
    private boolean rate =false;

    @Column(name="info_lookup")
    private boolean infoLookUp =false;

    @Column(name="status")
    private String status;

    @Column(name="user_name")
    private String createUser;

    @Column(name="task_id")
    private Long taskId;

    @Column(name="completed_count")
    private Long completedCount;

    @Column(name="carrier")
    private String carrierName;

    @Column(name="total_count")
    private Long totatCount;

    @Column(name="create_date")
    private String createDate;


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

    public Long getTaskId() {  return taskId;  }

    public void setTaskId(Long taskId) {  this.taskId = taskId;  }

    public Long getCompletedCount() {   return completedCount;   }

    public void setCompletedCount(Long completedCount) { this.completedCount = completedCount;  }

    public String getCarrierName() {  return carrierName;  }

    public void setCarrierName(String carrierName) { this.carrierName = carrierName;   }

    public Long getTotatCount() {  return totatCount;  }

    public void setTotatCount(Long totatCount) {  this.totatCount = totatCount;   }

    public String getCreateDate() {  return createDate;  }

    public void setCreateDate(String createDate) {    this.createDate = createDate;   }
}
