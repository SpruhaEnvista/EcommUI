package com.envista.msi.api.web.rest.dto.dashboard.netspend;

import com.envista.msi.api.domain.util.DashboardStoredProcParam;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by Sujit kumar on 10/03/2017.
 */

@NamedStoredProcedureQueries({
        @NamedStoredProcedureQuery(
                name = ActualVsBilledWeightDto.Config.StoredProcedureQueryName.ACTUAL_VS_BILLED_WEIGHT,
                procedureName = ActualVsBilledWeightDto.Config.StoredProcedureName.ACTUAL_VS_BILLED_WEIGHT,
                resultSetMappings = {ActualVsBilledWeightDto.Config.ResultMappings.ACTUAL_VS_BILLED_WEIGHT_MAPPING},
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.ActualVsBilledWeightParam.DATE_TYPE_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.ActualVsBilledWeightParam.CUSTOMER_IDS_CSV_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.ActualVsBilledWeightParam.CARRIER_IDS_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.ActualVsBilledWeightParam.MODES_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.ActualVsBilledWeightParam.SERVICES_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.ActualVsBilledWeightParam.LANES_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.ActualVsBilledWeightParam.ACCESSORIAL_NAME_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.ActualVsBilledWeightParam.FROM_DATE_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.ActualVsBilledWeightParam.TO_DATE_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.ActualVsBilledWeightParam.TOP_TEN_ACCESSORIAL_PARAM, type = Long.class),
                        @StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, name = DashboardStoredProcParam.ActualVsBilledWeightParam.ACTUAL_VS_BILLED_WEIGHT_DATA_PARAM, type = Void.class)
                }
        ),
        @NamedStoredProcedureQuery(
                name = ActualVsBilledWeightDto.Config.StoredProcedureQueryName.ACTUAL_VS_BILLED_WEIGHT_BY_CARRIER,
                procedureName = ActualVsBilledWeightDto.Config.StoredProcedureName.ACTUAL_VS_BILLED_WEIGHT_BY_CARRIER,
                resultSetMappings = {ActualVsBilledWeightDto.Config.ResultMappings.ACTUAL_VS_BILLED_WEIGHT_BY_CARRIER_MAPPING},
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.ActualVsBilledWeightParam.DATE_TYPE_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.ActualVsBilledWeightParam.CUSTOMER_IDS_CSV_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.ActualVsBilledWeightParam.CARRIER_IDS_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.ActualVsBilledWeightParam.MODES_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.ActualVsBilledWeightParam.SERVICES_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.ActualVsBilledWeightParam.LANES_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.ActualVsBilledWeightParam.ACCESSORIAL_NAME_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.ActualVsBilledWeightParam.FROM_DATE_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.ActualVsBilledWeightParam.TO_DATE_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.ActualVsBilledWeightParam.TOP_TEN_ACCESSORIAL_PARAM, type = Long.class),
                        @StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, name = DashboardStoredProcParam.ActualVsBilledWeightParam.ACTUAL_VS_BILLED_WEIGHT_DATA_PARAM, type = Void.class)
                }
        ),
        @NamedStoredProcedureQuery(
                name = ActualVsBilledWeightDto.Config.StoredProcedureQueryName.ACTUAL_VS_BILLED_WEIGHT_BY_MONTH,
                procedureName = ActualVsBilledWeightDto.Config.StoredProcedureName.ACTUAL_VS_BILLED_WEIGHT_BY_MONTH,
                resultSetMappings = {ActualVsBilledWeightDto.Config.ResultMappings.ACTUAL_VS_BILLED_WEIGHT_BY_MONTH_MAPPING},
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.ActualVsBilledWeightParam.DATE_TYPE_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.ActualVsBilledWeightParam.CUSTOMER_IDS_CSV_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.ActualVsBilledWeightParam.CARRIER_IDS_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.ActualVsBilledWeightParam.MODES_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.ActualVsBilledWeightParam.SERVICES_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.ActualVsBilledWeightParam.LANES_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.ActualVsBilledWeightParam.ACCESSORIAL_NAME_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.ActualVsBilledWeightParam.FROM_DATE_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.ActualVsBilledWeightParam.TO_DATE_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.ActualVsBilledWeightParam.TOP_TEN_ACCESSORIAL_PARAM, type = Long.class),
                        @StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, name = DashboardStoredProcParam.ActualVsBilledWeightParam.ACTUAL_VS_BILLED_WEIGHT_DATA_PARAM, type = Void.class)
                }
        )
})

@SqlResultSetMappings({
        @SqlResultSetMapping(
                name = ActualVsBilledWeightDto.Config.ResultMappings.ACTUAL_VS_BILLED_WEIGHT_MAPPING,
                classes = {
                        @ConstructorResult(
                                targetClass = ActualVsBilledWeightDto.class,
                                columns = {
                                        @ColumnResult(name = "BILLING_DATE", type = String.class),
                                        @ColumnResult(name = "ACTUAL_WEIGHT", type = Double.class),
                                        @ColumnResult(name = "BILL_WEIGHT", type = Double.class)
                                }
                        )
                }
        ),
        @SqlResultSetMapping(
                name = ActualVsBilledWeightDto.Config.ResultMappings.ACTUAL_VS_BILLED_WEIGHT_BY_CARRIER_MAPPING,
                classes = {
                        @ConstructorResult(
                                targetClass = ActualVsBilledWeightDto.class,
                                columns = {
                                        @ColumnResult(name = "CARRIER_ID", type = Long.class),
                                        @ColumnResult(name = "CARRIER_NAME", type = String.class),
                                        @ColumnResult(name = "ACTUAL_WEIGHT", type = Double.class),
                                        @ColumnResult(name = "BILL_WEIGHT", type = Double.class)
                                }
                        )
                }
        ),
        @SqlResultSetMapping(
                name = ActualVsBilledWeightDto.Config.ResultMappings.ACTUAL_VS_BILLED_WEIGHT_BY_MONTH_MAPPING,
                classes = {
                        @ConstructorResult(
                                targetClass = ActualVsBilledWeightDto.class,
                                columns = {
                                        @ColumnResult(name = "BILL_DATE", type = Date.class),
                                        @ColumnResult(name = "ACTUAL_WEIGHT", type = Double.class),
                                        @ColumnResult(name = "BILL_WEIGHT", type = Double.class)
                                }
                        )
                }
        )
})
@Entity
public class ActualVsBilledWeightDto implements Serializable {
    @Id
    private Long id;

    @Column(name = "CARRIER_ID")
    private Long carrierId;

    @Column(name = "CARRIER_NAME")
    private String carrierName;

    @Column(name = "BILLING_DATE")
    private String billingDate;

    @Column(name = "BILL_DATE")
    private Date billDate;

    @Column(name = "ACTUAL_WEIGHT")
    private Double actualWeight;

    @Column(name = "BILL_WEIGHT")
    private Double billedWeight;

    public ActualVsBilledWeightDto(String billingDate, Double actualWeight, Double billedWeight) {
        this.billingDate = billingDate;
        this.actualWeight = actualWeight;
        this.billedWeight = billedWeight;
    }

    public ActualVsBilledWeightDto(Long carrierId, String carrierName, Double actualWeight, Double billedWeight) {
        this.carrierId = carrierId;
        this.carrierName = carrierName;
        this.actualWeight = actualWeight;
        this.billedWeight = billedWeight;
    }

    public ActualVsBilledWeightDto(Date billDate, Double actualWeight, Double billedWeight) {
        this.billDate = billDate;
        this.actualWeight = actualWeight;
        this.billedWeight = billedWeight;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCarrierId() {
        return carrierId;
    }

    public void setCarrierId(Long carrierId) {
        this.carrierId = carrierId;
    }

    public String getCarrierName() {
        return carrierName;
    }

    public void setCarrierName(String carrierName) {
        this.carrierName = carrierName;
    }

    public String getBillingDate() {
        return billingDate;
    }

    public void setBillingDate(String billingDate) {
        this.billingDate = billingDate;
    }

    public Date getBillDate() {
        return billDate;
    }

    public void setBillDate(Date billDate) {
        this.billDate = billDate;
    }

    public Double getActualWeight() {
        return actualWeight;
    }

    public void setActualWeight(Double actualWeight) {
        this.actualWeight = actualWeight;
    }

    public Double getBilledWeight() {
        return billedWeight;
    }

    public void setBilledWeight(Double billedWeight) {
        this.billedWeight = billedWeight;
    }

    public static class Config{
        static class ResultMappings{
            static final String ACTUAL_VS_BILLED_WEIGHT_MAPPING = "ActualVsBilledWeightDto.ActualVsBilledWeightMapping";
            static final String ACTUAL_VS_BILLED_WEIGHT_BY_CARRIER_MAPPING = "ActualVsBilledWeightDto.ActualVsBilledWeightByCarrierMapping";
            static final String ACTUAL_VS_BILLED_WEIGHT_BY_MONTH_MAPPING = "ActualVsBilledWeightDto.ActualVsBilledWeightByMonthMapping";
        }

        static class StoredProcedureName{
            static final String ACTUAL_VS_BILLED_WEIGHT = "SHP_DB_ACTL_BILD_WGT_PROC";
            static final String ACTUAL_VS_BILLED_WEIGHT_BY_CARRIER = "SHP_DB_ACTL_BILD_WGT_CARR_PROC";
            static final String ACTUAL_VS_BILLED_WEIGHT_BY_MONTH = "SHP_DB_ACTL_BILD_WGT_MNTH_PROC";
        }

        public static class StoredProcedureQueryName{
            public static final String ACTUAL_VS_BILLED_WEIGHT = "ActualVsBilledWeightDto.getActualVsBilledWeight";
            public static final String ACTUAL_VS_BILLED_WEIGHT_BY_CARRIER = "ActualVsBilledWeightDto.getActualVsBilledWeightByCarrier";
            public static final String ACTUAL_VS_BILLED_WEIGHT_BY_MONTH = "ActualVsBilledWeightDto.getActualVsBilledWeightByMonth";
        }
    }
}
