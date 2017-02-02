package com.envista.msi.api.web.rest.dto.netspend;

import com.envista.msi.api.web.rest.dto.taxspend.TaxSpendByMonthDto;

import java.util.Date;

/**
 * Created by Sujit kumar on 31/01/2017.
 */
public class NetSpendMonthlyChartDto {
    private Date billDate;
    private Double amount;

    public NetSpendMonthlyChartDto(){}

    public NetSpendMonthlyChartDto(NetSpendOverTimeByMonthDto netSpendOverTimeByMonthDto){
        this.billDate = netSpendOverTimeByMonthDto.getBillDate();
        this.amount = netSpendOverTimeByMonthDto.getAmount();
    }

    public NetSpendMonthlyChartDto(NetSpendByMonthDto netSpendByMonthDto){
        this.billDate = netSpendByMonthDto.getBillDate();
        this.amount = netSpendByMonthDto.getAmount();
    }

    public NetSpendMonthlyChartDto(TaxSpendByMonthDto taxSpend){
        this.billDate = taxSpend.getBillDate();
        this.amount = taxSpend.getAmount();
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
