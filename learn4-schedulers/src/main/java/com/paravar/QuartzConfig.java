package com.paravar;

import lombok.AllArgsConstructor;
import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@AllArgsConstructor
public class QuartzConfig {

    @Bean
    public JobDetail jobDetail() {
        return JobBuilder.newJob(ScheduledJob.class)
                .withIdentity("job-1")
                .storeDurably()
                .build();
    }

    @Bean
    public Trigger trigger() {
        ScheduleBuilder<CronTrigger> scheduleBuilder = CronScheduleBuilder
                .cronSchedule("0/10 * * * * ?"); // Every 10 seconds

        return TriggerBuilder.newTrigger()
                .forJob(jobDetail())
                .withIdentity("job-1-trigger")
                .withSchedule(scheduleBuilder)
                .build();
    }
}
