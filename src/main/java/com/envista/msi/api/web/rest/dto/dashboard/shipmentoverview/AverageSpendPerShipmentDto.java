package com.envista.msi.api.web.rest.dto.dashboard.shipmentoverview;

import com.envista.msi.api.domain.util.DashboardSroredProcParam;


import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Siddhant on 13/02/2017.
 */
@NamedStoredProcedureQueries({
        @NamedStoredProcedureQuery(name = "AverageSpendPerShipmentDto.getAverageSpendPerShipment", procedureName = "SHP_DB_AVG_SPN_PER_SHPMT_PROC",
                resultSetMappings = "AverageSpendPerShipmentDto.AvgSpendPerShipmentMapping",
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.AverageSpendShipmentParam.DATE_TYPE_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.AverageSpendShipmentParam.CONVERTED_CURRENCY_ID_PARAM, type = Long.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.AverageSpendShipmentParam.CONVERTED_CURRENCY_CODE_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.AverageSpendShipmentParam.MODES_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.AverageSpendShipmentParam.SERVICES_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.AverageSpendShipmentParam.ACCESSORIAL_NAME_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.AverageSpendShipmentParam.LANES_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.AverageSpendShipmentParam.FROM_DATE_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.AverageSpendShipmentParam.TO_DATE_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.AverageSpendShipmentParam.CARRIER_ID_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.AverageSpendShipmentParam.CUSTOMER_IDS_CSV_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.AverageSpendShipmentParam.TOP_ACCESSORIAL_SPEND_PARAM, type = Long.class),
                        @StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, name = DashboardSroredProcParam.AverageSpendShipmentParam.AVG_SPEND_PER_SHIPMT_PARAM, type = Void.class)
                })
})

@SqlResultSetMappings({
        @SqlResultSetMapping(name = "AverageSpendPerShipmentDto.AvgSpendPerShipmentMapping", classes = {
                @ConstructorResult(
                        targetClass = AverageSpendPerShipmentDto.class,
                        columns = {
                                @ColumnResult(name = "BILL_DATE", type = String.class),
                                @ColumnResult(name = "MODES", type = String.class),
                                @ColumnResult(name = "NET_WEIGHT", type = Double.class)
                        }
                )
        })
})

@Entity
public class AverageSpendPerShipmentDto implements Serializable{
    @Id
    private Long id;

    @Column(name = "BILL_DATE")
    private String billDate;

    @Column(name = "MODES")
    private String modes;

    @Column(name = "NET_WEIGHT")
    private Double netWeight;

    public AverageSpendPerShipmentDto() {  }

    public AverageSpendPerShipmentDto(String billDate, String modes, Double netWeight) {
        this.billDate = billDate;
        this.modes = modes;
        this.netWeight = netWeight;
    }

    public AverageSpendPerShipmentDto(Long id, String billDate, String modes, Double netWeight) {
        this.id = id;
        this.billDate = billDate;
        this.modes = modes;
        this.netWeight = netWeight;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBillDate() {
        return billDate;
    }

    public void setBillDate(String billDate) {
        this.billDate = billDate;
    }

    public String getModes() {
        return modes;
    }

    public void setModes(String modes) {
        this.modes = modes;
    }

    public Double getNetWeight() {
        return netWeight;
    }

    public void setNetWeight(Double netWeight) {
        this.netWeight = netWeight;
    }



}
