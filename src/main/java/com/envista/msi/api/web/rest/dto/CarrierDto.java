package com.envista.msi.api.web.rest.dto;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by user1 on 1/24/2017.
 */
@NamedStoredProcedureQueries({
        @NamedStoredProcedureQuery(name = "carrTb.getCarrierList", procedureName = "SHP_FRT_CARR_GRP_BY_CUST_PROC",
                resultClasses = CarrierDto.class,
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_customer_ids", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, name = "p_carrList", type = void.class)
                })
})
@Entity
public class CarrierDto implements Serializable{

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name="carrier_id",unique=true)
    private Long carrierId;

    @Column(name="carrier_name")
    private String carrierName;

    @Column(name="scac_code")
    private String scacCode;

    @Column(name = "NSP_CARRIER_GROUP_ID")
    private Long carrierGroupId;

    @Column(name = "group_name")
    private String getCarrierGroupName;

    @Column(name = "group_desc")
    private String carrierGroupDescription;

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

    public String getScacCode() {
        return scacCode;
    }

    public void setScacCode(String scacCode) {
        this.scacCode = scacCode;
    }

    public Long getCarrierGroupId() {
        return carrierGroupId;
    }

    public void setCarrierGroupId(Long carrierGroupId) {
        this.carrierGroupId = carrierGroupId;
    }

    public String getGetCarrierGroupName() {
        return getCarrierGroupName;
    }

    public void setGetCarrierGroupName(String getCarrierGroupName) {
        this.getCarrierGroupName = getCarrierGroupName;
    }

    public String getCarrierGroupDescription() {
        return carrierGroupDescription;
    }

    public void setCarrierGroupDescription(String carrierGroupDescription) {
        this.carrierGroupDescription = carrierGroupDescription;
    }
}
