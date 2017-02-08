package com.envista.msi.api.web.rest.dto.dashboard.taxspend;

import com.envista.msi.api.domain.util.DashboardSroredProcParam;

import javax.persistence.*;

/**
 * Created by Sujit kumar on 01/02/2017.
 */
@NamedStoredProcedureQueries({
        @NamedStoredProcedureQuery(name = "TaxSpendDto.getTaxSpend", procedureName = "SHP_DB_TAX_SPEND_PROC",
                resultClasses = TaxSpendDto.class,
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.TaxSpendParams.DATE_TYPE_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.TaxSpendParams.CURRENCY_ID_PARAM, type = Long.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.TaxSpendParams.CUSTOMER_IDS_CSV_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.TaxSpendParams.CARRIER_ID_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.TaxSpendParams.MODES_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.TaxSpendParams.SERVICES_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.TaxSpendParams.LANES_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.TaxSpendParams.FROM_DATE_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.TaxSpendParams.TO_DATE_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.TaxSpendParams.MODE_NAMES_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, name = DashboardSroredProcParam.TaxSpendParams.TAX_SPEND_PARAM, type = Void.class)
                })
})
@Entity
public class TaxSpendDto {
    @Id
    @Column(name = "TAX_SPEND_ID")
    private Long id;

    @Column(name = "RANK")
    private Long rank;

    @Column(name = "TAX")
    private String tax;

    @Column(name = "SPEND")
    private Double spend;

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

    public String getTax() {
        return tax;
    }

    public void setTax(String tax) {
        this.tax = tax;
    }

    public Double getSpend() {
        return spend;
    }

    public void setSpend(Double spend) {
        this.spend = spend;
    }
}
