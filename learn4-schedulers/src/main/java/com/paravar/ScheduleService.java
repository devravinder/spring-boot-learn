package com.paravar;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

//@Component
public class ScheduleService {
    /*
     without any persistence, the scheduled tasks will be lost if the application is restarted.
    */

    // Run every 5 seconds
    @Scheduled(fixedRate = 5000)
    public void runEveryFiveSeconds() {
        System.out.println("runEveryFiveSeconds task started.");
        System.out.println("==========================================runEveryFiveSeconds");
        System.out.println("runEveryFiveSeconds task completed.");
    }

    // Run at a fixed time every day (e.g., 9:46 PM)
    @Scheduled(cron = "0 46 21 * * *") // sec min hour dayOfMonth month dayOfWeek
    public void runAt21_46() {
        System.out.println("runAt10AM task started.");
        System.out.println("==========================================runAt21_46");
        System.out.println("runAt10AM task completed.");
    }

    @Scheduled(fixedDelay = 10000) // Delay 10 seconds after the task finishes
    public void runWithDelay() {
        System.out.println("runWithDelay task started.");
        System.out.println("==========================================runWithDelay");
        sleep(5000);
        System.out.println("runWithDelay task completed.");
    }

    void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
