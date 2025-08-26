package com.paravar;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "app.apache-kafka")
public record AppProperties(String errorHandlerPostfix, Transactional transactional) {

    public record Transactional(String topic){}

}
