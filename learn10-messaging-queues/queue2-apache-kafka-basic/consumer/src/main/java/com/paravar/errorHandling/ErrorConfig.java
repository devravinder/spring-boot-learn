package com.paravar.errorHandling;

import com.paravar.AppProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.header.internals.RecordHeader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.listener.DeadLetterPublishingRecoverer;
import org.springframework.kafka.listener.DefaultErrorHandler;
import org.springframework.util.backoff.FixedBackOff;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class ErrorConfig {

    private final AppProperties appProperties;

    @Bean
    public DefaultErrorHandler kafkaErrorHandler(KafkaTemplate<Object, Object> kafkaTemplate) {
        DeadLetterPublishingRecoverer recoverer = new DeadLetterPublishingRecoverer(
                kafkaTemplate,
        (record, ex) -> {
            // Log the error details
            // log.error("Sending record to DLT due to error: topic={}, key={}, value={}, error={}",record.topic(), record.key(), record.value(), ex.getMessage(), ex);

            // Add exception details to message headers
            ProducerRecord<Object, Object> producerRecord = new ProducerRecord<>(
                    record.topic() + appProperties.errorHandlerPostfix(),
                    record.partition(),
                    record.key(),
                    record.value()
            );

            return new TopicPartition(record.topic() + appProperties.errorHandlerPostfix(), record.partition());
        });
        return new DefaultErrorHandler(recoverer, new FixedBackOff(1000L, 3)); // 3 retries with 1s delay
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, String> kafkaListenerContainerFactory(
            ConsumerFactory<String, String> consumerFactory,
            DefaultErrorHandler errorHandler
    ) {
        ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory);
        factory.setCommonErrorHandler(errorHandler);
        return factory;
    }
}