package com.envista.msi.rating.dao;

import com.envista.msi.rating.ServiceLocator;
import com.envista.msi.rating.ServiceLocatorException;
import com.envista.msi.rating.bean.RatingQueueBean;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
                ratingQBean.setBilledMiles(rss.getLong("BILLED_MILES"));
                ratingQBean.setFrtWeight(rss.getFloat("frtWeight"));
                ratingQBean.setFrtWeightUnits(rss.getString("FRT_WEIGHT_UNITS"));
                ratingQBean.setFrtActualWeight(rss.getLong("FRT_ACTUAL_WEIGHT"));
                ratingQBean.setFrtActualWeightUnits(rss.getString("FRT_ACTUAL_WEIGHT_UNITS"));
                ratingQBean.setFrtQyantity(rss.getLong("FRT_QUANTITY"));
                ratingQBean.setFrtQuantityUnits(rss.getString("FRT_QUANTITY_UNITS"));
                ratingQBean.setDimUnits(rss.getString("DIM_UNITS"));
                ratingQBean.setDimLength(rss.getLong("DIM_LENGTH"));
                ratingQBean.setDimWidth(rss.getLong("DIM_WIDTH"));
                ratingQBean.setDimHeight(rss.getLong("DIM_HEIGHT"));
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
                ratingQueueBean.setBilledMiles(rs.getLong("BILLED_MILES"));
                ratingQueueBean.setFrtWeight(rs.getFloat("FRT_WEIGHT"));
                ratingQueueBean.setFrtWeightUnits(rs.getString("FRT_WEIGHT_UNITS"));
                ratingQueueBean.setFrtActualWeight(rs.getLong("FRT_ACTUAL_WEIGHT"));
                ratingQueueBean.setFrtActualWeightUnits(rs.getString("FRT_ACTUAL_WEIGHT_UNITS"));
                ratingQueueBean.setFrtQyantity(rs.getLong("FRT_QUANTITY"));
                ratingQueueBean.setFrtQuantityUnits(rs.getString("FRT_QUANTITY_UNITS"));
                ratingQueueBean.setDimUnits(rs.getString("DIM_UNITS"));
                ratingQueueBean.setDimLength(rs.getLong("DIM_LENGTH"));
                ratingQueueBean.setDimWidth(rs.getLong("DIM_WIDTH"));
                ratingQueueBean.setDimHeight(rs.getLong("DIM_HEIGHT"));
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


}
