package com.paravar;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class Order {
    private Long id;
    private String productCode;
    private BigDecimal price;
    private int quantity;
}
