package com.envista.msi.api.web.rest.dto.reports;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

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
                                @ColumnResult(name = "group_name", type = String.class),
                                @ColumnResult(name = "group_under", type = Integer.class),
                                @ColumnResult(name = "group_under_name", type = String.class),
                        }
                )
        })
})
@Entity
public class ReportModesDto implements Serializable {

    @Id
    @Column(name = "rpt_id")
    private Long reportId;

    @Column(name = "report_name")
    private String reportName;

    @Column(name = "category")
    private Integer category;

    @Column(name = "isconsolidated")
    private Integer isConsolidated;

    @Column(name = "rpt_grp_id")
    private Integer reportGroupId;

    @Column(name = "group_name")
    private String groupName;

    @Column(name = "group_under")
    private Integer groupUnder;

    @Column(name = "group_under_name")
    private String groupUnderName;

    public ReportModesDto() {  }

    public ReportModesDto(Long reportId, String reportName, Integer category, Integer isConsolidated, Integer reportGroupId, String groupName, Integer groupUnder, String groupUnderName) {
        this.reportId = reportId;
        this.reportName = reportName;
        this.category = category;
        this.isConsolidated = isConsolidated;
        this.reportGroupId = reportGroupId;
        this.groupName = groupName;
        this.groupUnder = groupUnder;
        this.groupUnderName = groupUnderName;
    }

    public Long getReportId() {
        return reportId;
    }

    public void setReportId(Long reportId) {
        this.reportId = reportId;
    }

    public String getReportName() {
        return reportName;
    }

    public void setReportName(String reportName) {
        this.reportName = reportName;
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

    public String getGroupUnderName() {
        return groupUnderName;
    }

    public void setGroupUnderName(String groupUnderName) {
        this.groupUnderName = groupUnderName;
    }
}