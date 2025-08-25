package com.paravar.rpc;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RpcListener {

    @RabbitListener(queues = "${app.rabbitmq.rpc.queueName}")
    @SendTo  // Replies to the replyTo queue
    public String handleRpc(String message) {
        System.out.println("RPC request: " + message);
        return "Reply to: " + message.toUpperCase();  // Process and reply
    }
}
