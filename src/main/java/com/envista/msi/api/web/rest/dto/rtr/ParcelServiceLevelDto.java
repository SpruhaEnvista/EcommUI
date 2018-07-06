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
    @Column(name="TRANSIT_TIME_ID")
    private Long id;

    @Column(name="RTR_SERVICE_CODE")
    private String rtrServiceCode;

    @Column(name="SERVICE_DESC")
    private String serviceDesc;

    public ParcelServiceLevelDto() {
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRtrServiceCode() {
        return rtrServiceCode;
    }

    public void setRtrServiceCode(String rtrServiceCode) {
        this.rtrServiceCode = rtrServiceCode;
    }

    public String getServiceDesc() {
        return serviceDesc;
    }

    public void setServiceDesc(String serviceDesc) {
        this.serviceDesc = serviceDesc;
    }
}
