package com.paravar;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

@Service
@RequiredArgsConstructor
public class GithubService {
    private final WebClient webClient;

    public <T> Flux<T> getPublicRepositories(String username, Class<T> responseType) {
        return webClient.get()
                .uri("/users/{username}/repos", username)
                .retrieve()
                .bodyToFlux(responseType);
    }
}