1. Redis
2. Events
3. Schedulers
4. Multiple Databases
5. Database Migrations
6. Actuator
7. Code formatting
8. Git Commit 
9. Testing 
     - Testing Types in Spring Boot

    | Type                     | Scope                           | Tools                         |
    |--------------------------|---------------------------------|-------------------------------|
    | Unit Testing             | Single component                | JUnit, Mockito, Assertj       |
    | Integration Testing      | Interaction of components       | Spring Test, @SpringBootTest  |
    | Web Layer Testing        | Controller logic                | MockMvc                       |
    | End-to-End Testing       | Full application                | TestRestTemplate, RestAssured |
    | Database Testing         | Repository layer                | @DataJpaTest, H2              |
    | Mocking Dependencies     | Mock external systems           | WireMock                      |
    | Performance Testing      | Application performance         | JMeter, Gatling               |
    | Security Testing         | Authentication/authorization    | Spring Security Test          |

   - learn:- JUnit 5, RestAssured, Testcontainers, Awaitility, WireMock
   
   - Unit Testing:
      - JUnit ( Jupiter )
      - Mockito
      - Assertj
     
   - Integration Testing:
      - Testcontainers
      - RestAssured
      - WireMock / Mockserver
      - Awaitility
      - Karate DSL ( based on Cucumber )
     
   - End-To-End Testing:
      - Playwright
      - Cypress
     
   - Performance Testing:
      - Gatling
      - JMeter
      - K6
      - 
10. Test Containers
11. Message Queues
12. Observability ( Grafana, Prometheus, Loki, tempo )
13. Distributed locks & Job Scheduling
14. Resiliency: CircuitBreaker, Retry, RateLimiter, Bulkhead, TimeLimiter ( Resilience4j ) 
15. oAuth2
16. Docker & Kubernetes
17. re-usable modules
18. SSE