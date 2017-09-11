package com.envista.msi.api.web.rest.dto.dashboard.common;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Sujit kumar on 28/08/2017.
 */
public class CommonPeriodChartDto implements Serializable{
    private Date billDate;
    private Double averageAmount;
    private Double amount;
    private Long count;

    public Date getBillDate() {
        return billDate;
    }

    public void setBillDate(Date billDate) {
        this.billDate = billDate;
    }

    public Double getAverageAmount() {
        return averageAmount;
    }

    public void setAverageAmount(Double averageAmount) {
        this.averageAmount = averageAmount;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }
}
