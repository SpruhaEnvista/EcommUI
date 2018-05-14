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
    public void updateShipmentRateDetails(String referenceTableName, Long entityId, String userName, ParcelRateDetailsDto rateDetails) {
        Connection conn = null;
        Statement st = null;
        try{
            conn = ServiceLocator.getDatabaseConnection();
            StringBuilder sqlQuery = new StringBuilder();
            String dimDivisor = (rateDetails.getDimDivisor() != null ? rateDetails.getDimDivisor().toString() : "0.0");
            String ratedWeight = (rateDetails.getRatedWeight() != null ? rateDetails.getRatedWeight().toString() : "0.0");
            String fuelTablePercentage = (rateDetails.getFuelTablePercentage() != null ? rateDetails.getFuelTablePercentage().toString() : "0.0");
            String ratedBaseDiscount = (rateDetails.getRatedBaseDiscount() != null ? rateDetails.getRatedBaseDiscount().toString() : "0.0");
            String ratedEarnedDiscount = (rateDetails.getRatedEarnedDiscount() != null ? rateDetails.getRatedEarnedDiscount().toString() : "0.0");
            String ratedMinMaxAdj = (rateDetails.getRatedMinMaxAdjustment() != null ? rateDetails.getRatedMinMaxAdjustment().toString() : "0.0");
            String fuelSurchargeDsc = (rateDetails.getRatedFuelSurchargeDiscount() != null ? rateDetails.getRatedFuelSurchargeDiscount().toString() : "0.0");
            String custFuelSurchargeDsc = (rateDetails.getRatedCustomFuelSurchargeDiscount() != null ? rateDetails.getRatedCustomFuelSurchargeDiscount().toString() : "0.0" );
            String ratedGrossFuel = (rateDetails.getRatedGrossFuel() != null ? rateDetails.getRatedGrossFuel().toString() : "0.0");
            String resDsc = (rateDetails.getResidentialSurchargeDiscount() != null ? rateDetails.getResidentialSurchargeDiscount().toString() : "0.0");
            String resDscPerc = (rateDetails.getResidentialSurchargeDiscountPercentage() != null ? rateDetails.getResidentialSurchargeDiscountPercentage().toString() : "0.0");
            String dasDsc = (rateDetails.getDeliveryAreaSurchargeDiscount() != null ? rateDetails.getDeliveryAreaSurchargeDiscount().toString() : "0.0");
            String rtrAmount = (rateDetails.getRtrAmount() != null ? rateDetails.getRtrAmount().toString() : "0.0");

            if(ParcelAuditConstant.EBILL_MANIFEST_TABLE_NAME.equalsIgnoreCase(referenceTableName)) {
                sqlQuery.append(" MERGE INTO SHP_AUDIT_RATE_DETAILS_TB ar USING ")
                        .append(" (SELECT " + entityId + " AS manifest_id ")
                        .append(" FROM DUAL ")
                        .append(" ) b ON (ar.EBILL_MANIFEST_ID = b.manifest_id) ")
                        .append(" WHEN MATCHED THEN ")
                        .append(" UPDATE ")
                        .append(" SET LAST_UPDATE_USER = " + userName + ", ")
                        .append(" LAST_UPDATE_DATE = SYSDATE, ")
                        .append(" DIM_DIVISOR    = " + dimDivisor + ", ")
                        .append(" SHIPPER_CATEGORY = " + rateDetails.getShipperCategory() + ", ")
                        .append(" RATED_WEIGHT  = " + ratedWeight + ", ")
                        .append(" CONTRACT_NAME  = " + rateDetails.getContractName() + ", ")
                        .append(" FUEL_TABLE_PERC = " + fuelTablePercentage + ", ")
                        .append(" RATED_BASE_DISCOUNT = " + ratedBaseDiscount + ", ")
                        .append(" RATED_EARNED_DISCOUNT = " + ratedEarnedDiscount + ", ")
                        .append(" RATED_MIN_MAX_ADJ = " + ratedMinMaxAdj + ", ")
                        .append(" RATED_FUEL_SURCHARGE_DISC = " + fuelSurchargeDsc + ", ")
                        .append(" RATED_CUST_FUEL_SURCHARGE_DISC = " + custFuelSurchargeDsc + ", ")
                        .append(" RATED_GROSS_FUEL = " + ratedGrossFuel + ", ")
                        .append(" RES_SURCHARGE_DSC = " + resDsc + ", ")
                        .append(" RES_SURCHARGE_DSC_PERC = " + resDscPerc + ", ")
                        .append(" RATED_DAS_DSC = " + dasDsc + ", ")
                        .append(" RTR_AMOUNT = " + rtrAmount + ", ")
                        .append(" RTR_STATUS = " + rateDetails.getRtrStatus())
                        .append(" WHEN NOT MATCHED THEN ")
                        .append(" INSERT ")
                        .append(" (AUDIT_RATE_DETAILS_ID, EBILL_MANIFEST_ID, CREATE_DATE, CREATE_USER, LAST_UPDATE_USER, LAST_UPDATE_DATE, ")
                        .append(" DIM_DIVISOR, SHIPPER_CATEGORY, RATED_WEIGHT, CONTRACT_NAME, FUEL_TABLE_PERC, RATED_BASE_DISCOUNT, RATED_EARNED_DISCOUNT, ")
                        .append(" RATED_MIN_MAX_ADJ, RATED_FUEL_SURCHARGE_DISC, RATED_CUST_FUEL_SURCHARGE_DISC, RATED_GROSS_FUEL, RES_SURCHARGE_DSC, ")
                        .append(" RES_SURCHARGE_DSC_PERC, RATED_DAS_DSC, RTR_AMOUNT, RTR_STATUS) ")
                        .append(" VALUES ")
                        .append(" (SHP_AUDIT_RATE_DETAILS_S.NEXTVAL, b.manifest_id, SYSDATE, " + userName + ", " + userName + ", SYSDATE, ")
                        .append(dimDivisor + ", " + rateDetails.getShipperCategory() + ", " + ratedWeight + ", " + rateDetails.getContractName() + ", ")
                        .append(fuelTablePercentage + ", " + ratedBaseDiscount + ", " + ratedEarnedDiscount + ", " + ratedMinMaxAdj + ", ")
                        .append(fuelSurchargeDsc + ", " + custFuelSurchargeDsc + ", " + ratedGrossFuel + ", " + resDsc + ", " + resDscPerc + ", ")
                        .append(dasDsc + ", " + rtrAmount + ", " + rateDetails.getRtrStatus() + ") " );
            } else if(ParcelAuditConstant.EBILL_GFF_TABLE_NAME.equalsIgnoreCase(referenceTableName)) {
                sqlQuery.append(" MERGE INTO SHP_AUDIT_RATE_DETAILS_TB ar USING ")
                        .append(" (SELECT " + entityId + " AS gff_id ")
                        .append(" FROM DUAL ")
                        .append(" ) b ON (ar.EBILL_GFF_ID = b.gff_id) ")
                        .append(" WHEN MATCHED THEN ")
                        .append(" UPDATE ")
                        .append(" SET LAST_UPDATE_USER = " + userName + ", ")
                        .append(" LAST_UPDATE_DATE = SYSDATE, ")
                        .append(" DIM_DIVISOR    = " + dimDivisor + ", ")
                        .append(" SHIPPER_CATEGORY = " + rateDetails.getShipperCategory() + ", ")
                        .append(" RATED_WEIGHT  = " + ratedWeight + ", ")
                        .append(" CONTRACT_NAME  = " + rateDetails.getContractName() + ", ")
                        .append(" FUEL_TABLE_PERC = " + fuelTablePercentage + ", ")
                        .append(" RATED_BASE_DISCOUNT = " + ratedBaseDiscount + ", ")
                        .append(" RATED_EARNED_DISCOUNT = " + ratedEarnedDiscount + ", ")
                        .append(" RATED_MIN_MAX_ADJ = " + ratedMinMaxAdj + ", ")
                        .append(" RATED_FUEL_SURCHARGE_DISC = " + fuelSurchargeDsc + ", ")
                        .append(" RATED_CUST_FUEL_SURCHARGE_DISC = " + custFuelSurchargeDsc + ", ")
                        .append(" RATED_GROSS_FUEL = " + ratedGrossFuel + ", ")
                        .append(" RES_SURCHARGE_DSC = " + resDsc + ", ")
                        .append(" RES_SURCHARGE_DSC_PERC = " + resDscPerc + ", ")
                        .append(" RATED_DAS_DSC = " + dasDsc + ", ")
                        .append(" RTR_AMOUNT = " + rtrAmount + ", ")
                        .append(" RTR_STATUS = " + rateDetails.getRtrStatus())
                        .append(" WHEN NOT MATCHED THEN ")
                        .append(" INSERT ")
                        .append(" (AUDIT_RATE_DETAILS_ID, EBILL_MANIFEST_ID, CREATE_DATE, CREATE_USER, LAST_UPDATE_USER, LAST_UPDATE_DATE, ")
                        .append(" DIM_DIVISOR, SHIPPER_CATEGORY, RATED_WEIGHT, CONTRACT_NAME, FUEL_TABLE_PERC, RATED_BASE_DISCOUNT, RATED_EARNED_DISCOUNT, ")
                        .append(" RATED_MIN_MAX_ADJ, RATED_FUEL_SURCHARGE_DISC, RATED_CUST_FUEL_SURCHARGE_DISC, RATED_GROSS_FUEL, RES_SURCHARGE_DSC, ")
                        .append(" RES_SURCHARGE_DSC_PERC, RATED_DAS_DSC, RTR_AMOUNT, RTR_STATUS) ")
                        .append(" VALUES ")
                        .append(" (SHP_AUDIT_RATE_DETAILS_S.NEXTVAL, b.manifest_id, SYSDATE, " + userName + ", " + userName + ", SYSDATE, ")
                        .append(dimDivisor + ", " + rateDetails.getShipperCategory() + ", " + ratedWeight + ", " + rateDetails.getContractName() + ", ")
                        .append(fuelTablePercentage + ", " + ratedBaseDiscount + ", " + ratedEarnedDiscount + ", " + ratedMinMaxAdj + ", ")
                        .append(fuelSurchargeDsc + ", " + custFuelSurchargeDsc + ", " + ratedGrossFuel + ", " + resDsc + ", " + resDscPerc + ", ")
                        .append(dasDsc + ", " + rtrAmount + ", " + rateDetails.getRtrStatus() + ") ");

            }
            st.executeUpdate(sqlQuery.toString());
            conn.commit();
        }catch (SQLException sqle) {
            try {
                conn.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            System.out.println("Exception in updateShipmentRateDetails -- > "+sqle.getStackTrace());
        }  catch (ServiceLocatorException sle) {
            try {
                conn.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            System.out.println("Exception in updateShipmentRateDetails -- > "+sle.getStackTrace());
        }finally {
            try {
                if (st != null)
                    st.close();
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

    public void updateAllShipmentRateDetails(String referenceTableName, Long entityId, String userName, ParcelRateDetailsDto rateDetails){
        Connection conn = null;
        Statement st = null;
        try{
            conn = ServiceLocator.getDatabaseConnection();
            StringBuilder sqlQuery = new StringBuilder();
            String dimDivisor = (rateDetails.getDimDivisor() != null ? rateDetails.getDimDivisor().toString() : "0.0");
            String ratedWeight = (rateDetails.getRatedWeight() != null ? rateDetails.getRatedWeight().toString() : "0.0");
            String fuelTablePercentage = (rateDetails.getFuelTablePercentage() != null ? rateDetails.getFuelTablePercentage().toString() : "0.0");
            String ratedBaseDiscount = (rateDetails.getRatedBaseDiscount() != null ? rateDetails.getRatedBaseDiscount().toString() : "0.0");
            String ratedEarnedDiscount = (rateDetails.getRatedEarnedDiscount() != null ? rateDetails.getRatedEarnedDiscount().toString() : "0.0");
            String ratedMinMaxAdj = (rateDetails.getRatedMinMaxAdjustment() != null ? rateDetails.getRatedMinMaxAdjustment().toString() : "0.0");
            String fuelSurchargeDsc = (rateDetails.getRatedFuelSurchargeDiscount() != null ? rateDetails.getRatedFuelSurchargeDiscount().toString() : "0.0");
            String custFuelSurchargeDsc = (rateDetails.getRatedCustomFuelSurchargeDiscount() != null ? rateDetails.getRatedCustomFuelSurchargeDiscount().toString() : "0.0" );
            String ratedGrossFuel = (rateDetails.getRatedGrossFuel() != null ? rateDetails.getRatedGrossFuel().toString() : "0.0");
            String resDsc = (rateDetails.getResidentialSurchargeDiscount() != null ? rateDetails.getResidentialSurchargeDiscount().toString() : "0.0");
            String resDscPerc = (rateDetails.getResidentialSurchargeDiscountPercentage() != null ? rateDetails.getResidentialSurchargeDiscountPercentage().toString() : "0.0");
            String dasDsc = (rateDetails.getDeliveryAreaSurchargeDiscount() != null ? rateDetails.getDeliveryAreaSurchargeDiscount().toString() : "0.0");
            String rtrAmount = (rateDetails.getRtrAmount() != null ? rateDetails.getRtrAmount().toString() : "0.0");
            String freightCharge = rateDetails.getFreightCharge() != null ? rateDetails.getFreightCharge().toString() : "0.0";
            String fuelSurcharge = rateDetails.getFuelSurcharge() != null ? rateDetails.getFuelSurcharge().toString() : "0.0";
            String acc1 = rateDetails.getAccessorial1() != null ? rateDetails.getAccessorial1().toString() : "0.0";
            String acc2 = rateDetails.getAccessorial1() != null ? rateDetails.getAccessorial2().toString() : "0.0";
            String acc3 = rateDetails.getAccessorial1() != null ? rateDetails.getAccessorial3().toString() : "0.0";
            String acc4 = rateDetails.getAccessorial1() != null ? rateDetails.getAccessorial4().toString() : "0.0";

            if(ParcelAuditConstant.EBILL_MANIFEST_TABLE_NAME.equalsIgnoreCase(referenceTableName)) {
                sqlQuery.append(" MERGE INTO SHP_AUDIT_RATE_DETAILS_TB ar USING ")
                        .append(" (SELECT " + entityId + " AS manifest_id ")
                        .append(" FROM DUAL ")
                        .append(" ) b ON (ar.EBILL_MANIFEST_ID = b.manifest_id) ")
                        .append(" WHEN MATCHED THEN ")
                        .append(" UPDATE ")
                        .append(" SET LAST_UPDATE_USER = " + userName + ", ")
                        .append(" LAST_UPDATE_DATE = SYSDATE, ")
                        .append(" DIM_DIVISOR    = " + dimDivisor + ", ")
                        .append(" SHIPPER_CATEGORY = " + rateDetails.getShipperCategory() + ", ")
                        .append(" RATED_WEIGHT  = " + ratedWeight + ", ")
                        .append(" CONTRACT_NAME  = " + rateDetails.getContractName() + ", ")
                        .append(" FUEL_TABLE_PERC = " + fuelTablePercentage + ", ")
                        .append(" RATED_BASE_DISCOUNT = " + ratedBaseDiscount + ", ")
                        .append(" RATED_EARNED_DISCOUNT = " + ratedEarnedDiscount + ", ")
                        .append(" RATED_MIN_MAX_ADJ = " + ratedMinMaxAdj + ", ")
                        .append(" RATED_FUEL_SURCHARGE_DISC = " + fuelSurchargeDsc + ", ")
                        .append(" RATED_CUST_FUEL_SURCHARGE_DISC = " + custFuelSurchargeDsc + ", ")
                        .append(" RATED_GROSS_FUEL = " + ratedGrossFuel + ", ")
                        .append(" RES_SURCHARGE_DSC = " + resDsc + ", ")
                        .append(" RES_SURCHARGE_DSC_PERC = " + resDscPerc + ", ")
                        .append(" RATED_DAS_DSC = " + dasDsc + ", ")
                        .append(" RTR_AMOUNT = " + rtrAmount + ", ")
                        .append(" RTR_STATUS = " + rateDetails.getRtrStatus() + ", ")
                        .append(" FRT_CHARGE = " + freightCharge + ", ")
                        .append(" FSC_CHARGE =  " + fuelSurcharge + ", ")
                        .append(" ACCESSORIAL_1 = " + acc1 + ", ")
                        .append(" ACCESSORIAL_2 = " + acc2 + ", ")
                        .append(" ACCESSORIAL_3 = " + acc3 + ", ")
                        .append(" ACCESSORIAL_4 = " + acc4 + ", ")
                        .append(" ACCESSORIAL_1_CODE = " + rateDetails.getAccessorial1Code() + ", ")
                        .append(" ACCESSORIAL_2_CODE =  " + rateDetails.getAccessorial2Code() + ", ")
                        .append(" ACCESSORIAL_3_CODE = " + rateDetails.getAccessorial3Code() + ", ")
                        .append(" ACCESSORIAL_4_CODE = " + rateDetails.getAccessorial4Code())
                        .append(" WHEN NOT MATCHED THEN ")
                        .append(" INSERT ")
                        .append(" (AUDIT_RATE_DETAILS_ID, EBILL_MANIFEST_ID, CREATE_DATE, CREATE_USER, LAST_UPDATE_USER, LAST_UPDATE_DATE, ")
                        .append(" DIM_DIVISOR, SHIPPER_CATEGORY, RATED_WEIGHT, CONTRACT_NAME, FUEL_TABLE_PERC, RATED_BASE_DISCOUNT, RATED_EARNED_DISCOUNT, ")
                        .append(" RATED_MIN_MAX_ADJ, RATED_FUEL_SURCHARGE_DISC, RATED_CUST_FUEL_SURCHARGE_DISC, RATED_GROSS_FUEL, RES_SURCHARGE_DSC, ")
                        .append(" RES_SURCHARGE_DSC_PERC, RATED_DAS_DSC, RTR_AMOUNT, RTR_STATUS, FRT_CHARGE, FSC_CHARGE, FRT_CHARGE, FSC_CHARGE ")
                        .append(" ACCESSORIAL_1, ACCESSORIAL_2, ACCESSORIAL_3, ACCESSORIAL_4, ")
                        .append(" ACCESSORIAL_1_CODE, ACCESSORIAL_2_CODE, ACCESSORIAL_3_CODE, ACCESSORIAL_4_CODE) ")
                        .append(" VALUES ")
                        .append(" (SHP_AUDIT_RATE_DETAILS_S.NEXTVAL, b.manifest_id, SYSDATE, " + userName + ", " + userName + ", SYSDATE, ")
                        .append(dimDivisor + ", " + rateDetails.getShipperCategory() + ", " + ratedWeight + ", " + rateDetails.getContractName() + ", ")
                        .append(fuelTablePercentage + ", " + ratedBaseDiscount + ", " + ratedEarnedDiscount + ", " + ratedMinMaxAdj + ", ")
                        .append(fuelSurchargeDsc + ", " + custFuelSurchargeDsc + ", " + ratedGrossFuel + ", " + resDsc + ", " + resDscPerc + ", ")
                        .append(dasDsc + ", " + rtrAmount + ", " + rateDetails.getRtrStatus() + ", " + freightCharge +", " + fuelSurcharge +", ")
                        .append(acc1 + ", " + acc2 + ", " + acc3 + ", " + acc4)
                        .append(rateDetails.getAccessorial1Code() + ", " + rateDetails.getAccessorial2Code() + ", " + rateDetails.getAccessorial3Code() + ", " + rateDetails.getAccessorial4Code() + ") ");
            } else if(ParcelAuditConstant.EBILL_GFF_TABLE_NAME.equalsIgnoreCase(referenceTableName)) {
                sqlQuery.append(" MERGE INTO SHP_AUDIT_RATE_DETAILS_TB ar USING ")
                        .append(" (SELECT " + entityId + " AS gff_id ")
                        .append(" FROM DUAL ")
                        .append(" ) b ON (ar.EBILL_GFF_ID = b.gff_id) ")
                        .append(" WHEN MATCHED THEN ")
                        .append(" UPDATE ")
                        .append(" SET LAST_UPDATE_USER = " + userName + ", ")
                        .append(" LAST_UPDATE_DATE = SYSDATE, ")
                        .append(" DIM_DIVISOR    = " + dimDivisor + ", ")
                        .append(" SHIPPER_CATEGORY = " + rateDetails.getShipperCategory() + ", ")
                        .append(" RATED_WEIGHT  = " + ratedWeight + ", ")
                        .append(" CONTRACT_NAME  = " + rateDetails.getContractName() + ", ")
                        .append(" FUEL_TABLE_PERC = " + fuelTablePercentage + ", ")
                        .append(" RATED_BASE_DISCOUNT = " + ratedBaseDiscount + ", ")
                        .append(" RATED_EARNED_DISCOUNT = " + ratedEarnedDiscount + ", ")
                        .append(" RATED_MIN_MAX_ADJ = " + ratedMinMaxAdj + ", ")
                        .append(" RATED_FUEL_SURCHARGE_DISC = " + fuelSurchargeDsc + ", ")
                        .append(" RATED_CUST_FUEL_SURCHARGE_DISC = " + custFuelSurchargeDsc + ", ")
                        .append(" RATED_GROSS_FUEL = " + ratedGrossFuel + ", ")
                        .append(" RES_SURCHARGE_DSC = " + resDsc + ", ")
                        .append(" RES_SURCHARGE_DSC_PERC = " + resDscPerc + ", ")
                        .append(" RATED_DAS_DSC = " + dasDsc + ", ")
                        .append(" RTR_AMOUNT = " + rtrAmount + ", ")
                        .append(" RTR_STATUS = " + rateDetails.getRtrStatus())
                        .append(" FRT_CHARGE = " + freightCharge + ", ")
                        .append(" FSC_CHARGE =  " + fuelSurcharge + ", ")
                        .append(" ACCESSORIAL_1 = " + acc1 + ", ")
                        .append(" ACCESSORIAL_2 = " + acc2 + ", ")
                        .append(" ACCESSORIAL_3 = " + acc3 + ", ")
                        .append(" ACCESSORIAL_4 = " + acc4 + ", ")
                        .append(" ACCESSORIAL_1_CODE = " + rateDetails.getAccessorial1Code() + ", ")
                        .append(" ACCESSORIAL_2_CODE =  " + rateDetails.getAccessorial2Code() + ", ")
                        .append(" ACCESSORIAL_3_CODE = " + rateDetails.getAccessorial3Code() + ", ")
                        .append(" ACCESSORIAL_4_CODE = " + rateDetails.getAccessorial4Code())
                        .append(" WHEN NOT MATCHED THEN ")
                        .append(" INSERT ")
                        .append(" (AUDIT_RATE_DETAILS_ID, EBILL_MANIFEST_ID, CREATE_DATE, CREATE_USER, LAST_UPDATE_USER, LAST_UPDATE_DATE, ")
                        .append(" DIM_DIVISOR, SHIPPER_CATEGORY, RATED_WEIGHT, CONTRACT_NAME, FUEL_TABLE_PERC, RATED_BASE_DISCOUNT, RATED_EARNED_DISCOUNT, ")
                        .append(" RATED_MIN_MAX_ADJ, RATED_FUEL_SURCHARGE_DISC, RATED_CUST_FUEL_SURCHARGE_DISC, RATED_GROSS_FUEL, RES_SURCHARGE_DSC, ")
                        .append(" RES_SURCHARGE_DSC_PERC, RATED_DAS_DSC, RTR_AMOUNT, RTR_STATUS, FRT_CHARGE, FSC_CHARGE, FRT_CHARGE, FSC_CHARGE ")
                        .append(" ACCESSORIAL_1, ACCESSORIAL_2, ACCESSORIAL_3, ACCESSORIAL_4, ")
                        .append(" ACCESSORIAL_1_CODE, ACCESSORIAL_2_CODE, ACCESSORIAL_3_CODE, ACCESSORIAL_4_CODE) ")
                        .append(" VALUES ")
                        .append(" (SHP_AUDIT_RATE_DETAILS_S.NEXTVAL, b.manifest_id, SYSDATE, " + userName + ", " + userName + ", SYSDATE, ")
                        .append(dimDivisor + ", " + rateDetails.getShipperCategory() + ", " + ratedWeight + ", " + rateDetails.getContractName() + ", ")
                        .append(fuelTablePercentage + ", " + ratedBaseDiscount + ", " + ratedEarnedDiscount + ", " + ratedMinMaxAdj + ", ")
                        .append(fuelSurchargeDsc + ", " + custFuelSurchargeDsc + ", " + ratedGrossFuel + ", " + resDsc + ", " + resDscPerc + ", ")
                        .append(dasDsc + ", " + rtrAmount + ", " + rateDetails.getRtrStatus() + ", " + freightCharge +", " + fuelSurcharge +", ")
                        .append(acc1 + ", " + acc2 + ", " + acc3 + ", " + acc4)
                        .append(rateDetails.getAccessorial1Code() + ", " + rateDetails.getAccessorial2Code() + ", " + rateDetails.getAccessorial3Code() + ", " + rateDetails.getAccessorial4Code() + ") ");;

            }
            st.executeUpdate(sqlQuery.toString());
            conn.commit();
        }catch (SQLException sqle) {
            System.out.println("Exception in updateShipmentRateDetails -- > "+sqle.getStackTrace());
        }  catch (ServiceLocatorException sle) {
            System.out.println("Exception in updateShipmentRateDetails -- > "+sle.getStackTrace());
        }finally {

            try {
                if (st != null)
                    st.close();
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
