package com.envista.msi.api.web.rest.dto;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Sujit kumar on 14/09/2017.
 */
@NamedStoredProcedureQueries({
        @NamedStoredProcedureQuery(
                name = "ShipperDto.getShipperDetails",
                procedureName = "SHP_GET_SHIPPER_PROC",
                resultSetMappings = {"ShipperDto.getShipperDetailsMapping"},
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_shipper_ids", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_shipper_names", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_shipper_codes", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, name = "p_shipper_list", type = void.class)
                }
        )
})

@SqlResultSetMappings({
        @SqlResultSetMapping(
                name = "ShipperDto.getShipperDetailsMapping",
                classes = {
                        @ConstructorResult(
                                targetClass = ShipperDto.class,
                                columns = {
                                        @ColumnResult(name = "SHIPPER_ID", type = Long.class),
                                        @ColumnResult(name = "SHIPPER_NAME", type = String.class),
                                        @ColumnResult(name = "SHIPPER_CODE", type = String.class),
                                        @ColumnResult(name = "ADDRESS1", type = String.class),
                                        @ColumnResult(name = "CITY", type = String.class),
                                        @ColumnResult(name = "STATE", type = String.class),
                                        @ColumnResult(name = "ZIPCODE", type = String.class),
                                        @ColumnResult(name = "COUNTRY", type = String.class),
                                        @ColumnResult(name = "PHONE", type = String.class),
                                        @ColumnResult(name = "FAX", type = String.class),
                                        @ColumnResult(name = "EMAIL", type = String.class),
                                        @ColumnResult(name = "CUSTOMER_ID", type = Long.class),
                                        @ColumnResult(name = "ADDRESS2", type = String.class)
                                }
                        )
                }
        )
})

@Entity
public class ShipperDto implements Serializable {
    @Id
    @Column(name = "SHIPPER_ID")
    private Long shipperId;

    @Column(name = "SHIPPER_NAME")
    private String shipperName;

    @Column(name = "SHIPPER_CODE")
    private String shipperCode;

    @Column(name = "ADDRESS1")
    private String address1;

    @Column(name = "CITY")
    private String city;

    @Column(name = "STATE")
    private String state;

    @Column(name = "ZIPCODE")
    private String zipCode;

    @Column(name = "COUNTRY")
    private String country;

    @Column(name = "PHONE")
    private String phone;

    @Column(name = "FAX")
    private String fax;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "CUSTOMER_ID")
    private Long customerId;

    @Column(name = "ADDRESS2")
    private String address2;

    public ShipperDto() {
    }

    public ShipperDto(Long shipperId, String shipperName, String shipperCode, String address1, String city, String state, String zipCode, String country, String phone, String fax, String email, Long customerId, String address2) {
        this.shipperId = shipperId;
        this.shipperName = shipperName;
        this.shipperCode = shipperCode;
        this.address1 = address1;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
        this.country = country;
        this.phone = phone;
        this.fax = fax;
        this.email = email;
        this.customerId = customerId;
        this.address2 = address2;
    }

    public Long getShipperId() {
        return shipperId;
    }

    public void setShipperId(Long shipperId) {
        this.shipperId = shipperId;
    }

    public String getShipperName() {
        return shipperName;
    }

    public void setShipperName(String shipperName) {
        this.shipperName = shipperName;
    }

    public String getShipperCode() {
        return shipperCode;
    }

    public void setShipperCode(String shipperCode) {
        this.shipperCode = shipperCode;
    }

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }
}
