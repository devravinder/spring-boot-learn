package com.paravar;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class TransactionsConsumerApplication {
    public static void main(String[] args) {
        SpringApplication.run(TransactionsConsumerApplication.class, args);
    }
}