package com.envista.msi.api.service.rtr;

import com.envista.msi.api.dao.rtr.ParcelRTRDao;
import com.envista.msi.api.domain.util.ParcelRatingUtil;
import com.envista.msi.api.web.rest.dto.rtr.MsiARChargeCodesDto;
import com.envista.msi.api.web.rest.dto.rtr.ParcelAuditDetailsDto;
import com.envista.msi.api.web.rest.dto.rtr.ParcelAuditRequestResponseLog;
import com.envista.msi.api.web.rest.dto.rtr.ParcelRateDetailsDto;
import com.envista.msi.api.web.rest.dto.rtr.RateSetResponse;
import com.envista.msi.api.web.rest.dto.rtr.RatedChargeDetailsDto;
import com.envista.msi.api.web.rest.util.CommonUtil;
import com.envista.msi.api.web.rest.util.audit.parcel.ParcelAuditConstant;
import com.envista.msi.api.web.rest.util.audit.parcel.ParcelAuditConstant.RTRStatus;
import com.envista.msi.api.web.rest.util.audit.parcel.ParcelAuditConstant.RateTo;
import com.envista.msi.api.web.rest.util.audit.parcel.ParcelRateRequestBuilder;
import com.envista.msi.api.web.rest.util.audit.parcel.ParcelRateResponse;
import com.envista.msi.api.web.rest.util.audit.parcel.ParcelRateResponseParser;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import javax.inject.Inject;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.StringJoiner;

/**
 * Created by Sujit kumar on 08/06/2017.
 */
@Service
@Transactional
public class ParcelRTRService{

    private final Logger log = LoggerFactory.getLogger(ParcelRTRService.class);

    @Inject
    private ParcelRTRDao parcelRTRDao;

    @Autowired
    @org.springframework.beans.factory.annotation.Qualifier(value = "rtrRateResource")
    private MessageSource messageSource;

    @Autowired
    private RestTemplate restTemplate;

    public Map<String, List<ParcelAuditDetailsDto>> loadUpsParcelAuditDetails(String customerId, String fromDate, String toDate, String trackingNumbers, String invoiceIds, Integer ignoreRtrStatus){
        return prepareTrackingNumberWiseAuditDetails(parcelRTRDao.loadUpsParcelAuditDetails(customerId, fromDate, toDate, trackingNumbers, invoiceIds, ignoreRtrStatus));
    }

    public Map<String, List<ParcelAuditDetailsDto>> loadUpsParcelAuditDetails(String customerId, String fromDate, String toDate, String trackingNumbers, String invoiceIds){
        return prepareTrackingNumberWiseAuditDetails(parcelRTRDao.loadUpsParcelAuditDetails(customerId, fromDate, toDate, trackingNumbers, invoiceIds, 0));
    }

    public List<RatedChargeDetailsDto> getRatedChargeAmount(Long parentId){
        return parcelRTRDao.getRatedChargeAmount(parentId);
    }

    /**
     * Returns tracking number wise UPS parcel audit details.
     * @param fromDate
     * @param toDate
     * @return
     */
    public Map<String, List<ParcelAuditDetailsDto>> loadUpsParcelAuditDetails(String customerId, String fromDate, String toDate, String trackingNumbers){
        return prepareTrackingNumberWiseAuditDetails(parcelRTRDao.loadUpsParcelAuditDetails(customerId, fromDate, toDate, trackingNumbers, null, 0));
    }

    public List<ParcelAuditDetailsDto> loadUpsParcelAuditDetails(String customerId, String trackingNumber, Integer ignoreRtrStatus){
        return parcelRTRDao.loadUpsParcelAuditDetails(customerId, trackingNumber, ignoreRtrStatus);
    }

    /**
     * Returns tracking number wise non-UPS parcel audit details.
     * @param fromDate
     * @param toDate
     * @return
     */
    public Map<String, List<ParcelAuditDetailsDto>> loadNonUpsParcelAuditDetails(String customerId, String fromDate, String toDate, String trackingNumbers){
        return prepareTrackingNumberWiseAuditDetails(parcelRTRDao.loadNonUpsParcelAuditDetails(customerId, fromDate, toDate, ParcelAuditConstant.NON_UPS_CARRIER_IDS, trackingNumbers, null, false));
    }

    public Map<String, List<ParcelAuditDetailsDto>> loadNonUpsParcelAuditDetails(String customerId, String fromDate, String toDate, String trackingNumbers, String invoiceIds){
        return prepareTrackingNumberWiseAuditDetails(parcelRTRDao.loadNonUpsParcelAuditDetails(customerId, fromDate, toDate, ParcelAuditConstant.NON_UPS_CARRIER_IDS, trackingNumbers, invoiceIds, false));
    }

    /**
     * To prepare shipment wise audit details,
     * Here shipment means a tracking number.
     *
     * @param auditDetailsList
     * @return
     */
    private Map<String, List<ParcelAuditDetailsDto>> prepareTrackingNumberWiseAuditDetails(List<ParcelAuditDetailsDto> auditDetailsList){
        Map<String, List<ParcelAuditDetailsDto>> parcelAuditMap = null;
        if(auditDetailsList != null && !auditDetailsList.isEmpty()){
            parcelAuditMap = new HashMap<>();
            for(ParcelAuditDetailsDto parcelAuditDetails : auditDetailsList){
                if(parcelAuditDetails != null){
                    String trackingNumber = parcelAuditDetails.getTrackingNumber();
                    if(trackingNumber != null && !trackingNumber.isEmpty() && parcelAuditMap.containsKey(parcelAuditDetails.getTrackingNumber())){
                        parcelAuditMap.get(trackingNumber).add(parcelAuditDetails);
                    }else{
                        List<ParcelAuditDetailsDto> auditList = new ArrayList<>();
                        auditList.add(parcelAuditDetails);
                        parcelAuditMap.put(trackingNumber, auditList);
                    }
                }
            }
        }
        return parcelAuditMap;
    }

    /**
     *
     * @param customerId
     * @param fromDate
     * @param toDate
     */
    public void parcelRTRRating(String customerId, String fromDate, String toDate, String trackingNumbers){
        String licenseKey = messageSource.getMessage("RateRequest-LicenseKey", null, null);
        String strProtocol = messageSource.getMessage("RTRprotocol", null, null);
        String strHostName = messageSource.getMessage("RTRHostName", null, null);
        String strPrefix = messageSource.getMessage("RTRPrefix", null, null);
        String url = strProtocol + "://" + strHostName + "/" + strPrefix;

        // doParcelRating(loadUpsParcelAuditDetails(customerId, fromDate, toDate, trackingNumbers), url, licenseKey, RateTo.UPS);
        //  doParcelRating(loadNonUpsParcelAuditDetails(customerId, fromDate, toDate, trackingNumbers), url, licenseKey, RateTo.NON_UPS);
    }

    private void doParcelRatingForNonUpsCarrier(Map<String, List<ParcelAuditDetailsDto>> parcelAuditDetailsMap, String url, String licenseKey, String customerIds, MsiARChargeCodesDto msiARChargeCode) {
        if(parcelAuditDetailsMap != null && !parcelAuditDetailsMap.isEmpty()){

            Map<String, List<ParcelAuditDetailsDto>> mwtDetailsMap = prepareMultiWeightNumberWiseAuditDetails(parcelAuditDetailsMap);

            int shipmentCount = 1;
            Iterator<Map.Entry<String, List<ParcelAuditDetailsDto>>> entryIterator = parcelAuditDetailsMap.entrySet().iterator();
            while(entryIterator.hasNext()){
                Map.Entry<String,List<ParcelAuditDetailsDto>> parcelAuditEntry = entryIterator.next();
                if(parcelAuditEntry != null) {
                    try {
                        String trackingNumber = parcelAuditEntry.getKey();
                        List<ParcelAuditDetailsDto> shipmentRecords = null;
                        if(trackingNumber != null && !trackingNumber.isEmpty()){
                            shipmentRecords = parcelRTRDao.loadNonUpsParcelAuditDetails(customerIds, trackingNumber, "22", true);
                            Map<Long, List<ParcelAuditDetailsDto>> shipments = ParcelRatingUtil.organiseShipmentsByParentId(shipmentRecords);

                            List<ParcelAuditDetailsDto> previousShipment = null;
                            Iterator<Map.Entry<Long, List<ParcelAuditDetailsDto>>> shipmentIterator = shipments.entrySet().iterator();
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
                                    if(!frtFound){
                                        if(previousShipment != null && !previousShipment.isEmpty()){
                                            List<ParcelAuditDetailsDto> shipmentsWithPrevFrt = new ArrayList<>(shipmentDetails);
                                            ParcelAuditDetailsDto prevFrtCharge = ParcelRatingUtil.getFirstFrightChargeForNonUpsCarrier(previousShipment);
                                            if(prevFrtCharge != null){
                                                shipmentsWithPrevFrt.add(prevFrtCharge);
                                                callRTRAndPopulateRates(url, licenseKey, shipmentsWithPrevFrt, RateTo.NON_UPS, msiARChargeCode, false);
                                            }
                                        }
                                    } else {
                                        callRTRAndPopulateRates(url, licenseKey, shipmentDetails, RateTo.NON_UPS, msiARChargeCode, false);
                                    }
                                    System.out.println("Shipment Count :: " + shipmentCount++ + " :: " +parcelAuditEntry.getValue().get(0).getTrackingNumber());
                                    previousShipment = new ArrayList<>(shipmentDetails);
                                }
                            }
                        }
                    }catch (Exception e){}
                    entryIterator.remove();
                }
            }

            for (Map.Entry<String, List<ParcelAuditDetailsDto>> entry : mwtDetailsMap.entrySet()) {

                try {
                    callRTRAndPopulateRates(url, licenseKey, entry.getValue(), RateTo.NON_UPS, msiARChargeCode, true);
                } catch (Exception e) {
                    log.error(e.getMessage());
                }

            }
        }
    }

    private void doParcelRatingForUpsCarrier(Map<String, List<ParcelAuditDetailsDto>> parcelAuditDetailsMap, String url, String licenseKey, String customerIds, MsiARChargeCodesDto msiARChargeCode) {
        if(parcelAuditDetailsMap != null && !parcelAuditDetailsMap.isEmpty()){

            Map<String, List<ParcelAuditDetailsDto>> hwtDetailsMap = prepareHwtNumberWiseAuditDetails(parcelAuditDetailsMap);

            int shipmentCount = 1;

            //Keeping this logic temporarily. When we start new design then we have to change this logic here.
            Iterator<Map.Entry<String, List<ParcelAuditDetailsDto>>> entryIterator = parcelAuditDetailsMap.entrySet().iterator();
            while(entryIterator.hasNext()){
                Map.Entry<String,List<ParcelAuditDetailsDto>> parcelAuditEntry = entryIterator.next();
                if(parcelAuditEntry != null){
                    try{
                        String trackingNumber = parcelAuditEntry.getKey();
                        List<ParcelAuditDetailsDto> shipmentRecords = null;
                        if(trackingNumber != null && !trackingNumber.isEmpty()){
                            shipmentRecords = parcelRTRDao.loadUpsParcelAuditDetails(customerIds, trackingNumber, 1);
                        }

                        Map<Long, List<ParcelAuditDetailsDto>> shipments = ParcelRatingUtil.organiseShipmentsByParentId(shipmentRecords);
                        Iterator<Map.Entry<Long, List<ParcelAuditDetailsDto>>> shipmentIterator = shipments.entrySet().iterator();

                        List<ParcelAuditDetailsDto> previousShipment = null;
                        while(shipmentIterator.hasNext()){
                            Map.Entry<Long, List<ParcelAuditDetailsDto>> shpEntry = shipmentIterator.next();
                            if(shpEntry != null){
                                List<ParcelAuditDetailsDto> shipmentChargeList = shpEntry.getValue();
                                if(shipmentChargeList != null){
                                    if(ParcelRatingUtil.containsCharge(ParcelAuditConstant.COMMERCIAL_ADJUSTMENT_CHARGE_TYPE, shipmentChargeList)){
                                        callRTRAndPopulateRates(url, licenseKey, previousShipment, RateTo.UPS, msiARChargeCode, shipmentChargeList.get(0), previousShipment, false);
                                    } else if(ParcelRatingUtil.containsCharge(ParcelAuditConstant.RESIDENTIAL_ADJUSTMENT_CHARGE_TYPE, shipmentChargeList)){
                                        //keeping it in separate if condition in order to handle few more scenarios in future.
                                        callRTRAndPopulateRates(url, licenseKey, previousShipment, RateTo.UPS, msiARChargeCode, shipmentChargeList.get(0), previousShipment, false);
                                    } else {
                                        if(previousShipment != null){
                                            List<ParcelAuditDetailsDto> shipmentsToRate = new ArrayList<>(shipmentChargeList);
                                            if(shipmentsToRate != null) {
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
                                                for(ParcelAuditDetailsDto prevShpCharge : previousShipment){
                                                    if(prevShpCharge != null && ParcelAuditConstant.ChargeClassificationCode.ACC.name().equalsIgnoreCase(prevShpCharge.getChargeClassificationCode())) {
                                                        shipmentsToRate.add(prevShpCharge);
                                                    }
                                                    if(!hasFSCCharge && ParcelAuditConstant.ChargeClassificationCode.ACC.name().equalsIgnoreCase(prevShpCharge.getChargeClassificationCode())) {
                                                        shipmentsToRate.add(prevShpCharge);
                                                    }
                                                    if (!hasFrtCharge && !frtChargeManipulated && ParcelAuditConstant.ChargeClassificationCode.FRT.name().equalsIgnoreCase(prevShpCharge.getChargeClassificationCode())) {
                                                        shipmentsToRate.add(prevShpCharge);
                                                    }
                                                }
                                                callRTRAndPopulateRates(url, licenseKey, shipmentsToRate, RateTo.UPS, msiARChargeCode, previousShipment,false);
                                            }
                                        } else {
                                            callRTRAndPopulateRates(url, licenseKey, shipmentChargeList, RateTo.UPS, msiARChargeCode, null, false);
                                        }
                                    }
                                }
                                previousShipment = new ArrayList<>(shipmentChargeList);
                            }
                        }

                        System.out.println("Shipment Count :: " + shipmentCount++ + " :: " + trackingNumber);
                        entryIterator.remove();
                    }catch (Exception e){
                        System.out.println(e.getMessage());
                        //Do nothing
                    }
                }
            }

            for (Map.Entry<String, List<ParcelAuditDetailsDto>> entry : hwtDetailsMap.entrySet()) {

                try {
                    callRTRAndPopulateRates(url, licenseKey, entry.getValue(), RateTo.UPS, msiARChargeCode, null, true);
                } catch (Exception e) {
                    log.error(e.getMessage());
                }

            }
        }
    }

    private String callRTRAndPopulateRates(String url, String licenseKey, List<ParcelAuditDetailsDto> parcelAuditDetails, RateTo rateTo, MsiARChargeCodesDto msiARChargeCode, boolean isHwt) throws Exception {
        return callRTRAndPopulateRates(url, licenseKey, parcelAuditDetails, rateTo, msiARChargeCode, null, null, isHwt);
    }

    private String callRTRAndPopulateRates(String url, String licenseKey, List<ParcelAuditDetailsDto> parcelAuditDetails, RateTo rateTo, MsiARChargeCodesDto msiARChargeCode, List<ParcelAuditDetailsDto> previousShipment, boolean isHwt) throws Exception {
        return callRTRAndPopulateRates(url, licenseKey, parcelAuditDetails, rateTo, msiARChargeCode, null, previousShipment, isHwt);
    }

    private String callRTRAndPopulateRates(String url, String licenseKey, List<ParcelAuditDetailsDto> parcelAuditDetails, RateTo rateTo, MsiARChargeCodesDto msiARChargeCode, ParcelAuditDetailsDto commercialCharge, List<ParcelAuditDetailsDto> previousShipment, boolean isHwt) throws Exception {
        String requestPayload = "";
        String response = "";
        String status = "";
        switch (rateTo){
            case UPS:
                BigDecimal sumOfNetAmount = null;
                if(commercialCharge != null){
                    if(parcelAuditDetails != null && !parcelAuditDetails.isEmpty()){
                        if(ParcelAuditConstant.COMMERCIAL_ADJUSTMENT_CHARGE_TYPE.equalsIgnoreCase(commercialCharge.getChargeDescription())){
                            List<ParcelAuditDetailsDto> commercialShipment = new ArrayList<>();
                            commercialShipment.add(commercialCharge);
                            if(parcelAuditDetails != null){
                                for(ParcelAuditDetailsDto commShipment : parcelAuditDetails){
                                    if(commShipment != null && !"RES".equalsIgnoreCase(commShipment.getChargeDescriptionCode())
                                            && !"RSC".equalsIgnoreCase(commShipment.getChargeDescriptionCode()) && !"FRT".equalsIgnoreCase(commShipment.getChargeClassificationCode())){
                                        commercialShipment.add(commShipment);
                                    }
                                }
                            }

                            requestPayload = ParcelRateRequestBuilder.buildParcelRateRequestForUps(commercialShipment, licenseKey, msiARChargeCode, isHwt).toXmlString();

                            sumOfNetAmount = findSumOfNetAmount(commercialShipment);

                            if (isHwt)
                                parcelAuditDetails = getLeadShipmentDetails(commercialShipment);


                            response = CommonUtil.connectAndGetResponseAsString(url, requestPayload);
                            if(response != null && !response.trim().isEmpty()){
                                saveRequestResponse(requestPayload, response, commercialCharge.getParentId(), ParcelAuditConstant.EBILL_GFF_TABLE_NAME);
                                status = updateRateForUps(ParcelRateResponseParser.parse(response), commercialShipment, msiARChargeCode, commercialCharge, previousShipment, sumOfNetAmount);
                            }
                        } else if(ParcelAuditConstant.RESIDENTIAL_ADJUSTMENT_CHARGE_TYPE.equalsIgnoreCase(commercialCharge.getChargeDescription())){
                            List<ParcelAuditDetailsDto> residentialShipment = new ArrayList<>();
                            residentialShipment.add(commercialCharge);

                            if(parcelAuditDetails != null) {
                                for(ParcelAuditDetailsDto commShipment : parcelAuditDetails){
                                    if(commShipment != null && !"FRT".equalsIgnoreCase(commShipment.getChargeClassificationCode())){
                                        residentialShipment.add(commShipment);
                                    }
                                }
                            }

                            requestPayload = ParcelRateRequestBuilder.buildParcelRateRequestForUps(residentialShipment, licenseKey, msiARChargeCode, isHwt).toXmlString();

                            if (isHwt)
                                parcelAuditDetails = getLeadShipmentDetails(residentialShipment);

                            sumOfNetAmount = findSumOfNetAmount(residentialShipment);

                            response = CommonUtil.connectAndGetResponseAsString(url, requestPayload);
                            if(response != null && !response.trim().isEmpty()){
                                saveRequestResponse(requestPayload, response, commercialCharge.getParentId(), ParcelAuditConstant.EBILL_GFF_TABLE_NAME);
                                status = updateRateForUps(ParcelRateResponseParser.parse(response), residentialShipment, msiARChargeCode, commercialCharge, previousShipment, sumOfNetAmount);
                            }
                        }
                    }
                }else{
                    sumOfNetAmount = findSumOfNetAmount(parcelAuditDetails);

                    requestPayload = ParcelRateRequestBuilder.buildParcelRateRequestForUps(parcelAuditDetails, licenseKey, msiARChargeCode, isHwt).toXmlString();

                    if (isHwt)
                        parcelAuditDetails = getLeadShipmentDetails(parcelAuditDetails);


                    response = CommonUtil.connectAndGetResponseAsString(url, requestPayload);
                    if(response != null && !response.trim().isEmpty()){
                        saveRequestResponse(requestPayload, response, parcelAuditDetails.get(0).getParentId(), ParcelAuditConstant.EBILL_GFF_TABLE_NAME);
                        status = updateRateForUps(ParcelRateResponseParser.parse(response), parcelAuditDetails, msiARChargeCode, previousShipment, sumOfNetAmount);
                    }
                }
                log.debug("sumOfNetAmount=====" + sumOfNetAmount + "==requestPayload=======" + requestPayload);
                log.debug("response=======" + response);
                break;
            case NON_UPS:
                sumOfNetAmount = findSumOfNetAmount(parcelAuditDetails);

                requestPayload = ParcelRateRequestBuilder.buildParcelRateRequestForNonUpsCarrier(parcelAuditDetails, licenseKey, msiARChargeCode, isHwt).toXmlString();

                if (isHwt)
                    parcelAuditDetails = getLeadShipmentDetails(parcelAuditDetails);

                log.debug("sumOfNetAmount=====" + sumOfNetAmount + "==requestPayload=======" + requestPayload);

                response = CommonUtil.connectAndGetResponseAsString(url, requestPayload);

                log.debug("response=======" + response);

                if(response != null && !response.trim().isEmpty()) {
                    saveRequestResponse(requestPayload, response, parcelAuditDetails.get(0).getParentId(), ParcelAuditConstant.EBILL_MANIFEST_TABLE_NAME);
                    status = updateRateForNonUpsCarrier(ParcelRateResponseParser.parse(response), parcelAuditDetails, msiARChargeCode, sumOfNetAmount);
                }
                break;
        }
        return status;
    }

    private void saveRequestResponse(String requestPayload, String response, Long entityId, String tableName) {
        ParcelAuditRequestResponseLog requestResponseLog = new ParcelAuditRequestResponseLog();
        requestResponseLog.setEntityIds(entityId.toString());
        requestResponseLog.setCreateUser(ParcelAuditConstant.PARCEL_RTR_RATING_USER_NAME);
        if ( ParcelAuditConstant.EBILL_GFF_TABLE_NAME.equalsIgnoreCase( tableName) ) {
            requestResponseLog.setCarrierId(21);
        } else if ( ParcelAuditConstant.EBILL_MANIFEST_TABLE_NAME.equalsIgnoreCase( tableName) ) {
            requestResponseLog.setCarrierId(22);
        }
        if(requestPayload != null && !requestPayload.isEmpty()){
            requestResponseLog.setResponseXml( requestPayload);
        }

        if(response != null && !response.isEmpty()){
            requestResponseLog.setResponseXml( response);
        }
        parcelRTRDao.saveParcelAuditRequestAndResponseLog(requestResponseLog);
    }

    private String updateRateForNonUpsCarrier(ParcelRateResponse parcelRateResponse, List<ParcelAuditDetailsDto> parcelAuditDetails, MsiARChargeCodesDto msiARChargeCode, BigDecimal sumOfNetAmount) throws Exception {
        String status = "";
        if(parcelRateResponse != null){
            if(parcelRateResponse.getStatusCode().equals(0)){
                if(parcelRateResponse.getPriceSheets() != null && !parcelRateResponse.getPriceSheets().isEmpty()){
                    //Taking the first price sheet, as per the discussion we finalised that the first price sheet will be be correct and rated based on the latest contract.
                    ParcelRateResponse.PriceSheet firstPriceSheet = parcelRateResponse.getPriceSheets().get(0);

                    BigDecimal totalRateAmount = firstPriceSheet.getTotal().setScale(2, BigDecimal.ROUND_HALF_EVEN);

                    BigDecimal toleranceLowerBound = sumOfNetAmount.multiply(new BigDecimal("0.995"));
                    BigDecimal toleranceUpperBound = sumOfNetAmount.multiply(new BigDecimal("1.005"));

                    if(sumOfNetAmount.compareTo(totalRateAmount) == 0 || (totalRateAmount.compareTo(toleranceLowerBound) >= 0 && totalRateAmount.compareTo(toleranceUpperBound) <= 0)){
                        status = updateAmountWithRTRResponseChargesForNonUpsCarrier(firstPriceSheet, parcelAuditDetails, RTRStatus.CLOSED, msiARChargeCode);
                    } else{
                        if(sumOfNetAmount.compareTo(totalRateAmount) < 0){
                            status = updateAmountWithRTRResponseChargesForNonUpsCarrier(firstPriceSheet, parcelAuditDetails, RTRStatus.UNDER_CHARGED, msiARChargeCode);
                        } else if(sumOfNetAmount.compareTo(totalRateAmount) > 0){
                            status = updateAmountWithRTRResponseChargesForNonUpsCarrier(firstPriceSheet, parcelAuditDetails, RTRStatus.OVER_CHARGED, msiARChargeCode);
                        }
                    }
                }else{
                    updateRTRAmountAndStatus(parcelAuditDetails, RTRStatus.NO_PRICE_SHEET);
                    status = RTRStatus.NO_PRICE_SHEET.value;
                }
            }else{
                updateRTRAmountAndStatus(parcelAuditDetails, RTRStatus.RATING_EXCEPTION);
                status = RTRStatus.RATING_EXCEPTION.value;
            }
        }else{
            updateRTRAmountAndStatus(parcelAuditDetails, RTRStatus.RATING_EXCEPTION);
            status = RTRStatus.RATING_EXCEPTION.value;
        }
        return status;
    }

    /**
     * To update RTR amount/rate for each record in the shipment.
     * If shipment charge type is not found in the response the update the record with Zero amount.
     *
     * @param priceSheet
     * @param parcelAuditDetails
     * @param msiARChargeCode
     * @throws Exception
     */
    private String updateAmountWithRTRResponseChargesForNonUpsCarrier(ParcelRateResponse.PriceSheet priceSheet, List<ParcelAuditDetailsDto> parcelAuditDetails, RTRStatus rtrStatus, MsiARChargeCodesDto msiARChargeCode) throws Exception {
        boolean frtChargeFound = false;
        List<ParcelRateResponse.Charge> mappedAccChanges = new ArrayList<>();
        List<ParcelRateResponse.Charge> mappedDscChanges = new ArrayList<>();
        String shipperCategory = priceSheet.getCategory();
        String contractName = priceSheet.getContractName();
        BigDecimal fuelTablePerc = ParcelRateResponseParser.getFuelTablePercentage(priceSheet);
        BigDecimal ratedGrossFuel = ParcelRateResponseParser.getRatedGrossFuel(priceSheet);
        Map<String, String> dasChargeList = msiARChargeCode.getDasChargeCodes();
        Map<String, String> lpsChargeCodes = msiARChargeCode.getLpsChargeCodes();
        for(ParcelAuditDetailsDto auditDetails : parcelAuditDetails){
            if(auditDetails != null && auditDetails.getChargeClassificationCode() != null && !auditDetails.getChargeClassificationCode().isEmpty()){
                ParcelRateResponse.Charge charge = null;
                if(!frtChargeFound && ParcelAuditConstant.ChargeClassificationCode.FRT.name().equalsIgnoreCase(auditDetails.getChargeClassificationCode())
                        && auditDetails.getNetAmount() != null && !auditDetails.getNetAmount().trim().isEmpty()){
                    double netAmount = Double.parseDouble(auditDetails.getNetAmount());
                    if(netAmount > 0){
                        charge = ParcelRateResponseParser.findChargeByType(ParcelRateResponse.ChargeType.ITEM.name(), priceSheet);
                        if(charge != null){
                            frtChargeFound = true;
                            //parcelRTRDao.updateRTRInvoiceAmount(auditDetails.getId(), ParcelAuditConstant.PARCEL_RTR_RATING_USER_NAME, charge.getAmount(), rtrStatus.value, auditDetails.getCarrierId());

                            ParcelRateDetailsDto rateDetails = ParcelRateDetailsDto.getInstance();
                            rateDetails.setShipperCategory(shipperCategory);
                            rateDetails.setContractName(contractName);
                            rateDetails.setFuelTablePercentage(fuelTablePerc);
                            rateDetails.setRatedBaseDiscount(ParcelRateResponseParser.getSumOfFreightDiscount(priceSheet));
                            rateDetails.setRatedEarnedDiscount(ParcelRateResponseParser.getSpendDiscount(priceSheet));
                            rateDetails.setRatedMinMaxAdjustment(ParcelRateResponseParser.getMinMaxAdjustment(priceSheet));
                            rateDetails.setDimDivisor(charge.getDimDivisor() == null ? new BigDecimal("0") : charge.getDimDivisor());
                            rateDetails.setRatedWeight(charge.getWeight() == null ? new BigDecimal("0") : charge.getWeight());

                            ParcelRateResponse.Charge residentialSurchargeDiscountCharge = ParcelRateResponseParser.getResidentialSurchargeDiscount(priceSheet);
                            if(residentialSurchargeDiscountCharge != null){
                                rateDetails.setResidentialSurchargeDiscount(residentialSurchargeDiscountCharge.getAmount());
                                rateDetails.setResidentialSurchargeDiscountPercentage(residentialSurchargeDiscountCharge.getRate());
                            }
                            rateDetails.setRtrAmount(charge.getAmount());
                            rateDetails.setRtrStatus(rtrStatus.value);
                            parcelRTRDao.updateShipmentRateDetails(ParcelAuditConstant.EBILL_MANIFEST_TABLE_NAME, auditDetails.getId().toString(), ParcelAuditConstant.PARCEL_RTR_RATING_USER_NAME, rateDetails);
                        }
                    }
                }else if(ParcelAuditConstant.ChargeClassificationCode.ACS.name().equalsIgnoreCase(auditDetails.getChargeClassificationCode())
                        && ParcelAuditConstant.ChargeDescriptionCode.FSC.name().equalsIgnoreCase(auditDetails.getChargeDescriptionCode())){
                    charge = ParcelRateResponseParser.findChargeByType(ParcelRateResponse.ChargeType.ACCESSORIAL_FUEL.name(), priceSheet);
                    if(charge != null){
                        //parcelRTRDao.updateRTRInvoiceAmount(auditDetails.getId(), ParcelAuditConstant.PARCEL_RTR_RATING_USER_NAME, charge.getAmount(), rtrStatus.value, auditDetails.getCarrierId());

                        ParcelRateDetailsDto rateDetails = ParcelRateDetailsDto.getInstance();
                        rateDetails.setShipperCategory(shipperCategory);
                        rateDetails.setContractName(contractName);
                        rateDetails.setFuelTablePercentage(fuelTablePerc);
                        rateDetails.setRatedFuelSurchargeDiscount(ParcelRateResponseParser.getRatedSurchargeDiscount(priceSheet));
                        rateDetails.setRatedCustomFuelSurchargeDiscount(ParcelRateResponseParser.getRatedCustomSurchargeDiscount(priceSheet));
                        rateDetails.setRatedGrossFuel(ratedGrossFuel);
                        rateDetails.setDimDivisor(charge.getDimDivisor() == null ? new BigDecimal("0") : charge.getDimDivisor());
                        rateDetails.setRatedWeight(charge.getWeight() == null ? new BigDecimal("0") : charge.getWeight());
                        rateDetails.setRtrAmount(charge.getAmount());
                        rateDetails.setRtrStatus(rtrStatus.value);
                        parcelRTRDao.updateShipmentRateDetails(ParcelAuditConstant.EBILL_MANIFEST_TABLE_NAME, auditDetails.getId().toString(), ParcelAuditConstant.PARCEL_RTR_RATING_USER_NAME, rateDetails);
                    }
                }else if(ParcelAuditConstant.ChargeClassificationCode.ACS.name().equalsIgnoreCase(auditDetails.getChargeClassificationCode())
                        && (ParcelAuditConstant.ChargeDescriptionCode.RES.name().equalsIgnoreCase(auditDetails.getChargeDescriptionCode()) || ParcelAuditConstant.ChargeDescriptionCode.RSC.name().equalsIgnoreCase(auditDetails.getChargeDescriptionCode()))){
                    charge = ParcelRateResponseParser.getResidentialSurcharge(priceSheet);
                    if(charge != null){
                        mappedAccChanges.add(charge);
                        ParcelRateDetailsDto rateDetails = ParcelRateDetailsDto.getInstance();
                        rateDetails.setShipperCategory(shipperCategory);
                        rateDetails.setContractName(contractName);
                        rateDetails.setFuelTablePercentage(fuelTablePerc);
                        rateDetails.setDimDivisor(charge.getDimDivisor() == null ? new BigDecimal("0") : charge.getDimDivisor());
                        rateDetails.setRatedWeight(charge.getWeight() == null ? new BigDecimal("0") : charge.getWeight());
                        rateDetails.setRtrAmount(charge.getAmount());
                        rateDetails.setRtrStatus(rtrStatus.value);
                        //parcelRTRDao.updateRTRInvoiceAmount(auditDetails.getId(), ParcelAuditConstant.PARCEL_RTR_RATING_USER_NAME, charge.getAmount(), rtrStatus.value, auditDetails.getCarrierId());
                        parcelRTRDao.updateShipmentRateDetails(ParcelAuditConstant.EBILL_MANIFEST_TABLE_NAME, auditDetails.getId().toString(), ParcelAuditConstant.PARCEL_RTR_RATING_USER_NAME, rateDetails);
                    }
                }else if(ParcelAuditConstant.ChargeClassificationCode.ACS.name().equalsIgnoreCase(auditDetails.getChargeClassificationCode())
                        && ("DAS".equalsIgnoreCase(auditDetails.getChargeDescriptionCode()) || dasChargeList.containsKey(auditDetails.getChargeDescriptionCode()))){
                    if(auditDetails.getChargeDescription() != null && (auditDetails.getChargeDescription().contains("EXTENDED") || auditDetails.getChargeDescription().contains("extended"))){
                        charge = ParcelRateResponseParser.findChargeByEDICodeInResponse("DSX" ,priceSheet);
                    } else {
                        charge = ParcelRateResponseParser.getDeliveryAreaSurcharge(priceSheet);
                    }

                    if(charge != null){
                        mappedAccChanges.add(charge);
                        ParcelRateDetailsDto rateDetails = ParcelRateDetailsDto.getInstance();
                        rateDetails.setShipperCategory(shipperCategory);
                        rateDetails.setContractName(contractName);
                        if(auditDetails.getChargeDescription() != null && (auditDetails.getChargeDescription().contains("EXTENDED") || auditDetails.getChargeDescription().contains("extended"))){
                            ParcelRateResponse.Charge extendedDasDiscount = ParcelRateResponseParser.getRatedExtendedDasDiscount(priceSheet);
                            if(extendedDasDiscount != null){
                                mappedDscChanges.add(extendedDasDiscount);
                                rateDetails.setDeliveryAreaSurchargeDiscount(extendedDasDiscount.getAmount());
                            }
                        } else {
                            ParcelRateResponse.Charge dasDiscount = ParcelRateResponseParser.getRatedDasDiscount(priceSheet);
                            if(dasDiscount != null){
                                mappedDscChanges.add(dasDiscount);
                                rateDetails.setDeliveryAreaSurchargeDiscount(dasDiscount.getAmount());
                            }
                        }

                        rateDetails.setFuelTablePercentage(fuelTablePerc);
                        rateDetails.setDimDivisor(charge.getDimDivisor() == null ? new BigDecimal("0") : charge.getDimDivisor());
                        rateDetails.setRatedWeight(charge.getWeight() == null ? new BigDecimal("0") : charge.getWeight());

                        rateDetails.setRtrAmount(charge.getAmount());
                        rateDetails.setRtrStatus(rtrStatus.value);
                        //parcelRTRDao.updateRTRInvoiceAmount(auditDetails.getId(), ParcelAuditConstant.PARCEL_RTR_RATING_USER_NAME, charge.getAmount(), rtrStatus.value, auditDetails.getCarrierId());
                        parcelRTRDao.updateShipmentRateDetails(ParcelAuditConstant.EBILL_MANIFEST_TABLE_NAME, auditDetails.getId().toString(), ParcelAuditConstant.PARCEL_RTR_RATING_USER_NAME, rateDetails);
                    }
                } else if(ParcelAuditConstant.ChargeClassificationCode.ACS.name().equalsIgnoreCase(auditDetails.getChargeClassificationCode())
                        && lpsChargeCodes != null && lpsChargeCodes.containsKey(auditDetails.getChargeDescriptionCode())) {
                    charge = ParcelRateResponseParser.getLargePachageSurcharge(priceSheet);
                    if(charge != null) {
                        mappedAccChanges.add(charge);

                        ParcelRateDetailsDto rateDetails = ParcelRateDetailsDto.getInstance();
                        rateDetails.setShipperCategory(shipperCategory);
                        rateDetails.setContractName(contractName);
                        rateDetails.setFuelTablePercentage(fuelTablePerc);
                        rateDetails.setDimDivisor(charge.getDimDivisor() == null ? new BigDecimal("0") : charge.getDimDivisor());
                        rateDetails.setRatedWeight(charge.getWeight() == null ? new BigDecimal("0") : charge.getWeight());
                        rateDetails.setRtrAmount(charge.getAmount());
                        rateDetails.setRtrStatus(rtrStatus.value);
                        //parcelRTRDao.updateRTRInvoiceAmount(auditDetails.getId(), ParcelAuditConstant.PARCEL_RTR_RATING_USER_NAME, charge.getAmount(), rtrStatus.value, auditDetails.getCarrierId());
                        parcelRTRDao.updateShipmentRateDetails(ParcelAuditConstant.EBILL_MANIFEST_TABLE_NAME, auditDetails.getId().toString(), ParcelAuditConstant.PARCEL_RTR_RATING_USER_NAME, rateDetails);
                    }
                } else {
                    //need to clarify this step, whether it is required for NonUPS Carrier or not.
                    charge = ParcelRateResponseParser.findChargeByEDICodeInResponse(auditDetails.getChargeClassificationCode(), priceSheet);
                    if(charge != null){
                        if(charge != null && charge.getType() != null){
                            if(ParcelRateResponse.ChargeType.ACCESSORIAL.name().equalsIgnoreCase(charge.getType())) mappedAccChanges.add(charge);
                            else if(ParcelRateResponse.ChargeType.DISCOUNT.name().equalsIgnoreCase(charge.getType())) mappedDscChanges.add(charge);
                        }

                        ParcelRateDetailsDto rateDetails = ParcelRateDetailsDto.getInstance();
                        rateDetails.setShipperCategory(shipperCategory);
                        rateDetails.setContractName(contractName);
                        rateDetails.setFuelTablePercentage(fuelTablePerc);
                        rateDetails.setDimDivisor(charge.getDimDivisor() == null ? new BigDecimal("0") : charge.getDimDivisor());
                        rateDetails.setRatedWeight(charge.getWeight() == null ? new BigDecimal("0") : charge.getWeight());
                        rateDetails.setRtrAmount(charge.getAmount());
                        rateDetails.setRtrStatus(rtrStatus.value);
                        //parcelRTRDao.updateRTRInvoiceAmount(auditDetails.getId(), ParcelAuditConstant.PARCEL_RTR_RATING_USER_NAME, new BigDecimal("0"), rtrStatus.value, auditDetails.getCarrierId());
                        parcelRTRDao.updateShipmentRateDetails(ParcelAuditConstant.EBILL_MANIFEST_TABLE_NAME, auditDetails.getId().toString(), ParcelAuditConstant.PARCEL_RTR_RATING_USER_NAME, rateDetails);
                    }
                }

                if(charge == null){
                    ParcelRateDetailsDto rateDetails = ParcelRateDetailsDto.getInstance();
                    rateDetails.setShipperCategory(shipperCategory);
                    rateDetails.setContractName(contractName);
                    rateDetails.setFuelTablePercentage(fuelTablePerc);
                    rateDetails.setRtrAmount(new BigDecimal("0"));
                    rateDetails.setRtrStatus(rtrStatus.value);
                    parcelRTRDao.updateShipmentRateDetails(ParcelAuditConstant.EBILL_MANIFEST_TABLE_NAME, auditDetails.getId().toString(), ParcelAuditConstant.PARCEL_RTR_RATING_USER_NAME, rateDetails);
                    //parcelRTRDao.updateRTRInvoiceAmount(auditDetails.getId(), ParcelAuditConstant.PARCEL_RTR_RATING_USER_NAME, new BigDecimal("0"), rtrStatus.value, auditDetails.getCarrierId());
                }
            }
        }

        ParcelRateDetailsDto otherDscRateDetails = ParcelRateDetailsDto.getInstance();
        otherDscRateDetails.setShipperCategory(shipperCategory);
        otherDscRateDetails.setContractName(contractName);
        saveOtherDiscountsAppliedForNonUps(priceSheet, parcelAuditDetails, otherDscRateDetails, mappedDscChanges);

        ParcelRateDetailsDto accessorialRateDetails = ParcelRateDetailsDto.getInstance();
        accessorialRateDetails.setShipperCategory(shipperCategory);
        accessorialRateDetails.setContractName(contractName);
        saveAccessorialForNonUps(priceSheet, parcelAuditDetails, accessorialRateDetails, mappedAccChanges);
        return rtrStatus.value;
    }

    private String updateRateForUps(ParcelRateResponse parcelRateResponse, List<ParcelAuditDetailsDto> parcelAuditDetails, MsiARChargeCodesDto msiARChargeCodes, ParcelAuditDetailsDto commercialAdj, BigDecimal sumOfNetAmount) throws Exception {
        return updateRateForUps(parcelRateResponse, parcelAuditDetails, msiARChargeCodes, commercialAdj, null, sumOfNetAmount);
    }

    private String updateRateForUps(ParcelRateResponse parcelRateResponse, List<ParcelAuditDetailsDto> parcelAuditDetails, MsiARChargeCodesDto msiARChargeCodes, List<ParcelAuditDetailsDto> previousShipment, BigDecimal sumOfNetAmount) throws Exception {
        return updateRateForUps(parcelRateResponse, parcelAuditDetails, msiARChargeCodes, null, previousShipment, sumOfNetAmount);
    }

    private String updateRateForUps(ParcelRateResponse parcelRateResponse, List<ParcelAuditDetailsDto> parcelAuditDetails, MsiARChargeCodesDto msiARChargeCodes, ParcelAuditDetailsDto commercialAdjCharge, List<ParcelAuditDetailsDto> previousShipment, BigDecimal sumOfNetAmount) throws Exception {
        String status = "";
        if(parcelRateResponse != null){
            if(parcelRateResponse.getStatusCode() != null && parcelRateResponse.getStatusCode().equals(0)){
                if(parcelRateResponse.getPriceSheets() != null && !parcelRateResponse.getPriceSheets().isEmpty()){
                    //Taking the first price sheet, as per the discussion we finalised that the first price sheet will be be correct and rated based on the latest contract.
                    ParcelRateResponse.PriceSheet firstPriceSheet = parcelRateResponse.getPriceSheets().get(0);

                    BigDecimal totalRateAmount = firstPriceSheet.getTotal().setScale(2, BigDecimal.ROUND_HALF_EVEN);

                    BigDecimal toleranceLowerBound = sumOfNetAmount.multiply(new BigDecimal("0.995"));
                    BigDecimal toleranceUpperBound = sumOfNetAmount.multiply(new BigDecimal("1.005"));

                    if(sumOfNetAmount.compareTo(totalRateAmount) == 0 || (totalRateAmount.compareTo(toleranceLowerBound) >= 0 && totalRateAmount.compareTo(toleranceUpperBound) <= 0)){
                        if(commercialAdjCharge != null){
                            if(ParcelAuditConstant.COMMERCIAL_ADJUSTMENT_CHARGE_TYPE.equalsIgnoreCase(commercialAdjCharge.getChargeDescription())){
                                status = updateAmountWithRTRResponseChargesForUpsCommercialAdjustment(firstPriceSheet, parcelAuditDetails, RTRStatus.CLOSED, msiARChargeCodes, commercialAdjCharge, previousShipment);
                            } else if(ParcelAuditConstant.RESIDENTIAL_ADJUSTMENT_CHARGE_TYPE.equalsIgnoreCase(commercialAdjCharge.getChargeDescription())){
                                status = updateAmountWithRTRResponseChargesForUpsCommercialAdjustment(firstPriceSheet, parcelAuditDetails, RTRStatus.CLOSED, msiARChargeCodes, commercialAdjCharge, previousShipment);
                            }
                        } else{
                            if(previousShipment != null && !previousShipment.isEmpty()){
                                status = updateAdjustedRateForUps(firstPriceSheet, parcelAuditDetails, RTRStatus.CLOSED, msiARChargeCodes, previousShipment);
                            } else{
                                status = updateAmountWithRTRResponseChargesForUps(firstPriceSheet, parcelAuditDetails, RTRStatus.CLOSED, msiARChargeCodes);
                            }
                        }
                    } else {
                        if(sumOfNetAmount.compareTo(totalRateAmount) < 0){
                            if(commercialAdjCharge != null){
                                if(ParcelAuditConstant.COMMERCIAL_ADJUSTMENT_CHARGE_TYPE.equalsIgnoreCase(commercialAdjCharge.getChargeDescription())){
                                    status = updateAmountWithRTRResponseChargesForUpsCommercialAdjustment(firstPriceSheet, parcelAuditDetails, RTRStatus.UNDER_CHARGED, msiARChargeCodes, commercialAdjCharge, previousShipment);
                                } else if(ParcelAuditConstant.RESIDENTIAL_ADJUSTMENT_CHARGE_TYPE.equalsIgnoreCase(commercialAdjCharge.getChargeDescription())){
                                    status = updateAmountWithRTRResponseChargesForUpsCommercialAdjustment(firstPriceSheet, parcelAuditDetails, RTRStatus.OVER_CHARGED, msiARChargeCodes, commercialAdjCharge, previousShipment);
                                }
                            } else{
                                if(previousShipment != null && !previousShipment.isEmpty()){
                                    status = updateAdjustedRateForUps(firstPriceSheet, parcelAuditDetails, RTRStatus.UNDER_CHARGED, msiARChargeCodes, previousShipment);
                                }else {
                                    status = updateAmountWithRTRResponseChargesForUps(firstPriceSheet, parcelAuditDetails, RTRStatus.UNDER_CHARGED, msiARChargeCodes);
                                }
                            }
                        } else if(sumOfNetAmount.compareTo(totalRateAmount) > 0){
                            if(commercialAdjCharge != null){
                                if(ParcelAuditConstant.COMMERCIAL_ADJUSTMENT_CHARGE_TYPE.equalsIgnoreCase(commercialAdjCharge.getChargeDescription())){
                                    status = updateAmountWithRTRResponseChargesForUpsCommercialAdjustment(firstPriceSheet, parcelAuditDetails, RTRStatus.OVER_CHARGED, msiARChargeCodes, commercialAdjCharge, previousShipment);
                                } else if(ParcelAuditConstant.RESIDENTIAL_ADJUSTMENT_CHARGE_TYPE.equalsIgnoreCase(commercialAdjCharge.getChargeDescription())){
                                    status = updateAmountWithRTRResponseChargesForUpsCommercialAdjustment(firstPriceSheet, parcelAuditDetails, RTRStatus.OVER_CHARGED, msiARChargeCodes, commercialAdjCharge, previousShipment);
                                }
                            } else{
                                if(previousShipment != null && !previousShipment.isEmpty()){
                                    status = updateAdjustedRateForUps(firstPriceSheet, parcelAuditDetails, RTRStatus.OVER_CHARGED, msiARChargeCodes, previousShipment);
                                } else {
                                    status = updateAmountWithRTRResponseChargesForUps(firstPriceSheet, parcelAuditDetails, RTRStatus.OVER_CHARGED, msiARChargeCodes);
                                }
                            }
                        }
                    }
                }else{
                    updateRTRAmountAndStatus(parcelAuditDetails, RTRStatus.NO_PRICE_SHEET);
                    status = RTRStatus.NO_PRICE_SHEET.value;
                }
            }else{
                updateRTRAmountAndStatus(parcelAuditDetails, RTRStatus.RATING_EXCEPTION);
                status = RTRStatus.RATING_EXCEPTION.value;
            }
        }else{
            updateRTRAmountAndStatus(parcelAuditDetails, RTRStatus.RATING_EXCEPTION);
            status = RTRStatus.RATING_EXCEPTION.value;
        }
        return status;
    }

    private String updateAdjustedRateForUps(ParcelRateResponse.PriceSheet priceSheet, List<ParcelAuditDetailsDto> parcelAuditDetails, RTRStatus rtrStatus, MsiARChargeCodesDto msiARChargeCodes, List<ParcelAuditDetailsDto> previousShipment) {
        //List<ParcelAuditDetailsDto> ratedShipmentCharges = null;
        Map<String, String> dasChargeList = msiARChargeCodes.getDasChargeCodes();
        Map<String, String> lpsChargeCodes = msiARChargeCodes.getLpsChargeCodes();
        List<RatedChargeDetailsDto> ratedCharges = null;
        if(previousShipment != null && !previousShipment.isEmpty()){
            //ratedShipmentCharges = loadUpsParcelAuditDetails(previousShipment.get(0).getCustomerId().toString(), previousShipment.get(0).getTrackingNumber(), 1, previousShipment.get(0).getParentId());
            ratedCharges = getRatedChargeAmount(previousShipment.get(0).getParentId());
        }

        boolean frtChargeFound = false;
        List<ParcelRateResponse.Charge> mappedAccChanges = new ArrayList<>();
        List<ParcelRateResponse.Charge> mappedDscChanges = new ArrayList<>();
        String shipperCategory = priceSheet.getCategory();
        String contractName = priceSheet.getContractName();
        BigDecimal fuelTablePerc = ParcelRateResponseParser.getFuelTablePercentage(priceSheet);
        BigDecimal ratedGrossFuel = ParcelRateResponseParser.getRatedGrossFuel(priceSheet);
        for(ParcelAuditDetailsDto auditDetails : parcelAuditDetails){
            if(auditDetails != null && auditDetails.getChargeClassificationCode() != null && !auditDetails.getChargeClassificationCode().isEmpty()){
                ParcelRateResponse.Charge charge = null;
                if(ParcelAuditConstant.ChargeClassificationCode.FRT.name().equalsIgnoreCase(auditDetails.getChargeClassificationCode())){
                    if(!frtChargeFound && auditDetails.getNetAmount() != null && !auditDetails.getNetAmount().trim().isEmpty()){

                        //Need to check with Ashok, for frt amount condition > 0
                        charge = ParcelRateResponseParser.findChargeByType(ParcelRateResponse.ChargeType.ITEM.name(), priceSheet);
                        if(charge != null){
                            ParcelRateDetailsDto rateDetails = ParcelRateDetailsDto.getInstance();
                            rateDetails.setShipperCategory(shipperCategory);
                            rateDetails.setContractName(contractName);
                            rateDetails.setFuelTablePercentage(fuelTablePerc);

                            BigDecimal ratedDiscount = ParcelRateResponseParser.getSumOfFreightDiscount(priceSheet);

                            //Start coding here
                            //BigDecimal prevBaseDiscount = ParcelRatingUtil.getRatedBaseDiscount(ratedCharges);
                            rateDetails.setRatedBaseDiscount(ratedDiscount);
                            rateDetails.setRatedEarnedDiscount(ParcelRateResponseParser.getSpendDiscount(priceSheet));
                            rateDetails.setRatedMinMaxAdjustment(ParcelRateResponseParser.getMinMaxAdjustment(priceSheet));

                            rateDetails.setDimDivisor(charge.getDimDivisor() == null ? new BigDecimal("0") : charge.getDimDivisor());
                            rateDetails.setRatedWeight(charge.getWeight() == null ? new BigDecimal("0") : charge.getWeight());

                            ParcelRateResponse.Charge residentialSurchargeDiscountCharge = ParcelRateResponseParser.getResidentialSurchargeDiscount(priceSheet);
                            if(residentialSurchargeDiscountCharge != null){
                                mappedDscChanges.add(residentialSurchargeDiscountCharge);
                                rateDetails.setResidentialSurchargeDiscount(residentialSurchargeDiscountCharge.getAmount());
                                rateDetails.setResidentialSurchargeDiscountPercentage(residentialSurchargeDiscountCharge.getRate());
                            }

                            BigDecimal frtAmount = new BigDecimal("0");
                            BigDecimal prevRatedFrtAmt = new BigDecimal("0");
                            if(previousShipment != null && (ParcelRatingUtil.containsCharge(ParcelAuditConstant.COMMERCIAL_ADJUSTMENT_CHARGE_TYPE, previousShipment) || ParcelRatingUtil.containsCharge(ParcelAuditConstant.RESIDENTIAL_ADJUSTMENT_CHARGE_TYPE, previousShipment))){
                                prevRatedFrtAmt = ParcelRatingUtil.getRatedFreightChargeForCommOrResAjustment(ratedCharges);
                            }else{
                                prevRatedFrtAmt = ParcelRatingUtil.findRtrAmountByChargeClassificationCode("FRT", ratedCharges, auditDetails.getId());
                            }

                            if(charge.getAmount() != null){
                                if(prevRatedFrtAmt != null){
                                    frtAmount = charge.getAmount().subtract(prevRatedFrtAmt);
                                }else{
                                    frtAmount = charge.getAmount();
                                }
                            }
                            rateDetails.setRtrAmount(frtAmount);
                            rateDetails.setRtrStatus(rtrStatus.value);
                            //parcelRTRDao.updateRTRInvoiceAmount(auditDetails.getId(), ParcelAuditConstant.PARCEL_RTR_RATING_USER_NAME, frtAmount, rtrStatus.value, auditDetails.getCarrierId());
                            parcelRTRDao.updateShipmentRateDetails(ParcelAuditConstant.EBILL_GFF_TABLE_NAME, auditDetails.getId().toString(), ParcelAuditConstant.PARCEL_RTR_RATING_USER_NAME, rateDetails);

                            frtChargeFound = true;
                        }else{
                            ParcelRateDetailsDto rateDetails = ParcelRateDetailsDto.getInstance();
                            rateDetails.setShipperCategory(shipperCategory);
                            rateDetails.setContractName(contractName);
                            rateDetails.setFuelTablePercentage(fuelTablePerc);
                            rateDetails.setRtrAmount(new BigDecimal("0"));
                            rateDetails.setRtrStatus(rtrStatus.value);
                            //parcelRTRDao.updateRTRInvoiceAmount(auditDetails.getId(), ParcelAuditConstant.PARCEL_RTR_RATING_USER_NAME, new BigDecimal("0"), rtrStatus.value, auditDetails.getCarrierId());
                            parcelRTRDao.updateShipmentRateDetails(ParcelAuditConstant.EBILL_GFF_TABLE_NAME, auditDetails.getId().toString(), ParcelAuditConstant.PARCEL_RTR_RATING_USER_NAME, rateDetails);
                        }
                    }else{
                        ParcelRateDetailsDto rateDetails = ParcelRateDetailsDto.getInstance();
                        rateDetails.setShipperCategory(shipperCategory);
                        rateDetails.setContractName(contractName);
                        rateDetails.setFuelTablePercentage(fuelTablePerc);
                        rateDetails.setRtrAmount(new BigDecimal("0"));
                        rateDetails.setRtrStatus(rtrStatus.value);
                        //parcelRTRDao.updateRTRInvoiceAmount(auditDetails.getId(), ParcelAuditConstant.PARCEL_RTR_RATING_USER_NAME, new BigDecimal("0"), rtrStatus.value, auditDetails.getCarrierId());
                        parcelRTRDao.updateShipmentRateDetails(ParcelAuditConstant.EBILL_GFF_TABLE_NAME, auditDetails.getId().toString(), ParcelAuditConstant.PARCEL_RTR_RATING_USER_NAME, rateDetails);
                    }
                }else if(ParcelAuditConstant.ChargeClassificationCode.FSC.name().equalsIgnoreCase(auditDetails.getChargeClassificationCode())){
                    charge = ParcelRateResponseParser.findChargeByType(ParcelRateResponse.ChargeType.ACCESSORIAL_FUEL.name(), priceSheet);
                    if(charge != null){
                        ParcelRateDetailsDto rateDetails = ParcelRateDetailsDto.getInstance();
                        rateDetails.setShipperCategory(shipperCategory);
                        rateDetails.setContractName(contractName);
                        rateDetails.setFuelTablePercentage(fuelTablePerc);
                        rateDetails.setRatedFuelSurchargeDiscount(ParcelRateResponseParser.getRatedSurchargeDiscount(priceSheet));
                        rateDetails.setRatedCustomFuelSurchargeDiscount(ParcelRateResponseParser.getRatedCustomSurchargeDiscount(priceSheet));
                        rateDetails.setRatedGrossFuel(ratedGrossFuel);
                        rateDetails.setDimDivisor(charge.getDimDivisor() == null ? new BigDecimal("0") : charge.getDimDivisor());
                        rateDetails.setRatedWeight(charge.getWeight() == null ? new BigDecimal("0") : charge.getWeight());

                        BigDecimal fscAmount = new BigDecimal("0");
                        BigDecimal prevRatedFscAmt = new BigDecimal("0");
                        if(previousShipment != null && (ParcelRatingUtil.containsCharge(ParcelAuditConstant.COMMERCIAL_ADJUSTMENT_CHARGE_TYPE, previousShipment) || ParcelRatingUtil.containsCharge(ParcelAuditConstant.RESIDENTIAL_ADJUSTMENT_CHARGE_TYPE, previousShipment))){
                            prevRatedFscAmt = ParcelRatingUtil.getRatedFuelChargeForCommOrResAjustment(ratedCharges);
                        } else{
                            prevRatedFscAmt = ParcelRatingUtil.findRtrAmountByChargeClassificationCode("FSC", ratedCharges, auditDetails.getId());
                        }

                        if(charge.getAmount() != null){
                            if(prevRatedFscAmt != null){
                                fscAmount = charge.getAmount().subtract(prevRatedFscAmt);
                            }else{
                                fscAmount = charge.getAmount();
                            }

                        }
                        rateDetails.setRtrAmount(fscAmount);
                        rateDetails.setRtrStatus(rtrStatus.value);
                        //parcelRTRDao.updateRTRInvoiceAmount(auditDetails.getId(), ParcelAuditConstant.PARCEL_RTR_RATING_USER_NAME, fscAmount, rtrStatus.value, auditDetails.getCarrierId());
                        parcelRTRDao.updateShipmentRateDetails(ParcelAuditConstant.EBILL_GFF_TABLE_NAME, auditDetails.getId().toString(), ParcelAuditConstant.PARCEL_RTR_RATING_USER_NAME, rateDetails);
                    }
                } else if(ParcelAuditConstant.ChargeClassificationCode.ACC.name().equalsIgnoreCase(auditDetails.getChargeClassificationCode())
                        && (ParcelAuditConstant.ChargeDescriptionCode.RES.name().equalsIgnoreCase(auditDetails.getChargeDescriptionCode()) || ParcelAuditConstant.ChargeDescriptionCode.RSC.name().equalsIgnoreCase(auditDetails.getChargeDescriptionCode()))){
                    charge = ParcelRateResponseParser.getResidentialSurcharge(priceSheet);
                    if(charge != null){
                        mappedAccChanges.add(charge);
                        ParcelRateDetailsDto rateDetails = ParcelRateDetailsDto.getInstance();
                        rateDetails.setShipperCategory(shipperCategory);
                        rateDetails.setContractName(contractName);
                        rateDetails.setDimDivisor(charge.getDimDivisor() == null ? new BigDecimal("0") : charge.getDimDivisor());
                        rateDetails.setRatedWeight(charge.getWeight() == null ? new BigDecimal("0") : charge.getWeight());

                        BigDecimal resAmount = new BigDecimal("0");
                        BigDecimal prevRatedResAmt = ParcelRatingUtil.findRtrAmountByChargeClassificationCodeAndChargeDescriptionCode("ACC", "RES", ratedCharges, auditDetails.getId());
                        if(prevRatedResAmt == null){
                            prevRatedResAmt = ParcelRatingUtil.findRtrAmountByChargeClassificationCodeAndChargeDescriptionCode("ACC", "RSC", ratedCharges, auditDetails.getId());
                        }
                        if(charge.getAmount() != null){
                            if(prevRatedResAmt != null){
                                resAmount = charge.getAmount().subtract(prevRatedResAmt);
                            }else{
                                resAmount = charge.getAmount();
                            }

                        }
                        rateDetails.setRtrAmount(resAmount);
                        rateDetails.setRtrStatus(rtrStatus.value);
                        //parcelRTRDao.updateRTRInvoiceAmount(auditDetails.getId(), ParcelAuditConstant.PARCEL_RTR_RATING_USER_NAME, resAmount, rtrStatus.value, auditDetails.getCarrierId());
                        parcelRTRDao.updateShipmentRateDetails(ParcelAuditConstant.EBILL_GFF_TABLE_NAME, auditDetails.getId().toString(), ParcelAuditConstant.PARCEL_RTR_RATING_USER_NAME, rateDetails);
                    }
                }else if(ParcelAuditConstant.ChargeClassificationCode.ACC.name().equalsIgnoreCase(auditDetails.getChargeClassificationCode())
                        && ("DAS".equalsIgnoreCase(auditDetails.getChargeDescriptionCode()) || dasChargeList.containsKey(auditDetails.getChargeDescriptionCode()))){
                    charge = ParcelRateResponseParser.getDeliveryAreaSurcharge(priceSheet);
                    if(charge != null){
                        mappedAccChanges.add(charge);
                        ParcelRateDetailsDto rateDetails = ParcelRateDetailsDto.getInstance();
                        rateDetails.setShipperCategory(shipperCategory);
                        rateDetails.setContractName(contractName);
                        ParcelRateResponse.Charge dasDiscount = ParcelRateResponseParser.getRatedDasDiscount(priceSheet);
                        if(dasDiscount != null){
                            mappedDscChanges.add(dasDiscount);
                            rateDetails.setDeliveryAreaSurchargeDiscount(dasDiscount.getAmount());
                        }
                        rateDetails.setDimDivisor(charge.getDimDivisor() == null ? new BigDecimal("0") : charge.getDimDivisor());
                        rateDetails.setRatedWeight(charge.getWeight() == null ? new BigDecimal("0") : charge.getWeight());

                        BigDecimal dasAmount = new BigDecimal("0");
                        BigDecimal prevRatedDasAmt = ParcelRatingUtil.findRtrAmountByChargeClassificationCodeAndChargeDescriptionCode("ACC", auditDetails.getChargeDescriptionCode(), ratedCharges, auditDetails.getId());
                        if(charge.getAmount() != null){
                            if(prevRatedDasAmt != null){
                                dasAmount = charge.getAmount().subtract(prevRatedDasAmt);
                            }else{
                                dasAmount = charge.getAmount();
                            }

                        }
                        rateDetails.setRtrAmount(dasAmount);
                        rateDetails.setRtrStatus(rtrStatus.value);
                        //parcelRTRDao.updateRTRInvoiceAmount(auditDetails.getId(), ParcelAuditConstant.PARCEL_RTR_RATING_USER_NAME, dasAmount, rtrStatus.value, auditDetails.getCarrierId());
                        parcelRTRDao.updateShipmentRateDetails(ParcelAuditConstant.EBILL_GFF_TABLE_NAME, auditDetails.getId().toString(), ParcelAuditConstant.PARCEL_RTR_RATING_USER_NAME, rateDetails);
                    }
                } else if(ParcelAuditConstant.ChargeClassificationCode.ACC.name().equalsIgnoreCase(auditDetails.getChargeClassificationCode())
                        && lpsChargeCodes != null && lpsChargeCodes.containsKey(auditDetails.getChargeDescriptionCode())) {
                    charge = ParcelRateResponseParser.getLargePachageSurcharge(priceSheet);
                    if(charge != null) {
                        mappedAccChanges.add(charge);

                        ParcelRateDetailsDto rateDetails = ParcelRateDetailsDto.getInstance();
                        rateDetails.setShipperCategory(shipperCategory);
                        rateDetails.setContractName(contractName);
                        rateDetails.setFuelTablePercentage(fuelTablePerc);
                        rateDetails.setDimDivisor(charge.getDimDivisor() == null ? new BigDecimal("0") : charge.getDimDivisor());
                        rateDetails.setRatedWeight(charge.getWeight() == null ? new BigDecimal("0") : charge.getWeight());
                        rateDetails.setRtrAmount(charge.getAmount());
                        rateDetails.setRtrStatus(rtrStatus.value);
                        //parcelRTRDao.updateRTRInvoiceAmount(auditDetails.getId(), ParcelAuditConstant.PARCEL_RTR_RATING_USER_NAME, charge.getAmount(), rtrStatus.value, auditDetails.getCarrierId());
                        parcelRTRDao.updateShipmentRateDetails(ParcelAuditConstant.EBILL_GFF_TABLE_NAME, auditDetails.getId().toString(), ParcelAuditConstant.PARCEL_RTR_RATING_USER_NAME, rateDetails);
                    }
                } else{
                    charge = ParcelRateResponseParser.findChargeByEDICodeInResponse(auditDetails.getChargeClassificationCode(), priceSheet);
                    if(charge != null){
                        if(charge != null && charge.getType() != null){
                            if(ParcelRateResponse.ChargeType.ACCESSORIAL.name().equalsIgnoreCase(charge.getType())) mappedAccChanges.add(charge);
                            else if(ParcelRateResponse.ChargeType.DISCOUNT.name().equalsIgnoreCase(charge.getType())) mappedDscChanges.add(charge);
                        }
                        ParcelRateDetailsDto rateDetails = ParcelRateDetailsDto.getInstance();
                        rateDetails.setShipperCategory(shipperCategory);
                        rateDetails.setContractName(contractName);
                        rateDetails.setFuelTablePercentage(fuelTablePerc);
                        rateDetails.setDimDivisor(charge.getDimDivisor() == null ? new BigDecimal("0") : charge.getDimDivisor());
                        rateDetails.setRatedWeight(charge.getWeight() == null ? new BigDecimal("0") : charge.getWeight());

                        if(ParcelRateResponse.ChargeType.ACCESSORIAL.name().equalsIgnoreCase(charge.getType())){
                            BigDecimal otherAcc = new BigDecimal("0");
                            BigDecimal prevRatedAccAmt = ParcelRatingUtil.findRtrAmountByChargeClassificationCodeAndChargeDescriptionCode("ACC", auditDetails.getChargeDescriptionCode(), ratedCharges, auditDetails.getId());
                            if(charge.getAmount() != null){
                                if(prevRatedAccAmt != null){
                                    otherAcc = charge.getAmount().subtract(prevRatedAccAmt);
                                }else{
                                    otherAcc = charge.getAmount();
                                }

                            }
                            rateDetails.setRtrAmount(otherAcc);
                            rateDetails.setRtrStatus(rtrStatus.value);
                            //parcelRTRDao.updateRTRInvoiceAmount(auditDetails.getId(), ParcelAuditConstant.PARCEL_RTR_RATING_USER_NAME, otherAcc, rtrStatus.value, auditDetails.getCarrierId());
                            parcelRTRDao.updateShipmentRateDetails(ParcelAuditConstant.EBILL_GFF_TABLE_NAME, auditDetails.getId().toString(), ParcelAuditConstant.PARCEL_RTR_RATING_USER_NAME, rateDetails);
                        } else {
                            rateDetails.setRtrAmount(charge.getAmount());
                            rateDetails.setRtrStatus(rtrStatus.value);
                            //parcelRTRDao.updateRTRInvoiceAmount(auditDetails.getId(), ParcelAuditConstant.PARCEL_RTR_RATING_USER_NAME, charge.getAmount(), rtrStatus.value, auditDetails.getCarrierId());
                            parcelRTRDao.updateShipmentRateDetails(ParcelAuditConstant.EBILL_GFF_TABLE_NAME, auditDetails.getId().toString(), ParcelAuditConstant.PARCEL_RTR_RATING_USER_NAME, rateDetails);
                        }
                    }
                }

                if(null == charge){
                    ParcelRateDetailsDto rateDetails = ParcelRateDetailsDto.getInstance();
                    rateDetails.setShipperCategory(shipperCategory);
                    rateDetails.setContractName(contractName);
                    rateDetails.setFuelTablePercentage(fuelTablePerc);
                    rateDetails.setRtrAmount(new BigDecimal("0"));
                    rateDetails.setRtrStatus(rtrStatus.value);
                    //parcelRTRDao.updateRTRInvoiceAmount(auditDetails.getId(), ParcelAuditConstant.PARCEL_RTR_RATING_USER_NAME, new BigDecimal("0"), rtrStatus.value, auditDetails.getCarrierId());
                    parcelRTRDao.updateShipmentRateDetails(ParcelAuditConstant.EBILL_GFF_TABLE_NAME, auditDetails.getId().toString(), ParcelAuditConstant.PARCEL_RTR_RATING_USER_NAME, rateDetails);
                }
            }
        }

        ParcelRateDetailsDto otherDscRateDetails = ParcelRateDetailsDto.getInstance();
        otherDscRateDetails.setShipperCategory(shipperCategory);
        otherDscRateDetails.setContractName(contractName);
        saveOtherDiscountsAppliedForUps(priceSheet, parcelAuditDetails, otherDscRateDetails, mappedDscChanges);

        ParcelRateDetailsDto accessorialRateDetails = ParcelRateDetailsDto.getInstance();
        accessorialRateDetails.setShipperCategory(shipperCategory);
        accessorialRateDetails.setContractName(contractName);
        saveAccessorialForUps(priceSheet, parcelAuditDetails, accessorialRateDetails, mappedAccChanges, ratedCharges);
        return rtrStatus.value;
    }

    private String updateAmountWithRTRResponseChargesForUpsCommercialAdjustment(ParcelRateResponse.PriceSheet priceSheet, List<ParcelAuditDetailsDto> parcelAuditDetails, RTRStatus rtrStatus, MsiARChargeCodesDto msiARChargeCodes, ParcelAuditDetailsDto commercialAdjCharge, List<ParcelAuditDetailsDto> previousShipment) {
        boolean frtChargeFound = false;
        Map<String, String> dasChargeList = msiARChargeCodes.getDasChargeCodes();
        Map<String, String> lpsChargeCodes = msiARChargeCodes.getLpsChargeCodes();
        boolean accessorial1Mapped = false;
        boolean accessorial2Mapped = false;
        boolean accessorial3Mapped = false;
        boolean accessorial4Mapped = false;
        List<ParcelRateResponse.Charge> mappedAccChanges = new ArrayList<>();
        ParcelRateDetailsDto rateDetails = ParcelRateDetailsDto.getInstance();
        rateDetails.setContractName(priceSheet.getContractName());
        rateDetails.setShipperCategory(priceSheet.getCategory());
        BigDecimal totalRatedAmount = new BigDecimal("0");
        for(ParcelAuditDetailsDto auditDetails : parcelAuditDetails){
            if(auditDetails != null && auditDetails.getChargeClassificationCode() != null && !auditDetails.getChargeClassificationCode().isEmpty()){
                ParcelRateResponse.Charge charge = null;
                if(ParcelAuditConstant.ChargeClassificationCode.FRT.name().equalsIgnoreCase(auditDetails.getChargeClassificationCode())){
                    if(!frtChargeFound && auditDetails.getNetAmount() != null && !auditDetails.getNetAmount().trim().isEmpty()){
                        //double netAmount = Double.parseDouble(auditDetails.getNetAmount());

                        charge = ParcelRateResponseParser.findChargeByType(ParcelRateResponse.ChargeType.ITEM.name(), priceSheet);
                        if(charge != null){
                            rateDetails.setFreightCharge(charge.getAmount());
                            rateDetails.setRatedBaseDiscount(ParcelRateResponseParser.getSumOfFreightDiscount(priceSheet));
                            rateDetails.setRatedEarnedDiscount(ParcelRateResponseParser.getSpendDiscount(priceSheet));
                            rateDetails.setRatedMinMaxAdjustment(ParcelRateResponseParser.getMinMaxAdjustment(priceSheet));

                            if(rateDetails.getFreightCharge() != null) totalRatedAmount = totalRatedAmount.add(rateDetails.getFreightCharge());
                            if(rateDetails.getRatedBaseDiscount() != null) totalRatedAmount = totalRatedAmount.add(rateDetails.getRatedBaseDiscount());

                            ParcelRateResponse.Charge residentialSurchargeDiscountCharge = ParcelRateResponseParser.getResidentialSurchargeDiscount(priceSheet);
                            if(residentialSurchargeDiscountCharge != null){
                                rateDetails.setResidentialSurchargeDiscount(residentialSurchargeDiscountCharge.getAmount());
                                rateDetails.setResidentialSurchargeDiscountPercentage(residentialSurchargeDiscountCharge.getRate());
                            }
                        }
                        frtChargeFound = true;

                    }
                }else if(ParcelAuditConstant.ChargeClassificationCode.FSC.name().equalsIgnoreCase(auditDetails.getChargeClassificationCode())){
                    charge = ParcelRateResponseParser.findChargeByType(ParcelRateResponse.ChargeType.ACCESSORIAL_FUEL.name(), priceSheet);
                    if(charge != null){
                        rateDetails.setFuelSurcharge(charge.getAmount());
                        if(rateDetails.getFuelSurcharge() != null) totalRatedAmount = totalRatedAmount.add(rateDetails.getFuelSurcharge());
                        rateDetails.setRatedFuelSurchargeDiscount(ParcelRateResponseParser.getRatedSurchargeDiscount(priceSheet));
                        rateDetails.setRatedCustomFuelSurchargeDiscount(ParcelRateResponseParser.getRatedCustomSurchargeDiscount(priceSheet));
                    }
                } else if(ParcelAuditConstant.ChargeClassificationCode.ACC.name().equalsIgnoreCase(auditDetails.getChargeClassificationCode())
                        && (ParcelAuditConstant.ChargeDescriptionCode.RES.name().equalsIgnoreCase(auditDetails.getChargeDescriptionCode()) || ParcelAuditConstant.ChargeDescriptionCode.RSC.name().equalsIgnoreCase(auditDetails.getChargeDescriptionCode()))){
                    charge = ParcelRateResponseParser.getResidentialSurcharge(priceSheet);
                    if(charge != null){
                        mappedAccChanges.add(charge);
                        accessorial1Mapped = true;
                        rateDetails.setAccessorial1(charge.getAmount());
                        rateDetails.setAccessorial1Code("RES");

                        if(rateDetails.getAccessorial1() != null) totalRatedAmount = totalRatedAmount.add(rateDetails.getAccessorial1());
                    }
                }else if(ParcelAuditConstant.ChargeClassificationCode.ACC.name().equalsIgnoreCase(auditDetails.getChargeClassificationCode())
                        && ("DAS".equalsIgnoreCase(auditDetails.getChargeDescriptionCode()) || dasChargeList.containsKey(auditDetails.getChargeDescriptionCode()))){
                    charge = ParcelRateResponseParser.getDeliveryAreaSurcharge(priceSheet);
                    if(charge != null){
                        mappedAccChanges.add(charge);
                        accessorial2Mapped = true;
                        rateDetails.setAccessorial2(charge.getAmount());
                        rateDetails.setAccessorial2Code("DAS");

                        if(rateDetails.getAccessorial2() != null) totalRatedAmount = totalRatedAmount.add(rateDetails.getAccessorial2());
                    }
                } else if(ParcelAuditConstant.ChargeClassificationCode.ACC.name().equalsIgnoreCase(auditDetails.getChargeClassificationCode())
                        && lpsChargeCodes != null && lpsChargeCodes.containsKey(auditDetails.getChargeDescriptionCode())) {
                    charge = ParcelRateResponseParser.getLargePachageSurcharge(priceSheet);
                    if(charge != null){
                        mappedAccChanges.add(charge);
                        accessorial3Mapped = true;
                        rateDetails.setAccessorial3(charge.getAmount());
                        rateDetails.setAccessorial3Code(auditDetails.getChargeDescriptionCode());

                        if(rateDetails.getAccessorial3() != null) totalRatedAmount = totalRatedAmount.add(rateDetails.getAccessorial3());
                    }
                }
            }
        }

        try{
            if(priceSheet != null){
                List<ParcelRateResponse.Charge> accessorialCharges = ParcelRateResponseParser.getAccessorialCharges(priceSheet);

                if(accessorialCharges != null && !accessorialCharges.isEmpty()){
                    if(mappedAccChanges != null && !mappedAccChanges.isEmpty()){
                        Iterator<ParcelRateResponse.Charge> chargeIterator = accessorialCharges.iterator();
                        while(chargeIterator.hasNext()){
                            ParcelRateResponse.Charge tempCharge = chargeIterator.next();
                            for(ParcelRateResponse.Charge mappedChrg : mappedAccChanges){
                                if(mappedChrg != null && tempCharge != null
                                        && mappedChrg.getType() != null && tempCharge.getType() != null
                                        && mappedChrg.getType().equalsIgnoreCase(tempCharge.getType())
                                        && mappedChrg.getName() != null && tempCharge.getName() != null
                                        && mappedChrg.getName().equalsIgnoreCase(tempCharge.getName())) {
                                    chargeIterator.remove();
                                }
                            }
                        }
                    }

                    for(ParcelRateResponse.Charge accCharge : accessorialCharges) {
                        if(accCharge !=null){
                            if(!accessorial1Mapped) {
                                accessorial1Mapped = true;
                                rateDetails.setAccessorial1(accCharge.getAmount());
                                rateDetails.setAccessorial1Code(accCharge.getEdiCode());

                                if(rateDetails.getAccessorial1() != null) totalRatedAmount = totalRatedAmount.add(rateDetails.getAccessorial1());
                            } else if(!accessorial2Mapped){
                                accessorial2Mapped = true;
                                rateDetails.setAccessorial2(accCharge.getAmount());
                                rateDetails.setAccessorial2Code(accCharge.getEdiCode());

                                if(rateDetails.getAccessorial2() != null) totalRatedAmount = totalRatedAmount.add(rateDetails.getAccessorial2());
                            } else if(!accessorial3Mapped){
                                accessorial3Mapped = true;
                                rateDetails.setAccessorial3(accCharge.getAmount());
                                rateDetails.setAccessorial3Code(accCharge.getEdiCode());

                                if(rateDetails.getAccessorial3() != null) totalRatedAmount = totalRatedAmount.add(rateDetails.getAccessorial3());
                            } else if(!accessorial4Mapped) {
                                accessorial4Mapped = true;
                                rateDetails.setAccessorial4(accCharge.getAmount());
                                rateDetails.setAccessorial4Code(accCharge.getEdiCode());

                                if(rateDetails.getAccessorial4() != null) totalRatedAmount = totalRatedAmount.add(rateDetails.getAccessorial4());
                            }
                        }
                    }
                }
            }
        }catch (Exception e){}

        BigDecimal prevTotalRated = previousShipment != null && !previousShipment.isEmpty() ? getRatedTotalAmount(previousShipment.get(0).getParentId()) : new BigDecimal("0");
        rateDetails.setRtrAmount(totalRatedAmount.subtract(prevTotalRated));
        rateDetails.setRtrStatus(rtrStatus.value);
        //parcelRTRDao.updateRTRInvoiceAmount(commercialAdjCharge.getId(), ParcelAuditConstant.PARCEL_RTR_RATING_USER_NAME, (totalRatedAmount.subtract(prevTotalRated)) , rtrStatus.value, commercialAdjCharge.getCarrierId());
        parcelRTRDao.updateAllShipmentRateDetails(ParcelAuditConstant.EBILL_GFF_TABLE_NAME, commercialAdjCharge.getId().toString(), ParcelAuditConstant.PARCEL_RTR_RATING_USER_NAME, rateDetails);

        return rtrStatus.value;
    }

    private String updateAmountWithRTRResponseChargesForUps(ParcelRateResponse.PriceSheet priceSheet, List<ParcelAuditDetailsDto> parcelAuditDetails, RTRStatus rtrStatus, MsiARChargeCodesDto msiARChargeCodes) throws Exception {
        return updateAmountWithRTRResponseChargesForUps(priceSheet, parcelAuditDetails, rtrStatus, msiARChargeCodes, null);
    }

    private String updateAmountWithRTRResponseChargesForUps(ParcelRateResponse.PriceSheet priceSheet, List<ParcelAuditDetailsDto> parcelAuditDetails, RTRStatus rtrStatus, MsiARChargeCodesDto msiARChargeCodes, List<ParcelAuditDetailsDto> previousShipment) throws Exception {
        boolean frtChargeFound = false;
        Map<String, String> dasChargeList = msiARChargeCodes.getDasChargeCodes();
        Map<String, String> lpsChargeCodes = msiARChargeCodes.getLpsChargeCodes();
        List<ParcelRateResponse.Charge> mappedAccChanges = new ArrayList<>();
        List<ParcelRateResponse.Charge> mappedDscChanges = new ArrayList<>();
        String shipperCategory = priceSheet.getCategory();
        String contractName = priceSheet.getContractName();
        BigDecimal fuelTablePerc = ParcelRateResponseParser.getFuelTablePercentage(priceSheet);
        BigDecimal ratedGrossFuel = ParcelRateResponseParser.getRatedGrossFuel(priceSheet);
        for(ParcelAuditDetailsDto auditDetails : parcelAuditDetails){
            if(auditDetails != null && auditDetails.getChargeClassificationCode() != null && !auditDetails.getChargeClassificationCode().isEmpty()){
                ParcelRateResponse.Charge charge = null;
                if(ParcelAuditConstant.ChargeClassificationCode.FRT.name().equalsIgnoreCase(auditDetails.getChargeClassificationCode())){
                    if(!frtChargeFound && auditDetails.getNetAmount() != null && !auditDetails.getNetAmount().trim().isEmpty()){
                        double netAmount = Double.parseDouble(auditDetails.getNetAmount());
                        if(netAmount > 0){
                            charge = ParcelRateResponseParser.findChargeByType(ParcelRateResponse.ChargeType.ITEM.name(), priceSheet);
                            if(charge != null){
                                ParcelRateDetailsDto rateDetails = ParcelRateDetailsDto.getInstance();
                                rateDetails.setShipperCategory(shipperCategory);
                                rateDetails.setContractName(contractName);
                                rateDetails.setFuelTablePercentage(fuelTablePerc);
                                rateDetails.setRatedBaseDiscount(ParcelRateResponseParser.getSumOfFreightDiscount(priceSheet));
                                rateDetails.setRatedEarnedDiscount(ParcelRateResponseParser.getSpendDiscount(priceSheet));
                                rateDetails.setRatedMinMaxAdjustment(ParcelRateResponseParser.getMinMaxAdjustment(priceSheet));
                                rateDetails.setDimDivisor(charge.getDimDivisor() == null ? new BigDecimal("0") : charge.getDimDivisor());
                                rateDetails.setRatedWeight(charge.getWeight() == null ? new BigDecimal("0") : charge.getWeight());

                                ParcelRateResponse.Charge residentialSurchargeDiscountCharge = ParcelRateResponseParser.getResidentialSurchargeDiscount(priceSheet);
                                if(residentialSurchargeDiscountCharge != null){
                                    rateDetails.setResidentialSurchargeDiscount(residentialSurchargeDiscountCharge.getAmount());
                                    rateDetails.setResidentialSurchargeDiscountPercentage(residentialSurchargeDiscountCharge.getRate());
                                }

                                rateDetails.setRtrAmount(charge.getAmount());
                                rateDetails.setRtrStatus(rtrStatus.value);
                                parcelRTRDao.updateShipmentRateDetails(ParcelAuditConstant.EBILL_GFF_TABLE_NAME, auditDetails.getId().toString(), ParcelAuditConstant.PARCEL_RTR_RATING_USER_NAME, rateDetails);
                                //parcelRTRDao.updateRTRInvoiceAmount(auditDetails.getId(), ParcelAuditConstant.PARCEL_RTR_RATING_USER_NAME, charge.getAmount(), rtrStatus.value, auditDetails.getCarrierId());
                            }
                            frtChargeFound = true;
                        }else{
                            ParcelRateDetailsDto rateDetails = ParcelRateDetailsDto.getInstance();
                            rateDetails.setShipperCategory(shipperCategory);
                            rateDetails.setContractName(contractName);
                            rateDetails.setFuelTablePercentage(fuelTablePerc);
                            rateDetails.setRtrAmount(new BigDecimal("0"));
                            rateDetails.setRtrStatus(rtrStatus.value);
                            //parcelRTRDao.updateRTRInvoiceAmount(auditDetails.getId(), ParcelAuditConstant.PARCEL_RTR_RATING_USER_NAME, new BigDecimal("0"), rtrStatus.value, auditDetails.getCarrierId());
                            parcelRTRDao.updateShipmentRateDetails(ParcelAuditConstant.EBILL_GFF_TABLE_NAME, auditDetails.getId().toString(), ParcelAuditConstant.PARCEL_RTR_RATING_USER_NAME, rateDetails);
                        }
                    }else{
                        ParcelRateDetailsDto rateDetails = ParcelRateDetailsDto.getInstance();
                        rateDetails.setShipperCategory(shipperCategory);
                        rateDetails.setContractName(contractName);
                        rateDetails.setFuelTablePercentage(fuelTablePerc);
                        rateDetails.setRtrAmount(new BigDecimal("0"));
                        rateDetails.setRtrStatus(rtrStatus.value);
                        //parcelRTRDao.updateRTRInvoiceAmount(auditDetails.getId(), ParcelAuditConstant.PARCEL_RTR_RATING_USER_NAME, new BigDecimal("0"), rtrStatus.value, auditDetails.getCarrierId());
                        parcelRTRDao.updateShipmentRateDetails(ParcelAuditConstant.EBILL_GFF_TABLE_NAME, auditDetails.getId().toString(), ParcelAuditConstant.PARCEL_RTR_RATING_USER_NAME, rateDetails);
                    }
                }else if(ParcelAuditConstant.ChargeClassificationCode.FSC.name().equalsIgnoreCase(auditDetails.getChargeClassificationCode())){
                    charge = ParcelRateResponseParser.findChargeByType(ParcelRateResponse.ChargeType.ACCESSORIAL_FUEL.name(), priceSheet);
                    if(charge != null){
                        ParcelRateDetailsDto rateDetails = ParcelRateDetailsDto.getInstance();
                        rateDetails.setShipperCategory(shipperCategory);
                        rateDetails.setContractName(contractName);
                        rateDetails.setFuelTablePercentage(fuelTablePerc);
                        rateDetails.setRatedFuelSurchargeDiscount(ParcelRateResponseParser.getRatedSurchargeDiscount(priceSheet));
                        rateDetails.setRatedCustomFuelSurchargeDiscount(ParcelRateResponseParser.getRatedCustomSurchargeDiscount(priceSheet));
                        rateDetails.setRatedGrossFuel(ratedGrossFuel);
                        rateDetails.setDimDivisor(charge.getDimDivisor() == null ? new BigDecimal("0") : charge.getDimDivisor());
                        rateDetails.setRatedWeight(charge.getWeight() == null ? new BigDecimal("0") : charge.getWeight());

                        rateDetails.setRtrAmount(charge.getAmount());
                        rateDetails.setRtrStatus(rtrStatus.value);
                        //parcelRTRDao.updateRTRInvoiceAmount(auditDetails.getId(), ParcelAuditConstant.PARCEL_RTR_RATING_USER_NAME, charge.getAmount(), rtrStatus.value, auditDetails.getCarrierId());
                        parcelRTRDao.updateShipmentRateDetails(ParcelAuditConstant.EBILL_GFF_TABLE_NAME, auditDetails.getId().toString(), ParcelAuditConstant.PARCEL_RTR_RATING_USER_NAME, rateDetails);
                    }
                } else if(ParcelAuditConstant.ChargeClassificationCode.ACC.name().equalsIgnoreCase(auditDetails.getChargeClassificationCode())
                        && (ParcelAuditConstant.ChargeDescriptionCode.RES.name().equalsIgnoreCase(auditDetails.getChargeDescriptionCode()) || ParcelAuditConstant.ChargeDescriptionCode.RSC.name().equalsIgnoreCase(auditDetails.getChargeDescriptionCode()))){
                    charge = ParcelRateResponseParser.getResidentialSurcharge(priceSheet);
                    if(charge != null){
                        mappedAccChanges.add(charge);
                        ParcelRateDetailsDto rateDetails = ParcelRateDetailsDto.getInstance();
                        rateDetails.setShipperCategory(shipperCategory);
                        rateDetails.setContractName(contractName);
                        rateDetails.setDimDivisor(charge.getDimDivisor() == null ? new BigDecimal("0") : charge.getDimDivisor());
                        rateDetails.setRatedWeight(charge.getWeight() == null ? new BigDecimal("0") : charge.getWeight());
                        rateDetails.setRtrAmount(charge.getAmount());
                        rateDetails.setRtrStatus(rtrStatus.value);
                        //parcelRTRDao.updateRTRInvoiceAmount(auditDetails.getId(), ParcelAuditConstant.PARCEL_RTR_RATING_USER_NAME, charge.getAmount(), rtrStatus.value, auditDetails.getCarrierId());
                        parcelRTRDao.updateShipmentRateDetails(ParcelAuditConstant.EBILL_GFF_TABLE_NAME, auditDetails.getId().toString(), ParcelAuditConstant.PARCEL_RTR_RATING_USER_NAME, rateDetails);
                    }
                }else if(ParcelAuditConstant.ChargeClassificationCode.ACC.name().equalsIgnoreCase(auditDetails.getChargeClassificationCode())
                        && ("DAS".equalsIgnoreCase(auditDetails.getChargeDescriptionCode()) || dasChargeList.containsKey(auditDetails.getChargeDescriptionCode()))){
                    charge = ParcelRateResponseParser.getDeliveryAreaSurcharge(priceSheet);
                    if(charge != null){
                        mappedAccChanges.add(charge);
                        ParcelRateDetailsDto rateDetails = ParcelRateDetailsDto.getInstance();
                        rateDetails.setShipperCategory(shipperCategory);
                        rateDetails.setContractName(contractName);
                        ParcelRateResponse.Charge dasDiscount = ParcelRateResponseParser.getRatedDasDiscount(priceSheet);
                        if(dasDiscount != null){
                            mappedDscChanges.add(dasDiscount);
                            rateDetails.setDeliveryAreaSurchargeDiscount(dasDiscount.getAmount());
                        }
                        rateDetails.setDimDivisor(charge.getDimDivisor() == null ? new BigDecimal("0") : charge.getDimDivisor());
                        rateDetails.setRatedWeight(charge.getWeight() == null ? new BigDecimal("0") : charge.getWeight());
                        rateDetails.setRtrAmount(charge.getAmount());
                        rateDetails.setRtrStatus(rtrStatus.value);
                        //parcelRTRDao.updateRTRInvoiceAmount(auditDetails.getId(), ParcelAuditConstant.PARCEL_RTR_RATING_USER_NAME, charge.getAmount(), rtrStatus.value, auditDetails.getCarrierId());
                        parcelRTRDao.updateShipmentRateDetails(ParcelAuditConstant.EBILL_GFF_TABLE_NAME, auditDetails.getId().toString(), ParcelAuditConstant.PARCEL_RTR_RATING_USER_NAME, rateDetails);
                    }
                } else if(ParcelAuditConstant.ChargeClassificationCode.ACC.name().equalsIgnoreCase(auditDetails.getChargeClassificationCode())
                        && lpsChargeCodes != null && lpsChargeCodes.containsKey(auditDetails.getChargeDescriptionCode())) {
                    charge = ParcelRateResponseParser.getLargePachageSurcharge(priceSheet);
                    if(charge != null) {
                        mappedAccChanges.add(charge);

                        ParcelRateDetailsDto rateDetails = ParcelRateDetailsDto.getInstance();
                        rateDetails.setShipperCategory(shipperCategory);
                        rateDetails.setContractName(contractName);
                        rateDetails.setFuelTablePercentage(fuelTablePerc);
                        rateDetails.setDimDivisor(charge.getDimDivisor() == null ? new BigDecimal("0") : charge.getDimDivisor());
                        rateDetails.setRatedWeight(charge.getWeight() == null ? new BigDecimal("0") : charge.getWeight());
                        rateDetails.setRtrAmount(charge.getAmount());
                        rateDetails.setRtrStatus(rtrStatus.value);
                        //parcelRTRDao.updateRTRInvoiceAmount(auditDetails.getId(), ParcelAuditConstant.PARCEL_RTR_RATING_USER_NAME, charge.getAmount(), rtrStatus.value, auditDetails.getCarrierId());
                        parcelRTRDao.updateShipmentRateDetails(ParcelAuditConstant.EBILL_GFF_TABLE_NAME, auditDetails.getId().toString(), ParcelAuditConstant.PARCEL_RTR_RATING_USER_NAME, rateDetails);
                    }
                } else{
                    charge = ParcelRateResponseParser.findChargeByEDICodeInResponse(auditDetails.getChargeClassificationCode(), priceSheet);
                    if(charge != null){
                        if(charge != null && charge.getType() != null){
                            if(ParcelRateResponse.ChargeType.ACCESSORIAL.name().equalsIgnoreCase(charge.getType())) mappedAccChanges.add(charge);
                            else if(ParcelRateResponse.ChargeType.DISCOUNT.name().equalsIgnoreCase(charge.getType())) mappedDscChanges.add(charge);
                        }
                        ParcelRateDetailsDto rateDetails = ParcelRateDetailsDto.getInstance();
                        rateDetails.setShipperCategory(shipperCategory);
                        rateDetails.setContractName(contractName);
                        rateDetails.setFuelTablePercentage(fuelTablePerc);
                        rateDetails.setDimDivisor(charge.getDimDivisor() == null ? new BigDecimal("0") : charge.getDimDivisor());
                        rateDetails.setRatedWeight(charge.getWeight() == null ? new BigDecimal("0") : charge.getWeight());
                        rateDetails.setRtrAmount(charge.getAmount());
                        rateDetails.setRtrStatus(rtrStatus.value);
                        //parcelRTRDao.updateRTRInvoiceAmount(auditDetails.getId(), ParcelAuditConstant.PARCEL_RTR_RATING_USER_NAME, charge.getAmount(), rtrStatus.value, auditDetails.getCarrierId());
                        parcelRTRDao.updateShipmentRateDetails(ParcelAuditConstant.EBILL_GFF_TABLE_NAME, auditDetails.getId().toString(), ParcelAuditConstant.PARCEL_RTR_RATING_USER_NAME, rateDetails);
                    }
                }

                if(null == charge){
                    ParcelRateDetailsDto rateDetails = ParcelRateDetailsDto.getInstance();
                    rateDetails.setShipperCategory(shipperCategory);
                    rateDetails.setContractName(contractName);
                    rateDetails.setFuelTablePercentage(fuelTablePerc);
                    rateDetails.setRtrAmount(new BigDecimal("0"));
                    rateDetails.setRtrStatus(rtrStatus.value);
                    //parcelRTRDao.updateRTRInvoiceAmount(auditDetails.getId(), ParcelAuditConstant.PARCEL_RTR_RATING_USER_NAME, new BigDecimal("0"), rtrStatus.value, auditDetails.getCarrierId());
                    parcelRTRDao.updateShipmentRateDetails(ParcelAuditConstant.EBILL_GFF_TABLE_NAME, auditDetails.getId().toString(), ParcelAuditConstant.PARCEL_RTR_RATING_USER_NAME, rateDetails);
                }
            }
        }

        ParcelRateDetailsDto otherDscRateDetails = ParcelRateDetailsDto.getInstance();
        otherDscRateDetails.setShipperCategory(shipperCategory);
        otherDscRateDetails.setContractName(contractName);
        saveOtherDiscountsAppliedForUps(priceSheet, parcelAuditDetails, otherDscRateDetails, mappedDscChanges);

        ParcelRateDetailsDto accessorialRateDetails = ParcelRateDetailsDto.getInstance();
        accessorialRateDetails.setShipperCategory(shipperCategory);
        accessorialRateDetails.setContractName(contractName);
        saveAccessorialForUps(priceSheet, parcelAuditDetails, accessorialRateDetails, mappedAccChanges);
        return rtrStatus.value;
    }

    /**
     * To update rtr_status and rtr_amount.
     *
     * @param parcelAuditDetails
     * @param rtrStatus
     * @throws Exception
     */
    private void updateRTRAmountAndStatus(List<ParcelAuditDetailsDto> parcelAuditDetails, RTRStatus rtrStatus) throws Exception {
        if(parcelAuditDetails != null && !parcelAuditDetails.isEmpty()){
            StringJoiner entityIds = new StringJoiner(",");
            for (ParcelAuditDetailsDto auditDetails : parcelAuditDetails){
                if(auditDetails != null){
                    entityIds.add(auditDetails.getId().toString());
                }
            }
            if(entityIds.length() > 0){
                parcelRTRDao.updateRtrStatusByIds(entityIds.toString(), ParcelAuditConstant.PARCEL_RTR_RATING_USER_NAME, rtrStatus.value, parcelAuditDetails.get(0).getCarrierId());
            }
        }
    }

    /**
     * To find sum of total net amount applied in a particular shipment.
     *
     * @param parcelAuditDetailsList
     * @return
     */
    private BigDecimal findSumOfNetAmount(List<ParcelAuditDetailsDto> parcelAuditDetailsList) {
        BigDecimal sumOfNetAmount = new BigDecimal("0.0");
        if(parcelAuditDetailsList != null && !parcelAuditDetailsList.isEmpty()){
            for(ParcelAuditDetailsDto parcelAuditDetails : parcelAuditDetailsList){
                if(parcelAuditDetails != null && parcelAuditDetails.getNetAmount() != null && !parcelAuditDetails.getNetAmount().isEmpty()){
                    try{
                        sumOfNetAmount = sumOfNetAmount.add(new BigDecimal(parcelAuditDetails.getNetAmount()));
                    }catch (Exception e){}
                }
            }
        }
        return sumOfNetAmount;
    }

    public List<ParcelAuditDetailsDto> loadInvoiceIds(String fromDate, String toDate, String customerId, String invoiceIds, int limit, String rateTo){
        return parcelRTRDao.loadInvoiceIds(fromDate, toDate, customerId, invoiceIds, limit, rateTo);
    }

    public void doParcelAuditingInvoiceNumberWise(List<ParcelAuditDetailsDto> invoiceList, String trackingNumbers, String rateTo, String fromShipDate, String toShipDate, String customerIds) {
        if (invoiceList != null && !invoiceList.isEmpty()) {
            String licenseKey = messageSource.getMessage("RateRequest-LicenseKey", null, null);
            String strProtocol = messageSource.getMessage("RTRprotocol", null, null);
            String strHostName = messageSource.getMessage("RTRHostName", null, null);
            String strPrefix = messageSource.getMessage("RTRPrefix", null, null);
            String url = strProtocol + "://" + strHostName + "/" + strPrefix;

            MsiARChargeCodesDto msiARChargeCode = MsiARChargeCodesDto.getInstance();
            msiARChargeCode.setDasChargeCodes(parcelRTRDao.loadMappedARChargeCodes(ParcelAuditConstant.MSI_AR_CHARGE_CODE_MAPPING_MODELE_NAME, ParcelAuditConstant.MSI_AR_DAS_CHARGE_CODE_NAME));
            msiARChargeCode.setLpsChargeCodes(parcelRTRDao.loadMappedARChargeCodes(ParcelAuditConstant.MSI_AR_CHARGE_CODE_MAPPING_MODELE_NAME, ParcelAuditConstant.MSI_AR_LPS_CHARGE_CODE_NAME));

            for (ParcelAuditDetailsDto inv : invoiceList) {
                if (inv != null && inv.getInvoiceId() != null) {
                    System.out.println(inv.getInvoiceId());
                    if (rateTo == null || rateTo.isEmpty()) {
                        doParcelRatingForUpsCarrier(loadUpsParcelAuditDetails(customerIds, fromShipDate, toShipDate, trackingNumbers, inv.getInvoiceId().toString()), url, licenseKey, customerIds, msiARChargeCode);
                        doParcelRatingForNonUpsCarrier(loadNonUpsParcelAuditDetails(customerIds, fromShipDate, toShipDate, trackingNumbers, inv.getInvoiceId().toString()), url, licenseKey, customerIds, msiARChargeCode);
                    } else if ("UPS".equalsIgnoreCase(rateTo)) {
                        doParcelRatingForUpsCarrier(loadUpsParcelAuditDetails(customerIds, fromShipDate, toShipDate, trackingNumbers, inv.getInvoiceId().toString()), url, licenseKey, customerIds, msiARChargeCode);
                    } else if ("FEDEX".equalsIgnoreCase(rateTo)) {
                        doParcelRatingForNonUpsCarrier(loadNonUpsParcelAuditDetails(customerIds, fromShipDate, toShipDate, trackingNumbers, inv.getInvoiceId().toString()), url, licenseKey, customerIds, msiARChargeCode);
                    }
                }
            }
        }
    }

    @Deprecated
    private void updateInvoiceRtrStatus(Long invoiceId, Map<String, String> shipmentStatusMap) {
        String userName = "ParcelRTRRating";
        if(shipmentStatusMap != null && !shipmentStatusMap.isEmpty()){
            if(shipmentStatusMap.containsValue(RTRStatus.UNDER_CHARGED.value)){
                parcelRTRDao.updateInvoiceRtrStatus(invoiceId, RTRStatus.UNDER_CHARGED.value, userName);
            }else if(shipmentStatusMap.containsValue(RTRStatus.OVER_CHARGED.value)){
                parcelRTRDao.updateInvoiceRtrStatus(invoiceId, RTRStatus.OVER_CHARGED.value, userName);
            }else if(shipmentStatusMap.containsValue(RTRStatus.RATING_EXCEPTION.value)){
                parcelRTRDao.updateInvoiceRtrStatus(invoiceId, RTRStatus.RATING_EXCEPTION.value, userName);
            }else if(shipmentStatusMap.containsValue(RTRStatus.NO_PRICE_SHEET.value)){
                parcelRTRDao.updateInvoiceRtrStatus(invoiceId, RTRStatus.NO_PRICE_SHEET.value, userName);
            }else if(shipmentStatusMap.containsValue(RTRStatus.CLOSED.value)){
                parcelRTRDao.updateInvoiceRtrStatus(invoiceId, RTRStatus.CLOSED.value, userName);
            }else{
                parcelRTRDao.updateInvoiceRtrStatus(invoiceId, RTRStatus.READY_FOR_RATE.value, userName);
            }
        }
    }

    private void saveAccessorialForUps(ParcelRateResponse.PriceSheet priceSheet, List<ParcelAuditDetailsDto> parcelAuditDetails, ParcelRateDetailsDto rateDetails, List<ParcelRateResponse.Charge> mappedAccChanges) {
        saveAccessorialForUps(priceSheet, parcelAuditDetails, rateDetails, mappedAccChanges, null);
    }

    private void saveAccessorialForUps(ParcelRateResponse.PriceSheet priceSheet, List<ParcelAuditDetailsDto> parcelAuditDetails, ParcelRateDetailsDto rateDetails, List<ParcelRateResponse.Charge> mappedAccChanges, List<RatedChargeDetailsDto> ratedCharges) {
        try{
            if(priceSheet != null && parcelAuditDetails != null && !parcelAuditDetails.isEmpty()){
                StringJoiner entityIds = new StringJoiner(",");
                parcelAuditDetails.forEach(auditDetail -> {
                    entityIds.add(auditDetail.getId().toString());
                });
                List<ParcelRateResponse.Charge> accessorialCharges = ParcelRateResponseParser.getAccessorialCharges(priceSheet);

                if(accessorialCharges != null && !accessorialCharges.isEmpty()){
                    if(mappedAccChanges != null && !mappedAccChanges.isEmpty()){
                        Iterator<ParcelRateResponse.Charge> chargeIterator = accessorialCharges.iterator();
                        while(chargeIterator.hasNext()){
                            ParcelRateResponse.Charge tempCharge = chargeIterator.next();
                            for(ParcelRateResponse.Charge mappedChrg : mappedAccChanges){
                                if(mappedChrg != null && tempCharge != null
                                        && mappedChrg.getType() != null && tempCharge.getType() != null
                                        && mappedChrg.getType().equalsIgnoreCase(tempCharge.getType())
                                        && mappedChrg.getName() != null && tempCharge.getName() != null
                                        && mappedChrg.getName().equalsIgnoreCase(tempCharge.getName())) {
                                    chargeIterator.remove();
                                }
                            }
                        }
                    }

                    if(accessorialCharges.size() == 1){
                        BigDecimal adjustedAcc1 = new BigDecimal("0");
                        BigDecimal prevRatedAcc1 = null;

                        if(ratedCharges != null){
                            prevRatedAcc1 = ParcelRatingUtil.findAccessorialAmountByAccessorialCode(accessorialCharges.get(0).getEdiCode(), ratedCharges);
                        }
                        if(prevRatedAcc1 != null){
                            if(accessorialCharges.get(0).getAmount() != null) adjustedAcc1 = accessorialCharges.get(0).getAmount().subtract(prevRatedAcc1);
                        } else {
                            adjustedAcc1 = accessorialCharges.get(0).getAmount();
                        }
                        rateDetails.setAccessorial1(adjustedAcc1);
                        rateDetails.setAccessorial1Code(accessorialCharges.get(0).getEdiCode());
                    }else if(accessorialCharges.size() == 2){
                        BigDecimal adjustedAcc1 = new BigDecimal("0");
                        BigDecimal prevRatedAcc1 = null;
                        if(ratedCharges != null){
                            prevRatedAcc1 = ParcelRatingUtil.findAccessorialAmountByAccessorialCode(accessorialCharges.get(0).getEdiCode(), ratedCharges);
                        }
                        if(prevRatedAcc1 != null){
                            if(accessorialCharges.get(0).getAmount() != null) adjustedAcc1 = accessorialCharges.get(0).getAmount().subtract(prevRatedAcc1);
                        } else {
                            adjustedAcc1 = accessorialCharges.get(0).getAmount();
                        }
                        rateDetails.setAccessorial1(adjustedAcc1);
                        rateDetails.setAccessorial1Code(accessorialCharges.get(0).getEdiCode());

                        BigDecimal adjustedAcc2 = new BigDecimal("0");
                        BigDecimal prevRatedAcc2 = null;
                        if(ratedCharges != null){
                            prevRatedAcc2 = ParcelRatingUtil.findAccessorialAmountByAccessorialCode(accessorialCharges.get(1).getEdiCode(), ratedCharges);
                        }
                        if(prevRatedAcc2 != null){
                            if(accessorialCharges.get(1).getAmount() != null) adjustedAcc2 = accessorialCharges.get(1).getAmount().subtract(prevRatedAcc2);
                        } else {
                            adjustedAcc2 = accessorialCharges.get(1).getAmount();
                        }
                        rateDetails.setAccessorial2(adjustedAcc2);
                        rateDetails.setAccessorial2Code(accessorialCharges.get(1).getEdiCode());
                    }else if(accessorialCharges.size() == 3){
                        BigDecimal adjustedAcc1 = new BigDecimal("0");
                        BigDecimal prevRatedAcc1 = null;
                        if(ratedCharges != null){
                            prevRatedAcc1 = ParcelRatingUtil.findAccessorialAmountByAccessorialCode(accessorialCharges.get(0).getEdiCode(), ratedCharges);
                        }
                        if(prevRatedAcc1 != null){
                            if(accessorialCharges.get(0).getAmount() != null) adjustedAcc1 = accessorialCharges.get(0).getAmount().subtract(prevRatedAcc1);
                        } else {
                            adjustedAcc1 = accessorialCharges.get(0).getAmount();
                        }
                        rateDetails.setAccessorial1(adjustedAcc1);
                        rateDetails.setAccessorial1Code(accessorialCharges.get(0).getEdiCode());

                        BigDecimal adjustedAcc2 = new BigDecimal("0");
                        BigDecimal prevRatedAcc2 = null;
                        if(ratedCharges != null){
                            prevRatedAcc2 = ParcelRatingUtil.findAccessorialAmountByAccessorialCode(accessorialCharges.get(1).getEdiCode(), ratedCharges);
                        }
                        if(prevRatedAcc2 != null){
                            if(accessorialCharges.get(1).getAmount() != null) adjustedAcc2 = accessorialCharges.get(1).getAmount().subtract(prevRatedAcc2);
                        } else {
                            adjustedAcc2 = accessorialCharges.get(1).getAmount();
                        }
                        rateDetails.setAccessorial2(adjustedAcc2);
                        rateDetails.setAccessorial2Code(accessorialCharges.get(1).getEdiCode());

                        BigDecimal adjustedAcc3 = new BigDecimal("0");
                        BigDecimal prevRatedAcc3 = null;
                        if(ratedCharges != null){
                            prevRatedAcc3 = ParcelRatingUtil.findAccessorialAmountByAccessorialCode(accessorialCharges.get(2).getEdiCode(), ratedCharges);
                        }
                        if(prevRatedAcc3 != null){
                            if(accessorialCharges.get(2).getAmount() != null) adjustedAcc3 = accessorialCharges.get(2).getAmount().subtract(prevRatedAcc3);
                        } else {
                            adjustedAcc3 = accessorialCharges.get(2).getAmount();
                        }
                        rateDetails.setAccessorial3(adjustedAcc3);
                        rateDetails.setAccessorial3Code(accessorialCharges.get(2).getEdiCode());
                    } else if(accessorialCharges.size() >= 4){
                        BigDecimal adjustedAcc1 = new BigDecimal("0");
                        BigDecimal prevRatedAcc1 = null;
                        if(ratedCharges != null){
                            prevRatedAcc1 = ParcelRatingUtil.findAccessorialAmountByAccessorialCode(accessorialCharges.get(0).getEdiCode(), ratedCharges);
                        }
                        if(prevRatedAcc1 != null){
                            if(accessorialCharges.get(0).getAmount() != null) adjustedAcc1 = accessorialCharges.get(0).getAmount().subtract(prevRatedAcc1);
                        } else {
                            adjustedAcc1 = accessorialCharges.get(0).getAmount();
                        }
                        rateDetails.setAccessorial1(adjustedAcc1);
                        rateDetails.setAccessorial1Code(accessorialCharges.get(0).getEdiCode());

                        BigDecimal adjustedAcc2 = new BigDecimal("0");
                        BigDecimal prevRatedAcc2 = null;
                        if(ratedCharges != null){
                            prevRatedAcc2 = ParcelRatingUtil.findAccessorialAmountByAccessorialCode(accessorialCharges.get(1).getEdiCode(), ratedCharges);
                        }
                        if(prevRatedAcc2 != null){
                            if(accessorialCharges.get(1).getAmount() != null) adjustedAcc2 = accessorialCharges.get(1).getAmount().subtract(prevRatedAcc2);
                        } else {
                            adjustedAcc2 = accessorialCharges.get(1).getAmount();
                        }
                        rateDetails.setAccessorial2(adjustedAcc2);
                        rateDetails.setAccessorial2Code(accessorialCharges.get(1).getEdiCode());

                        BigDecimal adjustedAcc3 = new BigDecimal("0");
                        BigDecimal prevRatedAcc3 = null;
                        if(ratedCharges != null){
                            prevRatedAcc3 = ParcelRatingUtil.findAccessorialAmountByAccessorialCode(accessorialCharges.get(2).getEdiCode(), ratedCharges);
                        }
                        if(prevRatedAcc3 != null){
                            if(accessorialCharges.get(2).getAmount() != null) adjustedAcc3 = accessorialCharges.get(2).getAmount().subtract(prevRatedAcc3);
                        } else {
                            adjustedAcc3 = accessorialCharges.get(2).getAmount();
                        }
                        rateDetails.setAccessorial3(adjustedAcc3);
                        rateDetails.setAccessorial3Code(accessorialCharges.get(2).getEdiCode());

                        BigDecimal adjustedAcc4 = new BigDecimal("0");
                        BigDecimal prevRatedAcc4 = null;
                        if(ratedCharges != null){
                            prevRatedAcc4 = ParcelRatingUtil.findAccessorialAmountByAccessorialCode(accessorialCharges.get(3).getEdiCode(), ratedCharges);
                        }
                        if(prevRatedAcc4 != null){
                            if(accessorialCharges.get(3).getAmount() != null) adjustedAcc4 = accessorialCharges.get(3).getAmount().subtract(prevRatedAcc4);
                        } else {
                            adjustedAcc4 = accessorialCharges.get(3).getAmount();
                        }
                        rateDetails.setAccessorial4(adjustedAcc4);
                        rateDetails.setAccessorial4Code(accessorialCharges.get(3).getEdiCode());
                    }
                    parcelRTRDao.updateAccessorialShipmentRateDetails(ParcelAuditConstant.EBILL_GFF_TABLE_NAME, entityIds.toString(), ParcelAuditConstant.PARCEL_RTR_RATING_USER_NAME, rateDetails);
                }
            }
        }catch (Exception e){}
    }

    private void saveAccessorialForNonUps(ParcelRateResponse.PriceSheet priceSheet, List<ParcelAuditDetailsDto> parcelAuditDetails, ParcelRateDetailsDto rateDetails, List<ParcelRateResponse.Charge> mappedAccChanges) {
        try{
            if(priceSheet != null && parcelAuditDetails != null && !parcelAuditDetails.isEmpty()){
                StringJoiner entityIds = new StringJoiner(",");
                parcelAuditDetails.forEach(auditDetail -> {
                    entityIds.add(auditDetail.getId().toString());
                });
                List<ParcelRateResponse.Charge> accessorialCharges = ParcelRateResponseParser.getAccessorialCharges(priceSheet);
                if(accessorialCharges != null && !accessorialCharges.isEmpty()){
                    if(mappedAccChanges != null && !mappedAccChanges.isEmpty()){
                        Iterator<ParcelRateResponse.Charge> chargeIterator = accessorialCharges.iterator();
                        while(chargeIterator.hasNext()){
                            ParcelRateResponse.Charge tempCharge = chargeIterator.next();
                            for(ParcelRateResponse.Charge mappedChrg : mappedAccChanges){
                                if(mappedChrg != null && tempCharge != null
                                        && mappedChrg.getType() != null && tempCharge.getType() != null
                                        && mappedChrg.getType().equalsIgnoreCase(tempCharge.getType())
                                        && mappedChrg.getName() != null && tempCharge.getName() != null
                                        && mappedChrg.getName().equalsIgnoreCase(tempCharge.getName())) {
                                    chargeIterator.remove();
                                }
                            }
                        }
                    }

                    if(accessorialCharges.size() == 1){
                        rateDetails.setAccessorial1(accessorialCharges.get(0).getAmount());
                        rateDetails.setAccessorial1Code(accessorialCharges.get(0).getEdiCode());
                    }else if(accessorialCharges.size() == 2){
                        rateDetails.setAccessorial1(accessorialCharges.get(0).getAmount());
                        rateDetails.setAccessorial1Code(accessorialCharges.get(0).getEdiCode());

                        rateDetails.setAccessorial2(accessorialCharges.get(1).getAmount());
                        rateDetails.setAccessorial2Code(accessorialCharges.get(1).getEdiCode());
                    }else if(accessorialCharges.size() == 3){
                        rateDetails.setAccessorial1(accessorialCharges.get(0).getAmount());
                        rateDetails.setAccessorial1Code(accessorialCharges.get(0).getEdiCode());
                        rateDetails.setAccessorial2(accessorialCharges.get(1).getAmount());
                        rateDetails.setAccessorial2Code(accessorialCharges.get(1).getEdiCode());
                        rateDetails.setAccessorial3(accessorialCharges.get(2).getAmount());
                        rateDetails.setAccessorial3Code(accessorialCharges.get(2).getEdiCode());
                    } else if(accessorialCharges.size() >= 3){
                        rateDetails.setAccessorial1(accessorialCharges.get(0).getAmount());
                        rateDetails.setAccessorial1Code(accessorialCharges.get(0).getEdiCode());
                        rateDetails.setAccessorial2(accessorialCharges.get(1).getAmount());
                        rateDetails.setAccessorial2Code(accessorialCharges.get(1).getEdiCode());
                        rateDetails.setAccessorial3(accessorialCharges.get(2).getAmount());
                        rateDetails.setAccessorial3Code(accessorialCharges.get(2).getEdiCode());
                        rateDetails.setAccessorial4(accessorialCharges.get(3).getAmount());
                        rateDetails.setAccessorial4Code(accessorialCharges.get(3).getEdiCode());
                    }
                    parcelRTRDao.updateAccessorialShipmentRateDetails(ParcelAuditConstant.EBILL_MANIFEST_TABLE_NAME, entityIds.toString(), ParcelAuditConstant.PARCEL_RTR_RATING_USER_NAME, rateDetails);
                }
            }
        }catch (Exception e){}
    }
    private void saveOtherDiscountsAppliedForUps(ParcelRateResponse.PriceSheet priceSheet, List<ParcelAuditDetailsDto> parcelAuditDetails, ParcelRateDetailsDto rateDetails, List<ParcelRateResponse.Charge> mappedDscChanges) {
        try{
            if(priceSheet != null && parcelAuditDetails != null && !parcelAuditDetails.isEmpty()){
                StringJoiner entityIds = new StringJoiner(",");
                parcelAuditDetails.forEach(auditDetail -> {
                    entityIds.add(auditDetail.getId().toString());
                });
                List<ParcelRateResponse.Charge> discountCharges = ParcelRateResponseParser.getAllOtherDiscounts(priceSheet);
                if(discountCharges != null && !discountCharges.isEmpty()){
                    if(mappedDscChanges != null && !mappedDscChanges.isEmpty()){
                        Iterator<ParcelRateResponse.Charge> chargeIterator = mappedDscChanges.iterator();
                        while(chargeIterator.hasNext()){
                            ParcelRateResponse.Charge tempCharge = chargeIterator.next();
                            for(ParcelRateResponse.Charge mappedChrg : mappedDscChanges){
                                if(mappedChrg != null && tempCharge != null
                                        && mappedChrg.getType() != null && tempCharge.getType() != null
                                        && mappedChrg.getType().equalsIgnoreCase(tempCharge.getType())
                                        && mappedChrg.getName() != null && tempCharge.getName() != null
                                        && mappedChrg.getName().equalsIgnoreCase(tempCharge.getName())) {
                                    chargeIterator.remove();
                                }
                            }
                        }
                    }

                    if(discountCharges.size() == 1){
                        rateDetails.setOtherDiscount1(discountCharges.get(0).getAmount());
                    }else if(discountCharges.size() == 2){
                        rateDetails.setOtherDiscount1(discountCharges.get(0).getAmount());
                        rateDetails.setOtherDiscount2(discountCharges.get(1).getAmount());
                    }else if(discountCharges.size() >= 3){
                        rateDetails.setOtherDiscount1(discountCharges.get(0).getAmount());
                        rateDetails.setOtherDiscount2(discountCharges.get(1).getAmount());
                        rateDetails.setOtherDiscount3(discountCharges.get(2).getAmount());
                    }
                    parcelRTRDao.updateOtherDiscountShipmentRateDetails(ParcelAuditConstant.EBILL_GFF_TABLE_NAME, entityIds.toString(), ParcelAuditConstant.PARCEL_RTR_RATING_USER_NAME, rateDetails);
                }
            }
        }catch (Exception e){}
    }

    private void saveOtherDiscountsAppliedForNonUps(ParcelRateResponse.PriceSheet priceSheet, List<ParcelAuditDetailsDto> parcelAuditDetails, ParcelRateDetailsDto rateDetails, List<ParcelRateResponse.Charge> mappedDscChanges) {
        try{
            if(priceSheet != null && parcelAuditDetails != null && !parcelAuditDetails.isEmpty()){
                StringJoiner entityIds = new StringJoiner(",");
                parcelAuditDetails.forEach(auditDetail -> {
                    entityIds.add(auditDetail.getId().toString());
                });
                List<ParcelRateResponse.Charge> discountCharges = ParcelRateResponseParser.getAllOtherDiscounts(priceSheet);
                if(discountCharges != null && !discountCharges.isEmpty()){
                    if(mappedDscChanges != null && !mappedDscChanges.isEmpty()){
                        Iterator<ParcelRateResponse.Charge> chargeIterator = mappedDscChanges.iterator();
                        while(chargeIterator.hasNext()){
                            ParcelRateResponse.Charge tempCharge = chargeIterator.next();
                            for(ParcelRateResponse.Charge mappedChrg : mappedDscChanges){
                                if(mappedChrg != null && tempCharge != null
                                        && mappedChrg.getType() != null && tempCharge.getType() != null
                                        && mappedChrg.getType().equalsIgnoreCase(tempCharge.getType())
                                        && mappedChrg.getName() != null && tempCharge.getName() != null
                                        && mappedChrg.getName().equalsIgnoreCase(tempCharge.getName())) {
                                    chargeIterator.remove();
                                }
                            }
                        }
                    }
                    if(discountCharges.size() == 1){
                        rateDetails.setOtherDiscount1(discountCharges.get(0).getAmount());
                    }else if(discountCharges.size() == 2){
                        rateDetails.setOtherDiscount1(discountCharges.get(0).getAmount());
                        rateDetails.setOtherDiscount2(discountCharges.get(1).getAmount());
                    }else if(discountCharges.size() >= 3){
                        rateDetails.setOtherDiscount1(discountCharges.get(0).getAmount());
                        rateDetails.setOtherDiscount2(discountCharges.get(1).getAmount());
                        rateDetails.setOtherDiscount3(discountCharges.get(2).getAmount());
                    }
                    parcelRTRDao.updateOtherDiscountShipmentRateDetails(ParcelAuditConstant.EBILL_MANIFEST_TABLE_NAME, entityIds.toString(), ParcelAuditConstant.PARCEL_RTR_RATING_USER_NAME, rateDetails);
                }
            }
        }catch (Exception e){}
    }

    public BigDecimal getRatedTotalAmount(Long parentId){
        BigDecimal total = new BigDecimal("0");
        boolean frtFound = false;
        List<RatedChargeDetailsDto> ratedChargeDetails = getRatedChargeAmount(parentId);
        if(ratedChargeDetails != null){
            for(RatedChargeDetailsDto ratedCharge : ratedChargeDetails){
                if(ratedCharge != null){
                    if(!frtFound && "FRT".equalsIgnoreCase(ratedCharge.getChargeClassificationCode())){
                        frtFound = true;
                        if(ratedCharge.getRatedAmount() != null) total = total.add(ratedCharge.getRatedAmount());
                        if(ratedCharge.getRatedBaseDiscount() != null) total = total.add(ratedCharge.getRatedBaseDiscount());
                        if(ratedCharge.getAccessorial1() != null) total = total.add(ratedCharge.getAccessorial1());
                        if(ratedCharge.getAccessorial2() != null) total = total.add(ratedCharge.getAccessorial2());
                        if(ratedCharge.getAccessorial3() != null) total = total.add(ratedCharge.getAccessorial3());
                        if(ratedCharge.getAccessorial4() != null) total = total.add(ratedCharge.getAccessorial4());
                    } else if("FSC".equalsIgnoreCase(ratedCharge.getChargeClassificationCode())){
                        total = total.add(ratedCharge.getRatedAmount());
                    } else if("ACC".equalsIgnoreCase(ratedCharge.getChargeClassificationCode())) {
                        total = total.add(ratedCharge.getRatedAmount());
                    }
                }
            }
        }
        return total;
    }

    /**
     * This method will prepare bundle number or multi weight id tracking number wise details
     *
     * @param listMap
     * @return
     */
    private Map<String, List<ParcelAuditDetailsDto>> prepareMultiWeightNumberWiseAuditDetails(Map<String, List<ParcelAuditDetailsDto>> listMap) {

        Map<String, List<ParcelAuditDetailsDto>> mwtDetailsMap = new HashMap<>();
        Map<String, List<ParcelAuditDetailsDto>> tempMap = new HashMap<>(listMap);
        for (Map.Entry<String, List<ParcelAuditDetailsDto>> entry : tempMap.entrySet()) {


            for (ParcelAuditDetailsDto parcelAuditDetails : entry.getValue()) {
                if (parcelAuditDetails != null) {

                    if (parcelAuditDetails.getMultiWeightNumber() != null && !parcelAuditDetails.getMultiWeightNumber().isEmpty()) {
                        if (mwtDetailsMap.containsKey(parcelAuditDetails.getMultiWeightNumber()))
                            mwtDetailsMap.get(parcelAuditDetails.getMultiWeightNumber()).addAll(tempMap.get(parcelAuditDetails.getTrackingNumber()));
                        else
                            mwtDetailsMap.put(parcelAuditDetails.getMultiWeightNumber(), tempMap.get(parcelAuditDetails.getTrackingNumber()));

                        listMap.remove(parcelAuditDetails.getTrackingNumber());
                        break;
                    }
                }
            }
        }
        tempMap = null;
        return mwtDetailsMap;
    }

    /**
     * This method will prepare Lead Shipment tracking number wise details
     * @param listMap
     * @return
     */
    private Map<String, List<ParcelAuditDetailsDto>> prepareHwtNumberWiseAuditDetails(Map<String, List<ParcelAuditDetailsDto>> listMap) {

        Map<String, List<ParcelAuditDetailsDto>> hwtDetailsMap = new HashMap<>();
        Map<String, List<ParcelAuditDetailsDto>> tempMap = new HashMap<>(listMap);
        for (Map.Entry<String, List<ParcelAuditDetailsDto>> entry : tempMap.entrySet()) {

            for (ParcelAuditDetailsDto parcelAuditDetails : entry.getValue()) {
                if (parcelAuditDetails != null) {

                    if (parcelAuditDetails.getMultiWeightNumber() != null && !parcelAuditDetails.getMultiWeightNumber().isEmpty()) {
                        if (hwtDetailsMap.containsKey(parcelAuditDetails.getMultiWeightNumber()))
                            hwtDetailsMap.get(parcelAuditDetails.getMultiWeightNumber()).addAll(tempMap.get(parcelAuditDetails.getTrackingNumber()));
                        else
                            hwtDetailsMap.put(parcelAuditDetails.getMultiWeightNumber(), tempMap.get(parcelAuditDetails.getTrackingNumber()));

                        listMap.remove(parcelAuditDetails.getTrackingNumber());
                        break;
                    }
                }
            }
        }
        tempMap = null;
        return hwtDetailsMap;
    }

    /**
     * This method will return lead shipment details
     * @param parcelAuditDetails
     * @return
     */
    private List<ParcelAuditDetailsDto> getLeadShipmentDetails(List<ParcelAuditDetailsDto> parcelAuditDetails) {

        List<ParcelAuditDetailsDto> detailsDtos = new ArrayList<>();

        ParcelAuditDetailsDto minDto = parcelAuditDetails.stream().min(Comparator.comparing(ParcelAuditDetailsDto::getId)).orElseThrow(NoSuchElementException::new);

        if (minDto != null) {
            for (ParcelAuditDetailsDto dto : parcelAuditDetails) {

                if (StringUtils.equalsIgnoreCase(dto.getTrackingNumber(), minDto.getTrackingNumber())) {

                    detailsDtos.add(dto);
                }
            }
        }
        return detailsDtos;
    }

    public List<RateSetResponse.RateSet> getRateSetsByCustomer(String customerCode) {


        String url = ParcelAuditConstant.AR_RATE_REQUEST_PROTOCOL + "://"
                + ParcelAuditConstant.AR_RATE_REQUEST_HOST_NAME + "/"
                + ParcelAuditConstant.AR_RATE_SET_REQUEST_URI_PATH + ParcelAuditConstant.AR_RATE_REQUEST_LICENSE_KEY + "&customerCode=" + customerCode;

        log.info("url==" + url);


        RateSetResponse result = restTemplate.getForObject(url, RateSetResponse.class);

        log.info("result=====" + result.getRateSets().toString());

        return result.getRateSets();
    }
}