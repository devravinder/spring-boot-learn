# Oauth2


## Oauth2 Components
1. Resource owner
2. Client app
3. Resource Server ( API )
4. Authorization Server

## Grant Types


## OIDC

### OIDC vs Oauth2
1. How OIDC extends OAuth2
   - OAuth 2.0
       - Purpose: 
          - Authorization framework – it lets an app (client) access resources on behalf of a user.
          - Auth 2.0 issues access tokens (opaque or JWT) that allow access to APIs (resource servers).
          - eg: Google Drive Client access Google Drive
       - Problem: 
          - It doesn’t define how to authenticate a user. 
             - user information may not exist in token
          - You know a user authorized the app, but you don’t know who the user is.
         
   - OpenID Connect (OIDC):
      - Purpose: 
         - Authentication layer on top of OAuth 2.0.
         - adds an identity layer by introducing:
            - ID Token (always a JWT): contains user identity info like sub, email, name, etc.
            - UserInfo Endpoint: REST API to fetch additional profile info.
         - eg: when we 'Login With Google', that’s OIDC. 
           - The app gets both:
             - An ID Token → proves who the user is.
             - An Access Token → allows access to Google APIs.
   
   - Imp:-
     - Oauth2 = Authorization              = access token
     - OIDC Authentication + Authorization = id token + access token
     - OIDC is = oauth2 extension = (oauth2 + Authentication) = ( access token + (id token + userInfo api) )

## SSO

## 2-Step Auth

## Terms

1. GrantType 
   - Describes how the client obtains a token ( flow to get an access token )
   - types:
    1. Authorization Code (*)
       - User logs in via browser redirect, client exchanges code for token.
    2. Client Credentials (*)
       - Machine-to-machine (M2M), client directly asks for token.
    3. Password (deprecated)
       - Client sends username/password to token endpoint.
    4. Refresh Token (*)
       - Client exchanges refresh token for new access token.
       
2. Client Authentication Method
   - How the client proves its identity to the authorization server
   - types:
     1. client_secret_basic (*)
        - Client ID + Secret sent in Authorization: Basic header.
     2. client_secret_post (*)
        - Client ID + Secret sent in request body.
     3. client_secret_jwt 
        - Client signs a JWT with its secret, presents it.
     4. private_key_jwt (*)
        - Client signs a JWT with its private key.
     5. none 
        - Public client (e.g., SPAs, mobile apps without secret).
