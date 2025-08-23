package com.paravar;

import com.fasterxml.jackson.annotation.JsonProperty;

public record GithubUser(
    @JsonProperty("login") String login,
    @JsonProperty("name") String name,
    @JsonProperty("email") String email
) {}