package com.paravar;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class ProductController {

    @GetMapping("/products")
    public String getProducts() {
        log.info("Fetching products");
        return "Product List: Apple, Banana";
    }
}