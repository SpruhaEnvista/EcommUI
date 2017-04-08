package com.envista.msi.api.dao.reports;

import com.envista.msi.api.domain.PersistentContext;
import com.envista.msi.api.domain.util.QueryParameter;
import com.envista.msi.api.domain.util.StoredProcedureParameter;
import com.envista.msi.api.security.SecurityUtils;
import com.envista.msi.api.web.rest.dto.UserProfileDto;
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
    public List<SavedSchedReportsDto> getSavedSchedReports(long userId,long folderId) {
        return persistentContext.findEntitiesAndMapFields("SavedSchedReports.gerSavedSchedReports",
                StoredProcedureParameter.with("userId", userId)
                                        .and("folderId",folderId));
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
                .and("reportName",updateSavedSchedReportDto.getReportName())
                .and("rptFolderId",updateSavedSchedReportDto.getRptFolderId());;
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

    @Transactional
    public ReportFolderDto createReportFolder(ReportFolderDto reportFolderDto, UserProfileDto userProfileDto){
        QueryParameter queryParameter = StoredProcedureParameter.with("folderName",reportFolderDto.getReportFolderName())
                                        .and("createUser", (userProfileDto != null && userProfileDto.getUserName() != null ?  userProfileDto.getUserName() : "invalid" ))
                                        .and("userId", (userProfileDto != null && userProfileDto.getUserId() != null ? userProfileDto.getUserId() : 0l ) )
                                        .and("parentId", (reportFolderDto.getParentId() != null ? reportFolderDto.getParentId() : 0l ))
                                        .and("crud",1l);
        return persistentContext.findEntityAndMapFields("ReportFolder.createFolder", queryParameter);
    }

    @Transactional
    public ReportFolderDetailsDto moveReportToFolder(ReportFolderDetailsDto rptFolderDtlsDto){
        QueryParameter queryParameter = StoredProcedureParameter.with("rptFolderId",(rptFolderDtlsDto.getReportFolderId() == null ? 0 : rptFolderDtlsDto.getReportFolderId()))
                                            .and("savedSchRptId", (rptFolderDtlsDto.getSavedSchdReportId() == null ? 0 : rptFolderDtlsDto.getSavedSchdReportId()) )
                                            .and("crud",1l);
        return persistentContext.findEntityAndMapFields("ReportFolderDtls.createRow",queryParameter);
    }

    @Transactional
    public SavedSchedReportsDto changeOwnerBasedonSSRptId(String currentUserName,Long currentUserId,String newUserName,Long newUserId,Long ssRptId){
        QueryParameter queryParameter = StoredProcedureParameter.with("currentUserName",currentUserName)
                                                        .and("currentUserId",currentUserId)
                                                        .and("newUserName",newUserName)
                                                        .and("newUserId",newUserId)
                                                        .and("ssRptId",ssRptId);
        return persistentContext.findEntityAndMapFields("SavedSchedReports.changeOwnerBasedOnSSRptId",queryParameter);
    }

    @Transactional
    public ReportSavedSchdCriteriaDto updateSavedSchdCriteria(Long ssRptId,Long rptDtlsId,String assignOperator,String value,Long isMatchCase,String createUser,String andOrOperator ){
        QueryParameter queryParameter = StoredProcedureParameter.with("savedSchedRptId",ssRptId)
                                                            .and("rptDetailsId",rptDtlsId)
                                                            .and("assignOperator",assignOperator)
                                                            .and("value",value)
                                                            .and("isMatchCase",isMatchCase)
                                                            .and("createUser",createUser)
                                                            .and("andOrOperator",andOrOperator);

        return persistentContext.findEntityAndMapFields("ReportSavedSchdCrit.insertRecord",queryParameter);
    }

    @Transactional
    public ReportsInclColDto updateInclCol(Long ssRptId,Long rptDtlsId,String createUser ){
        QueryParameter queryParameter = StoredProcedureParameter.with("savedSchedRptId",ssRptId)
                .and("rptDetailsId",rptDtlsId)
                .and("createUser",createUser);

        return persistentContext.findEntityAndMapFields("ReportInclCol.insertRecord",queryParameter);
    }

    @Transactional
    public ReportsSavedSchdAccountDto updateSavedSchdAccs(Long ssRptId,Long custId,Long shipperGroupId,Long shipperId,String createUser ){
        QueryParameter queryParameter = StoredProcedureParameter.with("savedSchedRptId",ssRptId)
                .and("customerId",custId)
                .and("shipperGroupId",shipperGroupId)
                .and("shipperId",shipperId)
                .and("createUser",createUser);

        return persistentContext.findEntityAndMapFields("ReportSavedSchdAcc.insertRecord",queryParameter);
    }

    public  boolean isNumber(String strNumber) {
        try {

            Float.parseFloat(strNumber);
        } catch (Exception nfe) {
            return false;
        }
        return true;
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

        QueryParameter queryParameter = StoredProcedureParameter.with("savedSchedRptId", saveSchedUser.getSavedSchdRptId())
                .and("userId",saveSchedUser.getUserId())
                .and("isEmailTempTobeSent",saveSchedUser.isEmailTemplateToBeSent())
                .and("isReportAttachEmail",saveSchedUser.isReportAttachedMail())
                .and("isReportSubscribed",saveSchedUser.isReportSubscribed())
                .and("createUser",saveSchedUser.getCreateUser())
                .and("isShared",saveSchedUser.isShared())
                .and("canEdit",saveSchedUser.isCanEdit());

        return persistentContext.findEntity("SavedSchedReports.saveUsers",queryParameter);

    }
    /**
     * @param rptId
     * @return List<ReportFormatDto>
     */
    public List<ReportFormatDto> getReportDateOptions(Long rptId){
        return persistentContext.findEntities("ReportFormat.getReportDateOptions",StoredProcedureParameter.with("p_rpt_id", rptId));
    }
    /**
     * @param userId
     * @param carrierIds
     * @param rptId
     * @return List<ReportCriteriaDto>
     */
    public List<ReportColumnDto> getReportCriteria(Long userId, Long rptId, String carrierIds){
        QueryParameter queryParameter = StoredProcedureParameter.with("p_user_id",userId)
                                        .and("p_rpt_id", rptId)
                                        .and("p_carrier_ids",carrierIds);
        return persistentContext.findEntities("ReportCriteriaDto.getReportCriteria",queryParameter);
    }
    /**
     * @param userId
     * @param carrierIds
     * @param rptId
     * @return List<ReportColumnDto>
     */
    public List<ReportColumnDto> getIncludeExcludeSortCol(Long userId, Long rptId, String carrierIds){
        QueryParameter queryParameter = StoredProcedureParameter.with("p_user_id",userId)
                .and("p_rpt_id", rptId)
                .and("p_carriers",carrierIds);
        return persistentContext.findEntities("ReportCriteriaDto.getIncludeExcludeSortCol",queryParameter);
    }
    /**
     * @param userId
     * @param carrierIds
     * @param rptId
     * @return List<ReportColumnDto>
     */
    public List<ReportColumnDto> getSavedIncludeExcludeSortCol(Long userId, Long rptId, String carrierIds){
        QueryParameter queryParameter = StoredProcedureParameter.with("p_user_id",userId)
                .and("p_rpt_id", rptId)
                .and("p_carriers",carrierIds);
        return persistentContext.findEntities("ReportCriteriaDto.getSavedIncludeExcludeSortCol",queryParameter);
    }
    /**
     * @param rptId
     * @return List<ReportCodeValueDto>
     */
    public List<ReportCodeValueDto> getReportLocaleLabel(Long rptId){
        return persistentContext.findEntities("ReportCodeValueDto.getReportLocaleLabel", StoredProcedureParameter.with("p_rpt_id", rptId));
    }
    /**
     * @param rptId
     * @return List<ReportCodeValueDto>
     */
    public List<ReportCodeValueDto> getReportCurrencyLabel(Long rptId){
        return persistentContext.findEntities("ReportCodeValueDto.getReportCurrencyLabel", StoredProcedureParameter.with("p_rpt_id", rptId));
    }
    /**
     * @param rptId
     * @return List<ReportCodeValueDto>
     */
    public List<ReportCodeValueDto> getReportWeightLabel(Long rptId){
        return persistentContext.findEntities("ReportCodeValueDto.getReportWeightLabel", StoredProcedureParameter.with("p_rpt_id", rptId));
    }
    /**
     * @param customerIds
     * @param payRunNo
     * @param checkNo
     * @return List<ReportFormatDto>
     */
    public List<ReportFormatDto> getControlNumber(String customerIds,Integer payRunNo,Integer checkNo){
        QueryParameter queryParameter = StoredProcedureParameter.with("p_customer_ids",customerIds)
                .and("p_pay_run_no", payRunNo)
                .and("p_check_no",checkNo);
        return persistentContext.findEntities("ReportFormat.getControlNumber",queryParameter);
    }
    /**
     * @param userId
     * @return List<ReportFolderHierarchyDto>
     */
    public List<ReportFolderDto> getReportFolder(Long userId){
        return persistentContext.findEntities("ReportFolder.getReportFolder", StoredProcedureParameter.with("p_user_id",userId));
    }
    /**
     * @param customerIds
     *  @param shipperGroupIds
     *   @param shipperIds
     * @return List<ReportFolderHierarchyDto>
     */
    public List<ReportFTPServerDto> getReportFTPServer(String customerIds,String shipperGroupIds ,String shipperIds){
        QueryParameter queryParameter = StoredProcedureParameter.with("customerIds",customerIds)
                .and("shipperGroupIds", shipperGroupIds)
                .and("shipperIds",shipperIds);
        return persistentContext.findEntities("ReportFTPServer.getFTPServer",queryParameter);
    }/**
     * @param rptId
     * @return List<ReportFTPServerDto>
     */
    public List<ReportFTPServerDto> getSaveRptFTPServer(Long rptId){
        return persistentContext.findEntities("ReportFTPServer.getSaveRptFTPServer", StoredProcedureParameter.with("p_rpt_id",rptId));
    }

}
