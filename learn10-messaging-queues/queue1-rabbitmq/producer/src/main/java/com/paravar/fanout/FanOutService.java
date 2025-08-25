package com.paravar.fanout;

import com.paravar.AppProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FanOutService {
    private final RabbitTemplate rabbitTemplate;
    private final AppProperties appProperties;

    public void sendFanOutMessage(String message) {
        rabbitTemplate.convertAndSend(appProperties.fanOut().exchange(), "", message);
        System.out.println("Broadcast: " + message);
    }
}
