package com.envista.msi.rating;


import java.io.File;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.nio.channels.OverlappingFileLockException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author Sreenivas
 *
 *
 */
public class ParcelRatingScheduler {

    /**
     * @param args
     */
    public static void main(String[] args) throws Exception {

        File file = new File("ParcelRatingScheduler");
        FileChannel channel = new RandomAccessFile(file, "rw").getChannel();
        FileLock lock = null;
        int jobId=1;
        try {
            lock = channel.tryLock();
        } catch (OverlappingFileLockException e) {
            System.out.println("" + e);
            System.exit(0);
        }
        if (lock == null) {
            System.out.println("lock is acquired. Another program instance is running. ");
            System.exit(0);
        }
        if(args!=null && args.length>0){
            jobId = Integer.parseInt(args[0]);
        }

        // Get the scheduler
        ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
        // Get a handle, starting now, with a 10 minutes delay
        final ScheduledFuture<?> future = executor.scheduleWithFixedDelay(new ParcelRatingPollingService(jobId), 0, 10,TimeUnit.MINUTES);
        //future.cancel(false);
        //executor.shutdown();
    }
}

class ParcelRatingPollingService implements Runnable {

    private Log m_log = LogFactory.getLog(ParcelRatingPollingService.class);
    private int jobId;

    public ParcelRatingPollingService(int jobId) {
        this.jobId=jobId;
    }

    public void run() {
        try {
            ParcelRating parcelRating = new ParcelRating();
            parcelRating.processRating(this.jobId);
        } catch (Exception e) {
            m_log.error(e);
            e.printStackTrace();
        }
    }

}
