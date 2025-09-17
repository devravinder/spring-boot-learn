# Rabbitmq


## Terms
- durable
   - true:
     - when we publish message & queue is created
     - Queue exists even after server restart
     
  - false:
      - when we publish message & queue is created
      - these queues are volatile...won't exist after restart


## Types

1. Basic Queues
   - point-to-point messaging: Producer sends a message to a queue, consumer receives it.
   
2. Worker Queues
   - Distributes tasks to multiple workers
   - Similar to basic queue, but durable queue with acknowledgments
   - uses round-robin distribution
   - Run multiple consumer instances & check
   
3. Publish/Subscribe (Fanout Exchange) (Broadcast)
   - Broadcasts messages to all bound queues.
   - producer sends message using exchange
   - the consumers are attached to exchange...from that they receive message

4. Routing (Direct Exchange)
   - Routes messages to queues based on routing keys.
   - the consumers are attached to exchange...and they are further tied to key
       - based on exchange & key ...they receive messages
   - key is like path variable in rest end point & exchange is route end point

5. Topics (Topic Exchange)
   - Routes based on pattern matching (e.g., "*.error", "logs.#")
   
6. RPC (Request/Reply)
   - Producer sends request and waits for reply.
   - For this, consumer processes and replies.