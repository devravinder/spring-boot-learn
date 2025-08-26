package com.paravar.transactional;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafkaStreams;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@RequiredArgsConstructor
//@EnableTransactionManagement
public class TransactionalConfig {

    /*
    // auto config
    @Bean
    public KafkaTransactionManager transactionManager(ProducerFactory<String, String> producerFactory) {
        return new KafkaTransactionManager(producerFactory);
    }*/

}