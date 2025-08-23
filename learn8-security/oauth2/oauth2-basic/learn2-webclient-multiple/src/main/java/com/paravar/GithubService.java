package com.paravar;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

@Service
@RequiredArgsConstructor
public class GithubService {
    private final WebClient gitHubWebClient;

    public <T> Flux<T> getPublicRepositories(String username, Class<T> responseType) {
        return gitHubWebClient.get()
                .uri("/users/{username}/repos", username)
                .retrieve()
                .bodyToFlux(responseType);
    }
}