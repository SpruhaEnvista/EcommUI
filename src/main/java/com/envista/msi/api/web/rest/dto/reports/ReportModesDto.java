package com.envista.msi.api.web.rest.dto.reports;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Siddhant on 24/02/2017.
 */
@NamedStoredProcedureQueries({
        @NamedStoredProcedureQuery(name = "ReportModes.getReportModeList", procedureName = "SHP_RPT_MODES_PROC",
                resultSetMappings = "ReportModes",
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_user_id", type = Long.class),
                        @StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, name = "p_all_report_mode", type = Void.class)
                })
})
@SqlResultSetMappings({
        @SqlResultSetMapping(name = "ReportModes", classes = {
                @ConstructorResult(
                        targetClass = ReportModesDto.class,
                        columns = {
                                @ColumnResult(name = "rpt_id", type = Long.class),
                                @ColumnResult(name = "report_name", type = String.class),
                                @ColumnResult(name = "category", type = Integer.class),
                                @ColumnResult(name = "isconsolidated", type = Integer.class),
                                @ColumnResult(name = "rpt_grp_id", type = Integer.class),
                                @ColumnResult(name = "new_rpt_grp_id", type = Integer.class),
                                @ColumnResult(name = "group_name", type = String.class),
                                @ColumnResult(name = "group_under", type = Integer.class),
                                @ColumnResult(name = "is_supports_carriers", type = Boolean.class),
                                @ColumnResult(name = "group_under_name", type = String.class),
                                @ColumnResult(name = "rpt_descr", type = String.class),
                                @ColumnResult(name = "is_fav", type = Boolean.class),
                                @ColumnResult(name = "is_popular_report", type = Boolean.class),
                                @ColumnResult(name = "show_criteria_tab", type = Integer.class)

                        }
                )
        })
})
@Entity
public class ReportModesDto implements Serializable {

    @Id
    @Column(name = "rpt_id")
    private Long rptId;

    @Column(name = "report_name")
    private String reportFileName;

    @Column(name = "category")
    private Integer category;

    @Column(name = "isconsolidated")
    private Integer isConsolidated;

    @Column(name = "rpt_grp_id")
    private Integer reportGroupId;

    @Column(name = "new_rpt_grp_id")
    private Integer newRptGrpId;

    @Column(name = "group_name")
    private String groupName;

    @Column(name = "group_under")
    private Integer groupUnder;

    @Column(name = "is_supports_carriers")
    private Boolean isSupportsCarriers;

    @Column(name = "group_under_name")
    private String groupUnderName;

    @Column(name = "rpt_descr")
    private String reportDescr;


    @Column(name = "is_fav")
    private boolean isFavourite;

    @Column(name="is_popular_report")
    private boolean isPopularReport;

    @Column(name = "show_criteria_tab")
    private Integer showCriteriaTab;


    public ReportModesDto() {  }

    public ReportModesDto(Long rptId, String reportFileName, Integer category, Integer isConsolidated, Integer reportGroupId,Integer newRptGrpId,String groupName,
                             Integer groupUnder,Boolean isSupportsCarriers, String groupUnderName,String reportDescr, Boolean isFavourite,Boolean isPopularReport) {
        this.rptId = rptId;
        this.reportFileName = reportFileName;
        this.category = category;
        this.isConsolidated = isConsolidated;
        this.reportGroupId = reportGroupId;
        this.newRptGrpId = newRptGrpId;
        this.groupName = groupName;
        this.groupUnder = groupUnder;
        this.isSupportsCarriers = isSupportsCarriers;
        this.groupUnderName = groupUnderName;
        this.reportDescr= reportDescr;
        this.isFavourite = isFavourite;
        this.isPopularReport=isPopularReport;

    }

    public ReportModesDto(Long rptId, String reportFileName, Integer category, Integer isConsolidated, Integer reportGroupId,Integer newRptGrpId,String groupName,
                          Integer groupUnder,Boolean isSupportsCarriers, String groupUnderName,String reportDescr, Boolean isFavourite,
                          Boolean isPopularReport,Integer showCriteriaTab) {
        this.rptId = rptId;
        this.reportFileName = reportFileName;
        this.category = category;
        this.isConsolidated = isConsolidated;
        this.reportGroupId = reportGroupId;
        this.newRptGrpId = newRptGrpId;
        this.groupName = groupName;
        this.groupUnder = groupUnder;
        this.isSupportsCarriers = isSupportsCarriers;
        this.groupUnderName = groupUnderName;
        this.reportDescr= reportDescr;
        this.isFavourite = isFavourite;
        this.isPopularReport=isPopularReport;
        this.showCriteriaTab=showCriteriaTab;

    }

    public Long getRptId() {
        return rptId;
    }

    public void setRptId(Long rptId) {
        this.rptId = rptId;
    }

    public Integer getShowCriteriaTab() {
        return showCriteriaTab;
    }

    public void setShowCriteriaTab(Integer showCriteriaTab) {
        this.showCriteriaTab = showCriteriaTab;
    }

    public String getReportFileName() {
        return reportFileName;
    }

    public void setReportFileName(String reportFileName) {
        this.reportFileName = reportFileName;
    }

    public Integer getCategory() {
        return category;
    }

    public void setCategory(Integer category) {
        this.category = category;
    }

    public Integer getIsConsolidated() {
        return isConsolidated;
    }

    public void setIsConsolidated(Integer isConsolidated) {
        this.isConsolidated = isConsolidated;
    }

    public Integer getReportGroupId() {
        return reportGroupId;
    }

    public void setReportGroupId(Integer reportGroupId) {
        this.reportGroupId = reportGroupId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public Integer getGroupUnder() {
        return groupUnder;
    }

    public void setGroupUnder(Integer groupUnder) {
        this.groupUnder = groupUnder;
    }

    public Boolean getSupportsCarriers() {
        return isSupportsCarriers;
    }

    public void setSupportsCarriers(Boolean supportsCarriers) {
        isSupportsCarriers = supportsCarriers;
    }

    public String getGroupUnderName() {
        return groupUnderName;
    }

    public void setGroupUnderName(String groupUnderName) {
        this.groupUnderName = groupUnderName;
    }

    public String getReportDescr() { return reportDescr; }



    public void setReportDescr(String reportDescr) { this.reportDescr = reportDescr; }

    public Integer getNewRptGrpId() {
        return newRptGrpId;
    }

    public void setNewRptGrpId(Integer newRptGrpId) {
        this.newRptGrpId = newRptGrpId;
    }

    public boolean isFavourite() {
        return isFavourite;
    }

    public void setFavourite(boolean favourite) {
        isFavourite = favourite;
    }

    public boolean isPopularReport() {
        return isPopularReport;
    }

    public void setPopularReport(boolean popularReport) {
        isPopularReport = popularReport;
    }

}