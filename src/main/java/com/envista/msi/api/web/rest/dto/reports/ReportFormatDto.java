package com.envista.msi.api.web.rest.dto.reports;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by Siddhant on 20/03/2017.
 */
@NamedStoredProcedureQueries({
        @NamedStoredProcedureQuery(name = "ReportFormat.getReportFormat", procedureName = "shp_rpt_report_format_proc",
                resultSetMappings = "ReportFormat",
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_rpt_id", type = Long.class),
                        @StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, name = "get_report_format", type = Void.class)
        }),
        @NamedStoredProcedureQuery(name = "ReportFormat.getReportDateOptions", procedureName = "shp_rpt_date_criteria_proc",
                resultSetMappings = "ReportDateOptions",
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_rpt_id", type = Long.class),
                        @StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, name = "p_refcur_date_criteria_info", type = Void.class)
        }),
        @NamedStoredProcedureQuery(name = "ReportFormat.getControlNumber", procedureName = "shp_rpt_lookup_ctrl_no_proc",
                resultSetMappings = "ReportControlNumber",
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_customer_ids", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_pay_run_no", type = Integer.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_check_no", type = Integer.class),
                        @StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, name = "get_lookup_ctrl_no", type = Void.class)
        })
})
@SqlResultSetMappings({
        @SqlResultSetMapping(name = "ReportFormat", classes = {
                @ConstructorResult(
                        targetClass = ReportFormatDto.class,
                        columns = {
                                @ColumnResult(name ="type_id", type = Long.class),
                                @ColumnResult(name ="report_format", type = String.class),
                                @ColumnResult(name ="selected", type = Boolean.class)
                        })
        }),
        @SqlResultSetMapping(name = "ReportDateOptions", classes = {
                @ConstructorResult(
                        targetClass = ReportFormatDto.class,
                        columns = {
                                @ColumnResult(name ="date_criteria", type = String.class),
                                @ColumnResult(name ="rpt_date_options_id", type = Long.class),
                                @ColumnResult(name ="is_default", type = Boolean.class),
                                @ColumnResult(name ="selected", type = Boolean.class)
                        })
        }),
        @SqlResultSetMapping(name = "ReportControlNumber", classes = {
                @ConstructorResult(
                        targetClass = ReportFormatDto.class,
                        columns = {
                                @ColumnResult(name ="control_number", type = String.class),
                        })
        })
})
@Entity
public class ReportFormatDto implements Serializable {
    @Id
    private Long id;

    @Column(name="type_id")
    private Long typeId;

    @Column(name="report_format")
    private String reportFormat;

    @Column(name="selected")
    private Boolean selected;

    @Column(name="rpt_date_options_id")
    private Long rptDateOptionId;

    @Column(name="date_criteria")
    private String dateCriteriaName;

    @Column(name="is_default")
    private Boolean isDefault;

    @Column(name="control_number")
    private String controlNumber;

    private String customerIds;

    private Integer payRunNo;

    private Integer checkNo;

    public ReportFormatDto() { }

    public ReportFormatDto(String controlNumber) {
        this.controlNumber=controlNumber;
    }

    public ReportFormatDto(Long typeId, String reportFormat,Boolean selected) {
        this.typeId = typeId;
        this.reportFormat = reportFormat;
        this.selected=selected;
    }

    public ReportFormatDto(String dateCriteriaName ,Long rptDateOptionId,Boolean isDefault,Boolean selected) {
        this.rptDateOptionId = rptDateOptionId;
        this.dateCriteriaName = dateCriteriaName;
        this.isDefault=isDefault;
        this.selected=selected;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTypeId() {
        return typeId;
    }

    public void setTypeId(Long typeId) {
        this.typeId = typeId;
    }

    public String getReportFormat() {
        return reportFormat;
    }

    public void setReportFormat(String reportFormat) {
        this.reportFormat = reportFormat;
    }

    public Long getRptDateOptionId() { return rptDateOptionId;  }

    public void setRptDateOptionId(Long rptDateOptionId) {  this.rptDateOptionId = rptDateOptionId; }

    public String getDateCriteriaName() { return dateCriteriaName;  }

    public void setDateCriteriaName(String dateCriteriaName) { this.dateCriteriaName = dateCriteriaName; }

    public Boolean getSelected() { return selected; }

    public void setSelected(Boolean selected) { this.selected = selected;  }

    public Boolean getIsDefault() { return isDefault; }

    public void setIsDefault(Boolean isDefault) { this.isDefault = isDefault; }

    public String getControlNumber() { return controlNumber;  }

    public void setControlNumber(String controlNumber) { this.controlNumber = controlNumber; }

    public String getCustomerIds() { return customerIds; }

    public void setCustomerIds(String customerIds) { this.customerIds = customerIds; }

    public Integer getPayRunNo() { return payRunNo; }

    public void setPayRunNo(Integer payRunNo) { this.payRunNo = payRunNo; }

    public Integer getCheckNo() { return checkNo; }

    public void setCheckNo(Integer checkNo) {  this.checkNo = checkNo; }
}