package com.paravar.errorHandling;

import com.paravar.AppProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ErrorService {
    private final AppProperties appProperties;
    private final KafkaTemplate<String, String> kafkaTemplate;

    public void sendErrorMessage(String message) {
        kafkaTemplate.send(appProperties.error().topic(), message);
        System.out.println("Sent to error: " + appProperties.basic().topic() + " :" + message);
    }
}