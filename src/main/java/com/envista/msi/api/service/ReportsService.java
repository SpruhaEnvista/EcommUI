package com.envista.msi.api.service;

import com.envista.msi.api.dao.DaoException;
import com.envista.msi.api.dao.reports.ReportsDao;
import com.envista.msi.api.dao.type.GenericObject;
import com.envista.msi.api.domain.util.ReportsUtil;
import com.envista.msi.api.domain.util.StringEncrypter;
import com.envista.msi.api.web.rest.dto.UserDetailsDto;
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
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.sql.SQLException;
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
    private MailService mailService;


    @Inject
    private ReportsValidationDao reportsValidationDao;
    @Inject
    private UserRoleDao roleDao;

    @Value("${EXPORTDIR}")
    private String exportDir;
    @Value("${PRODEXPORTDIR}")
    private String prodExportDir;
    @Value("${FILESERVER}")
    private String fileServer;

    @Value("${PRODFILESERVER}")
    private String prodFileServer;

    @Value("${emailUnsubscribe.html}")
    private String emailHtml;

    @Value("${emailUnsubscribe.subject}")
    private String emailSubject;

    @Value("${website.0}")
    private String website;

    @Value("${support.emailid.0}")
    private String supportEmailId;

    @Value("${from.emailid.0}")
    private String fromEmailId;

    public List<ReportResultsDto> getReportResults(Long userId,String showAll,String orderBy, String ascDesc) {
        return  reportsDao.getReportResults(userId,showAll,orderBy,ascDesc);
    }
    public ReportResultsDto getGerPermissions(Long userId) {
        return  reportsDao.getGerPermissions(userId);
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

    public List<SavedSchedReportsDto> getSavedSchedReports(Long userId,Long filterId,String orderBy, String ascDesc){
        return reportsDao.getSavedSchedReports(userId,filterId,orderBy,ascDesc);
    }
    public List<SavedSchedReportsDto> getSavedSchedTemplates(Long userId){
        return reportsDao.getSavedSchedTemplates(userId);
    }

    public UpdateSavedSchedReportDto updateSavedSchedReport(UpdateSavedSchedReportDto updateSavedSchedReportDto){
        String msg = null;
        if(updateSavedSchedReportDto.getSharetoUserId()>0){
            if(roleDao.verifyuserRole(updateSavedSchedReportDto.getSharetoUserId(), "user").getVerificationMsg().equals("1") || roleDao.verifyuserRole(updateSavedSchedReportDto.getSharetoUserId(), "carrier").getVerificationMsg().equals("1")) {
                Long rptId = reportsDao.getReportDetails(updateSavedSchedReportDto.getSavedSchedRptId()).getRptId();
                if (rptId != null && rptId != 0) {
                    msg = reportsValidationDao.verifyAssignedReport(updateSavedSchedReportDto.getSharetoUserId(), rptId).getVerificationMsg();
                    if (msg != null && !msg.equals("1")) {
                        throw new DaoException(msg + updateSavedSchedReportDto.getSharetoUserId());
                    }

                    msg = reportsValidationDao.verifyAccounts(updateSavedSchedReportDto.getSavedSchedRptId(), updateSavedSchedReportDto.getSharetoUserId()).getVerificationMsg();
                    if (msg != null && !(msg.contains("1,"))) {
                        throw new DaoException(msg);
                    }
                    if (roleDao.verifyuserRole(updateSavedSchedReportDto.getSharetoUserId(), "carrier").getVerificationMsg().equals("1")) {
                        msg = reportsValidationDao.verifyCarrier(updateSavedSchedReportDto.getSharetoUserId(), rptId, updateSavedSchedReportDto.getSavedSchedRptId()).getVerificationMsg();
                        if (msg != null && !(msg.contains("1,"))) {
                            throw new DaoException(msg);
                        }
                    }
                    msg = reportsValidationDao.verifySavedSchedShippers(updateSavedSchedReportDto.getSavedSchedRptId(), updateSavedSchedReportDto.getSharetoUserId()).getVerificationMsg();
                    if (msg != null && !(msg.contains("1,"))) {
                        throw new DaoException(msg);
                    }
                    msg = reportsValidationDao.verifySavedSchedShipperGroups(updateSavedSchedReportDto.getSavedSchedRptId(), updateSavedSchedReportDto.getSharetoUserId()).getVerificationMsg();
                    if (msg != null && !(msg.contains("1,"))) {
                        throw new DaoException(msg);
                    }
                }
            }
        }
        return reportsDao.updateSavedSchedReport(updateSavedSchedReportDto);
    }
    public UpdateSavedSchedReportDto runSavedSchedReport(UpdateSavedSchedReportDto updateSavedSchedReportDto){
        return reportsDao.runSavedSchedReport(updateSavedSchedReportDto);
    }
    public UpdateSavedSchedReportDto saveFromReportResults(UpdateSavedSchedReportDto updateSavedSchedReportDto){
        return reportsDao.saveFromReportResults(updateSavedSchedReportDto);
    }
    public ReportResultsUsersListDto pushToUser(List<ReportResultsUsersListDto> reportResultsUsersListDto) throws Exception {
        for (ReportResultsUsersListDto usersListDto : reportResultsUsersListDto) {
            String msg = null;
            if (roleDao.verifyuserRole(usersListDto.getUserId(), "user").getVerificationMsg().equals("1") || roleDao.verifyuserRole(usersListDto.getUserId(), "carrier").getVerificationMsg().equals("1")) {
                Long rptId = reportsDao.getReportDetails(usersListDto.getSavedSchedRptId()).getRptId();
                if (rptId != null && rptId != 0) {
                    msg = reportsValidationDao.verifyAssignedReport(usersListDto.getUserId(), rptId).getVerificationMsg();
                    if (msg != null && !msg.equals("1")) {
                        throw new DaoException(msg + usersListDto.getUserName());
                    }

                    msg = reportsValidationDao.verifyAccounts(usersListDto.getSavedSchedRptId(), usersListDto.getUserId()).getVerificationMsg();
                    if (msg != null && !(msg.contains("1,"))) {
                        throw new DaoException(msg);
                    }
                    if (roleDao.verifyuserRole(usersListDto.getUserId(), "carrier").getVerificationMsg().equals("1")) {
                        msg = reportsValidationDao.verifyCarrier(usersListDto.getUserId(), rptId, usersListDto.getSavedSchedRptId()).getVerificationMsg();
                        if (msg != null && !(msg.contains("1,"))) {
                            throw new DaoException(msg);
                        }
                    }
                    msg = reportsValidationDao.verifySavedSchedShippers(usersListDto.getSavedSchedRptId(), usersListDto.getUserId()).getVerificationMsg();
                    if (msg != null && !(msg.contains("1,"))) {
                        throw new DaoException(msg);
                    }
                    msg = reportsValidationDao.verifySavedSchedShipperGroups(usersListDto.getSavedSchedRptId(), usersListDto.getUserId()).getVerificationMsg();
                    if (msg != null && !(msg.contains("1,"))) {
                        throw new DaoException(msg);
                    }
                }

            }
        }
        if(reportResultsUsersListDto!=null && reportResultsUsersListDto.size()>0){
            for(ReportResultsUsersListDto user : reportResultsUsersListDto){
                ReportGeneratedDetailsDto genRptDetailsDto = new ReportGeneratedDetailsDto();
                genRptDetailsDto.setSavedSchedRptId(user.getSavedSchedRptId());
                if(user.getGeneratedRptId()>0) {
                    genRptDetailsDto = reportsDao.getGenReportDetails(user.getGeneratedRptId());
                }
                UserDetailsDto userDetails = reportsDao.getUserDetailsById(user.getUserId());
                try{
                if(user.isEmailAlert()) {
                    sendReportConfirmationEmail(genRptDetailsDto, userDetails.getEmail(), "", user.isAttachReport(),fromEmailId ,
                    0, false, false);
                }
                }catch (Exception e){
                    throw new Exception("There is problem in sending email");
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
    public List<ReportCustomerCarrierDto> getReportCarrier(Long rptId, Long userId,String customerIds){
        return reportsDao.getReportCarrier(rptId,userId,customerIds);
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

        String rgn = "";
        String currId = "";
        if(customerList != null && customerList.size() > 0){
            rgn = customerList.get(0).getRegion();
            currId = customerList.get(0).getCurrencyId();
        }
        // Set the Parent Customer relationships.
        ReportCustomerCarrierDto everything = new ReportCustomerCarrierDto("Everything", -1, false, "-1", "Everything", null, null, false, rgn, currId);
        TreeSet<ReportCustomerCarrierDto> customerGroupIds = new TreeSet<ReportCustomerCarrierDto>();
        ReportCustomerCarrierDto customerGroups = new ReportCustomerCarrierDto("Customer Groups", -1, false, "-1", "CUGRP", null, null, false, rgn, currId);
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
                            parentCustomerDto = new ReportCustomerCarrierDto(parentCustomerName + " Group", -parentCustomerId, false, "-1", "CUGRP", null, null, false, rgn, currId);
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
                                grandParent = new ReportCustomerCarrierDto(parentCustomerName + " Group", -parentCustomerId, false, "-1", "CUGRP", null, null, false, rgn, currId);
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
            if (rptId != null && rptId!=0) {
                msg = reportsValidationDao.verifyAssignedReport(newUserId, rptId).getVerificationMsg();
                if (msg != null && !msg.equals("1")) {
                    throw new DaoException(msg + newUserName);
                }
                msg = reportsValidationDao.verifyAccounts(ssRptId, newUserId).getVerificationMsg();
                if (msg != null && !(msg.contains("1,"))) {
                    throw new DaoException(msg);
                }
                if (roleDao.verifyuserRole(newUserId, "carrier").getVerificationMsg().equals("1")) {
                    msg = reportsValidationDao.verifyCarrier(newUserId, rptId, ssRptId).getVerificationMsg();
                    if (msg != null && !(msg.contains("1,"))) {
                        throw new DaoException(msg);
                    }
                }

                msg = reportsValidationDao.verifySavedSchedShippers(ssRptId, newUserId).getVerificationMsg();
                if (msg != null && !(msg.contains("1,")))
                    throw new DaoException(msg);
            }
        }
        return reportsDao.changeOwnerBasedonSSRptId(currentUserName, currentUserId, newUserName, newUserId, ssRptId);

    }

    public File getReportFileDetails(Long generatedRptId) throws FileNotFoundException{

        List<ReportsFilesDto> reportsFilesDtoList = reportsDao.getReportFileDetails(generatedRptId);
        if(reportsFilesDtoList!=null && reportsFilesDtoList.size()>0){
            ReportsFilesDto reportsFilesDto = reportsFilesDtoList.get(0);
            String filePath = getFileServerAbsolutePath(reportsFilesDto.getFilePath());
                File file = new File(filePath);
                if(!file.exists()){
                    filePath = reportsFilesDto.getFilePath();
                    file = new File(filePath);
                    if(!file.exists()) {
                        throw new FileNotFoundException(file.getName() + "File not exist ");
                    }
              }
            return file;
        }
        return null;
    }

    public String getFileServerAbsolutePath(String physicalFileName) throws FileNotFoundException {

        String drive = physicalFileName.substring(physicalFileName.lastIndexOf('\\')+1, physicalFileName.length());
        physicalFileName = fileServer + drive;
        if (!(new File(physicalFileName)).exists()) {
            physicalFileName = physicalFileName.replace("$", "");
        }

        return physicalFileName;
    }
    public String getProdFileServerAbsolutePath(String physicalFileName) throws FileNotFoundException {

        String drive = physicalFileName.substring(0, physicalFileName.indexOf('\\'));
        String relativeFileLocation = physicalFileName.substring(physicalFileName.indexOf('\\'));
        physicalFileName = "\\\\" + prodFileServer + "\\" + drive.toLowerCase().replace(":", "$") + relativeFileLocation;
        if (!(new File(physicalFileName)).exists()) {
            physicalFileName = physicalFileName.replace("$", "");
        }

        return physicalFileName;
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
                if(savedSchedReportDto.getRptFolderId()!=null && savedSchedReportDto.getRptFolderId()>0){
                    moveReportToFolder(savedSchedReport.getSavedSchedRptId(),savedSchedReportDto.getRptFolderId());
                }

                inserChildTables(savedSchedReportDto,savedSchedReport.getSavedSchedRptId());
            }

        return savedSchedReport;
    }
    private void moveReportToFolder(Long savedSchedRptId, Long rptFolderId){
        ReportFolderDetailsDto rptFolderDetails = new ReportFolderDetailsDto();
        rptFolderDetails.setSavedSchdReportId(savedSchedRptId);
        rptFolderDetails.setReportFolderId(rptFolderId);
        reportsDao.moveReportToFolder(rptFolderDetails);
    }

    public SavedSchedReportDto updateSchedReport(SavedSchedReportDto savedSchedReportDto){

        SavedSchedReportDto savedSchedReport = reportsDao.updateSchedReport(savedSchedReportDto);

        if(savedSchedReport.getUpdateCount()!=null && savedSchedReport.getUpdateCount() >0){
            reportsDao.deleteChildDataSchedReport(savedSchedReportDto);
            inserChildTables(savedSchedReportDto,savedSchedReportDto.getSavedSchedRptId());
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
                    for(ReportSavedSchdUsersDto saveSchedUser : removeDuplicateUsers(savedSchedReportDto.getSavedSchedUsersDtoList())){
                        saveSchedUser.setSavedSchedRptId(savedSchedReport.getSavedSchedRptId());
                        ReportSavedSchdUsersDto outUserDto = reportsDao.saveSchedUser(saveSchedUser);
                    }
                }
                if(savedSchedReportDto.getRptFolderId()!=null && savedSchedReportDto.getRptFolderId()>0){
                    moveReportToFolder(savedSchedReport.getSavedSchedRptId(),savedSchedReportDto.getRptFolderId());
                }
            }

        }
        return savedSchedReport;
    }

    public ArrayList<ReportSavedSchdUsersDto> removeDuplicateUsers(ArrayList<ReportSavedSchdUsersDto> reportsavedUsersList){

        ArrayList<ReportSavedSchdUsersDto> finaluserlist =  new ArrayList<ReportSavedSchdUsersDto>();
        ArrayList<ReportSavedSchdUsersDto> secuserlist =  new ArrayList<ReportSavedSchdUsersDto>();
        boolean unique = true;
        ReportSavedSchdUsersDto finalObjforDup  = new ReportSavedSchdUsersDto();
        ArrayList<Long> usersList = new ArrayList<Long>();
        ArrayList<Long> finalObjUsersList = new ArrayList<Long>();

        for(ReportSavedSchdUsersDto saveSchedUser : reportsavedUsersList){
                if(usersList.contains(saveSchedUser.getUserId())){
                    secuserlist.add(saveSchedUser);
                }
            usersList.add(saveSchedUser.getUserId());
        }
        for(ReportSavedSchdUsersDto saveSchedUser : reportsavedUsersList){
            unique = true;
            finalObjforDup  = new ReportSavedSchdUsersDto();
            for(ReportSavedSchdUsersDto dupUser : secuserlist){
                if(dupUser.getUserId() == saveSchedUser.getUserId()){
                    unique = false;
                    finalObjforDup  = new ReportSavedSchdUsersDto();
                    finalObjforDup.setUserId(dupUser.getUserId());
                    finalObjforDup.setSavedSchedRptId(dupUser.getSavedSchedRptId());
                    finalObjforDup.setCreateUser(dupUser.getCreateUser());

                    if(dupUser.getShared() || saveSchedUser.getShared()){
                        finalObjforDup.setShared(true);
                    }
                    if(dupUser.getEmailTemplateToBeSent() || saveSchedUser.getEmailTemplateToBeSent()){
                        finalObjforDup.setEmailTemplateToBeSent(true);
                    }
                    if(dupUser.getReportAttachedMail() || saveSchedUser.getReportAttachedMail()){
                        finalObjforDup.setReportAttachedMail(true);
                    }
                    if(dupUser.getCanEdit() || saveSchedUser.getCanEdit()){
                        finalObjforDup.setCanEdit(true);
                    }
                    if(dupUser.getReportSubscribed() || saveSchedUser.getReportSubscribed()){
                        finalObjforDup.setReportSubscribed(true);
                    }
                }

            }
            if(!finalObjUsersList.contains(saveSchedUser.getUserId())) {
                if (unique) {
                    finaluserlist.add(saveSchedUser);
                } else {
                    finaluserlist.add(finalObjforDup);
                }
                finalObjUsersList.add(saveSchedUser.getUserId());
            }

        }
        return finaluserlist;
    }

    public SavedSchedReportDto updateSchedPacketReport(SavedSchedReportDto savedSchedReportDto){

        SavedSchedReportDto savedSchedReport = reportsDao.updateSchedReport(savedSchedReportDto);

        if(savedSchedReport.getUpdateCount()!=null && savedSchedReport.getUpdateCount() >0){
            reportsDao.deletePacketsDataSchedReport(savedSchedReportDto);
            for(ReportPacketsDetDto packetsDto : savedSchedReportDto.getReportPacketsDetList()){
                packetsDto.setSavedSchdRptId(savedSchedReportDto.getSavedSchedRptId());
                ReportPacketsDetDto outPacketDto = reportsDao.saveSchedPacketReport(packetsDto);
            }
            if(savedSchedReport.getUpdateCount()!=null && savedSchedReport.getUpdateCount() >0){
                reportsDao.deleteChildDataSchedReport(savedSchedReportDto);
            }

            if(savedSchedReportDto.getSavedSchedUsersDtoList()!=null && savedSchedReportDto.getSavedSchedUsersDtoList().size()>0){
                for(ReportSavedSchdUsersDto saveSchedUser : removeDuplicateUsers(savedSchedReportDto.getSavedSchedUsersDtoList())){
                    saveSchedUser.setSavedSchedRptId(savedSchedReportDto.getSavedSchedRptId());
                    ReportSavedSchdUsersDto outUserDto = reportsDao.saveSchedUser(saveSchedUser);
                }
            }
        }
        return savedSchedReport;
    }
    public void inserChildTables(SavedSchedReportDto savedSchedReportDto,Long savedSchedRrtId){

        if(savedSchedReportDto.getSavedSchedUsersDtoList()!=null && savedSchedReportDto.getSavedSchedUsersDtoList().size()>0){
            for(ReportSavedSchdUsersDto saveSchedUser : removeDuplicateUsers(savedSchedReportDto.getSavedSchedUsersDtoList())){
                saveSchedUser.setSavedSchedRptId(savedSchedRrtId);
                ReportSavedSchdUsersDto outUserDto = reportsDao.saveSchedUser(saveSchedUser);
            }
        }
        if(savedSchedReportDto.getSavedSchedUsersDtoList()!=null && savedSchedReportDto.getSavedSchedUsersDtoList().size()>0){
            for(ReportSavedSchdUsersDto saveUserGen : removeDuplicateUsers(savedSchedReportDto.getSavedSchedUsersDtoList())){
                ReportUserGenStatusDto saveUserGenStatus = new ReportUserGenStatusDto();
                saveUserGenStatus.setSavedSchedRptId(savedSchedRrtId);
                saveUserGenStatus.setUserId(saveUserGen.getUserId());
                saveUserGenStatus.setCreateUser(saveUserGen.getCreateUser());
                ReportUserGenStatusDto outUserDto = reportsDao.saveUserGenStatus(saveUserGenStatus);
            }
        }
        if(savedSchedReportDto.getSavedSchedAccountsDtoList()!=null && savedSchedReportDto.getSavedSchedAccountsDtoList().size()>0){
            ArrayList<GenericObject> genericObjectAcctList = new ArrayList<GenericObject>();
            for(ReportsSavedSchdAccountDto accoutsDto : savedSchedReportDto.getSavedSchedAccountsDtoList()){
                GenericObject genericObject = new  GenericObject();
                genericObject.setParam1(String.valueOf(savedSchedRrtId));
                genericObject.setParam2(String.valueOf(accoutsDto.getCustomerId()));
                genericObject.setParam3(accoutsDto.getCreateUser());
                genericObjectAcctList.add(genericObject);
            }
            try {
                reportsDao.saveSchedAcctDetails(genericObjectAcctList);
            } catch (SQLException e) {
            }
        }

        if(savedSchedReportDto.getReportCriteriaList()!=null && savedSchedReportDto.getReportCriteriaList().size()>0){
            for(ReportSavedSchdCriteriaDto criteriaDto : savedSchedReportDto.getReportCriteriaList()){
                criteriaDto.setSavedSchdRptId(savedSchedRrtId);
                reportsDao.saveSchedCriterisDetails(criteriaDto);
            }
        }

        if(savedSchedReportDto.getReportsInclColDtoList()!=null && savedSchedReportDto.getReportsInclColDtoList().size()>0){
            ArrayList<GenericObject> genericObjectList = new ArrayList<GenericObject>();
            for(ReportsInclColDto inclColDto : savedSchedReportDto.getReportsInclColDtoList()){
                GenericObject genericObject = new  GenericObject();
                genericObject.setParam1(String.valueOf(savedSchedRrtId));
                genericObject.setParam2(String.valueOf(inclColDto.getRptDetailsId()));
                genericObject.setParam3(inclColDto.getCreateUser());
                genericObjectList.add(genericObject);
            }
            try {
                reportsDao.saveSchedIncColDetails(genericObjectList);
            } catch (SQLException e) {
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

    public List<ReportFormatDto> getControlNumber(String customerIds,Integer payRunNo,Integer checkNo){
        String customerCondition="";
        int count=0;
        List<List<ReportFormatDto>> list=new ArrayList<List<ReportFormatDto>>() ;
        List<ReportFormatDto> resultList=new ArrayList<ReportFormatDto>();
        if (customerIds!=null && (customerIds.trim()).length()>0) {
            customerCondition="";
            String[] customerIdArr=customerIds.split(",");
            for(int i=0;i<customerIdArr.length;i++){
                if (count > 999) {
                    count = 0;
                    List<ReportFormatDto> dtos=reportsDao.getControlNumber(customerCondition,payRunNo,checkNo);
                    if(dtos!=null & dtos.size()>0)
                        list.add(dtos);
                    customerCondition="";
                }
                if (count != 0)
                    customerCondition = customerCondition + ",";
                customerCondition = customerCondition + customerIdArr[i].trim();
                count++;
            }
        }
        List<ReportFormatDto> dtos=reportsDao.getControlNumber(customerCondition,payRunNo,checkNo);
        if(dtos!=null & dtos.size()>0)
            list.add(dtos);
        if(list.size()>0){
            for( List<ReportFormatDto> dtoList:list){
                if(dtoList!=null && dtoList.size()>0){
                    for(ReportFormatDto dto:dtoList)
                        resultList.add(dto);
                }
            }
        }
        return  resultList;
    }

    public List<ReportFolderDto> getReportFolder(Long userId){ return  reportsDao.getReportFolder(userId); }

    public JSONObject getReportFTPServer(String customerIds,String shipperGroupIds ,String shipperIds,Long userId) throws  Exception{
        List<ReportFTPServerDto> ftpServerDtos=reportsDao.getReportFTPServer( customerIds, shipperGroupIds , shipperIds,userId);
        JSONObject jsonObjectReturn=new JSONObject();
        JSONArray jsonArray = new JSONArray();
        if(ftpServerDtos!=null && ftpServerDtos.size()>0){
            for (ReportFTPServerDto ftpServerDto:ftpServerDtos) {
                if (ftpServerDto != null) {
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("FTPSERVERNAME", ftpServerDto.getFtpAccountName());
                    jsonObject.put("FTPSERVERID", ftpServerDto.getFtpAccountId());
                    jsonArray.put(jsonObject);
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

    public List<ReportFolderDto> getSubFolders(Long rptFolderId, Long userId) {

        return reportsDao.getSubFolders(rptFolderId,userId);
    }

    public JSONArray getReportUserCustomers(Long userId) throws  Exception{
        JSONArray customerJsonArr=new JSONArray();
        List<SearchUserByCustomerDto> customerDtos=reportsDao.getReportUserCustomers(userId);
        if(customerDtos != null && customerDtos.size()>0){
            for(SearchUserByCustomerDto custoemrDto:customerDtos) {
                JSONObject customerJson = new JSONObject();
                customerJson.put("customerId", custoemrDto.getCustomerId());
                customerJson.put("customerName", custoemrDto.getCustomerName());
                customerJsonArr.put(customerJson);
            }
        }
        return customerJsonArr;
    }
    public JSONArray getReportSearchUsers(Long userId,Long customerId,String fullName,String email,Boolean userOnly)throws Exception{
        JSONArray userJsonArr=new JSONArray();
        List<SearchUserByCustomerDto> usersDtos=reportsDao.getReportSearchUsers(userId,customerId,fullName,email,userOnly);
        if(usersDtos != null && usersDtos.size()>0){
            for(SearchUserByCustomerDto userDto:usersDtos) {
                JSONObject userJson = new JSONObject();
                userJson.put("userID", userDto.getUserId());
                userJson.put("userName", userDto.getUserName());
                userJson.put("fullName", userDto.getFullName());
                userJson.put("email", userDto.getEmail());
                userJsonArr.put(userJson);
            }
        }
        return userJsonArr;
    }
    public void sendReportConfirmationEmail(ReportGeneratedDetailsDto generatedReportDetailsDto, String recipients, String key, boolean isFileToBeEmailed, String fromEmail,
                                            long accessFrom, boolean forceAsAdHocReport, boolean isLandOnReportsPage) throws Exception {

        String criteria = (generatedReportDetailsDto.getCriteria() == null) ? " " : generatedReportDetailsDto.getCriteria().replaceAll(";", "<BR>");

        SavedSchedReportDto savedSchedReportDto = reportsDao.getReportDetails(generatedReportDetailsDto.getSavedSchedRptId());

        // Initialize the string objects based on the pattern.
        String[] object = { (savedSchedReportDto.getReportFileName() == null) ? " " : savedSchedReportDto.getReportFileName(),
                (generatedReportDetailsDto.getExpiresDate() == null) ? " " : ReportsUtil.convertDateBasedOnFormat(generatedReportDetailsDto.getExpiresDate(), " MM/dd/yyyy 'at' HH:mm a z"),
                (forceAsAdHocReport ? "Adhoc" : (savedSchedReportDto == null ? "Scheduled" : (savedSchedReportDto.getScheduled()==null ? "Adhoc" : "Scheduled"))), criteria,
                Long.toString(generatedReportDetailsDto.getFileSize() == null ? 0 :generatedReportDetailsDto.getFileSize()),
                (generatedReportDetailsDto.getCompletionDate() == null) ? " " : ReportsUtil.convertDateBasedOnFormat(generatedReportDetailsDto.getCompletionDate(), " MM/dd/yyyy HH:mm:ss z"),
                (generatedReportDetailsDto.getExpiresDate() == null) ? " " : ReportsUtil.convertDateBasedOnFormat(generatedReportDetailsDto.getExpiresDate(), " MM/dd/yyyy HH:mm:ss z"),
                Long.toString(generatedReportDetailsDto.getCost() == null ? 0 : generatedReportDetailsDto.getCost()), URLEncoder.encode((key == null) ? " " : StringEncrypter.getInstance().encrypt(key), "UTF-8"), supportEmailId, website,
                (isLandOnReportsPage ? "true" : "false") };

        String[] object2 = { (savedSchedReportDto.getReportFileName() == null) ? " " : savedSchedReportDto.getReportFileName(), supportEmailId };

        String body = ReportsUtil.constructMessage(emailHtml, object);

        String subject = ReportsUtil.constructMessage(emailSubject, object2);

       if (isFileToBeEmailed) {

           ReportTypeDto reportTypeDto =   reportsDao.getReportTypeDetails(Long.parseLong(String.valueOf(savedSchedReportDto.getReportTypeId())));

           mailService.sendMail(fromEmail,recipients,subject,body,false,true,generatedReportDetailsDto.getReportFileName(),getFileServerAbsolutePath(generatedReportDetailsDto.getPhysicalFileName()));

        } else {
           mailService.sendEmail(recipients,subject,body,false,true);
        }

    }
    public ReportFolderDto getFolderHierarchy(Long userId){
        List<ReportFolderDto> folderHeirarchy = reportsDao.getFolderHierarchy(userId);
        List<ReportFolderDto> rptFolders = reportsDao.getReportFolder(userId);
        TreeSet<ReportFolderDto> hierarchy = new TreeSet<ReportFolderDto>();
        ReportFolderDto folderHierarchyDto=null;
        if(rptFolders!=null && rptFolders.size()>0) {
            for (ReportFolderDto dto : rptFolders) {
                if (dto.getRptFolderId().equals(dto.getParentId()))
                    hierarchy.add(dto);
            }
        }
        //54;;3;;22==name10;;12==new folder2;;54==1234546789
        //55;;3;;22==name10;;12==new folder2;;55==new123
        if(folderHeirarchy!=null && folderHeirarchy.size()>0) {
            folderHierarchyDto=new ReportFolderDto(-1L,"FolderName",-1L);
            for (ReportFolderDto hierarchyDto : folderHeirarchy) {
                String folderH = hierarchyDto.getFolderHierarchy();
                String[] folderDetA = folderH.split(";;");
                String[] folderParentD = folderDetA[2].split("==");
                Long parentId=Long.parseLong(folderParentD[0]);
                ReportFolderDto folderDto = getByfolderIdFromSet(hierarchy,parentId);
                for (int i = 0; i < folderDetA.length - 2; i++) {
                    String[] folderDetD = folderDetA[i + 2].split("==");
                    long folderHieId = Long.parseLong(folderDetD[0]);
                    String folderName = folderDetD[1];
                    long parentID = Long.parseLong(folderDetD[2]);
                    ReportFolderDto parentFolderDto = findFolderGroup(folderDto, parentID);
                    if(i==1){
                        if(parentFolderDto==null){
                            parentFolderDto = getByfolderIdFromSet(hierarchy,parentId);
                            ReportFolderDto chiledFolderDto = findFolderGroup(folderDto, folderHieId);
                            if(chiledFolderDto==null) {
                                parentFolderDto.getCollection().add(new ReportFolderDto(folderHieId, folderName, parentID));
                            }
                        }
                    }
                    if (i >0) {
                        ReportFolderDto chiledFolderDto = findFolderGroup(folderDto, folderHieId);
                        if(chiledFolderDto==null) {
                            parentFolderDto.getCollection().add(new ReportFolderDto(folderHieId, folderName, parentID));
                        }
                     }
                }
            }
            folderHierarchyDto.setCollection(hierarchy);
        }
        return folderHierarchyDto;
    }
    public  ReportFolderDto getByfolderIdFromSet(Set<ReportFolderDto> set, long folderId) {
        for (ReportFolderDto folderDto : set) {
            if (folderDto.getRptFolderId() == folderId)
                return folderDto;
        }
        return null;
    }
    public  ReportFolderDto findFolderGroup(ReportFolderDto folderGroups, Long folderId) {
        if(folderGroups!=null) {
            for (ReportFolderDto folderDto : folderGroups.getCollection()) {
                if (folderDto.getRptFolderId().equals(folderId))
                    return folderDto;
                ReportFolderDto child = findFolderGroup(folderDto, folderId);
                if (child != null)
                    return child;
            }
        }
        return null;
    }
    public ReportFolderDto updateReportFolder(ReportFolderDto reportFolderDto, UserProfileDto userProfileDto){
        return reportsDao.updateReportFolder(reportFolderDto,userProfileDto);
    }

    public List<ReportCodeValueDto> getCodeValues(Long codeGroupId, String orderBy){
        return reportsDao.getCodeValues(codeGroupId, orderBy);
    }

    public List<String> getReportWeightList(){
        return Arrays.asList("LBS", "KGS", "LITRES", "GALLONS", "TONS");
    }

    public Map<String, String> getReportCustomColumnNames(String customerId, Long reportId){
        List<ReportCustomColumnDto> customColumns = reportsDao.getReportCustomColumnNames(customerId, reportId);
        Map<String, String> customColsMap = null;
        if(customColumns != null && !customColumns.isEmpty()){
            customColsMap = new HashMap<>();
            for(ReportCustomColumnDto col : customColumns){
                if(col != null){
                    customColsMap.put(col.getReportFieldName().toUpperCase(), col.getCustomFieldName());
                }
            }
        }
        return customColsMap;
    }
}
