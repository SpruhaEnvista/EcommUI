package com.envista.msi.api.web.rest.dto.dashboard.common;

import com.envista.msi.api.web.rest.dto.dashboard.shipmentoverview.AverageSpendPerShipmentByMonthDto;

import javax.persistence.Column;
import java.util.Date;

/**
 * Created by Siddhant on 15/02/2017.
 */
public class CommonMonthlyChartDto {
    @Column(name = "AMOUNT")
    private Double amount;

    @Column(name = "BILLING_DATE")
    private Date billDate;

    public CommonMonthlyChartDto() {
    }

    public CommonMonthlyChartDto(Double amount, Date billDate) {
        this.amount = amount;
        this.billDate = billDate;
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
