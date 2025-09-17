package com.paravar.basic;

import com.paravar.AppProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class BasicService {
    private final AppProperties appProperties;
    private final KafkaTemplate<String, String> kafkaTemplate;

    public void sendBasicMessage(String message) {
        kafkaTemplate.send(appProperties.basic().topic(), message);
        System.out.println("Sent to " + appProperties.basic().topic() + " :" + message);
    }
}