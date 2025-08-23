package com.paravar;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.springframework.security.oauth2.client.web.reactive.function.client.ServletOAuth2AuthorizedClientExchangeFilterFunction.oauth2AuthorizedClient;

@Service
@RequiredArgsConstructor
public class GithubService {

    private final WebClient webClient;
    private final WebClient gitHubPrimaryWebClient;
    private final WebClient gitHubSecondaryWebClient;



    public <T> Flux<T> getPublicRepos(String username, Class<T> responseType) {
        return webClient.get()
                .uri("https://api.github.com/users/{username}/repos", username)
                .retrieve()
                .bodyToFlux(responseType);
    }



    public <T> Flux<T> getPrimaryRepos(String visibility, Class<T> responseType) {

        return gitHubPrimaryWebClient.get()
                .uri(uriBuilder -> uriBuilder
                .path("/user/repos")
                .queryParam("visibility", visibility) // all, private, public
                .build())
                .retrieve()
                .bodyToFlux(responseType);
    }

    public <T> Flux<T> getSecondaryRepos(String visibility, Class<T> responseType) {
        return gitHubSecondaryWebClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/user/repos")
                        .queryParam("visibility", visibility)
                        .build())
                .retrieve()
                .bodyToFlux(responseType);
    }

    public Mono<GithubUser> getPrimaryUser() {
        return gitHubPrimaryWebClient.get()
                .uri("/user")
                .retrieve()
                .bodyToMono(GithubUser.class);
    }

    public Mono<GithubUser> getSecondaryUser() {
        return gitHubSecondaryWebClient.get()
                .uri("/user")
                .retrieve()
                .bodyToMono(GithubUser.class);
    }

    public Mono<JsonNode> getPrimaryUserFullDetails(OAuth2AuthorizedClient authorizedClient) {

        return webClient.get()
                .uri("https://api.github.com/user")
//                .attributes(oauth2AuthorizedClient(authorizedClient))
                .headers(headers -> headers.setBearerAuth(authorizedClient.getAccessToken().getTokenValue()))
                .retrieve()
                .bodyToMono(JsonNode.class);

        /*
         the both are same
         .attributes(oauth2AuthorizedClient(authorizedClient))
          .headers(headers -> headers.setBearerAuth(authorizedClient.getAccessToken().getTokenValue()))
        */

    }

}