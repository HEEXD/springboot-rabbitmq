package com.hxd.springbootrabbitmq;

import org.junit.jupiter.api.Test;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * Topic Exchange
 */
@SpringBootTest
public class TopicExchangeTests {

    @Autowired
    private AmqpTemplate amqpTemplate;

    @Test
    void sender() {
        // 向名称为 topicExchange 的交换机发送满足规则为 "topic.message" 的消息
        amqpTemplate.convertAndSend("topic_exchange", "topic.message", "Hello Topic!");
    }

    @RabbitListener(queues = "topic_message_a")
    @RabbitHandler
    void receiverA(String msg) {
        System.out.println("我可以监听到规则为topic.message的消息：" + msg);
    }

    @RabbitListener(queues = "topic_message_b")
    @RabbitHandler
    void receiverB(String msg) {
        System.out.println("我可以监听到规则为topic.#的消息：" + msg);
    }

}
