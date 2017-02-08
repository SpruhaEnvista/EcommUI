package com.envista.msi.api.web.rest.dto.dashboard.common;

import com.envista.msi.api.web.rest.dto.dashboard.accessorialspend.AccessorialSpendDto;
import com.envista.msi.api.web.rest.dto.dashboard.netspend.NetSpendByCarrierDto;
import com.envista.msi.api.web.rest.dto.dashboard.taxspend.TaxSpendByCarrierDto;

import java.io.Serializable;

/**
 * Created by Sujit kumar on 02/02/2017.
 */
public class CommonValuesForChartDto implements Serializable{
    private Long id;
    private String name;
    private Double value;

    public CommonValuesForChartDto(){}

    public CommonValuesForChartDto(NetSpendByCarrierDto netSpend){
        this.id = netSpend.getCarrierId();
        this.name = netSpend.getCarrierName();
        this.value = netSpend.getSpend();
    }

    public CommonValuesForChartDto(TaxSpendByCarrierDto taxSpend){
        this.id = taxSpend.getCarrierId();
        this.name = taxSpend.getName();
        this.value = taxSpend.getValue();
    }

    public CommonValuesForChartDto(AccessorialSpendDto accSpend){
        this.id = accSpend.getCarrierId();
        this.name = accSpend.getCarrierName();
        this.value = accSpend.getSpend();
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
