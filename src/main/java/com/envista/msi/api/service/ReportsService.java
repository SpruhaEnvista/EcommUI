package com.envista.msi.api.service;

import com.envista.msi.api.dao.reports.ReportsDao;
import com.envista.msi.api.web.rest.dto.UserProfileDto;
import com.envista.msi.api.web.rest.dto.dashboard.DashboardAppliedFilterDto;
import com.envista.msi.api.web.rest.dto.reports.*;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.Date;
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
    public ReportResultsUsersListDto pushToUser(List<ReportResultsUsersListDto> reportResultsUsersListDto){
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
    public SavedSchedReportsDto changeOwnerBasedonSSRptId(String currentUserName,Long currentUserId,String newUserName,Long newUserId,Long ssRptId ){
        return reportsDao.changeOwnerBasedonSSRptId(currentUserName,currentUserId,newUserName,newUserId,ssRptId);
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




        }

        return new SavedSchedReportDto();
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
}
