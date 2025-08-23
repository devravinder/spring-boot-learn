package com.paravar;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    @Bean
    public WebClient webClient(@Value("${github.api.base-url}") String baseUrl){
        return WebClient.builder()
                .baseUrl(baseUrl)
                //.apply(oauth2.oauth2Configuration())
                .build();
    }
}
