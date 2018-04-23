package com.envista.msi.api.web.rest.dto.rtr;

import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.*;

/**
 * Created by krishna on 20/04/2018.
 */

@NamedStoredProcedureQueries({

        @NamedStoredProcedureQuery(
                name = ParcelAuditDASChargeDetailsDto.Config.StoredProcedureQueryName.AUDIT_LOAD_DAS_CHARGE_DETAILS,
                procedureName = ParcelAuditDASChargeDetailsDto.Config.StoredProcedureName.AUDIT_LOAD_DAS_CHARGE_DETAILS,
                resultSetMappings = {ParcelAuditDASChargeDetailsDto.Config.SqlResultSetMapping.LOAD_DAS_CHARGE_MAPPING},
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_module_name", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, name = "p_charge_list", type = Void.class)
                }
        )


})

@SqlResultSetMappings({
        @SqlResultSetMapping(
                name = ParcelAuditDASChargeDetailsDto.Config.SqlResultSetMapping.LOAD_DAS_CHARGE_MAPPING,
                classes = {
                        @ConstructorResult(
                                targetClass = ParcelAuditDASChargeDetailsDto.class,
                                columns = {
                                        @ColumnResult(name = "LOOKUP_CODE", type = String.class),
                                        @ColumnResult(name = "LOOKUP_VALUE",type = String.class)
                                }
                        )
                }
        )
})
@Entity
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ParcelAuditDASChargeDetailsDto {

    @Id
    @Column(name = "lookup_id")
    private Long id;

    @Column(name = "LOOKUP_CODE")
    private String lookupCode;

    @Column(name = "LOOKUP_VALUE")
    private String lookupValue;

    public ParcelAuditDASChargeDetailsDto() {}

    public ParcelAuditDASChargeDetailsDto(String lookupCode, String lookupValue) {
        this.lookupCode = lookupCode;
        this.lookupValue = lookupValue;
    }

    public String getLookupValue() {
        return lookupValue;
    }

    public void setLookupValue(String lookupValue) {
        this.lookupValue = lookupValue;
    }



    public String getLookupCode() {
        return lookupCode;
    }

    public void setLookupCode(String lookupCode) {
        this.lookupCode = lookupCode;
    }

    public static class Config{
        public static class StoredProcedureName{
            static final String AUDIT_LOAD_DAS_CHARGE_DETAILS = "SHP_DAS_CHARGE_DETAILS_PROC";
        }

        public static class StoredProcedureQueryName{
            public static final String AUDIT_LOAD_DAS_CHARGE_DETAILS = "ParcelAuditDASChargeDetailsDto.loadUpsParcelAuditDetails";
        }

        public static class SqlResultSetMapping{
            public static final String LOAD_DAS_CHARGE_MAPPING = "ParcelAuditDASChargeDetailsDto.loadDASChargeMapping";
        }
    }
}
