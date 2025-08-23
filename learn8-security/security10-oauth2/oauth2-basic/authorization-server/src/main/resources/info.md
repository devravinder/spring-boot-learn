1. We are spring security oauth2 authorization server.
   This provides many things automatically.  
   For Users:
    1. Login Page (Form)  - for User to login
    2. Consent page for User to consent to the 3rd party app( Resource Server)
  
   For the Client apps:
     1. authorization uri endpoint    (http://localhost:9000/oauth2/authorize)
     2. token uri endpoint            (http://localhost:9000/oauth2/token)
     3. jwk uri endpoint - used for validation of the token in the 3rd party app (http://localhost:9000/oauth2/jwks)
