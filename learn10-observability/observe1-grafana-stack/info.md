# Grafana stack (Prometheus + Loki + Tempo + Grafana)

## Prerequisites
 - docker & docker-compose

## Starting & stopping
  - start
    - 1st infra
      - inside `infra` folder `run docker compose up -d`
    - then start apps
    
  - stopping
    - 1st stop apps
    - then inside `infra` folder `run docker compose down` 


## Combinations
 - Logs:
    - we are pushing logs to `loki` server
        - using  `logback appender`
   
 - Metrics
   - we enabled the metrics endpoints using `spring boot actuator`
   - we are sending the logs using `micrometer-registry-prometheus` to `prometheus`
   
 - Tracing
   - we are generating tracings using `micrometer-tracing-bridge-otel`
     - and pushing them to `tempo` using `opentelemetry-exporter-zipkin`


## 
1. in Prometheus
   - visit: http://localhost:9090/targets  ( GUI: under status > targets )
   - config: http://localhost:9090/config  ( GUI: status > config )
   - Executing query: GUI: under Query > CTRL + SPACE will give auto-suggestion
       - make sure auto-complete is enabled under settings

2. Grafana
   - Add data sources
      - Connections > Data Sources > Select Prometheus /Loki / Tempo > New Connection
        - add Prometheus , Loki & Tempo
        - give host name & port as per docker ( see docker-compose )
        - eg: http://prometheus:9090
  
   - View 
     - Logs
       - goto explore > Select Loki ( from dropdown ) > select Time Frame (icon) > Refresh (icon)
           - add filters 
              - service_name = order_service
             
            - Note: if data is not showing...change time frame & refresh
         
     - Trace
       - explorer > tempo > Select Time Frame 
          - goto TraceQL > paste the traceId (get it from logs)
              - refresh if needed
       
   - Dashboard
      - dashboards > create dashboard > import existing
        ( visit the website url shown in the adding page )
          - search spring boot observability & copyId
         - add by ID
         - select Prometheus & Loki data sources ...then import

### Note:- 
1. to propagate trace headers...create webclient using  WebClient.Builder
   - else it'll create traceId will different in each service for the same request
   - see WebClientConfig
     ```java
        @Bean
         public WebClient webClient(WebClient.Builder builder){
            return builder
               .build();
         }
      ```