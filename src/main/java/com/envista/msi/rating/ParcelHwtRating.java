package com.envista.msi.rating;

import com.envista.msi.api.domain.util.ParcelRatingUtil;
import com.envista.msi.api.web.rest.dto.rtr.ParcelAuditDetailsDto;
import com.envista.msi.rating.bean.RatingQueueBean;
import com.envista.msi.rating.dao.RatingQueueDAO;
import com.envista.msi.rating.service.ParcelNonUpsRatingService;
import com.envista.msi.rating.service.ParcelUpsRatingService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ParcelHwtRating implements Callable<String> {

    private static final int MAX_THREADS = 1;

    private List<RatingQueueBean> queueBeans = null;
    private Log m_log = LogFactory.getLog(ParcelRating.class);

    public ParcelHwtRating() {
    }

    public ParcelHwtRating(List<RatingQueueBean> queueBeans) {
        this.queueBeans = queueBeans;
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

    public void processRating(String jobIds) throws Exception {

        RatingQueueDAO ratingQueueDao = new RatingQueueDAO();
        ArrayList<RatingQueueBean> beanList = ratingQueueDao.getRatingQueueByJobId(jobIds);

        Map<String, List<RatingQueueBean>> shipmentWiseInfo = ParcelRatingUtil.prepareHwtShipmentWiseInfo(beanList);

        m_log.info("in process parcel rating .....");
        if (shipmentWiseInfo != null) {
            ExecutorService executor = Executors.newFixedThreadPool(MAX_THREADS);
            for (Map.Entry<String, List<RatingQueueBean>> entry : shipmentWiseInfo.entrySet()) {
                Callable<String> callableTask = new ParcelHwtRating(entry.getValue());
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
        System.out.println("in call ....." + this.queueBeans);
        processParcelRating(this.queueBeans);
        return "Success";
    }

    public void processParcelRating(List<RatingQueueBean> queueBeans) throws Exception {
        ParcelUpsRatingService parcelUpsRatingService = new ParcelUpsRatingService();
        ParcelNonUpsRatingService nonUpsRatingService = new ParcelNonUpsRatingService();
        String status = null;
        if (queueBeans != null && queueBeans.size() > 0)
            if (queueBeans.get(0).getCarrierId() == 21) {
                status = parcelUpsRatingService.doParcelRatingForUpsCarrier(queueBeans);
            } else if (queueBeans.get(0).getCarrierId() == 22) {
                status = nonUpsRatingService.doRatingForNonUpsShipment(queueBeans);
            }
        String queueIds = ParcelRatingUtil.prepareQueueIdsInOperator(queueBeans);
        if (ParcelRatingUtil.isRatingDone(status)) {
            RatingQueueDAO ratingQueueDAO = new RatingQueueDAO();
            ratingQueueDAO.updateRateStatusInQueue(null, queueIds);
        }
    }
}

