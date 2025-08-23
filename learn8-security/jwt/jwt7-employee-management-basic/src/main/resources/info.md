
## Authentication
 - we are creating a AuthenticationManager bean to trigger authentication manually using username & password
     - this internally uses DAOAuthenticationProvider
            - in our case DAOAuthenticationProvider bean is autoconfigured
   
   - or we can use Authentication Filters          (  we are not doing this )
 - or we can use custom Authentication Providers   (  we are not doing this )

## Authorization ( Authentication also )
 - user has to send the token in headers
 - this token will be validated ( authentication )
 - if the token is valid & it'll inject Authentication object in the securityContext
     - if we are depending on jwt default converter
          - then the Authentication obj is 'jwtAuthentication' & principle is Jwt
                      - see UserController commented code
     
    - in our case we are using customJwtConverter
       - so we can access EmployeeDetails as principle
       - Note: we are injecting(returning) 'UsernamePasswordAuthenticationToken' from customJwtConverter
              - bcz it'll accept UserDetails(EmployeeDetails) as principle
              - JwtAuthentication accepts only username as principle
 


## Best Practices
- we are implementing cache for  `EmployeeDetailsService.loadUserByUsername` internally