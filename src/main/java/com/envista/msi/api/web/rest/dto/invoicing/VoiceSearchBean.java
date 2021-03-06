package com.envista.msi.api.web.rest.dto.invoicing;

import java.io.Serializable;

/**
 * Created by KRISHNAREDDYM on 4/13/2017.
 */
public class VoiceSearchBean implements Serializable {

    private String voiceName;
    private String parentVoiceName;
    private String voiceType;
    private String comments;
    private String voiceFlag;
    private Integer offset;
    private Integer pageSize;
    private String sort;

    public Integer getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    /**
     * @return the voiceName
     */
    public String getVoiceName() {
        return voiceName;
    }
    /**
     * @param voiceName the voiceName to set
     */
    public void setVoiceName(String voiceName) {
        this.voiceName = voiceName;
    }
    /**
     * @return the parentVoiceName
     */
    public String getParentVoiceName() {
        return parentVoiceName;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    /**
     * @param parentVoiceName the parentVoiceName to set
     */

    public void setParentVoiceName(String parentVoiceName) {
        this.parentVoiceName = parentVoiceName;
    }
    /**
     * @return the voiceType
     */
    public String getVoiceType() {
        return voiceType;
    }
    /**
     * @param voiceType the voiceType to set
     */
    public void setVoiceType(String voiceType) {
        this.voiceType = voiceType;
    }
    /**
     * @return the comments
     */
    public String getComments() {
        return comments;
    }
    /**
     * @param comments the comments to set
     */
    public void setComments(String comments) {
        this.comments = comments;
    }
    /**
     * @return the voiceFlag
     */
    public String getVoiceFlag() {
        return voiceFlag;
    }
    /**
     * @param voiceFlag the voiceFlag to set
     */
    public void setVoiceFlag(String voiceFlag) {
        this.voiceFlag = voiceFlag;
    }
}
