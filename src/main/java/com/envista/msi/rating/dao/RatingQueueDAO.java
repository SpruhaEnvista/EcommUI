package com.envista.msi.rating.dao;

import com.envista.msi.api.domain.util.ParcelRatingUtil;
import com.envista.msi.api.web.rest.dto.rtr.ParcelAuditDetailsDto;
import com.envista.msi.rating.ServiceLocator;
import com.envista.msi.rating.ServiceLocatorException;
import com.envista.msi.rating.bean.RatingQueueBean;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RatingQueueDAO {

    private final Logger log = LoggerFactory.getLogger(RatingQueueDAO.class);

    public RatingQueueBean getRatingBeanById(Long ratingQueueId) {
        RatingQueueBean ratingQBean = null;
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rss = null;

        String selectQueryStr = "select * from SHP_RATING_QUEUE_TB where SHP_RATING_QUEUE_ID=" + ratingQueueId;

        try {
            con = ServiceLocator.getDatabaseConnection();
            pstmt = con.prepareStatement(selectQueryStr);
            rss = pstmt.executeQuery();
            ratingQBean = new RatingQueueBean();

            if (rss.next()) {
                ratingQBean.setRatingQueueId(rss.getLong("SHP_RATING_QUEUE_ID"));
                ratingQBean.setManiestId(rss.getLong("maniestId"));
                ratingQBean.setGffId(rss.getLong("GFF_ID"));
                ratingQBean.setCustomerCode(rss.getString("CUSTOMER_CODE"));
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
                ratingQBean.setFrtWeight(rss.getFloat("FRT_WEIGHT"));
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
                ratingQBean.setDeliveryDate(rss.getDate("DALIVERY_DATE"));
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
                ratingQBean.setCreateUser(rss.getString("CREATE_USER"));
                ratingQBean.setCreateDate(rss.getDate("CREATE_DATE"));
                ratingQBean.setCarrierId(rss.getLong("CARRIER_ID"));
                ratingQBean.setParentId(rss.getLong("PARENT_ID"));
                ratingQBean.setRateStatus(rss.getInt("RATE_STATUS"));
                ratingQBean.setAccessorialInfo(rss.getString("ACCESSORIAL_INFO"));
                ratingQBean.setRevenueTier(rss.getString("REVENUE_TIER"));
                ratingQBean.setTrackingNumber(rss.getString("TRACKING_NUMBER"));
                ratingQBean.setPackageType(rss.getString("PACKAGE_TYPE"));
                ratingQBean.setHwtIdentifier(rss.getString("HWT_IDENTIFIER"));
                ratingQBean.setRateSetName(rss.getString("RATE_SET_NAME"));
                ratingQBean.setTaskId(rss.getLong("TASK_ID"));
                ratingQBean.setThresholdValue(rss.getString("THRESHOLD_VALUE"));
                ratingQBean.setThresholdType(rss.getString("THRESHOLD_TYPE"));
                ratingQBean.setZone(rss.getString("ZONE"));
                ratingQBean.setSenderBilledZipCode(rss.getString("SENDER_BILLED_ZIP_CODE"));
                ratingQBean.setReceiverBilledZipCode(rss.getString("RECEIVER_BILLED_ZIP_CODE"));
            }

        } catch (SQLException sqle) {
            sqle.printStackTrace();
            throw new DaoException("Exception in getRatingBeanById", sqle);
        } catch (ServiceLocatorException sle) {
            throw new DaoException("Exception in getRatingBeanById", sle);
        } finally {
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

    public ArrayList<RatingQueueBean> getRatingQueueByJobId(String jobIds) {
        Connection connection = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        java.util.ArrayList<RatingQueueBean> beanList = null;
        String selectQuery = "select * from SHP_RATING_QUEUE_TB where  RATE_STATUS = 0 and job_id in ( " + jobIds + " ) and rownum <= 30000 ";

        try {
            connection = ServiceLocator.getDatabaseConnection();
            stmt = connection.prepareStatement(selectQuery);
            beanList = new ArrayList<RatingQueueBean>();
            rs = stmt.executeQuery();
            while (rs.next()) {
                RatingQueueBean ratingQueueBean = new RatingQueueBean();
                ratingQueueBean.setRatingQueueId(rs.getLong("SHP_RATING_QUEUE_ID"));
                ratingQueueBean.setManiestId(rs.getLong("MANIFEST_ID"));
                ratingQueueBean.setGffId(rs.getLong("GFF_ID"));
                ratingQueueBean.setCustomerCode(rs.getString("CUSTOMER_CODE"));
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
                ratingQueueBean.setDeliveryDate(rs.getDate("DALIVERY_DATE"));
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
                ratingQueueBean.setCreateUser(rs.getString("CREATE_USER"));
                ratingQueueBean.setCreateDate(rs.getDate("CREATE_DATE"));
                ratingQueueBean.setCarrierId(rs.getLong("CARRIER_ID"));
                ratingQueueBean.setParentId(rs.getLong("PARENT_ID"));
                ratingQueueBean.setRateStatus(rs.getInt("RATE_STATUS"));
                ratingQueueBean.setAccessorialInfo(rs.getString("ACCESSORIAL_INFO"));
                ratingQueueBean.setRevenueTier(rs.getString("REVENUE_TIER"));
                ratingQueueBean.setTrackingNumber(rs.getString("TRACKING_NUMBER"));
                ratingQueueBean.setPackageType(rs.getString("PACKAGE_TYPE"));
                ratingQueueBean.setHwtIdentifier(rs.getString("HWT_IDENTIFIER"));
                ratingQueueBean.setRateSetName(rs.getString("RATE_SET_NAME"));
                ratingQueueBean.setTaskId(rs.getLong("TASK_ID"));
                ratingQueueBean.setThresholdType(rs.getString("THRESHOLD_TYPE"));
                ratingQueueBean.setThresholdValue(rs.getString("THRESHOLD_VALUE"));
                ratingQueueBean.setZone(rs.getString("ZONE"));
                ratingQueueBean.setSenderBilledZipCode(rs.getString("SENDER_BILLED_ZIP_CODE"));
                ratingQueueBean.setReceiverBilledZipCode(rs.getString("RECEIVER_BILLED_ZIP_CODE"));
                ratingQueueBean.setReturnFlag(rs.getString("RETURN_FLAG"));
                ratingQueueBean.setResiFlag(rs.getString("RESI_FLAG"));
                ratingQueueBean.setWorldeEaseNum(rs.getString("WORLD_EASE_NUM"));
                ratingQueueBean.setComToRes(rs.getString("COM_TO_RES"));
                ratingQueueBean.setPrpFlag(rs.getString("PRP_FLAG"));
                ratingQueueBean.setReturnFlag(rs.getString("RETURN_FLAG"));
                ratingQueueBean.setResiFlag(rs.getString("RESI_FLAG"));
                ratingQueueBean.setComToRes(rs.getString("COM_TO_RES"));
                ratingQueueBean.setActualServiceBucket(rs.getLong("ACTUAL_SERVICE_BUCKET"));
                beanList.add(ratingQueueBean);
            }

        } catch (SQLException sqle) {
            sqle.printStackTrace();
            throw new DaoException("Exception in getRatingQueueByJobId", sqle);
        } catch (ServiceLocatorException sle) {
            throw new DaoException("Exception in getRatingQueueByJobId", sle);
        } finally {
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


    public void updateRateStatusInQueue(Long ratingQueueId, int statusValue, String queueIds) {

        Connection connection = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        StringBuilder updateQuery = new StringBuilder("update SHP_RATING_QUEUE_TB set RATE_STATUS = " + statusValue + " where SHP_RATING_QUEUE_ID");

        if (ratingQueueId != null)
            updateQuery.append(" = " + ratingQueueId);
        else if (queueIds != null)
            updateQuery.append(" IN(" + queueIds + ")");
        else
            updateQuery.append(" = -1");

        try {
            connection = ServiceLocator.getDatabaseConnection();
            stmt = connection.prepareStatement(updateQuery.toString());
            stmt.executeUpdate();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            throw new DaoException("Exception in updateRateStatusinQueue", sqle);
        } catch (ServiceLocatorException sle) {
            throw new DaoException("Exception in updateRateStatusinQueue", sle);
        } finally {
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
    }

    public void saveRatingQueueBean(RatingQueueBean ratingQueueBean){
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            connection = ServiceLocator.getDatabaseConnection();
            String insertQuery = RatingDAOUtil.prepareRatingQueueInsertQuery(false);
            ps = connection.prepareStatement(insertQuery);
            RatingDAOUtil.setSqlStatementPlaceHolderValues(ps, ratingQueueBean);

            ps.executeUpdate();
        }catch (SQLException sqle) {
            sqle.printStackTrace();
            throw new DaoException("Exception in getRatingQueueByJobId", sqle);
        }  catch (ServiceLocatorException sle) {
            throw new DaoException("Exception in getRatingQueueByJobId", sle);
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

    public List<ParcelAuditDetailsDto> getUpsParcelShipmentDetails(String customerIds, String fromDate, String toDate, String trackingNumbers, String invoiceIds, boolean ignoreRtrStatus, String hwtNumbers) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<ParcelAuditDetailsDto> parcelUpsShipments = null;
        StringBuilder liveQuery = new StringBuilder();
        try {
            conn = ServiceLocator.getDatabaseConnection();

            liveQuery.append(" SELECT a.EBILL_GFF_ID AS ID, d.CUSTOMER_ID, d.CUSTOMER_CODE, c.CARRIER_ID, c.CONTRACT_NUMBER, ");
            liveQuery.append(" DECODE(a.BILL_OPTION_CODE, 'F/C', 'FC', 'F/D', 'FD', 'P/P', 'PP', 'C/B', 'CB', 'T/P', 'TP', a.BILL_OPTION_CODE) AS BILL_OPTION_CODE, ");
            liveQuery.append(" e.rtr_scac_code, e.scac_code, null AS TRAN_CODE, a.CHARGE_CLASSIFICATION_CODE, a.CHARGE_DESCRIPTION_CODE, ");
            liveQuery.append(" a.CHARGE_DESCRIPTION, a.BILLED_WEIGHT AS PACKAGE_WEIGHT, a.TRACKING_NUMBER, a.SHIPMENT_DATE AS PICKUP_DATE, ");
            liveQuery.append(" DECODE(a.CHARGE_DESCRIPTION_CODE, '001', 'MAN', '002', 'MAN', '003', 'MAN', a.CHARGE_DESCRIPTION_CODE) AS RESIDENTIAL_INDICATOR, ");
            liveQuery.append(" a.DELIVERY_DATE,a.SENDER_COUNTRY AS SENDER_COUNTRY, a.SENDER_STATE AS SENDER_STATE, a.SENDER_CITY AS SENDER_CITY, ");
            liveQuery.append(" a.SENDER_POSTAL AS SENDER_ZIP_CODE, a.RECEIVER_COUNTRY, a.RECEIVER_STATE, a.RECEIVER_CITY, a.RECEIVER_POSTAL AS RECEIVER_ZIP_CODE, ");
            liveQuery.append(" a.NET_AMOUNT, a.ITEM_QUANTITY, a.ITEM_QUANTITY_UNIT_OF_MEASURE AS QUANTITY_UNIT, a.BILLED_WEIGHT_UNIT_OF_MEASURE AS WEIGHT_UNIT, null AS DIM_LENGTH, ");
            liveQuery.append(" null AS DIM_HEIGHT, null AS DIM_WIDTH, a.Package_Dimen_Unit_Of_Measure AS UNIT_OF_DIM, a.INVOICE_CURRENCY_CODE AS CURRENCY, b.INVOICE_ID, ");
            liveQuery.append(" (select custom_defined_9 from shp_lookup_tb where lookup_id = a.actual_service_bucket) AS SERVICE_LEVEL, a.DW_FIELD_INFORMATION AS DW_FIELD_INFORMATION, ");
            liveQuery.append(" CONCAT('0000', a.ACCOUNT_NUMBER) AS SHIPPER_NUMBER, a.PARENT_ID, (CASE WHEN a.container_type = 'LTR' THEN 'Letter' WHEN a.container_type IN ('PKG', 'PAK') THEN 'PKG' ELSE a.container_type END) package_type, ");
            liveQuery.append(" a.PACKAGE_DIMENSIONS AS PACKAGE_DIMENSION, a.ENTERED_WEIGHT AS ACTUAL_WEIGHT, a.ENTERED_WEIGHT_UNIT_OF_MEASURE AS UNIT_OF_ACTUAL_WEIGHT, ");
            liveQuery.append(" (select rev.SPEND from Shp_Revenue_Tb rev where  rev.customer_id=c.customer_id and rev.carrier_id=c.carrier_id and rev.carrier_id=21 and (a.SHIPMENT_DATE BETWEEN week_from_date AND week_to_date) and rownum=1 and rev.spend is not null) AS REVENUE_TIER, ");
            liveQuery.append(" null AS CHARGE_CODE, a.Lead_Shipment_Number AS MULTI_WEIGHT_NUMBER, a.CHARGE_CATEGORY_DETAIL_CODE, ");
            liveQuery.append(" a.INVOICE_DATE, a.INVOICE_NUMBER,  a.ZONE as ZONE, a.INCENTIVE_AMOUNT, b.CREATE_DATE AS INV_CREATE_DATE, a.SENDER_POSTAL AS SENDER_BILLED_ZIP_CODE, a.RECEIVER_POSTAL AS RECEIVER_BILLED_ZIP_CODE," +
                    "     f.STATE as shipper_state,f.CITY  as shipper_city,f.ZIPCODE  as shipper_zipCode,f.COUNTRY as shipper_country," +
                    "a.World_Ease_Number,a.actual_service_bucket,ar.RTR_AMOUNT ,ar.rtr_status ");

            liveQuery.append(" FROM shp_ebill_gff_tb a, shp_ebill_invoice_tb b, shp_ebill_contract_tb c, shp_customer_profile_tb d," +
                    " shp_carrier_tb e, shp_shipper_tb f, SHP_EBILL_UPS_RATES_TB ar ");

            liveQuery.append(" WHERE a.invoice_id = b.invoice_id ");
            liveQuery.append(" AND b.INV_CONTRACT_NUMBER = c.CONTRACT_NUMBER ");
            liveQuery.append(" AND c.CUSTOMER_ID = d.CUSTOMER_ID ");
            liveQuery.append(" AND c.CARRIER_ID = e.CARRIER_ID ");
            liveQuery.append(" AND b.inv_CARRIER_ID = c.CARRIER_ID ");
            liveQuery.append(" AND b.shipper_code = f.shipper_code ");

            liveQuery.append(" AND a.ebill_gff_id = ar.ebill_gff_id(+) ");

            liveQuery.append(" and b.inv_carrier_id = 21 ");

            if(fromDate != null && !fromDate.isEmpty() && toDate != null && !toDate.isEmpty()) {
                liveQuery.append(" AND trunc(a.SHIPMENT_DATE) BETWEEN '" + fromDate + "' AND '" + toDate + "'  ");
            }
            liveQuery.append(" AND a.CHARGE_CLASSIFICATION_CODE IS NOT NULL ");


            if(trackingNumbers != null && !trackingNumbers.isEmpty()) {

                if (StringUtils.containsIgnoreCase(trackingNumbers, ","))
                    liveQuery.append(" AND a.tracking_number IN (" + trackingNumbers + ") ");
                else
                    liveQuery.append(" AND a.tracking_number IN ('" + trackingNumbers + "') ");
            }

            if(customerIds != null && !customerIds.isEmpty()) {
                liveQuery.append(" AND d.CUSTOMER_ID IN(" + customerIds + ") ");
            }

            if(invoiceIds != null && !invoiceIds.isEmpty()) {
                liveQuery.append(" AND a.invoice_id IN ( " + invoiceIds + ") ");
            }

            if (!ignoreRtrStatus) {
                liveQuery.append(" AND (UPPER(ar.RTR_STATUS) = 'READYFORRATE' OR ar.RTR_STATUS IS NULL)  ");
            }

            if (hwtNumbers != null && !hwtNumbers.isEmpty()) {

                if (StringUtils.containsIgnoreCase(hwtNumbers, ","))
                    liveQuery.append(" AND a.Lead_Shipment_Number IN (" + hwtNumbers + ") ");
                else
                    liveQuery.append(" AND a.Lead_Shipment_Number = ('" + hwtNumbers + "') ");
            }


            String archiveQuery = "";
            archiveQuery = liveQuery.toString().replace("shp_ebill_gff_tb", "arc_ebill_gff_tb");
            archiveQuery = archiveQuery.replace("shp_ebill_invoice_tb", "arc_ebill_invoice_tb");
            ps = conn.prepareStatement(liveQuery.toString() + " UNION " + archiveQuery);
            parcelUpsShipments = new ArrayList<>();

            rs = ps.executeQuery();
            String senderCountry = null;
            String senderState = null;
            String senderCity = null;
            String senderZipCode = null;

            String receiverCountry = null;
            String receiverState = null;
            String receiverCity = null;
            String receiverZipCode = null;
            String zone = null;
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
                shipmentDetails.setPackageType(rs.getString("PACKAGE_TYPE"));
                shipmentDetails.setRtrStatus(rs.getString("RTR_STATUS"));
                shipmentDetails.setMultiWeightNumber(rs.getString("MULTI_WEIGHT_NUMBER"));
                shipmentDetails.setChargeCategoryDetailCode(rs.getString("CHARGE_CATEGORY_DETAIL_CODE"));
                shipmentDetails.setInvoiceDate(rs.getDate("INVOICE_DATE"));
                shipmentDetails.setInvoiceNumber(rs.getString("INVOICE_NUMBER"));
                shipmentDetails.setZone(rs.getString("ZONE"));
                if(shipmentDetails.getZone() != null && !shipmentDetails.getZone().isEmpty()){
                    shipmentDetails.setZone(ParcelRatingUtil.translateUpsZone(shipmentDetails.getZone()));
                }
                shipmentDetails.setIncentiveAmount(rs.getBigDecimal("INCENTIVE_AMOUNT"));
                shipmentDetails.setInvoiceCreateDate(rs.getDate("INV_CREATE_DATE"));
                shipmentDetails.setSenderBilledZipCode(rs.getString("SENDER_BILLED_ZIP_CODE"));
                shipmentDetails.setReceiverBilledZipCode(rs.getString("RECEIVER_BILLED_ZIP_CODE"));

                shipmentDetails.setShipperCity(rs.getString("shipper_city"));
                shipmentDetails.setShipperState(rs.getString("shipper_state"));
                shipmentDetails.setShipperCountry(rs.getString("shipper_country"));
                shipmentDetails.setShipperZip(rs.getString("shipper_zipCode"));
                shipmentDetails.setWorldeEaseNum((rs.getString("World_Ease_Number")));
                shipmentDetails.setActualServiceBucket((rs.getLong("actual_service_bucket")));

                parcelUpsShipments.add(shipmentDetails);

                try{
                    if(senderCountry == null && shipmentDetails.getSenderCountry() != null && !shipmentDetails.getSenderCountry().isEmpty()){
                        senderCountry = shipmentDetails.getSenderCountry();
                    }
                    if(senderState == null && shipmentDetails.getSenderState() != null && !shipmentDetails.getSenderState().isEmpty()){
                        senderState = shipmentDetails.getSenderState();
                    }
                    if(senderCity == null && shipmentDetails.getSenderCity() != null && !shipmentDetails.getSenderCity().isEmpty()){
                        senderCity = shipmentDetails.getSenderCity();
                    }
                    if(senderZipCode == null && shipmentDetails.getSenderZipCode() != null && !shipmentDetails.getSenderZipCode().isEmpty()){
                        senderZipCode = shipmentDetails.getSenderZipCode();
                    }

                    if(receiverCountry == null && shipmentDetails.getReceiverCountry() != null && !shipmentDetails.getReceiverCountry().isEmpty()){
                        receiverCountry = shipmentDetails.getReceiverCountry();
                    }
                    if(receiverState == null && shipmentDetails.getReceiverState() != null && !shipmentDetails.getReceiverState().isEmpty()){
                        receiverState = shipmentDetails.getReceiverState();
                    }
                    if(receiverCity == null && shipmentDetails.getReceiverCity() != null && !shipmentDetails.getReceiverCity().isEmpty()){
                        receiverCity = shipmentDetails.getReceiverCity();
                    }
                    if(receiverZipCode == null && shipmentDetails.getReceiverZipCode() != null && !shipmentDetails.getReceiverZipCode().isEmpty()){
                        receiverZipCode = shipmentDetails.getReceiverZipCode();
                    }

                    if (zone == null && shipmentDetails.getZone() != null && !shipmentDetails.getZone().isEmpty()) {
                        zone = shipmentDetails.getZone();
                    }

                }catch (Exception e){
                    e.printStackTrace();
                }
            }

            String finalSenderCountry = senderCountry;
            String finalSenderState = senderState;
            String finalSenderCity = senderCity;
            String finalSenderZipCode = senderZipCode;
            String finalReceiverCountry = receiverCountry;
            String finalReceiverState = receiverState;
            String finalReceiverCity = receiverCity;
            String finalReceiverZipCode = receiverZipCode;
            String finalZone = zone;
            parcelUpsShipments.parallelStream().filter(r -> r != null).forEach(
                    rate -> {
                        if(rate.getSenderCountry() == null || rate.getSenderCountry().isEmpty()){
                            rate.setSenderCountry(finalSenderCountry);
                        }
                        if(rate.getSenderState() == null || rate.getSenderState().isEmpty()){
                            rate.setSenderState(finalSenderState);
                        }
                        if(rate.getSenderCity() == null || rate.getSenderCity().isEmpty()){
                            rate.setSenderCity(finalSenderCity);
                        }
                        if(rate.getSenderZipCode() == null || rate.getSenderZipCode().isEmpty()){
                            rate.setSenderZipCode(finalSenderZipCode);
                        }
                        if (rate.getSenderBilledZipCode() == null || rate.getSenderBilledZipCode().isEmpty()) {
                            rate.setSenderBilledZipCode(finalSenderZipCode);
                        }

                        if(rate.getReceiverCountry() == null || rate.getReceiverCountry().isEmpty()){
                            rate.setReceiverCountry(finalReceiverCountry);
                        }
                        if(rate.getReceiverState() == null || rate.getReceiverState().isEmpty()){
                            rate.setReceiverState(finalReceiverState);
                        }
                        if(rate.getReceiverCity() == null || rate.getReceiverCity().isEmpty()){
                            rate.setReceiverCity(finalReceiverCity);
                        }
                        if(rate.getReceiverZipCode() == null || rate.getReceiverZipCode().isEmpty()){
                            rate.setReceiverZipCode(finalReceiverZipCode);
                        }
                        if (rate.getReceiverBilledZipCode() == null || rate.getReceiverBilledZipCode().isEmpty()) {
                            rate.setReceiverBilledZipCode(finalReceiverZipCode);
                        }
                        if (rate.getZone() == null || rate.getZone().isEmpty()) {
                            rate.setZone(finalZone);
                        }
                    }
            );
        }catch (Exception e){
            e.printStackTrace();
            throw new DaoException("Exception in getUpsParcelShipmentDetails", e);
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
                if (conn != null)
                    conn.close();
            } catch (SQLException sqle) {
            }
        }
        return parcelUpsShipments;
    }

    public List<ParcelAuditDetailsDto> getNonUpsParcelShipmentDetails(String customerIds, String carrierIds, String fromDate, String toDate, String trackingNumbers, String invoiceId, boolean ignoreRtrStatus, boolean isHwt) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<ParcelAuditDetailsDto> parcelUpsShipments = null;

        try {
            conn = ServiceLocator.getDatabaseConnection();

            String liveSqlQuery = " SELECT ebmf.EBILL_MANIFEST_ID AS ID, cp.CUSTOMER_ID, cp.CUSTOMER_CODE, c.CARRIER_ID, ebmf.CONTRACT_NUMBER, ";
            liveSqlQuery += " ebmf.BILL_OPT AS BILL_OPTION_CODE, c.RTR_SCAC_CODE, c.SCAC_CODE, ebmf.TRAN_CODE, ";
            liveSqlQuery += " null AS CHARGE_CLASSIFICATION_CODE, ebmf.CHARGE_CODE AS CHARGE_DESCRIPTION_CODE, ebmf.SERVICE AS CHARGE_DESCRIPTION, ";
            liveSqlQuery += " ebmf.BILL_WEIGHT AS PACKAGE_WEIGHT, ebmf.TRACKING_NUMBER, ebmf.PICKUP_DATE, ebmf.TRAN_CODE AS RESIDENTIAL_INDICATOR, ";
            liveSqlQuery += " ebmf.DELIVERY_DATE, ebmf.SENDER_COUNTRY AS SENDER_COUNTRY, ebmf.SENDER_ST AS SENDER_STATE, ";
            liveSqlQuery += " ebmf.SENDER_CITY AS SENDER_CITY, ebmf.SENDER_ZIP AS SENDER_ZIP_CODE, ebmf.CONSIGNEE_COUNTRY AS RECEIVER_COUNTRY, ";
            liveSqlQuery += " ebmf.CONSIGNEE_ST AS RECEIVER_STATE, ebmf.CONSIGNEE_CITY AS RECEIVER_CITY, ebmf.CONSIGNEE_ZIP AS RECEIVER_ZIP_CODE, ebmf.NET_CHARGES AS NET_AMOUNT, ";
            liveSqlQuery += " ebmf.QTY AS ITEM_QUANTITY, null AS  QUANTITY_UNIT, ebmf.UNIT_OF_BILL_WEIGHT AS WEIGHT_UNIT, ebmf.DIM_LENGTH, ebmf.HEIGHT AS DIM_HEIGHT, ";
            liveSqlQuery += " ebmf.WIDTH AS DIM_WIDTH, ebmf.UNIT_OF_DIM, ebmf.INVOICE_BILLING_CURRENCY_CODE AS CURRENCY, ebmf.INVOICE_ID, ";
            liveSqlQuery += " (select custom_defined_9 from shp_lookup_tb where lookup_id = ebmf.service_bucket) AS SERVICE_LEVEL, ebmf.DW_FIELD_INFORMATION, ";
            liveSqlQuery += " ebmf.SHIPPER_CODE AS SHIPPER_NUMBER, ebmf.PARENT_ID, DECODE (ebmf.bill_weight, 0, 'Letter', 'PKG') package_type, ";
            liveSqlQuery += " null AS PACKAGE_DIMENSION, ebmf.ACT_WEIGHT AS ACTUAL_WEIGHT, ebmf.UNIT_OF_ACTUAL_WEIGHT, ";
            liveSqlQuery += " ebmf.INVOICE_NUMBER, ebmf.ZONE, ebmf.MISCELLANEOUS5, ebmf.PIECES, ebmf.DIM_DIVISOR AS BILLED_DIM_DIVISOR, ebmf.BILL_DATE AS INVOICE_DATE, inv.CREATE_DATE AS INV_CREATE_DATE, ebmf.SENDER_ZIP AS SENDER_BILLED_ZIP_CODE, ebmf.CONSIGNEE_ZIP AS RECEIVER_BILLED_ZIP_CODE," +
                    "  s.STATE as shipper_state,s.CITY  as shipper_city,s.ZIPCODE  as shipper_zipCode,s.COUNTRY as shipper_country , ";
            if (isHwt) {
                liveSqlQuery += " 0 as RTR_AMOUNT ,null as rtr_status,";
            } else {
                liveSqlQuery += " ar.RTR_AMOUNT ,ar.rtr_status,";
            }
            liveSqlQuery += " ebmf.REVENUE_TIER AS REVENUE_TIER, ebmf.CHARGE_CODE,DECODE (Ebmf.Bundle_Number,NULL,ebmf.MISCELLANEOUS5, ebmf.Bundle_Number) AS MULTI_WEIGHT_NUMBER, null AS CHARGE_CATEGORY_DETAIL_CODE ";
            liveSqlQuery += " FROM SHP_EBILL_MANIFEST_TB ebmf, SHP_EBILL_CONTRACT_TB ebc, SHP_CUSTOMER_PROFILE_TB cp, SHP_CARRIER_TB c, SHP_SHIPPER_TB s, SHP_EBILL_INVOICE_TB inv ";
            if (!isHwt) {
                liveSqlQuery += ", SHP_EBILL_FDX_RATES_TB ar  ";
            }

            liveSqlQuery += " WHERE ebmf.CONTRACT_NUMBER = ebc.CONTRACT_NUMBER ";
            liveSqlQuery += " AND ebmf.INVOICE_ID = inv.INVOICE_ID ";
            liveSqlQuery += " AND ebc.CUSTOMER_ID = cp.CUSTOMER_ID ";
            liveSqlQuery += " AND ebmf.CARRIER_ID = c.CARRIER_ID ";
            liveSqlQuery += " AND ebmf.SHIPPER_CODE = s.SHIPPER_CODE ";
            if (!isHwt) {
                liveSqlQuery += " AND ebmf.ebill_manifest_id = ar.ebill_manifest_id(+) ";
            }
            liveSqlQuery += " AND ebmf.CARRIER_ID IN ( " + carrierIds + ") ";

            if(fromDate != null && !fromDate.isEmpty() && toDate != null && !toDate.isEmpty()) {
                liveSqlQuery += " AND TRUNC(ebmf.PICKUP_DATE) BETWEEN  '" + fromDate + "' AND '" + toDate + "'  ";
            }

            liveSqlQuery += " AND ebmf.DW_FIELD_INFORMATION IS NOT NULL ";

            if(customerIds != null && !customerIds.isEmpty()) {
                liveSqlQuery += " AND cp.CUSTOMER_ID IN( " + customerIds + ") ";
            }

            if(trackingNumbers != null && !trackingNumbers.isEmpty()) {

                if (StringUtils.containsIgnoreCase(trackingNumbers, ","))
                    liveSqlQuery += " AND ebmf.tracking_number IN (" + trackingNumbers + ") ";
                else
                    liveSqlQuery += " AND ebmf.tracking_number IN ('" + trackingNumbers + "') ";
            }

            if(invoiceId != null && !invoiceId.isEmpty()) {
                liveSqlQuery += " AND ebmf.invoice_id IN ( " + invoiceId + ") ";
            }

            if (!ignoreRtrStatus && !isHwt) {
                liveSqlQuery += " AND (UPPER(ar.RTR_STATUS) = 'READYFORRATE' OR ar.RTR_STATUS IS NULL) and (Ebmf.Bundle_Number is null OR Ebmf.MISCELLANEOUS5 is null)";
            }

            if (isHwt) {
                liveSqlQuery += " and (Ebmf.Bundle_Number IS NOT NULL OR Ebmf.MISCELLANEOUS5 IS NOT NULL) AND (Ebmf.ebill_manifest_id,DECODE (Ebmf.Bundle_Number,NULL,Ebmf.MISCELLANEOUS5,Ebmf.Bundle_Number)) in(SELECT ebill_manifest_id,DECODE (Bundle_Number,NULL,MISCELLANEOUS5,Bundle_Number) FROM  SHP_EBILL_MANIFEST_TB " +
                        " where invoice_id IN ( " + invoiceId + ") MINUS SELECT a.manifest_id,a.hwt_identifier FROM  Shp_Rating_Queue_Tb a," +
                        " SHP_EBILL_MANIFEST_TB b where a.manifest_id = b.ebill_manifest_id and b.invoice_id IN ( " + invoiceId + "))";

            }

            String archiveQuery = liveSqlQuery.replace("SHP_EBILL_MANIFEST_TB", "ARC_EBILL_MANIFEST_TB");
            archiveQuery = archiveQuery.replace("SHP_EBILL_INVOICE_TB", "ARC_EBILL_INVOICE_TB");

            ps = conn.prepareStatement(liveSqlQuery + " UNION " + archiveQuery);
            parcelUpsShipments = new ArrayList<>();

            rs = ps.executeQuery();
            String senderCountry = null;
            String senderState = null;
            String senderCity = null;
            String senderZipCode = null;

            String receiverCountry = null;
            String receiverState = null;
            String receiverCity = null;
            String receiverZipCode = null;
            String zone = null;
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
                shipmentDetails.setActualchargeDescriptionCode(rs.getString("CHARGE_DESCRIPTION_CODE"));
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
                        e.printStackTrace();
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
                shipmentDetails.setPackageType(rs.getString("PACKAGE_TYPE"));
                shipmentDetails.setRtrStatus(rs.getString("RTR_STATUS"));
                shipmentDetails.setMultiWeightNumber(rs.getString("MULTI_WEIGHT_NUMBER"));
                shipmentDetails.setChargeCategoryDetailCode(rs.getString("CHARGE_CATEGORY_DETAIL_CODE"));
                shipmentDetails.setChargeCode(rs.getString("CHARGE_CODE"));
                shipmentDetails.setInvoiceDate(rs.getDate("INVOICE_DATE"));
                shipmentDetails.setInvoiceCreateDate(rs.getDate("INV_CREATE_DATE"));
                shipmentDetails.setPieces(rs.getInt("PIECES"));
                shipmentDetails.setZone(rs.getString("ZONE"));
                shipmentDetails.setInvoiceNumber(rs.getString("INVOICE_NUMBER"));
                shipmentDetails.setBilledDimDivisor(rs.getString("BILLED_DIM_DIVISOR"));
                shipmentDetails.setSenderBilledZipCode(rs.getString("SENDER_BILLED_ZIP_CODE"));
                shipmentDetails.setReceiverBilledZipCode(rs.getString("RECEIVER_BILLED_ZIP_CODE"));

                shipmentDetails.setShipperCity(rs.getString("shipper_city"));
                shipmentDetails.setShipperState(rs.getString("shipper_state"));
                shipmentDetails.setShipperCountry(rs.getString("shipper_country"));
                shipmentDetails.setShipperZip(rs.getString("shipper_zipCode"));

                //Need to add charge code here.
                parcelUpsShipments.add(shipmentDetails);

                try{
                    if(senderCountry == null && shipmentDetails.getSenderCountry() != null && !shipmentDetails.getSenderCountry().isEmpty()){
                        senderCountry = shipmentDetails.getSenderCountry();
                    }
                    if(senderState == null && shipmentDetails.getSenderState() != null && !shipmentDetails.getSenderState().isEmpty()){
                        senderState = shipmentDetails.getSenderState();
                    }
                    if(senderCity == null && shipmentDetails.getSenderCity() != null && !shipmentDetails.getSenderCity().isEmpty()){
                        senderCity = shipmentDetails.getSenderCity();
                    }
                    if(senderZipCode == null && shipmentDetails.getSenderZipCode() != null && !shipmentDetails.getSenderZipCode().isEmpty()){
                        senderZipCode = shipmentDetails.getSenderZipCode();
                    }

                    if(receiverCountry == null && shipmentDetails.getReceiverCountry() != null && !shipmentDetails.getReceiverCountry().isEmpty()){
                        receiverCountry = shipmentDetails.getReceiverCountry();
                    }
                    if(receiverState == null && shipmentDetails.getReceiverState() != null && !shipmentDetails.getReceiverState().isEmpty()){
                        receiverState = shipmentDetails.getReceiverState();
                    }
                    if(receiverCity == null && shipmentDetails.getReceiverCity() != null && !shipmentDetails.getReceiverCity().isEmpty()){
                        receiverCity = shipmentDetails.getReceiverCity();
                    }
                    if(receiverZipCode == null && shipmentDetails.getReceiverZipCode() != null && !shipmentDetails.getReceiverZipCode().isEmpty()){
                        receiverZipCode = shipmentDetails.getReceiverZipCode();
                    }
                    if (zone == null && shipmentDetails.getZone() != null && !shipmentDetails.getZone().isEmpty()) {
                        zone = shipmentDetails.getZone();
                    }

                }catch (Exception e){
                    e.printStackTrace();
                }
            }

            String finalSenderCountry = senderCountry;
            String finalSenderState = senderState;
            String finalSenderCity = senderCity;
            String finalSenderZipCode = senderZipCode;
            String finalReceiverCountry = receiverCountry;
            String finalReceiverState = receiverState;
            String finalReceiverCity = receiverCity;
            String finalReceiverZipCode = receiverZipCode;
            String finalZone = zone;
            parcelUpsShipments.parallelStream().filter(r -> r != null).forEach(
                    rate -> {
                        if(rate.getSenderCountry() == null || rate.getSenderCountry().isEmpty()){
                            rate.setSenderCountry(finalSenderCountry);
                        }
                        if(rate.getSenderState() == null || rate.getSenderState().isEmpty()){
                            rate.setSenderState(finalSenderState);
                        }
                        if(rate.getSenderCity() == null || rate.getSenderCity().isEmpty()){
                            rate.setSenderCity(finalSenderCity);
                        }
                        if(rate.getSenderZipCode() == null || rate.getSenderZipCode().isEmpty()){
                            rate.setSenderZipCode(finalSenderZipCode);
                        }

                        if(rate.getReceiverCountry() == null || rate.getReceiverCountry().isEmpty()){
                            rate.setReceiverCountry(finalReceiverCountry);
                        }
                        if(rate.getReceiverState() == null || rate.getReceiverState().isEmpty()){
                            rate.setReceiverState(finalReceiverState);
                        }
                        if(rate.getReceiverCity() == null || rate.getReceiverCity().isEmpty()){
                            rate.setReceiverCity(finalReceiverCity);
                        }
                        if(rate.getReceiverZipCode() == null || rate.getReceiverZipCode().isEmpty()){
                            rate.setReceiverZipCode(finalReceiverZipCode);
                        }
                        if (rate.getZone() == null || rate.getZone().isEmpty()) {
                            rate.setZone(finalZone);
                        }
                    }
            );
        }catch (Exception e){
            e.printStackTrace();
            throw new DaoException("Exception in getNonUpsParcelShipmentDetails", e);
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
                if (conn != null)
                    conn.close();
            } catch (SQLException sqle) {
            }
        }
        return parcelUpsShipments;
    }

    public void saveRatingQueueBean(List<RatingQueueBean> queueBeanList) throws SQLException {

        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            connection = ServiceLocator.getDatabaseConnection();
            connection.setAutoCommit(false);
            String insertQuery = RatingDAOUtil.prepareRatingQueueInsertQuery(true);
            ps = connection.prepareStatement(insertQuery);
            for (RatingQueueBean bean : queueBeanList) {
                RatingDAOUtil.setSqlStatementPlaceHolderValues(ps, bean);
                ps.addBatch();
            }
            ps.executeBatch();
            connection.commit();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            connection.rollback();
            log.error("Exception in saveRatingQueueBean-- > " + sqle.getStackTrace());
            throw new DaoException("Exception in saveRatingQueueBean", sqle);
        } catch (ServiceLocatorException sle) {
            log.error("Exception in saveRatingQueueBean-- > " + sle.getStackTrace());
            throw new DaoException("Exception in saveRatingQueueBean", sle);
        } finally {
            if (rs != null)
                rs.close();
            if (ps != null)
                ps.close();
            if (connection != null)
                connection.close();
        }
    }

    public boolean shipmentExist(Long parentId){
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            connection = ServiceLocator.getDatabaseConnection();
            String sqlQuery = "select count(1) shipment_count from shp_rating_queue_tb where parent_id = ? and rate_status = 0";
            ps = connection.prepareStatement(sqlQuery);
            ps.setLong(1, parentId);
            rs = ps.executeQuery();
            if (rs.next()) {
                if (rs.getInt("shipment_count") > 0) {
                    return true;
                }
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            throw new DaoException("Exception in shipmentExist", sqle);
        } catch (ServiceLocatorException sle) {
            throw new DaoException("Exception in shipmentExist", sle);
        } finally {
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
        return false;
    }

    public boolean hwtShipmentExist(String trackingNumber, Date billDate) {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            connection = ServiceLocator.getDatabaseConnection();
            String sqlQuery = "select count(1) shipment_count from shp_rating_queue_tb where tracking_number = ? AND INVOICE_DATE =? and rate_status = 0";
            ps = connection.prepareStatement(sqlQuery);

            ps.setString(1, trackingNumber);
            if (billDate != null) {
                ps.setDate(2, new java.sql.Date(billDate.getTime()));
            } else {
                ps.setNull(2, Types.DATE);
            }

            rs = ps.executeQuery();
            if(rs.next()) {
                if(rs.getInt("shipment_count") > 0) {
                    return true;
                }
            }
        }catch (SQLException sqle) {
            sqle.printStackTrace();
            throw new DaoException("Exception in shipmentExist", sqle);
        }  catch (ServiceLocatorException sle) {
            throw new DaoException("Exception in shipmentExist", sle);
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
        return false;
    }
}
