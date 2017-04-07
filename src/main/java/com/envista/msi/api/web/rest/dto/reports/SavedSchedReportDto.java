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
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "rptDateOptionsId", type = Long.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "reportTypeId", type = Integer.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "reportFileName", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "dateSelectionFrequency", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "date1", type = Date.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "date2", type = Date.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "periodOption", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "lastNoOfDays", type = Integer.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "scTriggerBy", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "scScheduleType", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "scWeeklyFrequency", type = Integer.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "scWeeklyMonthlyDayofWeek", type = Integer.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "scMonthlyDayOfMonth", type = Integer.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "scMonthlyNoOfMonths", type = Integer.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "scMonthlyPeriodicFreq", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "svReportStatus", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "scNextSubmitDate", type = Date.class),
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
                })
})

@SqlResultSetMappings({
        @SqlResultSetMapping(name = "SavedSchedReport", classes = {
                @ConstructorResult(
                        targetClass = SavedSchedReportDto.class,
                        columns = {
                                @ColumnResult(name = "saved_sched_rpt_id", type = Long.class)
                        }
                )
        })
})

@Entity
public class SavedSchedReportDto {

    @Id
    @Column(name = "saved_sched_rpt_id")
    private long savedSchedRptId;

    @Column(name = "rpt_id")
    private long rptId;

    @Column(name = "is_scheduled")
    private boolean isScheduled;

    @Column(name = "rpt_date_options_id")
    private int rptDateOptionsId;

    @Column(name = "report_type_id")
    private int reportTypeId;

    @Column(name = "report_file_name")
    private String reportFileName;

    @Column(name = "date_selection_frequency")
    private String dateSelectionFrequency;

    @Column(name = "date1")
    private Date date1;

    @Column(name = "date2")
    private Date date2;

    @Column(name = "period_option")
    private String periodOption;

    @Column(name = "last_no_of_days")
    private int lastNoOfDays;

    @Column(name = "sc_trigger_by")
    private String scTriggerBy;

    @Column(name = "sc_schedule_type")
    private String scScheduleType;

    @Column(name = "sc_weekly_frequency")
    private int scWeeklyFrequency;

    @Column(name = "sc_weekly_monthly_day_of_week")
    private String scWeekMonthlyDayOfWeek;

    @Column(name = "sc_monthly_day_of_month")
    private int scMonthlyDayOfMonth;

    @Column(name = "sc_monthly_no_of_months")
    private int scMonthlyNoOfMonths;

    @Column(name = "sc_monthly_periodic_frequency")
    private  String scMonthlyPeriodicFrequency;

    @Column(name = "sv_report_status")
    private String svReportStatus;

    @Column(name = "submitted_on")
    private Date submittedOn;

    @Column(name = "sc_next_submit_date")
    private Date scNextSubmitDate;

    @Column(name = "carrier_ids")
    private String carrierIds;

    @Column(name = "control_payrun_number")
    private String controlPayrunNumber;

    @Column(name = "consolidate")
    private boolean consolidate;

    @Column(name = "failed_attempts")
    private int failedAttempts;

    @Column(name = "create_user")
    private String createUser;

    @Column(name = "create_date")
    private Date createDate;

    @Column(name = "last_update_user")
    private  String lastUpdateUser;

    @Column(name = "last_update_date")
    private Date lastUpdateDate;

    @Column(name = "criteria")
    private String criteria;

    @Column(name = "date_range_today_minus1")
    private int dateRangeTodayMinus1;

    @Column(name = "date_range_today_minus2")
    private int dateRangeTodayMinus2;

    @Column(name = "ftp_accounts_id")
    private long ftpAccountsId;

    @Column(name = "is_suppress_invoices")
    private boolean suppressInvoices;

    @Column(name = "high_priority")
    private int highPriority;

    @Column(name = "submitted_from_system")
    private String submittedFromSystem;

    @Column(name = "is_packet")
    private boolean packet;

    @Column(name = "queue_name")
    private String queueName;

    @Column(name = "flags_json")
    private String flagsJson;

    @Column(name = "locale")
    private String locale;

    @Column(name = "currency")
    private String currency;

    @Column(name = "weight_uom")
    private String weightUom;

    @Column(name = "rpt_descr")
    private String rptDescr;

    ArrayList<ReportPacketsDetDto> reportPacketsDetList = new ArrayList<ReportPacketsDetDto>();

    ArrayList<ReportSavedSchdCriteriaDto> reportCriteriaList = new ArrayList<ReportSavedSchdCriteriaDto>();

    ArrayList<ReportSavedSchdUsersDto>  savedSchedUsersDtoList = new ArrayList<ReportSavedSchdUsersDto>();

    ArrayList<ReportsSavedSchdAccountDto> savedSchedAccountsDtoList = new ArrayList<ReportsSavedSchdAccountDto>();

    ArrayList<ReportsInclColDto> reportsInclColDtoList = new ArrayList<ReportsInclColDto>();

    ArrayList<ReportsSortDto> reportsSortColDtoList = new ArrayList<ReportsSortDto>();

    public long getSavedSchedRptId() {
        return savedSchedRptId;
    }

    public void setSavedSchedRptId(long savedSchedRptId) {
        this.savedSchedRptId = savedSchedRptId;
    }

    public long getRptId() {
        return rptId;
    }

    public void setRptId(long rptId) {
        this.rptId = rptId;
    }

    public boolean isScheduled() {
        return isScheduled;
    }

    public void setScheduled(boolean scheduled) {
        isScheduled = scheduled;
    }

    public int getRptDateOptionsId() {
        return rptDateOptionsId;
    }

    public void setRptDateOptionsId(int rptDateOptionsId) {
        this.rptDateOptionsId = rptDateOptionsId;
    }

    public int getReportTypeId() {
        return reportTypeId;
    }

    public void setReportTypeId(int reportTypeId) {
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

    public Date getDate1() {
        return date1;
    }

    public void setDate1(Date date1) {
        this.date1 = date1;
    }

    public Date getDate2() {
        return date2;
    }

    public void setDate2(Date date2) {
        this.date2 = date2;
    }

    public String getPeriodOption() {
        return periodOption;
    }

    public void setPeriodOption(String periodOption) {
        this.periodOption = periodOption;
    }

    public int getLastNoOfDays() {
        return lastNoOfDays;
    }

    public void setLastNoOfDays(int lastNoOfDays) {
        this.lastNoOfDays = lastNoOfDays;
    }

    public String getScTriggerBy() {
        return scTriggerBy;
    }

    public void setScTriggerBy(String scTriggerBy) {
        this.scTriggerBy = scTriggerBy;
    }

    public String getScScheduleType() {
        return scScheduleType;
    }

    public void setScScheduleType(String scScheduleType) {
        this.scScheduleType = scScheduleType;
    }

    public int getScWeeklyFrequency() {
        return scWeeklyFrequency;
    }

    public void setScWeeklyFrequency(int scWeeklyFrequency) {
        this.scWeeklyFrequency = scWeeklyFrequency;
    }

    public String getScWeekMonthlyDayOfWeek() {
        return scWeekMonthlyDayOfWeek;
    }

    public void setScWeekMonthlyDayOfWeek(String scWeekMonthlyDayOfWeek) {
        this.scWeekMonthlyDayOfWeek = scWeekMonthlyDayOfWeek;
    }

    public int getScMonthlyDayOfMonth() {
        return scMonthlyDayOfMonth;
    }

    public void setScMonthlyDayOfMonth(int scMonthlyDayOfMonth) {
        this.scMonthlyDayOfMonth = scMonthlyDayOfMonth;
    }

    public int getScMonthlyNoOfMonths() {
        return scMonthlyNoOfMonths;
    }

    public void setScMonthlyNoOfMonths(int scMonthlyNoOfMonths) {
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

    public Date getSubmittedOn() {
        return submittedOn;
    }

    public void setSubmittedOn(Date submittedOn) {
        this.submittedOn = submittedOn;
    }

    public Date getScNextSubmitDate() {
        return scNextSubmitDate;
    }

    public void setScNextSubmitDate(Date scNextSubmitDate) {
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

    public boolean isConsolidate() {
        return consolidate;
    }

    public void setConsolidate(boolean consolidate) {
        this.consolidate = consolidate;
    }

    public int getFailedAttempts() {
        return failedAttempts;
    }

    public void setFailedAttempts(int failedAttempts) {
        this.failedAttempts = failedAttempts;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getLastUpdateUser() {
        return lastUpdateUser;
    }

    public void setLastUpdateUser(String lastUpdateUser) {
        this.lastUpdateUser = lastUpdateUser;
    }

    public Date getLastUpdateDate() {
        return lastUpdateDate;
    }

    public void setLastUpdateDate(Date lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }

    public String getCriteria() {
        return criteria;
    }

    public void setCriteria(String criteria) {
        this.criteria = criteria;
    }

    public int getDateRangeTodayMinus1() {
        return dateRangeTodayMinus1;
    }

    public void setDateRangeTodayMinus1(int dateRangeTodayMinus1) {
        this.dateRangeTodayMinus1 = dateRangeTodayMinus1;
    }

    public int getDateRangeTodayMinus2() {
        return dateRangeTodayMinus2;
    }

    public void setDateRangeTodayMinus2(int dateRangeTodayMinus2) {
        this.dateRangeTodayMinus2 = dateRangeTodayMinus2;
    }

    public long getFtpAccountsId() {
        return ftpAccountsId;
    }

    public void setFtpAccountsId(long ftpAccountsId) {
        this.ftpAccountsId = ftpAccountsId;
    }

    public boolean isSuppressInvoices() {
        return suppressInvoices;
    }

    public void setSuppressInvoices(boolean suppressInvoices) {
        this.suppressInvoices = suppressInvoices;
    }

    public int getHighPriority() {
        return highPriority;
    }

    public void setHighPriority(int highPriority) {
        this.highPriority = highPriority;
    }

    public String getSubmittedFromSystem() {
        return submittedFromSystem;
    }

    public void setSubmittedFromSystem(String submittedFromSystem) {
        this.submittedFromSystem = submittedFromSystem;
    }

    public boolean isPacket() {
        return packet;
    }

    public void setPacket(boolean packet) {
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

}
