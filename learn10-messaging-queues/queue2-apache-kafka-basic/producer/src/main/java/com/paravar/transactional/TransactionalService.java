package com.paravar.transactional;

import com.paravar.AppProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class TransactionalService {

    private final AppProperties appProperties;
    private final KafkaTemplate<String, String> kafkaTemplate;

    @Transactional("kafkaTransactionManager")
    public void sendTransactional( String value) {
        kafkaTemplate.send(appProperties.transactional().topic(), "single-key", value);
        // If any exception occurs, the transaction is rolled back
    }

    @Transactional
    public void sendTransactional(String key, String message) {
        kafkaTemplate.executeInTransaction(ops -> {
            ops.send(appProperties.transactional().topic(), key, message);
            // Simulate more operations
            return null;
        });
    }
}
