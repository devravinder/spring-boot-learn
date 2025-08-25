package com.paravar;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "app.rabbitmq")
public record AppProperties(Basic basic, Work work, FanOut fanOut, Direct direct, Topic topic, Rpc rpc) {

    public record Basic(String textQueue, String objQueue){}

    public record Work(String workQueue){}

    public record FanOut(String exchange,String fanOutQueue1, String fanOutQueue2){}

    public record Direct(String exchange,String infoQueue, String infoQueueKey, String errorQueue, String errorQueueKey){}

    public record Topic(String exchange,String logsQueue, String logsQueuePattern, String errorQueue, String errorQueuePattern){}

    public record Rpc(String queueName){}
}
