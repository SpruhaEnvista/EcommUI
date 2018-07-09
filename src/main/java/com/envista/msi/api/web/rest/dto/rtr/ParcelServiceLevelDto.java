package com.envista.msi.api.web.rest.dto.rtr;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedStoredProcedureQueries;
import javax.persistence.NamedStoredProcedureQuery;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureParameter;
import java.io.Serializable;

@NamedStoredProcedureQueries({
        @NamedStoredProcedureQuery(name = "ParcelServiceLevelDto.getService",procedureName = "shp_rate_service_by_carrier",
                resultClasses = ParcelServiceLevelDto.class,
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_carrier_id", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, name = "refcur_rate_service", type = Void.class)
                })
})

@Entity
public class ParcelServiceLevelDto implements Serializable {
    @Id
    @Column(name="lookup_id")
    private Long lookUpId;

    @Column(name="lookup_code")
    private String lookupCode;

    @Column(name="custom_defined_9")
    private String customDefined9;

    public ParcelServiceLevelDto() {
    }

    public Long getLookUpId() {
        return lookUpId;
    }

    public void setLookUpId(Long lookUpId) {
        this.lookUpId = lookUpId;
    }

    public String getLookupCode() {
        return lookupCode;
    }

    public void setLookupCode(String lookupCode) {
        this.lookupCode = lookupCode;
    }

    public String getCustomDefined9() {
        return customDefined9;
    }

    public void setCustomDefined9(String customDefined9) {
        this.customDefined9 = customDefined9;
    }
}
