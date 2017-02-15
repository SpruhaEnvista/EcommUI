package com.envista.msi.api.web.rest.dto.dashboard.common;

import com.envista.msi.api.web.rest.dto.dashboard.accessorialspend.AccessorialSpendDto;
import com.envista.msi.api.web.rest.dto.dashboard.netspend.NetSpendByMonthDto;
import com.envista.msi.api.web.rest.dto.dashboard.netspend.NetSpendOverTimeByMonthDto;
import com.envista.msi.api.web.rest.dto.dashboard.shipmentoverview.InboundSpendDto;
import com.envista.msi.api.web.rest.dto.dashboard.shipmentoverview.OutboundSpendDto;
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

    public NetSpendMonthlyChartDto(InboundSpendDto inboundSpend){
        this.billDate = inboundSpend.getBillDate();
        this.amount = inboundSpend.getAmount();
    }

    public NetSpendMonthlyChartDto(OutboundSpendDto outboundSpend){
        this.billDate = outboundSpend.getBillDate();
        this.amount = outboundSpend.getAmount();
    }

    public static List<NetSpendMonthlyChartDto> buildInboundSpendListToNetSpendMonthlyChartList(List<InboundSpendDto> inboundSpendList){
        List<NetSpendMonthlyChartDto> monthlyChartDtoList = null;
        if(inboundSpendList != null && !inboundSpendList.isEmpty()){
            monthlyChartDtoList = new ArrayList<NetSpendMonthlyChartDto>();
            for(InboundSpendDto inboundSpend : inboundSpendList){
                if(inboundSpend != null){
                    monthlyChartDtoList.add(new NetSpendMonthlyChartDto(inboundSpend));
                }
            }
        }
        return monthlyChartDtoList;
    }

    public static List<NetSpendMonthlyChartDto> buildOutboundSpendListToNetSpendMonthlyChartList(List<OutboundSpendDto> outboundSpendList){
        List<NetSpendMonthlyChartDto> monthlyChartDtoList = null;
        if(outboundSpendList != null && !outboundSpendList.isEmpty()){
            monthlyChartDtoList = new ArrayList<NetSpendMonthlyChartDto>();
            for(OutboundSpendDto outboundSpend : outboundSpendList){
                if(outboundSpend != null){
                    monthlyChartDtoList.add(new NetSpendMonthlyChartDto(outboundSpend));
                }
            }
        }
        return monthlyChartDtoList;
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
