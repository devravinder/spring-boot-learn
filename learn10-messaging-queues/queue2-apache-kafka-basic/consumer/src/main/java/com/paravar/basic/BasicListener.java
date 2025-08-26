package com.paravar.basic;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class BasicListener {
    @KafkaListener(topics = "${app.apache-kafka.basic.topic}", groupId = "${app.apache-kafka.consumer.group-id}")
    public void listenSimple(String message) {
        System.out.println("1 Received 1: " + message);
    }
}