package com.envista.msi.api.web.rest.dto.invoicing;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by KRISHNAREDDYM on 5/22/2017.
 */
@NamedStoredProcedureQueries({
        @NamedStoredProcedureQuery(name = "CarrierDto.getAllCarriers", procedureName = "SHP_INV_GET_CARRIERS_PRO",
                resultClasses = CarrierDto.class,
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, name = "P_REFCUR_CARRIERS_INFO", type = Void.class)
                })
})
@Entity
public class CarrierDto implements Serializable {

    @Id
    @Column(name = "CARRIER_ID")
    private Long carrierId;

    @Column(name = "CARRIER_NAME")
    private String carrierName;

    public CarrierDto() {
    }

    public Long getCarrierId() {
        return carrierId;
    }

    public void setCarrierId(Long carrierId) {
        this.carrierId = carrierId;
    }

    public String getCarrierName() {
        return carrierName;
    }

    public void setCarrierName(String carrierName) {
        this.carrierName = carrierName;
    }
}
