package com.envista.msi.api.web.rest.dto.dashboard.shipmentoverview;

import com.envista.msi.api.domain.util.DashboardSroredProcParam;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Siddhant on 15/02/2017.
 */
@NamedStoredProcedureQueries({
        @NamedStoredProcedureQuery(name = "AverageSpendPerShipmentByMonthDto.getAverageSpendPerShipmentByMonth", procedureName = "SHP_DB_AVG_SPEND_BY_MNTH_PROC",
                resultSetMappings = "AverageSpendPerShipmentByMonthDto.AvgSpendPerShipmentByMonthMapping",
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.AverageSpendShipmentByMonthParam.DATE_TYPE_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.AverageSpendShipmentByMonthParam.CONVERTED_CURRENCY_ID_PARAM, type = Long.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.AverageSpendShipmentByMonthParam.CONVERTED_CURRENCY_CODE_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.AverageSpendShipmentByMonthParam.MODES_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.AverageSpendShipmentByMonthParam.SERVICES_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.AverageSpendShipmentByMonthParam.ACCESSORIAL_NAME_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.AverageSpendShipmentByMonthParam.LANES_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.AverageSpendShipmentByMonthParam.FROM_DATE_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.AverageSpendShipmentByMonthParam.TO_DATE_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.AverageSpendShipmentByMonthParam.CARRIER_ID_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.AverageSpendShipmentByMonthParam.CUSTOMER_IDS_CSV_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.AverageSpendShipmentByMonthParam.TOP_ACCESSORIAL_SPEND_PARAM, type = Long.class),
                        @StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, name = DashboardSroredProcParam.AverageSpendShipmentByMonthParam.AVG_SPEND_PER_SHIPMT_PARAM, type = Void.class)
                })
})

@SqlResultSetMappings({
        @SqlResultSetMapping(name = "AverageSpendPerShipmentByMonthDto.AvgSpendPerShipmentByMonthMapping", classes = {
                @ConstructorResult(
                        targetClass = AverageSpendPerShipmentByMonthDto.class,
                        columns = {
                                @ColumnResult(name = "BILL_DATE", type = Date.class),
                                @ColumnResult(name = "AMOUNT", type = Double.class),
                        }
                )
        })
})

@Entity
public class AverageSpendPerShipmentByMonthDto {
    @Id
    private Long id;

    @Column(name = "BILLL_DATE")
    private Date billDate;

    @Column(name = "AMOUNT")
    private Double amount;

    public AverageSpendPerShipmentByMonthDto() {    }

    public AverageSpendPerShipmentByMonthDto(Date billDate, Double amount) {
        this.billDate = billDate;
        this.amount = amount;
    }

    public AverageSpendPerShipmentByMonthDto(Long id, Date billDate, Double amount) {
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
