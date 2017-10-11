package com.envista.msi.api.web.rest.dto.invoicing;

import javax.persistence.*;

/**
 * Created by SRIKANTH GUJJA on 09/10/2017.
 */

@NamedStoredProcedureQueries({
        @NamedStoredProcedureQuery(name = "FileDefDto.getFileDefTypes", procedureName = "SHP_INV_GET_FILE_DEF_LIST_PRO",
                resultClasses = FileDefDto.class,
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_FILE_DEF_ID", type = Long.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_ACTION_TYPE", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, name = "P_REFCUR_FILE_DEF_INFO", type = Void.class)
                })
})
@Entity
public class FileDefDto {

    @Id
    @Column(name = "FILE_DEF_ID")
    private Long id;

    @Column(name = "FILE_NAME")
    private String fileName;

    @Column(name = "FILE_TYPE")
    private String fileType;

    @Column(name = "FILE_SIGNATURE")
    private String fileSignature;

    @Column(name = "IS_ACTIVE")
    private boolean active;

    public FileDefDto() {
    }

    public void setId(Long id) {
        this.id = id;
    }
    public Long getId() {
        return id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public String getFileSignature() {
        return fileSignature;
    }

    public void setFileSignature(String fileSignature) {
        this.fileSignature = fileSignature;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }


}
