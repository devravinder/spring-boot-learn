package com.paravar;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class GithubController {

	private final GithubService githubService;

	@GetMapping("/users/{username}")
	public ResponseEntity<GitHubUser> getGithubUserProfile(@PathVariable String username) {
		GitHubUser githubUserProfile = githubService.getGithubUserProfile(username);
		return ResponseEntity.ok(githubUserProfile);
	}

	@ExceptionHandler(GitHubServiceException.class)
	ResponseEntity<ApiError> handle(GitHubServiceException e){
		ApiError apiError = new ApiError(e.getMessage());
		return ResponseEntity.internalServerError().body(apiError);
	}

}