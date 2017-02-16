package com.envista.msi.api.web.rest.dto.dashboard.common;

import com.envista.msi.api.web.rest.dto.dashboard.accessorialspend.AccessorialSpendDto;
import com.envista.msi.api.web.rest.dto.dashboard.shipmentoverview.InboundSpendDto;
import com.envista.msi.api.web.rest.dto.dashboard.shipmentoverview.OutboundSpendDto;
import com.envista.msi.api.web.rest.dto.dashboard.taxspend.TaxSpendDto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sujit kumar on 03/02/2017.
 */
public class NetSpendCommonDto implements Serializable{
    private String spendTypeName;
    private Double spend;
    private String billingDate;
    private Long carrierId;
    private String carrierName;
    private Double netDueAmount;

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

    public NetSpendCommonDto(InboundSpendDto inboundSpend) {
        this.billingDate = inboundSpend.getBillingDate();
        this.carrierId = inboundSpend.getCarrierId();
        this.carrierName = inboundSpend.getCarrierName();
        this.netDueAmount = inboundSpend.getNetDueAmount();
    }

    public NetSpendCommonDto(OutboundSpendDto outboundSpend) {
        this.billingDate = outboundSpend.getBillingDate();
        this.carrierId = outboundSpend.getCarrierId();
        this.carrierName = outboundSpend.getCarrierName();
        this.netDueAmount = outboundSpend.getNetDueAmount();
    }

    public static List<NetSpendCommonDto> buildInboundSpendListToNetSpendCommonList(List<InboundSpendDto> inboundSpendList){
        List<NetSpendCommonDto> spendCommonList = null;
        if(inboundSpendList != null && !inboundSpendList.isEmpty()){
            spendCommonList = new ArrayList<NetSpendCommonDto>();
            for(InboundSpendDto spendDto : inboundSpendList){
                if(spendDto != null){
                    spendCommonList.add(new NetSpendCommonDto(spendDto));
                }
            }
        }
        return spendCommonList;
    }

    public static List<NetSpendCommonDto> buildOutboundSpendListToNetSpendCommonList(List<OutboundSpendDto> outboundSpendList){
        List<NetSpendCommonDto> spendCommonList = null;
        if(outboundSpendList != null && !outboundSpendList.isEmpty()){
            spendCommonList = new ArrayList<NetSpendCommonDto>();
            for(OutboundSpendDto spendDto : outboundSpendList){
                if(spendDto != null){
                    spendCommonList.add(new NetSpendCommonDto(spendDto));
                }
            }
        }
        return spendCommonList;
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

    public String getBillingDate() {
        return billingDate;
    }

    public void setBillingDate(String billingDate) {
        this.billingDate = billingDate;
    }

    public String getCarrierName() {
        return carrierName;
    }

    public void setCarrierName(String carrierName) {
        this.carrierName = carrierName;
    }

    public Double getNetDueAmount() {
        return netDueAmount;
    }

    public void setNetDueAmount(Double netDueAmount) {
        this.netDueAmount = netDueAmount;
    }

    public Long getCarrierId() {
        return carrierId;
    }

    public void setCarrierId(Long carrierId) {
        this.carrierId = carrierId;
    }
}
