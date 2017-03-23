package com.envista.msi.api.web.rest.dto.dashboard.networkanalysis;

import com.envista.msi.api.domain.util.DashboardStoredProcParam;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Sujit kumar on 22/03/2017.
 */

@NamedStoredProcedureQueries({
        @NamedStoredProcedureQuery(
                name = ShipmentDto.Config.StoredProcedureQueryName.SHIPMENT_BY_ZONE,
                procedureName = ShipmentDto.Config.StoredProcedureName.SHIPMENT_BY_ZONE,
                resultSetMappings = {ShipmentDto.Config.ResultMappings.SHIPMENT_BY_ZONE_MAPPING},
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.ShipmentParam.DATE_TYPE_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.ShipmentParam.CUSTOMER_IDS_CSV_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.ShipmentParam.CARRIER_IDS_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.ShipmentParam.MODES_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.ShipmentParam.SERVICES_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.ShipmentParam.LANES_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.ShipmentParam.ACCESSORIAL_NAME_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.ShipmentParam.FROM_DATE_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.ShipmentParam.TO_DATE_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.ShipmentParam.TOP_TEN_ACCESSORIAL_PARAM, type = Long.class),
                        @StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, name = DashboardStoredProcParam.ShipmentParam.SHIPMENT_DATA_PARAM, type = Void.class)
                }
        )
})

@SqlResultSetMappings({
        @SqlResultSetMapping(
                name = ShipmentDto.Config.ResultMappings.SHIPMENT_BY_ZONE_MAPPING,
                classes = {
                        @ConstructorResult(
                                targetClass = ShipmentDto.class,
                                columns = {
                                        @ColumnResult(name = "ZONE", type = String.class),
                                        @ColumnResult(name = "SHIPPER_CITY", type = String.class),
                                        @ColumnResult(name = "SHIPPER_STATE", type = String.class),
                                        @ColumnResult(name = "SHIPPER_COUNTRY", type = String.class),
                                        @ColumnResult(name = "SHIPMENT_COUNT", type = Integer.class)
                                }
                        )
                }
        )
})

@Entity
public class ShipmentDto implements Serializable {
    @Id
    private Long id;

    @Column(name = "ZONE")
    private String zone;

    @Column(name = "SHIPPER_CITY")
    private String shipperCity;

    @Column(name = "SHIPPER_STATE")
    private String shipperState;

    @Column(name = "SHIPPER_COUNTRY")
    private String shipperCountry;

    @Column(name = "SHIPMENT_COUNT")
    private Integer shipmentCount;

    public ShipmentDto(String zone, String shipperCity, String shipperState, String shipperCountry, Integer shipmentCount) {
        this.zone = zone;
        this.shipperCity = shipperCity;
        this.shipperState = shipperState;
        this.shipperCountry = shipperCountry;
        this.shipmentCount = shipmentCount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getZone() {
        return zone;
    }

    public void setZone(String zone) {
        this.zone = zone;
    }

    public String getShipperCity() {
        return shipperCity;
    }

    public void setShipperCity(String shipperCity) {
        this.shipperCity = shipperCity;
    }

    public String getShipperState() {
        return shipperState;
    }

    public void setShipperState(String shipperState) {
        this.shipperState = shipperState;
    }

    public String getShipperCountry() {
        return shipperCountry;
    }

    public void setShipperCountry(String shipperCountry) {
        this.shipperCountry = shipperCountry;
    }

    public Integer getShipmentCount() {
        return shipmentCount;
    }

    public void setShipmentCount(Integer shipmentCount) {
        this.shipmentCount = shipmentCount;
    }

    public static class Config{
        static class ResultMappings{
            static final String SHIPMENT_BY_ZONE_MAPPING = "ShipmentDto.ShipmentByZoneMapping";
        }

        static class StoredProcedureName{
            static final String SHIPMENT_BY_ZONE = "SHP_SHIPMENT_CNT_BY_ZONE_PROC";
        }

        public static class StoredProcedureQueryName{
            public static final String SHIPMENT_BY_ZONE = "ShipmentDto.getShipmentByZone";
        }
    }
}
