package com.envista.msi.api.web.rest.dto.invoicing;

import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by KRISHNAREDDYM on 4/25/2017.
 */

@NamedStoredProcedureQueries({
        @NamedStoredProcedureQuery(name = "CodeValueDto.getCodeValues", procedureName = "SHP_INV_GET_CODE_VALUES_PRO",
                resultClasses = CodeValueDto.class,
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_CODE_GROUP_NAME", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_PROPERTY_1", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_CODE_VALUE", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_ACTION_TYPE", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, name = "P_REFCUR_CODES_INFO", type = Void.class)
                }),
        @NamedStoredProcedureQuery(name = "CodeValueDto.getCodeValuesBygroupName", procedureName = "SHP_INV_GET_CODE_VALUES_PRO",
                resultClasses = CodeValueDto.class,
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_CARRIER_ID", type = Long.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_CODE_GROUP_ID", type = Long.class),
                        @StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, name = "P_REFCUR_CODES_INFO", type = Void.class)
                }),
        @NamedStoredProcedureQuery(name = "CodeValueDto.getClaimFlagByVoiceId", procedureName = "SHP_INV_GET_CLAIM_VAL_BY_PRO",
                resultClasses = CodeValueDto.class,
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_VOICE_ID", type = Long.class),
                        @StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, name = "P_REFCUR_CLAIM_INFO", type = Void.class)
                }),
        @NamedStoredProcedureQuery(
                name = CodeValueDto.Config.DataObjectFilter.STORED_PROCEDURE_QUERY_NAME,
                procedureName = CodeValueDto.Config.DataObjectFilter.STORED_PROCEDURE_NAME,
                resultSetMappings = {CodeValueDto.Config.DataObjectFilter.STORED_PROCEDURE_QUERY_MAPPING},
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_CODE_GROUP_NAME", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, name = "P_REFCUR_FILTER_INFO", type = Void.class)
                })
})

@SqlResultSetMappings({@SqlResultSetMapping(
        name = CodeValueDto.Config.DataObjectFilter.STORED_PROCEDURE_QUERY_MAPPING,
        classes = {
                @ConstructorResult(
                        targetClass = CodeValueDto.class,
                        columns = {
                                @ColumnResult(name = "NSP_CODE_VALUE_ID", type = Long.class),
                                @ColumnResult(name = "CODE_VALUE", type = String.class),@ColumnResult(name = "PROPERTY_2", type = String.class)
                        }
                )
        }

)})

@Entity
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CodeValueDto implements Serializable {

    @Id
    @Column(name = "NSP_CODE_VALUE_ID")
    private Long id;

    @Column(name = "CODE_VALUE")
    private String codeValue;

    @Column(name = "CODE_GROUP_ID")
    private Long codeGroupId;

    @Column(name = "PROPERTY_1")
    private String property1;

    @Column(name = "PROPERTY_2")
    private String property2;

    @Column(name = "PROPERTY_3")
    private String property3;

    public CodeValueDto() {
    }

    public CodeValueDto(Long id, String codeValue,String property2) {
        this.id = id;
        this.codeValue = codeValue;
        this.property2 = property2;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodeValue() {
        return codeValue;
    }

    public void setCodeValue(String codeValue) {
        this.codeValue = codeValue;
    }

    public Long getCodeGroupId() {
        return codeGroupId;
    }

    public void setCodeGroupId(Long codeGroupId) {
        this.codeGroupId = codeGroupId;
    }

    public String getProperty1() {
        return property1;
    }

    public void setProperty1(String property1) {
        this.property1 = property1;
    }

    public String getProperty2() {
        return property2;
    }

    public void setProperty2(String property2) {
        this.property2 = property2;
    }

    public String getProperty3() {
        return property3;
    }

    public void setProperty3(String property3) {
        this.property3 = property3;
    }

    public static class Config{
        public static class DataObjectFilter {
            public static final String STORED_PROCEDURE_QUERY_NAME = "CodeValueDto.getDataObjectFilter";
            public static final String STORED_PROCEDURE_NAME = "SHP_GLM_GET_DATA_FILTER_PRO";
            public static final String STORED_PROCEDURE_QUERY_MAPPING = "CodeValueDto.getDataObjectFilterMapping";
        }
    }
}
