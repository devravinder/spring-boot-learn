package com.paravar.partition;

import com.paravar.AppProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class PartitionService {
    private final AppProperties appProperties;
    private final KafkaTemplate<String, String> kafkaTemplate;

    public void sendPartitionedMessage(String message, Integer partition) {
        kafkaTemplate.send(appProperties.partition().topic(), partition, null, message);
        System.out.println("Sent to partitioned-topic, partition " + partition + ": " + message);
    }
}