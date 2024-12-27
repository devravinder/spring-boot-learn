package com.paravar.others;

import com.redis.lettucemod.api.sync.RedisModulesCommands;
import com.redis.lettucemod.timeseries.*;
import io.lettuce.core.RedisCommandExecutionException;
import lombok.AllArgsConstructor;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.GeoResults;
import org.springframework.data.geo.Metric;
import org.springframework.data.geo.Point;
import org.springframework.data.redis.RedisSystemException;
import org.springframework.data.redis.connection.RedisGeoCommands;
import org.springframework.data.redis.connection.stream.*;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import com.redis.lettucemod.api.StatefulRedisModulesConnection;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@Service
@AllArgsConstructor
class RedisStringService {

    /*
     for simple strings ( key, & value ) operations
     we can use StringRedisTemplate
    * */

    private final StringRedisTemplate stringRedisTemplate;

    public void setString(String key, String value) {
        stringRedisTemplate.opsForValue().set(key, value);
    }

    public void setWithExpiry(String key, String value, long timeout, TimeUnit unit) {
        stringRedisTemplate.opsForValue().set(key, value, timeout, unit);
    }

    public String getString(String key) {
        return stringRedisTemplate.opsForValue().get(key);
    }
}

// List Operations Example
@Service
@AllArgsConstructor
class RedisListService {
    /*
        for list operations ( duplicates allowed ) // multiple string values
        we can use RedisTemplate
    */
    private final RedisTemplate<String, String> redisTemplate;

    public void addToList(String listKey, String value) {
        redisTemplate.opsForList().rightPush(listKey, value);
    }

    public void addAllToList(String listKey, List<String> values) {
        redisTemplate.opsForList().rightPushAll(listKey, values);
    }

    public List<String> getListRange(String listKey, long start, long end) {
        return redisTemplate.opsForList().range(listKey, start, end);
    }

    public Long getListSize(String listKey) {
        return redisTemplate.opsForList().size(listKey);
    }
}


// Set Operations Example
@Service
@AllArgsConstructor
class RedisSetService {

    /*
     for set operations ( non duplicate values ) // multiple string values
    * */
    private final RedisTemplate<String, String> redisTemplate;

    public void addToSet(String setKey, String... values) {
        redisTemplate.opsForSet().add(setKey, values);
    }

    public Set<String> getSetMembers(String setKey) {
        return redisTemplate.opsForSet().members(setKey);
    }

    public Boolean isMember(String setKey, String value) {
        return redisTemplate.opsForSet().isMember(setKey, value);
    }

    public Set<String> getIntersection(String key1, String key2) {
        return redisTemplate.opsForSet().intersect(key1, key2);
    }

    public void removeFromSet(String setKey, String value) {
        redisTemplate.opsForSet().remove(setKey, value);
    }
}


// Hash Operations Example
@Service
@AllArgsConstructor
class RedisHashService {

    /*
    for objects / map operations
    * */

    private final RedisTemplate<String, Object> redisTemplate;

    public void setHashField(String key, String field, Object value) {
        redisTemplate.opsForHash().put(key, field, value);
    }

    public void setMultipleHashFields(String key, Map<String, Object> map) {
        redisTemplate.opsForHash().putAll(key, map);
    }

    public Object getHashField(String key, String field) {
        return redisTemplate.opsForHash().get(key, field);
    }

    public Map<Object, Object> getAllHashFields(String key) {
        return redisTemplate.opsForHash().entries(key);
    }
}

// Sorted Set Operations Example
@Service
@AllArgsConstructor
class RedisSortedSetService {

    /*
     Use Case: Ranking systems, leaderboards, or scheduling tasks with scores.
     sort by score

     eg: marks of students
    * */
    private final RedisTemplate<String, String> redisTemplate;

    public void addToSortedSet(String key, String value, double score) {
        redisTemplate.opsForZSet().add(key, value, score);
    }

    public Set<String> getRangeByScore(String key, double min, double max) {
        return redisTemplate.opsForZSet().rangeByScore(key, min, max);
    }

    public Long getRank(String key, String value) {
        return redisTemplate.opsForZSet().rank(key, value);
    }

    public Double getScore(String key, String value) {
        return redisTemplate.opsForZSet().score(key, value);
    }
}

// HyperLogLog Operations Example
@Service
@AllArgsConstructor
class RedisHyperLogLogService {

    /*
    Use Case: Counting unique elements in a set.

    eg: Counting unique visitors to a website.
    * */

    private final RedisTemplate<String, String> redisTemplate;

    public void add(String key, String... values) {
        redisTemplate.opsForHyperLogLog().add(key, values);
    }

    public Long size(String key) {
        return redisTemplate.opsForHyperLogLog().size(key);
    }

    public Long union(String destination, String... sourceKeys) {
        return redisTemplate.opsForHyperLogLog().union(destination, sourceKeys);
    }
}

@Service
@AllArgsConstructor
class RedisGeoService {
    /*
     Use Case: Location-based features, nearby places
     Stores location data with latitude and longitude
    */
    private final RedisTemplate<String, String> redisTemplate;

    public void addLocation(String key, double longitude, double latitude, String member) {
        redisTemplate.opsForGeo().add(key, new Point(longitude, latitude), member);
    }

    public Distance getDistance(String key, String member1, String member2, Metric metric) {
        return redisTemplate.opsForGeo().distance(key, member1, member2, metric);
    }

    public List<Point> getCoordinates(String key, String... members) {
        return redisTemplate.opsForGeo().position(key, members);
    }

    public GeoResults<RedisGeoCommands.GeoLocation<String>> findNearby(
            String key, String member, Distance distance) {
        return redisTemplate.opsForGeo().radius(key, member, distance);
    }
}

// ============ advanced ============

/*
 Graphs are deprecated in RedisGraph 2.0.0, removing the feature from the library.
   - so better to use Graph databases like Neo4j, Amazon Neptune, etc.

* */

@Service
@AllArgsConstructor
class RedisJsonService {
    /*
     * Use Case: Storing and retrieving JSON data (e.g., user profiles, product details, etc.)
     * RedisJSON allows storage and manipulation of JSON data directly in Redis.
     */
    private final StringRedisTemplate stringRedisTemplate;

    public void setUserProfile(String userId, String name, int age, String email, String city) {
        String key = "user:" + userId;
        String json = "{ \"name\": \"" + name + "\", \"age\": " + age + ", \"email\": \"" + email + "\", \"city\": \"" + city + "\" }";
        stringRedisTemplate.opsForValue().set(key, json);
    }

    public String getUserProfile(String userId) {
        String key = "user:" + userId;
        return stringRedisTemplate.opsForValue().get(key);
    }

    public void updateUserProfile(String userId, String name, int age, String email, String city) {
        String key = "user:" + userId;
        String json = "{ \"name\": \"" + name + "\", \"age\": " + age + ", \"email\": \"" + email + "\", \"city\": \"" + city + "\" }";
        stringRedisTemplate.opsForValue().set(key, json);
    }

    public String getUserNameAndEmail(String userId) {
        String key = "user:" + userId;
        String json = stringRedisTemplate.opsForValue().get(key);

        // Assuming the JSON is formatted in a standard way
        String name = extractJsonValue(json, "name");
        String email = extractJsonValue(json, "email");

        return "Name: " + name + ", Email: " + email;
    }

    private String extractJsonValue(String json, String key) {
        return json.split("\"" + key + "\": \"")[1].split("\"")[0];
    }
}


@Service
@AllArgsConstructor
class RedisStreamService {
    /*
     * Use Case: Handling Event-driven systems, message queues, etc.
     * Redis Streams allow the management of streams of messages (events) with consumer groups, message acknowledgment, etc.
     */
    private final RedisTemplate<String, String> redisTemplate;

    public void addEvent(String streamKey, Map<String, String> event) {
        StreamRecords.newRecord()
                .in(streamKey)
                .ofMap(event);
        redisTemplate.opsForStream()
                .add(StreamRecords.newRecord()
                        .in(streamKey)
                        .ofMap(event));
    }

    public List<MapRecord<String, String, String>> readEvents(String streamKey) {
        return redisTemplate.<String, String>opsForStream()
                .read(StreamOffset.fromStart(streamKey));
    }

    public List<MapRecord<String, String, String>> readGroup(String streamKey,
                                                             String groupName,
                                                             String consumerName, int size) {
        return redisTemplate.<String, String>opsForStream()
                .read(
                        Consumer.from(groupName, consumerName),
                        StreamReadOptions.empty().count(size),
                        StreamOffset.create(streamKey, ReadOffset.lastConsumed())
                );
    }

    public void acknowledge(String streamKey, String groupName, String recordId) {
        redisTemplate.opsForStream()
                .acknowledge(streamKey, groupName, recordId);
    }

    public void createConsumerGroup(String streamKey, String groupName) {
        try {
            redisTemplate.opsForStream().createGroup(streamKey, ReadOffset.from("0"), groupName);
        } catch (RedisSystemException e) {

            if (e.getCause().getMessage().contains("BUSYGROUP")) {
                System.out.println(groupName + " Consumer group already exists");
            } else {
                throw e;
            }
            // Group already exists, continue silently
        }
    }
}

@Service
@AllArgsConstructor
class RedisTimeSeriesService {
    /*
        * Use Case: Time-series data, monitoring, analytics, IoT devices ,etc.
        * RedisTimeSeries allows the storage and querying of time-series data with aggregation, downsampling, etc.
        * used along with the data that changes over a time period
    * */
    private final RedisModulesCommands<String, String> commands;

    public void createTimeSeries(String key, Map<String, String> labels) {
        try {

            CreateOptions<String, String> options = CreateOptions.<String, String>builder()
                    .labels(labels)
                    .build();

            commands.tsCreate(key, options);
            System.out.println("Created time series: " + key);
        } catch (RedisCommandExecutionException e) {
            if (e.getCause().getMessage().contains("TSDB: key already exists")) {
                System.out.println("Time series already exists");
            } else {
                System.out.println("Error creating time series: " + e.getMessage());
                throw new RuntimeException("Failed to create time series", e);
            }

        }
    }

    public void addDataPoint(String key, double value) {
        try {
            commands.tsAdd(key, Sample.of(System.currentTimeMillis(), value));
            System.out.println("Added data point to " + key + ": " + value);
        } catch (Exception e) {
            System.out.println("Error adding data point: " + e.getMessage());
            throw new RuntimeException("Failed to add data point", e);
        }
    }

    public void addDataPointWithTimestamp(String key, long timestamp, double value) {
        try {
            commands.tsAdd(key, Sample.of(timestamp, value));
            System.out.println("Added data point to " + key + " at " + timestamp + ": " + value);
        } catch (Exception e) {
            System.out.println("Error adding data point with timestamp: " + e.getMessage());
            throw new RuntimeException("Failed to add data point with timestamp", e);
        }
    }

    public List<Sample> getRange(String key, long fromTimestamp, long toTimestamp) {
        try {
            TimeRange timeRange = TimeRange.from(fromTimestamp).to(toTimestamp).build();


            // Create RangeOptions object with various configurations
            RangeOptions rangeOptions = RangeOptions.builder()
                    .count(100)           // Limit results to 100 samples
                    .aggregation(new Aggregation(Aggregator.AVG, Duration.ofSeconds(60)))
                    // 1-minute aggregation using average
                    .build();
            return commands.tsRange(key, timeRange, rangeOptions);
        } catch (Exception e) {
            System.out.println("Error getting range: " + e.getMessage());
            throw new RuntimeException("Failed to get range", e);
        }
    }

    // Example of creating a rule for downsampling
    public void createAggregationRule(String sourceKey, String destinationKey, long timeBucket) {
        try {
            CreateRuleOptions options = CreateRuleOptions.builder(Aggregator.AVG).bucketDuration(Duration.of(timeBucket, ChronoUnit.MILLIS)).build();
            commands.tsCreaterule(sourceKey, destinationKey, options);
            System.out.println("Created aggregation rule from " + sourceKey + " to " + destinationKey);
        } catch (RedisCommandExecutionException e) {
            if (e.getCause().getMessage().contains("the destination key already has")) {
                System.out.println("Aggregation rule already exists");
            } else {
                System.out.println("Error creating aggregation rule: " + e.getMessage());
                throw new RuntimeException("Failed to create aggregation rule", e);
            }
        }
    }
}



