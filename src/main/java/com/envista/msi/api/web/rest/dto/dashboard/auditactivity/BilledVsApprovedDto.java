package com.envista.msi.api.web.rest.dto.dashboard.auditactivity;

import com.envista.msi.api.domain.util.DashboardSroredProcParam;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by Sujit kumar on 13/02/2017.
 */

@NamedStoredProcedureQueries({
        @NamedStoredProcedureQuery(name = "BilledVsApprovedDto.getBilledVsApproved", procedureName = "SHP_DB_BILL_APPRVD_PROC",
                resultSetMappings = "BilledVsApprovedMapping",
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.BilledVsApprovedParams.DATE_TYPE_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.BilledVsApprovedParams.CURRENCY_ID_PARAM, type = Long.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.BilledVsApprovedParams.CUSTOMER_IDS_CSV_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.BilledVsApprovedParams.CARRIER_IDS_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.BilledVsApprovedParams.MODES_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.BilledVsApprovedParams.SERVICES_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.BilledVsApprovedParams.LANES_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.BilledVsApprovedParams.FROM_DATE_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.BilledVsApprovedParams.TO_DATE_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.BilledVsApprovedParams.MODE_NAMES_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, name = DashboardSroredProcParam.BilledVsApprovedParams.BILLED_VS_APPROVED_DATA_PARAM, type = Void.class)
                }),
        @NamedStoredProcedureQuery(name = "BilledVsApprovedDto.getBilledVsApprovedByMonth", procedureName = "SHP_DB_BILL_APPRVD_MNTH_PROC",
                resultSetMappings = "BilledVsApprovedByMonthMapping",
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.BilledVsApprovedParams.DATE_TYPE_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.BilledVsApprovedParams.CURRENCY_ID_PARAM, type = Long.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.BilledVsApprovedParams.CUSTOMER_IDS_CSV_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.BilledVsApprovedParams.CARRIER_IDS_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.BilledVsApprovedParams.MODES_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.BilledVsApprovedParams.SERVICES_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.BilledVsApprovedParams.LANES_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.BilledVsApprovedParams.FROM_DATE_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.BilledVsApprovedParams.TO_DATE_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.BilledVsApprovedParams.MODE_NAMES_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.BilledVsApprovedParams.BILLED_APPROVED_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, name = DashboardSroredProcParam.BilledVsApprovedParams.BILLED_VS_APPROVED_DATA_PARAM, type = Void.class)
                })
})

@SqlResultSetMappings({
        @SqlResultSetMapping(name = "BilledVsApprovedMapping", classes = {
                @ConstructorResult(targetClass = BilledVsApprovedDto.class,
                columns = {
                        @ColumnResult(name = "CARRIER_ID", type = Long.class),
                        @ColumnResult(name = "CARRIER_NAME", type = String.class),
                        @ColumnResult(name = "BILLED", type = Double.class),
                        @ColumnResult(name = "APPROVED", type = Double.class),
                        @ColumnResult(name = "RECOVERED", type = Double.class)
                })
        }),
        @SqlResultSetMapping(name = "BilledVsApprovedByMonthMapping", classes = {
                @ConstructorResult(targetClass = BilledVsApprovedDto.class,
                        columns = {
                                @ColumnResult(name = "BILL_DATE", type = Date.class),
                                @ColumnResult(name = "AMOUNT", type = Double.class)
                        })
        })
})

@Entity
public class BilledVsApprovedDto implements Serializable {
    @Id
    private Long id;

    @Column(name = "CARRIER_ID")
    private Long carrierId;

    @Column(name = "CARRIER_NAME")
    private String carrierName;

    @Column(name = "BILLED")
    private Double billedAmount;

    @Column(name = "APPROVED")
    private Double approvedAmount;

    @Column(name = "RECOVERED")
    private Double recoveredAmount;

    @Column(name = "BILL_DATE")
    private Date billDate;

    @Column(name = "AMOUNT")
    private Double amount;

    public BilledVsApprovedDto(){}

    public BilledVsApprovedDto(Long id, Long carrierId, String carrierName, Double billedAmount, Double approvedAmount, Double recoveredAmount) {
        this.id = id;
        this.carrierId = carrierId;
        this.carrierName = carrierName;
        this.billedAmount = billedAmount;
        this.approvedAmount = approvedAmount;
        this.recoveredAmount = recoveredAmount;
    }

    public BilledVsApprovedDto(Long carrierId, String carrierName, Double billedAmount, Double approvedAmount, Double recoveredAmount) {
        this.carrierId = carrierId;
        this.carrierName = carrierName;
        this.billedAmount = billedAmount;
        this.approvedAmount = approvedAmount;
        this.recoveredAmount = recoveredAmount;
    }

    public BilledVsApprovedDto(Date billDate, Double amount) {
        this.billDate = billDate;
        this.amount = amount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCarrierId() {
        return carrierId;
    }

    public void setCarrierId(Long carrierId) {
        this.carrierId = carrierId;
    }

    public String getCarrierName() {
        return carrierName;
    }

    public void setCarrierName(String carrierName) {
        this.carrierName = carrierName;
    }

    public Double getBilledAmount() {
        return billedAmount;
    }

    public void setBilledAmount(Double billedAmount) {
        this.billedAmount = billedAmount;
    }

    public Double getApprovedAmount() {
        return approvedAmount;
    }

    public void setApprovedAmount(Double approvedAmount) {
        this.approvedAmount = approvedAmount;
    }

    public Double getRecoveredAmount() {
        return recoveredAmount;
    }

    public void setRecoveredAmount(Double recoveredAmount) {
        this.recoveredAmount = recoveredAmount;
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
