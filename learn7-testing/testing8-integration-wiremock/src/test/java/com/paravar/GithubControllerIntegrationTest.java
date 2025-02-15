package com.paravar;

import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.junit5.WireMockExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;


import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;
import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.urlMatching;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class GithubControllerIntegrationTest {

	@Autowired
	protected MockMvc mockMvc;

	@RegisterExtension
	static WireMockExtension wireMockServer = WireMockExtension.newInstance()
		.options(wireMockConfig().dynamicPort())
		.build();

	@DynamicPropertySource
	static void configureProperties(DynamicPropertyRegistry registry) {
		registry.add("github.api.base-url", wireMockServer::baseUrl);
	}

	@Test
	void shouldGetGithubUserProfile() throws Exception {
		String username = "devravinder";
		wireMockServer.stubFor(WireMock.get(urlMatching("/users/.*"))
				.willReturn(
					aResponse()
						.withHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
						.withBody("""
						{
							"login": "%s",
							"name": "Ravinder Reddy Kothabad",
							"twitter_username": "Ravinder__Reddy",
							"public_repos": 50
						}
						""".formatted(username))));

		this.mockMvc.perform(get("/api/users/{username}", username))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.login", is(username)))
			.andExpect(jsonPath("$.name", is("Ravinder Reddy Kothabad")))
			.andExpect(jsonPath("$.public_repos", is(50)));
	}

	@Test
	void shouldGetFailureResponseWhenGitHubApiFailed() throws Exception {
		String username = "devravinder";

		wireMockServer.stubFor(WireMock.get(urlMatching("/users/.*"))
				.willReturn(aResponse().withStatus(500)));

		String expectedError = "Fail to fetch github profile for " + username;
		this.mockMvc.perform(get("/api/users/{username}", username))
				.andExpect(status().is5xxServerError())
				.andExpect(jsonPath("$.message", is(expectedError)));
	}
}