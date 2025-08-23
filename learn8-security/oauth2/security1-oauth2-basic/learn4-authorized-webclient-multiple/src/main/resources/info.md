# Web Client
- use browser to hit the apis


## Note:-
- from one browser only one git-hub account login is allowed
   - so, use another browser to test secondary client
  
- in security config the below code is added
    - so it'll trigger authentication(authorization) with git-hub
  ```java
     .requestMatchers("/api/primary/**").authenticated() 
     .requestMatchers("/api/secondary/**").authenticated() 
   ```

- the default login page `http://localhost:8080/login`
  - shows all the oauth2 registered clients

- by default spring boot provides authorization urls for all the registered clients
  - `/oauth2/authorization/<clientId>`
  - in our case
    - /oauth2/authorization/github-primary
    - /oauth2/authorization/github-secondary
  - when we hit these url...it'll redirect to the corresponding auth server(provider) 
     - these information will be taken form application.yaml


- Multiple ways we can access oauth resource
  - with oauth server specific web client
        - eg: gitHubPrimaryWebClient ( see: GithubService - Mono<GithubUser> getPrimaryUser)
            - this is a web client, internally oauth2 authorized client is injected
  
  - Normal web client
       - while sending api request...injecting oauth2 authorized client (token)
          - using OAuth2AuthorizedClientService
       - eg: GithubService - Mono<JsonNode> getPrimaryUserFullDetails