package com.paravar;

import java.math.BigDecimal;

record ProductPriceChangedEvent(String productCode, BigDecimal price) {}