package com.envista.msi.api.web.rest.dto;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by Sujit kumar on 14/09/2017.
 */

@NamedStoredProcedureQueries({
        @NamedStoredProcedureQuery(
                name = "ShipperGroupDto.getShipperGroupDetails",
                procedureName = "SHP_GET_SHIPPER_GROUP_PROC",
                resultSetMappings = {"ShipperGroupDto.getShipperGroupDetailsMapping"},
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_shipper_grp_ids", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_shipper_grp_name", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_customer_ids", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, name = "p_shipper_grp_list", type = void.class)
                }
        )
})

@SqlResultSetMappings({
        @SqlResultSetMapping(
                name = "ShipperGroupDto.getShipperGroupDetailsMapping",
                classes = {
                        @ConstructorResult(
                                targetClass = ShipperGroupDto.class,
                                columns = {
                                        @ColumnResult(name = "SHIPPER_GROUP_ID", type = Long.class),
                                        @ColumnResult(name = "SHIPPER_GROUP_NAME", type = String.class),
                                        @ColumnResult(name = "SHIPPER_GROUP_DESCRIPTION", type = String.class),
                                        @ColumnResult(name = "CUSTOMER_ID", type = Long.class)
                                }
                        )
                }
        )
})

@Entity
public class ShipperGroupDto implements Serializable {
    @Id
    @Column(name = "SHIPPER_GROUP_ID")
    private Long shipperGroupId;

    @Column(name = "SHIPPER_GROUP_NAME")
    private String shipperGroupName;

    @Column(name = "SHIPPER_GROUP_DESCRIPTION")
    private String shipperGroupDescription;

    @Column(name = "CUSTOMER_ID")
    private Long customerId;

    @Column(name = "CREATE_USER")
    private String createUser;

    @Column(name = "CREATE_DATE")
    private Date createDate;

    @Column(name = "LAST_UPDATE_USER")
    private String lastUpdateUser;

    @Column(name = "LAST_UPDATE_DATE")
    private Date lastUpdateDate;

    public ShipperGroupDto() {
    }

    public ShipperGroupDto(Long shipperGroupId, String shipperGroupName, String shipperGroupDescription, Long customerId) {
        this.shipperGroupId = shipperGroupId;
        this.shipperGroupName = shipperGroupName;
        this.shipperGroupDescription = shipperGroupDescription;
        this.customerId = customerId;
    }

    public Long getShipperGroupId() {
        return shipperGroupId;
    }

    public void setShipperGroupId(Long shipperGroupId) {
        this.shipperGroupId = shipperGroupId;
    }

    public String getShipperGroupName() {
        return shipperGroupName;
    }

    public void setShipperGroupName(String shipperGroupName) {
        this.shipperGroupName = shipperGroupName;
    }

    public String getShipperGroupDescription() {
        return shipperGroupDescription;
    }

    public void setShipperGroupDescription(String shipperGroupDescription) {
        this.shipperGroupDescription = shipperGroupDescription;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getLastUpdateUser() {
        return lastUpdateUser;
    }

    public void setLastUpdateUser(String lastUpdateUser) {
        this.lastUpdateUser = lastUpdateUser;
    }

    public Date getLastUpdateDate() {
        return lastUpdateDate;
    }

    public void setLastUpdateDate(Date lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }
}
