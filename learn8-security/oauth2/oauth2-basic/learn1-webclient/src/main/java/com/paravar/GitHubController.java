package com.paravar;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class GitHubController {

    private final GithubService githubService;

    @GetMapping
    public String test(){
        return "Hello";
    }
    @GetMapping("/{username}/repos")
    public Flux<GithubRepo> getUserRepos(@PathVariable String username) {
        return githubService.getPublicRepositories(username, GithubRepo.class);
    }
    @GetMapping("/{username}/repos-full")
    public Flux<JsonNode> getUserReposFull(@PathVariable String username) {
        return githubService.getPublicRepositories(username, JsonNode.class);
    }
}
