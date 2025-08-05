# Two use multiple Databases

1. in application.yaml
   - we need to pass two datasource for each db 
   - 
2. we should use two different configuration files for each db
   - each configuration should initialize beans of
     - Datasource
     - EntityManagerFactory (EntityFactory)
     - TransactionManager
   - also need to pass references of EntityManagerFactory & TransactionManager in @EnableJpaRepositories

3. `very important`
   - make only one configuration beans as primary beans
      - these will be auto-injected
   - to inject non-primary beans
      - use qualifier, sometimes method named-resolution won't work  *or*
      - call the method directly to pass as args

4. *optional*
   - we can pass separate jpa properties for each DB