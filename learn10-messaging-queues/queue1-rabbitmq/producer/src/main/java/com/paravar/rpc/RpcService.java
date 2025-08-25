package com.paravar.rpc;

import com.paravar.AppProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RpcService {
    private final RabbitTemplate rabbitTemplate;
    private final AppProperties appProperties;

    public String sendRpcMessage(String message) {
        // Send message and expect a String response
        Object response = rabbitTemplate.convertSendAndReceive(appProperties.rpc().queueName(), message);
        System.out.println("RPC message sent message: "+message);
        return response != null ? response.toString() : null; // Handle null response safely
    }
}
