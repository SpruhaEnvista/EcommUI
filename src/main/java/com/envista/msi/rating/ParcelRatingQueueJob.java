package com.envista.msi.rating;

import com.envista.msi.api.domain.util.ParcelRatingUtil;
import com.envista.msi.api.web.rest.dto.rtr.MsiARChargeCodesDto;
import com.envista.msi.api.web.rest.dto.rtr.ParcelAuditDetailsDto;
import com.envista.msi.api.web.rest.util.audit.parcel.ParcelAuditConstant;
import com.envista.msi.rating.bean.RatingQueueBean;
import com.envista.msi.rating.dao.DirectJDBCDAO;
import com.envista.msi.rating.service.ParcelNonUpsRatingService;
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
                }
            }


            boolean isValidInput = true;
            /*if(customerId == null || customerId.isEmpty()){
                System.out.println("Please enter customer ID.");
                isValidInput = false;
            }
            if(fromShipDate == null || fromShipDate.isEmpty()){
                System.out.println("Please enter fromShipDate.");
                isValidInput = false;
            }
            if(toShipDate == null || toShipDate.isEmpty()){
                System.out.println("Please enter toShipDate.");
                isValidInput = false;
            }
            if(rateTo == null || rateTo.isEmpty()){
                System.out.println("Please enter rateTo(UPS/FedEx..).");
                isValidInput = false;
            }*/

            if(isValidInput){
                ParcelRatingQueueJob.getInstance().processShipments(customerId, fromShipDate, toShipDate, trackingNumbers, invoiceIds, rateTo);
            }
        }
    }

    private void processShipments(String customerId, String fromShipDate, String toShipDate, String trackingNumber, String invoiceIds, String rateTo) {
        List<ParcelAuditDetailsDto> allShipmentDetails = null;
        if("ups".equalsIgnoreCase(rateTo)){
            List<Long> invoiceList = new DirectJDBCDAO().loadInvoiceIds(fromShipDate, toShipDate, customerId, invoiceIds, 0, "UPS");
            if(invoiceList != null && !invoiceList.isEmpty()) {
                for(Long invId : invoiceList){
                    if(invId != null) {
                        System.out.println("For Invoice-->"+invId);
                        allShipmentDetails = new ParcelUpsRatingService().getUpsParcelShipmentDetails(customerId, fromShipDate, toShipDate, trackingNumber, invId.toString());
                        if(allShipmentDetails != null && !allShipmentDetails.isEmpty()){
                            Map<String, List<ParcelAuditDetailsDto>> trackingNumberWiseShipments = ParcelRatingUtil.prepareTrackingNumberWiseAuditDetails(allShipmentDetails);
                            processUpsShipments(trackingNumberWiseShipments, parcelRatingService.getAllMappedARChargeCodes(), customerId);
                        }
                    }
                }
            }

        } else if("fedex".equalsIgnoreCase(rateTo)) {
            List<Long> invoiceList = new DirectJDBCDAO().loadInvoiceIds(fromShipDate, toShipDate, customerId, invoiceIds, 0, "FEDEX");
            if(invoiceList != null && !invoiceList.isEmpty()) {
                for (Long invId : invoiceList) {
                    if (invId != null) {
                        System.out.println("For Invoice-->"+invId);
                        allShipmentDetails =  parcelRatingService.getFedExParcelShipmentDetails(customerId, fromShipDate, toShipDate, trackingNumber, invId.toString());
                        if(allShipmentDetails != null && !allShipmentDetails.isEmpty()){
                            Map<String, List<ParcelAuditDetailsDto>> trackingNumberWiseShipments = ParcelRatingUtil.prepareTrackingNumberWiseAuditDetails(allShipmentDetails);
                            processFedExShipments(trackingNumberWiseShipments, parcelRatingService.getAllMappedARChargeCodes());
                        }
                    }
                }
            }
        }
    }

    private void processUpsShipments(Map<String, List<ParcelAuditDetailsDto>> trackingNumberWiseShipments, MsiARChargeCodesDto allMappedARChargeCodes, String customerIds) {
        Iterator<Map.Entry<String, List<ParcelAuditDetailsDto>>> entryIterator = trackingNumberWiseShipments.entrySet().iterator();
        while(entryIterator.hasNext()){
            Map.Entry<String,List<ParcelAuditDetailsDto>> parcelAuditEntry = entryIterator.next();
            if(parcelAuditEntry != null){
                try{
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
                                        addUpsShipmentEntryIntoQueue(commercialShipment, allMappedARChargeCodes);
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
                                        addUpsShipmentEntryIntoQueue(residentialShipment, allMappedARChargeCodes);
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
                                            addUpsShipmentEntryIntoQueue(resComShipmentToRate, allMappedARChargeCodes);
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
                                                addUpsShipmentEntryIntoQueue(shipmentsToRate, allMappedARChargeCodes);
                                            }
                                        } else {
                                            addUpsShipmentEntryIntoQueue(shipmentChargeList, allMappedARChargeCodes);
                                        }
                                    }
                                }
                                previousShipment = new ArrayList<>(shipmentChargeList);
                            }
                        }
                    }
                    entryIterator.remove();
                }catch (Exception e){
                    e.printStackTrace();
                    System.out.println(e.getMessage());
                    //Do nothing
                }
            }
        }
    }

    public void processFedExShipments(Map<String, List<ParcelAuditDetailsDto>> trackingNumberWiseShipments, MsiARChargeCodesDto msiARChargeCode) {
        if(trackingNumberWiseShipments != null && !trackingNumberWiseShipments.isEmpty()) {
            if(trackingNumberWiseShipments != null && !trackingNumberWiseShipments.isEmpty()){
                List<ParcelAuditDetailsDto> previousShipment = null;
                Iterator<Map.Entry<String, List<ParcelAuditDetailsDto>>> entryIterator = trackingNumberWiseShipments.entrySet().iterator();
                while(entryIterator.hasNext()){
                    Map.Entry<String,List<ParcelAuditDetailsDto>> parcelAuditEntry = entryIterator.next();
                    if(parcelAuditEntry != null) {
                        try {
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
                                                    addNonUpsShipmentEntryIntoQueue(shipmentsWithPrevFrt, msiARChargeCode);
                                                }
                                            }
                                        } else {
                                            addNonUpsShipmentEntryIntoQueue(shipmentDetails, msiARChargeCode);
                                        }
                                        previousShipment = new ArrayList<>(shipmentDetails);
                                    }
                                }
                            }
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                        entryIterator.remove();
                    }
                }
            }
        }
    }

    private void addNonUpsShipmentEntryIntoQueue(List<ParcelAuditDetailsDto> shipmentsWithPrevFrt, MsiARChargeCodesDto msiARChargeCode) {
        if(!parcelRatingService.shipmentExist(shipmentsWithPrevFrt.get(0).getParentId())) {
            RatingQueueBean ratingQueueBean = ParcelRatingUtil.prepareShipmentEntryForNonUpsShipment(shipmentsWithPrevFrt, msiARChargeCode);
            if(ratingQueueBean != null){
                parcelRatingService.saveRatingQueueBean(ratingQueueBean);
            }
        }
    }

    private void addUpsShipmentEntryIntoQueue(List<ParcelAuditDetailsDto> shipmentsWithPrevFrt, MsiARChargeCodesDto msiARChargeCode) {
        if(!parcelRatingService.shipmentExist(shipmentsWithPrevFrt.get(0).getParentId())) {
            RatingQueueBean ratingQueueBean = ParcelRatingUtil.prepareShipmentEntryForUpsShipment(shipmentsWithPrevFrt, msiARChargeCode);
            if(ratingQueueBean != null){
                parcelRatingService.saveRatingQueueBean(ratingQueueBean);
            }
        }
    }
}
