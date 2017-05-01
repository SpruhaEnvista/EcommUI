package com.envista.msi.api.web.rest.dto.invoicing;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by KRISHNAREDDYM on 4/25/2017.
 */

@NamedStoredProcedureQueries({
        @NamedStoredProcedureQuery(name = "CodeValueDto.getCodeValues", procedureName = "SHP_INV_GET_CODE_VALUES_PRO",
                resultClasses = CodeValueDto.class,
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_CARRIER_ID", type = Long.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_CODE_GROUP_ID", type = Long.class),
                        @StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, name = "P_REFCUR_CODES_INFO", type = Void.class)
                })
})

@Entity
public class CodeValueDto implements Serializable {

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

    public CodeValueDto() {
    }

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
}
