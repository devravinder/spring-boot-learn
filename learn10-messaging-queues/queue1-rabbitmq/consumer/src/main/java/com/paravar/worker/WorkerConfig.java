package com.paravar.worker;

import com.paravar.AppProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class WorkerConfig {

    private final AppProperties appProperties;

    @Bean
    public Queue workQueue() {
        return new Queue(appProperties.work().workQueue(), true);
    }

}
