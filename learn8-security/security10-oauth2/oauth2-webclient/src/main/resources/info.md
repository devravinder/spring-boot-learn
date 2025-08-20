1. Without any login, only public-explicit apis only will work
2. With app user login, public-explicit & authenticated-explicit apis will work
3. With Oauth2 GitHub login, all apis will work.  
   public-explicit,public-implicit,authenticated-explicit & authenticated-implicit.

4. In this application, we are learning how to use Webclient with  
   @RegisteredOAuth2AuthorizedClient & ServletOAuth2AuthorizedClientExchangeFilterFunction

5. @RegisteredOAuth2AuthorizedClient & ServletOAuth2AuthorizedClientExchangeFilterFunction  
   both are used to access web-client, but the both work differently & used in different use-cases.
   
   1. @RegisteredOAuth2AuthorizedClient:
      1. This is used to get OAuth2AuthorizedClient in controllers with annotation.
      2. Works with servlet-based web applications ( in API controllers ).
      3. This is used Interactive flows (e.g., user-initiated actions in a servlet app).  
         - when user access one api & we can perform some actions on that api.
      4. We directly access the OAuth2AuthorizedClient for oAuth token.
   
   2. ServletOAuth2AuthorizedClientExchangeFilterFunction:
      1. This allows us to access web-client directly ( thorough dependency injection ).
      2. Works in all the applications.
      3. Service-to-service communication (backend API calls
      4. web-client handles the oAuth token.
   
6. Implicit - Explicit:  
   - Implicit means = We are not specifying which authorized client to use.
   - Explicit means = We are specifying which authorized client to use ( client id ).
   
7. Things to remember (read the below reference links as well) :
   1. even though /public/*/implicit is public, it requires authentication.  
      - bcz we are accessing the default web-client(implicit),  
        which is configured as oauth2.setDefaultOAuth2AuthorizedClient(true).  
        i.e for default web-client, authentication is required.
   
   2. but /public/*/explicit does not require authentication.  
      - even tough we are accessing the web-client,  
      - but we are getting the web client explicitly (not default). Using clint id.
      
8. Read this:  
   - [How WebClinet works under the hood](https://docs.spring.io/spring-security/reference/reactive/oauth2/client/authorized-clients.html#oauth2-client-web-client)  
   - [How Default Authorized Client works](https://docs.spring.io/spring-security/reference/reactive/oauth2/client/authorized-clients.html#oauth2-client-web-client-default-authorized-client)