package com.paravar.streams;

import com.paravar.AppProperties;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.KTable;
import org.apache.kafka.streams.kstream.Materialized;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafkaStreams;

import java.util.Arrays;

@Configuration
@EnableKafkaStreams
@RequiredArgsConstructor
public class StreamsConfig {

    private final AppProperties appProperties;

    @Bean
    public KStream<String, String> kStream(StreamsBuilder streamsBuilder) {
        KStream<String, String> stream = streamsBuilder.stream(appProperties.streams().inputTopic());


        // Example: Convert values to uppercase and send to output-topic
        stream
                .mapValues(s->s.toUpperCase())
                .to(appProperties.streams().outputTopic());

        return stream;
    }
}