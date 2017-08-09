package com.envista.msi.api.web.rest.dto.freight.invoice;

import com.envista.msi.api.domain.util.FreightStoreProcParam;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Sujit kumar on 20/07/2017.
 */

@NamedStoredProcedureQueries({
        @NamedStoredProcedureQuery(name = "InvoiceCodeValuesDto.getCodeValuesByDynamicParamValues", procedureName = "SHP_FRT_GET_CODE_VALUES_PROC",
                resultClasses = InvoiceCodeValuesDto.class,
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = FreightStoreProcParam.CodeValuesParam.CODE_VALUE_ID_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = FreightStoreProcParam.CodeValuesParam.CODE_GROUP_ID_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = FreightStoreProcParam.CodeValuesParam.PROPERTY_1_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = FreightStoreProcParam.CodeValuesParam.PROPERTY_2_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = FreightStoreProcParam.CodeValuesParam.PROPERTY_3_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = FreightStoreProcParam.CodeValuesParam.PROPERTY_4_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = FreightStoreProcParam.CodeValuesParam.PROPERTY_5_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = FreightStoreProcParam.CodeValuesParam.PROPERTY_6_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = FreightStoreProcParam.CodeValuesParam.PROPERTY_7_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = FreightStoreProcParam.CodeValuesParam.PROPERTY_8_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = FreightStoreProcParam.CodeValuesParam.PROPERTY_9_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = FreightStoreProcParam.CodeValuesParam.ORDER_BY_PARAM, type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = FreightStoreProcParam.CodeValuesParam.SELECT_ACTIVE_INACTIVE_PARAM, type = Integer.class),
                        @StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, name = FreightStoreProcParam.CodeValuesParam.CODE_VALUES_INFO_PARAM, type = Void.class)
                }),
        @NamedStoredProcedureQuery(name = "InvoiceCodeValuesDto.getFreightInvoiceLookupColumns", procedureName = "SHP_FRT_INV_USER_DEF_COLS_PROC",
                resultSetMappings = {"InvoiceCodeValuesDto.getFreightInvoiceLookupColumnsMapping"},
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "P_USER_ID", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, name = "P_USER_DEF_COLS", type = Void.class)
                })
})

@SqlResultSetMappings({
        @SqlResultSetMapping(name = "InvoiceCodeValuesDto.getFreightInvoiceLookupColumnsMapping", classes = {
                @ConstructorResult(targetClass = InvoiceCodeValuesDto.class,
                        columns = {
                                @ColumnResult(name = "NSP_CODE_VALUE_ID", type = Long.class),
                                @ColumnResult(name = "CODE_VALUE", type = String.class),
                                @ColumnResult(name = "PROPERTY_1", type = String.class),
                                @ColumnResult(name = "PROPERTY_2", type = String.class),
                                @ColumnResult(name = "PROPERTY_3", type = String.class)
                        })
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

    public InvoiceCodeValuesDto() {
    }

    public InvoiceCodeValuesDto(Long id, String codeValue, String property1, String property2, String property3) {
        this.id = id;
        this.codeValue = codeValue;
        this.property1 = property1;
        this.property2 = property2;
        this.property3 = property3;
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

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof InvoiceCodeValuesDto){
            InvoiceCodeValuesDto codeValue = ((InvoiceCodeValuesDto) obj);
            return (codeValue.getId() != null && this.getId() != null && codeValue.getId().compareTo(this.getId()) == 0);
        }
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        if(this.getId() != null){
            return this.getId().intValue();
        }
        return super.hashCode();
    }
}
