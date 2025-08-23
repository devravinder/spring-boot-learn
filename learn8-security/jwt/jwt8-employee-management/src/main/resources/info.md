# Employee Management

## Customization
- custom authentication ( JwtAuthToken )
- custom jwt converter ( JwtConverter )
- custom evaluation object ( AccessControl )
- custom Authentication Principal ( AuthPrincipal )
- custom GrantedAuthority (Permission )
- custom auth exception handlers
    - ResourceAccessDeniedHandler
    - AuthEntryPoint
- custom login ( AuthResource /login)

## Things to observe
- Port & Adapter pattern
- @PreAuthorize & @PostAuthorize at the interface level ( EmployeeQuery )
- Custom DB queries ( EmployeeH2Repository )
  - projections
  - slice 
