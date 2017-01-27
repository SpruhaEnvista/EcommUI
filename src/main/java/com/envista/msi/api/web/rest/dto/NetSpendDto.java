package com.envista.msi.api.web.rest.dto;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by Sujit kumar on 23/01/2017.
 */

@NamedStoredProcedureQueries({
        @NamedStoredProcedureQuery(name = "NetSpendDto.getNetSpendByMode", procedureName = "SHP_NET_SPEND_BY_MODES_PROC",
                resultClasses = NetSpendDto.class,
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_date_type", type = String.class),
                        /*@StoredProcedureParameter(mode = ParameterMode.IN, name = "p_currency_id", type = Long.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_converted_curr_code", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_customer_ids", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_carrier_id", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_modes", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_services", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_lanes_info", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_accessorial_name", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_from_date", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_to_date", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_is_top_ten_accessorial", type = Long.class),*/
                        @StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, name = "p_net_spend", type = Void.class)
                }),

        @NamedStoredProcedureQuery(name = "NetSpendDto.getNetSpendByMonth", procedureName = "SHP_NET_SPEND_BY_MONTH_PROC",
                resultClasses = NetSpendDto.class,
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_date_type", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_currency_id", type = Long.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_converted_curr_code", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_customer_ids", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_carrier_id", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_modes", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_services", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_lanes_info", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_accessorial_name", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_from_date", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_to_date", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_is_top_ten_accessorial", type = Long.class),
                        @StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, name = "p_net_spend", type = Void.class)
                }),

        @NamedStoredProcedureQuery(name = "NetSpendDto.getNetSpendByOverTime", procedureName = "SHP_NET_SPEND_OVER_TIME_PROC",
                resultClasses = NetSpendDto.class,
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_date_type", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_currency_id", type = Long.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_converted_curr_code", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_customer_ids", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_carrier_id", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_modes", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_services", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_lanes_info", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_accessorial_name", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_from_date", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_to_date", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_is_top_ten_accessorial", type = Long.class),
                        @StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, name = "p_net_spend", type = Void.class)
                })
})

@Entity
public class NetSpendDto implements Serializable {
    @Id
    @Column(name = "NET_SPEND_ID",unique = true)
    private Long id;

    @Column(name = "MODES")
    private String modes;

    @Column(name = "SPEND")
    private Double spend;

    @Column(name = "SCORE_TYPE")
    private String scoreType;

    /*@Column(name = "BILL_DATE")
    private Date billDate;

    @Column(name = "AMOUNT")
    private Double amount;*/

    public String getModes() {
        return modes;
    }

    public void setModes(String modes) {
        this.modes = modes;
    }

    public Double getSpend() {
        return spend;
    }

    public void setSpend(Double spend) {
        this.spend = spend;
    }

    public String getScoreType() {
        return scoreType;
    }

    public void setScoreType(String scoreType) {
        this.scoreType = scoreType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    /*public Date getBillDate() {
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
    }*/
}
