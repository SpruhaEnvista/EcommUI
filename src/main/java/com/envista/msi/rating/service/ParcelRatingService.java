package com.envista.msi.rating.service;

import com.envista.msi.api.web.rest.dto.rtr.MsiARChargeCodesDto;
import com.envista.msi.api.web.rest.dto.rtr.ParcelARChargeCodeMappingDto;
import com.envista.msi.api.web.rest.dto.rtr.ParcelAuditDetailsDto;
import com.envista.msi.api.web.rest.util.audit.parcel.ParcelAuditConstant;
import com.envista.msi.rating.bean.RatingQueueBean;
import com.envista.msi.rating.dao.DirectJDBCDAO;
import com.envista.msi.rating.dao.RatingQueueDAO;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Sujit kumar on 02/05/2018.
 */
public class ParcelRatingService {
    private DirectJDBCDAO directJDBCDAO = new DirectJDBCDAO();
    RatingQueueDAO ratingQueueDAO = new RatingQueueDAO();
    public void doParcelAuditingInvoiceNumberWise(List<ParcelAuditDetailsDto> invoiceList, String trackingNumbers, String rateTo, String fromShipDate, String toShipDate, String customerIds) {
        if (invoiceList != null && !invoiceList.isEmpty()) {
            String licenseKey = "FRT23A9DB63C39VM2A0C";
            String strProtocol = "https";
            String strHostName = "rtr02.envistacorp.com";
            String strPrefix = "ws/freight/rate";
            String url = strProtocol + "://" + strHostName + "/" + strPrefix;

            MsiARChargeCodesDto msiARChargeCode = getAllMappedARChargeCodes();


        }
    }

    public Map<String, String> getMappedDASChargeCodes(){
        Map<String, String> chargeCodeMap = null;
        List<ParcelARChargeCodeMappingDto> mappedChargeCodes = directJDBCDAO.loadMappedARChargeCodes(ParcelAuditConstant.MSI_AR_CHARGE_CODE_MAPPING_MODELE_NAME, ParcelAuditConstant.MSI_AR_DAS_CHARGE_CODE_NAME);
        if(mappedChargeCodes != null && !mappedChargeCodes.isEmpty()){
            chargeCodeMap =  prepareChargeCodeMap(mappedChargeCodes);
        }
        return chargeCodeMap;
    }

    public Map<String, String> getMappedLPSChargeCodes(){
        Map<String, String> chargeCodeMap = null;
        List<ParcelARChargeCodeMappingDto> mappedChargeCodes = directJDBCDAO.loadMappedARChargeCodes(ParcelAuditConstant.MSI_AR_CHARGE_CODE_MAPPING_MODELE_NAME, ParcelAuditConstant.MSI_AR_LPS_CHARGE_CODE_NAME);
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

    public void saveRatingQueueBean(RatingQueueBean ratingQueueBean){
        if(ratingQueueBean != null){
            ratingQueueDAO.saveRatingQueueBean(ratingQueueBean);
        }
    }

    public List<ParcelAuditDetailsDto> getUpsParcelShipmentDetails(String customerId, String fromShipDate, String toShipDate, String trackingNumbers, String invoiceIds, boolean ignoreRtrStatus){
        return ratingQueueDAO.getUpsParcelShipmentDetails(customerId, fromShipDate, toShipDate, trackingNumbers, invoiceIds, ignoreRtrStatus);
    }

    public List<ParcelAuditDetailsDto> getUpsParcelShipmentDetails(String customerId, String fromShipDate, String toShipDate, boolean ignoreRtrStatus){
        return getUpsParcelShipmentDetails(customerId, fromShipDate, toShipDate, null, null, ignoreRtrStatus);
    }

    public List<ParcelAuditDetailsDto> getUpsParcelShipmentDetails(String customerId, String fromShipDate, String toShipDate){
        return getUpsParcelShipmentDetails(customerId, fromShipDate, toShipDate, null, null, false);
    }

    public List<ParcelAuditDetailsDto> getFedExParcelShipmentDetails(String customerId, String fromShipDate, String toShipDate, String trackingNumbers, String invoiceIds){
        return ratingQueueDAO.getNonUpsParcelShipmentDetails(customerId, "22", fromShipDate, toShipDate, trackingNumbers, invoiceIds);
    }

    public List<ParcelAuditDetailsDto> getFedExParcelShipmentDetails(String customerId, String fromShipDate, String toShipDate) {
        return getFedExParcelShipmentDetails(customerId, fromShipDate, toShipDate, null, null);
    }
}
