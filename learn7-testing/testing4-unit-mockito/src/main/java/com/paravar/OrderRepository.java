package com.paravar;

import java.util.List;

// interacting with external services
public class OrderRepository {

    public List<Order> getOrders(String productCode) {
        //query DB or fetch from REST API
        return List.of();
    }

    public List<Order> getAllOrders() {
        //query DB or fetch from REST API
        return List.of();
    }
}
