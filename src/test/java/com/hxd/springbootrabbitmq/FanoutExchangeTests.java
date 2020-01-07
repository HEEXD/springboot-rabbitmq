package com.hxd.springbootrabbitmq;

import org.junit.jupiter.api.Test;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * Fanout Exchange
 */
@SpringBootTest
public class FanoutExchangeTests {

    @Autowired
    private AmqpTemplate amqpTemplate;

    /**
     * 发送方
     * 这里使用了 A、B、C 三个队列绑定到 Fanout 交换机上面，发送端的 routing_key 写任何字符都会被忽略：
     */
    @Test
    void sender() {
        this.amqpTemplate.convertAndSend("fanout_exchange", "", "Hello Fanout!");
    }

    /**
     * 接收方A
     */
    @RabbitListener(queues = "messageA")
    @RabbitHandler
    void receiverA(String message) {
        System.out.println("receiverA接收消息；" + message);
    }

    /**
     * 接收方B
     */
    @RabbitListener(queues = "messageB")
    @RabbitHandler
    void receiverB(String message) {
        System.out.println("receiverB接收消息；" + message);
    }

    /**
     * 接收方C
     */
    @RabbitListener(queues = "messageC")
    @RabbitHandler
    void receiverC(String message) {
        System.out.println("receiverC接收消息；" + message);
    }
}
