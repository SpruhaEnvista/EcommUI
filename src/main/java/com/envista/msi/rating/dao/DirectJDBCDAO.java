package com.envista.msi.rating.dao;

import com.envista.msi.api.web.rest.dto.rtr.ParcelARChargeCodeMappingDto;
import com.envista.msi.api.web.rest.dto.rtr.ParcelAuditDetailsDto;
import com.envista.msi.api.web.rest.dto.rtr.ParcelAuditRequestResponseLog;
import com.envista.msi.api.web.rest.dto.rtr.ParcelRateDetailsDto;
import com.envista.msi.api.web.rest.dto.rtr.RatedChargeDetailsDto;
import com.envista.msi.api.web.rest.dto.rtr.StoreRatingDetailsDto;
import com.envista.msi.rating.ServiceLocator;
import com.envista.msi.rating.ServiceLocatorException;
import com.envista.msi.rating.bean.AccessorialDto;
import com.envista.msi.rating.bean.ServiceFlagAccessorialBean;
import com.envista.msi.rating.entity.ParcelRatingInputCriteriaDto;
import oracle.jdbc.OracleTypes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DirectJDBCDAO {

    private static final Logger log = LoggerFactory.getLogger(DirectJDBCDAO.class);

    @Deprecated
    public void updateRTRInvoiceAmount(Long id, String userName, BigDecimal rtrAmount, String rtrStatus, Long carrierId){
        Connection con = null;
        PreparedStatement pstmt = null;
        PreparedStatement apstmt = null;

        String liveUpdateQuery = "";
        String arcUpdateQuery = "";
        if(carrierId==21){
            liveUpdateQuery = " UPDATE SHP_EBILL_GFF_TB SET RTR_STATUS = '"+rtrStatus+"', RTR_AMOUNT="+rtrAmount+", LAST_UPDATE_DATE=sysdate, " +
                    " LAST_UPDATE_USER = '"+userName+"' WHERE EBILL_GFF_ID = "+id;
            arcUpdateQuery = " UPDATE ARC_EBILL_GFF_TB SET RTR_STATUS = '"+rtrStatus+"', RTR_AMOUNT="+rtrAmount+", LAST_UPDATE_DATE=sysdate, " +
                    " LAST_UPDATE_USER = '"+userName+"' WHERE EBILL_GFF_ID = "+id;
        }else{
            liveUpdateQuery = " UPDATE SHP_EBILL_MANIFEST_TB SET RTR_STATUS = '"+rtrStatus+"', RTR_AMOUNT="+rtrAmount+", LAST_UPDATE_DATE=sysdate, " +
                    " LAST_UPDATE_USER = '"+userName+"' WHERE EBILL_MANIFEST_ID = "+id;
            arcUpdateQuery = " UPDATE ARC_EBILL_MANIFEST_TB SET RTR_STATUS = '"+rtrStatus+"', RTR_AMOUNT="+rtrAmount+", LAST_UPDATE_DATE=sysdate, " +
                    " LAST_UPDATE_USER = '"+userName+"' WHERE EBILL_MANIFEST_ID = "+id;
        }

        try {
            con = ServiceLocator.getDatabaseConnection();
            pstmt = con.prepareStatement(liveUpdateQuery);
            pstmt.executeUpdate();
            apstmt = con.prepareStatement(arcUpdateQuery);
            apstmt.executeQuery();

        }catch (SQLException sqle) {
            throw new DaoException("Exception in updateRTRInvoiceAmount", sqle);
        }  catch (ServiceLocatorException sle) {
            throw new DaoException("Exception in updateRTRInvoiceAmount", sle);
        }finally {

            try {
                if (pstmt != null)
                    pstmt.close();
            } catch (SQLException sqle) {
            }

            try {
                if (apstmt != null)
                    apstmt.close();
            } catch (SQLException sqle) {
            }

            try {
                if (con != null)
                    con.close();
            } catch (SQLException sqle) {
            }
        }
    }

    public void updateRtrStatusByIds(String entityIds, String userName, String rtrStatus, Long carrierId) {
        Connection conn = null;
        CallableStatement cstmt = null;
        try {
            conn = ServiceLocator.getDatabaseConnection();
            cstmt = conn.prepareCall("{ call SHP_UPDATE_RTR_STATUS_PROC(?,?,?,?)}");
            cstmt.setString(1, entityIds);
            cstmt.setString(2, userName);
            cstmt.setString(3, rtrStatus);
            cstmt.setLong(4, carrierId);
            cstmt.executeUpdate();

        }catch (SQLException sqle) {
            throw new DaoException("Exception in updateRtrStatusByIds", sqle);
        }  catch (ServiceLocatorException sle) {
            throw new DaoException("Exception in updateRtrStatusByIds", sle);
        }finally {
            try {
                if (cstmt != null)
                    cstmt.close();
            } catch (SQLException sqle) {
            }
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException sqle) {
            }
        }
    }
    public void saveParcelAuditRequestAndResponseLog(ParcelAuditRequestResponseLog requestResponseLog){
        Connection conn = null;
        CallableStatement cstmt =null;
        try{
            conn = ServiceLocator.getDatabaseConnection();
            cstmt = conn.prepareCall("{ call SHP_FRT_SAVE_XML_RATING_PROC(?,?,?,?,?,?,?,?,?)}");
            cstmt.setString(1, requestResponseLog.getTableName());
            cstmt.setString(2, requestResponseLog.getEntityIds());
            cstmt.setString(3, requestResponseLog.getRequestXml1());
            cstmt.setString(4, requestResponseLog.getRequestXml2());
            cstmt.setString(5, requestResponseLog.getResponseXml1());
            cstmt.setString(6, requestResponseLog.getResponseXml2());
            cstmt.setString(7, requestResponseLog.getResponseXml3());
            cstmt.setString(8, requestResponseLog.getCreateUser());
            cstmt.setString(9, requestResponseLog.getRequestXml3());
            cstmt.executeUpdate();

        }catch (SQLException sqle) {
            throw new DaoException("Exception in saveParcelAuditRequestAndResponseLog", sqle);
        }  catch (ServiceLocatorException sle) {
            throw new DaoException("Exception in saveParcelAuditRequestAndResponseLog", sle);
        }finally {
            try {
                if (cstmt != null)
                    cstmt.close();
            } catch (SQLException sqle) {
            }
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException sqle) {
            }
        }
    }

    public void updateInvoiceRtrStatus(Long invoiceId, String rtrStatus, String userName){

        Connection conn = null;
        PreparedStatement prstmt = null;
        PreparedStatement aprstmt = null;

        String lUpdateQuery = "";
        String aUpdateQuery = "";
            lUpdateQuery = " UPDATE SHP_EBILL_INVOICE_TB SET RTR_STATUS = '"+rtrStatus+"', LAST_UPDATE_DATE=sysdate, " +
                    " LAST_UPDATE_USER = '"+userName+"' WHERE INVOICE_ID = "+invoiceId;
            aUpdateQuery = " UPDATE ARC_EBILL_INVOICE_TB SET RTR_STATUS = '"+rtrStatus+"', LAST_UPDATE_DATE=sysdate, " +
                    " LAST_UPDATE_USER = '"+userName+"' WHERE INVOICE_ID = "+invoiceId;


        try {
            conn = ServiceLocator.getDatabaseConnection();
            prstmt = conn.prepareStatement(lUpdateQuery);
            prstmt.executeUpdate();
            aprstmt = conn.prepareStatement(aUpdateQuery);
            aprstmt.executeQuery();

        }catch (SQLException sqle) {
            throw new DaoException("Exception in updateInvoiceRtrStatus", sqle);
        }  catch (ServiceLocatorException sle) {
            throw new DaoException("Exception in updateInvoiceRtrStatus", sle);
        }finally {

            try {
                if (prstmt != null)
                    prstmt.close();
            } catch (SQLException sqle) {
            }

            try {
                if (aprstmt != null)
                    aprstmt.close();
            } catch (SQLException sqle) {
            }

            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException sqle) {
            }
        }
    }

    public void updateShipmentRateDetails(String referenceTableName, String entityId, String userName, ParcelRateDetailsDto rateDetails) {
        Connection conn = null;
        CallableStatement cstmt = null;
        try{
            conn = ServiceLocator.getDatabaseConnection();
            cstmt = conn.prepareCall("{ call SHP_SAVE_RATE_DETAILS_PROC(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
            cstmt.setString(1,referenceTableName);
            cstmt.setString(2, entityId);
            cstmt.setString(3,userName);
            cstmt.setBigDecimal(4,rateDetails != null && rateDetails.getDimDivisor() != null ? rateDetails.getDimDivisor() : new BigDecimal("0"));
            cstmt.setString(5, rateDetails != null && rateDetails.getShipperCategory() != null ? rateDetails.getShipperCategory() : "");
            cstmt.setBigDecimal(6, rateDetails != null && rateDetails.getRatedWeight() != null ? rateDetails.getRatedWeight() : new BigDecimal("0"));
            cstmt.setString(7, rateDetails != null && rateDetails.getContractName() != null ? rateDetails.getContractName() : "");
            cstmt.setBigDecimal(8, rateDetails != null && rateDetails.getFuelTablePercentage() != null ? rateDetails.getFuelTablePercentage() : new BigDecimal("0"));
            cstmt.setBigDecimal(9, rateDetails != null && rateDetails.getRatedFuelSurchargeDiscount() != null ? rateDetails.getRatedFuelSurchargeDiscount() : new BigDecimal("0"));
            cstmt.setBigDecimal(10, rateDetails != null && rateDetails.getRatedCustomFuelSurchargeDiscount() != null ? rateDetails.getRatedCustomFuelSurchargeDiscount() : new BigDecimal("0"));
            cstmt.setBigDecimal(11, rateDetails != null && rateDetails.getRatedBaseDiscount() != null ? rateDetails.getRatedBaseDiscount() : new BigDecimal("0"));
            cstmt.setBigDecimal(12, rateDetails != null && rateDetails.getRatedEarnedDiscount() != null ? rateDetails.getRatedEarnedDiscount() : new BigDecimal("0"));
            cstmt.setBigDecimal(13, rateDetails != null && rateDetails.getRatedMinMaxAdjustment() != null ? rateDetails.getRatedMinMaxAdjustment() : new BigDecimal("0"));
            cstmt.setBigDecimal(14, rateDetails != null && rateDetails.getRatedGrossFuel() != null ? rateDetails.getRatedGrossFuel() : new BigDecimal("0"));
            cstmt.setBigDecimal(15, rateDetails != null && rateDetails.getResidentialSurchargeDiscount() != null ? rateDetails.getResidentialSurchargeDiscount() : new BigDecimal("0"));
            cstmt.setBigDecimal(16, rateDetails != null && rateDetails.getResidentialSurchargeDiscountPercentage() != null ? rateDetails.getResidentialSurchargeDiscountPercentage() : new BigDecimal("0"));
            cstmt.setBigDecimal(17, rateDetails != null && rateDetails.getDeliveryAreaSurchargeDiscount() != null ? rateDetails.getDeliveryAreaSurchargeDiscount() : new BigDecimal("0"));
            cstmt.setBigDecimal(18, rateDetails != null && rateDetails.getRtrAmount() != null ? rateDetails.getRtrAmount() : new BigDecimal("0"));
            cstmt.setString(19, rateDetails != null && rateDetails.getRtrStatus() != null ? rateDetails.getRtrStatus() : "");
            cstmt.setString(20, rateDetails != null && rateDetails.getHwtIdentifier() != null ? rateDetails.getHwtIdentifier() : null);
            cstmt.setString(21, rateDetails != null && rateDetails.getRateSetName() != null ? rateDetails.getRateSetName() : null);
            cstmt.setString(22, rateDetails != null && rateDetails.getFlagged() != null ? rateDetails.getFlagged() : null);
            cstmt.setString(23, rateDetails != null && rateDetails.getZone() != null ? rateDetails.getZone() : null);
            cstmt.setString(24, rateDetails != null && rateDetails.getAccCode() != null ? rateDetails.getAccCode() : null);
            cstmt.setString(25, rateDetails != null && rateDetails.getReturnFlag() != null ? rateDetails.getReturnFlag() : "N");
            cstmt.setString(26, rateDetails != null && rateDetails.getResiFlag() != null ? rateDetails.getResiFlag() : "N");
            cstmt.setString(27, rateDetails != null && rateDetails.getComToRes() != null ? rateDetails.getComToRes() : "");
            cstmt.setLong(28, rateDetails != null && rateDetails.getActualServiceBucket() != null ? rateDetails.getActualServiceBucket() : -1L);

            cstmt.executeUpdate();

        } catch (SQLException sqle) {
            sqle.printStackTrace();
            throw new DaoException("Exception in updateShipmentRateDetails", sqle);
        }  catch (ServiceLocatorException sle) {
            throw new DaoException("Exception in updateShipmentRateDetails", sle);
        }finally {
            try {
                if (cstmt != null)
                    cstmt.close();
            } catch (SQLException sqle) {
            }
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException sqle) {
            }
        }
    }
    public void updateOtherDiscountShipmentRateDetails(String referenceTableName, String entityIds, String userName, ParcelRateDetailsDto rateDetails){
        Connection conn = null;
        CallableStatement cstmt =null;
        try{
            conn = ServiceLocator.getDatabaseConnection();
            cstmt = conn.prepareCall("{ call SHP_UPDATE_OTHER_DSC_PROC(?,?,?,?,?,?,?,?,?,?)}");
            cstmt.setString(1, referenceTableName);
            cstmt.setString(2, entityIds);
            cstmt.setString(3, userName);
            cstmt.setString(4, rateDetails != null && rateDetails.getContractName() != null ? rateDetails.getContractName() : "");
            cstmt.setString(5, rateDetails != null && rateDetails.getShipperCategory() != null ? rateDetails.getShipperCategory() : "");
            cstmt.setBigDecimal(6, rateDetails != null && rateDetails.getOtherDiscount1() != null ? rateDetails.getOtherDiscount1() : new BigDecimal("0"));
            cstmt.setBigDecimal(7, rateDetails != null && rateDetails.getOtherDiscount2() != null ? rateDetails.getOtherDiscount2() : new BigDecimal("0"));
            cstmt.setBigDecimal(8, rateDetails != null && rateDetails.getOtherDiscount3() != null ? rateDetails.getOtherDiscount3() : new BigDecimal("0"));
            cstmt.setString(9, rateDetails != null && rateDetails.getFlagged() != null ? rateDetails.getFlagged() : "");
            cstmt.setString(10, rateDetails != null && rateDetails.getZone() != null ? rateDetails.getZone() : null);
            cstmt.executeUpdate();
        }catch (SQLException sqle) {
            sqle.printStackTrace();
            throw new DaoException("Exception in updateOtherDiscountShipmentRateDetails", sqle);
        }  catch (ServiceLocatorException sle) {
            throw new DaoException("Exception in updateOtherDiscountShipmentRateDetails", sle);
        }finally {

            try {
                if (cstmt != null)
                    cstmt.close();
            } catch (SQLException sqle) {
            }
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException sqle) {
            }
        }
    }
    public void updateAccessorialShipmentRateDetails(String referenceTableName, String entityIds, String userName, ParcelRateDetailsDto rateDetails){

        Connection conn = null;
        CallableStatement cstmt =null;
        try{
            conn = ServiceLocator.getDatabaseConnection();
            cstmt = conn.prepareCall("{ call SHP_RATE_UPDATE_ACC_PROC(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
            cstmt.setString(1, referenceTableName);
            cstmt.setString(2, entityIds);
            cstmt.setString(3, userName);
            cstmt.setString(4, rateDetails != null && rateDetails.getContractName() != null ? rateDetails.getContractName() : "");
            cstmt.setString(5, rateDetails != null && rateDetails.getShipperCategory() != null ? rateDetails.getShipperCategory() : "");
            cstmt.setBigDecimal(6, rateDetails != null && rateDetails.getAccessorial1() != null ? rateDetails.getAccessorial1() : new BigDecimal("0"));
            cstmt.setBigDecimal(7, rateDetails != null && rateDetails.getAccessorial2() != null ? rateDetails.getAccessorial2() : new BigDecimal("0"));
            cstmt.setBigDecimal(8, rateDetails != null && rateDetails.getAccessorial3() != null ? rateDetails.getAccessorial3() : new BigDecimal("0"));
            cstmt.setBigDecimal(9, rateDetails != null && rateDetails.getAccessorial4() != null ? rateDetails.getAccessorial4() : new BigDecimal("0"));
            cstmt.setString(10, rateDetails != null && rateDetails.getAccessorial1Code() != null ? rateDetails.getAccessorial1Code() : null);
            cstmt.setString(11, rateDetails != null && rateDetails.getAccessorial2Code() != null ? rateDetails.getAccessorial2Code() : null);
            cstmt.setString(12, rateDetails != null && rateDetails.getAccessorial3Code() != null ? rateDetails.getAccessorial3Code() : null);
            cstmt.setString(13, rateDetails != null && rateDetails.getAccessorial4Code() != null ? rateDetails.getAccessorial4Code() : null);
            cstmt.setString(14, rateDetails != null && rateDetails.getFlagged() != null ? rateDetails.getFlagged() : null);
            cstmt.setString(15, rateDetails != null && rateDetails.getZone() != null ? rateDetails.getZone() : null);
            cstmt.executeUpdate();
        }catch (SQLException sqle) {
            sqle.printStackTrace();
            throw new DaoException("Exception in updateAccessorialShipmentRateDetails", sqle);
        }  catch (ServiceLocatorException sle) {
            sle.printStackTrace();
            throw new DaoException("Exception in updateAccessorialShipmentRateDetails", sle);
        }finally {
            try {
                if (cstmt != null)
                    cstmt.close();
            } catch (SQLException sqle) {
            }
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException sqle) {
            }
        }
    }

    public List<ParcelARChargeCodeMappingDto> loadMappedARChargeCodes(String moduleName, String chargeDescription) {
        try{
            Connection conn = null;
            CallableStatement cstmt =null;
            ResultSet rs = null;
            List<ParcelARChargeCodeMappingDto> chargeCodes = null;
            try{
                conn = ServiceLocator.getDatabaseConnection();
                cstmt = conn.prepareCall("{ call SHP_GET_AR_CHARGE_CODES_PROC(?,?,?)}");
                cstmt.setString(1, moduleName);
                cstmt.setString(2, chargeDescription);
                cstmt.registerOutParameter(3, OracleTypes.CURSOR);
                cstmt.execute();

                rs = (ResultSet) cstmt.getObject(3);
                chargeCodes = new ArrayList<>();
                while(rs.next()){
                    ParcelARChargeCodeMappingDto mappedChargeType = new ParcelARChargeCodeMappingDto();
                    mappedChargeType.setId(rs.getLong("lookup_id"));
                    mappedChargeType.setLookupCode(rs.getString("lookup_code"));
                    mappedChargeType.setCodeValue(rs.getString("code_value"));
                    chargeCodes.add(mappedChargeType);
                }
            }catch (SQLException sqle) {
                System.out.println(
                        "Exception in loadMappedARChargeCodes -- > " + sqle.getStackTrace());
            }  catch (ServiceLocatorException sle) {
                System.out.println("Exception in loadMappedARChargeCodes -- > "+sle.getStackTrace());
            }finally {
                try {
                    if (cstmt != null)
                        cstmt.close();
                } catch (SQLException sqle) {
                }
                try {
                    if (conn != null)
                        conn.close();
                } catch (SQLException sqle) {
                }
            }
            return chargeCodes;
        }catch (Exception e){
            e.printStackTrace();
            throw new DaoException("Error while loading MSI-AR charge Details", e);
        }
    }

    public List<RatedChargeDetailsDto> getRatedChargeAmount(Long parentId, String tracking_number){
        Connection conn = null;
        CallableStatement cstmt = null;
        ResultSet rs = null;
        List<RatedChargeDetailsDto> ratedChargeDetailsDtoList = null;
        try{
            conn = ServiceLocator.getDatabaseConnection();
            cstmt = conn.prepareCall("{ call SHP_GET_UPS_RATED_AMOUNT_PROC(?,?,?)}");
            cstmt.setLong(1, parentId);
            cstmt.setString(2, tracking_number);
            cstmt.registerOutParameter(3, OracleTypes.CURSOR);
            cstmt.execute();

            ratedChargeDetailsDtoList = new ArrayList<>();
            rs = (ResultSet) cstmt.getObject(3);
            while(rs.next()){
                RatedChargeDetailsDto ratedChargeDetailsDto = new RatedChargeDetailsDto();
                ratedChargeDetailsDto.setId(rs.getLong("ID"));
                ratedChargeDetailsDto.setChargeClassificationCode(rs.getString("charge_classification_code"));
                ratedChargeDetailsDto.setChargeDescriptionCode(rs.getString("charge_description_code"));
                ratedChargeDetailsDto.setBilledAmount(rs.getBigDecimal("net_amount"));
                ratedChargeDetailsDto.setRatedAmount(rs.getBigDecimal("rtr_amount"));
                ratedChargeDetailsDto.setDimDivisor(rs.getBigDecimal("DIM_DIVISOR"));
                ratedChargeDetailsDto.setShipperCategory(rs.getString("SHIPPER_CATEGORY"));
                ratedChargeDetailsDto.setRatedWeight(rs.getBigDecimal("RATED_WEIGHT"));
                ratedChargeDetailsDto.setContractName(rs.getString("CONTRACT_NAME"));
                ratedChargeDetailsDto.setFuelTablePercentage(rs.getBigDecimal("FUEL_TABLE_PERC"));
                ratedChargeDetailsDto.setRatedBaseDiscount(rs.getBigDecimal("RATED_BASE_DISCOUNT"));
                ratedChargeDetailsDto.setRatedEarnedDiscount(rs.getBigDecimal("RATED_EARNED_DISCOUNT"));
                ratedChargeDetailsDto.setRatedMinMaxAdjustment(rs.getBigDecimal("RATED_MIN_MAX_ADJ"));
                ratedChargeDetailsDto.setRatedFuelSurchargeDiscount(rs.getBigDecimal("RATED_FUEL_SURCHARGE_DISC"));
                ratedChargeDetailsDto.setRatedCustomFuelSurchargeDiscount(rs.getBigDecimal("RATED_CUST_FUEL_SURCHARGE_DISC"));
                ratedChargeDetailsDto.setRatedGrossFuel(rs.getBigDecimal("RATED_GROSS_FUEL"));
                ratedChargeDetailsDto.setResidentialSurchargeDiscount(rs.getBigDecimal("RES_SURCHARGE_DSC"));
                ratedChargeDetailsDto.setResidentialSurchargeDiscountPercentage(rs.getBigDecimal("RES_SURCHARGE_DSC_PERC"));
                ratedChargeDetailsDto.setOtherDiscount1(rs.getBigDecimal("OTHER_DSC_1"));
                ratedChargeDetailsDto.setOtherDiscount2(rs.getBigDecimal("OTHER_DSC_2"));
                ratedChargeDetailsDto.setOtherDiscount3(rs.getBigDecimal("OTHER_DSC_3"));
                ratedChargeDetailsDto.setAccessorial1(rs.getBigDecimal("ACCESSORIAL_1"));
                ratedChargeDetailsDto.setAccessorial2(rs.getBigDecimal("ACCESSORIAL_2"));
                ratedChargeDetailsDto.setAccessorial3(rs.getBigDecimal("ACCESSORIAL_3"));
                ratedChargeDetailsDto.setAccessorial4(rs.getBigDecimal("ACCESSORIAL_4"));
                ratedChargeDetailsDto.setDeliveryAreaSurchargeDiscount(rs.getBigDecimal("RATED_DAS_DSC"));
                ratedChargeDetailsDto.setAccessorial1Code(rs.getString("ACCESSORIAL_1_CODE"));
                ratedChargeDetailsDto.setAccessorial2Code(rs.getString("ACCESSORIAL_2_CODE"));
                ratedChargeDetailsDto.setAccessorial3Code(rs.getString("ACCESSORIAL_3_CODE"));
                ratedChargeDetailsDto.setAccessorial4Code(rs.getString("ACCESSORIAL_4_CODE"));
                ratedChargeDetailsDto.setFreightCharge(rs.getBigDecimal("FRT_CHARGE"));
                ratedChargeDetailsDto.setFuelSurcharge(rs.getBigDecimal("FSC_CHARGE"));
                ratedChargeDetailsDtoList.add(ratedChargeDetailsDto);
            }
        }catch (SQLException sqle) {
            throw new RuntimeException("Exception in fetching rate details in getRatedChargeAmount -- > "+sqle.getStackTrace());
        }  catch (ServiceLocatorException sle) {
            throw new RuntimeException("Exception in getting connection in getRatedChargeAmount -- > "+sle.getStackTrace());
        }finally {
            try {
                if (rs != null)
                    rs.close();
            } catch (SQLException sqle) {
            }
            try {
                if (cstmt != null)
                    cstmt.close();
            } catch (SQLException sqle) {
            }
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException sqle) {
            }
        }
        return ratedChargeDetailsDtoList;
    }

    public void updateAllShipmentRateDetails(String referenceTableName, String entityIds, String userName, ParcelRateDetailsDto rateDetails){
        Connection conn = null;
        CallableStatement cstmt =null;
        try{
            conn = ServiceLocator.getDatabaseConnection();
            cstmt = conn.prepareCall("{ call SHP_SAVE_ALL_RATE_DETAILS_PROC(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
            cstmt.setString(1,referenceTableName);
            cstmt.setString(2,entityIds);
            cstmt.setString(3,userName);
            cstmt.setBigDecimal(4,rateDetails != null && rateDetails.getDimDivisor() != null ? rateDetails.getDimDivisor() : new BigDecimal("0"));
            cstmt.setString(5, rateDetails != null && rateDetails.getShipperCategory() != null ? rateDetails.getShipperCategory() : "");
            cstmt.setBigDecimal(6, rateDetails != null && rateDetails.getRatedWeight() != null ? rateDetails.getRatedWeight() : new BigDecimal("0"));
            cstmt.setString(7, rateDetails != null && rateDetails.getContractName() != null ? rateDetails.getContractName() : "");
            cstmt.setBigDecimal(8, rateDetails != null && rateDetails.getFuelTablePercentage() != null ? rateDetails.getFuelTablePercentage() : new BigDecimal("0"));
            cstmt.setBigDecimal(9, rateDetails != null && rateDetails.getRatedFuelSurchargeDiscount() != null ? rateDetails.getRatedFuelSurchargeDiscount() : new BigDecimal("0"));
            cstmt.setBigDecimal(10, rateDetails != null && rateDetails.getRatedCustomFuelSurchargeDiscount() != null ? rateDetails.getRatedCustomFuelSurchargeDiscount() : new BigDecimal("0"));
            cstmt.setBigDecimal(11, rateDetails != null && rateDetails.getRatedBaseDiscount() != null ? rateDetails.getRatedBaseDiscount() : new BigDecimal("0"));
            cstmt.setBigDecimal(12, rateDetails != null && rateDetails.getRatedEarnedDiscount() != null ? rateDetails.getRatedEarnedDiscount() : new BigDecimal("0"));
            cstmt.setBigDecimal(13, rateDetails != null && rateDetails.getRatedMinMaxAdjustment() != null ? rateDetails.getRatedMinMaxAdjustment() : new BigDecimal("0"));
            cstmt.setBigDecimal(14, rateDetails != null && rateDetails.getRatedGrossFuel() != null ? rateDetails.getRatedGrossFuel() : new BigDecimal("0"));
            cstmt.setBigDecimal(15, rateDetails != null && rateDetails.getResidentialSurchargeDiscount() != null ? rateDetails.getResidentialSurchargeDiscount() : new BigDecimal("0"));
            cstmt.setBigDecimal(16, rateDetails != null && rateDetails.getResidentialSurchargeDiscountPercentage() != null ? rateDetails.getResidentialSurchargeDiscountPercentage() : new BigDecimal("0"));
            cstmt.setBigDecimal(17, rateDetails != null && rateDetails.getDeliveryAreaSurchargeDiscount() != null ? rateDetails.getDeliveryAreaSurchargeDiscount() : new BigDecimal("0"));
            cstmt.setBigDecimal(18 , rateDetails != null && rateDetails.getFreightCharge() != null ? rateDetails.getFreightCharge() : new BigDecimal("0"));
            cstmt.setBigDecimal(19, rateDetails != null && rateDetails.getFuelSurcharge() != null ? rateDetails.getFuelSurcharge() : new BigDecimal("0"));
            cstmt.setBigDecimal(20, rateDetails != null && rateDetails.getAccessorial1() != null ? rateDetails.getAccessorial1() : new BigDecimal("0"));
            cstmt.setBigDecimal(21, rateDetails != null && rateDetails.getAccessorial2() != null ? rateDetails.getAccessorial2() : new BigDecimal("0"));
            cstmt.setBigDecimal(22, rateDetails != null && rateDetails.getAccessorial3() != null ? rateDetails.getAccessorial3() : new BigDecimal("0"));
            cstmt.setBigDecimal(23, rateDetails != null && rateDetails.getAccessorial4() != null ? rateDetails.getAccessorial4() : new BigDecimal("0"));
            cstmt.setString(24, rateDetails != null && rateDetails.getAccessorial1Code() != null ? rateDetails.getAccessorial1Code() : "");
            cstmt.setString(25, rateDetails != null && rateDetails.getAccessorial2Code() != null ? rateDetails.getAccessorial2Code() : "");
            cstmt.setString(26, rateDetails != null && rateDetails.getAccessorial3Code() != null ? rateDetails.getAccessorial3Code() : "");
            cstmt.setString(27, rateDetails != null && rateDetails.getAccessorial4Code() != null ? rateDetails.getAccessorial4Code() : "");
            cstmt.setBigDecimal(28, rateDetails != null && rateDetails.getRtrAmount() != null ? rateDetails.getRtrAmount() : new BigDecimal("0"));
            cstmt.setString(29, rateDetails != null && rateDetails.getRtrStatus() != null ? rateDetails.getRtrStatus() : "");
            cstmt.setString(30, rateDetails != null && rateDetails.getFlagged() != null ? rateDetails.getFlagged() : "");
            cstmt.setString(31, rateDetails != null && rateDetails.getRateSetName() != null ? rateDetails.getRateSetName() : "");
            cstmt.setString(32, rateDetails != null && rateDetails.getZone() != null ? rateDetails.getZone() : "");
            cstmt.executeUpdate();

        }catch (SQLException sqle) {
            throw new DaoException("updateAllShipmentRateDetails", sqle);
        }  catch (ServiceLocatorException sle) {
            throw new DaoException("updateAllShipmentRateDetails", sle);
        }finally {

            try {
                if (cstmt != null)
                    cstmt.close();
            } catch (SQLException sqle) {
            }
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException sqle) {
            }
        }
    }

    public List<Long> loadInvoiceIds(String fromDate, String toDate, String customerId, String invoiceIds, int limit, String rateTo,String serviceLevelIds){

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String liveSqlQuery = "";
        String archiveQuery = "";
        List<Long> invoiceList = new ArrayList<>();
        try {
            if("UPS".equalsIgnoreCase(rateTo)) {
                if(invoiceIds != null) {
                    liveSqlQuery = " SELECT DISTINCT INVOICE_ID FROM SHP_EBILL_INVOICE_TB ";
                    liveSqlQuery += " WHERE INVOICE_ID IN (" + invoiceIds + ") ";
                } else {
                    liveSqlQuery += " SELECT DISTINCT INVOICE_ID FROM SHP_EBILL_MANIFEST_TB ";
                    liveSqlQuery += " WHERE  INVOICE_ID IN ( ";
                    liveSqlQuery += " SELECT invoice_id FROM SHP_EBILL_INVOICE_TB ";
                    liveSqlQuery += " WHERE  inv_contract_number IN (SELECT contract_number FROM SHP_EBILL_CONTRACT_TB ";
                    liveSqlQuery += " WHERE customer_id in (" + customerId + ") and carrier_id = 21) and inv_carrier_id = 21) ";
                    liveSqlQuery += " and trunc(pickup_date) between TRUNC(TO_DATE('"+ fromDate +"', 'DD-MON-YYYY')) AND TRUNC(TO_DATE('"+ toDate +"', 'DD-MON-YYYY')) ";

                    if( serviceLevelIds != null && !"-1".equalsIgnoreCase(serviceLevelIds) ) {

                        liveSqlQuery += " and ( SERVICE_BUCKET in ("+serviceLevelIds+") OR  ACTUAL_SERVICE_BUCKET in ("+serviceLevelIds+") ) ";
                    }
                }

                if(limit > 0) {
                    liveSqlQuery += " AND ROWNUM <= " + limit;
                }

                archiveQuery = liveSqlQuery.replace("SHP_EBILL_INVOICE_TB", "ARC_EBILL_INVOICE_TB");
                archiveQuery = archiveQuery.replace("SHP_EBILL_MANIFEST_TB", "ARC_EBILL_MANIFEST_TB");
            } else if ("FEDEX".equalsIgnoreCase(rateTo)) {
                liveSqlQuery += " SELECT DISTINCT INVOICE_ID FROM SHP_EBILL_MANIFEST_TB ";
                liveSqlQuery += " WHERE 1 = 1 ";

                if(invoiceIds != null) {
                    liveSqlQuery += " AND INVOICE_ID IN (" + invoiceIds +") ";
                } else {
                    liveSqlQuery += " AND INVOICE_ID IN ( ";
                    liveSqlQuery += " SELECT invoice_id ";
                    liveSqlQuery += " FROM SHP_EBILL_INVOICE_TB ";
                    liveSqlQuery += " WHERE inv_contract_number IN (SELECT contract_number FROM SHP_EBILL_CONTRACT_TB ";
                    liveSqlQuery += " WHERE customer_id IN ("  + customerId + ") and carrier_id = 22) and inv_carrier_id = 22) ";
                    liveSqlQuery += " and trunc(pickup_date) between TRUNC(TO_DATE('"+ fromDate +"', 'DD-MON-YYYY')) AND TRUNC(TO_DATE('"+ toDate +"', 'DD-MON-YYYY')) ";

                    if( serviceLevelIds != null && !"-1".equalsIgnoreCase(serviceLevelIds) ) {

                        liveSqlQuery += " and ( SERVICE_BUCKET in ("+serviceLevelIds+") OR  ACTUAL_SERVICE_BUCKET in ("+serviceLevelIds+") ) ";
                    }

                    if(limit > 0) {
                        liveSqlQuery += " AND ROWNUM <= " + limit;
                    }
                }
                archiveQuery = liveSqlQuery.replace("SHP_EBILL_INVOICE_TB", "ARC_EBILL_INVOICE_TB");
                archiveQuery = archiveQuery.replace("SHP_EBILL_MANIFEST_TB", "ARC_EBILL_MANIFEST_TB");
            }

            conn = ServiceLocator.getDatabaseConnection();
            ps = conn.prepareStatement(liveSqlQuery + " UNION " + archiveQuery);
            rs = ps.executeQuery();


            while(rs.next()) {
                invoiceList.add(rs.getLong("INVOICE_ID"));
            }
        }catch (SQLException sqle) {
            throw new DaoException("loadInvoiceIds", sqle);
        }  catch (ServiceLocatorException sle) {
            throw new DaoException("loadInvoiceIds :: Error getting connection", sle);
        }finally {

            try {
                if (ps != null)
                    ps.close();
            } catch (SQLException sqle) {
            }
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException sqle) {
            }
        }
        return invoiceList;
    }

    public ParcelRatingInputCriteriaDto getRatingInputCriteria(String status) {
        ParcelRatingInputCriteriaDto inputCriteria = null;
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        String selectQueryStr = "SELECT * FROM SHP_PARCEL_RATING_INPUT_TB where STATUS = ? AND ROWNUM <= 1";

        try {
            con = ServiceLocator.getDatabaseConnection();
            pstmt = con.prepareStatement(selectQueryStr);
            pstmt.setString(1, status);
            rs = pstmt.executeQuery();
            inputCriteria = new ParcelRatingInputCriteriaDto();
            if(rs.next()) {
                inputCriteria.setId(rs.getLong("PARCEL_RATING_INPUT_ID"));
                inputCriteria.setFromShipDate(rs.getDate("FROM_SHIP_DATE"));
                inputCriteria.setToShipDate(rs.getDate("TO_SHIP_DATE"));
                inputCriteria.setCustomerId(rs.getLong("CUSTOMER_ID"));
                inputCriteria.setCarrierId(rs.getLong("CARRIER_ID"));
                inputCriteria.setStatus(rs.getString("STATUS"));
                inputCriteria.setTaskId(rs.getLong("TASK_ID"));
                inputCriteria.setRateSetName(rs.getString("RATE_SET"));
                inputCriteria.setThresholdType(rs.getString("THRESHOLD_TYPE"));
                inputCriteria.setThresholdValue(rs.getString("THRESHOLD_VALUE"));
                inputCriteria.setServiceLevel(rs.getString("SERVICE_LEVELS_ID"));
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new DaoException("getRatingInputCriteria", e);
        }finally {
            try {
                if (pstmt != null)
                    pstmt.close();
            } catch (SQLException sqle) {
            }
            try{
                if(rs != null) rs.close();
            }catch (SQLException e){}
            try {
                if (con != null)
                    con.close();
            } catch (SQLException sqle) {
            }
        }
        return inputCriteria;
    }

    public void updateRatingInputCriteriaStatus(Long id, String status) {
        Connection con = null;
        PreparedStatement pstmt = null;

        String selectQueryStr = "UPDATE SHP_PARCEL_RATING_INPUT_TB SET STATUS = ? WHERE PARCEL_RATING_INPUT_ID = ? ";
        try {
            con = ServiceLocator.getDatabaseConnection();
            pstmt = con.prepareStatement(selectQueryStr);
            pstmt.setString(1, status);
            pstmt.setLong(2, id);
            pstmt.executeUpdate();
            con.commit();
        } catch (Exception e) {
            e.printStackTrace();
            try {
                con.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            throw new DaoException("updateRatingInputCriteriaStatus method", e);
        }finally {
            try {
                if (pstmt != null)
                    pstmt.close();
            } catch (SQLException sqle) {
            }
            try {
                if (con != null)
                    con.close();
            } catch (SQLException sqle) {
            }
        }
    }

    public void updateRatingVoidShipmentStatus(String tableName, String entityIds, int value) {
        Connection con = null;
        PreparedStatement pstmt = null;
        String selectQueryStr = "";
        if("SHP_EBILL_MANIFEST_TB".equalsIgnoreCase(tableName)){
            selectQueryStr = "UPDATE SHP_EBILL_FDX_RATES_TB SET IS_VOID_SHIPMENT = ? WHERE EBILL_MANIFEST_ID IN (" + entityIds + ")";
        } else if("SHP_EBILL_GFF_TB".equalsIgnoreCase(tableName)) {
            selectQueryStr = "UPDATE SHP_EBILL_UPS_RATES_TB SET IS_VOID_SHIPMENT = ? WHERE EBILL_GFF_ID IN (" + entityIds + ")";
        }

        try {
            con = ServiceLocator.getDatabaseConnection();
            pstmt = con.prepareStatement(selectQueryStr);
            pstmt.setInt(1, value);
            pstmt.executeUpdate();
            con.commit();
        } catch (Exception e) {
            e.printStackTrace();
            try {
                con.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            throw new DaoException("Exception in updateRatingVoidShipmentStatus", e);
        }finally {
            try {
                if (pstmt != null)
                    pstmt.close();
            } catch (SQLException sqle) {
            }
            try {
                if (con != null)
                    con.close();
            } catch (SQLException sqle) {
            }
        }
    }

    public void updateUpsOtherFieldValues(List<ParcelAuditDetailsDto> rateDetailsList) {
        Connection con = null;
        PreparedStatement pstmt = null;
        String sqlQuery = " UPDATE SHP_EBILL_UPS_RATES_TB ur SET ur.SENDER_COUNTRY = ?, ";
        sqlQuery += " ur.RECEIVER_COUNTRY = ?, ur.SENDER_STATE = ?, ur.RECEIVER_STATE = ?, ur.SHIPMENT_DATE = ?, ur.INVOICE_DATE = ?, ur.INVOICE_NUMBER = ?, ";
        sqlQuery += " ur.LEAD_SHIPMENT_NUMBER = ?, ur.TRACKING_NUMBER = ?, ur.ENTERED_WEIGHT = ?, ur.BILLED_WEIGHT = ?, ur.CONTAINER_TYPE = ?, ur.PACKAGE_DIMENSIONS = ?, ";
        sqlQuery += " ur.ZONE = ?, ur.CHARGE_CLASSIFICATION_CODE = ?, ur.CHARGE_DESCRIPTION = ?, ur.INCENTIVE_AMOUNT = ?, ur.NET_AMOUNT = ?, ur.CUSTOMER_CODE = ?, ur.SHIPPER_CODE = ?, ";
        sqlQuery += " ur.INV_CREATE_DATE = ?, ur.PARENT_ID = ?, ur.DW_FIELD_INFORMATION = ?, ur.INVOICE_ID = ?, ur.CUSTOMER_ID = ?, ur.BILL_OPTION_CODE = ?, ur.CHARGE_CATEGORY_DETAIL_CODE = ?, ";
        sqlQuery += " ur.PIECES = ?, CHARGE_DESCRIPTION_CODE = ? ";
        sqlQuery += " WHERE ur.EBILL_GFF_ID = ? ";

        try {
            con = ServiceLocator.getDatabaseConnection();
            pstmt = con.prepareStatement(sqlQuery);
            con.setAutoCommit(false);

            for(ParcelAuditDetailsDto rateDetails : rateDetailsList){
                pstmt.setString(1, rateDetails.getSenderCountry());
                pstmt.setString(2, rateDetails.getReceiverCountry());
                pstmt.setString(3, rateDetails.getSenderState());
                pstmt.setString(4, rateDetails.getReceiverState());
                if(rateDetails.getPickupDate() != null){
                    pstmt.setDate(5, new java.sql.Date(rateDetails.getPickupDate().getTime()));
                }else {
                    pstmt.setNull(5, OracleTypes.DATE);
                }
                if(rateDetails.getInvoiceDate() != null){
                    pstmt.setDate(6, new java.sql.Date(rateDetails.getInvoiceDate().getTime()));
                }else{
                    pstmt.setNull(6, OracleTypes.DATE);
                }
                pstmt.setString(7, rateDetails.getInvoiceNumber());
                pstmt.setString(8, rateDetails.getMultiWeightNumber());
                pstmt.setString(9, rateDetails.getTrackingNumber());
                pstmt.setBigDecimal(10, rateDetails.getActualWeight());
                pstmt.setString(11, rateDetails.getPackageWeight());
                pstmt.setString(12, rateDetails.getPackageType());
                pstmt.setString(13, rateDetails.getPackageDimension());
                pstmt.setString(14, rateDetails.getZone());
                pstmt.setString(15, rateDetails.getChargeClassificationCode());
                pstmt.setString(16, rateDetails.getChargeDescription());
                pstmt.setBigDecimal(17, rateDetails.getIncentiveAmount());
                pstmt.setString(18, rateDetails.getNetAmount());
                pstmt.setString(19, rateDetails.getCustomerCode());
                pstmt.setString(20, rateDetails.getShipperNumber());
                if(rateDetails.getInvoiceCreateDate() != null){
                    pstmt.setDate(21, new java.sql.Date(rateDetails.getInvoiceCreateDate().getTime()));
                }else{
                    pstmt.setNull(21, OracleTypes.DATE);
                }
                pstmt.setLong(22, rateDetails.getParentId());
                pstmt.setString(23, rateDetails.getDwFieldInformation());
                pstmt.setLong(24, rateDetails.getInvoiceId());
                pstmt.setLong(25, rateDetails.getCustomerId());
                pstmt.setString(26, rateDetails.getBillOption());
                pstmt.setString(27, rateDetails.getChargeCategoryDetailCode());
                if(rateDetails.getPieces() != null){
                    pstmt.setInt(28, rateDetails.getPieces());
                }else{
                    pstmt.setNull(28, OracleTypes.INTEGER);
                }
                pstmt.setString(29, rateDetails.getChargeDescriptionCode());
                pstmt.setLong(30, rateDetails.getId());
                pstmt.addBatch();
            }

            pstmt.executeBatch();
            con.commit();
        } catch (Exception e) {
            e.printStackTrace();
            try {
                con.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            throw new DaoException("Exception in updateUpsOtherFieldValues", e);
        }finally {
            try {
                if (pstmt != null)
                    pstmt.close();
            } catch (SQLException sqle) {
            }
            try {
                if (con != null)
                    con.close();
            } catch (SQLException sqle) {
            }
        }
    }

    public void updateFedExOtherFieldValues(List<ParcelAuditDetailsDto> rateDetailsList) {
        Connection con = null;
        PreparedStatement pstmt = null;
        String sqlQuery = " UPDATE SHP_EBILL_FDX_RATES_TB ur SET ur.SENDER_COUNTRY = ?, ";
        sqlQuery += " ur.CONSIGNEE_COUNTRY = ?, ur.SENDER_ST = ?, ur.CONSIGNEE_ST = ?, ur.DW_FIELD_INFORMATION = ?, ur.INVOICE_ID = ?, ur.SHIPPER_CODE = ?, ur.INVOICE_NUMBER = ?, ";
        sqlQuery += " ur.TRACKING_NUMBER = ?, ur.BILL_WEIGHT = ?, ur.ACT_WEIGHT = ?, ur.ZONE = ?, ur.SERVICE = ?, ur.BILL_OPT = ?, ur.NET_CHARGES = ?, ur.MISCELLANEOUS5 = ?, ur.BUNDLE_NUMBER = ?, ";
        sqlQuery += " ur.PARENT_ID = ?, ur.PIECES = ?, ur.DIM_LENGTH = ?, ur.WIDTH = ?, ur.HEIGHT = ?, ur.CHARGE_CODE = ?, ur.CUSTOMER_CODE = ?, ur.PICKUP_DATE = ?, ur.BILL_DATE = ?, ur.INV_CREATE_DATE = ?, ur.CUSTOMER_ID = ?, ";
        sqlQuery += " ur.BILLED_DIM_DIVISOR = ? ";
        sqlQuery += " WHERE ur.EBILL_MANIFEST_ID = ? ";

        try {
            con = ServiceLocator.getDatabaseConnection();
            pstmt = con.prepareStatement(sqlQuery);
            con.setAutoCommit(false);

            for(ParcelAuditDetailsDto rateDetails : rateDetailsList){
                pstmt.setString(1, rateDetails.getSenderCountry());
                pstmt.setString(2, rateDetails.getReceiverCountry());
                pstmt.setString(3, rateDetails.getSenderState());
                pstmt.setString(4, rateDetails.getReceiverState());
                pstmt.setString(5, rateDetails.getDwFieldInformation());
                pstmt.setLong(6, rateDetails.getInvoiceId());
                pstmt.setString(7, rateDetails.getShipperNumber());
                pstmt.setString(8, rateDetails.getInvoiceNumber());
                pstmt.setString(9, rateDetails.getTrackingNumber());
                pstmt.setString(10, rateDetails.getPackageWeight());
                pstmt.setBigDecimal(11, rateDetails.getActualWeight());
                pstmt.setString(12, rateDetails.getZone());
                pstmt.setString(13, rateDetails.getChargeDescription());
                pstmt.setString(14, rateDetails.getBillOption());
                pstmt.setString(15, rateDetails.getNetAmount());
                pstmt.setString(16, rateDetails.getMiscellaneous5());
                pstmt.setString(17, rateDetails.getMultiWeightNumber());
                pstmt.setLong(18, rateDetails.getParentId());
                if(rateDetails.getPieces() != null){
                    pstmt.setInt(19, rateDetails.getPieces());
                }else{
                    pstmt.setNull(19, OracleTypes.INTEGER);
                }

                pstmt.setString(20, rateDetails.getDimLength());
                pstmt.setString(21, rateDetails.getDimWidth());
                pstmt.setString(22, rateDetails.getDimHeight());
                pstmt.setString(23, rateDetails.getChargeCode());
                pstmt.setString(24, rateDetails.getCustomerCode());
                if(rateDetails.getPickupDate() != null){
                    pstmt.setDate(25, new java.sql.Date(rateDetails.getPickupDate().getTime()));
                }else {
                    pstmt.setNull(25, OracleTypes.DATE);
                }
                if(rateDetails.getInvoiceDate() != null){
                    pstmt.setDate(26, new java.sql.Date(rateDetails.getInvoiceDate().getTime()));
                }else{
                    pstmt.setNull(26, OracleTypes.DATE);
                }
                if(rateDetails.getInvoiceCreateDate() != null){
                    pstmt.setDate(27, new java.sql.Date(rateDetails.getInvoiceCreateDate().getTime()));
                }else{
                    pstmt.setNull(27, OracleTypes.DATE);
                }
                pstmt.setLong(28, rateDetails.getCustomerId());
                pstmt.setString(29, rateDetails.getBilledDimDivisor());
                pstmt.setLong(30, rateDetails.getId());
                pstmt.addBatch();
            }

            pstmt.executeBatch();
            con.commit();
        } catch (Exception e) {
            e.printStackTrace();
            try {
                con.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            throw new DaoException("Exception in updateFedExOtherFieldValues", e);
        }finally {
            try {
                if (pstmt != null)
                    pstmt.close();
            } catch (SQLException sqle) {
            }
            try {
                if (con != null)
                    con.close();
            } catch (SQLException sqle) {
            }
        }
    }



    public void updateRtrStatus(Long carrierId, String trackingNumber, String status) {
        Connection con = null;
        PreparedStatement pstmt = null;
        String sqlQuery = "";
        if(carrierId == 21L){
            sqlQuery += " UPDATE SHP_EBILL_UPS_RATES_TB ur SET ur.RTR_STATUS = ? WHERE ur.TRACKING_NUMBER = ? ";
        } else if(carrierId == 22L){
            sqlQuery += " UPDATE SHP_EBILL_FDX_RATES_TB ur SET ur.RTR_STATUS = ? WHERE ur.TRACKING_NUMBER = ? ";
        }

        try {
            con = ServiceLocator.getDatabaseConnection();
            pstmt = con.prepareStatement(sqlQuery);
            con.setAutoCommit(false);

            pstmt.setString(1, status);
            pstmt.setString(2, trackingNumber);
            pstmt.executeUpdate();
            con.commit();
        } catch (Exception e) {
            e.printStackTrace();
            try {
                con.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            throw new DaoException("Exception in updateFedExOtherFieldValues", e);
        }finally {
            try {
                if (pstmt != null)
                    pstmt.close();
            } catch (SQLException sqle) {
            }
            try {
                if (con != null)
                    con.close();
            } catch (SQLException sqle) {
            }
        }
    }

    public List<StoreRatingDetailsDto> getRatingJobsByCustomer(Long customerId){
        Connection conn = null;
        CallableStatement cstmt = null;
        ResultSet rs = null;
        List<StoreRatingDetailsDto> storeRatingDetailsDtoList = null;

        try{
            conn = ServiceLocator.getDatabaseConnection();
            cstmt = conn.prepareCall("{ call SHP_RATING_JOBS_BY_CUST_PROC(?,?)}");
            cstmt.setLong(1, customerId);
            cstmt.registerOutParameter(2, OracleTypes.CURSOR);
            cstmt.execute();
            storeRatingDetailsDtoList = new ArrayList<>();
            rs = (ResultSet) cstmt.getObject(2);
            while(rs.next()){
                StoreRatingDetailsDto storeRatingDetailsDto = new StoreRatingDetailsDto();


                storeRatingDetailsDto.setCustomerId(rs.getLong("customer_id"));
                storeRatingDetailsDto.setFromInvoiceDate(rs.getString("from_invoice_date"));
                storeRatingDetailsDto.setToInvoiceDate(rs.getString("to_invoice_date"));
                storeRatingDetailsDto.setFromShipDate(rs.getString("from_ship_date"));
                storeRatingDetailsDto.setToShipDate(rs.getString("to_ship_date"));
                storeRatingDetailsDto.setRateSet(rs.getString("rate_set"));
                storeRatingDetailsDto.setInvoiceRate(rs.getBoolean("invoice_rate"));
                storeRatingDetailsDto.setFlaggedShipments(rs.getBoolean("flagged_shipments"));
                storeRatingDetailsDto.setThresholdValue(rs.getString("threshold_value"));
                storeRatingDetailsDto.setThresholdType(rs.getString("threshold_type"));
                storeRatingDetailsDto.setRate(rs.getBoolean("rate"));
                storeRatingDetailsDto.setInfoLookUp(rs.getBoolean("info_lookup"));
                storeRatingDetailsDto.setStatus(rs.getString("status"));
                storeRatingDetailsDto.setTaskId(rs.getLong("task_id"));
                storeRatingDetailsDto.setCreateDate(rs.getString("create_date"));
                storeRatingDetailsDto.setTotatCount(rs.getLong("total_count"));
                storeRatingDetailsDto.setCompletedCount(rs.getLong("completed_count"));
                storeRatingDetailsDto.setCarrierName(rs.getString("carrier"));

                storeRatingDetailsDtoList.add(storeRatingDetailsDto);

            }
        }catch (Exception e) {
            e.printStackTrace();
            throw new DaoException("getRatingJobsByCustomer", e);
        }finally {
            try {
                if (rs != null)
                    rs.close();
            } catch (SQLException sqle) {
            }
            try {
                if (cstmt != null)
                    cstmt.close();
            } catch (SQLException sqle) {
            }
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException sqle) {
            }
        }
        return storeRatingDetailsDtoList;
    }


    /**
     * This method returns service flag accessroials based on carrier id and module name which needs to send in the XML request
     *
     * @param carrierId
     * @param moduleName
     * @return
     */
    public List<ServiceFlagAccessorialBean> getServiceFlagAcessorials(Long carrierId, String moduleName) {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<ServiceFlagAccessorialBean> beans = new ArrayList<>();

        String sqlQuery = "select Lookup_Id,Module_Name,Column_Name,Lookup_Code,Lookup_Value,Custom_Defined_1,Custom_Defined_2 from shp_lookup_tb where module_name=? and Custom_Defined_2=?";


        try {
            con = ServiceLocator.getDatabaseConnection();
            pstmt = con.prepareStatement(sqlQuery);

            pstmt.setString(1, moduleName);
            pstmt.setString(2, String.valueOf(carrierId));
            rs = pstmt.executeQuery();

            while ((rs.next())) {
                ServiceFlagAccessorialBean bean = new ServiceFlagAccessorialBean();

                bean.setLookUpId(rs.getLong("Lookup_Id"));
                bean.setModuleName(rs.getString("Module_Name"));
                bean.setColumnName(rs.getString("Column_Name"));
                bean.setLookUpCode(rs.getString("Lookup_Code"));
                bean.setLookUpValue(rs.getString("Lookup_Value"));
                bean.setCustomDefined1(rs.getString("Custom_Defined_1"));
                bean.setCustomDefined2(rs.getString("Custom_Defined_2"));

                beans.add(bean);
            }


        } catch (SQLException e) {
            e.printStackTrace();

        } catch (ServiceLocatorException e) {
            log.error(e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                if (rs != null)
                    rs.close();
                if (pstmt != null)
                    pstmt.close();
                if (con != null)
                    con.close();
            } catch (SQLException sqle) {
                log.error(sqle.getMessage());
                //sqle.printStackTrace();
            }
        }
        return beans;
    }

    /**
     * This method returns fedex rates information for that parent id.
     *
     * @param parentId
     * @param tracking_number
     * @param pickUpDate
     * @return
     */
    public List<RatedChargeDetailsDto> getRatedChargeAmountforNonUPS(Long parentId, String tracking_number, String pickUpDate) {
        Connection conn = null;
        CallableStatement cstmt = null;
        ResultSet rs = null;
        List<RatedChargeDetailsDto> ratedChargeDetailsDtoList = null;
        try {
            conn = ServiceLocator.getDatabaseConnection();
            cstmt = conn.prepareCall("{ call SHP_GET_NON_UPS_RATED_AMT_PRO(?,?,?,?)}");
            cstmt.setLong(1, parentId);
            cstmt.setString(2, tracking_number);
            cstmt.setString(3, pickUpDate);
            cstmt.registerOutParameter(4, OracleTypes.CURSOR);
            cstmt.execute();

            ratedChargeDetailsDtoList = new ArrayList<>();
            rs = (ResultSet) cstmt.getObject(4);
            while (rs.next()) {
                RatedChargeDetailsDto ratedChargeDetailsDto = new RatedChargeDetailsDto();
                ratedChargeDetailsDto.setId(rs.getLong("ID"));
                ratedChargeDetailsDto.setChargeClassificationCode(rs.getString("charge_classification_code"));
                if (ratedChargeDetailsDto.getChargeClassificationCode() != null) {
                    try {
                        String[] dwFieldInfo = ratedChargeDetailsDto.getChargeClassificationCode().split(",");
                        if (dwFieldInfo != null && dwFieldInfo.length > 0) {
                            ratedChargeDetailsDto.setChargeClassificationCode(dwFieldInfo[1].trim());
                            ratedChargeDetailsDto.setChargeDescriptionCode(dwFieldInfo[2].trim());
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                //ratedChargeDetailsDto.setChargeDescriptionCode(rs.getString("charge_description_code"));
                ratedChargeDetailsDto.setBilledAmount(rs.getBigDecimal("net_amount"));
                ratedChargeDetailsDto.setRatedAmount(rs.getBigDecimal("rtr_amount"));
                ratedChargeDetailsDto.setDimDivisor(rs.getBigDecimal("DIM_DIVISOR"));
                ratedChargeDetailsDto.setShipperCategory(rs.getString("SHIPPER_CATEGORY"));
                ratedChargeDetailsDto.setRatedWeight(rs.getBigDecimal("RATED_WEIGHT"));
                ratedChargeDetailsDto.setContractName(rs.getString("CONTRACT_NAME"));
                ratedChargeDetailsDto.setFuelTablePercentage(rs.getBigDecimal("FUEL_TABLE_PERC"));
                ratedChargeDetailsDto.setRatedBaseDiscount(rs.getBigDecimal("RATED_BASE_DISCOUNT"));
                ratedChargeDetailsDto.setRatedEarnedDiscount(rs.getBigDecimal("RATED_EARNED_DISCOUNT"));
                ratedChargeDetailsDto.setRatedMinMaxAdjustment(rs.getBigDecimal("RATED_MIN_MAX_ADJ"));
                ratedChargeDetailsDto.setRatedFuelSurchargeDiscount(rs.getBigDecimal("RATED_FUEL_SURCHARGE_DISC"));
                ratedChargeDetailsDto.setRatedCustomFuelSurchargeDiscount(rs.getBigDecimal("RATED_CUST_FUEL_SURCHARGE_DISC"));
                ratedChargeDetailsDto.setRatedGrossFuel(rs.getBigDecimal("RATED_GROSS_FUEL"));
                ratedChargeDetailsDto.setResidentialSurchargeDiscount(rs.getBigDecimal("RES_SURCHARGE_DSC"));
                ratedChargeDetailsDto.setResidentialSurchargeDiscountPercentage(rs.getBigDecimal("RES_SURCHARGE_DSC_PERC"));
                ratedChargeDetailsDto.setOtherDiscount1(rs.getBigDecimal("OTHER_DSC_1"));
                ratedChargeDetailsDto.setOtherDiscount2(rs.getBigDecimal("OTHER_DSC_2"));
                ratedChargeDetailsDto.setOtherDiscount3(rs.getBigDecimal("OTHER_DSC_3"));
                ratedChargeDetailsDto.setAccessorial1(rs.getBigDecimal("ACCESSORIAL_1"));
                ratedChargeDetailsDto.setAccessorial2(rs.getBigDecimal("ACCESSORIAL_2"));
                ratedChargeDetailsDto.setAccessorial3(rs.getBigDecimal("ACCESSORIAL_3"));
                ratedChargeDetailsDto.setAccessorial4(rs.getBigDecimal("ACCESSORIAL_4"));
                ratedChargeDetailsDto.setDeliveryAreaSurchargeDiscount(rs.getBigDecimal("RATED_DAS_DSC"));
                ratedChargeDetailsDto.setAccessorial1Code(rs.getString("ACCESSORIAL_1_CODE"));
                ratedChargeDetailsDto.setAccessorial2Code(rs.getString("ACCESSORIAL_2_CODE"));
                ratedChargeDetailsDto.setAccessorial3Code(rs.getString("ACCESSORIAL_3_CODE"));
                ratedChargeDetailsDto.setAccessorial4Code(rs.getString("ACCESSORIAL_4_CODE"));
                ratedChargeDetailsDto.setFreightCharge(rs.getBigDecimal("FRT_CHARGE"));
                ratedChargeDetailsDto.setFuelSurcharge(rs.getBigDecimal("FSC_CHARGE"));
                ratedChargeDetailsDtoList.add(ratedChargeDetailsDto);
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            throw new RuntimeException("Exception in fetching rate details in getRatedChargeAmount -- > " + sqle.getStackTrace());
        } catch (ServiceLocatorException sle) {
            throw new RuntimeException("Exception in getting connection in getRatedChargeAmount -- > " + sle.getStackTrace());
        } finally {
            try {
                if (rs != null)
                    rs.close();
            } catch (SQLException sqle) {
            }
            try {
                if (cstmt != null)
                    cstmt.close();
            } catch (SQLException sqle) {
            }
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException sqle) {
            }
        }
        return ratedChargeDetailsDtoList;
    }

    public void updateRtrStatus(Long carrierId, String trackingNumber, String status, Date pickUpDate) {
        Connection con = null;
        PreparedStatement pstmt = null;
        String sqlQuery = "";
        if (carrierId == 21L) {
            sqlQuery += " UPDATE SHP_EBILL_UPS_RATES_TB ur SET ur.RTR_STATUS = ? WHERE ur.TRACKING_NUMBER = ? and ur.Pickup_Date=? ";
        } else if (carrierId == 22L) {
            sqlQuery += " UPDATE SHP_EBILL_FDX_RATES_TB ur SET ur.RTR_STATUS = ? WHERE ur.TRACKING_NUMBER = ? and ur.Pickup_Date=? ";
        }

        try {
            con = ServiceLocator.getDatabaseConnection();
            pstmt = con.prepareStatement(sqlQuery);
            con.setAutoCommit(false);

            pstmt.setString(1, status);
            pstmt.setString(2, trackingNumber);
            pstmt.setDate(3, pickUpDate);

            pstmt.executeUpdate();
            con.commit();
        } catch (Exception e) {
            e.printStackTrace();
            try {
                con.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            throw new DaoException("Exception in updateFedExOtherFieldValues", e);
        } finally {
            try {
                if (pstmt != null)
                    pstmt.close();
            } catch (SQLException sqle) {
            }
            try {
                if (con != null)
                    con.close();
            } catch (SQLException sqle) {
            }
        }
    }

    public void saveAccInfo(List<AccessorialDto> dtos, Long parentId, long carrierId) {
        Connection con = null;
        PreparedStatement pstmt = null;
        String tbName;
        String seqName;
        if (carrierId == 21) {
            tbName = "SHP_UPS_ACC_AND_DIS_TB";
            seqName = "SHP_UPS_ACC_AND_DIS_S.NEXTVAL";
        } else {
            tbName = "SHP_FDX_ACC_AND_DIS_TB";
            seqName = "SHP_FDX_ACC_AND_DIS_S.NEXTVAL";
        }

        deleteAccInfoByParentId(parentId, tbName);

        String sqlQuery = " INSERT\n" +
                "    INTO " + tbName + "\n" +
                "      (\n" +
                "        ACC_ID,\n" +
                "        PARENT_ID,\n" +
                "        ACC_TYPE,\n" +
                "        ACC_CODE,\n" +
                "        RTR_AMOUNT,\n" +
                "        CREATE_DATE,\n" +
                "        LAST_UPDATED_DATE,\n" +
                "        NAME\n" +
                "      )\n" +
                "      VALUES\n" +
                "      (\n" +
                "        " + seqName + ",\n" +
                "        ?,\n" +
                "        ?,\n" +
                "        ?,\n" +
                "        ?,\n" +
                "        sysdate,\n" +
                "        sysdate,\n" +
                "        ?\n" +
                "      ) ";


        try {
            con = ServiceLocator.getDatabaseConnection();
            pstmt = con.prepareStatement(sqlQuery);
            con.setAutoCommit(false);
            for (AccessorialDto dto : dtos) {
                pstmt.setLong(1, dto.getParentId());
                pstmt.setString(2, dto.getType());
                pstmt.setString(3, dto.getCode());
                pstmt.setBigDecimal(4, dto.getRtrAmount());
                pstmt.setString(5, dto.getName());
                pstmt.addBatch();
            }

            pstmt.executeBatch();
            con.commit();
        } catch (Exception e) {
            e.printStackTrace();
            try {
                con.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            throw new DaoException("Exception in saveAccInfo", e);
        } finally {
            try {
                if (pstmt != null)
                    pstmt.close();
            } catch (SQLException sqle) {
            }
            try {
                if (con != null)
                    con.close();
            } catch (SQLException sqle) {
            }
        }
    }

    public void deleteAccInfoByParentId(Long parentId, String tbName) {
        Connection con = null;
        PreparedStatement pstmt = null;
        String sqlQuery = " DELETE FROM " + tbName + " WHERE PARENT_ID=? ";


        try {
            con = ServiceLocator.getDatabaseConnection();
            pstmt = con.prepareStatement(sqlQuery);
            con.setAutoCommit(false);

            pstmt.setLong(1, parentId);
            pstmt.executeQuery();
            con.commit();
        } catch (Exception e) {
            e.printStackTrace();
            try {
                con.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            throw new DaoException("Exception in deleteAccInfoByParentId", e);
        } finally {
            try {
                if (pstmt != null)
                    pstmt.close();
            } catch (SQLException sqle) {
            }
            try {
                if (con != null)
                    con.close();
            } catch (SQLException sqle) {
            }
        }
    }

    public List<AccessorialDto> getRatesForPrevParentIds(String trackingNumber, Long parentId, String returnFlag, long carrierId, java.util.Date pickupDate) {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String ratesTbName;
        String accTbName;
        String idColumnName;
        if (carrierId == 21) {
            ratesTbName = "Shp_Ebill_Ups_Rates_Tb";
            idColumnName = " Ebill_Gff_Id ";
            accTbName = "SHP_UPS_ACC_AND_DIS_TB";
        } else {
            ratesTbName = "Shp_Ebill_Fdx_Rates_Tb";
            accTbName = "SHP_FDX_ACC_AND_DIS_TB";
            idColumnName = " Ebill_Manifest_Id ";
        }
        StringBuilder sqlQuery = new StringBuilder();
        sqlQuery.append(" select " + idColumnName + " as id, Parent_Id,Rtr_Amount,acc_Code,RATED_BASE_DISCOUNT,RATED_EARNED_DISCOUNT" +
                ",RATED_MIN_MAX_ADJ,RATED_FUEL_SURCHARGE_DISC,RATED_CUST_FUEL_SURCHARGE_DISC,RATED_DAS_DSC,RES_SURCHARGE_DSC, RATED_GROSS_FUEL" +
                " from " + ratesTbName + "" +
                " where Tracking_Number=? and Parent_Id < ? and return_flag=? ");

        if (carrierId == 22 && pickupDate != null) {
            sqlQuery.append(" and trunc(Pickup_Date) = ? ");
        }
        List<AccessorialDto> dtos = new ArrayList<>();
        List<String> parentIds = new ArrayList<>();

        try {
            con = ServiceLocator.getDatabaseConnection();
            pstmt = con.prepareStatement(sqlQuery.toString());


            pstmt.setString(1, trackingNumber);
            pstmt.setLong(2, parentId);
            pstmt.setString(3, returnFlag);
            if (carrierId == 22 && pickupDate != null) {
                pstmt.setDate(4, new Date(pickupDate.getTime()));
            }

            rs = pstmt.executeQuery();

            while (rs.next()) {

                AccessorialDto dto = new AccessorialDto();

                dto.setEbillGffId(rs.getLong("id"));
                dto.setParentId(rs.getLong("Parent_Id"));
                dto.setParentId(rs.getLong("Parent_Id"));
                dto.setRtrAmount(rs.getBigDecimal("Rtr_Amount"));
                dto.setCode(rs.getString("acc_Code"));

                if (dto.getEbillGffId().compareTo(dto.getParentId()) == 0) {
                    dto.setBaseDis(rs.getBigDecimal("RATED_BASE_DISCOUNT"));
                    dto.setEarnedDis(rs.getBigDecimal("RATED_EARNED_DISCOUNT"));
                    dto.setMinMaxDis(rs.getBigDecimal("RATED_MIN_MAX_ADJ"));
                    dto.setFuleSurDis(rs.getBigDecimal("RATED_FUEL_SURCHARGE_DISC"));
                    dto.setCustFuleSurDis(rs.getBigDecimal("RATED_CUST_FUEL_SURCHARGE_DISC"));
                    dto.setDasDis(rs.getBigDecimal("RATED_DAS_DSC"));
                    dto.setResDis(rs.getBigDecimal("RES_SURCHARGE_DSC"));
                    dto.setRatedGrossFuel(rs.getBigDecimal("RATED_GROSS_FUEL"));
                }
                dtos.add(dto);

                if (!parentIds.contains(dto.getParentId()))
                    parentIds.add(String.valueOf(dto.getParentId()));
            }

            String parentIdsInResult = String.join(",", parentIds);
            if (parentIdsInResult != null && parentIdsInResult.length() > 0) {
                String sqlAccQuery = " select Parent_Id,Acc_Code,Rtr_Amount, ACC_TYPE, NAME from " + accTbName + " " +
                        " where Parent_Id in (" + parentIdsInResult + ") ";

                pstmt = con.prepareStatement(sqlAccQuery);

                rs = pstmt.executeQuery();

                while (rs.next()) {

                    AccessorialDto dto = new AccessorialDto();

                    dto.setParentId(rs.getLong("Parent_Id"));
                    dto.setRtrAmount(rs.getBigDecimal("Rtr_Amount"));
                    dto.setCode(rs.getString("Acc_Code"));
                    dto.setType(rs.getString("ACC_TYPE"));
                    dto.setName(rs.getString("NAME"));
                    dtos.add(dto);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();

            throw new DaoException("Exception in getRatesForPrevParentIds ", e);
        } finally {
            try {
                if (pstmt != null)
                    pstmt.close();
            } catch (SQLException sqle) {
                sqle.printStackTrace();
            }
            try {
                if (con != null)
                    con.close();
            } catch (SQLException sqle) {
                sqle.printStackTrace();
            }
            try {
                if (rs != null)
                    rs.close();
            } catch (SQLException sqle) {
                sqle.printStackTrace();
            }
        }
        return dtos;
    }

}
