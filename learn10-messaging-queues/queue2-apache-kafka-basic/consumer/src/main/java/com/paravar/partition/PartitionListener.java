package com.paravar.partition;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

@Component
public class PartitionListener {

    @KafkaListener(topics = "${app.apache-kafka.partition.topic}", groupId = "${app.apache-kafka.consumer.group-id}")
    public void listenPartitioned(String message,@Header(KafkaHeaders.RECEIVED_PARTITION) int partition) {
        System.out.println("1 Received from partitioned-topic, partition " + partition + ": " + message);
    }

}