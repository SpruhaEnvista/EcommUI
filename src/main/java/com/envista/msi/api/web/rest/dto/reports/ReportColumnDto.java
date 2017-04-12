package com.envista.msi.api.web.rest.dto.reports;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Siddhant on 03/04/2017.
 */

@NamedStoredProcedureQueries({
        @NamedStoredProcedureQuery(name = "ReportCriteriaDto.getReportCriteria", procedureName = "shp_rpt_criteria_proc",
                resultSetMappings = "CriteriaCol",
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_user_id", type = Long.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_rpt_id", type = Long.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_carrier_ids", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, name = "get_criteria_info", type = Void.class)
        }),
        @NamedStoredProcedureQuery(name = "ReportCriteriaDto.getSavedIncludeExcludeSortColByName", procedureName = "shp_rpt_save_excl_byname__proc",
                resultSetMappings = "InclExclSortCol",
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_user_id", type = Long.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_rpt_id", type = Long.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_carriers", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, name = "p_refcur_excl_incl_info", type = Void.class)
        }),
        @NamedStoredProcedureQuery(name = "ReportCriteriaDto.getSavedIncludeExcludeColBySequence", procedureName = "shp_rpt_save_incl_byseque_proc",
                resultSetMappings = "InclExclSortCol",
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_user_id", type = Long.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_rpt_id", type = Long.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_carriers", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, name = "p_refcur_excl_incl_info", type = Void.class)
        }),
        @NamedStoredProcedureQuery(name = "ReportCriteriaDto.getDefaultInclExclCol", procedureName = "shp_rpt_default_incl_col_proc",
                resultSetMappings = "DefaultInclExclCol",
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_savedSchedRptId", type = Long.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_rptid", type = Long.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_createUser", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, name = "get_default_incl_col", type = Void.class)
                })
})
@SqlResultSetMappings({
        @SqlResultSetMapping(name = "CriteriaCol", classes = {
                @ConstructorResult(
                        targetClass = ReportColumnDto.class,
                        columns = {
                                @ColumnResult(name = "sno", type= Integer.class),
                                @ColumnResult(name = "rpt_details_id", type = Long.class),
                                @ColumnResult(name = "rpt_id", type = Long.class),
                                @ColumnResult(name = "column_name", type = String.class),
                                @ColumnResult(name = "column_data_type", type = String.class),
                                @ColumnResult(name = "is_sortable", type = Boolean.class),
                                @ColumnResult(name = "is_select_criteria", type = Boolean.class),
                                @ColumnResult(name = "select_clause", type = String.class),
                                @ColumnResult(name = "query", type = String.class),
                                @ColumnResult(name = "carrier_id", type = String.class),
                                @ColumnResult(name = "sequence", type = Long.class),
                                @ColumnResult(name = "is_excludable", type = Boolean.class),
                                @ColumnResult(name = "is_inner", type = Boolean.class),
                                @ColumnResult(name = "special_type_options", type = String.class),
                                @ColumnResult(name = "is_default_excluded", type = Boolean.class),
                                @ColumnResult(name = "aggregate_type", type = String.class),
                                @ColumnResult(name = "format", type = String.class),
                                @ColumnResult(name = "show_when_no_incl_colmns", type = String.class),
                                @ColumnResult(name = "assign_operator", type = String.class),
                                @ColumnResult(name = "value", type = String.class),
                                @ColumnResult(name = "selected", type = Boolean.class)
                        })
        }),
        @SqlResultSetMapping(name = "InclExclSortCol", classes = {
                @ConstructorResult(
                        targetClass = ReportColumnDto.class,
                        columns = {
                                @ColumnResult(name = "rpt_details_id", type = Long.class),
                                @ColumnResult(name = "rpt_id", type = Long.class),
                                @ColumnResult(name = "column_name", type = String.class),
                                @ColumnResult(name = "column_data_type", type = String.class),
                                @ColumnResult(name = "is_sortable", type = Boolean.class),
                                @ColumnResult(name = "is_select_criteria", type = Boolean.class),
                                @ColumnResult(name = "select_clause", type = String.class),
                                @ColumnResult(name = "query", type = String.class),
                                @ColumnResult(name = "carrier_id", type = String.class),
                                @ColumnResult(name = "sequence", type = Long.class),
                                @ColumnResult(name = "is_excludable", type = Boolean.class),
                                @ColumnResult(name = "is_inner", type = Boolean.class),
                                @ColumnResult(name = "special_type_options", type = String.class),
                                @ColumnResult(name = "is_default_excluded", type = Boolean.class),
                                @ColumnResult(name = "aggregate_type", type = String.class),
                                @ColumnResult(name = "format", type = String.class),
                                @ColumnResult(name = "show_when_no_incl_colmns", type = String.class),
                                @ColumnResult(name = "is_sub_total", type = Boolean.class),
                                @ColumnResult(name = "in_sort", type = Boolean.class),
                                @ColumnResult(name = "group_by_col", type = Boolean.class)
                        })
        }),
        @SqlResultSetMapping(name = "DefaultInclExclCol", classes = {
                @ConstructorResult(
                        targetClass = ReportColumnDto.class,
                        columns = {

                                @ColumnResult(name = "SAVED_SCHED_RPT_ID", type = Long.class),
                                @ColumnResult(name = "RPT_DETAILS_ID", type = Long.class),
                                @ColumnResult(name = "CREATE_USER", type = String.class),
                        })
        })
})
@Entity
public class ReportColumnDto implements Serializable {

    @Id
    @Column(name="rpt_details_id")
    private Long rptDetailsId;

    @Column(name="rpt_id")
    private Long rptId;

    @Column(name="column_name")
    private String columnName;

    @Column(name="column_data_type")
    private String columnDataType;

    @Column(name="is_sortable")
    private Boolean sortColumn;

    @Column(name="is_select_criteria")
    private Boolean selectCriteria;

    @Column(name="select_clause")
    private String selectCluse;

    @Column(name="query")
    private String query;

    @Column(name="carrier_id")
    private String carrierId;

    @Column(name="sequence")
    private Long sequence;

    @Column(name="is_excludable")
    private Boolean excludable;

    @Column(name="is_inner")
    private Boolean inner;

    @Column(name="special_type_options")
    private String specialTypeOptions;

    @Column(name="is_default_excluded")
    private Boolean defaultExcluded;

    @Column(name="aggregate_type")
    private String aggregateType;

    @Column(name="format")
    private String format;

    @Column(name="show_when_no_incl_colmns")
    private String showWhenNoInclColmns;

    @Column(name="assign_operator")
    private String assignOperator;

    @Column(name="value")
    private String value;

    @Column(name="selected")
    private Boolean selected;

    @Column(name="is_sub_total")
    private Boolean subTotal;

    @Column(name="sno")
    private Integer sno;

    @Column(name="in_sort")
    private Boolean inSort; // populate only for savedschedrtptId

    @Column(name="group_by_col")
    private Boolean groupByCol ; // populate only for savedschedrtptId based on groupbyCol column in save_sched_sorT_tb

    @Column(name="SAVED_SCHED_RPT_ID")
    private Long savedSchedId;

    @Column(name="CREATE_USER")
    private String createUser;

    public ReportColumnDto() {  }

    public ReportColumnDto(Long savedSchedId, Long rptDetailsId, String createUser) {
        this.rptDetailsId = rptDetailsId;
        this.savedSchedId = savedSchedId;
        this.createUser = createUser;
    }

    public ReportColumnDto(Integer sno, Long rptDetailsId, Long rptId, String columnName, String columnDataType, Boolean sortColumn, Boolean selectCriteria, String selectCluse, String query, String carrierId, Long sequence, Boolean excludable, Boolean inner, String specialTypeOptions, Boolean defaultExcluded, String aggregateType, String format, String showWhenNoInclColmns, String assignOperator, String value, Boolean selected) {
        this.rptDetailsId = rptDetailsId;
        this.rptId = rptId;
        this.columnName = columnName;
        this.columnDataType = columnDataType;
        this.sortColumn = sortColumn;
        this.selectCriteria = selectCriteria;
        this.selectCluse = selectCluse;
        this.query = query;
        this.carrierId = carrierId;
        this.sequence = sequence;
        this.excludable = excludable;
        this.inner = inner;
        this.specialTypeOptions = specialTypeOptions;
        this.defaultExcluded = defaultExcluded;
        this.aggregateType = aggregateType;
        this.format = format;
        this.showWhenNoInclColmns = showWhenNoInclColmns;
        this.assignOperator = assignOperator;
        this.value = value;
        this.selected = selected;
        this.sno=sno;
    }

    public ReportColumnDto(Long rptDetailsId, Long rptId, String columnName, String columnDataType, Boolean sortColumn, Boolean selectCriteria, String selectCluse, String query, String carrierId, Long sequence, Boolean excludable, Boolean inner, String specialTypeOptions, Boolean defaultExcluded, String aggregateType, String format, String showWhenNoInclColmns, Boolean subTotal,Boolean inSort, Boolean groupByCol) {
        this.rptDetailsId = rptDetailsId;
        this.rptId = rptId;
        this.columnName = columnName;
        this.columnDataType = columnDataType;
        this.sortColumn = sortColumn;
        this.selectCriteria = selectCriteria;
        this.selectCluse = selectCluse;
        this.query = query;
        this.carrierId = carrierId;
        this.sequence = sequence;
        this.excludable = excludable;
        this.inner = inner;
        this.specialTypeOptions = specialTypeOptions;
        this.defaultExcluded = defaultExcluded;
        this.aggregateType = aggregateType;
        this.format = format;
        this.showWhenNoInclColmns = showWhenNoInclColmns;
        this.subTotal=subTotal;
        this.inSort=inSort;
        this.groupByCol=groupByCol;
    }

    public Long getRptDetailsId() { return rptDetailsId;  }

    public void setRptDetailsId(Long rptDetailsId) { this.rptDetailsId = rptDetailsId;  }

    public Long getRptId() { return rptId;}

    public void setRptId(Long rptId) { this.rptId = rptId; }

    public String getColumnName() {  return columnName; }

    public void setColumnName(String columnName) {  this.columnName = columnName; }

    public String getColumnDataType() {  return columnDataType;  }

    public void setColumnDataType(String columnDataType) { this.columnDataType = columnDataType; }

    public Boolean getSortColumn() { return sortColumn; }

    public void setSortColumn(Boolean sortColumn) { this.sortColumn = sortColumn; }

    public Boolean getSelectCriteria() { return selectCriteria;  }

    public void setSelectCriteria(Boolean selectCriteria) { this.selectCriteria = selectCriteria;  }

    public String getSelectCluse() { return selectCluse; }

    public void setSelectCluse(String selectCluse) { this.selectCluse = selectCluse;  }

    public String getQuery() { return query; }

    public void setQuery(String query) { this.query = query; }
    public String getCarrierId() { return carrierId; }

    public void setCarrierId(String carrierId) { this.carrierId = carrierId; }

    public Long getSequence() { return sequence; }

    public void setSequence(Long sequence) { this.sequence = sequence; }

    public Boolean getExcludable() { return excludable; }

    public void setExcludable(Boolean excludable) { this.excludable = excludable; }

    public Boolean getInner() { return inner; }

    public void setInner(Boolean inner) { this.inner = inner; }

    public String getSpecialTypeOptions() { return specialTypeOptions; }

    public void setSpecialTypeOptions(String specialTypeOptions) { this.specialTypeOptions = specialTypeOptions;  }

    public Boolean getDefaultExcluded() { return defaultExcluded; }

    public void setDefaultExcluded(Boolean defaultExcluded) {  this.defaultExcluded = defaultExcluded; }

    public String getAggregateType() { return aggregateType; }

    public void setAggregateType(String aggregateType) { this.aggregateType = aggregateType;  }

    public String getFormat() {  return format;  }

    public void setFormat(String format) { this.format = format; }

    public String getShowWhenNoInclColmns() { return showWhenNoInclColmns;  }

    public void setShowWhenNoInclColmns(String showWhenNoInclColmns) {  this.showWhenNoInclColmns = showWhenNoInclColmns;  }

    public String getAssignOperator() { return assignOperator; }

    public void setAssignOperator(String assignOperator) { this.assignOperator = assignOperator; }

    public String getValue() { return value; }

    public void setValue(String value) { this.value = value; }

    public Boolean getSelected() {  return selected; }

    public void setSelected(Boolean selected) { this.selected = selected; }

    public Boolean getSubTotal() { return subTotal; }

    public void setSubTotal(Boolean subTotal) { this.subTotal = subTotal; }

    public Integer getSno() { return sno; }

    public void setSno(Integer sno) { this.sno = sno; }

    public Boolean isInSort() { return inSort; }

    public void setInSort(Boolean inSort) { this.inSort = inSort; }

    public Boolean isGroupByCol() {  return groupByCol; }

    public void setGroupByCol(Boolean groupByCol) {  this.groupByCol = groupByCol; }

    public Long getSavedSchedId() { return savedSchedId; }

    public void setSavedSchedId(Long savedSchedId) { this.savedSchedId = savedSchedId; }

    public String getCreateUser() { return createUser; }

    public void setCreateUser(String createUser) { this.createUser = createUser; }
}
