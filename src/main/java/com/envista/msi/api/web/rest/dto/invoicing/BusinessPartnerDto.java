package com.envista.msi.api.web.rest.dto.invoicing;

import javax.persistence.*;

/**
 * Created by KRISHNAREDDYM on 10/26/2017.
 */
@NamedStoredProcedureQueries({
        @NamedStoredProcedureQuery(name = "BusinessPartnerDto.getBusinessPartner", procedureName = "SHP_INV_GET_BUSINESS_PART_PRO",
                resultClasses = BusinessPartnerDto.class,
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_CUSTOMER_ID", type = Long.class),
                        @StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, name = "P_BUSINESS_PART_DATA", type = Void.class)
                })
})
@Entity
public class BusinessPartnerDto {

    @Id
    @Column(name = "BUSINESS_PARTNER_ID")
    private int businessPartnerId;

    public BusinessPartnerDto() {
    }

    public int getBusinessPartnerId() {
        return businessPartnerId;
    }

    public void setBusinessPartnerId(int businessPartnerId) {
        this.businessPartnerId = businessPartnerId;
    }
}
