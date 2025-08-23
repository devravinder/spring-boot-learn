# Web Client
- use browser to hit the apis


## Explanation

### In Application.yaml
```yaml

security:
  oauth2:
    client:
      registration:
        github: # client id/name (within the app )
          client-id: ${GITHUB_CLIENT_ID:Ov23liWMWsveZfZV5dxX}
          client-secret: ${GITHUB_CLIENT_SECRET:636d189505d8511d4a8f41d2e3abc5af9272ab2a}
          scope: repo
      provider:
        github: # provider name
          authorization-uri: https://github.com/login/oauth/authorize
                          # url for login (authorization)
          token-uri: https://github.com/login/oauth/access_token
                          # url for access_token
          user-info-uri: https://api.github.com/user
                         # api end point to get user-info
          user-name-attribute: login
                        # attribute in which user info is stored in the response of user-info api

```


- we are using
   - Client Authentication Method: client_secret_basic
   - GrantType: Authorization Code



## Note:-
- to trigger oauth2 authentication
   - that url should be added in spring security for authentication
   - ```.requestMatchers("/api/self/**").authenticated()```

- if multiple clients are registered 
   - then default login page `http://localhost:8080/login`


- by default spring boot provides authorization urls for all the registered clients
  - `/oauth2/authorization/<clientId>`
  - in our case
    - `http://localhost:8080/oauth2/authorization/github`
  - when we hit these url...it'll redirect to the corresponding auth server(provider)
    - these information will be taken form application.yaml



### Imp
- We can access secured resources of auth server multiple ways
  1. web client tied to specific authorized client ( see: gitHubWebClient )
  2. Normal web client injected with authorized client
     - authorized client can be accessed in multiple ways
       1. using OAuth2AuthorizedClientService
       2. using @RegisteredOAuth2AuthorizedClient OAuth2AuthorizedClient  ( implicit )
       3. using @RegisteredOAuth2AuthorizedClient("clinetId") OAuth2AuthorizedClient  ( explicit )



  
### Spring Security info
- all the registered clients are stored in ClientRegistrationRepository
- authorized clients are stored in OAuth2AuthorizedClientRepository
- AuthorizedClientManager gives authorized clients
   - this internally uses 
      - OAuth2AuthorizedClientProvider
      - ClientRegistrationRepository
      - OAuth2AuthorizedClientRepository

- OAuth2AuthorizedClientService internally user AuthorizedClientManager