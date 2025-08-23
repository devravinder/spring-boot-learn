package com.paravar;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class AppController {

    private final GithubService githubService;
    private final RestService restService;
    private final OAuth2AuthorizedClientService authorizedClientService;


    @GetMapping
    public String test(){
        return "Hello";
    }

    @GetMapping("/{username}/repos")
    public Flux<GithubRepo> getPublicRepos(@PathVariable String username) {
        return githubService.getPublicRepos(username, GithubRepo.class);
    }
    @GetMapping("/primary/repos/{visibility}")
    // visibility: all, private, public
    public Flux<GithubRepo> getPrimaryRepos(@PathVariable String visibility) {
        return githubService.getPrimaryRepos(visibility,GithubRepo.class);
    }
    @GetMapping("/secondary/repos/{visibility}")
    // visibility: all, private, public
    public Flux<GithubRepo> getSecondaryRepos(@PathVariable String visibility) {
        return githubService.getSecondaryRepos(visibility,GithubRepo.class);
    }

    @GetMapping("/primary/user")
    public Mono<GithubUser> getPrimaryUser() {
        return githubService.getPrimaryUser();
    }

    @GetMapping("/secondary/user")
    public Mono<GithubUser> getSecondaryUser() {
        return githubService.getSecondaryUser();
    }

    @GetMapping("/primary/user-full")
    public Mono<JsonNode> getPrimaryUserFull(Authentication authentication) {
        OAuth2AuthorizedClient authorizedClient = authorizedClientService
                .loadAuthorizedClient("github", authentication.getName());

        return githubService.getPrimaryUserFullDetails(authorizedClient);
    }

    @GetMapping("/primary/user-full/explicit")
    public Mono<JsonNode> getPrimaryUserFullExplicit(@RegisteredOAuth2AuthorizedClient("github") OAuth2AuthorizedClient authorizedClient) {
        return githubService.getPrimaryUserFullDetails(authorizedClient);
    }


    //======

    @GetMapping("/todos")
    public Flux<JsonNode> getTodos() {
        return restService.getAll("https://jsonplaceholder.typicode.com/todos", JsonNode.class);
    }
    @GetMapping("/todos/{id}")
    public Mono<JsonNode> getTodo(@PathVariable String id) {
        return restService.getOne(String.format("https://jsonplaceholder.typicode.com/todos/%s",id), JsonNode.class);
    }
}
