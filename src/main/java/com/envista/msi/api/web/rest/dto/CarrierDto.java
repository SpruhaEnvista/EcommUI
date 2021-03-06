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
                }),
        @NamedStoredProcedureQuery(name = "CarrierDto.getCarriersByIds", procedureName = "SHP_GET_CARRIER_INFO_PROC",
                resultSetMappings = {"CarrierDto.getCarriersByIdsMapping"},
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_carrier_ids", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, name = "p_carrier_details", type = void.class)
                }),
        @NamedStoredProcedureQuery(name = "CarrierDto.getUserCarrierDetailsForReport", procedureName = "shp_rpt_carrier_proc",
                resultSetMappings = {"CarrierDto.getCarrierDetailsForUserAndCustomerMapping"},
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_user_id", type = Long.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_rpt_id", type = Long.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_customer_ids", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, name = "p_refcur_carrier_info", type = void.class)
                })
})

@SqlResultSetMappings({
        @SqlResultSetMapping(
                name = "CarrierDto.getCarriersByIdsMapping",
                classes = {
                        @ConstructorResult(
                                targetClass = CarrierDto.class,
                                columns = {
                                        @ColumnResult(name = "carrier_id", type = Long.class),
                                        @ColumnResult(name = "carrier_name", type = String.class)
                                }
                        )
                }
        ),
        @SqlResultSetMapping(
                name = "CarrierDto.getCarrierDetailsForUserAndCustomerMapping",
                classes = {
                        @ConstructorResult(
                                targetClass = CarrierDto.class,
                                columns = {
                                        @ColumnResult(name = "carrier_id", type = Long.class),
                                        @ColumnResult(name = "carrier_name", type = String.class)
                                }
                        )
                }
        )
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

    public CarrierDto() {
    }

    public CarrierDto(Long carrierId, String carrierName) {
        this.carrierId = carrierId;
        this.carrierName = carrierName;
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
