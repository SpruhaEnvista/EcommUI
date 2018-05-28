package com.envista.msi.rating.service;

import com.envista.msi.api.dao.rtr.ParcelRTRDao;
import com.envista.msi.api.domain.util.ParcelRatingUtil;
import com.envista.msi.api.web.rest.dto.rtr.*;
import com.envista.msi.api.web.rest.util.CommonUtil;
import com.envista.msi.api.web.rest.util.audit.parcel.ParcelAuditConstant;
import com.envista.msi.api.web.rest.util.audit.parcel.ParcelRateRequestBuilder;
import com.envista.msi.api.web.rest.util.audit.parcel.ParcelRateResponse;
import com.envista.msi.api.web.rest.util.audit.parcel.ParcelRateResponseParser;
import com.envista.msi.rating.bean.RatingQueueBean;
import com.envista.msi.rating.dao.DirectJDBCDAO;
import com.envista.msi.rating.dao.RatingQueueDAO;

import java.math.BigDecimal;
import java.util.*;

/**
 * Created by Sujit kumar on 05/05/2018.
 */
public class ParcelUpsRatingService {
    public List<ParcelAuditDetailsDto> getUpsParcelShipmentDetails(String customerId, String fromShipDate, String toShipDate, String trackingNumbers, String invoiceIds, boolean ignoreRtrStatus, boolean isHwt) {
        return new RatingQueueDAO().getUpsParcelShipmentDetails(customerId, fromShipDate, toShipDate, trackingNumbers, invoiceIds, ignoreRtrStatus, isHwt);
    }

    public List<ParcelAuditDetailsDto> getUpsParcelShipmentDetails(String customerId, String fromShipDate, String toShipDate, String trackingNumbers, String invoiceIds, boolean isHwt) {
        return getUpsParcelShipmentDetails(customerId, fromShipDate, toShipDate, trackingNumbers, invoiceIds, false, isHwt);
    }

    public List<ParcelAuditDetailsDto> getUpsParcelShipmentDetails(String customerId, String fromShipDate, String toShipDate, boolean ignoreRtrStatus, boolean isHwt) {
        return getUpsParcelShipmentDetails(customerId, fromShipDate, toShipDate, null, null, ignoreRtrStatus, isHwt);
    }

    public List<ParcelAuditDetailsDto> getUpsParcelShipmentDetails(String customerId, String fromShipDate, String toShipDate, boolean isHwt) {
        return getUpsParcelShipmentDetails(customerId, fromShipDate, toShipDate, null, null, false, isHwt);
    }

    public List<ParcelAuditDetailsDto> getUpsParcelShipmentDetails(String customerId, String trackingNumber, boolean ignoreRtrStatus, boolean isHwt) {
        return getUpsParcelShipmentDetails(customerId, null, null, trackingNumber, null, ignoreRtrStatus, isHwt);
    }

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

    public MsiARChargeCodesDto getAllMappedARChargeCodes(){
        MsiARChargeCodesDto msiARChargeCode = MsiARChargeCodesDto.getInstance();
        msiARChargeCode.setDasChargeCodes(getMappedDASChargeCodes());
        msiARChargeCode.setLpsChargeCodes(getMappedLPSChargeCodes());
        return msiARChargeCode;
    }

    public String doParcelRatingForUpsCarrier(RatingQueueBean bean){
        String status = null;
        if(bean != null){
            String url = ParcelAuditConstant.AR_RATE_REQUEST_PROTOCOL + "://" + ParcelAuditConstant.AR_RATE_REQUEST_HOST_NAME + "/" + ParcelAuditConstant.AR_RATE_REQUEST_URI_PATH;
            String licenseKey = ParcelAuditConstant.AR_RATE_REQUEST_LICENSE_KEY;

            String trackingNumber = bean.getTrackingNumber();
            List<ParcelAuditDetailsDto> shipmentRecords = null;
            if(trackingNumber != null && !trackingNumber.isEmpty()){
                try{
                    MsiARChargeCodesDto msiARChargeCode = getAllMappedARChargeCodes();
                    shipmentRecords = getUpsParcelShipmentDetails(null, trackingNumber, true, false);
                    if(shipmentRecords != null && !shipmentRecords.isEmpty()) {
                        Map<Long, List<ParcelAuditDetailsDto>> shipments = ParcelRatingUtil.organiseShipmentsByParentId(shipmentRecords);
                        List<ParcelAuditDetailsDto> shipmentToRate = shipments.get(bean.getParentId());

                        if(shipmentToRate != null && !shipmentToRate.isEmpty()) {
                            if(ParcelRatingUtil.isFirstShipmentToRate(shipments, bean.getParentId())) {
                                if(!ParcelRatingUtil.isShipmentRated(shipmentToRate)) {
                                    status = callRTRAndPopulateRates(url, licenseKey, shipmentToRate, msiARChargeCode, null, bean, null);
                                }
                            } else {
                                if(!ParcelRatingUtil.isShipmentRated(shipmentToRate)) {
                                    List<ParcelAuditDetailsDto> previousShipment = ParcelRatingUtil.getPreviousShipmentDetails(shipments, bean.getParentId());
                                    if(previousShipment!= null && !previousShipment.isEmpty()){
                                        if(ParcelRatingUtil.isShipmentRated(previousShipment)){
                                            if (ParcelRatingUtil.containsCharge(ParcelAuditConstant.COMMERCIAL_ADJUSTMENT_CHARGE_TYPE, shipmentToRate)) {
                                                status = callRTRAndPopulateRates(url, licenseKey, previousShipment, msiARChargeCode, shipmentToRate.get(0), previousShipment, bean, null);
                                            } else if (ParcelRatingUtil.containsCharge(ParcelAuditConstant.RESIDENTIAL_ADJUSTMENT_CHARGE_TYPE, shipmentToRate)) {
                                                //keeping it in separate if condition in order to handle few more scenarios in future.
                                                status = callRTRAndPopulateRates(url, licenseKey, previousShipment, msiARChargeCode, shipmentToRate.get(0), previousShipment, bean, null);
                                                status = callRTRAndPopulateRates(url, licenseKey, previousShipment, msiARChargeCode, shipmentToRate.get(0), previousShipment, bean);
                                            } else if(ParcelRatingUtil.containsCharge(ParcelAuditConstant.RESIDENTIAL_COMMERCIAL_ADJUSTMENT_CHARGE_TYPE, shipmentToRate)) {
                                                if(previousShipment != null) {
                                                    List<ParcelAuditDetailsDto> resComShipmentToRate = new ArrayList<>();
                                                    for(ParcelAuditDetailsDto shpCharge : shipmentToRate) {
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
                                                    status = callRTRAndPopulateRates(url, licenseKey, resComShipmentToRate, msiARChargeCode, previousShipment, bean);
                                                }
                                            } else {
                                                if(previousShipment != null){
                                                    if(shipmentToRate != null) {
                                                        boolean hasFrtCharge = false;
                                                        boolean frtChargeManipulated = false;
                                                        ParcelAuditDetailsDto frtCharged = ParcelRatingUtil.findFrtCharge(shipmentToRate);
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
                                                        boolean hasFSCCharge = ParcelRatingUtil.containsFuelSurcharge(shipmentToRate);
                                                        for(ParcelAuditDetailsDto prevShpCharge : previousShipment){
                                                            if(prevShpCharge != null && ParcelAuditConstant.ChargeClassificationCode.ACC.name().equalsIgnoreCase(prevShpCharge.getChargeClassificationCode())) {
                                                                shipmentToRate.add(prevShpCharge);
                                                            }
                                                            if(!hasFSCCharge && ParcelAuditConstant.ChargeClassificationCode.FSC.name().equalsIgnoreCase(prevShpCharge.getChargeClassificationCode())) {
                                                                shipmentToRate.add(prevShpCharge);
                                                            }
                                                            if (!hasFrtCharge && !frtChargeManipulated && ParcelAuditConstant.ChargeClassificationCode.FRT.name().equalsIgnoreCase(prevShpCharge.getChargeClassificationCode())) {
                                                                shipmentToRate.add(prevShpCharge);
                                                            }
                                                        }
                                                        status = callRTRAndPopulateRates(url, licenseKey, shipmentToRate, msiARChargeCode, previousShipment, bean, null);
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
        return status;
    }

    private String callRTRAndPopulateRates(String url, String licenseKey, List<ParcelAuditDetailsDto> parcelAuditDetails, MsiARChargeCodesDto msiARChargeCode, List<ParcelAuditDetailsDto> previousShipment, RatingQueueBean bean, List<RatingQueueBean> beans) throws Exception {
        return callRTRAndPopulateRates(url, licenseKey, parcelAuditDetails, msiARChargeCode, null, previousShipment, bean, beans);
    }

    private String callRTRAndPopulateRates(String url, String licenseKey, List<ParcelAuditDetailsDto> parcelAuditDetails, MsiARChargeCodesDto msiARChargeCode, ParcelAuditDetailsDto commercialCharge, List<ParcelAuditDetailsDto> previousShipment, RatingQueueBean bean, List<RatingQueueBean> beans) throws Exception {
        String requestPayload = "";
        String response = "";
        String status = "";
        DirectJDBCDAO directJDBCDAO = new DirectJDBCDAO();

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

                    requestPayload = com.envista.msi.rating.util.ParcelRateRequestBuilder.buildParcelRateRequest(bean, ParcelAuditConstant.AR_RATE_REQUEST_LICENSE_KEY, beans).toXmlString();
                    response = CommonUtil.connectAndGetResponseAsString(url, requestPayload);
                    if(response != null && !response.trim().isEmpty()){

                        if (beans != null && beans.size() > 0) {
                            commercialShipment = ParcelRatingUtil.getLeadShipmentDetails(commercialShipment);
                            bean = ParcelRatingUtil.getLeadShipmentQueueBean(commercialShipment, beans);
                        }

                        directJDBCDAO.saveParcelAuditRequestAndResponseLog(ParcelRatingUtil.prepareRequestResponseLog(requestPayload, response, commercialCharge.getParentId(), ParcelAuditConstant.EBILL_GFF_TABLE_NAME));
                        status = updateRateForUps(ParcelRateResponseParser.parse(response), commercialShipment, msiARChargeCode, commercialCharge, previousShipment, null);
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
                    requestPayload = com.envista.msi.rating.util.ParcelRateRequestBuilder.buildParcelRateRequest(bean, ParcelAuditConstant.AR_RATE_REQUEST_LICENSE_KEY, beans).toXmlString();
                    response = CommonUtil.connectAndGetResponseAsString(url, requestPayload);
                    if(response != null && !response.trim().isEmpty()){

                        if (beans != null && beans.size() > 0) {
                            residentialShipment = ParcelRatingUtil.getLeadShipmentDetails(residentialShipment);
                            bean = ParcelRatingUtil.getLeadShipmentQueueBean(residentialShipment, beans);
                        }

                        directJDBCDAO.saveParcelAuditRequestAndResponseLog(ParcelRatingUtil.prepareRequestResponseLog(requestPayload, response, commercialCharge.getParentId(), ParcelAuditConstant.EBILL_GFF_TABLE_NAME));
                        status = updateRateForUps(ParcelRateResponseParser.parse(response), residentialShipment, msiARChargeCode, commercialCharge, previousShipment, null);
                    }
                }
            }
        }else{
            requestPayload = com.envista.msi.rating.util.ParcelRateRequestBuilder.buildParcelRateRequest(bean, ParcelAuditConstant.AR_RATE_REQUEST_LICENSE_KEY, beans).toXmlString();
            response = CommonUtil.connectAndGetResponseAsString(url, requestPayload);
            if(response != null && !response.trim().isEmpty()){
                BigDecimal hwtNetAmount=null;
                if (beans != null && beans.size() > 0) {
                    hwtNetAmount = ParcelRatingUtil.findSumOfNetAmount(parcelAuditDetails);
                    parcelAuditDetails = ParcelRatingUtil.getLeadShipmentDetails(parcelAuditDetails);
                    bean = ParcelRatingUtil.getLeadShipmentQueueBean(parcelAuditDetails, beans);
                }

                directJDBCDAO.saveParcelAuditRequestAndResponseLog(ParcelRatingUtil.prepareRequestResponseLog(requestPayload, response, parcelAuditDetails.get(0).getParentId(), ParcelAuditConstant.EBILL_GFF_TABLE_NAME));
                status = updateRateForUps(ParcelRateResponseParser.parse(response), parcelAuditDetails, msiARChargeCode, previousShipment,hwtNetAmount);
            }
        }

        return status;
    }

    private String updateRateForUps(ParcelRateResponse parcelRateResponse, List<ParcelAuditDetailsDto> parcelAuditDetails, MsiARChargeCodesDto msiARChargeCodes, List<ParcelAuditDetailsDto> previousShipment,BigDecimal hwtNetAmount) throws Exception {
        return updateRateForUps(parcelRateResponse, parcelAuditDetails, msiARChargeCodes, null, previousShipment,hwtNetAmount);
    }

    private String updateRateForUps(ParcelRateResponse parcelRateResponse, List<ParcelAuditDetailsDto> parcelAuditDetails, MsiARChargeCodesDto msiARChargeCodes, ParcelAuditDetailsDto commercialAdjCharge, List<ParcelAuditDetailsDto> previousShipment,BigDecimal hwtNetAmount) throws Exception {
        String status = "";
        if(parcelRateResponse != null){
            if(parcelRateResponse.getStatusCode() != null && parcelRateResponse.getStatusCode().equals(0)){
                if(parcelRateResponse.getPriceSheets() != null && !parcelRateResponse.getPriceSheets().isEmpty()){
                    //Taking the first price sheet, as per the discussion we finalised that the first price sheet will be be correct and rated based on the latest contract.
                    ParcelRateResponse.PriceSheet firstPriceSheet = parcelRateResponse.getPriceSheets().get(0);
                    BigDecimal sumOfNetAmount = null;
                    if(hwtNetAmount != null)
                        sumOfNetAmount = hwtNetAmount;
                    else
                        sumOfNetAmount = ParcelRatingUtil.findSumOfNetAmount(parcelAuditDetails);
                    BigDecimal totalRateAmount = firstPriceSheet.getTotal().setScale(2, BigDecimal.ROUND_HALF_EVEN);

                    BigDecimal toleranceLowerBound = sumOfNetAmount.multiply(new BigDecimal("0.995"));
                    BigDecimal toleranceUpperBound = sumOfNetAmount.multiply(new BigDecimal("1.005"));

                    if(sumOfNetAmount.compareTo(totalRateAmount) == 0 || (totalRateAmount.compareTo(toleranceLowerBound) >= 0 && totalRateAmount.compareTo(toleranceUpperBound) <= 0)){
                        if(commercialAdjCharge != null){
                            if(ParcelAuditConstant.COMMERCIAL_ADJUSTMENT_CHARGE_TYPE.equalsIgnoreCase(commercialAdjCharge.getChargeDescription())){
                                status = updateAmountWithRTRResponseChargesForUpsCommercialAdjustment(firstPriceSheet, parcelAuditDetails, ParcelAuditConstant.RTRStatus.CLOSED, msiARChargeCodes, commercialAdjCharge, previousShipment);
                            } else if(ParcelAuditConstant.RESIDENTIAL_ADJUSTMENT_CHARGE_TYPE.equalsIgnoreCase(commercialAdjCharge.getChargeDescription())){
                                status = updateAmountWithRTRResponseChargesForUpsCommercialAdjustment(firstPriceSheet, parcelAuditDetails, ParcelAuditConstant.RTRStatus.CLOSED, msiARChargeCodes, commercialAdjCharge, previousShipment);
                            }
                        } else{
                            if(previousShipment != null && !previousShipment.isEmpty()){
                                status = updateAdjustedRateForUps(firstPriceSheet, parcelAuditDetails, ParcelAuditConstant.RTRStatus.CLOSED, msiARChargeCodes, previousShipment);
                            } else{
                                status = updateAmountWithRTRResponseChargesForUps(firstPriceSheet, parcelAuditDetails, ParcelAuditConstant.RTRStatus.CLOSED, msiARChargeCodes);
                            }
                        }
                    } else {
                        if(sumOfNetAmount.compareTo(totalRateAmount) < 0){
                            if(commercialAdjCharge != null){
                                if(ParcelAuditConstant.COMMERCIAL_ADJUSTMENT_CHARGE_TYPE.equalsIgnoreCase(commercialAdjCharge.getChargeDescription())){
                                    status = updateAmountWithRTRResponseChargesForUpsCommercialAdjustment(firstPriceSheet, parcelAuditDetails, ParcelAuditConstant.RTRStatus.UNDER_CHARGED, msiARChargeCodes, commercialAdjCharge, previousShipment);
                                } else if(ParcelAuditConstant.RESIDENTIAL_ADJUSTMENT_CHARGE_TYPE.equalsIgnoreCase(commercialAdjCharge.getChargeDescription())){
                                    status = updateAmountWithRTRResponseChargesForUpsCommercialAdjustment(firstPriceSheet, parcelAuditDetails, ParcelAuditConstant.RTRStatus.OVER_CHARGED, msiARChargeCodes, commercialAdjCharge, previousShipment);
                                }
                            } else{
                                if(previousShipment != null && !previousShipment.isEmpty()){
                                    status = updateAdjustedRateForUps(firstPriceSheet, parcelAuditDetails, ParcelAuditConstant.RTRStatus.UNDER_CHARGED, msiARChargeCodes, previousShipment);
                                }else {
                                    status = updateAmountWithRTRResponseChargesForUps(firstPriceSheet, parcelAuditDetails, ParcelAuditConstant.RTRStatus.UNDER_CHARGED, msiARChargeCodes);
                                }
                            }
                        } else if(sumOfNetAmount.compareTo(totalRateAmount) > 0){
                            if(commercialAdjCharge != null){
                                if(ParcelAuditConstant.COMMERCIAL_ADJUSTMENT_CHARGE_TYPE.equalsIgnoreCase(commercialAdjCharge.getChargeDescription())){
                                    status = updateAmountWithRTRResponseChargesForUpsCommercialAdjustment(firstPriceSheet, parcelAuditDetails, ParcelAuditConstant.RTRStatus.OVER_CHARGED, msiARChargeCodes, commercialAdjCharge, previousShipment);
                                } else if(ParcelAuditConstant.RESIDENTIAL_ADJUSTMENT_CHARGE_TYPE.equalsIgnoreCase(commercialAdjCharge.getChargeDescription())){
                                    status = updateAmountWithRTRResponseChargesForUpsCommercialAdjustment(firstPriceSheet, parcelAuditDetails, ParcelAuditConstant.RTRStatus.OVER_CHARGED, msiARChargeCodes, commercialAdjCharge, previousShipment);
                                }
                            } else{
                                if(previousShipment != null && !previousShipment.isEmpty()){
                                    status = updateAdjustedRateForUps(firstPriceSheet, parcelAuditDetails, ParcelAuditConstant.RTRStatus.OVER_CHARGED, msiARChargeCodes, previousShipment);
                                } else {
                                    status = updateAmountWithRTRResponseChargesForUps(firstPriceSheet, parcelAuditDetails, ParcelAuditConstant.RTRStatus.OVER_CHARGED, msiARChargeCodes);
                                }
                            }
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

    private String updateAmountWithRTRResponseChargesForUps(ParcelRateResponse.PriceSheet priceSheet, List<ParcelAuditDetailsDto> parcelAuditDetails, ParcelAuditConstant.RTRStatus rtrStatus, MsiARChargeCodesDto msiARChargeCodes) throws Exception {
        return updateAmountWithRTRResponseChargesForUps(priceSheet, parcelAuditDetails, rtrStatus, msiARChargeCodes, null);
    }

    private String updateAmountWithRTRResponseChargesForUps(ParcelRateResponse.PriceSheet priceSheet, List<ParcelAuditDetailsDto> parcelAuditDetails, ParcelAuditConstant.RTRStatus rtrStatus, MsiARChargeCodesDto msiARChargeCodes, List<ParcelAuditDetailsDto> previousShipment) throws Exception {
        DirectJDBCDAO directJDBCDAO = new DirectJDBCDAO();
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
                                rateDetails.setHwtIdentifier(auditDetails.getMultiWeightNumber());
                                directJDBCDAO.updateShipmentRateDetails(ParcelAuditConstant.EBILL_GFF_TABLE_NAME, auditDetails.getId().toString(), ParcelAuditConstant.PARCEL_RTR_RATING_USER_NAME, rateDetails);
                            }
                            frtChargeFound = true;
                        }else{
                            ParcelRateDetailsDto rateDetails = ParcelRateDetailsDto.getInstance();
                            rateDetails.setShipperCategory(shipperCategory);
                            rateDetails.setContractName(contractName);
                            rateDetails.setFuelTablePercentage(fuelTablePerc);
                            rateDetails.setRtrAmount(new BigDecimal("0"));
                            rateDetails.setRtrStatus(rtrStatus.value);
                            rateDetails.setHwtIdentifier(auditDetails.getMultiWeightNumber());
                            directJDBCDAO.updateShipmentRateDetails(ParcelAuditConstant.EBILL_GFF_TABLE_NAME, auditDetails.getId().toString(), ParcelAuditConstant.PARCEL_RTR_RATING_USER_NAME, rateDetails);
                        }
                    }else{
                        ParcelRateDetailsDto rateDetails = ParcelRateDetailsDto.getInstance();
                        rateDetails.setShipperCategory(shipperCategory);
                        rateDetails.setContractName(contractName);
                        rateDetails.setFuelTablePercentage(fuelTablePerc);
                        rateDetails.setRtrAmount(new BigDecimal("0"));
                        rateDetails.setRtrStatus(rtrStatus.value);
                        rateDetails.setHwtIdentifier(auditDetails.getMultiWeightNumber());
                        directJDBCDAO.updateShipmentRateDetails(ParcelAuditConstant.EBILL_GFF_TABLE_NAME, auditDetails.getId().toString(), ParcelAuditConstant.PARCEL_RTR_RATING_USER_NAME, rateDetails);
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
                        rateDetails.setHwtIdentifier(auditDetails.getMultiWeightNumber());

                        directJDBCDAO.updateShipmentRateDetails(ParcelAuditConstant.EBILL_GFF_TABLE_NAME, auditDetails.getId().toString(), ParcelAuditConstant.PARCEL_RTR_RATING_USER_NAME, rateDetails);
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
                        rateDetails.setHwtIdentifier(auditDetails.getMultiWeightNumber());

                        directJDBCDAO.updateShipmentRateDetails(ParcelAuditConstant.EBILL_GFF_TABLE_NAME, auditDetails.getId().toString(), ParcelAuditConstant.PARCEL_RTR_RATING_USER_NAME, rateDetails);
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
                        rateDetails.setHwtIdentifier(auditDetails.getMultiWeightNumber());

                        directJDBCDAO.updateShipmentRateDetails(ParcelAuditConstant.EBILL_GFF_TABLE_NAME, auditDetails.getId().toString(), ParcelAuditConstant.PARCEL_RTR_RATING_USER_NAME, rateDetails);
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
                        rateDetails.setHwtIdentifier(auditDetails.getMultiWeightNumber());

                        directJDBCDAO.updateShipmentRateDetails(ParcelAuditConstant.EBILL_GFF_TABLE_NAME, auditDetails.getId().toString(), ParcelAuditConstant.PARCEL_RTR_RATING_USER_NAME, rateDetails);
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
                        rateDetails.setHwtIdentifier(auditDetails.getMultiWeightNumber());

                        directJDBCDAO.updateShipmentRateDetails(ParcelAuditConstant.EBILL_GFF_TABLE_NAME, auditDetails.getId().toString(), ParcelAuditConstant.PARCEL_RTR_RATING_USER_NAME, rateDetails);
                    }
                }

                if(null == charge){
                    ParcelRateDetailsDto rateDetails = ParcelRateDetailsDto.getInstance();
                    rateDetails.setShipperCategory(shipperCategory);
                    rateDetails.setContractName(contractName);
                    rateDetails.setFuelTablePercentage(fuelTablePerc);
                    rateDetails.setRtrAmount(new BigDecimal("0"));
                    rateDetails.setRtrStatus(rtrStatus.value);
                    rateDetails.setHwtIdentifier(auditDetails.getMultiWeightNumber());

                    directJDBCDAO.updateShipmentRateDetails(ParcelAuditConstant.EBILL_GFF_TABLE_NAME, auditDetails.getId().toString(), ParcelAuditConstant.PARCEL_RTR_RATING_USER_NAME, rateDetails);
                }
            }
        }

        ParcelRateDetailsDto otherDscRateDetails = ParcelRateDetailsDto.getInstance();
        otherDscRateDetails.setShipperCategory(shipperCategory);
        otherDscRateDetails.setContractName(contractName);
        otherDscRateDetails.setHwtIdentifier(parcelAuditDetails.get(0).getMultiWeightNumber());
        saveOtherDiscountsAppliedForUps(priceSheet, parcelAuditDetails, otherDscRateDetails, mappedDscChanges);

        ParcelRateDetailsDto accessorialRateDetails = ParcelRateDetailsDto.getInstance();
        accessorialRateDetails.setShipperCategory(shipperCategory);
        accessorialRateDetails.setContractName(contractName);
        accessorialRateDetails.setHwtIdentifier(parcelAuditDetails.get(0).getMultiWeightNumber());
        saveAccessorialForUps(priceSheet, parcelAuditDetails, accessorialRateDetails, mappedAccChanges);
        return rtrStatus.value;
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
                    new ParcelRTRDao().updateOtherDiscountShipmentRateDetails(ParcelAuditConstant.EBILL_GFF_TABLE_NAME, entityIds.toString(), ParcelAuditConstant.PARCEL_RTR_RATING_USER_NAME, rateDetails);
                }
            }
        }catch (Exception e){}
    }

    private String updateAmountWithRTRResponseChargesForUpsCommercialAdjustment(ParcelRateResponse.PriceSheet priceSheet, List<ParcelAuditDetailsDto> parcelAuditDetails, ParcelAuditConstant.RTRStatus rtrStatus, MsiARChargeCodesDto msiARChargeCodes, ParcelAuditDetailsDto commercialAdjCharge, List<ParcelAuditDetailsDto> previousShipment) {
        DirectJDBCDAO directJDBCDAO = new DirectJDBCDAO();
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
        rateDetails.setHwtIdentifier(parcelAuditDetails.get(0).getMultiWeightNumber());
        rateDetails.setRtrStatus(rtrStatus.value);

        directJDBCDAO.updateAllShipmentRateDetails(ParcelAuditConstant.EBILL_GFF_TABLE_NAME, commercialAdjCharge.getId().toString(), ParcelAuditConstant.PARCEL_RTR_RATING_USER_NAME, rateDetails);

        return rtrStatus.value;
    }

    private String updateAdjustedRateForUps(ParcelRateResponse.PriceSheet priceSheet, List<ParcelAuditDetailsDto> parcelAuditDetails, ParcelAuditConstant.RTRStatus rtrStatus, MsiARChargeCodesDto msiARChargeCodes, List<ParcelAuditDetailsDto> previousShipment) {
        DirectJDBCDAO directJDBCDAO = new DirectJDBCDAO();
        Map<String, String> dasChargeList = msiARChargeCodes.getDasChargeCodes();
        Map<String, String> lpsChargeCodes = msiARChargeCodes.getLpsChargeCodes();
        List<RatedChargeDetailsDto> ratedCharges = null;
        if(previousShipment != null && !previousShipment.isEmpty()){
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
                                prevRatedFrtAmt = ParcelRatingUtil.findRtrAmountByChargeClassificationCode("FRT", ratedCharges);
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
                            rateDetails.setHwtIdentifier(auditDetails.getMultiWeightNumber());
                            directJDBCDAO.updateShipmentRateDetails(ParcelAuditConstant.EBILL_GFF_TABLE_NAME, auditDetails.getId().toString(), ParcelAuditConstant.PARCEL_RTR_RATING_USER_NAME, rateDetails);

                            frtChargeFound = true;
                        }else{
                            ParcelRateDetailsDto rateDetails = ParcelRateDetailsDto.getInstance();
                            rateDetails.setShipperCategory(shipperCategory);
                            rateDetails.setContractName(contractName);
                            rateDetails.setFuelTablePercentage(fuelTablePerc);
                            rateDetails.setRtrAmount(new BigDecimal("0"));
                            rateDetails.setRtrStatus(rtrStatus.value);
                            rateDetails.setHwtIdentifier(auditDetails.getMultiWeightNumber());
                            directJDBCDAO.updateShipmentRateDetails(ParcelAuditConstant.EBILL_GFF_TABLE_NAME, auditDetails.getId().toString(), ParcelAuditConstant.PARCEL_RTR_RATING_USER_NAME, rateDetails);
                        }
                    }else{
                        ParcelRateDetailsDto rateDetails = ParcelRateDetailsDto.getInstance();
                        rateDetails.setShipperCategory(shipperCategory);
                        rateDetails.setContractName(contractName);
                        rateDetails.setFuelTablePercentage(fuelTablePerc);
                        rateDetails.setRtrAmount(new BigDecimal("0"));
                        rateDetails.setRtrStatus(rtrStatus.value);
                        rateDetails.setHwtIdentifier(auditDetails.getMultiWeightNumber());
                        directJDBCDAO.updateShipmentRateDetails(ParcelAuditConstant.EBILL_GFF_TABLE_NAME, auditDetails.getId().toString(), ParcelAuditConstant.PARCEL_RTR_RATING_USER_NAME, rateDetails);
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
                            prevRatedFscAmt = ParcelRatingUtil.findRtrAmountByChargeClassificationCode("FSC", ratedCharges);
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
                        rateDetails.setHwtIdentifier(auditDetails.getMultiWeightNumber());
                        directJDBCDAO.updateShipmentRateDetails(ParcelAuditConstant.EBILL_GFF_TABLE_NAME, auditDetails.getId().toString(), ParcelAuditConstant.PARCEL_RTR_RATING_USER_NAME, rateDetails);
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
                        BigDecimal prevRatedResAmt = ParcelRatingUtil.findRtrAmountByChargeClassificationCodeAndChargeDescriptionCode("ACC", "RES", ratedCharges);
                        if(prevRatedResAmt == null){
                            prevRatedResAmt = ParcelRatingUtil.findRtrAmountByChargeClassificationCodeAndChargeDescriptionCode("ACC", "RSC", ratedCharges);
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
                        rateDetails.setHwtIdentifier(auditDetails.getMultiWeightNumber());
                        directJDBCDAO.updateShipmentRateDetails(ParcelAuditConstant.EBILL_GFF_TABLE_NAME, auditDetails.getId().toString(), ParcelAuditConstant.PARCEL_RTR_RATING_USER_NAME, rateDetails);
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
                        BigDecimal prevRatedDasAmt = ParcelRatingUtil.findRtrAmountByChargeClassificationCodeAndChargeDescriptionCode("ACC", auditDetails.getChargeDescriptionCode(), ratedCharges);
                        if(charge.getAmount() != null){
                            if(prevRatedDasAmt != null){
                                dasAmount = charge.getAmount().subtract(prevRatedDasAmt);
                            }else{
                                dasAmount = charge.getAmount();
                            }

                        }
                        rateDetails.setRtrAmount(dasAmount);
                        rateDetails.setRtrStatus(rtrStatus.value);
                        rateDetails.setHwtIdentifier(auditDetails.getMultiWeightNumber());
                        directJDBCDAO.updateShipmentRateDetails(ParcelAuditConstant.EBILL_GFF_TABLE_NAME, auditDetails.getId().toString(), ParcelAuditConstant.PARCEL_RTR_RATING_USER_NAME, rateDetails);
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
                        rateDetails.setHwtIdentifier(auditDetails.getMultiWeightNumber());
                        directJDBCDAO.updateShipmentRateDetails(ParcelAuditConstant.EBILL_GFF_TABLE_NAME, auditDetails.getId().toString(), ParcelAuditConstant.PARCEL_RTR_RATING_USER_NAME, rateDetails);
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
                            BigDecimal prevRatedAccAmt = ParcelRatingUtil.findRtrAmountByChargeClassificationCodeAndChargeDescriptionCode("ACC", auditDetails.getChargeDescriptionCode(), ratedCharges);
                            if(charge.getAmount() != null){
                                if(prevRatedAccAmt != null){
                                    otherAcc = charge.getAmount().subtract(prevRatedAccAmt);
                                }else{
                                    otherAcc = charge.getAmount();
                                }

                            }
                            rateDetails.setRtrAmount(otherAcc);
                            rateDetails.setRtrStatus(rtrStatus.value);
                            rateDetails.setHwtIdentifier(auditDetails.getMultiWeightNumber());
                            directJDBCDAO.updateShipmentRateDetails(ParcelAuditConstant.EBILL_GFF_TABLE_NAME, auditDetails.getId().toString(), ParcelAuditConstant.PARCEL_RTR_RATING_USER_NAME, rateDetails);
                        } else {
                            rateDetails.setRtrAmount(charge.getAmount());
                            rateDetails.setRtrStatus(rtrStatus.value);
                            rateDetails.setHwtIdentifier(auditDetails.getMultiWeightNumber());
                            directJDBCDAO.updateShipmentRateDetails(ParcelAuditConstant.EBILL_GFF_TABLE_NAME, auditDetails.getId().toString(), ParcelAuditConstant.PARCEL_RTR_RATING_USER_NAME, rateDetails);
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
                    rateDetails.setHwtIdentifier(auditDetails.getMultiWeightNumber());
                    directJDBCDAO.updateShipmentRateDetails(ParcelAuditConstant.EBILL_GFF_TABLE_NAME, auditDetails.getId().toString(), ParcelAuditConstant.PARCEL_RTR_RATING_USER_NAME, rateDetails);
                }
            }
        }

        ParcelRateDetailsDto otherDscRateDetails = ParcelRateDetailsDto.getInstance();
        otherDscRateDetails.setShipperCategory(shipperCategory);
        otherDscRateDetails.setContractName(contractName);
        otherDscRateDetails.setHwtIdentifier(parcelAuditDetails.get(0).getMultiWeightNumber());
        saveOtherDiscountsAppliedForUps(priceSheet, parcelAuditDetails, otherDscRateDetails, mappedDscChanges);

        ParcelRateDetailsDto accessorialRateDetails = ParcelRateDetailsDto.getInstance();
        accessorialRateDetails.setShipperCategory(shipperCategory);
        accessorialRateDetails.setContractName(contractName);
        accessorialRateDetails.setHwtIdentifier(parcelAuditDetails.get(0).getMultiWeightNumber());
        saveAccessorialForUps(priceSheet, parcelAuditDetails, accessorialRateDetails, mappedAccChanges, ratedCharges);
        return rtrStatus.value;
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
                    new DirectJDBCDAO().updateAccessorialShipmentRateDetails(ParcelAuditConstant.EBILL_GFF_TABLE_NAME, entityIds.toString(), ParcelAuditConstant.PARCEL_RTR_RATING_USER_NAME, rateDetails);
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

    public List<RatedChargeDetailsDto> getRatedChargeAmount(Long parentId){
        return new DirectJDBCDAO().getRatedChargeAmount(parentId);
    }

    public String doParcelRatingForUpsCarrier(List<RatingQueueBean> beans) {
        String status = null;
        if (beans != null) {
            String url = ParcelAuditConstant.AR_RATE_REQUEST_PROTOCOL + "://" + ParcelAuditConstant.AR_RATE_REQUEST_HOST_NAME + "/" + ParcelAuditConstant.AR_RATE_REQUEST_URI_PATH;
            String licenseKey = ParcelAuditConstant.AR_RATE_REQUEST_LICENSE_KEY;

            String trackingNumbers = ParcelRatingUtil.prepareTrackingNumbersInOperator(beans);
            List<ParcelAuditDetailsDto> shipmentRecords = null;
            if (trackingNumbers != null && !trackingNumbers.isEmpty()) {
                try {
                    MsiARChargeCodesDto msiARChargeCode = getAllMappedARChargeCodes();
                    shipmentRecords = getUpsParcelShipmentDetails(null, trackingNumbers, true, false);
                    if (shipmentRecords != null && !shipmentRecords.isEmpty()) {

                        status = callRTRAndPopulateRates(url, licenseKey, shipmentRecords, msiARChargeCode, null, null, beans);


                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return status;
    }
}
