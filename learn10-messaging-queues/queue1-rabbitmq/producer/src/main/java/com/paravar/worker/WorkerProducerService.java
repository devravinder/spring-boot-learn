package com.paravar.worker;

import com.paravar.AppProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WorkerProducerService {
    private final RabbitTemplate rabbitTemplate;
    private final AppProperties appProperties;

    public void sendMessage(String message) {
        // normal string
        rabbitTemplate.convertAndSend(appProperties.work().workQueue(), message);
        System.out.println("Sent Work Item: " + message);
    }
}