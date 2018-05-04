package com.envista.msi.rating;

import com.envista.msi.api.domain.util.ParcelRatingUtil;
import com.envista.msi.api.web.rest.dto.rtr.ParcelAuditDetailsDto;
import com.envista.msi.api.web.rest.util.CommonUtil;
import com.envista.msi.api.web.rest.util.audit.parcel.ParcelAuditConstant;
import com.envista.msi.api.web.rest.util.audit.parcel.ParcelRateRequestBuilder;
import com.envista.msi.api.web.rest.util.audit.parcel.ParcelRateResponseParser;
import com.envista.msi.rating.bean.RatingQueueBean;
import com.envista.msi.rating.dao.DirectJDBCDAO;
import com.envista.msi.rating.dao.RatingQueueDAO;
import com.envista.msi.rating.entity.RatingQueue;

import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import java.util.concurrent.Callable;
import java.io.File;
import java.io.FileInputStream;
import java.io.PrintStream;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PropertyResourceBundle;
import java.util.Collection;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;


import com.envista.msi.rating.service.ParcelRatingService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class ParcelRating implements Callable<String> {

    private static final int MAX_THREADS = 5;

    private RatingQueueBean ratingQueueBean = null;
    private Log m_log = LogFactory.getLog(ParcelRating.class);

    public ParcelRating() { }

    public ParcelRating(RatingQueueBean bean) {
        this.ratingQueueBean = bean;
    }

    /*public void getRecord(){

        EntityManager entitymanager = ServiceLocator.getEntityManager();
        RatingQueue ratingQueue = entitymanager.find( RatingQueue.class, 12 );

        System.out.println("ratingQueue ID = " + ratingQueue.getRatingQueueId());
        System.out.println("ratingQueue manifest id = " + ratingQueue.getManiestId());
        System.out.println("ratingQueue bill option = " + ratingQueue.getBillOption());
        System.out.println("ratingQueue rating status = " + ratingQueue.getRatingStatus());
        entitymanager.close();
    }*/

    public void processRating(String jobIds) throws Exception{

        RatingQueueDAO ratingQueueDao = new RatingQueueDAO();
        ArrayList<RatingQueueBean> beanList = ratingQueueDao.getRatingQueueByJobId(jobIds);


        System.out.println("in processExcelUpload .....");
        if (beanList != null) {
            ExecutorService executor = Executors.newFixedThreadPool(MAX_THREADS);
            for(RatingQueueBean bean :beanList){
                Callable<String> callableTask = new ParcelRating(bean);
                executor.submit(callableTask);
            }
            try {
                executor.shutdown();
                // Wait until all threads are finish
                executor.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
            } catch (Exception e) {
                m_log.error("Executor Shutdown Interrupted..." + e);
                e.printStackTrace();
            }
        }
    }
    @Override
    public String call() throws Exception {
        System.out.println("in call ....."+this.ratingQueueBean.getRatingQueueId());
        processParcelRating(this.ratingQueueBean);
        return "Success";
    }
    public void processParcelRating(RatingQueueBean bean) throws Exception {
        String requestPayload = "";
        String response = "";
        String status = null;
        String url = ParcelAuditConstant.AR_RATE_REQUEST_PROTOCOL + "://" + ParcelAuditConstant.AR_RATE_REQUEST_HOST_NAME + "/" + ParcelAuditConstant.AR_RATE_REQUEST_URI_PATH;

        DirectJDBCDAO directJDBCDAO = new DirectJDBCDAO();
        ParcelRatingService parcelRatingService = new ParcelRatingService();
        if(bean.getCarrierId() == 21){


        } else if(bean.getCarrierId() == 22) {
            List<ParcelAuditDetailsDto> allShipmentCharges = parcelRatingService.getFedExParcelShipmentDetails(bean.getTrackingNumber());
            Map<Long, List<ParcelAuditDetailsDto>> shipments = ParcelRatingUtil.organiseShipmentsByParentId(allShipmentCharges);

            if(ParcelRatingUtil.hasMultipleParentIds(shipments)) {
                List<ParcelAuditDetailsDto> shipmentToRate = shipments.get(bean.getParentId());
                if(shipmentToRate != null && !shipmentToRate.isEmpty()) {

                }
            } else {
                requestPayload = com.envista.msi.rating.util.ParcelRateRequestBuilder.buildParcelRateRequest(bean, ParcelAuditConstant.AR_RATE_REQUEST_LICENSE_KEY).toXmlString();
                response = CommonUtil.connectAndGetResponseAsString(url, requestPayload);
                if(response != null && !response.trim().isEmpty()) {
                    directJDBCDAO.saveParcelAuditRequestAndResponseLog(ParcelRatingUtil.prepareRequestResponseLog(requestPayload, response, bean.getParentId(), ParcelAuditConstant.EBILL_MANIFEST_TABLE_NAME));
                    status = parcelRatingService.updateRateForNonUpsCarrier(ParcelRateResponseParser.parse(response), allShipmentCharges, parcelRatingService.getAllMappedARChargeCodes());
                }
            }
        }

        RatingQueueDAO ratingQueueDAO = new RatingQueueDAO();
        ratingQueueDAO.updateRateStatusInQueue(bean.getRatingQueueId());
    }

}
