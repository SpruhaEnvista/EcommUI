package com.envista.msi.api.web.rest.dto.dashboard.filter;

import com.envista.msi.api.domain.util.DashboardStoredProcParam;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by Sujit kumar on 07/04/2017.
 */

@NamedStoredProcedureQueries({
        @NamedStoredProcedureQuery(
                name = DashSavedFilterDto.Config.StoredProcedureQueryName.DELETE_SAVED_FILTER,
                procedureName = DashSavedFilterDto.Config.StoredProcedureName.DELETE_SAVED_FILTER,
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.DashSavedFilterParam.FILTER_ID_PARAM, type = Long.class)
                }
        ),
        @NamedStoredProcedureQuery(
                name = DashSavedFilterDto.Config.StoredProcedureQueryName.UPDATE_SAVED_FILTER,
                procedureName = DashSavedFilterDto.Config.StoredProcedureName.UPDATE_SAVED_FILTER,
                resultClasses = {DashSavedFilterDto.class},
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.DashSavedFilterParam.FILTER_ID_PARAM, type = Long.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.DashSavedFilterParam.FILTER_NAME_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.DashSavedFilterParam.USER_ID_PARAM, type = Long.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.DashSavedFilterParam.CUSTOMER_IDS_CSV_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.DashSavedFilterParam.CARRIER_IDS_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.DashSavedFilterParam.DATE_TYPE_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.DashSavedFilterParam.FROM_DATE_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.DashSavedFilterParam.TO_DATE_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.DashSavedFilterParam.MODES_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.DashSavedFilterParam.SERVICES_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.DashSavedFilterParam.CONVERTED_CURRENCY_ID_PARAM, type = Long.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.DashSavedFilterParam.WEIGHT_UNIT_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.DashSavedFilterParam.SHIPPER_CITIES_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.DashSavedFilterParam.SHIPPER_STATES_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.DashSavedFilterParam.SHIPPER_COUNTRIES_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.DashSavedFilterParam.RECEIVER_CITIES_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.DashSavedFilterParam.RECEIVER_STATES_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.DashSavedFilterParam.RECEIVER_COUNTRIES_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.DashSavedFilterParam.DEFAULT_FILTER_PARAM, type = Integer.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.DashSavedFilterParam.CREATE_DATE_PARAM, type = Date.class),
                        @StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, name = DashboardStoredProcParam.DashSavedFilterParam.FILTER_DATA_PARAM, type = Void.class)
                }
        ),
        @NamedStoredProcedureQuery(
                name = DashSavedFilterDto.Config.StoredProcedureQueryName.MAKE_DEFAULT_FILTER,
                procedureName = DashSavedFilterDto.Config.StoredProcedureName.MAKE_DEFAULT_FILTER,
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.DashSavedFilterParam.FILTER_ID_PARAM, type = Long.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.DashSavedFilterParam.USER_ID_PARAM, type = Long.class)
                }
        ),
        @NamedStoredProcedureQuery(
                name = DashSavedFilterDto.Config.StoredProcedureQueryName.GET_FILTER_BY_USER,
                procedureName = DashSavedFilterDto.Config.StoredProcedureName.GET_FILTER_BY_USER,
                resultClasses = {DashSavedFilterDto.class},
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.DashSavedFilterParam.USER_ID_PARAM, type = Long.class),
                        @StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, name = DashboardStoredProcParam.DashSavedFilterParam.FILTER_DATA_PARAM, type = Void.class)
                }
        ),
        @NamedStoredProcedureQuery(
                name = DashSavedFilterDto.Config.StoredProcedureQueryName.GET_FILTER_BY_ID,
                procedureName = DashSavedFilterDto.Config.StoredProcedureName.GET_FILTER_BY_ID,
                resultClasses = {DashSavedFilterDto.class},
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.DashSavedFilterParam.FILTER_ID_PARAM, type = Long.class),
                        @StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, name = DashboardStoredProcParam.DashSavedFilterParam.FILTER_DATA_PARAM, type = Void.class)
                }
        ),
        @NamedStoredProcedureQuery(
                name = DashSavedFilterDto.Config.StoredProcedureQueryName.GET_USER_FILTER_NY_FILTER_NAME,
                procedureName = DashSavedFilterDto.Config.StoredProcedureName.GET_USER_FILTER_NY_FILTER_NAME,
                resultClasses = {DashSavedFilterDto.class},
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.DashSavedFilterParam.USER_ID_PARAM, type = Long.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.DashSavedFilterParam.FILTER_NAME_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, name = DashboardStoredProcParam.DashSavedFilterParam.FILTER_DATA_PARAM, type = Void.class)
                }
        )
})
@Entity
public class DashSavedFilterDto implements Serializable {
    @Id
    @Column(name = "FILTER_ID")
    private Long filterId;

    @Column(name = "FILTER_NAME")
    private String filterName;

    @Column(name = "USER_ID")
    private Long userId;

    @Column(name = "CUSTOMER_ID")
    private String customerIds;

    @Column(name = "CARRIER_ID")
    private String carrierIds;

    @Column(name = "DATE_TYPE")
    private String dateType;

    @Column(name = "FROM_DATE")
    private String fromDate;

    @Column(name = "TO_DATE")
    private String toDate;

    @Column(name = "MODES")
    private String modes;

    @Column(name = "SERVICES")
    private String services;

    @Column(name = "CURRENCY_ID")
    private Long currencyId;

    @Column(name = "WEIGHT_UNIT")
    private String weightUnit;

    @Column(name = "SHIPPER_CITIES")
    private String shipperCities;

    @Column(name = "SHIPPER_STATES")
    private String shipperStates;

    @Column(name = "SHIPPER_COUNTRIES")
    private String shipperCountries;

    @Column(name = "RECEIVER_CITIES")
    private String receiverCities;

    @Column(name = "RECEIVER_STATES")
    private String receiverStates;

    @Column(name = "RECEIVER_COUNTRIES")
    private String receiverCountries;

    @Column(name = "DEFAULT_FILTER")
    private Integer defaultFilter;

    @Column(name = "CREATE_DATE")
    private Date createDate;

    public Long getFilterId() {
        return filterId;
    }

    public void setFilterId(Long filterId) {
        this.filterId = filterId;
    }

    public String getFilterName() {
        return filterName;
    }

    public void setFilterName(String filterName) {
        this.filterName = filterName;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
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

    public String getDateType() {
        return dateType;
    }

    public void setDateType(String dateType) {
        this.dateType = dateType;
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

    public Long getCurrencyId() {
        return currencyId;
    }

    public void setCurrencyId(Long currencyId) {
        this.currencyId = currencyId;
    }

    public String getWeightUnit() {
        return weightUnit;
    }

    public void setWeightUnit(String weightUnit) {
        this.weightUnit = weightUnit;
    }

    public String getShipperCities() {
        return shipperCities;
    }

    public void setShipperCities(String shipperCities) {
        this.shipperCities = shipperCities;
    }

    public String getShipperStates() {
        return shipperStates;
    }

    public void setShipperStates(String shipperStates) {
        this.shipperStates = shipperStates;
    }

    public String getShipperCountries() {
        return shipperCountries;
    }

    public void setShipperCountries(String shipperCountries) {
        this.shipperCountries = shipperCountries;
    }

    public String getReceiverCities() {
        return receiverCities;
    }

    public void setReceiverCities(String receiverCities) {
        this.receiverCities = receiverCities;
    }

    public String getReceiverStates() {
        return receiverStates;
    }

    public void setReceiverStates(String receiverStates) {
        this.receiverStates = receiverStates;
    }

    public String getReceiverCountries() {
        return receiverCountries;
    }

    public void setReceiverCountries(String receiverCountries) {
        this.receiverCountries = receiverCountries;
    }

    public Integer getDefaultFilter() {
        return defaultFilter;
    }

    public void setDefaultFilter(Integer defaultFilter) {
        this.defaultFilter = defaultFilter;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public static class Config{
        static class StoredProcedureName{
            static final String DELETE_SAVED_FILTER = "SHP_DB_DEL_SAVED_FILTER_PROC";
            static final String UPDATE_SAVED_FILTER = "SHP_DB_UPDATE_SAVED_FILTR_PROC";
            static final String MAKE_DEFAULT_FILTER = "SHP_DB_SET_DEFAULT_FILTER_PROC";
            static final String GET_FILTER_BY_USER = "SHP_SAVED_FILTER_BY_USER_PROC";
            static final String GET_FILTER_BY_ID = "SHP_SAVED_FILTER_BY_ID_PROC";
            static final String GET_USER_FILTER_NY_FILTER_NAME = "SHP_DB_USER_FLTR_BY_NAME_PROC";
        }

        public static class StoredProcedureQueryName{
            public static final String DELETE_SAVED_FILTER = "DashSavedFilterDto.DeleteSavedFilter";
            public static final String UPDATE_SAVED_FILTER = "DashSavedFilterDto.UpdateSavedFilter";
            public static final String MAKE_DEFAULT_FILTER = "DashSavedFilterDto.MakeDefaultFilter";
            public static final String GET_FILTER_BY_USER = "DashSavedFilterDto.getSavedFilterByUser";
            public static final String GET_FILTER_BY_ID = "DashSavedFilterDto.getSavedFilterById";
            public static final String GET_USER_FILTER_NY_FILTER_NAME = "DashSavedFilterDto.getFilterByUser";
        }
    }
}
