package com.envista.msi.api.web.rest.dto.reports;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Sreenivas on 4/6/2017.
 */
@NamedStoredProcedureQueries({
        @NamedStoredProcedureQuery(name = "SavedSchedReports.saveSchedReport", procedureName = "shp_rpt_saveschedule_proc",
                resultSetMappings = "SavedSchedReport",
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, name = "p_savedSchedCusr", type = Void.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "rptId", type = Long.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "isScheduled", type = Boolean.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "rptDateOptionsId", type = Integer.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "reportTypeId", type = Integer.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "reportFileName", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "dateSelectionFrequency", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "date1", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "date2", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "periodOption", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "lastNoOfDays", type = Integer.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "scTriggerBy", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "scScheduleType", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "scWeeklyFrequency", type = Integer.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "scWeeklyMonthlyDayofWeek", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "scMonthlyDayOfMonth", type = Integer.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "scMonthlyNoOfMonths", type = Integer.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "scMonthlyPeriodicFreq", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "svReportStatus", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "scNextSubmitDate", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "carrierIds", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "controlPayrunNumber", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "consolidate", type = Boolean.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "createUser", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "criteria", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "dateRangeTodayMinus1", type = Integer.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "dateRangeTodayMinus2", type = Integer.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "ftpAccountsId", type = Long.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "isSuppressInvoices", type = Boolean.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "submittedFromSystem", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "isPacket", type = Boolean.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "flagsJson", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "locale", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "currency", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "weightUom", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "rptDescr", type = String.class)
                }),
        @NamedStoredProcedureQuery(name = "SavedSchedReports.updateSchedReport", procedureName = "shp_rpt_update_savshde_proc",
                resultSetMappings = "UpdateSavedSchedReport",
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, name = "p_savedSchedUpCusr", type = Void.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "savedSchedRptId", type = Long.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "rptId", type = Long.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "isScheduled", type = Boolean.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "rptDateOptionsId", type = Integer.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "reportTypeId", type = Integer.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "reportFileName", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "dateSelectionFrequency", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "date1", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "date2", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "periodOption", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "lastNoOfDays", type = Integer.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "scTriggerBy", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "scScheduleType", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "scWeeklyFrequency", type = Integer.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "scWeeklyMonthlyDayofWeek", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "scMonthlyDayOfMonth", type = Integer.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "scMonthlyNoOfMonths", type = Integer.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "scMonthlyPeriodicFreq", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "svReportStatus", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "scNextSubmitDate", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "carrierIds", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "controlPayrunNumber", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "consolidate", type = Boolean.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "createUser", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "criteria", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "dateRangeTodayMinus1", type = Integer.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "dateRangeTodayMinus2", type = Integer.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "ftpAccountsId", type = Long.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "isSuppressInvoices", type = Boolean.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "submittedFromSystem", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "isPacket", type = Boolean.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "flagsJson", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "locale", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "currency", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "weightUom", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "rptDescr", type = String.class)
                }),
        @NamedStoredProcedureQuery(name = "SavedSchedReports.deleteChildDataSchedReport", procedureName = "shp_rpt_delete_records_mt_proc",
                resultSetMappings = "UpdateSavedSchedReport",
                parameters = {
                    @StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, name = "deletedRows", type = Void.class),
                    @StoredProcedureParameter(mode = ParameterMode.IN, name = "savedSchedRptId", type = Long.class)
                }),
        @NamedStoredProcedureQuery(name = "SavedSchedReports.deletePacketDataSchedReport", procedureName = "SHP_DELETE_RPT_PKT_DETAILS_PRO",
                resultSetMappings = "UpdateSavedSchedReport",
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, name = "deletedRows", type = Void.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "savedSchedRptId", type = Long.class)
                }),
        @NamedStoredProcedureQuery(name = "SavedSchedReports.getReportDetails", procedureName = "shp_rpt_get_report_details",
                resultSetMappings = "ReportDetails",
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, name = "reportDetails", type = Void.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "savedSchedRptId", type = Long.class)
                }),
        @NamedStoredProcedureQuery(name = "SavedSchedReportDto.getReportDetailsByIds", procedureName = "SHP_RPT_REPORT_DETAILS_PROC",
                resultSetMappings = {"SavedSchedReportDto.getReportDetailsByIdsMapping"},
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_report_ids", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, name = "reportDetails", type = Void.class)
                }
        )
})

@SqlResultSetMappings({
        @SqlResultSetMapping(name = "SavedSchedReport",
                classes = {
                @ConstructorResult(
                        targetClass = SavedSchedReportDto.class,
                        columns = {
                                @ColumnResult(name = "saved_sched_rpt_id", type = Long.class)
                        }
                )
        }),
        @SqlResultSetMapping(name = "UpdateSavedSchedReport",
                classes = {
                @ConstructorResult(
                        targetClass = SavedSchedReportDto.class,
                        columns = {
                                @ColumnResult(name = "updateCount", type = Integer.class)
                        }
                )
        }),
        @SqlResultSetMapping(name = "ReportDetails",
                classes = {
                @ConstructorResult(
                        targetClass = SavedSchedReportDto.class,
                        columns = {
                                @ColumnResult(name = "saved_sched_rpt_id", type = Long.class),
                                @ColumnResult(name = "rpt_id", type = Long.class),
                                @ColumnResult(name = "is_scheduled", type = Boolean.class),
                                @ColumnResult(name = "rpt_date_options_id", type = Integer.class),
                                @ColumnResult(name = "report_type_id", type = Integer.class),
                                @ColumnResult(name = "report_file_name", type = String.class),
                                @ColumnResult(name = "date_selection_frequency", type = String.class),
                                @ColumnResult(name = "date1", type = String.class),
                                @ColumnResult(name = "date2", type = String.class),
                                @ColumnResult(name = "period_option", type = String.class),
                                @ColumnResult(name = "last_no_of_days", type = Integer.class),
                                @ColumnResult(name = "sc_trigger_by", type = String.class),
                                @ColumnResult(name = "sc_schedule_type", type = String.class),
                                @ColumnResult(name = "sc_weekly_frequency", type = Integer.class),
                                @ColumnResult(name = "sc_weekly_monthly_day_of_week", type = String.class),
                                @ColumnResult(name = "sc_monthly_day_of_month", type = Integer.class),
                                @ColumnResult(name = "sc_monthly_no_of_months", type = Integer.class),
                                @ColumnResult(name = "sc_monthly_periodic_frequency", type = String.class),
                                @ColumnResult(name = "sv_report_status", type = String.class),
                                @ColumnResult(name = "submitted_on", type = String.class),
                                @ColumnResult(name = "sc_next_submit_date", type = String.class),
                                @ColumnResult(name = "carrier_ids", type = String.class),
                                @ColumnResult(name = "control_payrun_number", type = String.class),
                                @ColumnResult(name = "consolidate", type = Boolean.class),
                                @ColumnResult(name = "failed_attempts", type = Integer.class),
                                @ColumnResult(name = "create_user", type = String.class),
                                @ColumnResult(name = "create_date", type = String.class),
                                @ColumnResult(name = "last_update_user", type = String.class),
                                @ColumnResult(name = "last_update_date", type = String.class),
                                @ColumnResult(name = "criteria", type = String.class),
                                @ColumnResult(name = "date_range_today_minus1", type = Integer.class),
                                @ColumnResult(name = "date_range_today_minus2", type = Integer.class),
                                @ColumnResult(name = "ftp_accounts_id", type = Long.class),
                                @ColumnResult(name = "is_suppress_invoices", type = Boolean.class),
                                @ColumnResult(name = "high_priority", type = Integer.class),
                                @ColumnResult(name = "submitted_from_system", type = String.class),
                                @ColumnResult(name = "is_packet", type = Boolean.class),
                                @ColumnResult(name = "queue_name", type = String.class),
                                @ColumnResult(name = "flags_json", type = String.class),
                                @ColumnResult(name = "locale", type = String.class),
                                @ColumnResult(name = "currency", type = String.class),
                                @ColumnResult(name = "weight_uom", type = String.class),
                                @ColumnResult(name = "rpt_descr", type = String.class),
                                @ColumnResult(name = "rpt_folder_id", type = Long.class)
                        }
                )
        }),
        @SqlResultSetMapping(name = "SavedSchedReportDto.getReportDetailsByIdsMapping",
                classes = {
                        @ConstructorResult(
                                targetClass = SavedSchedReportDto.class,
                                columns = {
                                        @ColumnResult(name = "saved_sched_rpt_id", type = Long.class),
                                        @ColumnResult(name = "rpt_id", type = Long.class),
                                        @ColumnResult(name = "criteria", type = String.class)
                                }
                        )
                })
})

@Entity
public class SavedSchedReportDto {

    @Id
    @Column(name = "saved_sched_rpt_id")
    private Long savedSchedRptId;

    @Column(name = "rpt_id")
    private Long rptId;

    @Column(name = "is_scheduled")
    private Boolean scheduled;

    @Column(name = "rpt_date_options_id")
    private Integer rptDateOptionsId;

    @Column(name = "report_type_id")
    private Integer reportTypeId;

    @Column(name = "report_file_name")
    private String reportFileName;

    @Column(name = "date_selection_frequency")
    private String dateSelectionFrequency;

    @Column(name = "date1")
    private String date1;

    @Column(name = "date2")
    private String date2;

    @Column(name = "period_option")
    private String periodOption;

    @Column(name = "last_no_of_days")
    private Integer lastNoOfDays;

    @Column(name = "sc_trigger_by")
    private String scTriggerBy;

    @Column(name = "sc_schedule_type")
    private String scScheduleType;

    @Column(name = "sc_weekly_frequency")
    private Integer scWeeklyFrequency;

    @Column(name = "sc_weekly_monthly_day_of_week")
    private String scWeeklyMonthlyDayofWeek;

    @Column(name = "sc_monthly_day_of_month")
    private Integer scMonthlyDayOfMonth;

    @Column(name = "sc_monthly_no_of_months")
    private Integer scMonthlyNoOfMonths;

    @Column(name = "sc_monthly_periodic_frequency")
    private  String scMonthlyPeriodicFrequency;

    @Column(name = "sv_report_status")
    private String svReportStatus;

    @Column(name = "submitted_on")
    private String submittedOn;

    @Column(name = "sc_next_submit_date")
    private String scNextSubmitDate;

    @Column(name = "carrier_ids")
    private String carrierIds;

    @Column(name = "control_payrun_number")
    private String controlPayrunNumber;

    @Column(name = "consolidate")
    private Boolean consolidate;

    @Column(name = "failed_attempts")
    private Integer failedAttempts;

    @Column(name = "create_user")
    private String createUser;

    @Column(name = "create_date")
    private String createDate;

    @Column(name = "last_update_user")
    private String lastUpdateUser;

    @Column(name = "last_update_date")
    private String lastUpdateDate;

    @Column(name = "criteria")
    private String criteria;

    @Column(name = "date_range_today_minus1")
    private Integer dateRangeTodayMinus1;

    @Column(name = "date_range_today_minus2")
    private Integer dateRangeTodayMinus2;

    @Column(name = "ftp_accounts_id")
    private Long ftpAccountsId;

    @Column(name = "is_suppress_invoices")
    private Boolean suppressInvoices;

    @Column(name = "high_priority")
    private Integer highPriority;

    @Column(name = "submitted_from_system")
    private String submittedFromSystem;

    @Column(name = "is_packet")
    private Boolean packet;

    @Column(name = "queue_name")
    private String queueName;

    @Column(name = "flags_json")
    private String flagsJson;

    @Column(name = "locale")
    private String locale;

    @Transient
    private Integer category;

    public SavedSchedReportDto(Long savedSchedRptId, Long rptId, Boolean scheduled, Integer rptDateOptionsId, Integer reportTypeId, String reportFileName, String dateSelectionFrequency, String date1, String date2,
                               String periodOption, Integer lastNoOfDays, String scTriggerBy, String scScheduleType, Integer scWeeklyFrequency, String scWeeklyMonthlyDayofWeek, Integer scMonthlyDayOfMonth,
                               Integer scMonthlyNoOfMonths, String scMonthlyPeriodicFrequency, String svReportStatus, String submittedOn, String scNextSubmitDate, String carrierIds, String controlPayrunNumber, Boolean consolidate,
                               Integer failedAttempts, String createUser, String createDate, String lastUpdateUser, String lastUpdateDate, String criteria, Integer dateRangeTodayMinus1, Integer dateRangeTodayMinus2,
                               Long ftpAccountsId, Boolean suppressInvoices, Integer highPriority, String submittedFromSystem, Boolean packet, String queueName, String flagsJson, String locale, String currency, String weightUom, String rptDescr) {
        this.savedSchedRptId = savedSchedRptId;
        this.rptId = rptId;
        this.scheduled = scheduled;
        this.rptDateOptionsId = rptDateOptionsId;
        this.reportTypeId = reportTypeId;
        this.reportFileName = reportFileName;
        this.dateSelectionFrequency = dateSelectionFrequency;
        this.date1 = date1;
        this.date2 = date2;
        this.periodOption = periodOption;
        this.lastNoOfDays = lastNoOfDays;
        this.scTriggerBy = scTriggerBy;
        this.scScheduleType = scScheduleType;
        this.scWeeklyFrequency = scWeeklyFrequency;
        this.scWeeklyMonthlyDayofWeek = scWeeklyMonthlyDayofWeek;
        this.scMonthlyDayOfMonth = scMonthlyDayOfMonth;
        this.scMonthlyNoOfMonths = scMonthlyNoOfMonths;
        this.scMonthlyPeriodicFrequency = scMonthlyPeriodicFrequency;
        this.svReportStatus = svReportStatus;
        this.submittedOn = submittedOn;
        this.scNextSubmitDate = scNextSubmitDate;
        this.carrierIds = carrierIds;
        this.controlPayrunNumber = controlPayrunNumber;
        this.consolidate = consolidate;
        this.failedAttempts = failedAttempts;
        this.createUser = createUser;
        this.createDate = createDate;
        this.lastUpdateUser = lastUpdateUser;
        this.lastUpdateDate = lastUpdateDate;
        this.criteria = criteria;
        this.dateRangeTodayMinus1 = dateRangeTodayMinus1;
        this.dateRangeTodayMinus2 = dateRangeTodayMinus2;
        this.ftpAccountsId = ftpAccountsId;
        this.suppressInvoices = suppressInvoices;
        this.highPriority = highPriority;
        this.submittedFromSystem = submittedFromSystem;
        this.packet = packet;
        this.queueName = queueName;
        this.flagsJson = flagsJson;
        this.locale = locale;
        this.currency = currency;
        this.weightUom = weightUom;
        this.rptDescr = rptDescr;
    }

    @Column(name = "currency")
    private String currency;

    @Column(name = "weight_uom")
    private String weightUom;

    @Column(name = "rpt_descr")
    private String rptDescr;

    @Column(name = "updateCount")
    private Integer updateCount;

    @Column(name = "rpt_folder_id")
    private Long rptFolderId;

    public SavedSchedReportDto(Long savedSchedRptId, Long rptId, String criteria) {
        this.savedSchedRptId = savedSchedRptId;
        this.rptId = rptId;
        this.criteria = criteria;
    }

    public Long getSavedSchedRptId() {
        return savedSchedRptId;
    }

    public void setSavedSchedRptId(Long savedSchedRptId) {
        this.savedSchedRptId = savedSchedRptId;
    }

    public Long getRptId() {
        return rptId;
    }

    public void setRptId(Long rptId) {
        this.rptId = rptId;
    }

    public Integer getRptDateOptionsId() {
        return rptDateOptionsId;
    }

    public void setRptDateOptionsId(Integer rptDateOptionsId) {
        this.rptDateOptionsId = rptDateOptionsId;
    }

    public Integer getReportTypeId() {
        return reportTypeId;
    }

    public void setReportTypeId(Integer reportTypeId) {
        this.reportTypeId = reportTypeId;
    }

    public String getReportFileName() {
        return reportFileName;
    }

    public void setReportFileName(String reportFileName) {
        this.reportFileName = reportFileName;
    }

    public String getDateSelectionFrequency() {
        return dateSelectionFrequency;
    }

    public void setDateSelectionFrequency(String dateSelectionFrequency) {
        this.dateSelectionFrequency = dateSelectionFrequency;
    }

    public String getDate1() {
        return date1;
    }

    public void setDate1(String date1) {
        this.date1 = date1;
    }

    public String getDate2() {
        return date2;
    }

    public void setDate2(String date2) {
        this.date2 = date2;
    }

    public String getPeriodOption() {
        return periodOption;
    }

    public void setPeriodOption(String periodOption) {
        this.periodOption = periodOption;
    }

    public Integer getLastNoOfDays() {
        return lastNoOfDays;
    }

    public void setLastNoOfDays(Integer lastNoOfDays) {
        this.lastNoOfDays = lastNoOfDays;
    }

    public String getScTriggerBy() {
        return scTriggerBy;
    }

    public void setScTriggerBy(String scTriggerBy) {
        this.scTriggerBy = scTriggerBy;
    }

    public String getScWeeklyMonthlyDayofWeek() {
        return scWeeklyMonthlyDayofWeek;
    }

    public void setScWeeklyMonthlyDayofWeek(String scWeeklyMonthlyDayofWeek) {
        this.scWeeklyMonthlyDayofWeek = scWeeklyMonthlyDayofWeek;
    }

    public String getScScheduleType() {
        return scScheduleType;
    }

    public void setScScheduleType(String scScheduleType) {
        this.scScheduleType = scScheduleType;
    }

    public Integer getScWeeklyFrequency() {
        return scWeeklyFrequency;
    }

    public void setScWeeklyFrequency(Integer scWeeklyFrequency) {
        this.scWeeklyFrequency = scWeeklyFrequency;
    }

    public Integer getScMonthlyDayOfMonth() {
        return scMonthlyDayOfMonth;
    }

    public void setScMonthlyDayOfMonth(Integer scMonthlyDayOfMonth) {
        this.scMonthlyDayOfMonth = scMonthlyDayOfMonth;
    }

    public Integer getScMonthlyNoOfMonths() {
        return scMonthlyNoOfMonths;
    }

    public void setScMonthlyNoOfMonths(Integer scMonthlyNoOfMonths) {
        this.scMonthlyNoOfMonths = scMonthlyNoOfMonths;
    }

    public String getScMonthlyPeriodicFrequency() {
        return scMonthlyPeriodicFrequency;
    }

    public void setScMonthlyPeriodicFrequency(String scMonthlyPeriodicFrequency) {
        this.scMonthlyPeriodicFrequency = scMonthlyPeriodicFrequency;
    }

    public String getSvReportStatus() {
        return svReportStatus;
    }

    public void setSvReportStatus(String svReportStatus) {
        this.svReportStatus = svReportStatus;
    }

    public String getSubmittedOn() {
        return submittedOn;
    }

    public void setSubmittedOn(String submittedOn) {
        this.submittedOn = submittedOn;
    }

    public String getScNextSubmitDate() {
        return scNextSubmitDate;
    }

    public void setScNextSubmitDate(String scNextSubmitDate) {
        this.scNextSubmitDate = scNextSubmitDate;
    }

    public String getCarrierIds() {
        return carrierIds;
    }

    public void setCarrierIds(String carrierIds) {
        this.carrierIds = carrierIds;
    }

    public String getControlPayrunNumber() {
        return controlPayrunNumber;
    }

    public void setControlPayrunNumber(String controlPayrunNumber) {
        this.controlPayrunNumber = controlPayrunNumber;
    }

    public Boolean getConsolidate() {
        return consolidate;
    }

    public void setConsolidate(Boolean consolidate) {
        this.consolidate = consolidate;
    }

    public Integer getFailedAttempts() {
        return failedAttempts;
    }

    public void setFailedAttempts(Integer failedAttempts) {
        this.failedAttempts = failedAttempts;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getLastUpdateUser() {
        return lastUpdateUser;
    }

    public void setLastUpdateUser(String lastUpdateUser) {
        this.lastUpdateUser = lastUpdateUser;
    }

    public String getLastUpdateDate() {
        return lastUpdateDate;
    }

    public void setLastUpdateDate(String lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }

    public String getCriteria() {
        return criteria;
    }

    public void setCriteria(String criteria) {
        this.criteria = criteria;
    }

    public Integer getDateRangeTodayMinus1() {
        return dateRangeTodayMinus1;
    }

    public void setDateRangeTodayMinus1(Integer dateRangeTodayMinus1) {
        this.dateRangeTodayMinus1 = dateRangeTodayMinus1;
    }

    public Integer getDateRangeTodayMinus2() {
        return dateRangeTodayMinus2;
    }

    public void setDateRangeTodayMinus2(Integer dateRangeTodayMinus2) {
        this.dateRangeTodayMinus2 = dateRangeTodayMinus2;
    }

    public Long getFtpAccountsId() {
        return ftpAccountsId;
    }

    public void setFtpAccountsId(Long ftpAccountsId) {
        this.ftpAccountsId = ftpAccountsId;
    }

    public Boolean getSuppressInvoices() {
        return suppressInvoices;
    }

    public void setSuppressInvoices(Boolean suppressInvoices) {
        this.suppressInvoices = suppressInvoices;
    }

    public Integer getHighPriority() {
        return highPriority;
    }

    public void setHighPriority(Integer highPriority) {
        this.highPriority = highPriority;
    }

    public String getSubmittedFromSystem() {
        return submittedFromSystem;
    }

    public void setSubmittedFromSystem(String submittedFromSystem) {
        this.submittedFromSystem = submittedFromSystem;
    }

    public Boolean getPacket() {
        return packet;
    }

    public void setPacket(Boolean packet) {
        this.packet = packet;
    }

    public String getQueueName() {
        return queueName;
    }

    public void setQueueName(String queueName) {
        this.queueName = queueName;
    }

    public String getFlagsJson() {
        return flagsJson;
    }

    public void setFlagsJson(String flagsJson) {
        this.flagsJson = flagsJson;
    }

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getWeightUom() {
        return weightUom;
    }

    public void setWeightUom(String weightUom) {
        this.weightUom = weightUom;
    }

    public String getRptDescr() {
        return rptDescr;
    }

    public void setRptDescr(String rptDescr) {
        this.rptDescr = rptDescr;
    }

    public Long getRptFolderId() {
        return rptFolderId;
    }

    public void setRptFolderId(Long rptFolderId) {
        this.rptFolderId = rptFolderId;
    }

    public Boolean getScheduled() {
        return scheduled;
    }

    public void setScheduled(Boolean scheduled) {
        this.scheduled = scheduled;
    }

    public ArrayList<ReportPacketsDetDto> getReportPacketsDetList() {
        return reportPacketsDetList;
    }

    public void setReportPacketsDetList(ArrayList<ReportPacketsDetDto> reportPacketsDetList) {
        this.reportPacketsDetList = reportPacketsDetList;
    }

    public ArrayList<ReportSavedSchdCriteriaDto> getReportCriteriaList() {
        return reportCriteriaList;
    }

    public void setReportCriteriaList(ArrayList<ReportSavedSchdCriteriaDto> reportCriteriaList) {
        this.reportCriteriaList = reportCriteriaList;
    }

    public ArrayList<ReportSavedSchdUsersDto> getSavedSchedUsersDtoList() {
        return savedSchedUsersDtoList;
    }

    public void setSavedSchedUsersDtoList(ArrayList<ReportSavedSchdUsersDto> savedSchedUsersDtoList) {
        this.savedSchedUsersDtoList = savedSchedUsersDtoList;
    }

    public ArrayList<ReportsSavedSchdAccountDto> getSavedSchedAccountsDtoList() {
        return savedSchedAccountsDtoList;
    }

    public void setSavedSchedAccountsDtoList(ArrayList<ReportsSavedSchdAccountDto> savedSchedAccountsDtoList) {
        this.savedSchedAccountsDtoList = savedSchedAccountsDtoList;
    }

    public ArrayList<ReportsInclColDto> getReportsInclColDtoList() {
        return reportsInclColDtoList;
    }

    public void setReportsInclColDtoList(ArrayList<ReportsInclColDto> reportsInclColDtoList) {
        this.reportsInclColDtoList = reportsInclColDtoList;
    }

    public ArrayList<ReportsSortDto> getReportsSortColDtoList() {
        return reportsSortColDtoList;
    }

    public void setReportsSortColDtoList(ArrayList<ReportsSortDto> reportsSortColDtoList) {
        this.reportsSortColDtoList = reportsSortColDtoList;
    }

    ArrayList<ReportPacketsDetDto> reportPacketsDetList = new ArrayList<ReportPacketsDetDto>();

    ArrayList<ReportSavedSchdCriteriaDto> reportCriteriaList = new ArrayList<ReportSavedSchdCriteriaDto>();

    ArrayList<ReportSavedSchdUsersDto>  savedSchedUsersDtoList = new ArrayList<ReportSavedSchdUsersDto>();

    ArrayList<ReportsSavedSchdAccountDto> savedSchedAccountsDtoList = new ArrayList<ReportsSavedSchdAccountDto>();

    ArrayList<ReportsInclColDto> reportsInclColDtoList = new ArrayList<ReportsInclColDto>();

    ArrayList<ReportsSortDto> reportsSortColDtoList = new ArrayList<ReportsSortDto>();

    public SavedSchedReportDto(){

    }
    public SavedSchedReportDto(Long savedSchedRptId){
        this.savedSchedRptId = savedSchedRptId;
    }

    public SavedSchedReportDto(Integer updateCount){
        this.updateCount = updateCount;
    }

    public Integer getUpdateCount() {
        return updateCount;
    }

    public void setUpdateCount(Integer updateCount) {
        this.updateCount = updateCount;
    }

    public SavedSchedReportDto(Long savedSchedRptId,
                               Long rptId, Boolean scheduled, Integer rptDateOptionsId,
                               Integer reportTypeId,String reportFileName, String dateSelectionFrequency,String date1,String date2,
                               String periodOption,Integer lastNoOfDays,String scTriggerBy,String scScheduleType,
                               Integer scWeeklyFrequency,String scWeeklyMonthlyDayofWeek,Integer scMonthlyDayOfMonth,
                               Integer scMonthlyNoOfMonths,String scMonthlyPeriodicFrequency,String svReportStatus,String submittedOn,
                               String scNextSubmitDate,String carrierIds,String controlPayrunNumber,Boolean consolidate,Integer failedAttempts,
                               String createUser,String createDate,String lastUpdateUser,String lastUpdateDate,String criteria,
                               Integer dateRangeTodayMinus1,Integer dateRangeTodayMinus2,Long ftpAccountsId,Boolean suppressInvoices,
                               Integer highPriority,String submittedFromSystem,Boolean packet,String queueName,String flagsJson,
                               String locale,String currency,String weightUom,String rptDescr, Long rptFolderId){
        this.savedSchedRptId = savedSchedRptId;
        this.rptId = rptId;
        this.scheduled = scheduled;
        this.rptDateOptionsId = rptDateOptionsId;
        this.reportTypeId = reportTypeId;
        this.reportFileName = reportFileName;
        this.dateSelectionFrequency = dateSelectionFrequency;
        this.date1 = date1;
        this.date2 = date2;
        this.periodOption = periodOption;
        this.lastNoOfDays = lastNoOfDays;
        this.scTriggerBy = scTriggerBy;
        this.scScheduleType = scScheduleType;
        this.scWeeklyFrequency = scWeeklyFrequency;
        this.scWeeklyMonthlyDayofWeek = scWeeklyMonthlyDayofWeek;
        this.scMonthlyDayOfMonth = scMonthlyDayOfMonth;
        this.scMonthlyNoOfMonths = scMonthlyNoOfMonths;
        this.scMonthlyPeriodicFrequency = scMonthlyPeriodicFrequency;
        this.svReportStatus = svReportStatus;
        this.submittedOn = submittedOn;
        this.scNextSubmitDate = scNextSubmitDate;
        this.carrierIds = carrierIds;
        this.controlPayrunNumber = controlPayrunNumber;
        this.consolidate = consolidate;
        this.failedAttempts = failedAttempts;
        this.createUser = createUser;
        this.createDate = createDate;
        this.lastUpdateUser = lastUpdateUser;
        this.lastUpdateDate = lastUpdateDate;
        this.criteria = criteria;
        this.dateRangeTodayMinus1 = dateRangeTodayMinus1;
        this.dateRangeTodayMinus2 = dateRangeTodayMinus2;
        this.ftpAccountsId = ftpAccountsId;
        this.suppressInvoices = suppressInvoices;
        this.highPriority = highPriority;
        this.submittedFromSystem = submittedFromSystem;
        this.packet = packet;
        this.queueName = queueName;
        this.flagsJson = flagsJson;
        this.locale = locale;
        this.currency = currency;
        this.weightUom = weightUom;
        this.rptDescr = rptDescr;
        this.rptFolderId = rptFolderId;
    }

    public Integer getCategory() {
        return category;
    }

    public void setCategory(Integer category) {
        this.category = category;
    }
}
