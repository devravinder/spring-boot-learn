package com.paravar.topic;

import com.paravar.AppProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class TopicConfig {

    private final AppProperties appProperties;

    @Bean
    public TopicExchange topicExchange() {
        return new TopicExchange(appProperties.topic().exchange());
    }

    @Bean
    public Queue topicQueueAll() {
        return new Queue(appProperties.topic().logsQueue(), false);
    }

    @Bean
    public Queue topicQueueError() {
        return new Queue(appProperties.topic().errorQueue(), false);
    }

    @Bean
    public Binding topicBindingAll() {
        return BindingBuilder.bind(topicQueueAll()).to(topicExchange()).with(appProperties.topic().logsQueuePattern());  // All logs
    }

    @Bean
    public Binding topicBindingError() {
        return BindingBuilder.bind(topicQueueError()).to(topicExchange()).with(appProperties.topic().errorQueuePattern());  // Only errors
    }

}
