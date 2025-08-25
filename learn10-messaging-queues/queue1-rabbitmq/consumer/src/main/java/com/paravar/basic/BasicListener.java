package com.paravar.basic;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;

@Component
@ConditionalOnProperty(name = "spring.rabbitmq.listener.simple.acknowledge-mode", havingValue = "manual")
public class BasicListener {


    // change:  acknowledge-mode: manual to auto, and uncomment & see auto acknowledge

     /*
     - by default...auto acknowledgement is enabled...once after the message & received & processed
         - it'll be auto acknowledged

     - we can change acknowledgement to 'manual'
        - then we need to trigger acknowledgement
           - channel.basicAck(tag, false);


     - if the acknowledgement mode is manual & if we not acknowledge...
           then it'll be delivered again...on consumer restart or to a different new consumer

    * */

    @RabbitListener(queues = "${app.rabbitmq.basic.textQueue}")
    public void receiveText(String message, Channel channel, @Header(AmqpHeaders.DELIVERY_TAG) long tag) throws IOException {
        System.out.println("Received: " + message);
        channel.basicAck(tag, false);
        // boolean is for 'multiple', if false means acknowledging single message...not multiple

    }

    @RabbitListener(queues = "${app.rabbitmq.basic.objQueue}")
    public void receiveObj(Map<String, String> message, Channel channel, @Header(AmqpHeaders.DELIVERY_TAG) long tag) throws IOException {
        System.out.println("Received: " + message);
        channel.basicAck(tag, false);
    }
}