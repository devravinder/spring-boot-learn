package com.paravar.key;

import com.paravar.AppProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KeyService {
    private final AppProperties appProperties;
    private final KafkaTemplate<String, String> kafkaTemplate;

    public void sendKeyedMessage(String key, String message) {
        kafkaTemplate.send(appProperties.key().topic(), key, message);
        System.out.println("Sent to keyed-topic with key " + key + ": " + message);
    }
}