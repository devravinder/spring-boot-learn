package com.paravar.streams;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class StreamsListener {
    @KafkaListener(topics = "${app.apache-kafka.streams.input-topic}", groupId = "${app.apache-kafka.consumer.group-id}")
    public void inputStream(String message) {
        System.out.println("in-Stream message received: " + message);
    }

    @KafkaListener(topics = "${app.apache-kafka.streams.output-topic}", groupId = "${app.apache-kafka.consumer.group-id}")
    public void outputStream(String message) {
        System.out.println("out-Stream message received: " + message);
    }
}