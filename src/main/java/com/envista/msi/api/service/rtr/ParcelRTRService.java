package com.envista.msi.api.service.rtr;

import com.envista.msi.api.dao.rtr.ParcelRTRDao;
import com.envista.msi.api.service.BaseService;
import com.envista.msi.api.web.rest.dto.UserProfileDto;
import com.envista.msi.api.web.rest.dto.rtr.ParcelAuditDetailsDto;
import com.envista.msi.api.web.rest.util.CommonUtil;
import com.envista.msi.api.web.rest.util.audit.parcel.ParcelAuditConstant;
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
public class ParcelRTRService extends BaseService{
    @Inject
    private ParcelRTRDao parcelRTRDao;

    @Autowired
    @org.springframework.beans.factory.annotation.Qualifier(value = "rtrRateResource")
    private MessageSource messageSource;

    enum RTRStatus{
        ReadyForRate,
        Contested,
        Closed,
        RatingException,
        NoPriceSheet
    }

    enum RateTo{
        UPS,
        NON_UPS
    }
    /**
     * Returns tracking number wise UPS parcel audit details.
     * @param fromDate
     * @param toDate
     * @return
     */
    public Map<String, List<ParcelAuditDetailsDto>> loadUpsParcelAuditDetails(String customerId, String fromDate, String toDate){
        return prepareTrackingNumberWiseAuditDetails(parcelRTRDao.loadUpsParcelAuditDetails(customerId, fromDate, toDate));
    }

    /**
     * Returns tracking number wise non-UPS parcel audit details.
     * @param fromDate
     * @param toDate
     * @return
     */
    public Map<String, List<ParcelAuditDetailsDto>> loadNonUpsParcelAuditDetails(String customerId, String fromDate, String toDate){
        return prepareTrackingNumberWiseAuditDetails(parcelRTRDao.loadNonUpsParcelAuditDetails(customerId, fromDate, toDate, "22"));
    }

    public void parcelRTRRating(String fromDate, String toDate, String customerId){
        String licenseKey = messageSource.getMessage("RateRequest-LicenseKey", null, null);
        String strProtocol = messageSource.getMessage("RTRprotocol", null, null);
        String strHostName = messageSource.getMessage("RTRHostName", null, null);
        String strPrefix = messageSource.getMessage("RTRPrefix", null, null);
        String url = strProtocol + "://" + strHostName + "/" + strPrefix;

        doParcelRating(loadUpsParcelAuditDetails(customerId, fromDate, toDate), url, licenseKey, RateTo.UPS);
        doParcelRating(loadNonUpsParcelAuditDetails(customerId, fromDate, toDate), url, licenseKey, RateTo.NON_UPS);
    }

    private void doParcelRating(Map<String, List<ParcelAuditDetailsDto>> parcelAuditDetailsMap, String url, String licenseKey, RateTo rateTo){
        if(parcelAuditDetailsMap != null && !parcelAuditDetailsMap.isEmpty()){
            for(Map.Entry<String, List<ParcelAuditDetailsDto>> parcelAuditEntry : parcelAuditDetailsMap.entrySet()){
                if(parcelAuditEntry != null){
                    try{
                        callRTRAndPopulateRates(url, licenseKey, parcelAuditEntry.getValue(), rateTo);
                    }catch (Exception e){
                        //Do nothing
                    }
                }
            }
        }
    }

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

    //@Transactional(propagation=Propagation.REQUIRED, readOnly=false, rollbackFor=Exception.class)
    private void callRTRAndPopulateRates(String url, String licenseKey, List<ParcelAuditDetailsDto> parcelAuditDetails, RateTo rateTo) throws Exception {
        String requestPayload = "";
        switch (rateTo){
            case UPS:
                requestPayload = ParcelRateRequestBuilder.buildParcelRateRequestForUps(parcelAuditDetails, licenseKey).toXmlString();
                updateRateForUps(ParcelRateResponseParser.parse(CommonUtil.connectAndGetResponseAsString(url, requestPayload)), parcelAuditDetails);
                break;
            case NON_UPS:
                requestPayload = ParcelRateRequestBuilder.buildParcelRateRequestForNonUpsCarrier(parcelAuditDetails, licenseKey).toXmlString();
                updateRateForNonUpsCarrier(ParcelRateResponseParser.parse(CommonUtil.connectAndGetResponseAsString(url, requestPayload)), parcelAuditDetails);
                break;
        }
    }

    private void updateRateForNonUpsCarrier(ParcelRateResponse parcelRateResponse, List<ParcelAuditDetailsDto> parcelAuditDetails) throws Exception {
        if(parcelRateResponse != null){
            if(parcelRateResponse.getStatusCode().equals(0)){
                if(parcelRateResponse.getPriceSheets() != null && !parcelRateResponse.getPriceSheets().isEmpty()){
                    BigDecimal sumOfNetAmount = findSumOfNetAmount(parcelAuditDetails);

                    //Considering first price sheet in the response as actual price sheet to compare the total amount.
                    ParcelRateResponse.PriceSheet firstPriceSheet = parcelRateResponse.getPriceSheets().get(0);
                    if(firstPriceSheet != null && firstPriceSheet.getTotal() != null && firstPriceSheet.getTotal().compareTo(sumOfNetAmount) < 0){
                        updateAmountWithRTRResponseChargesForNonUpsCarrier(firstPriceSheet, parcelAuditDetails);
                    }else{
                        updateRTRAmountAndStatus(parcelAuditDetails, RTRStatus.Closed);
                    }
                }else{
                    updateRTRAmountAndStatus(parcelAuditDetails, RTRStatus.NoPriceSheet);
                }
            }else{
                updateRTRAmountAndStatus(parcelAuditDetails, RTRStatus.RatingException);
            }
        }else{
            updateRTRAmountAndStatus(parcelAuditDetails, RTRStatus.RatingException);
        }
    }

    private void updateAmountWithRTRResponseChargesForNonUpsCarrier(ParcelRateResponse.PriceSheet priceSheet, List<ParcelAuditDetailsDto> parcelAuditDetails) throws Exception {
        boolean hasDiscount = false;
        UserProfileDto user = getLoggedInUser();
        for(ParcelAuditDetailsDto auditDetails : parcelAuditDetails){
            if(ParcelAuditConstant.ChargeClassificationCode.ACS.name().equalsIgnoreCase(auditDetails.getChargeClassificationCode())
                    && ParcelAuditConstant.ChargeDescriptionCode.DSC.name().equalsIgnoreCase(auditDetails.getChargeDescriptionCode())){
                hasDiscount = true;
            }
            if(auditDetails != null && auditDetails.getChargeClassificationCode() != null && !auditDetails.getChargeClassificationCode().isEmpty()){
                ParcelRateResponse.Charge charge = null;
                if(ParcelAuditConstant.ChargeClassificationCode.FRT.name().equalsIgnoreCase(auditDetails.getChargeClassificationCode())){
                    charge = ParcelRateResponseParser.findChargeByType(ParcelRateResponse.ChargeType.ITEM.name(), priceSheet);
                }else if(ParcelAuditConstant.ChargeClassificationCode.ACS.name().equalsIgnoreCase(auditDetails.getChargeClassificationCode())
                        && ParcelAuditConstant.ChargeDescriptionCode.FSC.name().equalsIgnoreCase(auditDetails.getChargeDescriptionCode())){
                    charge = ParcelRateResponseParser.findChargeByType(ParcelRateResponse.ChargeType.ACCESSORIAL_FUEL.name(), priceSheet);
                }else {
                    //need to clarify this step, whether it is required for NonUPS Carrier or not.
                    charge = ParcelRateResponseParser.findChargeByEDICodeInResponse(auditDetails.getChargeClassificationCode(), priceSheet);
                }

                if(charge != null){
                    parcelRTRDao.updateRTRInvoiceAmount(auditDetails.getId(), user.getUserName(), charge.getAmount(), RTRStatus.Contested.name(), auditDetails.getCarrierId());
                }else{
                    parcelRTRDao.updateRTRInvoiceAmount(auditDetails.getId(), user.getUserName(), new BigDecimal("0"), RTRStatus.Contested.name(), auditDetails.getCarrierId());
                }
            }
        }

        if(hasDiscount){ //if discounts applied at shipment level.
            updateDiscountChargeForNonUpsCarrier(parcelAuditDetails, RTRStatus.Contested);
        }
    }

    private void updateDiscountChargeForNonUpsCarrier(List<ParcelAuditDetailsDto> parcelAuditDetails, RTRStatus rtrStatus) throws Exception {
        if(parcelAuditDetails != null && !parcelAuditDetails.isEmpty()){
            StringJoiner entityIds = new StringJoiner(",");
            for(ParcelAuditDetailsDto auditDetails : parcelAuditDetails){
                if (ParcelAuditConstant.ChargeClassificationCode.ACS.name().equalsIgnoreCase(auditDetails.getChargeClassificationCode())
                        && ParcelAuditConstant.ChargeDescriptionCode.DSC.name().equalsIgnoreCase(auditDetails.getChargeDescriptionCode())){
                    if(auditDetails.getNetAmount() != null){
                        entityIds.add(auditDetails.getId().toString());
                    }
                }
            }
            if(entityIds.length() > 0){
                parcelRTRDao.updateInvoiceAmountByIds(entityIds.toString(), getLoggedInUser().getUserName(), rtrStatus.name(), parcelAuditDetails.get(0).getCarrierId());
            }
        }
    }

    private void updateRateForUps(ParcelRateResponse parcelRateResponse, List<ParcelAuditDetailsDto> parcelAuditDetails) throws Exception {
        if(parcelRateResponse != null){
            if(parcelRateResponse.getStatusCode() != null && parcelRateResponse.getStatusCode().equals(0)){
                if(parcelRateResponse.getPriceSheets() != null && !parcelRateResponse.getPriceSheets().isEmpty()){
                    BigDecimal sumOfNetAmount = findSumOfNetAmount(parcelAuditDetails);

                    //Considering first price sheet in the response as actual price sheet to compare the total amount.
                    ParcelRateResponse.PriceSheet firstPriceSheet = parcelRateResponse.getPriceSheets().get(0);
                    if(firstPriceSheet != null && firstPriceSheet.getTotal() != null && firstPriceSheet.getTotal().compareTo(sumOfNetAmount) < 0){
                        updateAmountWithRTRResponseChargesForUps(firstPriceSheet, parcelAuditDetails);
                    }else{
                        updateRTRAmountAndStatus(parcelAuditDetails, RTRStatus.Closed);
                    }
                }else{
                    updateRTRAmountAndStatus(parcelAuditDetails, RTRStatus.NoPriceSheet);
                }
            }else{
                updateRTRAmountAndStatus(parcelAuditDetails, RTRStatus.RatingException);
            }
        }else{
            updateRTRAmountAndStatus(parcelAuditDetails, RTRStatus.Contested);
        }
    }

    private void updateAmountWithRTRResponseChargesForUps(ParcelRateResponse.PriceSheet priceSheet, List<ParcelAuditDetailsDto> parcelAuditDetails) throws Exception {
        UserProfileDto user = getLoggedInUser();
        for(ParcelAuditDetailsDto auditDetails : parcelAuditDetails){
            if(auditDetails != null && auditDetails.getChargeClassificationCode() != null && !auditDetails.getChargeClassificationCode().isEmpty()){
                ParcelRateResponse.Charge charge = null;
                if(ParcelAuditConstant.ChargeClassificationCode.FRT.name().equalsIgnoreCase(auditDetails.getChargeClassificationCode())){
                    charge = ParcelRateResponseParser.findChargeByType(ParcelRateResponse.ChargeType.ITEM.name(), priceSheet);
                    parcelRTRDao.updateRTRInvoiceAmount(auditDetails.getId(), user.getUserName(), charge.getRate(), RTRStatus.Contested.name(), auditDetails.getCarrierId());
                }else if(ParcelAuditConstant.ChargeClassificationCode.FSC.name().equalsIgnoreCase(auditDetails.getChargeClassificationCode())){
                    charge = ParcelRateResponseParser.findChargeByType(ParcelRateResponse.ChargeType.ACCESSORIAL_FUEL.name(), priceSheet);
                    parcelRTRDao.updateRTRInvoiceAmount(auditDetails.getId(), user.getUserName(), charge.getAmount(), RTRStatus.Contested.name(), auditDetails.getCarrierId());
                }else{
                    charge = ParcelRateResponseParser.findChargeByEDICodeInResponse(auditDetails.getChargeClassificationCode(), priceSheet);
                    parcelRTRDao.updateRTRInvoiceAmount(auditDetails.getId(), user.getUserName(), charge.getAmount(), RTRStatus.Contested.name(), auditDetails.getCarrierId());
                }

                if(null == charge){
                    parcelRTRDao.updateRTRInvoiceAmount(auditDetails.getId(), user.getUserName(), new BigDecimal("0"), RTRStatus.Contested.name(), auditDetails.getCarrierId());
                }
            }
        }
    }

    private void updateRTRAmountAndStatus(List<ParcelAuditDetailsDto> parcelAuditDetails, RTRStatus rtrStatus) throws Exception {
        if(parcelAuditDetails != null && !parcelAuditDetails.isEmpty()){
            StringJoiner entityIds = new StringJoiner(",");
            for (ParcelAuditDetailsDto auditDetails : parcelAuditDetails){
                if(auditDetails != null){
                    entityIds.add(auditDetails.getId().toString());
                }
            }
            if(entityIds.length() > 0){
                parcelRTRDao.updateInvoiceAmountByIds(entityIds.toString(), getLoggedInUser().getUserName(), rtrStatus.name(), parcelAuditDetails.get(0).getCarrierId());
            }
        }
    }

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
}
