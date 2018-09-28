package com.envista.msi.rating.service;

import com.envista.msi.api.domain.util.ParcelRatingUtil;
import com.envista.msi.api.web.rest.dto.rtr.MsiARChargeCodesDto;
import com.envista.msi.api.web.rest.dto.rtr.ParcelARChargeCodeMappingDto;
import com.envista.msi.api.web.rest.dto.rtr.ParcelAuditDetailsDto;
import com.envista.msi.api.web.rest.dto.rtr.ParcelRateDetailsDto;
import com.envista.msi.api.web.rest.dto.rtr.RatedChargeDetailsDto;
import com.envista.msi.api.web.rest.util.CommonUtil;
import com.envista.msi.api.web.rest.util.audit.parcel.ParcelAuditConstant;
import com.envista.msi.api.web.rest.util.audit.parcel.ParcelRateResponse;
import com.envista.msi.api.web.rest.util.audit.parcel.ParcelRateResponseParser;
import com.envista.msi.rating.bean.AccessorialDto;
import com.envista.msi.rating.bean.RatingQueueBean;
import com.envista.msi.rating.bean.ServiceFlagAccessorialBean;
import com.envista.msi.rating.dao.DirectJDBCDAO;
import com.envista.msi.rating.dao.RatingQueueDAO;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;

/**
 * Created by Sujit kumar on 05/05/2018.
 */
public class ParcelUpsRatingService {
    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(ParcelUpsRatingService.class);
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
            chargeCodeMap = prepareChargeCodeMap(mappedChargeCodes);
        }
        return chargeCodeMap;
    }

    public Map<String, String> getMappedLPSChargeCodes(){
        Map<String, String> chargeCodeMap = null;
        List<ParcelARChargeCodeMappingDto> mappedChargeCodes = new DirectJDBCDAO().loadMappedARChargeCodes(ParcelAuditConstant.MSI_AR_CHARGE_CODE_MAPPING_MODELE_NAME, ParcelAuditConstant.MSI_AR_LPS_CHARGE_CODE_NAME);
        if(mappedChargeCodes != null && !mappedChargeCodes.isEmpty()){
            chargeCodeMap = prepareChargeCodeMap(mappedChargeCodes);
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

    public String doParcelRatingForUpsCarrier(RatingQueueBean bean, List<ServiceFlagAccessorialBean> accessorialBeans) {
        String status = null;
        if(bean != null){
            String url = ParcelAuditConstant.AR_RATE_REQUEST_PROTOCOL + "://" + ParcelAuditConstant.AR_RATE_REQUEST_HOST_NAME + "/" + ParcelAuditConstant.AR_RATE_REQUEST_URI_PATH;
            String licenseKey = ParcelAuditConstant.AR_RATE_REQUEST_LICENSE_KEY;

            String trackingNumber = bean.getTrackingNumber();
            List<ParcelAuditDetailsDto> shipmentRecords = null;
            if(trackingNumber != null && !trackingNumber.isEmpty()){
                try{
                    shipmentRecords = getUpsParcelShipmentDetails(null, trackingNumber, true, false);
                    if(shipmentRecords != null && !shipmentRecords.isEmpty()) {
                        Map<Long, List<ParcelAuditDetailsDto>> shipments = ParcelRatingUtil.organiseShipmentsByParentId(shipmentRecords);
                        List<ParcelAuditDetailsDto> shipmentToRate = shipments.get(bean.getParentId());

                        if(shipmentToRate != null && !shipmentToRate.isEmpty()) {
                            if(ParcelRatingUtil.isFirstShipmentToRate(shipments, bean.getParentId())) {
                                if(ParcelRatingUtil.isRatedWithException(shipmentToRate)) {
                                    status = ParcelAuditConstant.RTRStatus.RATING_EXCEPTION.value;
                                } else if(ParcelRatingUtil.isRatedWithEmptyPriceSheet(shipmentToRate)) {
                                    status = ParcelAuditConstant.RTRStatus.NO_PRICE_SHEET.value;
                                } else if(!ParcelRatingUtil.isShipmentRated(shipmentToRate)) {
                                    status = callRTRAndPopulateRates(url, licenseKey, shipmentToRate, null, bean, accessorialBeans);
                                }
                            } else {
                                if(!ParcelRatingUtil.isShipmentRated(shipmentToRate)) {
                                    List<ParcelAuditDetailsDto> previousShipment = ParcelRatingUtil.getPreviousShipmentDetails(shipments, bean.getParentId());
                                    if(previousShipment!= null && !previousShipment.isEmpty()){
                                        if(ParcelRatingUtil.isShipmentRated(previousShipment)){


                                            if(shipmentToRate != null) {

                                                        status = callRTRAndPopulateRates(url, licenseKey, shipmentToRate, previousShipment, bean, accessorialBeans);
                                                    }


                                        } else if(ParcelRatingUtil.isRatedWithException(previousShipment)) {
                                            status = ParcelAuditConstant.RTRStatus.RATING_EXCEPTION.value;
                                        } else if(ParcelRatingUtil.isRatedWithEmptyPriceSheet(previousShipment)) {
                                            status = ParcelAuditConstant.RTRStatus.NO_PRICE_SHEET.value;
                                        }
                                    }
                                }
                            }
                        }

                        //To check whether it is a void shipment or not, if so then update the IS_VOID_SHIPMENT = 1.
                        checkForVoidShipmentAndUpdate(shipmentRecords);
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
        return status;
    }

    private void checkForVoidShipmentAndUpdate(List<ParcelAuditDetailsDto> shipmentRecords) {
        try{
            if(ParcelRatingUtil.isVoidShipment(shipmentRecords)){
                BigDecimal totalNetAmount = new BigDecimal("0");

                StringJoiner entityIds = new StringJoiner(",");
                for(ParcelAuditDetailsDto ship : shipmentRecords){
                    if(ship != null && ship.getId() != null){
                        if(ship.getNetAmount() != null && !ship.getNetAmount().isEmpty()){
                            totalNetAmount = totalNetAmount.add(new BigDecimal(ship.getNetAmount()));
                        }
                        entityIds.add(ship.getId().toString());
                    }
                }
                if(totalNetAmount.compareTo(new BigDecimal("0")) == 0) {
                    new DirectJDBCDAO().updateRatingVoidShipmentStatus(ParcelAuditConstant.EBILL_GFF_TABLE_NAME, entityIds.toString(), 1);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            log.error("ERROR in checkForVoidShipmentAndUpdate", e.getMessage());
        }
    }

    private String callRTRAndPopulateRates(String url, String licenseKey, List<ParcelAuditDetailsDto> parcelAuditDetails, List<ParcelAuditDetailsDto> previousShipment, RatingQueueBean bean, List<ServiceFlagAccessorialBean> accessorialBeans) throws Exception {
        return callRTRAndPopulateRates(url, licenseKey, parcelAuditDetails, null, previousShipment, bean, null, accessorialBeans);
    }

    private String callRTRAndPopulateRates(String url, String licenseKey, List<ParcelAuditDetailsDto> parcelAuditDetails, List<ParcelAuditDetailsDto> previousShipment, RatingQueueBean bean, List<RatingQueueBean> beans, List<ServiceFlagAccessorialBean> accessorialBeans) throws Exception {
        return callRTRAndPopulateRates(url, licenseKey, parcelAuditDetails, null, previousShipment, bean, beans, accessorialBeans);
    }

    private String callRTRAndPopulateRates(String url, String licenseKey, List<ParcelAuditDetailsDto> parcelAuditDetails, ParcelAuditDetailsDto commercialCharge, List<ParcelAuditDetailsDto> previousShipment, RatingQueueBean bean, List<RatingQueueBean> beans, List<ServiceFlagAccessorialBean> accessorialBeans) throws Exception {
        String requestPayload = "";
        String response = "";
        String status = "";
        DirectJDBCDAO directJDBCDAO = new DirectJDBCDAO();

            requestPayload = com.envista.msi.rating.util.ParcelRateRequestBuilder.buildParcelRateRequest(bean, ParcelAuditConstant.AR_RATE_REQUEST_LICENSE_KEY, beans).toXmlString();
            response = CommonUtil.connectAndGetResponseAsString(url, requestPayload);
            if (response != null && !response.trim().isEmpty()) {
                BigDecimal hwtNetAmount = null;
                if (beans != null && beans.size() > 0) {
                    hwtNetAmount = ParcelRatingUtil.findSumOfNetAmount(parcelAuditDetails);
                    parcelAuditDetails = ParcelRatingUtil.updateWeightAndNetChargesForHwt(parcelAuditDetails);
                    bean = ParcelRatingUtil.getLeadShipmentQueueBean(parcelAuditDetails, beans);
                }

                directJDBCDAO.saveParcelAuditRequestAndResponseLog(ParcelRatingUtil.prepareRequestResponseLog(requestPayload, response, parcelAuditDetails.get(0).getParentId(), ParcelAuditConstant.EBILL_GFF_TABLE_NAME));
                status = updateRateForUps(ParcelRateResponseParser.parse(response), parcelAuditDetails, previousShipment, hwtNetAmount, bean, accessorialBeans);
            }


        updateUpsOtherFieldValues(parcelAuditDetails);
/*        if(status != null && !status.isEmpty()){
            new DirectJDBCDAO().updateRtrStatus(21L, bean.getTrackingNumber(), status);
        }*/
        return status;
    }

    private String updateRateForUps(ParcelRateResponse parcelRateResponse, List<ParcelAuditDetailsDto> parcelAuditDetails, List<ParcelAuditDetailsDto> previousShipment, BigDecimal hwtNetAmount, RatingQueueBean bean, List<ServiceFlagAccessorialBean> accessorialBeans) throws Exception {
        return updateRateForUps(parcelRateResponse, parcelAuditDetails, null, previousShipment, hwtNetAmount, bean, accessorialBeans);
    }

    private String updateRateForUps(ParcelRateResponse parcelRateResponse, List<ParcelAuditDetailsDto> parcelAuditDetails, ParcelAuditDetailsDto commercialAdjCharge, List<ParcelAuditDetailsDto> previousShipment, BigDecimal hwtNetAmount, RatingQueueBean bean, List<ServiceFlagAccessorialBean> accessorialBeans) throws Exception {
        String status = "";
        if(parcelRateResponse != null){
            if(parcelRateResponse.getStatusCode() != null && parcelRateResponse.getStatusCode().equals(0)){
                if(parcelRateResponse.getPriceSheets() != null && !parcelRateResponse.getPriceSheets().isEmpty()){
                    //Taking the first price sheet, as per the discussion we finalised that the first price sheet will be be correct and rated based on the latest contract.
                    ParcelRateResponse.PriceSheet firstPriceSheet = parcelRateResponse.getPriceSheets().get(0);
                    BigDecimal sumOfNetAmount = null;
                    if (hwtNetAmount != null)
                        sumOfNetAmount = hwtNetAmount;
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

                        status = updateAmountWithRTRResponseChargesForUps(firstPriceSheet, parcelAuditDetails, ParcelAuditConstant.RTRStatus.CLOSED, flagged, accessorialBeans, bean);


                    } else {
                        if(sumOfNetAmount.compareTo(totalRateAmount) < 0){


                            status = updateAmountWithRTRResponseChargesForUps(firstPriceSheet, parcelAuditDetails, ParcelAuditConstant.RTRStatus.UNDER_CHARGED, flagged, accessorialBeans, bean);


                        } else if(sumOfNetAmount.compareTo(totalRateAmount) > 0){


                            status = updateAmountWithRTRResponseChargesForUps(firstPriceSheet, parcelAuditDetails, ParcelAuditConstant.RTRStatus.OVER_CHARGED, flagged, accessorialBeans, bean);


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


    private String updateAmountWithRTRResponseChargesForUps(ParcelRateResponse.PriceSheet priceSheet, List<ParcelAuditDetailsDto> parcelAuditDetails, ParcelAuditConstant.RTRStatus rtrStatus, String flagged, List<ServiceFlagAccessorialBean> accessorialBeans, RatingQueueBean queueBean) throws Exception {
        DirectJDBCDAO directJDBCDAO = new DirectJDBCDAO();
        boolean frtChargeFound = false;
        boolean fscChargeFound = false;
        List<ParcelRateResponse.Charge> mappedAccChanges = new ArrayList<>();
        List<ParcelRateResponse.Charge> mappedDscChanges = new ArrayList<>();
        List<String> mappedAccList = new ArrayList<>();
        BigDecimal ratedGrossFuel = ParcelRateResponseParser.getRatedGrossFuel(priceSheet);

        List<AccessorialDto> prevParentsRatesDtos = directJDBCDAO.getRatesForPrevParentIds(parcelAuditDetails.get(0).getTrackingNumber(), parcelAuditDetails.get(0).getParentId());

        for(ParcelAuditDetailsDto auditDetails : parcelAuditDetails){
            if(auditDetails != null && auditDetails.getChargeClassificationCode() != null && !auditDetails.getChargeClassificationCode().isEmpty()){
                ParcelRateResponse.Charge charge = null;
                if(ParcelAuditConstant.ChargeClassificationCode.FRT.name().equalsIgnoreCase(auditDetails.getChargeClassificationCode())){
                    if (!frtChargeFound) {

                            charge = ParcelRateResponseParser.findChargeByType(ParcelRateResponse.ChargeType.ITEM.name(), priceSheet);
                            if(charge != null){
                                ParcelRateDetailsDto rateDetails = ParcelRateDetailsDto.getInstance();

                                rateDetails.setDimDivisor(charge.getDimDivisor() == null ? new BigDecimal("0") : charge.getDimDivisor());
                                rateDetails.setRatedWeight(charge.getWeight() == null ? new BigDecimal("0") : charge.getWeight());

                                BigDecimal prevRtrAmt = ParcelRatingUtil.findPrevRateAmtByCode(prevParentsRatesDtos, "FRT", "accessorial");
                                if(!mappedAccList.contains("FTR"))
                                    rateDetails.setRtrAmount(charge.getAmount().subtract(prevRtrAmt));
                                else
                                    rateDetails.setRtrAmount(new BigDecimal("0.00"));

                                rateDetails.setRtrStatus(rtrStatus.value);
                                rateDetails.setHwtIdentifier(auditDetails.getMultiWeightNumber());

                                rateDetails.setFlagged(flagged);
                                rateDetails.setAccCode("FRT");

                                mappedAccList.add("FRT");

                                setCommonValues(rateDetails, queueBean, priceSheet);

                                if (auditDetails.getId().compareTo(auditDetails.getParentId()) == 0)
                                    ParcelRateResponseParser.mapPercentageAndDis(rateDetails, priceSheet, mappedDscChanges, prevParentsRatesDtos);

                                directJDBCDAO.updateShipmentRateDetails(ParcelAuditConstant.EBILL_GFF_TABLE_NAME, auditDetails.getId().toString(), ParcelAuditConstant.PARCEL_RTR_RATING_USER_NAME, rateDetails);
                            }
                            frtChargeFound = true;

                    } else {
                        ParcelRateDetailsDto rateDetails = ParcelRateDetailsDto.getInstance();
                        rateDetails.setRtrAmount(new BigDecimal("0"));
                        rateDetails.setRtrStatus(rtrStatus.value);
                        rateDetails.setHwtIdentifier(auditDetails.getMultiWeightNumber());
                        rateDetails.setFlagged(flagged);
                        setCommonValues(rateDetails, queueBean, priceSheet);
                        if (auditDetails.getId().compareTo(auditDetails.getParentId()) == 0)
                            ParcelRateResponseParser.mapPercentageAndDis(rateDetails, priceSheet, mappedDscChanges, prevParentsRatesDtos);

                        directJDBCDAO.updateShipmentRateDetails(ParcelAuditConstant.EBILL_GFF_TABLE_NAME, auditDetails.getId().toString(), ParcelAuditConstant.PARCEL_RTR_RATING_USER_NAME, rateDetails);
                    }
                }else if(ParcelAuditConstant.ChargeClassificationCode.FSC.name().equalsIgnoreCase(auditDetails.getChargeClassificationCode())){
                    fscChargeFound = true;
                    charge = ParcelRateResponseParser.findChargeByType(ParcelRateResponse.ChargeType.ACCESSORIAL_FUEL.name(), priceSheet);
                    if(charge != null){
                        ParcelRateDetailsDto rateDetails = ParcelRateDetailsDto.getInstance();

                        rateDetails.setRatedGrossFuel(ratedGrossFuel);
                        rateDetails.setDimDivisor(charge.getDimDivisor() == null ? new BigDecimal("0") : charge.getDimDivisor());
                        rateDetails.setRatedWeight(charge.getWeight() == null ? new BigDecimal("0") : charge.getWeight());
                        BigDecimal prevRtrAmt = ParcelRatingUtil.findPrevRateAmtByCode(prevParentsRatesDtos, "FRT", "accessorial");
                        if(!mappedAccList.contains("FSC"))
                            rateDetails.setRtrAmount(charge.getAmount().subtract(prevRtrAmt));
                        else
                            rateDetails.setRtrAmount(new BigDecimal("0.00"));
                        rateDetails.setRtrStatus(rtrStatus.value);
                        rateDetails.setHwtIdentifier(auditDetails.getMultiWeightNumber());
                        rateDetails.setFlagged(flagged);
                        rateDetails.setAccCode("FSC");

                        mappedAccList.add("FSC");

                        setCommonValues(rateDetails, queueBean, priceSheet);

                        if (auditDetails.getId().compareTo(auditDetails.getParentId()) == 0)
                            ParcelRateResponseParser.mapPercentageAndDis(rateDetails, priceSheet, mappedDscChanges, prevParentsRatesDtos);

                        directJDBCDAO.updateShipmentRateDetails(ParcelAuditConstant.EBILL_GFF_TABLE_NAME, auditDetails.getId().toString(), ParcelAuditConstant.PARCEL_RTR_RATING_USER_NAME, rateDetails);
                    }
                } else{
                    ServiceFlagAccessorialBean bean = ParcelRatingUtil.getAccessorialBean(accessorialBeans, auditDetails.getChargeDescription(), auditDetails.getChargeDescriptionCode(), 21L);
                    if (bean != null) {
/*                        if("RSC".equalsIgnoreCase(bean.getLookUpValue()))
                            bean.setLookUpValue("RSS");*/

                        charge = ParcelRateResponseParser.findChargeByEDICodeInResponse(bean.getLookUpValue(), priceSheet);
                    }
                    if(charge != null){
                        mappedAccChanges.add(charge);
                        if(charge != null && charge.getType() != null){
                            if(ParcelRateResponse.ChargeType.ACCESSORIAL.name().equalsIgnoreCase(charge.getType())) mappedAccChanges.add(charge);
                            else if(ParcelRateResponse.ChargeType.DISCOUNT.name().equalsIgnoreCase(charge.getType())) mappedDscChanges.add(charge);
                        }
                        ParcelRateDetailsDto rateDetails = ParcelRateDetailsDto.getInstance();

                        rateDetails.setDimDivisor(charge.getDimDivisor() == null ? new BigDecimal("0") : charge.getDimDivisor());
                        rateDetails.setRatedWeight(charge.getWeight() == null ? new BigDecimal("0") : charge.getWeight());
                        BigDecimal prevRtrAmt = ParcelRatingUtil.findPrevRateAmtByCode(prevParentsRatesDtos, "FRT", "accessorial");
                        if(bean != null && !mappedAccList.contains(bean.getLookUpValue()))
                            rateDetails.setRtrAmount(charge.getAmount().subtract(prevRtrAmt));
                        else
                            rateDetails.setRtrAmount(new BigDecimal("0.00"));

                        rateDetails.setRtrStatus(rtrStatus.value);
                        rateDetails.setHwtIdentifier(auditDetails.getMultiWeightNumber());

                        rateDetails.setFlagged(flagged);
                        if (bean != null) {
                            rateDetails.setAccCode(bean.getLookUpValue());
                            mappedAccList.add(bean.getLookUpValue());
                        }
                        setCommonValues(rateDetails, queueBean, priceSheet);
                        if (auditDetails.getId().compareTo(auditDetails.getParentId()) == 0)
                            ParcelRateResponseParser.mapPercentageAndDis(rateDetails, priceSheet, mappedDscChanges, prevParentsRatesDtos);

                        directJDBCDAO.updateShipmentRateDetails(ParcelAuditConstant.EBILL_GFF_TABLE_NAME, auditDetails.getId().toString(), ParcelAuditConstant.PARCEL_RTR_RATING_USER_NAME, rateDetails);
                    }
                }

                if(null == charge){
                    ParcelRateDetailsDto rateDetails = ParcelRateDetailsDto.getInstance();

                    rateDetails.setRtrAmount(new BigDecimal("0.00"));
                    rateDetails.setRtrStatus(rtrStatus.value);
                    rateDetails.setHwtIdentifier(auditDetails.getMultiWeightNumber());

                    rateDetails.setFlagged(flagged);
                    ServiceFlagAccessorialBean bean = ParcelRatingUtil.getAccessorialBean(accessorialBeans, auditDetails.getChargeDescription(), auditDetails.getChargeDescriptionCode(), 21L);

                    if (bean != null) {
/*                        if ("RSC".equalsIgnoreCase(bean.getLookUpValue()))
                            bean.setLookUpValue("RSS");*/
                        rateDetails.setAccCode(bean.getLookUpValue());
                    } else
                        rateDetails.setAccCode("UNKNOWN");

                    setCommonValues(rateDetails, queueBean, priceSheet);
                    if (auditDetails.getId().compareTo(auditDetails.getParentId()) == 0)
                        ParcelRateResponseParser.mapPercentageAndDis(rateDetails, priceSheet, mappedDscChanges, prevParentsRatesDtos);

                    directJDBCDAO.updateShipmentRateDetails(ParcelAuditConstant.EBILL_GFF_TABLE_NAME, auditDetails.getId().toString(), ParcelAuditConstant.PARCEL_RTR_RATING_USER_NAME, rateDetails);
                }
            }
        }


        List<AccessorialDto> addAccAndDisdtos = new ArrayList<>();

        prepareAdditionalAccessorialForUps(priceSheet, parcelAuditDetails.get(0).getParentId(), mappedAccChanges, addAccAndDisdtos, frtChargeFound, fscChargeFound, prevParentsRatesDtos);
        prepareAddDiscounts(priceSheet, parcelAuditDetails.get(0).getParentId(), mappedDscChanges, addAccAndDisdtos, prevParentsRatesDtos);

        directJDBCDAO.saveAccInfo(addAccAndDisdtos, parcelAuditDetails.get(0).getParentId());

        return rtrStatus.value;
    }

    private void setCommonValues(ParcelRateDetailsDto rateDetails, RatingQueueBean queueBean, ParcelRateResponse.PriceSheet priceSheet) {

        rateDetails.setReturnFlag(queueBean.getReturnFlag());
        rateDetails.setResiFlag(queueBean.getResiFlag());
        rateDetails.setComToRes(queueBean.getComToRes());
        rateDetails.setRateSetName(priceSheet.getRateSet());
        rateDetails.setShipperCategory(priceSheet.getCategory());
        rateDetails.setContractName(priceSheet.getContractName());
        rateDetails.setZone(priceSheet.getZone());
        rateDetails.setActualServiceBucket(queueBean.getActualServiceBucket());

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

                    DirectJDBCDAO directJDBCDAO = new DirectJDBCDAO();
                    directJDBCDAO.updateOtherDiscountShipmentRateDetails(ParcelAuditConstant.EBILL_GFF_TABLE_NAME, entityIds.toString(), ParcelAuditConstant.PARCEL_RTR_RATING_USER_NAME, rateDetails);
                    // new ParcelRTRDao().updateOtherDiscountShipmentRateDetails(ParcelAuditConstant.EBILL_GFF_TABLE_NAME, entityIds.toString(), ParcelAuditConstant.PARCEL_RTR_RATING_USER_NAME, rateDetails);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String updateAmountWithRTRResponseChargesForUpsCommercialAdjustment(ParcelRateResponse.PriceSheet priceSheet, List<ParcelAuditDetailsDto> parcelAuditDetails, ParcelAuditConstant.RTRStatus rtrStatus, ParcelAuditDetailsDto commercialAdjCharge, List<ParcelAuditDetailsDto> previousShipment, String flagged, List<ServiceFlagAccessorialBean> accessorialBeans) {
        DirectJDBCDAO directJDBCDAO = new DirectJDBCDAO();
        boolean frtChargeFound = false;
        boolean accessorial1Mapped = false;
        boolean accessorial2Mapped = false;
        boolean accessorial3Mapped = false;
        boolean accessorial4Mapped = false;
        List<ParcelRateResponse.Charge> mappedAccChanges = new ArrayList<>();
        ParcelRateDetailsDto rateDetails = ParcelRateDetailsDto.getInstance();
        rateDetails.setContractName(priceSheet.getContractName());
        rateDetails.setShipperCategory(priceSheet.getCategory());
        rateDetails.setRateSetName(priceSheet.getRateSet());
        rateDetails.setZone(priceSheet.getZone());
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
                } else {
                    ServiceFlagAccessorialBean bean = ParcelRatingUtil.getAccessorialBean(accessorialBeans, auditDetails.getChargeDescription(), auditDetails.getChargeDescriptionCode(), 21L);

                    if (bean != null){
                        /*if("RSC".equalsIgnoreCase(bean.getLookUpValue())) bean.setLookUpValue("RSS");*/
                        charge = ParcelRateResponseParser.findChargeByEDICodeInResponse(bean.getLookUpValue(), priceSheet);
                    }

                    if(charge != null){
                        if(charge != null && charge.getType() != null){
                            if(ParcelRateResponse.ChargeType.ACCESSORIAL.name().equalsIgnoreCase(charge.getType())) mappedAccChanges.add(charge);
                        }

                        if (rateDetails.getAccessorial1() == null && !accessorial1Mapped) {
                            mappedAccChanges.add(charge);
                            accessorial1Mapped = true;
                            rateDetails.setAccessorial1(charge.getAmount());
                            rateDetails.setAccessorial1Code(charge.getEdiCode());

                            if (rateDetails.getAccessorial1() != null)
                                totalRatedAmount = totalRatedAmount.add(rateDetails.getAccessorial1());

                        } else if (rateDetails.getAccessorial2() == null && !accessorial2Mapped) {
                            mappedAccChanges.add(charge);
                            accessorial2Mapped = true;
                            rateDetails.setAccessorial2(charge.getAmount());
                            rateDetails.setAccessorial2Code(charge.getEdiCode());

                            if (rateDetails.getAccessorial2() != null)
                                totalRatedAmount = totalRatedAmount.add(rateDetails.getAccessorial2());

                        } else if (rateDetails.getAccessorial3() == null && !accessorial3Mapped) {
                            mappedAccChanges.add(charge);
                            accessorial3Mapped = true;
                            rateDetails.setAccessorial3(charge.getAmount());
                            rateDetails.setAccessorial3Code(charge.getEdiCode());

                            if (rateDetails.getAccessorial3() != null)
                                totalRatedAmount = totalRatedAmount.add(rateDetails.getAccessorial3());

                        }

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
        } catch (Exception e) {
            e.printStackTrace();
        }

        BigDecimal prevTotalRated = previousShipment != null && !previousShipment.isEmpty() ? getRatedTotalAmount(previousShipment.get(0).getParentId(), previousShipment.get(0).getTrackingNumber()) : new BigDecimal("0");
        rateDetails.setRtrAmount(totalRatedAmount.subtract(prevTotalRated));
        rateDetails.setHwtIdentifier(parcelAuditDetails.get(0).getMultiWeightNumber());
        rateDetails.setRtrStatus(rtrStatus.value);
        rateDetails.setFlagged(flagged);

        directJDBCDAO.updateAllShipmentRateDetails(ParcelAuditConstant.EBILL_GFF_TABLE_NAME, commercialAdjCharge.getId().toString(), ParcelAuditConstant.PARCEL_RTR_RATING_USER_NAME, rateDetails);

        return rtrStatus.value;
    }

    private String updateAdjustedRateForUps(ParcelRateResponse.PriceSheet priceSheet, List<ParcelAuditDetailsDto> parcelAuditDetails, ParcelAuditConstant.RTRStatus rtrStatus, List<ParcelAuditDetailsDto> previousShipment, String flagged, List<ServiceFlagAccessorialBean> accessorialBeans) {
        DirectJDBCDAO directJDBCDAO = new DirectJDBCDAO();
        List<RatedChargeDetailsDto> ratedCharges = null;
        if(previousShipment != null && !previousShipment.isEmpty()){
            ratedCharges = getRatedChargeAmount(previousShipment.get(0).getParentId(), previousShipment.get(0).getTrackingNumber());
        }

        boolean frtChargeFound = false;
        List<ParcelRateResponse.Charge> mappedAccChanges = new ArrayList<>();
        List<ParcelRateResponse.Charge> mappedDscChanges = new ArrayList<>();
        String shipperCategory = priceSheet.getCategory();
        String contractName = priceSheet.getContractName();
        String rateSetName = priceSheet.getRateSet();
        String zone = priceSheet.getZone();
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
                            rateDetails.setZone(zone);

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

                            ParcelRateResponse.Charge dasDiscount = ParcelRateResponseParser.getRatedDasDiscount(priceSheet);
                            if (dasDiscount != null) {
                                mappedDscChanges.add(dasDiscount);
                                rateDetails.setDeliveryAreaSurchargeDiscount(dasDiscount.getAmount());
                            }

                            BigDecimal frtAmount = new BigDecimal("0");
                            BigDecimal prevRatedFrtAmt = new BigDecimal("0");
                            if(previousShipment != null && (ParcelRatingUtil.containsCharge(ParcelAuditConstant.COMMERCIAL_ADJUSTMENT_CHARGE_TYPE, previousShipment) || ParcelRatingUtil.containsCharge(ParcelAuditConstant.RESIDENTIAL_ADJUSTMENT_CHARGE_TYPE, previousShipment))){
                                prevRatedFrtAmt = ParcelRatingUtil.getRatedFreightChargeForCommOrResAjustment(ratedCharges);
                            }else{
                                if(previousShipment != null && previousShipment.contains(auditDetails)){
                                    prevRatedFrtAmt = ParcelRatingUtil.findRtrAmountByChargeClassificationCode("FRT", ratedCharges, auditDetails.getId());
                                } else {
                                    prevRatedFrtAmt = ParcelRatingUtil.findRtrAmountByChargeClassificationCode("FRT", ratedCharges);
                                }

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
                            rateDetails.setRateSetName(rateSetName);
                            rateDetails.setFlagged(flagged);
                            directJDBCDAO.updateShipmentRateDetails(ParcelAuditConstant.EBILL_GFF_TABLE_NAME, auditDetails.getId().toString(), ParcelAuditConstant.PARCEL_RTR_RATING_USER_NAME, rateDetails);

                            frtChargeFound = true;
                        }else{
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
                            directJDBCDAO.updateShipmentRateDetails(ParcelAuditConstant.EBILL_GFF_TABLE_NAME, auditDetails.getId().toString(), ParcelAuditConstant.PARCEL_RTR_RATING_USER_NAME, rateDetails);
                        }
                    }else{
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
                        rateDetails.setZone(zone);

                        BigDecimal fscAmount = new BigDecimal("0");
                        BigDecimal prevRatedFscAmt = new BigDecimal("0");
                        if(previousShipment != null && (ParcelRatingUtil.containsCharge(ParcelAuditConstant.COMMERCIAL_ADJUSTMENT_CHARGE_TYPE, previousShipment) || ParcelRatingUtil.containsCharge(ParcelAuditConstant.RESIDENTIAL_ADJUSTMENT_CHARGE_TYPE, previousShipment))){
                            prevRatedFscAmt = ParcelRatingUtil.getRatedFuelChargeForCommOrResAjustment(ratedCharges);
                        } else{
                            if(previousShipment != null && previousShipment.contains(auditDetails)){
                                prevRatedFscAmt = ParcelRatingUtil.findRtrAmountByChargeClassificationCode("FSC", ratedCharges, auditDetails.getId());
                            }else{
                                prevRatedFscAmt = ParcelRatingUtil.findRtrAmountByChargeClassificationCode("FSC", ratedCharges);
                            }
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
                        rateDetails.setRateSetName(rateSetName);
                        rateDetails.setFlagged(flagged);
                        directJDBCDAO.updateShipmentRateDetails(ParcelAuditConstant.EBILL_GFF_TABLE_NAME, auditDetails.getId().toString(), ParcelAuditConstant.PARCEL_RTR_RATING_USER_NAME, rateDetails);
                    }
                } else {
                    ServiceFlagAccessorialBean bean = ParcelRatingUtil.getAccessorialBean(accessorialBeans, auditDetails.getChargeDescription(), auditDetails.getChargeDescriptionCode(), 21L);
                    if (bean != null){
                       /* if("RSC".equalsIgnoreCase(bean.getLookUpValue())) bean.setLookUpValue("RSS");*/
                        charge = ParcelRateResponseParser.findChargeByEDICodeInResponse(bean.getLookUpValue(), priceSheet);
                    }

                    if(charge != null){
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

                        if(ParcelRateResponse.ChargeType.ACCESSORIAL.name().equalsIgnoreCase(charge.getType())){
                            BigDecimal otherAcc = new BigDecimal("0");
                            BigDecimal prevRatedAccAmt = new BigDecimal("0.0");
                            if(previousShipment != null && previousShipment.contains(auditDetails)){
                                prevRatedAccAmt = ParcelRatingUtil.findRtrAmountByChargeClassificationCodeAndChargeDescriptionCode("ACC", auditDetails.getChargeDescriptionCode(), ratedCharges, auditDetails.getId());
                            }else{
                                prevRatedAccAmt = ParcelRatingUtil.findRtrAmountByChargeClassificationCodeAndChargeDescriptionCode("ACC", auditDetails.getChargeDescriptionCode(), ratedCharges);
                            }
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
                            rateDetails.setRateSetName(rateSetName);
                            rateDetails.setFlagged(flagged);
                            directJDBCDAO.updateShipmentRateDetails(ParcelAuditConstant.EBILL_GFF_TABLE_NAME, auditDetails.getId().toString(), ParcelAuditConstant.PARCEL_RTR_RATING_USER_NAME, rateDetails);
                        } else {
                            rateDetails.setRtrAmount(charge.getAmount());
                            rateDetails.setRtrStatus(rtrStatus.value);
                            rateDetails.setHwtIdentifier(auditDetails.getMultiWeightNumber());
                            rateDetails.setRateSetName(rateSetName);
                            rateDetails.setFlagged(flagged);
                            directJDBCDAO.updateShipmentRateDetails(ParcelAuditConstant.EBILL_GFF_TABLE_NAME, auditDetails.getId().toString(), ParcelAuditConstant.PARCEL_RTR_RATING_USER_NAME, rateDetails);
                        }
                    }
                }

                if(null == charge){
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
                    directJDBCDAO.updateShipmentRateDetails(ParcelAuditConstant.EBILL_GFF_TABLE_NAME, auditDetails.getId().toString(), ParcelAuditConstant.PARCEL_RTR_RATING_USER_NAME, rateDetails);
                }
            }
        }

        ParcelRateDetailsDto otherDscRateDetails = ParcelRateDetailsDto.getInstance();
        otherDscRateDetails.setShipperCategory(shipperCategory);
        otherDscRateDetails.setContractName(contractName);
        otherDscRateDetails.setHwtIdentifier(parcelAuditDetails.get(0).getMultiWeightNumber());
        otherDscRateDetails.setRateSetName(rateSetName);
        otherDscRateDetails.setFlagged(flagged);
        otherDscRateDetails.setZone(zone);
        saveOtherDiscountsAppliedForUps(priceSheet, parcelAuditDetails, otherDscRateDetails, mappedDscChanges);

        ParcelRateDetailsDto accessorialRateDetails = ParcelRateDetailsDto.getInstance();
        accessorialRateDetails.setShipperCategory(shipperCategory);
        accessorialRateDetails.setContractName(contractName);
        accessorialRateDetails.setHwtIdentifier(parcelAuditDetails.get(0).getMultiWeightNumber());
        accessorialRateDetails.setRateSetName(rateSetName);
        accessorialRateDetails.setFlagged(flagged);
        accessorialRateDetails.setZone(zone);
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
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public BigDecimal getRatedTotalAmount(Long parentId, String trackingNumber){
        BigDecimal total = new BigDecimal("0");
        boolean frtFound = false;
        List<RatedChargeDetailsDto> ratedChargeDetails = getRatedChargeAmount(parentId, trackingNumber);
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

    public List<RatedChargeDetailsDto> getRatedChargeAmount(Long parentId, String trackingNumber){
        return new DirectJDBCDAO().getRatedChargeAmount(parentId, trackingNumber);
    }

    public String doParcelRatingForUpsCarrier(List<RatingQueueBean> beans, List<ServiceFlagAccessorialBean> accessorialBeans) {
        String status = null;
        if (beans != null) {
            String url = ParcelAuditConstant.AR_RATE_REQUEST_PROTOCOL + "://" + ParcelAuditConstant.AR_RATE_REQUEST_HOST_NAME + "/" + ParcelAuditConstant.AR_RATE_REQUEST_URI_PATH;
            String licenseKey = ParcelAuditConstant.AR_RATE_REQUEST_LICENSE_KEY;

            String trackingNumbers = ParcelRatingUtil.prepareTrackingNumbersInOperator(beans);
            List<ParcelAuditDetailsDto> shipmentRecords = null;
            if (trackingNumbers != null && !trackingNumbers.isEmpty()) {
                try {
                    shipmentRecords = getUpsParcelShipmentDetails(null, trackingNumbers, true, false);
                    if (shipmentRecords != null && !shipmentRecords.isEmpty()) {

                        status = callRTRAndPopulateRates(url, licenseKey, shipmentRecords, null, null, beans, accessorialBeans);


                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return status;
    }

    public void updateUpsOtherFieldValues(List<ParcelAuditDetailsDto> rateDetailsList) {
        try{
            new DirectJDBCDAO().updateUpsOtherFieldValues(rateDetailsList);
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
    public List<ServiceFlagAccessorialBean> getServiceFlagAcessorials(Long carrierId, String moduleName) {

        return new DirectJDBCDAO().getServiceFlagAcessorials(carrierId, moduleName);
    }


    private void prepareAdditionalAccessorialForUps(ParcelRateResponse.PriceSheet priceSheet, Long parentId, List<ParcelRateResponse.Charge> mappedAccChanges, List<AccessorialDto> dtos, boolean frtChargeFound, boolean fscChargeFound, List<AccessorialDto> prevParentsRatesDtos) {

        List<ParcelRateResponse.Charge> accessorialCharges = ParcelRateResponseParser.getAccessorialChargesForUps(priceSheet);

        if (accessorialCharges != null && !accessorialCharges.isEmpty()) {
            if (mappedAccChanges != null && !mappedAccChanges.isEmpty()) {
                Iterator<ParcelRateResponse.Charge> chargeIterator = accessorialCharges.iterator();
                List<ParcelRateResponse.Charge> tempRemoveList = new ArrayList<>();
                while (chargeIterator.hasNext()) {
                    ParcelRateResponse.Charge tempCharge = chargeIterator.next();
                    for (ParcelRateResponse.Charge mappedChrg : mappedAccChanges) {
                        if (mappedChrg != null && tempCharge != null
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
        }


        if (accessorialCharges != null && accessorialCharges.size() > 0) {

            for (ParcelRateResponse.Charge charge : accessorialCharges) {

                AccessorialDto dto = new AccessorialDto();

                dto.setCode(charge.getEdiCode());

                BigDecimal prevRtrAmt = ParcelRatingUtil.findPrevRateAmtByCode(prevParentsRatesDtos, charge.getEdiCode(), "accessorial");

                dto.setRtrAmount(charge.getAmount().subtract(prevRtrAmt));
                dto.setType("accessorial");
                dto.setParentId(parentId);
                dto.setName(charge.getName());
                dtos.add(dto);
            }

        }

        if (!fscChargeFound) {
            ParcelRateResponse.Charge charge = ParcelRateResponseParser.findChargeByType(ParcelRateResponse.ChargeType.ACCESSORIAL_FUEL.name(), priceSheet);

            if (charge != null) {

                AccessorialDto dto = new AccessorialDto();

                dto.setCode(charge.getEdiCode());
                BigDecimal prevRtrAmt = ParcelRatingUtil.findPrevRateAmtByCode(prevParentsRatesDtos, charge.getEdiCode(), "accessorial");
                dto.setRtrAmount(charge.getAmount().subtract(prevRtrAmt));
                dto.setType("accessorial");
                dto.setParentId(parentId);
                dto.setName(charge.getName());
                dtos.add(dto);
            }
        }

        if (!frtChargeFound) {
            ParcelRateResponse.Charge charge = ParcelRateResponseParser.findChargeByType(ParcelRateResponse.ChargeType.ITEM.name(), priceSheet);

            if (charge != null) {

                AccessorialDto dto = new AccessorialDto();

                dto.setCode("FRT");
                BigDecimal prevRtrAmt = ParcelRatingUtil.findPrevRateAmtByCode(prevParentsRatesDtos, "FRT", "accessorial");
                dto.setRtrAmount(charge.getAmount().subtract(prevRtrAmt));
                dto.setType("accessorial");
                dto.setParentId(parentId);
                dto.setName(charge.getName());
                dtos.add(dto);
            }
        }


    }


    private void prepareAddDiscounts(ParcelRateResponse.PriceSheet priceSheet, Long parentId, List<ParcelRateResponse.Charge> mappedDscChanges, List<AccessorialDto> addAccAndDisdtos, List<AccessorialDto> prevParentsRatesDtos) {

        List<ParcelRateResponse.Charge> discountCharges = ParcelRateResponseParser.getAllOtherDiscountsForUPSCarrier(priceSheet);

        if (discountCharges != null && !discountCharges.isEmpty()) {
            if (mappedDscChanges != null && !mappedDscChanges.isEmpty()) {
                Iterator<ParcelRateResponse.Charge> chargeIterator = mappedDscChanges.iterator();
                List<ParcelRateResponse.Charge> tempRemoveList = new ArrayList<>();
                while (chargeIterator.hasNext()) {
                    ParcelRateResponse.Charge tempCharge = chargeIterator.next();
                    for (ParcelRateResponse.Charge mappedChrg : mappedDscChanges) {
                        if (mappedChrg != null && tempCharge != null
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
        }

        if (discountCharges != null && discountCharges.size() > 0) {

            for (ParcelRateResponse.Charge charge : discountCharges) {

                AccessorialDto dto = new AccessorialDto();

                dto.setCode(charge.getEdiCode());
                BigDecimal prevRtrAmt = ParcelRatingUtil.findPrevRateAmtByDisName(prevParentsRatesDtos, charge.getName(), "discount");
                dto.setRtrAmount(charge.getAmount().subtract(prevRtrAmt));
                dto.setType("discount");
                dto.setParentId(parentId);
                dto.setName(charge.getName());

                addAccAndDisdtos.add(dto);
            }

        }

    }


}
