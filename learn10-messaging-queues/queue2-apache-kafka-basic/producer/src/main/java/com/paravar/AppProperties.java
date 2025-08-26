package com.paravar;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "app.apache-kafka")
public record AppProperties(Basic basic, Partition partition, Key key, Error error, Streams streams, Transactional transactional) {

    public record Basic(String topic){}

    public record Partition(String topic){}

    public record Key(String topic){}

    public record Error(String topic){}

    public record Streams(String inputTopic, String outputTopic){}

    public record Transactional(String topic){}

}
