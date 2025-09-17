package com.paravar.streams;

import com.paravar.AppProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class StreamsService {
    private final AppProperties appProperties;
    private final KafkaTemplate<String, String> kafkaTemplate;

    public void sendStream(String message) {
        kafkaTemplate.send(appProperties.streams().inputTopic(), message);
        System.out.println("Sent to streams " + appProperties.basic().topic() + " :" + message);
    }
}