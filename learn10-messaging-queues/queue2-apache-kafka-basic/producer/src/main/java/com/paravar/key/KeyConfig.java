package com.paravar.key;

import com.paravar.AppProperties;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
@RequiredArgsConstructor
public class KeyConfig {

    private final AppProperties appProperties;

    @Bean
    public NewTopic keyedTopic() {
        return TopicBuilder.name(appProperties.key().topic())
                .partitions(4)
                .replicas(1)
                .build();
    }
}