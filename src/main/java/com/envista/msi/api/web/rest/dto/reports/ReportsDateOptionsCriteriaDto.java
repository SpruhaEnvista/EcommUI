package com.envista.msi.api.web.rest.dto.reports;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Sujit kumar on 13/09/2017.
 */
@NamedStoredProcedureQueries({
        @NamedStoredProcedureQuery(
                name = "ReportsDateOptionsCriteriaDto.getDateOptionCriteriaByIds",
                procedureName = "SHP_RPT_DATE_OPTIONS_CRIT_PROC",
                resultSetMappings = {"ReportsDateOptionsCriteriaDto.getDateOptionCriteriaByIdsMapping"},
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_date_option_ids", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, name = "p_date_option_list", type = void.class)
                }
        )
})

@SqlResultSetMappings({
        @SqlResultSetMapping(
                name = "ReportsDateOptionsCriteriaDto.getDateOptionCriteriaByIdsMapping",
                classes = {
                        @ConstructorResult(
                                targetClass = ReportsDateOptionsCriteriaDto.class,
                                columns = {
                                        @ColumnResult(name = "RPT_DATE_OPTIONS_ID", type = Long.class),
                                        @ColumnResult(name = "DATE_CRITERIA", type = String.class)
                                }
                        )
                }
        )
})

@Entity
public class ReportsDateOptionsCriteriaDto implements Serializable {
    @Id
    @Column(name = "RPT_DATE_OPTIONS_ID")
    private long rptDateOptionsId;

    @Column(name = "DATE_CRITERIA")
    private String dateCriteria;

    public ReportsDateOptionsCriteriaDto() {
    }

    public ReportsDateOptionsCriteriaDto(long rptDateOptionsId, String dateCriteria) {
        this.rptDateOptionsId = rptDateOptionsId;
        this.dateCriteria = dateCriteria;
    }

    public long getRptDateOptionsId() {
        return rptDateOptionsId;
    }

    public void setRptDateOptionsId(long rptDateOptionsId) {
        this.rptDateOptionsId = rptDateOptionsId;
    }

    public String getDateCriteria() {
        return dateCriteria;
    }

    public void setDateCriteria(String dateCriteria) {
        this.dateCriteria = dateCriteria;
    }
}
