package com.envista.msi.api.web.rest.dto.invoicing;

/**
 * Created by KRISHNAREDDYM on 6/14/2017.
 */
public class UvVoiceUpdateBean {

    private String actionName;

    private String ebillManifestIds;

    private String voiceName;

    private String voiceComments;

    private String internalInvComments;

    private Long voiceId;

    private String actionType;

    private Long ebillManifestId;

    private String claimFlag;

    private String userName;


    public String getActionName() {
        return actionName;
    }

    public void setActionName(String actionName) {
        this.actionName = actionName;
    }

    public String getEbillManifestIds() {
        return ebillManifestIds;
    }

    public void setEbillManifestIds(String ebillManifestIds) {
        this.ebillManifestIds = ebillManifestIds;
    }

    public String getVoiceName() {
        return voiceName;
    }

    public void setVoiceName(String voiceName) {
        this.voiceName = voiceName;
    }

    public String getVoiceComments() {
        return voiceComments;
    }

    public void setVoiceComments(String voiceComments) {
        this.voiceComments = voiceComments;
    }

    public String getInternalInvComments() {
        return internalInvComments;
    }

    public void setInternalInvComments(String internalInvComments) {
        this.internalInvComments = internalInvComments;
    }

    public Long getVoiceId() {
        return voiceId;
    }

    public void setVoiceId(Long voiceId) {
        this.voiceId = voiceId;
    }

    public String getActionType() {
        return actionType;
    }

    public void setActionType(String actionType) {
        this.actionType = actionType;
    }

    public Long getEbillManifestId() {
        return ebillManifestId;
    }

    public void setEbillManifestId(Long ebillManifestId) {
        this.ebillManifestId = ebillManifestId;
    }

    public String getClaimFlag() {
        return claimFlag;
    }

    public void setClaimFlag(String claimFlag) {
        this.claimFlag = claimFlag;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
