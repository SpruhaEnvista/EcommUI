package com.envista.msi.api.web.rest.dto.dashboard.networkanalysis;

import com.envista.msi.api.domain.util.DashboardStoredProcParam;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by Sarvesh on 2/8/2017.
 */

@NamedStoredProcedureQueries({
        @NamedStoredProcedureQuery(name = ShipmentRegionDto.Config.StoredProcedureQueryName.SHIPMENT_BY_REGION,
                procedureName = ShipmentRegionDto.Config.StoredProcedureName.SHIPMENT_BY_REGION,
                resultSetMappings =  ShipmentRegionDto.Config.ResultMappings.SHIPMENT_BY_REGION_MAPPING,
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.ShipmentRegionParams.DATE_TYPE_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.ShipmentRegionParams.CUSTOMER_IDS_CSV_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.ShipmentRegionParams.CARRIER_ID_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.ShipmentRegionParams.MODES_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.ShipmentRegionParams.SERVICES_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.ShipmentRegionParams.LANES_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.ShipmentRegionParams.ACCESSORIAL_NAME_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.ShipmentRegionParams.FROM_DATE_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.ShipmentRegionParams.TO_DATE_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.ShipmentRegionParams.NO_OF_LANES_PARAM, type = Long.class),
                        @StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, name = DashboardStoredProcParam.ShipmentRegionParams.RESULTS_DATA_PARAM, type = Void.class)
                }),
        @NamedStoredProcedureQuery(name = ShipmentRegionDto.Config.StoredProcedureQueryName.SHIPMENT_BY_REGION_BY_CARRIER,
                procedureName = ShipmentRegionDto.Config.StoredProcedureName.SHIPMENT_BY_REGION_BY_CARRIER,
                resultSetMappings =  ShipmentRegionDto.Config.ResultMappings.SHIPMENT_BY_REGION_BY_CARRIER_MAPPING,
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.ShipmentRegionParams.DATE_TYPE_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.ShipmentRegionParams.CONVERTED_CURRENCY_ID_PARAM, type = Long.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.ShipmentRegionParams.CONVERTED_CURRENCY_CODE_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.ShipmentRegionParams.CUSTOMER_IDS_CSV_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.ShipmentRegionParams.CARRIER_ID_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.ShipmentRegionParams.MODES_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.ShipmentRegionParams.SERVICES_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.ShipmentRegionParams.LANES_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.ShipmentRegionParams.ACCESSORIAL_NAME_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.ShipmentRegionParams.FROM_DATE_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.ShipmentRegionParams.TO_DATE_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.ShipmentRegionParams.SHIPPER_CITY, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.ShipmentRegionParams.RECEIVER_CITY, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, name = DashboardStoredProcParam.ShipmentRegionParams.RESULTS_DATA_PARAM, type = Void.class)
                }),
        @NamedStoredProcedureQuery(name = ShipmentRegionDto.Config.StoredProcedureQueryName.SHIPMENT_BY_REGION_BY_MONTH,
                procedureName = ShipmentRegionDto.Config.StoredProcedureName.SHIPMENT_BY_REGION_BY_MONTH,
                resultSetMappings =  ShipmentRegionDto.Config.ResultMappings.SHIPMENT_BY_REGION_BY_MONTH_MAPPING,
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.ShipmentRegionParams.DATE_TYPE_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.ShipmentRegionParams.CONVERTED_CURRENCY_ID_PARAM, type = Long.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.ShipmentRegionParams.CONVERTED_CURRENCY_CODE_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.ShipmentRegionParams.CUSTOMER_IDS_CSV_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.ShipmentRegionParams.CARRIER_ID_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.ShipmentRegionParams.MODES_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.ShipmentRegionParams.SERVICES_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.ShipmentRegionParams.LANES_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.ShipmentRegionParams.ACCESSORIAL_NAME_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.ShipmentRegionParams.FROM_DATE_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.ShipmentRegionParams.TO_DATE_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.ShipmentRegionParams.SHIPPER_CITY, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.ShipmentRegionParams.RECEIVER_CITY, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, name = DashboardStoredProcParam.ShipmentRegionParams.RESULTS_DATA_PARAM, type = Void.class)
                })
})


@SqlResultSetMappings({
        @SqlResultSetMapping(name = ShipmentRegionDto.Config.ResultMappings.SHIPMENT_BY_REGION_MAPPING, classes = {
                @ConstructorResult(
                        targetClass = ShipmentRegionDto.class,
                        columns = {
                                @ColumnResult(name = "RANK", type = Long.class),
                                @ColumnResult(name = "SHIPPER_CITY", type = String.class),
                                @ColumnResult(name = "SHIPPERADDRESS", type = String.class),
                                @ColumnResult(name = "RECEIVER_CITY", type = String.class),
                                @ColumnResult(name = "RECEIVERADDRESS", type = String.class),
                                @ColumnResult(name = "LANE_COUNT", type = Integer.class),
                        })
        }),
        @SqlResultSetMapping(name = ShipmentRegionDto.Config.ResultMappings.SHIPMENT_BY_REGION_BY_CARRIER_MAPPING, classes = {
                @ConstructorResult(
                        targetClass = ShipmentRegionDto.class,
                        columns = {
                                @ColumnResult(name = "CARRIER_ID", type = Long.class),
                                @ColumnResult(name = "CARRIER_NAME", type = String.class),
                                @ColumnResult(name = "SPEND", type = Double.class),
                        })
        }),
        @SqlResultSetMapping(name = ShipmentRegionDto.Config.ResultMappings.SHIPMENT_BY_REGION_BY_MONTH_MAPPING, classes = {
                @ConstructorResult(
                        targetClass = ShipmentRegionDto.class,
                        columns = {
                                @ColumnResult(name = "BILL_DATE", type = Date.class),
                                @ColumnResult(name = "AMOUNT", type = Double.class),
                        })
        })
})


@Entity
public class ShipmentRegionDto implements Serializable {

    @Id
    @Column(name = "LANE_ID")
    private Long id;

    @Column(name = "RANK")
    private Long rank;

    @Column(name = "SHIPPER_CITY")
    private String shipperCity;

    @Column(name = "SHIPPERADDRESS")
    private String shipperAddress;

    @Column(name = "RECEIVER_CITY")
    private String receiverCity;

    @Column(name = "RECEIVERADDRESS")
    private String receiverAddress;

    @Column (name = "LANE_COUNT")
    private int laneCount;

    @Column(name = "CARRIER_NAME")
    private String carrierName;

    @Column(name = "CARRIER_ID")
    private Long carrierId;

    @Column(name = "SPEND")
    private Double spend;

    @Column(name = "AMOUNT")
    private Double amount;

    @Column(name = "BILL_DATE")
    private Date billDate;


    public ShipmentRegionDto (Long rank, String shipperCity, String shipperAddress, String receiverCity
            , String receiverAddress, int laneCount) {
        this.setRank(rank);
        this.setShipperCity(shipperCity);
        this.setShipperAddress(shipperAddress);
        this.setReceiverCity(receiverCity);
        this.setReceiverAddress(receiverAddress);
        this.setLaneCount(laneCount);
    }


    public ShipmentRegionDto (Long carrierId, String carrierName,Double spend) {
        this.setCarrierId(carrierId);
        this.setCarrierName(carrierName);
        this.setSpend(spend);
    }

    public ShipmentRegionDto (Date billDate,Double amount) {
        this.setBillDate(billDate);
        this.setAmount(amount);
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getShipperCity() {
        return shipperCity;
    }

    public void setShipperCity(String shipperCity) {
        this.shipperCity = shipperCity;
    }

    public String getShipperAddress() {
        return shipperAddress;
    }

    public void setShipperAddress(String shipperAddress) {
        this.shipperAddress = shipperAddress;
    }

    public String getReceiverCity() {
        return receiverCity;
    }

    public void setReceiverCity(String receiverCity) {
        this.receiverCity = receiverCity;
    }

    public String getReceiverAddress() {
        return receiverAddress;
    }

    public void setReceiverAddress(String receiverAddress) {
        this.receiverAddress = receiverAddress;
    }

    public int getLaneCount() {
        return laneCount;
    }

    public void setLaneCount(int laneCount) {
        this.laneCount = laneCount;
    }

    public String getCarrierName() {
        return carrierName;
    }

    public void setCarrierName(String carrierName) {
        this.carrierName = carrierName;
    }

    public Long getCarrierId() {
        return carrierId;
    }

    public void setCarrierId(Long carrierId) {
        this.carrierId = carrierId;
    }

    public Double getSpend() {
        return spend;
    }

    public void setSpend(Double spend) {
        this.spend = spend;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Date getBillDate() {
        return billDate;
    }

    public void setBillDate(Date billDate) {
        this.billDate = billDate;
    }

    public Long getRank() {
        return rank;
    }

    public void setRank(Long rank) {
        this.rank = rank;
    }

    public static class Config{
        static class ResultMappings{
            static final String SHIPMENT_BY_REGION_MAPPING = "ShipmentRegionDto.ShipmentRegionMapping";
            static final String SHIPMENT_BY_REGION_BY_CARRIER_MAPPING = "ShipmentRegionDto.ShipmentRegionCarrierMapping";
            static final String SHIPMENT_BY_REGION_BY_MONTH_MAPPING = "ShipmentRegionDto.ShipmentRegionMonthMapping";
        }

        static class StoredProcedureName{
            static final String SHIPMENT_BY_REGION = "SHP_DB_SHMNT_MAP_PROC";
            static final String SHIPMENT_BY_REGION_BY_CARRIER = "SHP_DB_SHMNT_MAP_CARR_PROC";
            static final String SHIPMENT_BY_REGION_BY_MONTH = "SHP_DB_SHMNT_MAP_MNTH_PROC";
        }

        public static class StoredProcedureQueryName{
            public static final String SHIPMENT_BY_REGION = "ShipmentRegionDto.getShipmentByRegion";
            public static final String SHIPMENT_BY_REGION_BY_CARRIER = "ShipmentRegionDto.getShipmentRegionByCarrier";
            public static final String SHIPMENT_BY_REGION_BY_MONTH = "ShipmentRegionDto.getShipmentRegionByMonth";
        }
    }
}
