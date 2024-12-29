package com.paravar;

import java.math.BigDecimal;
import java.util.List;


// not interacting with any external services
public class SalesReporter {


    public SalesSummary generateSalesSummary(List<Order> orders) {
        int soldItemsCount = 0;
        BigDecimal totalRevenue = BigDecimal.ZERO;

        for (Order order : orders) {
            soldItemsCount += order.getQuantity();
            BigDecimal subTotal = order.getPrice().multiply(BigDecimal.valueOf(order.getQuantity()));
            totalRevenue = totalRevenue.add(subTotal);
        }
        return new SalesSummary(soldItemsCount, totalRevenue);
    }
}
