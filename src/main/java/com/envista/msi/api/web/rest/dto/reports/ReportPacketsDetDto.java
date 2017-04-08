package com.envista.msi.api.web.rest.dto.reports;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by SreedharT on 4/6/2017.
 */

@NamedStoredProcedureQueries({
        @NamedStoredProcedureQuery(name = "SavedSchedPackets.saveSchedPakcet", procedureName = "shp_save_rpt_pkt_details_pro",
                resultSetMappings = "SavedSchedPacketReport",
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, name = "pktDetIdCursor", type = Void.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "savedSchedRptId", type = Long.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "tabName", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "sequenceNum", type = Integer.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "templateId", type = Long.class)
                })
})

@SqlResultSetMappings({
        @SqlResultSetMapping(name = "SavedSchedPacketReport", classes = {
                @ConstructorResult(
                        targetClass = ReportPacketsDetDto.class,
                        columns = {
                                @ColumnResult(name = "packets_det_id", type = Long.class)
                        }
                )
        })
})
@Entity
public class ReportPacketsDetDto {

    @Id
    @Column(name="packets_det_id")
    private Long packetsDetId;

    @Column(name="saved_sched_rpt_id")
    private Long savedSchdRptId;

    @Column(name="tab_name")
    private String tabName;

    @Column(name="sequence")
    private Integer sequence;

    @Column(name="template_id")
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

    public Integer getSequence() {
        return sequence;
    }

    public void setSequence(Integer sequence) {
        this.sequence = sequence;
    }

    public Long getTemplateId() {
        return templateId;
    }

    public void setTemplateId(Long templateId) {
        this.templateId = templateId;
    }

    public ReportPacketsDetDto(){
    }

    public ReportPacketsDetDto(long packetsDetId){
        this.packetsDetId = packetsDetId;
    }
}
