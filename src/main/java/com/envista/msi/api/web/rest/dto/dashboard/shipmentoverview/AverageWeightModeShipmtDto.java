package com.envista.msi.api.web.rest.dto.dashboard.shipmentoverview;

import com.envista.msi.api.domain.util.DashboardSroredProcParam;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Siddhant on 14/02/2017.
 */
@NamedStoredProcedureQueries({
        @NamedStoredProcedureQuery(name = "AverageWeightModeShipmtDto.getAverageWeightModeShipment", procedureName = "SHP_DB_AVG_WET_MODE_SHPMT_PROC",
                resultSetMappings = "AverageWeightModeShipmtDto.AvgWeightByModeShipmentMapping",
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.AverageWeightByModeShipmtParam.DATE_TYPE_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.AverageWeightByModeShipmtParam.CONVERTED_WEIGHT_UNIT_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.AverageWeightByModeShipmtParam.MODES_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.AverageWeightByModeShipmtParam.SERVICES_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.AverageWeightByModeShipmtParam.ACCESSORIAL_NAME_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.AverageWeightByModeShipmtParam.LANES_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.AverageWeightByModeShipmtParam.FROM_DATE_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.AverageWeightByModeShipmtParam.TO_DATE_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.AverageWeightByModeShipmtParam.CARRIER_ID_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.AverageWeightByModeShipmtParam.CUSTOMER_IDS_CSV_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.AverageWeightByModeShipmtParam.TOP_ACCESSORIAL_SPEND_PARAM, type = Long.class),
                        @StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, name = DashboardSroredProcParam.AverageWeightByModeShipmtParam.AVG_WEGT_MODE_SHIPMT_PARAM, type = Void.class)
                })
})
@SqlResultSetMappings({
        @SqlResultSetMapping(name = "AverageWeightModeShipmtDto.AvgWeightByModeShipmentMapping", classes = {
                @ConstructorResult(
                        targetClass = AverageWeightModeShipmtDto.class,
                        columns = {
                                @ColumnResult(name = "BILL_DATE", type = String.class),
                                @ColumnResult(name = "MODES", type = String.class),
                                @ColumnResult(name = "NET_WEIGHT", type = Double.class)
                        }
                )
        })
})

@Entity
public class AverageWeightModeShipmtDto implements Serializable {
    @Id
    private Long id;

    @Column(name = "BILL_DATE")
    private String billDate;

    @Column(name = "MODES")
    private String modes;

    @Column(name = "NET_WEIGHT")
    private Double netWeight;

    public AverageWeightModeShipmtDto() {    }

    public AverageWeightModeShipmtDto(Long id, String billDate, String modes, Double netWeight) {
        this.id = id;
        this.billDate = billDate;
        this.modes = modes;
        this.netWeight = netWeight;
    }

    public AverageWeightModeShipmtDto(String billDate, String modes, Double netWeight) {
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
