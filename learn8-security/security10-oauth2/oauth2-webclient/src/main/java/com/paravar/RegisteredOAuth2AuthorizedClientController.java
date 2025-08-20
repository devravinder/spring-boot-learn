package com.paravar;

import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.reactive.function.client.WebClient;

import static org.springframework.security.oauth2.client.web.reactive.function.client.ServletOAuth2AuthorizedClientExchangeFilterFunction.oauth2AuthorizedClient;

@Controller
@RequestMapping(path = { "/annotation", "/public/annotation" })
public class RegisteredOAuth2AuthorizedClientController {

    private final WebClient webClient;

    public RegisteredOAuth2AuthorizedClientController(WebClient webClient) {
        this.webClient = webClient;
    }

    @GetMapping("/explicit")
    String explicit(Model model,
                    @RegisteredOAuth2AuthorizedClient("rare-spring-security-app") OAuth2AuthorizedClient authorizedClient) {
        // @formatter:off
        String body = this.webClient
                .get()
                .attributes(oauth2AuthorizedClient(authorizedClient))
                .retrieve()
                .bodyToMono(String.class)
                .block();
        // @formatter:on
        model.addAttribute("body", body);
        return "response";
    }

    @GetMapping("/implicit")
    String implicit(Model model, @RegisteredOAuth2AuthorizedClient OAuth2AuthorizedClient authorizedClient) {
        // @formatter:off
        String body = this.webClient
                .get()
                .attributes(oauth2AuthorizedClient(authorizedClient))
                .retrieve()
                .bodyToMono(String.class)
                .block();
        // @formatter:on
        model.addAttribute("body", body);
        return "response";
    }

}