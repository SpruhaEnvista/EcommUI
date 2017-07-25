package com.envista.msi.api.web.rest.dto.freight.invoice;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Sujit kumar on 20/07/2017.
 */

@NamedStoredProcedureQueries({
        @NamedStoredProcedureQuery(name = "InvoiceCodeValuesDto.getCodeValuesByDynamicParamValues", procedureName = "SHP_FRT_GET_CODE_VALUES_PROC",
                resultClasses = InvoiceCodeValuesDto.class,
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_NSP_CODE_VALUE_ID", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_CODE_GROUP_ID", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_PROPERTY_1", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_PROPERTY_2", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_PROPERTY_3", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_PROPERTY_4", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_PROPERTY_5", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_PROPERTY_6", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_PROPERTY_7", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_PROPERTY_8", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_PROPERTY_9", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_ORDER_BY", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_SELECT_ACTIVE_INACTIVE", type = Integer.class),
                        @StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, name = "P_CODE_VALUES_INFO", type = Void.class)
                })
})

@Entity
public class InvoiceCodeValuesDto implements Serializable {
    @Id
    @Column(name = "NSP_CODE_VALUE_ID")
    private Long id;

    @Column(name = "CODE_VALUE")
    private String codeValue;

    @Column(name = "CODE_GROUP_ID")
    private Long codeGroupId;

    @Column(name = "PROPERTY_1")
    private String property1;

    @Column(name = "PROPERTY_2")
    private String property2;

    @Column(name = "PROPERTY_3")
    private String property3;

    @Column(name = "PROPERTY_4")
    private String property4;

    @Column(name = "PROPERTY_5")
    private String property5;

    @Column(name = "PROPERTY_6")
    private String property6;

    @Column(name = "PROPERTY_7")
    private String property7;

    @Column(name = "PROPERTY_8")
    private String property8;

    @Column(name = "PROPERTY_9")
    private String property9;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodeValue() {
        return codeValue;
    }

    public void setCodeValue(String codeValue) {
        this.codeValue = codeValue;
    }

    public Long getCodeGroupId() {
        return codeGroupId;
    }

    public void setCodeGroupId(Long codeGroupId) {
        this.codeGroupId = codeGroupId;
    }

    /*public String getProtect() {
        return protect;
    }

    public void setProtect(String protect) {
        this.protect = protect;
    }

    public String getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }*/

    public String getProperty1() {
        return property1;
    }

    public void setProperty1(String property1) {
        this.property1 = property1;
    }

    public String getProperty2() {
        return property2;
    }

    public void setProperty2(String property2) {
        this.property2 = property2;
    }

    public String getProperty3() {
        return property3;
    }

    public void setProperty3(String property3) {
        this.property3 = property3;
    }

    public String getProperty4() {
        return property4;
    }

    public void setProperty4(String property4) {
        this.property4 = property4;
    }

    public String getProperty5() {
        return property5;
    }

    public void setProperty5(String property5) {
        this.property5 = property5;
    }

    public String getProperty6() {
        return property6;
    }

    public void setProperty6(String property6) {
        this.property6 = property6;
    }

    public String getProperty7() {
        return property7;
    }

    public void setProperty7(String property7) {
        this.property7 = property7;
    }

    public String getProperty8() {
        return property8;
    }

    public void setProperty8(String property8) {
        this.property8 = property8;
    }

    public String getProperty9() {
        return property9;
    }

    public void setProperty9(String property9) {
        this.property9 = property9;
    }

    /*public Long getSequence() {
        return sequence;
    }

    public void setSequence(Long sequence) {
        this.sequence = sequence;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getTaxable() {
        return taxable;
    }

    public void setTaxable(String taxable) {
        this.taxable = taxable;
    }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }*/
}
