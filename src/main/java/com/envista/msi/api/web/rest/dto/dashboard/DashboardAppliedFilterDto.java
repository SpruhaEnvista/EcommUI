package com.envista.msi.api.web.rest.dto.dashboard;

import com.envista.msi.api.domain.util.DashboardStoredProcParam;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Sarvesh on 1/18/2017.
 */

@NamedStoredProcedureQueries({
        @NamedStoredProcedureQuery(name = "DashAppliedFilterTb.getUserAppliedFilter", procedureName = "SHP_DB_GET_APPLIED_FILTER_PROC",
                resultClasses = DashboardAppliedFilterDto.class,
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_user_id", type = Long.class),
                        @StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, name = "applied_filter_data", type = void.class)
                }),
        @NamedStoredProcedureQuery(name = "DashboardAppliedFilterDto.saveAppliedFilter", procedureName = "shp_db_ins_applied_filter_proc",
                resultClasses = DashboardAppliedFilterDto.class,
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.AppliedFilterParam.CUSTOMER_IDS_CSV_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.AppliedFilterParam.CARRIER_IDS_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.AppliedFilterParam.FROM_DATE_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.AppliedFilterParam.TO_DATE_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.AppliedFilterParam.MODES_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.AppliedFilterParam.SERVICES_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.AppliedFilterParam.DATE_TYPE_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.AppliedFilterParam.LANES_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.AppliedFilterParam.USER_ID_PARAM, type = Long.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.AppliedFilterParam.CONVERTED_CURRENCY_ID_PARAM, type = Long.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.AppliedFilterParam.WEIGHT_UNIT_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.AppliedFilterParam.CURRENCY_CODE_PARAM, type = String.class),
                })
})

@Entity
public class DashboardAppliedFilterDto implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name="USER_ID")
    private long loginUserId;

    @Column(name="CUSTOMER_ID")
    private String customerIds;

    @Column(name="CARRIER_ID")
    private String carrierIds;

    @Column(name="FROM_DATE")
    private String fromDate;

    @Column(name="TO_DATE")
    private String toDate;

    private String modes;

    private String services;

    @Column(name="DATE_TYPE")
    private String dateType;

    private String lanes;

    @Column(name="CURRENCY_ID")
    private String currencyId;

    @Column(name="WEIGHT_UNIT")
    private String weightUnit;

    @Column(name="CURRENCY_CODE")
    private String currencyCode;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getCustomerIds() {
        return customerIds;
    }

    public void setCustomerIds(String customerIds) {
        this.customerIds = customerIds;
    }

    public String getCarrierIds() {
        return carrierIds;
    }

    public void setCarrierIds(String carrierIds) {
        this.carrierIds = carrierIds;
    }

    public String getFromDate() {
        return fromDate;
    }

    public void setFromDate(String fromDate) {
        this.fromDate = fromDate;
    }

    public String getToDate() {
        return toDate;
    }

    public void setToDate(String toDate) {
        this.toDate = toDate;
    }

    public String getModes() {
        return modes;
    }

    public void setModes(String modes) {
        this.modes = modes;
    }

    public String getServices() {
        return services;
    }

    public void setServices(String services) {
        this.services = services;
    }

    public String getDateType() {
        return dateType;
    }

    public void setDateType(String dateType) {
        this.dateType = dateType;
    }

    public String getLanes() {
        return lanes;
    }

    public void setLanes(String lanes) {
        this.lanes = lanes;
    }

    public long getLoginUserId() {
        return loginUserId;
    }

    public void setLoginUserId(long loginUserId) {
        this.loginUserId = loginUserId;
    }

    public String getCurrencyId() {
        return currencyId;
    }

    public void setCurrencyId(String currencyId) {
        this.currencyId = currencyId;
    }

    public String getWeightUnit() {
        return weightUnit;
    }

    public void setWeightUnit(String weightUnit) {
        this.weightUnit = weightUnit;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    /* private String shipperGroupIdsCSV;
    private String modeNames;
    private String serviceNames;
    private String shipperIdsCSV;
    private FreightDashboardsDataBean freightDashboardsDataBean;
    private String invoiceStatusId;
    private String SpendByMonth;
    private String invoiceMethod;
    private String orderMatch;
    private String tax;
    private String accDesc;
    private String service;
    private String scoreType;
    private String billedVsApproved;
    private String deliveryFlag;
    private String accessorialName;
    private String dashletteName;
    private String openInvoiceStatusIds;
    private String shipperCity;
    private String receiverCity;
    private String feedBackType;
    private String feedBackDescription;
    private String feedBackTabName;
    private String reportForDashlette;
    private String originalFromDate;
    private String originalToDate;
    private boolean isLineItemReport;
    private long convertCurrencyId;
    private String convertWeightUnit;
    private String deliveryFlagDesc;
    private String locale;
    private String convertCurrencyCode;
    private String boundType;
    private String originalDateType;
    private boolean handleParcelServices;
    private boolean parcelDashlettes;*/

}
