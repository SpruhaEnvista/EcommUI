package com.envista.msi.api.service.rtr;

import com.envista.msi.api.dao.rtr.ParcelRTRDao;
import com.envista.msi.api.web.rest.dto.rtr.ParcelAuditDetailsDto;
import com.envista.msi.api.web.rest.dto.rtr.ParcelAuditRequestResponseLog;
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

        doParcelRating(loadUpsParcelAuditDetails(customerId, fromDate, toDate, trackingNumbers), url, licenseKey, RateTo.UPS);
        doParcelRating(loadNonUpsParcelAuditDetails(customerId, fromDate, toDate, trackingNumbers), url, licenseKey, RateTo.NON_UPS);
    }

    private Map<String, String> doParcelRating(Map<String, List<ParcelAuditDetailsDto>> parcelAuditDetailsMap, String url, String licenseKey, RateTo rateTo){
        Map<String, String> shipmentRateStatus = null;
        if(parcelAuditDetailsMap != null && !parcelAuditDetailsMap.isEmpty()){
            shipmentRateStatus = new HashMap<>();
            for(Map.Entry<String, List<ParcelAuditDetailsDto>> parcelAuditEntry : parcelAuditDetailsMap.entrySet()){
                if(parcelAuditEntry != null){
                    try{
                        String status = callRTRAndPopulateRates(url, licenseKey, parcelAuditEntry.getValue(), rateTo);
                        shipmentRateStatus.put(parcelAuditEntry.getValue().get(0).getTrackingNumber(), status);
                    }catch (Exception e){
                        System.out.println(e.getMessage());
                        //Do nothing
                    }
                }
            }
        }
        return shipmentRateStatus;
    }

    private String callRTRAndPopulateRates(String url, String licenseKey, List<ParcelAuditDetailsDto> parcelAuditDetails, RateTo rateTo) throws Exception {
        String requestPayload = "";
        String response = "";
        String trackingNumber = parcelAuditDetails.get(0).getTrackingNumber();
        String status = "";
        switch (rateTo){
            case UPS:
                requestPayload = ParcelRateRequestBuilder.buildParcelRateRequestForUps(parcelAuditDetails, licenseKey).toXmlString();
                response = CommonUtil.connectAndGetResponseAsString(url, requestPayload);
                if(response != null && !response.trim().isEmpty()){
                    saveRequestResponse(requestPayload, response, trackingNumber);
                    status = updateRateForUps(ParcelRateResponseParser.parse(response), parcelAuditDetails);
                }
                break;
            case NON_UPS:
                requestPayload = ParcelRateRequestBuilder.buildParcelRateRequestForNonUpsCarrier(parcelAuditDetails, licenseKey).toXmlString();
                response = CommonUtil.connectAndGetResponseAsString(url, requestPayload);
                if(response != null && !response.trim().isEmpty()) {
                    saveRequestResponse(requestPayload, response, trackingNumber);
                    status = updateRateForNonUpsCarrier(ParcelRateResponseParser.parse(response), parcelAuditDetails);
                }
                break;
        }
        return status;
    }

    private void saveRequestResponse(String requestPayload, String response, String trackingNumber) {
        ParcelAuditRequestResponseLog requestResponseLog = new ParcelAuditRequestResponseLog();
        requestResponseLog.setCreateUser("Avatar");
        if(requestPayload != null && !requestPayload.isEmpty()){
            int requestLength = requestPayload.length();
            if (requestLength <= 4000) {
                requestResponseLog.setRequestXml(requestPayload);
            } else {
                requestResponseLog.setRequestXml(requestPayload.substring(0, 3999));
                requestResponseLog.setRequestXml1(requestPayload.substring(4000, requestLength));
            }
        }

        if(response != null && !response.isEmpty()){
            int respLength = response.length();
            if (respLength <= 4000) {
                requestResponseLog.setResponseXml(response);
            } else {
                requestResponseLog.setResponseXml(response.substring(0, 3999));
                if(respLength >= 4000 && respLength < 8000){
                    requestResponseLog.setResponseXml1(response.substring(3999, respLength));
                }else{
                    try{  requestResponseLog.setResponseXml1(response.substring(3999, 7999));}catch (Exception e){}
                }
            }
        }
        requestResponseLog.setResponseXml2(trackingNumber);
        parcelRTRDao.saveParcelAuditRequestAndResponseLog(requestResponseLog);
    }

    private String updateRateForNonUpsCarrier(ParcelRateResponse parcelRateResponse, List<ParcelAuditDetailsDto> parcelAuditDetails) throws Exception {
        String status = "";
        if(parcelRateResponse != null){
            if(parcelRateResponse.getStatusCode().equals(0)){
                if(parcelRateResponse.getPriceSheets() != null && !parcelRateResponse.getPriceSheets().isEmpty()){
                    if(parcelRateResponse.getPriceSheets().size() == 1){
                        ParcelRateResponse.PriceSheet firstPriceSheet = parcelRateResponse.getPriceSheets().get(0);
                        BigDecimal sumOfNetAmount = findSumOfNetAmount(parcelAuditDetails);
                        BigDecimal totalRateAmount = findTotalRateAmountFromResponseForNonUpsCarrier(parcelAuditDetails, firstPriceSheet);

                        if(sumOfNetAmount.compareTo(totalRateAmount) < 0){
                            status = updateAmountWithRTRResponseChargesForNonUpsCarrier(firstPriceSheet, parcelAuditDetails, RTRStatus.UNDER_CHARGED);
                        } else if(sumOfNetAmount.compareTo(totalRateAmount) > 0){
                            status = updateAmountWithRTRResponseChargesForNonUpsCarrier(firstPriceSheet, parcelAuditDetails, RTRStatus.OVER_CHARGED);
                        } else {
                            status = updateAmountWithRTRResponseChargesForNonUpsCarrier(firstPriceSheet, parcelAuditDetails, RTRStatus.CLOSED);
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

    private BigDecimal findTotalRateAmountFromResponseForUpsCarrier(List<ParcelAuditDetailsDto> parcelAuditDetails, ParcelRateResponse.PriceSheet priceSheet) {
        BigDecimal totalRateAmount = new BigDecimal("0.0");
        if(priceSheet != null){
            boolean frtChargeFound = false;
            for(ParcelAuditDetailsDto auditDetails : parcelAuditDetails){
                if(auditDetails != null && auditDetails.getChargeClassificationCode() != null && !auditDetails.getChargeClassificationCode().isEmpty()){
                    ParcelRateResponse.Charge charge = null;
                    if(ParcelAuditConstant.ChargeClassificationCode.FRT.name().equalsIgnoreCase(auditDetails.getChargeClassificationCode())){
                        if(!frtChargeFound && auditDetails.getNetAmount() != null && !auditDetails.getNetAmount().trim().isEmpty()){
                            double netAmount = Double.parseDouble(auditDetails.getNetAmount());
                            if(netAmount > 0){
                                charge = ParcelRateResponseParser.findChargeByType(ParcelRateResponse.ChargeType.ITEM.name(), priceSheet);
                                if(charge != null){
                                    totalRateAmount = totalRateAmount.add(charge.getRate());
                                }
                                frtChargeFound = true;
                            }
                        }
                    }else if(ParcelAuditConstant.ChargeClassificationCode.FSC.name().equalsIgnoreCase(auditDetails.getChargeClassificationCode())){
                        charge = ParcelRateResponseParser.findChargeByType(ParcelRateResponse.ChargeType.ACCESSORIAL_FUEL.name(), priceSheet);
                        if(charge != null){
                            totalRateAmount = totalRateAmount.add(charge.getAmount());
                        }
                    }else{
                        charge = ParcelRateResponseParser.findChargeByEDICodeInResponse(auditDetails.getChargeClassificationCode(), priceSheet);
                        if(charge != null){
                            totalRateAmount = totalRateAmount.add(charge.getAmount());
                        }
                    }
                }
            }
        }
        return totalRateAmount;
    }

    private BigDecimal findTotalRateAmountFromResponseForNonUpsCarrier(List<ParcelAuditDetailsDto> parcelAuditDetails, ParcelRateResponse.PriceSheet priceSheet) {
        BigDecimal totalRateAmount = new BigDecimal("0.0");
        boolean frtChargeFound = false;
        boolean isDiscountApplied = false;
        for(ParcelAuditDetailsDto auditDetails : parcelAuditDetails){
            if(auditDetails != null && auditDetails.getChargeClassificationCode() != null && !auditDetails.getChargeClassificationCode().isEmpty()){
                ParcelRateResponse.Charge charge = null;
                if(!frtChargeFound && ParcelAuditConstant.ChargeClassificationCode.FRT.name().equalsIgnoreCase(auditDetails.getChargeClassificationCode())
                        && auditDetails.getNetAmount() != null && !auditDetails.getNetAmount().trim().isEmpty()){
                    double netAmount = Double.parseDouble(auditDetails.getNetAmount());
                    if(netAmount > 0){
                        charge = ParcelRateResponseParser.findChargeByType(ParcelRateResponse.ChargeType.ITEM.name(), priceSheet);
                        frtChargeFound = true;
                    }
                }else if(ParcelAuditConstant.ChargeClassificationCode.ACS.name().equalsIgnoreCase(auditDetails.getChargeClassificationCode())
                        && ParcelAuditConstant.ChargeDescriptionCode.FSC.name().equalsIgnoreCase(auditDetails.getChargeDescriptionCode())){
                    charge = ParcelRateResponseParser.findChargeByType(ParcelRateResponse.ChargeType.ACCESSORIAL_FUEL.name(), priceSheet);
                }else if(ParcelAuditConstant.ChargeClassificationCode.ACS.name().equalsIgnoreCase(auditDetails.getChargeClassificationCode())
                        && ParcelAuditConstant.ChargeDescriptionCode.DSC.name().equalsIgnoreCase(auditDetails.getChargeDescriptionCode())){
                    isDiscountApplied = true;
                }else {
                    charge = ParcelRateResponseParser.findChargeByEDICodeInResponse(auditDetails.getChargeClassificationCode(), priceSheet);
                }

                if(charge != null){
                    totalRateAmount = totalRateAmount.add(charge.getAmount());
                }
            }
            if(isDiscountApplied){
                List<ParcelRateResponse.Charge> discountCharges = ParcelRateResponseParser.getAllRatedDiscountForFedEx(priceSheet);
                if(discountCharges != null && !discountCharges.isEmpty()){
                    for(ParcelRateResponse.Charge discount : discountCharges){
                        if(discount != null && discount.getAmount() != null){
                            totalRateAmount = totalRateAmount.add(discount.getAmount());
                        }
                    }
                }
            }
        }
        return totalRateAmount;
    }

    /**
     * To update RTR amount/rate for each record in the shipment.
     * If shipment charge type is not found in the response the update the record with Zero amount.
     *
     * @param priceSheet
     * @param parcelAuditDetails
     * @throws Exception
     */
    private String updateAmountWithRTRResponseChargesForNonUpsCarrier(ParcelRateResponse.PriceSheet priceSheet, List<ParcelAuditDetailsDto> parcelAuditDetails, RTRStatus rtrStatus) throws Exception {
        boolean hasDiscount = false;
        boolean frtChargeFound = false;
        List<ParcelAuditDetailsDto> billedDiscountCharges = new ArrayList<>();
        String shipperCategory = priceSheet.getCategory();
        for(ParcelAuditDetailsDto auditDetails : parcelAuditDetails){
            if(ParcelAuditConstant.ChargeClassificationCode.ACS.name().equalsIgnoreCase(auditDetails.getChargeClassificationCode())
                    && ParcelAuditConstant.ChargeDescriptionCode.DSC.name().equalsIgnoreCase(auditDetails.getChargeDescriptionCode())){
                hasDiscount = true;
                billedDiscountCharges.add(auditDetails);
            }
            if(auditDetails != null && auditDetails.getChargeClassificationCode() != null && !auditDetails.getChargeClassificationCode().isEmpty()){
                ParcelRateResponse.Charge charge = null;
                if(!frtChargeFound && ParcelAuditConstant.ChargeClassificationCode.FRT.name().equalsIgnoreCase(auditDetails.getChargeClassificationCode())
                        && auditDetails.getNetAmount() != null && !auditDetails.getNetAmount().trim().isEmpty()){
                    double netAmount = Double.parseDouble(auditDetails.getNetAmount());
                    if(netAmount > 0){
                        charge = ParcelRateResponseParser.findChargeByType(ParcelRateResponse.ChargeType.ITEM.name(), priceSheet);
                        frtChargeFound = true;
                    }
                }else if(ParcelAuditConstant.ChargeClassificationCode.ACS.name().equalsIgnoreCase(auditDetails.getChargeClassificationCode())
                        && ParcelAuditConstant.ChargeDescriptionCode.FSC.name().equalsIgnoreCase(auditDetails.getChargeDescriptionCode())){
                    charge = ParcelRateResponseParser.findChargeByType(ParcelRateResponse.ChargeType.ACCESSORIAL_FUEL.name(), priceSheet);
                }else {
                    //need to clarify this step, whether it is required for NonUPS Carrier or not.
                    charge = ParcelRateResponseParser.findChargeByEDICodeInResponse(auditDetails.getChargeClassificationCode(), priceSheet);
                }

                if(charge != null){
                    parcelRTRDao.updateRTRInvoiceAmount(auditDetails.getId(), ParcelAuditConstant.PARCEL_RTR_RATING_USER_NAME, charge.getAmount(), rtrStatus.value, auditDetails.getCarrierId());
                    parcelRTRDao.updateShipmentRateDetails(ParcelAuditConstant.EBILL_MANIFEST_TABLE_NAME, auditDetails.getId().toString(), ParcelAuditConstant.PARCEL_RTR_RATING_USER_NAME, charge.getDimDivisor() == null ? new BigDecimal("0") : charge.getDimDivisor(), shipperCategory);
                }else{
                    parcelRTRDao.updateRTRInvoiceAmount(auditDetails.getId(), ParcelAuditConstant.PARCEL_RTR_RATING_USER_NAME, new BigDecimal("0"), rtrStatus.value, auditDetails.getCarrierId());
                    parcelRTRDao.updateShipmentRateDetails(ParcelAuditConstant.EBILL_MANIFEST_TABLE_NAME, auditDetails.getId().toString(), ParcelAuditConstant.PARCEL_RTR_RATING_USER_NAME, new BigDecimal("0"), shipperCategory);
                }
            }
        }

        if(hasDiscount){ //if discounts applied at shipment level.
            List<ParcelRateResponse.Charge> ratedDiscountForFedExList = ParcelRateResponseParser.getRatedDiscountForFedEx(priceSheet);
            int billedDiscountCount =  billedDiscountCharges.size();
            int ratedDiscountCount = ratedDiscountForFedExList == null ? 0 : ratedDiscountForFedExList.size();

            if(ratedDiscountCount > billedDiscountCount){
                Long carrierId = billedDiscountCharges.get(0).getCarrierId();
                for(int i = 0; i < billedDiscountCount - 1; i++){
                    parcelRTRDao.updateRTRInvoiceAmount(billedDiscountCharges.get(i).getId(), ParcelAuditConstant.PARCEL_RTR_RATING_USER_NAME, ratedDiscountForFedExList.get(i).getAmount(), rtrStatus.value, carrierId);
                }
                BigDecimal remainingDiscount = new BigDecimal("0.0");
                for(int j = billedDiscountCount - 1; j < ratedDiscountCount; j++){
                    remainingDiscount = remainingDiscount.add(ratedDiscountForFedExList.get(j).getAmount());
                }
                ParcelAuditDetailsDto lestBilledDiscount = billedDiscountCharges.get(billedDiscountCount - 1);
                parcelRTRDao.updateRTRInvoiceAmount(lestBilledDiscount.getId(), ParcelAuditConstant.PARCEL_RTR_RATING_USER_NAME, remainingDiscount, rtrStatus.value, carrierId);
            }else{
                int index = 0;
                for(ParcelAuditDetailsDto auditDetails : parcelAuditDetails){
                   if(auditDetails != null && ParcelAuditConstant.ChargeClassificationCode.ACS.name().equalsIgnoreCase(auditDetails.getChargeClassificationCode())
                           && ParcelAuditConstant.ChargeDescriptionCode.DSC.name().equalsIgnoreCase(auditDetails.getChargeDescriptionCode())){
                       ParcelRateResponse.Charge discountCharge = null;
                       try{
                           discountCharge = ratedDiscountForFedExList != null ? ratedDiscountForFedExList.get(index++) : null;
                       }catch (Exception e){
                           discountCharge = null;
                           //Nothing
                       }
                       if(discountCharge != null){
                           parcelRTRDao.updateRTRInvoiceAmount(auditDetails.getId(), ParcelAuditConstant.PARCEL_RTR_RATING_USER_NAME, discountCharge.getAmount(), rtrStatus.value, auditDetails.getCarrierId());
                       }else{
                           parcelRTRDao.updateRTRInvoiceAmount(auditDetails.getId(), ParcelAuditConstant.PARCEL_RTR_RATING_USER_NAME, new BigDecimal("0"), rtrStatus.value, auditDetails.getCarrierId());
                       }
                   }
                }
            }
        }
        return rtrStatus.value;
    }

    private String updateRateForUps(ParcelRateResponse parcelRateResponse, List<ParcelAuditDetailsDto> parcelAuditDetails) throws Exception {
        String status = "";
        if(parcelRateResponse != null){
            if(parcelRateResponse.getStatusCode() != null && parcelRateResponse.getStatusCode().equals(0)){
                if(parcelRateResponse.getPriceSheets() != null && !parcelRateResponse.getPriceSheets().isEmpty()){
                    if(parcelRateResponse.getPriceSheets().size() == 1){
                        ParcelRateResponse.PriceSheet firstPriceSheet = parcelRateResponse.getPriceSheets().get(0);
                        BigDecimal sumOfNetAmount = findSumOfNetAmount(parcelAuditDetails);
                        BigDecimal totalRateAmount = findTotalRateAmountFromResponseForUpsCarrier(parcelAuditDetails, firstPriceSheet);

                        if(sumOfNetAmount.compareTo(totalRateAmount) < 0){
                            status = updateAmountWithRTRResponseChargesForUps(firstPriceSheet, parcelAuditDetails, RTRStatus.UNDER_CHARGED);
                        } else if(sumOfNetAmount.compareTo(totalRateAmount) > 0){
                            status = updateAmountWithRTRResponseChargesForUps(firstPriceSheet, parcelAuditDetails, RTRStatus.OVER_CHARGED);
                        } else {
                            status = updateAmountWithRTRResponseChargesForUps(firstPriceSheet, parcelAuditDetails, RTRStatus.CLOSED);
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

    private String updateAmountWithRTRResponseChargesForUps(ParcelRateResponse.PriceSheet priceSheet, List<ParcelAuditDetailsDto> parcelAuditDetails, RTRStatus rtrStatus) throws Exception {
        boolean frtChargeFound = false;
        String shipperCategory = priceSheet.getCategory();
        for(ParcelAuditDetailsDto auditDetails : parcelAuditDetails){
            if(auditDetails != null && auditDetails.getChargeClassificationCode() != null && !auditDetails.getChargeClassificationCode().isEmpty()){
                ParcelRateResponse.Charge charge = null;
                if(ParcelAuditConstant.ChargeClassificationCode.FRT.name().equalsIgnoreCase(auditDetails.getChargeClassificationCode())){
                    if(!frtChargeFound && auditDetails.getNetAmount() != null && !auditDetails.getNetAmount().trim().isEmpty()){
                        double netAmount = Double.parseDouble(auditDetails.getNetAmount());
                        if(netAmount > 0){
                            charge = ParcelRateResponseParser.findChargeByType(ParcelRateResponse.ChargeType.ITEM.name(), priceSheet);
                            if(charge != null){
                                parcelRTRDao.updateRTRInvoiceAmount(auditDetails.getId(), ParcelAuditConstant.PARCEL_RTR_RATING_USER_NAME, charge.getRate(), rtrStatus.value, auditDetails.getCarrierId());
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
                        parcelRTRDao.updateRTRInvoiceAmount(auditDetails.getId(), ParcelAuditConstant.PARCEL_RTR_RATING_USER_NAME, charge.getAmount(), rtrStatus.value, auditDetails.getCarrierId());
                    }
                }else{
                    charge = ParcelRateResponseParser.findChargeByEDICodeInResponse(auditDetails.getChargeClassificationCode(), priceSheet);
                    if(charge != null){
                        parcelRTRDao.updateRTRInvoiceAmount(auditDetails.getId(), ParcelAuditConstant.PARCEL_RTR_RATING_USER_NAME, charge.getAmount(), rtrStatus.value, auditDetails.getCarrierId());
                    }
                }

                if(null == charge){
                    parcelRTRDao.updateRTRInvoiceAmount(auditDetails.getId(), ParcelAuditConstant.PARCEL_RTR_RATING_USER_NAME, new BigDecimal("0"), rtrStatus.value, auditDetails.getCarrierId());
                    parcelRTRDao.updateShipmentRateDetails(ParcelAuditConstant.EBILL_GFF_TABLE_NAME, auditDetails.getId().toString(), ParcelAuditConstant.PARCEL_RTR_RATING_USER_NAME, new BigDecimal("0"), shipperCategory);
                }else{
                    parcelRTRDao.updateShipmentRateDetails(ParcelAuditConstant.EBILL_GFF_TABLE_NAME, auditDetails.getId().toString(), ParcelAuditConstant.PARCEL_RTR_RATING_USER_NAME, charge.getDimDivisor() == null ? new BigDecimal("0") : charge.getDimDivisor(), shipperCategory);
                }
            }
        }
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

    public List<ParcelAuditDetailsDto> loadInvoiceIds(String fromDate, String toDate, String customerId, String invoiceIds, int limit){
        return parcelRTRDao.loadInvoiceIds(fromDate, toDate, customerId, invoiceIds, limit);
    }

    public void doParcelAuditingInvoiceNumberWise(List<ParcelAuditDetailsDto> invoiceList, String trackingNumbers, String rateTo) {
        if (invoiceList != null && !invoiceList.isEmpty()) {
            String licenseKey = messageSource.getMessage("RateRequest-LicenseKey", null, null);
            String strProtocol = messageSource.getMessage("RTRprotocol", null, null);
            String strHostName = messageSource.getMessage("RTRHostName", null, null);
            String strPrefix = messageSource.getMessage("RTRPrefix", null, null);
            String url = strProtocol + "://" + strHostName + "/" + strPrefix;

            Map<String, String> shipmentStatusMap = null;
            for (ParcelAuditDetailsDto inv : invoiceList) {
                if (inv != null && inv.getInvoiceId() != null) {
                    shipmentStatusMap = new HashMap<>();
                    Map<String, String> upsShipmentRateStatus = null;
                    Map<String, String> nonUpsShipmentRateStatus = null;
                    if (rateTo == null || rateTo.isEmpty()) {
                        upsShipmentRateStatus = doParcelRating(loadUpsParcelAuditDetails(inv.getInvoiceId().toString(), trackingNumbers), url, licenseKey, RateTo.UPS);
                        nonUpsShipmentRateStatus = doParcelRating(loadNonUpsParcelAuditDetails(inv.getInvoiceId().toString(), trackingNumbers), url, licenseKey, RateTo.NON_UPS);
                    } else if ("UPS".equalsIgnoreCase(rateTo)) {
                        upsShipmentRateStatus = doParcelRating(loadUpsParcelAuditDetails(inv.getInvoiceId().toString(), trackingNumbers), url, licenseKey, RateTo.UPS);
                    } else if ("FEDEX".equalsIgnoreCase(rateTo)) {
                        nonUpsShipmentRateStatus = doParcelRating(loadNonUpsParcelAuditDetails(inv.getInvoiceId().toString(), trackingNumbers), url, licenseKey, RateTo.NON_UPS);
                    }

                    if (upsShipmentRateStatus != null) shipmentStatusMap.putAll(upsShipmentRateStatus);
                    if (nonUpsShipmentRateStatus != null) shipmentStatusMap.putAll(nonUpsShipmentRateStatus);

                    if (shipmentStatusMap != null)
                        updateInvoiceRtrStatus(inv.getInvoiceId(), shipmentStatusMap);
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
}
