package com.paravar;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@RedisHash("Product")
@Data
public class Product {
    @Id
    private String id;
    private String name;
    private double price;
}
