package com.paravar;

import com.fasterxml.jackson.annotation.JsonProperty;

public record GithubRepo(String name, String html_url,
                         String description,
                         @JsonProperty("private") boolean isPrivate
) {}