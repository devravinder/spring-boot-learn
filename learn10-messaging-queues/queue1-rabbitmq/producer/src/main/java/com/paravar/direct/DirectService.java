package com.paravar.direct;

import com.paravar.AppProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DirectService {
    private final RabbitTemplate rabbitTemplate;
    private final AppProperties appProperties;

    public void sendDirectMessage(String message, String routingKey) {
        rabbitTemplate.convertAndSend(appProperties.direct().exchange(), routingKey, message);
        System.out.println("Direct - Sent to " + routingKey + ": " + message);
    }
}
