package com.envista.msi.api.web.rest.dto;

import com.envista.msi.api.web.rest.dto.reports.ReportDto;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Sujit kumar on 24/10/2017.
 */

@NamedStoredProcedureQueries({
        @NamedStoredProcedureQuery(
                name = "TogglesDto.getTogglesDetails",
                procedureName = "shp_toggles_proc",
                resultSetMappings = {"TogglesDto.getTogglesDetailsMapping"},
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, name = "togglesList", type = Void.class)
                }
        )
})

@SqlResultSetMappings({
        @SqlResultSetMapping(
                name = "TogglesDto.getTogglesDetailsMapping",
                classes = {
                        @ConstructorResult(
                                targetClass = TogglesDto.class,
                                columns = {
                                        @ColumnResult(name = "toggle_id", type = Long.class),
                                        @ColumnResult(name = "feature_name", type = String.class),
                                        @ColumnResult(name = "feature_enabled", type = Boolean.class),
                                        @ColumnResult(name = "strategy_id", type = String.class),
                                        @ColumnResult(name = "strategy_params", type = String.class)
                                }
                        )
                }
        )
})

@Entity
public class TogglesDto implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "toggle_id")
    private Long toggleId;

    @Column(name = "feature_name")
    private String featureName;

    @Column(name = "feature_enabled")
    private Boolean featureEnabled;

    @Column(name = "strategy_id")
    private String strategyId;

    @Column(name = "strategy_params")
    private String strategyParams;

    public Long getToggleId() {
        return toggleId;
    }

    public void setToggleId(Long toggleId) {
        this.toggleId = toggleId;
    }

    public String getFeatureName() {
        return featureName;
    }

    public void setFeatureName(String featureName) {
        this.featureName = featureName;
    }

    public Boolean getFeatureEnabled() {
        return featureEnabled;
    }

    public void setFeatureEnabled(Boolean featureEnabled) {
        this.featureEnabled = featureEnabled;
    }

    public String getStrategyId() {
        return strategyId;
    }

    public void setStrategyId(String strategyId) {
        this.strategyId = strategyId;
    }

    public String getStrategyParams() {
        return strategyParams;
    }

    public void setStrategyParams(String strategyParams) {
        this.strategyParams = strategyParams;
    }
    public TogglesDto(){

    }
    public TogglesDto(Long toggleId,String featureName,Boolean featureEnabled,String strategyId,String strategyParams){
        this.toggleId = toggleId;
        this.featureName = featureName;
        this.featureEnabled = featureEnabled;
        this.strategyId = strategyId;
        this.strategyParams = strategyParams;
    }
}
