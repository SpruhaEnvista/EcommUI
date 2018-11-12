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
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;

/**
 * Created by Sujit kumar on 05/05/2018.
 */
public class ParcelUpsRatingService {
    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(ParcelUpsRatingService.class);

    public List<ParcelAuditDetailsDto> getUpsParcelShipmentDetails(String customerId, String fromShipDate, String toShipDate, String trackingNumbers, String invoiceIds, boolean ignoreRtrStatus, String hwtNumbers) {
        return new RatingQueueDAO().getUpsParcelShipmentDetails(customerId, fromShipDate, toShipDate, trackingNumbers, invoiceIds, ignoreRtrStatus, hwtNumbers);
    }

    public List<ParcelAuditDetailsDto> getUpsParcelShipmentDetails(String customerId, String fromShipDate, String toShipDate, String trackingNumbers, String invoiceIds, String hwtNumbers) {
        return getUpsParcelShipmentDetails(customerId, fromShipDate, toShipDate, trackingNumbers, invoiceIds, false, hwtNumbers);
    }

    public List<ParcelAuditDetailsDto> getUpsParcelShipmentDetails(String customerId, String fromShipDate, String toShipDate, boolean ignoreRtrStatus, String hwtNumbers) {
        return getUpsParcelShipmentDetails(customerId, fromShipDate, toShipDate, null, null, ignoreRtrStatus, hwtNumbers);
    }

    public List<ParcelAuditDetailsDto> getUpsParcelShipmentDetails(String customerId, String fromShipDate, String toShipDate, String hwtNumbers) {
        return getUpsParcelShipmentDetails(customerId, fromShipDate, toShipDate, null, null, false, hwtNumbers);
    }

    public List<ParcelAuditDetailsDto> getUpsParcelShipmentDetails(String customerId, String trackingNumber, boolean ignoreRtrStatus, String hwtNumbers) {
        return getUpsParcelShipmentDetails(customerId, null, null, trackingNumber, null, ignoreRtrStatus, hwtNumbers);
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
            List<ParcelAuditDetailsDto> shipmentToRate = null;
            boolean prevHwtRated = false;

            if(trackingNumber != null && !trackingNumber.isEmpty()){
                try{
                    if (bean.getHwtIdentifier() == null || bean.getHwtIdentifier().isEmpty())
                        shipmentRecords = getUpsParcelShipmentDetails(null, trackingNumber, true, null);
                    else {
                        shipmentRecords = new ParcelUpsRatingService().getUpsParcelShipmentDetails(bean.getCustomerId() != null ? bean.getCustomerId().toString() : null, null, true, bean.getHwtIdentifier());
                        shipmentToRate = new ArrayList<>();
                        prevHwtRated = ParcelRatingUtil.isHwtShipmentRated(shipmentRecords, bean.getParentId(), shipmentToRate, bean.getInvoiceDate());
                    }


                    if(shipmentRecords != null && !shipmentRecords.isEmpty()) {
                        Map<Long, List<ParcelAuditDetailsDto>> shipments = null;
                        shipments = ParcelRatingUtil.organiseShipmentsByParentId(shipmentRecords);
                        if (bean.getHwtIdentifier() == null || bean.getHwtIdentifier().isEmpty())
                            shipmentToRate = shipments.get(bean.getParentId());

                        if (shipmentToRate.get(0).getPickupDate() == null)
                            ParcelRatingUtil.setPrevParentIdShipDate(shipmentToRate, shipmentRecords);

                        if(shipmentToRate != null && !shipmentToRate.isEmpty()) {
                            if (bean.getHwtIdentifier() == null || bean.getHwtIdentifier().isEmpty()) {
                                if (ParcelRatingUtil.isFirstShipmentToRate(shipments, bean.getParentId())) {
                                    if (ParcelRatingUtil.isRatedWithException(shipmentToRate)) {
                                        status = ParcelAuditConstant.RTRStatus.RATING_EXCEPTION.value;
                                    } else if (ParcelRatingUtil.isRatedWithEmptyPriceSheet(shipmentToRate)) {
                                        status = ParcelAuditConstant.RTRStatus.NO_PRICE_SHEET.value;
                                    } else if (!ParcelRatingUtil.isShipmentRated(shipmentToRate)) {
                                        status = callRTRAndPopulateRates(url, licenseKey, shipmentToRate, null, bean, accessorialBeans);
                                    }
                                } else {
                                    if (!ParcelRatingUtil.isShipmentRated(shipmentToRate)) {
                                        List<ParcelAuditDetailsDto> previousShipment = ParcelRatingUtil.getPreviousShipmentDetails(shipments, bean.getParentId());
                                        if (previousShipment != null && !previousShipment.isEmpty()) {
                                            if (ParcelRatingUtil.isShipmentRated(previousShipment)) {


                                                if (shipmentToRate != null) {

                                                    status = callRTRAndPopulateRates(url, licenseKey, shipmentToRate, previousShipment, bean, accessorialBeans);
                                                }


                                            } else if (ParcelRatingUtil.isRatedWithException(previousShipment)) {
                                                status = ParcelAuditConstant.RTRStatus.RATING_EXCEPTION.value;
                                            } else if (ParcelRatingUtil.isRatedWithEmptyPriceSheet(previousShipment)) {
                                                status = ParcelAuditConstant.RTRStatus.NO_PRICE_SHEET.value;
                                            }
                                        }
                                    }
                                }
                            } else {
                                if (ParcelRatingUtil.isRatedWithException(shipmentToRate)) {
                                    status = ParcelAuditConstant.RTRStatus.RATING_EXCEPTION.value;
                                } else if (ParcelRatingUtil.isRatedWithEmptyPriceSheet(shipmentToRate)) {
                                    status = ParcelAuditConstant.RTRStatus.NO_PRICE_SHEET.value;
                                } else if (!prevHwtRated) {
                                    status = callRTRAndPopulateRates(url, licenseKey, shipmentToRate, null, bean, accessorialBeans);
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

                StringJoiner entityIds = new StringJoiner(",");
                for(ParcelAuditDetailsDto ship : shipmentRecords){
                    if(ship != null && ship.getId() != null){
                        entityIds.add(ship.getId().toString());
                    }
                }
                if (entityIds != null && entityIds.length() > 0) {
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

        List<AccessorialDto> prevParentsRatesDtos = directJDBCDAO.getRatesForPrevParentIds(parcelAuditDetails.get(0).getTrackingNumber(), parcelAuditDetails.get(0).getParentId(), queueBean.getReturnFlag(), 21, parcelAuditDetails.get(0).getPickupDate());

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
                                if (!mappedAccList.contains("FRT"))
                                    rateDetails.setRtrAmount(charge.getAmount().subtract(prevRtrAmt));
                                else
                                    rateDetails.setRtrAmount(new BigDecimal("0.00"));

                                rateDetails.setRtrStatus(rtrStatus.value);


                                rateDetails.setFlagged(flagged);
                                rateDetails.setAccCode("FRT");

                                mappedAccList.add("FRT");

                                ParcelRatingUtil.setCommonValues(rateDetails, queueBean, priceSheet);

                                if (auditDetails.getId().compareTo(auditDetails.getParentId()) == 0)
                                    ParcelRateResponseParser.mapPercentageAndDis(rateDetails, priceSheet, mappedDscChanges, prevParentsRatesDtos);

                                directJDBCDAO.updateShipmentRateDetails(ParcelAuditConstant.EBILL_GFF_TABLE_NAME, auditDetails.getId().toString(), ParcelAuditConstant.PARCEL_RTR_RATING_USER_NAME, rateDetails);
                            }
                            frtChargeFound = true;

                    } else {
                        ParcelRateDetailsDto rateDetails = ParcelRateDetailsDto.getInstance();
                        rateDetails.setRtrAmount(new BigDecimal("0"));
                        rateDetails.setRtrStatus(rtrStatus.value);

                        rateDetails.setFlagged(flagged);
                        ParcelRatingUtil.setCommonValues(rateDetails, queueBean, priceSheet);
                        if (auditDetails.getId().compareTo(auditDetails.getParentId()) == 0)
                            ParcelRateResponseParser.mapPercentageAndDis(rateDetails, priceSheet, mappedDscChanges, prevParentsRatesDtos);

                        directJDBCDAO.updateShipmentRateDetails(ParcelAuditConstant.EBILL_GFF_TABLE_NAME, auditDetails.getId().toString(), ParcelAuditConstant.PARCEL_RTR_RATING_USER_NAME, rateDetails);
                    }
                }else if(ParcelAuditConstant.ChargeClassificationCode.FSC.name().equalsIgnoreCase(auditDetails.getChargeClassificationCode())){
                    fscChargeFound = true;
                    charge = ParcelRateResponseParser.findChargeByType(ParcelRateResponse.ChargeType.ACCESSORIAL_FUEL.name(), priceSheet);
                    if(charge != null){
                        ParcelRateDetailsDto rateDetails = ParcelRateDetailsDto.getInstance();

                        rateDetails.setDimDivisor(charge.getDimDivisor() == null ? new BigDecimal("0") : charge.getDimDivisor());
                        rateDetails.setRatedWeight(charge.getWeight() == null ? new BigDecimal("0") : charge.getWeight());
                        BigDecimal prevRtrAmt = ParcelRatingUtil.findPrevRateAmtByCode(prevParentsRatesDtos, "FSC", "accessorial");
                        if(!mappedAccList.contains("FSC"))
                            rateDetails.setRtrAmount(charge.getAmount().subtract(prevRtrAmt));
                        else
                            rateDetails.setRtrAmount(new BigDecimal("0.00"));
                        rateDetails.setRtrStatus(rtrStatus.value);

                        rateDetails.setFlagged(flagged);
                        rateDetails.setAccCode("FSC");

                        mappedAccList.add("FSC");

                        ParcelRatingUtil.setCommonValues(rateDetails, queueBean, priceSheet);

                        if (auditDetails.getId().compareTo(auditDetails.getParentId()) == 0)
                            ParcelRateResponseParser.mapPercentageAndDis(rateDetails, priceSheet, mappedDscChanges, prevParentsRatesDtos);

                        directJDBCDAO.updateShipmentRateDetails(ParcelAuditConstant.EBILL_GFF_TABLE_NAME, auditDetails.getId().toString(), ParcelAuditConstant.PARCEL_RTR_RATING_USER_NAME, rateDetails);
                    }
                } else{
                    ServiceFlagAccessorialBean bean = ParcelRatingUtil.getAccessorialBean(accessorialBeans, auditDetails.getChargeDescription(), auditDetails.getChargeDescriptionCode(), 21L);
                    if (bean != null) {
/*                        if("RSC".equalsIgnoreCase(bean.getLookUpValue()))
                            bean.setLookUpValue("RSS");*/

                        if (queueBean.getHwtIdentifier() == null || queueBean.getHwtIdentifier().isEmpty())
                            charge = ParcelRateResponseParser.findChargeByEDICodeInResponse(bean.getLookUpValue(), priceSheet);
                        else
                            charge = ParcelRateResponseParser.findChargeByEDICodeInResForHwt(bean.getLookUpValue(), priceSheet, mappedAccChanges);
                    }
                    if(charge != null){
                        if (queueBean.getHwtIdentifier() == null || queueBean.getHwtIdentifier().isEmpty())
                            mappedAccChanges.add(charge);

                        if(charge != null && charge.getType() != null){
                            if(ParcelRateResponse.ChargeType.ACCESSORIAL.name().equalsIgnoreCase(charge.getType())) mappedAccChanges.add(charge);
                            else if(ParcelRateResponse.ChargeType.DISCOUNT.name().equalsIgnoreCase(charge.getType())) mappedDscChanges.add(charge);
                        }
                        ParcelRateDetailsDto rateDetails = ParcelRateDetailsDto.getInstance();

                        rateDetails.setDimDivisor(charge.getDimDivisor() == null ? new BigDecimal("0") : charge.getDimDivisor());
                        rateDetails.setRatedWeight(charge.getWeight() == null ? new BigDecimal("0") : charge.getWeight());
                        BigDecimal prevRtrAmt = ParcelRatingUtil.findPrevRateAmtByCode(prevParentsRatesDtos, bean.getLookUpValue(), "accessorial");
                        if(bean != null && !mappedAccList.contains(bean.getLookUpValue()))
                            rateDetails.setRtrAmount(charge.getAmount().subtract(prevRtrAmt));
                        else
                            rateDetails.setRtrAmount(new BigDecimal("0.00"));

                        rateDetails.setRtrStatus(rtrStatus.value);


                        rateDetails.setFlagged(flagged);
                        if (bean != null) {
                            rateDetails.setAccCode(bean.getLookUpValue());
                            mappedAccList.add(bean.getLookUpValue());
                        }
                        ParcelRatingUtil.setCommonValues(rateDetails, queueBean, priceSheet);
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

                    ParcelRatingUtil.setCommonValues(rateDetails, queueBean, priceSheet);

                    if (auditDetails.getId().compareTo(auditDetails.getParentId()) == 0)
                        ParcelRateResponseParser.mapPercentageAndDis(rateDetails, priceSheet, mappedDscChanges, prevParentsRatesDtos);

                    directJDBCDAO.updateShipmentRateDetails(ParcelAuditConstant.EBILL_GFF_TABLE_NAME, auditDetails.getId().toString(), ParcelAuditConstant.PARCEL_RTR_RATING_USER_NAME, rateDetails);
                }
            }
        }


        List<AccessorialDto> addAccAndDisdtos = new ArrayList<>();

        ParcelRatingUtil.prepareAdditionalAccessorial(priceSheet, parcelAuditDetails.get(0).getParentId(), mappedAccChanges, addAccAndDisdtos, frtChargeFound, fscChargeFound, prevParentsRatesDtos);
        ParcelRatingUtil.prepareAddDiscounts(priceSheet, parcelAuditDetails.get(0).getParentId(), mappedDscChanges, addAccAndDisdtos, prevParentsRatesDtos);

        directJDBCDAO.saveAccInfo(addAccAndDisdtos, parcelAuditDetails.get(0).getParentId(), 21);

        return rtrStatus.value;
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
                    shipmentRecords = getUpsParcelShipmentDetails(null, trackingNumbers, true, null);
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




}
