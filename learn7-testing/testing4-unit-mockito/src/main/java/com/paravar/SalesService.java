package com.paravar;

import lombok.AllArgsConstructor;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@AllArgsConstructor
public class SalesService {
    private final OrderRepository orderRepository;
    private final SalesReporter salesReporter;

    public SalesSummary getSalesSummary(String productCode) {
        List<Order> orders = orderRepository.getOrders(productCode);
        return salesReporter.generateSalesSummary(orders);
    }

    public String getMostSoldProduct() {
        List<Order> orders = orderRepository.getAllOrders();
        Map<String, Integer> map =
                orders.stream()
                        .collect(Collectors.groupingBy(
                                Order::getProductCode,
                                Collectors.summingInt(Order::getQuantity)));
        return Collections.max(map.entrySet(),
                Comparator.comparingInt(Map.Entry::getValue)).getKey();
    }
}
