package com.envista.msi.api.service;

import com.envista.msi.api.dao.DaoException;
import com.envista.msi.api.dao.reports.ReportsDao;
import com.envista.msi.api.dao.reports.ReportsValidationDao;
import com.envista.msi.api.dao.reports.UserRoleDao;
import com.envista.msi.api.web.rest.dto.UserProfileDto;
import com.envista.msi.api.web.rest.dto.dashboard.DashboardAppliedFilterDto;
import com.envista.msi.api.web.rest.dto.reports.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 * Created by Sreenivas on 2/17/2017.
 */

@Service
@Transactional
public class ReportsService {

    @Inject
    private ReportsDao reportsDao;

    @Inject
    private ReportsValidationDao reportsValidationDao;
    @Inject
    private UserRoleDao roleDao;

    @Value("${EXPORTDIR}")
    private String exportDir;

    public List<ReportResultsDto> getReportResults(long userId) {
        return  reportsDao.getReportResults(userId);
    }
    public ReportResultsDto updateExpiryDate(Long generatedRptId,String expiryDate) {
        return  reportsDao.updateExpiryDate(generatedRptId,expiryDate);
    }

    public ReportResultsDto deleteReportInResults(long generatedRptId, long userId, String userName) {
        return  reportsDao.deleteReportInResults(generatedRptId, userId, userName);
    }
    public List<ReportResultsUsersListDto> getUsersList(String userName) {
        return  reportsDao.getUsersList(userName);
    }

    public List<SavedSchedReportsDto> getSavedSchedReports(long userId,long filterId){
        return reportsDao.getSavedSchedReports(userId,filterId);
    }

    public UpdateSavedSchedReportDto updateSavedSchedReport(UpdateSavedSchedReportDto updateSavedSchedReportDto){
        return reportsDao.updateSavedSchedReport(updateSavedSchedReportDto);
    }
    public UpdateSavedSchedReportDto runSavedSchedReport(UpdateSavedSchedReportDto updateSavedSchedReportDto){
        return reportsDao.runSavedSchedReport(updateSavedSchedReportDto);
    }
    public UpdateSavedSchedReportDto saveFromReportResults(UpdateSavedSchedReportDto updateSavedSchedReportDto){
        return reportsDao.saveFromReportResults(updateSavedSchedReportDto);
    }
    public ReportResultsUsersListDto pushToUser(List<ReportResultsUsersListDto> reportResultsUsersListDto) {

        for (ReportResultsUsersListDto usersListDto : reportResultsUsersListDto) {
            String msg = null;
            if (roleDao.verifyuserRole(usersListDto.getUserId(), "user").getVerificationMsg().equals("1") || roleDao.verifyuserRole(usersListDto.getUserId(), "carrier").getVerificationMsg().equals("1")) {
                Long rptId = reportsDao.getReportDetails(usersListDto.getSavedSchedRptId()).getRptId();
                if (rptId != null && rptId != 0) {
                    msg = reportsValidationDao.verifyAssignedReport(usersListDto.getUserId(), rptId).getVerificationMsg();
                    if (msg != null && !msg.equals("1")) {
                        throw new DaoException(msg + usersListDto.getUserName());
                    }

                } else {
                    throw new DaoException("Report not found ");
                }
                msg = reportsValidationDao.verifyAccounts(usersListDto.getSavedSchedRptId(), usersListDto.getUserId()).getVerificationMsg();
                if (msg != null && !(msg.contains("1,"))) {
                    throw new DaoException(msg);
                }
                msg = reportsValidationDao.verifyCarrier(usersListDto.getUserId(), rptId, usersListDto.getSavedSchedRptId()).getVerificationMsg();
                if (msg != null && !(msg.contains("1,"))) {
                    throw new DaoException(msg);
                }
                msg = reportsValidationDao.verifySavedSchedShippers(usersListDto.getSavedSchedRptId(), usersListDto.getUserId()).getVerificationMsg();
                if (msg != null && !(msg.contains("1,"))) {
                    throw new DaoException(msg);
                }
                msg =reportsValidationDao.verifySavedSchedShipperGroups(usersListDto.getSavedSchedRptId(),usersListDto.getUserId()).getVerificationMsg();
                if (msg != null && !(msg.contains("1,"))) {
                    throw new DaoException(msg);
                }
            }

        }
        return reportsDao.pushToUser(reportResultsUsersListDto);
    }
    public List<ReportModesDto> getReportForModes(Long userId) {
        return  reportsDao.getReportForModes(userId);
    }
    public List<ReportCustomerCarrierDto> getReportCustomers(Long rptId, Long userId){
        return reportsDao.getReportCustomers(rptId,userId);
    }
    public List<ReportCustomerCarrierDto> getReportCarrier(Long rptId, Long userId){
        return reportsDao.getReportCarrier(rptId,userId);
    }

    public  ReportCustomerCarrierDto getCustomerHierarchyObject(List<ReportCustomerCarrierDto> customerList) throws JSONException {
        return getCustomerHierarchyObject(customerList, true);
    }

    public  ReportCustomerCarrierDto getCustomerHierarchyObject( List<ReportCustomerCarrierDto> customerList, boolean shippersReq) throws JSONException {
        // First create all the customers/shipper groups/shipper level beans.
        // Data structure to hold all the customers before making them into groups.
        TreeSet<ReportCustomerCarrierDto> customerDtos = new TreeSet<ReportCustomerCarrierDto>();
        for(ReportCustomerCarrierDto customerDto : customerList) {
            String parentCustomerName =customerDto.getParentCustomerName();
            Long parentCustomerId = customerDto.getParentCustomerId();
            Long customerId = customerDto.getCustomerId();
            String customerName = customerDto.getCustomerName();
            Long shipperId = customerDto.getShipperId();
            String shipCodeName = customerDto.getShipCodeName();
            String carrierIds = customerDto.getCustomerCarrierId();
            Long shipperGroupId = customerDto.getShipperGroupId();
            boolean isPaidCust = customerDto.getPaidCust();
            String shipperGroupName = customerDto.getShipperGroupName();
            boolean selected = customerDto.getSelected() != null ? customerDto.getSelected() : false;
            ReportCustomerCarrierDto customerCarrDto = getByIdFromSet(customerDtos, customerId);
            String region = customerDto.getRegion();
            String currencyId = customerDto.getCurrencyId();
            if (customerCarrDto == null) {
                customerCarrDto = new ReportCustomerCarrierDto(customerName, customerId,selected,carrierIds,"CUST", parentCustomerId,parentCustomerName, isPaidCust, region, currencyId);
                customerDtos.add(customerCarrDto);
            }
            if(shippersReq){
                if (shipperGroupId!=null && shipperGroupId > 0) {
                    // Add a shipper group bean. and a shipper bean after that to the shipper group bean.
                    ReportCustomerCarrierDto custShipperGroup = getByIdFromSet(customerCarrDto.getCollection(), shipperGroupId);
                    if (custShipperGroup == null) {
                        custShipperGroup = new ReportCustomerCarrierDto(shipperGroupName, shipperGroupId, selected, carrierIds, "SHGRP", customerId, null, isPaidCust, region, currencyId);
                        customerCarrDto.getCollection().add(custShipperGroup);
                    }
                    // Add the shipper Id.
                    if (getByIdFromSet(custShipperGroup.getCollection(), shipperId) == null)
                        custShipperGroup.getCollection().add(new ReportCustomerCarrierDto(shipCodeName, shipperId, selected, carrierIds, "SHP", null, null, isPaidCust, region, currencyId));
                }
                if (shipperId!=null &&(shipperId > 0 && shipCodeName != null)) {
                    // Add the shipper Id to all shippers.
                    ReportCustomerCarrierDto allShipperCustDto = getByIdFromSet(customerCarrDto.getCollection(), new Long(-1));
                    if (allShipperCustDto == null) {
                        allShipperCustDto = new ReportCustomerCarrierDto("All Accounts", -1, false, "-1", "SHGRP", null, null, isPaidCust, region, currencyId);
                        customerCarrDto.getCollection().add(allShipperCustDto);
                    }
                    if (getByIdFromSet(allShipperCustDto.getCollection(), shipperId) == null)
                        allShipperCustDto.getCollection().add(new ReportCustomerCarrierDto(shipCodeName, shipperId, selected, carrierIds, "SHP", null, null, isPaidCust, region, currencyId));
                }
            }
        }
        // Set the Parent Customer relationships.
        ReportCustomerCarrierDto everything = new ReportCustomerCarrierDto("Everything", -1, false, "-1", "Everything", null, null, false, null, null);
        TreeSet<ReportCustomerCarrierDto> customerGroupIds = new TreeSet<ReportCustomerCarrierDto>();
        ReportCustomerCarrierDto customerGroups = new ReportCustomerCarrierDto("Customer Groups", -1, false, "-1", "CUGRP", null, null, false, null, null);
        StringBuffer customerIdsCSVForLevelInfo = new StringBuffer();
        int count = 0;
        for (ReportCustomerCarrierDto customerDto : customerDtos) {
            if (customerDto.getParentCustomerId()!=null && customerDto.getParentCustomerId() > 0) {
                customerGroupIds.add(customerDto);
                if (count > 0)
                    customerIdsCSVForLevelInfo.append(",");
                customerIdsCSVForLevelInfo.append(customerDto.getCustomerId());
                count++;

            } else {
                everything.getCollection().add(customerDto);
            }
        }
        // Get the level information for all the customers
        List<ReportCustomerCarrierDto> customerHeirarchy = reportsDao.getCustomerLevels(customerIdsCSVForLevelInfo.toString());
        // 19028;;3;;19024==Legrand North America;;19040==Legrand - Electrical Wiring Systems;;19028==Legrand - Wiremold
        if(customerHeirarchy!=null && customerHeirarchy.size()>0) {
            for (ReportCustomerCarrierDto customerHierarchyDto : customerHeirarchy) {
                String customerH = customerHierarchyDto.getValue();
                String[] customerDetA = customerH.split(";;");
                long customerId = Integer.parseInt(customerDetA[0]);
                int level = Integer.parseInt(customerDetA[1]);
                long grandCustomerId = -1;
                ReportCustomerCarrierDto customerBean = getByIdFromSet(customerGroupIds, customerId);
                for (int i = 0; i < customerDetA.length - 2; i++) {
                    String[] customerDetD = customerDetA[i + 2].split("==");
                    long parentCustomerId = Long.parseLong(customerDetD[0]);
                    String parentCustomerName = customerDetD[1];
                    // Parent cutomers.
                    ReportCustomerCarrierDto parentCustomerDto = findCustomerGroup(customerGroups, -parentCustomerId);
                    if (parentCustomerDto == null) {
                        if (i < level - 1) {
                            parentCustomerDto = new ReportCustomerCarrierDto(parentCustomerName + " Group", -parentCustomerId, false, "-1", "CUGRP", null, null, false, null, null);
                            if (i == 0) {
                                customerGroups.getCollection().add(parentCustomerDto);
                            } else {
                                ReportCustomerCarrierDto grandParent = findCustomerGroup(customerGroups, -grandCustomerId);
                                // Something unusual;
                                if (grandParent == null)
                                    break;
                                grandParent.getCollection().add(parentCustomerDto);
                            }
                        }
                    }
                    if (i == level - 1) {
                        if (parentCustomerDto != null) {
                            parentCustomerDto.getCollection().add(customerBean);
                        } else {
                            ReportCustomerCarrierDto grandParent = findCustomerGroup(customerGroups, -grandCustomerId);
                            if (grandParent == null) {
                                grandParent = new ReportCustomerCarrierDto(parentCustomerName + " Group", -parentCustomerId, false, "-1", "CUGRP", null, null, false, null, null);
                                customerGroups.getCollection().add(grandParent);
                            }
                            grandParent.getCollection().add(customerBean);
                        }
                    }
                    grandCustomerId = parentCustomerId;
                }
            }
        }
        everything.getCollection().addAll(customerGroups.getCollection());
        return everything;
    }

    public  ReportCustomerCarrierDto getByIdFromSet(Set<ReportCustomerCarrierDto> set, long customerId) {
        for (ReportCustomerCarrierDto customerDto : set) {
            if (customerDto.getCustomerId() == customerId)
                return customerDto;
        }
        return null;
    }

    public  ReportCustomerCarrierDto findCustomerGroup(ReportCustomerCarrierDto customerGroups, long customerId) {
        for (ReportCustomerCarrierDto customerDto : customerGroups.getCollection()) {
            if (customerDto.getCustomerId() == customerId)
                return customerDto;
            ReportCustomerCarrierDto child = findCustomerGroup(customerDto, customerId);
            if (child != null)
                return child;
        }
        return null;
    }

    public List<ReportFormatDto> getReportFormat(Long rptId) {
        return reportsDao.getReportFormat(rptId);
    }
    public ReportFolderDto createReportFolder(ReportFolderDto reportFolderDto, UserProfileDto userProfileDto){
        return reportsDao.createReportFolder(reportFolderDto,userProfileDto);
    }
    public ReportFolderDetailsDto moveRptsToFolder( ReportFolderDetailsDto rptFolderDetailsDto ){
        return reportsDao.moveReportToFolder(rptFolderDetailsDto);
    }

    public SavedSchedReportsDto changeOwnerBasedonSSRptId(String currentUserName, Long currentUserId, String newUserName, Long newUserId, Long ssRptId) {
        if (roleDao.verifyuserRole(newUserId, "user").getVerificationMsg().equals("1") || roleDao.verifyuserRole(newUserId, "carrier").getVerificationMsg().equals("1")) {
            Long rptId = reportsDao.getReportDetails(ssRptId).getRptId();
            String msg = null;
            if (rptId != null) {
               msg = reportsValidationDao.verifyAssignedReport(newUserId, rptId).getVerificationMsg();
                if (msg!=null && !msg.equals("1") ) {
                    throw new DaoException(msg + newUserName);
                }
                msg = reportsValidationDao.verifyAccounts(ssRptId, newUserId).getVerificationMsg();
                if (msg != null && !(msg.contains("1,"))) {
                    throw new DaoException(msg);
                }
                msg = reportsValidationDao.verifyCarrier(newUserId, rptId, ssRptId).getVerificationMsg();
                if (msg != null && !(msg.contains("1,"))) {
                    throw new DaoException(msg);
                }
            } else {
                throw new DaoException("Report not found ");
            }
          msg= reportsValidationDao.verifySavedSchedShippers(ssRptId,newUserId).getVerificationMsg();
         if (msg != null && !(msg.contains("1,")))
             throw  new DaoException(msg);
        }
        return reportsDao.changeOwnerBasedonSSRptId(currentUserName, currentUserId, newUserName, newUserId, ssRptId);

    }

    public File getReportFileDetails(Long generatedRptId) throws FileNotFoundException{

        List<ReportsFilesDto> reportsFilesDtoList = reportsDao.getReportFileDetails(generatedRptId);
        if(reportsFilesDtoList!=null && reportsFilesDtoList.size()>0){
            ReportsFilesDto reportsFilesDto = reportsFilesDtoList.get(0);

            String filePath = reportsFilesDto.getFilePath();

              if(exportDir!=null && !exportDir.isEmpty()){
                  filePath = filePath.replaceAll("E:",exportDir);
             }

                File file = new File(filePath);
              if(!file.exists()){
                  throw new FileNotFoundException(file.getName()+"File not exist ");
              }

            return file;
        }

        return null;
    }

    public SavedSchedReportDto saveSchedReport(SavedSchedReportDto savedSchedReportDto){

        SavedSchedReportDto savedSchedReport = reportsDao.saveSchedReport(savedSchedReportDto);

            if(savedSchedReport.getSavedSchedRptId()>0){
                if(savedSchedReportDto.getReportsInclColDtoList() == null || savedSchedReportDto.getReportsInclColDtoList().size()==0){
                    ArrayList<ReportColumnDto> defaultInclCols = (ArrayList<ReportColumnDto>) reportsDao.getDefaultInclExclCol(savedSchedReport.getSavedSchedRptId(),
                            savedSchedReportDto.getRptId(),savedSchedReportDto.getCreateUser());
                    ArrayList<ReportsInclColDto> finalColDto = new ArrayList<ReportsInclColDto>();
                    for(ReportColumnDto columnDto : defaultInclCols){
                        ReportsInclColDto inclColDto = new ReportsInclColDto();
                        inclColDto.setSavedSchdRptId(savedSchedReport.getSavedSchedRptId());
                        inclColDto.setRptDetailsId(columnDto.getRptDetailsId());
                        inclColDto.setCreateUser(columnDto.getCreateUser());
                        finalColDto.add(inclColDto);
                    }
                    savedSchedReportDto.setReportsInclColDtoList(finalColDto);
                }

                inserChildTables(savedSchedReportDto,savedSchedReport.getSavedSchedRptId());
            }

        return savedSchedReport;
    }

    public SavedSchedReportDto updateSchedReport(SavedSchedReportDto savedSchedReportDto){

        SavedSchedReportDto savedSchedReport = reportsDao.updateSchedReport(savedSchedReportDto);

        if(savedSchedReport.getUpdateCount()!=null && savedSchedReport.getUpdateCount() >0){
            reportsDao.deleteChildDataSchedReport(savedSchedReportDto);
            inserChildTables(savedSchedReportDto,savedSchedReport.getSavedSchedRptId());
        }

        return savedSchedReport;
    }

    public SavedSchedReportDto saveSchedPacketReport(SavedSchedReportDto savedSchedReportDto){

        SavedSchedReportDto savedSchedReport = new SavedSchedReportDto();
        if(savedSchedReportDto.getReportPacketsDetList() !=null && savedSchedReportDto.getReportPacketsDetList().size()>0){

            savedSchedReport = reportsDao.saveSchedReport(savedSchedReportDto);

            if(savedSchedReport.getSavedSchedRptId()>0){

                for(ReportPacketsDetDto packetsDto : savedSchedReportDto.getReportPacketsDetList()){
                    packetsDto.setSavedSchdRptId(savedSchedReport.getSavedSchedRptId());
                    ReportPacketsDetDto outPacketDto = reportsDao.saveSchedPacketReport(packetsDto);
                }

                if(savedSchedReportDto.getSavedSchedUsersDtoList()!=null && savedSchedReportDto.getSavedSchedUsersDtoList().size()>0){
                    for(ReportSavedSchdUsersDto saveSchedUser : savedSchedReportDto.getSavedSchedUsersDtoList()){
                        saveSchedUser.setSavedSchdRptId(savedSchedReport.getSavedSchedRptId());
                        ReportSavedSchdUsersDto outUserDto = reportsDao.saveSchedUser(saveSchedUser);
                    }
                }

            }
        }
        return savedSchedReport;
    }
    public SavedSchedReportDto updateSchedPacketReport(SavedSchedReportDto savedSchedReportDto){

        SavedSchedReportDto savedSchedReport = reportsDao.updateSchedReport(savedSchedReportDto);

        if(savedSchedReport.getUpdateCount()!=null && savedSchedReport.getUpdateCount() >0){
            reportsDao.deletePacketsDataSchedReport(savedSchedReportDto);
            for(ReportPacketsDetDto packetsDto : savedSchedReportDto.getReportPacketsDetList()){
                packetsDto.setSavedSchdRptId(savedSchedReportDto.getSavedSchedRptId());
                ReportPacketsDetDto outPacketDto = reportsDao.saveSchedPacketReport(packetsDto);
            }

            if(savedSchedReportDto.getSavedSchedUsersDtoList()!=null && savedSchedReportDto.getSavedSchedUsersDtoList().size()>0){
                for(ReportSavedSchdUsersDto saveSchedUser : savedSchedReportDto.getSavedSchedUsersDtoList()){
                    saveSchedUser.setSavedSchdRptId(savedSchedReportDto.getSavedSchedRptId());
                    ReportSavedSchdUsersDto outUserDto = reportsDao.saveSchedUser(saveSchedUser);
                }
            }
        }
        return savedSchedReport;
    }
    public void inserChildTables(SavedSchedReportDto savedSchedReportDto,Long savedSchedRrtId){

        if(savedSchedReportDto.getSavedSchedUsersDtoList()!=null && savedSchedReportDto.getSavedSchedUsersDtoList().size()>0){
            for(ReportSavedSchdUsersDto saveSchedUser : savedSchedReportDto.getSavedSchedUsersDtoList()){
                saveSchedUser.setSavedSchdRptId(savedSchedRrtId);
                ReportSavedSchdUsersDto outUserDto = reportsDao.saveSchedUser(saveSchedUser);
            }
        }
        if(savedSchedReportDto.getSavedSchedAccountsDtoList()!=null && savedSchedReportDto.getSavedSchedAccountsDtoList().size()>0){
            for(ReportsSavedSchdAccountDto accoutsDto : savedSchedReportDto.getSavedSchedAccountsDtoList()){
                accoutsDto.setSavedSchdRptId(savedSchedRrtId);
                reportsDao.saveSchedAccountsDetails(accoutsDto);
            }
        }

        if(savedSchedReportDto.getReportCriteriaList()!=null && savedSchedReportDto.getReportCriteriaList().size()>0){
            for(ReportSavedSchdCriteriaDto criteriaDto : savedSchedReportDto.getReportCriteriaList()){
                criteriaDto.setSavedSchdRptId(savedSchedRrtId);
                reportsDao.saveSchedCriterisDetails(criteriaDto);
            }
        }

        if(savedSchedReportDto.getReportsInclColDtoList()!=null && savedSchedReportDto.getReportsInclColDtoList().size()>0){
            for(ReportsInclColDto inclColDto : savedSchedReportDto.getReportsInclColDtoList()){
                inclColDto.setSavedSchdRptId(savedSchedRrtId);
                reportsDao.saveSchedIncColDetails(inclColDto);
            }
        }

        if(savedSchedReportDto.getReportsSortColDtoList()!=null && savedSchedReportDto.getReportsSortColDtoList().size()>0){
            for(ReportsSortDto sortColDto : savedSchedReportDto.getReportsSortColDtoList()){
                sortColDto.setSavedSchedRptId(savedSchedRrtId);
                reportsDao.saveSchedSortColDetails(sortColDto);
            }
        }
    }

    public List<ReportFormatDto> getReportDateOptions(Long rptId) {
        return reportsDao.getReportDateOptions(rptId);
    }

    public List<ReportColumnDto> getReportCriteria(Long userId, Long rptId, String carrierIds){ return reportsDao.getReportCriteria(userId,rptId,carrierIds); }

    public List<ReportColumnDto> getIncludeExcludeSortCol(Long userId, Long rptId, String carrierIds){
        List<ReportColumnDto> inclExclColNameDtos = reportsDao.getSavedIncludeExcludeColNameOrder(userId, rptId, carrierIds);
        List<ReportColumnDto> inclExclColSequenceDtos = reportsDao.getSavedIncludeExcludeColSequencOrder(userId, rptId, carrierIds);
        List<ReportColumnDto>  inclExclColDtos  = new ArrayList<ReportColumnDto>();
        if (inclExclColSequenceDtos != null && inclExclColSequenceDtos.size() > 0){
            for (ReportColumnDto sequenceColDto : inclExclColSequenceDtos) {
                inclExclColDtos.add(sequenceColDto);
            }
        }
        if (inclExclColNameDtos != null && inclExclColNameDtos.size() > 0) {
            for (ReportColumnDto colNameDto : inclExclColNameDtos) {
                inclExclColDtos.add(colNameDto);
            }
        }
        return inclExclColDtos;
    }

    public List<ReportCodeValueDto> getReportLocaleLabel(Long rptId){ return reportsDao.getReportLocaleLabel(rptId);  }

    public List<ReportCodeValueDto> getReportCurrencyLabel(Long rptId){ return reportsDao.getReportCurrencyLabel(rptId); }

    public List<ReportCodeValueDto> getReportWeightLabel(Long rptId){ return reportsDao.getReportWeightLabel(rptId); }

    public List<ReportFormatDto> getControlNumber(String customerIds,Integer payRunNo,Integer checkNo){ return  reportsDao.getControlNumber(customerIds,payRunNo,checkNo); }

    public List<ReportFolderDto> getReportFolder(Long userId){ return  reportsDao.getReportFolder(userId); }

    public JSONObject getReportFTPServer(String customerIds,String shipperGroupIds ,String shipperIds,Long rptId) throws  Exception{
        List<ReportFTPServerDto> ftpServerDtos=reportsDao.getReportFTPServer( customerIds, shipperGroupIds , shipperIds);
        List<ReportFTPServerDto> ftpAccountDtos=reportsDao.getSaveRptFTPServer(rptId);
        JSONObject jsonObjectReturn=new JSONObject();
        JSONArray jsonArray = new JSONArray();
        boolean isSavedFTPAccountPresent=false;
        if(ftpServerDtos!=null){
            for (ReportFTPServerDto ftpServerDto:ftpServerDtos) {
                if (ftpServerDto != null) {
                    JSONObject jsonObject = new JSONObject();
                    if (ftpAccountDtos != null) {
                        for (ReportFTPServerDto savedServer : ftpAccountDtos) {
                            if (savedServer != null) {
                                if (ftpServerDto.getFtpAccountId()!=null && savedServer.getFtpAccountId()!=null && ftpServerDto.getFtpAccountId()==savedServer.getFtpAccountId())
                                    isSavedFTPAccountPresent = true;
                            }
                        }
                    }
                    jsonObject.put("FTPSERVERNAME", ftpServerDto.getFtpAccountName());
                    jsonObject.put("FTPSERVERID", ftpServerDto.getFtpAccountId());
                    jsonArray.put(jsonObject);
                }

            }
        }
        if (!isSavedFTPAccountPresent ) {
            if (ftpAccountDtos != null) {
                for (ReportFTPServerDto savedServer : ftpAccountDtos) {
                    if (savedServer != null) {
                        if (savedServer.getFtpAccountId()!=null) {
                            JSONObject jsonObject = new JSONObject();
                            if (!savedServer.getIsActive())
                                jsonObject.put("FTPSERVERNAME", savedServer.getFtpAccountName() + "-Inactive");
                            else
                                jsonObject.put("FTPSERVERNAME", savedServer.getFtpAccountName() + "-Invalid");
                            jsonObject.put("FTPSERVERID", savedServer.getFtpAccountId());
                            jsonArray.put(jsonObject);
                        }
                    }
                }
            }
        }
        return jsonObjectReturn.put("ftpServers", jsonArray);
    }

    public List<ReportUserListByRptIdDto> getUserListByRptId(Long rptId){
        return reportsDao.getUsersListByRptId(rptId);
    }
    public SavedSchedReportDto getReportDetails(Long savedSchedRptId) {

        SavedSchedReportDto savedSchedReportDto = reportsDao.getReportDetails(savedSchedRptId);

        if(savedSchedReportDto.getPacket()!=null && savedSchedReportDto.getPacket()) {
            savedSchedReportDto.setReportPacketsDetList((ArrayList<ReportPacketsDetDto>) reportsDao.getReportPacketDtlsList(savedSchedRptId));
        }
        savedSchedReportDto.setSavedSchedUsersDtoList ((ArrayList<ReportSavedSchdUsersDto>)reportsDao.getReportSSUsersList(savedSchedRptId));

        return savedSchedReportDto;
    }
    public List<ReportColumnDto> getDefaultInclExclCol(Long saveSchedId,Long rptId,String createUser){
        return reportsDao.getDefaultInclExclCol(saveSchedId,rptId,createUser);
    }
    public  JSONArray getReportTriggerOptions(Long rptId,String carrierIds) throws Exception{
        List<ReportFormatDto> triggerOptionsDtos=reportsDao.getReportTriggerOptions(rptId,carrierIds);
        JSONArray jsonArray=new JSONArray();
        if(triggerOptionsDtos!=null){
            for(ReportFormatDto dto:triggerOptionsDtos){
                JSONObject triggerOptionJson=new JSONObject();
                if(dto.getDateCriteriaName().equals("Invoice Date")){
                    triggerOptionJson.put("triggerOptionName","New Invoice");
                    triggerOptionJson.put("triggerOptionId",dto.getRptDateOptionId());
                    triggerOptionJson.put("isDefault",dto.getIsDefault());
                }
                if(dto.getDateCriteriaName().equals("Control Number")){
                    triggerOptionJson.put("triggerOptionName","New Control Number");
                    triggerOptionJson.put("triggerOptionId",dto.getRptDateOptionId());
                    triggerOptionJson.put("isDefault",dto.getIsDefault());
                }
                if(dto.getDateCriteriaName().equals("Closed Date")){
                    triggerOptionJson.put("triggerOptionName","Recently Closed");
                    triggerOptionJson.put("triggerOptionId",dto.getRptDateOptionId());
                    triggerOptionJson.put("isDefault",dto.getIsDefault());
                }
                if(dto.getDateCriteriaName().equals("Pay Run Number")){
                    triggerOptionJson.put("triggerOptionName","New Pay Run Number");
                    triggerOptionJson.put("triggerOptionId",dto.getRptDateOptionId());
                    triggerOptionJson.put("isDefault",dto.getIsDefault());
                }
                if(dto.getDateCriteriaName().equals("New Check Details")){
                    triggerOptionJson.put("triggerOptionName","New Check Details");
                    triggerOptionJson.put("triggerOptionId",dto.getRptDateOptionId());
                    triggerOptionJson.put("isDefault",dto.getIsDefault());
                }
                jsonArray.put(triggerOptionJson);
            }
        }
        return  jsonArray;
    }

    public ReportFolderDto deleteFolder(Long rptFolderId, Long userId) {
        return reportsDao.deleteFolder(rptFolderId,userId);
    }
}
