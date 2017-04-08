package com.envista.msi.api.dao.reports;

import com.envista.msi.api.domain.PersistentContext;
import com.envista.msi.api.domain.util.QueryParameter;
import com.envista.msi.api.domain.util.StoredProcedureParameter;
import com.envista.msi.api.web.rest.dto.reports.*;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
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
    public List<ReportResultsUsersListDto> getUsersList() {
        return persistentContext.findEntitiesAndMapFields("ReportResultsUsersList.getUsersList",null);
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
                .and("reportName",updateSavedSchedReportDto.getReportName());;
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
