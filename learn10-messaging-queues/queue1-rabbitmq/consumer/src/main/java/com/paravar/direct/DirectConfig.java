package com.paravar.direct;

import com.paravar.AppProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class DirectConfig {

    private final AppProperties appProperties;

    @Bean
    public DirectExchange directExchange() {
        return new DirectExchange(appProperties.direct().exchange());
    }

    @Bean
    public Queue directQueueInfo() {
        return new Queue(appProperties.direct().infoQueue(), false);
    }

    @Bean
    public Queue directQueueError() {
        return new Queue(appProperties.direct().errorQueue(), false);
    }

    @Bean
    public Binding bindingInfo() {
        return BindingBuilder.bind(directQueueInfo()).to(directExchange()).with(appProperties.direct().infoQueueKey());
    }

    @Bean
    public Binding bindingError() {
        return BindingBuilder.bind(directQueueError()).to(directExchange()).with(appProperties.direct().errorQueueKey());
    }

}
