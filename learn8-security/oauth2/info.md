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
2. id_token vs access_token


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
