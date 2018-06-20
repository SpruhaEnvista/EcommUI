package com.envista.msi.api.web.rest.dto.reports;

import javax.persistence.*;
import java.io.Serializable;
import java.util.TreeSet;

/**
 * Created by Siddhant on 02/03/2017.
 */
@NamedStoredProcedureQueries({
        @NamedStoredProcedureQuery(name = "ReportCustomerCarrier.getReportCustomer", procedureName = "shp_rpt_customer_proc",
                resultSetMappings = "ReportCustomer",
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_user_id", type = Long.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_rpt_id", type = Long.class),
                        @StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, name = "refcur_customer_info", type = Void.class)
                }),
        @NamedStoredProcedureQuery(name = "ReportCustomerCarrier.getReportCarrier", procedureName = "shp_rpt_carrier_proc",
                resultSetMappings = "ReportCarrier",
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_user_id", type = Long.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_rpt_id", type = Long.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_customer_ids", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, name = "p_refcur_carrier_info", type = Void.class)
                }),
        @NamedStoredProcedureQuery(name = "ReportCustomerCarrier.getCustomerLevels", procedureName = "shp_rpt_customer_levels_proc",
                resultSetMappings = "CustomerLevels",
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_cusatomer_ids", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, name = "p_refcur_customer_level_info", type = Void.class)
                }),
        @NamedStoredProcedureQuery(name = "ReportCustomerCarrierDto.getDashboardCustomers",procedureName = "SHP_DB_GET_CUSTOMERS_PROC",
                resultSetMappings = "ReportCustomerCarrierDto.DashboardCustomerMapping",
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_user_id", type = Long.class),
                        @StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, name = "p_customer_data", type = Void.class)
                }
        ),
        @NamedStoredProcedureQuery(name = "ReportCustomerCarrierDto.getRateCustomers",procedureName = "shp_rate_customer_proc",
                resultSetMappings = "ReportCustomerCarrierDto.RateCustomers",
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_user_id", type = Long.class),
                        @StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, name = "refcur_rate_customer", type = Void.class)
                }
        )
})
@SqlResultSetMappings({
        @SqlResultSetMapping(name = "ReportCustomer", classes = {
                @ConstructorResult(
                        targetClass = ReportCustomerCarrierDto.class,
                        columns = {
                                @ColumnResult(name = "customer_id", type = Long.class),
                                @ColumnResult(name = "customer_name", type = String.class),
                                @ColumnResult(name = "carrier_ids", type = String.class),
                                @ColumnResult(name = "is_child", type = Boolean.class),
                                @ColumnResult(name = "parent_customer_id", type = Long.class),
                                @ColumnResult(name = "parent_customer_name", type = String.class),
                                @ColumnResult(name = "shipper_id", type = Long.class),
                                @ColumnResult(name = "ship_code_name", type = String.class),
                                @ColumnResult(name = "shipper_group_id", type = Long.class),
                                @ColumnResult(name = "paid_cust", type = Boolean.class),
                                @ColumnResult(name = "shipper_group_name", type = String.class),
                                @ColumnResult(name = "selected", type = Boolean.class)
                        })
        }),
        @SqlResultSetMapping(name = "ReportCarrier", classes = {
                @ConstructorResult(
                        targetClass = ReportCustomerCarrierDto.class,
                        columns = {
                                @ColumnResult(name = "carrier_id", type = Long.class),
                                @ColumnResult(name = "carrier_name", type = String.class),
                                @ColumnResult(name = "is_ltl", type = Boolean.class),
                                @ColumnResult(name = "selected", type = Boolean.class)
                        })
        }),
        @SqlResultSetMapping(name = "CustomerLevels", classes = {
                @ConstructorResult(
                        targetClass = ReportCustomerCarrierDto.class,
                        columns = {
                                @ColumnResult(name = "value", type = String.class),
                        })
        }),
        @SqlResultSetMapping(
                name = "ReportCustomerCarrierDto.DashboardCustomerMapping",
                classes = {
                        @ConstructorResult(
                                targetClass = ReportCustomerCarrierDto.class,
                                columns = {
                                        @ColumnResult(name = "parent_customer_name", type = String.class),
                                        @ColumnResult(name = "parent_customer_id", type = Long.class),
                                        @ColumnResult(name = "customer_id", type = Long.class),
                                        @ColumnResult(name = "customer_name", type = String.class),
                                        @ColumnResult(name = "shipper_id", type = Long.class),
                                        @ColumnResult(name = "shipper_name", type = String.class),
                                        @ColumnResult(name = "carrier_id", type = Long.class),
                                        @ColumnResult(name = "shipper_group_id", type = Long.class),
                                        @ColumnResult(name = "paid_cust", type = Boolean.class),
                                        @ColumnResult(name = "shipper_group_name", type = String.class),
                                        @ColumnResult(name = "region", type = String.class),
                                        @ColumnResult(name = "currency_id", type = String.class),
                                }
                        )
                }

        ),
        @SqlResultSetMapping(
                name = "ReportCustomerCarrierDto.RateCustomers",
                classes = {
                        @ConstructorResult(
                                targetClass = ReportCustomerCarrierDto.class,
                                columns = {
                                        @ColumnResult(name = "customer_id", type = Long.class),
                                        @ColumnResult(name = "customer_name", type = String.class),
                                        @ColumnResult(name = "parent_customer_id", type = Long.class),
                                        @ColumnResult(name = "customer_code", type = String.class)
                                }
                        )
                }
        )
})

@Entity
public class ReportCustomerCarrierDto implements Serializable,Comparable<ReportCustomerCarrierDto> {
    @Id
    private Long id;

    @Column(name="customer_id")
    private Long customerId;

    @Column(name="customer_name")
    private String customerName;

    @Column(name="carrier_ids")
    private String customerCarrierId;

    @Column(name="is_child")
    private Boolean isChild;

    @Column(name="parent_customer_id")
    private Long parentCustomerId;

    @Column(name="parent_customer_name")
    private String parentCustomerName;

    @Column(name="shipper_id")
    private Long shipperId;

    @Column(name = "shipper_name")
    private String shipperName;

    @Column(name="ship_code_name")
    private String shipCodeName;

    @Column(name="shipper_group_id")
    private Long shipperGroupId;

    @Column(name="paid_cust")
    private Boolean paidCust;

    @Column(name="shipper_group_name")
    private String shipperGroupName;

    @Column(name="selected")
    private Boolean selected;

    @Column(name="carrier_id")
    private Long carrierId;

    @Column(name="carrier_name")
    private String carrierName;

    @Column(name="isLtl")
    private Boolean isLtl;

    @Column(name="value")
    private String value;

    private String type;

    @Column(name = "region")
    private String region;

    @Column(name = "currency_id")
    private String currencyId;

    @Column(name = "customer_code")
    private String customerCode;

    private TreeSet<ReportCustomerCarrierDto> collection = new TreeSet<ReportCustomerCarrierDto>();

    public ReportCustomerCarrierDto() { }

    public ReportCustomerCarrierDto(String value) { this.value = value;  }

    public ReportCustomerCarrierDto( Long carrierId, String carrierName, Boolean isLtl,Boolean selected) {
        this.carrierId = carrierId;
        this.carrierName = carrierName;
        this.isLtl = isLtl;
        this.selected = selected;
    }
    public ReportCustomerCarrierDto(Long customerId, String customerName,Long parentCustomerId, String customerCode) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.parentCustomerId = parentCustomerId;
        this.customerCode = customerCode;
    }

    public ReportCustomerCarrierDto(Long customerId, String customerName, String customerCarrierId, Boolean isChild, Long parentCustomerId, String parentCustomerName, Long shipperId, String shipCodeName, Long shipperGroupId, Boolean paidCust, String shipperGroupName, Boolean selected) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.customerCarrierId = customerCarrierId;
        this.isChild = isChild;
        this.parentCustomerId = parentCustomerId;
        this.parentCustomerName = parentCustomerName;
        this.shipperId = shipperId;
        this.shipCodeName = shipCodeName;
        this.shipperGroupId = shipperGroupId;
        this.paidCust = paidCust;
        this.shipperGroupName=shipperGroupName;
        this.selected=selected;
    }

    public ReportCustomerCarrierDto(String name, long id, boolean selected, String customerCarrierId, String type, Long parentId, String parentName, boolean paidCust, String region, String currencyId) {
        this.customerName = name;
        this.customerId = id;
        this.selected = selected;
        this.customerCarrierId = customerCarrierId;
        this.type = type;
        if(parentId != null) {
            this.parentCustomerId = parentId;
        }
        this.parentCustomerName = parentName;
        this.paidCust = paidCust;
        this.region = region;
        this.currencyId = currencyId;
    }

    public ReportCustomerCarrierDto(String parentCustomerName, Long parentCustomerId, Long customerId, String customerName, Long shipperId, String shipperName, Long carrierId, Long shipperGroupId, Boolean paidCust, String shipperGroupName, String region, String currencyId) {
        this.parentCustomerName = parentCustomerName;
        this.parentCustomerId = parentCustomerId;
        this.customerId = customerId;
        this.customerName = customerName;
        this.shipperId = shipperId;
        this.shipperName = shipperName;
        this.carrierId = carrierId;
        this.shipperGroupId = shipperGroupId;
        this.paidCust = paidCust;
        this.shipperGroupName = shipperGroupName;
        this.region = region;
        this.currencyId = currencyId;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerCarrierId() {
        return customerCarrierId;
    }

    public void setCustomerCarrierId(String customerCarrierId) {
        this.customerCarrierId = customerCarrierId;
    }

    public Long getParentCustomerId() { return parentCustomerId;  }

    public void setParentCustomerId(Long parentCustomerId) {  this.parentCustomerId = parentCustomerId;   }

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

    public Boolean getIsChild() { return isChild;  }

    public void setIsChild(Boolean child) {   isChild = child;   }

    public String getParentCustomerName() {    return parentCustomerName;   }

    public void setParentCustomerName(String parentCustomerName) { this.parentCustomerName = parentCustomerName;   }

    public Long getShipperId() {    return shipperId;  }

    public void setShipperId(Long shipperId) { this.shipperId = shipperId;  }

    public String getShipperName() {
        return shipperName;
    }

    public void setShipperName(String shipperName) {
        this.shipperName = shipperName;
    }

    public String getShipCodeName() {  return shipCodeName;  }

    public void setShipCodeName(String shipCodeName) { this.shipCodeName = shipCodeName;  }

    public Long getShipperGroupId() {  return shipperGroupId; }

    public void setShipperGroupId(Long shipperGroupId) {  this.shipperGroupId = shipperGroupId;  }

    public Boolean getPaidCust() {   return paidCust;  }

    public void setPaidCust(Boolean paidCust) {   this.paidCust = paidCust;   }

    public String getShipperGroupName() { return shipperGroupName;   }

    public void setShipperGroupName(String shipperGroupName) { this.shipperGroupName = shipperGroupName; }

    public TreeSet<ReportCustomerCarrierDto> getCollection() {  return collection;   }

    public void setCollection(TreeSet<ReportCustomerCarrierDto> collection) {   this.collection = collection;   }

    public String getValue() {  return value; }

    public void setValue(String value) { this.value = value; }

    public Boolean getSelected() { return selected; }

    public Boolean getIsLtl() {  return isLtl; }

    public void setIsLtl(Boolean ltl) {  isLtl = ltl;  }

    public void setSelected(Boolean selected) { this.selected = selected;  }

    public String getType() {    return type;   }

    public void setType(String type) {  this.type = type;  }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getCurrencyId() {
        return currencyId;
    }

    public void setCurrencyId(String currencyId) {
        this.currencyId = currencyId;
    }

    public String getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(String customerCode) {
        this.customerCode = customerCode;
    }

    @Override
    public int compareTo(ReportCustomerCarrierDto dto) {
        return this.getCustomerName().compareToIgnoreCase(dto.getCustomerName());
    }
}