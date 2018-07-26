package com.envista.msi.rating.dao;

import com.envista.msi.api.web.rest.dto.rtr.*;
import com.envista.msi.rating.ServiceLocator;
import com.envista.msi.rating.ServiceLocatorException;
import com.envista.msi.rating.entity.ParcelRatingInputCriteriaDto;
import oracle.jdbc.OracleTypes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.sql.*;
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
            cstmt = conn.prepareCall("{ call SHP_SAVE_RATE_DETAILS_PROC(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
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
            cstmt.setString(22, rateDetails != null && rateDetails.getZone() != null ? rateDetails.getZone() : null);
            cstmt.executeUpdate();

        }catch (SQLException sqle) {;
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
            cstmt = conn.prepareCall("{ call SHP_UPDATE_OTHER_DSC_PROC(?,?,?,?,?,?,?,?,?)}");
            cstmt.setString(1, referenceTableName);
            cstmt.setString(2, entityIds);
            cstmt.setString(3, userName);
            cstmt.setString(4, rateDetails != null && rateDetails.getContractName() != null ? rateDetails.getContractName() : "");
            cstmt.setString(5, rateDetails != null && rateDetails.getShipperCategory() != null ? rateDetails.getShipperCategory() : "");
            cstmt.setBigDecimal(6, rateDetails != null && rateDetails.getOtherDiscount1() != null ? rateDetails.getOtherDiscount1() : new BigDecimal("0"));
            cstmt.setBigDecimal(7, rateDetails != null && rateDetails.getOtherDiscount2() != null ? rateDetails.getOtherDiscount2() : new BigDecimal("0"));
            cstmt.setBigDecimal(8, rateDetails != null && rateDetails.getOtherDiscount3() != null ? rateDetails.getOtherDiscount3() : new BigDecimal("0"));
            cstmt.setString(9, rateDetails != null && rateDetails.getFlagged() != null ? rateDetails.getFlagged() : "");
            cstmt.setString(9, rateDetails != null && rateDetails.getZone() != null ? rateDetails.getZone() : null);
            cstmt.executeUpdate();
        }catch (SQLException sqle) {
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
            cstmt = conn.prepareCall("{ call SHP_RATE_UPDATE_ACC_PROC(?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
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
            cstmt.setString(14, rateDetails != null && rateDetails.getZone() != null ? rateDetails.getZone() : null);
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
                System.out.println("Exception in loadMappedARChargeCodes -- > "+sqle.getStackTrace());
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

    public List<RatedChargeDetailsDto> getRatedChargeAmount(Long parentId){
        Connection conn = null;
        CallableStatement cstmt = null;
        ResultSet rs = null;
        List<RatedChargeDetailsDto> ratedChargeDetailsDtoList = null;
        try{
            conn = ServiceLocator.getDatabaseConnection();
            cstmt = conn.prepareCall("{ call SHP_GET_UPS_RATED_AMOUNT_PROC(?,?)}");
            cstmt.setLong(1, parentId);
            cstmt.registerOutParameter(2, OracleTypes.CURSOR);
            cstmt.execute();

            ratedChargeDetailsDtoList = new ArrayList<>();
            rs = (ResultSet) cstmt.getObject(2);
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
            cstmt = conn.prepareCall("{ call SHP_SAVE_ALL_RATE_DETAILS_PROC(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
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
            cstmt.setString(30, rateDetails != null && rateDetails.getZone() != null ? rateDetails.getZone() : "");
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
        System.out.println("Loading Invoices");
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
                    liveSqlQuery += " WHERE INVOICE_ID IN ( ";
                    liveSqlQuery += " SELECT invoice_id FROM SHP_EBILL_INVOICE_TB ";
                    liveSqlQuery += " WHERE inv_contract_number IN (SELECT contract_number FROM SHP_EBILL_CONTRACT_TB ";
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
            System.out.println("Sql Query --> " + liveSqlQuery + " UNION " + archiveQuery);
            conn = ServiceLocator.getDatabaseConnection();
            ps = conn.prepareStatement(liveSqlQuery + " UNION " + archiveQuery);
            rs = ps.executeQuery();

            System.out.println("Got Invoices-->");
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
        sqlQuery += " ur.INV_CREATE_DATE = ?, ur.PARENT_ID = ?, ur.DW_FIELD_INFORMATION = ?, ur.INVOICE_ID = ?, ur.CUSTOMER_ID = ?, ur.BILL_OPTION_CODE = ?, ur.CHARGE_CATEGORY_DETAIL_CODE = ? ";
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
                if(rateDetails.getDeliveryDate() != null){
                    pstmt.setDate(5, new java.sql.Date(rateDetails.getDeliveryDate().getTime()));
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
                pstmt.setString(15, rateDetails.getChargeDescriptionCode());
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
                pstmt.setLong(28, rateDetails.getId());
                pstmt.addBatch();
            }

            pstmt.executeBatch();
            con.commit();
        } catch (Exception e) {
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
                if(rateDetails.getDeliveryDate() != null){
                    pstmt.setDate(25, new java.sql.Date(rateDetails.getDeliveryDate().getTime()));
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
}
