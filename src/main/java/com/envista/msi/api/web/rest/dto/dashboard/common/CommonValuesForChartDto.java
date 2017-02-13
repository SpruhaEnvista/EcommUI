package com.envista.msi.api.web.rest.dto.dashboard.common;

import com.envista.msi.api.web.rest.dto.dashboard.accessorialspend.AccessorialSpendDto;
import com.envista.msi.api.web.rest.dto.dashboard.auditactivity.*;
import com.envista.msi.api.web.rest.dto.dashboard.netspend.NetSpendByCarrierDto;
import com.envista.msi.api.web.rest.dto.dashboard.taxspend.TaxSpendByCarrierDto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

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

    public CommonValuesForChartDto(InvoiceStatusCountDto invStatusCount){
        this.id = invStatusCount.getId();
        this.name = invStatusCount.getName();
        this.value = invStatusCount.getValue();
    }

    public CommonValuesForChartDto(InvoiceStatusAmountDto invStatusAmt){
        this.id = invStatusAmt.getId();
        this.name = invStatusAmt.getName();
        this.value = invStatusAmt.getValue();
    }

    public CommonValuesForChartDto(InvoiceMethodScoreDto invoiceMethodScore){
        this.id = invoiceMethodScore.getId();
        this.name = invoiceMethodScore.getName();
        this.value = invoiceMethodScore.getValue();
    }

    public CommonValuesForChartDto(OrderMatchDto orderMatch){
        this.id = orderMatch.getId();
        this.name = orderMatch.getName();
        this.value = orderMatch.getValue();
    }

    public CommonValuesForChartDto(RecoveryAdjustmentDto recoveryAdjustment){
        this.id = recoveryAdjustment.getId();
        this.name = recoveryAdjustment.getName();
        this.value = recoveryAdjustment.getValue();
    }

    public CommonValuesForChartDto(RecoveryServiceDto recoveryServ){
        this.id = recoveryServ.getId();
        this.name = recoveryServ.getName();
        this.value = recoveryServ.getValue();
    }

    public static List<CommonValuesForChartDto> buildRecoveryAdjustmentListToCommonValueForChartList(List<RecoveryAdjustmentDto> recoveryAdjustmentList){
        if(recoveryAdjustmentList == null || recoveryAdjustmentList.isEmpty()) return null;

        List<CommonValuesForChartDto> commonValuesForChartList = new ArrayList<>();
        for(RecoveryAdjustmentDto recoveryAdjustment : recoveryAdjustmentList){
            if(recoveryAdjustment != null){
                commonValuesForChartList.add(new CommonValuesForChartDto(recoveryAdjustment));
            }
        }
        return commonValuesForChartList;
    }

    public static List<CommonValuesForChartDto> buildRecoveryServiceListToCommonValueForChartList(List<RecoveryServiceDto> recoveryServiceList){
        if(recoveryServiceList == null || recoveryServiceList.isEmpty()) return null;

        List<CommonValuesForChartDto> commonValuesForChartList = new ArrayList<>();
        for(RecoveryServiceDto recoveryServ : recoveryServiceList){
            if(recoveryServ != null){
                commonValuesForChartList.add(new CommonValuesForChartDto(recoveryServ));
            }
        }
        return commonValuesForChartList;
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
