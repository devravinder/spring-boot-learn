package com.paravar;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.springframework.stereotype.Component;

@Component
public class ScheduledJob implements Job {
    @Override
    public void execute(JobExecutionContext context) {
        System.out.println("=====================================ScheduledJob is running");
    }
}
