package com.envista.msi.api.service.rtr;

import com.envista.msi.api.dao.rtr.ParcelRTRDao;
import com.envista.msi.api.domain.util.ParcelRatingUtil;
import com.envista.msi.api.web.rest.dto.rtr.ParcelAuditDetailsDto;
import com.envista.msi.api.web.rest.dto.rtr.ParcelAuditRequestResponseLog;
import com.envista.msi.api.web.rest.dto.rtr.ParcelRateDetailsDto;
import com.envista.msi.api.web.rest.util.CommonUtil;
import com.envista.msi.api.web.rest.util.audit.parcel.ParcelAuditConstant;
import com.envista.msi.api.web.rest.util.audit.parcel.ParcelAuditConstant.RTRStatus;
import com.envista.msi.api.web.rest.util.audit.parcel.ParcelAuditConstant.RateTo;
import com.envista.msi.api.web.rest.util.audit.parcel.ParcelRateRequestBuilder;
import com.envista.msi.api.web.rest.util.audit.parcel.ParcelRateResponse;
import com.envista.msi.api.web.rest.util.audit.parcel.ParcelRateResponseParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.math.BigDecimal;
import java.util.*;

/**
 * Created by Sujit kumar on 08/06/2017.
 */
@Service
@Transactional
public class ParcelRTRService{
    @Inject
    private ParcelRTRDao parcelRTRDao;

    @Autowired
    @org.springframework.beans.factory.annotation.Qualifier(value = "rtrRateResource")
    private MessageSource messageSource;

    public Map<String, List<ParcelAuditDetailsDto>> loadUpsParcelAuditDetails(String customerId, String fromDate, String toDate, String trackingNumbers, String invoiceIds){
        return prepareTrackingNumberWiseAuditDetails(parcelRTRDao.loadUpsParcelAuditDetails(customerId, fromDate, toDate, trackingNumbers, invoiceIds));
    }

    /**
     * Returns tracking number wise UPS parcel audit details.
     * @param fromDate
     * @param toDate
     * @return
     */
    public Map<String, List<ParcelAuditDetailsDto>> loadUpsParcelAuditDetails(String customerId, String fromDate, String toDate, String trackingNumbers){
        return prepareTrackingNumberWiseAuditDetails(parcelRTRDao.loadUpsParcelAuditDetails(customerId, fromDate, toDate, trackingNumbers, null));
    }

    /**
     * Returns tracking number wise non-UPS parcel audit details.
     * @param fromDate
     * @param toDate
     * @return
     */
    public Map<String, List<ParcelAuditDetailsDto>> loadNonUpsParcelAuditDetails(String customerId, String fromDate, String toDate, String trackingNumbers){
        return prepareTrackingNumberWiseAuditDetails(parcelRTRDao.loadNonUpsParcelAuditDetails(customerId, fromDate, toDate, ParcelAuditConstant.NON_UPS_CARRIER_IDS, trackingNumbers, null));
    }

    public Map<String, List<ParcelAuditDetailsDto>> loadNonUpsParcelAuditDetails(String customerId, String fromDate, String toDate, String trackingNumbers, String invoiceIds){
        return prepareTrackingNumberWiseAuditDetails(parcelRTRDao.loadNonUpsParcelAuditDetails(customerId, fromDate, toDate, ParcelAuditConstant.NON_UPS_CARRIER_IDS, trackingNumbers, invoiceIds));
    }

    /**
     *
     * @param invoiceId
     * @return
     */
    public Map<String, List<ParcelAuditDetailsDto>> loadUpsParcelAuditDetails(String invoiceId, String trackingNumbers){
        return prepareTrackingNumberWiseAuditDetails(parcelRTRDao.loadUpsParcelAuditDetails(null, null, null, trackingNumbers, invoiceId));
    }

    public Map<String, List<ParcelAuditDetailsDto>> loadNonUpsParcelAuditDetails(String invoiceId, String trackingNumbers){
        return prepareTrackingNumberWiseAuditDetails(parcelRTRDao.loadNonUpsParcelAuditDetails(null, null, null, ParcelAuditConstant.NON_UPS_CARRIER_IDS, trackingNumbers, invoiceId));
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

        doParcelRatingForUpsCarrier(loadUpsParcelAuditDetails(customerId, fromDate, toDate, trackingNumbers), url, licenseKey, customerId);
        doParcelRatingForNonUpsCarrier(loadNonUpsParcelAuditDetails(customerId, fromDate, toDate, trackingNumbers), url, licenseKey);
    }

    private void doParcelRatingForNonUpsCarrier(Map<String, List<ParcelAuditDetailsDto>> parcelAuditDetailsMap, String url, String licenseKey){
        if(parcelAuditDetailsMap != null && !parcelAuditDetailsMap.isEmpty()){
            int shipmentCount = 1;
            Iterator<Map.Entry<String, List<ParcelAuditDetailsDto>>> entryIterator = parcelAuditDetailsMap.entrySet().iterator();
            while(entryIterator.hasNext()){
                Map.Entry<String,List<ParcelAuditDetailsDto>> parcelAuditEntry = entryIterator.next();
                if(parcelAuditEntry != null){
                    try{
                        callRTRAndPopulateRates(url, licenseKey, parcelAuditEntry.getValue(), RateTo.NON_UPS);
                        System.out.println("Shipment Count :: " + shipmentCount++ + " :: " +parcelAuditEntry.getValue().get(0).getTrackingNumber());
                    }catch (Exception e){
                        System.out.println(e.getMessage());
                        //Do nothing
                    }
                }
                entryIterator.remove();
            }
        }
    }

    private void doParcelRatingForUpsCarrier(Map<String, List<ParcelAuditDetailsDto>> parcelAuditDetailsMap, String url, String licenseKey, String customerIds){
        if(parcelAuditDetailsMap != null && !parcelAuditDetailsMap.isEmpty()){
            int shipmentCount = 1;
            Iterator<Map.Entry<String, List<ParcelAuditDetailsDto>>> entryIterator = parcelAuditDetailsMap.entrySet().iterator();
            while(entryIterator.hasNext()){
                Map.Entry<String,List<ParcelAuditDetailsDto>> parcelAuditEntry = entryIterator.next();
                if(parcelAuditEntry != null){
                    try{
                        String trackingNumber = parcelAuditEntry.getKey();
                        List<ParcelAuditDetailsDto> shipmentRecords = null;
                        if(trackingNumber != null && !trackingNumber.isEmpty()){
                            shipmentRecords = parcelRTRDao.loadUpsParcelAuditDetails(customerIds, trackingNumber);
                        }

                        Map<Long, List<ParcelAuditDetailsDto>> shipments = ParcelRatingUtil.organiseShipmentsByParentId(shipmentRecords);
                        Iterator<Map.Entry<Long, List<ParcelAuditDetailsDto>>> shipmentIterator = shipments.entrySet().iterator();
                        while(shipmentIterator.hasNext()){
                            Map.Entry<Long, List<ParcelAuditDetailsDto>> shpEntry = shipmentIterator.next();
                            if(shpEntry != null){
                                callRTRAndPopulateRates(url, licenseKey, shpEntry.getValue(), RateTo.UPS);
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
        }
    }

    private void callRTRAndPopulateRates(String url, String licenseKey, List<ParcelAuditDetailsDto> parcelAuditDetails, RateTo rateTo) throws Exception {
    private String callRTRAndPopulateRates(String url, String licenseKey, List<ParcelAuditDetailsDto> parcelAuditDetails, RateTo rateTo,Map<String, String> dasChargeList) throws Exception {
        String requestPayload = "";
        String response = "";
        switch (rateTo){
            case UPS:
                requestPayload = ParcelRateRequestBuilder.buildParcelRateRequestForUps(parcelAuditDetails, licenseKey, dasChargeList).toXmlString();
                response = CommonUtil.connectAndGetResponseAsString(url, requestPayload);
                if(response != null && !response.trim().isEmpty()){
                    saveRequestResponse(requestPayload, response, parcelAuditDetails.get(0).getParentId(), ParcelAuditConstant.EBILL_GFF_TABLE_NAME);
                    status = updateRateForUps(ParcelRateResponseParser.parse(response), parcelAuditDetails, dasChargeList);
                    updateRateForUps(ParcelRateResponseParser.parse(response), parcelAuditDetails);
                }
                break;
            case NON_UPS:
                requestPayload = ParcelRateRequestBuilder.buildParcelRateRequestForNonUpsCarrier(parcelAuditDetails, licenseKey,dasChargeList).toXmlString();
                response = CommonUtil.connectAndGetResponseAsString(url, requestPayload);
                if(response != null && !response.trim().isEmpty()) {
                    saveRequestResponse(requestPayload, response, parcelAuditDetails.get(0).getParentId(), ParcelAuditConstant.EBILL_MANIFEST_TABLE_NAME);
                    status = updateRateForNonUpsCarrier(ParcelRateResponseParser.parse(response), parcelAuditDetails, dasChargeList);
                    updateRateForNonUpsCarrier(ParcelRateResponseParser.parse(response), parcelAuditDetails);
                }
                break;
        }
    }

    private void saveRequestResponse(String requestPayload, String response, Long entityId, String tableName) {
        ParcelAuditRequestResponseLog requestResponseLog = new ParcelAuditRequestResponseLog();
        requestResponseLog.setEntityIds(entityId.toString());
        requestResponseLog.setCreateUser(ParcelAuditConstant.PARCEL_RTR_RATING_USER_NAME);
        requestResponseLog.setTableName(tableName);
        if(requestPayload != null && !requestPayload.isEmpty()){
            int requestLength = requestPayload.length();
            if (requestLength <= 4000) {
                requestResponseLog.setRequestXml1(requestPayload);
            } else {
                requestResponseLog.setRequestXml1(requestPayload.substring(0, 3999));
                requestResponseLog.setRequestXml2(requestPayload.substring(4000, requestLength));
            }
        }

        if(response != null && !response.isEmpty()){
            int respLength = response.length();
            if (respLength <= 4000) {
                requestResponseLog.setResponseXml1(response);
            } else {
                requestResponseLog.setResponseXml1(response.substring(0, 3999));
                if(respLength >= 4000 && respLength < 8000){
                    requestResponseLog.setResponseXml2(response.substring(3999, respLength));
                }else{
                    try{  requestResponseLog.setResponseXml2(response.substring(3999, 7999));}catch (Exception e){}
                }
            }
        }
        parcelRTRDao.saveParcelAuditRequestAndResponseLog(requestResponseLog);
    }

    private String updateRateForNonUpsCarrier(ParcelRateResponse parcelRateResponse, List<ParcelAuditDetailsDto> parcelAuditDetails, Map<String, String> dasChargeList) throws Exception {
        String status = "";
        if(parcelRateResponse != null){
            if(parcelRateResponse.getStatusCode().equals(0)){
                if(parcelRateResponse.getPriceSheets() != null && !parcelRateResponse.getPriceSheets().isEmpty()){
                    //Taking the first price sheet, as per the discussion we finalised that the first price sheet will be be correct and rated based on the latest contract.
                    ParcelRateResponse.PriceSheet firstPriceSheet = parcelRateResponse.getPriceSheets().get(0);
                    BigDecimal sumOfNetAmount = findSumOfNetAmount(parcelAuditDetails);
                    BigDecimal totalRateAmount = firstPriceSheet.getTotal().setScale(2, BigDecimal.ROUND_HALF_EVEN);

                    BigDecimal toleranceLowerBound = sumOfNetAmount.multiply(new BigDecimal("0.995"));
                    BigDecimal toleranceUpperBound = sumOfNetAmount.multiply(new BigDecimal("1.005"));

                    if(sumOfNetAmount.compareTo(totalRateAmount) == 0 || (totalRateAmount.compareTo(toleranceLowerBound) >= 0 && totalRateAmount.compareTo(toleranceUpperBound) <= 0)){
                        status = updateAmountWithRTRResponseChargesForNonUpsCarrier(firstPriceSheet, parcelAuditDetails, RTRStatus.CLOSED, dasChargeList);
                    } else{
                        if(sumOfNetAmount.compareTo(totalRateAmount) < 0){
                            status = updateAmountWithRTRResponseChargesForNonUpsCarrier(firstPriceSheet, parcelAuditDetails, RTRStatus.UNDER_CHARGED, dasChargeList);
                        } else if(sumOfNetAmount.compareTo(totalRateAmount) > 0){
                            status = updateAmountWithRTRResponseChargesForNonUpsCarrier(firstPriceSheet, parcelAuditDetails, RTRStatus.OVER_CHARGED, dasChargeList);
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
     * @param dasChargeList
     * @throws Exception
     */
    private String updateAmountWithRTRResponseChargesForNonUpsCarrier(ParcelRateResponse.PriceSheet priceSheet, List<ParcelAuditDetailsDto> parcelAuditDetails, RTRStatus rtrStatus, Map<String, String> dasChargeList) throws Exception {
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
                if(!frtChargeFound && ParcelAuditConstant.ChargeClassificationCode.FRT.name().equalsIgnoreCase(auditDetails.getChargeClassificationCode())
                        && auditDetails.getNetAmount() != null && !auditDetails.getNetAmount().trim().isEmpty()){
                    double netAmount = Double.parseDouble(auditDetails.getNetAmount());
                    if(netAmount > 0){
                        charge = ParcelRateResponseParser.findChargeByType(ParcelRateResponse.ChargeType.ITEM.name(), priceSheet);
                        if(charge != null){
                            frtChargeFound = true;
                            parcelRTRDao.updateRTRInvoiceAmount(auditDetails.getId(), ParcelAuditConstant.PARCEL_RTR_RATING_USER_NAME, charge.getAmount(), rtrStatus.value, auditDetails.getCarrierId());

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

                            parcelRTRDao.updateShipmentRateDetails(ParcelAuditConstant.EBILL_MANIFEST_TABLE_NAME, auditDetails.getId().toString(), ParcelAuditConstant.PARCEL_RTR_RATING_USER_NAME, rateDetails);
                        }
                    }
                }else if(ParcelAuditConstant.ChargeClassificationCode.ACS.name().equalsIgnoreCase(auditDetails.getChargeClassificationCode())
                        && ParcelAuditConstant.ChargeDescriptionCode.FSC.name().equalsIgnoreCase(auditDetails.getChargeDescriptionCode())){
                    charge = ParcelRateResponseParser.findChargeByType(ParcelRateResponse.ChargeType.ACCESSORIAL_FUEL.name(), priceSheet);
                    if(charge != null){
                        parcelRTRDao.updateRTRInvoiceAmount(auditDetails.getId(), ParcelAuditConstant.PARCEL_RTR_RATING_USER_NAME, charge.getAmount(), rtrStatus.value, auditDetails.getCarrierId());

                        ParcelRateDetailsDto rateDetails = ParcelRateDetailsDto.getInstance();
                        rateDetails.setShipperCategory(shipperCategory);
                        rateDetails.setContractName(contractName);
                        rateDetails.setFuelTablePercentage(fuelTablePerc);
                        rateDetails.setRatedFuelSurchargeDiscount(ParcelRateResponseParser.getRatedSurchargeDiscount(priceSheet));
                        rateDetails.setRatedCustomFuelSurchargeDiscount(ParcelRateResponseParser.getRatedCustomSurchargeDiscount(priceSheet));
                        rateDetails.setRatedGrossFuel(ratedGrossFuel);
                        rateDetails.setDimDivisor(charge.getDimDivisor() == null ? new BigDecimal("0") : charge.getDimDivisor());
                        rateDetails.setRatedWeight(charge.getWeight() == null ? new BigDecimal("0") : charge.getWeight());

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

                        parcelRTRDao.updateRTRInvoiceAmount(auditDetails.getId(), ParcelAuditConstant.PARCEL_RTR_RATING_USER_NAME, charge.getAmount(), rtrStatus.value, auditDetails.getCarrierId());
                        parcelRTRDao.updateShipmentRateDetails(ParcelAuditConstant.EBILL_MANIFEST_TABLE_NAME, auditDetails.getId().toString(), ParcelAuditConstant.PARCEL_RTR_RATING_USER_NAME, rateDetails);
                    }
                }else if(ParcelAuditConstant.ChargeClassificationCode.ACS.name().equalsIgnoreCase(auditDetails.getChargeClassificationCode())
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
                        rateDetails.setFuelTablePercentage(fuelTablePerc);
                        rateDetails.setDimDivisor(charge.getDimDivisor() == null ? new BigDecimal("0") : charge.getDimDivisor());
                        rateDetails.setRatedWeight(charge.getWeight() == null ? new BigDecimal("0") : charge.getWeight());

                    parcelRTRDao.updateRTRInvoiceAmount(auditDetails.getId(), ParcelAuditConstant.PARCEL_RTR_RATING_USER_NAME, new BigDecimal("0"), rtrStatus.value, auditDetails.getCarrierId());
                }
            }
        }

        ParcelRateDetailsDto otherDscRateDetails = ParcelRateDetailsDto.getInstance();
        otherDscRateDetails.setShipperCategory(shipperCategory);
        otherDscRateDetails.setContractName(contractName);
        saveOtherDiscountsAppliedForNonUps(priceSheet, parcelAuditDetails, otherDscRateDetails);

        ParcelRateDetailsDto accessorialRateDetails = ParcelRateDetailsDto.getInstance();
        accessorialRateDetails.setShipperCategory(shipperCategory);
        accessorialRateDetails.setContractName(contractName);
        saveAccessorialForNonUps(priceSheet, parcelAuditDetails, accessorialRateDetails);

                        parcelRTRDao.updateRTRInvoiceAmount(auditDetails.getId(), ParcelAuditConstant.PARCEL_RTR_RATING_USER_NAME, charge.getAmount(), rtrStatus.value, auditDetails.getCarrierId());
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
                        parcelRTRDao.updateRTRInvoiceAmount(auditDetails.getId(), ParcelAuditConstant.PARCEL_RTR_RATING_USER_NAME, new BigDecimal("0"), rtrStatus.value, auditDetails.getCarrierId());
                        parcelRTRDao.updateShipmentRateDetails(ParcelAuditConstant.EBILL_MANIFEST_TABLE_NAME, auditDetails.getId().toString(), ParcelAuditConstant.PARCEL_RTR_RATING_USER_NAME, rateDetails);
                    }
                }

                if(charge == null){
                    ParcelRateDetailsDto rateDetails = ParcelRateDetailsDto.getInstance();
                    rateDetails.setShipperCategory(shipperCategory);
                    rateDetails.setContractName(contractName);
                    rateDetails.setFuelTablePercentage(fuelTablePerc);

                    parcelRTRDao.updateRTRInvoiceAmount(auditDetails.getId(), ParcelAuditConstant.PARCEL_RTR_RATING_USER_NAME, new BigDecimal("0"), rtrStatus.value, auditDetails.getCarrierId());
                    //parcelRTRDao.updateShipmentRateDetails(ParcelAuditConstant.EBILL_MANIFEST_TABLE_NAME, auditDetails.getId().toString(), ParcelAuditConstant.PARCEL_RTR_RATING_USER_NAME, rateDetails);
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

    private String updateRateForUps(ParcelRateResponse parcelRateResponse, List<ParcelAuditDetailsDto> parcelAuditDetails,Map<String, String> dasChargeList) throws Exception {
        String status = "";
        if(parcelRateResponse != null){
            if(parcelRateResponse.getStatusCode() != null && parcelRateResponse.getStatusCode().equals(0)){
                if(parcelRateResponse.getPriceSheets() != null && !parcelRateResponse.getPriceSheets().isEmpty()){
                    //Taking the first price sheet, as per the discussion we finalised that the first price sheet will be be correct and rated based on the latest contract.
                    ParcelRateResponse.PriceSheet firstPriceSheet = parcelRateResponse.getPriceSheets().get(0);
                    BigDecimal sumOfNetAmount = findSumOfNetAmount(parcelAuditDetails);
                    BigDecimal totalRateAmount = firstPriceSheet.getTotal().setScale(2, BigDecimal.ROUND_HALF_EVEN);

                    BigDecimal toleranceLowerBound = sumOfNetAmount.multiply(new BigDecimal("0.995"));
                    BigDecimal toleranceUpperBound = sumOfNetAmount.multiply(new BigDecimal("1.005"));

                    if(sumOfNetAmount.compareTo(totalRateAmount) == 0 || (totalRateAmount.compareTo(toleranceLowerBound) >= 0 && totalRateAmount.compareTo(toleranceUpperBound) <= 0)){
                        status = updateAmountWithRTRResponseChargesForUps(firstPriceSheet, parcelAuditDetails, RTRStatus.CLOSED,dasChargeList);
                    } else {
                        if(sumOfNetAmount.compareTo(totalRateAmount) < 0){
                            status = updateAmountWithRTRResponseChargesForUps(firstPriceSheet, parcelAuditDetails, RTRStatus.UNDER_CHARGED,dasChargeList);
                        } else if(sumOfNetAmount.compareTo(totalRateAmount) > 0){
                            status = updateAmountWithRTRResponseChargesForUps(firstPriceSheet, parcelAuditDetails, RTRStatus.OVER_CHARGED,dasChargeList);
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

    private String updateAmountWithRTRResponseChargesForUps(ParcelRateResponse.PriceSheet priceSheet, List<ParcelAuditDetailsDto> parcelAuditDetails, RTRStatus rtrStatus,Map<String, String> dasChargeList) throws Exception {
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

                                parcelRTRDao.updateShipmentRateDetails(ParcelAuditConstant.EBILL_GFF_TABLE_NAME, auditDetails.getId().toString(), ParcelAuditConstant.PARCEL_RTR_RATING_USER_NAME, rateDetails);
                                parcelRTRDao.updateRTRInvoiceAmount(auditDetails.getId(), ParcelAuditConstant.PARCEL_RTR_RATING_USER_NAME, charge.getAmount(), rtrStatus.value, auditDetails.getCarrierId());
                            }
                            frtChargeFound = true;
                        }else{
                            parcelRTRDao.updateRTRInvoiceAmount(auditDetails.getId(), ParcelAuditConstant.PARCEL_RTR_RATING_USER_NAME, new BigDecimal("0"), rtrStatus.value, auditDetails.getCarrierId());
                        }
                    }else{
                        parcelRTRDao.updateRTRInvoiceAmount(auditDetails.getId(), ParcelAuditConstant.PARCEL_RTR_RATING_USER_NAME, new BigDecimal("0"), rtrStatus.value, auditDetails.getCarrierId());
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

                        parcelRTRDao.updateRTRInvoiceAmount(auditDetails.getId(), ParcelAuditConstant.PARCEL_RTR_RATING_USER_NAME, charge.getAmount(), rtrStatus.value, auditDetails.getCarrierId());
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

                        parcelRTRDao.updateRTRInvoiceAmount(auditDetails.getId(), ParcelAuditConstant.PARCEL_RTR_RATING_USER_NAME, charge.getAmount(), rtrStatus.value, auditDetails.getCarrierId());
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

                        parcelRTRDao.updateRTRInvoiceAmount(auditDetails.getId(), ParcelAuditConstant.PARCEL_RTR_RATING_USER_NAME, charge.getAmount(), rtrStatus.value, auditDetails.getCarrierId());
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

                        parcelRTRDao.updateRTRInvoiceAmount(auditDetails.getId(), ParcelAuditConstant.PARCEL_RTR_RATING_USER_NAME, charge.getAmount(), rtrStatus.value, auditDetails.getCarrierId());
                    }
                }

                if(null == charge){
                    ParcelRateDetailsDto rateDetails = ParcelRateDetailsDto.getInstance();
                    rateDetails.setShipperCategory(shipperCategory);
                    rateDetails.setContractName(contractName);
                    rateDetails.setFuelTablePercentage(fuelTablePerc);

                    parcelRTRDao.updateRTRInvoiceAmount(auditDetails.getId(), ParcelAuditConstant.PARCEL_RTR_RATING_USER_NAME, new BigDecimal("0"), rtrStatus.value, auditDetails.getCarrierId());
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
        saveOtherDiscountsAppliedForUps(priceSheet, parcelAuditDetails, otherDscRateDetails, mappedDscChanges);

        ParcelRateDetailsDto accessorialRateDetails = ParcelRateDetailsDto.getInstance();
        accessorialRateDetails.setShipperCategory(shipperCategory);
        accessorialRateDetails.setContractName(contractName);
        saveAccessorialForUps(priceSheet, parcelAuditDetails, accessorialRateDetails, mappedAccChanges);

        return rtrStatus.value;
    }

    private void saveAccessorialForUps(ParcelRateResponse.PriceSheet priceSheet, List<ParcelAuditDetailsDto> parcelAuditDetails, ParcelRateDetailsDto rateDetails, List<ParcelRateResponse.Charge> mappedAccChanges) {
        try{
            if(priceSheet != null && parcelAuditDetails != null && !parcelAuditDetails.isEmpty()){
                StringJoiner entityIds = new StringJoiner(",");
                parcelAuditDetails.forEach(auditDetail -> {
                    entityIds.add(auditDetail.getId().toString());
                });
                List<ParcelRateResponse.Charge> accessorialCharges = ParcelRateResponseParser.getAccessorialChargesForUps(priceSheet);
                if(accessorialCharges != null && !accessorialCharges.isEmpty()){
                    if(accessorialCharges.size() == 1){
                        rateDetails.setAccessorial1(accessorialCharges.get(0).getAmount());
                    }else if(accessorialCharges.size() == 2){
                        rateDetails.setAccessorial1(accessorialCharges.get(0).getAmount());
                        rateDetails.setAccessorial2(accessorialCharges.get(1).getAmount());
                    }else if(accessorialCharges.size() >= 3){
                        rateDetails.setAccessorial1(accessorialCharges.get(0).getAmount());
                        rateDetails.setAccessorial2(accessorialCharges.get(1).getAmount());
                        rateDetails.setAccessorial3(accessorialCharges.get(2).getAmount());
                    }
                    parcelRTRDao.updateAccessorialShipmentRateDetails(ParcelAuditConstant.EBILL_GFF_TABLE_NAME, entityIds.toString(), ParcelAuditConstant.PARCEL_RTR_RATING_USER_NAME, rateDetails);
                }
            }
        }catch (Exception e){}
    }

    private void saveAccessorialForNonUps(ParcelRateResponse.PriceSheet priceSheet, List<ParcelAuditDetailsDto> parcelAuditDetails, ParcelRateDetailsDto rateDetails) {
        try{
            if(priceSheet != null && parcelAuditDetails != null && !parcelAuditDetails.isEmpty()){
                StringJoiner entityIds = new StringJoiner(",");
                parcelAuditDetails.forEach(auditDetail -> {
                    entityIds.add(auditDetail.getId().toString());
                });
                List<ParcelRateResponse.Charge> accessorialCharges = ParcelRateResponseParser.getAccessorialChargesForFedEx(priceSheet);
                if(accessorialCharges != null && !accessorialCharges.isEmpty()){
                    if(accessorialCharges.size() == 1){
                        rateDetails.setAccessorial1(accessorialCharges.get(0).getAmount());
                    }else if(accessorialCharges.size() == 2){
                        rateDetails.setAccessorial1(accessorialCharges.get(0).getAmount());
                        rateDetails.setAccessorial2(accessorialCharges.get(1).getAmount());
                    }else if(accessorialCharges.size() >= 3){
                        rateDetails.setAccessorial1(accessorialCharges.get(0).getAmount());
                        rateDetails.setAccessorial2(accessorialCharges.get(1).getAmount());
                        rateDetails.setAccessorial3(accessorialCharges.get(2).getAmount());
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
                List<ParcelRateResponse.Charge> discountCharges = ParcelRateResponseParser.getAllOtherDiscountsForUPSCarrier(priceSheet);
                if(discountCharges != null && !discountCharges.isEmpty()){
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

    private void saveOtherDiscountsAppliedForNonUps(ParcelRateResponse.PriceSheet priceSheet, List<ParcelAuditDetailsDto> parcelAuditDetails, ParcelRateDetailsDto rateDetails) {
        try{
            if(priceSheet != null && parcelAuditDetails != null && !parcelAuditDetails.isEmpty()){
                StringJoiner entityIds = new StringJoiner(",");
                parcelAuditDetails.forEach(auditDetail -> {
                    entityIds.add(auditDetail.getId().toString());
                });
                List<ParcelRateResponse.Charge> discountCharges = ParcelRateResponseParser.getAllOtherDiscountsForUPSCarrier(priceSheet);
                if(discountCharges != null && !discountCharges.isEmpty()){
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
                parcelRTRDao.updateInvoiceAmountByIds(entityIds.toString(), ParcelAuditConstant.PARCEL_RTR_RATING_USER_NAME, rtrStatus.value, parcelAuditDetails.get(0).getCarrierId());
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
            Map<String, String>  dasChargeList= parcelRTRDao.loadDASChargeDetails();

            for (ParcelAuditDetailsDto inv : invoiceList) {
                if (inv != null && inv.getInvoiceId() != null) {
                    System.out.println(inv.getInvoiceId());
                    if (rateTo == null || rateTo.isEmpty()) {
                        upsShipmentRateStatus = doParcelRating(loadUpsParcelAuditDetails(customerIds, fromShipDate, toShipDate, trackingNumbers, inv.getInvoiceId().toString()), url, licenseKey, RateTo.UPS,dasChargeList);
                        nonUpsShipmentRateStatus = doParcelRating(loadNonUpsParcelAuditDetails(customerIds, fromShipDate, toShipDate, trackingNumbers, inv.getInvoiceId().toString()), url, licenseKey, RateTo.NON_UPS,dasChargeList);
                        doParcelRatingForUpsCarrier(loadUpsParcelAuditDetails(customerIds, fromShipDate, toShipDate, trackingNumbers, inv.getInvoiceId().toString()), url, licenseKey, customerIds);
                        doParcelRatingForNonUpsCarrier(loadNonUpsParcelAuditDetails(customerIds, fromShipDate, toShipDate, trackingNumbers, inv.getInvoiceId().toString()), url, licenseKey);
                    } else if ("UPS".equalsIgnoreCase(rateTo)) {
                        upsShipmentRateStatus = doParcelRating(loadUpsParcelAuditDetails(customerIds, fromShipDate, toShipDate, trackingNumbers, inv.getInvoiceId().toString()), url, licenseKey, RateTo.UPS,dasChargeList);
                        doParcelRatingForUpsCarrier(loadUpsParcelAuditDetails(customerIds, fromShipDate, toShipDate, trackingNumbers, inv.getInvoiceId().toString()), url, licenseKey, customerIds);
                    } else if ("FEDEX".equalsIgnoreCase(rateTo)) {
                        nonUpsShipmentRateStatus = doParcelRating(loadNonUpsParcelAuditDetails(customerIds, fromShipDate, toShipDate, trackingNumbers, inv.getInvoiceId().toString()), url, licenseKey, RateTo.NON_UPS,dasChargeList);
                        doParcelRatingForNonUpsCarrier(loadNonUpsParcelAuditDetails(customerIds, fromShipDate, toShipDate, trackingNumbers, inv.getInvoiceId().toString()), url, licenseKey);
                    }
                }
            }
        }
    }

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
        try{
            if(priceSheet != null && parcelAuditDetails != null && !parcelAuditDetails.isEmpty()){
                StringJoiner entityIds = new StringJoiner(",");
                parcelAuditDetails.forEach(auditDetail -> {
                    entityIds.add(auditDetail.getId().toString());
                });
                List<ParcelRateResponse.Charge> accessorialCharges = ParcelRateResponseParser.getAccessorialChargesForUps(priceSheet);

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
                    }else if(accessorialCharges.size() == 2){
                        rateDetails.setAccessorial1(accessorialCharges.get(0).getAmount());
                        rateDetails.setAccessorial2(accessorialCharges.get(1).getAmount());
                    }else if(accessorialCharges.size() >= 3){
                        rateDetails.setAccessorial1(accessorialCharges.get(0).getAmount());
                        rateDetails.setAccessorial2(accessorialCharges.get(1).getAmount());
                        rateDetails.setAccessorial3(accessorialCharges.get(2).getAmount());
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
                List<ParcelRateResponse.Charge> accessorialCharges = ParcelRateResponseParser.getAccessorialChargesForFedEx(priceSheet);
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
                    }else if(accessorialCharges.size() == 2){
                        rateDetails.setAccessorial1(accessorialCharges.get(0).getAmount());
                        rateDetails.setAccessorial2(accessorialCharges.get(1).getAmount());
                    }else if(accessorialCharges.size() >= 3){
                        rateDetails.setAccessorial1(accessorialCharges.get(0).getAmount());
                        rateDetails.setAccessorial2(accessorialCharges.get(1).getAmount());
                        rateDetails.setAccessorial3(accessorialCharges.get(2).getAmount());
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
                List<ParcelRateResponse.Charge> discountCharges = ParcelRateResponseParser.getAllOtherDiscountsForUPSCarrier(priceSheet);
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
                List<ParcelRateResponse.Charge> discountCharges = ParcelRateResponseParser.getAllOtherDiscountsForUPSCarrier(priceSheet);
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
}
