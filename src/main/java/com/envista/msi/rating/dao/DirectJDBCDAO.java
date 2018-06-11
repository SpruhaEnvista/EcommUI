package com.envista.msi.rating.dao;

import com.envista.msi.api.dao.DaoException;
import com.envista.msi.api.web.rest.dto.rtr.*;
import com.envista.msi.api.web.rest.util.audit.parcel.ParcelAuditConstant;
import com.envista.msi.rating.ServiceLocator;
import com.envista.msi.rating.ServiceLocatorException;
import oracle.jdbc.OracleTypes;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DirectJDBCDAO {

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
            System.out.println("Exception in updateRTRInvoiceAmount -- > "+sqle.getStackTrace());
        }  catch (ServiceLocatorException sle) {
            System.out.println("Exception in updateRTRInvoiceAmount -- > "+sle.getStackTrace());
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
            System.out.println("Exception in updateRtrStatusByIds -- > " + sqle.getStackTrace());
        }  catch (ServiceLocatorException sle) {
            System.out.println("Exception in updateRtrStatusByIds -- > " + sle.getStackTrace());
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
            System.out.println("Exception in saveParcelAuditRequestAndResponseLog -- > "+sqle.getStackTrace());
        }  catch (ServiceLocatorException sle) {
            System.out.println("Exception in saveParcelAuditRequestAndResponseLog -- > "+sle.getStackTrace());
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
            System.out.println("Exception in updateInvoiceRtrStatus -- > "+sqle.getStackTrace());
        }  catch (ServiceLocatorException sle) {
            System.out.println("Exception in updateInvoiceRtrStatus -- > "+sle.getStackTrace());
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
            cstmt = conn.prepareCall("{ call SHP_SAVE_RATE_DETAILS_PROC(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
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
            cstmt.executeUpdate();

        }catch (SQLException sqle) {
            System.out.println("Exception in updateShipmentRateDetails -- > "+sqle.getStackTrace());
        }  catch (ServiceLocatorException sle) {
            System.out.println("Exception in updateShipmentRateDetails -- > "+sle.getStackTrace());
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
            cstmt = conn.prepareCall("{ call SHP_UPDATE_OTHER_DSC_PROC(?,?,?,?,?,?,?,?)}");
            cstmt.setString(1, referenceTableName);
            cstmt.setString(2, entityIds);
            cstmt.setString(3, userName);
            cstmt.setString(4, rateDetails != null && rateDetails.getContractName() != null ? rateDetails.getContractName() : "");
            cstmt.setString(5, rateDetails != null && rateDetails.getShipperCategory() != null ? rateDetails.getShipperCategory() : "");
            cstmt.setBigDecimal(6, rateDetails != null && rateDetails.getOtherDiscount1() != null ? rateDetails.getOtherDiscount1() : new BigDecimal("0"));
            cstmt.setBigDecimal(7, rateDetails != null && rateDetails.getOtherDiscount2() != null ? rateDetails.getOtherDiscount2() : new BigDecimal("0"));
            cstmt.setBigDecimal(8, rateDetails != null && rateDetails.getOtherDiscount3() != null ? rateDetails.getOtherDiscount3() : new BigDecimal("0"));
            cstmt.executeUpdate();
        }catch (SQLException sqle) {
            System.out.println("Exception in updateOtherDiscountShipmentRateDetails -- > "+sqle.getStackTrace());
        }  catch (ServiceLocatorException sle) {
            System.out.println("Exception in updateOtherDiscountShipmentRateDetails -- > "+sle.getStackTrace());
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
            cstmt = conn.prepareCall("{ call SHP_RATE_UPDATE_ACC_PROC(?,?,?,?,?,?,?,?,?,?,?,?,?)}");
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
            cstmt.executeUpdate();
        }catch (SQLException sqle) {
            sqle.printStackTrace();
            System.out.println("Exception in updateAccessorialShipmentRateDetails -- > "+sqle.getStackTrace());
        }  catch (ServiceLocatorException sle) {
            sle.printStackTrace();
            System.out.println("Exception in updateAccessorialShipmentRateDetails -- > "+sle.getStackTrace());
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
            System.out.println("Exception in fetching rate details in getRatedChargeAmount-- > "+sqle.getStackTrace());
            throw new RuntimeException("Exception in fetching rate details in getRatedChargeAmount -- > "+sqle.getStackTrace());
        }  catch (ServiceLocatorException sle) {
            System.out.println("Exception in getting connection in getRatedChargeAmount -- > "+sle.getStackTrace());
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
            cstmt = conn.prepareCall("{ call SHP_SAVE_ALL_RATE_DETAILS_PROC(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
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
            cstmt.executeUpdate();

        }catch (SQLException sqle) {
            System.out.println("Exception in updateAllShipmentRateDetails -- > " + sqle.getStackTrace());
        }  catch (ServiceLocatorException sle) {
            System.out.println("Exception in updateAllShipmentRateDetails -- > " + sle.getStackTrace());
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

    public List<Long> loadInvoiceIds(String fromDate, String toDate, String customerId, String invoiceIds, int limit, String rateTo){
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
            System.out.println("Exception in loadInvoiceIds -- > "+sqle.getStackTrace());
        }  catch (ServiceLocatorException sle) {
            System.out.println("Exception in loadInvoiceIds -- > "+sle.getStackTrace());
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
}
