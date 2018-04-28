package com.envista.msi.api.web.rest.dto.rtr;

import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.*;

/**
 * Created by krishna on 20/04/2018.
 */

@NamedStoredProcedureQueries({

        @NamedStoredProcedureQuery(
                name = ParcelARChargeCodeMappingDto.Config.StoredProcedureQueryName.AUDIT_LOAD_MAPPED_AR_CHARGE_DETAILS,
                procedureName = ParcelARChargeCodeMappingDto.Config.StoredProcedureName.AUDIT_LOAD_MAPPED_AR_CHARGE_DETAILS,
                resultSetMappings = {ParcelARChargeCodeMappingDto.Config.SqlResultSetMapping.LOAD_MAPPED_AR_CHARGE_MAPPING},
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_module_name", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_custom_defined_1", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, name = "p_charge_list", type = Void.class)
                }
        )


})

@SqlResultSetMappings({
        @SqlResultSetMapping(
                name = ParcelARChargeCodeMappingDto.Config.SqlResultSetMapping.LOAD_MAPPED_AR_CHARGE_MAPPING,
                classes = {
                        @ConstructorResult(
                                targetClass = ParcelARChargeCodeMappingDto.class,
                                columns = {
                                        @ColumnResult(name = "lookup_id", type = Long.class),
                                        @ColumnResult(name = "LOOKUP_CODE", type = String.class),
                                        @ColumnResult(name = "code_value",type = String.class)
                                }
                        )
                }
        )
})
@Entity
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ParcelARChargeCodeMappingDto {

    @Id
    @Column(name = "lookup_id")
    private Long id;

    @Column(name = "LOOKUP_CODE")
    private String lookupCode;

    @Column(name = "code_value")
    private String codeValue;

    public ParcelARChargeCodeMappingDto() {}

    public ParcelARChargeCodeMappingDto(Long id, String lookupCode, String codeValue) {
        this.id = id;
        this.lookupCode = lookupCode;
        this.codeValue = codeValue;
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

    public String getLookupCode() {
        return lookupCode;
    }

    public void setLookupCode(String lookupCode) {
        this.lookupCode = lookupCode;
    }

    public static class Config{
        public static class StoredProcedureName{
            static final String AUDIT_LOAD_MAPPED_AR_CHARGE_DETAILS = "SHP_GET_AR_CHARGE_CODES_PROC";
        }

        public static class StoredProcedureQueryName{
            public static final String AUDIT_LOAD_MAPPED_AR_CHARGE_DETAILS = "ParcelAuditDASChargeDetailsDto.loadUpsParcelAuditDetails";
        }

        public static class SqlResultSetMapping{
            public static final String LOAD_MAPPED_AR_CHARGE_MAPPING = "ParcelAuditDASChargeDetailsDto.loadDASChargeMapping";
        }
    }
}
