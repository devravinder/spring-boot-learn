package com.paravar.topic;

import com.paravar.AppProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TopicListener {


    @RabbitListener(queues ="${app.rabbitmq.topic.logsQueue}")
    public void receiveAll(String message) {
        System.out.println("Topic: All logs: " + message);
    }

    @RabbitListener(queues ="${app.rabbitmq.topic.errorQueue}")
    public void receiveErrorOnly(String message) {
        System.out.println("Topic: Error only: " + message);
    }
}
