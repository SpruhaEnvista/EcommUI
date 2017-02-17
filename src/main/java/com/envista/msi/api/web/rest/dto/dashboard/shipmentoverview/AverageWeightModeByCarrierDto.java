package com.envista.msi.api.web.rest.dto.dashboard.shipmentoverview;

import com.envista.msi.api.domain.util.DashboardSroredProcParam;

import javax.persistence.*;

/**
 * Created by Siddhant on 17/02/2017.
 */
@NamedStoredProcedureQueries({
        @NamedStoredProcedureQuery(name = "AverageWeightModeByCarrierDto.getAverageWeightModeByCarrier", procedureName = "SHP_DB_AVG_WGT_BY_CARR_PROC",
                resultSetMappings = "AverageWeightModeByCarrierDto.AvgWeightModeByCarrierMapping",
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.AverageWeightShipmentByCarrierParam.DATE_TYPE_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.AverageWeightShipmentByCarrierParam.MODES_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.AverageWeightShipmentByCarrierParam.SERVICES_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.AverageWeightShipmentByCarrierParam.ACCESSORIAL_NAME_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.AverageWeightShipmentByCarrierParam.LANES_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.AverageWeightShipmentByCarrierParam.FROM_DATE_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.AverageWeightShipmentByCarrierParam.TO_DATE_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.AverageWeightShipmentByCarrierParam.ORIGINAL_FROM_DATE_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.AverageWeightShipmentByCarrierParam.ORIGINAL_TO_DATE_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.AverageWeightShipmentByCarrierParam.CONVERTE_WEIGHT_UNIT_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.AverageWeightShipmentByCarrierParam.CARRIER_ID_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.AverageWeightShipmentByCarrierParam.CUSTOMER_IDS_CSV_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.AverageWeightShipmentByCarrierParam.TOP_ACCESSORIAL_SPEND_PARAM, type = Long.class),
                        @StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, name = DashboardSroredProcParam.AverageWeightShipmentByCarrierParam.AVG_WGT_SHIPMT_BY_CARR_PARAM, type = Void.class)
                })
})
@SqlResultSetMappings({
        @SqlResultSetMapping(name = "AverageWeightModeByCarrierDto.AvgWeightModeByCarrierMapping", classes = {
                @ConstructorResult(
                        targetClass = AverageWeightModeByCarrierDto.class,
                        columns = {
                                @ColumnResult(name = "ID", type = Long.class),
                                @ColumnResult(name = "NAME", type = String.class),
                                @ColumnResult(name = "VALUE", type = Double.class)
                        }
                )
        })
})

@Entity
public class AverageWeightModeByCarrierDto {
    @Id
    @Column(name = "ID")
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "VALUE")
    private Double value;

    public AverageWeightModeByCarrierDto() {    }

    public AverageWeightModeByCarrierDto(String name, Double value) {
        this.name = name;
        this.value = value;
    }

    public AverageWeightModeByCarrierDto(Long id, String name, Double value) {
        this.id = id;
        this.name = name;
        this.value = value;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }
}
