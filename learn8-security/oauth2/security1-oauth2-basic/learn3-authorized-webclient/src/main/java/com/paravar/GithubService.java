package com.paravar;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.springframework.security.oauth2.client.web.reactive.function.client.ServletOAuth2AuthorizedClientExchangeFilterFunction.oauth2AuthorizedClient;

@Service
@RequiredArgsConstructor
public class GithubService {
    private final WebClient gitHubWebClient;
    private final WebClient webClient;


    public <T> Flux<T> getPublicRepos(String username, Class<T> responseType) {
        return webClient.get()
                .uri("https://api.github.com/users/{username}/repos", username)
                .retrieve()
                .bodyToFlux(responseType);
    }



    public <T> Flux<T> getSelfRepos(String visibility, Class<T> responseType) {

        return gitHubWebClient.get()
                .uri(uriBuilder -> uriBuilder
                .path("/user/repos")
                .queryParam("visibility", visibility) // all, private, public
                .build())
                .retrieve()
                .bodyToFlux(responseType);
    }

    public Mono<JsonNode> getSelfUserDetails(OAuth2AuthorizedClient authorizedClient) {
        return webClient.get()
                .uri("https://api.github.com/user")
                .attributes(oauth2AuthorizedClient(authorizedClient))
                // .headers(headers -> headers.setBearerAuth(authorizedClient.getAccessToken().getTokenValue()))
                .retrieve()
                .bodyToMono(JsonNode.class);

        /*
         the both are same
         .attributes(oauth2AuthorizedClient(authorizedClient))
          .headers(headers -> headers.setBearerAuth(authorizedClient.getAccessToken().getTokenValue()))
        */
    }
}