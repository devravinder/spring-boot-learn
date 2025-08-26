package com.paravar;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class StreamsConsumerApplication {
    public static void main(String[] args) {
        SpringApplication.run(StreamsConsumerApplication.class, args);
    }
}