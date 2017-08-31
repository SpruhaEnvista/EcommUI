package com.envista.msi.api.web.rest.dto.dashboard.common;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Sujit kumar on 28/08/2017.
 */
public class CommonWeekChartDto implements Serializable {
    private Date billDate;
    private Integer weekNumber;
    private Double amount;
    private Long count;

    public Date getBillDate() {
        return billDate;
    }

    public void setBillDate(Date billDate) {
        this.billDate = billDate;
    }

    public Integer getWeekNumber() {
        return weekNumber;
    }

    public void setWeekNumber(Integer weekNumber) {
        this.weekNumber = weekNumber;
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
