package com.envista.msi.api.web.rest.dto.reports;

import javax.persistence.*;

/**
 * Created by user1 on 4/8/2017.
 */

@NamedStoredProcedureQueries({
        @NamedStoredProcedureQuery(name = "ReportGetPcktDetails.getReportPckts", procedureName = "SHP_GET_RPT_PKT_DETAILS_PRO",
                resultClasses = ReportGetPcktDetailsDto.class,
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, name = "pktDetIdCursor", type = Void.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "savedSchedRptId", type = Long.class)
                })
})
@Entity
public class ReportGetPcktDetailsDto {

    @Id
    @Column(name = "packets_det_id")
    private Long packetsDetId;

    @Column(name = "saved_sched_rpt_id")
    private Long savedSchdRptId;

    @Column(name = "tab_name")
    private String tabName;

    @Column(name="sequence")
    private Long sequence;

    @Column(name="TEMPLATE_ID")
    private Long templateId;

    public Long getPacketsDetId() {
        return packetsDetId;
    }

    public void setPacketsDetId(Long packetsDetId) {
        this.packetsDetId = packetsDetId;
    }

    public Long getSavedSchdRptId() {
        return savedSchdRptId;
    }

    public void setSavedSchdRptId(Long savedSchdRptId) {
        this.savedSchdRptId = savedSchdRptId;
    }

    public String getTabName() {
        return tabName;
    }

    public void setTabName(String tabName) {
        this.tabName = tabName;
    }

    public Long getSequence() {
        return sequence;
    }

    public void setSequence(Long sequence) {
        this.sequence = sequence;
    }

    public Long getTemplateId() {
        return templateId;
    }

    public void setTemplateId(Long templateId) {
        this.templateId = templateId;
    }
}
