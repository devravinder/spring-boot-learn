package com.paravar;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@RestController
@Slf4j
@RequiredArgsConstructor
public class OrderController {

    private final WebClient webClient;



    @GetMapping("/whs/jai-ganesha")
    public Mono<String> createOrder() {
        log.info("Creating order, calling product service");
        return webClient.get()
                .uri("http://localhost:8081/products")
                .retrieve()
                .bodyToMono(String.class);
    }
}