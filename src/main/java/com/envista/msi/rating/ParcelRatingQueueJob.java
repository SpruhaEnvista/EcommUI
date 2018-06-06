package com.envista.msi.rating;

import com.envista.msi.api.domain.util.ParcelRatingUtil;
import com.envista.msi.api.web.rest.dto.rtr.MsiARChargeCodesDto;
import com.envista.msi.api.web.rest.dto.rtr.ParcelAuditDetailsDto;
import com.envista.msi.api.web.rest.util.DateUtil;
import com.envista.msi.api.web.rest.util.audit.parcel.ParcelAuditConstant;
import com.envista.msi.rating.bean.ParcelRatingInputCriteriaBean;
import com.envista.msi.rating.bean.RatingQueueBean;
import com.envista.msi.rating.dao.DirectJDBCDAO;
import com.envista.msi.rating.entity.ParcelRatingInputCriteriaDto;
import com.envista.msi.rating.service.ParcelNonUpsRatingService;
import com.envista.msi.rating.service.ParcelRatingService;
import com.envista.msi.rating.service.ParcelUpsRatingService;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by Sujit kumar on 01/05/2018.
 */
public class ParcelRatingQueueJob {
    public static ParcelRatingQueueJob getInstance(){return new ParcelRatingQueueJob();}
    ParcelNonUpsRatingService parcelRatingService = new ParcelNonUpsRatingService();

    public static void main(String[] args) {
        String customerId = null;
        String fromShipDate = null;
        String toShipDate = null;
        String rateTo = null;
        String trackingNumbers = null;
        String invoiceIds = null;

        ParcelRatingService ratingService = new ParcelRatingService();
        ParcelRatingInputCriteriaDto ratingInputCriteria = ratingService.getRatingInputCriteria(ParcelAuditConstant.ParcelRatingInputProcessStatus.NEW.value);

        if(ratingInputCriteria != null) {
            customerId = ratingInputCriteria.getCustomerId() != null ? ratingInputCriteria.getCustomerId().toString() : null;
            fromShipDate = ratingInputCriteria.getFromShipDate() != null ? DateUtil.format(ratingInputCriteria.getFromShipDate(), "dd-MMM-yyyy") : null;
            toShipDate = ratingInputCriteria.getToShipDate() != null ? DateUtil.format(ratingInputCriteria.getToShipDate(), "dd-MMM-yyyy") : null;
            if(ratingInputCriteria.getCarrierId() != null) {
                if(ratingInputCriteria.getCarrierId() == 21) {
                    rateTo = "ups";
                } else if(ratingInputCriteria.getCarrierId() == 22) {
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
                ParcelRatingQueueJob.getInstance().processShipments(ratingInputCriteriaBean);

                ratingService.updateRatingInputCriteriaStatus(ratingInputCriteria.getId(), ParcelAuditConstant.ParcelRatingInputProcessStatus.COMPLETED.value);
            }catch (Exception e){
                ratingService.updateRatingInputCriteriaStatus(ratingInputCriteria.getId(), ParcelAuditConstant.ParcelRatingInputProcessStatus.EXCEPTION.value);
            }

        }
    }

    private void processShipments(ParcelRatingInputCriteriaBean ratingInputCriteriaBean) {
        List<ParcelAuditDetailsDto> allShipmentDetails = null;
        if("ups".equalsIgnoreCase(ratingInputCriteriaBean.getRateTo())){
            List<Long> invoiceList = new DirectJDBCDAO().loadInvoiceIds(ratingInputCriteriaBean.getFromShipDate(), ratingInputCriteriaBean.getToShipDate(), ratingInputCriteriaBean.getCustomerId(), ratingInputCriteriaBean.getInvoiceIds(), 0, "UPS");
            if(invoiceList != null && !invoiceList.isEmpty()) {
                for(Long invId : invoiceList){
                    if(invId != null) {
                        System.out.println("For Invoice-->"+invId);
                        allShipmentDetails = new ParcelUpsRatingService().getUpsParcelShipmentDetails(ratingInputCriteriaBean.getCustomerId(), ratingInputCriteriaBean.getFromShipDate(), ratingInputCriteriaBean.getToShipDate(), ratingInputCriteriaBean.getTrackingNumbers(), invId.toString());
                        if(allShipmentDetails != null && !allShipmentDetails.isEmpty()){
                            Map<String, List<ParcelAuditDetailsDto>> trackingNumberWiseShipments = ParcelRatingUtil.prepareTrackingNumberWiseAuditDetails(allShipmentDetails);
                            processUpsShipments(trackingNumberWiseShipments, parcelRatingService.getAllMappedARChargeCodes(), ratingInputCriteriaBean.getCustomerId(), ratingInputCriteriaBean);
                        }
                    }
                }
            }

        } else if("fedex".equalsIgnoreCase(ratingInputCriteriaBean.getRateTo())) {
            List<Long> invoiceList = new DirectJDBCDAO().loadInvoiceIds(ratingInputCriteriaBean.getFromShipDate(), ratingInputCriteriaBean.getToShipDate(), ratingInputCriteriaBean.getCustomerId(), ratingInputCriteriaBean.getInvoiceIds(), 0, "FEDEX");
            if(invoiceList != null && !invoiceList.isEmpty()) {
                for (Long invId : invoiceList) {
                    if (invId != null) {
                        System.out.println("For Invoice-->"+invId);
                        allShipmentDetails =  parcelRatingService.getFedExParcelShipmentDetails(ratingInputCriteriaBean.getCustomerId(), ratingInputCriteriaBean.getFromShipDate(), ratingInputCriteriaBean.getToShipDate(), ratingInputCriteriaBean.getTrackingNumbers(), invId.toString());
                        if(allShipmentDetails != null && !allShipmentDetails.isEmpty()){
                            Map<String, List<ParcelAuditDetailsDto>> trackingNumberWiseShipments = ParcelRatingUtil.prepareTrackingNumberWiseAuditDetails(allShipmentDetails);
                            processFedExShipments(trackingNumberWiseShipments, parcelRatingService.getAllMappedARChargeCodes(), ratingInputCriteriaBean);
                        }
                    }
                }
            }
        }
    }

    private void processUpsShipments(Map<String, List<ParcelAuditDetailsDto>> trackingNumberWiseShipments, MsiARChargeCodesDto allMappedARChargeCodes, String customerIds, ParcelRatingInputCriteriaBean ratingInputCriteriaBean) {
        Iterator<Map.Entry<String, List<ParcelAuditDetailsDto>>> entryIterator = trackingNumberWiseShipments.entrySet().iterator();
        while(entryIterator.hasNext()){
            Map.Entry<String,List<ParcelAuditDetailsDto>> parcelAuditEntry = entryIterator.next();
            if(parcelAuditEntry != null){
                String trackingNumber = parcelAuditEntry.getKey();
                List<ParcelAuditDetailsDto> shipmentRecords = null;
                if(trackingNumber != null && !trackingNumber.isEmpty()){
                    shipmentRecords = new ParcelUpsRatingService().getUpsParcelShipmentDetails(customerIds, trackingNumber, true);
                }

                Map<Long, List<ParcelAuditDetailsDto>> shipments = ParcelRatingUtil.organiseShipmentsByParentId(shipmentRecords);
                Iterator<Map.Entry<Long, List<ParcelAuditDetailsDto>>> shipmentIterator = shipments.entrySet().iterator();

                List<ParcelAuditDetailsDto> previousShipment = null;
                while(shipmentIterator.hasNext()){
                    Map.Entry<Long, List<ParcelAuditDetailsDto>> shpEntry = shipmentIterator.next();
                    if(shpEntry != null){
                        List<ParcelAuditDetailsDto> shipmentChargeList = shpEntry.getValue();
                        /*if(shipmentChargeList != null) {
                            if(shipmentChargeList.size() == 1 && "FRT".equalsIgnoreCase(shipmentChargeList.get(0).getChargeClassificationCode())
                                    && shipmentChargeList.get(0).getNetAmount() != null && Double.parseDouble(shipmentChargeList.get(0).getNetAmount()) == 0.0) {
                                continue;
                            }
                        }*/

                        if(shipmentChargeList != null) {
                            if(!ParcelRatingUtil.isShipmentRated(shipmentChargeList)){
                                if(ParcelRatingUtil.containsCharge(ParcelAuditConstant.COMMERCIAL_ADJUSTMENT_CHARGE_TYPE, shipmentChargeList)){
                                    List<ParcelAuditDetailsDto> commercialShipment = new ArrayList<>();
                                    commercialShipment.addAll(shipmentChargeList);

                                    if(previousShipment != null) {
                                        for(ParcelAuditDetailsDto commShipment : previousShipment){
                                            if(commShipment != null && !"RES".equalsIgnoreCase(commShipment.getChargeDescriptionCode())
                                                    && !"RSC".equalsIgnoreCase(commShipment.getChargeDescriptionCode()) && !"FRT".equalsIgnoreCase(commShipment.getChargeClassificationCode())){
                                                commercialShipment.add(commShipment);
                                            }
                                        }
                                    }
                                    addUpsShipmentEntryIntoQueue(commercialShipment, allMappedARChargeCodes, ratingInputCriteriaBean);
                                } else if(ParcelRatingUtil.containsCharge(ParcelAuditConstant.RESIDENTIAL_ADJUSTMENT_CHARGE_TYPE, shipmentChargeList)){
                                    //keeping it in separate if condition in order to handle few more scenarios in future.
                                    List<ParcelAuditDetailsDto> residentialShipment = new ArrayList<>();
                                    residentialShipment.addAll(shipmentChargeList);
                                    if(previousShipment != null) {
                                        for(ParcelAuditDetailsDto commShipment : previousShipment){
                                            if(commShipment != null && !"FRT".equalsIgnoreCase(commShipment.getChargeClassificationCode())){
                                                residentialShipment.add(commShipment);
                                            }
                                        }
                                    }
                                    addUpsShipmentEntryIntoQueue(residentialShipment, allMappedARChargeCodes, ratingInputCriteriaBean);
                                } else if(ParcelRatingUtil.containsCharge(ParcelAuditConstant.RESIDENTIAL_COMMERCIAL_ADJUSTMENT_CHARGE_TYPE, shipmentChargeList)) {
                                    if(previousShipment != null) {
                                        List<ParcelAuditDetailsDto> resComShipmentToRate = new ArrayList<>();
                                        for(ParcelAuditDetailsDto shpCharge : shipmentChargeList) {
                                            if(shpCharge != null && ParcelAuditConstant.ChargeClassificationCode.ACC.name().equalsIgnoreCase(shpCharge.getChargeClassificationCode())
                                                    && !"RES".equalsIgnoreCase(shpCharge.getChargeDescriptionCode())) {
                                                resComShipmentToRate.add(shpCharge);
                                            }
                                        }
                                        for(ParcelAuditDetailsDto prevShipmentCharge : previousShipment) {
                                            if(prevShipmentCharge != null) {
                                                if(ParcelAuditConstant.ChargeClassificationCode.FRT.name().equalsIgnoreCase(prevShipmentCharge.getChargeClassificationCode())) {
                                                    resComShipmentToRate.add(prevShipmentCharge);
                                                } else if(ParcelAuditConstant.ChargeClassificationCode.ACC.name().equalsIgnoreCase(prevShipmentCharge.getChargeClassificationCode())
                                                        && !"RES".equalsIgnoreCase(prevShipmentCharge.getChargeDescriptionCode())) {
                                                    resComShipmentToRate.add(prevShipmentCharge);
                                                }
                                            }
                                        }
                                        addUpsShipmentEntryIntoQueue(resComShipmentToRate, allMappedARChargeCodes, ratingInputCriteriaBean);
                                    }
                                } else {
                                    if(previousShipment != null){
                                        List<ParcelAuditDetailsDto> shipmentsToRate = new ArrayList<>(shipmentChargeList);
                                        if(shipmentsToRate != null) {
                                            boolean hasFrtCharge = false;
                                            boolean frtChargeManipulated = false;
                                            ParcelAuditDetailsDto frtCharged = ParcelRatingUtil.findFrtCharge(shipmentsToRate);
                                            if(frtCharged != null) {
                                                hasFrtCharge = true;
                                                if(frtCharged.getPackageWeight() != null && !frtCharged.getPackageWeight().isEmpty() && Float.parseFloat(frtCharged.getPackageWeight()) == 0) {
                                                    ParcelAuditDetailsDto prevShipmentFrtCharge = ParcelRatingUtil.findFrtCharge(previousShipment);
                                                    if(prevShipmentFrtCharge != null && prevShipmentFrtCharge.getPackageWeight() != null
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
                                                        System.out.println("Prev shipment weight added for tracking number :: " + prevShipmentFrtCharge.getTrackingNumber());

                                                        frtChargeManipulated = true;
                                                    }
                                                }
                                            }else {
                                                hasFrtCharge = false;
                                            }
                                            boolean hasFSCCharge = ParcelRatingUtil.containsFuelSurcharge(shipmentsToRate);
                                            for(ParcelAuditDetailsDto prevShpCharge : previousShipment){
                                                if(prevShpCharge != null && ParcelAuditConstant.ChargeClassificationCode.ACC.name().equalsIgnoreCase(prevShpCharge.getChargeClassificationCode())) {
                                                    shipmentsToRate.add(prevShpCharge);
                                                }
                                                if(!hasFSCCharge && ParcelAuditConstant.ChargeClassificationCode.ACC.name().equalsIgnoreCase(prevShpCharge.getChargeClassificationCode())) {
                                                    shipmentsToRate.add(prevShpCharge);
                                                }
                                                if(!hasFrtCharge && !frtChargeManipulated && ParcelAuditConstant.ChargeClassificationCode.FRT.name().equalsIgnoreCase(prevShpCharge.getChargeClassificationCode())) {
                                                    shipmentsToRate.add(prevShpCharge);
                                                }
                                            }
                                            addUpsShipmentEntryIntoQueue(shipmentsToRate, allMappedARChargeCodes, ratingInputCriteriaBean);
                                        }
                                    } else {
                                        addUpsShipmentEntryIntoQueue(shipmentChargeList, allMappedARChargeCodes, ratingInputCriteriaBean);
                                    }
                                }
                            }
                            previousShipment = new ArrayList<>(shipmentChargeList);
                        }
                    }
                }
                entryIterator.remove();
            }
        }
    }

    public void processFedExShipments(Map<String, List<ParcelAuditDetailsDto>> trackingNumberWiseShipments, MsiARChargeCodesDto msiARChargeCode, ParcelRatingInputCriteriaBean ratingInputCriteriaBean) {
        if(trackingNumberWiseShipments != null && !trackingNumberWiseShipments.isEmpty()) {
            if(trackingNumberWiseShipments != null && !trackingNumberWiseShipments.isEmpty()){
                List<ParcelAuditDetailsDto> previousShipment = null;
                Iterator<Map.Entry<String, List<ParcelAuditDetailsDto>>> entryIterator = trackingNumberWiseShipments.entrySet().iterator();
                while(entryIterator.hasNext()){
                    Map.Entry<String,List<ParcelAuditDetailsDto>> parcelAuditEntry = entryIterator.next();
                    if(parcelAuditEntry != null) {
                        String trackingNumber = parcelAuditEntry.getKey();
                        List<ParcelAuditDetailsDto> shipmentRecords = parcelAuditEntry.getValue();
                        if(trackingNumber != null && !trackingNumber.isEmpty()){
                            Map<Long, List<ParcelAuditDetailsDto>> shipments = ParcelRatingUtil.organiseShipmentsByParentId(shipmentRecords);
                            Iterator<Map.Entry<Long, List<ParcelAuditDetailsDto>>> shipmentIterator = shipments.entrySet().iterator();
                            while(shipmentIterator.hasNext()) {
                                Map.Entry<Long, List<ParcelAuditDetailsDto>> shpEntry = shipmentIterator.next();
                                if(shpEntry != null) {
                                    boolean frtFound = false;
                                    List<ParcelAuditDetailsDto> shipmentDetails = shpEntry.getValue();
                                    for(ParcelAuditDetailsDto auditDetails : shipmentDetails) {
                                        if(auditDetails != null && "FRT".equalsIgnoreCase(auditDetails.getChargeClassificationCode())){
                                            frtFound = true;
                                        }
                                    }
                                    if(!frtFound){
                                        if(previousShipment != null && !previousShipment.isEmpty()){
                                            List<ParcelAuditDetailsDto> shipmentsWithPrevFrt = new ArrayList<>(shipmentDetails);
                                            ParcelAuditDetailsDto prevFrtCharge = ParcelRatingUtil.getFirstFrightChargeForNonUpsCarrier(previousShipment);
                                            if(prevFrtCharge != null){
                                                shipmentsWithPrevFrt.add(prevFrtCharge);
                                                addNonUpsShipmentEntryIntoQueue(shipmentsWithPrevFrt, msiARChargeCode, ratingInputCriteriaBean);
                                            }
                                        }
                                    } else {
                                        addNonUpsShipmentEntryIntoQueue(shipmentDetails, msiARChargeCode, ratingInputCriteriaBean);
                                    }
                                    previousShipment = new ArrayList<>(shipmentDetails);
                                }
                            }
                        }
                        entryIterator.remove();
                    }
                }
            }
        }
    }

    private void addNonUpsShipmentEntryIntoQueue(List<ParcelAuditDetailsDto> shipmentsWithPrevFrt, MsiARChargeCodesDto msiARChargeCode, ParcelRatingInputCriteriaBean ratingInputCriteriaBean) {
        if(!parcelRatingService.shipmentExist(shipmentsWithPrevFrt.get(0).getParentId())) {
            try{
                RatingQueueBean ratingQueueBean = ParcelRatingUtil.prepareShipmentEntryForNonUpsShipment(shipmentsWithPrevFrt, msiARChargeCode);
                if(ratingQueueBean != null){
                    ratingQueueBean.setTaskId(ratingInputCriteriaBean.getTaskId());
                    ratingQueueBean.setRateSetName(ratingInputCriteriaBean.getRateSetName());
                    parcelRatingService.saveRatingQueueBean(ratingQueueBean);
                }
            } catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    private void addUpsShipmentEntryIntoQueue(List<ParcelAuditDetailsDto> shipmentsWithPrevFrt, MsiARChargeCodesDto msiARChargeCode, ParcelRatingInputCriteriaBean ratingInputCriteriaBean) {
        if(!parcelRatingService.shipmentExist(shipmentsWithPrevFrt.get(0).getParentId())) {
            try{
                RatingQueueBean ratingQueueBean = ParcelRatingUtil.prepareShipmentEntryForUpsShipment(shipmentsWithPrevFrt, msiARChargeCode);
                if(ratingQueueBean != null){
                    ratingQueueBean.setTaskId(ratingInputCriteriaBean.getTaskId());
                    ratingQueueBean.setRateSetName(ratingInputCriteriaBean.getRateSetName());
                    parcelRatingService.saveRatingQueueBean(ratingQueueBean);
                }
            }catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
