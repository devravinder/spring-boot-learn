package com.paravar.fanout;

import com.paravar.AppProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FanOutListener {
    @RabbitListener(queues = "${app.rabbitmq.fan-out.fanOutQueue1}")
    public void receiveFanOut1(String message) {
        System.out.println("FanOut1 received: " + message);
    }

    @RabbitListener(queues = "${app.rabbitmq.fan-out.fanOutQueue2}")
    public void receiveFanOut2(String message) {
        System.out.println("FanOut2 received: " + message);
    }
}
