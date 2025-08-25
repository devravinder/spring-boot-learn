package com.paravar.topic;

import com.paravar.AppProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TopicService {
    private final RabbitTemplate rabbitTemplate;
    private final AppProperties appProperties;

    public void sendTopicMessage(String message, String routingKey) {
        rabbitTemplate.convertAndSend(appProperties.topic().exchange(), routingKey, message);
        System.out.println("Sent to topic " + routingKey + ": " + message);
    }
}
