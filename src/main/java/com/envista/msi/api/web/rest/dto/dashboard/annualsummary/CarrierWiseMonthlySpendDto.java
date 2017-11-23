package com.envista.msi.api.web.rest.dto.dashboard.annualsummary;

import com.envista.msi.api.domain.util.DashboardStoredProcParam;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Sujit kumar on 17/11/2017.
 */

@NamedStoredProcedureQueries({
        @NamedStoredProcedureQuery(
                name = CarrierWiseMonthlySpendDto.Config.CarrierWiseMonthlySpend.STORED_PROCEDURE_QUERY_NAME,
                procedureName = CarrierWiseMonthlySpendDto.Config.CarrierWiseMonthlySpend.STORED_PROCEDURE_NAME,
                resultSetMappings = {CarrierWiseMonthlySpendDto.Config.CarrierWiseMonthlySpend.STORED_PROCEDURE_QUERY_MAPPING},
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.CarrierWiseMonthlySpend.DATE_TYPE_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.CarrierWiseMonthlySpend.CONVERTED_CURRENCY_ID_PARAM, type = Long.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.CarrierWiseMonthlySpend.CONVERTED_CURRENCY_CODE_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.CarrierWiseMonthlySpend.CUSTOMER_IDS_CSV_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.CarrierWiseMonthlySpend.CARRIER_IDS_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.CarrierWiseMonthlySpend.MODES_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.CarrierWiseMonthlySpend.SERVICES_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.CarrierWiseMonthlySpend.LANES_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.CarrierWiseMonthlySpend.ACCESSORIAL_NAME_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.CarrierWiseMonthlySpend.FROM_DATE_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.CarrierWiseMonthlySpend.TO_DATE_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.CarrierWiseMonthlySpend.TOP_TEN_ACCESSORIAL_PARAM, type = Long.class),
                        @StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, name = DashboardStoredProcParam.CarrierWiseMonthlySpend.MONTHLY_SPEND_PARAM, type = Void.class)
                }
        )
})

@SqlResultSetMappings({
        @SqlResultSetMapping(
                name = CarrierWiseMonthlySpendDto.Config.CarrierWiseMonthlySpend.STORED_PROCEDURE_QUERY_MAPPING,
                classes = {
                        @ConstructorResult(
                                targetClass = CarrierWiseMonthlySpendDto.class,
                                columns = {
                                        @ColumnResult(name = "billing_date", type = String.class),
                                        @ColumnResult(name = "carrier_name", type = String.class),
                                        @ColumnResult(name = "net_charges", type = Double.class),
                                }
                        )
                }
        )
})

@Entity
public class CarrierWiseMonthlySpendDto implements Serializable {
    @Id
    private Long id;

    @Column(name = "billing_date")
    private String billingDate;

    @Column(name = "carrier_name")
    private String carrierName;

    @Column(name = "net_charges")
    private Double netCharges;

    public CarrierWiseMonthlySpendDto() {
    }

    public CarrierWiseMonthlySpendDto(String billingDate, String carrierName, Double netCharges) {
        this.billingDate = billingDate;
        this.carrierName = carrierName;
        this.netCharges = netCharges;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCarrierName() {
        return carrierName;
    }

    public void setCarrierName(String carrierName) {
        this.carrierName = carrierName;
    }

    public String getBillingDate() {
        return billingDate;
    }

    public void setBillingDate(String billingDate) {
        this.billingDate = billingDate;
    }

    public Double getNetCharges() {
        return netCharges;
    }

    public void setNetCharges(Double netCharges) {
        this.netCharges = netCharges;
    }

    public static class Config{
        public static class CarrierWiseMonthlySpend {
            public static final String STORED_PROCEDURE_QUERY_NAME = "CarrierWiseMonthlySpendDto.getMonthlySpendByCarrier";
            public static final String STORED_PROCEDURE_NAME = "SHP_DB_MONTHLY_SPEND_CARR_PROC";
            public static final String STORED_PROCEDURE_QUERY_MAPPING = "CarrierWiseMonthlySpendDto.getMonthlySpendByCarrierMapping";
        }
    }
}
