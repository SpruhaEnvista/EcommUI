package com.envista.msi.rating;

import com.envista.msi.api.domain.util.ParcelRatingUtil;
import com.envista.msi.api.web.rest.dto.rtr.ParcelAuditDetailsDto;
import com.envista.msi.api.web.rest.util.DateUtil;
import com.envista.msi.api.web.rest.util.audit.parcel.ParcelAuditConstant;
import com.envista.msi.rating.bean.ParcelRatingInputCriteriaBean;
import com.envista.msi.rating.bean.RatingQueueBean;
import com.envista.msi.rating.bean.ServiceFlagAccessorialBean;
import com.envista.msi.rating.dao.DirectJDBCDAO;
import com.envista.msi.rating.entity.ParcelRatingInputCriteriaDto;
import com.envista.msi.rating.service.ParcelNonUpsRatingService;
import com.envista.msi.rating.service.ParcelRatingService;
import com.envista.msi.rating.service.ParcelUpsRatingService;
import org.apache.commons.lang.BooleanUtils;
import org.apache.commons.lang.StringUtils;
import org.json.JSONException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by Sujit kumar on 01/05/2018.
 */
public class ParcelRatingQueueJob {

    private static final Logger log = LoggerFactory.getLogger(ParcelRatingQueueJob.class);

    public static ParcelRatingQueueJob getInstance(){return new ParcelRatingQueueJob();}

    ParcelNonUpsRatingService parcelRatingService = ParcelNonUpsRatingService.getInstance();

    public static void main(String[] args) {
        String customerId = null;
        String fromShipDate = null;
        String toShipDate = null;
        String rateTo = null;
        String trackingNumbers = null;
        String invoiceIds = null;
        boolean isHwt = false;
        String rateSet = null;
        // ParcelRatingUtil.setLoggerConfiguration("ratinglog");
        if(args != null && args.length > 0){
            for (String s : args) {
                String[] array = s.split("=");

                if("customerId".equalsIgnoreCase(array[0].trim())) {
                    customerId = array[1].trim();
                } else if("fromShipDate".equalsIgnoreCase(array[0].trim())) {
                    if(!array[1].trim().equalsIgnoreCase("\\")){
                        fromShipDate = array[1].trim();
                    }
                } else if("toShipDate".equalsIgnoreCase(array[0].trim())) {
                    if(!array[1].trim().equalsIgnoreCase("\\")){
                        toShipDate = array[1].trim();
                    }
                } else if("rateTo".equalsIgnoreCase(array[0].trim())) {
                    rateTo = array[1].trim();
                } else if("trackingNumbers".equalsIgnoreCase(array[0].trim())) {
                    if(!array[1].trim().equalsIgnoreCase("\\")){
                        trackingNumbers = array[1].trim();
                    }
                } else if("invoiceIds".equalsIgnoreCase(array[0].trim())) {
                    if(!array[1].trim().equalsIgnoreCase("\\")){
                        invoiceIds = array[1].trim();
                    }
                } else if ("isHwt".equalsIgnoreCase(array[0].trim())) {
                    Boolean b = BooleanUtils.toBooleanObject((String) array[1].trim());
                    if (b != null)
                        isHwt = b;
                } else if ("rateSet".equalsIgnoreCase(array[0].trim())) {
                    rateSet = array[1].trim();

                }
            }

            try{
                ParcelRatingInputCriteriaBean ratingInputCriteriaBean = new ParcelRatingInputCriteriaBean();
                ratingInputCriteriaBean.setTaskId(null);
                ratingInputCriteriaBean.setRateSetName(rateSet);
                ratingInputCriteriaBean.setCustomerId(customerId);
                ratingInputCriteriaBean.setFromShipDate(fromShipDate);
                ratingInputCriteriaBean.setToShipDate(toShipDate);
                ratingInputCriteriaBean.setTrackingNumbers(trackingNumbers);
                ratingInputCriteriaBean.setInvoiceIds(invoiceIds);
                ratingInputCriteriaBean.setRateTo(rateTo);
                ParcelRatingQueueJob.getInstance().processShipments(ratingInputCriteriaBean, isHwt);
            } catch (Exception e) {
                e.printStackTrace();
                log.error("ERROR - " + e.getMessage());
            }
        } else {
            ParcelRatingService ratingService = new ParcelRatingService();
            ParcelRatingInputCriteriaDto ratingInputCriteria = ratingService.getRatingInputCriteria(ParcelAuditConstant.ParcelRatingInputProcessStatus.NEW.value);

            if(ratingInputCriteria != null) {
                customerId = ratingInputCriteria.getCustomerId() != null ? ratingInputCriteria.getCustomerId().toString() : null;
                fromShipDate = ratingInputCriteria.getFromShipDate() != null ? DateUtil.format(ratingInputCriteria.getFromShipDate(), "dd-MMM-yyyy") : null;
                toShipDate = ratingInputCriteria.getToShipDate() != null ? DateUtil.format(ratingInputCriteria.getToShipDate(), "dd-MMM-yyyy") : null;
                if (ratingInputCriteria.getCarrierId() != null) {
                    if (ratingInputCriteria.getCarrierId() == 21) {
                        rateTo = "ups";
                    } else if (ratingInputCriteria.getCarrierId() == 22) {
                        rateTo = "fedex";
                    }
                }
                try {
                    ratingService.updateRatingInputCriteriaStatus(ratingInputCriteria.getId(), ParcelAuditConstant.ParcelRatingInputProcessStatus.RUNNING.value);

                    ParcelRatingInputCriteriaBean ratingInputCriteriaBean = new ParcelRatingInputCriteriaBean();
                    ratingInputCriteriaBean.setTaskId(ratingInputCriteria.getTaskId());
                    ratingInputCriteriaBean.setRateSetName(ratingInputCriteria.getRateSetName());
                    ratingInputCriteriaBean.setCustomerId(customerId);
                    ratingInputCriteriaBean.setFromShipDate(fromShipDate);
                    ratingInputCriteriaBean.setToShipDate(toShipDate);
                    ratingInputCriteriaBean.setTrackingNumbers(trackingNumbers);
                    ratingInputCriteriaBean.setInvoiceIds(invoiceIds);
                    ratingInputCriteriaBean.setRateTo(rateTo);
                    ratingInputCriteriaBean.setThresholdValue(ratingInputCriteria.getThresholdValue());
                    ratingInputCriteriaBean.setThresholdType(ratingInputCriteria.getThresholdType());
                    ratingInputCriteriaBean.setServiceLevel(ratingInputCriteria.getServiceLevel());
                    ParcelRatingQueueJob.getInstance().processShipments(ratingInputCriteriaBean, false);
                    ParcelRatingQueueJob.getInstance().processShipments(ratingInputCriteriaBean, true);

                    ratingService.updateRatingInputCriteriaStatus(ratingInputCriteria.getId(), ParcelAuditConstant.ParcelRatingInputProcessStatus.COMPLETED.value);
                } catch (Exception e) {
                    log.error("ERROR - " + e.getMessage());
                    e.printStackTrace();
                    ratingService.updateRatingInputCriteriaStatus(ratingInputCriteria.getId(), ParcelAuditConstant.ParcelRatingInputProcessStatus.EXCEPTION.value);
                }
            }
        }
    }

    private void processShipments(ParcelRatingInputCriteriaBean ratingInputCriteriaBean, boolean isHwt) throws SQLException, JSONException {
        List<ParcelAuditDetailsDto> allShipmentDetails = null;
        if("ups".equalsIgnoreCase(ratingInputCriteriaBean.getRateTo())){
            List<Long> invoiceList = DirectJDBCDAO.getInstance().loadInvoiceIds(ratingInputCriteriaBean.getFromShipDate(), ratingInputCriteriaBean.getToShipDate(), ratingInputCriteriaBean.getCustomerId(), ratingInputCriteriaBean.getInvoiceIds(), 0, "UPS", ratingInputCriteriaBean.getServiceLevel());
            if(invoiceList != null && !invoiceList.isEmpty()) {
                for(Long invId : invoiceList){
                    if(invId != null) {
                        log.info("populating into rating queue table started for invoice id ->" + invId);
                        // System.out.println("populating into rating queue table started for invoice id ->" + invId);
                        allShipmentDetails = ParcelUpsRatingService.getInstance().getUpsParcelShipmentDetails(ratingInputCriteriaBean.getCustomerId(), ratingInputCriteriaBean.getFromShipDate(), ratingInputCriteriaBean.getToShipDate(), ratingInputCriteriaBean.getTrackingNumbers(), invId.toString(), null);

                        if(allShipmentDetails != null && !allShipmentDetails.isEmpty()){
                            Map<String, List<ParcelAuditDetailsDto>> trackingNumberWiseShipments = ParcelRatingUtil.prepareTrackingNumberWiseAuditDetails(allShipmentDetails);
                            List<ServiceFlagAccessorialBean> accessorialBeans = parcelRatingService.getServiceFlagAcessorials(21l, ParcelAuditConstant.MSI_AR_CHARGE_CODE_MAPPING_MODELE_NAME);
                            processUpsShipments(trackingNumberWiseShipments, ratingInputCriteriaBean.getCustomerId(), ratingInputCriteriaBean, isHwt, accessorialBeans);
                        }
                    }
                }
            }

        } else if("fedex".equalsIgnoreCase(ratingInputCriteriaBean.getRateTo())) {
            List<Long> invoiceList = DirectJDBCDAO.getInstance().loadInvoiceIds(ratingInputCriteriaBean.getFromShipDate(), ratingInputCriteriaBean.getToShipDate(), ratingInputCriteriaBean.getCustomerId(), ratingInputCriteriaBean.getInvoiceIds(), 0, "FEDEX", ratingInputCriteriaBean.getServiceLevel());
            if(invoiceList != null && !invoiceList.isEmpty()) {
                for (Long invId : invoiceList) {
                    if (invId != null) {
                        log.info("populating into rating queue table started for invoice id ->" + invId);
                        //System.out.println("populating into rating queue table started for invoice id ->" + invId);
                        allShipmentDetails = parcelRatingService.getFedExParcelShipmentDetails(ratingInputCriteriaBean.getCustomerId(), ratingInputCriteriaBean.getFromShipDate(), ratingInputCriteriaBean.getToShipDate(), ratingInputCriteriaBean.getTrackingNumbers(), invId.toString(), false, null);

                        if(allShipmentDetails != null && !allShipmentDetails.isEmpty()){
                            Map<String, List<ParcelAuditDetailsDto>> trackingNumberWiseShipments = ParcelRatingUtil.prepareTrackingNumberWiseAuditDetails(allShipmentDetails);
                            List<ServiceFlagAccessorialBean> accessorialBeans = parcelRatingService.getServiceFlagAcessorials(22l, ParcelAuditConstant.MSI_AR_CHARGE_CODE_MAPPING_MODELE_NAME);
                            processFedExShipments(trackingNumberWiseShipments, ratingInputCriteriaBean, isHwt, accessorialBeans, ratingInputCriteriaBean.getCustomerId());
                        }
                    }
                }
            }
        }
    }

    private void processUpsShipments(Map<String, List<ParcelAuditDetailsDto>> trackingNumberWiseShipments, String customerIds, ParcelRatingInputCriteriaBean ratingInputCriteriaBean, boolean isHwt, List<ServiceFlagAccessorialBean> accessorialBeans) {
        if(trackingNumberWiseShipments != null) {
            // Map<String, List<ParcelAuditDetailsDto>> hwtDetailsMap = ParcelRatingUtil.prepareHwtNumberWiseAuditDetails(trackingNumberWiseShipments);
            Iterator<Map.Entry<String, List<ParcelAuditDetailsDto>>> entryIterator = trackingNumberWiseShipments.entrySet().iterator();
            List<ParcelAuditDetailsDto> shipmentRecords = null;

                while (entryIterator.hasNext()) {

                    try {

                        shipmentRecords = null;

                        Map.Entry<String, List<ParcelAuditDetailsDto>> parcelAuditEntry = entryIterator.next();
                        if (parcelAuditEntry != null) {
                            String trackingNumber = parcelAuditEntry.getKey();

                            boolean hwtShipment = false;
                            if ((parcelAuditEntry.getValue().get(0).getMultiWeightNumber() != null && !parcelAuditEntry.getValue().get(0).getMultiWeightNumber().isEmpty())) {

                                shipmentRecords = ParcelUpsRatingService.getInstance().getUpsParcelShipmentDetails(customerIds, null, true, parcelAuditEntry.getValue().get(0).getMultiWeightNumber());
                                hwtShipment = ParcelRatingUtil.checkHwtShipment(shipmentRecords);
                            }

                            if (!hwtShipment) {
                                ratingInputCriteriaBean.setHwt(false);
                                if (trackingNumber != null && !trackingNumber.isEmpty()) {
                                    shipmentRecords = ParcelUpsRatingService.getInstance().getUpsParcelShipmentDetails(customerIds, trackingNumber, true, null);
                                }

                                Map<Long, List<ParcelAuditDetailsDto>> shipments = ParcelRatingUtil.organiseShipmentsByParentId(shipmentRecords);

                                if (shipments != null) {

                                    ParcelRatingUtil.setAddressCorrectionAsAccessorial(shipmentRecords);

                                    for (Map.Entry<Long, List<ParcelAuditDetailsDto>> entry : shipments.entrySet()) {

                                        List<ParcelAuditDetailsDto> shipmentChargeList = ParcelRatingUtil.prepareChargeList(entry.getKey(), shipments);

                                        addUpsShipmentEntryIntoQueue(shipmentChargeList, ratingInputCriteriaBean, accessorialBeans, shipmentRecords, null);
                                    }
                                }
                            } else {

                                Map<Date, List<ParcelAuditDetailsDto>> shipments = ParcelRatingUtil.organiseShipmentsByBillDate(shipmentRecords);

                                List<ParcelAuditDetailsDto> leadShipmentDetails = ParcelRatingUtil.getLeadShipmentDetails(shipmentRecords);

                                Map<String, List<ParcelAuditDetailsDto>> LeadTrackingWiseShipments = ParcelRatingUtil.prepareTrackingNumberWiseAuditDetails(shipmentRecords);


                                for (Map.Entry<Date, List<ParcelAuditDetailsDto>> entry : shipments.entrySet()) {

                                    boolean shipmentExist = parcelRatingService.hwtShipmentExist(leadShipmentDetails.get(0).getTrackingNumber(), entry.getKey());

                                    if (!shipmentExist) {

                                        Map<String, Long> hwtSequenceInfo = new HashMap<>();

                                        ParcelRatingUtil.getMinParentId(entry.getValue(), hwtSequenceInfo);

                                        Map<String, List<ParcelAuditDetailsDto>> billDateTrackingWiseShipments = ParcelRatingUtil.prepareTrackingNumberWiseAuditDetails(entry.getValue());

                                        ParcelRatingUtil.addMissTrackingInfo(billDateTrackingWiseShipments, LeadTrackingWiseShipments, entry.getKey(), null);

                                        List<ParcelAuditDetailsDto> shipmentChargeList = ParcelRatingUtil.prepareHwtAccList(billDateTrackingWiseShipments, hwtSequenceInfo);
                                        ratingInputCriteriaBean.setHwt(true);
                                        addUpsShipmentEntryIntoQueue(shipmentChargeList, ratingInputCriteriaBean, accessorialBeans, shipmentRecords, hwtSequenceInfo);


                                    }


                                }
                            }

                            entryIterator.remove();
                        }
                    } catch (Exception e) {
                        log.error("ERROR - " + e.getMessage() + "--Parent Id->" + shipmentRecords.get(0).getParentId());
                        e.printStackTrace();
                    }
                }

            //addMwtOrHwtShipmentEntryIntoQueue(hwtDetailsMap, "ups", ratingInputCriteriaBean, accessorialBeans);

        }
    }

    public void processFedExShipments(Map<String, List<ParcelAuditDetailsDto>> trackingNumberWiseShipments, ParcelRatingInputCriteriaBean ratingInputCriteriaBean, boolean isHwt, List<ServiceFlagAccessorialBean> accessorialBeans, String customerId) throws SQLException, JSONException {
        if(trackingNumberWiseShipments != null && !trackingNumberWiseShipments.isEmpty()) {
            //Map<String, List<ParcelAuditDetailsDto>> mwtDetailsMap = ParcelRatingUtil.prepareMultiWeightNumberWiseAuditDetails(trackingNumberWiseShipments);

            Iterator<Map.Entry<String, List<ParcelAuditDetailsDto>>> entryIterator = trackingNumberWiseShipments.entrySet().iterator();
            while(entryIterator.hasNext()){
                List<ParcelAuditDetailsDto> trackingNumberDetails = null;
                try {
                    Map.Entry<String,List<ParcelAuditDetailsDto>> parcelAuditEntry = entryIterator.next();
                    if(parcelAuditEntry != null) {
                        String trackingNumber = parcelAuditEntry.getKey();


                        boolean hwtShipment = false;
                        if ((parcelAuditEntry.getValue().get(0).getMultiWeightNumber() != null && !parcelAuditEntry.getValue().get(0).getMultiWeightNumber().isEmpty())) {

                            trackingNumberDetails = parcelRatingService.getFedExParcelShipmentDetails(ratingInputCriteriaBean.getCustomerId(), null, null, null, null, true, parcelAuditEntry.getValue().get(0).getMultiWeightNumber());
                            hwtShipment = ParcelRatingUtil.checkHwtShipment(trackingNumberDetails);
                        }
                        if (!hwtShipment) {
                            ratingInputCriteriaBean.setHwt(false);
                            if (trackingNumber != null && !trackingNumber.isEmpty()) {
                                ParcelAuditDetailsDto dto = parcelAuditEntry.getValue().get(0);
                                trackingNumberDetails = parcelRatingService.getFedExParcelShipmentDetails(ratingInputCriteriaBean.getCustomerId(), null, null, trackingNumber, null, true, null);
                            }

                            if (trackingNumberDetails != null && trackingNumberDetails.size() > 0) {

                                Map<Long, List<ParcelAuditDetailsDto>> shipments = ParcelRatingUtil.organiseShipmentsByParentId(trackingNumberDetails);
                                Iterator<Map.Entry<Long, List<ParcelAuditDetailsDto>>> shipmentIterator = shipments.entrySet().iterator();

                                while (shipmentIterator.hasNext()) {
                                    Map.Entry<Long, List<ParcelAuditDetailsDto>> shpEntry = shipmentIterator.next();
                                    if (shpEntry != null) {
                                        boolean frtFound = false;
                                        List<ParcelAuditDetailsDto> shipmentDetails = shpEntry.getValue();
                                        for (ParcelAuditDetailsDto auditDetails : shipmentDetails) {
                                            if (auditDetails != null && "FRT".equalsIgnoreCase(auditDetails.getChargeClassificationCode())) {
                                                frtFound = true;

                                                if (((auditDetails.getPackageWeight() != null && new BigDecimal(auditDetails.getPackageWeight()).compareTo(BigDecimal.ZERO) == 0)
                                                        && (auditDetails.getActualWeight() != null && auditDetails.getActualWeight().compareTo(BigDecimal.ZERO) == 0))) {

                                                    List<ParcelAuditDetailsDto> prevParentIdInfo = ParcelRatingUtil.getImmediateParentIdInfo(auditDetails.getParentId(), shipments);
                                                    if ( prevParentIdInfo != null && prevParentIdInfo.size() > 0 ) {
                                                    ParcelAuditDetailsDto frtDto = ParcelRatingUtil.getLatestFrightCharge(prevParentIdInfo);
                                                    if (frtDto != null) {
                                                        if (frtDto.getActualWeight() != null)
                                                            auditDetails.setActualWeight(frtDto.getActualWeight());
                                                        if (frtDto.getPackageWeight() != null)
                                                            auditDetails.setPackageWeight(frtDto.getPackageWeight());
                                                    }

                                                }
                                                }

                                                break;
                                            }
                                        }


                                        if (!frtFound) {
                                            ParcelAuditDetailsDto dto = ParcelRatingUtil.getImmediateFrtInfo(shipmentDetails, trackingNumberDetails);
                                            if (dto != null) {
                                                shipmentDetails.add(dto);
                                                frtFound = true;
                                            }
                                        }

                                        for (ParcelAuditDetailsDto dto : trackingNumberDetails) {

                                            if (shipmentDetails.get(0).getParentId().compareTo(dto.getParentId()) > 0
                                                    && shipmentDetails.get(0).getPickupDate().compareTo(dto.getPickupDate()) == 0
                                                    && ParcelAuditConstant.ChargeClassificationCode.ACS.name().equalsIgnoreCase(dto.getChargeClassificationCode())) {

                                                shipmentDetails.add(dto);


                                            }
                                        }


                                        if (frtFound)
                                            addNonUpsShipmentEntryIntoQueue(shipmentDetails, ratingInputCriteriaBean, accessorialBeans, trackingNumberDetails, null);
                                        else {
                                            log.warn("FRT is not found for tracking #->" + shipmentDetails.get(0).getTrackingNumber() + " Parent Id->" + shipmentDetails.get(0).getParentId());
                                            // System.out.println("FRT is not found for tracking #->" + shipmentDetails.get(0).getTrackingNumber() + " ebill manifest id->" + shipmentDetails.get(0).getParentId());

                                        }

                                    }
                                }
                            }
                        } else {

                            Map<Date, List<ParcelAuditDetailsDto>> shipments = ParcelRatingUtil.organiseShipmentsByBillDate(trackingNumberDetails);

                            List<ParcelAuditDetailsDto> leadShipmentDetails = ParcelRatingUtil.getLeadShipmentDetails(trackingNumberDetails);

                            Map<String, List<ParcelAuditDetailsDto>> trackingWiseShipments = ParcelRatingUtil.prepareTrackingNumberWiseAuditDetails(trackingNumberDetails);


                            for (Map.Entry<Date, List<ParcelAuditDetailsDto>> entry : shipments.entrySet()) {

                                boolean shipmentExist = parcelRatingService.hwtShipmentExist(leadShipmentDetails.get(0).getTrackingNumber(), entry.getKey());

                                if (!shipmentExist) {

                                    Map<String, Long> hwtSequenceInfo = new HashMap<>();

                                    ParcelRatingUtil.getMinParentId(entry.getValue(), hwtSequenceInfo);

                                    Map<String, List<ParcelAuditDetailsDto>> billDateTrackingWiseShipments = ParcelRatingUtil.prepareTrackingNumberWiseAuditDetails(entry.getValue());


                                    ParcelRatingUtil.addMissTrackingInfo(billDateTrackingWiseShipments, trackingWiseShipments, entry.getKey(), entry.getValue().get(0).getPickupDate());

                                    List<ParcelAuditDetailsDto> shipmentChargeList = ParcelRatingUtil.prepareHwtAccList(billDateTrackingWiseShipments, hwtSequenceInfo);
                                    ratingInputCriteriaBean.setHwt(true);

                                    addNonUpsShipmentEntryIntoQueue(shipmentChargeList, ratingInputCriteriaBean, accessorialBeans, trackingNumberDetails, hwtSequenceInfo);

                                }


                            }


                        }
                        entryIterator.remove();
                    }

                } catch (Exception e) {
                    log.error("ERROR - " + e.getMessage() + "--Parent Id->" + trackingNumberDetails.get(0).getParentId());
                    e.printStackTrace();
                }
            }

        }
    }

    private void addNonUpsShipmentEntryIntoQueue(List<ParcelAuditDetailsDto> shipments, ParcelRatingInputCriteriaBean ratingInputCriteriaBean, List<ServiceFlagAccessorialBean> accessorialBeans, List<ParcelAuditDetailsDto> trackingNumDetails, Map<String, Long> hwtSequenceInfo) {
        if (ratingInputCriteriaBean.isHwt() || !parcelRatingService.shipmentExist(shipments.get(0).getParentId())) {
            try{
                RatingQueueBean ratingQueueBean = ParcelRatingUtil.prepareShipmentEntryForNonUpsShipment(shipments, ratingInputCriteriaBean.getRateSetName(), accessorialBeans, trackingNumDetails, hwtSequenceInfo);
                if(ratingQueueBean != null){
                    ratingQueueBean.setTaskId(ratingInputCriteriaBean.getTaskId());
                    ratingQueueBean.setThresholdValue(ratingInputCriteriaBean.getThresholdValue());
                    ratingQueueBean.setThresholdType(ratingInputCriteriaBean.getThresholdType());
                    ratingQueueBean.setServiceLevel(ratingInputCriteriaBean.getServiceLevel());
                    parcelRatingService.saveRatingQueueBean(ratingQueueBean);
                }
            } catch (Exception e){
                log.error("ERROR - " + e.getMessage() + "--Parent Id->" + shipments.get(0).getParentId());
                e.printStackTrace();
            }
        }
    }

    private void addUpsShipmentEntryIntoQueue(List<ParcelAuditDetailsDto> shipmentsWithPrevFrt, ParcelRatingInputCriteriaBean ratingInputCriteriaBean, List<ServiceFlagAccessorialBean> accessorialBeans, List<ParcelAuditDetailsDto> trackingNumDetails, Map<String, Long> hwtSequenceInfo) {
        if (ratingInputCriteriaBean.isHwt() || !parcelRatingService.shipmentExist(shipmentsWithPrevFrt.get(0).getParentId())) {
            try{
                log.info("populating into rating queue table started for parent id ->" + shipmentsWithPrevFrt.get(0).getParentId());
                // System.out.println("populating into rating queue table started for parent id ->" + shipmentsWithPrevFrt.get(0).getParentId());
                RatingQueueBean ratingQueueBean = ParcelRatingUtil.prepareShipmentEntryForUpsShipment(shipmentsWithPrevFrt, ratingInputCriteriaBean.getRateSetName(), accessorialBeans, trackingNumDetails, hwtSequenceInfo);
                if(ratingQueueBean != null){
                    ratingQueueBean.setTaskId(ratingInputCriteriaBean.getTaskId());
                    ratingQueueBean.setThresholdValue(ratingInputCriteriaBean.getThresholdValue());
                    ratingQueueBean.setThresholdType(ratingInputCriteriaBean.getThresholdType());
                    ratingQueueBean.setServiceLevel(ratingInputCriteriaBean.getServiceLevel());
                    parcelRatingService.saveRatingQueueBean(ratingQueueBean);
                }
            }catch (Exception e) {
                if (shipmentsWithPrevFrt != null && shipmentsWithPrevFrt.size() > 0)
                    log.error("ERROR - " + e.getMessage() + "--Parent Id->" + shipmentsWithPrevFrt.get(0).getParentId());
                else
                    log.error("ERROR - " + e.getStackTrace());
                e.printStackTrace();
            }
        }
    }


    private void addMwtOrHwtShipmentEntryIntoQueue(Map<String, List<ParcelAuditDetailsDto>> mwtDetailsMap, String rateTo, ParcelRatingInputCriteriaBean ratingInputCriteriaBean, List<ServiceFlagAccessorialBean> accessorialBeans) throws SQLException, JSONException {

        List<RatingQueueBean> queueBeanList = null;
        for (Map.Entry<String, List<ParcelAuditDetailsDto>> entry : mwtDetailsMap.entrySet()) {

            Map<String, List<ParcelAuditDetailsDto>> trackingNumberWiseShipments = ParcelRatingUtil.prepareTrackingNumberWiseAuditDetails(entry.getValue());

            for (Map.Entry<String, List<ParcelAuditDetailsDto>> listEntry : trackingNumberWiseShipments.entrySet()) {

                RatingQueueBean ratingQueueBean = null;
                if (!parcelRatingService.shipmentExist(listEntry.getValue().get(0).getParentId())) {
                    if (StringUtils.equalsIgnoreCase("fedex", rateTo)) {
                        ratingQueueBean = ParcelRatingUtil.prepareShipmentEntryForNonUpsShipment(listEntry.getValue(), ratingInputCriteriaBean.getRateSetName(), accessorialBeans, null, null);
                    } else if (StringUtils.equalsIgnoreCase("ups", rateTo)) {
                        ratingQueueBean = ParcelRatingUtil.prepareShipmentEntryForUpsShipment(listEntry.getValue(), ratingInputCriteriaBean.getRateSetName(), accessorialBeans, null, null);
                    }
                    if (ratingQueueBean != null) {
                        ratingQueueBean.setTaskId(ratingInputCriteriaBean.getTaskId());

                        if (queueBeanList == null)
                            queueBeanList = new ArrayList<RatingQueueBean>();

                        ratingQueueBean.setTaskId(ratingInputCriteriaBean.getTaskId());
                        ratingQueueBean.setThresholdValue(ratingInputCriteriaBean.getThresholdValue());
                        ratingQueueBean.setThresholdType(ratingInputCriteriaBean.getThresholdType());
                        ratingQueueBean.setServiceLevel(ratingInputCriteriaBean.getServiceLevel());

                        queueBeanList.add(ratingQueueBean);
                    }
                }
            }
            if (queueBeanList != null && queueBeanList.size() > 0) {
                parcelRatingService.saveRatingQueueBean(queueBeanList);
            }
            queueBeanList = null;
        }
    }

}
