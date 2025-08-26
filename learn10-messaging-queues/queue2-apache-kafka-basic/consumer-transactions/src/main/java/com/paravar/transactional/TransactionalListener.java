package com.paravar.transactional;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class TransactionalListener {


    @KafkaListener(topics = "${app.apache-kafka.transactional.topic}", groupId = "${app.apache-kafka.consumer.group-id}")
    public void consume(ConsumerRecord<String, String> record, Acknowledgment ack) {
        try {
            System.out.println(" Transactional Received: " + record.value());
            // process...

            ack.acknowledge(); // commit offset after processing
        } catch (Exception e) {
            // no ack → offset not committed
            throw e;
        }
    }

}