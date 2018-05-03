package com.envista.msi.rating;

import com.envista.msi.api.domain.util.ParcelRatingUtil;
import com.envista.msi.api.web.rest.dto.rtr.MsiARChargeCodesDto;
import com.envista.msi.api.web.rest.dto.rtr.ParcelAuditDetailsDto;
import com.envista.msi.api.web.rest.util.audit.parcel.ParcelAuditConstant;
import com.envista.msi.rating.bean.RatingQueueBean;
import com.envista.msi.rating.dao.RatingQueueDAO;
import com.envista.msi.rating.service.ParcelRatingService;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by Sujit kumar on 01/05/2018.
 */
public class ParcelRatingQueueJob {
    public static ParcelRatingQueueJob getInstance(){return new ParcelRatingQueueJob();}
    ParcelRatingService parcelRatingService = new ParcelRatingService();
    public static void main(String[] args) {
        String customerId = null;
        String fromShipDate = null;
        String toShipDate = null;
        String rateTo = null;
        String trackingNumbers = null;
        String invoiceIds = null;
        if(args != null && args.length > 0){
            for (String s : args) {
                String[] array = s.split("-");

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
            allShipmentDetails = parcelRatingService.getUpsParcelShipmentDetails(customerId, fromShipDate, toShipDate);
            if(allShipmentDetails != null && !allShipmentDetails.isEmpty()){
                Map<String, List<ParcelAuditDetailsDto>> trackingNumberWiseShipments = ParcelRatingUtil.prepareTrackingNumberWiseAuditDetails(allShipmentDetails);

            }
        } else if("fedex".equalsIgnoreCase(rateTo)) {
            allShipmentDetails =  parcelRatingService.getFedExParcelShipmentDetails(customerId, fromShipDate, toShipDate, trackingNumber, invoiceIds);
            if(allShipmentDetails != null && !allShipmentDetails.isEmpty()){
                Map<String, List<ParcelAuditDetailsDto>> trackingNumberWiseShipments = ParcelRatingUtil.prepareTrackingNumberWiseAuditDetails(allShipmentDetails);
                processFedExShipments(trackingNumberWiseShipments, parcelRatingService.getAllMappedARChargeCodes());
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
                                                    addShipmentEntryIntoQueue(shipmentsWithPrevFrt, msiARChargeCode);
                                                }
                                            }
                                        } else {
                                            addShipmentEntryIntoQueue(shipmentDetails, msiARChargeCode);
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

    private void addShipmentEntryIntoQueue(List<ParcelAuditDetailsDto> shipmentsWithPrevFrt, MsiARChargeCodesDto msiARChargeCode) {
        RatingQueueBean ratingQueueBean = ParcelRatingUtil.prepareShipmentEntryForUpsShipment(shipmentsWithPrevFrt, ParcelAuditConstant.AR_RATE_REQUEST_LICENSE_KEY, msiARChargeCode);
        if(ratingQueueBean != null){
            parcelRatingService.saveRatingQueueBean(ratingQueueBean);
        }
    }
}
