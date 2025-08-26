package com.paravar.streams;

import com.paravar.AppProperties;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
@RequiredArgsConstructor
public class StreamsConfig {
    private final AppProperties appProperties;
    @Bean
    public NewTopic streamInputTopic() {
        return TopicBuilder.name(appProperties.streams().inputTopic())
                .partitions(1)
                .replicas(1)
                .build();
    }
}