package com.paravar;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "app.apache-kafka")
public record AppProperties(String errorHandlerPostfix, Basic basic, Partition partition, Key key) {

    public record Basic(String topic){}

    public record Partition(String topic){}

    public record Key(String topic){}



}
