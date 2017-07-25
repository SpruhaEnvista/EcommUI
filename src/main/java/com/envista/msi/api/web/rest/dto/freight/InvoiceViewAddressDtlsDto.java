package com.envista.msi.api.web.rest.dto.freight;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Sreedhar.T on 7/20/2017.
 */
@NamedStoredProcedureQueries({
        @NamedStoredProcedureQuery(name = "InvViewAddrDtls.invoiceAddrDetails", procedureName = "shp_frt_inv_htab_addr_proc",
                resultClasses = InvoiceViewAddressDtlsDto.class,
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, name = "refCur", type = Void.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "invoiceId", type = Long.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "addrType", type = String.class)
                })
})
@Entity
public class InvoiceViewAddressDtlsDto implements Serializable{

    @Id
    @Column(name = "rownum")
    private Long rowNum;

    @Column(name = "name1")
    private String name;

    @Column(name = "company")
    private String company;

    @Column(name = "address1")
    private String address1;

    @Column(name = "address2")
    private String address2;

    @Column(name = "city")
    private String city;

    @Column(name = "state")
    private String state;

    @Column(name = "zipcode")
    private String zipcode;

    @Column(name = "country")
    private String country;

    public Long getRowNum() {
        return rowNum;
    }

    public void setRowNum(Long rowNum) {
        this.rowNum = rowNum;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
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

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
