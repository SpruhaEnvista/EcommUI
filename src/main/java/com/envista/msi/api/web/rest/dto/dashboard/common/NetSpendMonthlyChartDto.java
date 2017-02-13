package com.envista.msi.api.web.rest.dto.dashboard.common;

import com.envista.msi.api.web.rest.DashboardsController;
import com.envista.msi.api.web.rest.dto.dashboard.accessorialspend.AccessorialSpendDto;
import com.envista.msi.api.web.rest.dto.dashboard.auditactivity.*;
import com.envista.msi.api.web.rest.dto.dashboard.netspend.NetSpendByMonthDto;
import com.envista.msi.api.web.rest.dto.dashboard.netspend.NetSpendOverTimeByMonthDto;
import com.envista.msi.api.web.rest.dto.dashboard.taxspend.TaxSpendByMonthDto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

    public NetSpendMonthlyChartDto(AccessorialSpendDto accSpend){
        this.billDate = accSpend.getBillingDate();
        this.amount = accSpend.getAmount();
    }

    public NetSpendMonthlyChartDto(InvoiceStatusCountDto invStsCount){
        this.billDate = invStsCount.getBillDate();
        this.amount = invStsCount.getAmount();
    }

    public NetSpendMonthlyChartDto(InvoiceStatusAmountDto invStsamt){
        this.billDate = invStsamt.getBillDate();
        this.amount = invStsamt.getAmount();
    }

    public NetSpendMonthlyChartDto(InvoiceMethodScoreDto invoiceMethodScore){
        this.billDate = invoiceMethodScore.getBillDate();
        this.amount = invoiceMethodScore.getAmount();
    }

    public NetSpendMonthlyChartDto(OrderMatchDto orderMatch){
        this.billDate = orderMatch.getBillDate();
        this.amount = orderMatch.getAmount();
    }

    public NetSpendMonthlyChartDto(BilledVsApprovedDto billedVsApproved){
        this.billDate = billedVsApproved.getBillDate();
        this.amount = billedVsApproved.getAmount();
    }

    public NetSpendMonthlyChartDto(RecoveryAdjustmentDto recoveryAdjustment){
        this.billDate = recoveryAdjustment.getBillDate();
        this.amount = recoveryAdjustment.getAmount();
    }

    public NetSpendMonthlyChartDto(RecoveryServiceDto recoveryAdjustment){
        this.billDate = recoveryAdjustment.getBillDate();
        this.amount = recoveryAdjustment.getAmount();
    }

    public static List<NetSpendMonthlyChartDto> buildRecoveryAdjustmentListToMonthlyChartList(List<RecoveryAdjustmentDto> recoveryAdjustmentList){
        List<NetSpendMonthlyChartDto> monthlyChartList = null;
        if(recoveryAdjustmentList != null && !recoveryAdjustmentList.isEmpty()){
            monthlyChartList = new ArrayList<NetSpendMonthlyChartDto>();
            for(RecoveryAdjustmentDto recoveryAdjustment : recoveryAdjustmentList){
                if(recoveryAdjustment != null){
                    monthlyChartList.add(new NetSpendMonthlyChartDto(recoveryAdjustment));
                }
            }
        }
        return monthlyChartList;
    }

    public static List<NetSpendMonthlyChartDto> buildRecoveryServiceListToMonthlyChartList(List<RecoveryServiceDto> recoveryServiceList){
        List<NetSpendMonthlyChartDto> monthlyChartList = null;
        if(recoveryServiceList != null && !recoveryServiceList.isEmpty()){
            monthlyChartList = new ArrayList<NetSpendMonthlyChartDto>();
            for(RecoveryServiceDto recoveryService : recoveryServiceList){
                if(recoveryService != null){
                    monthlyChartList.add(new NetSpendMonthlyChartDto(recoveryService));
                }
            }
        }
        return monthlyChartList;
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
