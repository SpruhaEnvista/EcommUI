package com.envista.msi.api.web.rest.dto.dashboard.carrierspend;

import com.envista.msi.api.domain.util.DashboardStoredProcParam;

import javax.persistence.NamedStoredProcedureQueries;
import javax.persistence.NamedStoredProcedureQuery;
import javax.persistence.StoredProcedureParameter;

import javax.persistence.SqlResultSetMappings;
import javax.persistence.SqlResultSetMapping;

import javax.persistence.ConstructorResult;
import javax.persistence.ColumnResult;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Column;

import javax.persistence.ParameterMode;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Sujit kumar on 09/02/2017.
 */

@NamedStoredProcedureQueries({
        @NamedStoredProcedureQuery(name = CarrierSpendAnalysisDto.Config.StoredProcedureQueryName.CARR_SPND_Analysis,
                procedureName = CarrierSpendAnalysisDto.Config.StoredProcedureName.CARR_SPND_Analysis,
                resultSetMappings = {CarrierSpendAnalysisDto.Config.ResultMappings.CARR_SPND_Analysis_MAPPING},
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.NetSpendParams.DATE_TYPE_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.NetSpendParams.CONVERTED_CURRENCY_ID_PARAM, type = Long.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.NetSpendParams.CONVERTED_CURRENCY_CODE_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.NetSpendParams.CUSTOMER_IDS_CSV_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.NetSpendParams.CARRIER_IDS_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.NetSpendParams.MODES_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.NetSpendParams.SERVICES_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.NetSpendParams.LANES_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.NetSpendParams.FROM_DATE_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.NetSpendParams.TO_DATE_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.NetSpendParams.CONVERTED_WEIGHT_UNIT_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, name = DashboardStoredProcParam.RelSpendParams.REL_SPEND_PARAM, type = Void.class)
                })

})

@SqlResultSetMappings({
        @SqlResultSetMapping(name = CarrierSpendAnalysisDto.Config.ResultMappings.CARR_SPND_Analysis_MAPPING, classes = {
                @ConstructorResult(
                        targetClass = CarrierSpendAnalysisDto.class,
                        columns = {
                                @ColumnResult(name = "carrier_id", type = Long.class),
                                @ColumnResult(name = "carrier_name", type = String.class),
                                @ColumnResult(name = "spend", type = Double.class),
                                @ColumnResult(name = "perc_spend", type = Double.class),
                                @ColumnResult(name = "no_of_shipments", type = Double.class),
                                @ColumnResult(name = "Perc_Shipments", type = Double.class),
                                @ColumnResult(name = "total_weight", type = Double.class),
                                @ColumnResult(name = "cost_per_shpmnt", type = Double.class),
                                @ColumnResult(name = "weight_per_shmnt", type = Double.class),
                                @ColumnResult(name = "cost_weight", type = Double.class)
                        })
        })
})

@Entity
public class CarrierSpendAnalysisDto implements Serializable {

    @Id
    @Column(name = "carrier_id")
    private Long id;

    @Column(name = "carrier_name")
    private String carrierName;


    @Column(name = "spend")
    private Double spend;

    @Column(name = "perc_spend")
    private Double percSpend;

    @Column(name = "no_of_shipments")
    private Double noOfShipments;


    @Column(name = "Perc_Shipments")
    private Double percShipments;



    @Column(name = "total_weight")
    private Double totalWeight;

    @Column(name = "cost_per_shpmnt")
    private Double costPerShpmnt;


    @Column(name = "weight_per_shmnt")
    private Double weightPerShmnt;


    @Column(name = "cost_weight")
    private Double costWeight;



    public CarrierSpendAnalysisDto(){}

    public CarrierSpendAnalysisDto(Long id, String carrierName, Double spend,Double percSpend,
    Double noOfShipments,Double percShipments,Double totalWeight,Double costPerShpmnt,Double weightPerShmnt,Double costWeight) {
        this.id = id;
        this.carrierName = carrierName;
        this.spend = spend;
        this.percSpend = percSpend;
        this.noOfShipments = noOfShipments;
        this.percShipments = percShipments;
        this.totalWeight = totalWeight ;
        this.costPerShpmnt = costPerShpmnt;
        this.weightPerShmnt = weightPerShmnt;
        this.costWeight = costWeight;

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

    public Double getSpend() {
        return spend;
    }

    public void setSpend(Double spend) {
        this.spend = spend;
    }

    public Double getPercSpend() {
        return percSpend;
    }

    public void setPercSpend(Double percSpend) {
        this.percSpend = percSpend;
    }

    public Double getNoOfShipments() {
        return noOfShipments;
    }

    public void setNoOfShipments(Double noOfShipments) {
        this.noOfShipments = noOfShipments;
    }

    public Double getPercShipments() {
        return percShipments;
    }

    public void setPercShipments(Double percShipments) {
        this.percShipments = percShipments;
    }

    public Double getCostPerShpmnt() {
        return costPerShpmnt;
    }

    public void setCostPerShpmnt(Double costPerShpmnt) {
        this.costPerShpmnt = costPerShpmnt;
    }

    public Double getWeightPerShmnt() {
        return weightPerShmnt;
    }

    public void setWeightPerShmnt(Double weightPerShmnt) {
        this.weightPerShmnt = weightPerShmnt;
    }

    public Double getCostWeight() {
        return costWeight;
    }

    public void setCostWeight(Double costWeight) {
        this.costWeight = costWeight;
    }

    public Double getTotalWeight() {
        return totalWeight;
    }

    public void setTotalWeight(Double totalWeight) {
        this.totalWeight = totalWeight;
    }

    public static class Config{
        static class ResultMappings{
            static final String CARR_SPND_Analysis_MAPPING = "CarrierSpendAnalysisDto.getCarrierSpendAnalysis";
        }

        static class StoredProcedureName{
            static final String CARR_SPND_Analysis = "SHP_DB_CARR_SPND_Analysis_PROC";

        }

        public static class StoredProcedureQueryName{
            public static final String CARR_SPND_Analysis = "CarrierSpendAnalysisDto.getCarrierSpendAnalysis";

        }
    }
}
