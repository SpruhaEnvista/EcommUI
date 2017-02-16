package com.envista.msi.api.web.rest.dto.dashboard.common;

import com.envista.msi.api.web.rest.dto.dashboard.accessorialspend.AccessorialSpendDto;
import com.envista.msi.api.web.rest.dto.dashboard.taxspend.TaxSpendDto;

import java.io.Serializable;

/**
 * Created by Sujit kumar on 03/02/2017.
 */
public class NetSpendCommonDto implements Serializable{
    private String spendTypeName;
    private Double spend;

    public NetSpendCommonDto(){}

    public NetSpendCommonDto(String spendTypeName, Double spend) {
        this.spendTypeName = spendTypeName;
        this.spend = spend;
    }

    public NetSpendCommonDto(TaxSpendDto taxSpend){
        this.spendTypeName = taxSpend.getTax();
        this.spend = taxSpend.getSpend();
    }

    public NetSpendCommonDto(AccessorialSpendDto accSpend){
        this.spendTypeName = accSpend.getAccessorialDescription();
        this.spend = accSpend.getSpend();
    }

    public String getSpendTypeName() {
        return spendTypeName;
    }

    public void setSpendTypeName(String spendTypeName) {
        this.spendTypeName = spendTypeName;
    }

    public Double getSpend() {
        return spend;
    }

    public void setSpend(Double spend) {
        this.spend = spend;
    }
}
