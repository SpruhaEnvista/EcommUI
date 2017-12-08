package com.envista.msi.api.web.rest.dto.dashboard.servicelevel;

import com.envista.msi.api.domain.util.DashboardStoredProcParam;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by Sujit kumar on 09/02/2017.
 */

@NamedStoredProcedureQueries({
        @NamedStoredProcedureQuery(name = ServiceLevelDto.Config.StoredProcedureQueryName.SERVICE_LEVEL_ANALYSIS,
                procedureName = ServiceLevelDto.Config.StoredProcedureName.SERVICE_LEVEL_ANALYSIS,
                resultSetMappings = {ServiceLevelDto.Config.ResultMappings.SERVICE_LEVEL_ANALYSIS_MAPPING},
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
                }),
        @NamedStoredProcedureQuery(name = ServiceLevelDto.Config.StoredProcedureQueryName.TOTAL_SPEND_SERVICE_LEVEL,
                procedureName = ServiceLevelDto.Config.StoredProcedureName.TOTAL_SPEND_SERVICE_LEVEL,
                resultSetMappings = {ServiceLevelDto.Config.ResultMappings.TOTAL_SPEND_SERVICE_LEVEL_MAPPING},
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
                }),
        @NamedStoredProcedureQuery(name = ServiceLevelDto.Config.StoredProcedureQueryName.TOTAL_PCKG_SERVICE_LEVEL,
                procedureName = ServiceLevelDto.Config.StoredProcedureName.TOTAL_PCKG_SERVICE_LEVEL,
                resultSetMappings = {ServiceLevelDto.Config.ResultMappings.TOTAL_PCKG_SERVICE_LEVEL_MAPPING},
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
                }),
        @NamedStoredProcedureQuery(name = ServiceLevelDto.Config.StoredProcedureQueryName.COST_SHIPMENT_SERVICE_LEVEL,
                procedureName = ServiceLevelDto.Config.StoredProcedureName.COST_SHIPMENT_SERVICE_LEVEL,
                resultSetMappings = {ServiceLevelDto.Config.ResultMappings.COST_SHIPMENT_SERVICE_LEVEL_MAPPING},
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
        @SqlResultSetMapping(name = ServiceLevelDto.Config.ResultMappings.SERVICE_LEVEL_ANALYSIS_MAPPING, classes = {
                @ConstructorResult(
                        targetClass = ServiceLevelDto.class,
                        columns = {
                                @ColumnResult(name = "service_level", type = String.class),
                                @ColumnResult(name = "spend", type = Double.class),
                                @ColumnResult(name = "perc_spend", type = Double.class),
                                @ColumnResult(name = "no_of_packages", type = Double.class),
                                @ColumnResult(name = "Perc_packages", type = Double.class),
                                @ColumnResult(name = "total_weight", type = Double.class),
                                @ColumnResult(name = "cost_per_package", type = Double.class),
                                @ColumnResult(name = "weight_per_package", type = Double.class),
                                @ColumnResult(name = "cost_weight", type = Double.class)
                        })
        }),
        @SqlResultSetMapping(name = ServiceLevelDto.Config.ResultMappings.TOTAL_SPEND_SERVICE_LEVEL_MAPPING, classes = {
                @ConstructorResult(
                        targetClass = ServiceLevelDto.class,
                        columns = {
                                @ColumnResult(name = "service_level", type = String.class),
                                @ColumnResult(name = "spend", type = Double.class),
                                @ColumnResult(name = "no_of_packages", type = Double.class)
                        })
        }),
        @SqlResultSetMapping(name = ServiceLevelDto.Config.ResultMappings.TOTAL_PCKG_SERVICE_LEVEL_MAPPING, classes = {
                @ConstructorResult(
                        targetClass = ServiceLevelDto.class,
                        columns = {
                                @ColumnResult(name = "service_level", type = String.class),
                                @ColumnResult(name = "spend", type = Double.class),
                                @ColumnResult(name = "no_of_packages", type = Double.class)
                        })
        }),
        @SqlResultSetMapping(name = ServiceLevelDto.Config.ResultMappings.COST_SHIPMENT_SERVICE_LEVEL_MAPPING, classes = {
                @ConstructorResult(
                        targetClass = ServiceLevelDto.class,
                        columns = {
                                @ColumnResult(name = "BILLING_DATE", type = String.class),
                                @ColumnResult(name = "service_level", type = String.class),
                                @ColumnResult(name = "cost_per_package", type = Double.class),
                                @ColumnResult(name = "cost_weight", type = Double.class)
                        })
        })

})

@Entity
public class ServiceLevelDto implements Serializable {

    @Id
    @Column(name = "service_level")
    private String servicelevel;

    @Column(name = "spend")
    private Double spend;

    @Column(name = "perc_spend")
    private Double percSpend;

    @Column(name = "no_of_packages")
    private Double noOfPackages;

    @Column(name = "Perc_packages")
    private Double percPackages;

    @Column(name = "total_weight")
    private Double totalWeight;

    @Column(name = "cost_per_package")
    private Double costPerPackage;

    @Column(name = "weight_per_package")
    private Double weightPerPackage;

    @Column(name = "cost_weight")
    private Double costWeight;

    @Column(name = "BILLING_DATE")
    private String billingDate;



    public ServiceLevelDto(){}

    public ServiceLevelDto(String billingDate,String servicelevel, Double  costPerPackage,Double  costWeight)
    {
        this.billingDate=billingDate;
        this.servicelevel = servicelevel;
        this.costPerPackage = costPerPackage;
        this.costWeight = costWeight;
    }

    public ServiceLevelDto(String servicelevel, Double  spend,Double  noOfPackages)
    {
        this.servicelevel = servicelevel;
        this.spend = spend;
        this.noOfPackages = noOfPackages;
    }

    public ServiceLevelDto(String servicelevel, Double  spend,Double percSpend,
                                   Double noOfPackages,Double percPackages,Double totalWeight,Double costPerPackage,
                           Double weightPerPackage,Double costWeight,String billingDate) {

        this.servicelevel = servicelevel;
        this.spend = spend;
        this.percSpend = percSpend;
        this.noOfPackages = noOfPackages;
        this.percPackages = percPackages;
        this.totalWeight = totalWeight ;
        this.costPerPackage = costPerPackage;
        this.weightPerPackage = weightPerPackage;
        this.costWeight = costWeight;
        this.billingDate = billingDate;

    }

    public String getServicelevel() {
        return servicelevel;
    }

    public void setServicelevel(String servicelevel) {
        this.servicelevel = servicelevel;
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

    public Double getNoOfPackages() {
        return noOfPackages;
    }

    public void setNoOfPackages(Double noOfPackages) {
        this.noOfPackages = noOfPackages;
    }

    public Double getPercPackages() {
        return percPackages;
    }

    public void setPercPackages(Double percPackages) {
        this.percPackages = percPackages;
    }

    public Double getTotalWeight() {
        return totalWeight;
    }

    public void setTotalWeight(Double totalWeight) {
        this.totalWeight = totalWeight;
    }

    public Double getCostPerPackage() {
        return costPerPackage;
    }

    public void setCostPerPackage(Double costPerPackage) {
        this.costPerPackage = costPerPackage;
    }

    public Double getWeightPerPackage() {
        return weightPerPackage;
    }

    public void setWeightPerPackage(Double weightPerPackage) {
        this.weightPerPackage = weightPerPackage;
    }

    public Double getCostWeight() {
        return costWeight;
    }

    public void setCostWeight(Double costWeight) {
        this.costWeight = costWeight;
    }

    public String getBillingDate() {
        return billingDate;
    }

    public void setBillingDate(String billingDate) {
        this.billingDate = billingDate;
    }

    public static class Config{
        static class ResultMappings{
            static final String SERVICE_LEVEL_ANALYSIS_MAPPING = "ServiceLevelDto.getServiceLevAnalysis";
             static final String TOTAL_SPEND_SERVICE_LEVEL_MAPPING = "ServiceLevelDto.getTotalSpendByService";
            static final String TOTAL_PCKG_SERVICE_LEVEL_MAPPING = "ServiceLevelDto.getTotalPckgsByService";
             static final String COST_SHIPMENT_SERVICE_LEVEL_MAPPING = "ServiceLevelDto.getCostPerShipmentByService";

        }

        static class StoredProcedureName{
            static final String SERVICE_LEVEL_ANALYSIS = "SHP_DB_SERV_LVL_Analysis_PROC";
            static final String TOTAL_SPEND_SERVICE_LEVEL = "SHP_DB_SERV_LVL_PERC_PROC";
            static final String TOTAL_PCKG_SERVICE_LEVEL = "SHP_DB_SERV_LVL_PERC_PROC";
            static final String COST_SHIPMENT_SERVICE_LEVEL = "SHP_DB_SERV_LVL_PERC_PROC";


        }

        public static class StoredProcedureQueryName{
            public static final String SERVICE_LEVEL_ANALYSIS = "ServiceLevelDto.getServiceLevAnalysis";
            public static final String TOTAL_SPEND_SERVICE_LEVEL = "ServiceLevelDto.getTotalSpendByService";
            public static final String TOTAL_PCKG_SERVICE_LEVEL = "ServiceLevelDto.getTotalPckgsByService";
            public static final String COST_SHIPMENT_SERVICE_LEVEL = "ServiceLevelDto.getCostPerShipmentByService";


        }
    }
}
