package com.paravar.direct;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DirectListener {

    @RabbitListener(queues = "${app.rabbitmq.direct.infoQueue}")
    public void receiveInfo(String message) {
        System.out.println("Direct Info received: " + message);
    }

    @RabbitListener(queues ="${app.rabbitmq.direct.errorQueue}")
    public void receiveError(String message) {
        System.out.println("Direct Error received: " + message);
    }
}
