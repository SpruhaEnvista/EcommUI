package com.envista.msi.api.web.rest.dto;

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
                })
})

@Entity
public class DashboardAppliedFilterDto implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "JSESSION_ID",unique = true)
    private String jSessionId;

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

    @Column(name="CUSTOMER_GROUP_NAME")
    private String customerGroupName;

    @Column(name="LOGINUSER_ID")
    private long loginUserId;

    @Column(name="DASHLET_CATE")
    private  String dashletCategory;

    @Column(name="USER_NAME")
    private String  userName;

    @Column(name="CURRENCY_ID")
    private String currencyId;

    @Column(name="WEIGHT_UNIT")
    private String weightUnit;

    @Column(name="SHIPPER_GROUP_ID")
    private String shipperGroupId;

    @Column(name="CURRENCY_CODE")
    private String currencyCode;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getjSessionId() {
        return jSessionId;
    }

    public void setjSessionId(String jSessionId) {
        this.jSessionId = jSessionId;
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

    public String getCustomerGroupName() {
        return customerGroupName;
    }

    public void setCustomerGroupName(String customerGroupName) {
        this.customerGroupName = customerGroupName;
    }

    public long getLoginUserId() {
        return loginUserId;
    }

    public void setLoginUserId(long loginUserId) {
        this.loginUserId = loginUserId;
    }

    public String getDashletCategory() {
        return dashletCategory;
    }

    public void setDashletCategory(String dashletCategory) {
        this.dashletCategory = dashletCategory;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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

    public String getShipperGroupId() {
        return shipperGroupId;
    }

    public void setShipperGroupId(String shipperGroupId) {
        this.shipperGroupId = shipperGroupId;
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
