package com.envista.msi.rating.dao;

import com.envista.msi.api.web.rest.util.audit.parcel.ParcelAuditConstant;
import com.envista.msi.rating.bean.RatingQueueBean;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Sujit kumar on 02/05/2018.
 */
public class RatingDAOUtil {
    public static List<String> RATING_QUEUE_COLUMN_NAMES = new ArrayList<>();
    static {
        RATING_QUEUE_COLUMN_NAMES.add("SHP_RATING_QUEUE_ID");
        RATING_QUEUE_COLUMN_NAMES.add("MANIFEST_ID");
        RATING_QUEUE_COLUMN_NAMES.add("GFF_ID");
        RATING_QUEUE_COLUMN_NAMES.add("CUSTOMER_CODE");
        RATING_QUEUE_COLUMN_NAMES.add("SCAC_CODE");
        RATING_QUEUE_COLUMN_NAMES.add("CONTRACT_NAME");
        RATING_QUEUE_COLUMN_NAMES.add("SERVICE");
        RATING_QUEUE_COLUMN_NAMES.add("ACCESORIAL1");
        RATING_QUEUE_COLUMN_NAMES.add("ACCESORIAL2");
        RATING_QUEUE_COLUMN_NAMES.add("ACCESORIAL3");
        RATING_QUEUE_COLUMN_NAMES.add("ACCESORIAL4");
        RATING_QUEUE_COLUMN_NAMES.add("ACCESORIAL5");
        RATING_QUEUE_COLUMN_NAMES.add("BILL_OPTION");
        RATING_QUEUE_COLUMN_NAMES.add("CURRENCY_CODE");
        RATING_QUEUE_COLUMN_NAMES.add("SHIPPER_NUMBER");
        RATING_QUEUE_COLUMN_NAMES.add("TRANSIT_TIME");
        RATING_QUEUE_COLUMN_NAMES.add("VERBOSE");
        RATING_QUEUE_COLUMN_NAMES.add("TOTAL_WEIGHT");
        RATING_QUEUE_COLUMN_NAMES.add("TOTAL_ACTUAL_WEIGHT");
        RATING_QUEUE_COLUMN_NAMES.add("TOTAL_QUANTITY");
        RATING_QUEUE_COLUMN_NAMES.add("BILLED_MILES");
        RATING_QUEUE_COLUMN_NAMES.add("SHIP_DATE");
        RATING_QUEUE_COLUMN_NAMES.add("SHIPPER_LOCATION_CODE");
        RATING_QUEUE_COLUMN_NAMES.add("SHIPPER_CITY");
        RATING_QUEUE_COLUMN_NAMES.add("SHIPPER_STATE");
        RATING_QUEUE_COLUMN_NAMES.add("SHIPPER_ZIP");
        RATING_QUEUE_COLUMN_NAMES.add("SHIPPER_COUNTRY");
        RATING_QUEUE_COLUMN_NAMES.add("RECEIVER_LOCATION_CODE");
        RATING_QUEUE_COLUMN_NAMES.add("RECEIVER_CITY");
        RATING_QUEUE_COLUMN_NAMES.add("RECEIVER_STATE");
        RATING_QUEUE_COLUMN_NAMES.add("RECEIVER_ZIP");
        RATING_QUEUE_COLUMN_NAMES.add("RECEIVER_COUNTRY");
        RATING_QUEUE_COLUMN_NAMES.add("IS_HUNDRED_WEIGHT");
        RATING_QUEUE_COLUMN_NAMES.add("JOB_ID");
        RATING_QUEUE_COLUMN_NAMES.add("CREATE_USER");
        RATING_QUEUE_COLUMN_NAMES.add("CREATE_DATE");
        RATING_QUEUE_COLUMN_NAMES.add("CARRIER_ID");
        RATING_QUEUE_COLUMN_NAMES.add("PARENT_ID");
        RATING_QUEUE_COLUMN_NAMES.add("RATE_STATUS");
        RATING_QUEUE_COLUMN_NAMES.add("ACCESSORIAL_INFO");
        RATING_QUEUE_COLUMN_NAMES.add("DALIVERY_DATE");
        RATING_QUEUE_COLUMN_NAMES.add("TRACKING_NUMBER");
        RATING_QUEUE_COLUMN_NAMES.add("REVENUE_TIER");
        RATING_QUEUE_COLUMN_NAMES.add("PACKAGE_TYPE");
        RATING_QUEUE_COLUMN_NAMES.add("HWT_IDENTIFIER");
        RATING_QUEUE_COLUMN_NAMES.add("RATE_SET_NAME");
        RATING_QUEUE_COLUMN_NAMES.add("TASK_ID");
        RATING_QUEUE_COLUMN_NAMES.add("THRESHOLD_VALUE");
        RATING_QUEUE_COLUMN_NAMES.add("THRESHOLD_TYPE");
        RATING_QUEUE_COLUMN_NAMES.add("SERVICE_LEVELS_ID");
        RATING_QUEUE_COLUMN_NAMES.add("ZONE");
        RATING_QUEUE_COLUMN_NAMES.add("SENDER_BILLED_ZIP_CODE");
        RATING_QUEUE_COLUMN_NAMES.add("RECEIVER_BILLED_ZIP_CODE");
        RATING_QUEUE_COLUMN_NAMES.add("RETURN_FLAG");
        RATING_QUEUE_COLUMN_NAMES.add("RESI_FLAG");
        RATING_QUEUE_COLUMN_NAMES.add("WORLD_EASE_NUM");
        RATING_QUEUE_COLUMN_NAMES.add("COM_TO_RES");
        RATING_QUEUE_COLUMN_NAMES.add("PRP_FLAG");
        RATING_QUEUE_COLUMN_NAMES.add("ACTUAL_SERVICE_BUCKET");
        RATING_QUEUE_COLUMN_NAMES.add("INVOICE_DATE");
        RATING_QUEUE_COLUMN_NAMES.add("ITEM_TAGS");
        RATING_QUEUE_COLUMN_NAMES.add("CUSTOMER_ID");
        RATING_QUEUE_COLUMN_NAMES.add("EXCLUDE_RATING");
    }

    public static String prepareRatingQueueInsertQuery(boolean isHwt) {
        Iterator<String> columnIterator = RATING_QUEUE_COLUMN_NAMES.listIterator();
        StringBuffer sqlQuery = new StringBuffer("INSERT INTO SHP_RATING_QUEUE_TB (");
        StringBuffer questionmark = new StringBuffer("SHP_RATING_QUEUE_S.NEXTVAL");
        sqlQuery.append(columnIterator.next());
        while (columnIterator.hasNext()) {
            String columnName = columnIterator.next();
            sqlQuery.append(" , " + columnName);
            if("JOB_ID".equalsIgnoreCase(columnName)){
                if (!isHwt)
                    questionmark.append("," + ParcelAuditConstant.RATE_JOB_ID_SEQUENCE_VALUE);
                else
                    questionmark.append(",99");
            } else {
                questionmark.append(",?");
            }


        }
        sqlQuery.append(") values (").append(questionmark).append(")");
        return sqlQuery.toString();
    }

    public static void setSqlStatementPlaceHolderValues(PreparedStatement ps, RatingQueueBean queueBean) throws SQLException {
        for(String columnName : RATING_QUEUE_COLUMN_NAMES) {
            if("SHP_RATING_QUEUE_ID".equalsIgnoreCase(columnName)) {
                ;
            } else if("MANIFEST_ID".equalsIgnoreCase(columnName)) {
                if(queueBean.getManiestId() != null){
                    ps.setLong(1, queueBean.getManiestId());
                } else {
                    ps.setNull(1, Types.INTEGER);
                }

            } else if("GFF_ID".equalsIgnoreCase(columnName)) {
                if(queueBean.getGffId() != null) {
                    ps.setLong(2, queueBean.getGffId());
                } else {
                    ps.setNull(2, Types.INTEGER);
                }

            } else if("CUSTOMER_CODE".equalsIgnoreCase(columnName)) {
                ps.setString(3, queueBean.getCustomerCode());
            } else if("SCAC_CODE".equalsIgnoreCase(columnName)) {
                ps.setString(4, queueBean.getScacCode());
            } else if("CONTRACT_NAME".equalsIgnoreCase(columnName)) {
                ps.setString(5, queueBean.getContractName());
            } else if("SERVICE".equalsIgnoreCase(columnName)) {
                ps.setString(6, queueBean.getService());
            } else if("ACCESORIAL1".equalsIgnoreCase(columnName)) {
                ps.setString(7, queueBean.getAccesorial1());
            } else if("ACCESORIAL2".equalsIgnoreCase(columnName)) {
                ps.setString(8, queueBean.getAccesorial2());
            } else if("ACCESORIAL3".equalsIgnoreCase(columnName)) {
                ps.setString(9, queueBean.getAccesorial3());
            } else if("ACCESORIAL4".equalsIgnoreCase(columnName)) {
                ps.setString(10, queueBean.getAccesorial4());
            } else if("ACCESORIAL5".equalsIgnoreCase(columnName)) {
                ps.setString(11, queueBean.getAccesorial5());
            } else if("BILL_OPTION".equalsIgnoreCase(columnName)) {
                ps.setString(12, queueBean.getBillOption());
            } else if("CURRENCY_CODE".equalsIgnoreCase(columnName)) {
                ps.setString(13, queueBean.getCurrencyCode());
            } else if("SHIPPER_NUMBER".equalsIgnoreCase(columnName)) {
                ps.setString(14, queueBean.getShipperNumber());
            } else if("TRANSIT_TIME".equalsIgnoreCase(columnName)) {
                ps.setFloat(15, queueBean.getTransitTime());
            } else if("VERBOSE".equalsIgnoreCase(columnName)) {
                ps.setInt(16, queueBean.getVerbose());
            } else if("TOTAL_WEIGHT".equalsIgnoreCase(columnName)) {
                ps.setFloat(17, queueBean.getTotalWeight());
            } else if("TOTAL_ACTUAL_WEIGHT".equalsIgnoreCase(columnName)) {
                ps.setFloat(18, queueBean.getTotalActualWeight());
            } else if("TOTAL_QUANTITY".equalsIgnoreCase(columnName)) {
                ps.setFloat(19, queueBean.getTotalQuantity());
            } else if("BILLED_MILES".equalsIgnoreCase(columnName)) {
                if(queueBean.getBilledMiles() != null){
                    ps.setFloat(20, queueBean.getBilledMiles());
                } else {
                    ps.setNull(20, Types.FLOAT);
                }

            } else if("SHIP_DATE".equalsIgnoreCase(columnName)) {
                if(queueBean.getShipDate() != null){
                    ps.setDate(21, new java.sql.Date(queueBean.getShipDate().getTime()));
                } else {
                    ps.setNull(21, Types.DATE);
                }
            } else if("SHIPPER_LOCATION_CODE".equalsIgnoreCase(columnName)) {
                ps.setString( 22, queueBean.getShipperLocationCode());
            } else if("SHIPPER_CITY".equalsIgnoreCase(columnName)) {
                ps.setString( 23, queueBean.getShipperCity());
            } else if("SHIPPER_STATE".equalsIgnoreCase(columnName)) {
                ps.setString( 24, queueBean.getShipperState());
            } else if("SHIPPER_ZIP".equalsIgnoreCase(columnName)) {
                ps.setString( 25, queueBean.getShipperZip());
            } else if("SHIPPER_COUNTRY".equalsIgnoreCase(columnName)) {
                ps.setString( 26, queueBean.getShipperCountry());
            } else if("RECEIVER_LOCATION_CODE".equalsIgnoreCase(columnName)) {
                ps.setString( 27, queueBean.getReceiverLocationCode());
            }  else if("RECEIVER_CITY".equalsIgnoreCase(columnName)) {
                ps.setString( 28, queueBean.getReceiverCity());
            } else if("RECEIVER_STATE".equalsIgnoreCase(columnName)) {
                ps.setString( 29, queueBean.getReceiverState());
            } else if("RECEIVER_ZIP".equalsIgnoreCase(columnName)) {
                ps.setString(30, queueBean.getReceiverZip());
            } else if("RECEIVER_COUNTRY".equalsIgnoreCase(columnName)) {
                ps.setString(31, queueBean.getReceiverCountry());
            } else if("IS_HUNDRED_WEIGHT".equalsIgnoreCase(columnName)) {
                ps.setInt(32, queueBean.getHundredWeight() != null && queueBean.getHundredWeight() ? 1 : 0);
            } else if("JOB_ID".equalsIgnoreCase(columnName)) {
                ;
            } else if("CREATE_USER".equalsIgnoreCase(columnName)) {
                ps.setString(33, queueBean.getCreateUser());
            } else if("CREATE_DATE".equalsIgnoreCase(columnName)) {
                ps.setDate(34, new java.sql.Date(System.currentTimeMillis()));
            } else if("CARRIER_ID".equalsIgnoreCase(columnName)) {
                if(queueBean.getCarrierId() != null) {
                    ps.setLong(35, queueBean.getCarrierId());
                } else {
                    ps.setNull(35, Types.INTEGER);
                }
            } else if("PARENT_ID".equalsIgnoreCase(columnName)) {
                if(queueBean.getParentId() != null) {
                    ps.setLong(36, queueBean.getParentId());
                } else {
                    ps.setNull(36, Types.INTEGER);
                }
            } else if("RATE_STATUS".equalsIgnoreCase(columnName)) {
                ps.setInt(37, queueBean.getRateStatus() != null ? queueBean.getRateStatus() : 0);
            } else if("ACCESSORIAL_INFO".equalsIgnoreCase(columnName)) {
                ps.setString(38, queueBean.getAccessorialInfo());
            } else if("DALIVERY_DATE".equalsIgnoreCase(columnName)) {
                if(queueBean.getDeliveryDate() != null) {
                    ps.setDate(39, new java.sql.Date(queueBean.getDeliveryDate().getTime()));
                } else {
                    ps.setNull(39, Types.DATE);
                }
            } else if("TRACKING_NUMBER".equalsIgnoreCase(columnName)) {
                ps.setString(40, queueBean.getTrackingNumber());
            } else if("REVENUE_TIER".equalsIgnoreCase(columnName)) {
                ps.setString(41, queueBean.getRevenueTier());
            } else if("PACKAGE_TYPE".equalsIgnoreCase(columnName)) {
                ps.setString(42, queueBean.getPackageType());
            } else if ("HWT_IDENTIFIER".equalsIgnoreCase(columnName)) {
                ps.setString(43, queueBean.getHwtIdentifier());
            } else if("RATE_SET_NAME".equalsIgnoreCase(columnName)){
                ps.setString(44, queueBean.getRateSetName());
            } else if("TASK_ID".equalsIgnoreCase(columnName)){
                if(queueBean.getTaskId() != null){
                    ps.setLong(45, queueBean.getTaskId());
                } else {
                    ps.setNull(45, Types.INTEGER);
                }
            } else if ("THRESHOLD_VALUE".equalsIgnoreCase(columnName)){
                ps.setString(46, queueBean.getThresholdValue());
            } else if("THRESHOLD_TYPE".equalsIgnoreCase(columnName)) {
                ps.setString(47, queueBean.getThresholdType());
            } else if("SERVICE_LEVELS_ID".equalsIgnoreCase(columnName)) {
                ps.setString(48,queueBean.getServiceLevel());
            } else if("ZONE".equalsIgnoreCase(columnName)) {
                ps.setString(49, queueBean.getZone());
            } else if("SENDER_BILLED_ZIP_CODE".equalsIgnoreCase(columnName)) {
                ps.setString(50, queueBean.getSenderBilledZipCode());
            } else if("RECEIVER_BILLED_ZIP_CODE".equalsIgnoreCase(columnName)) {
                ps.setString(51, queueBean.getReceiverBilledZipCode());
            } else if ("RETURN_FLAG".equalsIgnoreCase(columnName)) {
                ps.setString(52, queueBean.getReturnFlag());
            } else if ("RESI_FLAG".equalsIgnoreCase(columnName)) {
                ps.setString(53, queueBean.getResiFlag());
            } else if ("WORLD_EASE_NUM".equalsIgnoreCase(columnName)) {
                ps.setString(54, queueBean.getWorldeEaseNum());
            } else if ("COM_TO_RES".equalsIgnoreCase(columnName)) {
                ps.setString(55, queueBean.getComToRes());
            } else if ("PRP_FLAG".equalsIgnoreCase(columnName)) {
                ps.setString(56, queueBean.getPrpFlag());
            } else if ("ACTUAL_SERVICE_BUCKET".equalsIgnoreCase(columnName)) {
                ps.setLong(57, queueBean.getActualServiceBucket());
            } else if ("INVOICE_DATE".equalsIgnoreCase(columnName)) {
                if (queueBean.getInvoiceDate() != null) {
                    ps.setDate(58, new java.sql.Date(queueBean.getInvoiceDate().getTime()));
                } else {
                    ps.setNull(58, Types.DATE);
                }
            } else if ("ITEM_TAGS".equalsIgnoreCase(columnName)) {
                ps.setString(59, queueBean.getItemTagInfo());
            } else if ("CUSTOMER_ID".equalsIgnoreCase(columnName)) {
                if (queueBean.getCustomerId() != null) {
                    ps.setLong(60, queueBean.getCustomerId());
                } else {
                    ps.setNull(60, Types.INTEGER);
                }
            } else if ("EXCLUDE_RATING".equalsIgnoreCase(columnName))
                ps.setInt(61, queueBean.getExcludeRating());
            else {
                throw new RuntimeException("Column name not mapped");
            }
        }
    }
}
