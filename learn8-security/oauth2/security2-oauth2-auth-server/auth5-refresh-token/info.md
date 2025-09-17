## Changes to observe
 - for the client `offline_access` scope is added
 - we are using manual configuration...bcz we were unable to set expiration for token using application.properties



### Imp
- `refresh token` grant is `authorization_code` with `offline_access` scope 
- to get the access token we should use authorization_code grant
    - we should send `offline_access`
    - then the auth server sends `refresh token` along with `access token`

- `Then Why refresh_token grant ?`
  - the refresh_token grant is used by the auth client...to get the new tokens, when they expire
       