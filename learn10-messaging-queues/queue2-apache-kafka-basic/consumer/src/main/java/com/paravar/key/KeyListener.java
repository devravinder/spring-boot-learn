package com.paravar.key;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

@Component
public class KeyListener {

    @KafkaListener(topics = "${app.apache-kafka.key.topic}", groupId = "${app.apache-kafka.consumer.group-id}")
    public void listenKeyed(String message, @Header(KafkaHeaders.RECEIVED_KEY) String key) {
        System.out.println("1 Received from keyed-topic, key " + key + ": " + message);
    }

}