package com.envista.msi.api.web.rest.dto.reports;

import javax.persistence.*;

/**
 * Created by Sreenivas on 4/18/2017.
 */

@NamedStoredProcedureQueries({
        @NamedStoredProcedureQuery(name = "ReportTypes.reportTypeDetails", procedureName = "shp_rpt_type_det_proc",
                resultClasses = ReportTypeDto.class,
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, name = "p_rptTypeCusr", type = Void.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "rptTypeId", type = Long.class)
                })
})

@Entity
public class ReportTypeDto {

    //, type_name , type_description, file_extension, content_type
    @Id
    @Column(name = "type_id")
    private Long typeId;

    @Column(name = "type_name")
    private String typeName;

    @Column(name = "type_description")
    private String typeDescription;

    @Column(name = "file_extension")
    private String fileExtension;

    @Column(name = "content_type")
    private String contentType;

    public Long getTypeId() {
        return typeId;
    }

    public void setTypeId(Long typeId) {
        this.typeId = typeId;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getTypeDescription() {
        return typeDescription;
    }

    public void setTypeDescription(String typeDescription) {
        this.typeDescription = typeDescription;
    }

    public String getFileExtension() {
        return fileExtension;
    }

    public void setFileExtension(String fileExtension) {
        this.fileExtension = fileExtension;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

}
