package com.envista.msi.api.web.rest.dto.dashboard.auditactivity;

import com.envista.msi.api.domain.util.DashboardStoredProcParam;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by Sujit kumar on 13/02/2017.
 */

@NamedStoredProcedureQueries({
        @NamedStoredProcedureQuery(name = PackageExceptionDto.Config.StoredProcedureQueryName.PACKAGE_EXCEPTION,
                procedureName = PackageExceptionDto.Config.StoredProcedureName.PACKAGE_EXCEPTION,
                resultSetMappings = {PackageExceptionDto.Config.ResultMappings.PACKAGE_EXCEPTION_MAPPING},
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.PackageExceptionParams.DATE_TYPE_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.PackageExceptionParams.CUSTOMER_IDS_CSV_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.PackageExceptionParams.CARRIER_IDS_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.PackageExceptionParams.MODES_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.PackageExceptionParams.SERVICES_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.PackageExceptionParams.LANES_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.PackageExceptionParams.FROM_DATE_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.PackageExceptionParams.TO_DATE_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.PackageExceptionParams.ACCESSORIAL_NAME_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.PackageExceptionParams.TOP_TEN_ACCESSORIAL_PARAM, type = Long.class),
                        @StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, name = DashboardStoredProcParam.PackageExceptionParams.PACKAGE_EXCEPTION_DATA_PARAM, type = Void.class)
                }
        ),
        @NamedStoredProcedureQuery(name = PackageExceptionDto.Config.StoredProcedureQueryName.PACKAGE_EXCEPTION_BY_CARRIER,
                procedureName = PackageExceptionDto.Config.StoredProcedureName.PACKAGE_EXCEPTION_BY_CARRIER,
                resultSetMappings = PackageExceptionDto.Config.ResultMappings.PACKAGE_EXCEPTION_BY_CARRIER_MAPPING,
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.PackageExceptionParams.DATE_TYPE_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.PackageExceptionParams.CUSTOMER_IDS_CSV_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.PackageExceptionParams.CARRIER_IDS_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.PackageExceptionParams.MODES_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.PackageExceptionParams.SERVICES_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.PackageExceptionParams.LANES_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.PackageExceptionParams.FROM_DATE_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.PackageExceptionParams.TO_DATE_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.PackageExceptionParams.ACCESSORIAL_NAME_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.PackageExceptionParams.TOP_TEN_ACCESSORIAL_PARAM, type = Long.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.PackageExceptionParams.DELIVERY_FLAG_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, name = DashboardStoredProcParam.PackageExceptionParams.PACKAGE_EXCEPTION_DATA_PARAM, type = Void.class)
                }
        ),
        @NamedStoredProcedureQuery(name = PackageExceptionDto.Config.StoredProcedureQueryName.PACKAGE_EXCEPTION_BY_MONTH,
                procedureName = PackageExceptionDto.Config.StoredProcedureName.PACKAGE_EXCEPTION_BY_MONTH,
                resultSetMappings = PackageExceptionDto.Config.ResultMappings.PACKAGE_EXCEPTION_BY_MONTH_MAPPING,
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.PackageExceptionParams.DATE_TYPE_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.PackageExceptionParams.CUSTOMER_IDS_CSV_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.PackageExceptionParams.CARRIER_IDS_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.PackageExceptionParams.MODES_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.PackageExceptionParams.SERVICES_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.PackageExceptionParams.LANES_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.PackageExceptionParams.FROM_DATE_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.PackageExceptionParams.TO_DATE_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.PackageExceptionParams.ACCESSORIAL_NAME_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.PackageExceptionParams.TOP_TEN_ACCESSORIAL_PARAM, type = Long.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = DashboardStoredProcParam.PackageExceptionParams.DELIVERY_FLAG_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, name = DashboardStoredProcParam.PackageExceptionParams.PACKAGE_EXCEPTION_DATA_PARAM, type = Void.class)
                }
        )
})

@SqlResultSetMappings({
        @SqlResultSetMapping(name = PackageExceptionDto.Config.ResultMappings.PACKAGE_EXCEPTION_MAPPING,
            classes = {
                    @ConstructorResult(targetClass = PackageExceptionDto.class,
                    columns = {
                            @ColumnResult(name = "BILLING_DATE", type = String.class),
                            @ColumnResult(name = "DELIVERY_FLAG", type = String.class),
                            @ColumnResult(name = "COUNT_DEL_FLAG", type = Integer.class)
                    })
            }),
        @SqlResultSetMapping(name = PackageExceptionDto.Config.ResultMappings.PACKAGE_EXCEPTION_BY_CARRIER_MAPPING,
                classes = {
                        @ConstructorResult(targetClass = PackageExceptionDto.class,
                                columns = {
                                        @ColumnResult(name = "ID", type = Long.class),
                                        @ColumnResult(name = "NAME", type = String.class),
                                        @ColumnResult(name = "VALUE", type = Double.class)
                                })
                }),
        @SqlResultSetMapping(name = PackageExceptionDto.Config.ResultMappings.PACKAGE_EXCEPTION_BY_MONTH_MAPPING,
                classes = {
                        @ConstructorResult(targetClass = PackageExceptionDto.class,
                                columns = {
                                        @ColumnResult(name = "BILL_DATE", type = Date.class),
                                        @ColumnResult(name = "AMOUNT", type = Double.class)
                                })
                })
})

@Entity
public class PackageExceptionDto implements Serializable{
    @Id
    @Column(name = "ID")
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "VALUE")
    private Double value;

    @Column(name = "BILL_DATE")
    private Date billDate;

    @Column(name = "AMOUNT")
    private Double amount;

    @Column(name = "BILLING_DATE")
    private String billingDate;

    @Column(name = "DELIVERY_FLAG")
    private String deliveryFlag;

    @Column(name = "COUNT_DEL_FLAG")
    private Integer deliveryFlagCount;

    public PackageExceptionDto(){}

    public PackageExceptionDto(String billingDate, String deliveryFlag, Integer deliveryFlagCount) {
        this.billingDate = billingDate;
        this.deliveryFlag = deliveryFlag;
        this.deliveryFlagCount = deliveryFlagCount;
    }

    public PackageExceptionDto(Date billDate, Double amount) {
        this.billDate = billDate;
        this.amount = amount;
    }

    public PackageExceptionDto(Long id, String name, Double value) {
        this.id = id;
        this.name = name;
        this.value = value;
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

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public Date getBillDate() {
        return billDate;
    }

    public void setBillDate(Date billDate) {
        this.billDate = billDate;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Integer getDeliveryFlagCount() {
        return deliveryFlagCount;
    }

    public void setDeliveryFlagCount(Integer deliveryFlagCount) {
        this.deliveryFlagCount = deliveryFlagCount;
    }

    public String getBillingDate() {
        return billingDate;
    }

    public void setBillingDate(String billingDate) {
        this.billingDate = billingDate;
    }

    public String getDeliveryFlag() {
        return deliveryFlag;
    }

    public void setDeliveryFlag(String deliveryFlag) {
        this.deliveryFlag = deliveryFlag;
    }

    public static class Config{
        static class ResultMappings{
            static final String PACKAGE_EXCEPTION_MAPPING = "PackageExceptionDto.PackageExceptionMapping";
            static final String PACKAGE_EXCEPTION_BY_CARRIER_MAPPING = "PackageExceptionDto.PackageExceptionByCarrierMapping";
            static final String PACKAGE_EXCEPTION_BY_MONTH_MAPPING = "PackageExceptionDto.PackageExceptionByMonthMapping";
        }

        static class StoredProcedureName{
            static final String PACKAGE_EXCEPTION = "SHP_DB_PKG_EXCP_RPOC";
            static final String PACKAGE_EXCEPTION_BY_CARRIER = "SHP_DB_PKG_EXCP_CARR_RPOC";
            static final String PACKAGE_EXCEPTION_BY_MONTH = "SHP_DB_PKG_EXCP_MNTH_RPOC";
        }

        public static class StoredProcedureQueryName{
            public static final String PACKAGE_EXCEPTION = "PackageExceptionDto.getPackageException";
            public static final String PACKAGE_EXCEPTION_BY_CARRIER = "PackageExceptionDto.getPackageExceptionByCarrier";
            public static final String PACKAGE_EXCEPTION_BY_MONTH = "PackageExceptionDto.getPackageExceptionByMonth";
        }
    }
}
