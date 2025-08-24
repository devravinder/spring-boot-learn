package com.paravar;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Controller
@RequiredArgsConstructor
public class AppController {

	private final WebClient webClient;

	@GetMapping("/")
	public String index(Model model, @RegisteredOAuth2AuthorizedClient OAuth2AuthorizedClient authorizedClient,
			@AuthenticationPrincipal OAuth2User oauth2User) {
		model.addAttribute("userName", oauth2User.getName());
		model.addAttribute("clientName", authorizedClient.getClientRegistration().getClientName());
		model.addAttribute("userAttributes", oauth2User.getAttributes());
		model.addAttribute("accessToken",authorizedClient.getAccessToken().getTokenValue());
		model.addAttribute("refreshToken",authorizedClient.getRefreshToken().getTokenValue());
		return "index";
	}

	@GetMapping("/resource")
	@ResponseBody
	public Mono<String> getResource( @RegisteredOAuth2AuthorizedClient OAuth2AuthorizedClient authorizedClient,
									   @Value("${app.resource-server.url}") String resourceServerUrl
									  ) {
		return webClient.get()
				.uri(resourceServerUrl+"/resource")
//				.attributes(oauth2AuthorizedClient(authorizedClient)) // this also set's token same as headers()
				.headers(headers -> headers
						.setBearerAuth(authorizedClient.getAccessToken().getTokenValue())
				)
				.retrieve()
				.bodyToMono(String.class);
	}

}
