package com.envista.msi.api.web.rest.dto.dashboard.filter;

import com.envista.msi.api.domain.util.DashboardStoredProcParam;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Sujit kumar on 13/03/2017.
 */

@NamedStoredProcedureQueries({
        @NamedStoredProcedureQuery(
                name = UserFilterUtilityDataDto.Config.StoredProcedureQueryName.CARRIER_BY_CUSTOMER,
                procedureName = UserFilterUtilityDataDto.Config.StoredProcedureName.CARRIER_BY_CUSTOMER,
                resultSetMappings = {UserFilterUtilityDataDto.Config.ResultMappings.CARRIER_BY_CUSTOMER_MAPPING},
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.UserFilterUtilityDataParam.CUSTOMER_IDS_CSV_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.UserFilterUtilityDataParam.IS_PARCEL_DASHLETTES_PARAM, type = Integer.class),
                        @StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, name = DashboardStoredProcParam.UserFilterUtilityDataParam.CARRIER_DATA_PARAM, type = Void.class)
                }
        ),
        @NamedStoredProcedureQuery(
                name = UserFilterUtilityDataDto.Config.StoredProcedureQueryName.MODES_BY_CARRIER,
                procedureName = UserFilterUtilityDataDto.Config.StoredProcedureName.MODES_BY_CARRIER,
                resultSetMappings = {UserFilterUtilityDataDto.Config.ResultMappings.MODES_BY_CARRIER_MAPPING},
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.UserFilterUtilityDataParam.DATE_TYPE_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.UserFilterUtilityDataParam.CUSTOMER_IDS_CSV_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.UserFilterUtilityDataParam.CARRIER_IDS_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.UserFilterUtilityDataParam.IS_PARCEL_DASHLETTES_PARAM, type = Integer.class),
                        @StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, name = DashboardStoredProcParam.UserFilterUtilityDataParam.MODES_DATA_PARAM, type = Void.class)
                }
        ),
        @NamedStoredProcedureQuery(
                name = UserFilterUtilityDataDto.Config.StoredProcedureQueryName.SERVICE_BY_GROUP,
                procedureName = UserFilterUtilityDataDto.Config.StoredProcedureName.SERVICE_BY_GROUP,
                resultSetMappings = {UserFilterUtilityDataDto.Config.ResultMappings.SERVICE_BY_GROUP_MAPPING},
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.UserFilterUtilityDataParam.DATE_TYPE_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.UserFilterUtilityDataParam.CUSTOMER_IDS_CSV_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.UserFilterUtilityDataParam.CARRIER_IDS_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.UserFilterUtilityDataParam.MODES_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.UserFilterUtilityDataParam.IS_PARCEL_DASHLETTES_PARAM, type = Integer.class),
                        @StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, name = DashboardStoredProcParam.UserFilterUtilityDataParam.SERVICE_DATA_PARAM, type = Void.class)
                }
        ),
        @NamedStoredProcedureQuery(
                name = UserFilterUtilityDataDto.Config.StoredProcedureQueryName.CARRIER_BY_IDS,
                procedureName = UserFilterUtilityDataDto.Config.StoredProcedureName.CARRIER_BY_IDS,
                resultSetMappings = {UserFilterUtilityDataDto.Config.ResultMappings.CARRIER_BY_IDS_MAPPING},
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.UserFilterUtilityDataParam.CARRIER_IDS_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, name = DashboardStoredProcParam.UserFilterUtilityDataParam.CARRIER_DATA_PARAM, type = Void.class)
                }
        )
})

@SqlResultSetMappings({
        @SqlResultSetMapping(
                name = UserFilterUtilityDataDto.Config.ResultMappings.CARRIER_BY_CUSTOMER_MAPPING,
                classes = {
                        @ConstructorResult(
                                targetClass = UserFilterUtilityDataDto.class,
                                columns = {
                                        @ColumnResult(name = "CARRIER_TYPE", type = String.class),
                                        @ColumnResult(name = "CARRIER_ID", type = Long.class),
                                        @ColumnResult(name = "CARRIER_NAME", type = String.class)
                                }
                        )
                }
        ),
        @SqlResultSetMapping(
                name = UserFilterUtilityDataDto.Config.ResultMappings.MODES_BY_CARRIER_MAPPING,
                classes = {
                        @ConstructorResult(
                                targetClass = UserFilterUtilityDataDto.class,
                                columns = {
                                        @ColumnResult(name = "ID", type = Long.class),
                                        @ColumnResult(name = "NAME", type = String.class),
                                }
                        )
                }
        ),
        @SqlResultSetMapping(
                name = UserFilterUtilityDataDto.Config.ResultMappings.SERVICE_BY_GROUP_MAPPING,
                classes = {
                        @ConstructorResult(
                                targetClass = UserFilterUtilityDataDto.class,
                                columns = {
                                        @ColumnResult(name = "ID", type = Long.class),
                                        @ColumnResult(name = "NAME", type = String.class),
                                        @ColumnResult(name = "ACTIVE", type = String.class),
                                        @ColumnResult(name = "TYPE", type = String.class),
                                }
                        )
                }
        ),
        @SqlResultSetMapping(
                name = UserFilterUtilityDataDto.Config.ResultMappings.CARRIER_BY_IDS_MAPPING,
                classes = {
                        @ConstructorResult(
                                targetClass = UserFilterUtilityDataDto.class,
                                columns = {
                                        @ColumnResult(name = "ID", type = Long.class),
                                        @ColumnResult(name = "IS_LTL", type = Integer.class)
                                }
                        )
                }
        )
})

@Entity
public class UserFilterUtilityDataDto implements Serializable {
    @Id
    private Long id;

    private String name;

    @Column(name = "CARRIER_TYPE")
    private String carrierType;

    @Column(name = "CARRIER_ID")
    private Long carrierId;

    @Column(name = "CARRIER_NAME")
    private String carrierName;

    @Column(name = "ACTIVE")
    private String active;

    @Column(name = "TYPE")
    private String type;

    @Column(name = "IS_LTL")
    private Integer ltl;

    public UserFilterUtilityDataDto(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public UserFilterUtilityDataDto(String carrierType, Long carrierId, String carrierName) {
        this.carrierType = carrierType;
        this.carrierId = carrierId;
        this.carrierName = carrierName;
    }

    public UserFilterUtilityDataDto(Long id, String name, String active, String type) {
        this.id = id;
        this.name = name;
        this.active = active;
        this.type = type;
    }

    public UserFilterUtilityDataDto(Long id, Integer ltl) {
        this.id = id;
        this.ltl = ltl;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCarrierType() {
        return carrierType;
    }

    public void setCarrierType(String carrierType) {
        this.carrierType = carrierType;
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

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getLtl() {
        return ltl;
    }

    public void setLtl(Integer ltl) {
        this.ltl = ltl;
    }

    public static class Config{
        static class ResultMappings{
            static final String CARRIER_BY_CUSTOMER_MAPPING = "UserFilterUtilityDataDto.CarrierByCustomerMapping";
            static final String MODES_BY_CARRIER_MAPPING = "UserFilterUtilityDataDto.ModesByCarrierMapping";
            static final String SERVICE_BY_GROUP_MAPPING = "UserFilterUtilityDataDto.ServiceByGroupMapping";
            static final String CARRIER_BY_IDS_MAPPING = "UserFilterUtilityDataDto.CarrierByIdsMapping";
        }

        static class StoredProcedureName{
            static final String CARRIER_BY_CUSTOMER = "SHP_DB_CARR_BY_CUSTOMER_PROC";
            static final String MODES_BY_CARRIER = "SHP_DB_MODES_BY_CARR_PROC";
            static final String SERVICE_BY_GROUP = "SHP_DB_SERVICE_BY_GROUP_PROC";
            static final String CARRIER_BY_IDS = "SHP_DB_CARRIERS_BY_IDS_PROC";
        }

        public static class StoredProcedureQueryName{
            public static final String CARRIER_BY_CUSTOMER = "UserFilterUtilityDataDto.getCarrierByCustomer";
            public static final String MODES_BY_CARRIER = "UserFilterUtilityDataDto.getModesByCarrier";
            public static final String SERVICE_BY_GROUP = "UserFilterUtilityDataDto.getServiceByGroup";
            public static final String CARRIER_BY_IDS = "UserFilterUtilityDataDto.getCarrierByIds";
        }
    }
}
