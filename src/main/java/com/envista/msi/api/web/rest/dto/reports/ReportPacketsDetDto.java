package com.envista.msi.api.web.rest.dto.reports;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by user1 on 4/6/2017.
 */
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
    private Long sequence;

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
