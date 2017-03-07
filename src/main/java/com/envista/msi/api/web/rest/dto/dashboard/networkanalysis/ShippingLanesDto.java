package com.envista.msi.api.web.rest.dto.dashboard.networkanalysis;

import com.envista.msi.api.domain.util.DashboardStoredProcParam;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Sarvesh on 2/15/2017.
 */

@NamedStoredProcedureQueries({
        @NamedStoredProcedureQuery(name = ShippingLanesDto.Config.StoredProcedureQueryName.SHIPPING_LANES,
                procedureName = ShippingLanesDto.Config.StoredProcedureName.SHIPPING_LANES,
                resultSetMappings = ShippingLanesDto.Config.ResultMappings.SHIPPING_LANES_MAPPING,
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.ShippingLanesParams.DATE_TYPE_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.ShippingLanesParams.CONVERTED_CURRENCY_ID_PARAM, type = Long.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.ShippingLanesParams.CUSTOMER_IDS_CSV_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.ShippingLanesParams.CARRIER_ID_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.ShippingLanesParams.MODES_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.ShippingLanesParams.SERVICES_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.ShippingLanesParams.LANES_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.ShippingLanesParams.FROM_DATE_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.ShippingLanesParams.TO_DATE_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, name = DashboardStoredProcParam.ShippingLanesParams.RESULTS_DATA_PARAM, type = Void.class)
                }),
        @NamedStoredProcedureQuery(name = ShippingLanesDto.Config.StoredProcedureQueryName.SHIPPING_LANES_BY_CARRIER,
                procedureName = ShippingLanesDto.Config.StoredProcedureName.SHIPPING_LANES_BY_CARRIER,
                resultSetMappings = ShippingLanesDto.Config.ResultMappings.SHIPPING_LANES_BY_CARRIER_MAPPING,
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.ShippingLanesParams.DATE_TYPE_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.ShippingLanesParams.CONVERTED_CURRENCY_ID_PARAM, type = Long.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.ShippingLanesParams.CUSTOMER_IDS_CSV_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.ShippingLanesParams.CARRIER_ID_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.ShippingLanesParams.MODES_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.ShippingLanesParams.SERVICES_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.ShippingLanesParams.LANES_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.ShippingLanesParams.FROM_DATE_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.ShippingLanesParams.TO_DATE_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.ShippingLanesParams.SHIPPER_CITY, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.ShippingLanesParams.SHIPPER_STATE, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.ShippingLanesParams.SHIPPER_COUNTRY, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.ShippingLanesParams.RECEIVER_CITY, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.ShippingLanesParams.RECEIVER_STATE, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.ShippingLanesParams.RECEIVER_COUNTRY, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, name = DashboardStoredProcParam.ShippingLanesParams.RESULTS_DATA_PARAM, type = Void.class)
                }),
        @NamedStoredProcedureQuery(name = ShippingLanesDto.Config.StoredProcedureQueryName.SHIPPING_LANES_BY_MONTH,
                procedureName = ShippingLanesDto.Config.StoredProcedureName.SHIPPING_LANES_BY_MONTH,
                resultSetMappings = ShippingLanesDto.Config.ResultMappings.SHIPPING_LANES_BY_MONTH_MAPPING,
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.ShippingLanesParams.DATE_TYPE_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.ShippingLanesParams.CONVERTED_CURRENCY_ID_PARAM, type = Long.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.ShippingLanesParams.CUSTOMER_IDS_CSV_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.ShippingLanesParams.CARRIER_ID_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.ShippingLanesParams.MODES_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.ShippingLanesParams.SERVICES_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.ShippingLanesParams.LANES_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.ShippingLanesParams.FROM_DATE_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.ShippingLanesParams.TO_DATE_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.ShippingLanesParams.SHIPPER_CITY, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.ShippingLanesParams.SHIPPER_STATE, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.ShippingLanesParams.SHIPPER_COUNTRY, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.ShippingLanesParams.RECEIVER_CITY, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.ShippingLanesParams.RECEIVER_STATE, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.ShippingLanesParams.RECEIVER_COUNTRY, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, name = DashboardStoredProcParam.ShippingLanesParams.RESULTS_DATA_PARAM, type = Void.class)
                })
})


@SqlResultSetMappings({
        @SqlResultSetMapping(name = ShippingLanesDto.Config.ResultMappings.SHIPPING_LANES_MAPPING, classes = {
                @ConstructorResult(
                        targetClass = ShippingLanesDto.class,
                        columns = {
                                @ColumnResult(name = "RANK", type = Long.class),
                                @ColumnResult(name = "SHIPPERADDRESS", type = String.class),
                                @ColumnResult(name = "RECEIVERADDRESS", type = String.class),
                                @ColumnResult(name = "LANETOTAL", type = Double.class),
                        })
        }),
        @SqlResultSetMapping(name = ShippingLanesDto.Config.ResultMappings.SHIPPING_LANES_BY_CARRIER_MAPPING, classes = {
                @ConstructorResult(
                        targetClass = ShippingLanesDto.class,
                        columns = {
                                @ColumnResult(name = "CARRIER_ID", type = Long.class),
                                @ColumnResult(name = "CARRIER_NAME", type = String.class),
                                @ColumnResult(name = "SPEND", type = Double.class),
                        })
        }),
        @SqlResultSetMapping(name = ShippingLanesDto.Config.ResultMappings.SHIPPING_LANES_BY_MONTH_MAPPING, classes = {
                @ConstructorResult(
                        targetClass = ShippingLanesDto.class,
                        columns = {
                                @ColumnResult(name = "BILL_DATE", type = Date.class),
                                @ColumnResult(name = "AMOUNT", type = Double.class),
                        })
        })
})

@Entity
public class ShippingLanesDto {

    @Id
    @Column(name = "ID")
    private Long id;

    @Column(name = "RANK")
    private Long rank;

    @Column(name = "SHIPPERADDRESS")
    private String shipperAddress;

    @Column(name = "RECEIVERADDRESS")
    private String receiverAddress;

    @Column (name = "LANETOTAL")
    private Double laneTotal;

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


    public ShippingLanesDto (Long rank, String shipperAddress,String receiverAddress, Double laneCount) {
        this.setRank(rank);
        this.setShipperAddress(shipperAddress);
        this.setReceiverAddress(receiverAddress);
        this.setLaneTotal(laneCount);
    }

    public ShippingLanesDto (Long carrierId, String carrierName,Double spend) {
        this.setCarrierId(carrierId);
        this.setCarrierName(carrierName);
        this.setSpend(spend);
    }

    public ShippingLanesDto (Date billDate,Double amount) {
        this.setBillDate(billDate);
        this.setAmount(amount);
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRank() {
        return rank;
    }

    public void setRank(Long rank) {
        this.rank = rank;
    }

    public String getShipperAddress() {
        return shipperAddress;
    }

    public void setShipperAddress(String shipperAddress) {
        this.shipperAddress = shipperAddress;
    }

    public String getReceiverAddress() {
        return receiverAddress;
    }

    public void setReceiverAddress(String receiverAddress) {
        this.receiverAddress = receiverAddress;
    }

    public Double getLaneTotal() {
        return laneTotal;
    }

    public void setLaneTotal(Double laneTotal) {
        this.laneTotal = laneTotal;
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

    public static class Config{
        static class ResultMappings{
            static final String SHIPPING_LANES_MAPPING = "ShippingLanesDto.ShippingLanesMapping";
            static final String SHIPPING_LANES_BY_CARRIER_MAPPING = "ShippingLanesDto.ShippingLanesCarrierMapping";
            static final String SHIPPING_LANES_BY_MONTH_MAPPING = "ShippingLanesDto.ShippingLanesMonthMapping";
        }

        static class StoredProcedureName{
            static final String SHIPPING_LANES = "shp_db_shping_lanes_proc";
            static final String SHIPPING_LANES_BY_CARRIER = "SHP_DB_SHPING_LANES_CARR_PROC";
            static final String SHIPPING_LANES_BY_MONTH = "SHP_DB_SHPING_LANES_MNTH_PROC";
        }

        public static class StoredProcedureQueryName{
            public static final String SHIPPING_LANES = "ShippingLanesDto.getTopShippingLanes";
            public static final String SHIPPING_LANES_BY_CARRIER = "ShippingLanesDto.getShippingLanesByCarrier";
            public static final String SHIPPING_LANES_BY_MONTH = "ShippingLanesDto.getShippingLanesByMonth";
        }
    }
}
