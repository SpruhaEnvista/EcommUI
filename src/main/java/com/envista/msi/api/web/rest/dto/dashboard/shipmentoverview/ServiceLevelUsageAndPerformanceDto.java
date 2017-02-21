package com.envista.msi.api.web.rest.dto.dashboard.shipmentoverview;

import com.envista.msi.api.domain.util.DashboardSroredProcParam;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Sujit kumar on 15/02/2017.
 */
@NamedStoredProcedureQueries({
        @NamedStoredProcedureQuery(name = ServiceLevelUsageAndPerformanceDto.Config.StoredProcedureQueryName.SERVICE_LEVEL_USAGE_AND_PERFORMANCE,
                procedureName = ServiceLevelUsageAndPerformanceDto.Config.StoredProcedureName.SERVICE_LEVEL_USAGE_AND_PERFORMANCE,
            resultClasses = ServiceLevelUsageAndPerformanceDto.class,
            parameters = {
                    @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.ServiceLevelUsageAndPerformanceParams.DATE_TYPE_PARAM, type = String.class),
                    @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.ServiceLevelUsageAndPerformanceParams.CUSTOMER_IDS_CSV_PARAM, type = String.class),
                    @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.ServiceLevelUsageAndPerformanceParams.CARRIER_IDS_PARAM, type = String.class),
                    @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.ServiceLevelUsageAndPerformanceParams.MODES_PARAM, type = String.class),
                    @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.ServiceLevelUsageAndPerformanceParams.SERVICES_PARAM, type = String.class),
                    @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.ServiceLevelUsageAndPerformanceParams.LANES_PARAM, type = String.class),
                    @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.ServiceLevelUsageAndPerformanceParams.FROM_DATE_PARAM, type = String.class),
                    @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.ServiceLevelUsageAndPerformanceParams.TO_DATE_PARAM, type = String.class),
                    @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.ServiceLevelUsageAndPerformanceParams.ACCESSORIAL_NAME_PARAM, type = String.class),
                    @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardSroredProcParam.ServiceLevelUsageAndPerformanceParams.TOP_TEN_ACCESSORIAL_PARAM, type = Long.class),
                    @StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, name = DashboardSroredProcParam.ServiceLevelUsageAndPerformanceParams.SERVICE_LEVEL_USAGE_PERFORMANCE_DATA, type = Void.class)
        })
})

@Entity
public class ServiceLevelUsageAndPerformanceDto implements Serializable {
    @Id
    private Long id;

    @Column(name = "CARRIER_NAME")
    private String carrierName;

    @Column(name = "NDA_PERC")
    private Double ndaPercentage;

    @Column(name = "NDA_LATE_PERC")
    private Double ndaLatePercentage;

    @Column(name = "NDA_COUNT")
    private Double ndaCount;

    @Column(name = "DAY2_PERC")
    private Double day2Percentage;

    @Column(name = "DAY2_LATE_PERC")
    private Double day2LatePercentage;

    @Column(name = "DAY2_COUNT")
    private Double day2Count;

    @Column(name = "DAY3_PERC")
    private Double day3Percentage;

    @Column(name = "DAY3_LATE_PERC")
    private Double day3LatePercentage;

    @Column(name = "DAY3_COUNT")
    private Double day3Count;

    @Column(name = "GROUND_PERC")
    private Double groundPercentage;

    @Column(name = "GROUND_LATE_PERC")
    private Double groundLatePercentage;

    @Column(name = "GROUND_COUNT")
    private Double groundCount;

    @Column(name = "INTL_PERC")
    private Double internationalPercentage;

    @Column(name = "INTL_LATE_PERC")
    private Double internationalLatePercentage;

    @Column(name = "INTL_COUNT")
    private Double internationalCount;

    @Column(name = "POSTAL_INTG_PERC")
    private Double postalIntgPercentage;

    @Column(name = "POSTAL_INTG_LATE_PERC")
    private Double postalIntgLatePercentage;

    @Column(name = "POSTAL_INTG_COUNT")
    private Double postalIntgCount;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCarrierName() {
        return carrierName;
    }

    public void setCarrierName(String carrierName) {
        this.carrierName = carrierName;
    }

    public Double getNdaPercentage() {
        return ndaPercentage;
    }

    public void setNdaPercentage(Double ndaPercentage) {
        this.ndaPercentage = ndaPercentage;
    }

    public Double getNdaLatePercentage() {
        return ndaLatePercentage;
    }

    public void setNdaLatePercentage(Double ndaLatePercentage) {
        this.ndaLatePercentage = ndaLatePercentage;
    }

    public Double getNdaCount() {
        return ndaCount;
    }

    public void setNdaCount(Double ndaCount) {
        this.ndaCount = ndaCount;
    }

    public Double getDay2Percentage() {
        return day2Percentage;
    }

    public void setDay2Percentage(Double day2Percentage) {
        this.day2Percentage = day2Percentage;
    }

    public Double getDay2LatePercentage() {
        return day2LatePercentage;
    }

    public void setDay2LatePercentage(Double day2LatePercentage) {
        this.day2LatePercentage = day2LatePercentage;
    }

    public Double getDay2Count() {
        return day2Count;
    }

    public void setDay2Count(Double day2Count) {
        this.day2Count = day2Count;
    }

    public Double getDay3Percentage() {
        return day3Percentage;
    }

    public void setDay3Percentage(Double day3Percentage) {
        this.day3Percentage = day3Percentage;
    }

    public Double getDay3LatePercentage() {
        return day3LatePercentage;
    }

    public void setDay3LatePercentage(Double day3LatePercentage) {
        this.day3LatePercentage = day3LatePercentage;
    }

    public Double getDay3Count() {
        return day3Count;
    }

    public void setDay3Count(Double day3Count) {
        this.day3Count = day3Count;
    }

    public Double getGroundPercentage() {
        return groundPercentage;
    }

    public void setGroundPercentage(Double groundPercentage) {
        this.groundPercentage = groundPercentage;
    }

    public Double getGroundLatePercentage() {
        return groundLatePercentage;
    }

    public void setGroundLatePercentage(Double groundLatePercentage) {
        this.groundLatePercentage = groundLatePercentage;
    }

    public Double getGroundCount() {
        return groundCount;
    }

    public void setGroundCount(Double groundCount) {
        this.groundCount = groundCount;
    }

    public Double getInternationalPercentage() {
        return internationalPercentage;
    }

    public void setInternationalPercentage(Double internationalPercentage) {
        this.internationalPercentage = internationalPercentage;
    }

    public Double getInternationalLatePercentage() {
        return internationalLatePercentage;
    }

    public void setInternationalLatePercentage(Double internationalLatePercentage) {
        this.internationalLatePercentage = internationalLatePercentage;
    }

    public Double getInternationalCount() {
        return internationalCount;
    }

    public void setInternationalCount(Double internationalCount) {
        this.internationalCount = internationalCount;
    }

    public Double getPostalIntgPercentage() {
        return postalIntgPercentage;
    }

    public void setPostalIntgPercentage(Double postalIntgPercentage) {
        this.postalIntgPercentage = postalIntgPercentage;
    }

    public Double getPostalIntgLatePercentage() {
        return postalIntgLatePercentage;
    }

    public void setPostalIntgLatePercentage(Double postalIntgLatePercentage) {
        this.postalIntgLatePercentage = postalIntgLatePercentage;
    }

    public Double getPostalIntgCount() {
        return postalIntgCount;
    }

    public void setPostalIntgCount(Double postalIntgCount) {
        this.postalIntgCount = postalIntgCount;
    }

    public static class Config{
        static class StoredProcedureName{
            static final String SERVICE_LEVEL_USAGE_AND_PERFORMANCE = "SHP_DB_SERV_USAGE_PERF_PROC";
        }

        public static class StoredProcedureQueryName{
            public static final String SERVICE_LEVEL_USAGE_AND_PERFORMANCE = "ServiceLevelUsageAndPerformanceDto.getServiceLevelUsageAndPerformance";
        }
    }
}
