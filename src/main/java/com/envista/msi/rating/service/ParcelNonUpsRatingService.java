package com.envista.msi.rating.service;

import com.envista.msi.api.domain.util.ParcelRatingUtil;
import com.envista.msi.api.web.rest.dto.rtr.ParcelARChargeCodeMappingDto;
import com.envista.msi.api.web.rest.dto.rtr.ParcelAuditDetailsDto;
import com.envista.msi.api.web.rest.dto.rtr.ParcelRateDetailsDto;
import com.envista.msi.api.web.rest.dto.rtr.RatedChargeDetailsDto;
import com.envista.msi.api.web.rest.util.CommonUtil;
import com.envista.msi.api.web.rest.util.audit.parcel.ParcelAuditConstant;
import com.envista.msi.api.web.rest.util.audit.parcel.ParcelRateResponse;
import com.envista.msi.api.web.rest.util.audit.parcel.ParcelRateResponseParser;
import com.envista.msi.rating.bean.RatingQueueBean;
import com.envista.msi.rating.bean.ServiceFlagAccessorialBean;
import com.envista.msi.rating.dao.DirectJDBCDAO;
import com.envista.msi.rating.dao.RatingQueueDAO;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;

/**
 * Created by Sujit kumar on 02/05/2018.
 */
public class ParcelNonUpsRatingService {

    public Map<String, String> getMappedDASChargeCodes(){
        Map<String, String> chargeCodeMap = null;
        List<ParcelARChargeCodeMappingDto> mappedChargeCodes = new DirectJDBCDAO().loadMappedARChargeCodes(ParcelAuditConstant.MSI_AR_CHARGE_CODE_MAPPING_MODELE_NAME, ParcelAuditConstant.MSI_AR_DAS_CHARGE_CODE_NAME);
        if(mappedChargeCodes != null && !mappedChargeCodes.isEmpty()){
            chargeCodeMap =  prepareChargeCodeMap(mappedChargeCodes);
        }
        return chargeCodeMap;
    }

    public Map<String, String> getMappedLPSChargeCodes(){
        Map<String, String> chargeCodeMap = null;
        List<ParcelARChargeCodeMappingDto> mappedChargeCodes = new DirectJDBCDAO().loadMappedARChargeCodes(ParcelAuditConstant.MSI_AR_CHARGE_CODE_MAPPING_MODELE_NAME, ParcelAuditConstant.MSI_AR_LPS_CHARGE_CODE_NAME);
        if(mappedChargeCodes != null && !mappedChargeCodes.isEmpty()){
            chargeCodeMap =  prepareChargeCodeMap(mappedChargeCodes);
        }
        return chargeCodeMap;
    }

    public Map<String, String> getMappedDeclaredValueChargeCodes(){
        return getMappedARChargeCode(ParcelAuditConstant.MSI_AR_DECLARED_VALUE_CHARGE_CODE_NAME);
    }

    public Map<String, String> getMappedARChargeCode(String chargeType){
        Map<String, String> chargeCodeMap = null;
        List<ParcelARChargeCodeMappingDto> mappedChargeCodes = new DirectJDBCDAO().loadMappedARChargeCodes(ParcelAuditConstant.MSI_AR_CHARGE_CODE_MAPPING_MODELE_NAME, chargeType);
        if(mappedChargeCodes != null && !mappedChargeCodes.isEmpty()){
            chargeCodeMap =  prepareChargeCodeMap(mappedChargeCodes);
        }
        return chargeCodeMap;
    }

    public Map<String, String> prepareChargeCodeMap(List<ParcelARChargeCodeMappingDto> mappedChargeCodes){
        Map<String, String> chargeCodeMap = null;
        if(mappedChargeCodes != null && !mappedChargeCodes.isEmpty()){
            chargeCodeMap = new HashMap<>();
            for(ParcelARChargeCodeMappingDto chargeCode : mappedChargeCodes){
                if(chargeCode != null){
                    chargeCodeMap.put(chargeCode.getLookupCode(), chargeCode.getCodeValue());
                }
            }
        }
        return chargeCodeMap;
    }


    public void saveRatingQueueBean(RatingQueueBean ratingQueueBean){
        if(ratingQueueBean != null){
            new RatingQueueDAO().saveRatingQueueBean(ratingQueueBean);
        }
    }

    public List<ParcelAuditDetailsDto> getFedExParcelShipmentDetails(String customerId, String fromShipDate, String toShipDate, String trackingNumbers, String invoiceIds, boolean ignoreRtrStatus, boolean isHwt) {
        return new RatingQueueDAO().getNonUpsParcelShipmentDetails(customerId, "22", fromShipDate, toShipDate, trackingNumbers, invoiceIds, ignoreRtrStatus, isHwt);
    }

    public List<ParcelAuditDetailsDto> getFedExParcelShipmentDetails(String customerId, String fromShipDate, String toShipDate, String trackingNumbers, String invoiceIds, boolean isHwt) {
        return new RatingQueueDAO().getNonUpsParcelShipmentDetails(customerId, "22", fromShipDate, toShipDate, trackingNumbers, invoiceIds, false, isHwt);
    }

    public List<ParcelAuditDetailsDto> getFedExParcelShipmentDetails(String customerId, String fromShipDate, String toShipDate, boolean isHwt) {
        return getFedExParcelShipmentDetails(customerId, fromShipDate, toShipDate, null, null, isHwt);
    }

    public List<ParcelAuditDetailsDto> getFedExParcelShipmentDetails(String trackingNumbers, boolean ignoreRtrStatus, boolean isHwt) {
        return getFedExParcelShipmentDetails(null, null, null, trackingNumbers, null, ignoreRtrStatus, isHwt);
    }

    public String doRatingForNonUpsShipment(RatingQueueBean bean, List<ServiceFlagAccessorialBean> accessorialBeans) throws Exception {
        List<ParcelAuditDetailsDto> allShipmentCharges = getFedExParcelShipmentDetails(bean.getTrackingNumber(), true, false);
        String status = null;

        if(allShipmentCharges != null && !allShipmentCharges.isEmpty()) {
            Map<Long, List<ParcelAuditDetailsDto>> shipments = ParcelRatingUtil.organiseShipmentsByParentId(allShipmentCharges);
            List<ParcelAuditDetailsDto> shipmentToRate = shipments.get(bean.getParentId());

            if(shipmentToRate != null && !shipmentToRate.isEmpty()) {
                if(ParcelRatingUtil.isFirstShipmentToRate(shipments, bean.getParentId())) {
                    if(!ParcelRatingUtil.isShipmentRated(shipmentToRate)) {
                        status = callRTRAndPopulateRates(shipmentToRate, bean, null, accessorialBeans, null);
                        updateFedExOtherFieldValues(shipmentToRate);
                    }
                } else {
                    if(!ParcelRatingUtil.isShipmentRated(shipmentToRate)) {
                        List<ParcelAuditDetailsDto> previousShipment = ParcelRatingUtil.getPreviousShipmentDetails(shipments, bean.getParentId());
                        if(previousShipment!= null && !previousShipment.isEmpty()) {
                            if (ParcelRatingUtil.isShipmentRated(previousShipment)) {
                                if(!ParcelRatingUtil.hasFrtCharge(shipmentToRate)) {
                                    ParcelAuditDetailsDto prevShipmentFrtCharge = ParcelRatingUtil.getPreviousShipmentBaseChargeDetails(shipments, bean.getParentId());
                                    if(prevShipmentFrtCharge != null) {
                                        shipmentToRate.add(prevShipmentFrtCharge);
                                        status = callRTRAndPopulateRates(shipmentToRate, bean, null, accessorialBeans, previousShipment);
                                        updateFedExOtherFieldValues(shipmentToRate);
                                    }
                                }else {
                                    status = callRTRAndPopulateRates(shipmentToRate, bean, null, accessorialBeans, previousShipment);
                                    updateFedExOtherFieldValues(shipmentToRate);
                                }
                            }
                        }
                    }
                }
            }
        }
        return status;
    }

    public String callRTRAndPopulateRates(List<ParcelAuditDetailsDto> shipmentToRate, RatingQueueBean bean, List<RatingQueueBean> queueBeans, List<ServiceFlagAccessorialBean> accessorialBeans, List<ParcelAuditDetailsDto> prevShipmentDetails) throws Exception {
        String requestPayload = "";
        String response = "";
        String status = null;
        String url = ParcelAuditConstant.AR_RATE_REQUEST_PROTOCOL + "://" + ParcelAuditConstant.AR_RATE_REQUEST_HOST_NAME + "/" + ParcelAuditConstant.AR_RATE_REQUEST_URI_PATH;

        requestPayload = com.envista.msi.rating.util.ParcelRateRequestBuilder.buildParcelRateRequest(bean, ParcelAuditConstant.AR_RATE_REQUEST_LICENSE_KEY, queueBeans).toXmlString();
        response = CommonUtil.connectAndGetResponseAsString(url, requestPayload);

        if(response != null && !response.trim().isEmpty()) {
            if (queueBeans != null && queueBeans.size() > 0) {
                BigDecimal sumOfNetAmount = ParcelRatingUtil.findSumOfNetAmount(shipmentToRate);
                shipmentToRate = ParcelRatingUtil.updateWeightAndNetChargesForHwt(shipmentToRate);
                bean = ParcelRatingUtil.getLeadShipmentQueueBean(shipmentToRate, queueBeans);
                if (bean != null)
                    bean.setNetAmount(sumOfNetAmount);
            }

            new DirectJDBCDAO().saveParcelAuditRequestAndResponseLog(ParcelRatingUtil.prepareRequestResponseLog(requestPayload, response, bean.getParentId(), ParcelAuditConstant.EBILL_MANIFEST_TABLE_NAME));

            status = updateRateForNonUpsCarrier(ParcelRateResponseParser.parse(response), shipmentToRate, bean, accessorialBeans, prevShipmentDetails);
            updateFedExOtherFieldValues(shipmentToRate);
            if(status != null && !status.isEmpty()){
                new DirectJDBCDAO().updateRtrStatus(22L, bean.getTrackingNumber(), status, new java.sql.Date(bean.getShipDate().getTime()));
            }
        }
        return status;
    }

    public String updateRateForNonUpsCarrier(ParcelRateResponse parcelRateResponse, List<ParcelAuditDetailsDto> parcelAuditDetails, RatingQueueBean bean, List<ServiceFlagAccessorialBean> accessorialBeans, List<ParcelAuditDetailsDto> prevShipmentDetails) throws Exception {
        String status = "";
        if(parcelRateResponse != null){
            if(parcelRateResponse.getStatusCode().equals(0)){
                if(parcelRateResponse.getPriceSheets() != null && !parcelRateResponse.getPriceSheets().isEmpty()){
                    //Taking the first price sheet, as per the discussion we finalised that the first price sheet will be be correct and rated based on the latest contract.
                    ParcelRateResponse.PriceSheet firstPriceSheet = parcelRateResponse.getPriceSheets().get(0);
                    BigDecimal sumOfNetAmount = null;
                    if (bean.getHwtIdentifier() != null)
                        sumOfNetAmount = bean.getNetAmount();
                    else
                        sumOfNetAmount = ParcelRatingUtil.findSumOfNetAmount(parcelAuditDetails);
                    BigDecimal totalRateAmount = firstPriceSheet.getTotal().setScale(2, BigDecimal.ROUND_HALF_EVEN);

                    String flagged = null;
                    if(bean != null && bean.getThresholdType() != null ){

                        BigDecimal thresholdLowerBound = null;
                        BigDecimal thresholdUpperBound = null;
                        try {
                            if (ParcelAuditConstant.THRESHOLD_TYPE_PERCENT.equalsIgnoreCase(bean.getThresholdType())) {

                                if (bean.getThresholdValue() != null) {

                                    BigDecimal percentValue = new BigDecimal(bean.getThresholdValue());
                                    BigDecimal tolerance = percentValue.multiply(sumOfNetAmount).divide(new BigDecimal("100.000"), 2, BigDecimal.ROUND_HALF_EVEN);
                                    thresholdLowerBound = sumOfNetAmount.subtract(tolerance);
                                    thresholdUpperBound = sumOfNetAmount.add(tolerance);
                                }
                            } else if ( ParcelAuditConstant.THRESHOLD_TYPE_FLAT.equalsIgnoreCase(bean.getThresholdType()) ) {

                                if (bean.getThresholdValue() != null) {

                                    BigDecimal tolerance = new BigDecimal(bean.getThresholdValue());
                                    thresholdLowerBound = sumOfNetAmount.subtract(tolerance);
                                    thresholdUpperBound = sumOfNetAmount.add(tolerance);
                                }
                            }
                        } catch( Exception e){ e.printStackTrace(); }

                        if( thresholdLowerBound != null && thresholdUpperBound != null ) {

                            if(sumOfNetAmount.compareTo(totalRateAmount) == 0 || (totalRateAmount.compareTo(thresholdLowerBound) >= 0 && totalRateAmount.compareTo(thresholdUpperBound) <= 0))
                                flagged = "false";
                            else
                                flagged = "true";
                        }
                    }

                    BigDecimal toleranceLowerBound = sumOfNetAmount.multiply(new BigDecimal("0.995"));
                    BigDecimal toleranceUpperBound = sumOfNetAmount.multiply(new BigDecimal("1.005"));

                    if(sumOfNetAmount.compareTo(totalRateAmount) == 0 || (totalRateAmount.compareTo(toleranceLowerBound) >= 0 && totalRateAmount.compareTo(toleranceUpperBound) <= 0)){
                        status = updateAmountWithRTRResponseChargesForNonUpsCarrier(firstPriceSheet, parcelAuditDetails, ParcelAuditConstant.RTRStatus.CLOSED, flagged, accessorialBeans, prevShipmentDetails);
                    } else{
                        if(sumOfNetAmount.compareTo(totalRateAmount) < 0){
                            status = updateAmountWithRTRResponseChargesForNonUpsCarrier(firstPriceSheet, parcelAuditDetails, ParcelAuditConstant.RTRStatus.UNDER_CHARGED, flagged, accessorialBeans, prevShipmentDetails);
                        } else if(sumOfNetAmount.compareTo(totalRateAmount) > 0){
                            status = updateAmountWithRTRResponseChargesForNonUpsCarrier(firstPriceSheet, parcelAuditDetails, ParcelAuditConstant.RTRStatus.OVER_CHARGED, flagged, accessorialBeans, prevShipmentDetails);
                        }
                    }
                }else{
                    updateRTRAmountAndStatus(parcelAuditDetails, ParcelAuditConstant.RTRStatus.NO_PRICE_SHEET);
                    status = ParcelAuditConstant.RTRStatus.NO_PRICE_SHEET.value;
                }
            }else{
                updateRTRAmountAndStatus(parcelAuditDetails, ParcelAuditConstant.RTRStatus.RATING_EXCEPTION);
                status = ParcelAuditConstant.RTRStatus.RATING_EXCEPTION.value;
            }
        }else{
            updateRTRAmountAndStatus(parcelAuditDetails, ParcelAuditConstant.RTRStatus.RATING_EXCEPTION);
            status = ParcelAuditConstant.RTRStatus.RATING_EXCEPTION.value;
        }
        return status;
    }

    /**
     * To update RTR amount/rate for each record in the shipment.
     * If shipment charge type is not found in the response the update the record with Zero amount.
     *
     * @param priceSheet
     * @param parcelAuditDetails
     * @param rtrStatus
     * @param flagged
     * @param accessorialBeans
     * @return
     * @throws Exception
     */
    private String updateAmountWithRTRResponseChargesForNonUpsCarrier(ParcelRateResponse.PriceSheet priceSheet, List<ParcelAuditDetailsDto> parcelAuditDetails, ParcelAuditConstant.RTRStatus rtrStatus, String flagged, List<ServiceFlagAccessorialBean> accessorialBeans, List<ParcelAuditDetailsDto> prevShipmentDetails) throws Exception {
        DirectJDBCDAO directJDBCDAO = new DirectJDBCDAO();
        boolean frtChargeFound = false;
        List<ParcelRateResponse.Charge> mappedAccChanges = new ArrayList<>();
        List<ParcelRateResponse.Charge> mappedDscChanges = new ArrayList<>();
        String shipperCategory = priceSheet.getCategory();
        String contractName = priceSheet.getContractName();
        String rateSetName = priceSheet.getRateSet();
        String zone = priceSheet.getZone();
        BigDecimal fuelTablePerc = ParcelRateResponseParser.getFuelTablePercentage(priceSheet);
        BigDecimal ratedGrossFuel = ParcelRateResponseParser.getRatedGrossFuel(priceSheet);

        List<RatedChargeDetailsDto> ratedCharges = null;

        ratedCharges = getRatedChargeAmountForNonUPS(parcelAuditDetails.get(0).getParentId(), parcelAuditDetails.get(0).getTrackingNumber(), parcelAuditDetails.get(0).getPickupDate().toString());


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

                            ParcelRateDetailsDto rateDetails = ParcelRateDetailsDto.getInstance();
                            rateDetails.setShipperCategory(shipperCategory);
                            rateDetails.setContractName(contractName);
                            rateDetails.setFuelTablePercentage(fuelTablePerc);
                            rateDetails.setZone(zone);
                            rateDetails.setRatedBaseDiscount(ParcelRateResponseParser.getSumOfFreightDiscount(priceSheet));
                            rateDetails.setRatedEarnedDiscount(ParcelRateResponseParser.getSpendDiscount(priceSheet));
                            rateDetails.setRatedMinMaxAdjustment(ParcelRateResponseParser.getMinMaxAdjustment(priceSheet));
                            rateDetails.setDimDivisor(charge.getDimDivisor() == null ? new BigDecimal("0") : charge.getDimDivisor());
                            rateDetails.setRatedWeight(charge.getWeight() == null ? new BigDecimal("0") : charge.getWeight());
                            rateDetails.setHwtIdentifier(auditDetails.getMultiWeightNumber());
                            rateDetails.setRateSetName(rateSetName);
                            ParcelRateResponse.Charge residentialSurchargeDiscountCharge = ParcelRateResponseParser.getResidentialSurchargeDiscount(priceSheet);
                            if(residentialSurchargeDiscountCharge != null){
                                rateDetails.setResidentialSurchargeDiscount(residentialSurchargeDiscountCharge.getAmount());
                                rateDetails.setResidentialSurchargeDiscountPercentage(residentialSurchargeDiscountCharge.getRate());
                            }

                            if (auditDetails.getChargeDescription() != null && (auditDetails.getChargeDescription().contains("EXTENDED") || auditDetails.getChargeDescription().contains("extended"))) {
                                ParcelRateResponse.Charge extendedDasDiscount = ParcelRateResponseParser.getRatedExtendedDasDiscount(priceSheet);
                                if (extendedDasDiscount != null) {
                                    mappedDscChanges.add(extendedDasDiscount);
                                    rateDetails.setDeliveryAreaSurchargeDiscount(extendedDasDiscount.getAmount());
                                }
                            } else {
                                ParcelRateResponse.Charge dasDiscount = ParcelRateResponseParser.getRatedDasDiscount(priceSheet);
                                if (dasDiscount != null) {
                                    mappedDscChanges.add(dasDiscount);
                                    rateDetails.setDeliveryAreaSurchargeDiscount(dasDiscount.getAmount());
                                }
                            }
                            BigDecimal prevRatedFrtAmt = new BigDecimal("0");
                            BigDecimal frtAmount = new BigDecimal("0");
                            if (ratedCharges != null)
                                prevRatedFrtAmt = ParcelRatingUtil.findRtrAmountByChargeClassificationCode("FRT", ratedCharges, auditDetails.getId());
                            frtAmount = charge.getAmount().subtract(prevRatedFrtAmt);
                            rateDetails.setRtrAmount(frtAmount);
                            rateDetails.setRtrStatus(rtrStatus.value);
                            rateDetails.setFlagged(flagged);
                            directJDBCDAO.updateShipmentRateDetails(ParcelAuditConstant.EBILL_MANIFEST_TABLE_NAME, auditDetails.getId().toString(), ParcelAuditConstant.PARCEL_RTR_RATING_USER_NAME, rateDetails);
                        }
                    }
                } else if (ParcelAuditConstant.ChargeClassificationCode.ACS.name().equalsIgnoreCase(auditDetails.getChargeClassificationCode())
                        && ParcelAuditConstant.ChargeDescriptionCode.FSC.name().equalsIgnoreCase(auditDetails.getChargeDescriptionCode())) {
                    charge = ParcelRateResponseParser.findChargeByType(ParcelRateResponse.ChargeType.ACCESSORIAL_FUEL.name(), priceSheet);
                    if(charge != null){
                        ParcelRateDetailsDto rateDetails = ParcelRateDetailsDto.getInstance();
                        rateDetails.setShipperCategory(shipperCategory);
                        rateDetails.setContractName(contractName);
                        rateDetails.setFuelTablePercentage(fuelTablePerc);
                        rateDetails.setZone(zone);
                        rateDetails.setRatedFuelSurchargeDiscount(ParcelRateResponseParser.getRatedSurchargeDiscount(priceSheet));
                        rateDetails.setRatedCustomFuelSurchargeDiscount(ParcelRateResponseParser.getRatedCustomSurchargeDiscount(priceSheet));
                        rateDetails.setRatedGrossFuel(ratedGrossFuel);
                        rateDetails.setDimDivisor(charge.getDimDivisor() == null ? new BigDecimal("0") : charge.getDimDivisor());
                        rateDetails.setRatedWeight(charge.getWeight() == null ? new BigDecimal("0") : charge.getWeight());
                        BigDecimal prevRatedFscAmt = new BigDecimal("0");
                        BigDecimal fscAmount = new BigDecimal("0");
                        if (ratedCharges != null)
                            prevRatedFscAmt = ParcelRatingUtil.findRtrAmountByChargeClassificationCodeAndChargeDescriptionCode(ParcelAuditConstant.ChargeClassificationCode.ACS.name(), "FSC", ratedCharges, auditDetails.getId());
                        fscAmount = charge.getAmount().subtract(prevRatedFscAmt);
                        rateDetails.setRtrAmount(fscAmount);
                        rateDetails.setRtrStatus(rtrStatus.value);
                        rateDetails.setHwtIdentifier(auditDetails.getMultiWeightNumber());
                        rateDetails.setRateSetName(rateSetName);
                        rateDetails.setFlagged(flagged);
                        directJDBCDAO.updateShipmentRateDetails(ParcelAuditConstant.EBILL_MANIFEST_TABLE_NAME, auditDetails.getId().toString(), ParcelAuditConstant.PARCEL_RTR_RATING_USER_NAME, rateDetails);
                    }
                } else {
                    //need to clarify this step, whether it is required for NonUPS Carrier or not.
                    ServiceFlagAccessorialBean bean = ParcelRatingUtil.getAccessorialBean(accessorialBeans, auditDetails.getChargeDescription(), auditDetails.getActualchargeDescriptionCode(), 22L);
                    if (bean != null) {
                        if("RSC".equalsIgnoreCase(bean.getLookUpValue()))
                            bean.setLookUpValue("RSS");

                        charge = ParcelRateResponseParser.findChargeByEDICodeInResponse(bean.getLookUpValue(), priceSheet);
                    }

                    if(charge != null){
                        mappedAccChanges.add(charge);
                        if(charge != null && charge.getType() != null){
                            if(ParcelRateResponse.ChargeType.ACCESSORIAL.name().equalsIgnoreCase(charge.getType())) mappedAccChanges.add(charge);
                            else if(ParcelRateResponse.ChargeType.DISCOUNT.name().equalsIgnoreCase(charge.getType())) mappedDscChanges.add(charge);
                        }

                        ParcelRateDetailsDto rateDetails = ParcelRateDetailsDto.getInstance();
                        rateDetails.setShipperCategory(shipperCategory);
                        rateDetails.setContractName(contractName);
                        rateDetails.setFuelTablePercentage(fuelTablePerc);
                        rateDetails.setZone(zone);
                        rateDetails.setDimDivisor(charge.getDimDivisor() == null ? new BigDecimal("0") : charge.getDimDivisor());
                        rateDetails.setRatedWeight(charge.getWeight() == null ? new BigDecimal("0") : charge.getWeight());
                        rateDetails.setRtrAmount(charge.getAmount());
                        rateDetails.setRtrStatus(rtrStatus.value);
                        rateDetails.setHwtIdentifier(auditDetails.getMultiWeightNumber());
                        rateDetails.setRateSetName(rateSetName);
                        rateDetails.setFlagged(flagged);
                        directJDBCDAO.updateShipmentRateDetails(ParcelAuditConstant.EBILL_MANIFEST_TABLE_NAME, auditDetails.getId().toString(), ParcelAuditConstant.PARCEL_RTR_RATING_USER_NAME, rateDetails);
                    }
                }

                if(charge == null){
                    ParcelRateDetailsDto rateDetails = ParcelRateDetailsDto.getInstance();
                    rateDetails.setShipperCategory(shipperCategory);
                    rateDetails.setContractName(contractName);
                    rateDetails.setFuelTablePercentage(fuelTablePerc);
                    rateDetails.setZone(zone);
                    rateDetails.setRtrAmount(new BigDecimal("0"));
                    rateDetails.setRtrStatus(rtrStatus.value);
                    rateDetails.setHwtIdentifier(auditDetails.getMultiWeightNumber());
                    rateDetails.setRateSetName(rateSetName);
                    rateDetails.setFlagged(flagged);
                    directJDBCDAO.updateShipmentRateDetails(ParcelAuditConstant.EBILL_MANIFEST_TABLE_NAME, auditDetails.getId().toString(), ParcelAuditConstant.PARCEL_RTR_RATING_USER_NAME, rateDetails);
                }
            }
        }

        ParcelRateDetailsDto otherDscRateDetails = ParcelRateDetailsDto.getInstance();
        otherDscRateDetails.setShipperCategory(shipperCategory);
        otherDscRateDetails.setContractName(contractName);
        otherDscRateDetails.setHwtIdentifier(parcelAuditDetails.get(0).getMultiWeightNumber());
        otherDscRateDetails.setRateSetName(rateSetName);
        otherDscRateDetails.setZone(zone);
        otherDscRateDetails.setFlagged(flagged);
        saveOtherDiscountsAppliedForNonUps(priceSheet, parcelAuditDetails, otherDscRateDetails, mappedDscChanges);

        ParcelRateDetailsDto accessorialRateDetails = ParcelRateDetailsDto.getInstance();
        accessorialRateDetails.setShipperCategory(shipperCategory);
        accessorialRateDetails.setContractName(contractName);
        accessorialRateDetails.setHwtIdentifier(parcelAuditDetails.get(0).getMultiWeightNumber());
        accessorialRateDetails.setRateSetName(rateSetName);
        accessorialRateDetails.setZone(zone);
        accessorialRateDetails.setFlagged(flagged);
        saveAccessorialForNonUps(priceSheet, parcelAuditDetails, accessorialRateDetails, mappedAccChanges);
        return rtrStatus.value;
    }

    /**
     * To update rtr_status and rtr_amount.
     *
     * @param parcelAuditDetails
     * @param rtrStatus
     * @throws Exception
     */
    private void updateRTRAmountAndStatus(List<ParcelAuditDetailsDto> parcelAuditDetails, ParcelAuditConstant.RTRStatus rtrStatus) throws Exception {
        if(parcelAuditDetails != null && !parcelAuditDetails.isEmpty()){
            StringJoiner entityIds = new StringJoiner(",");
            for (ParcelAuditDetailsDto auditDetails : parcelAuditDetails){
                if(auditDetails != null){
                    entityIds.add(auditDetails.getId().toString());
                }
            }
            if(entityIds.length() > 0){
                new DirectJDBCDAO().updateRtrStatusByIds(entityIds.toString(), ParcelAuditConstant.PARCEL_RTR_RATING_USER_NAME, rtrStatus.value, parcelAuditDetails.get(0).getCarrierId());
            }
        }
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
                        Iterator<ParcelRateResponse.Charge> chargeIterator = discountCharges.iterator();
                        List<ParcelRateResponse.Charge> tempRemoveList = new ArrayList<>();
                        while(chargeIterator.hasNext()){
                            ParcelRateResponse.Charge tempCharge = chargeIterator.next();
                            for(ParcelRateResponse.Charge mappedChrg : mappedDscChanges){
                                if(mappedChrg != null && tempCharge != null
                                        && mappedChrg.getType() != null && tempCharge.getType() != null
                                        && mappedChrg.getType().equalsIgnoreCase(tempCharge.getType())
                                        && mappedChrg.getName() != null && tempCharge.getName() != null
                                        && mappedChrg.getName().equalsIgnoreCase(tempCharge.getName())) {
                                    tempRemoveList.add(tempCharge);
                                }
                            }
                        }
                        if (tempRemoveList != null && tempRemoveList.size() > 0) {
                            discountCharges.removeAll(tempRemoveList);
                            tempRemoveList = null;
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
                    new DirectJDBCDAO().updateOtherDiscountShipmentRateDetails(ParcelAuditConstant.EBILL_MANIFEST_TABLE_NAME, entityIds.toString(), ParcelAuditConstant.PARCEL_RTR_RATING_USER_NAME, rateDetails);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
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
                        List<ParcelRateResponse.Charge> tempRemoveList = new ArrayList<>();
                        while(chargeIterator.hasNext()){
                            ParcelRateResponse.Charge tempCharge = chargeIterator.next();
                            for(ParcelRateResponse.Charge mappedChrg : mappedAccChanges){
                                if(mappedChrg != null && tempCharge != null
                                        && mappedChrg.getType() != null && tempCharge.getType() != null
                                        && mappedChrg.getType().equalsIgnoreCase(tempCharge.getType())
                                        && mappedChrg.getName() != null && tempCharge.getName() != null
                                        && mappedChrg.getName().equalsIgnoreCase(tempCharge.getName())) {
                                    tempRemoveList.add(tempCharge);
                                }
                            }
                        }
                        if (tempRemoveList != null && tempRemoveList.size() > 0) {
                            accessorialCharges.removeAll(tempRemoveList);
                            tempRemoveList = null;
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
                    new DirectJDBCDAO().updateAccessorialShipmentRateDetails(ParcelAuditConstant.EBILL_MANIFEST_TABLE_NAME, entityIds.toString(), ParcelAuditConstant.PARCEL_RTR_RATING_USER_NAME, rateDetails);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void saveRatingQueueBean(List<RatingQueueBean> queueBeanList) throws SQLException {
        if (queueBeanList != null) {
            new RatingQueueDAO().saveRatingQueueBean(queueBeanList);
        }
    }


    /**
     * @param beans
     * @return
     * @throws Exception
     */
    public String doRatingForNonUpsShipment(List<RatingQueueBean> beans, List<ServiceFlagAccessorialBean> accessorialBeans) throws Exception {

        String trackingNumbers = ParcelRatingUtil.prepareTrackingNumbersInOperator(beans);
        String status = null;
        if (trackingNumbers != null && trackingNumbers.length() > 0) {

            List<ParcelAuditDetailsDto> allShipmentCharges = getFedExParcelShipmentDetails(trackingNumbers, true, false);


            if (allShipmentCharges != null && !allShipmentCharges.isEmpty()) {
                status = callRTRAndPopulateRates(allShipmentCharges, null, beans, accessorialBeans, null);
            }

        }

        return status;
    }

    public boolean shipmentExist(Long parentId){
        return new RatingQueueDAO().shipmentExist(parentId);
    }

    public void updateFedExOtherFieldValues(List<ParcelAuditDetailsDto> rateDetailsList) {
        try{
            new DirectJDBCDAO().updateFedExOtherFieldValues(rateDetailsList);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * This method returns service flag accessroials based on carrier id and module name which needs to send in the XML request
     *
     * @param carrierId
     * @param moduleName
     * @return
     */
    public static List<ServiceFlagAccessorialBean> getServiceFlagAcessorials(Long carrierId, String moduleName) {

        return new DirectJDBCDAO().getServiceFlagAcessorials(carrierId, moduleName);
    }

    public List<RatedChargeDetailsDto> getRatedChargeAmountForNonUPS(Long parentId, String trackingNumber, String pickUpDate) {
        return new DirectJDBCDAO().getRatedChargeAmountforNonUPS(parentId, trackingNumber, pickUpDate);
    }
}
