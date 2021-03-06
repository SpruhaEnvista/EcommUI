package com.envista.msi.rating.service;

import com.envista.msi.api.domain.util.ParcelRatingUtil;
import com.envista.msi.api.web.rest.dto.rtr.*;
import com.envista.msi.api.web.rest.util.CommonUtil;
import com.envista.msi.api.web.rest.util.audit.parcel.ParcelAuditConstant;
import com.envista.msi.api.web.rest.util.audit.parcel.ParcelRateResponse;
import com.envista.msi.api.web.rest.util.audit.parcel.ParcelRateResponseParser;
import com.envista.msi.rating.bean.AccessorialDto;
import com.envista.msi.rating.bean.RatingQueueBean;
import com.envista.msi.rating.bean.ServiceFlagAccessorialBean;
import com.envista.msi.rating.dao.DirectJDBCDAO;
import com.envista.msi.rating.dao.RatingQueueDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

    private static final Logger log = LoggerFactory.getLogger(ParcelUpsRatingService.class);

    private static ParcelUpsRatingService instance = null;

    private ParcelUpsRatingService() {
        // Exists only to defeat instantiation.
    }

    public static ParcelUpsRatingService getInstance() {
        if (instance == null) {
            instance = new ParcelUpsRatingService();
        }
        return instance;
    }

    public List<ParcelAuditDetailsDto> getUpsParcelShipmentDetails(String customerId, String fromShipDate, String toShipDate, String trackingNumbers, String invoiceIds, boolean ignoreRtrStatus, String hwtNumbers) {
        return RatingQueueDAO.getInstance().getUpsParcelShipmentDetails(customerId, fromShipDate, toShipDate, trackingNumbers, invoiceIds, ignoreRtrStatus, hwtNumbers);
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
        List<ParcelARChargeCodeMappingDto> mappedChargeCodes = DirectJDBCDAO.getInstance().loadMappedARChargeCodes(ParcelAuditConstant.MSI_AR_CHARGE_CODE_MAPPING_MODELE_NAME, ParcelAuditConstant.MSI_AR_DAS_CHARGE_CODE_NAME);
        if(mappedChargeCodes != null && !mappedChargeCodes.isEmpty()){
            chargeCodeMap = prepareChargeCodeMap(mappedChargeCodes);
        }
        return chargeCodeMap;
    }

    public Map<String, String> getMappedLPSChargeCodes(){
        Map<String, String> chargeCodeMap = null;
        List<ParcelARChargeCodeMappingDto> mappedChargeCodes = DirectJDBCDAO.getInstance().loadMappedARChargeCodes(ParcelAuditConstant.MSI_AR_CHARGE_CODE_MAPPING_MODELE_NAME, ParcelAuditConstant.MSI_AR_LPS_CHARGE_CODE_NAME);
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
                    if (bean.getHwtIdentifier() == null || bean.getHwtIdentifier().isEmpty()) {
                        shipmentRecords = getUpsParcelShipmentDetails(bean.getCustomerId() != null ? bean.getCustomerId().toString() : null, trackingNumber, true, null);
                        bean.setPiecesCount(1);
                    }
                    else {
                        shipmentRecords = getUpsParcelShipmentDetails(bean.getCustomerId() != null ? bean.getCustomerId().toString() : null, null, true, bean.getHwtIdentifier());
                        shipmentToRate = new ArrayList<>();
                        bean.setPiecesCount(ParcelRatingUtil.getPiecesCount(shipmentRecords));
                        prevHwtRated = ParcelRatingUtil.isHwtShipmentRated(shipmentRecords, bean.getParentId(), shipmentToRate, bean.getInvoiceDate());
                    }


                    if(shipmentRecords != null && !shipmentRecords.isEmpty()) {
                        Map<Long, List<ParcelAuditDetailsDto>> shipments = null;
                        shipments = ParcelRatingUtil.organiseShipmentsByParentId(shipmentRecords);
                        if (bean.getHwtIdentifier() == null || bean.getHwtIdentifier().isEmpty())
                            shipmentToRate = shipments.get(bean.getParentId());

                        if (shipmentToRate != null && !shipmentToRate.isEmpty() && shipmentToRate.size() > 0) {

                            ParcelRatingUtil.setAddressCorrectionAsAccessorial(shipmentToRate);

                            if (shipmentToRate.get(0).getPickupDate() == null)
                                ParcelRatingUtil.setPrevParentIdShipDate(shipmentToRate, shipmentRecords);

                            if (bean.getHwtIdentifier() == null || bean.getHwtIdentifier().isEmpty()) {
                                if (ParcelRatingUtil.isFirstShipmentToRate(shipments, bean.getParentId())) {

                                    if (!ParcelRatingUtil.isShipmentRated(shipmentToRate)) {
                                        status = callRTRAndPopulateRates(url, licenseKey, shipmentToRate, bean, accessorialBeans);
                                    }
                                } else {
                                    if (!ParcelRatingUtil.isShipmentRated(shipmentToRate)) {
                                        List<ParcelAuditDetailsDto> previousShipment = ParcelRatingUtil.getPreviousShipmentDetails(shipments, bean.getParentId());
                                        if (previousShipment != null && !previousShipment.isEmpty()) {
                                            if (ParcelRatingUtil.isShipmentRated(previousShipment)) {


                                                if (shipmentToRate != null) {

                                                    status = callRTRAndPopulateRates(url, licenseKey, shipmentToRate, bean, accessorialBeans);
                                                }


                                            }
                                        }
                                    }
                                }
                            } else {

                                if (!prevHwtRated) {
                                    status = callRTRAndPopulateRates(url, licenseKey, shipmentToRate, bean, accessorialBeans);
                                }
                            }
                        }

                        //To check whether it is a void shipment or not, if so then update the IS_VOID_SHIPMENT = 1.
                        checkForVoidShipmentAndUpdate(shipmentRecords);
                    }
                }catch (Exception e){
                    log.error("ERROR - " + e.getStackTrace() + "--Parent Id->" + bean.getParentId());
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
                    DirectJDBCDAO.getInstance().updateRatingVoidShipmentStatus(ParcelAuditConstant.EBILL_GFF_TABLE_NAME, entityIds.toString(), 1);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            log.error("ERROR in checkForVoidShipmentAndUpdate", e.getStackTrace() + "--Parent Id->" + shipmentRecords.get(0).getParentId());
        }
    }

    private String callRTRAndPopulateRates(String url, String licenseKey, List<ParcelAuditDetailsDto> parcelAuditDetails, RatingQueueBean bean, List<ServiceFlagAccessorialBean> accessorialBeans) throws Exception {
        String requestPayload = "";
        String response = "";
        String status = "";
        DirectJDBCDAO directJDBCDAO = DirectJDBCDAO.getInstance();

        if (bean.getExcludeRating() != 1) {
            requestPayload = com.envista.msi.rating.util.ParcelRateRequestBuilder.buildParcelRateRequest(bean, ParcelAuditConstant.AR_RATE_REQUEST_LICENSE_KEY).toXmlString();
            response = CommonUtil.connectAndGetResponseAsString(url, requestPayload);
            if (response != null && !response.trim().isEmpty()) {


                directJDBCDAO.saveParcelAuditRequestAndResponseLog(ParcelRatingUtil.prepareRequestResponseLog(requestPayload, response, parcelAuditDetails.get(0).getParentId(), bean.getCarrierId()));
            }
        }
        status = updateRateForUps(ParcelRateResponseParser.parse(response), parcelAuditDetails, bean, accessorialBeans);


        return status;
    }

    private String updateRateForUps(ParcelRateResponse parcelRateResponse, List<ParcelAuditDetailsDto> parcelAuditDetails, RatingQueueBean bean, List<ServiceFlagAccessorialBean> accessorialBeans) throws Exception {
        return updateRateForUps(parcelRateResponse, parcelAuditDetails, null, bean, accessorialBeans);
    }

    private String updateRateForUps(ParcelRateResponse parcelRateResponse, List<ParcelAuditDetailsDto> parcelAuditDetails, ParcelAuditDetailsDto commercialAdjCharge, RatingQueueBean bean, List<ServiceFlagAccessorialBean> accessorialBeans) throws Exception {
        String status = "";
        DirectJDBCDAO directJDBCDAO = DirectJDBCDAO.getInstance();
        if(parcelRateResponse != null){
            if(parcelRateResponse.getStatusCode() != null && parcelRateResponse.getStatusCode().equals(0)){
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
                        } catch (Exception e) {
                            e.printStackTrace();
                            log.error("ERROR - " + e.getStackTrace() + "--Parent Id->" + bean.getParentId());
                        }

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

                    status = ParcelAuditConstant.RTRStatus.NO_PRICE_SHEET.value;
                }
            }else{

                status = ParcelAuditConstant.RTRStatus.RATING_EXCEPTION.value;
            }
        } else if (bean.getExcludeRating() == 1) {

            status = ParcelAuditConstant.RTRStatus.CLOSED.value;
        } else {

            status = ParcelAuditConstant.RTRStatus.RATING_EXCEPTION.value;
        }

        if (ParcelAuditConstant.RTRStatus.NO_PRICE_SHEET.value.equalsIgnoreCase(status) ||
                ParcelAuditConstant.RTRStatus.RATING_EXCEPTION.value.equalsIgnoreCase(status) || bean.getExcludeRating() == 1) {
            if (parcelAuditDetails != null) {
                // Connection conn = null;
                try {
                    // conn = ServiceLocator.getDatabaseConnection();
                    // conn.setAutoCommit(false);
                    for (ParcelAuditDetailsDto dto : parcelAuditDetails) {
                        if (dto != null && dto.getId() != null) {
                            ParcelRateDetailsDto rateDetails = new ParcelRateDetailsDto();
                            rateDetails.setRtrStatus(status);
                            dto.setPieces(bean.getPiecesCount());
                            ParcelRatingUtil.setCommonValues(rateDetails, bean, null);
                            rateDetails.setExcludeRating(bean.getExcludeRating());
                            if (rateDetails.getExcludeRating() == 1) {
                                rateDetails.setRtrAmount(dto.getNetAmount() != null ? new BigDecimal(dto.getNetAmount()) : BigDecimal.ZERO);
                            }
                            directJDBCDAO.updateShipmentRateDetails(ParcelAuditConstant.EBILL_GFF_TABLE_NAME, dto.getId().toString(), ParcelAuditConstant.PARCEL_RTR_RATING_USER_NAME, rateDetails);
                        }
                    }
                    updateUpsOtherFieldValues(parcelAuditDetails);
                    // conn.commit();
                } catch (Exception e) {
                    // conn.rollback();
                    log.error("ERROR - " + e.getStackTrace() + "--Parent Id->" + parcelAuditDetails.get(0).getParentId());
                    e.printStackTrace();
                } finally {
                    //  ParcelRatingUtil.closeConnection(conn);
                }
            }
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
                DirectJDBCDAO.getInstance().updateRtrStatusByIds(entityIds.toString(), ParcelAuditConstant.PARCEL_RTR_RATING_USER_NAME, rtrStatus.value, parcelAuditDetails.get(0).getCarrierId());
            }
        }
    }


    private String updateAmountWithRTRResponseChargesForUps(ParcelRateResponse.PriceSheet priceSheet, List<ParcelAuditDetailsDto> parcelAuditDetails, ParcelAuditConstant.RTRStatus rtrStatus, String flagged, List<ServiceFlagAccessorialBean> accessorialBeans, RatingQueueBean queueBean) throws Exception {

        DirectJDBCDAO directJDBCDAO = DirectJDBCDAO.getInstance();
        boolean frtChargeFound = false;
        boolean fscChargeFound = false;
        List<ParcelRateResponse.Charge> mappedAccChanges = new ArrayList<>();
        List<ParcelRateResponse.Charge> mappedDscChanges = new ArrayList<>();
        List<String> mappedAccList = new ArrayList<>();

        ParcelRateResponse.Charge frtCharge = ParcelRateResponseParser.findChargeByType(ParcelRateResponse.ChargeType.ITEM.name(), priceSheet);

        BigDecimal ratedWeight = frtCharge.getWeight() == null ? new BigDecimal("0") : frtCharge.getWeight();

        List<AccessorialDto> prevParentsRatesDtos = directJDBCDAO.getRatesForPrevParentIds(parcelAuditDetails.get(0), queueBean.getReturnFlag());

        // Connection conn = null;

        try {
            // conn = ServiceLocator.getDatabaseConnection();
            // conn.setAutoCommit(false);

            for(ParcelAuditDetailsDto auditDetails : parcelAuditDetails){

                auditDetails.setPieces(queueBean.getPiecesCount());

                if(auditDetails != null && auditDetails.getChargeClassificationCode() != null && !auditDetails.getChargeClassificationCode().isEmpty()){
                    ParcelRateResponse.Charge charge = null;
                    if(ParcelAuditConstant.ChargeClassificationCode.FRT.name().equalsIgnoreCase(auditDetails.getChargeClassificationCode())){
                        if (!frtChargeFound) {

                            charge = ParcelRateResponseParser.findChargeByType(ParcelRateResponse.ChargeType.ITEM.name(), priceSheet);
                            if(charge != null){
                                ParcelRateDetailsDto rateDetails = ParcelRateDetailsDto.getInstance();

                                rateDetails.setDimDivisor(charge.getDimDivisor() == null ? new BigDecimal("0") : charge.getDimDivisor());
                                rateDetails.setRatedWeight(charge.getWeight() == null ? new BigDecimal("0") : charge.getWeight());

                                ratedWeight = rateDetails.getRatedWeight();

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
                            if ("RDELSC".equalsIgnoreCase(bean.getLookUpValue()) && (auditDetails.getReceiverState() != null
                                    && "AK".equalsIgnoreCase(auditDetails.getReceiverState())))
                                bean.setLookUpValue("RASAK");
                            if ("RDELSC".equalsIgnoreCase(bean.getLookUpValue()) && (auditDetails.getReceiverState() != null
                                    && "HI".equalsIgnoreCase(auditDetails.getReceiverState())))
                                bean.setLookUpValue("RASHI");

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

/*                        if ((auditDetails.getChargeCatagoryCode() != null && auditDetails.getChargeCategoryDetailCode() != null) &&
                                ("ADJ".equalsIgnoreCase(auditDetails.getChargeCatagoryCode()) && "CADJ".equalsIgnoreCase(auditDetails.getChargeCategoryDetailCode()))) {
                            rateDetails.setRtrAmount(auditDetails.getNetAmount() != null ? new BigDecimal(auditDetails.getNetAmount()) : new BigDecimal("0.00"));
                        } else*/
                            rateDetails.setRtrAmount(new BigDecimal("0.00"));

                        rateDetails.setRtrStatus(rtrStatus.value);
                        rateDetails.setHwtIdentifier(auditDetails.getMultiWeightNumber());
                        rateDetails.setRatedWeight(ratedWeight);
                        rateDetails.setFlagged(flagged);
                        ServiceFlagAccessorialBean bean = ParcelRatingUtil.getAccessorialBean(accessorialBeans, auditDetails.getChargeDescription(), auditDetails.getChargeDescriptionCode(), 21L);

                        if (bean != null) {
                            rateDetails.setAccCode(bean.getLookUpValue());

                            BigDecimal prevRtrAmt = ParcelRatingUtil.findPrevRateAmtByCode(prevParentsRatesDtos, bean.getLookUpValue(), "accessorial");
                            if (bean != null && prevRtrAmt != null && !mappedAccList.contains(bean.getLookUpValue()))
                                rateDetails.setRtrAmount(rateDetails.getRtrAmount().subtract(prevRtrAmt));

                            mappedAccList.add(bean.getLookUpValue());

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

            ParcelRatingUtil.prepareAdditionalAccessorial(priceSheet, parcelAuditDetails.get(0).getParentId(), mappedAccChanges, addAccAndDisdtos, frtChargeFound, fscChargeFound, prevParentsRatesDtos, queueBean);
            ParcelRatingUtil.prepareAddDiscounts(priceSheet, parcelAuditDetails.get(0).getParentId(), mappedDscChanges, addAccAndDisdtos, prevParentsRatesDtos);

            directJDBCDAO.saveAccInfo(addAccAndDisdtos, parcelAuditDetails.get(0).getParentId(), 21);

            updateUpsOtherFieldValues(parcelAuditDetails);

            // conn.commit();
        } catch (Exception e) {
            //conn.rollback();
            log.error("ERROR - " + e.getStackTrace() + "--Parent Id->" + parcelAuditDetails.get(0).getParentId());
            e.printStackTrace();
        } finally {
            // ParcelRatingUtil.closeConnection(conn);
        }

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
        return DirectJDBCDAO.getInstance().getRatedChargeAmount(parentId, trackingNumber);
    }


    public void updateUpsOtherFieldValues(List<ParcelAuditDetailsDto> rateDetailsList) throws Exception {

        DirectJDBCDAO.getInstance().updateUpsOtherFieldValues(rateDetailsList);

    }

    /**
     * This method returns service flag accessroials based on carrier id and module name which needs to send in the XML request
     *
     * @param carrierId
     * @param moduleName
     * @return
     */
    public List<ServiceFlagAccessorialBean> getServiceFlagAcessorials(Long carrierId, String moduleName) {

        return DirectJDBCDAO.getInstance().getServiceFlagAcessorials(carrierId, moduleName);
    }

}
