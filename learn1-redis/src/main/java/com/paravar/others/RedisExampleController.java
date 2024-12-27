package com.paravar.others;

import com.redis.lettucemod.timeseries.Sample;
import lombok.AllArgsConstructor;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.GeoResults;
import org.springframework.data.geo.Metrics;
import org.springframework.data.geo.Point;
import org.springframework.data.redis.connection.RedisGeoCommands;
import org.springframework.data.redis.connection.stream.MapRecord;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/redis")
@AllArgsConstructor
public class RedisExampleController {

    private final RedisStringService stringService;
    private final RedisListService listService;
    private final RedisHashService hashService;
    private  final RedisSetService setService;
    private final RedisSortedSetService sortedSetService;
    private final RedisHyperLogLogService hyperLogLogService;
    private final RedisGeoService geoService;
    private final RedisJsonService redisJsonService;
    private final RedisStreamService streamService;
    private final RedisTimeSeriesService timeSeriesService;


    @GetMapping("/examples")
    public void runExamples() {
        // String examples


        System.out.println("============= String Examples =============");

        stringService.setString("user:name", "John Doe");
        String userName = stringService.getString("user:name");
        System.out.printf("User name is %s%n", userName);
        stringService.setWithExpiry("session:token", "abc123", 30, TimeUnit.MILLISECONDS);
        String token = stringService.getString("session:token");
        System.out.printf("Session token is %s%n", token);
        sleep(30);

        token = stringService.getString("session:token");
        System.out.printf(" after 30s Session token is %s%n", token);





        // List examples
        System.out.println("============= List Examples =============");
        listService.addToList("recent:products", "product1");
        listService.addToList("recent:products", "product2");
        listService.addAllToList("recent:products", List.of("product3", "product4"));
        List<String> recentProducts = listService.getListRange("recent:products", 0, -1);
        System.out.printf("Recent products: %s%n", recentProducts);
        long size = listService.getListSize("recent:products");
        System.out.printf("Recent products size: %d%n", size);


        // Set examples
        System.out.println("============= Set Examples =============");
        setService.addToSet("group1:users", "user1", "user2", "user3", "user4");
        Boolean isGroup1 = setService.isMember("group1:users", "user1");
        System.out.printf("User1 is group1: %s%n", isGroup1);

        setService.removeFromSet("group1:users", "user4");
        Set<String> members = setService.getSetMembers("group1:users");
        System.out.printf("group1 users: %s%n", members);

        setService.addToSet("group2:users", "user2", "user3", "user4");
        Set<String> commonMembers = setService.getIntersection("group1:users", "group2:users");
        System.out.printf("Common members: %s%n", commonMembers);


        // Hash examples
        System.out.println("============= Hash Examples =============");
        Map<String, Object> userProfile = new HashMap<>();
        userProfile.put("name", "John");
        userProfile.put("email", "john@example.com");
        hashService.setMultipleHashFields("user:profile:123", userProfile);

        var email = hashService.getHashField("user:profile:123", "email");

        System.out.printf("User email is %s%n", email);

        hashService.setHashField("user:profile:123", "phone", "1234567890");

        var fields = hashService.getAllHashFields("user:profile:123");

        System.out.printf("User profile: %s%n", fields);



        // Sorted Set examples
        System.out.println("============= Sorted Set Examples =============");
        sortedSetService.addToSortedSet("leaderboard", "player1", 100);
        sortedSetService.addToSortedSet("leaderboard", "player2", 200);
        sortedSetService.addToSortedSet("leaderboard", "player3", 50);
        sortedSetService.addToSortedSet("leaderboard", "player4", 600);

        Set<String> topPlayers = sortedSetService.getRangeByScore("leaderboard", 200, 600);
        System.out.println("Top players: " + topPlayers);

        var rank = sortedSetService.getRank("leaderboard", "player2");
        System.out.printf("Player2 rank: %d%n", rank);

        var score = sortedSetService.getScore("leaderboard", "player2");
        System.out.printf("Player2 score: %f%n", score);



        // HyperLogLog examples
        hyperLogLogService.add("page1:visitors", "visitor1", "visitor2", "visitor3");
        long page1Visitors = hyperLogLogService.size("page1:visitors");

        System.out.println("Page1 visitors: " + page1Visitors);

        hyperLogLogService.add("page2:visitors", "visitor3", "visitor4", "visitor5");
        long page2Visitors = hyperLogLogService.size("page2:visitors");

        System.out.println("Page2 visitors: " + page2Visitors);

        Long allVisitors = hyperLogLogService.union("all:visitors", "page1:visitors", "page2:visitors");

        System.out.println("Total visitors: " + allVisitors);






        // Geo examples

        geoService.addLocation("locations", 13.361389, 38.115556, "Palermo");
        geoService.addLocation("locations", 15.087269, 37.502669, "Catania");
        geoService.addLocation("locations", 14.268124, 37.870987, "Messina");

        Distance distance = geoService.getDistance("locations", "Palermo", "Catania", Metrics.KILOMETERS);
        System.out.printf("Distance between Palermo and Catania is %.2f km", distance.getValue());
        System.out.println();

        List<Point> coordinates = geoService.getCoordinates("locations", "Palermo", "Catania");

        coordinates.forEach(point -> {
            System.out.println("Longitude: " + point.getX() + ", Latitude: " + point.getY());
        });


        // Find locations within 200 km of Palermo
        GeoResults<RedisGeoCommands.GeoLocation<String>> nearbyLocations = geoService.findNearby(
                "locations", "Palermo", new Distance(100, Metrics.KILOMETERS));

        // Print results
        nearbyLocations.getContent().forEach(result -> {
            String name = result.getContent().getName();
            System.out.println("Name: " + name );

        });



        System.out.println("============= JSON Example =============");

        redisJsonService.setUserProfile("user123", "Alice", 30, "alice@example.com", "New York");

        String profile = redisJsonService.getUserProfile("user123");
        System.out.println(profile);

        redisJsonService.updateUserProfile("user123", "Alice Johnson", 31, "alice.johnson@example.com", "Los Angeles");

        String summary = redisJsonService.getUserNameAndEmail("user123");
        System.out.println(summary);


        System.out.println("============= Redis Streams Example =============");

        Map<String, String> message1 = new HashMap<>();
        message1.put("orderId", "12345");
        message1.put("product", "laptop");
        message1.put("price", "999.99");
        streamService.addEvent("orders", message1);

        Map<String, String> message2 = new HashMap<>();
        message2.put("orderId", "12345-1");
        message2.put("product", "laptop-1");
        message2.put("price", "999.99-1");
        streamService.addEvent("orders", message2);

        Map<String, String> message3 = new HashMap<>();
        message3.put("orderId", "12345-2");
        message3.put("product", "laptop-2");
        message3.put("price", "999.99-2");
        streamService.addEvent("orders", message3);

        // Create consumer group and process orders
        streamService.createConsumerGroup("orders", "order-processors");

        var events =  streamService.readEvents("orders");
        System.out.println("events "+events);

        List<MapRecord<String, String, String>> orders =
                streamService.readGroup("orders", "order-processors", "worker1", 2);

        orders.forEach(orderRecord -> {
            System.out.println("Processing order: " + orderRecord.getValue());
            System.out.println("order id: "+orderRecord.getId().getValue());
            streamService.acknowledge("orders","order-processors" ,orderRecord.getId().getValue());
        });

        System.out.println("read again");
        orders = streamService.readGroup("orders", "order-processors", "worker1", 2);

        orders.forEach(orderRecord -> {
            System.out.println("Processing order: " + orderRecord.getValue());
            streamService.acknowledge("orders","order-processors" ,orderRecord.getId().getValue());
        });

        System.out.println("========================Time Series Examples=======================");

        // Create temperature series with labels
        String sensorKey = "sensor:temp:1";
        Map<String, String> labels = Map.of(
                "sensor_id", "temp_1",
                "location", "room_a",
                "unit", "celsius"
        );
        timeSeriesService.createTimeSeries(sensorKey, labels);

        // Add some temperature readings
        timeSeriesService.addDataPoint(sensorKey, 22.5);
        timeSeriesService.addDataPoint(sensorKey, 23.1);
        timeSeriesService.addDataPoint(sensorKey, 22.8);

        var instant =  Instant.now();

        var oneHourBack = instant.minus(1, ChronoUnit.HOURS);
        var twoHoursBack = instant.minus(2, ChronoUnit.HOURS);
        var threeHoursBack = instant.minus(3, ChronoUnit.HOURS);

        timeSeriesService.addDataPointWithTimestamp(sensorKey,oneHourBack.toEpochMilli(), 22.5);
        timeSeriesService.addDataPointWithTimestamp(sensorKey,twoHoursBack.toEpochMilli(), 23.1);
        timeSeriesService.addDataPointWithTimestamp(sensorKey,threeHoursBack.toEpochMilli(), 22.8);

        // Create downsampled series for hourly averages
        String avgKey = "sensor:temp:1:hourly";
        timeSeriesService.createTimeSeries(avgKey, Map.of("type", "hourly_avg"));
        timeSeriesService.createAggregationRule(sensorKey, avgKey, 60*60*1000);

        // Query temperature range
        long now = System.currentTimeMillis();
        long hourAgo = now - 60*60*1000;
        List<Sample> samples = timeSeriesService.getRange(sensorKey, hourAgo, now);

        samples.forEach(sample ->
                System.out.printf("Time: %d, Temperature: %.1f%n",
                        sample.getTimestamp(),
                        sample.getValue())
        );

    }

    void sleep(long ms){
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}