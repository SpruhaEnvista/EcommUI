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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by Sujit kumar on 01/05/2018.
 */
public class ParcelRatingQueueJob {

    private static final Logger log = LoggerFactory.getLogger(ParcelRatingQueueJob.class);

    public static ParcelRatingQueueJob getInstance(){return new ParcelRatingQueueJob();}
    ParcelNonUpsRatingService parcelRatingService = new ParcelNonUpsRatingService();

    public static void main(String[] args) {
        String customerId = null;
        String fromShipDate = null;
        String toShipDate = null;
        String rateTo = null;
        String trackingNumbers = null;
        String invoiceIds = null;
        boolean isHwt = false;
        String rateSet = null;
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
                log.error("ERROR - ", e.getMessage());
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
                    e.printStackTrace();
                    ratingService.updateRatingInputCriteriaStatus(ratingInputCriteria.getId(), ParcelAuditConstant.ParcelRatingInputProcessStatus.EXCEPTION.value);
                }
            }
        }
    }

    private void processShipments(ParcelRatingInputCriteriaBean ratingInputCriteriaBean, boolean isHwt) throws SQLException {
        List<ParcelAuditDetailsDto> allShipmentDetails = null;
        if("ups".equalsIgnoreCase(ratingInputCriteriaBean.getRateTo())){
            List<Long> invoiceList = new DirectJDBCDAO().loadInvoiceIds(ratingInputCriteriaBean.getFromShipDate(), ratingInputCriteriaBean.getToShipDate(), ratingInputCriteriaBean.getCustomerId(), ratingInputCriteriaBean.getInvoiceIds(), 0, "UPS",ratingInputCriteriaBean.getServiceLevel());
            if(invoiceList != null && !invoiceList.isEmpty()) {
                for(Long invId : invoiceList){
                    if(invId != null) {
                        log.info("populating into rating queue table started for invoice id ->" + invId);
                        System.out.println("populating into rating queue table started for invoice id ->" + invId);
                        allShipmentDetails = new ParcelUpsRatingService().getUpsParcelShipmentDetails(ratingInputCriteriaBean.getCustomerId(), ratingInputCriteriaBean.getFromShipDate(), ratingInputCriteriaBean.getToShipDate(), ratingInputCriteriaBean.getTrackingNumbers(), invId.toString(), isHwt);

                        if(allShipmentDetails != null && !allShipmentDetails.isEmpty()){
                            Map<String, List<ParcelAuditDetailsDto>> trackingNumberWiseShipments = ParcelRatingUtil.prepareTrackingNumberWiseAuditDetails(allShipmentDetails);
                            List<ServiceFlagAccessorialBean> accessorialBeans = parcelRatingService.getServiceFlagAcessorials(21l, ParcelAuditConstant.MSI_AR_CHARGE_CODE_MAPPING_MODELE_NAME);
                            processUpsShipments(trackingNumberWiseShipments, ratingInputCriteriaBean.getCustomerId(), ratingInputCriteriaBean, isHwt, accessorialBeans);
                        }
                    }
                }
            }

        } else if("fedex".equalsIgnoreCase(ratingInputCriteriaBean.getRateTo())) {
            List<Long> invoiceList = new DirectJDBCDAO().loadInvoiceIds(ratingInputCriteriaBean.getFromShipDate(), ratingInputCriteriaBean.getToShipDate(), ratingInputCriteriaBean.getCustomerId(), ratingInputCriteriaBean.getInvoiceIds(), 0, "FEDEX",ratingInputCriteriaBean.getServiceLevel());
            if(invoiceList != null && !invoiceList.isEmpty()) {
                for (Long invId : invoiceList) {
                    if (invId != null) {
                        log.info("populating into rating queue table started for invoice id ->" + invId);
                        System.out.println("populating into rating queue table started for invoice id ->" + invId);
                        allShipmentDetails =  parcelRatingService.getFedExParcelShipmentDetails(ratingInputCriteriaBean.getCustomerId(), ratingInputCriteriaBean.getFromShipDate(), ratingInputCriteriaBean.getToShipDate(), ratingInputCriteriaBean.getTrackingNumbers(), invId.toString(), isHwt);

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

    private void processUpsShipments(Map<String, List<ParcelAuditDetailsDto>> trackingNumberWiseShipments, String customerIds, ParcelRatingInputCriteriaBean ratingInputCriteriaBean, boolean isHwt, List<ServiceFlagAccessorialBean> accessorialBeans) throws SQLException {
        if(trackingNumberWiseShipments != null) {
            Map<String, List<ParcelAuditDetailsDto>> hwtDetailsMap = ParcelRatingUtil.prepareHwtNumberWiseAuditDetails(trackingNumberWiseShipments);
            Iterator<Map.Entry<String, List<ParcelAuditDetailsDto>>> entryIterator = trackingNumberWiseShipments.entrySet().iterator();
            while(entryIterator.hasNext()){
                Map.Entry<String,List<ParcelAuditDetailsDto>> parcelAuditEntry = entryIterator.next();
                if (parcelAuditEntry != null) {
                    String trackingNumber = parcelAuditEntry.getKey();
                    List<ParcelAuditDetailsDto> shipmentRecords = null;
                    if (trackingNumber != null && !trackingNumber.isEmpty()) {
                        shipmentRecords = new ParcelUpsRatingService().getUpsParcelShipmentDetails(customerIds, trackingNumber, true, false);
                    }

                    Map<Long, List<ParcelAuditDetailsDto>> shipments = ParcelRatingUtil.organiseShipmentsByParentId(shipmentRecords);

                    if (shipments != null) {

                        Iterator<Map.Entry<Long, List<ParcelAuditDetailsDto>>> shipmentIterator = shipments.entrySet().iterator();

                        List<ParcelAuditDetailsDto> previousShipment = null;
                        while (shipmentIterator.hasNext()) {
                            Map.Entry<Long, List<ParcelAuditDetailsDto>> shpEntry = shipmentIterator.next();
                            if (shpEntry != null) {
                                List<ParcelAuditDetailsDto> shipmentChargeList = shpEntry.getValue();
                            /*if(shipmentChargeList != null) {
                                if(shipmentChargeList.size() == 1 && "FRT".equalsIgnoreCase(shipmentChargeList.get(0).getChargeClassificationCode())
                                        && shipmentChargeList.get(0).getNetAmount() != null && Double.parseDouble(shipmentChargeList.get(0).getNetAmount()) == 0.0) {
                                    continue;
                                }
                            }*/

                                if (shipmentChargeList != null) {
                                    if (!ParcelRatingUtil.isShipmentRated(shipmentChargeList)) {
                                        if (ParcelRatingUtil.containsCharge(ParcelAuditConstant.COMMERCIAL_ADJUSTMENT_CHARGE_TYPE, shipmentChargeList)) {
                                            List<ParcelAuditDetailsDto> commercialShipment = new ArrayList<>();
                                            commercialShipment.addAll(shipmentChargeList);

                                            if (previousShipment != null) {
                                                for (ParcelAuditDetailsDto commShipment : previousShipment) {
                                                    if (commShipment != null && !"RES".equalsIgnoreCase(commShipment.getChargeDescriptionCode())
                                                            && !"RSC".equalsIgnoreCase(commShipment.getChargeDescriptionCode()) && !"FRT".equalsIgnoreCase(commShipment.getChargeClassificationCode())) {
                                                        commercialShipment.add(commShipment);
                                                    }
                                                }
                                            }
                                            addUpsShipmentEntryIntoQueue(commercialShipment, ratingInputCriteriaBean, accessorialBeans);
                                        } else if (ParcelRatingUtil.containsCharge(ParcelAuditConstant.RESIDENTIAL_ADJUSTMENT_CHARGE_TYPE, shipmentChargeList)) {
                                            //keeping it in separate if condition in order to handle few more scenarios in future.
                                            List<ParcelAuditDetailsDto> residentialShipment = new ArrayList<>();
                                            residentialShipment.addAll(shipmentChargeList);
                                            if (previousShipment != null) {
                                                for (ParcelAuditDetailsDto commShipment : previousShipment) {
                                                    if (commShipment != null && !"FRT".equalsIgnoreCase(commShipment.getChargeClassificationCode())) {
                                                        residentialShipment.add(commShipment);
                                                    }
                                                }
                                            }
                                            addUpsShipmentEntryIntoQueue(residentialShipment, ratingInputCriteriaBean, accessorialBeans);
                                        } else if (ParcelRatingUtil.containsCharge(ParcelAuditConstant.RESIDENTIAL_COMMERCIAL_ADJUSTMENT_CHARGE_TYPE, shipmentChargeList)) {
                                            if (previousShipment != null) {
                                                List<ParcelAuditDetailsDto> resComShipmentToRate = new ArrayList<>();
                                                for (ParcelAuditDetailsDto shpCharge : shipmentChargeList) {
                                                    if (shpCharge != null && ParcelAuditConstant.ChargeClassificationCode.ACC.name().equalsIgnoreCase(shpCharge.getChargeClassificationCode())
                                                            && !"RES".equalsIgnoreCase(shpCharge.getChargeDescriptionCode())) {
                                                        resComShipmentToRate.add(shpCharge);
                                                    }
                                                }
                                                for (ParcelAuditDetailsDto prevShipmentCharge : previousShipment) {
                                                    if (prevShipmentCharge != null) {
                                                        if (ParcelAuditConstant.ChargeClassificationCode.FRT.name().equalsIgnoreCase(prevShipmentCharge.getChargeClassificationCode())) {
                                                            resComShipmentToRate.add(prevShipmentCharge);
                                                        } else if (ParcelAuditConstant.ChargeClassificationCode.ACC.name().equalsIgnoreCase(prevShipmentCharge.getChargeClassificationCode())
                                                                && !"RES".equalsIgnoreCase(prevShipmentCharge.getChargeDescriptionCode())) {
                                                            resComShipmentToRate.add(prevShipmentCharge);
                                                        }
                                                    }
                                                }
                                                addUpsShipmentEntryIntoQueue(resComShipmentToRate, ratingInputCriteriaBean, accessorialBeans);
                                            }
                                        } else {
                                            if (previousShipment != null) {
                                                List<ParcelAuditDetailsDto> shipmentsToRate = new ArrayList<>(shipmentChargeList);
                                                if (shipmentsToRate != null) {
                                                    boolean hasFrtCharge = false;
                                                    boolean frtChargeManipulated = false;
                                                    ParcelAuditDetailsDto frtCharged = ParcelRatingUtil.findFrtCharge(shipmentsToRate);
                                                    if (frtCharged != null) {
                                                        hasFrtCharge = true;
                                                        if (frtCharged.getPackageWeight() != null && !frtCharged.getPackageWeight().isEmpty() && Float.parseFloat(frtCharged.getPackageWeight()) == 0) {
                                                            ParcelAuditDetailsDto prevShipmentFrtCharge = ParcelRatingUtil.findFrtCharge(previousShipment);
                                                            if (prevShipmentFrtCharge != null && prevShipmentFrtCharge.getPackageWeight() != null
                                                                    && !prevShipmentFrtCharge.getPackageWeight().isEmpty() && Float.parseFloat(prevShipmentFrtCharge.getPackageWeight()) > 0) {
                                                                frtCharged.setPackageWeight(prevShipmentFrtCharge.getPackageWeight());
                                                                frtCharged.setWeightUnit(prevShipmentFrtCharge.getWeightUnit());
                                                                frtCharged.setActualWeight(prevShipmentFrtCharge.getActualWeight());
                                                                frtCharged.setActualWeightUnit(prevShipmentFrtCharge.getActualWeightUnit());
                                                                frtCharged.setDimHeight(prevShipmentFrtCharge.getDimHeight());
                                                                frtCharged.setDimWidth(prevShipmentFrtCharge.getDimWidth());
                                                                frtCharged.setDimLength(prevShipmentFrtCharge.getDimLength());
                                                                frtCharged.setUnitOfDim(prevShipmentFrtCharge.getUnitOfDim());
                                                                frtCharged.setPackageDimension(prevShipmentFrtCharge.getPackageDimension());


                                                                frtChargeManipulated = true;
                                                            }
                                                        }
                                                    } else {
                                                        hasFrtCharge = false;
                                                    }
                                                    boolean hasFSCCharge = ParcelRatingUtil.containsFuelSurcharge(shipmentsToRate);
                                                    for (ParcelAuditDetailsDto prevShpCharge : previousShipment) {
                                                        if (prevShpCharge != null && ParcelAuditConstant.ChargeClassificationCode.ACC.name().equalsIgnoreCase(prevShpCharge.getChargeClassificationCode())) {
                                                            shipmentsToRate.add(prevShpCharge);
                                                        }
                                                        if (!hasFSCCharge && ParcelAuditConstant.ChargeClassificationCode.ACC.name().equalsIgnoreCase(prevShpCharge.getChargeClassificationCode())) {
                                                            shipmentsToRate.add(prevShpCharge);
                                                        }
                                                        if (!hasFrtCharge && !frtChargeManipulated && ParcelAuditConstant.ChargeClassificationCode.FRT.name().equalsIgnoreCase(prevShpCharge.getChargeClassificationCode())) {
                                                            shipmentsToRate.add(prevShpCharge);
                                                        }
                                                    }
                                                    addUpsShipmentEntryIntoQueue(shipmentsToRate, ratingInputCriteriaBean, accessorialBeans);
                                                }
                                            } else {
                                                addUpsShipmentEntryIntoQueue(shipmentChargeList, ratingInputCriteriaBean, accessorialBeans);
                                            }
                                        }
                                    }
                                    previousShipment = new ArrayList<>(shipmentChargeList);
                                }
                            }
                        }
                    }
                    entryIterator.remove();
                }
            }

            addMwtOrHwtShipmentEntryIntoQueue(hwtDetailsMap, "ups", ratingInputCriteriaBean, accessorialBeans);

        }
    }

    public void processFedExShipments(Map<String, List<ParcelAuditDetailsDto>> trackingNumberWiseShipments, ParcelRatingInputCriteriaBean ratingInputCriteriaBean, boolean isHwt, List<ServiceFlagAccessorialBean> accessorialBeans, String customerId) throws SQLException {
        if(trackingNumberWiseShipments != null && !trackingNumberWiseShipments.isEmpty()) {
            Map<String, List<ParcelAuditDetailsDto>> mwtDetailsMap = ParcelRatingUtil.prepareMultiWeightNumberWiseAuditDetails(trackingNumberWiseShipments);

            Iterator<Map.Entry<String, List<ParcelAuditDetailsDto>>> entryIterator = trackingNumberWiseShipments.entrySet().iterator();
            while(entryIterator.hasNext()){
                Map.Entry<String,List<ParcelAuditDetailsDto>> parcelAuditEntry = entryIterator.next();
                if(parcelAuditEntry != null) {
                    String trackingNumber = parcelAuditEntry.getKey();
                    List<ParcelAuditDetailsDto> pickUpDateShipmentDeatils = null;
                    if (trackingNumber != null && !trackingNumber.isEmpty()) {
                        ParcelAuditDetailsDto dto = parcelAuditEntry.getValue().get(0);
                        pickUpDateShipmentDeatils = parcelRatingService.getFedExParcelShipmentDetails(ratingInputCriteriaBean.getCustomerId(), null, null, trackingNumber, null, true, false);
                    }
                    List<ParcelAuditDetailsDto> shipmentRecords = parcelAuditEntry.getValue();
                    if(trackingNumber != null && !trackingNumber.isEmpty()){
                        Map<Long, List<ParcelAuditDetailsDto>> shipments = ParcelRatingUtil.organiseShipmentsByParentId(shipmentRecords);
                        Iterator<Map.Entry<Long, List<ParcelAuditDetailsDto>>> shipmentIterator = shipments.entrySet().iterator();

                        List<ParcelAuditDetailsDto> previousShipment = null;
                        while(shipmentIterator.hasNext()) {
                            Map.Entry<Long, List<ParcelAuditDetailsDto>> shpEntry = shipmentIterator.next();
                            if(shpEntry != null) {
                                boolean frtFound = false;
                                List<ParcelAuditDetailsDto> shipmentDetails = shpEntry.getValue();
                                for(ParcelAuditDetailsDto auditDetails : shipmentDetails) {
                                    if(auditDetails != null && "FRT".equalsIgnoreCase(auditDetails.getChargeClassificationCode())){
                                        frtFound = true;
                                        break;
                                    }
                                }

                                List<ParcelAuditDetailsDto> shipmentsWithPrevFrt = new ArrayList<>(shipmentDetails);

                                if (!frtFound) {
                                    if (previousShipment != null && !previousShipment.isEmpty()) {

                                        ParcelAuditDetailsDto prevFrtCharge = ParcelRatingUtil.getFirstFrightChargeForNonUpsCarrier(previousShipment);
                                        if (prevFrtCharge != null) {
                                            shipmentsWithPrevFrt.add(prevFrtCharge);

                                        }
                                    }
                                }
                                if (pickUpDateShipmentDeatils != null && pickUpDateShipmentDeatils.size() > 0) {

                                    for (ParcelAuditDetailsDto dto : pickUpDateShipmentDeatils) {
                                        if (shipmentDetails.get(0).getParentId() > dto.getParentId()
                                                && shipmentDetails.get(0).getPickupDate().compareTo(dto.getPickupDate()) == 0
                                                && ParcelAuditConstant.ChargeClassificationCode.ACS.name().equalsIgnoreCase(dto.getChargeClassificationCode())) {

                                            shipmentsWithPrevFrt.add(dto);


                                            }
                                        }
                                    }

                                addNonUpsShipmentEntryIntoQueue(shipmentsWithPrevFrt, ratingInputCriteriaBean, accessorialBeans);

                                previousShipment = new ArrayList<>(shipmentDetails);
                            }
                        }
                    }
                    entryIterator.remove();
                }
            }
            addMwtOrHwtShipmentEntryIntoQueue(mwtDetailsMap, "fedex", ratingInputCriteriaBean, accessorialBeans);
        }
    }

    private void addNonUpsShipmentEntryIntoQueue(List<ParcelAuditDetailsDto> shipmentsWithPrevFrt, ParcelRatingInputCriteriaBean ratingInputCriteriaBean, List<ServiceFlagAccessorialBean> accessorialBeans) {
        if(!parcelRatingService.shipmentExist(shipmentsWithPrevFrt.get(0).getParentId())) {
            try{
                RatingQueueBean ratingQueueBean = ParcelRatingUtil.prepareShipmentEntryForNonUpsShipment(shipmentsWithPrevFrt, ratingInputCriteriaBean.getRateSetName(), accessorialBeans);
                if(ratingQueueBean != null){
                    ratingQueueBean.setTaskId(ratingInputCriteriaBean.getTaskId());
                    ratingQueueBean.setThresholdValue(ratingInputCriteriaBean.getThresholdValue());
                    ratingQueueBean.setThresholdType(ratingInputCriteriaBean.getThresholdType());
                    ratingQueueBean.setServiceLevel(ratingInputCriteriaBean.getServiceLevel());
                    parcelRatingService.saveRatingQueueBean(ratingQueueBean);
                }
            } catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    private void addUpsShipmentEntryIntoQueue(List<ParcelAuditDetailsDto> shipmentsWithPrevFrt, ParcelRatingInputCriteriaBean ratingInputCriteriaBean, List<ServiceFlagAccessorialBean> accessorialBeans) {
        if(!parcelRatingService.shipmentExist(shipmentsWithPrevFrt.get(0).getParentId())) {
            try{
                RatingQueueBean ratingQueueBean = ParcelRatingUtil.prepareShipmentEntryForUpsShipment(shipmentsWithPrevFrt, ratingInputCriteriaBean.getRateSetName(), accessorialBeans);
                if(ratingQueueBean != null){
                    ratingQueueBean.setTaskId(ratingInputCriteriaBean.getTaskId());
                    ratingQueueBean.setThresholdValue(ratingInputCriteriaBean.getThresholdValue());
                    ratingQueueBean.setThresholdType(ratingInputCriteriaBean.getThresholdType());
                    ratingQueueBean.setServiceLevel(ratingInputCriteriaBean.getServiceLevel());
                    parcelRatingService.saveRatingQueueBean(ratingQueueBean);
                }
            }catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    private void addMwtOrHwtShipmentEntryIntoQueue(Map<String, List<ParcelAuditDetailsDto>> mwtDetailsMap, String rateTo, ParcelRatingInputCriteriaBean ratingInputCriteriaBean, List<ServiceFlagAccessorialBean> accessorialBeans) throws SQLException {

        List<RatingQueueBean> queueBeanList = null;
        for (Map.Entry<String, List<ParcelAuditDetailsDto>> entry : mwtDetailsMap.entrySet()) {

            Map<String, List<ParcelAuditDetailsDto>> trackingNumberWiseShipments = ParcelRatingUtil.prepareTrackingNumberWiseAuditDetails(entry.getValue());

            for (Map.Entry<String, List<ParcelAuditDetailsDto>> listEntry : trackingNumberWiseShipments.entrySet()) {

                RatingQueueBean ratingQueueBean = null;
                if (!parcelRatingService.shipmentExist(listEntry.getValue().get(0).getParentId())) {
                    if (StringUtils.equalsIgnoreCase("fedex", rateTo)) {
                        ratingQueueBean = ParcelRatingUtil.prepareShipmentEntryForNonUpsShipment(listEntry.getValue(), ratingInputCriteriaBean.getRateSetName(), accessorialBeans);
                    } else if (StringUtils.equalsIgnoreCase("ups", rateTo)) {
                        ratingQueueBean = ParcelRatingUtil.prepareShipmentEntryForUpsShipment(listEntry.getValue(), ratingInputCriteriaBean.getRateSetName(), accessorialBeans);
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
