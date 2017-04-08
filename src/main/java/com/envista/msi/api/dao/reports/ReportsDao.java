package com.envista.msi.api.dao.reports;

import com.envista.msi.api.domain.PersistentContext;
import com.envista.msi.api.domain.util.QueryParameter;
import com.envista.msi.api.domain.util.StoredProcedureParameter;
import com.envista.msi.api.web.rest.dto.UserProfileDto;
import com.envista.msi.api.web.rest.dto.reports.*;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import javax.persistence.ParameterMode;
import java.util.Date;
import java.util.List;

/**
 * Created by Sreenivas on 2/17/2017.
 */

@Repository("ReportsDao")
public class ReportsDao {

    @Inject
    private PersistentContext persistentContext;
    /**
     * Get bet reports based on userId.
     * @param userId
     * @return list<ReportResultsDto>
     */
    @Transactional( readOnly = true )
    public List<ReportResultsDto> getReportResults(Long userId) {
        return persistentContext.findEntitiesAndMapFields("ReportResults.getReportResults",
                StoredProcedureParameter.with("userId", userId));
    }
    /**
     * Update Expiry Date.
     * @param generatedRptId
     * @param expiryDate
     * @return list<ReportResultsDto>
     */
    @Transactional
    public ReportResultsDto updateExpiryDate(Long generatedRptId,String expiryDate) {
        Date toexpiryDate =convertDateFullYear(expiryDate);
        QueryParameter queryParameter = StoredProcedureParameter.with("expiryDate", toexpiryDate)
                .and("generatedRptId", generatedRptId);
        return persistentContext.findEntityAndMapFields("ReportResults.updateExpiryDate",queryParameter);
    }
    @Transactional
    public List<ReportResultsUsersListDto> getUsersList(String userName) {
        QueryParameter queryParameter = StoredProcedureParameter.with("userName",userName);
        return persistentContext.findEntitiesAndMapFields("ReportResultsUsersList.getUsersList",queryParameter);
    }

    public static Date convertDateFullYear(String date)  {
        Date sdate = new Date(System.currentTimeMillis());
        try {
            sdate = new Date(Long.parseLong(date));
        }catch(Exception e){
            e.printStackTrace();
        }
        return sdate;
    }

    public static String convertDateFullYearString(String date) {
        String sdate = "";
        try {
            sdate = String.valueOf(new Date(Long.parseLong(date)));
        }catch(Exception e){
            e.printStackTrace();
        }
        return sdate;
    }

    /**
     * Update Expiry Date.
     * @param generatedRptId
     * @param userId
     *  @param userName
     * @return
     */
    @Transactional
    public ReportResultsDto deleteReportInResults(long generatedRptId, long userId, String userName){
        QueryParameter queryParameter = StoredProcedureParameter.with("generatedRptId", generatedRptId)
                                        .and("userId", userId)
                                        .and("userName", userName);
        return persistentContext.findEntityAndMapFields("ReportResults.deleteResultReport",queryParameter);
    }
    /**
     * Get Saved Sched Reports.
     * @param userId
     * @return
     */

    @Transactional( readOnly = true )
    public List<SavedSchedReportsDto> getSavedSchedReports(long userId) {
        return persistentContext.findEntitiesAndMapFields("SavedSchedReports.gerSavedSchedReports",
                StoredProcedureParameter.with("userId", userId));
    }

    @Transactional
    public UpdateSavedSchedReportDto updateSavedSchedReport(UpdateSavedSchedReportDto updateSavedSchedReportDto) {
        if(updateSavedSchedReportDto.getSharetoUserId()>0) {
            QueryParameter queryParameter = StoredProcedureParameter.with("userId", updateSavedSchedReportDto.getSharetoUserId())
                    .and("savedSchedId", updateSavedSchedReportDto.getSavedSchedRptId())
                    .and("createUser",updateSavedSchedReportDto.getCreateUser());
            return persistentContext.findEntityAndMapFields("SavedReports.addUserToSavedReport", queryParameter);
        }
        else if(updateSavedSchedReportDto.getSharetoUserId()==0 && updateSavedSchedReportDto.isDeleteAll()){
            QueryParameter queryParameter = StoredProcedureParameter.with("userId", updateSavedSchedReportDto.getLoggedinuserId())
                    .and("savedSchedId", updateSavedSchedReportDto.getSavedSchedRptId());
            return persistentContext.findEntityAndMapFields("SavedReports.deleteAllSavedSchedReport", queryParameter);
        }else if(updateSavedSchedReportDto.getSharetoUserId()==0 && !updateSavedSchedReportDto.isDeleteAll()){
            QueryParameter queryParameter = StoredProcedureParameter.with("userId", updateSavedSchedReportDto.getLoggedinuserId())
                    .and("savedSchedId", updateSavedSchedReportDto.getSavedSchedRptId());
            return persistentContext.findEntityAndMapFields("SavedReports.deleteUserSavedSchedReport", queryParameter);
        }
        return new UpdateSavedSchedReportDto(0l);
    }
    @Transactional
    public UpdateSavedSchedReportDto runSavedSchedReport(UpdateSavedSchedReportDto updateSavedSchedReportDto) {
            QueryParameter queryParameter = StoredProcedureParameter.with("userId", updateSavedSchedReportDto.getLoggedinuserId())
                    .and("savedSchedId", updateSavedSchedReportDto.getSavedSchedRptId())
                    .and("createUser",updateSavedSchedReportDto.getCreateUser());
            return persistentContext.findEntityAndMapFields("SavedReports.runSavedSchedReport", queryParameter);
    }
    @Transactional
    public UpdateSavedSchedReportDto saveFromReportResults(UpdateSavedSchedReportDto updateSavedSchedReportDto) {
        QueryParameter queryParameter = StoredProcedureParameter.with("userId", updateSavedSchedReportDto.getLoggedinuserId())
                .and("savedSchedId", updateSavedSchedReportDto.getSavedSchedRptId())
                .and("createUser",updateSavedSchedReportDto.getCreateUser())
                .and("reportName",updateSavedSchedReportDto.getReportName());
        return persistentContext.findEntityAndMapFields("SavedReports.saveFromReportResults", queryParameter);
    }
    @Transactional
    public ReportResultsUsersListDto pushToUser(List<ReportResultsUsersListDto> reportResultsUsersListDto) {

        for(ReportResultsUsersListDto userDto : reportResultsUsersListDto) {
            QueryParameter queryParameter = StoredProcedureParameter.with("userId", userDto.getUserId())
                    .and("savedSchedId", userDto.getSavedSchedRptId())
                    .and("generatedRptId",userDto.getGeneratedRptId())
                    .and("createUser", userDto.getCreateUser());
            return persistentContext.findEntityAndMapFields("ReportResultsUsersList.pushToUser", queryParameter);
        }
        return new ReportResultsUsersListDto(0l);
    }
    /**
     * @param userId
     * @return List<ReportModesDto>
     */
    @Transactional
    public List<ReportModesDto> getReportForModes(Long userId) {
        return persistentContext.findEntitiesAndMapFields("ReportModes.getReportModeList",
                StoredProcedureParameter.with("p_user_id", userId));
    }
    /**
     * @param rptId
     * @param userId
     * @return List<ReportCustomerCarrierDto>
     */
    public List<ReportCustomerCarrierDto> getReportCustomers(Long rptId, Long userId){
        QueryParameter queryParameter = StoredProcedureParameter.with("p_user_id", userId)
                .and("p_rpt_id", rptId);
        return persistentContext.findEntities("ReportCustomerCarrier.getReportCustomer",queryParameter);
    }
    /**
     * @param rptId
     * @param userId
     * @return List<ReportCustomerCarrierDto>
     */
    public List<ReportCustomerCarrierDto> getReportCarrier(Long rptId, Long userId){
        QueryParameter queryParameter = StoredProcedureParameter.with("p_user_id", userId)
                .and("p_rpt_id", rptId);
        return persistentContext.findEntities("ReportCustomerCarrier.getReportCarrier",queryParameter);
    }
    /**
     * @param customerIdsCSV
     * @return List<ReportCustomerCarrierDto>
     */
    public List<ReportCustomerCarrierDto> getCustomerLevels(String customerIdsCSV){
        return persistentContext.findEntities("ReportCustomerCarrier.getCustomerLevels",StoredProcedureParameter.with("p_cusatomer_ids", customerIdsCSV));
    }
    /**
     * @param rptId
     * @return List<ReportFormatDto>
     */
    public List<ReportFormatDto> getReportFormat(Long rptId){
        return persistentContext.findEntities("ReportFormat.getReportFormat",StoredProcedureParameter.with("p_rpt_id", rptId));
    }

    /**
     * @param generatedRptId
     * @return List<ReportFormatDto>
     */
    public List<ReportsFilesDto> getReportFileDetails(Long generatedRptId){
        QueryParameter queryParameter = StoredProcedureParameter.with("crGeneratedRptId", generatedRptId);
        return persistentContext.findEntitiesAndMapFields("EmailReports.getReportPath",queryParameter);
    }

    public UserProfileDto getUserDetails(String userName){
        QueryParameter queryParameter = StoredProcedureParameter.with("userNameInParam", userName);
        return persistentContext.findEntity("UserProfileTb.getUserByProcUserEntity",queryParameter);
    }

    @Transactional
    public SavedSchedReportDto saveSchedReport(SavedSchedReportDto savedSchedReportDto){

        if(savedSchedReportDto.getDate1()!=null && !savedSchedReportDto.getDate1().isEmpty()){
            savedSchedReportDto.setDate1(convertDateFullYearString(savedSchedReportDto.getDate1()));
        }
        if(savedSchedReportDto.getDate2()!=null && !savedSchedReportDto.getDate2().isEmpty()){
            savedSchedReportDto.setDate2(convertDateFullYearString(savedSchedReportDto.getDate2()));
        }
        if(savedSchedReportDto.getScNextSubmitDate()!=null && !savedSchedReportDto.getScNextSubmitDate().isEmpty()){
            savedSchedReportDto.setScNextSubmitDate(convertDateFullYearString(savedSchedReportDto.getScNextSubmitDate()));
        }

        QueryParameter queryParameter = StoredProcedureParameter.with("rptId", savedSchedReportDto.getRptId()==null?0:savedSchedReportDto.getRptId())
                .and("isScheduled",(savedSchedReportDto.getScheduled()==null ? false : savedSchedReportDto.getScheduled()))
                .and("rptDateOptionsId",savedSchedReportDto.getRptDateOptionsId()==null ? 0:savedSchedReportDto.getRptDateOptionsId())
                .and("reportTypeId",savedSchedReportDto.getReportTypeId()==null ? 0:savedSchedReportDto.getReportTypeId())
                .and("reportFileName",savedSchedReportDto.getReportFileName())
                .and("dateSelectionFrequency",savedSchedReportDto.getDateSelectionFrequency())
                .and("date1",savedSchedReportDto.getDate1())
                .and("date2",savedSchedReportDto.getDate2())
                .and("periodOption",savedSchedReportDto.getPeriodOption())
                .and("lastNoOfDays",savedSchedReportDto.getLastNoOfDays()==null?0:savedSchedReportDto.getLastNoOfDays())
                .and("scTriggerBy",savedSchedReportDto.getScTriggerBy())
                .and("scScheduleType",savedSchedReportDto.getScScheduleType())
                .and("scWeeklyFrequency",savedSchedReportDto.getScWeeklyFrequency()==null?0:savedSchedReportDto.getScWeeklyFrequency())
                .and("scWeeklyMonthlyDayofWeek",savedSchedReportDto.getScWeeklyMonthlyDayofWeek())
                .and("scMonthlyDayOfMonth",savedSchedReportDto.getScMonthlyDayOfMonth()==null?0:savedSchedReportDto.getScMonthlyDayOfMonth())
                .and("scMonthlyNoOfMonths",savedSchedReportDto.getScMonthlyNoOfMonths()==null?0:savedSchedReportDto.getScMonthlyNoOfMonths())
                .and("scMonthlyPeriodicFreq",savedSchedReportDto.getScMonthlyPeriodicFrequency())
                .and("svReportStatus",savedSchedReportDto.getSvReportStatus())
                .and("scNextSubmitDate",null)
                .and("carrierIds",savedSchedReportDto.getCarrierIds())
                .and("controlPayrunNumber",savedSchedReportDto.getControlPayrunNumber())
                .and("consolidate",savedSchedReportDto.getConsolidate()==null?false:savedSchedReportDto.getConsolidate())
                .and("createUser",savedSchedReportDto.getCreateUser())
                .and("criteria",savedSchedReportDto.getCriteria())
                .and("dateRangeTodayMinus1",savedSchedReportDto.getDateRangeTodayMinus1()==null?0:savedSchedReportDto.getDateRangeTodayMinus1())
                .and("dateRangeTodayMinus2",savedSchedReportDto.getDateRangeTodayMinus2()==null?0:savedSchedReportDto.getDateRangeTodayMinus2())
                .and("ftpAccountsId",savedSchedReportDto.getFtpAccountsId())
                .and("isSuppressInvoices",savedSchedReportDto.getSuppressInvoices()==null?false:savedSchedReportDto.getSuppressInvoices())
                .and("submittedFromSystem",savedSchedReportDto.getSubmittedFromSystem())
                .and("isPacket",savedSchedReportDto.getPacket()==null?false:savedSchedReportDto.getPacket())
                .and("flagsJson",savedSchedReportDto.getFlagsJson())
                .and("locale",savedSchedReportDto.getLocale())
                .and("currency",savedSchedReportDto.getCurrency())
                .and("weightUom",savedSchedReportDto.getWeightUom())
                .and("rptDescr",savedSchedReportDto.getRptDescr());

        return persistentContext.findEntity("SavedSchedReports.saveSchedReport",queryParameter);

    }

    @Transactional
    public ReportPacketsDetDto saveSchedPacketReport(ReportPacketsDetDto reportPacketsDetDto){

        QueryParameter queryParameter = StoredProcedureParameter.with("savedSchedRptId", reportPacketsDetDto.getSavedSchdRptId())
                .and("tabName",reportPacketsDetDto.getTabName())
                .and("sequenceNum",reportPacketsDetDto.getSequence())
                .and("templateId",reportPacketsDetDto.getTemplateId());

        return persistentContext.findEntity("SavedSchedPackets.saveSchedPakcet",queryParameter);

    }

    @Transactional
    public ReportSavedSchdUsersDto saveSchedUser(ReportSavedSchdUsersDto saveSchedUser){

        QueryParameter queryParameter = StoredProcedureParameter.with("savedSchedRptId", saveSchedUser.getSavedSchdRptId()==null?0:saveSchedUser.getSavedSchdRptId())
                .and("userId",saveSchedUser.getUserId())
                .and("isEmailTempTobeSent",saveSchedUser.isEmailTemplateToBeSent()==null?true:saveSchedUser.isEmailTemplateToBeSent())
                .and("isReportAttachEmail",saveSchedUser.isReportAttachedMail()==null?false:saveSchedUser.isReportAttachedMail())
                .and("isReportSubscribed",saveSchedUser.isReportSubscribed()==null?true:saveSchedUser.isReportSubscribed())
                .and("createUser",saveSchedUser.getCreateUser())
                .and("isShared",saveSchedUser.isShared()==null?false:saveSchedUser.isShared())
                .and("canEdit",saveSchedUser.isCanEdit()==null?true:saveSchedUser.isCanEdit());

        return persistentContext.findEntity("SavedSchedReports.saveUsers",queryParameter);

    }

}
