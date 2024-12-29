package com.paravar;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
@AllArgsConstructor
@Slf4j
class ProductPriceChangedEventHandler {


    private final ProductRepository productRepository;

    @RabbitListener(queues = "product-price-changes")
    public void handle(ProductPriceChangedEvent event) {
        log.info(
                "Received a ProductPriceChangedEvent with productCode:{}: ",
                event.getProductCode()
        );
        productRepository.updateProductPrice(event.getProductCode(), event.getPrice());
    }
}