package com.paravar.worker;

import com.rabbitmq.client.Channel;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@RequiredArgsConstructor
@ConditionalOnProperty(name = "spring.rabbitmq.listener.simple.acknowledge-mode", havingValue = "manual")
public class WorkListener {


    /*
     - by default...auto acknowledgement is enabled...once after the message & received & processed
         - it'll be auto acknowledged

     - we can change acknowledgement to 'manual'
        - then we need to trigger acknowledgement
           - channel.basicAck(tag, false);


     - if the acknowledgement mode is manual & if we not acknowledge...
           then it'll be delivered again...on consumer restart or to a different new consumer

    * */

    @RabbitListener(queues = "${app.rabbitmq.work.workQueue}")
    public void receiveWork1(String message, Channel channel, @Header(AmqpHeaders.DELIVERY_TAG) long tag) throws IOException {
        System.out.println("Received work in 1: " + message);

        /*
        Delivery tag is unique number(id) for message per channel ( not globally )
        */

        try {
            // Simulate work
            Thread.sleep(1000);

            // Acknowledge after processing
            channel.basicAck(tag, false);
               // boolean is for 'multiple', if false means acknowledging single message...not multiple


        } catch (InterruptedException e) {

            // 👇 In case of error, reject & requeue (or not)
            channel.basicNack(tag, false, true); // requeue = true
            // first boolean is for 'multiple' , second boolean is for 'requeue'

            Thread.currentThread().interrupt();
        }


    }

    @RabbitListener(queues = "${app.rabbitmq.work.workQueue}")
    public void receiveWork2(String message, Channel channel, @Header(AmqpHeaders.DELIVERY_TAG) long tag) throws IOException {
        System.out.println("Received work in 2: " + message);

        /*
        Delivery tag is unique number(id) for message per channel ( not globally )
        */

        try {
            // Simulate work
            Thread.sleep(1000);

            // Acknowledge after processing
            channel.basicAck(tag, false);
            // boolean is for 'multiple', if false means acknowledging single message...not multiple


        } catch (InterruptedException e) {

            // 👇 In case of error, reject & requeue (or not)
            channel.basicNack(tag, false, true); // requeue = true
            // first boolean is for 'multiple' , second boolean is for 'requeue'

            Thread.currentThread().interrupt();
        }


    }
}