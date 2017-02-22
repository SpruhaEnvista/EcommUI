package com.envista.msi.api.web.rest.dto.dashboard.auditactivity;

import com.envista.msi.api.domain.util.DashboardSroredProcParam;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by Sujit kumar on 09/02/2017.
 */

@NamedStoredProcedureQueries({
        @NamedStoredProcedureQuery(name = OrderMatchDto.Config.StoredProcedureQueryName.ORDER_MATCH,
                procedureName = OrderMatchDto.Config.StoredProcedureName.ORDER_MATCH,
                resultSetMappings = OrderMatchDto.Config.ResultMappings.ORDER_MATCH_MAPPING,
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.OrderMatchParams.DATE_TYPE_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.OrderMatchParams.CUSTOMER_IDS_CSV_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.OrderMatchParams.CARRIER_IDS_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.OrderMatchParams.MODES_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.OrderMatchParams.SERVICES_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.OrderMatchParams.LANES_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.OrderMatchParams.FROM_DATE_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.OrderMatchParams.TO_DATE_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.OrderMatchParams.MODE_NAMES_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, name = DashboardSroredProcParam.OrderMatchParams.ORDER_MATCH_DATA_PARAM, type = Void.class)
                }),
        @NamedStoredProcedureQuery(name = OrderMatchDto.Config.StoredProcedureQueryName.ORDER_MATCH_BY_CARRIER,
                procedureName = OrderMatchDto.Config.StoredProcedureName.ORDER_MATCH_BY_CARRIER,
                resultSetMappings = OrderMatchDto.Config.ResultMappings.ORDER_MATCH_BY_CARRIER_MAPPING,
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.OrderMatchParams.DATE_TYPE_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.OrderMatchParams.CUSTOMER_IDS_CSV_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.OrderMatchParams.CARRIER_IDS_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.OrderMatchParams.MODES_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.OrderMatchParams.SERVICES_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.OrderMatchParams.LANES_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.OrderMatchParams.FROM_DATE_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.OrderMatchParams.TO_DATE_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.OrderMatchParams.MODE_NAMES_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.OrderMatchParams.ORDER_MATCH_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, name = DashboardSroredProcParam.OrderMatchParams.ORDER_MATCH_DATA_PARAM, type = Void.class)
                }),
        @NamedStoredProcedureQuery(name = OrderMatchDto.Config.StoredProcedureQueryName.ORDER_MATCH_BY_MONTH,
                procedureName = OrderMatchDto.Config.StoredProcedureName.ORDER_MATCH_BY_MONTH,
                resultSetMappings = OrderMatchDto.Config.ResultMappings.ORDER_MATCH_BY_MONTH_MAPPING,
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.OrderMatchParams.DATE_TYPE_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.OrderMatchParams.CUSTOMER_IDS_CSV_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.OrderMatchParams.CARRIER_IDS_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.OrderMatchParams.MODES_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.OrderMatchParams.SERVICES_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.OrderMatchParams.LANES_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.OrderMatchParams.FROM_DATE_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.OrderMatchParams.TO_DATE_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.OrderMatchParams.MODE_NAMES_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.OrderMatchParams.ORDER_MATCH_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, name = DashboardSroredProcParam.OrderMatchParams.ORDER_MATCH_DATA_PARAM, type = Void.class)
                })
})

@SqlResultSetMappings({
        @SqlResultSetMapping(name = OrderMatchDto.Config.ResultMappings.ORDER_MATCH_MAPPING, classes = {
                @ConstructorResult(
                        targetClass = OrderMatchDto.class,
                        columns = {
                                @ColumnResult(name = "VALUE", type = Double.class),
                                @ColumnResult(name = "STATUS", type = String.class)
                        })
        }),
        @SqlResultSetMapping(name = OrderMatchDto.Config.ResultMappings.ORDER_MATCH_BY_CARRIER_MAPPING, classes = {
                @ConstructorResult(
                        targetClass = OrderMatchDto.class,
                        columns = {
                                @ColumnResult(name = "ID", type = Long.class),
                                @ColumnResult(name = "NAME", type = String.class),
                                @ColumnResult(name = "VALUE", type = Double.class)
                        })
        }),
        @SqlResultSetMapping(name = OrderMatchDto.Config.ResultMappings.ORDER_MATCH_BY_MONTH_MAPPING, classes = {
                @ConstructorResult(
                        targetClass = OrderMatchDto.class,
                        columns = {
                                @ColumnResult(name = "BILL_DATE", type = Date.class),
                                @ColumnResult(name = "AMOUNT", type = Double.class)
                        })
        })
})

@Entity
public class OrderMatchDto implements Serializable {
    @Id
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "VALUE")
    private Double value;

    @Column(name = "STATUS")
    private String status;

    @Column(name = "BILL_DATE")
    private Date billDate;

    @Column(name = "AMOUNT")
    private Double amount;

    public OrderMatchDto(){}

    public OrderMatchDto(Double value, String status) {
        this.value = value;
        this.status = status;
    }

    public OrderMatchDto(Long id, String name, Double value) {
        this.id = id;
        this.name = name;
        this.value = value;
    }

    public OrderMatchDto(Date billDate, Double amount) {
        this.billDate = billDate;
        this.amount = amount;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public static class Config{
        static class ResultMappings{
            static final String ORDER_MATCH_MAPPING = "OrderMatchDto.OrderMatchMapping";
            static final String ORDER_MATCH_BY_CARRIER_MAPPING = "OrderMatchDto.OrderMatchByCarrierMapping";
            static final String ORDER_MATCH_BY_MONTH_MAPPING = "OrderMatchDto.OrderMatchByMonthMapping";
        }

        static class StoredProcedureName{
            static final String ORDER_MATCH = "SHP_DB_ORDER_MATCH_PROC";
            static final String ORDER_MATCH_BY_CARRIER = "SHP_DB_ORDER_MATCH_CARR_PROC";
            static final String ORDER_MATCH_BY_MONTH = "SHP_DB_ORDER_MATCH_MNTH_PROC";
        }

        public static class StoredProcedureQueryName{
            public static final String ORDER_MATCH = "OrderMatchDto.getOrderMatch";
            public static final String ORDER_MATCH_BY_CARRIER = "OrderMatchDto.getOrderMatchByCarrier";
            public static final String ORDER_MATCH_BY_MONTH = "OrderMatchDto.getOrderMatchByMonth";
        }
    }
}
