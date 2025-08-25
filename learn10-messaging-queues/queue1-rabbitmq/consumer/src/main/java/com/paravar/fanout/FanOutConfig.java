package com.paravar.fanout;

import com.paravar.AppProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class FanOutConfig {

    private final AppProperties appProperties;

    @Bean
    public FanoutExchange fanoutExchange() {
        return new FanoutExchange(appProperties.fanOut().exchange());
    }

    @Bean
    public Queue fanOutQueue1() {
        return new Queue(appProperties.fanOut().fanOutQueue1(), false);
    }

    @Bean
    public Queue fanOutQueue2() {
        return new Queue(appProperties.fanOut().fanOutQueue2(), false);
    }

    @Bean
    public Binding binding1() {
        return BindingBuilder.bind(fanOutQueue1()).to(fanoutExchange());
    }

    @Bean
    public Binding binding2() {
        return BindingBuilder.bind(fanOutQueue2()).to(fanoutExchange());
    }

}
