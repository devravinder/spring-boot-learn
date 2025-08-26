package com.paravar.basic;

import com.paravar.AppProperties;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
@RequiredArgsConstructor
public class BasicConfig {
    private final AppProperties appProperties;

    // this is not necessary auto config will take care
    @Bean
    public NewTopic basicTopic() {
        return TopicBuilder.name(appProperties.basic().topic())
                .partitions(1)
                .replicas(1)
                .build();
    }
}