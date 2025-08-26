package com.paravar.errorHandling;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.*;

@Slf4j
@Component
public class ErrorConsumer {
    @KafkaListener(topics = "${app.apache-kafka.error.topic}") // errorHandler = "kafkaErrorHandler"
    public void consume(String message) {
        if (message.contains("fail")) {
            throw new RuntimeException("Simulated failure");
        }
        System.out.println("Processed: " + message);
    }


    Set<String> keys = Set.of("kafka_dlt-original-topic","kafka_dlt-exception-message","kafka_dlt-exception-cause-fqcn","kafka_dlt-exception-stacktrace");

    @KafkaListener(topics = "${app.apache-kafka.error.topic}${app.apache-kafka.error-handler-postfix}")
    public void listenDlt(ConsumerRecord<String, String> record) {

       // System.out.println(record);
       // System.out.println(record.headers());

        System.out.println("Time: "+new Date(record.timestamp()).toString());
        System.out.println("value: "+record.value());


        for( var header : record.headers()){
            if(keys.contains(header.key()))
             System.out.println(header.key()+" : "+ new String(header.value()));
        }

    }
}