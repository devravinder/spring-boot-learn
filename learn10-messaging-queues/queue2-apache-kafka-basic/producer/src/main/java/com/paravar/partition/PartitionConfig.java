package com.paravar.partition;

import com.paravar.AppProperties;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
@RequiredArgsConstructor
public class PartitionConfig {

    private final AppProperties appProperties;

    @Bean
    public NewTopic partitionedTopic() {
        System.out.println("Creating partitioned-topic with 4 partitions");
        return TopicBuilder.name(appProperties.partition().topic())
                .partitions(4)
                .replicas(1)
                .build();
    }

}