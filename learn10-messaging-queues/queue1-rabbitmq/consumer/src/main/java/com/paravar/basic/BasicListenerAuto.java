package com.paravar.basic;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@ConditionalOnProperty(name = "spring.rabbitmq.listener.simple.acknowledge-mode", havingValue = "auto", matchIfMissing = true)
public class BasicListenerAuto {


    // change:  acknowledge-mode: manual to auto,


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
    public void receiveText(String message) {
        System.out.println("Received (auto acknowledge): " + message);
    }

    @RabbitListener(queues = "${app.rabbitmq.basic.objQueue}")
    public void receiveObj(Map<String, String> message) {
        System.out.println("Received (auto acknowledge): " + message);
    }

}