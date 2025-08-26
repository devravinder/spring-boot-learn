package com.paravar;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "app.apache-kafka")
public record AppProperties(String errorHandlerPostfix, Streams streams) {

    public record Streams(String inputTopic, String outputTopic){}

}
