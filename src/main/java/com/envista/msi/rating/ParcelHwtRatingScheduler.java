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


public class ParcelHwtRatingScheduler {

    /**
     * @param args
     */
    public static void main(String[] args) throws Exception {

        File file = new File("ParcelHwtRatingScheduler");
        FileChannel channel = new RandomAccessFile(file, "rw").getChannel();
        FileLock lock = null;
        String jobIds = "99";
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
        if (args != null && args.length > 0) {
            jobIds = args[0];
        }

        // Get the scheduler
        ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
        // Get a handle, starting now, with a 10 minutes delay
        final ScheduledFuture<?> future = executor.scheduleWithFixedDelay(new ParcelRatingPollingService(jobIds), 0, 1, TimeUnit.MINUTES);
        //future.cancel(false);
        //executor.shutdown();
    }
}

class ParcelHwtRatingPollingService implements Runnable {

    private Log m_log = LogFactory.getLog(ParcelRatingPollingService.class);
    private String jobIds;

    public ParcelHwtRatingPollingService(String jobIds) {
        this.jobIds = jobIds;
    }

    public void run() {
        try {
            ParcelHwtRating parcelHwtRating = new ParcelHwtRating();
            parcelHwtRating.processRating(this.jobIds);
        } catch (Exception e) {
            m_log.error(e);
            e.printStackTrace();
        }
    }

}

