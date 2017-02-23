package com.envista.msi.api.web.rest.dto.dashboard.shipmentoverview;

import com.envista.msi.api.domain.util.DashboardSroredProcParam;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Siddhant on 17/02/2017.
 */
@NamedStoredProcedureQueries({
        @NamedStoredProcedureQuery(name = "AverageWeightModeByMonthDto.getAverageWeightModeByMonth", procedureName = "SHP_DB_AVG_WGT_BY_MNTH_PROC",
                resultSetMappings = "AverageWeightModeByMonthDto.AvgWeightModetByMonthMapping",
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.AverageWeightShipmentByMonthParam.DATE_TYPE_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.AverageWeightShipmentByMonthParam.MODES_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.AverageWeightShipmentByMonthParam.SERVICES_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.AverageWeightShipmentByMonthParam.ACCESSORIAL_NAME_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.AverageWeightShipmentByMonthParam.LANES_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.AverageWeightShipmentByMonthParam.FROM_DATE_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.AverageWeightShipmentByMonthParam.TO_DATE_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.AverageWeightShipmentByMonthParam.CONVERTE_WEIGHT_UNIT_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.AverageWeightShipmentByMonthParam.CARRIER_ID_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.AverageWeightShipmentByMonthParam.CUSTOMER_IDS_CSV_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.AverageWeightShipmentByMonthParam.TOP_ACCESSORIAL_SPEND_PARAM, type = Long.class),
                        @StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, name = DashboardSroredProcParam.AverageWeightShipmentByMonthParam.AVG_WGT_SHIPMT_BY_MNTH_PARAM, type = Void.class)
                })
})
@SqlResultSetMappings({
        @SqlResultSetMapping(name = "AverageWeightModeByMonthDto.AvgWeightModetByMonthMapping", classes = {
                @ConstructorResult(
                        targetClass = AverageWeightModeByMonthDto.class,
                        columns = {
                                @ColumnResult(name = "BILL_DATE", type = Date.class),
                                @ColumnResult(name = "AMOUNT", type = Double.class),
                        }
                )
        })
})

@Entity
public class AverageWeightModeByMonthDto {
    @Id
    private Long id;

    @Column(name = "BILLL_DATE")
    private Date billDate;

    @Column(name = "AMOUNT")
    private Double amount;

    public AverageWeightModeByMonthDto() {    }

    public AverageWeightModeByMonthDto(Date billDate, Double amount) {
        this.billDate = billDate;
        this.amount = amount;
    }

    public AverageWeightModeByMonthDto(Long id, Date billDate, Double amount) {
        this.id = id;
        this.billDate = billDate;
        this.amount = amount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getBillDate() {
        return billDate;
    }

    public void setBillDate(Date billDate) {
        this.billDate = billDate;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }
}
