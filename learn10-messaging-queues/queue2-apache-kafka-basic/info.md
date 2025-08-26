# Kafka (Kraft Mode)
 - No Zookeeper in the latest versions after 2.8 - Kraft Mode



## Terms
1. Topic
   - A topic in Kafka is like a category or channel where messages are stored and published.
   - Producers write (publish) messages to a topic.
   - Consumers read (subscribe) messages from a topic.

2. Partition
   - Each topic is split into partitions (small, ordered logs).
   - A partition is the actual unit where messages are stored.
   - Each partition is: 
   - Ordered (messages inside one partition keep the order).
   - Immutable (once written, can’t be changed).
   - Identified by an integer (0, 1, 2, …).

3. Consumer Group
   - A consumer group is a set of one or more consumers 
      - that work together to read data from a Kafka topic.
   - Each consumer in the group reads data from different partitions of the topic.
   - Kafka ensures that each partition is consumed by only one consumer in the group (no duplication).
   - Multiple groups can read the same topic independently.


## Examples

1. Simple Messaging (Basic)
   - Producer sends messages to a topic; consumer listens.

2. Consumer Groups
   - Multiple consumer instances in the same group share messages across partitions.
   - same like simple...but multiple instances

3. Topic Partitioning (partition)
   - Send messages to specific partitions.
   
4. Key-Based Routing
   - Messages with the same key go to the same partition.

5. Stream Processing ( with Kafka Streams )
   - processing data in real-time with stateful/stateless transformations

6. Transactions
   - ensuring exactly-once delivery semantics when producing/consuming.