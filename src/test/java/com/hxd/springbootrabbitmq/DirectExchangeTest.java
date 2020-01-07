package com.hxd.springbootrabbitmq;

import org.junit.jupiter.api.Test;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * Created by hee on 2020/1/7 10:53
 */
@SpringBootTest
public class DirectExchangeTest {

    @Autowired
    private AmqpTemplate amqpTemplate;

    @Test
    void senderA() {
        amqpTemplate.convertAndSend("direct_exchange", "direct_routing_key_a", "Hello Direct AAA");
    }

    @RabbitHandler
    @RabbitListener(queues = "direct_queue_a")
    void receiverA(String msg) {
        System.out.println(msg);
    }

    @Test
    void senderB() {
        amqpTemplate.convertAndSend("direct_exchange", "direct_routing_key_b", "Hello Direct BBB");
    }

    @RabbitHandler
    @RabbitListener(queues = "direct_queue_b")
    void receiverB(String msg) {
        System.out.println(msg);
    }
}
