
## Flow
1. Visit client app secure endpoints 
    - this will redirect to login page
   
2. if we click on our auth-server that will redirect to 
    - client app's log-in url: http://127.0.0.1:8080/oauth2/authorization/login-client
   
3. client app's log-in url will redirect to
   - oauth server log-in url: http://localhost:9000/oauth2/authorize?response_type=code&client_id=login-client&scope=openid%20profile&state=PMC7XNZ81toTWlgYqxliFd1LQndtV8Cl_6oZ025ORwo%3D&redirect_uri=http://127.0.0.1:8080/login/oauth2/code/login-client&nonce=kt0RemFN48tzE1vKWjvOUBqcycKxs1YCmPblE3JNIjA
   -  this will redirect to oauth server's log-in screen ( if not logged in )
   -  Note:-
       - hare `state=asdd` is csrf token
   
4. after the login... oauth server will redirect to
   - oauth server's consent screen url: http://localhost:9000/oauth2/authorize?response_type=code&client_id=login-client&scope=openid%20profile&state=PMC7XNZ81toTWlgYqxliFd1LQndtV8Cl_6oZ025ORwo%3D&redirect_uri=http://127.0.0.1:8080/login/oauth2/code/login-client&nonce=kt0RemFN48tzE1vKWjvOUBqcycKxs1YCmPblE3JNIjA
     
5. after the consent the oauth server will redirect to
   - client app's auth code url : http://127.0.0.1:8080/login/oauth2/code/login-client?code=2f3LBN-LmywNerPvnacmGYBmMEhaC9IzZjk-NquDtXJdbCBqvwTnKrYGRrhBIFnZilXs47hKwPYaaaWkEroDtpnsXPUIxJe1x5yHY4WMJar2Mt6LE3MBVfT1SAvSvOAt&state=PMC7XNZ81toTWlgYqxliFd1LQndtV8Cl_6oZ025ORwo%3D
    
6. once after code is received in client app
   - the client app call's auth-server's `/oauth2/token` endpoint
      - it shares all the necessary info
        - client_id, client_secret
        - grant_type=authorization_code
        - redirect_uri 
        - code
7. then the auth server responds with tokens
      - access_token
      - id_token
      - refresh_token ( based on grant type )
      - token_type ( eg: Bearer )
      - expires_in ( eg: 3600 )

### Imp
- all the openid configuration can be accessed by
   - `http://localhost:9000/.well-known/openid-configuration` on auth server
   - this is injected by OIDC default configuration
- the json web keys set can be accessed by
  - `http://localhost:9000/oauth2/jwks` on auth server
  - these JWKS are used by auth client & resource servers to verify tokens
  - these are public keys to verify auth token
  - these are generated JWKSource


### Note:-
- to capture the url stop the target server and see in url in browser
  - eg: to see oauth server log-in url
      - stop oauth server
      - click on client app log-in url
      - it'll redirect to oauth server log-in url but stops, then we can see


- always run auth server first
- sometimes Chrome browser devtools send request...that may create issue
    - but retry ( or visit home page again )

