package com.paravar;

import lombok.Data;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@Data
public class AppController {

    private final ApplicationEventPublisher publisher;

    @RequestMapping
    public String hello() {
        publisher.publishEvent(new AppEvent(this, "Hi, How are you?", "Paravar"));
        return "Hello World!";
    }
}
