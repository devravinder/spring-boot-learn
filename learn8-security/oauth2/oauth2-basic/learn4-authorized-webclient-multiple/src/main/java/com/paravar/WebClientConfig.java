package com.paravar;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientManager;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientProvider;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientProviderBuilder;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.web.DefaultOAuth2AuthorizedClientManager;
import org.springframework.security.oauth2.client.web.OAuth2AuthorizedClientRepository;
import org.springframework.security.oauth2.client.web.reactive.function.client.ServletOAuth2AuthorizedClientExchangeFilterFunction;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    @Bean
    public WebClient webClient(){
        return WebClient.builder()
                .build();
    }


    @Bean
    WebClient gitHubPrimaryWebClient(OAuth2AuthorizedClientManager authorizedClientManager, @Value("${github.api.base-url}") String baseUrl) {

        ServletOAuth2AuthorizedClientExchangeFilterFunction oauth2 = new ServletOAuth2AuthorizedClientExchangeFilterFunction(
                authorizedClientManager);
        oauth2.setDefaultOAuth2AuthorizedClient(true); // ***
        // i.e always uses authorized client... even for insecure apis...if this webclient is used

        oauth2.setDefaultClientRegistrationId("github");


        // @formatter:off
        return WebClient.builder()
                .baseUrl(baseUrl)
                .apply(oauth2.oauth2Configuration())
                .build();
        // @formatter:on
    }

    @Bean
    WebClient gitHubSecondaryWebClient(OAuth2AuthorizedClientManager authorizedClientManager, @Value("${github.api.base-url}") String baseUrl) {

        ServletOAuth2AuthorizedClientExchangeFilterFunction oauth2 = new ServletOAuth2AuthorizedClientExchangeFilterFunction(
                authorizedClientManager);
        oauth2.setDefaultOAuth2AuthorizedClient(true); // ***
        // i.e always uses authorized client... even for insecure apis...if this webclient is used

        oauth2.setDefaultClientRegistrationId("github-secondary");


        // @formatter:off
        return WebClient.builder()
                .baseUrl(baseUrl)
                .apply(oauth2.oauth2Configuration())
                .build();
        // @formatter:on
    }

    @Bean
    OAuth2AuthorizedClientManager authorizedClientManager(ClientRegistrationRepository clientRegistrationRepository,
                                                          OAuth2AuthorizedClientRepository authorizedClientRepository) {

        OAuth2AuthorizedClientProvider authorizedClientProvider =
                OAuth2AuthorizedClientProviderBuilder.builder()
                        .authorizationCode()
                        .refreshToken()
                        .clientCredentials()
//                        .password() // deprecated
                        .build();


        DefaultOAuth2AuthorizedClientManager authorizedClientManager = new DefaultOAuth2AuthorizedClientManager(
                clientRegistrationRepository, authorizedClientRepository);

        authorizedClientManager.setAuthorizedClientProvider(authorizedClientProvider);

        return authorizedClientManager;
    }
}
