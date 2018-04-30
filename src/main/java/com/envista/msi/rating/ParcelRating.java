package com.envista.msi.rating;

import com.envista.msi.rating.bean.RatingQueueBean;
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

    public void processRating(int jobId) throws Exception{

        RatingQueueDAO ratingQueueDao = new RatingQueueDAO();
        ArrayList<RatingQueueBean> beanList = ratingQueueDao.getRatingQueueByJobId(jobId);


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

        //TO-DO rating logic needs to be here
    }

}
