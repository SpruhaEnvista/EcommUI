package com.envista.msi.api.web.rest.dto.dashboard.networkanalysis;

import com.envista.msi.api.domain.util.DashboardSroredProcParam;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Sarvesh on 2/17/2017.
 */


@NamedStoredProcedureQueries({
        @NamedStoredProcedureQuery(name = "PortLanesDto.getTopPortLanes", procedureName = "SHP_DB_PORT_LANES_PROC",
                resultSetMappings = "PortLanesMapping",
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.PortLanesParams.DATE_TYPE_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.PortLanesParams.CURRENCY_ID_PARAM, type = Long.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.PortLanesParams.CUSTOMER_IDS_CSV_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.PortLanesParams.CARRIER_ID_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.PortLanesParams.MODES_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.PortLanesParams.SERVICES_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.PortLanesParams.LANES_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.PortLanesParams.FROM_DATE_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.PortLanesParams.TO_DATE_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, name = DashboardSroredProcParam.PortLanesParams.RESULTS_DATA_PARAM, type = Void.class)
                }),
        @NamedStoredProcedureQuery(name = "PortLanesDto.getPortLanesByCarrier", procedureName = "SHP_DB_PORT_LANES_CARR_PROC",
                resultSetMappings = "PortLanesCarrierMapping",
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.PortLanesParams.DATE_TYPE_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.PortLanesParams.CURRENCY_ID_PARAM, type = Long.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.PortLanesParams.CUSTOMER_IDS_CSV_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.PortLanesParams.CARRIER_ID_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.PortLanesParams.MODES_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.PortLanesParams.SERVICES_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.PortLanesParams.LANES_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.PortLanesParams.FROM_DATE_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.PortLanesParams.TO_DATE_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.PortLanesParams.POL, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.PortLanesParams.POD, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, name = DashboardSroredProcParam.PortLanesParams.RESULTS_DATA_PARAM, type = Void.class)
                }),
        @NamedStoredProcedureQuery(name = "PortLanesDto.getPortLanesByMonth", procedureName = "SHP_DB_PORT_LANES_MNTH_PROC",
                resultSetMappings = "PortLanesMonthMapping",
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.PortLanesParams.DATE_TYPE_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.PortLanesParams.CURRENCY_ID_PARAM, type = Long.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.PortLanesParams.CUSTOMER_IDS_CSV_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.PortLanesParams.CARRIER_ID_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.PortLanesParams.MODES_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.PortLanesParams.SERVICES_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.PortLanesParams.LANES_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.PortLanesParams.FROM_DATE_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.PortLanesParams.TO_DATE_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.PortLanesParams.POL, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.PortLanesParams.POD, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, name = DashboardSroredProcParam.PortLanesParams.RESULTS_DATA_PARAM, type = Void.class)
                })
})


@SqlResultSetMappings({
        @SqlResultSetMapping(name = "PortLanesMapping", classes = {
                @ConstructorResult(
                        targetClass = PortLanesDto.class,
                        columns = {
                                @ColumnResult(name = "RANK", type = Long.class),
                                @ColumnResult(name = "POL", type = String.class),
                                @ColumnResult(name = "POD", type = String.class),
                                @ColumnResult(name = "LANETOTAL", type = Double.class),
                        })
        }),
        @SqlResultSetMapping(name = "PortLanesCarrierMapping", classes = {
                @ConstructorResult(
                        targetClass = PortLanesDto.class,
                        columns = {
                                @ColumnResult(name = "CARRIER_ID", type = Long.class),
                                @ColumnResult(name = "CARRIER_NAME", type = String.class),
                                @ColumnResult(name = "SPEND", type = Double.class),
                        })
        }),
        @SqlResultSetMapping(name = "PortLanesMonthMapping", classes = {
                @ConstructorResult(
                        targetClass = PortLanesDto.class,
                        columns = {
                                @ColumnResult(name = "BILL_DATE", type = Date.class),
                                @ColumnResult(name = "AMOUNT", type = Double.class),
                        })
        })
})

@Entity
public class PortLanesDto {
    @Id
    @Column(name = "ID")
    private Long id;

    @Column(name = "RANK")
    private Long rank;

    @Column(name = "POL")
    private String pol;

    @Column(name = "POD")
    private String pod;

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


    public PortLanesDto (Long rank, String pol,String pod, Double laneCount) {
        this.setRank(rank);
        this.setPol(pol);
        this.setPod(pod);
        this.setLaneTotal(laneCount);
    }

    public PortLanesDto (Long carrierId, String carrierName,Double spend) {
        this.setCarrierId(carrierId);
        this.setCarrierName(carrierName);
        this.setSpend(spend);
    }

    public PortLanesDto (Date billDate,Double amount) {
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

    public String getPol() {
        return pol;
    }

    public void setPol(String pol) {
        this.pol = pol;
    }

    public String getPod() {
        return pod;
    }

    public void setPod(String pod) {
        this.pod = pod;
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
}
