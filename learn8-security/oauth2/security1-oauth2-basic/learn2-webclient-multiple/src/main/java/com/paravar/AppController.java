package com.paravar;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class AppController {

    private final GithubService githubService;
    private final RestService restService;

    @GetMapping
    public String test(){
        return "Hello";
    }
    @GetMapping("/{username}/repos")
    public Flux<GithubRepo> getUserRepos(@PathVariable String username) {
        return githubService.getPublicRepositories(username, GithubRepo.class);
    }
    @GetMapping("/todos")
    public Flux<JsonNode> getTodos() {
        return restService.getAll("https://jsonplaceholder.typicode.com/todos", JsonNode.class);
    }
    @GetMapping("/todos/{id}")
    public Mono<JsonNode> getTodo(@PathVariable String id) {
        return restService.getOne(String.format("https://jsonplaceholder.typicode.com/todos/%s",id), JsonNode.class);
    }
}
