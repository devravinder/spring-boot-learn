package com.paravar.basic;

import com.paravar.AppProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class BasicProducerService {
    private final RabbitTemplate rabbitTemplate;
    private final AppProperties appProperties;

    public void sendMessage(String message) {

        // normal string
        rabbitTemplate.convertAndSend(appProperties.basic().textQueue(), message);
        System.out.println("Sent: " + message);


        // obj
        Map<String, String> obj = new HashMap<>();
        obj.put("hi", "how are you?");
        obj.put("hello", "how are you?");
        rabbitTemplate.convertAndSend(appProperties.basic().objQueue(), obj);

        System.out.println("Sent: " + obj);
    }
}