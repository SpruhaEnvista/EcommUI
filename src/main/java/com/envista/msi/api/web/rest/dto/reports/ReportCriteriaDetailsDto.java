package com.envista.msi.api.web.rest.dto.reports;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Sujit kumar on 14/09/2017.
 */

@NamedStoredProcedureQueries({
        @NamedStoredProcedureQuery(
                name = "ReportCriteriaDetailsDto.getReportCriteriaDetails",
                procedureName = "SHP_RPT_CRITERIA_DETAILS_PROC",
                resultClasses = {ReportCriteriaDetailsDto.class},
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_shipper_ids", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_shipper_names", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_shipper_codes", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, name = "p_shipper_list", type = void.class)
                }
        )
})

@Entity
public class ReportCriteriaDetailsDto implements Serializable {
    @Id
    @Column(name = "RPT_DETAILS_ID")
    private Long rptDetailsId;

    @Column(name = "RPT_ID")
    private Long rptId;

    @Column(name = "COLUMN_NAME")
    private String columnName;

    @Column(name = "COLUMN_DATA_TYPE")
    private String columnDataType;

    @Column(name = "IS_SORTABLE")
    private Boolean isSortable;

    @Column(name = "IS_SELECT_CRITERIA")
    private Boolean isSelectCriteria;

    @Column(name = "SELECT_CLAUSE")
    private String selectClause;

    @Column(name = "QUERY")
    private String query;

    @Column(name = "CARRIER_ID")
    private Long carrierId;

    @Column(name = "SEQUENCE")
    private Integer sequence;

    @Column(name = "IS_EXCLUDABLE")
    private Boolean isExcludable;

    @Column(name = "IS_INNER")
    private Boolean isInner;

    @Column(name = "SPECIAL_TYPE_OPTIONS")
    private String specialTypeOption;

    @Column(name = "IS_DEFAULT_EXCLUDED")
    private Boolean isDefaultExecuted;

    @Column(name = "FORMAT")
    private String format;

    @Column(name = "AGGREGATE_TYPE")
    private String aggregateType;

    @Column(name = "SHOW_WHEN_NO_INCL_COLMNS")
    private Boolean showWhenNoIncludeColumns;

    public Long getRptDetailsId() {
        return rptDetailsId;
    }

    public void setRptDetailsId(Long rptDetailsId) {
        this.rptDetailsId = rptDetailsId;
    }

    public Long getRptId() {
        return rptId;
    }

    public void setRptId(Long rptId) {
        this.rptId = rptId;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public String getColumnDataType() {
        return columnDataType;
    }

    public void setColumnDataType(String columnDataType) {
        this.columnDataType = columnDataType;
    }

    public Boolean getSortable() {
        return isSortable;
    }

    public void setSortable(Boolean sortable) {
        isSortable = sortable;
    }

    public Boolean getSelectCriteria() {
        return isSelectCriteria;
    }

    public void setSelectCriteria(Boolean selectCriteria) {
        isSelectCriteria = selectCriteria;
    }

    public String getSelectClause() {
        return selectClause;
    }

    public void setSelectClause(String selectClause) {
        this.selectClause = selectClause;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public Long getCarrierId() {
        return carrierId;
    }

    public void setCarrierId(Long carrierId) {
        this.carrierId = carrierId;
    }

    public Integer getSequence() {
        return sequence;
    }

    public void setSequence(Integer sequence) {
        this.sequence = sequence;
    }

    public Boolean getExcludable() {
        return isExcludable;
    }

    public void setExcludable(Boolean excludable) {
        isExcludable = excludable;
    }

    public Boolean getInner() {
        return isInner;
    }

    public void setInner(Boolean inner) {
        isInner = inner;
    }

    public String getSpecialTypeOption() {
        return specialTypeOption;
    }

    public void setSpecialTypeOption(String specialTypeOption) {
        this.specialTypeOption = specialTypeOption;
    }

    public Boolean getDefaultExecuted() {
        return isDefaultExecuted;
    }

    public void setDefaultExecuted(Boolean defaultExecuted) {
        isDefaultExecuted = defaultExecuted;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public String getAggregateType() {
        return aggregateType;
    }

    public void setAggregateType(String aggregateType) {
        this.aggregateType = aggregateType;
    }

    public Boolean getShowWhenNoIncludeColumns() {
        return showWhenNoIncludeColumns;
    }

    public void setShowWhenNoIncludeColumns(Boolean showWhenNoIncludeColumns) {
        this.showWhenNoIncludeColumns = showWhenNoIncludeColumns;
    }
}
