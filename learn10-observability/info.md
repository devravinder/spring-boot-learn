# Observability


1. What is Observability?
   - logs, metrics, traces
   - In microservices, it helps answer:
     - What’s happening? (Are services healthy?)
     - Why is it happening? (Root cause analysis)
     -  Where is the problem? (Pinpoint bottlenecks, errors)
     
2. Key Pillars of Observability (the 3 + 1 signals):

    1. Logs → Text records of events (errors, requests, exceptions).
    2. Metrics → Numeric data over time (CPU, memory, request latency, throughput).
    3. Traces → Request journey across services (helps track latency between microservices).
    4. (Optional: Profiles) → CPU/memory profiling for performance bottlenecks.

### Famous Observability Stacks

1. Logs
   1. Agents
      - Logstash
      - Fluentd
      - Pomtail
      
   2. Storage
      - Elasticsearch
      - Loki
   3. Visualizers ( Dashboards )
      - Kibana
      - Grafana ( via LogQL )

2. Metrics
   1. Agents
      - Spring Boot Actuator + Micrometer
      
   2. Storage
      - Prometheus 
      - Metricbeat
      
   3. Visualizers ( Dashboards )
      - Kibana
      - Grafana ( via LogQL )
    
3. Tracing
   1. Agents
      - Jaeger
      - Zipkin
          - these agents acts like data source, and produce trances and send to centralized server
              - Jaeger is new one compared to Zipkin
   2. Storage         
      - Tempo
      - Elastic APM
      
   3. Visualizers ( Dashboards )
       - Kibana
       - Grafana ( via LogQL )   

4. Frameworks
    1. OpenTelemetry (OTel)
       - A standard framework for collecting metrics, logs, and traces
       - exports to Prometheus, Jaeger, Grafana, Datadog, etc.
       - Provide a vendor-neutral way to instrument applications for observability 
           - telemetry data = logs, metrics, traces
       - we can send telemetry data to any stack
       - OpenTelemetry is the “universal language” of observability.
       
    2. Commercial / Managed Tools
       - Datadog, New Relic, Splunk, Dynatrace, AWS CloudWatch, Azure Monitor

5. Dashboards
   1. Kibana
   2. Grafana


### Famous Combinations
1. Grafana stack (Prometheus + Loki + Tempo + Grafana)
   - Prometheus → Metrics
   - Loki → Logs
   - Tempo → Traces
   - Grafana → Unified UI
   
   - these stack is cost-effective,
      - indexes only metadata
      - advanced search by attributes is not possible
      - Object storage (S3, GCS, Azure Blob, MinIO, filesystem) → cheap & simple
      - ✅ Best for cloud-native / Kubernetes setups.
      - ✅ Lightweight, cost-efficient, open source, CNCF + Grafana Labs ecosystem.
   
2. ELK / Elastic Stack ( Elastic + Logstash / Beats / Fluentd + Kibana )
   - Metrics → Metricbeat (or Prometheus → Elastic)
   - Logs → Logstash / Beats → Elasticsearch
   - Traces → APM (Elastic APM or Jaeger → Elastic)
   - Visualization → Kibana

   -  ✅ Best if you’re already invested in Elasticsearch.
   -  ✅ Very powerful log search & indexing, good APM.
   -  ❌ Heavier infrastructure, more resource-hungry.

3. Jaeger + Prometheus + Grafana
   - Metrics → Prometheus
   - Logs → (Fluentd/Fluent Bit → Elasticsearch OR Loki)
   - Traces → Jaeger
   - Visualization → Grafana (with Jaeger plugin)
    
   - ✅ Rich tracing (search by tags, service, operation).
   - ✅ Good for debugging distributed microservices.

4. OpenTelemetry + Any Backend
   - Metrics, Logs, Traces → Collected by OpenTelemetry SDK + OTel Collector
   - Backends → can send to Prometheus, Jaeger, Zipkin, Tempo, Loki, Elastic, Datadog, New Relic, etc.
   - Visualization → Grafana or vendor dashboards
   - ✅ Vendor-neutral, flexible, future-proof.
   - ✅ Single instrumentation standard → switch backends anytime.

5. Commercial Cloud-Native Observability
   - Datadog → Logs, Metrics, Traces, Visualization (all-in-one SaaS).
   - New Relic → Same, plus infra monitoring.
   - Splunk Observability → Logs + Metrics + APM.
   - ✅ Fully managed, fast to adopt.
   - ❌ Costly, vendor lock-in.

### Note:-
1. Actuator is the default metrics generator
2. logback is default logging framework
