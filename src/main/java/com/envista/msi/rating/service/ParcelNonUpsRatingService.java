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
import com.envista.msi.rating.bean.AccessorialDto;
import com.envista.msi.rating.bean.RatingQueueBean;
import com.envista.msi.rating.bean.ServiceFlagAccessorialBean;
import com.envista.msi.rating.dao.DirectJDBCDAO;
import com.envista.msi.rating.dao.RatingQueueDAO;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
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

    public List<ParcelAuditDetailsDto> getFedExParcelShipmentDetails(String customerId, String fromShipDate, String toShipDate, String trackingNumbers, String invoiceIds, boolean ignoreRtrStatus, String hwtNumbers) {
        return new RatingQueueDAO().getNonUpsParcelShipmentDetails(customerId, "22", fromShipDate, toShipDate, trackingNumbers, invoiceIds, ignoreRtrStatus, hwtNumbers);
    }


    public List<ParcelAuditDetailsDto> getFedExParcelShipmentDetails(String trackingNumbers, boolean ignoreRtrStatus) {
        return getFedExParcelShipmentDetails(null, null, null, trackingNumbers, null, ignoreRtrStatus, null);
    }

    public String doRatingForNonUpsShipment(RatingQueueBean bean, List<ServiceFlagAccessorialBean> accessorialBeans) throws Exception {

        List<ParcelAuditDetailsDto> allShipmentCharges;

        String status = null;
        boolean prevHwtRated = false;
        List<ParcelAuditDetailsDto> shipmentToRate = null;
        if (bean.getHwtIdentifier() == null || bean.getHwtIdentifier().isEmpty())
            allShipmentCharges = getFedExParcelShipmentDetails(bean.getCustomerId().toString(), null, null, bean.getTrackingNumber(), null, true, null);
        else {
            allShipmentCharges = getFedExParcelShipmentDetails(bean.getCustomerId().toString(), null, null, null, null, true, bean.getHwtIdentifier());
            shipmentToRate = new ArrayList<>();
            prevHwtRated = ParcelRatingUtil.isHwtShipmentRated(allShipmentCharges, bean.getParentId(), shipmentToRate, bean.getInvoiceDate());
        }
        if(allShipmentCharges != null && !allShipmentCharges.isEmpty()) {


            Map<Long, List<ParcelAuditDetailsDto>> shipments = null;
            if (bean.getHwtIdentifier() == null || bean.getHwtIdentifier().isEmpty()) {
                shipments = ParcelRatingUtil.organiseShipmentsByParentId(allShipmentCharges);
                shipmentToRate = shipments.get(bean.getParentId());
            }

            if (shipmentToRate != null && !shipmentToRate.isEmpty() && shipmentToRate.size() > 0) {

                if (shipmentToRate.get(0).getPickupDate() == null)
                    ParcelRatingUtil.setPrevParentIdShipDate(shipmentToRate, allShipmentCharges);


                if (bean.getHwtIdentifier() == null || bean.getHwtIdentifier().isEmpty()) {
                    if (ParcelRatingUtil.isFirstShipmentToRate(shipments, bean.getParentId())) {
                        if (!ParcelRatingUtil.isShipmentRated(shipmentToRate)) {
                            status = callRTRAndPopulateRates(shipmentToRate, bean, null, accessorialBeans, null);
                            updateFedExOtherFieldValues(shipmentToRate);
                        }
                    } else {
                        if (!ParcelRatingUtil.isShipmentRated(shipmentToRate)) {
                            List<ParcelAuditDetailsDto> previousShipment = ParcelRatingUtil.getPreviousShipmentDetails(shipments, bean.getParentId());
                            if (previousShipment != null && !previousShipment.isEmpty()) {
                                if (ParcelRatingUtil.isShipmentRated(previousShipment)) {
                                    if (!ParcelRatingUtil.hasFrtCharge(shipmentToRate)) {
                                        ParcelAuditDetailsDto prevShipmentFrtCharge = ParcelRatingUtil.getPreviousShipmentBaseChargeDetails(shipments, bean.getParentId());
                                        if (prevShipmentFrtCharge != null) {
                                            shipmentToRate.add(prevShipmentFrtCharge);
                                            status = callRTRAndPopulateRates(shipmentToRate, bean, null, accessorialBeans, previousShipment);
                                            updateFedExOtherFieldValues(shipmentToRate);
                                        }
                                    } else {
                                        status = callRTRAndPopulateRates(shipmentToRate, bean, null, accessorialBeans, previousShipment);
                                        updateFedExOtherFieldValues(shipmentToRate);
                                    }
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
                        status = callRTRAndPopulateRates(shipmentToRate, bean, null, accessorialBeans, null);
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

            status = updateRateForNonUpsCarrier(ParcelRateResponseParser.parse(response), shipmentToRate, bean, accessorialBeans);
            updateFedExOtherFieldValues(shipmentToRate);
  /*          if(status != null && !status.isEmpty()){
                new DirectJDBCDAO().updateRtrStatus(22L, bean.getTrackingNumber(), status, new java.sql.Date(bean.getShipDate().getTime()));
            }*/
        }
        return status;
    }

    public String updateRateForNonUpsCarrier(ParcelRateResponse parcelRateResponse, List<ParcelAuditDetailsDto> parcelAuditDetails, RatingQueueBean bean, List<ServiceFlagAccessorialBean> accessorialBeans) throws Exception {
        String status = "";
        if(parcelRateResponse != null){
            if(parcelRateResponse.getStatusCode().equals(0)){
                if(parcelRateResponse.getPriceSheets() != null && !parcelRateResponse.getPriceSheets().isEmpty()){
                    //Taking the first price sheet, as per the discussion we finalised that the first price sheet will be be correct and rated based on the latest contract.
                    ParcelRateResponse.PriceSheet firstPriceSheet = parcelRateResponse.getPriceSheets().get(0);
                    BigDecimal sumOfNetAmount = ParcelRatingUtil.findSumOfNetAmount(parcelAuditDetails);
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
                        status = updateAmountWithRTRResponseChargesForNonUpsCarrier(firstPriceSheet, parcelAuditDetails, ParcelAuditConstant.RTRStatus.CLOSED, flagged, accessorialBeans, bean);
                    } else{
                        if(sumOfNetAmount.compareTo(totalRateAmount) < 0){
                            status = updateAmountWithRTRResponseChargesForNonUpsCarrier(firstPriceSheet, parcelAuditDetails, ParcelAuditConstant.RTRStatus.UNDER_CHARGED, flagged, accessorialBeans, bean);
                        } else if(sumOfNetAmount.compareTo(totalRateAmount) > 0){
                            status = updateAmountWithRTRResponseChargesForNonUpsCarrier(firstPriceSheet, parcelAuditDetails, ParcelAuditConstant.RTRStatus.OVER_CHARGED, flagged, accessorialBeans, bean);
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
    private String updateAmountWithRTRResponseChargesForNonUpsCarrier(ParcelRateResponse.PriceSheet priceSheet, List<ParcelAuditDetailsDto> parcelAuditDetails, ParcelAuditConstant.RTRStatus rtrStatus, String flagged, List<ServiceFlagAccessorialBean> accessorialBeans, RatingQueueBean queueBean) throws Exception {
        DirectJDBCDAO directJDBCDAO = new DirectJDBCDAO();
        boolean frtChargeFound = false;
        boolean fscChargeFound = false;
        List<ParcelRateResponse.Charge> mappedAccChanges = new ArrayList<>();
        List<ParcelRateResponse.Charge> mappedDscChanges = new ArrayList<>();
        List<String> mappedAccList = new ArrayList<>();

        List<AccessorialDto> prevParentsRatesDtos = directJDBCDAO.getRatesForPrevParentIds(parcelAuditDetails.get(0).getTrackingNumber(), parcelAuditDetails.get(0).getParentId(), queueBean.getReturnFlag(), 22, parcelAuditDetails.get(0).getPickupDate());



        for(ParcelAuditDetailsDto auditDetails : parcelAuditDetails){
            if(auditDetails != null && auditDetails.getChargeClassificationCode() != null && !auditDetails.getChargeClassificationCode().isEmpty()){
                ParcelRateResponse.Charge charge = null;
                if(!frtChargeFound && ParcelAuditConstant.ChargeClassificationCode.FRT.name().equalsIgnoreCase(auditDetails.getChargeClassificationCode())
                        && auditDetails.getNetAmount() != null && !auditDetails.getNetAmount().trim().isEmpty()){

                    charge = ParcelRateResponseParser.findChargeByType(ParcelRateResponse.ChargeType.ITEM.name(), priceSheet);
                        if(charge != null){
                            frtChargeFound = true;

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

                            directJDBCDAO.updateShipmentRateDetails(ParcelAuditConstant.EBILL_MANIFEST_TABLE_NAME, auditDetails.getId().toString(), ParcelAuditConstant.PARCEL_RTR_RATING_USER_NAME, rateDetails);
                        }

                } else if (ParcelAuditConstant.ChargeClassificationCode.ACS.name().equalsIgnoreCase(auditDetails.getChargeClassificationCode())
                        && ParcelAuditConstant.ChargeDescriptionCode.FSC.name().equalsIgnoreCase(auditDetails.getChargeDescriptionCode())) {
                    charge = ParcelRateResponseParser.findChargeByType(ParcelRateResponse.ChargeType.ACCESSORIAL_FUEL.name(), priceSheet);
                    fscChargeFound = true;
                    if(charge != null){
                        ParcelRateDetailsDto rateDetails = ParcelRateDetailsDto.getInstance();

                        rateDetails.setDimDivisor(charge.getDimDivisor() == null ? new BigDecimal("0") : charge.getDimDivisor());
                        rateDetails.setRatedWeight(charge.getWeight() == null ? new BigDecimal("0") : charge.getWeight());
                        BigDecimal prevRtrAmt = ParcelRatingUtil.findPrevRateAmtByCode(prevParentsRatesDtos, "FSC", "accessorial");
                        if (!mappedAccList.contains("FSC"))
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

                        directJDBCDAO.updateShipmentRateDetails(ParcelAuditConstant.EBILL_MANIFEST_TABLE_NAME, auditDetails.getId().toString(), ParcelAuditConstant.PARCEL_RTR_RATING_USER_NAME, rateDetails);
                    }
                } else {
                    //need to clarify this step, whether it is required for NonUPS Carrier or not.
                    ServiceFlagAccessorialBean bean = ParcelRatingUtil.getAccessorialBean(accessorialBeans, auditDetails.getChargeDescription(), auditDetails.getActualchargeDescriptionCode(), 22L);
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
                        if (bean != null && !mappedAccList.contains(bean.getLookUpValue()))
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

                        directJDBCDAO.updateShipmentRateDetails(ParcelAuditConstant.EBILL_MANIFEST_TABLE_NAME, auditDetails.getId().toString(), ParcelAuditConstant.PARCEL_RTR_RATING_USER_NAME, rateDetails);
                    }
                }

                if(charge == null){
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


                    directJDBCDAO.updateShipmentRateDetails(ParcelAuditConstant.EBILL_MANIFEST_TABLE_NAME, auditDetails.getId().toString(), ParcelAuditConstant.PARCEL_RTR_RATING_USER_NAME, rateDetails);
                }
            }
        }


        List<AccessorialDto> addAccAndDisdtos = new ArrayList<>();

        ParcelRatingUtil.prepareAdditionalAccessorial(priceSheet, parcelAuditDetails.get(0).getParentId(), mappedAccChanges, addAccAndDisdtos, frtChargeFound, fscChargeFound, prevParentsRatesDtos);
        ParcelRatingUtil.prepareAddDiscounts(priceSheet, parcelAuditDetails.get(0).getParentId(), mappedDscChanges, addAccAndDisdtos, prevParentsRatesDtos);

        directJDBCDAO.saveAccInfo(addAccAndDisdtos, parcelAuditDetails.get(0).getParentId(), 22);

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

    public void saveRatingQueueBean(List<RatingQueueBean> queueBeanList) throws SQLException {
        if (queueBeanList != null) {
            new RatingQueueDAO().saveRatingQueueBean(queueBeanList);
        }
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


    public boolean hwtShipmentExist(String trackingNumber, Date billDate) {
        return new RatingQueueDAO().hwtShipmentExist(trackingNumber, billDate);
    }
}
