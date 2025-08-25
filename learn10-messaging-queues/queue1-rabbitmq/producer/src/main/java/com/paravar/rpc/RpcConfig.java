package com.paravar.rpc;

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
public class RpcConfig {

    private final AppProperties appProperties;

    @Bean
    public Queue rpcQueue() {
        return new Queue(appProperties.rpc().queueName(), false);
    }

}
