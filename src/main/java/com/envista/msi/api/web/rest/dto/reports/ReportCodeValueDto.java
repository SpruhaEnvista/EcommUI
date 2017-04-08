package com.envista.msi.api.web.rest.dto.reports;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Siddhant on 04/04/2017.
 */
@NamedStoredProcedureQueries({
        @NamedStoredProcedureQuery(name = "ReportCodeValueDto.getReportLocaleLabel", procedureName = "shp_rpt_locale_label_proc",
                resultClasses = ReportCodeValueDto.class,
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_rpt_id", type = Long.class),
                        @StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, name = "p_refcur_locale_info", type = Void.class)
        }),
        @NamedStoredProcedureQuery(name = "ReportCodeValueDto.getReportCurrencyLabel", procedureName = "shp_rpt_currency_label_proc",
                resultClasses = ReportCodeValueDto.class,
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_rpt_id", type = Long.class),
                        @StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, name = "p_refcur_currency_info", type = Void.class)
        }),
        @NamedStoredProcedureQuery(name = "ReportCodeValueDto.getReportWeightLabel", procedureName = "shp_rpt_wieght_proc",
                resultSetMappings ="ReportWeight",
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_rpt_id", type = Long.class),
                        @StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, name = "p_refcur_weight_info", type = Void.class)
        })
})
@SqlResultSetMappings({
        @SqlResultSetMapping(name = "ReportWeight", classes = {
                @ConstructorResult(
                        targetClass = ReportCodeValueDto.class,
                        columns = {
                                @ColumnResult(name = "nsp_code_value_id", type = Long.class),
                                @ColumnResult(name = "code_value", type = String.class),
                                @ColumnResult(name = "property_1", type = String.class),
                                @ColumnResult(name = "selected", type = Boolean.class)
                        })
        })
})
@Entity
public class ReportCodeValueDto implements Serializable {
    @Id
    @Column(name="nsp_code_value_id")
    private Long codeValueId;

    @Column(name="code_group_id")
    private Long codeGroupId;

    @Column(name="code_value")
    private String codeValue;

    @Column(name="property_1")
    private String property1;

    @Column(name="property_2")
    private String property2;

    @Column(name="property_3")
    private String property3;

    @Column(name="property_4")
    private String property4;

    @Column(name="property_5")
    private String property5;

    @Column(name="property_6")
    private String property6;

    @Column(name="property_7")
    private String property7;

    @Column(name="property_8")
    private String property8;

    @Column(name="property_9")
    private String property9;

    @Column(name="selected")
    private Boolean selected;

    public ReportCodeValueDto() { }

    public ReportCodeValueDto(Long codeValueId, String codeValue, String property1, Boolean selected) {
        this.codeValueId = codeValueId;
        this.codeValue = codeValue;
        this.property1 = property1;
        this.selected = selected;
    }

    public ReportCodeValueDto(Long codeValueId, Long codeGroupId, String codeValue, String property1, String property2, String property3, String property4, String property5, String property6, String property7, String property8, String property9, Boolean selected) {
        this.codeValueId = codeValueId;
        this.codeGroupId = codeGroupId;
        this.codeValue = codeValue;
        this.property1 = property1;
        this.property2 = property2;
        this.property3 = property3;
        this.property4 = property4;
        this.property5 = property5;
        this.property6 = property6;
        this.property7 = property7;
        this.property8 = property8;
        this.property9 = property9;
        this.selected = selected;
    }

    public Long getCodeValueId() {  return codeValueId; }

    public void setCodeValueId(Long codeValueId) { this.codeValueId = codeValueId;  }

    public Long getCodeGroupId() { return codeGroupId; }

    public void setCodeGroupId(Long codeGroupId) { this.codeGroupId = codeGroupId;  }

    public String getCodeValue() { return codeValue;  }

    public void setCodeValue(String codeValue) { this.codeValue = codeValue; }

    public String getProperty1() { return property1; }

    public void setProperty1(String property1) { this.property1 = property1; }

    public String getProperty2() { return property2; }

    public void setProperty2(String property2) { this.property2 = property2; }

    public String getProperty3() { return property3; }

    public void setProperty3(String property3) { this.property3 = property3; }

    public String getProperty4() { return property4; }

    public void setProperty4(String property4) {  this.property4 = property4; }

    public String getProperty5() { return property5; }

    public void setProperty5(String property5) {  this.property5 = property5; }

    public String getProperty6() {  return property6;  }

    public void setProperty6(String property6) {  this.property6 = property6; }

    public String getProperty7() {  return property7;  }

    public void setProperty7(String property7) { this.property7 = property7;  }

    public String getProperty8() { return property8;  }

    public void setProperty8(String property8) { this.property8 = property8; }

    public String getProperty9() { return property9; }

    public void setProperty9(String property9) { this.property9 = property9;  }

    public boolean getSelected() { return selected;  }

    public void setSelected(boolean selected) { this.selected = selected;  }
}
