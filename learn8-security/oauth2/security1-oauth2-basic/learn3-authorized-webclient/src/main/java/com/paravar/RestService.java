package com.paravar;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class RestService {
    private final WebClient webClient;

    public <T> Flux<T> getAll(String url,Class<T> responseType) {
        return webClient.get()
                .uri(url)
                .retrieve()
                .bodyToFlux(responseType);
    }
    public <T> Mono<T> getOne(String url, Class<T> responseType) {
        return webClient.get()
                .uri(url)
                .retrieve()
                .bodyToMono(responseType);
    }

}
