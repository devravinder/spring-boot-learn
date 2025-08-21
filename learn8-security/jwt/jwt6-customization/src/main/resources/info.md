# JWT Customization
- using CustomGrantedAuthority to hold permissions
     - it can have Permission objects or any complex objects

- using CustomJwtAuthenticationToken to store User as principle
- using CustomJwtAuthenticationConverter to convert the Jwt token
     - to
        - CustomJwtAuthenticationToken
        - extract CustomGrantedAuthority from roles ( claims )
       
- using CustomAccessDeniedHandler & CustomAuthenticationEntryPoint for error handling

## Note:-
- we are not Implementing UserDetails 
   - as we are not depending on UserDetailsService for authentication

- we are using custom authentication mechanism ( initial login )