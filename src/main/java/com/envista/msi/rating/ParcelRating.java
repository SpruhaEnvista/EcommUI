package com.envista.msi.rating;

import com.envista.msi.api.domain.util.ParcelRatingUtil;
import com.envista.msi.api.web.rest.util.audit.parcel.ParcelAuditConstant;
import com.envista.msi.rating.bean.RatingQueueBean;
import com.envista.msi.rating.dao.RatingQueueDAO;
import com.envista.msi.rating.service.ParcelNonUpsRatingService;
import com.envista.msi.rating.service.ParcelUpsRatingService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

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


        System.out.println("in process parcel rating .....");
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
        ParcelUpsRatingService parcelUpsRatingService = new ParcelUpsRatingService();
        ParcelNonUpsRatingService nonUpsRatingService = new ParcelNonUpsRatingService();
        String status = null;
        if(bean.getCarrierId() == 21){
            status = parcelUpsRatingService.doParcelRatingForUpsCarrier(bean);
            System.out.println("Rating : " + bean.getTrackingNumber() + " : Status : " + status);
        } else if(bean.getCarrierId() == 22) {
            status = nonUpsRatingService.doRatingForNonUpsShipment(bean);
            System.out.println("Rating : " + bean.getTrackingNumber() + " : Status : " + status);
        }

        if (status != null && !status.isEmpty()) {
            RatingQueueDAO ratingQueueDAO = new RatingQueueDAO();
            if (ParcelAuditConstant.RTRStatus.RATING_EXCEPTION.value.equalsIgnoreCase(status)) {
                ratingQueueDAO.updateRateStatusInQueue(bean.getRatingQueueId(), ParcelAuditConstant.ParcelRatingQueueRateStatus.RATING_EXCEPTION.value);
            } else if (ParcelAuditConstant.RTRStatus.NO_PRICE_SHEET.value.equalsIgnoreCase(status)) {
                ratingQueueDAO.updateRateStatusInQueue(bean.getRatingQueueId(), ParcelAuditConstant.ParcelRatingQueueRateStatus.EMPTY_PRICE_SHEET.value);
            } else if (ParcelRatingUtil.isRatingDone(status)) {
                ratingQueueDAO.updateRateStatusInQueue(bean.getRatingQueueId(), ParcelAuditConstant.ParcelRatingQueueRateStatus.DONE.value);
            }
        }
    }
}
