package com.envista.msi.rating.dao;

import com.envista.msi.api.web.rest.dto.rtr.ParcelAuditDetailsDto;
import com.envista.msi.rating.ServiceLocator;
import com.envista.msi.rating.ServiceLocatorException;
import com.envista.msi.rating.bean.RatingQueueBean;
import oracle.jdbc.OracleTypes;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RatingQueueDAO {


    public RatingQueueBean getRatingBeanById(Long ratingQueueId){
        RatingQueueBean ratingQBean = null;
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rss = null;

        String selectQueryStr = "select * from SHP_RATING_QUEUE_TB where SHP_RATING_QUEUE_ID="+ratingQueueId;

        try {
            con = ServiceLocator.getDatabaseConnection();
            pstmt = con.prepareStatement(selectQueryStr);
            rss = pstmt.executeQuery();
            ratingQBean = new RatingQueueBean();

            if(rss.next()){
                ratingQBean.setRatingQueueId(rss.getLong("SHP_RATING_QUEUE_ID"));
                ratingQBean.setManiestId(rss.getLong("maniestId"));
                ratingQBean.setGffId(rss.getLong("GFF_ID"));
                ratingQBean.setCurrencyCode(rss.getString("CUSTOMERs_CODE"));
                ratingQBean.setScacCode(rss.getString("SCAC_CODE"));
                ratingQBean.setContractName(rss.getString("CONTRACT_NAME"));
                ratingQBean.setService(rss.getString("SERVICE"));
                ratingQBean.setAccesorial1(rss.getString("ACCESORIAL1"));
                ratingQBean.setAccesorial2(rss.getString("ACCESORIAL2"));
                ratingQBean.setAccesorial3(rss.getString("ACCESORIAL3"));
                ratingQBean.setAccesorial4(rss.getString("ACCESORIAL4"));
                ratingQBean.setAccesorial5(rss.getString("ACCESORIAL5"));
                ratingQBean.setBillOption(rss.getString("BILL_OPTION"));
                ratingQBean.setCurrencyCode(rss.getString("CURRENCY_CODE"));
                ratingQBean.setShipperNumber(rss.getString("SHIPPER_NUMBER"));
                ratingQBean.setTransitTime(rss.getInt("TRANSIT_TIME"));
                ratingQBean.setVerbose(rss.getInt("VERBOSE"));
                ratingQBean.setTotalWeight(rss.getFloat("TOTAL_WEIGHT"));
                ratingQBean.setTotalActualWeight(rss.getFloat("TOTAL_ACTUAL_WEIGHT"));
                ratingQBean.setTotalQuantity(rss.getFloat("TOTAL_QUANTITY"));
                ratingQBean.setBilledMiles(rss.getFloat("BILLED_MILES"));
                ratingQBean.setFrtWeight(rss.getFloat("frtWeight"));
                ratingQBean.setFrtWeightUnits(rss.getString("FRT_WEIGHT_UNITS"));
                ratingQBean.setFrtActualWeight(rss.getFloat("FRT_ACTUAL_WEIGHT"));
                ratingQBean.setFrtActualWeightUnits(rss.getString("FRT_ACTUAL_WEIGHT_UNITS"));
                ratingQBean.setFrtQyantity(rss.getLong("FRT_QUANTITY"));
                ratingQBean.setFrtQuantityUnits(rss.getString("FRT_QUANTITY_UNITS"));
                ratingQBean.setDimUnits(rss.getString("DIM_UNITS"));
                ratingQBean.setDimLength(rss.getFloat("DIM_LENGTH"));
                ratingQBean.setDimWidth(rss.getFloat("DIM_WIDTH"));
                ratingQBean.setDimHeight(rss.getFloat("DIM_HEIGHT"));
                ratingQBean.setShipDate(rss.getDate("SHIP_DATE"));
                ratingQBean.setShipperLocationCode(rss.getString("SHIPPER_LOCATION_CODE"));
                ratingQBean.setShipperCity(rss.getString("SHIPPER_CITY"));
                ratingQBean.setShipperState(rss.getString("SHIPPER_STATE"));
                ratingQBean.setShipperZip(rss.getString("SHIPPER_ZIP"));
                ratingQBean.setShipperCountry(rss.getString("SHIPPER_COUNTRY"));
                ratingQBean.setReceiverLocationCode(rss.getString("RECEIVER_LOCATION_CODE"));
                ratingQBean.setReceiverCity(rss.getString("RECEIVER_CITY"));
                ratingQBean.setReceiverState(rss.getString("RECEIVER_STATE"));
                ratingQBean.setReceiverZip(rss.getString("RECEIVER_ZIP"));
                ratingQBean.setReceiverCountry(rss.getString("RECEIVER_COUNTRY"));
                ratingQBean.setHundredWeight(rss.getBoolean("IS_HUNDRED_WEIGHT"));
                ratingQBean.setJobId(rss.getLong("JOB_ID"));
                ratingQBean.setRatingStatus(rss.getString("RATING_STATUS"));
                ratingQBean.setCreateUser(rss.getString("CREATE_USER"));
                ratingQBean.setCreateDate(rss.getDate("CREATE_DATE"));
                ratingQBean.setCarrierId(rss.getLong("CARRIER_ID"));
                ratingQBean.setParentId(rss.getLong("PARENT_ID"));
                ratingQBean.setRateStatus(rss.getBoolean("RATE_STATUS"));
                ratingQBean.setRatingStatus(rss.getString("ACCESSORIAL_INFO"));
                }

        } catch (SQLException sqle) {
            System.out.println("Exception in getRatingBeanById -- > "+sqle.getStackTrace());
        }  catch (ServiceLocatorException sle) {
            System.out.println("Exception in getRatingBeanById -- > "+sle.getStackTrace());
        }finally {
            try {
                if (rss != null)
                    rss.close();
            } catch (SQLException sqle) {
            }
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

        return ratingQBean;
    }

    public ArrayList<RatingQueueBean> getRatingQueueByJobId(int jobId){
        Connection connection = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        java.util.ArrayList<RatingQueueBean> beanList = null;
        String selectQuery = "select * from SHP_RATING_QUEUE_TB where RATE_STATUS = 0 and job_id="+jobId;

        try {
            connection = ServiceLocator.getDatabaseConnection();
            stmt = connection.prepareStatement(selectQuery);
            beanList = new ArrayList<RatingQueueBean>();
            rs = stmt.executeQuery();
            while(rs.next()){
                RatingQueueBean ratingQueueBean = new RatingQueueBean();
                ratingQueueBean.setRatingQueueId(rs.getLong("SHP_RATING_QUEUE_ID"));
                ratingQueueBean.setManiestId(rs.getLong("MANIFEST_ID"));
                ratingQueueBean.setGffId(rs.getLong("GFF_ID"));
                ratingQueueBean.setCurrencyCode(rs.getString("CUSTOMER_CODE"));
                ratingQueueBean.setScacCode(rs.getString("SCAC_CODE"));
                ratingQueueBean.setContractName(rs.getString("CONTRACT_NAME"));
                ratingQueueBean.setService(rs.getString("SERVICE"));
                ratingQueueBean.setAccesorial1(rs.getString("ACCESORIAL1"));
                ratingQueueBean.setAccesorial2(rs.getString("ACCESORIAL2"));
                ratingQueueBean.setAccesorial3(rs.getString("ACCESORIAL3"));
                ratingQueueBean.setAccesorial4(rs.getString("ACCESORIAL4"));
                ratingQueueBean.setAccesorial5(rs.getString("ACCESORIAL5"));
                ratingQueueBean.setBillOption(rs.getString("BILL_OPTION"));
                ratingQueueBean.setCurrencyCode(rs.getString("CURRENCY_CODE"));
                ratingQueueBean.setShipperNumber(rs.getString("SHIPPER_NUMBER"));
                ratingQueueBean.setTransitTime(rs.getInt("TRANSIT_TIME"));
                ratingQueueBean.setVerbose(rs.getInt("VERBOSE"));
                ratingQueueBean.setTotalWeight(rs.getFloat("TOTAL_WEIGHT"));
                ratingQueueBean.setTotalActualWeight(rs.getFloat("TOTAL_ACTUAL_WEIGHT"));
                ratingQueueBean.setTotalQuantity(rs.getFloat("TOTAL_QUANTITY"));
                ratingQueueBean.setBilledMiles(rs.getFloat("BILLED_MILES"));
                ratingQueueBean.setFrtWeight(rs.getFloat("FRT_WEIGHT"));
                ratingQueueBean.setFrtWeightUnits(rs.getString("FRT_WEIGHT_UNITS"));
                ratingQueueBean.setFrtActualWeight(rs.getFloat("FRT_ACTUAL_WEIGHT"));
                ratingQueueBean.setFrtActualWeightUnits(rs.getString("FRT_ACTUAL_WEIGHT_UNITS"));
                ratingQueueBean.setFrtQyantity(rs.getLong("FRT_QUANTITY"));
                ratingQueueBean.setFrtQuantityUnits(rs.getString("FRT_QUANTITY_UNITS"));
                ratingQueueBean.setDimUnits(rs.getString("DIM_UNITS"));
                ratingQueueBean.setDimLength(rs.getFloat("DIM_LENGTH"));
                ratingQueueBean.setDimWidth(rs.getFloat("DIM_WIDTH"));
                ratingQueueBean.setDimHeight(rs.getFloat("DIM_HEIGHT"));
                ratingQueueBean.setShipDate(rs.getDate("SHIP_DATE"));
                ratingQueueBean.setShipperLocationCode(rs.getString("SHIPPER_LOCATION_CODE"));
                ratingQueueBean.setShipperCity(rs.getString("SHIPPER_CITY"));
                ratingQueueBean.setShipperState(rs.getString("SHIPPER_STATE"));
                ratingQueueBean.setShipperZip(rs.getString("SHIPPER_ZIP"));
                ratingQueueBean.setShipperCountry(rs.getString("SHIPPER_COUNTRY"));
                ratingQueueBean.setReceiverLocationCode(rs.getString("RECEIVER_LOCATION_CODE"));
                ratingQueueBean.setReceiverCity(rs.getString("RECEIVER_CITY"));
                ratingQueueBean.setReceiverState(rs.getString("RECEIVER_STATE"));
                ratingQueueBean.setReceiverZip(rs.getString("RECEIVER_ZIP"));
                ratingQueueBean.setReceiverCountry(rs.getString("RECEIVER_COUNTRY"));
                ratingQueueBean.setHundredWeight(rs.getBoolean("IS_HUNDRED_WEIGHT"));
                ratingQueueBean.setJobId(rs.getLong("JOB_ID"));
                ratingQueueBean.setRatingStatus(rs.getString("RATING_STATUS"));
                ratingQueueBean.setCreateUser(rs.getString("CREATE_USER"));
                ratingQueueBean.setCreateDate(rs.getDate("CREATE_DATE"));
                ratingQueueBean.setCarrierId(rs.getLong("CARRIER_ID"));
                ratingQueueBean.setParentId(rs.getLong("PARENT_ID"));
                ratingQueueBean.setRateStatus(rs.getBoolean("RATE_STATUS"));
                ratingQueueBean.setRatingStatus(rs.getString("ACCESSORIAL_INFO"));

                beanList.add(ratingQueueBean);
            }

        } catch (SQLException sqle) {
            System.out.println("Exception in getRatingQueueByJobId-- > "+sqle.getStackTrace());
            sqle.printStackTrace();
        }  catch (ServiceLocatorException sle) {
            System.out.println("Exception in getRatingQueueByJobId-- > "+sle.getStackTrace());
        }finally {
            try {
                if (rs != null)
                    rs.close();
            } catch (SQLException sqle) {
            }
            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException sqle) {
            }
            try {
                if (connection != null)
                    connection.close();
            } catch (SQLException sqle) {
            }
        }

        return beanList;
    }

    public void saveRatingQueueBean(RatingQueueBean ratingQueueBean){
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            connection = ServiceLocator.getDatabaseConnection();
            String insertQuery = RatingDAOUtil.prepareRatingQueueInsertQuery();
            ps = connection.prepareStatement(insertQuery);
            RatingDAOUtil.setSqlStatementPlaceHolderValues(ps, ratingQueueBean);

            ps.executeUpdate();
        }catch (SQLException sqle) {
            System.out.println("Exception in getRatingQueueByJobId-- > "+sqle.getStackTrace());
            sqle.printStackTrace();
        }  catch (ServiceLocatorException sle) {
            System.out.println("Exception in getRatingQueueByJobId-- > "+sle.getStackTrace());
        }finally {
            try {
                if (rs != null)
                    rs.close();
            } catch (SQLException sqle) {
            }
            try {
                if (ps != null)
                    ps.close();
            } catch (SQLException sqle) {
            }
            try {
                if (connection != null)
                    connection.close();
            } catch (SQLException sqle) {
            }
        }
    }

    public List<ParcelAuditDetailsDto> getUpsParcelShipmentDetails(String customerIds, String fromDate, String toDate, String trackingNumbers, String invoiceIds, boolean ignoreRtrStatus){
        Connection conn = null;
        CallableStatement cstmt = null;
        ResultSet rs = null;
        List<ParcelAuditDetailsDto> parcelUpsShipments = null;
        try {
            conn = ServiceLocator.getDatabaseConnection();
            cstmt = conn.prepareCall("{ call SHP_AUDIT_UPS_PARCEL_PROC(?, ?, ?, ?, ?, ?, ?)}");
            parcelUpsShipments = new ArrayList<>();

            cstmt.setString(1,customerIds);
            cstmt.setString(2, fromDate);
            cstmt.setString(3, toDate);
            cstmt.setString(4, trackingNumbers);
            cstmt.setString(5, invoiceIds);
            cstmt.setInt(6, ignoreRtrStatus ? 1 : 0);
            cstmt.registerOutParameter(7, OracleTypes.CURSOR);
            cstmt.execute();

            rs = (ResultSet) cstmt.getObject(7);

            while(rs.next()){
                ParcelAuditDetailsDto shipmentDetails = new ParcelAuditDetailsDto();
                shipmentDetails.setId(rs.getLong("ID"));
                shipmentDetails.setCustomerId(rs.getLong("CUSTOMER_ID"));
                shipmentDetails.setCustomerCode(rs.getString("CUSTOMER_CODE"));
                shipmentDetails.setCarrierId(rs.getLong("CARRIER_ID"));
                shipmentDetails.setContractNumber(rs.getString("CONTRACT_NUMBER"));
                shipmentDetails.setBillOption(rs.getString("BILL_OPTION_CODE"));
                shipmentDetails.setRtrScacCode(rs.getString("rtr_scac_code"));
                shipmentDetails.setScacCode(rs.getString("scac_code"));
                shipmentDetails.setTranCode(rs.getString("TRAN_CODE"));
                shipmentDetails.setChargeClassificationCode(rs.getString("CHARGE_CLASSIFICATION_CODE"));
                shipmentDetails.setChargeDescriptionCode(rs.getString("CHARGE_DESCRIPTION_CODE"));
                shipmentDetails.setChargeDescription(rs.getString("CHARGE_DESCRIPTION"));
                shipmentDetails.setPackageWeight(rs.getString("PACKAGE_WEIGHT"));
                shipmentDetails.setTrackingNumber(rs.getString("TRACKING_NUMBER"));
                shipmentDetails.setPickupDate(rs.getDate("PICKUP_DATE"));
                shipmentDetails.setResidentialIndicator(rs.getString("RESIDENTIAL_INDICATOR"));
                shipmentDetails.setDeliveryDate(rs.getDate("DELIVERY_DATE"));
                shipmentDetails.setSenderCountry(rs.getString("SENDER_COUNTRY"));
                shipmentDetails.setSenderState(rs.getString("SENDER_STATE"));
                shipmentDetails.setSenderCity(rs.getString("SENDER_CITY"));
                shipmentDetails.setSenderZipCode(rs.getString("SENDER_ZIP_CODE"));
                shipmentDetails.setReceiverCountry(rs.getString("RECEIVER_COUNTRY"));
                shipmentDetails.setReceiverState(rs.getString("RECEIVER_STATE"));
                shipmentDetails.setReceiverCity(rs.getString("RECEIVER_CITY"));
                shipmentDetails.setReceiverZipCode(rs.getString("RECEIVER_ZIP_CODE"));
                shipmentDetails.setNetAmount(rs.getString("NET_AMOUNT"));
                shipmentDetails.setItemQuantity(rs.getString("ITEM_QUANTITY"));
                shipmentDetails.setQuantityUnit(rs.getString("QUANTITY_UNIT"));
                shipmentDetails.setWeightUnit(rs.getString("WEIGHT_UNIT"));
                shipmentDetails.setDimLength(rs.getString("DIM_LENGTH"));
                shipmentDetails.setDimHeight(rs.getString("DIM_HEIGHT"));
                shipmentDetails.setDimWidth(rs.getString("DIM_WIDTH"));
                shipmentDetails.setUnitOfDim(rs.getString("UNIT_OF_DIM"));
                shipmentDetails.setCurrency(rs.getString("CURRENCY"));
                shipmentDetails.setInvoiceId(rs.getLong("INVOICE_ID"));
                shipmentDetails.setServiceLevel(rs.getString("SERVICE_LEVEL"));
                shipmentDetails.setDwFieldInformation(rs.getString("DW_FIELD_INFORMATION"));
                shipmentDetails.setShipperNumber(rs.getString("SHIPPER_NUMBER"));
                shipmentDetails.setParentId(rs.getLong("PARENT_ID"));
                shipmentDetails.setPackageType(rs.getString("package_type"));
                shipmentDetails.setPackageDimension(rs.getString("PACKAGE_DIMENSION"));
                shipmentDetails.setActualWeight(rs.getBigDecimal("ACTUAL_WEIGHT"));
                shipmentDetails.setActualWeightUnit(rs.getString("UNIT_OF_ACTUAL_WEIGHT"));
                shipmentDetails.setRtrAmount(rs.getBigDecimal("rtr_amount"));
                shipmentDetails.setRevenueTier(rs.getString("REVENUE_TIER"));
                parcelUpsShipments.add(shipmentDetails);
            }

        }catch (Exception e){

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
        return parcelUpsShipments;
    }

    public List<ParcelAuditDetailsDto> getNonUpsParcelShipmentDetails(String customerIds, String carrierIds, String fromDate, String toDate, String trackingNumbers, String invoiceId){
        Connection conn = null;
        CallableStatement cstmt = null;
        ResultSet rs = null;
        List<ParcelAuditDetailsDto> parcelUpsShipments = null;
        try {
            conn = ServiceLocator.getDatabaseConnection();
            cstmt = conn.prepareCall("{ call SHP_AUDIT_NON_UPS_PRCEL_PROC(?, ?, ?, ?, ?, ?, ?)}");
            parcelUpsShipments = new ArrayList<>();

            cstmt.setString(1,customerIds);
            cstmt.setString(2, carrierIds);
            cstmt.setString(3, fromDate);
            cstmt.setString(4, toDate);
            cstmt.setString(5, trackingNumbers);
            cstmt.setString(6, invoiceId);
            cstmt.registerOutParameter(7, OracleTypes.CURSOR);
            cstmt.execute();

            rs = (ResultSet) cstmt.getObject(7);

            while(rs.next()){
                ParcelAuditDetailsDto shipmentDetails = new ParcelAuditDetailsDto();
                shipmentDetails.setId(rs.getLong("ID"));
                shipmentDetails.setCustomerId(rs.getLong("CUSTOMER_ID"));
                shipmentDetails.setCustomerCode(rs.getString("CUSTOMER_CODE"));
                shipmentDetails.setCarrierId(rs.getLong("CARRIER_ID"));
                shipmentDetails.setContractNumber(rs.getString("CONTRACT_NUMBER"));
                shipmentDetails.setBillOption(rs.getString("BILL_OPTION_CODE"));
                shipmentDetails.setRtrScacCode(rs.getString("rtr_scac_code"));
                shipmentDetails.setScacCode(rs.getString("scac_code"));
                shipmentDetails.setTranCode(rs.getString("TRAN_CODE"));
                shipmentDetails.setChargeClassificationCode(rs.getString("CHARGE_CLASSIFICATION_CODE"));
                shipmentDetails.setChargeDescriptionCode(rs.getString("CHARGE_DESCRIPTION_CODE"));
                shipmentDetails.setChargeDescription(rs.getString("CHARGE_DESCRIPTION"));
                shipmentDetails.setPackageWeight(rs.getString("PACKAGE_WEIGHT"));
                shipmentDetails.setTrackingNumber(rs.getString("TRACKING_NUMBER"));
                shipmentDetails.setPickupDate(rs.getDate("PICKUP_DATE"));
                shipmentDetails.setResidentialIndicator(rs.getString("RESIDENTIAL_INDICATOR"));
                shipmentDetails.setDeliveryDate(rs.getDate("DELIVERY_DATE"));
                shipmentDetails.setSenderCountry(rs.getString("SENDER_COUNTRY"));
                shipmentDetails.setSenderState(rs.getString("SENDER_STATE"));
                shipmentDetails.setSenderCity(rs.getString("SENDER_CITY"));
                shipmentDetails.setSenderZipCode(rs.getString("SENDER_ZIP_CODE"));
                shipmentDetails.setReceiverCountry(rs.getString("RECEIVER_COUNTRY"));
                shipmentDetails.setReceiverState(rs.getString("RECEIVER_STATE"));
                shipmentDetails.setReceiverCity(rs.getString("RECEIVER_CITY"));
                shipmentDetails.setReceiverZipCode(rs.getString("RECEIVER_ZIP_CODE"));
                shipmentDetails.setNetAmount(rs.getString("NET_AMOUNT"));
                shipmentDetails.setItemQuantity(rs.getString("ITEM_QUANTITY"));
                shipmentDetails.setQuantityUnit(rs.getString("QUANTITY_UNIT"));
                shipmentDetails.setWeightUnit(rs.getString("WEIGHT_UNIT"));
                shipmentDetails.setDimLength(rs.getString("DIM_LENGTH"));
                shipmentDetails.setDimHeight(rs.getString("DIM_HEIGHT"));
                shipmentDetails.setDimWidth(rs.getString("DIM_WIDTH"));
                shipmentDetails.setUnitOfDim(rs.getString("UNIT_OF_DIM"));
                shipmentDetails.setCurrency(rs.getString("CURRENCY"));
                shipmentDetails.setInvoiceId(rs.getLong("INVOICE_ID"));
                shipmentDetails.setServiceLevel(rs.getString("SERVICE_LEVEL"));
                shipmentDetails.setDwFieldInformation(rs.getString("DW_FIELD_INFORMATION"));
                if(shipmentDetails.getDwFieldInformation() != null){
                    try {
                        String[] dwFieldInfo = shipmentDetails.getDwFieldInformation().split(",");
                        if (dwFieldInfo != null && dwFieldInfo.length > 0) {
                            shipmentDetails.setChargeClassificationCode(dwFieldInfo[1].trim());
                            shipmentDetails.setChargeDescriptionCode(dwFieldInfo[2].trim());
                        }
                    } catch (Exception e) {
                    }
                }
                shipmentDetails.setShipperNumber(rs.getString("SHIPPER_NUMBER"));
                shipmentDetails.setParentId(rs.getLong("PARENT_ID"));
                shipmentDetails.setPackageType(rs.getString("package_type"));
                shipmentDetails.setPackageDimension(rs.getString("PACKAGE_DIMENSION"));
                shipmentDetails.setActualWeight(rs.getBigDecimal("ACTUAL_WEIGHT"));
                shipmentDetails.setActualWeightUnit(rs.getString("UNIT_OF_ACTUAL_WEIGHT"));
                shipmentDetails.setRtrAmount(rs.getBigDecimal("rtr_amount"));
                shipmentDetails.setRevenueTier(rs.getString("REVENUE_TIER"));
                //Need to add charge code here.
                parcelUpsShipments.add(shipmentDetails);
            }
        }catch (Exception e){

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
        return parcelUpsShipments;
    }
}
