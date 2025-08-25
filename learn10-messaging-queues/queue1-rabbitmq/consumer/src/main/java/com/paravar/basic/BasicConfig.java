package com.paravar.basic;

import com.paravar.AppProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class BasicConfig {

    private final AppProperties appProperties;

    @Bean
    public Queue textQueue() {
        return new Queue(appProperties.basic().textQueue(), false);
    }

    @Bean
    public Queue objQueue() {
        return new Queue(appProperties.basic().objQueue(), false);
    }
}
