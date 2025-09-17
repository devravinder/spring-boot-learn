# JWT 

### Scopes Vs Roles
1. Scopes:-
   - are grated to client app by auth server
   - defines what an app can perform on behalf of user
   - they are given like
       - in jwt token:
            - "scope": "read write update"
            - "scope": "message:read message:write message:update"
         
       - in UserDetails(GrantedAuthority):
            - "authorities": ["SCOPE_read", "SCOPE_write", "SCOPE_update"]
            - "authorities": ["SCOPE_message.read", "SCOPE_message.update"]

2. Roles:-
   - are granted to user by auth server/ resource server
   - defines what a user can perform
   - they are stored as:
       - in UserDetails(GrantedAuthority):
           - "authorities": ["ROLE_USER", "ROLE_ADMIN"]


### Note:-

- Both roles & scopes should be added(converted) to Granted Authorities to use them for authentication
  - but the default Jwt Converter supports only scopes conversion to authorities
  - to convert roles to authorities we use custom converter
- if we support both role & scopes then 
     - the GrantedAuthority will be like
        -  "authorities": ["SCOPE_read" ,"SCOPE_write", "SCOPE_update", "ROLE_USER", "ROLE_ADMIN"]
       
     - scopes are pre-fixed with SCOPE_  & roles are with ROLE_ 