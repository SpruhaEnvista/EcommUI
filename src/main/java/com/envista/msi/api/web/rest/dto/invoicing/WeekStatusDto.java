package com.envista.msi.api.web.rest.dto.invoicing;

import javax.persistence.*;

/**
 * Created by admin on 6/2/2017.
 */
@NamedStoredProcedureQueries({
        @NamedStoredProcedureQuery(name = "WeekStatusDto.getWeekStatusInfo", procedureName = "SHP_INV_GET_WEEK_S_INFO_PRO",
                resultClasses = WeekStatusDto.class,
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_FROM_DATE", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_TO_DATE", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_ACTION_TYPE", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, name = "P_REFCUR_WEEK_STATUS_INFO", type = Void.class)
                })
})
@Entity
public class WeekStatusDto {

    @Id
    @Column(name = "ROW_NUM")
    private Long id;

    @Column(name = "CLOSE_BUTTON")
    private String closeButton;

    @Column(name = "SCRUB_BUTTON")
    private String scrubButton;

    @Column(name = "FILE_UPLOAD")
    private String fileUpload;

    public WeekStatusDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCloseButton() {
        return closeButton;
    }

    public void setCloseButton(String closeButton) {
        this.closeButton = closeButton;
    }

    public String getScrubButton() {
        return scrubButton;
    }

    public void setScrubButton(String scrubButton) {
        this.scrubButton = scrubButton;
    }

    public String getFileUpload() {
        return fileUpload;
    }

    public void setFileUpload(String fileUpload) {
        this.fileUpload = fileUpload;
    }
}
