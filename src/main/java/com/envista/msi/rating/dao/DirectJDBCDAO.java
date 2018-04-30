package com.envista.msi.rating.dao;

import com.envista.msi.api.web.rest.dto.rtr.ParcelAuditRequestResponseLog;
import com.envista.msi.api.web.rest.dto.rtr.ParcelRateDetailsDto;
import com.envista.msi.rating.ServiceLocator;
import com.envista.msi.rating.ServiceLocatorException;
import com.envista.msi.rating.bean.RatingQueueBean;

import java.math.BigDecimal;
import java.sql.*;

public class DirectJDBCDAO {

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

    public void updateInvoiceAmountByIds(String entityIds, String userName, String rtrStatus, Long carrierId){
        Connection conn = null;
        PreparedStatement prstmt = null;
        PreparedStatement aprstmt = null;

        String lUpdateQuery = "";
        String aUpdateQuery = "";
        if(carrierId==21){
            lUpdateQuery = " UPDATE SHP_EBILL_GFF_TB SET RTR_STATUS = '"+rtrStatus+"', RTR_AMOUNT=NET_AMOUNT, LAST_UPDATE_DATE=sysdate, " +
                    " LAST_UPDATE_USER = '"+userName+"' WHERE EBILL_GFF_ID in ("+entityIds+")";
            aUpdateQuery = " UPDATE ARC_EBILL_GFF_TB SET RTR_STATUS = '"+rtrStatus+"', RTR_AMOUNT=NET_AMOUNT, LAST_UPDATE_DATE=sysdate, " +
                    " LAST_UPDATE_USER = '"+userName+"' WHERE EBILL_GFF_ID in ("+entityIds+")";
        }else{
            lUpdateQuery = " UPDATE SHP_EBILL_MANIFEST_TB SET RTR_STATUS = '"+rtrStatus+"', RTR_AMOUNT=NET_CHARGES, LAST_UPDATE_DATE=sysdate, " +
                    " LAST_UPDATE_USER = '"+userName+"' WHERE EBILL_MANIFEST_ID in ("+entityIds+")";
            aUpdateQuery = " UPDATE ARC_EBILL_MANIFEST_TB SET RTR_STATUS = '"+rtrStatus+"', RTR_AMOUNT=NET_CHARGES, LAST_UPDATE_DATE=sysdate, " +
                    " LAST_UPDATE_USER = '"+userName+"' WHERE EBILL_MANIFEST_ID in ("+entityIds+")";
        }

        try {
            conn = ServiceLocator.getDatabaseConnection();
            prstmt = conn.prepareStatement(lUpdateQuery);
            prstmt.executeUpdate();
            aprstmt = conn.prepareStatement(aUpdateQuery);
            aprstmt.executeQuery();

        }catch (SQLException sqle) {
            System.out.println("Exception in updateInvoiceAmountByIds -- > "+sqle.getStackTrace());
        }  catch (ServiceLocatorException sle) {
            System.out.println("Exception in updateInvoiceAmountByIds -- > "+sle.getStackTrace());
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
    public void saveParcelAuditRequestAndResponseLog(ParcelAuditRequestResponseLog requestResponseLog){
        Connection conn = null;
        CallableStatement cstmt =null;
        try{
            conn = ServiceLocator.getDatabaseConnection();
            cstmt = conn.prepareCall("{ call SHP_FRT_SAVE_XML_RATING_PROC(?,?,?,?,?,?,?,?)}");
            cstmt.setString(1, requestResponseLog.getTableName());
            cstmt.setString(2, requestResponseLog.getEntityIds());
            cstmt.setString(3, requestResponseLog.getRequestXml1());
            cstmt.setString(4, requestResponseLog.getRequestXml2());
            cstmt.setString(5, requestResponseLog.getResponseXml1());
            cstmt.setString(6, requestResponseLog.getResponseXml2());
            cstmt.setString(7, requestResponseLog.getResponseXml3());
            cstmt.setString(8, requestResponseLog.getCreateUser());
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
    public void updateShipmentRateDetails(String referenceTableName, String entityIds, String userName, ParcelRateDetailsDto rateDetails){
        Connection conn = null;
        CallableStatement cstmt =null;
        try{
            conn = ServiceLocator.getDatabaseConnection();
            cstmt = conn.prepareCall("{ call SHP_SAVE_RATE_DETAILS_PROC(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
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
            cstmt = conn.prepareCall("{ call SHP_RATE_UPDATE_ACC_PROC(?,?,?,?,?,?,?,?)}");
            cstmt.setString(1, referenceTableName);
            cstmt.setString(2, entityIds);
            cstmt.setString(3, userName);
            cstmt.setString(4, rateDetails != null && rateDetails.getContractName() != null ? rateDetails.getContractName() : "");
            cstmt.setString(5, rateDetails != null && rateDetails.getShipperCategory() != null ? rateDetails.getShipperCategory() : "");
            cstmt.setBigDecimal(6, rateDetails != null && rateDetails.getAccessorial1() != null ? rateDetails.getAccessorial1() : new BigDecimal("0"));
            cstmt.setBigDecimal(7, rateDetails != null && rateDetails.getAccessorial2() != null ? rateDetails.getAccessorial2() : new BigDecimal("0"));
            cstmt.setBigDecimal(8, rateDetails != null && rateDetails.getAccessorial3() != null ? rateDetails.getAccessorial3() : new BigDecimal("0"));
            cstmt.executeUpdate();
        }catch (SQLException sqle) {
            System.out.println("Exception in updateAccessorialShipmentRateDetails -- > "+sqle.getStackTrace());
        }  catch (ServiceLocatorException sle) {
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
}
