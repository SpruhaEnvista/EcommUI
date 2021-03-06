package com.envista.msi.rating;

import com.envista.msi.api.domain.util.ParcelRatingUtil;
import com.envista.msi.api.web.rest.util.audit.parcel.ParcelAuditConstant;
import com.envista.msi.rating.bean.RatingQueueBean;
import com.envista.msi.rating.bean.ServiceFlagAccessorialBean;
import com.envista.msi.rating.dao.RatingQueueDAO;
import com.envista.msi.rating.service.ParcelNonUpsRatingService;
import com.envista.msi.rating.service.ParcelUpsRatingService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ParcelRating implements Callable<String> {

    private Log m_log = LogFactory.getLog(ParcelRating.class);

    private static final int MAX_THREADS = 5;

    private RatingQueueBean ratingQueueBean = null;


    private static final List<ServiceFlagAccessorialBean> fedexAccessorialBeans = ParcelNonUpsRatingService.getServiceFlagAcessorials(22l, ParcelAuditConstant.MSI_AR_CHARGE_CODE_MAPPING_MODELE_NAME);
    ;
    private static final List<ServiceFlagAccessorialBean> upsAccessorialBeans = ParcelNonUpsRatingService.getServiceFlagAcessorials(21l, ParcelAuditConstant.MSI_AR_CHARGE_CODE_MAPPING_MODELE_NAME);


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

        RatingQueueDAO ratingQueueDao = RatingQueueDAO.getInstance();
        ArrayList<RatingQueueBean> beanList = ratingQueueDao.getRatingQueueByJobId(jobIds);


        m_log.info("in process parcel rating .....");
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

        processParcelRating(this.ratingQueueBean);
        return "Success";
    }

    public void processParcelRating(RatingQueueBean bean) throws Exception {
        ParcelUpsRatingService parcelUpsRatingService = ParcelUpsRatingService.getInstance();
        ParcelNonUpsRatingService nonUpsRatingService = ParcelNonUpsRatingService.getInstance();
        String status = null;

        try {
            if (bean.getCarrierId() == 21) {
                //System.out.println("rating started for tracking number ->" + bean.getTrackingNumber() + " ebill manifest id->" + bean.getGffId());
                m_log.info("rating started for tracking number ->" + bean.getTrackingNumber() + " ebill manifest id->" + bean.getGffId());
                status = parcelUpsRatingService.doParcelRatingForUpsCarrier(bean, upsAccessorialBeans);
                m_log.info("Rating : " + bean.getTrackingNumber() + " : Status : " + status + ":gff id->" + bean.getGffId());
            } else if (bean.getCarrierId() == 22) {
                //System.out.println("rating started for tracking number ->" + bean.getTrackingNumber() + " ebill manifest id->" + bean.getManiestId());
                m_log.info("rating started for tracking number ->" + bean.getTrackingNumber() + " ebill manifest id->" + bean.getManiestId());
                status = nonUpsRatingService.doRatingForNonUpsShipment(bean, fedexAccessorialBeans);
                m_log.info("Rating : " + bean.getTrackingNumber() + " : Status : " + status);
            }

            if (status != null && !status.isEmpty()) {
                RatingQueueDAO ratingQueueDAO = RatingQueueDAO.getInstance();
                if (ParcelAuditConstant.RTRStatus.RATING_EXCEPTION.value.equalsIgnoreCase(status)) {
                    ratingQueueDAO.updateRateStatusInQueue(bean.getRatingQueueId(), ParcelAuditConstant.ParcelRatingQueueRateStatus.RATING_EXCEPTION.value, null);
                } else if (ParcelAuditConstant.RTRStatus.NO_PRICE_SHEET.value.equalsIgnoreCase(status)) {
                    ratingQueueDAO.updateRateStatusInQueue(bean.getRatingQueueId(), ParcelAuditConstant.ParcelRatingQueueRateStatus.EMPTY_PRICE_SHEET.value, null);
                } else if (ParcelRatingUtil.isRatingDone(status)) {
                    ratingQueueDAO.updateRateStatusInQueue(bean.getRatingQueueId(), ParcelAuditConstant.ParcelRatingQueueRateStatus.DONE.value, null);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            m_log.error(e.getStackTrace() + " Parent Id" + bean.getParentId() + "--Carrier Id->" + bean.getCarrierId());
        }
    }
}
